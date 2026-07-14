/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.serde;

import com.electronwill.nightconfig.core.ConfigFormat;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.serde.ObjectSerializer;
import com.electronwill.nightconfig.core.serde.SerializerContext;
import com.electronwill.nightconfig.core.serde.StandardSerializers;
import com.electronwill.nightconfig.core.serde.Util;
import com.electronwill.nightconfig.core.serde.ValueSerializer;
import com.electronwill.nightconfig.core.serde.ValueSerializerProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class ObjectSerializerBuilder {
    final IdentityHashMap<Class<?>, ValueSerializer<?, ?>> classBasedSerializers = new IdentityHashMap(7);
    final List<ValueSerializerProvider<?, ?>> generalProviders = new ArrayList();
    ValueSerializerProvider<?, ?> defaultProvider = NoProvider.INSTANCE;
    boolean applyTransientModifier = true;

    ObjectSerializerBuilder(boolean standards) {
        if (standards) {
            this.registerStandardSerializers();
        }
    }

    public ObjectSerializer build() {
        return new ObjectSerializer(this);
    }

    public <V, R> void withSerializerForExactClass(Class<V> cls, ValueSerializer<? super V, ? extends R> serializer) {
        this.classBasedSerializers.put(cls, serializer);
    }

    public <V, R> void withSerializerForClass(Class<V> cls, ValueSerializer<? super V, ? extends R> serializer) {
        this.generalProviders.add((valueClass, ctx) -> valueClass != null && Util.canAssign(cls, valueClass) ? serializer : null);
    }

    public <V, R> void withSerializerProvider(ValueSerializerProvider<V, R> provider) {
        this.generalProviders.add(provider);
    }

    public <V, R> void withDefaultSerializerProvider(ValueSerializerProvider<V, R> provider) {
        this.defaultProvider = provider;
    }

    public void withDefaultSerializerProvider() {
        StandardSerializers.TrivialSerializer trivialSer = new StandardSerializers.TrivialSerializer();
        StandardSerializers.FieldsToConfigSerializer fieldsSer = new StandardSerializers.FieldsToConfigSerializer();
        ValueSerializer<Object, Object> numberToIntSer = (value, ctx) -> ((Number)value).intValue();
        ValueSerializer<Object, Object> charToIntSer = (value, ctx) -> (int)((Character)value).charValue();
        this.defaultProvider = (valueClass, ctx) -> {
            ConfigFormat<Integer> format = ctx.configFormat();
            if (format == null || format.supportsType(valueClass)) {
                return trivialSer;
            }
            if (valueClass != null && (Util.isPrimitiveOrWrapper(valueClass) || valueClass == String.class || valueClass.isArray())) {
                if (format.supportsType(Integer.TYPE) && Util.canAssign(Integer.TYPE, valueClass)) {
                    if (valueClass == Character.class || valueClass == Character.TYPE) {
                        return charToIntSer;
                    }
                    return numberToIntSer;
                }
                return null;
            }
            return fieldsSer;
        };
    }

    public void serializeTransientFields() {
        this.applyTransientModifier = false;
    }

    private void registerStandardSerializers() {
        this.withDefaultSerializerProvider();
        StandardSerializers.MapSerializer mapSer = new StandardSerializers.MapSerializer();
        StandardSerializers.CollectionSerializer collSer = new StandardSerializers.CollectionSerializer();
        StandardSerializers.IterableSerializer iterSer = new StandardSerializers.IterableSerializer();
        StandardSerializers.ArraySerializer arraySer = new StandardSerializers.ArraySerializer();
        StandardSerializers.EnumSerializer enumSer = new StandardSerializers.EnumSerializer();
        StandardSerializers.TrivialSerializer trivialSer = new StandardSerializers.TrivialSerializer();
        StandardSerializers.UuidSerializer uuidSer = new StandardSerializers.UuidSerializer();
        this.withSerializerProvider((valueClass, ctx) -> {
            if (valueClass == null) {
                ConfigFormat<?> format = ctx.configFormat();
                if (format == null || format.supportsType(null)) {
                    return trivialSer;
                }
                return null;
            }
            if (Map.class.isAssignableFrom(valueClass)) {
                return mapSer;
            }
            if (Collection.class.isAssignableFrom(valueClass)) {
                return collSer;
            }
            if (Iterable.class.isAssignableFrom(valueClass)) {
                return iterSer;
            }
            if (UnmodifiableConfig.class.isAssignableFrom(valueClass)) {
                return trivialSer;
            }
            if (Enum.class.isAssignableFrom(valueClass)) {
                return enumSer;
            }
            if (valueClass.isArray()) {
                return arraySer;
            }
            if (valueClass == UUID.class) {
                return uuidSer;
            }
            return null;
        });
    }

    static final class NoProvider
    implements ValueSerializerProvider<Object, Object> {
        static final NoProvider INSTANCE = new NoProvider();

        NoProvider() {
        }

        @Override
        public ValueSerializer<Object, Object> provide(Class<?> valueClass, SerializerContext ctx) {
            return null;
        }
    }
}

