/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.serde;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.EnumGetMethod;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.serde.DeserializerContext;
import com.electronwill.nightconfig.core.serde.SerdeException;
import com.electronwill.nightconfig.core.serde.TypeConstraint;
import com.electronwill.nightconfig.core.serde.ValueDeserializer;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

final class StandardDeserializers {
    private StandardDeserializers() {
    }

    static final class RiskyNumberDeserializer
    implements ValueDeserializer<Number, Number> {
        RiskyNumberDeserializer() {
        }

        public static boolean isNumberTypeSupported(Class<?> t) {
            return t == Integer.class || t == Integer.TYPE || t == Long.class || t == Long.TYPE;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public Number deserialize(Number value, Optional<TypeConstraint> resultType, DeserializerContext ctx) {
            TypeConstraint numberType = resultType.orElseThrow(() -> new SerdeException("Cannot deserialize a value with a risky number conversion without knowing the number type"));
            Class<?> resultCls = numberType.getSatisfyingRawType().orElseThrow(() -> new SerdeException("Could not find a concrete number type that can satisfy the constraint " + String.valueOf(numberType)));
            Class<?> valueCls = value.getClass();
            if (valueCls == Long.class) {
                long l = value.longValue();
                if (resultCls == Integer.class || resultCls == Integer.TYPE) {
                    int i = (int)l;
                    if ((long)i != l) throw new SerdeException(String.format("Cannot deserialize %s to %s: the conversion would be lossy", value, resultCls));
                    return i;
                }
                if (resultCls == Short.class || resultCls == Short.TYPE) {
                    short s = (short)l;
                    if ((long)s != l) throw new SerdeException(String.format("Cannot deserialize %s to %s: the conversion would be lossy", value, resultCls));
                    return s;
                }
                if (resultCls != Byte.class && resultCls != Byte.TYPE) throw new SerdeException(String.format("Cannot deserialize from %s to %s: risky conversion not implemented, you should change your types.", valueCls, resultCls));
                byte b = (byte)l;
                if ((long)b != l) throw new SerdeException(String.format("Cannot deserialize %s to %s: the conversion would be lossy", value, resultCls));
                return b;
            }
            if (valueCls != Integer.class) throw new SerdeException(String.format("Cannot deserialize %s to %s: the conversion would be lossy", value, resultCls));
            int i = value.intValue();
            if (resultCls == Short.class || resultCls == Short.TYPE) {
                short s = (short)i;
                if (s != i) throw new SerdeException(String.format("Cannot deserialize %s to %s: the conversion would be lossy", value, resultCls));
                return s;
            }
            if (resultCls != Byte.class && resultCls != Byte.TYPE) throw new SerdeException(String.format("Cannot deserialize from %s to %s: risky conversion not implemented, you should change your types.", valueCls, resultCls));
            byte b = (byte)i;
            if (b != i) throw new SerdeException(String.format("Cannot deserialize %s to %s: the conversion would be lossy", value, resultCls));
            return b;
        }
    }

    static final class UuidDeserializer
    implements ValueDeserializer<String, UUID> {
        UuidDeserializer() {
        }

        @Override
        public UUID deserialize(String value, Optional<TypeConstraint> resultType, DeserializerContext ctx) {
            return UUID.fromString(value);
        }
    }

    static final class EnumDeserializer
    implements ValueDeserializer<String, Enum<?>> {
        EnumDeserializer() {
        }

        @Override
        public Enum<?> deserialize(String value, Optional<TypeConstraint> resultType, DeserializerContext ctx) {
            TypeConstraint enumType = resultType.orElseThrow(() -> new SerdeException("Cannot deserialize a value to an enum without knowing the enum type"));
            Class<?> cls = enumType.getSatisfyingRawType().orElseThrow(() -> new SerdeException("Could not find a concrete enum type that can satisfy the constraint " + String.valueOf(enumType)));
            return EnumGetMethod.NAME.get(value, cls);
        }
    }

    static final class CollectionToArrayDeserializer
    implements ValueDeserializer<Collection<?>, Object> {
        CollectionToArrayDeserializer() {
        }

        @Override
        public Object deserialize(Collection<?> collectionValue, Optional<TypeConstraint> resultType, DeserializerContext ctx) {
            Optional<TypeConstraint> valueType;
            Object res;
            int size = collectionValue.size();
            if (resultType.isPresent()) {
                TypeConstraint arrayType = resultType.get();
                Class<?> componentType = ((Class)arrayType.getFullType()).getComponentType();
                assert (componentType != null);
                res = Array.newInstance(componentType, size);
                valueType = Optional.of(new TypeConstraint(componentType));
            } else {
                res = new Object[size];
                valueType = Optional.empty();
            }
            int i = 0;
            for (Object v : collectionValue) {
                Object deserialized = ctx.deserializeValue(v, valueType);
                Array.set(res, i, deserialized);
                ++i;
            }
            return res;
        }
    }

    static final class CollectionDeserializer
    implements ValueDeserializer<Collection<?>, Collection<?>> {
        CollectionDeserializer() {
        }

        @Override
        public Collection<?> deserialize(Collection<?> collectionValue, Optional<TypeConstraint> resultType, DeserializerContext ctx) {
            Optional<TypeConstraint> valueType;
            Collection<Object> res;
            int size = collectionValue.size();
            if (resultType.isPresent()) {
                TypeConstraint collectionType = resultType.get();
                res = this.createCollectionInstance(collectionType.getSatisfyingRawType().get(), size);
                valueType = CollectionDeserializer.extractCollectionValueType(collectionType);
            } else {
                res = new ArrayList(size);
                valueType = Optional.empty();
            }
            for (Object v : collectionValue) {
                Object deserialized = ctx.deserializeValue(v, valueType);
                res.add(deserialized);
            }
            return res;
        }

        private Collection<Object> createCollectionInstance(Class<?> cls, int sizeHint) {
            if (cls.isAssignableFrom(ArrayList.class)) {
                return new ArrayList<Object>(sizeHint);
            }
            if (cls.isAssignableFrom(LinkedList.class)) {
                return new LinkedList<Object>();
            }
            if (cls.isAssignableFrom(ArrayDeque.class)) {
                return new ArrayDeque<Object>(sizeHint);
            }
            try {
                return (Collection)cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            }
            catch (Exception ex) {
                throw new SerdeException("Failed to create an instance of " + String.valueOf(cls), ex);
            }
        }

        private static Optional<TypeConstraint> extractCollectionValueType(TypeConstraint collType) {
            return collType.resolveTypeArgumentsFor(Collection.class).map(c -> c[0]);
        }
    }

    static final class MapDeserializer
    implements ValueDeserializer<Object, Map<String, ?>> {
        MapDeserializer() {
        }

        @Override
        public Map<String, ?> deserialize(Object mapValue, Optional<TypeConstraint> resultType, DeserializerContext ctx) {
            Optional<Object> mapKVType;
            Map<Object, Object> res;
            int size = mapValue instanceof UnmodifiableConfig ? ((UnmodifiableConfig)mapValue).size() : ((Map)mapValue).size();
            if (resultType.isPresent()) {
                TypeConstraint mapType = resultType.get();
                res = MapDeserializer.createMapInstance(mapType.getSatisfyingRawType().get(), size);
                mapKVType = MapDeserializer.extractMapKVType(mapType);
            } else {
                mapKVType = Optional.empty();
                res = Config.isInsertionOrderPreserved() ? new LinkedHashMap(size) : new HashMap(size);
            }
            Optional<TypeConstraint> mapKeyType = mapKVType.map(arr -> arr[0]);
            Optional<TypeConstraint> mapValueType = mapKVType.map(arr -> arr[1]);
            if (mapKeyType.isPresent() && !mapKeyType.get().getSatisfyingRawType().equals(Optional.of(String.class))) {
                throw new SerdeException("Invalid map type for deserialization, the keys should be of type String instead of " + String.valueOf(mapKeyType.get()) + ". Full map type: " + String.valueOf(resultType.get()));
            }
            if (mapValue instanceof UnmodifiableConfig) {
                for (UnmodifiableConfig.Entry entry : ((UnmodifiableConfig)mapValue).entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    Object deserialized = ctx.deserializeValue(value, mapValueType);
                    res.put(key, deserialized);
                }
            } else {
                for (Map.Entry entry : ((Map)mapValue).entrySet()) {
                    Object key = entry.getKey();
                    if (!(key instanceof String)) {
                        String keyClassStr = key == null ? "null" : key.getClass().toString();
                        throw new SerdeException("Invalid map type for deserialization, the keys should be of type String instead of " + keyClassStr + ". Full map type: " + String.valueOf(resultType.get()));
                    }
                    Object value = entry.getValue();
                    Object deserialized = ctx.deserializeValue(value, mapValueType);
                    res.put((String)key, deserialized);
                }
            }
            return res;
        }

        private static Optional<TypeConstraint[]> extractMapKVType(TypeConstraint mapTypeC) {
            return mapTypeC.resolveTypeArgumentsFor(Map.class);
        }

        private static Map<String, Object> createMapInstance(Class<?> cls, int sizeHint) {
            if (cls == Map.class) {
                return Config.isInsertionOrderPreserved() ? new LinkedHashMap(sizeHint) : new HashMap(sizeHint);
            }
            if (cls == LinkedHashMap.class) {
                return new LinkedHashMap<String, Object>(sizeHint);
            }
            if (cls == HashMap.class) {
                return new HashMap<String, Object>(sizeHint);
            }
            if (cls == IdentityHashMap.class) {
                return new IdentityHashMap<String, Object>(sizeHint);
            }
            if (cls.isAssignableFrom(HashMap.class)) {
                if (Config.isInsertionOrderPreserved() && cls.isAssignableFrom(LinkedHashMap.class)) {
                    return new LinkedHashMap<String, Object>(sizeHint);
                }
                return new HashMap<String, Object>(sizeHint);
            }
            if (cls.isAssignableFrom(ConcurrentHashMap.class)) {
                return new ConcurrentHashMap<String, Object>(sizeHint);
            }
            try {
                return (Map)cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            }
            catch (Exception ex) {
                throw new SerdeException("Failed to create an instance of " + String.valueOf(cls), ex);
            }
        }
    }

    static final class TrivialDeserializer
    implements ValueDeserializer<Object, Object> {
        TrivialDeserializer() {
        }

        @Override
        public Object deserialize(Object value, Optional<TypeConstraint> resultType, DeserializerContext ctx) {
            return value;
        }
    }
}

