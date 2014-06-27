package com.ips.payroll.balance;

import java.beans.PropertyDescriptor;
import java.util.Comparator;
import java.util.Map;

/**
 * Created by acardenas on 6/26/14.
 */
public class PropertyDescriptorComparator
        implements Comparator<PropertyDescriptor>
{
    @Override
    public int compare(PropertyDescriptor o1, PropertyDescriptor o2)
    {
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
