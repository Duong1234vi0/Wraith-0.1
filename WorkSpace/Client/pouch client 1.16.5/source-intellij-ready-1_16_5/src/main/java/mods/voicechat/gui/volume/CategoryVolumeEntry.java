/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.gui.volume;

import lightning.product.C_2701_A;
import lightning.product.U_2871_b;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import mods.voicechat.VoicechatClient;
import mods.voicechat.gui.volume.AdjustVolumeSlider;
import mods.voicechat.gui.volume.AdjustVolumesScreen;
import mods.voicechat.gui.volume.VolumeEntry;
import mods.voicechat.plugins.impl.VolumeCategoryImpl;
import mods.voicechat.voice.client.ClientManager;

public class CategoryVolumeEntry
extends VolumeEntry {
    protected final VolumeCategoryImpl category;
    protected final g_2336_b texture;

    public CategoryVolumeEntry(VolumeCategoryImpl category, AdjustVolumesScreen screen) {
        super(screen, new CategoryVolumeConfigEntry(category.getId()));
        this.category = category;
        this.texture = ClientManager.getCategoryManager().getTexture(category.getId(), OTHER_VOLUME_ICON);
    }

    public VolumeCategoryImpl getCategory() {
        return this.category;
    }

    @Override
    public void renderElement(g_221_o poseStack, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovered, float delta, int skinX, int skinY, int textX, int textY) {
        this.minecraft.G_624_v().n_1700_B(this.texture);
        C_2701_A.blit(poseStack, skinX, skinY, 24, 24, 16.0f, 16.0f, 16, 16, 16, 16);
        this.minecraft.t_148_a.J_1907_R(poseStack, new U_2871_b(this.category.getName()), (float)textX, (float)textY, PLAYER_NAME_COLOR);
        if (hovered && this.category.getDescription() != null) {
            this.screen.postRender(() -> this.screen.renderTooltip(poseStack, new U_2871_b(this.category.getDescription()), mouseX, mouseY));
        }
    }

    private static class CategoryVolumeConfigEntry
    implements AdjustVolumeSlider.VolumeConfigEntry {
        private final String category;

        public CategoryVolumeConfigEntry(String category) {
            this.category = category;
        }

        @Override
        public void save(double value) {
            VoicechatClient.CATEGORY_VOLUME_CONFIG.setVolume(this.category, value, new String[0]);
            VoicechatClient.CATEGORY_VOLUME_CONFIG.save();
        }

        @Override
        public double get() {
            return VoicechatClient.CATEGORY_VOLUME_CONFIG.getVolume(this.category);
        }
    }
}

