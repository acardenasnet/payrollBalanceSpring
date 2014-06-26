package com.ips.payroll.balance.service.service;

import au.com.bytecode.opencsv.CSVWriter;
import com.ips.payroll.balance.converter.BeanToCsv;
import com.ips.payroll.balance.model.ReportItem;
import com.ips.payroll.balance.service.api.CsvService;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by acardenas on 6/26/14.
 */
@Service
public class CsvServiceBean
    implements CsvService<ReportItem>
{

    @Override
    public String convertToCsv(ReportItem aBean)
    {


        return aBean.getCurp();
    }
}
