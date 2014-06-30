package com.ips.payroll.balance.service.service;

import au.com.bytecode.opencsv.CSVWriter;
import com.ips.payroll.balance.converter.BeanToCsv;
import com.ips.payroll.balance.converter.ReportItemToCsv;
import com.ips.payroll.balance.exceptions.PayrollException;
import com.ips.payroll.balance.model.ReportItem;
import com.ips.payroll.balance.service.api.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

    public void setWriter(CSVWriter aWriter)
    {
        this.writer = aWriter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] convertToCsv(ReportItem aBean)
    {
        BeanToCsv myBeanToCsv = new ReportItemToCsv(true, false);
        myBeanToCsv.writeBean(writer, aBean);
        return outputStream.toByteArray();
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

            BeanToCsv myBeanToCsv = new ReportItemToCsv(true, false);
            for (ReportItem myReportItem : aBeans)
            {
                if (myReportItem != null)
                {
                    myBeanToCsv.writeBean(writer, myReportItem);
                }
            }
        } catch (IOException e)
        {
            throw new PayrollException(e);
        }

        return outputStream.toByteArray();
    }

    private void clean() throws IOException
    {
        writer.close();
        outputStream = new ByteArrayOutputStream();
        writer = new CSVWriter(new PrintWriter(outputStream));
    }
}
