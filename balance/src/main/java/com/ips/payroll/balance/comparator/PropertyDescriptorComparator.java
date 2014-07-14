package com.ips.payroll.balance.comparator;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.Map;

import com.ips.payroll.balance.model.enums.IncapacidadType;

/**
 *class to compare the field types and sort by with the correct order.
 */
public class PropertyDescriptorComparator
        implements Comparator<PropertyDescriptor>, Serializable
{
    private static final int MOVE_TO_END = 99;
    private static final int MOVE_TO_END_NEGATION = -99;

    private static final long serialVersionUID = 832459946124616940L;

    @Override
    /** {@inheritDoc} */
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
                return MOVE_TO_END;
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
                return MOVE_TO_END_NEGATION;
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
