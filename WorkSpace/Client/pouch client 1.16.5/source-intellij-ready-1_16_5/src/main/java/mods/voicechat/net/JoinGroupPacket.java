/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.net;

import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import mods.voicechat.net.Packet;

public class JoinGroupPacket
implements Packet<JoinGroupPacket> {
    public static final g_2336_b SET_GROUP = new g_2336_b("voicechat", "set_group");
    private UUID group;
    @Nullable
    private String password;

    public JoinGroupPacket() {
    }

    public JoinGroupPacket(UUID group, @Nullable String password) {
        this.group = group;
        this.password = password;
    }

    public UUID getGroup() {
        return this.group;
    }

    @Nullable
    public String getPassword() {
        return this.password;
    }

    @Override
    public g_2336_b getIdentifier() {
        return SET_GROUP;
    }

    @Override
    public JoinGroupPacket fromBytes(b_2585_i buf) {
        this.group = buf.w_1484_f();
        if (buf.readBoolean()) {
            this.password = buf.P_1922_E(512);
        }
        return this;
    }

    @Override
    public void toBytes(b_2585_i buf) {
        buf.n_1700_B(this.group);
        buf.writeBoolean(this.password != null);
        if (this.password != null) {
            buf.n_1700_B(this.password, 512);
        }
    }
}

