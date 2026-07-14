/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.gui.volume;

import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.j_3341_s;
import mods.voicechat.VoicechatClient;
import mods.voicechat.gui.GameProfileUtils;
import mods.voicechat.gui.volume.AdjustVolumeSlider;
import mods.voicechat.gui.volume.AdjustVolumesScreen;
import mods.voicechat.gui.volume.VolumeEntry;
import mods.voicechat.voice.common.PlayerState;

public class PlayerVolumeEntry
extends VolumeEntry {
    @Nullable
    protected final PlayerState state;

    public PlayerVolumeEntry(@Nullable PlayerState state, AdjustVolumesScreen screen) {
        super(screen, new PlayerVolumeConfigEntry(state != null ? state.getUuid() : j_3341_s.J_1907_R));
        this.state = state;
    }

    @Nullable
    public PlayerState getState() {
        return this.state;
    }

    @Override
    public void renderElement(g_221_o poseStack, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovered, float delta, int skinX, int skinY, int textX, int textY) {
        if (this.state != null) {
            this.minecraft.G_624_v().n_1700_B(GameProfileUtils.getSkin(this.state.getUuid()));
            C_2701_A.blit(poseStack, skinX, skinY, 24, 24, 8.0f, 8.0f, 8, 8, 64, 64);
            c_4037_x.Y_601_j();
            C_2701_A.blit(poseStack, skinX, skinY, 24, 24, 40.0f, 8.0f, 8, 8, 64, 64);
            c_4037_x.Y_259_p();
            this.minecraft.t_148_a.J_1907_R(poseStack, this.state.getName(), (float)textX, (float)textY, PLAYER_NAME_COLOR);
        } else {
            this.minecraft.G_624_v().n_1700_B(OTHER_VOLUME_ICON);
            C_2701_A.blit(poseStack, skinX, skinY, 24, 24, 16.0f, 16.0f, 16, 16, 16, 16);
            this.minecraft.t_148_a.J_1907_R(poseStack, OTHER_VOLUME, (float)textX, (float)textY, PLAYER_NAME_COLOR);
            if (hovered) {
                this.screen.postRender(() -> this.screen.renderTooltip(poseStack, OTHER_VOLUME_DESCRIPTION, mouseX, mouseY));
            }
        }
    }

    public static class PlayerVolumeConfigEntry
    implements AdjustVolumeSlider.VolumeConfigEntry {
        private final UUID playerUUID;

        public PlayerVolumeConfigEntry(UUID playerUUID) {
            this.playerUUID = playerUUID;
        }

        @Override
        public void save(double value) {
            VoicechatClient.PLAYER_VOLUME_CONFIG.setVolume(this.playerUUID, value, new String[0]);
            VoicechatClient.PLAYER_VOLUME_CONFIG.save();
        }

        @Override
        public double get() {
            return VoicechatClient.PLAYER_VOLUME_CONFIG.getVolume(this.playerUUID);
        }
    }
}

