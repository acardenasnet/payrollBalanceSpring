package com.ips.payroll.balance.service.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.bytecode.opencsv.CSVWriter;

import com.ips.payroll.balance.converter.ReportItemToCsv;
import com.ips.payroll.balance.exceptions.PayrollException;
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
    private ByteArrayOutputStream outputStream;
    private CSVWriter writer;
    
    @Autowired
    private ReportItemToCsv reportItemToCsv;

    @PostConstruct
    public void init()
    {
        LOG.debug("Calling init");
        outputStream = new ByteArrayOutputStream();
        writer = new CSVWriter(new PrintWriter(outputStream));
        reportItemToCsv = new ReportItemToCsv(true, false);
    }

    public CSVWriter getWriter()
    {
        return writer;
    }

    public void setWriter(CSVWriter aWriter)
    {
        writer = aWriter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] convertToCsv(List<ReportItem> aBeans)
    {
        try
        {
            clean();

            for (ReportItem myReportItem : aBeans)
            {
                if (myReportItem != null)
                {
                    reportItemToCsv.writeBean(writer, myReportItem);
                }
            }
        }
        catch (IOException e)
        {
            LOG.error("convertToCsv IOException", e);
            throw new PayrollException(e);
        }

        return outputStream.toByteArray();
    }

    private void clean() throws IOException
    {
        LOG.debug("Close {}", writer);
        writer.close();
        outputStream = new ByteArrayOutputStream();
        writer = new CSVWriter(new PrintWriter(outputStream));
    }
}
