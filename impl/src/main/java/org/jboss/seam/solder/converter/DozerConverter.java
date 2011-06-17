package org.jboss.seam.solder.converter;

import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import org.dozer.Mapper;
import org.jboss.seam.solder.bean.defaultbean.DefaultBean;

@DefaultBean(Converter.class)
public class DozerConverter implements Converter<Object, Object> {

    private Class<?> toClass;

    @Inject
    private Mapper mapper;

    @Inject
    public DozerConverter(InjectionPoint injectionPoint) {
        System.out.println(injectionPoint.getType());
    }

    @Override
    public Object convert(Object source) {
        return mapper.map(source, toClass);
    }
}
