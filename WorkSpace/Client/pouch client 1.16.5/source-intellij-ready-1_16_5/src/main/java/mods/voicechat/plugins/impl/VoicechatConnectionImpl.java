/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins.impl;

import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import mods.voicechat.Voicechat;
import mods.voicechat.api.ServerPlayer;
import mods.voicechat.api.VoicechatConnection;
import mods.voicechat.plugins.impl.GroupImpl;
import mods.voicechat.plugins.impl.ServerPlayerImpl;
import mods.voicechat.voice.common.PlayerState;
import mods.voicechat.voice.server.Group;
import mods.voicechat.voice.server.PlayerStateManager;
import mods.voicechat.voice.server.Server;

public class VoicechatConnectionImpl
implements VoicechatConnection {
    private final ServerPlayer player;
    private final B_4088_l serverPlayer;
    private final PlayerState state;
    @Nullable
    private final mods.voicechat.api.Group group;

    public VoicechatConnectionImpl(B_4088_l player, PlayerState state) {
        this.serverPlayer = player;
        this.player = new ServerPlayerImpl(player);
        this.state = state;
        this.group = GroupImpl.create(state);
    }

    @Nullable
    public static VoicechatConnectionImpl fromPlayer(@Nullable B_4088_l player) {
        if (player == null) {
            return null;
        }
        Server server = Voicechat.SERVER.getServer();
        if (server == null) {
            return null;
        }
        PlayerState state = server.getPlayerStateManager().getState(player.w_2705_t());
        if (state == null) {
            return null;
        }
        return new VoicechatConnectionImpl(player, state);
    }

    @Override
    @Nullable
    public mods.voicechat.api.Group getGroup() {
        return this.group;
    }

    @Override
    public boolean isInGroup() {
        return this.group != null;
    }

    @Override
    public void setGroup(@Nullable mods.voicechat.api.Group group) {
        Server server = Voicechat.SERVER.getServer();
        if (server == null) {
            return;
        }
        if (group == null) {
            server.getGroupManager().leaveGroup(this.serverPlayer);
            return;
        }
        if (group instanceof GroupImpl) {
            GroupImpl g = (GroupImpl)group;
            Group actualGroup = server.getGroupManager().getGroup(g.getGroup().getId());
            if (actualGroup == null) {
                server.getGroupManager().addGroup(g.getGroup(), this.serverPlayer);
                actualGroup = g.getGroup();
            }
            server.getGroupManager().joinGroup(actualGroup, this.serverPlayer, g.getGroup().getPassword());
        }
    }

    @Override
    public boolean isConnected() {
        return !this.state.isDisconnected();
    }

    @Override
    public void setConnected(boolean connected) {
        if (this.isInstalled()) {
            return;
        }
        Server server = Voicechat.SERVER.getServer();
        if (server == null) {
            return;
        }
        PlayerStateManager manager = server.getPlayerStateManager();
        PlayerState actualState = manager.getState(this.state.getUuid());
        if (actualState == null) {
            return;
        }
        if (actualState.isDisconnected() != connected) {
            return;
        }
        actualState.setDisconnected(!connected);
        manager.broadcastState(actualState);
    }

    @Override
    public boolean isDisabled() {
        return this.state.isDisabled();
    }

    @Override
    public void setDisabled(boolean disabled) {
        if (this.isInstalled()) {
            return;
        }
        Server server = Voicechat.SERVER.getServer();
        if (server == null) {
            return;
        }
        PlayerStateManager manager = server.getPlayerStateManager();
        PlayerState actualState = manager.getState(this.state.getUuid());
        if (actualState == null) {
            return;
        }
        if (actualState.isDisabled() == disabled) {
            return;
        }
        actualState.setDisabled(disabled);
        manager.broadcastState(actualState);
    }

    @Override
    public boolean isInstalled() {
        return Voicechat.SERVER.isCompatible(this.serverPlayer);
    }

    @Override
    public ServerPlayer getPlayer() {
        return this.player;
    }
}

