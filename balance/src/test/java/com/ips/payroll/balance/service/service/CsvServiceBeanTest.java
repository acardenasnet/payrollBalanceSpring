package com.ips.payroll.balance.service.service;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import au.com.bytecode.opencsv.CSVWriter;

import com.ips.payroll.balance.converter.ReportItemToCsv;
import com.ips.payroll.balance.exceptions.PayrollException;
import com.ips.payroll.balance.model.ReportItem;
import com.ips.payroll.balance.service.api.CsvService;

@RunWith(MockitoJUnitRunner.class)
public class CsvServiceBeanTest
{
    
    private CsvService<ReportItem> csvService;
    
    @Mock
    private CSVWriter mockCSVWriter;
    
    @Mock
    private ReportItemToCsv reportItemToCsv;
    
    @Before
    public void setup()
    {
        CsvServiceBean myCsvServiceBean =  new  CsvServiceBean();
        myCsvServiceBean.init();
        myCsvServiceBean.setWriter(mockCSVWriter);
        csvService = myCsvServiceBean;
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void simpleTest() throws Exception
    {
        csvService.convertToCsv(new ArrayList<ReportItem>());
    }
        
    @Test
    public void simpleDataTest() throws Exception
    {
        String outputExpected = 
                "\"antiguedad\",\"banco\",\"clabe\",\"curp\",\"departamento\",\"fechaFinalPago\",\"fechaInicialPago\",\"fechaInicioRelLaboral\",\"fechaPago\",\"numDiasPagados\",\"numEmpleado\",\"numSeguridadSocial\",\"periodicidadPago\",\"puesto\",\"registroPatronal\",\"riesgoPuesto\",\"salarioBaseCotApor\",\"salarioDiarioIntegrado\",\"tipoContrato\",\"tipoJornada\",\"tipoRegimen\",\"version\",\"Retencion Cuota IMSS-Excento\",\"Retencion Cuota IMSS-Gravable\",\"Retencion Cuota IMSS-Excento\",\"Retencion Cuota IMSS-Gravable\",\"ISPT AGUI Y PV-Excento\",\"ISPT AGUI Y PV-Gravable\",\"ISPT A CARGO (AJUSTE ANUAL)-Excento\",\"ISPT A CARGO (AJUSTE ANUAL)-Gravable\",\"ISPT A FAVOR (AJUSTE ANUAL)-Excento\",\"ISPT A FAVOR (AJUSTE ANUAL)-Gravable\",\"ISPT Finiquito-Excento\",\"ISPT Finiquito-Gravable\",\"Impuesto PTU-Excento\",\"Impuesto PTU-Gravable\",\"Otras Deducciones-Excento\",\"Otras Deducciones-Gravable\",\"Seguro de Gastos Medicos Adicional-Excento\",\"Seguro de Gastos Medicos Adicional-Gravable\",\"Intereses prestamo empresa-Excento\",\"Intereses prestamo empresa-Gravable\",\"Prestamo Empresa-Excento\",\"Prestamo Empresa-Gravable\",\"Pension Alimenticia-Excento\",\"Pension Alimenticia-Gravable\",\"Prestamo INFONAVIT-Excento\",\"Prestamo INFONAVIT-Gravable\",\"Prestamos FONACOT-Excento\",\"Prestamos FONACOT-Gravable\",\"Fondo Ahorro Empleado-Excento\",\"Fondo Ahorro Empleado-Gravable\",\"Prestamo Caja de Ahorro-Excento\",\"Prestamo Caja de Ahorro-Gravable\",\"Retroactivo-Excento\",\"Retroactivo-Gravable\",\"Sueldo-Excento\",\"Sueldo-Gravable\",\"Vacaciones Proporcionales-Excento\",\"Vacaciones Proporcionales-Gravable\",\"Vacaciones pendientes-Excento\",\"Vacaciones pendientes-Gravable\",\"Aguinaldo-Excento\",\"Aguinaldo-Gravable\",\"Aguinaldo Proporcional-Excento\",\"Aguinaldo Proporcional-Gravable\",\"PTU-Excento\",\"PTU-Gravable\",\"Incapacidad Pagada por la Empresa-Excento\",\"Incapacidad Pagada por la Empresa-Gravable\",\"Premio por Puntualidad-Excento\",\"Premio por Puntualidad-Gravable\",\"Devolución INFONAVIT-Excento\",\"Devolución INFONAVIT-Gravable\",\"Ajuste anual de impuestos-Excento\",\"Ajuste anual de impuestos-Gravable\",\"Subsidio para el empleo-Excento\",\"Subsidio para el empleo-Gravable\",\"Prima Vacacional-Excento\",\"Prima Vacacional-Gravable\",\"Prima Vacacional Proporcional-Excento\",\"Prima Vacacional Proporcional-Gravable\",\"Liquidación Prima de Antigüedad-Excento\",\"Liquidación Prima de Antigüedad-Gravable\",\"Indemnizacion Especial-Excento\",\"Indemnizacion Especial-Gravable\",\"Indemnización-Excento\",\"Indemnización-Gravable\",\"Liquidación 20 Dias por Año-Excento\",\"Liquidación 20 Dias por Año-Gravable\",\"Liquidación 3 Meses de Sueldo-Excento\",\"Liquidación 3 Meses de Sueldo-Gravable\",\"Premio de Asistencia-Excento\",\"Premio de Asistencia-Gravable\",\"Bono productividad-Excento\",\"Bono productividad-Gravable\",\"Gratificación-Excento\",\"Gratificación-Gravable\",\"Otras percepciones-Excento\",\"Otras percepciones-Gravable\",\"incapacidades\"\n" +
                 "\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"99999\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"0\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"\n";        
        ArrayList<ReportItem> myReportItems = new ArrayList<ReportItem>();
        ReportItem myReportItem = new ReportItem();
        myReportItem.setNumEmpleado("99999");
        myReportItems.add(myReportItem);
        
        byte[] myBytes = csvService.convertToCsv(myReportItems);
        
        Assert.assertEquals(outputExpected, new String(myBytes));
    }
    
    @Test
    public void twoDataTest() throws Exception
    {
        String outputExpected = 
                "\"antiguedad\",\"banco\",\"clabe\",\"curp\",\"departamento\",\"fechaFinalPago\",\"fechaInicialPago\",\"fechaInicioRelLaboral\",\"fechaPago\",\"numDiasPagados\",\"numEmpleado\",\"numSeguridadSocial\",\"periodicidadPago\",\"puesto\",\"registroPatronal\",\"riesgoPuesto\",\"salarioBaseCotApor\",\"salarioDiarioIntegrado\",\"tipoContrato\",\"tipoJornada\",\"tipoRegimen\",\"version\",\"Retencion Cuota IMSS-Excento\",\"Retencion Cuota IMSS-Gravable\",\"Retencion Cuota IMSS-Excento\",\"Retencion Cuota IMSS-Gravable\",\"ISPT AGUI Y PV-Excento\",\"ISPT AGUI Y PV-Gravable\",\"ISPT A CARGO (AJUSTE ANUAL)-Excento\",\"ISPT A CARGO (AJUSTE ANUAL)-Gravable\",\"ISPT A FAVOR (AJUSTE ANUAL)-Excento\",\"ISPT A FAVOR (AJUSTE ANUAL)-Gravable\",\"ISPT Finiquito-Excento\",\"ISPT Finiquito-Gravable\",\"Impuesto PTU-Excento\",\"Impuesto PTU-Gravable\",\"Otras Deducciones-Excento\",\"Otras Deducciones-Gravable\",\"Seguro de Gastos Medicos Adicional-Excento\",\"Seguro de Gastos Medicos Adicional-Gravable\",\"Intereses prestamo empresa-Excento\",\"Intereses prestamo empresa-Gravable\",\"Prestamo Empresa-Excento\",\"Prestamo Empresa-Gravable\",\"Pension Alimenticia-Excento\",\"Pension Alimenticia-Gravable\",\"Prestamo INFONAVIT-Excento\",\"Prestamo INFONAVIT-Gravable\",\"Prestamos FONACOT-Excento\",\"Prestamos FONACOT-Gravable\",\"Fondo Ahorro Empleado-Excento\",\"Fondo Ahorro Empleado-Gravable\",\"Prestamo Caja de Ahorro-Excento\",\"Prestamo Caja de Ahorro-Gravable\",\"Retroactivo-Excento\",\"Retroactivo-Gravable\",\"Sueldo-Excento\",\"Sueldo-Gravable\",\"Vacaciones Proporcionales-Excento\",\"Vacaciones Proporcionales-Gravable\",\"Vacaciones pendientes-Excento\",\"Vacaciones pendientes-Gravable\",\"Aguinaldo-Excento\",\"Aguinaldo-Gravable\",\"Aguinaldo Proporcional-Excento\",\"Aguinaldo Proporcional-Gravable\",\"PTU-Excento\",\"PTU-Gravable\",\"Incapacidad Pagada por la Empresa-Excento\",\"Incapacidad Pagada por la Empresa-Gravable\",\"Premio por Puntualidad-Excento\",\"Premio por Puntualidad-Gravable\",\"Devolución INFONAVIT-Excento\",\"Devolución INFONAVIT-Gravable\",\"Ajuste anual de impuestos-Excento\",\"Ajuste anual de impuestos-Gravable\",\"Subsidio para el empleo-Excento\",\"Subsidio para el empleo-Gravable\",\"Prima Vacacional-Excento\",\"Prima Vacacional-Gravable\",\"Prima Vacacional Proporcional-Excento\",\"Prima Vacacional Proporcional-Gravable\",\"Liquidación Prima de Antigüedad-Excento\",\"Liquidación Prima de Antigüedad-Gravable\",\"Indemnizacion Especial-Excento\",\"Indemnizacion Especial-Gravable\",\"Indemnización-Excento\",\"Indemnización-Gravable\",\"Liquidación 20 Dias por Año-Excento\",\"Liquidación 20 Dias por Año-Gravable\",\"Liquidación 3 Meses de Sueldo-Excento\",\"Liquidación 3 Meses de Sueldo-Gravable\",\"Premio de Asistencia-Excento\",\"Premio de Asistencia-Gravable\",\"Bono productividad-Excento\",\"Bono productividad-Gravable\",\"Gratificación-Excento\",\"Gratificación-Gravable\",\"Otras percepciones-Excento\",\"Otras percepciones-Gravable\",\"incapacidades\"\n" +
                 "\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"99999\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"0\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"\n" +
                 "\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"99998\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"0\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"\n";
        ArrayList<ReportItem> myReportItems = new ArrayList<ReportItem>();
        
        ReportItem myReportItem = new ReportItem();
        myReportItem.setNumEmpleado("99999");
        ReportItem myReportItem2 = new ReportItem();
        myReportItem2.setNumEmpleado("99998");        
        
        myReportItems.add(myReportItem);
        myReportItems.add(myReportItem2);
        
        byte[] myBytes = csvService.convertToCsv(myReportItems);
        
        Assert.assertEquals(outputExpected, new String(myBytes));
        
    }
    
    @Test(expected = PayrollException.class)
    public void simpleExceptionTest() throws Exception
    {
        Mockito.doThrow(new IOException())
            .when(mockCSVWriter).close();
        ((CsvServiceBean) csvService).setWriter(mockCSVWriter);
        csvService.convertToCsv(new ArrayList<ReportItem>());
    }
}
