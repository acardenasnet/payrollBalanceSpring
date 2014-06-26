package com.ips.payroll.balance.converter;

import java.util.HashMap;
import java.util.Map;

import mx.gob.sat.nomina.Nomina;
import mx.gob.sat.nomina.Nomina.Percepciones;

import org.springframework.core.convert.converter.Converter;

import com.ips.payroll.balance.model.Percepcion;
import com.ips.payroll.balance.model.enums.PercepcionType;

public class PercepcionesToListPercepcion
implements Converter<Nomina.Percepciones,Map<PercepcionType, Percepcion>>
{

	public PercepcionesToListPercepcion() 
	{
		// empty
	}

	@Override
	public Map<PercepcionType, Percepcion> convert(Percepciones aPercepciones) 
	{
		Map<PercepcionType, Percepcion> myReturn = new HashMap<PercepcionType, Percepcion>();
		
		for (mx.gob.sat.nomina.Nomina.Percepciones.Percepcion myPercepcion : aPercepciones.getPercepcion())
		{
			try
			{
				Percepcion myPercepcionToAdd = new Percepcion();
				myPercepcionToAdd.setImporteExento(myPercepcion.getImporteExento());
				myPercepcionToAdd.setImporteGravado(myPercepcion.getImporteGravado());
				
				myReturn.put(PercepcionType.valueOf(myPercepcion.getClave()),myPercepcionToAdd);
			}
			catch (IllegalArgumentException e)
			{
				continue; //FIXME: So far continue with next perceptions, we need handle this way.
			}
		}
		
		return myReturn;
	}

}
