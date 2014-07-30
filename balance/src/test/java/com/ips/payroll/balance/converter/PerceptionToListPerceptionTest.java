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

import com.ips.payroll.balance.model.Percepcion;
import com.ips.payroll.balance.model.enums.PercepcionType;

@RunWith(MockitoJUnitRunner.class)
public class PerceptionToListPerceptionTest 
{
    private PerceptionToListPerception perceptionToListPerception; 
    
    @Before
    public void setup()
    {
        perceptionToListPerception = new PerceptionToListPerception();
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void nullConvert()
    {
        Map<PercepcionType, Percepcion> myResult =
                perceptionToListPerception.convert(null);
        Assert.assertEquals(0, myResult.size());
    }
    
    @Test
    public void oneValue()
    {
        Nomina.Percepciones myPerceptions = new Nomina.Percepciones();
        Nomina.Percepciones.Percepcion myPerception = new Nomina.Percepciones.Percepcion();
        
        myPerception.setClave(PercepcionType.SALABASE.name());
        myPerception.setImporteExento(new BigDecimal(42));
        myPerception.setImporteGravado(new BigDecimal(42));
        myPerception.setTipoPercepcion(1);
        
        myPerceptions.getPercepcion().add(myPerception);
        
        Map<PercepcionType, Percepcion> myResult =
                perceptionToListPerception.convert(myPerceptions);
        
        Assert.assertEquals(1, myResult.size());
    }
    
    @Test
    public void dummyCode()
    {
        Nomina.Percepciones myPerceptions = new Nomina.Percepciones();
        Nomina.Percepciones.Percepcion myPerception = new Nomina.Percepciones.Percepcion();
        
        myPerception.setClave("Dummy");
        myPerception.setImporteExento(new BigDecimal(42));
        myPerception.setImporteGravado(new BigDecimal(42));
        myPerception.setTipoPercepcion(1);
        
        myPerceptions.getPercepcion().add(myPerception);
        
        Map<PercepcionType, Percepcion> myResult =
                perceptionToListPerception.convert(myPerceptions);
        
        Assert.assertEquals(0, myResult.size());
    }

}
