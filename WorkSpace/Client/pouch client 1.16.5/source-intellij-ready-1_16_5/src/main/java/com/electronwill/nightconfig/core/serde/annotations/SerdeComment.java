/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.serde.annotations;

import com.electronwill.nightconfig.core.serde.annotations.SerdeCommentsContainer;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Repeatable(value=SerdeCommentsContainer.class)
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD})
public @interface SerdeComment {
    public String value();
}

