/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.voice.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.a_3913_L;
import lightning.product.MinecraftClient;
import lightning.product.k_2603_m;
import lightning.product.x_282_a;
import mods.voicechat.Voicechat;
import mods.voicechat.VoicechatClient;
import mods.voicechat.api.events.ClientVoicechatConnectionEvent;
import mods.voicechat.api.events.MicrophoneMuteEvent;
import mods.voicechat.api.events.VoicechatDisableEvent;
import mods.voicechat.gui.CreateGroupScreen;
import mods.voicechat.gui.EnterPasswordScreen;
import mods.voicechat.gui.group.GroupList;
import mods.voicechat.gui.group.GroupScreen;
import mods.voicechat.gui.group.JoinGroupList;
import mods.voicechat.gui.group.JoinGroupScreen;
import mods.voicechat.gui.onboarding.OnboardingManager;
import mods.voicechat.gui.volume.AdjustVolumeList;
import mods.voicechat.intercompatibility.ClientCompatibilityManager;
import mods.voicechat.intercompatibility.CommonCompatibilityManager;
import mods.voicechat.net.ClientServerNetManager;
import mods.voicechat.net.UpdateStatePacket;
import mods.voicechat.plugins.PluginManager;
import mods.voicechat.plugins.impl.events.ClientVoicechatConnectionEventImpl;
import mods.voicechat.plugins.impl.events.MicrophoneMuteEventImpl;
import mods.voicechat.plugins.impl.events.VoicechatDisableEventImpl;
import mods.voicechat.voice.client.ClientManager;
import mods.voicechat.voice.client.ClientVoicechat;
import mods.voicechat.voice.client.ClientVoicechatConnection;
import mods.voicechat.voice.common.ClientGroup;
import mods.voicechat.voice.common.PlayerState;

public class ClientPlayerStateManager {
    private boolean disconnected = true;
    @Nullable
    private UUID group = null;
    private Map<UUID, PlayerState> states = new HashMap<UUID, PlayerState>();

    public ClientPlayerStateManager() {
        ClientServerNetManager.setClientListener(CommonCompatibilityManager.INSTANCE.getNetManager().playerStateChannel, (client, handler, packet) -> {
            ClientVoicechat c;
            this.states.put(packet.getPlayerState().getUuid(), packet.getPlayerState());
            Voicechat.LOGGER.debug("Got state for {}: {}", packet.getPlayerState().getName(), packet.getPlayerState());
            VoicechatClient.USERNAME_CACHE.updateUsernameAndSave(packet.getPlayerState().getUuid(), packet.getPlayerState().getName());
            if (packet.getPlayerState().isDisconnected() && (c = ClientManager.getClient()) != null) {
                c.closeAudioChannel(packet.getPlayerState().getUuid());
            }
            AdjustVolumeList.update();
            JoinGroupList.update();
            GroupList.update();
        });
        ClientServerNetManager.setClientListener(CommonCompatibilityManager.INSTANCE.getNetManager().playerStatesChannel, (client, handler, packet) -> {
            this.states = new HashMap<UUID, PlayerState>();
            for (PlayerState state : packet.getPlayerStates()) {
                this.states.put(state.getUuid(), state);
                VoicechatClient.USERNAME_CACHE.updateUsername(state.getUuid(), state.getName());
            }
            VoicechatClient.USERNAME_CACHE.save();
            AdjustVolumeList.update();
            JoinGroupList.update();
            GroupList.update();
        });
        ClientServerNetManager.setClientListener(CommonCompatibilityManager.INSTANCE.getNetManager().removeStateChannel, (client, handler, packet) -> {
            this.states.remove(packet.getUuid());
            Voicechat.LOGGER.debug("Removed state for {}", packet.getUuid());
            AdjustVolumeList.update();
            JoinGroupList.update();
            GroupList.update();
        });
        ClientServerNetManager.setClientListener(CommonCompatibilityManager.INSTANCE.getNetManager().joinedGroupChannel, (client, handler, packet) -> {
            k_2603_m screen = MinecraftClient.A_4115_X().Y_1740_V;
            this.group = packet.getGroup();
            if (packet.isWrongPassword()) {
                if (screen instanceof JoinGroupScreen || screen instanceof CreateGroupScreen || screen instanceof EnterPasswordScreen) {
                    MinecraftClient.A_4115_X().n_1700_B((k_2603_m)null);
                }
                client.Y_259_p.n_1700_B((x_282_a)new F_2904_S("message.voicechat.wrong_password").n_1700_B(D_4024_W.P_1922_E), true);
            } else if (this.group != null && screen instanceof JoinGroupScreen || screen instanceof CreateGroupScreen || screen instanceof EnterPasswordScreen) {
                ClientGroup clientGroup = this.getGroup();
                if (clientGroup != null) {
                    MinecraftClient.A_4115_X().n_1700_B(new GroupScreen(clientGroup));
                } else {
                    Voicechat.LOGGER.warn("Received join group packet without group being present", new Object[0]);
                }
            }
            GroupList.update();
        });
        ClientCompatibilityManager.INSTANCE.onVoiceChatConnected(this::onVoiceChatConnected);
        ClientCompatibilityManager.INSTANCE.onVoiceChatDisconnected(this::onVoiceChatDisconnected);
        ClientCompatibilityManager.INSTANCE.onDisconnect(this::onDisconnect);
    }

    private void resetOwnState() {
        this.disconnected = true;
        this.group = null;
    }

    public void onVoiceChatDisconnected() {
        this.disconnected = true;
        this.syncOwnState();
        PluginManager.instance().dispatchEvent(ClientVoicechatConnectionEvent.class, new ClientVoicechatConnectionEventImpl(false));
    }

    public void onVoiceChatConnected(ClientVoicechatConnection client) {
        this.disconnected = false;
        this.syncOwnState();
        PluginManager.instance().dispatchEvent(ClientVoicechatConnectionEvent.class, new ClientVoicechatConnectionEventImpl(true));
    }

    private void onDisconnect() {
        this.clearStates();
        this.resetOwnState();
    }

    public boolean isPlayerDisabled(a_3913_L player) {
        PlayerState playerState = this.states.get(player.w_2705_t());
        if (playerState == null) {
            return false;
        }
        return playerState.isDisabled();
    }

    public boolean isPlayerDisconnected(a_3913_L player) {
        PlayerState playerState = this.states.get(player.w_2705_t());
        if (playerState == null) {
            return (Boolean)VoicechatClient.CLIENT_CONFIG.showFakePlayersDisconnected.get();
        }
        return playerState.isDisconnected();
    }

    public void syncOwnState() {
        ClientServerNetManager.sendToServer(new UpdateStatePacket(this.isDisabled()));
        Voicechat.LOGGER.debug("Sent own state to server: disabled={}", this.isDisabled());
    }

    public boolean isDisabled() {
        if (!this.canEnable()) {
            return true;
        }
        return (Boolean)VoicechatClient.CLIENT_CONFIG.disabled.get();
    }

    public boolean canEnable() {
        if (OnboardingManager.isOnboarding()) {
            return false;
        }
        ClientVoicechat client = ClientManager.getClient();
        if (client == null) {
            return false;
        }
        return client.getSoundManager() != null;
    }

    public void setDisabled(boolean disabled) {
        VoicechatClient.CLIENT_CONFIG.disabled.set((Object)disabled).save();
        this.syncOwnState();
        PluginManager.instance().dispatchEvent(VoicechatDisableEvent.class, new VoicechatDisableEventImpl(disabled));
    }

    public boolean isDisconnected() {
        return this.disconnected;
    }

    public boolean isMuted() {
        return (Boolean)VoicechatClient.CLIENT_CONFIG.muted.get();
    }

    public void setMuted(boolean muted) {
        VoicechatClient.CLIENT_CONFIG.muted.set((Object)muted).save();
        PluginManager.instance().dispatchEvent(MicrophoneMuteEvent.class, new MicrophoneMuteEventImpl(muted));
    }

    public void onFinishOnboarding() {
        this.syncOwnState();
    }

    public boolean isInGroup(a_3913_L player) {
        PlayerState state = this.states.get(player.w_2705_t());
        if (state == null) {
            return false;
        }
        return state.hasGroup();
    }

    @Nullable
    public UUID getGroup(a_3913_L player) {
        PlayerState state = this.states.get(player.w_2705_t());
        if (state == null) {
            return null;
        }
        return state.getGroup();
    }

    @Nullable
    public ClientGroup getGroup() {
        if (this.group == null) {
            return null;
        }
        return ClientManager.getGroupManager().getGroup(this.group);
    }

    @Nullable
    public UUID getGroupID() {
        return this.group;
    }

    public List<PlayerState> getPlayerStates(boolean includeSelf) {
        if (includeSelf) {
            return new ArrayList<PlayerState>(this.states.values());
        }
        return this.states.values().stream().filter(playerState -> !playerState.getUuid().equals(this.getOwnID())).collect(Collectors.toList());
    }

    public UUID getOwnID() {
        ClientVoicechatConnection connection;
        ClientVoicechat client = ClientManager.getClient();
        if (client != null && (connection = client.getConnection()) != null) {
            return connection.getData().getPlayerUUID();
        }
        return MinecraftClient.A_4115_X().z_1737_N().P_1922_E().getId();
    }

    @Nullable
    public PlayerState getState(UUID player) {
        return this.states.get(player);
    }

    public void clearStates() {
        this.states.clear();
    }
}


