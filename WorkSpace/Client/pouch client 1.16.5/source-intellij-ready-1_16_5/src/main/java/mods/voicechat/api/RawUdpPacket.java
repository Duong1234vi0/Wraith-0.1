/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api;

import java.net.SocketAddress;

public interface RawUdpPacket {
    public byte[] getData();

    public long getTimestamp();

    public SocketAddress getSocketAddress();
}

