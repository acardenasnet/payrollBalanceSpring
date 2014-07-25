package com.ips.payroll.balance.model;

import java.math.BigDecimal;

/**
 * Created by acardenas on 6/26/14.
 */
public class HorasExtras
{
    private int dias;
    private String tipoHoras;
    private int horasExtra;
    private BigDecimal importePagado;

    public int getDias()
    {
        return dias;
    }

    public void setDias(int aDias)
    {
        dias = aDias;
    }

    public String getTipoHoras()
    {
        return tipoHoras;
    }

    public void setTipoHoras(String aTipoHoras)
    {
        tipoHoras = aTipoHoras;
    }

    public int getHorasExtra()
    {
        return horasExtra;
    }

    public void setHorasExtra(int aHorasExtra)
    {
        horasExtra = aHorasExtra;
    }

    public BigDecimal getImportePagado()
    {
        return importePagado;
    }

    public void setImportePagado(BigDecimal anIimportePagado)
    {
        importePagado = anIimportePagado;
    }
}
