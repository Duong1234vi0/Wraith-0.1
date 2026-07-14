/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.serde;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public final class TypeConstraint {
    private final Type fullType;
    private Optional<Class<?>> rawClass = null;

    static TypeConstraint[] mapArray(Type[] t) {
        TypeConstraint[] c = new TypeConstraint[t.length];
        for (int i = 0; i < t.length; ++i) {
            c[i] = new TypeConstraint(t[i]);
        }
        return c;
    }

    public TypeConstraint(Type fullType) {
        this.fullType = fullType;
    }

    public Type getFullType() {
        return this.fullType;
    }

    public Optional<Class<?>> getSatisfyingRawType() {
        if (this.rawClass == null) {
            this.rawClass = Optional.ofNullable(TypeConstraint.findSatisfyingRawType(this.fullType));
        }
        return this.rawClass;
    }

    public Optional<TypeConstraint[]> resolveTypeArgumentsFor(Class<?> classToFind) {
        return Optional.ofNullable(TypeConstraint.resolveTypeArgumentsFor(this.fullType, classToFind, new HashMap()));
    }

    public String toString() {
        return String.format("TypeConstraint[%s, rawType=%s]", this.fullType, this.getSatisfyingRawType());
    }

    private static final Class<?> findSatisfyingRawType(Type t) {
        if (t instanceof Class) {
            return (Class)t;
        }
        if (t instanceof ParameterizedType) {
            return TypeConstraint.findSatisfyingRawType(((ParameterizedType)t).getRawType());
        }
        if (t instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType)t).getGenericComponentType();
            Class<?> componentClass = TypeConstraint.findSatisfyingRawType(componentType);
            if (componentClass == null) {
                return null;
            }
            return Array.newInstance(componentClass, 0).getClass();
        }
        if (t instanceof WildcardType) {
            WildcardType w = (WildcardType)t;
            Type[] lowerBounds = w.getLowerBounds();
            Type[] upperBounds = w.getUpperBounds();
            if (upperBounds.length == 1) {
                Type upper = upperBounds[0];
                if (lowerBounds.length == 1 && upper == Object.class) {
                    return TypeConstraint.findSatisfyingRawType(lowerBounds[0]);
                }
                return TypeConstraint.findSatisfyingRawType(upper);
            }
            if (lowerBounds.length == 1 && upperBounds.length == 0) {
                return TypeConstraint.findSatisfyingRawType(lowerBounds[0]);
            }
            return null;
        }
        if (t instanceof TypeVariable) {
            Type[] bounds = ((TypeVariable)t).getBounds();
            if (bounds.length == 1) {
                return TypeConstraint.findSatisfyingRawType(bounds[0]);
            }
            return null;
        }
        return null;
    }

    private static TypeConstraint[] resolveTypeArgumentsFor(Type t, Class<?> classToFind, Map<TypeVariable<?>, Type> resolvedVariables) {
        if (t instanceof Class) {
            if (t == classToFind) {
                return null;
            }
            return TypeConstraint.findParent((Class)t, parent -> TypeConstraint.resolveTypeArgumentsFor(parent, classToFind, resolvedVariables));
        }
        if (t instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType)t;
            Type rawType = pt.getRawType();
            Type[] actualTypeArgs = pt.getActualTypeArguments();
            for (int i = 0; i < actualTypeArgs.length; ++i) {
                Type typeArg = actualTypeArgs[i];
                if (typeArg instanceof WildcardType) {
                    WildcardType wildcard = (WildcardType)typeArg;
                    Class cls = (Class)rawType;
                    TypeVariable<Class<Object>> declaredTypeParam = cls.getTypeParameters()[i];
                    actualTypeArgs[i] = TypeConstraint.refineWildcard(wildcard, declaredTypeParam, resolvedVariables);
                    continue;
                }
                actualTypeArgs[i] = TypeConstraint.resolveIfVariable(typeArg, resolvedVariables);
            }
            if (rawType == classToFind) {
                return TypeConstraint.mapArray(actualTypeArgs);
            }
            TypeVariable<Class<T>>[] declaredTypeArgs = ((Class)rawType).getTypeParameters();
            for (int i = 0; i < declaredTypeArgs.length; ++i) {
                resolvedVariables.put(declaredTypeArgs[i], actualTypeArgs[i]);
            }
            return TypeConstraint.findParent((Class)rawType, parent -> TypeConstraint.resolveTypeArgumentsFor(parent, classToFind, resolvedVariables));
        }
        if (t instanceof TypeVariable) {
            Type[] bounds = ((TypeVariable)t).getBounds();
            TypeConstraint[] res = null;
            for (Type bound : bounds) {
                res = TypeConstraint.resolveTypeArgumentsFor(bound = TypeConstraint.resolveIfVariable(bound, resolvedVariables), classToFind, resolvedVariables);
                if (res != null) break;
            }
            return res;
        }
        if (t instanceof WildcardType) {
            WildcardType w = (WildcardType)t;
            TypeConstraint[] res = null;
            for (Type bound : w.getUpperBounds()) {
                res = TypeConstraint.resolveTypeArgumentsFor(TypeConstraint.resolveIfVariable(bound, resolvedVariables), classToFind, resolvedVariables);
                if (res == null) continue;
                return res;
            }
            for (Type bound : w.getLowerBounds()) {
                res = TypeConstraint.resolveTypeArgumentsFor(TypeConstraint.resolveIfVariable(bound, resolvedVariables), classToFind, resolvedVariables);
                if (res == null) continue;
                return res;
            }
        }
        return null;
    }

    private static Type resolveIfVariable(Type t, Map<TypeVariable<?>, Type> resolvedVariables) {
        Type resolved;
        if (t instanceof TypeVariable && (resolved = resolvedVariables.get(t)) != null) {
            return resolved;
        }
        return t;
    }

    private static <R> R findParent(Class<?> cls, Function<Type, R> f) {
        R res = null;
        Type parentClass = cls.getGenericSuperclass();
        if (parentClass != null) {
            res = f.apply(parentClass);
        }
        if (res == null) {
            Type parent;
            Type[] parentInterfaces;
            Type[] typeArray = parentInterfaces = cls.getGenericInterfaces();
            int n = typeArray.length;
            for (int i = 0; i < n && (res = (R)f.apply(parent = typeArray[i])) == null; ++i) {
            }
        }
        return res;
    }

    private static Type wildcardLowerBound(WildcardType t) {
        Type[] bounds = t.getLowerBounds();
        if (bounds.length > 0) {
            return bounds[0];
        }
        return null;
    }

    private static Type wildcardUpperBound(WildcardType t) {
        Type[] bounds = t.getUpperBounds();
        if (bounds.length > 0) {
            return bounds[0];
        }
        return null;
    }

    static Type refineWildcard(WildcardType wildcard, TypeVariable<Class<Object>> declaredTypeParam, Map<TypeVariable<?>, Type> resolvedVariables) {
        Type[] refinedUpper;
        if (wildcard instanceof RefinedWildcard) {
            return (RefinedWildcard)wildcard;
        }
        Type upperBound = TypeConstraint.wildcardUpperBound(wildcard);
        Type[] lowerBounds = wildcard.getLowerBounds();
        Type[] declaredUpperBounds = declaredTypeParam.getBounds();
        if (declaredUpperBounds.length == 0 || declaredUpperBounds.length == 1 && (declaredUpperBounds[0] == Object.class || declaredUpperBounds[0] == upperBound)) {
            return wildcard;
        }
        Type[] refinedLower = lowerBounds;
        if (upperBound == null || upperBound == Object.class) {
            refinedUpper = declaredUpperBounds;
        } else {
            ArrayList<Type> upper = new ArrayList<Type>(declaredUpperBounds.length + 1);
            upper.add(upperBound);
            for (int i = 0; i < declaredUpperBounds.length; ++i) {
                Type declaredUpper = declaredUpperBounds[i];
                if (upperBound == declaredUpper) continue;
                upper.add(declaredUpper);
            }
            refinedUpper = upper.toArray(new Type[upper.size()]);
        }
        if (refinedLower.length == 1 && refinedUpper.length == 1 && refinedLower[0].equals(refinedUpper[0])) {
            return refinedLower[0];
        }
        return new RefinedWildcard(refinedLower, refinedUpper);
    }

    static final class RefinedWildcard
    implements WildcardType {
        private final Type[] lowerBounds;
        private final Type[] upperBounds;

        RefinedWildcard(Type[] lowerBounds, Type[] upperBounds) {
            this.lowerBounds = lowerBounds;
            this.upperBounds = upperBounds;
        }

        @Override
        public Type[] getLowerBounds() {
            return this.lowerBounds;
        }

        @Override
        public Type[] getUpperBounds() {
            return this.upperBounds;
        }

        public String toString() {
            String lower = this.lowerBounds.length == 0 ? "" : " >: " + String.valueOf(Arrays.asList(this.lowerBounds));
            String upper = "<: " + String.valueOf(Arrays.asList(this.upperBounds));
            return "?" + lower + " " + upper;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof WildcardType)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            WildcardType other = (WildcardType)obj;
            return Arrays.equals(this.lowerBounds, other.getLowerBounds()) && Arrays.equals(this.upperBounds, other.getUpperBounds());
        }

        public int hashCode() {
            return Objects.hash(this.lowerBounds, this.upperBounds);
        }
    }

    static final class ManuallyParameterized
    implements ParameterizedType {
        private final Type rawType;
        private final Type[] arguments;

        public ManuallyParameterized(Type rawType, Type ... arguments) {
            this.rawType = Objects.requireNonNull(rawType);
            this.arguments = arguments;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return this.arguments;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }

        @Override
        public Type getRawType() {
            return this.rawType;
        }

        public String toString() {
            if (this.arguments.length == 0) {
                return this.rawType.toString();
            }
            return String.valueOf(this.rawType) + "<" + String.join((CharSequence)", ", (CharSequence[])Arrays.stream(this.arguments).map(t -> t.toString()).toArray(String[]::new)) + ">";
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ParameterizedType)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            ParameterizedType other = (ParameterizedType)obj;
            return null == other.getOwnerType() && Objects.equals(this.rawType, other.getRawType()) && Arrays.equals(this.arguments, other.getActualTypeArguments());
        }
    }
}

