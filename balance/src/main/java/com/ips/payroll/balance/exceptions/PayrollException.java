package com.ips.payroll.balance.exceptions;

/**
 * Created by acardenas on 6/27/14.
 */
public class PayrollException
        extends RuntimeException
{
    public PayrollException()
    {
    }

    public PayrollException(String message)
    {
        super(message);
    }

    public PayrollException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public PayrollException(Throwable cause)
    {
        super(cause);
    }

    public PayrollException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
