package com.ips.payroll.balance.model.enums;

/**
 * Created by acardenas on 6/26/14.
 */
public enum DeduccionType
{
    RCI("Retencion Cuota IMSS", "001", "Seguridad social"),
    IMPURETE("Retencion Cuota IMSS", "002", "ISR"),
    ISPTAGUI("ISPT AGUI Y PV", "002", "ISR"),
    ISPTCAR("ISPT A CARGO (AJUSTE ANUAL)", "002", "ISR"),
    ISPTFAV("ISPT A FAVOR (AJUSTE ANUAL)", "002", "ISR"),
    ISPTFIN("ISPT Finiquito", "002", "ISR"),
    ISPTPTU("Impuesto PTU", "002", "ISR"),
    OTRDEDUC("Otras Deducciones", "004", "Otros"),
    GMA("Seguro de Gastos Medicos Adicional", "004", "Otros")
    ;

    private String description;
    private String satCode;
    private String satDescription;


    private DeduccionType(String aDescription, String aSatCode, String aSatDescription)
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
