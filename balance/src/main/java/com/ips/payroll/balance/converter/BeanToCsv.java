package com.ips.payroll.balance.converter;

import au.com.bytecode.opencsv.CSVWriter;
import com.ips.payroll.balance.exceptions.BlunderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BeanToCsv
{

    private static final Logger LOG = LoggerFactory.getLogger(BeanToCsv.class);

    private boolean wroteHeader;
    private boolean writeHeaders;
    private boolean writeDeprecated;

    public BeanToCsv(boolean aWriteHeaders, boolean aWriteDeprecated)
    {
        setWriteDeprecated(aWriteDeprecated);
        setWriteHeaders(aWriteHeaders);
        setWroteHeader(false);
    }

    public void writeBean(CSVWriter aWriter, Object aBean)
    {
        writeBean(aWriter, aBean, java.lang.Object.class);
    }

    public void writeBean(CSVWriter aWriter, Object bean, Class<?> anStopClass)
    {
        if (bean == null)
        {
            throw new NullPointerException("Bean can not be null!");
        }
        writeBean(aWriter, bean, resolvePropertyDescriptors(bean.getClass(), anStopClass));
    }

    public void writeBean(CSVWriter aWriter, Object aBean, List<PropertyDescriptor> aDescriptors)
    {
        try
        {
            List<String> values = new ArrayList<String>();
            for (PropertyDescriptor pd : aDescriptors)
            {
                Object value = pd.getReadMethod().invoke(aBean, new Object[]{});
                if (value == null)
                {
                    values.add("");
                }
                else
                {
                    values.add(value.toString());
                }
            }
            if (isWriteHeaders() && !isWroteHeader())
            {
                writeHeaders(aWriter, aDescriptors);
            }
            aWriter.writeNext(values.toArray(new String[]{}));
            aWriter.flush();
        }
        catch (Exception e)
        {
            throw new BlunderException("Error writing bean", e);
        }
    }

    public void writeAllBeans(CSVWriter aWriter, List<?> aBeans)
    {
        writeAllBeans(aWriter, aBeans, java.lang.Object.class);
    }

    public void writeAllBeans(CSVWriter aWriter, List<?> aBeans, Class<?> anStopClass)
    {
        List<PropertyDescriptor> descriptors = resolvePropertyDescriptors(aBeans.get(0).getClass(), anStopClass);
        if (isWriteHeaders() && !isWroteHeader())
        {
            writeHeaders(aWriter, descriptors);
        }
        for (Object bean : aBeans)
        {
            writeBean(aWriter, bean, descriptors);
        }
    }

    protected void writeHeaders(CSVWriter writer, List<PropertyDescriptor> descriptors)
    {
        List<String> headers = new ArrayList<String>();
        for (PropertyDescriptor pd : descriptors)
        {
            headers.add(pd.getName());
        }
        writer.writeNext(headers.toArray(new String[]{}));
        setWroteHeader(true);
    }

    protected List<PropertyDescriptor> resolvePropertyDescriptors(Class<?> beanClass, Class<?> stopClass)
    {
        PropertyDescriptor[] propertyDescriptors = null;
        try
        {
            propertyDescriptors = Introspector.getBeanInfo(beanClass, stopClass).getPropertyDescriptors();
        }
        catch (Exception e)
        {
            throw new BlunderException("Error writing bean", e);
        }

        if (isWriteDeprecated())
        {
            return Arrays.asList(propertyDescriptors);
        }

        List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
        for (PropertyDescriptor pd : propertyDescriptors)
        {
            if (pd.getWriteMethod().getAnnotation(Deprecated.class) != null)
            {
                LOG.debug("Filtering property named [" + pd.getName() + "] for being annotated with @Deprecated!");
            }
            else
            {
                descriptors.add(pd);
            }
        }
        return descriptors;
    }

    public boolean isWriteHeaders()
    {
        return writeHeaders;
    }

    public void setWriteHeaders(boolean aWriteHeaders)
    {
        writeHeaders = aWriteHeaders;
    }

    public boolean isWriteDeprecated()
    {
        return writeDeprecated;
    }

    public void setWriteDeprecated(boolean aWriteDeprecated)
    {
        writeDeprecated = aWriteDeprecated;
    }

    public boolean isWroteHeader()
    {
        return wroteHeader;
    }

    public void setWroteHeader(boolean aWroteHeader)
    {
        wroteHeader = aWroteHeader;
    }
}
