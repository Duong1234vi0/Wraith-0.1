/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api.packets;

import mods.voicechat.api.packets.SoundPacket;

public interface StaticSoundPacket
extends SoundPacket {

    public static interface Builder<T extends Builder<T>>
    extends SoundPacket.Builder<T, StaticSoundPacket> {
    }
}

