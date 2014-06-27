package com.ips.payroll.balance.converter;

import au.com.bytecode.opencsv.CSVWriter;
import com.ips.payroll.balance.PropertyDescriptorComparator;
import com.ips.payroll.balance.exceptions.BlunderException;
import com.ips.payroll.balance.model.Deduccion;
import com.ips.payroll.balance.model.HorasExtras;
import com.ips.payroll.balance.model.Incapacidad;
import com.ips.payroll.balance.model.Percepcion;
import com.ips.payroll.balance.model.enums.DeduccionType;
import com.ips.payroll.balance.model.enums.HorasExtrasType;
import com.ips.payroll.balance.model.enums.IncapacidadType;
import com.ips.payroll.balance.model.enums.PercepcionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ReportItemToCsv
        extends BeanToCsv
{

    private static final Logger LOG = LoggerFactory.getLogger(ReportItemToCsv.class);

    public ReportItemToCsv(boolean writeHeaders, boolean writeDeprecated)
    {
        super(writeHeaders, writeDeprecated);
    }


    @Override
    public void writeBean(CSVWriter writer, Object bean,
                          List<PropertyDescriptor> descriptors)
    {
        LOG.debug("writeBean");
        try
        {
            List<String> values = new ArrayList<String>();
            for (PropertyDescriptor myPropertyDescriptor : descriptors)
            {
                Type mytype = myPropertyDescriptor.getReadMethod().getGenericReturnType();
                ParameterizedType genericReturnType = null;
                Class<?> type = null;
                if (mytype instanceof ParameterizedType)
                {

                    genericReturnType = (ParameterizedType) mytype;
                    type = (Class<?>) (genericReturnType.getActualTypeArguments()[0]); // could be class or interface

                    LOG.debug("class = {}, {}",
                            type.isAssignableFrom(PercepcionType.class),
                            type);
                }

                if (myPropertyDescriptor.getPropertyType().isAssignableFrom(Map.class) &&
                        type.isAssignableFrom(PercepcionType.class))
                {
                    LOG.debug("myPropertyDescriptor = {}", myPropertyDescriptor.getReadMethod().getGenericReturnType());

                    //LOG.debug("class = {}", genericReturnType.getActualTypeArguments());

                    for (PercepcionType myPercepcionType : PercepcionType.values())
                    {
                        Object value = myPropertyDescriptor.getReadMethod().invoke(bean, new Object[]{});
                        Map<PercepcionType, Percepcion> myMap = (Map<PercepcionType, Percepcion>) value;
                        LOG.debug(myPercepcionType.name() + myMap);
                        Percepcion myPercepcion = myMap.get(myPercepcionType);
                        LOG.debug("{}", myPercepcion);
                        if (myMap.get(myPercepcionType) == null)
                        {
                            values.add("");
                            values.add("");
                            continue;
                        }
                        values.add((myMap.get(myPercepcionType).getImporteExento().toString()));
                        values.add((myMap.get(myPercepcionType).getImporteGravado().toString()));
                    }
                } else if (myPropertyDescriptor.getPropertyType().isAssignableFrom(Map.class) &&
                        type.isAssignableFrom(DeduccionType.class))
                {
                    LOG.debug("myPropertyDescriptor = {}", myPropertyDescriptor.getReadMethod().getGenericReturnType());

                    for (DeduccionType myDeduccionType : DeduccionType.values())
                    {
                        Object value = myPropertyDescriptor.getReadMethod().invoke(bean, new Object[]{});
                        Map<DeduccionType, Deduccion> myMap = (Map<DeduccionType, Deduccion>) value;
                        LOG.debug(myDeduccionType.name() + myMap);
                        Deduccion myDeduccion = myMap.get(myDeduccionType);
                        LOG.debug("{}", myDeduccion);
                        if (myMap.get(myDeduccionType) == null)
                        {
                            values.add("");
                            values.add("");
                            continue;
                        }
                        values.add((myMap.get(myDeduccionType).getImporteExento().toString()));
                        values.add((myMap.get(myDeduccionType).getImporteGravado().toString()));
                    }
                } else if (myPropertyDescriptor.getPropertyType().isAssignableFrom(Map.class) &&
                        type.isAssignableFrom(IncapacidadType.class))
                {
                    for (IncapacidadType myIncapacidadType : IncapacidadType.values())
                    {
                        Object value = myPropertyDescriptor.getReadMethod().invoke(bean, new Object[]{});
                        Map<IncapacidadType, Incapacidad> myMap = (Map<IncapacidadType, Incapacidad>) value;
                        LOG.debug(myIncapacidadType.name() + myMap);
                        Incapacidad myIncapacidad = myMap.get(myIncapacidadType);
                        LOG.debug("{}", myIncapacidad);
                        if (myMap.get(myIncapacidadType) == null)
                        {
                            values.add("");
                            values.add("");
                            continue;
                        }
                        values.add((myMap.get(myIncapacidadType).getDescuento().toString()));
                        values.add((myMap.get(myIncapacidadType).getDiasIncapacidad().toString()));
                    }
                } else if (myPropertyDescriptor.getPropertyType().isAssignableFrom(Map.class) &&
                        type.isAssignableFrom(HorasExtrasType.class))
                {
                    LOG.debug("myPropertyDescriptor = {}", myPropertyDescriptor.getReadMethod().getGenericReturnType());

                    for (HorasExtrasType myHorasExtrasType : HorasExtrasType.values())
                    {
                        Object value = myPropertyDescriptor.getReadMethod().invoke(bean, new Object[]{});
                        Map<HorasExtrasType, HorasExtras> myMap = (Map<HorasExtrasType, HorasExtras>) value;
                        LOG.debug(myHorasExtrasType.name() + myMap);
                        HorasExtras myHorasExtras = myMap.get(myHorasExtrasType);
                        LOG.debug("{}", myHorasExtras);
                        if (myMap.get(myHorasExtrasType) == null)
                        {
                            values.add("");
                            values.add("");
                            values.add("");
                            continue;
                        }
                        values.add(String.valueOf(myMap.get(myHorasExtrasType).getDias()));
                        values.add(String.valueOf(myMap.get(myHorasExtrasType).getHorasExtra()));
                        values.add(myMap.get(myHorasExtrasType).getImportePagado().toString());
                    }
                } else
                {
                    Object value = myPropertyDescriptor.getReadMethod().invoke(bean, new Object[]{});
                    values.add(value == null ? "" : value.toString());
                }
            }
            if (isWriteHeaders() && !isWroteHeader())
            {
                writeHeaders(writer, descriptors);
            }
            LOG.debug("{}", values);
            writer.writeNext(values.toArray(new String[]{}));
            writer.flush();
        } catch (Exception e)
        {
            throw new BlunderException("Error writing bean", e);
        }
    }

    protected List<PropertyDescriptor> resolvePropertyDescriptors(Class<?> beanClass, Class<?> stopClass)
    {
        List<PropertyDescriptor> myPropertyDescriptors = super.resolvePropertyDescriptors(beanClass, stopClass);
        LOG.debug("Before Ordered {}", myPropertyDescriptors);
        Collections.sort(myPropertyDescriptors, new PropertyDescriptorComparator());
        LOG.debug("After Ordered {}", myPropertyDescriptors);

        return myPropertyDescriptors;
    }
}