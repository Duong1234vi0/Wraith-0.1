/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.conversion;

import com.electronwill.nightconfig.core.EnumGetMethod;
import com.electronwill.nightconfig.core.conversion.AdvancedPath;
import com.electronwill.nightconfig.core.conversion.Conversion;
import com.electronwill.nightconfig.core.conversion.Converter;
import com.electronwill.nightconfig.core.conversion.InvalidValueException;
import com.electronwill.nightconfig.core.conversion.Path;
import com.electronwill.nightconfig.core.conversion.PreserveNotNull;
import com.electronwill.nightconfig.core.conversion.ReflectionException;
import com.electronwill.nightconfig.core.conversion.SpecClassInArray;
import com.electronwill.nightconfig.core.conversion.SpecDoubleInRange;
import com.electronwill.nightconfig.core.conversion.SpecEnum;
import com.electronwill.nightconfig.core.conversion.SpecFloatInRange;
import com.electronwill.nightconfig.core.conversion.SpecIntInRange;
import com.electronwill.nightconfig.core.conversion.SpecLongInRange;
import com.electronwill.nightconfig.core.conversion.SpecNotNull;
import com.electronwill.nightconfig.core.conversion.SpecStringInArray;
import com.electronwill.nightconfig.core.conversion.SpecStringInRange;
import com.electronwill.nightconfig.core.conversion.SpecValidator;
import com.electronwill.nightconfig.core.utils.StringUtils;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

final class AnnotationUtils {
    private AnnotationUtils() {
    }

    static boolean hasPreserveNotNull(AnnotatedElement annotatedElement) {
        return annotatedElement.isAnnotationPresent(PreserveNotNull.class);
    }

    static boolean mustPreserve(Field field, Class<?> fieldClass) {
        return AnnotationUtils.hasPreserveNotNull(field) || AnnotationUtils.hasPreserveNotNull(fieldClass);
    }

    static Converter<Object, Object> getConverter(Field field) {
        Conversion conversion = field.getAnnotation(Conversion.class);
        if (conversion != null) {
            try {
                Constructor<Converter<?, ?>> constructor = conversion.value().getDeclaredConstructor(new Class[0]);
                if (!constructor.isAccessible()) {
                    constructor.setAccessible(true);
                }
                return constructor.newInstance(new Object[0]);
            }
            catch (ReflectiveOperationException ex) {
                throw new ReflectionException("Cannot create a converter for field " + String.valueOf(field), ex);
            }
        }
        return null;
    }

    static List<String> getPath(Field field) {
        List<String> annotatedPath = AnnotationUtils.getPath((AnnotatedElement)field);
        return annotatedPath == null ? Collections.singletonList(field.getName()) : annotatedPath;
    }

    static List<String> getPath(AnnotatedElement annotatedElement) {
        Path path = annotatedElement.getDeclaredAnnotation(Path.class);
        if (path != null) {
            return StringUtils.split(path.value(), '.');
        }
        AdvancedPath advancedPath = annotatedElement.getDeclaredAnnotation(AdvancedPath.class);
        if (advancedPath != null) {
            return Arrays.asList(advancedPath.value());
        }
        return null;
    }

    static void checkField(Field field, Object value) {
        SpecValidator specValidator;
        SpecEnum specEnum;
        SpecIntInRange specIntInRange;
        SpecLongInRange specLongInRange;
        SpecFloatInRange specFloatInRange;
        SpecDoubleInRange specDoubleInRange;
        SpecStringInRange specStringInRange;
        SpecStringInArray specStringInArray;
        SpecClassInArray specClassInArray;
        SpecNotNull specNotNull = field.getDeclaredAnnotation(SpecNotNull.class);
        if (specNotNull != null) {
            AnnotationUtils.checkNotNull(field, value);
        }
        if ((specClassInArray = field.getDeclaredAnnotation(SpecClassInArray.class)) != null) {
            AnnotationUtils.checkFieldSpec(field, value, specClassInArray);
        }
        if ((specStringInArray = field.getDeclaredAnnotation(SpecStringInArray.class)) != null) {
            AnnotationUtils.checkFieldSpec(field, value, specStringInArray);
        }
        if ((specStringInRange = field.getDeclaredAnnotation(SpecStringInRange.class)) != null) {
            AnnotationUtils.checkFieldSpec(field, value, specStringInRange);
        }
        if ((specDoubleInRange = field.getDeclaredAnnotation(SpecDoubleInRange.class)) != null) {
            AnnotationUtils.checkFieldSpec(field, value, specDoubleInRange);
        }
        if ((specFloatInRange = field.getDeclaredAnnotation(SpecFloatInRange.class)) != null) {
            AnnotationUtils.checkFieldSpec(field, value, specFloatInRange);
        }
        if ((specLongInRange = field.getDeclaredAnnotation(SpecLongInRange.class)) != null) {
            AnnotationUtils.checkFieldSpec(field, value, specLongInRange);
        }
        if ((specIntInRange = field.getDeclaredAnnotation(SpecIntInRange.class)) != null) {
            AnnotationUtils.checkFieldSpec(field, value, specIntInRange);
        }
        if ((specEnum = field.getDeclaredAnnotation(SpecEnum.class)) != null) {
            AnnotationUtils.checkFieldSpec(field, value, specEnum);
        }
        if ((specValidator = field.getDeclaredAnnotation(SpecValidator.class)) != null) {
            AnnotationUtils.checkFieldSpec(field, value, specValidator);
        }
    }

    private static void checkFieldSpec(Field field, Object value, SpecValidator spec) {
        Predicate<Object> validatorInstance;
        try {
            Constructor<? extends Predicate<Object>> constructor = spec.value().getDeclaredConstructor(new Class[0]);
            constructor.setAccessible(true);
            validatorInstance = constructor.newInstance(new Object[0]);
        }
        catch (ReflectiveOperationException ex) {
            throw new ReflectionException("Cannot create a converter for field " + String.valueOf(field), ex);
        }
        if (!validatorInstance.test(value)) {
            throw new InvalidValueException("Invalid value \"%s\" for field %s: it doesn't conform to %s", value, field, spec);
        }
    }

    private static void checkFieldSpec(Field field, Object value, SpecClassInArray spec) {
        AnnotationUtils.checkNotNull(field, value);
        Class<?> valueClass = value.getClass();
        if (spec.strict()) {
            for (Class<?> aClass : spec.value()) {
                if (!aClass.isAssignableFrom(valueClass)) continue;
                return;
            }
        } else {
            for (Class<?> aClass : spec.value()) {
                if (!aClass.equals(valueClass)) continue;
                return;
            }
        }
        throw new InvalidValueException("Invalid value \"%s\" for field %s: it doesn't conform to %s", value, field, spec);
    }

    private static void checkFieldSpec(Field field, Object value, SpecStringInRange spec) {
        AnnotationUtils.checkClass(field, value, String.class);
        String s = (String)value;
        if (s.compareTo(spec.min()) < 0 || s.compareTo(spec.max()) > 0) {
            throw new InvalidValueException("Invalid value \"%s\" for field %s: it doesn't conform to %s", value, field, spec);
        }
    }

    private static void checkFieldSpec(Field field, Object value, SpecEnum spec) {
        EnumGetMethod m = spec.method();
        Class<?> fieldType = field.getType();
        if (!fieldType.isEnum()) {
            throw new InvalidValueException("Field %s is annotated with @SpecEnum but isn't of type enum", field);
        }
        Class<?> t = fieldType;
        if (!m.validate(value, t)) {
            throw new InvalidValueException("Invalid value \"%s\" for field %s: it doesn't conform to %s", value, field, spec);
        }
    }

    private static void checkFieldSpec(Field field, Object value, SpecStringInArray spec) {
        AnnotationUtils.checkClass(field, value, String.class);
        String s = (String)value;
        if (spec.ignoreCase()) {
            for (String acceptable : spec.value()) {
                if (!s.equalsIgnoreCase(acceptable)) continue;
                return;
            }
        } else {
            for (String acceptable : spec.value()) {
                if (!s.equals(acceptable)) continue;
                return;
            }
        }
        throw new InvalidValueException("Invalid value \"%s\" for field %s: it doesn't conform to %s", value, field, spec);
    }

    private static void checkFieldSpec(Field field, Object value, SpecDoubleInRange spec) {
        AnnotationUtils.checkClass(field, value, Double.class);
        double d = (Double)value;
        if (d < spec.min() || d > spec.max()) {
            throw new InvalidValueException("Invalid value %f for field %s: it doesn't conform to %s", value, field, spec);
        }
    }

    private static void checkFieldSpec(Field field, Object value, SpecFloatInRange spec) {
        AnnotationUtils.checkClass(field, value, Float.class);
        float d = ((Float)value).floatValue();
        if (d < spec.min() || d > spec.max()) {
            throw new InvalidValueException("Invalid value %f for field %s: it doesn't conform to %s", value, field, spec);
        }
    }

    private static void checkFieldSpec(Field field, Object value, SpecLongInRange spec) {
        AnnotationUtils.checkClass(field, value, Long.class);
        long d = (Long)value;
        if (d < spec.min() || d > spec.max()) {
            throw new InvalidValueException("Invalid value %d for field %s: it doesn't conform to %s", value, field, spec);
        }
    }

    private static void checkFieldSpec(Field field, Object value, SpecIntInRange spec) {
        AnnotationUtils.checkClass(field, value, Integer.class);
        int d = (Integer)value;
        if (d < spec.min() || d > spec.max()) {
            throw new InvalidValueException("Invalid value %d for field %s: it doesn't conform to %s", value, field, spec);
        }
    }

    private static void checkNotNull(Field field, Object value) {
        if (value == null) {
            throw new InvalidValueException("Invalid null value for field %s", field);
        }
    }

    private static void checkClass(Field field, Object value, Class<?> expectedClass) {
        AnnotationUtils.checkNotNull(field, value);
        Class<?> valueClass = value.getClass();
        if (valueClass != expectedClass) {
            throw new InvalidValueException("Invalid type %s for field %s, expected %s", valueClass, field, expectedClass);
        }
    }
}

