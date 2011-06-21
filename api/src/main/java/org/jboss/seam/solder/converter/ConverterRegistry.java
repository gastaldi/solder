package org.jboss.seam.solder.converter;

/**
 * Allows registering converters for specific classes
 * 
 * @author george
 * 
 */
public interface ConverterRegistry {
    <S, T> void registerConverter(Class<S> from, Class<T> to, Converter<S, T> converter);

    <S, T> Converter<S, T> unregisterConverter(Class<S> from, Class<T> to);

    <S, T> Converter<S, T> getConverter(Class<S> from, Class<T> to) throws ConverterNotFoundException;
}