package org.jboss.seam.solder.converter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.UnsatisfiedResolutionException;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

@SuppressWarnings("rawtypes")
public class GenericConverter implements Converter {

    private Class<?> sourceClass;
    private Class<?> destinationClass;
    private Mapper mapper;

    @Inject
    public GenericConverter(InjectionPoint injectionPoint, Instance<Mapper> mapperInstance) {
        ParameterizedType paramType = (ParameterizedType) injectionPoint.getType();
        Type[] actualTypeArguments = paramType.getActualTypeArguments();
        sourceClass = (Class) actualTypeArguments[0];
        destinationClass = (Class) actualTypeArguments[1];
        try {
            this.mapper = mapperInstance.get();
        } catch (UnsatisfiedResolutionException e) {
            this.mapper = DozerBeanMapperSingletonWrapper.getInstance();
        }
    }

    public GenericConverter(Class<?> sourceClass, Class<?> destinationClass) {
        this(sourceClass, destinationClass, DozerBeanMapperSingletonWrapper.getInstance());
    }

    public GenericConverter(Class<?> sourceClass, Class<?> destinationClass, Mapper mapper) {
        this.sourceClass = sourceClass;
        this.destinationClass = destinationClass;
        this.mapper = mapper;
    }

    @Override
    public Object convert(Object source) {
        if (source == null) {
            return null;
        }
        Object valueOfObj = tryWithValueOf(source);
        if (valueOfObj == null) {
            valueOfObj = mapper.map(source, destinationClass);
        }
        return valueOfObj;
    }

    private Object tryWithValueOf(Object source) {
        try {
            try {
                return destinationClass.getMethod("valueOf", sourceClass).invoke(null, source);
            } catch (NoSuchMethodException nsme) {
                return destinationClass.getMethod("valueOf", (Class) sourceClass.getField("TYPE").get(null)).invoke(null,
                        source);
            }
        } catch (Exception e) {
            return null;
        }

    }
}
