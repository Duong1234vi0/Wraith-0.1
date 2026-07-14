/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.serde;

import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.serde.DeserializerContext;
import com.electronwill.nightconfig.core.serde.SerdeException;
import com.electronwill.nightconfig.core.serde.TypeConstraint;
import com.electronwill.nightconfig.core.serde.ValueDeserializer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Optional;

final class ConfigToPojoDeserializer
implements ValueDeserializer<UnmodifiableConfig, Object> {
    ConfigToPojoDeserializer() {
    }

    @Override
    public Object deserialize(UnmodifiableConfig value, Optional<TypeConstraint> resultType, DeserializerContext ctx) {
        Object instance;
        if (!resultType.isPresent()) {
            return value;
        }
        TypeConstraint t = resultType.get();
        Class<?> cls = t.getSatisfyingRawType().orElseThrow(() -> new SerdeException("Could not find a concrete type that can satisfy the constraint " + String.valueOf(t)));
        try {
            Constructor<?> constructor = cls.getDeclaredConstructor(new Class[0]);
            if (!Modifier.isPublic(constructor.getModifiers())) {
                constructor.setAccessible(true);
            }
            instance = constructor.newInstance(new Object[0]);
        }
        catch (Exception e) {
            throw new SerdeException("Failed to create an instance of " + String.valueOf(cls), e);
        }
        ctx.deserializeFields(value, instance);
        return instance;
    }
}

