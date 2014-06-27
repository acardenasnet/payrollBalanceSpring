package com.ips.payroll.balance.service.service;

import au.com.bytecode.opencsv.CSVWriter;
import com.ips.payroll.balance.converter.BeanToCsv;
import com.ips.payroll.balance.converter.ReportItemToCsv;
import com.ips.payroll.balance.model.ReportItem;
import com.ips.payroll.balance.service.api.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

/**
 * Created by acardenas on 6/26/14.
 */
@Service
public class CsvServiceBean
        implements CsvService<ReportItem>
{

    private static final Logger LOG = LoggerFactory.getLogger(CsvServiceBean.class);
    private CSVWriter writer;
    ByteArrayOutputStream outputStream;

    @PostConstruct
    public void init()
    {
        LOG.debug("Calling init");
        //writer = new CSVWriter(new FileWriter(new File("otput.csv")));
        outputStream = new ByteArrayOutputStream();
        writer = new CSVWriter(new PrintWriter(outputStream));
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
    public byte[] convertToCsv(ReportItem aBean)
    {
        BeanToCsv myBeanToCsv = new ReportItemToCsv(true, false);
        myBeanToCsv.writeBean(writer, aBean);
        return outputStream.toByteArray();
    }
}
