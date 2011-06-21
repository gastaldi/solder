package org.jboss.seam.solder.converter;

import static org.jboss.seam.solder.reflection.Reflections.invokeMethod;

import java.lang.reflect.Method;

import org.jboss.seam.solder.core.Veto;

/**
 * Calls a static method to convert from one object to another
 * 
 * @author george
 * 
 */
@Veto
public class MethodConverter implements Converter<Object, Object> {

    private Method method;

    protected void setMethod(Method method) {
        this.method = method;
    }

    protected Method getMethod() {
        return method;
    }

    @Override
    public Object convert(Object source) {
        return invokeMethod(method, source);
    }
}
