/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.api.opus;

import javax.annotation.Nullable;

public interface OpusDecoder {
    public short[] decode(@Nullable byte[] var1);

    public void resetState();

    public boolean isClosed();

    public void close();
}

