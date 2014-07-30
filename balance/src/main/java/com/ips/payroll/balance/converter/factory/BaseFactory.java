package com.ips.payroll.balance.converter.factory;

/**
 * Created by acardenas on 7/27/14.
 */
public interface BaseFactory<S, T>
{
    /**
     * Set this value When the converter found and null value instead of the null.
     */
    String DEFAULT_NULL_VALUE = "";

    T convertMap(S aSoure);
}
