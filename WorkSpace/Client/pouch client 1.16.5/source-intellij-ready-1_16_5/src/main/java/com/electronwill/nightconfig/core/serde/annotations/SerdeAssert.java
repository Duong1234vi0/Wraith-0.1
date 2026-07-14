/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.serde.annotations;

import com.electronwill.nightconfig.core.serde.annotations.SerdeAssertsContainer;
import com.electronwill.nightconfig.core.serde.annotations.SerdePhase;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Repeatable(value=SerdeAssertsContainer.class)
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD})
public @interface SerdeAssert {
    public AssertThat[] value();

    public Class<?> customClass() default Object.class;

    public String customCheck() default "";

    public SerdePhase phase() default SerdePhase.BOTH;

    public static enum AssertThat {
        NOT_NULL,
        NOT_EMPTY,
        CUSTOM;

    }
}

