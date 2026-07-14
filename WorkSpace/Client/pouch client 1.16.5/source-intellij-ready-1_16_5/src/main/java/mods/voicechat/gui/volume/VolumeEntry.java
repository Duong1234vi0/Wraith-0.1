/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.gui.volume;

import lightning.product.C_2701_A;
import lightning.product.F_2904_S;
import lightning.product.M_4239_y;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.x_282_a;
import mods.voicechat.gui.volume.AdjustVolumeSlider;
import mods.voicechat.gui.volume.AdjustVolumesScreen;
import mods.voicechat.gui.widgets.ListScreenEntryBase;

public abstract class VolumeEntry
extends ListScreenEntryBase<VolumeEntry> {
    protected static final x_282_a OTHER_VOLUME = new F_2904_S("message.voicechat.other_volume");
    protected static final x_282_a OTHER_VOLUME_DESCRIPTION = new F_2904_S("message.voicechat.other_volume.description");
    protected static final g_2336_b OTHER_VOLUME_ICON = new g_2336_b("voicechat/textures/icons/other_volume.png");
    protected static final int SKIN_SIZE = 24;
    protected static final int PADDING = 4;
    protected static final int BG_FILL = M_4239_y.n_1700_B.n_1700_B(255, 74, 74, 74);
    protected static final int PLAYER_NAME_COLOR = M_4239_y.n_1700_B.n_1700_B(255, 255, 255, 255);
    protected final MinecraftClient minecraft = MinecraftClient.A_4115_X();
    protected final AdjustVolumesScreen screen;
    protected final AdjustVolumeSlider volumeSlider;

    public VolumeEntry(AdjustVolumesScreen screen, AdjustVolumeSlider.VolumeConfigEntry entry) {
        this.screen = screen;
        this.volumeSlider = new AdjustVolumeSlider(0, 0, 100, 20, entry);
        this.children.add(this.volumeSlider);
    }

    @Override
    public void render(g_221_o poseStack, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovered, float delta) {
        int skinX = left + 4;
        int skinY = top + (height - 24) / 2;
        int textX = skinX + 24 + 4;
        int textY = top + (height - this.minecraft.t_148_a.n_1700_B) / 2;
        C_2701_A.fill(poseStack, left, top, left + width, top + height, BG_FILL);
        this.renderElement(poseStack, index, top, left, width, height, mouseX, mouseY, hovered, delta, skinX, skinY, textX, textY);
        this.volumeSlider.x = left + (width - this.volumeSlider.getWidth() - 4);
        this.volumeSlider.y = top + (height - this.volumeSlider.getHeightRealms()) / 2;
        this.volumeSlider.render(poseStack, mouseX, mouseY, delta);
    }

    public abstract void renderElement(g_221_o var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, boolean var9, float var10, int var11, int var12, int var13, int var14);
}


