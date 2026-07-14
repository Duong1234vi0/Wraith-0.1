/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api;

import java.net.SocketAddress;
import mods.voicechat.api.RawUdpPacket;

public interface ClientVoicechatSocket {
    public void open() throws Exception;

    public RawUdpPacket read() throws Exception;

    public void send(byte[] var1, SocketAddress var2) throws Exception;

    public void close();

    public boolean isClosed();
}

