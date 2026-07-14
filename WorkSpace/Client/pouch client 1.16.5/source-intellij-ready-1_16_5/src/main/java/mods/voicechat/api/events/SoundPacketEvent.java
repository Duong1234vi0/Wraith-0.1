/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api.events;

import mods.voicechat.api.events.PacketEvent;
import mods.voicechat.api.packets.Packet;

public interface SoundPacketEvent<T extends Packet>
extends PacketEvent<T> {
    public static final String SOURCE_GROUP = "group";
    public static final String SOURCE_PROXIMITY = "proximity";
    public static final String SOURCE_SPECTATOR = "spectator";
    public static final String SOURCE_PLUGIN = "plugin";

    public String getSource();
}

