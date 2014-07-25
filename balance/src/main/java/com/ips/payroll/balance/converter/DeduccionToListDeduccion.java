package com.ips.payroll.balance.converter;

import com.ips.payroll.balance.model.Deduccion;
import com.ips.payroll.balance.model.enums.DeduccionType;
import mx.gob.sat.nomina.Nomina;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by acardenas on 6/26/14.
 */
public class DeduccionToListDeduccion
        implements Converter<Nomina.Deducciones, Map<DeduccionType, Deduccion>>
{
    public DeduccionToListDeduccion()
    {
        // empty
    }

    @Override
    public Map<DeduccionType, Deduccion> convert(Nomina.Deducciones aDeducciones)
    {
        Map<DeduccionType, Deduccion> myReturn = new HashMap<DeduccionType, Deduccion>();

        for (Nomina.Deducciones.Deduccion myDeduccion : aDeducciones.getDeduccion())
        {
            try
            {
                Deduccion myDeduccionToAdd = new Deduccion();
                myDeduccionToAdd.setImporteExento(myDeduccion.getImporteExento());
                myDeduccionToAdd.setImporteGravado(myDeduccion.getImporteGravado());

                myReturn.put(DeduccionType.valueOf(myDeduccion.getClave()), myDeduccionToAdd);
            }
            catch (IllegalArgumentException e)
            {
                continue; //FIXME So far continue with next perceptions, we need handle this way.
            }
        }

        return myReturn;
    }
}
