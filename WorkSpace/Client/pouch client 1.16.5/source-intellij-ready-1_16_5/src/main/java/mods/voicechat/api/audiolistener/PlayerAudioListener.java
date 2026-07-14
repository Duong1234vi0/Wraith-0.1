/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api.audiolistener;

import java.util.UUID;
import java.util.function.Consumer;
import mods.voicechat.api.ServerPlayer;
import mods.voicechat.api.audiolistener.AudioListener;
import mods.voicechat.api.packets.SoundPacket;

public interface PlayerAudioListener
extends AudioListener {
    public UUID getPlayerUuid();

    public static interface Builder {
        public Builder setPlayer(ServerPlayer var1);

        public Builder setPlayer(UUID var1);

        public Builder setPacketListener(Consumer<SoundPacket> var1);

        public PlayerAudioListener build();
    }
}

