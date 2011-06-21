package org.jboss.seam.solder.converter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.jboss.seam.solder.core.Veto;

/**
 * Converts the field with calling a static valueOf
 * 
 * @author george
 * 
 */
@Veto
public class ValueOfConverter extends StaticMethodConverter {
    private static final String TYPE_FIELD = "TYPE";
    private static final String VALUE_OF = "valueOf";

    private Class<?> sourceClass;
    private Class<?> destinationClass;

    public ValueOfConverter(Class<?> sourceClass, Class<?> destinationClass) {
        super();
        this.sourceClass = sourceClass;
        this.destinationClass = destinationClass;
        setValueOfMethod();
    }

    private void setValueOfMethod() {
        try {
            Method method;
            try {
                method = destinationClass.getMethod(VALUE_OF, sourceClass);
            } catch (NoSuchMethodException nsme) {
                Field typeField = sourceClass.getField(TYPE_FIELD);
                method = destinationClass.getMethod(VALUE_OF, (Class<?>)typeField.get(null));
            }
            setMethod(method);
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

}
