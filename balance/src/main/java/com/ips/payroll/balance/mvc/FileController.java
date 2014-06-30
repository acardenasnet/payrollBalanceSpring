package com.ips.payroll.balance.mvc;

import com.ips.payroll.balance.exceptions.PayrollException;
import com.ips.payroll.balance.model.ReportItem;
import com.ips.payroll.balance.service.api.CsvService;
import com.ips.payroll.balance.service.api.NominaService;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

@Controller
@RequestMapping("/")
@Scope("session")
public class FileController
{
    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private NominaService nominaService;

    @Autowired
    private CsvService<ReportItem> csvService;

    @Autowired
    private FileMetaResponse fileMetaResponse;

    private LinkedList<ReportItem> reportItems;

    @PostConstruct
    public void init()
    {
        reportItems = new LinkedList<ReportItem>();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(HttpSession sessionObj)
    {
        LOG.debug(sessionObj.getId());
        return "upload";
    }

    /**
     * ************************************************
     * URL: /rest/controller/upload
     * upload(): receives files
     *
     * @param request  : MultipartHttpServletRequest auto passed
     * @param response : HttpServletResponse auto passed
     * @return LinkedList<FileMeta> as json format
     * **************************************************
     */
    @RequestMapping(value = "/controller/upload", method = RequestMethod.POST)
    public
    @ResponseBody
    LinkedList<FileMeta> upload(MultipartHttpServletRequest request, HttpServletResponse response, HttpSession sessionObj)
    {
        LOG.debug(sessionObj.getId());
        LinkedList<FileMeta> files = fileMetaResponse.getFileMetas();
        ReportItem myReportItem = new ReportItem();

        Iterator<String> myIterator = request.getFileNames();
        MultipartFile myMultipartFile = null;

        while (myIterator.hasNext())
        {

            //2.1 get next MultipartFile
            myMultipartFile = request.getFile(myIterator.next());
            LOG.debug(myMultipartFile.getOriginalFilename() + " uploaded! " + files.size());

            //2.2 if files > 10 remove the first from the list
            if (files.size() >= 10)
                files.pop();

            //2.3 create new fileMeta
            FileMeta fileMeta = new FileMeta();
            fileMeta.setFileName(myMultipartFile.getOriginalFilename());
            LOG.debug("fileMeta = {}", myMultipartFile.getOriginalFilename());
            LOG.debug("fileMeta Bean= {}", fileMeta.getFileName());
            fileMeta.setFileSize(myMultipartFile.getSize() / 1024 + " Kb");
            fileMeta.setFileType(myMultipartFile.getContentType());

            try
            {
                fileMeta.setBytes(myMultipartFile.getBytes());
                LOG.debug("name before transform = {}", myMultipartFile.getOriginalFilename());
                LOG.debug("name before transform Bean= {}", fileMeta.getFileName());
                myReportItem = nominaService.createNomina(myMultipartFile.getInputStream());
                LOG.debug("name after transform = {}", myMultipartFile.getOriginalFilename());
                LOG.debug("name after transform bean = {}", fileMeta.getFileName());

                fileMeta.setSuccess(true);
            }
            catch (IOException e)
            {
                LOG.error("Exception IOException", e);
                fileMeta.setSuccess(false);
            } catch (PayrollException e)
            {
                LOG.error("Exception IOException", e);
                fileMeta.setSuccess(false);
            }
            //2.4 add to files
            reportItems.add(myReportItem);
            LOG.debug("reportItems = {}", reportItems);
            files.add(fileMeta);
            LOG.debug("filesMeta = {}", files);
            LOG.debug("fileMeta = {}", fileMeta);
            LOG.debug("Done !!");

        }

        // result will be like this
        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
        return files;

    }

    /**
     * ************************************************
     * URL: /rest/controller/get/{value}
     * get(): get file as an attachment
     *
     * @param response : passed by the server
     * @param value    : value from the URL
     * @return void
     * **************************************************
     */
    @RequestMapping(value = "/controller/get/{value}", method = RequestMethod.GET)
    public void get(HttpServletResponse response, @PathVariable String value)
    {
        try
        {
            LOG.debug("File {}", new String(Base64.decodeBase64(value)));
            FileInputStream myFileInputStream = new FileInputStream(new File(new String(Base64.decodeBase64(value))));
            //FileMeta getFile = files.get(Integer.parseInt(value));

            response.setContentType("text/csv");
            response.setHeader("Content-disposition", "attachment; filename=\"nomina.csv\"");
            FileCopyUtils.copy(myFileInputStream, response.getOutputStream());
        } catch (IOException e)
        {
            LOG.error(e.getCause().getMessage(), e);
        }
    }

    /**
     * ************************************************
     * URL: /rest/controller/get/reset
     * reset(): clean files List and meta data
     *
     * @return String
     * **************************************************
     */
    @RequestMapping(value = "/controller/get/reset", method = RequestMethod.GET)
    public String reset()
    {
        fileMetaResponse.setFileMetas(null);
        reportItems = new LinkedList<ReportItem>();

        return "redirect:/";
    }

    /**
     * ************************************************
     * URL: /rest/controller/process
     * process(): process files
     *
     * @param response : HttpServletResponse auto passed
     * @return LinkedList<FileMeta> as json format
     * **************************************************
     */
    @RequestMapping(value = "/controller/process", method = RequestMethod.POST)
    public void process(HttpServletResponse response)
    {
        LOG.info("process starting ...");
        if (LOG.isDebugEnabled())
        {
            for (ReportItem myReportItemDebug : reportItems)
            {
                LOG.debug("Emp No: {}", myReportItemDebug.getNumEmpleado());
            }
        }
        try
        {
            byte[] myFileContent = csvService.convertToCsv(reportItems);
            response.setContentType("text/csv");
            response.setHeader("Content-disposition", "attachment; filename=\"nomina.csv\"");
            FileCopyUtils.copy(myFileContent, response.getOutputStream());
        } catch (IOException e)
        {
            LOG.error("unable to create CSV file,  please see the stcktrace", e);
        }
    }

}