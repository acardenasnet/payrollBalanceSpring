package com.ips.payroll.balance.converter.factory;

/**
 * Created by acardenas on 7/27/14.
 */
public interface BaseFactory<S, T>
{
    T convertMap(S aSoure);
}
