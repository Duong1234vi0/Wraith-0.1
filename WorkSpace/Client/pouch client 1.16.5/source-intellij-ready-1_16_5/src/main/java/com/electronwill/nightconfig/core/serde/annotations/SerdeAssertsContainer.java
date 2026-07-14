/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.serde.annotations;

import com.electronwill.nightconfig.core.serde.annotations.SerdeAssert;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD})
public @interface SerdeAssertsContainer {
    public SerdeAssert[] value();
}

