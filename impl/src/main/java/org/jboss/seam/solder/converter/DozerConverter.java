package org.jboss.seam.solder.converter;

import org.dozer.Mapper;
import org.jboss.seam.solder.core.Veto;

@Veto
public class DozerConverter implements Converter<Object, Object> {

    private Class<?> destinationClass;
    private Mapper mapper;

    public DozerConverter(Class<?> destinationClass, Mapper mapper) {
        this.destinationClass = destinationClass;
        this.mapper = mapper;
    }

    @Override
    public Object convert(Object source) {
        return mapper.map(source, destinationClass);
    }
}
