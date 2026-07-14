/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.serde;

import com.electronwill.nightconfig.core.NullObject;
import com.electronwill.nightconfig.core.serde.SerdeException;
import com.electronwill.nightconfig.core.serde.Util;
import com.electronwill.nightconfig.core.serde.annotations.SerdeAssert;
import com.electronwill.nightconfig.core.serde.annotations.SerdeDefault;
import com.electronwill.nightconfig.core.serde.annotations.SerdePhase;
import com.electronwill.nightconfig.core.serde.annotations.SerdeSkipDeserializingIf;
import com.electronwill.nightconfig.core.serde.annotations.SerdeSkipSerializingIf;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

final class AnnotationProcessor {
    AnnotationProcessor() {
    }

    static Predicate<?> resolveAssertPredicate(SerdeAssert[] annotations, Object currentInstance, SerdePhase currentPhase, Field field) {
        ArrayList predicates = new ArrayList(annotations.length);
        for (SerdeAssert annot : annotations) {
            SerdePhase annotPhase = annot.phase();
            SerdeAssert.AssertThat[] conditions = annot.value();
            SerdeAssertSanityCheck sanityCheck = new SerdeAssertSanityCheck();
            if (annotPhase == currentPhase || annotPhase == SerdePhase.BOTH) {
                for (int i = 0; i < conditions.length; ++i) {
                    SerdeAssert.AssertThat condition = conditions[i];
                    predicates.add(AnnotationProcessor.resolveAssertPredicate1(condition, annot, currentInstance, currentPhase, field.getType(), sanityCheck));
                }
            }
            sanityCheck.check(annot);
        }
        return AnnotationProcessor.combineAnd(predicates);
    }

    private static Predicate<?> resolveAssertPredicate1(SerdeAssert.AssertThat assertThat, SerdeAssert annotation, Object currentInstance, SerdePhase currentPhase, Class<?> fieldType, SerdeAssertSanityCheck sanityCheck) {
        Class<?> cls = annotation.customClass();
        String methodOrFieldName = annotation.customCheck();
        if (assertThat == SerdeAssert.AssertThat.CUSTOM) {
            sanityCheck.hasCustomAssert = true;
            if (methodOrFieldName.isEmpty()) {
                throw new SerdeException(String.format("Invalid annotation %s: with AssertThat.CUSTOM, parameter `customCheck` must be provided and non-empty.", AnnotationProcessor.annotToString(annotation)));
            }
            return AnnotationProcessor.findCustomPredicate("assert predicate", annotation, cls, methodOrFieldName, currentInstance, fieldType);
        }
        boolean bl = sanityCheck.hasCustomParam = !methodOrFieldName.isEmpty() || cls != Object.class;
        if (assertThat == SerdeAssert.AssertThat.NOT_NULL) {
            return v -> v != null;
        }
        if (assertThat == SerdeAssert.AssertThat.NOT_EMPTY) {
            return v -> v == null || !Util.isEmpty(v);
        }
        assert (false) : "missing case";
        return null;
    }

    static Predicate<?> resolveSkipDeserializingIfPredicate(SerdeSkipDeserializingIf annotation, Object currentInstance) {
        SerdeSkipDeserializingIf.SkipDeIf[] conditions = annotation.value();
        Predicate[] predicates = new Predicate[conditions.length];
        for (int i = 0; i < predicates.length; ++i) {
            SerdeSkipDeserializingIf.SkipDeIf condition = conditions[i];
            predicates[i] = AnnotationProcessor.resolveSkipDeserializingIfPredicate1(condition, annotation, currentInstance, Object.class);
        }
        return AnnotationProcessor.combineOr(predicates);
    }

    private static Predicate<?> resolveSkipDeserializingIfPredicate1(SerdeSkipDeserializingIf.SkipDeIf skipIf, SerdeSkipDeserializingIf annotation, Object currentInstance, Class<?> configValueType) {
        Class<?> cls = annotation.customClass();
        String methodOrFieldName = annotation.customCheck();
        if (skipIf == SerdeSkipDeserializingIf.SkipDeIf.CUSTOM) {
            if (methodOrFieldName.isEmpty()) {
                throw new SerdeException(String.format("Invalid annotation %s: with SkipDeIf.CUSTOM, parameter `customCheck` must be provided and non-empty.", AnnotationProcessor.annotToString(annotation)));
            }
            return AnnotationProcessor.findCustomPredicate("skip predicate", annotation, cls, methodOrFieldName, currentInstance, configValueType);
        }
        if (!methodOrFieldName.isEmpty() || cls != Object.class) {
            throw new SerdeException(String.format("Invalid annotation %s: with SkipDeIf.%s, no additional parameter must be specified.", AnnotationProcessor.annotToString(annotation), skipIf.name()));
        }
        if (skipIf == SerdeSkipDeserializingIf.SkipDeIf.IS_MISSING) {
            return v -> v == null;
        }
        if (skipIf == SerdeSkipDeserializingIf.SkipDeIf.IS_NULL) {
            return v -> v == NullObject.NULL_OBJECT;
        }
        if (skipIf == SerdeSkipDeserializingIf.SkipDeIf.IS_EMPTY) {
            return v -> v != null && Util.isEmpty(v);
        }
        assert (false) : "missing case";
        return null;
    }

    static Predicate<?> resolveSkipSerializingIfPredicate(SerdeSkipSerializingIf annotation, Object currentInstance, Field field) {
        SerdeSkipSerializingIf.SkipSerIf[] conditions = annotation.value();
        Predicate[] predicates = new Predicate[conditions.length];
        for (int i = 0; i < predicates.length; ++i) {
            SerdeSkipSerializingIf.SkipSerIf condition = conditions[i];
            predicates[i] = AnnotationProcessor.resolveSkipSerializingIfPredicate1(condition, annotation, currentInstance, field.getType());
        }
        return AnnotationProcessor.combineOr(predicates);
    }

    private static Predicate<?> resolveSkipSerializingIfPredicate1(SerdeSkipSerializingIf.SkipSerIf skipIf, SerdeSkipSerializingIf annotation, Object currentInstance, Class<?> fieldType) {
        Class<?> cls = annotation.customClass();
        String methodOrFieldName = annotation.customCheck();
        if (skipIf == SerdeSkipSerializingIf.SkipSerIf.CUSTOM) {
            if (methodOrFieldName.isEmpty()) {
                throw new SerdeException(String.format("Invalid annotation %s: with SkipSerIf.CUSTOM, parameter `customCheck` must be provided and non-empty.", AnnotationProcessor.annotToString(annotation)));
            }
            return AnnotationProcessor.findCustomPredicate("skip predicate", annotation, cls, methodOrFieldName, currentInstance, fieldType);
        }
        if (!methodOrFieldName.isEmpty() || cls != Object.class) {
            throw new SerdeException(String.format("Invalid annotation %s: with SkipSerIf.%s, no additional parameter must be specified.", AnnotationProcessor.annotToString(annotation), skipIf.name()));
        }
        if (skipIf == SerdeSkipSerializingIf.SkipSerIf.IS_NULL) {
            return v -> v == null;
        }
        if (skipIf == SerdeSkipSerializingIf.SkipSerIf.IS_EMPTY) {
            return v -> v != null && Util.isEmpty(v);
        }
        assert (false) : "missing case";
        return null;
    }

    private static Predicate<?> pedicateFromField(String label, Field field, Object instance, boolean mustBeStatic) {
        return AnnotationProcessor.anyFromField(Predicate.class, label, field, instance, mustBeStatic);
    }

    static EnumMap<SerdePhase, EnumMap<SerdeDefault.WhenValue, SerdeDefault>> getConfigDefaultAnnotations(Field field) {
        EnumMap<SerdePhase, EnumMap<SerdeDefault.WhenValue, SerdeDefault>> byPhase = new EnumMap<SerdePhase, EnumMap<SerdeDefault.WhenValue, SerdeDefault>>(SerdePhase.class);
        for (SerdeDefault annot : (SerdeDefault[])field.getAnnotationsByType(SerdeDefault.class)) {
            SerdePhase[] phases = annot.phase() == SerdePhase.BOTH ? new SerdePhase[]{SerdePhase.BOTH, SerdePhase.SERIALIZING, SerdePhase.DESERIALIZING} : new SerdePhase[]{annot.phase()};
            for (SerdePhase phase : phases) {
                EnumMap byWhen = byPhase.computeIfAbsent(phase, p -> new EnumMap(SerdeDefault.WhenValue.class));
                for (SerdeDefault.WhenValue when : annot.whenValue()) {
                    SerdeDefault conflict = byWhen.put(when, annot);
                    if (conflict == null) continue;
                    String msg = String.format("Annotation %s is conflicting with annotation %s on field `%s`. Only one @SerdeDefault must be applicable in a given situation.", AnnotationProcessor.annotToString(annot), conflict, field);
                    throw new SerdeException(msg);
                }
            }
        }
        return byPhase;
    }

    static Supplier<?> resolveConfigDefaultProvider(SerdeDefault annotation, Object currentInstance) {
        Class<?> cls = annotation.cls();
        String methodOrFieldName = annotation.provider();
        Class[] noParameters = new Class[]{};
        Object methodOrField = cls == Object.class ? AnnotationProcessor.findFieldOrMethodIn(currentInstance.getClass(), methodOrFieldName, true, noParameters) : AnnotationProcessor.findFieldOrMethodIn(cls, methodOrFieldName, false, noParameters);
        if (methodOrField == null) {
            String msg = String.format("Default value provider `%s` not found for annotation %s", methodOrFieldName, AnnotationProcessor.annotToString(annotation));
            throw new SerdeException(msg);
        }
        return methodOrField instanceof Field ? AnnotationProcessor.defaultSupplierFromField((Field)methodOrField, currentInstance, cls != Object.class) : AnnotationProcessor.defaultSupplierFromMethod((Method)methodOrField, currentInstance, cls != Object.class);
    }

    private static <T> Predicate<T> combineOr(Predicate<T>[] predicates) {
        if (predicates.length == 1) {
            return predicates[0];
        }
        return o -> {
            for (Predicate p : predicates) {
                if (!p.test(o)) continue;
                return true;
            }
            return false;
        };
    }

    private static <T> Predicate<T> combineAnd(List<Predicate<T>> predicates) {
        if (predicates.isEmpty()) {
            return null;
        }
        if (predicates.size() == 1) {
            return predicates.get(0);
        }
        return o -> {
            for (Predicate p : predicates) {
                if (p.test(o)) continue;
                return false;
            }
            return true;
        };
    }

    private static Predicate<?> findCustomPredicate(String label, Annotation annotation, Class<?> cls, String methodOrFieldName, Object currentInstance, Class<?> predicateParameter) {
        Class[] methodParameters = new Class[]{predicateParameter};
        Object methodOrField = cls == Object.class ? AnnotationProcessor.findFieldOrMethodIn(currentInstance.getClass(), methodOrFieldName, true, methodParameters) : AnnotationProcessor.findFieldOrMethodIn(cls, methodOrFieldName, false, methodParameters);
        if (methodOrField == null) {
            String msg = String.format("Custom %s `%s` not found for annotation %s", label, methodOrFieldName, AnnotationProcessor.annotToString(annotation));
            throw new SerdeException(msg);
        }
        return methodOrField instanceof Field ? AnnotationProcessor.pedicateFromField(label, (Field)methodOrField, currentInstance, cls != Object.class) : AnnotationProcessor.predicateFromMethod(label, (Method)methodOrField, currentInstance, cls != Object.class, predicateParameter);
    }

    private static Object findFieldOrMethodIn(Class<?> cls, String name, boolean recurse, Class<?>[] methodParameters) {
        boolean methodOnly = false;
        if (name.endsWith("()")) {
            methodOnly = true;
            name = name.substring(0, name.length() - 2);
        }
        while (true) {
            if (!methodOnly) {
                try {
                    return cls.getDeclaredField(name);
                }
                catch (NoSuchFieldException noSuchFieldException) {
                    // empty catch block
                }
            }
            try {
                return cls.getDeclaredMethod(name, methodParameters);
            }
            catch (NoSuchMethodException noSuchMethodException) {
                if (recurse && (cls = cls.getSuperclass()) != Object.class) continue;
                return null;
            }
            break;
        }
    }

    private static Supplier<?> defaultSupplierFromField(Field field, Object instance, boolean mustBeStatic) {
        return AnnotationProcessor.anyFromField(Supplier.class, "default value provider", field, instance, mustBeStatic);
    }

    private static Supplier<?> defaultSupplierFromMethod(Method method, Object instance, boolean mustBeStatic) {
        return AnnotationProcessor.supplierFromMethod("default value provider", method, instance, mustBeStatic);
    }

    private static <T> T anyFromField(Class<T> t, String label, Field field, Object instance, boolean mustBeStatic) {
        Object value;
        int mods = field.getModifiers();
        if (!Modifier.isPublic(mods)) {
            field.setAccessible(true);
        }
        if (mustBeStatic && !Modifier.isStatic(mods)) {
            String msg = String.format("Invalid %s: field %s should be declared as static.", label, field);
            throw new SerdeException(msg);
        }
        try {
            value = field.get(instance);
        }
        catch (IllegalAccessException | IllegalArgumentException e) {
            String msg = String.format("Could not read the %s `%s` on object `%s`.", label, field, instance);
            throw new SerdeException(msg, e);
        }
        if (value == null) {
            throw new SerdeException(String.format("Invalid %s: field `%s` is null in object `%s`.", label, field, instance));
        }
        if (!t.isAssignableFrom(value.getClass())) {
            throw new SerdeException(String.format("Invalid %s: field `%s` must be of type `%s`.", label, field.getName(), t));
        }
        return (T)value;
    }

    private static Supplier<?> supplierFromMethod(String label, Method method, Object instance, boolean mustBeStatic) {
        if (method.getParameterCount() > 0) {
            throw new SerdeException(String.format("Invalid %s: method %s should take no parameter.", label, method));
        }
        int mods = method.getModifiers();
        if (!Modifier.isPublic(mods)) {
            method.setAccessible(true);
        }
        if (Modifier.isStatic(mods)) {
            return () -> {
                try {
                    return method.invoke(null, new Object[0]);
                }
                catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    throw new SerdeException(String.format("Could not invoke the %s `%s`", label, e));
                }
            };
        }
        if (mustBeStatic) {
            String msg = String.format("Invalid %s: method %s should be declared as static.", label, method);
            throw new SerdeException(msg);
        }
        return () -> {
            try {
                return method.invoke(instance, new Object[0]);
            }
            catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                throw new SerdeException(String.format("Could not invoke the %s `%s` on object %s", label, method, instance), e);
            }
        };
    }

    private static Predicate<?> predicateFromMethod(String label, Method method, Object instance, boolean mustBeStatic, Class<?> parameterType) {
        if (method.getParameterCount() != 1) {
            throw new SerdeException(String.format("Invalid %s: method %s should take exactly one parameter of type %s.", label, method, parameterType));
        }
        if (method.getReturnType() != Boolean.TYPE) {
            throw new SerdeException(String.format("Invalid %s: method %s should return a boolean.", label, method));
        }
        int mods = method.getModifiers();
        if (!Modifier.isPublic(mods)) {
            method.setAccessible(true);
        }
        if (Modifier.isStatic(mods)) {
            return x -> {
                try {
                    return (Boolean)method.invoke(null, x);
                }
                catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    throw new SerdeException(String.format("Could not invoke the %s `%s`", label, e));
                }
            };
        }
        if (mustBeStatic) {
            String msg = String.format("Invalid %s: method %s should be declared as static.", label, method);
            throw new SerdeException(msg);
        }
        return x -> {
            try {
                return (Boolean)method.invoke(instance, x);
            }
            catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                throw new SerdeException(String.format("Could not invoke the %s `%s` on object %s", label, method, instance), e);
            }
        };
    }

    static String annotToString(Annotation annotation) {
        return annotation.toString().replace("@com.electronwill.nightconfig.core.serde.annotations.", "@");
    }

    private static class SerdeAssertSanityCheck {
        boolean hasCustomAssert;
        boolean hasCustomParam;

        private SerdeAssertSanityCheck() {
        }

        void check(SerdeAssert annotation) {
            if (this.hasCustomParam && !this.hasCustomAssert) {
                throw new SerdeException(String.format("Invalid annotation %s: without AssertThat.CUSTOM, no additional parameter must be specified.", AnnotationProcessor.annotToString(annotation)));
            }
        }
    }
}

