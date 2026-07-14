/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.FormattedText;
import lightning.product.W_3491_f;
import lightning.product.BrewingStandMenu;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.u_530_F;
import lightning.product.x_282_a;
import lightning.product.z_3427_G;

public class BrewingStandScreen
extends z_3427_G<BrewingStandMenu> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/gui/container/brewing_stand.png");
    private static final int[] J_1907_R = new int[]{29, 24, 20, 16, 11, 6, 0};

    public BrewingStandScreen(BrewingStandMenu p_i51097_1_, W_3491_f p_i51097_2_, x_282_a p_i51097_3_) {
        super(p_i51097_1_, p_i51097_2_, p_i51097_3_);
    }

    @Override
    protected void init() {
        super.init();
        this.u_2550_I = (this.t_148_a - this.font.n_1700_B((FormattedText)this.title)) / 2;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.J_1907_R(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void n_1700_B(g_221_o matrixStack, float partialTicks, int x, int y) {
        int i1;
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.G_624_v().n_1700_B(n_1700_B);
        int i = (this.width - this.t_148_a) / 2;
        int j = (this.height - this.s_956_w) / 2;
        this.blit(matrixStack, i, j, 0, 0, this.t_148_a, this.s_956_w);
        int k = ((BrewingStandMenu)this.Q_4569_t).n_1700_B();
        int l = u_530_F.n_1700_B((18 * k + 20 - 1) / 20, 0, 18);
        if (l > 0) {
            this.blit(matrixStack, i + 60, j + 44, 176, 29, l, 4);
        }
        if ((i1 = ((BrewingStandMenu)this.Q_4569_t).J_1907_R()) > 0) {
            int j1 = (int)(28.0f * (1.0f - (float)i1 / 400.0f));
            if (j1 > 0) {
                this.blit(matrixStack, i + 97, j + 16, 176, 0, 9, j1);
            }
            if ((j1 = J_1907_R[i1 / 2 % 7]) > 0) {
                this.blit(matrixStack, i + 63, j + 14 + 29 - j1, 185, 29 - j1, 12, j1);
            }
        }
    }
}


