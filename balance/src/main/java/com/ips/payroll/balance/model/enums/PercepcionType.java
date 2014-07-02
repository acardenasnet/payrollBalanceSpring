package com.ips.payroll.balance.model.enums;

public enum PercepcionType
{
    RETROACT("Retroactivo", "001", "Sueldos, salarios, rayas y jornales"),
    SALABASE("Sueldo", "001", "Sueldos, salarios, rayas y jornales"),
    VAFIN("Vacaciones Proporcionales", "001", "Sueldos, salarios, rayas y jornales"),
    VAPEND("Vacaciones pendientes", "001", "Sueldos, salarios, rayas y jornales"),
    AGUINAL("Aguinaldo", "002", "Gratificación Anual"),
    AGUIFIN("Aguinaldo Proporcional", "002", "Gratificación Anual"),
    PTU("PTU", "003", "Participación de los Trabajadores en las Utilidades (PTU)"),
    INCPAGEM("Incapacidad Pagada por la Empresa", "009", "Contribuciones a cargo del trabajador pagadas por el patrón"),
    BONOPUNT("Premio por Puntualidad", "010", "Premios por puntualidad"),
    DEVOINFO("Devolución INFONAVIT", "016", "Otros"),
    AJIMP("Ajuste anual de impuestos", "016", "Otros"),
    SUBEMPLE("Subsidio para el empleo", "017", "Subsidio para el empleo"),
    PRIMAVAC("Prima Vacacional", "021", "Prima Vacacional"),
    PRIMAFIN("Prima Vacacional Proporcional", "021", "Prima Vacacional"),
    LIQPRIMA("Liquidación Prima de Antigüedad", "022", "Prima por antigüedad"),
    GRATSERV("Indemnizacion Especial", "025", "Indemnizaciones"),
    INDEM("Indemnización", "025", "Indemnizaciones"),
    LIQDIAS("Liquidación 20 Dias por Año", "025", "Indemnizaciones"),
    LIQMESES("Liquidación 3 Meses de Sueldo", "025", "Indemnizaciones"),
    BONOASIS("Premio de Asistencia", "038", "Otros ingresos por salarios"),
    BONOPROD("Bono productividad", "038", "Premios por puntualidad"),
    GRATIF("Gratificación", "038", "Otros ingresos por salarios"),
    OTROINGR("Otras percepciones", "038", "Otros ingresos por salarios");

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
