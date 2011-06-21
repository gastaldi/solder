package org.jboss.seam.solder.converter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Default registry for {@link Converter} objects
 * 
 * @author george
 * 
 */
public class DefaultConverterRegistry implements ConverterRegistry {

    private Map<ClassTuple, Converter<?, ?>> registry;

    private static final ConverterRegistry INSTANCE = new DefaultConverterRegistry();

    public static ConverterRegistry getInstance() {
        return INSTANCE;
    }

    private DefaultConverterRegistry() {
        registry = new ConcurrentHashMap<DefaultConverterRegistry.ClassTuple, Converter<?, ?>>();
    }

    @Override
    public <S, F> void registerConverter(Class<S> from, Class<F> to, Converter<S, F> converter) {
        ClassTuple tuple = new ClassTuple(from, to);
        registry.put(tuple, converter);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <S, F> Converter<S, F> unregisterConverter(Class<S> from, Class<F> to) {
        return (Converter<S, F>) registry.remove(new ClassTuple(from, to));
    }

    @Override
    @SuppressWarnings("unchecked")
    /**
     * FIXME
     */
    public <S, F> Converter<S, F> getConverter(Class<S> from, Class<F> to) throws ConverterNotFoundException {
        return (Converter<S, F>) new ValueOfConverter(from, to);
        
        //        ClassTuple tuple = new ClassTuple(from, to);
//        return (Converter<S, F>) registry.get(tuple);
    }

    /**
     * Stores a class tuple
     * 
     * @author George Gastaldi
     * 
     */
    private class ClassTuple {
        private Class<?> fromClass;
        private Class<?> toClass;

        public ClassTuple(Class<?> fromClass, Class<?> toClass) {
            this.fromClass = fromClass;
            this.toClass = toClass;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((fromClass == null) ? 0 : fromClass.hashCode());
            result = prime * result + ((toClass == null) ? 0 : toClass.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            ClassTuple other = (ClassTuple) obj;
            if (fromClass == null) {
                if (other.fromClass != null)
                    return false;
            } else if (!fromClass.isAssignableFrom(other.fromClass))
                return false;
            if (toClass == null) {
                if (other.toClass != null)
                    return false;
            } else if (!toClass.isAssignableFrom(other.toClass))
                return false;
            return true;
        }
    }

}
