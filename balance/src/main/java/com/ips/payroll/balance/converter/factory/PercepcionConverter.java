package com.ips.payroll.balance.converter.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ips.payroll.balance.model.Percepcion;
import com.ips.payroll.balance.model.enums.PercepcionType;

public class PercepcionConverter
    implements BaseFactory<Map<PercepcionType, Percepcion>, List<String>>
{

    @Override
    public List<String> convertMap(Map<PercepcionType, Percepcion> aSoure)
    {
        List<String> myReturn = new ArrayList<String>();
        for (PercepcionType myPercepcionType : PercepcionType.values())
        {
            if (aSoure.get(myPercepcionType) == null)
            {
                myReturn.add(DEFAULT_NULL_VALUE);
                myReturn.add(DEFAULT_NULL_VALUE);
                continue;
            }
            myReturn.add(aSoure.get(myPercepcionType).getImporteExento().toString());
            myReturn.add(aSoure.get(myPercepcionType).getImporteGravado().toString());
        }
        
        return myReturn;
    }
}
