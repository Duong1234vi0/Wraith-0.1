/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins.impl.audiolistener;

import java.util.UUID;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import mods.voicechat.api.ServerPlayer;
import mods.voicechat.api.audiolistener.PlayerAudioListener;
import mods.voicechat.api.packets.SoundPacket;

public class PlayerAudioListenerImpl
implements PlayerAudioListener {
    private final UUID playerUuid;
    private final Consumer<SoundPacket> listener;
    private final UUID listenerId;

    public PlayerAudioListenerImpl(UUID playerUuid, Consumer<SoundPacket> listener) {
        this.playerUuid = playerUuid;
        this.listener = listener;
        this.listenerId = UUID.randomUUID();
    }

    @Override
    public UUID getListenerId() {
        return this.listenerId;
    }

    @Override
    public UUID getPlayerUuid() {
        return this.playerUuid;
    }

    public Consumer<SoundPacket> getListener() {
        return this.listener;
    }

    public static class BuilderImpl
    implements PlayerAudioListener.Builder {
        @Nullable
        private UUID playerUuid;
        @Nullable
        private Consumer<SoundPacket> listener;

        @Override
        public PlayerAudioListener.Builder setPlayer(ServerPlayer player) {
            this.playerUuid = player.getUuid();
            return this;
        }

        @Override
        public PlayerAudioListener.Builder setPlayer(UUID playerUuid) {
            this.playerUuid = playerUuid;
            return this;
        }

        @Override
        public PlayerAudioListener.Builder setPacketListener(Consumer<SoundPacket> listener) {
            this.listener = listener;
            return this;
        }

        @Override
        public PlayerAudioListener build() {
            if (this.playerUuid == null) {
                throw new IllegalStateException("No player provided");
            }
            if (this.listener == null) {
                throw new IllegalStateException("No listener provided");
            }
            return new PlayerAudioListenerImpl(this.playerUuid, this.listener);
        }
    }
}

