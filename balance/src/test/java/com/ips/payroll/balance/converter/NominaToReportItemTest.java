package com.ips.payroll.balance.converter;

import mx.gob.sat.nomina.Nomina;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.ips.payroll.balance.model.ReportItem;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

public class NominaToReportItemTest
{

    private NominaToReportItem nominaToReportItem; 
    
    @Before
    public void setup()
    {
        nominaToReportItem = new NominaToReportItem();
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void nullConvert()
    {
        ReportItem myResult =
        nominaToReportItem.convert(null);
        Assert.assertNotNull(myResult);
        Assert.assertNotNull(myResult.getDeducciones());
        Assert.assertNotNull(myResult.getPercepciones());
        Assert.assertNotNull(myResult.getIncapacidades());
        Assert.assertNull(myResult.getFechaInicialPago());
        Assert.assertNull(myResult.getFechaPago());
    }
    
    @Test
    public void oneValue()
    {
        Nomina myNomina = new Nomina();
        myNomina.setAntiguedad(3);
        myNomina.setNumEmpleado("42");
        
        ReportItem myResult =
        nominaToReportItem.convert(myNomina);
        Assert.assertNotNull(myResult);
        Assert.assertNotNull(myResult.getDeducciones());
        Assert.assertNotNull(myResult.getPercepciones());
        Assert.assertNotNull(myResult.getIncapacidades());
        Assert.assertNull(myResult.getFechaInicialPago());
        Assert.assertNull(myResult.getFechaPago());
        Assert.assertEquals("42", myResult.getNumEmpleado());
        
    }
    
    @Test
    public void oneValueWithDates()
    {
        Nomina myNomina = new Nomina();
        myNomina.setAntiguedad(3);
        myNomina.setNumEmpleado("42");
        myNomina.setFechaFinalPago(new XMLGregorianCalendarImpl());
        myNomina.setFechaInicialPago(new XMLGregorianCalendarImpl());
        myNomina.setFechaPago(new XMLGregorianCalendarImpl());
        myNomina.setFechaInicioRelLaboral(new XMLGregorianCalendarImpl());
        
        ReportItem myResult =
        nominaToReportItem.convert(myNomina);
        Assert.assertNotNull(myResult);
        Assert.assertNotNull(myResult.getDeducciones());
        Assert.assertNotNull(myResult.getPercepciones());
        Assert.assertNotNull(myResult.getIncapacidades());
        Assert.assertNotNull(myResult.getFechaFinalPago());
        Assert.assertNotNull(myResult.getFechaInicialPago());
        Assert.assertNotNull(myResult.getFechaPago());
        Assert.assertEquals(myNomina.getFechaPago().toGregorianCalendar().getTime(), 
                myResult.getFechaPago());
        Assert.assertNotNull(myResult.getFechaInicioRelLaboral());
        Assert.assertEquals("42", myResult.getNumEmpleado());
    }
}
