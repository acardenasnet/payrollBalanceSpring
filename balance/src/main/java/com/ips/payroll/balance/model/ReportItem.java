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

    public void setNumEmpleado(String aNumEmpleado)
    {
        numEmpleado = aNumEmpleado;
    }

    public String getCurp()
    {
        return curp;
    }

    public void setCurp(String aCurp)
    {
        curp = aCurp;
    }

    public int getTipoRegimen()
    {
        return tipoRegimen;
    }

    public void setTipoRegimen(int aTipoRegimen)
    {
        tipoRegimen = aTipoRegimen;
    }

    public String getNumSeguridadSocial()
    {
        return numSeguridadSocial;
    }

    public void setNumSeguridadSocial(String aNumSeguridadSocial)
    {
        numSeguridadSocial = aNumSeguridadSocial;
    }

    public Date getFechaPago()
    {
        return new Date(fechaPago.getTime());
    }

    public void setFechaPago(Date aFechaPago)
    {
        fechaPago = aFechaPago;
    }

    public Date getFechaInicialPago()
    {
        return fechaInicialPago;
    }

    public void setFechaInicialPago(Date aPayStartDate)
    {
        fechaInicialPago = aPayStartDate;
    }

    public Date getFechaFinalPago()
    {
        return fechaFinalPago;
    }

    public void setFechaFinalPago(Date aPayEndDate)
    {
        fechaFinalPago = aPayEndDate;
    }

    public BigDecimal getNumDiasPagados()
    {
        return numDiasPagados;
    }

    public void setNumDiasPagados(BigDecimal aNumDiasPagados)
    {
        numDiasPagados = aNumDiasPagados;
    }

    public String getDepartamento()
    {
        return departamento;
    }

    public void setDepartamento(String aDepartament)
    {
        departamento = aDepartament;
    }

    public BigInteger getClabe()
    {
        return clabe;
    }

    public void setClabe(BigInteger aClabe)
    {
        clabe = aClabe;
    }

    public Integer getBanco()
    {
        return banco;
    }

    public void setBanco(Integer aBank)
    {
        banco = aBank;
    }

    public Date getFechaInicioRelLaboral()
    {
        return fechaInicioRelLaboral;
    }

    public void setFechaInicioRelLaboral(Date aFechaInicioRelLaboral)
    {
        fechaInicioRelLaboral = aFechaInicioRelLaboral;
    }

    public Integer getAntiguedad()
    {
        return antiguedad;
    }

    public void setAntiguedad(Integer anAntiguedad)
    {
        antiguedad = anAntiguedad;
    }

    public String getPuesto()
    {
        return puesto;
    }

    public void setPuesto(String aPuesto)
    {
        puesto = aPuesto;
    }

    public String getTipoContrato()
    {
        return tipoContrato;
    }

    public void setTipoContrato(String aContractType)
    {
        tipoContrato = aContractType;
    }

    public String getTipoJornada()
    {
        return tipoJornada;
    }

    public void setTipoJornada(String aTipoJornada)
    {
        tipoJornada = aTipoJornada;
    }

    public String getPeriodicidadPago()
    {
        return periodicidadPago;
    }

    public void setPeriodicidadPago(String aPeriodicidadPago)
    {
        periodicidadPago = aPeriodicidadPago;
    }

    public BigDecimal getSalarioBaseCotApor()
    {
        return salarioBaseCotApor;
    }

    public void setSalarioBaseCotApor(BigDecimal aSalarioBaseCotApor)
    {
        salarioBaseCotApor = aSalarioBaseCotApor;
    }

    public Integer getRiesgoPuesto()
    {
        return riesgoPuesto;
    }

    public void setRiesgoPuesto(Integer aRiesgoPuesto)
    {
        this.riesgoPuesto = aRiesgoPuesto;
    }

    public BigDecimal getSalarioDiarioIntegrado()
    {
        return salarioDiarioIntegrado;
    }

    public void setSalarioDiarioIntegrado(BigDecimal aSalarioDiarioIntegrado)
    {
        this.salarioDiarioIntegrado = aSalarioDiarioIntegrado;
    }

    public Map<IncapacidadType, Incapacidad> getIncapacidades()
    {
        return incapacidades;
    }

    public void setIncapacidades(Map<IncapacidadType, Incapacidad> anIncapacidades)
    {
        this.incapacidades = anIncapacidades;
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
