package com.ips.payroll.balance.service.service;

import com.ips.payroll.balance.service.api.NominaService;
import mx.gob.sat.cfd._3.Comprobante;
import mx.gob.sat.cfd.x3.ComprobanteDocument;
import mx.gob.sat.nomina.Nomina;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by acardenas on 6/23/14.
 */
@Service
public class NominaServiceBean
        implements NominaService
{
    private static final Logger LOG = LoggerFactory.getLogger(NominaServiceBean.class);

    private Collection<XmlHandlerAbstract<?>> handlers =
            new HashSet<XmlHandlerAbstract<?>>();

    private static final javax.xml.namespace.QName NOMINA$0 =
            new javax.xml.namespace.QName("http://www.sat.gob.mx/nomina", "Nomina");

    public NominaServiceBean()
    {
        handlers.add(new NominaHandler());
        handlers.add(new ComprobanteHandler());
    }

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


//            Nomina myNomina = (Nomina) u.unmarshal(myComprobante.getComplemento().getAny().get(0).toString());

            LOG.debug("Nomina {}", ((Nomina) myComprobante.getComplemento().getAny().get(0)).getPercepciones().getTotalGravado().toPlainString());

            ComprobanteDocument myDocument = ComprobanteDocument.Factory.parse(myFile);

            LOG.debug("XMLBEANS {}", editExistingDocWithSelectChildren(myDocument));
//            editExistingDocWithSelectChildren(myDocument);
            LOG.debug("XMLBEANS {}", myDocument.getComprobante().getComplemento());


/*
            boolean myHandled = false;
            for (XmlHandlerAbstract myHandler : handlers)
            {

                Class<?> myHandledClass = myHandler.handles();
                if (myHandledClass.isInstance(myDocument))
                {
                    myHandler.handle(myDocument);
                    myHandled = true;
                    break;
                }
            }*/

        } catch (XmlException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JAXBException e)
        {
            e.printStackTrace();
        }

    }

    public boolean editExistingDocWithSelectChildren(ComprobanteDocument rootDoc)
    {
        String namespaceUri = "http://xmlbeans.apache.org/samples/any";
        ComprobanteDocument.Comprobante root = rootDoc.getComprobante();

        // Select the <anyfoo> children of <root>.
        XmlObject[] stringElements =
                root.selectChildren(NOMINA$0);

        // If the element is there, replace it with another element.
        if (stringElements.length > 0)
        {
            XmlCursor editCursor = stringElements[0].newCursor();
            editCursor.removeXml();
            editCursor.beginElement(NOMINA$0);
            editCursor.insertChars("some other text");
            editCursor.dispose();
        }
        return rootDoc.validate();
    }
}
