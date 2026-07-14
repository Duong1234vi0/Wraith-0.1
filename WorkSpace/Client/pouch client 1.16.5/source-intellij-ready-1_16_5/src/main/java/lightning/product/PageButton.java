/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.SimpleSoundInstance;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.SoundEvents;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.i_1140_L;
import lightning.product.k_4218_M;

public class PageButton
extends Button {
    private final boolean n_1700_B;
    private final boolean J_1907_R;

    public PageButton(int x, int y, boolean isForward, Button.n_1700_B onPress, boolean playTurnSound) {
        super(x, y, 23, 13, U_2871_b.R_4764_Y, onPress);
        this.n_1700_B = isForward;
        this.J_1907_R = playTurnSound;
    }

    @Override
    public void renderButton(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        MinecraftClient.A_4115_X().G_624_v().n_1700_B(i_1140_L.J_1907_R);
        int i = 0;
        int j = 192;
        if (this.isHovered()) {
            i += 23;
        }
        if (!this.n_1700_B) {
            j += 13;
        }
        this.blit(matrixStack, this.x, this.y, i, j, 23, 13);
    }

    @Override
    public void playDownSound(k_4218_M handler) {
        if (this.J_1907_R) {
            handler.n_1700_B(SimpleSoundInstance.n_1700_B(SoundEvents.S_980_j, 1.0f));
        }
    }
}



