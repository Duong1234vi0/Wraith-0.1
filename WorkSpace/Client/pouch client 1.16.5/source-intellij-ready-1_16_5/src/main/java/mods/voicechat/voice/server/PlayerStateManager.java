/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.voice.server;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import mods.voicechat.Voicechat;
import mods.voicechat.intercompatibility.CommonCompatibilityManager;
import mods.voicechat.net.NetManager;
import mods.voicechat.net.PlayerStatePacket;
import mods.voicechat.net.PlayerStatesPacket;
import mods.voicechat.plugins.PluginManager;
import mods.voicechat.voice.common.PlayerState;
import mods.voicechat.voice.server.Server;

public class PlayerStateManager {
    private final ConcurrentHashMap<UUID, PlayerState> states;
    private final Server voicechatServer;

    public PlayerStateManager(Server voicechatServer) {
        this.voicechatServer = voicechatServer;
        this.states = new ConcurrentHashMap();
        CommonCompatibilityManager.INSTANCE.getNetManager().updateStateChannel.setServerListener((server, player, handler, packet) -> {
            PlayerState state = this.states.get(player.w_2705_t());
            if (state == null) {
                state = PlayerStateManager.defaultDisconnectedState(player);
            }
            state.setDisabled(packet.isDisabled());
            this.states.put(player.w_2705_t(), state);
            this.broadcastState(state);
            Voicechat.LOGGER.debug("Got state of {}: {}", player.O_1309_Q().getString(), state);
        });
    }

    public void broadcastState(PlayerState state) {
        PlayerStatePacket packet = new PlayerStatePacket(state);
        this.voicechatServer.getServer().p_178_J().w_1457_N().forEach(p -> NetManager.sendToClient(p, packet));
        PluginManager.instance().onPlayerStateChanged(state);
    }

    public void onPlayerCompatibilityCheckSucceeded(B_4088_l player) {
        PlayerStatesPacket packet = new PlayerStatesPacket(this.states.values());
        NetManager.sendToClient(player, packet);
        Voicechat.LOGGER.debug("Sending initial states to {}", player.O_1309_Q().getString());
    }

    public void onPlayerLoggedIn(B_4088_l player) {
        PlayerState state = PlayerStateManager.defaultDisconnectedState(player);
        this.states.put(player.w_2705_t(), state);
        this.broadcastState(state);
        Voicechat.LOGGER.debug("Setting default state of {}: {}", player.O_1309_Q().getString(), state);
    }

    public void onPlayerLoggedOut(B_4088_l player) {
        this.states.remove(player.w_2705_t());
        this.broadcastState(new PlayerState(player.w_2705_t(), player.y_4642_Y().getName(), false, true));
        Voicechat.LOGGER.debug("Removing state of {}", player.O_1309_Q().getString());
    }

    public void onPlayerVoicechatDisconnect(UUID uuid) {
        PlayerState state = this.states.get(uuid);
        if (state == null) {
            return;
        }
        state.setDisconnected(true);
        this.broadcastState(state);
        Voicechat.LOGGER.debug("Set state of {} to disconnected: {}", uuid, state);
    }

    public void onPlayerVoicechatConnect(B_4088_l player) {
        PlayerState state = this.states.get(player.w_2705_t());
        if (state == null) {
            state = PlayerStateManager.defaultDisconnectedState(player);
        }
        state.setDisconnected(false);
        this.states.put(player.w_2705_t(), state);
        this.broadcastState(state);
        Voicechat.LOGGER.debug("Set state of {} to connected: {}", player.O_1309_Q().getString(), state);
    }

    @Nullable
    public PlayerState getState(UUID playerUUID) {
        return this.states.get(playerUUID);
    }

    public static PlayerState defaultDisconnectedState(B_4088_l player) {
        return new PlayerState(player.w_2705_t(), player.y_4642_Y().getName(), false, true);
    }

    public void setGroup(B_4088_l player, @Nullable UUID group) {
        PlayerState state = this.states.get(player.w_2705_t());
        if (state == null) {
            state = PlayerStateManager.defaultDisconnectedState(player);
            Voicechat.LOGGER.debug("Defaulting to default state for {}: {}", player.O_1309_Q().getString(), state);
        }
        state.setGroup(group);
        this.states.put(player.w_2705_t(), state);
        this.broadcastState(state);
        Voicechat.LOGGER.debug("Setting group of {}: {}", player.O_1309_Q().getString(), state);
    }

    public Collection<PlayerState> getStates() {
        return this.states.values();
    }
}

