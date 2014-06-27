package com.ips.payroll.balance.service.api;

import java.util.List;

/**
 * Created by acardenas on 6/26/14.
 */
public interface CsvService<T>
{
    byte[] convertToCsv(T aBean);
    
    /**
     * Convert aList of beans to bytes array
     * @param aBeans <T> Source Bean to transform
     * @return Bytes Array to transform.
     */
    byte[] convertToCsv(List<T> aBeans);
}
