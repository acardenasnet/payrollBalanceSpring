package com.ips.payroll.balance.converter;

import mx.gob.sat.nomina.Nomina;

import org.springframework.core.convert.converter.Converter;

import com.ips.payroll.balance.model.ReportItem;

public class NominaToReportItem
	implements Converter<Nomina, ReportItem>
{

	@Override
	public ReportItem convert(Nomina aNomina) 
	{
		ReportItem myReturn = new ReportItem();
		
		myReturn.setAntiguedad(aNomina.getAntiguedad());
		myReturn.setBanco(aNomina.getBanco());
		myReturn.setClabe(aNomina.getCLABE());
		myReturn.setCurp(aNomina.getCURP());
		myReturn.setDepartamento(aNomina.getDepartamento());
		myReturn.setFechaFinalPago(aNomina.getFechaFinalPago().toGregorianCalendar().getTime());
		myReturn.setFechaInicialPago(aNomina.getFechaInicialPago().toGregorianCalendar().getTime());
		if (aNomina.getFechaInicioRelLaboral() != null)
		{
			myReturn.setFechaInicioRelLaboral(aNomina.getFechaInicioRelLaboral().toGregorianCalendar().getTime());
		}
		myReturn.setFechaPago(aNomina.getFechaPago().toGregorianCalendar().getTime());
		myReturn.setNumDiasPagados(aNomina.getNumDiasPagados());
		myReturn.setNumEmpleado(aNomina.getNumEmpleado());
		myReturn.setNumSeguridadSocial(aNomina.getNumSeguridadSocial());
		myReturn.setPeriodicidadPago(aNomina.getPeriodicidadPago());
		myReturn.setPuesto(aNomina.getPuesto());
		myReturn.setRegistroPatronal(aNomina.getRegistroPatronal());
		myReturn.setRiesgoPuesto(aNomina.getRiesgoPuesto());
		myReturn.setSalarioBaseCotApor(aNomina.getSalarioBaseCotApor());
		myReturn.setSalarioDiarioIntegrado(aNomina.getSalarioDiarioIntegrado());
		myReturn.setTipoContrato(aNomina.getTipoContrato());
		myReturn.setTipoJornada(aNomina.getTipoJornada());
		myReturn.setTipoRegimen(aNomina.getTipoRegimen());
		myReturn.setVersion(aNomina.getVersion());
		
		
		return myReturn;
	}

}
