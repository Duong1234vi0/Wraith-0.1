/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import mods.voicechat.api.ClientVoicechatSocket;
import mods.voicechat.api.RawUdpPacket;
import mods.voicechat.plugins.impl.VoicechatSocketBase;

public class ClientVoicechatSocketImpl
extends VoicechatSocketBase
implements ClientVoicechatSocket {
    private DatagramSocket socket;

    @Override
    public void open() throws Exception {
        this.socket = new DatagramSocket();
    }

    @Override
    public RawUdpPacket read() throws Exception {
        if (this.socket == null) {
            throw new IllegalStateException("Socket not opened yet");
        }
        return this.read(this.socket);
    }

    @Override
    public void send(byte[] data, SocketAddress address) throws Exception {
        if (this.socket == null) {
            return;
        }
        this.socket.send(new DatagramPacket(data, data.length, address));
    }

    @Override
    public void close() {
        if (this.socket != null) {
            this.socket.close();
        }
    }

    @Override
    public boolean isClosed() {
        return this.socket == null;
    }
}

