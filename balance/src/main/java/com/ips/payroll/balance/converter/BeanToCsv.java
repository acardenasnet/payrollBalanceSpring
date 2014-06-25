package com.ips.payroll.balance.converter;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.MappingStrategy;

public class BeanToCsv<T>
{
	public BeanToCsv() 
	{
		// empty
	}
	
	public boolean write(MappingStrategy<T> mapper, Writer writer, List<?> objects)
	{
		return write(mapper, new CSVWriter(writer), objects);
	}

	public boolean write(MappingStrategy<T> mapper, CSVWriter csv, List<?> objects)
	{
		if (objects == null || objects.isEmpty())
		{
			return false;
		}

		try
		{
			csv.writeNext(processHeader(mapper));
			List<Method> getters = findGetters(mapper);
			for (Object obj : objects)
			{
				String[] line = processObject(getters, obj);
				csv.writeNext(line);
			}
			return true;
		}
		catch (Exception e)
		{
			throw new RuntimeException("Error writing CSV !", e);
		}
	}

	protected String[] processHeader(MappingStrategy<T> mapper) throws IntrospectionException
	{
		List<String> values = new ArrayList<String>();
		int i = 0;
		PropertyDescriptor prop = mapper.findDescriptor(i);
		while (prop != null)
		{
			values.add(prop.getName());
			i++;
			prop = mapper.findDescriptor(i);
		}
		return values.toArray(new String[0]);
	}

	protected String[] processObject(List<Method> getters, Object bean) throws IntrospectionException, IllegalArgumentException,
		IllegalAccessException, InvocationTargetException
	{
		List<String> values = new ArrayList<String>();
		// retrieve bean values
		for (Method getter : getters)
		{
			Object value = getter.invoke(bean, (Object[]) null);
			if (value == null)
			{
				values.add("");
			}
			// else {
			// if ( value instanceof Date ) {
			// SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			// String date = format.format(new java.util.Date());
			// values.add(date);
			// }
			else
			{
				values.add(value.toString());
			}

			// }
		}
		return values.toArray(new String[0]);
	}

	/**
	 * Build getters list from provided mapper.
	 */
	private List<Method> findGetters(MappingStrategy<T> mapper) throws IntrospectionException
	{
		int i = 0;
		PropertyDescriptor prop = mapper.findDescriptor(i);
		// build getters methods list
		List<Method> readers = new ArrayList<Method>();
		while (prop != null)
		{
			readers.add(prop.getReadMethod());
			i++;
			prop = mapper.findDescriptor(i);
		}
		return readers;
	}

}
