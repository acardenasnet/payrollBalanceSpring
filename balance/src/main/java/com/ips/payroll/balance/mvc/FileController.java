package com.ips.payroll.balance.mvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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

import com.ips.payroll.balance.model.ReportItem;
import com.ips.payroll.balance.service.api.CsvService;
import com.ips.payroll.balance.service.api.NominaService;

@Controller
@RequestMapping("/controller")
public class FileController
{
    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private NominaService nominaService;

    @Autowired
    private CsvService<ReportItem> csvService;

    private LinkedList<FileMeta> files = new LinkedList<FileMeta>();
    private FileMeta fileMeta = null;
    private List<ReportItem> reportItems = new LinkedList<ReportItem>();
    

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
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public
    @ResponseBody
    LinkedList<FileMeta> upload(MultipartHttpServletRequest request, HttpServletResponse response)
    {
    	ReportItem myReportItem = new ReportItem();
        //1. build an iterator
        Iterator<String> myIterator = request.getFileNames();
        MultipartFile myMultipartFile = null;

        //2. get each file
        while (myIterator.hasNext())
        {

            //2.1 get next MultipartFile
            myMultipartFile = request.getFile(myIterator.next());
            LOG.debug(myMultipartFile.getOriginalFilename() + " uploaded! " + files.size());

            //2.2 if files > 10 remove the first from the list
            if (files.size() >= 10)
                files.pop();

            //2.3 create new fileMeta
            fileMeta = new FileMeta();
            fileMeta.setFileName(myMultipartFile.getOriginalFilename());
            fileMeta.setFileSize(myMultipartFile.getSize() / 1024 + " Kb");
            fileMeta.setFileType(myMultipartFile.getContentType());

            try
            {
                fileMeta.setBytes(myMultipartFile.getBytes());

                // copy file to local disk (make sure the path "e.g. D:/temp/files" exists)
                //FileCopyUtils.copy(myMultipartFile.getBytes(), new FileOutputStream("files/" + myMultipartFile.getOriginalFilename()));

                

                myReportItem = nominaService.createNomina(myMultipartFile.getInputStream());
                
                
//                byte[] myFileContent = csvService.convertToCsv(myReportItem);
//                File myFile = File.createTempFile("nomina", ".csv");

//                FileOutputStream myFileOutputStream = new FileOutputStream(myFile);
//                myFileOutputStream.write(myFileContent);
//                myFileOutputStream.close();
//
//                byte[] myFileNameEncoded = Base64.encodeBase64(myFile.getAbsolutePath().getBytes());
//                LOG.debug("Encode {}", new String(myFileNameEncoded));
//                myFileMetaResponse.setDownloadName(new String(myFileNameEncoded));
                fileMeta.setSuccess(true);
            } 
            catch (IOException e)
            {
                LOG.error("Exception IOException", e);
                fileMeta.setSuccess(false);
            }
            //2.4 add to files
            reportItems.add(myReportItem);
            LOG.debug("reportItems = {}", reportItems);
            files.add(fileMeta);
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
    @RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
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
        } 
        catch (IOException e)
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
    @RequestMapping(value = "/get/reset", method = RequestMethod.GET)
    public String reset()
    {
    	fileMeta = null;
    	files = new LinkedList<FileMeta>();
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
    @RequestMapping(value = "/process", method = RequestMethod.POST)
    public void  process(HttpServletResponse response)
    {
    	LOG.info("process starting ...");
        if (LOG.isDebugEnabled())
        {
        	for (ReportItem myReportItemDebug: reportItems)
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
    	}
    	catch (IOException e)
    	{
    		LOG.error("unable to create CSV file,  please see the stcktrace", e);
    	}
    }
    
}