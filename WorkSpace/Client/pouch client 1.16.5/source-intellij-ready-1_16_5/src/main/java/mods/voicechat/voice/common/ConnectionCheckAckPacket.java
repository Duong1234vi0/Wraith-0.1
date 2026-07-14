/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.voice.common;

import lightning.product.b_2585_i;
import mods.voicechat.voice.common.Packet;

public class ConnectionCheckAckPacket
implements Packet<ConnectionCheckAckPacket> {
    @Override
    public ConnectionCheckAckPacket fromBytes(b_2585_i buf) {
        return new ConnectionCheckAckPacket();
    }

    @Override
    public void toBytes(b_2585_i buf) {
    }
}

