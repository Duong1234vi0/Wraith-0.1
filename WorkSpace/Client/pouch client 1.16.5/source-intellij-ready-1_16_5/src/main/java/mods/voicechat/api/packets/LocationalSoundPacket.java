/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api.packets;

import mods.voicechat.api.Position;
import mods.voicechat.api.packets.SoundPacket;

public interface LocationalSoundPacket
extends SoundPacket {
    public Position getPosition();

    public float getDistance();

    public static interface Builder<T extends Builder<T>>
    extends SoundPacket.Builder<T, LocationalSoundPacket> {
        public T position(Position var1);

        public T distance(float var1);
    }
}

