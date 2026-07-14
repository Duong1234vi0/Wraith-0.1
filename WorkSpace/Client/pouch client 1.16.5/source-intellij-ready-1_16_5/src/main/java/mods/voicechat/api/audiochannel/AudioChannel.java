/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.api.audiochannel;

import java.util.UUID;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import mods.voicechat.api.ServerPlayer;
import mods.voicechat.api.packets.MicrophonePacket;

public interface AudioChannel {
    public void send(byte[] var1);

    public void send(MicrophonePacket var1);

    public void setFilter(Predicate<ServerPlayer> var1);

    public void flush();

    public boolean isClosed();

    public UUID getId();

    @Nullable
    public String getCategory();

    public void setCategory(@Nullable String var1);
}

