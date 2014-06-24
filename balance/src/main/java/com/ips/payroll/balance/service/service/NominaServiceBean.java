package com.ips.payroll.balance.service.service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import mx.gob.sat.nomina.NominaDocument;
import mx.gob.sat.nomina.impl.NominaDocumentImpl;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ips.payroll.balance.service.api.NominaService;

/**
 * Created by acardenas on 6/23/14.
 */
@Service
public class NominaServiceBean
        implements NominaService
{
	private static final Logger LOG = LoggerFactory.getLogger(NominaServiceBean.class);
	
	 private Collection<XmlHandlerAbstract < ? >> handlers =
		        new HashSet<XmlHandlerAbstract< ? >>();
	 
	    private static final javax.xml.namespace.QName NOMINA$0 = 
	            new javax.xml.namespace.QName("http://www.sat.gob.mx/nomina", "Nomina");
	 
public NominaServiceBean() {
	handlers.add(new NominaHandler());
	handlers.add(new ComprobanteHandler());
}	 

    @Override
    public void createNomina()
    {
    	
    	try {
    		
    		XmlObject myDocument = XmlObject.Factory.parse(new File("/home/acardenas/Dropbox/ips/xml-nomina/done/1-QNC-QNC-2014-11-00107.xml"));
		
    		
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}
