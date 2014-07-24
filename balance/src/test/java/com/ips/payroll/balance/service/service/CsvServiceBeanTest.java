package com.ips.payroll.balance.service.service;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.ips.payroll.balance.model.ReportItem;
import com.ips.payroll.balance.service.api.CsvService;

@RunWith(MockitoJUnitRunner.class)
public class CsvServiceBeanTest
{
    
    private CsvService<ReportItem> csvService;
    
    @Before
    public void setup()
    {
        CsvServiceBean myCsvServiceBean =  new  CsvServiceBean();
        myCsvServiceBean.init();
        csvService = myCsvServiceBean;
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void simpleExceptionTest() throws Exception
    {
        csvService.convertToCsv(new ArrayList<ReportItem>());
    }
    

}
