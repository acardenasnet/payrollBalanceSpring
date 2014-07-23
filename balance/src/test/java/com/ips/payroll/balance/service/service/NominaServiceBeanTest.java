package com.ips.payroll.balance.service.service;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ips.payroll.balance.exceptions.PayrollException;
import com.ips.payroll.balance.model.ReportItem;
import com.ips.payroll.balance.service.api.CsvService;

@RunWith(MockitoJUnitRunner.class)
public class NominaServiceBeanTest
{
    @InjectMocks
    NominaServiceBean nominaServiceBean;
    
    @Mock
    private CsvService<ReportItem> mockCsvService;    
    
    @Before
    public void setup()
    {
        nominaServiceBean = new NominaServiceBean(); 
    }

    @Test(expected = PayrollException.class)
    public void simpleExceptionTest() throws Exception
    {
        nominaServiceBean.createNomina(new ByteArrayInputStream("TEST".getBytes()));
    }

}
