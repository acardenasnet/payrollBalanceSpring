package com.ips.payroll.balance.service.service;

import mx.gob.sat.cfd.x3.ComprobanteDocument;
import mx.gob.sat.cfd.x3.TUbicacion;
import mx.gob.sat.cfd.x3.TUbicacionFiscal;
import mx.gob.sat.nomina.NominaDocument;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

public class ComprobanteHandler
        implements XmlHandlerAbstract<ComprobanteDocument>
{

    private static final Logger LOG = LoggerFactory.getLogger(ComprobanteHandler.class);

    private Collection<XmlHandlerAbstract<?>> handlers =
            new HashSet<XmlHandlerAbstract<?>>();

    private static final javax.xml.namespace.QName NOMINA$0 =
            new javax.xml.namespace.QName("http://www.sat.gob.mx/nomina", "Nomina");

    public ComprobanteHandler()
    {
        handlers.add(new NominaHandler());
    }

    @Override
    public Class<ComprobanteDocument> handles()
    {
        return ComprobanteDocument.class;
    }

    @Override
    public void handle(ComprobanteDocument aRequest) throws IOException
    {
        LOG.debug("handle {}", aRequest.getComprobante().getComplemento().getDomNode().getFirstChild());

        try
        {
            //Datos exraidos del comprobante XML

            ComprobanteDocument.Comprobante comprobante = aRequest.getComprobante();
            ComprobanteDocument.Comprobante.Emisor emisor=comprobante.getEmisor();
            ComprobanteDocument.Comprobante.Emisor.RegimenFiscal rf=emisor.getRegimenFiscalArray(0);//pocision del arreglo
            ComprobanteDocument.Comprobante.Receptor receptor=comprobante.getReceptor();
            TUbicacionFiscal domicilioFiscal=emisor.getDomicilioFiscal();
            TUbicacion domicilioFiscalR=receptor.getDomicilio();
            TUbicacion expedidoEn=emisor.getExpedidoEn();
            ComprobanteDocument.Comprobante.Complemento complemento=comprobante.getComplemento();
            LOG.debug("Nomina {}", complemento.selectChildren(NOMINA$0)[0].schemaType());
            LOG.debug("Nomina {}", complemento.selectChildren(NOMINA$0)[0].substitute(NOMINA$0,NominaDocument.type).xmlText());
            LOG.debug("Nomina {}", complemento.substitute(NOMINA$0,NominaDocument.type));
            LOG.debug("Nomina {}", complemento);
            NominaDocument nominaDocument=NominaDocument.Factory.parse(complemento.selectChildren(NOMINA$0)[0].toString());
            NominaDocument.Nomina nomina=nominaDocument.getNomina();

            LOG.debug("Nomina {}", nomina);

            // myDocument = XmlString.Factory.parse(aRequest.getComprobante().getComplemento().getDomNode());
			XmlObject myDocument = XmlObject.Factory.parse(aRequest.getComprobante().getComplemento().getDomNode());
//			XmlObject[] myDocuments = XmlObject.Factory.parse((aRequest.getComprobante().getComplemento().toString())).selectChildren(NOMINA$0);
//			XmlObject myDocument = myDocuments[0];
            XmlOptions myXmlOptions = new XmlOptions();
            myXmlOptions.setDocumentType(NominaDocument.type);


            myDocument = XmlObject.Factory.parse(myDocument.getDomNode());

            LOG.debug("myDocument {}", myDocument.xmlText());

            XmlCursor cursor = myDocument.newCursor();
            LOG.debug("cursor child {}", cursor.toChild(NOMINA$0));
            while (cursor.toNextSelection())
            {
                LOG.debug("cursor value {}", cursor.getTextValue());
            }


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
            }
        } catch (XmlException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


}
