/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.net;

import java.util.ArrayList;
import java.util.Collection;
import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import mods.voicechat.net.Packet;
import mods.voicechat.voice.common.PlayerState;

public class PlayerStatesPacket
implements Packet<PlayerStatesPacket> {
    private Collection<PlayerState> playerStates;
    public static final g_2336_b PLAYER_STATES = new g_2336_b("voicechat", "states");

    public PlayerStatesPacket() {
    }

    public PlayerStatesPacket(Collection<PlayerState> playerStates) {
        this.playerStates = playerStates;
    }

    public Collection<PlayerState> getPlayerStates() {
        return this.playerStates;
    }

    @Override
    public g_2336_b getIdentifier() {
        return PLAYER_STATES;
    }

    @Override
    public PlayerStatesPacket fromBytes(b_2585_i buf) {
        int count = buf.readInt();
        this.playerStates = new ArrayList<PlayerState>(count);
        for (int i = 0; i < count; ++i) {
            PlayerState playerState = PlayerState.fromBytes(buf);
            this.playerStates.add(playerState);
        }
        return this;
    }

    @Override
    public void toBytes(b_2585_i buf) {
        buf.writeInt(this.playerStates.size());
        for (PlayerState state : this.playerStates) {
            state.toBytes(buf);
        }
    }
}

