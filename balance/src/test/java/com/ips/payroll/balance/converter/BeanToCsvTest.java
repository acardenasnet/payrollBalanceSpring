package com.ips.payroll.balance.converter;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.ips.payroll.balance.exceptions.BlunderException;
import com.ips.payroll.balance.model.ReportItem;

import au.com.bytecode.opencsv.CSVWriter;

@RunWith(MockitoJUnitRunner.class)
public class BeanToCsvTest
{
    private BeanToCsv beanToCsv; 
    
    @Mock
    private CSVWriter mockCSVWriter;
    
    @Before
    public void setup()
    {
        beanToCsv = new BeanToCsv(true, true);
        MockitoAnnotations.initMocks(this);
    }
    
    @Test(expected = BlunderException.class)
    public void beanToCsvExceptionTest() throws Exception
    {
        Mockito.doThrow(new IOException())
            .when(mockCSVWriter).flush();
        beanToCsv.writeBean(mockCSVWriter, new ReportItem());
    }
    
    @Test
    public void beanToCsvTest() 
    {
        beanToCsv.writeBean(mockCSVWriter, new ReportItem());
    }
    
    @Test
    public void beanToCsvListTest() 
    {
        beanToCsv.writeBean(mockCSVWriter, new ArrayList<ReportItem>());
    }
}
