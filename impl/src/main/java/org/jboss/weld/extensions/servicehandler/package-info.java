/**
 * <p>
 * The service handler facility allow you to declare interfaces and abstract classes as automatically implemented beans.
 * Any call to an abstract method on the interface or abstract class will be forwarded to the invocation handler for 
 * processing.
 * </p>
 * 
 * <p>
 * If you wish to convert some non-type-safe lookup to a type-safe lookup, then service handlers may be useful for you, 
 * as they allow the end user to map a lookup to a method using domain specific annotations.
 * </p>
 * 
 * @see org.jboss.weld.extensions.servicehandler.ServiceHandler
 */
package org.jboss.weld.extensions.servicehandler;

