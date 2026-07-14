/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.serde.annotations;

import com.electronwill.nightconfig.core.serde.annotations.SerdeDefaultsContainer;
import com.electronwill.nightconfig.core.serde.annotations.SerdePhase;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Repeatable(value=SerdeDefaultsContainer.class)
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD})
public @interface SerdeDefault {
    public Class<?> cls() default Object.class;

    public String provider();

    public SerdePhase phase() default SerdePhase.BOTH;

    public WhenValue[] whenValue() default {WhenValue.IS_MISSING};

    public static enum WhenValue {
        IS_MISSING,
        IS_NULL,
        IS_EMPTY;

    }
}

