/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.api.audiochannel;

import java.util.UUID;
import javax.annotation.Nullable;

public interface ClientAudioChannel {
    public UUID getId();

    public void play(short[] var1);

    @Nullable
    public String getCategory();

    public void setCategory(@Nullable String var1);
}

