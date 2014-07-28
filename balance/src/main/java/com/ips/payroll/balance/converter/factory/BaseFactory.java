package com.ips.payroll.balance.converter.factory;

/**
 * Created by acardenas on 7/27/14.
 */
public interface BaseFactory<T, R>
{
    R convertMap(T aSoure);
}
