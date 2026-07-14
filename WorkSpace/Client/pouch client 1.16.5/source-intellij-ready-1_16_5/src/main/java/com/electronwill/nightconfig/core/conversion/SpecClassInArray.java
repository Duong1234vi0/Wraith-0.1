/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.conversion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Deprecated
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD})
public @interface SpecClassInArray {
    public Class<?>[] value();

    public boolean strict() default false;
}

