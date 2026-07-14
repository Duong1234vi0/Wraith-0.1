/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api.packets;

import java.util.UUID;
import mods.voicechat.api.packets.SoundPacket;

public interface EntitySoundPacket
extends SoundPacket {
    public UUID getEntityUuid();

    public boolean isWhispering();

    public float getDistance();

    public static interface Builder<T extends Builder<T>>
    extends SoundPacket.Builder<T, EntitySoundPacket> {
        public T entityUuid(UUID var1);

        public T whispering(boolean var1);

        public T distance(float var1);
    }
}

