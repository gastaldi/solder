package org.jboss.seam.solder.converter;

/**
 * Allows registering converters for specific classes
 * 
 * @author george
 * 
 */
public interface ConverterRegistry {
    /**
     * Add a plain converter to this registry. The convertible sourceType/targetType pair is specified explicitly. Allows for a
     * Converter to be reused for multiple distinct pairs without having to create a Converter class for each pair.
     */
    void registerConverter(Class<?> sourceType, Class<?> targetType, Converter<?, ?> converter);

    Converter<?, ?> unregisterConverter(Class<?> sourceType, Class<?> targetType);

    <S, T> Converter<S, T> getConverter(Class<S> from, Class<T> to) throws ConverterNotFoundException;
}