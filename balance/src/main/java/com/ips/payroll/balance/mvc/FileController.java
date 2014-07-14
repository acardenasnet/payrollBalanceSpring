package com.ips.payroll.balance.mvc;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ips.payroll.balance.exceptions.PayrollException;
import com.ips.payroll.balance.model.ReportItem;
import com.ips.payroll.balance.service.api.CsvService;
import com.ips.payroll.balance.service.api.NominaService;

@Controller
@RequestMapping("/")
@Scope("session")
public class FileController
{
    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);
    private static final int ONE_KB_FROM_BYTES = 1024;

    @Autowired
    private NominaService nominaService;

    @Autowired
    private CsvService<ReportItem> csvService;

    @Autowired
    private FileMetaResponse fileMetaResponse;

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
    @ResponseBody
    public LinkedList<FileMeta> upload(MultipartHttpServletRequest request, HttpSession sessionObj)
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

            //TODO: Handler a big List or set a limit

            //2.3 create new fileMeta
            FileMeta fileMeta = new FileMeta();
            fileMeta.setFileName(myMultipartFile.getOriginalFilename());
            LOG.debug("fileMeta Bean= {}", fileMeta.getFileName());
            fileMeta.setFileSize(myMultipartFile.getSize() / ONE_KB_FROM_BYTES + " Kb");
            fileMeta.setFileType(myMultipartFile.getContentType());

            try
            {
                myReportItem = nominaService.createNomina(myMultipartFile.getInputStream());

                fileMeta.setSuccess(true);
                fileMetaResponse.getReportItems().add(myReportItem);
            }
            catch (IOException e)
            {
                LOG.error("Exception IOException", e);
                fileMeta.setSuccess(false);
            }
            catch (PayrollException e)
            {
                LOG.error("Exception IOException", e);
                fileMeta.setSuccess(false);
            }
            files.add(fileMeta);
            LOG.info("Done !!");

        }

        // result will be like this
        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
        return files;

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
        fileMetaResponse.setReportItems(null);

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

        try
        {
            byte[] myFileContent = csvService.convertToCsv(fileMetaResponse.getReportItems());
            response.setContentType("text/csv");
            response.setHeader("Content-disposition", "attachment; filename=\"nomina.csv\"");
            FileCopyUtils.copy(myFileContent, response.getOutputStream());
        }
        catch (IOException e)
        {
            LOG.error("unable to create CSV file,  please see the stackTrace", e);
        }
    }

    /**
     * ************************************************
     * URL: /rest/controller/get/list
     * upload(): receives files
     *
     * @return LinkedList<FileMeta> as json format
     * **************************************************
     */
    @RequestMapping(value = "/controller/get/list", method = RequestMethod.GET)
    public
    @ResponseBody
    LinkedList<FileMeta> getList()
    {
        return fileMetaResponse.getFileMetas();
    }
}
