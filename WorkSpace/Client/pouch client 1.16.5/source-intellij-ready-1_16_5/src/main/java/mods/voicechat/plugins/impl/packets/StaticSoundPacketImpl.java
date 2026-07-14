/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins.impl.packets;

import java.util.UUID;
import javax.annotation.Nullable;
import mods.voicechat.api.packets.StaticSoundPacket;
import mods.voicechat.plugins.impl.packets.SoundPacketImpl;
import mods.voicechat.voice.common.GroupSoundPacket;

public class StaticSoundPacketImpl
extends SoundPacketImpl
implements StaticSoundPacket {
    public StaticSoundPacketImpl(GroupSoundPacket packet) {
        super(packet);
    }

    public static class BuilderImpl
    extends SoundPacketImpl.BuilderImpl<BuilderImpl, StaticSoundPacket>
    implements StaticSoundPacket.Builder<BuilderImpl> {
        public BuilderImpl(SoundPacketImpl soundPacket) {
            super(soundPacket);
        }

        public BuilderImpl(UUID channelId, UUID sender, byte[] opusEncodedData, long sequenceNumber, @Nullable String category) {
            super(channelId, sender, opusEncodedData, sequenceNumber, category);
        }

        @Override
        public StaticSoundPacket build() {
            return new StaticSoundPacketImpl(new GroupSoundPacket(this.channelId, this.sender, this.opusEncodedData, this.sequenceNumber, this.category));
        }
    }
}

