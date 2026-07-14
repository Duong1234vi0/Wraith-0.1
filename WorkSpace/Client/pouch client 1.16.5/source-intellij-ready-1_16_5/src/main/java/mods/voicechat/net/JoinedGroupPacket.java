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

public class JoinedGroupPacket
implements Packet<JoinedGroupPacket> {
    public static final g_2336_b JOINED_GROUP = new g_2336_b("voicechat", "joined_group");
    @Nullable
    private UUID group;
    private boolean wrongPassword;

    public JoinedGroupPacket() {
    }

    public JoinedGroupPacket(@Nullable UUID group, boolean wrongPassword) {
        this.group = group;
        this.wrongPassword = wrongPassword;
    }

    @Nullable
    public UUID getGroup() {
        return this.group;
    }

    public boolean isWrongPassword() {
        return this.wrongPassword;
    }

    @Override
    public g_2336_b getIdentifier() {
        return JOINED_GROUP;
    }

    @Override
    public JoinedGroupPacket fromBytes(b_2585_i buf) {
        if (buf.readBoolean()) {
            this.group = buf.w_1484_f();
        }
        this.wrongPassword = buf.readBoolean();
        return this;
    }

    @Override
    public void toBytes(b_2585_i buf) {
        buf.writeBoolean(this.group != null);
        if (this.group != null) {
            buf.n_1700_B(this.group);
        }
        buf.writeBoolean(this.wrongPassword);
    }
}

