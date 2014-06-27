package com.ips.payroll.balance.model;

import java.math.BigDecimal;


public class Percepcion
{
    private BigDecimal importeGravado;
    private BigDecimal importeExento;

    public Percepcion()
    {
        // empty
    }

    public BigDecimal getImporteGravado()
    {
        return importeGravado;
    }

    public void setImporteGravado(BigDecimal importeGravado)
    {
        this.importeGravado = importeGravado;
    }

    public BigDecimal getImporteExento()
    {
        return importeExento;
    }

    public void setImporteExento(BigDecimal importeExento)
    {
        this.importeExento = importeExento;
    }


}
