/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.serde;

import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.serde.ConfigToPojoDeserializer;
import com.electronwill.nightconfig.core.serde.ObjectDeserializer;
import com.electronwill.nightconfig.core.serde.StandardDeserializers;
import com.electronwill.nightconfig.core.serde.TypeConstraint;
import com.electronwill.nightconfig.core.serde.Util;
import com.electronwill.nightconfig.core.serde.ValueDeserializer;
import com.electronwill.nightconfig.core.serde.ValueDeserializerProvider;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class ObjectDeserializerBuilder {
    final List<ValueDeserializerProvider<?, ?>> deserializerProviders = new ArrayList();
    ValueDeserializerProvider<?, ?> defaultProvider = NoProvider.INSTANCE;
    boolean applyTransientModifier = true;

    ObjectDeserializerBuilder(boolean standards) {
        if (standards) {
            this.registerStandardDeserializers();
        }
    }

    public ObjectDeserializer build() {
        return new ObjectDeserializer(this);
    }

    public void deserializeTransientFields() {
        this.applyTransientModifier = false;
    }

    public <V, R> void withDeserializerForClass(Class<V> valueClass, Class<R> resultClass, ValueDeserializer<? super V, ? extends R> deserializer) {
        this.withDeserializerProvider((valueCls, resultType) -> resultType.getSatisfyingRawType().map(resultCls -> {
            if (valueCls.isAssignableFrom(valueClass) && resultCls.isAssignableFrom(resultClass)) {
                return deserializer;
            }
            return null;
        }).orElse(null));
    }

    public <V, R> void withDeserializerProvider(ValueDeserializerProvider<V, R> provider) {
        this.deserializerProviders.add(provider);
    }

    public <V, R> void withDefaultDeserializerProvider(ValueDeserializerProvider<V, R> provider) {
        this.defaultProvider = provider;
    }

    public void withDefaultDeserializerProvider() {
        ConfigToPojoDeserializer pojoDe = new ConfigToPojoDeserializer();
        this.defaultProvider = (valueClass, resultType) -> {
            if (UnmodifiableConfig.class.isAssignableFrom(valueClass)) {
                return pojoDe;
            }
            return null;
        };
    }

    private void registerStandardDeserializers() {
        this.withDefaultDeserializerProvider();
        StandardDeserializers.TrivialDeserializer trivialDe = new StandardDeserializers.TrivialDeserializer();
        StandardDeserializers.MapDeserializer mapDe = new StandardDeserializers.MapDeserializer();
        StandardDeserializers.CollectionDeserializer collDe = new StandardDeserializers.CollectionDeserializer();
        StandardDeserializers.CollectionToArrayDeserializer arrDe = new StandardDeserializers.CollectionToArrayDeserializer();
        StandardDeserializers.EnumDeserializer enumDe = new StandardDeserializers.EnumDeserializer();
        StandardDeserializers.UuidDeserializer uuidDe = new StandardDeserializers.UuidDeserializer();
        StandardDeserializers.RiskyNumberDeserializer numberDe = new StandardDeserializers.RiskyNumberDeserializer();
        this.withDeserializerProvider((valueClass, resultType) -> {
            Type fullType = resultType.getFullType();
            return resultType.getSatisfyingRawType().map(resultClass -> {
                if (Util.canAssign(resultClass, valueClass) && (valueClass == null || fullType instanceof Class)) {
                    return trivialDe;
                }
                if (Collection.class.isAssignableFrom(valueClass)) {
                    if (Collection.class.isAssignableFrom((Class<?>)resultClass)) {
                        return collDe;
                    }
                    if (resultClass.isArray()) {
                        return arrDe;
                    }
                }
                if ((UnmodifiableConfig.class.isAssignableFrom(valueClass) || Map.class.isAssignableFrom(valueClass)) && Map.class.isAssignableFrom((Class<?>)resultClass)) {
                    return mapDe;
                }
                if (resultClass == UUID.class && valueClass == String.class) {
                    return uuidDe;
                }
                if (valueClass == String.class && Enum.class.isAssignableFrom((Class<?>)resultClass)) {
                    return enumDe;
                }
                if (StandardDeserializers.RiskyNumberDeserializer.isNumberTypeSupported(valueClass) && Util.isPrimitiveOrWrapperNumber(resultClass)) {
                    return numberDe;
                }
                return null;
            }).orElse(null);
        });
    }

    static final class NoProvider
    implements ValueDeserializerProvider<Object, Object> {
        static final NoProvider INSTANCE = new NoProvider();

        NoProvider() {
        }

        @Override
        public ValueDeserializer<Object, Object> provide(Class<?> valueClass, TypeConstraint resultType) {
            return null;
        }
    }
}

