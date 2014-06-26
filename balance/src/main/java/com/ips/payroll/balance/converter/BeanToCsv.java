package com.ips.payroll.balance.converter;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVWriter;

import com.ips.payroll.balance.exceptions.BlunderException;

public class BeanToCsv {

	   private static final Logger LOG = LoggerFactory.getLogger( BeanToCsv.class );

	   private boolean wroteHeader;
	   private boolean writeHeaders;
	   private boolean writeDeprecated;

	   public BeanToCsv( boolean writeHeaders, boolean writeDeprecated ) {
	      setWriteDeprecated( writeDeprecated );
	      setWriteHeaders( writeHeaders );
	      setWroteHeader( false );
	   }

	   public void writeBean( CSVWriter writer, Object bean ) {
	      writeBean( writer, bean, java.lang.Object.class );
	   }

	   public void writeBean( CSVWriter writer, Object bean, Class<?> stopClass ) {
	      if ( bean == null ) {
	         throw new NullPointerException( "Bean can not be null!" );
	      }
	      writeBean( writer, bean, resolvePropertyDescriptors( bean.getClass(), stopClass ) );
	   }

	   public void writeBean( CSVWriter writer, Object bean, List<PropertyDescriptor> descriptors ) {
	      try {
	         List<String> values = new ArrayList<String>();
	         for ( PropertyDescriptor pd : descriptors ) {	        	 
	            Object value = pd.getReadMethod().invoke( bean, new Object[]{} );
	            values.add( value == null ? "" : value.toString() );
	         }
	         if ( isWriteHeaders() && !isWroteHeader() ) {
	            writeHeaders( writer, descriptors );
	         }
	         writer.writeNext( values.toArray( new String[]{} ) );
	         writer.flush();
	      } catch ( Exception e ) {
	         throw new BlunderException( "Error writing bean", e );
	      }
	   }

	   public void writeAllBeans( CSVWriter writer, List<?> beans ) {
	      writeAllBeans( writer, beans, java.lang.Object.class );
	   }

	   public void writeAllBeans( CSVWriter writer, List<?> beans, Class<?> stopClass ) {
	      List<PropertyDescriptor> descriptors = resolvePropertyDescriptors( beans.get( 0 ).getClass(), stopClass );
	      if ( isWriteHeaders() && !isWroteHeader() ) {
	         writeHeaders( writer, descriptors );
	      }
	      for ( Object bean : beans ) {
	         writeBean( writer, bean, descriptors );
	      }
	   }

	   protected void writeHeaders( CSVWriter writer, List<PropertyDescriptor> descriptors ) {
	      List<String> headers = new ArrayList<String>();
	      for ( PropertyDescriptor pd : descriptors ) {
	         headers.add( pd.getName() );
	      }
	      writer.writeNext( headers.toArray( new String[]{} ) );
	      setWroteHeader( true );
	   }

	   private List<PropertyDescriptor> resolvePropertyDescriptors( Class<?> beanClass, Class<?> stopClass ) {
	      PropertyDescriptor[] descs = null;
	      try {
	         descs = Introspector.getBeanInfo( beanClass, stopClass ).getPropertyDescriptors();
	      } catch ( Exception e ) {
	         throw new BlunderException( "Error writing bean", e );
	      }
	      
	      if ( isWriteDeprecated() ) {
	         return Arrays.asList( descs );
	      }
	      
	      List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
	      for ( PropertyDescriptor pd : descs ) {
	         if ( pd.getWriteMethod().getAnnotation( Deprecated.class ) != null ) {
	            LOG.debug( "Filtering property named [" + pd.getName() + "] for being annotated with @Deprecated!" );
	         } else {
	            descriptors.add( pd );
	         }
	      }
	      return descriptors;
	   }

	   public boolean isWriteHeaders() {
	      return writeHeaders;
	   }

	   public void setWriteHeaders( boolean writeHeaders ) {
	      this.writeHeaders = writeHeaders;
	   }

	   public boolean isWriteDeprecated() {
	      return writeDeprecated;
	   }

	   public void setWriteDeprecated( boolean writeDeprecated ) {
	      this.writeDeprecated = writeDeprecated;
	   }

	   public boolean isWroteHeader() {
	      return wroteHeader;
	   }

	   public void setWroteHeader( boolean wroteHeader ) {
	      this.wroteHeader = wroteHeader;
	   }

	}