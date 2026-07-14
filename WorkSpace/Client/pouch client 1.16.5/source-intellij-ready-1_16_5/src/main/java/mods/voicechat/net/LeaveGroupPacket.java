/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.net;

import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import mods.voicechat.net.Packet;

public class LeaveGroupPacket
implements Packet<LeaveGroupPacket> {
    public static final g_2336_b LEAVE_GROUP = new g_2336_b("voicechat", "leave_group");

    @Override
    public g_2336_b getIdentifier() {
        return LEAVE_GROUP;
    }

    @Override
    public LeaveGroupPacket fromBytes(b_2585_i buf) {
        return this;
    }

    @Override
    public void toBytes(b_2585_i buf) {
    }
}

