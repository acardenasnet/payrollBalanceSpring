package com.ips.payroll.balance.mvc.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.FileCopyUtils;

import com.ips.payroll.balance.model.ReportItem;
import com.ips.payroll.balance.mvc.FileMeta;
import com.ips.payroll.balance.mvc.FileMetaResponse;
import com.ips.payroll.balance.service.api.CsvService;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
@PrepareForTest(FileCopyUtils.class)
public class FileControllerTest
{
    
    private MockMvc mockMvc;

    @InjectMocks
    private FileController fileController;
    
    public @Rule PowerMockRule rule = new PowerMockRule();
    
    @Mock
    private FileMetaResponse mockFileMetaResponse;
    
    @Mock
    private CsvService<ReportItem> mockCsvService;    

    public FileControllerTest()
    {
        // empty
    }
    
    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(fileController).build();
    }

    @Test
    public void simple() throws Exception
    {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("upload"));
    }
    
    @Test
    public void testReset() throws Exception
    {
        mockMvc.perform(get("/controller/get/reset"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/"));
    }
    
    @Test
    public void testProcess() throws Exception
    {       
        PowerMockito.mockStatic(FileCopyUtils.class);
        FileCopyUtils.copy(any(byte[].class), any(OutputStream.class));
        
        mockMvc.perform(post("/controller/process"))                
                .andExpect(status().isOk())
                .andExpect(header().string("Content-disposition",  "attachment; filename=\"nomina.csv\""))
                .andExpect(content().string(""));
    }
    
    @Test
    public void testProcessException() throws Exception
    {

        PowerMockito.mockStatic(FileCopyUtils.class);
        PowerMockito.doThrow(new IOException())
            .when(FileCopyUtils.class);
         
        FileCopyUtils.copy(any(byte[].class), any(OutputStream.class));

        
        mockMvc.perform(post("/controller/process"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-disposition",  "attachment; filename=\"nomina.csv\""))
                .andExpect(content().string(""));
    }

    @Test
    public void testGetList() throws Exception
    {
        when(mockFileMetaResponse.getFileMetas()).thenCallRealMethod();
        
        when(mockCsvService.convertToCsv(anyListOf(ReportItem.class))).thenThrow(new RuntimeException());
        mockMvc.perform(get("/controller/get/list"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }
    
    @Test
    public void testGetListWithData() throws Exception
    {
        LinkedList<FileMeta> myFileMetaResponses = new LinkedList<FileMeta>();
        FileMeta myFileMeta = new FileMeta();
        myFileMeta.setFileName("JUnit");
        myFileMeta.setFileType("test");
        myFileMeta.setSuccess(true);
        
        myFileMetaResponses.add(myFileMeta);
        
        when(mockFileMetaResponse.getFileMetas()).thenReturn(myFileMetaResponses);
        
        mockMvc.perform(get("/controller/get/list"))
        .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$",Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].fileType",Matchers.is("test")))
                .andExpect(jsonPath("$[0].fileName", Matchers.is("JUnit")));
    }
    
    @Test
    public void testUpload() throws Exception
    {
        LinkedList<FileMeta> myFileMetaResponses = new LinkedList<FileMeta>();
        FileMeta myFileMeta = new FileMeta();
        myFileMeta.setFileName("JUnit");
        myFileMeta.setFileType("test");
        myFileMeta.setSuccess(true);
        
        myFileMetaResponses.add(myFileMeta);
        
        when(mockFileMetaResponse.getFileMetas()).thenReturn(myFileMetaResponses);
        
        mockMvc.perform(fileUpload("/controller/upload"))
        .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$",Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].fileType",Matchers.is("test")))
                .andExpect(jsonPath("$[0].fileName", Matchers.is("JUnit")));
    }
}
