/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.serde.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD})
public @interface SerdeSkipDeserializingIf {
    public SkipDeIf[] value();

    public Class<?> customClass() default Object.class;

    public String customCheck() default "";

    public static enum SkipDeIf {
        IS_MISSING,
        IS_NULL,
        IS_EMPTY,
        CUSTOM;

    }
}

