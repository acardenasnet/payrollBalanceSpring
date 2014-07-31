package com.ips.payroll.balance.converter;

import com.ips.payroll.balance.model.Percepcion;
import com.ips.payroll.balance.model.enums.PercepcionType;
import mx.gob.sat.nomina.Nomina;
import mx.gob.sat.nomina.Nomina.Percepciones;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

public class PerceptionToListPerception
        implements Converter<Nomina.Percepciones, Map<PercepcionType, Percepcion>>
{
    @Override
    public Map<PercepcionType, Percepcion> convert(Percepciones aPercepciones)
    {
        Map<PercepcionType, Percepcion> myReturn = new HashMap<PercepcionType, Percepcion>();

        if (aPercepciones == null)
        {
            return myReturn;
        }

        for (Nomina.Percepciones.Percepcion myPercepcion : aPercepciones.getPercepcion())
        {
            try
            {
                Percepcion myPercepcionToAdd = new Percepcion();
                myPercepcionToAdd.setImporteExento(myPercepcion.getImporteExento());
                myPercepcionToAdd.setImporteGravado(myPercepcion.getImporteGravado());

                myReturn.put(PercepcionType.valueOf(myPercepcion.getClave()), myPercepcionToAdd);
            }
            catch (IllegalArgumentException e)
            {
                continue; //FIXME So far continue with next perceptions, we need handle this way.
            }
        }

        return myReturn;
    }

}
