package com.ips.payroll.balance.model;

import java.math.BigDecimal;

/**
 * Created by acardenas on 6/26/14.
 */
public class Incapacidad
{
    private BigDecimal diasIncapacidad;
    private int tipoIncapacidad;
    private BigDecimal descuento;

    public BigDecimal getDiasIncapacidad()
    {
        return diasIncapacidad;
    }

    public void setDiasIncapacidad(BigDecimal aDiasIncapacidad)
    {
        diasIncapacidad = aDiasIncapacidad;
    }

    public int getTipoIncapacidad()
    {
        return tipoIncapacidad;
    }

    public void setTipoIncapacidad(int aTipoIncapacidad)
    {
        tipoIncapacidad = aTipoIncapacidad;
    }

    public BigDecimal getDescuento()
    {
        return descuento;
    }

    public void setDescuento(BigDecimal aDescuento)
    {
        descuento = aDescuento;
    }
}
