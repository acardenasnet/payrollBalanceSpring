package com.ips.payroll.balance.mvc;

import com.ips.payroll.balance.jaxb.Nomina;
import com.ips.payroll.balance.service.api.NominaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by acardenas on 6/23/14.
 */
@Controller
@RequestMapping("/")
public class NominaController
{
    private static final Logger LOG = LoggerFactory.getLogger(NominaController.class);

    @Autowired
    private NominaService nominaService;

    public String test()
    {
        LOG.info("TST");
       return nominaService.createNomina(new InputStream()
       {
           @Override
           public int read() throws IOException
           {
               return 0;
           }
       }).getCURP();
    }
}
