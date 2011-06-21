package org.jboss.seam.solder.converter;

import static org.jboss.seam.solder.reflection.Reflections.invokeMethod;

import org.jboss.seam.solder.core.Veto;

@Veto
public class StaticMethodConverter extends MethodConverter {
    @Override
    public Object convert(Object source) {
        return invokeMethod(getMethod(), Object.class,null, source);
    }
}
