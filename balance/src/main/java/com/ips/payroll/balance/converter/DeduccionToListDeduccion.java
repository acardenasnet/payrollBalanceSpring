package com.ips.payroll.balance.converter;

import java.util.HashMap;
import java.util.Map;

import mx.gob.sat.nomina.Nomina;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import com.ips.payroll.balance.model.Deduccion;
import com.ips.payroll.balance.model.enums.DeduccionType;

/**
 * Created by acardenas on 6/26/14.
 */
public class DeduccionToListDeduccion
        implements Converter<Nomina.Deducciones, Map<DeduccionType, Deduccion>>
{
    private static final Logger LOG = LoggerFactory.getLogger(DeduccionToListDeduccion.class);

    @Override
    public Map<DeduccionType, Deduccion> convert(Nomina.Deducciones aDeducciones)
    {
        Map<DeduccionType, Deduccion> myReturn = new HashMap<DeduccionType, Deduccion>();

        if (aDeducciones == null)
        {
            return myReturn;
        }

        for (Nomina.Deducciones.Deduccion myDeduccion : aDeducciones.getDeduccion())
        {
            try
            {
                Deduccion myDeduccionToAdd = new Deduccion();
                myDeduccionToAdd.setImporteExento(myDeduccion.getImporteExento());
                myDeduccionToAdd.setImporteGravado(myDeduccion.getImporteGravado());

                myReturn.put(DeduccionType.valueOf(myDeduccion.getClave()), myDeduccionToAdd);
                LOG.debug("Has been converted and added ({}, {})", myDeduccion.getClave(), myDeduccionToAdd);
            }
            catch (IllegalArgumentException e)
            {
                LOG.debug("Skip Code {}", myDeduccion.getClave());
                continue; //FIXME So far continue with next perceptions, we need handle this way.
            }
        }
        LOG.debug("convert Result = {}", myReturn);
        return myReturn;
    }
}
