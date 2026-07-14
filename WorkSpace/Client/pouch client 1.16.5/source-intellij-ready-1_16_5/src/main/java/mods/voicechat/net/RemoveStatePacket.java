/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.net;

import java.util.UUID;
import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import mods.voicechat.net.Packet;

public class RemoveStatePacket
implements Packet<RemoveStatePacket> {
    public static final g_2336_b REMOVE_STATE = new g_2336_b("voicechat", "remove_state");
    private UUID uuid;

    public RemoveStatePacket() {
    }

    public RemoveStatePacket(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    @Override
    public g_2336_b getIdentifier() {
        return REMOVE_STATE;
    }

    @Override
    public RemoveStatePacket fromBytes(b_2585_i buf) {
        this.uuid = buf.w_1484_f();
        return this;
    }

    @Override
    public void toBytes(b_2585_i buf) {
        buf.n_1700_B(this.uuid);
    }
}

