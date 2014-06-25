package com.ips.payroll.balance.service.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import mx.gob.sat.cfd._3.Comprobante;
import mx.gob.sat.nomina.Nomina;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import au.com.bytecode.opencsv.CSVWriter;

import com.ips.payroll.balance.service.api.NominaService;

/**
 * Created by acardenas on 6/23/14.
 */
@Service
public class NominaServiceBean
        implements NominaService
{
    private static final Logger LOG = LoggerFactory.getLogger(NominaServiceBean.class);


    private static final javax.xml.namespace.QName NOMINA$0 =
            new javax.xml.namespace.QName("http://www.sat.gob.mx/nomina", "Nomina");


    @Override
    public void createNomina()
    {

        try
        {
            File myFile = new File("/home/acardenas/Dropbox/ips/xml-nomina/done/1-QNC-QNC-2014-11-00107.xml");
            JAXBContext context = JAXBContext.newInstance("mx.gob.sat.cfd._3:mx.gob.sat.nomina");
            Unmarshaller u = context.createUnmarshaller();

            Comprobante myComprobante = (Comprobante) u.unmarshal(myFile);

            LOG.debug("Complemento {}", myComprobante.getReceptor().getNombre());

            LOG.debug("Complemento {}", myComprobante.getComplemento().getAny().get(0));


            Nomina myNomina = (Nomina) myComprobante.getComplemento().getAny().get(0);

            LOG.debug("Nomina {}", myNomina.getPercepciones().getTotalGravado().toPlainString());
            
            CSVWriter myCsvWriter = new CSVWriter(new FileWriter("output.csv"));
            
            List<String[]> myRecords = new ArrayList<String[]>();
            
            String[] myRecord = {myNomina.getNumEmpleado(), 
            		myNomina.getFechaPago().toGregorianCalendar().toString(),
            		};
            
            myRecords.add(myRecord);
            
            myCsvWriter.writeAll(myRecords);
            myCsvWriter.flush();
            
            myCsvWriter.close();


        } 
        catch (JAXBException e)
        {
            LOG.error("Unable to Parse XML ", e);
        } 
        catch (IOException e) 
        {
        	LOG.error("Unable to Write CSV ", e);
		}

    }

}
