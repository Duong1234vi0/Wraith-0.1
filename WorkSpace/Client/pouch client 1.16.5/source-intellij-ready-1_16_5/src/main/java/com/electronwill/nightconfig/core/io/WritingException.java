/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.io;

public class WritingException
extends RuntimeException {
    public WritingException(String message) {
        super(message);
    }

    public WritingException(String message, Throwable cause) {
        super(message, cause);
    }

    public WritingException(Throwable cause) {
        this("Failed to write data: ", cause);
    }
}

