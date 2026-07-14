/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api.packets;

import java.util.UUID;
import mods.voicechat.api.Position;
import mods.voicechat.api.packets.EntitySoundPacket;
import mods.voicechat.api.packets.LocationalSoundPacket;
import mods.voicechat.api.packets.StaticSoundPacket;

public interface ConvertablePacket {
    public EntitySoundPacket.Builder<?> entitySoundPacketBuilder();

    public LocationalSoundPacket.Builder<?> locationalSoundPacketBuilder();

    public StaticSoundPacket.Builder<?> staticSoundPacketBuilder();

    @Deprecated
    public EntitySoundPacket toEntitySoundPacket(UUID var1, boolean var2);

    @Deprecated
    public LocationalSoundPacket toLocationalSoundPacket(Position var1);

    @Deprecated
    public StaticSoundPacket toStaticSoundPacket();
}

