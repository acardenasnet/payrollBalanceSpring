package com.ips.payroll.balance.converter;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVWriter;

import com.ips.payroll.balance.exceptions.BlunderException;
import com.ips.payroll.balance.model.Percepcion;
import com.ips.payroll.balance.model.enums.PercepcionType;

public class ReportItemToCsv
	extends BeanToCsv
{
	
	private static final Logger LOG = LoggerFactory.getLogger(ReportItemToCsv.class);

	public ReportItemToCsv(boolean writeHeaders, boolean writeDeprecated) 
	{
		super(writeHeaders, writeDeprecated);
	}
	

	@Override
	public void writeBean(CSVWriter writer, Object bean,
			List<PropertyDescriptor> descriptors) 
	{
		LOG.debug("writeBean");
	      try {
		         List<String> values = new ArrayList<String>();
		         for ( PropertyDescriptor myPropertyDescriptor : descriptors ) 
		         {
		  
		        	if (myPropertyDescriptor.getPropertyType().isAssignableFrom(Map.class))
		        	{
		             	 LOG.debug("myPropertyDescriptor = {}", myPropertyDescriptor.getReadMethod().getGenericReturnType());
		             	Type mytype= myPropertyDescriptor.getReadMethod().getGenericReturnType();
		              	 LOG.debug("myPropertyDescriptor = {}", mytype.getClass());
			        	 
		        		for (PercepcionType myPercepcionType : PercepcionType.values())
		        		{
		        			Object value = myPropertyDescriptor.getReadMethod().invoke( bean, new Object[]{} );
		        			Map<PercepcionType, Percepcion> myMap = (Map<PercepcionType, Percepcion>) value;
		        			LOG.debug(myPercepcionType.name() + myMap);
		        			Percepcion myPercepcion = myMap.get(myPercepcionType);
		        			LOG.debug("{}", myPercepcion);
		        			if (myMap.get(myPercepcionType) == null)
		        			{
		        				values.add("");
		        				values.add("");
		        				continue;
		        			}
		        			values.add((myMap.get(myPercepcionType).getImporteExento().toString()));
		        			values.add((myMap.get(myPercepcionType).getImporteGravado().toString()));
		        		}
		        	}
		        	else
		        	{
			            Object value = myPropertyDescriptor.getReadMethod().invoke( bean, new Object[]{} );
			            values.add( value == null ? "" : value.toString() );
		        	}
		         }
		         if ( isWriteHeaders() && !isWroteHeader() ) 
		         {
		            writeHeaders( writer, descriptors );
		         }
		         LOG.debug("{}", values);
		         writer.writeNext( values.toArray( new String[]{} ) );
		         writer.flush();
		      } 
	      	catch ( Exception e ) 
	      	{
		         throw new BlunderException( "Error writing bean", e );
		    }
	}

}
