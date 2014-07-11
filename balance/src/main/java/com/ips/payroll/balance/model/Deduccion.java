package com.ips.payroll.balance.model;

import java.math.BigDecimal;

public class Deduccion
{
    private BigDecimal importeGravado;
    private BigDecimal importeExento;

    public Deduccion()
    {
        // empty
    }

    public BigDecimal getImporteGravado()
    {
        return importeGravado;
    }

    public void setImporteGravado(BigDecimal anImporteGravado)
    {
        importeGravado = anImporteGravado;
    }

    public BigDecimal getImporteExento()
    {
        return importeExento;
    }

    public void setImporteExento(BigDecimal anImporteExento)
    {
        importeExento = anImporteExento;
    }
}
