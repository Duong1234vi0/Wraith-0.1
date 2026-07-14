/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.gui.audiodevice;

import java.util.Objects;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.M_4239_y;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import mods.voicechat.gui.widgets.ListScreenEntryBase;

public class AudioDeviceEntry
extends ListScreenEntryBase<AudioDeviceEntry> {
    protected static final g_2336_b SELECTED = new g_2336_b("voicechat/textures/icons/device_selected.png");
    protected static final int PADDING = 4;
    protected static final int BG_FILL = M_4239_y.n_1700_B.n_1700_B(255, 74, 74, 74);
    protected static final int BG_FILL_HOVERED = M_4239_y.n_1700_B.n_1700_B(255, 90, 90, 90);
    protected static final int BG_FILL_SELECTED = M_4239_y.n_1700_B.n_1700_B(255, 40, 40, 40);
    protected static final int DEVICE_NAME_COLOR = M_4239_y.n_1700_B.n_1700_B(255, 255, 255, 255);
    protected final MinecraftClient minecraft;
    protected final String device;
    protected final String visibleDeviceName;
    @Nullable
    protected final g_2336_b icon;
    protected final Supplier<Boolean> isSelected;

    public AudioDeviceEntry(String device, String name, @Nullable g_2336_b icon, Supplier<Boolean> isSelected) {
        this.device = device;
        this.icon = icon;
        this.isSelected = isSelected;
        this.visibleDeviceName = name;
        this.minecraft = MinecraftClient.A_4115_X();
    }

    @Override
    public void render(g_221_o poseStack, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovered, float delta) {
        boolean selected = this.isSelected.get();
        if (selected) {
            C_2701_A.fill(poseStack, left, top, left + width, top + height, BG_FILL_SELECTED);
        } else if (hovered) {
            C_2701_A.fill(poseStack, left, top, left + width, top + height, BG_FILL_HOVERED);
        } else {
            C_2701_A.fill(poseStack, left, top, left + width, top + height, BG_FILL);
        }
        if (this.icon != null) {
            this.minecraft.G_624_v().n_1700_B(this.icon);
            C_2701_A.blit(poseStack, left + 4, top + height / 2 - 8, 16.0f, 16.0f, 16, 16, 16, 16);
        }
        if (selected) {
            this.minecraft.G_624_v().n_1700_B(SELECTED);
            C_2701_A.blit(poseStack, left + 4, top + height / 2 - 8, 16.0f, 16.0f, 16, 16, 16, 16);
        }
        float deviceWidth = this.minecraft.t_148_a.J_1907_R(this.visibleDeviceName);
        float space = width - 4 - 16 - 4 - 4;
        float scale = Math.min(space / deviceWidth, 1.0f);
        poseStack.n_1700_B();
        double d = left + 4 + 16 + 4;
        float f = top + height / 2;
        Objects.requireNonNull(this.minecraft.t_148_a);
        poseStack.n_1700_B(d, (double)(f - 9.0f * scale / 2.0f), 0.0);
        poseStack.n_1700_B(scale, scale, 1.0f);
        this.minecraft.t_148_a.J_1907_R(poseStack, this.visibleDeviceName, 0.0f, 0.0f, DEVICE_NAME_COLOR);
        poseStack.J_1907_R();
    }

    public String getDevice() {
        return this.device;
    }
}


