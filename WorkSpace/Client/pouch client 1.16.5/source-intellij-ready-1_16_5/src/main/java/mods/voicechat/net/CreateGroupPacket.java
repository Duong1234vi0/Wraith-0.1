/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.net;

import javax.annotation.Nullable;
import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import mods.voicechat.api.Group;
import mods.voicechat.net.Packet;
import mods.voicechat.plugins.impl.GroupImpl;

public class CreateGroupPacket
implements Packet<CreateGroupPacket> {
    public static final g_2336_b CREATE_GROUP = new g_2336_b("voicechat", "create_group");
    private String name;
    @Nullable
    private String password;
    private Group.Type type;

    public CreateGroupPacket() {
    }

    public CreateGroupPacket(String name, @Nullable String password, Group.Type type) {
        this.name = name;
        this.password = password;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    @Nullable
    public String getPassword() {
        return this.password;
    }

    public Group.Type getType() {
        return this.type;
    }

    @Override
    public g_2336_b getIdentifier() {
        return CREATE_GROUP;
    }

    @Override
    public CreateGroupPacket fromBytes(b_2585_i buf) {
        this.name = buf.P_1922_E(512);
        this.password = null;
        if (buf.readBoolean()) {
            this.password = buf.P_1922_E(512);
        }
        this.type = GroupImpl.TypeImpl.fromInt(buf.readShort());
        return this;
    }

    @Override
    public void toBytes(b_2585_i buf) {
        buf.n_1700_B(this.name, 512);
        buf.writeBoolean(this.password != null);
        if (this.password != null) {
            buf.n_1700_B(this.password, 512);
        }
        buf.writeShort(GroupImpl.TypeImpl.toInt(this.type));
    }
}

