package com.ips.payroll.balance.converter;

import java.math.BigDecimal;
import java.util.Map;

import junit.framework.Assert;
import mx.gob.sat.nomina.Nomina;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.ips.payroll.balance.model.Deduccion;
import com.ips.payroll.balance.model.enums.DeduccionType;

@RunWith(MockitoJUnitRunner.class)
public class DeductionToListDeductionTest 
{
    private DeduccionToListDeduccion deduccionToListDeduccion; 
    
    @Before
    public void setup()
    {
        deduccionToListDeduccion = new DeduccionToListDeduccion();
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void nullConvert()
    {
        Map<DeduccionType, Deduccion> myResult =
                deduccionToListDeduccion.convert(null);
        Assert.assertEquals(0, myResult.size());
    }
    
    @Test
    public void oneValue()
    {
        Nomina.Deducciones myDeducciones = new Nomina.Deducciones();
        Nomina.Deducciones.Deduccion myDeduccion = new Nomina.Deducciones.Deduccion();
        
        myDeduccion.setClave(DeduccionType.RCI.name());
        myDeduccion.setImporteExento(new BigDecimal(42));
        myDeduccion.setImporteGravado(new BigDecimal(42));
        myDeduccion.setTipoDeduccion(1);
        
        myDeducciones.getDeduccion().add(myDeduccion);
        
        Map<DeduccionType, Deduccion> myResult =        
                deduccionToListDeduccion.convert(myDeducciones);
        
        Assert.assertEquals(1, myResult.size());
    }
    
    @Test
    public void dummyCode()
    {
        Nomina.Deducciones myDeducciones = new Nomina.Deducciones();
        Nomina.Deducciones.Deduccion myDeduccion = new Nomina.Deducciones.Deduccion();
        
        myDeduccion.setClave("Dummy");
        myDeduccion.setImporteExento(new BigDecimal(42));
        myDeduccion.setImporteGravado(new BigDecimal(42));
        myDeduccion.setTipoDeduccion(1);
        
        myDeducciones.getDeduccion().add(myDeduccion);
        
        Map<DeduccionType, Deduccion> myResult =        
                deduccionToListDeduccion.convert(myDeducciones);
        
        Assert.assertEquals(0, myResult.size());
    }

}
