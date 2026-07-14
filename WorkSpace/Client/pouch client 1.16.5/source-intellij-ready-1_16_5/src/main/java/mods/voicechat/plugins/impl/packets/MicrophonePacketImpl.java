/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl.packets;

import java.util.Objects;
import java.util.UUID;
import mods.voicechat.api.Position;
import mods.voicechat.api.packets.EntitySoundPacket;
import mods.voicechat.api.packets.LocationalSoundPacket;
import mods.voicechat.api.packets.MicrophonePacket;
import mods.voicechat.api.packets.StaticSoundPacket;
import mods.voicechat.plugins.impl.PositionImpl;
import mods.voicechat.plugins.impl.packets.EntitySoundPacketImpl;
import mods.voicechat.plugins.impl.packets.LocationalSoundPacketImpl;
import mods.voicechat.plugins.impl.packets.StaticSoundPacketImpl;
import mods.voicechat.voice.common.GroupSoundPacket;
import mods.voicechat.voice.common.LocationSoundPacket;
import mods.voicechat.voice.common.MicPacket;
import mods.voicechat.voice.common.PlayerSoundPacket;
import mods.voicechat.voice.common.Utils;

public class MicrophonePacketImpl
implements MicrophonePacket {
    private final MicPacket packet;
    private final UUID sender;

    public MicrophonePacketImpl(MicPacket packet, UUID sender) {
        this.packet = packet;
        this.sender = sender;
    }

    @Override
    public boolean isWhispering() {
        return this.packet.isWhispering();
    }

    @Override
    public byte[] getOpusEncodedData() {
        return this.packet.getData();
    }

    @Override
    public void setOpusEncodedData(byte[] data) {
        this.packet.setData(Objects.requireNonNull(data));
    }

    @Override
    public EntitySoundPacket.Builder<?> entitySoundPacketBuilder() {
        return new EntitySoundPacketImpl.BuilderImpl(this.sender, this.sender, this.packet.getData(), this.packet.getSequenceNumber(), null);
    }

    @Override
    public LocationalSoundPacket.Builder<?> locationalSoundPacketBuilder() {
        return new LocationalSoundPacketImpl.BuilderImpl(this.sender, this.sender, this.packet.getData(), this.packet.getSequenceNumber(), null);
    }

    @Override
    public StaticSoundPacket.Builder<?> staticSoundPacketBuilder() {
        return new StaticSoundPacketImpl.BuilderImpl(this.sender, this.sender, this.packet.getData(), this.packet.getSequenceNumber(), null);
    }

    @Override
    @Deprecated
    public EntitySoundPacket toEntitySoundPacket(UUID entityUuid, boolean whispering) {
        return new EntitySoundPacketImpl(new PlayerSoundPacket(this.sender, this.sender, this.packet.getData(), this.packet.getSequenceNumber(), whispering, Utils.getDefaultDistanceServer(), null));
    }

    @Override
    @Deprecated
    public LocationalSoundPacket toLocationalSoundPacket(Position position) {
        if (position instanceof PositionImpl) {
            PositionImpl p = (PositionImpl)position;
            return new LocationalSoundPacketImpl(new LocationSoundPacket(this.sender, this.sender, p.getPosition(), this.packet.getData(), this.packet.getSequenceNumber(), Utils.getDefaultDistanceServer(), null));
        }
        throw new IllegalArgumentException("position is not an instance of PositionImpl");
    }

    @Override
    @Deprecated
    public StaticSoundPacket toStaticSoundPacket() {
        return new StaticSoundPacketImpl(new GroupSoundPacket(this.sender, this.sender, this.packet.getData(), this.packet.getSequenceNumber(), null));
    }
}

