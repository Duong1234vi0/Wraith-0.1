/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.serde;

import com.electronwill.nightconfig.core.serde.SerdeException;

public final class SerdeAssertException
extends SerdeException {
    SerdeAssertException(String message) {
        super(message);
    }

    SerdeAssertException(String message, Throwable cause) {
        super(message, cause);
    }
}

