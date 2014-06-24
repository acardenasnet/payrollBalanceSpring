package com.ips.payroll.balance.service.service;

import com.ips.payroll.balance.jaxb.Nomina;
import com.ips.payroll.balance.jaxb.ObjectFactory;
import com.ips.payroll.balance.service.api.NominaService;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.InputStream;

/**
 * Created by acardenas on 6/23/14.
 */
@Service
public class NominaServiceBean
        implements NominaService
{

    @Override
    public Nomina createNomina(InputStream anInputStream)
    {
        JAXBContext jaxbContext = null;
        try
        {
            jaxbContext = JAXBContext.newInstance(Nomina.class);

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Nomina ts = (Nomina) jaxbUnmarshaller.unmarshal(new File("/home/acardenas/Dropbox/ips/xml-nomina/done/nomina.xml"));

            System.out.println(ts);
            return ts;

        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }

        return null;

    }
}
