/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.gui.widgets;

import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.ContainerObjectSelectionList;
import lightning.product.g_221_o;
import mods.voicechat.gui.widgets.ListScreenEntryBase;

public abstract class ListScreenListBase<T extends ListScreenEntryBase<T>>
extends ContainerObjectSelectionList<T> {
    public ListScreenListBase(int width, int height, int top, int size) {
        super(MinecraftClient.A_4115_X(), width, height, top, top + height, size);
    }

    public void updateSize(int width, int height, int top) {
        this.updateSize(width, height, top, top + height);
    }

    @Override
    public void render(g_221_o poseStack, int x, int y, float partialTicks) {
        double scale = this.minecraft.RealmsServerPing().w_1457_N();
        int scaledHeight = this.minecraft.RealmsServerPing().M_182_A();
        c_4037_x.n_1700_B(0, (int)((double)(scaledHeight - this.y1) * scale), 0x3FFFFFFF, (int)((double)this.height * scale));
        super.render(poseStack, x, y, partialTicks);
        c_4037_x.w_1457_N();
    }
}



