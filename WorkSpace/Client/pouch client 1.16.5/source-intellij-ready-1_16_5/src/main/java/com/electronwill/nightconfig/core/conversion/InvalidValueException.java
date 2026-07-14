/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.conversion;

@Deprecated
public final class InvalidValueException
extends RuntimeException {
    public InvalidValueException(String message) {
        super(message);
    }

    public InvalidValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidValueException(String messageFormat, Object ... args) {
        super(String.format(messageFormat, args));
    }
}

