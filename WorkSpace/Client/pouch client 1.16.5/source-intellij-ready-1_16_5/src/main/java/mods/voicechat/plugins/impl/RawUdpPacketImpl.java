/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl;

import java.net.SocketAddress;
import mods.voicechat.api.RawUdpPacket;

public class RawUdpPacketImpl
implements RawUdpPacket {
    private final byte[] data;
    private final SocketAddress socketAddress;
    private final long timestamp;

    public RawUdpPacketImpl(byte[] data, SocketAddress socketAddress, long timestamp) {
        this.data = data;
        this.socketAddress = socketAddress;
        this.timestamp = timestamp;
    }

    @Override
    public byte[] getData() {
        return this.data;
    }

    @Override
    public long getTimestamp() {
        return this.timestamp;
    }

    @Override
    public SocketAddress getSocketAddress() {
        return this.socketAddress;
    }
}

