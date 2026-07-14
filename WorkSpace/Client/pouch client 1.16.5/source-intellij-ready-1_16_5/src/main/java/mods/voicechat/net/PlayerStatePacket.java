/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.net;

import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import mods.voicechat.net.Packet;
import mods.voicechat.voice.common.PlayerState;

public class PlayerStatePacket
implements Packet<PlayerStatePacket> {
    public static final g_2336_b PLAYER_STATE = new g_2336_b("voicechat", "state");
    private PlayerState playerState;

    public PlayerStatePacket() {
    }

    public PlayerStatePacket(PlayerState playerState) {
        this.playerState = playerState;
    }

    public PlayerState getPlayerState() {
        return this.playerState;
    }

    @Override
    public g_2336_b getIdentifier() {
        return PLAYER_STATE;
    }

    @Override
    public PlayerStatePacket fromBytes(b_2585_i buf) {
        this.playerState = PlayerState.fromBytes(buf);
        return this;
    }

    @Override
    public void toBytes(b_2585_i buf) {
        this.playerState.toBytes(buf);
    }
}

