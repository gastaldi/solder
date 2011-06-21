package org.jboss.seam.solder.converter;

import java.lang.reflect.Type;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.inject.spi.ProcessInjectionTarget;

import org.jboss.seam.solder.core.ExtensionManaged;
import org.jboss.seam.solder.reflection.Reflections;

@SuppressWarnings("rawtypes")
public class ConverterExtension implements Extension {

    public void discoverAnnotatedType(@Observes ProcessAnnotatedType<Converter> pb, BeanManager beanManager) throws Exception {
        AnnotatedType<Converter> annotatedType = pb.getAnnotatedType();
        Class<Converter> javaClass = annotatedType.getJavaClass();
        // Workaround until SOLDER-110 is fixed
        if (!javaClass.isAnnotationPresent(ExtensionManaged.class)) {
            pb.veto();
        }
        // Type[] types = Reflections.getActualTypeArgumentsForGenericClass(javaClass, Converter.class);
        // if (types.length == 2) {
        // Class<?> fromClass = (Class<?>) types[0];
        // Class<?> toClass = (Class<?>) types[1];
        // // DefaultConverterRegistry.getInstance().registerConverter(fromClass, toClass, javaClass.newInstance());
        // }

    }

    @SuppressWarnings("unchecked")
    public <S, T> void processInjectionPoint(@Observes ProcessInjectionTarget<Converter> pit, BeanManager beanManager) {
        Type[] types = Reflections
                .getActualTypeArgumentsForGenericClass(pit.getAnnotatedType().getJavaClass(), Converter.class);
        if (types.length == 2) {
            Class<S> fromClass = (Class<S>) types[0];
            Class<T> toClass = (Class<T>) types[1];
            Converter<S, T> converter = (Converter<S, T>) new ValueOfConverter(fromClass, toClass);
            DefaultConverterRegistry.getInstance().registerConverter(fromClass, toClass, converter);
        }
    }

}
