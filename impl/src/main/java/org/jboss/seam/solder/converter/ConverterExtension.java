package org.jboss.seam.solder.converter;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.inject.spi.ProcessInjectionTarget;

import org.jboss.seam.solder.bean.defaultbean.DefaultBean;
import org.jboss.seam.solder.reflection.Reflections;

@SuppressWarnings("rawtypes")
public class ConverterExtension implements Extension {

    private Set<ClassTuple> classTuples = new HashSet<ClassTuple>();
    private AnnotatedType<Converter> defaultConverterType;

    public void discoverAnnotatedType(@Observes ProcessAnnotatedType<Converter> pb) {
        AnnotatedType<Converter> annotatedType = pb.getAnnotatedType();
        Class<Converter> javaClass = annotatedType.getJavaClass();
        // Workaround until SOLDER-110 is fixed
        if (javaClass.isAnnotationPresent(DefaultBean.class)) {
            defaultConverterType = annotatedType;
        }
        Type[] types = Reflections.getActualTypeArgumentsForGenericClass(javaClass, Converter.class);
        if (types.length == 2) {
            Class<?> fromClass = (Class<?>) types[0];
            Class<?> toClass = (Class<?>) types[1];
            classTuples.add(new ClassTuple(fromClass, toClass));
        }
    }

    public void processInjectionPoint(@Observes ProcessInjectionTarget<Converter> pit, BeanManager beanManager) {
//        System.out.println(pit.getAnnotatedType());
//        Type[] converterTypeGenerics = getConverterTypeGenerics(pit.getAnnotatedType().getJavaClass(), Converter.class);
//        if (converterTypeGenerics == null
//                || !classTuples.contains(new ClassTuple((Class<?>) converterTypeGenerics[0],
//                        (Class<?>) converterTypeGenerics[1]))) {
//        }
//        InjectionTarget<Converter> defaultInjection = beanManager.createInjectionTarget(defaultConverterType);
//        pit.setInjectionTarget(defaultInjection);
    }

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
