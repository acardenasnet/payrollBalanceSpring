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
    GMA("Seguro de Gastos Medicos Adicional", "004", "Otros"),
    INTPEMP("Intereses prestamo empresa", "004", "Otros"),
    PRESTAMO("Prestamo Empresa", "004", "Otros"),
    PENALIME("Pension Alimenticia", "007", "Pensión alimenticia"),
    INFONAVI("Prestamo INFONAVIT", "010", "Pago pot crédito de vivienda"),
    FONACOT("Prestamos FONACOT", "011", "Pago de abonos infonacot"),
    FOAHOEMP("Fondo Ahorro Empleado", "018", "Cuotas para la constitución y fomento de sociedades cooperativas y de cajas de ahorro"),
    PRESCAHO("Prestamo Caja de Ahorro", "018", "Cuotas para la constitución y fomento de sociedades cooperativas y de cajas de ahorro");

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
