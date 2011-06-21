package org.jboss.seam.solder.converter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import org.jboss.seam.solder.bean.defaultbean.DefaultBean;
import org.jboss.seam.solder.core.ExtensionManaged;

@SuppressWarnings("rawtypes")
@DefaultBean(Converter.class)
@ExtensionManaged
public class InjectableDelegatingConverter implements Converter {

//    @Inject
    private ConverterRegistry converterRegistry = DefaultConverterRegistry.getInstance();

    private Class<?> sourceClass;
    private Class<?> destinationClass;

    @Inject
    public InjectableDelegatingConverter(InjectionPoint injectionPoint) {
        ParameterizedType paramType = (ParameterizedType) injectionPoint.getType();
        Type[] actualTypeArguments = paramType.getActualTypeArguments();
        sourceClass = (Class<?>) actualTypeArguments[0];
        destinationClass = (Class<?>) actualTypeArguments[1];
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object convert(Object source) {
        if (source == null) {
            return null;
        }
        Converter converter = converterRegistry.getConverter(sourceClass, destinationClass);
        return converter.convert(source);
    }

}
