/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins.impl.events;

import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import mods.voicechat.Voicechat;
import mods.voicechat.api.VoicechatConnection;
import mods.voicechat.api.events.PlayerStateChangedEvent;
import mods.voicechat.plugins.impl.VoicechatConnectionImpl;
import mods.voicechat.plugins.impl.events.ServerEventImpl;
import mods.voicechat.voice.common.PlayerState;
import mods.voicechat.voice.server.Server;

public class PlayerStateChangedEventImpl
extends ServerEventImpl
implements PlayerStateChangedEvent {
    protected final PlayerState state;
    @Nullable
    protected VoicechatConnectionImpl connection;

    public PlayerStateChangedEventImpl(PlayerState state) {
        this.state = state;
    }

    @Override
    public boolean isDisabled() {
        return this.state.isDisabled();
    }

    @Override
    public boolean isDisconnected() {
        return this.state.isDisconnected();
    }

    @Override
    public UUID getPlayerUuid() {
        return this.state.getUuid();
    }

    @Override
    @Nullable
    public VoicechatConnection getConnection() {
        if (this.connection == null) {
            Server server = Voicechat.SERVER.getServer();
            if (server == null) {
                return null;
            }
            B_4088_l player = server.getServer().p_178_J().n_1700_B(this.state.getUuid());
            if (player == null) {
                return null;
            }
            this.connection = VoicechatConnectionImpl.fromPlayer(player);
        }
        return this.connection;
    }
}

