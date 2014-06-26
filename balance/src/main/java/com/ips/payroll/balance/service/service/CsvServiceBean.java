package com.ips.payroll.balance.service.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import au.com.bytecode.opencsv.CSVWriter;

import com.ips.payroll.balance.converter.BeanToCsv;
import com.ips.payroll.balance.converter.ReportItemToCsv;
import com.ips.payroll.balance.model.ReportItem;
import com.ips.payroll.balance.service.api.CsvService;

/**
 * Created by acardenas on 6/26/14.
 */
@Service
public class CsvServiceBean
    implements CsvService<ReportItem>
{
	
	private static final Logger LOG = LoggerFactory.getLogger(CsvServiceBean.class);
	private CSVWriter writer;
	
	@PostConstruct
	public void init() 
	{
		try {
			LOG.debug("Calling init");
			writer =  new CSVWriter(new FileWriter(new File("otput.csv")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public CSVWriter getWriter() 
    {
		return writer;
	}

	public void setWriter(CSVWriter writer) 
	{
		this.writer = writer;
	}

	@Override
    public String convertToCsv(ReportItem aBean)
    {
    	BeanToCsv myBeanToCsv = new ReportItemToCsv(false, false);
    	myBeanToCsv.writeBean(writer, aBean);
        return "Done";
    }
}
