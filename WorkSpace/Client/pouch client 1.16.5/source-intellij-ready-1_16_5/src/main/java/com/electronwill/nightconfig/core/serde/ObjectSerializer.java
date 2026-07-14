/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.serde;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.ConfigFormat;
import com.electronwill.nightconfig.core.serde.AnnotationProcessor;
import com.electronwill.nightconfig.core.serde.ObjectSerializerBuilder;
import com.electronwill.nightconfig.core.serde.SerdeException;
import com.electronwill.nightconfig.core.serde.SerializerContext;
import com.electronwill.nightconfig.core.serde.Util;
import com.electronwill.nightconfig.core.serde.ValueSerializer;
import com.electronwill.nightconfig.core.serde.ValueSerializerProvider;
import com.electronwill.nightconfig.core.serde.annotations.SerdeDefault;
import com.electronwill.nightconfig.core.serde.annotations.SerdePhase;
import java.lang.reflect.Field;
import java.util.EnumMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.function.Supplier;

public final class ObjectSerializer {
    private final IdentityHashMap<Class<?>, ValueSerializer<?, ?>> classBasedSerializers;
    private final List<ValueSerializerProvider<?, ?>> generalProviders;
    private final ValueSerializerProvider<?, ?> defaultProvider;
    final boolean applyTransientModifier;

    public static ObjectSerializerBuilder builder() {
        return new ObjectSerializerBuilder(true);
    }

    public static ObjectSerializerBuilder blankBuilder() {
        return new ObjectSerializerBuilder(false);
    }

    public static ObjectSerializer standard() {
        return ObjectSerializer.builder().build();
    }

    ObjectSerializer(ObjectSerializerBuilder builder) {
        this.classBasedSerializers = builder.classBasedSerializers;
        this.generalProviders = builder.generalProviders;
        this.defaultProvider = builder.defaultProvider;
        this.applyTransientModifier = builder.applyTransientModifier;
        assert (this.classBasedSerializers != null && this.generalProviders != null && this.defaultProvider != null);
    }

    public Object serialize(Object value, Supplier<? extends Config> configSupplier) {
        SerializerContext ctx = new SerializerContext(this, () -> ((Config)configSupplier.get()).configFormat(), configSupplier);
        return ctx.serializeValue(value);
    }

    public <C extends Config> C serializeFields(Object source, Supplier<C> configSupplier) {
        Config dest = (Config)configSupplier.get();
        this.serializeFields(source, dest);
        return (C)dest;
    }

    public void serializeFields(Object source, Config destination) {
        SerializerContext ctx = new SerializerContext(this, () -> destination.configFormat(), () -> destination.createSubConfig());
        ctx.serializeFields(source, destination);
    }

    <T, R> ValueSerializer<T, R> findValueSerializer(Object value, SerializerContext ctx) {
        Class<?> valueClass = value == null ? null : value.getClass();
        ValueSerializer<?, ?> maybeSe = null;
        for (ValueSerializerProvider<?, ?> provider : this.generalProviders) {
            maybeSe = provider.provide(valueClass, ctx);
            if (maybeSe == null) continue;
            return maybeSe;
        }
        maybeSe = this.classBasedSerializers.get(valueClass);
        if (maybeSe != null) {
            return maybeSe;
        }
        maybeSe = this.defaultProvider.provide(valueClass, ctx);
        if (maybeSe != null) {
            return maybeSe;
        }
        throw ObjectSerializer.noSerializerFound(value, valueClass, ctx);
    }

    Supplier<?> findDefaultValueSupplier(Object fieldValue, Field field, Object instance) {
        EnumMap<SerdeDefault.WhenValue, SerdeDefault> defaultForSerializing = AnnotationProcessor.getConfigDefaultAnnotations(field).get((Object)SerdePhase.SERIALIZING);
        if (defaultForSerializing == null) {
            return null;
        }
        SerdeDefault applicableDefault = null;
        if (fieldValue == null) {
            applicableDefault = defaultForSerializing.get((Object)SerdeDefault.WhenValue.IS_NULL);
        } else {
            SerdeDefault forEmpty = defaultForSerializing.get((Object)SerdeDefault.WhenValue.IS_EMPTY);
            if (forEmpty != null && Util.isEmpty(fieldValue)) {
                applicableDefault = forEmpty;
            }
        }
        if (applicableDefault == null) {
            return null;
        }
        return AnnotationProcessor.resolveConfigDefaultProvider(applicableDefault, instance);
    }

    static SerdeException noSerializerFound(Object value, Class<?> valueClass, SerializerContext ctx) {
        ConfigFormat<?> format = ctx.configFormat();
        String supportedStr = format == null ? "The current SerializerContext has no ConfigFormat. Is there a bug in the implementation of the chosen ClientConfig type?" : (format.supportsType(valueClass) ? "The value's type is supported by the ConfigFormat of the current SerializerContext." : "The value's type is NOT supported by the ConfigFormat of the current SerializerContext.");
        String ofTypeStr = valueClass == null ? "" : " of type " + String.valueOf(valueClass);
        return new SerdeException("No suitable serializer found for value" + ofTypeStr + ": " + String.valueOf(value) + ". " + supportedStr);
    }
}

