package com.ips.payroll.balance.service.api;

import com.ips.payroll.balance.jaxb.Nomina;

import java.io.InputStream;

/**
 * Created by acardenas on 6/23/14.
 */
public interface NominaService
{
    Nomina createNomina(InputStream anInputStream);
}
