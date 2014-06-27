package com.ips.payroll.balance.mvc;

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

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

@Controller
@RequestMapping("/controller")
@Scope(value = "request")
public class FileController
{
    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private NominaService nominaService;

    @Autowired
    private CsvService csvService;

    LinkedList<FileMeta> files = new LinkedList<FileMeta>();
    FileMeta fileMeta = null;

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
    FileMetaResponse upload(MultipartHttpServletRequest request, HttpServletResponse response)
    {

        FileMetaResponse myFileMetaResponse = new FileMetaResponse();
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

                LOG.debug("Done !!");

                byte[] myFileContent = csvService.convertToCsv(
                        nominaService.createNomina(myMultipartFile.getInputStream()));
                File myFile = File.createTempFile("nomina", ".csv");

                FileOutputStream myFileOutputStream = new FileOutputStream(myFile);
                myFileOutputStream.write(myFileContent);
                myFileOutputStream.close();

                byte[] myFileNameEncoded = Base64.encodeBase64(myFile.getAbsolutePath().getBytes());
                LOG.debug("Encode {}", new String(myFileNameEncoded));
                myFileMetaResponse.setDownloadName(new String(myFileNameEncoded));
                fileMeta.setSuccess(true);
            } catch (IOException e)
            {
                LOG.error("Exception IOException", e);
                fileMeta.setSuccess(false);
            }
            //2.4 add to files

            files.add(fileMeta);

        }

        // result will be like this
        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
        myFileMetaResponse.setFileMetas(files);
        return myFileMetaResponse;

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
            LOG.debug("File {}", String.valueOf(Base64.decodeBase64(value)));
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
}