/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.io;

import com.electronwill.nightconfig.core.io.CharsetUnicodeBom;
import java.nio.charset.Charset;

public final class AdditionalCharsets {
    public static final Charset UTF_8_BOM = new CharsetUnicodeBom(true);
    public static final Charset UTF_8_OR_16 = new CharsetUnicodeBom(false);

    private AdditionalCharsets() {
        assert (false);
    }
}

