package com.ips.payroll.balance.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class Deduccion 
{
    protected int tipoDeduccion;
    protected String clave;
    protected String concepto;
    protected BigDecimal importeGravado;
    protected BigDecimal importeExento;
	public int getTipoDeduccion() {
		return tipoDeduccion;
	}
	public void setTipoDeduccion(int tipoDeduccion) {
		this.tipoDeduccion = tipoDeduccion;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public BigDecimal getImporteGravado() {
		return importeGravado;
	}
	public void setImporteGravado(BigDecimal importeGravado) {
		this.importeGravado = importeGravado;
	}
	public BigDecimal getImporteExento() {
		return importeExento;
	}
	public void setImporteExento(BigDecimal importeExento) {
		this.importeExento = importeExento;
	}

    
}
