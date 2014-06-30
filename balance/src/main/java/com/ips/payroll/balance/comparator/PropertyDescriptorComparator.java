package com.ips.payroll.balance.comparator;

import com.ips.payroll.balance.model.enums.IncapacidadType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.Map;

/**
 * Created by acardenas on 6/26/14.
 */
public class PropertyDescriptorComparator
        implements Comparator<PropertyDescriptor>
{
    private static final Logger LOG = LoggerFactory.getLogger(PropertyDescriptorComparator.class);

    @Override
    public int compare(PropertyDescriptor o1, PropertyDescriptor o2)
    {
        Type mytype = o1.getReadMethod().getGenericReturnType();
        ParameterizedType genericReturnType = null;
        Class<?> type = null;
        if (mytype instanceof ParameterizedType)
        {
            LOG.debug("ParameterizedType");
            genericReturnType = (ParameterizedType) mytype;
            type = (Class<?>) (genericReturnType.getActualTypeArguments()[0]); // could be class or interface

            if (type.isAssignableFrom(IncapacidadType.class))
            {
                LOG.debug("ParameterizedType {}", type);
                return 99;
            }

        }

        mytype = o2.getReadMethod().getGenericReturnType();
        genericReturnType = null;
        type = null;
        if (mytype instanceof ParameterizedType)
        {
            LOG.debug("ParameterizedType");
            genericReturnType = (ParameterizedType) mytype;
            type = (Class<?>) (genericReturnType.getActualTypeArguments()[0]); // could be class or interface

            if (type.isAssignableFrom(IncapacidadType.class))
            {
                LOG.debug("ParameterizedType 2{}", type);
                return -99;
            }

        }

        if (o1.getPropertyType().isAssignableFrom(Map.class))
        {
            return 1;
        }

        if (o2.getPropertyType().isAssignableFrom(Map.class))
        {
            return -1;
        }
        return 0;
    }
}
