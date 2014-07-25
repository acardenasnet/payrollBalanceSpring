package com.ips.payroll.balance.service.api;


import com.ips.payroll.balance.model.ReportItem;

import java.io.InputStream;

/**
 * Created by acardenas on 6/23/14.
 */
public interface NominaService
{
    ReportItem createNomina(InputStream anInputStream);
}
