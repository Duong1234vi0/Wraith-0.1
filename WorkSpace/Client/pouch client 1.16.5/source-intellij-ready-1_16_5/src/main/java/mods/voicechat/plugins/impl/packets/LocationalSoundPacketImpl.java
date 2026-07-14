/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins.impl.packets;

import java.util.UUID;
import javax.annotation.Nullable;
import mods.voicechat.api.Position;
import mods.voicechat.api.packets.LocationalSoundPacket;
import mods.voicechat.plugins.impl.PositionImpl;
import mods.voicechat.plugins.impl.packets.EntitySoundPacketImpl;
import mods.voicechat.plugins.impl.packets.SoundPacketImpl;
import mods.voicechat.voice.common.LocationSoundPacket;
import mods.voicechat.voice.common.Utils;

public class LocationalSoundPacketImpl
extends SoundPacketImpl
implements LocationalSoundPacket {
    private final LocationSoundPacket packet;
    private final PositionImpl position;

    public LocationalSoundPacketImpl(LocationSoundPacket packet) {
        super(packet);
        this.packet = packet;
        this.position = new PositionImpl(packet.getLocation());
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public float getDistance() {
        return this.packet.getDistance();
    }

    public LocationSoundPacket getPacket() {
        return this.packet;
    }

    public static class BuilderImpl
    extends SoundPacketImpl.BuilderImpl<BuilderImpl, LocationalSoundPacket>
    implements LocationalSoundPacket.Builder<BuilderImpl> {
        protected PositionImpl position;
        protected float distance;

        public BuilderImpl(SoundPacketImpl soundPacket) {
            super(soundPacket);
            if (soundPacket instanceof LocationalSoundPacketImpl) {
                LocationalSoundPacketImpl p = (LocationalSoundPacketImpl)soundPacket;
                this.position = p.position;
                this.distance = p.getDistance();
            } else if (soundPacket instanceof EntitySoundPacketImpl) {
                EntitySoundPacketImpl p = (EntitySoundPacketImpl)soundPacket;
                this.distance = p.getDistance();
            } else {
                this.distance = Utils.getDefaultDistanceServer();
            }
        }

        public BuilderImpl(UUID channelId, UUID sender, byte[] opusEncodedData, long sequenceNumber, @Nullable String category) {
            super(channelId, sender, opusEncodedData, sequenceNumber, category);
            this.distance = Utils.getDefaultDistanceServer();
        }

        @Override
        public BuilderImpl position(Position position) {
            this.position = (PositionImpl)position;
            return this;
        }

        @Override
        public BuilderImpl distance(float distance) {
            this.distance = distance;
            return this;
        }

        @Override
        public LocationalSoundPacket build() {
            if (this.position == null) {
                throw new IllegalStateException("position missing");
            }
            return new LocationalSoundPacketImpl(new LocationSoundPacket(this.channelId, this.sender, this.position.getPosition(), this.opusEncodedData, this.sequenceNumber, this.distance, this.category));
        }
    }
}

