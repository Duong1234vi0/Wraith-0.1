/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.serde;

import com.electronwill.nightconfig.core.NullObject;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.serde.AnnotationProcessor;
import com.electronwill.nightconfig.core.serde.DeserializerContext;
import com.electronwill.nightconfig.core.serde.ObjectDeserializerBuilder;
import com.electronwill.nightconfig.core.serde.SerdeException;
import com.electronwill.nightconfig.core.serde.TypeConstraint;
import com.electronwill.nightconfig.core.serde.Util;
import com.electronwill.nightconfig.core.serde.ValueDeserializer;
import com.electronwill.nightconfig.core.serde.ValueDeserializerProvider;
import com.electronwill.nightconfig.core.serde.annotations.SerdeDefault;
import com.electronwill.nightconfig.core.serde.annotations.SerdePhase;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

class AbstractObjectDeserializer {
    protected final List<ValueDeserializerProvider<?, ?>> generalProviders;
    protected ValueDeserializerProvider<?, ?> defaultProvider;
    protected final boolean applyTransientModifier;

    protected AbstractObjectDeserializer(ObjectDeserializerBuilder builder) {
        this.generalProviders = builder.deserializerProviders;
        this.defaultProvider = builder.defaultProvider;
        this.applyTransientModifier = builder.applyTransientModifier;
        assert (this.generalProviders != null && this.defaultProvider != null);
    }

    protected <C extends Collection<V>, V> C deserializeToCollection(Object configValue, Class<C> collectionClass, Class<V> valueClass) {
        DeserializerContext ctx = new DeserializerContext(this);
        TypeConstraint t = new TypeConstraint(new TypeConstraint.ManuallyParameterized(collectionClass, valueClass));
        return (C)((Collection)ctx.deserializeValue(configValue, Optional.of(t)));
    }

    protected <M extends Map<String, V>, V> M deserializeToMap(Object configValue, Class<M> mapClass, Class<V> valueClass) {
        DeserializerContext ctx = new DeserializerContext(this);
        TypeConstraint t = new TypeConstraint(new TypeConstraint.ManuallyParameterized(mapClass, new Type[]{String.class, valueClass}));
        return (M)((Map)ctx.deserializeValue(configValue, Optional.of(t)));
    }

    protected void deserializeFields(UnmodifiableConfig source, Object destination) {
        DeserializerContext ctx = new DeserializerContext(this);
        ctx.deserializeFields(source, destination);
    }

    protected <R> R deserializeFields(UnmodifiableConfig source, Supplier<? extends R> destinationSupplier) {
        R dest = destinationSupplier.get();
        this.deserializeFields(source, dest);
        return dest;
    }

    protected <T, R> ValueDeserializer<T, R> findValueDeserializer(T value, TypeConstraint resultType) {
        ValueDeserializer<?, ?> maybeDe;
        Class<?> valueClass = value == null ? null : value.getClass();
        for (ValueDeserializerProvider<?, ?> provider : this.generalProviders) {
            maybeDe = provider.provide(valueClass, resultType);
            if (maybeDe == null) continue;
            return maybeDe;
        }
        maybeDe = this.defaultProvider.provide(valueClass, resultType);
        if (maybeDe != null) {
            return maybeDe;
        }
        String ofTypeStr = valueClass == null ? "" : " of type " + String.valueOf(valueClass);
        throw new SerdeException("No suitable deserializer found for value" + ofTypeStr + ": " + String.valueOf(value) + " and result constraint " + String.valueOf(resultType));
    }

    protected Supplier<?> findDefaultValueSupplier(Object rawConfigValue, Field field, Object instance) {
        EnumMap<SerdeDefault.WhenValue, SerdeDefault> defaultForDeserializing = AnnotationProcessor.getConfigDefaultAnnotations(field).get((Object)SerdePhase.DESERIALIZING);
        if (defaultForDeserializing == null) {
            return null;
        }
        SerdeDefault applicableDefault = null;
        if (rawConfigValue == null) {
            applicableDefault = defaultForDeserializing.get((Object)SerdeDefault.WhenValue.IS_MISSING);
        } else if (rawConfigValue == NullObject.NULL_OBJECT) {
            applicableDefault = defaultForDeserializing.get((Object)SerdeDefault.WhenValue.IS_NULL);
        } else {
            SerdeDefault forEmpty = defaultForDeserializing.get((Object)SerdeDefault.WhenValue.IS_EMPTY);
            if (forEmpty != null && Util.isEmpty(rawConfigValue)) {
                applicableDefault = forEmpty;
            }
        }
        if (applicableDefault == null) {
            return null;
        }
        return AnnotationProcessor.resolveConfigDefaultProvider(applicableDefault, instance);
    }
}

