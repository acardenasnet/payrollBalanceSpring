package com.ips.payroll.balance.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class Deducciones 
{
    protected List<Deduccion> deduccion;
    protected BigDecimal totalGravado;
    protected BigDecimal totalExento;
	public List<Deduccion> getDeduccion() {
		return deduccion;
	}
	public void setDeduccion(List<Deduccion> deduccion) {
		this.deduccion = deduccion;
	}
	public BigDecimal getTotalGravado() {
		return totalGravado;
	}
	public void setTotalGravado(BigDecimal totalGravado) {
		this.totalGravado = totalGravado;
	}
	public BigDecimal getTotalExento() {
		return totalExento;
	}
	public void setTotalExento(BigDecimal totalExento) {
		this.totalExento = totalExento;
	}
    
    
}
