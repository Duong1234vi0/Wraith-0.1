/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api;

import java.net.SocketAddress;
import mods.voicechat.api.RawUdpPacket;

public interface VoicechatSocket {
    public void open(int var1, String var2) throws Exception;

    public RawUdpPacket read() throws Exception;

    public void send(byte[] var1, SocketAddress var2) throws Exception;

    public int getLocalPort();

    public void close();

    public boolean isClosed();
}

