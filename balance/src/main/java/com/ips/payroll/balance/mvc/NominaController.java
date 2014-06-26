package com.ips.payroll.balance.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ips.payroll.balance.service.api.NominaService;

/**
 * Created by acardenas on 6/23/14.
 */
@Controller
public class NominaController
{
    private static final Logger LOG = LoggerFactory.getLogger(NominaController.class);

    @RequestMapping("/")
    public String test()
    {
        LOG.info("TST");
       return "index";
    }
}
