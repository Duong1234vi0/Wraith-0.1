/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.F_3620_e;
import lightning.product.G_3165_y;
import lightning.product.W_3491_f;
import lightning.product.Z_1993_T;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.i_3895_t;
import lightning.product.l_3747_P;
import lightning.product.o_3091_w;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.x_282_a;
import lightning.product.z_3427_G;

public class CartographyTableScreen
extends z_3427_G<i_3895_t> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/gui/container/cartography_table.png");

    public CartographyTableScreen(i_3895_t screenContainer, W_3491_f inv, x_282_a titleIn) {
        super(screenContainer, inv, titleIn);
        this.M_588_G -= 2;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.J_1907_R(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void n_1700_B(g_221_o matrixStack, float partialTicks, int x, int y) {
        F_3620_e mapdata;
        this.renderBackground(matrixStack);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.G_624_v().n_1700_B(n_1700_B);
        int i = this.multiplayerClientSuggestionProvider;
        int j = this.w_1457_N;
        this.blit(matrixStack, i, j, 0, 0, this.t_148_a, this.s_956_w);
        q_1613_l item = ((i_3895_t)this.Q_4569_t).n_1700_B(1).n_1700_B().J_1907_R();
        boolean flag = item == Items.S_1431_H;
        boolean flag1 = item == Items.l_3370_o;
        boolean flag2 = item == Items.U_4087_m;
        Z_1993_T itemstack = ((i_3895_t)this.Q_4569_t).n_1700_B(0).n_1700_B();
        boolean flag3 = false;
        if (itemstack.J_1907_R() == Items.K_4518_s) {
            mapdata = G_3165_y.n_1700_B(itemstack, this.minecraft.Y_601_j);
            if (mapdata != null) {
                if (mapdata.w_1484_f) {
                    flag3 = true;
                    if (flag1 || flag2) {
                        this.blit(matrixStack, i + 35, j + 31, this.t_148_a + 50, 132, 28, 21);
                    }
                }
                if (flag1 && mapdata.u_1723_Y >= 4) {
                    flag3 = true;
                    this.blit(matrixStack, i + 35, j + 31, this.t_148_a + 50, 132, 28, 21);
                }
            }
        } else {
            mapdata = null;
        }
        this.n_1700_B(matrixStack, mapdata, flag, flag1, flag2, flag3);
    }

    private void n_1700_B(g_221_o p_238807_1_, @Nullable F_3620_e p_238807_2_, boolean p_238807_3_, boolean p_238807_4_, boolean p_238807_5_, boolean p_238807_6_) {
        int i = this.multiplayerClientSuggestionProvider;
        int j = this.w_1457_N;
        if (p_238807_4_ && !p_238807_6_) {
            this.blit(p_238807_1_, i + 67, j + 13, this.t_148_a, 66, 66, 66);
            this.n_1700_B(p_238807_2_, i + 85, j + 31, 0.226f);
        } else if (p_238807_3_) {
            this.blit(p_238807_1_, i + 67 + 16, j + 13, this.t_148_a, 132, 50, 66);
            this.n_1700_B(p_238807_2_, i + 86, j + 16, 0.34f);
            this.minecraft.G_624_v().n_1700_B(n_1700_B);
            c_4037_x.v_4276_D();
            c_4037_x.R_4764_Y(0.0f, 0.0f, 1.0f);
            this.blit(p_238807_1_, i + 67, j + 13 + 16, this.t_148_a, 132, 50, 66);
            this.n_1700_B(p_238807_2_, i + 70, j + 32, 0.34f);
            c_4037_x.d_2461_k();
        } else if (p_238807_5_) {
            this.blit(p_238807_1_, i + 67, j + 13, this.t_148_a, 0, 66, 66);
            this.n_1700_B(p_238807_2_, i + 71, j + 17, 0.45f);
            this.minecraft.G_624_v().n_1700_B(n_1700_B);
            c_4037_x.v_4276_D();
            c_4037_x.R_4764_Y(0.0f, 0.0f, 1.0f);
            this.blit(p_238807_1_, i + 66, j + 12, 0, this.s_956_w, 66, 66);
            c_4037_x.d_2461_k();
        } else {
            this.blit(p_238807_1_, i + 67, j + 13, this.t_148_a, 0, 66, 66);
            this.n_1700_B(p_238807_2_, i + 71, j + 17, 0.45f);
        }
    }

    private void n_1700_B(@Nullable F_3620_e mapDataIn, int x, int y, float scale) {
        if (mapDataIn != null) {
            c_4037_x.v_4276_D();
            c_4037_x.R_4764_Y((float)x, (float)y, 1.0f);
            c_4037_x.J_1907_R(scale, scale, 1.0f);
            o_3091_w.n_1700_B irendertypebuffer$impl = o_3091_w.n_1700_B(l_3747_P.n_1700_B().R_4764_Y());
            this.minecraft.s_956_w.t_148_a().n_1700_B(new g_221_o(), irendertypebuffer$impl, mapDataIn, true, 0xF000F0);
            irendertypebuffer$impl.J_1907_R();
            c_4037_x.d_2461_k();
        }
    }
}


