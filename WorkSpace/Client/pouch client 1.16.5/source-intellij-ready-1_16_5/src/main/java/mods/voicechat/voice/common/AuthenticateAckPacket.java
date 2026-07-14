/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.voice.common;

import lightning.product.b_2585_i;
import mods.voicechat.voice.common.Packet;

public class AuthenticateAckPacket
implements Packet<AuthenticateAckPacket> {
    @Override
    public AuthenticateAckPacket fromBytes(b_2585_i buf) {
        return new AuthenticateAckPacket();
    }

    @Override
    public void toBytes(b_2585_i buf) {
    }
}

