package com.ips.payroll.balance.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;

import com.ips.payroll.balance.entity.Deducciones;
import com.ips.payroll.balance.entity.HorasExtras;
import com.ips.payroll.balance.entity.Incapacidades;
import com.ips.payroll.balance.entity.Percepciones;

public class ReportItem {

	private Percepciones percepciones;
	private Deducciones deducciones;
	private Incapacidades incapacidades;
	private HorasExtras horasExtras;
	private String version;
	private String registroPatronal;
	private String numEmpleado;
	private String curp;
	private int tipoRegimen;
	private String numSeguridadSocial;
	private Calendar fechaPago;
	private Calendar fechaInicialPago;
	private Calendar fechaFinalPago;
	private BigDecimal numDiasPagados;
	private String departamento;
	private BigInteger clabe;
	private Integer banco;
	private Calendar fechaInicioRelLaboral;
	private Integer antiguedad;
	private String puesto;
	private String tipoContrato;
	private String tipoJornada;
	private String periodicidadPago;
	private BigDecimal salarioBaseCotApor;
	private Integer riesgoPuesto;
	private BigDecimal salarioDiarioIntegrado;
	
	public Percepciones getPercepciones() {
		return percepciones;
	}
	public void setPercepciones(Percepciones percepciones) {
		this.percepciones = percepciones;
	}
	public Deducciones getDeducciones() {
		return deducciones;
	}
	public void setDeducciones(Deducciones deducciones) {
		this.deducciones = deducciones;
	}
	public Incapacidades getIncapacidades() {
		return incapacidades;
	}
	public void setIncapacidades(Incapacidades incapacidades) {
		this.incapacidades = incapacidades;
	}
	public HorasExtras getHorasExtras() {
		return horasExtras;
	}
	public void setHorasExtras(HorasExtras horasExtras) {
		this.horasExtras = horasExtras;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getRegistroPatronal() {
		return registroPatronal;
	}
	public void setRegistroPatronal(String registroPatronal) {
		this.registroPatronal = registroPatronal;
	}
	public String getNumEmpleado() {
		return numEmpleado;
	}
	public void setNumEmpleado(String numEmpleado) {
		this.numEmpleado = numEmpleado;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public int getTipoRegimen() {
		return tipoRegimen;
	}
	public void setTipoRegimen(int tipoRegimen) {
		this.tipoRegimen = tipoRegimen;
	}
	public String getNumSeguridadSocial() {
		return numSeguridadSocial;
	}
	public void setNumSeguridadSocial(String numSeguridadSocial) {
		this.numSeguridadSocial = numSeguridadSocial;
	}
	public Calendar getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Calendar fechaPago) {
		this.fechaPago = fechaPago;
	}
	public Calendar getFechaInicialPago() {
		return fechaInicialPago;
	}
	public void setFechaInicialPago(Calendar fechaInicialPago) {
		this.fechaInicialPago = fechaInicialPago;
	}
	public Calendar getFechaFinalPago() {
		return fechaFinalPago;
	}
	public void setFechaFinalPago(Calendar fechaFinalPago) {
		this.fechaFinalPago = fechaFinalPago;
	}
	public BigDecimal getNumDiasPagados() {
		return numDiasPagados;
	}
	public void setNumDiasPagados(BigDecimal numDiasPagados) {
		this.numDiasPagados = numDiasPagados;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public BigInteger getClabe() {
		return clabe;
	}
	public void setClabe(BigInteger clabe) {
		this.clabe = clabe;
	}
	public Integer getBanco() {
		return banco;
	}
	public void setBanco(Integer banco) {
		this.banco = banco;
	}
	public Calendar getFechaInicioRelLaboral() {
		return fechaInicioRelLaboral;
	}
	public void setFechaInicioRelLaboral(Calendar fechaInicioRelLaboral) {
		this.fechaInicioRelLaboral = fechaInicioRelLaboral;
	}
	public Integer getAntiguedad() {
		return antiguedad;
	}
	public void setAntiguedad(Integer antiguedad) {
		this.antiguedad = antiguedad;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public String getTipoContrato() {
		return tipoContrato;
	}
	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}
	public String getTipoJornada() {
		return tipoJornada;
	}
	public void setTipoJornada(String tipoJornada) {
		this.tipoJornada = tipoJornada;
	}
	public String getPeriodicidadPago() {
		return periodicidadPago;
	}
	public void setPeriodicidadPago(String periodicidadPago) {
		this.periodicidadPago = periodicidadPago;
	}
	public BigDecimal getSalarioBaseCotApor() {
		return salarioBaseCotApor;
	}
	public void setSalarioBaseCotApor(BigDecimal salarioBaseCotApor) {
		this.salarioBaseCotApor = salarioBaseCotApor;
	}
	public Integer getRiesgoPuesto() {
		return riesgoPuesto;
	}
	public void setRiesgoPuesto(Integer riesgoPuesto) {
		this.riesgoPuesto = riesgoPuesto;
	}
	public BigDecimal getSalarioDiarioIntegrado() {
		return salarioDiarioIntegrado;
	}
	public void setSalarioDiarioIntegrado(BigDecimal salarioDiarioIntegrado) {
		this.salarioDiarioIntegrado = salarioDiarioIntegrado;
	}

	
}
