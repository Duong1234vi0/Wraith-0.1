/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.net;

import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import mods.voicechat.net.Packet;
import mods.voicechat.voice.common.ClientGroup;

public class AddGroupPacket
implements Packet<AddGroupPacket> {
    public static final g_2336_b ADD_ADD_GROUP = new g_2336_b("voicechat", "add_group");
    private ClientGroup group;

    public AddGroupPacket() {
    }

    public AddGroupPacket(ClientGroup group) {
        this.group = group;
    }

    public ClientGroup getGroup() {
        return this.group;
    }

    @Override
    public g_2336_b getIdentifier() {
        return ADD_ADD_GROUP;
    }

    @Override
    public AddGroupPacket fromBytes(b_2585_i buf) {
        this.group = ClientGroup.fromBytes(buf);
        return this;
    }

    @Override
    public void toBytes(b_2585_i buf) {
        this.group.toBytes(buf);
    }
}

