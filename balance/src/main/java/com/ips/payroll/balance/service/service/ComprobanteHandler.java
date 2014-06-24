package com.ips.payroll.balance.service.service;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import mx.gob.sat.cfd.x3.ComprobanteDocument;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComprobanteHandler 
	implements XmlHandlerAbstract<ComprobanteDocument>
{
	
	private static final Logger LOG = LoggerFactory.getLogger(ComprobanteHandler.class);
	
	 private Collection<XmlHandlerAbstract < ? >> handlers =
		        new HashSet<XmlHandlerAbstract< ? >>();
	 
	    private static final javax.xml.namespace.QName NOMINA$0 = 
	            new javax.xml.namespace.QName("http://www.sat.gob.mx/nomina", "Nomina");

	public ComprobanteHandler() {
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
		
		try {
			XmlObject myDocument = XmlString.Factory.parse(aRequest.getComprobante().getComplemento().getDomNode());
//			XmlObject myDocument = XmlObject.Factory.parse(aRequest.getComprobante().getComplemento().getDomNode());
//			XmlObject[] myDocuments = XmlObject.Factory.parse((aRequest.getComprobante().getComplemento().toString())).selectChildren(NOMINA$0);
//			XmlObject myDocument = myDocuments[0];
			
			LOG.debug("myDocument {}", myDocument);
    		
    		XmlCursor cursor = myDocument.newCursor();
    	    while (cursor.toNextSelection())
    	    {
    	    	LOG.debug("cursor value {}", cursor.getTextValue());
    	    }
    		
			
			boolean myHandled = false;
            for (XmlHandlerAbstract myHandler : handlers)
            {

                Class< ? > myHandledClass = myHandler.handles();
                if (myHandledClass.isInstance(myDocument))
                {
                    myHandler.handle(myDocument);
                    myHandled = true;
                    break;
                }
            }
		} catch (XmlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	} 


}
