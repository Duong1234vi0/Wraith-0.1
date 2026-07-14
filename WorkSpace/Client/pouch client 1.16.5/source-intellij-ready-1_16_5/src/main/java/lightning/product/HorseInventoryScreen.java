/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Q_1939_l;
import lightning.product.R_3940_n;
import lightning.product.U_2534_D;
import lightning.product.W_3443_Y;
import lightning.product.W_3491_f;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.g_4407_j;
import lightning.product.z_3427_G;

public class HorseInventoryScreen
extends z_3427_G<R_3940_n> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/gui/container/horse.png");
    private final U_2534_D J_1907_R;
    private float R_4764_Y;
    private float G_564_y;

    public HorseInventoryScreen(R_3940_n p_i51084_1_, W_3491_f p_i51084_2_, U_2534_D p_i51084_3_) {
        super(p_i51084_1_, p_i51084_2_, p_i51084_3_.c_());
        this.J_1907_R = p_i51084_3_;
        this.passEvents = false;
    }

    @Override
    protected void n_1700_B(g_221_o matrixStack, float partialTicks, int x, int y) {
        W_3443_Y abstractchestedhorseentity;
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.G_624_v().n_1700_B(n_1700_B);
        int i = (this.width - this.t_148_a) / 2;
        int j = (this.height - this.s_956_w) / 2;
        this.blit(matrixStack, i, j, 0, 0, this.t_148_a, this.s_956_w);
        if (this.J_1907_R instanceof W_3443_Y && (abstractchestedhorseentity = (W_3443_Y)this.J_1907_R).V_1176_p()) {
            this.blit(matrixStack, i + 79, j + 17, 0, this.s_956_w, abstractchestedhorseentity.V_537_k() * 18, 54);
        }
        if (this.J_1907_R.n_1700_B()) {
            this.blit(matrixStack, i + 7, j + 35 - 18, 18, this.s_956_w + 54, 18, 18);
        }
        if (this.J_1907_R.N_4006_T()) {
            if (this.J_1907_R instanceof g_4407_j) {
                this.blit(matrixStack, i + 7, j + 35, 36, this.s_956_w + 54, 18, 18);
            } else {
                this.blit(matrixStack, i + 7, j + 35, 0, this.s_956_w + 54, 18, 18);
            }
        }
        Q_1939_l.n_1700_B(i + 51, j + 60, 17, (float)(i + 51) - this.R_4764_Y, (float)(j + 75 - 50) - this.G_564_y, this.J_1907_R);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.R_4764_Y = mouseX;
        this.G_564_y = mouseY;
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.J_1907_R(matrixStack, mouseX, mouseY);
    }
}


