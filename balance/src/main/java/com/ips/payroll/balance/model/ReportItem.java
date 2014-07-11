package com.ips.payroll.balance.model;

import com.ips.payroll.balance.model.enums.DeduccionType;
import com.ips.payroll.balance.model.enums.IncapacidadType;
import com.ips.payroll.balance.model.enums.PercepcionType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

public class ReportItem
{

    private Map<PercepcionType, Percepcion> percepciones;
    private Map<DeduccionType, Deduccion> deducciones;
    private Map<IncapacidadType, Incapacidad> incapacidades;
    //    private Map<HorasExtrasType, HorasExtras> horasExtras;
    private String version;
    private String registroPatronal;
    private String numEmpleado;
    private String curp;
    private int tipoRegimen;
    private String numSeguridadSocial;
    private Date fechaPago;
    private Date fechaInicialPago;
    private Date fechaFinalPago;
    private BigDecimal numDiasPagados;
    private String departamento;
    private BigInteger clabe;
    private Integer banco;
    private Date fechaInicioRelLaboral;
    private Integer antiguedad;
    private String puesto;
    private String tipoContrato;
    private String tipoJornada;
    private String periodicidadPago;
    private BigDecimal salarioBaseCotApor;
    private Integer riesgoPuesto;
    private BigDecimal salarioDiarioIntegrado;

    public Map<PercepcionType, Percepcion> getPercepciones()
    {
        return percepciones;
    }

    public void setPercepciones(Map<PercepcionType, Percepcion> aPercepciones)
    {
        percepciones = aPercepciones;
    }

    public Map<DeduccionType, Deduccion> getDeducciones()
    {
        return deducciones;
    }

    public void setDeducciones(Map<DeduccionType, Deduccion> aDeducciones)
    {
        deducciones = aDeducciones;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String aVersion)
    {
        version = aVersion;
    }

    public String getRegistroPatronal()
    {
        return registroPatronal;
    }

    public void setRegistroPatronal(String aRregistroPatronal)
    {
        registroPatronal = aRregistroPatronal;
    }

    public String getNumEmpleado()
    {
        return numEmpleado;
    }

    public void setNumEmpleado(String numEmpleado)
    {
        this.numEmpleado = numEmpleado;
    }

    public String getCurp()
    {
        return curp;
    }

    public void setCurp(String curp)
    {
        this.curp = curp;
    }

    public int getTipoRegimen()
    {
        return tipoRegimen;
    }

    public void setTipoRegimen(int tipoRegimen)
    {
        this.tipoRegimen = tipoRegimen;
    }

    public String getNumSeguridadSocial()
    {
        return numSeguridadSocial;
    }

    public void setNumSeguridadSocial(String numSeguridadSocial)
    {
        this.numSeguridadSocial = numSeguridadSocial;
    }

    public Date getFechaPago()
    {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago)
    {
        this.fechaPago = fechaPago;
    }

    public Date getFechaInicialPago()
    {
        return fechaInicialPago;
    }

    public void setFechaInicialPago(Date fechaInicialPago)
    {
        this.fechaInicialPago = fechaInicialPago;
    }

    public Date getFechaFinalPago()
    {
        return fechaFinalPago;
    }

    public void setFechaFinalPago(Date fechaFinalPago)
    {
        this.fechaFinalPago = fechaFinalPago;
    }

    public BigDecimal getNumDiasPagados()
    {
        return numDiasPagados;
    }

    public void setNumDiasPagados(BigDecimal numDiasPagados)
    {
        this.numDiasPagados = numDiasPagados;
    }

    public String getDepartamento()
    {
        return departamento;
    }

    public void setDepartamento(String departamento)
    {
        this.departamento = departamento;
    }

    public BigInteger getClabe()
    {
        return clabe;
    }

    public void setClabe(BigInteger clabe)
    {
        this.clabe = clabe;
    }

    public Integer getBanco()
    {
        return banco;
    }

    public void setBanco(Integer banco)
    {
        this.banco = banco;
    }

    public Date getFechaInicioRelLaboral()
    {
        return fechaInicioRelLaboral;
    }

    public void setFechaInicioRelLaboral(Date fechaInicioRelLaboral)
    {
        this.fechaInicioRelLaboral = fechaInicioRelLaboral;
    }

    public Integer getAntiguedad()
    {
        return antiguedad;
    }

    public void setAntiguedad(Integer antiguedad)
    {
        this.antiguedad = antiguedad;
    }

    public String getPuesto()
    {
        return puesto;
    }

    public void setPuesto(String puesto)
    {
        this.puesto = puesto;
    }

    public String getTipoContrato()
    {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato)
    {
        this.tipoContrato = tipoContrato;
    }

    public String getTipoJornada()
    {
        return tipoJornada;
    }

    public void setTipoJornada(String tipoJornada)
    {
        this.tipoJornada = tipoJornada;
    }

    public String getPeriodicidadPago()
    {
        return periodicidadPago;
    }

    public void setPeriodicidadPago(String periodicidadPago)
    {
        this.periodicidadPago = periodicidadPago;
    }

    public BigDecimal getSalarioBaseCotApor()
    {
        return salarioBaseCotApor;
    }

    public void setSalarioBaseCotApor(BigDecimal salarioBaseCotApor)
    {
        this.salarioBaseCotApor = salarioBaseCotApor;
    }

    public Integer getRiesgoPuesto()
    {
        return riesgoPuesto;
    }

    public void setRiesgoPuesto(Integer riesgoPuesto)
    {
        this.riesgoPuesto = riesgoPuesto;
    }

    public BigDecimal getSalarioDiarioIntegrado()
    {
        return salarioDiarioIntegrado;
    }

    public void setSalarioDiarioIntegrado(BigDecimal salarioDiarioIntegrado)
    {
        this.salarioDiarioIntegrado = salarioDiarioIntegrado;
    }

    public Map<IncapacidadType, Incapacidad> getIncapacidades()
    {
        return incapacidades;
    }

    public void setIncapacidades(Map<IncapacidadType, Incapacidad> incapacidades)
    {
        this.incapacidades = incapacidades;
    }

//    public Map<HorasExtrasType, HorasExtras> getHorasExtras()
//    {
//        return horasExtras;
//    }

    //  public void setHorasExtras(Map<HorasExtrasType, HorasExtras> horasExtras)
    //  {
    //      this.horasExtras = horasExtras;
    //  }

    @Override
    public String toString()
    {
        StringBuilder myStringBuilder = new StringBuilder();
        myStringBuilder.append("[Employee Number = ").append(getNumEmpleado())
                .append(", CURP = ").append(getCurp())
                .append("]");

        return myStringBuilder.toString();
    }
}
