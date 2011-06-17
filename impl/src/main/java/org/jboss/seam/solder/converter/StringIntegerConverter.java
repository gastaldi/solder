package org.jboss.seam.solder.converter;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

public class StringIntegerConverter implements Converter<String, Integer> {
    @Inject
    public StringIntegerConverter(InjectionPoint injectionPoint) {
        ParameterizedType paramType = (ParameterizedType) injectionPoint.getType();
        System.out.println(Arrays.toString(paramType.getActualTypeArguments()));
    }

    @Override
    public Integer convert(String source) {
        return Integer.valueOf(source);
    }
}
