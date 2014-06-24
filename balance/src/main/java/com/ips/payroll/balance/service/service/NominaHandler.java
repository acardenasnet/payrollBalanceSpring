package com.ips.payroll.balance.service.service;

import java.io.IOException;

import mx.gob.sat.nomina.NominaDocument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NominaHandler
	implements XmlHandlerAbstract<NominaDocument>
{

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(NominaHandler.class); 

	@Override
	public Class<NominaDocument> handles() 
	{
		return NominaDocument.class;
	}

	@Override
	public void handle(NominaDocument aRequest)
			throws IOException 
	{
		LOG.debug("handle  CLABE{}", aRequest.getNomina().getCLABE());
		
	}

}
