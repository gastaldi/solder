/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.seam.solder.properties;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

import org.jboss.seam.solder.reflection.Reflections;

import static org.jboss.seam.solder.reflection.Reflections.getFieldValue;
import static org.jboss.seam.solder.reflection.Reflections.setFieldValue;

/**
 * A bean property based on the value contained in a field
 *
 * @author Pete Muir
 * @author Shane Bryzak
 */
class FieldPropertyImpl<V> implements FieldProperty<V> {

    private final Field field;

    FieldPropertyImpl(Field field) {
        this.field = field;
    }

    public String getName() {
        return field.getName();
    }

    public Type getBaseType() {
        return field.getGenericType();
    }

    public Field getAnnotatedElement() {
        return field;
    }

    public Member getMember() {
        return field;
    }

    @SuppressWarnings("unchecked")
    public Class<V> getJavaClass() {
        return (Class<V>) field.getType();
    }

    public V getValue(Object instance) {
        return getFieldValue(field, instance, getJavaClass());
    }

    public void setValue(Object instance, V value) {
        setFieldValue(field, instance, value);
    }

    public Class<?> getDeclaringClass() {
        return field.getDeclaringClass();
    }

    public boolean isReadOnly() {
        return false;
    }

    public void setAccessible() {
        Reflections.setAccessible(field);
    }

    @Override
    public String toString() {
        return field.toString();
    }

    @Override
    public int hashCode() {
        return field.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return field.equals(obj);
    }
}
