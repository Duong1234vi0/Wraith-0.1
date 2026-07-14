/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.net;

import java.util.UUID;
import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import mods.voicechat.net.Packet;

public class RemoveGroupPacket
implements Packet<RemoveGroupPacket> {
    public static final g_2336_b REMOVE_GROUP = new g_2336_b("voicechat", "remove_group");
    private UUID groupId;

    public RemoveGroupPacket() {
    }

    public RemoveGroupPacket(UUID groupId) {
        this.groupId = groupId;
    }

    public UUID getGroupId() {
        return this.groupId;
    }

    @Override
    public g_2336_b getIdentifier() {
        return REMOVE_GROUP;
    }

    @Override
    public RemoveGroupPacket fromBytes(b_2585_i buf) {
        this.groupId = buf.w_1484_f();
        return this;
    }

    @Override
    public void toBytes(b_2585_i buf) {
        buf.n_1700_B(this.groupId);
    }
}

