package com.ips.payroll.balance.model.enums;

/**
 * Created by acardenas on 6/26/14.
 */
public enum DeduccionType
{
    RCI("Retencion Cuota IMSS", "001", "IMSS");

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
