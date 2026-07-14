/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.serde;

import com.electronwill.nightconfig.core.NullObject;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.serde.AbstractObjectDeserializer;
import com.electronwill.nightconfig.core.serde.AnnotationProcessor;
import com.electronwill.nightconfig.core.serde.SerdeAssertException;
import com.electronwill.nightconfig.core.serde.SerdeException;
import com.electronwill.nightconfig.core.serde.TypeConstraint;
import com.electronwill.nightconfig.core.serde.ValueDeserializer;
import com.electronwill.nightconfig.core.serde.annotations.SerdeAssert;
import com.electronwill.nightconfig.core.serde.annotations.SerdeKey;
import com.electronwill.nightconfig.core.serde.annotations.SerdePhase;
import com.electronwill.nightconfig.core.serde.annotations.SerdeSkipDeserializingIf;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class DeserializerContext {
    final AbstractObjectDeserializer settings;

    DeserializerContext(AbstractObjectDeserializer settings) {
        this.settings = settings;
    }

    public Object deserializeValue(Object value, Optional<TypeConstraint> typeConstraint) {
        TypeConstraint t = typeConstraint.orElse(new TypeConstraint((Type)((Object)Object.class)));
        ValueDeserializer deserializer = this.settings.findValueDeserializer(value, t);
        return deserializer.deserialize(value, typeConstraint, this);
    }

    public void deserializeFields(UnmodifiableConfig source, Object destination) {
        for (Class<?> cls = destination.getClass(); cls != Object.class; cls = cls.getSuperclass()) {
            for (Field field : cls.getDeclaredFields()) {
                Object deserialized;
                List<String> path;
                Object value;
                if (!this.preCheck(field) || this.skipField(field, destination, value = source.getRaw(path = Collections.singletonList(this.configKey(field))))) continue;
                Supplier<?> defaultValueSupplier = this.settings.findDefaultValueSupplier(value, field, destination);
                if (defaultValueSupplier != null) {
                    try {
                        deserialized = defaultValueSupplier.get();
                    }
                    catch (Exception e) {
                        throw new SerdeException("Error in default value provider for field " + String.valueOf(field), e);
                    }
                }
                value = this.normalizeForDeserialization(value, path, field);
                TypeConstraint resultType = new TypeConstraint(field.getGenericType());
                ValueDeserializer deserializer = this.settings.findValueDeserializer(value, resultType);
                try {
                    Optional<TypeConstraint> type = Optional.of(resultType);
                    deserialized = deserializer.deserialize(value, type, this);
                }
                catch (Exception ex) {
                    throw new SerdeException("Error during deserialization of value `" + String.valueOf(value) + "` to field `" + String.valueOf(field) + "` with deserializer " + String.valueOf(deserializer), ex);
                }
                if (!this.assertField(field, destination, value)) {
                    throw new SerdeAssertException("Field `" + String.valueOf(field) + "` has an invalid value: " + String.valueOf(value));
                }
                try {
                    field.set(destination, deserialized);
                }
                catch (Exception e) {
                    throw new SerdeException("Could not assign the deserialized value `" + String.valueOf(deserialized) + "` to the field " + String.valueOf(field) + ". The original config value was " + String.valueOf(value));
                }
            }
        }
    }

    private Object normalizeForDeserialization(Object configValue, List<String> path, Field field) {
        if (configValue == null) {
            throw new SerdeException("Missing configuration entry " + String.valueOf(path) + " for field `" + String.valueOf(field) + "` declared in " + String.valueOf(field.getDeclaringClass()));
        }
        if (configValue == NullObject.NULL_OBJECT) {
            return null;
        }
        return configValue;
    }

    private String configKey(Field field) {
        SerdeKey keyAnnot = field.getAnnotation(SerdeKey.class);
        return keyAnnot == null ? field.getName() : keyAnnot.value();
    }

    private boolean skipField(Field field, Object fieldContainer, Object rawConfigValue) {
        SerdeSkipDeserializingIf annot = field.getAnnotation(SerdeSkipDeserializingIf.class);
        if (annot == null) {
            return false;
        }
        try {
            Predicate<?> skipPredicate = AnnotationProcessor.resolveSkipDeserializingIfPredicate(annot, fieldContainer);
            return skipPredicate.test(rawConfigValue);
        }
        catch (Exception e) {
            String msg = "Failed to resolve or apply skip predicate for deserialization of field " + String.valueOf(field);
            throw new SerdeException(msg, e);
        }
    }

    private boolean assertField(Field field, Object fieldContainer, Object fieldValue) {
        SerdeAssert[] annot = (SerdeAssert[])field.getAnnotationsByType(SerdeAssert.class);
        if (annot == null || annot.length == 0) {
            return true;
        }
        try {
            Predicate<?> assertPredicate = AnnotationProcessor.resolveAssertPredicate(annot, fieldContainer, SerdePhase.DESERIALIZING, field);
            if (assertPredicate == null) {
                return true;
            }
            return assertPredicate.test(fieldValue);
        }
        catch (Exception e) {
            String msg = "Failed to resolve or apply assertion for deserialization of field " + String.valueOf(field);
            throw new SerdeException(msg, e);
        }
    }

    private boolean preCheck(Field field) {
        int mods = field.getModifiers();
        if (Modifier.isStatic(mods) || field.isSynthetic()) {
            return false;
        }
        if (Modifier.isTransient(mods) && this.settings.applyTransientModifier) {
            return false;
        }
        if (Modifier.isFinal(mods) || !Modifier.isPublic(mods)) {
            field.setAccessible(true);
        }
        return true;
    }
}

