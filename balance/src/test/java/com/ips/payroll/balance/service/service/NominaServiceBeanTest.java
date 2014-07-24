package com.ips.payroll.balance.service.service;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;

import com.ips.payroll.balance.exceptions.PayrollException;
import com.ips.payroll.balance.model.ReportItem;

@RunWith(MockitoJUnitRunner.class)
public class NominaServiceBeanTest
{  
    @InjectMocks
    NominaServiceBean nominaServiceBean;

    @Mock
    private ConversionService mockConversionService;
    
    @Before
    public void setup()
    {
        nominaServiceBean = new NominaServiceBean();
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = PayrollException.class)
    public void simpleExceptionTest() throws Exception
    {
        nominaServiceBean.createNomina(new ByteArrayInputStream("TEST".getBytes()));
    }
    
    @Test
    public void simpleTest() throws Exception
    {
        
        Mockito.when(mockConversionService.convert(Matchers.any(), Mockito.eq(ReportItem.class)  ))
            .thenReturn(new ReportItem());
        System.out.println(mockConversionService.convert("", ReportItem.class));
        nominaServiceBean.createNomina(getClass().getResourceAsStream("/1-test.xml"));
    }

}
