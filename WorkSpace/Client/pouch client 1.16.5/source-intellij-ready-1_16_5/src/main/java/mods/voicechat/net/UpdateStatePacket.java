/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.net;

import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import mods.voicechat.net.Packet;

public class UpdateStatePacket
implements Packet<UpdateStatePacket> {
    public static final g_2336_b PLAYER_STATE = new g_2336_b("voicechat", "update_state");
    private boolean disabled;

    public UpdateStatePacket() {
    }

    public UpdateStatePacket(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isDisabled() {
        return this.disabled;
    }

    @Override
    public g_2336_b getIdentifier() {
        return PLAYER_STATE;
    }

    @Override
    public UpdateStatePacket fromBytes(b_2585_i buf) {
        this.disabled = buf.readBoolean();
        return this;
    }

    @Override
    public void toBytes(b_2585_i buf) {
        buf.writeBoolean(this.disabled);
    }
}

