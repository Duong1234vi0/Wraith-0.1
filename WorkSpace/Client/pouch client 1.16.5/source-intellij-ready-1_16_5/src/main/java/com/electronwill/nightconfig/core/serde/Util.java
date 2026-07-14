/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.serde;

import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.serde.SerdeException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.Buffer;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Optional;

final class Util {
    private static final IdentityHashMap<Class<?>, TypeAndOrder> PRIMITIVE_TO_WRAPPER = new IdentityHashMap();
    private static final IdentityHashMap<Class<?>, TypeAndOrder> WRAPPER_TO_PRIMITIVE = new IdentityHashMap();

    Util() {
    }

    static boolean isEmpty(Object configValue) {
        Class<?> cls;
        if (configValue instanceof Collection) {
            return ((Collection)configValue).isEmpty();
        }
        if (configValue instanceof Map) {
            return ((Map)configValue).isEmpty();
        }
        if (configValue instanceof UnmodifiableConfig) {
            return ((UnmodifiableConfig)configValue).isEmpty();
        }
        if (configValue instanceof CharSequence) {
            return ((CharSequence)configValue).length() == 0;
        }
        if (configValue instanceof Optional) {
            return !((Optional)configValue).isPresent();
        }
        if (configValue instanceof Buffer) {
            ((Buffer)configValue).hasRemaining();
        }
        if ((cls = configValue.getClass()).isArray()) {
            return Array.getLength(configValue) == 0;
        }
        return Util.isEmptyWithReflection(cls, configValue);
    }

    private static boolean isEmptyWithReflection(Class<?> cls, Object configValue) {
        Method isEmptyMethod;
        AdditionalEmptyables.EmptyableClass scalaIterableOnce = AdditionalEmptyables.scalaIterableOnce;
        if (scalaIterableOnce != null && scalaIterableOnce.isInstance(cls)) {
            return scalaIterableOnce.isEmpty(configValue);
        }
        AdditionalEmptyables.EmptyableClass kotlinCollection = AdditionalEmptyables.kotlinCollection;
        if (kotlinCollection != null && kotlinCollection.isInstance(cls)) {
            return kotlinCollection.isEmpty(configValue);
        }
        try {
            isEmptyMethod = cls.getMethod("isEmpty", new Class[0]);
        }
        catch (NoSuchMethodException | SecurityException e) {
            return false;
        }
        if (isEmptyMethod.getReturnType() != Boolean.TYPE) {
            return false;
        }
        try {
            return (Boolean)isEmptyMethod.invoke(configValue, new Object[0]);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new SerdeException("Exception during call to isEmpty() on " + String.valueOf(configValue), e);
        }
    }

    static boolean canAssign(Class<?> fieldType, Class<?> valueType) {
        if (valueType == null) {
            return !fieldType.isPrimitive();
        }
        if (fieldType.isPrimitive() || valueType.isPrimitive()) {
            TypeAndOrder a = fieldType.isPrimitive() ? PRIMITIVE_TO_WRAPPER.get(fieldType) : WRAPPER_TO_PRIMITIVE.get(fieldType);
            TypeAndOrder b = valueType.isPrimitive() ? PRIMITIVE_TO_WRAPPER.get(valueType) : WRAPPER_TO_PRIMITIVE.get(valueType);
            return a != null && b != null && a.canAssignValue(b);
        }
        return fieldType.isAssignableFrom(valueType);
    }

    static boolean isPrimitiveOrWrapper(Class<?> type) {
        return type.isPrimitive() || WRAPPER_TO_PRIMITIVE.get(type) != null;
    }

    static boolean isPrimitiveOrWrapperNumber(Class<?> type) {
        return Util.isPrimitiveOrWrapper(type) && type != Boolean.class && type != Boolean.TYPE && type != Character.class && type != Character.TYPE;
    }

    static void addPrimitiveAndWrapper(Class<?> primitiveType, Class<?> wrapperType) {
        PRIMITIVE_TO_WRAPPER.put(primitiveType, new TypeAndOrder(PRIMITIVE_TO_WRAPPER.size(), wrapperType));
        WRAPPER_TO_PRIMITIVE.put(wrapperType, new TypeAndOrder(WRAPPER_TO_PRIMITIVE.size(), primitiveType));
    }

    static {
        Util.addPrimitiveAndWrapper(Boolean.TYPE, Boolean.class);
        Util.addPrimitiveAndWrapper(Byte.TYPE, Byte.class);
        Util.addPrimitiveAndWrapper(Short.TYPE, Short.class);
        Util.addPrimitiveAndWrapper(Character.TYPE, Character.class);
        Util.addPrimitiveAndWrapper(Integer.TYPE, Integer.class);
        Util.addPrimitiveAndWrapper(Long.TYPE, Long.class);
        Util.addPrimitiveAndWrapper(Float.TYPE, Float.class);
        Util.addPrimitiveAndWrapper(Double.TYPE, Double.class);
    }

    private static final class AdditionalEmptyables {
        static final EmptyableClass scalaIterableOnce = AdditionalEmptyables.classOrNull("scala.collection.IterableOnce");
        static final EmptyableClass kotlinCollection = AdditionalEmptyables.classOrNull("kotlin.collections.Collection");

        private AdditionalEmptyables() {
        }

        private static EmptyableClass classOrNull(String fullName) {
            Method m;
            Class<?> cls;
            try {
                cls = Class.forName(fullName);
            }
            catch (ClassNotFoundException e) {
                return null;
            }
            try {
                m = cls.getMethod("isEmpty", new Class[0]);
            }
            catch (NoSuchMethodException | SecurityException e) {
                return null;
            }
            if (m.getReturnType() != Boolean.TYPE) {
                return null;
            }
            return new EmptyableClass(cls, m);
        }

        private static class EmptyableClass {
            final Class<?> cls;
            final Method isEmptyMethod;

            EmptyableClass(Class<?> cls, Method isEmptyMethod) {
                this.cls = cls;
                this.isEmptyMethod = isEmptyMethod;
            }

            boolean isInstance(Class<?> instanceClass) {
                return this.cls.isAssignableFrom(instanceClass);
            }

            boolean isEmpty(Object instance) {
                try {
                    return (Boolean)this.isEmptyMethod.invoke(instance, new Object[0]);
                }
                catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    throw new SerdeException("Exception during call to isEmpty() on " + String.valueOf(instance), e);
                }
            }
        }
    }

    private static final class TypeAndOrder {
        final int order;
        final Class<?> type;

        TypeAndOrder(int order, Class<?> type) {
            this.order = order;
            this.type = type;
        }

        boolean canAssignValue(TypeAndOrder valueType) {
            if (this.order == 0) {
                return valueType.order == 0;
            }
            if (valueType.order == 0) {
                return false;
            }
            return this.order >= valueType.order;
        }

        public String toString() {
            return "TypeAndOrder [order=" + this.order + ", type=" + String.valueOf(this.type) + "]";
        }
    }
}

