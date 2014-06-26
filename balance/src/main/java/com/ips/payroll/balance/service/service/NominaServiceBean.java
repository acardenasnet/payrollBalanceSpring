package com.ips.payroll.balance.service.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import mx.gob.sat.cfd._3.Comprobante;
import mx.gob.sat.nomina.Nomina;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.ips.payroll.balance.model.Percepcion;
import com.ips.payroll.balance.model.ReportItem;
import com.ips.payroll.balance.model.enums.PercepcionType;
import com.ips.payroll.balance.service.api.NominaService;

/**
 * Created by acardenas on 6/23/14.
 */
@Service
public class NominaServiceBean
        implements NominaService
{
    private static final Logger LOG = LoggerFactory.getLogger(NominaServiceBean.class);

    private ConversionService conversionService;
    
    @Autowired
    public NominaServiceBean(ConversionService conversionService) 
    {
        this.conversionService = conversionService;
    }

    @Override
    public ReportItem createNomina(InputStream anInputStream)
    {
        ReportItem myReturn = new ReportItem();
        try
        {
            //File myFile = new File("/home/acardenas/Dropbox/ips/xml-nomina/done/1-QNC-QNC-2014-11-00107.xml");

            JAXBContext context = JAXBContext.newInstance("mx.gob.sat.cfd._3:mx.gob.sat.nomina");
            Unmarshaller u = context.createUnmarshaller();

            Comprobante myComprobante = (Comprobante) u.unmarshal(anInputStream);

            LOG.debug("Complemento {}", myComprobante.getReceptor().getNombre());


            Nomina myNomina = null;
            List<Object> myObjectList = myComprobante.getComplemento().getAny();
            for (Object myObject : myObjectList)
            {
                if (myObject instanceof Nomina)
                {
                    myNomina = (Nomina) myObject;
                }
            }

            if (myNomina == null)
            {
                throw new RuntimeException("Nomina not Match");
            }

            myReturn = conversionService.convert(myNomina, ReportItem.class);
			Map<PercepcionType, Percepcion> myPercepciones = conversionService.convert(myNomina.getPercepciones(), Map.class);
            
            
            myReturn.setPercepciones(myPercepciones);

//            myReturn.setNumSeguridadSocial(myNomina.getNumSeguridadSocial());
//            myReturn.setCurp(myNomina.getCURP());
//            myReturn.setAntiguedad(myNomina.getAntiguedad());
//            myReturn.setDeducciones(new Deducciones());


        } catch (JAXBException e)
        {
            LOG.error("Unable to Parse XML ", e);
        }

        return myReturn;
    }

}
