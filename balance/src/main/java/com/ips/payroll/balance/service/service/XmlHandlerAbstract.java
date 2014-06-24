package com.ips.payroll.balance.service.service;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import org.apache.xmlbeans.XmlObject;

public interface XmlHandlerAbstract<X extends XmlObject>
	extends Serializable
{

/**
 * Returns the type that is handled by this handler. This type is equal to
 * the type as specified in the generics part of this class. This method is
 * required runtime because the generics information is not available
 * anymore at that time.
 * 
 * @return the handled type.
 */
Class<X> handles();

/**
 * Handles a single request.
 * 
 * @param aRequest the request to handle
 * @param aResponse stream to write the response (if any) to
 * @throws IOException if an I/O error occurs. In particular, an IOException
 *             may be thrown if the output stream has been closed.
 * @throws ClientConnectorException if something fails.
 */
void handle(X aRequest) throws IOException;
}