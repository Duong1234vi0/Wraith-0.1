/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.voice.common;

import lightning.product.b_2585_i;
import mods.voicechat.voice.common.Packet;

public class ConnectionCheckPacket
implements Packet<ConnectionCheckPacket> {
    @Override
    public ConnectionCheckPacket fromBytes(b_2585_i buf) {
        return new ConnectionCheckPacket();
    }

    @Override
    public void toBytes(b_2585_i buf) {
    }
}

