/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.voice.client;

import lightning.product.D_590_W;
import lightning.product.F_2904_S;
import lightning.product.V_4423_d;
import lightning.product.V_772_m;
import lightning.product.MinecraftClient;
import lightning.product.k_2603_m;
import lightning.product.x_282_a;
import mods.voicechat.Voicechat;
import mods.voicechat.VoicechatClient;
import mods.voicechat.gui.VoiceChatScreen;
import mods.voicechat.gui.VoiceChatSettingsScreen;
import mods.voicechat.gui.group.GroupScreen;
import mods.voicechat.gui.group.JoinGroupScreen;
import mods.voicechat.gui.onboarding.OnboardingManager;
import mods.voicechat.gui.volume.AdjustVolumesScreen;
import mods.voicechat.intercompatibility.ClientCompatibilityManager;
import mods.voicechat.voice.client.ClientManager;
import mods.voicechat.voice.client.ClientPlayerStateManager;
import mods.voicechat.voice.client.ClientVoicechat;
import mods.voicechat.voice.common.ClientGroup;

public class KeyEvents {
    private final MinecraftClient minecraft = MinecraftClient.A_4115_X();
    public static D_590_W[] ALL_KEYS;

    public KeyEvents() {
        ClientCompatibilityManager.INSTANCE.onHandleKeyBinds(this::handleKeybinds);
        ALL_KEYS = new D_590_W[]{V_4423_d.RealmsWorldOptions, V_4423_d.RealmsWorldResetDto, V_4423_d.RegionPingResult, V_4423_d.H_1083_k, V_4423_d.R_3908_n, V_4423_d.ValueObject, V_4423_d.F_1410_V, V_4423_d.S_4022_R, V_4423_d.l_4537_E, V_4423_d.F_2624_D};
    }

    private void handleKeybinds() {
        V_772_m player = this.minecraft.Y_259_p;
        if (player == null) {
            return;
        }
        if (OnboardingManager.isOnboarding()) {
            for (D_590_W allKey : ALL_KEYS) {
                if (!allKey.u_1723_Y()) continue;
                OnboardingManager.startOnboarding(null);
                return;
            }
            return;
        }
        ClientVoicechat client = ClientManager.getClient();
        ClientPlayerStateManager playerStateManager = ClientManager.getPlayerStateManager();
        if (V_4423_d.ValueObject.u_1723_Y()) {
            if (k_2603_m.hasAltDown()) {
                if (k_2603_m.hasControlDown()) {
                    VoicechatClient.CLIENT_CONFIG.onboardingFinished.set((Object)false).save();
                    player.n_1700_B((x_282_a)new F_2904_S("message.voicechat.onboarding.reset"), true);
                } else {
                    ClientManager.getDebugOverlay().toggle();
                }
            } else {
                this.minecraft.n_1700_B(new VoiceChatScreen());
            }
        }
        if (V_4423_d.S_4022_R.G_564_y()) {
            if (client != null && client.getConnection() != null && client.getConnection().getData().groupsEnabled()) {
                ClientGroup group = playerStateManager.getGroup();
                if (group != null) {
                    this.minecraft.n_1700_B(new GroupScreen(group));
                } else {
                    this.minecraft.n_1700_B(new JoinGroupScreen());
                }
            } else {
                player.n_1700_B((x_282_a)new F_2904_S("message.voicechat.groups_disabled"), true);
            }
        }
        if (V_4423_d.F_1410_V.u_1723_Y()) {
            this.minecraft.n_1700_B(new VoiceChatSettingsScreen());
        }
        if (V_4423_d.F_2624_D.u_1723_Y()) {
            this.minecraft.n_1700_B(new AdjustVolumesScreen());
        }
        if (V_4423_d.RealmsWorldOptions.u_1723_Y()) {
            this.checkConnected();
        }
        if (V_4423_d.RealmsWorldResetDto.u_1723_Y()) {
            this.checkConnected();
        }
        if (V_4423_d.RegionPingResult.u_1723_Y()) {
            playerStateManager.setMuted(!playerStateManager.isMuted());
        }
        if (V_4423_d.H_1083_k.u_1723_Y()) {
            playerStateManager.setDisabled(!playerStateManager.isDisabled());
        }
        if (V_4423_d.l_4537_E.u_1723_Y() && client != null) {
            ClientManager.getClient().toggleRecording();
        }
        if (V_4423_d.R_3908_n.u_1723_Y()) {
            boolean hidden = (Boolean)VoicechatClient.CLIENT_CONFIG.hideIcons.get() == false;
            VoicechatClient.CLIENT_CONFIG.hideIcons.set((Object)hidden).save();
            if (hidden) {
                player.n_1700_B((x_282_a)new F_2904_S("message.voicechat.icons_hidden"), true);
            } else {
                player.n_1700_B((x_282_a)new F_2904_S("message.voicechat.icons_visible"), true);
            }
        }
    }

    private boolean checkConnected() {
        if (ClientManager.getClient() == null || ClientManager.getClient().getConnection() == null || !ClientManager.getClient().getConnection().isInitialized()) {
            this.sendNotConnectedMessage();
            return false;
        }
        return true;
    }

    private void sendNotConnectedMessage() {
        V_772_m player = this.minecraft.Y_259_p;
        if (player == null) {
            Voicechat.LOGGER.warn("Voice chat not connected", new Object[0]);
            return;
        }
        player.n_1700_B((x_282_a)new F_2904_S("message.voicechat.voice_chat_not_connected"), true);
    }
}



