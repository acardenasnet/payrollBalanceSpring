package com.ips.payroll.balance.service.service;

import com.ips.payroll.balance.exceptions.PayrollException;
import com.ips.payroll.balance.model.Deduccion;
import com.ips.payroll.balance.model.HorasExtras;
import com.ips.payroll.balance.model.Incapacidad;
import com.ips.payroll.balance.model.Percepcion;
import com.ips.payroll.balance.model.ReportItem;
import com.ips.payroll.balance.model.enums.DeduccionType;
import com.ips.payroll.balance.model.enums.HorasExtrasType;
import com.ips.payroll.balance.model.enums.IncapacidadType;
import com.ips.payroll.balance.model.enums.PercepcionType;
import com.ips.payroll.balance.service.api.NominaService;
import mx.gob.sat.cfd._3.Comprobante;
import mx.gob.sat.nomina.Nomina;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by acardenas on 6/23/14.
 */
@Service
public class NominaServiceBean
        implements NominaService
{
    private static final Logger LOG = LoggerFactory.getLogger(NominaServiceBean.class);

    private ConversionService conversionService;

    @Autowired
    public NominaServiceBean(ConversionService conversionService)
    {
        this.conversionService = conversionService;
    }

    @Override
    public ReportItem createNomina(InputStream anInputStream)
    {
        ReportItem myReturn = new ReportItem();
        try
        {
            //File myFile = new File("/home/acardenas/Dropbox/ips/xml-nomina/done/1-QNC-QNC-2014-11-00107.xml");

            JAXBContext context = JAXBContext.newInstance("mx.gob.sat.cfd._3:mx.gob.sat.nomina");
            Unmarshaller u = context.createUnmarshaller();

            Comprobante myComprobante = (Comprobante) u.unmarshal(anInputStream);

            Nomina myNomina = null;
            List<Object> myObjectList = myComprobante.getComplemento().getAny();
            for (Object myObject : myObjectList)
            {
                if (myObject instanceof Nomina)
                {
                    myNomina = (Nomina) myObject;
                }
            }

            if (myNomina == null)
            {
                throw new RuntimeException("Nomina not Match");
            }

            myReturn = conversionService.convert(myNomina, ReportItem.class);
            Map<PercepcionType, Percepcion> myPercepciones = conversionService.convert(
                    myNomina.getPercepciones(), Map.class);
            Map<DeduccionType, Deduccion> myDeducciones = conversionService.convert(
                    myNomina.getDeducciones(), Map.class);
            Map<IncapacidadType, Incapacidad> myIncapacidad = conversionService.convert(
                    myNomina.getDeducciones(), Map.class);
            Map<HorasExtrasType, HorasExtras> myHorasExtras = conversionService.convert(
                    myNomina.getDeducciones(), Map.class);

            myReturn.setPercepciones(myPercepciones);
            myReturn.setDeducciones(myDeducciones);
            myReturn.setIncapacidades(myIncapacidad);
//            myReturn.setHorasExtras(myHorasExtras);

        }
        catch (JAXBException e)
        {
            LOG.error("Unable to Parse XML ", e);
            throw new PayrollException("XML invalid for this application", e);
        }

        return myReturn;
    }

}
