package com.ips.payroll.balance.comparator;

import com.ips.payroll.balance.model.enums.IncapacidadType;

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

    @Override
    public int compare(PropertyDescriptor aPropertyDescriptor1, PropertyDescriptor aPropertyDescriptor2)
    {
        Type mytype = aPropertyDescriptor1.getReadMethod().getGenericReturnType();
        ParameterizedType genericReturnType = null;
        Class<?> type = null;
        if (mytype instanceof ParameterizedType)
        {
            genericReturnType = (ParameterizedType) mytype;
            type = (Class<?>) (genericReturnType.getActualTypeArguments()[0]); // could be class or interface

            if (type.isAssignableFrom(IncapacidadType.class))
            {
                return 99;
            }

        }

        mytype = aPropertyDescriptor2.getReadMethod().getGenericReturnType();
        genericReturnType = null;
        type = null;
        if (mytype instanceof ParameterizedType)
        {
            genericReturnType = (ParameterizedType) mytype;
            type = (Class<?>) (genericReturnType.getActualTypeArguments()[0]); // could be class or interface

            if (type.isAssignableFrom(IncapacidadType.class))
            {
                return -99;
            }

        }

        if (aPropertyDescriptor1.getPropertyType().isAssignableFrom(Map.class))
        {
            return 1;
        }

        if (aPropertyDescriptor2.getPropertyType().isAssignableFrom(Map.class))
        {
            return -1;
        }
        return 0;
    }
}
