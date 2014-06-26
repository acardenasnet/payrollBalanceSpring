package com.ips.payroll.balance.model.enums;

public enum PercepcionType
{
	RETROACT("Retroactivo", "001", "Sueldos, salarios, rayas y jornales"),
	SALABASE("Sueldo", "001", "Sueldos, salarios, rayas y jornales"),
	VAFIN("Vacaciones Proporcionales", "001", "Sueldos, salarios, rayas y jornales"),
	VAPEND("Vacaciones pendientes", "001", "Sueldos, salarios, rayas y jornales"),
	AGUINAL("Aguinaldo", "002", "Gratificación Anual"),
	BONOPUNT("Premio por Puntualidad", "010", "Premios por puntualidad"),
	BONOASIS("Premio por Puntualidad", "010", "Premios por puntualidad"),
	BONOPROD("Premio por Puntualidad", "010", "Premios por puntualidad")
	
	;
	
	private String description;
	private String satCode;
	private String satDescription;
	
	
	private PercepcionType(String aDescription, String aSatCode, String aSatDescription) 
	{
		description = aDescription;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public String getSatCode()
	{
		return satCode;
	}
	
	public String getSatDescription()
	{
		return satDescription;
	}

}
