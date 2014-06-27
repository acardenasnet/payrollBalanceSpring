package com.ips.payroll.balance.service.api;

/**
 * Created by acardenas on 6/26/14.
 */
public interface CsvService<T>
{
    byte[] convertToCsv(T aBean);
}
