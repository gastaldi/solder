package org.jboss.seam.solder.converter;

public class IntegerStringConverter implements Converter<Integer, String> {
    @Override
    public String convert(Integer source) {
        return source.toString();
    }
}
