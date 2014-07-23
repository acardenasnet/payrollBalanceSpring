package com.ips.payroll.balance.service.api;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.io.OutputStream;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.WebApplicationContext;

import com.ips.payroll.balance.model.ReportItem;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
@PrepareForTest(FileCopyUtils.class)
public class FileControllerTest
{
    private MockMvc mockMvc;
    public @Rule PowerMockRule rule = new PowerMockRule();
    
    @Mock
    private FileCopyUtils clazz;
    
    @Mock
    private CsvService<ReportItem> mockCsvService;    

    @Autowired
    @InjectMocks
    protected WebApplicationContext wac;

    public FileControllerTest()
    {
        // empty
    }
    
    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = webAppContextSetup(this.wac).build();
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

        when(mockCsvService.convertToCsv(anyListOf(ReportItem.class))).thenThrow(new RuntimeException());
        mockMvc.perform(post("/controller/process"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-disposition",  "attachment; filename=\"nomina.csv\""))
                .andExpect(content().string(""));
    }

}
