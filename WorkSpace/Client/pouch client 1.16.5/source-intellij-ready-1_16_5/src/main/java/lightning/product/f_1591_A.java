/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.N_4263_v;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.l_3747_P;
import lightning.product.n_4915_r;
import lightning.product.o_3091_w;
import net.optifine.Config;
import net.optifine.shaders.Shaders;

public class f_1591_A
implements n_4915_r.n_1700_B {
    private final MinecraftClient n_1700_B;

    public f_1591_A(MinecraftClient minecraftIn) {
        this.n_1700_B = minecraftIn;
    }

    @Override
    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, double camX, double camY, double camZ) {
        if (!Shaders.isShadowPass) {
            if (Config.isShaders()) {
                Shaders.beginLeash();
            }
            c_4037_x.multiplayerClientSuggestionProvider();
            c_4037_x.w_1484_f(7425);
            c_4037_x.M_588_G();
            c_4037_x.l_1233_K();
            N_4263_v entity = this.n_1700_B.s_956_w.M_588_G().v_4262_N();
            l_3747_P tessellator = l_3747_P.n_1700_B();
            D_3318_r bufferbuilder = tessellator.R_4764_Y();
            double d0 = 0.0 - camY;
            double d1 = 256.0 - camY;
            c_4037_x.e_4240_b();
            c_4037_x.Y_259_p();
            double d2 = (double)(entity.u_744_e << 4) - camX;
            double d3 = (double)(entity.r_3651_U << 4) - camZ;
            c_4037_x.G_564_y(1.0f);
            bufferbuilder.n_1700_B(3, E_688_b.Y_601_j);
            for (int i = -16; i <= 32; i += 16) {
                for (int j = -16; j <= 32; j += 16) {
                    bufferbuilder.pos(d2 + (double)i, d0, d3 + (double)j).n_1700_B(1.0f, 0.0f, 0.0f, 0.0f).endVertex();
                    bufferbuilder.pos(d2 + (double)i, d0, d3 + (double)j).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                    bufferbuilder.pos(d2 + (double)i, d1, d3 + (double)j).n_1700_B(1.0f, 0.0f, 0.0f, 0.5f).endVertex();
                    bufferbuilder.pos(d2 + (double)i, d1, d3 + (double)j).n_1700_B(1.0f, 0.0f, 0.0f, 0.0f).endVertex();
                }
            }
            for (int k = 2; k < 16; k += 2) {
                bufferbuilder.pos(d2 + (double)k, d0, d3).n_1700_B(1.0f, 1.0f, 0.0f, 0.0f).endVertex();
                bufferbuilder.pos(d2 + (double)k, d0, d3).n_1700_B(1.0f, 1.0f, 0.0f, 1.0f).endVertex();
                bufferbuilder.pos(d2 + (double)k, d1, d3).n_1700_B(1.0f, 1.0f, 0.0f, 1.0f).endVertex();
                bufferbuilder.pos(d2 + (double)k, d1, d3).n_1700_B(1.0f, 1.0f, 0.0f, 0.0f).endVertex();
                bufferbuilder.pos(d2 + (double)k, d0, d3 + 16.0).n_1700_B(1.0f, 1.0f, 0.0f, 0.0f).endVertex();
                bufferbuilder.pos(d2 + (double)k, d0, d3 + 16.0).n_1700_B(1.0f, 1.0f, 0.0f, 1.0f).endVertex();
                bufferbuilder.pos(d2 + (double)k, d1, d3 + 16.0).n_1700_B(1.0f, 1.0f, 0.0f, 1.0f).endVertex();
                bufferbuilder.pos(d2 + (double)k, d1, d3 + 16.0).n_1700_B(1.0f, 1.0f, 0.0f, 0.0f).endVertex();
            }
            for (int l = 2; l < 16; l += 2) {
                bufferbuilder.pos(d2, d0, d3 + (double)l).n_1700_B(1.0f, 1.0f, 0.0f, 0.0f).endVertex();
                bufferbuilder.pos(d2, d0, d3 + (double)l).n_1700_B(1.0f, 1.0f, 0.0f, 1.0f).endVertex();
                bufferbuilder.pos(d2, d1, d3 + (double)l).n_1700_B(1.0f, 1.0f, 0.0f, 1.0f).endVertex();
                bufferbuilder.pos(d2, d1, d3 + (double)l).n_1700_B(1.0f, 1.0f, 0.0f, 0.0f).endVertex();
                bufferbuilder.pos(d2 + 16.0, d0, d3 + (double)l).n_1700_B(1.0f, 1.0f, 0.0f, 0.0f).endVertex();
                bufferbuilder.pos(d2 + 16.0, d0, d3 + (double)l).n_1700_B(1.0f, 1.0f, 0.0f, 1.0f).endVertex();
                bufferbuilder.pos(d2 + 16.0, d1, d3 + (double)l).n_1700_B(1.0f, 1.0f, 0.0f, 1.0f).endVertex();
                bufferbuilder.pos(d2 + 16.0, d1, d3 + (double)l).n_1700_B(1.0f, 1.0f, 0.0f, 0.0f).endVertex();
            }
            for (int i1 = 0; i1 <= 256; i1 += 2) {
                double d4 = (double)i1 - camY;
                bufferbuilder.pos(d2, d4, d3).n_1700_B(1.0f, 1.0f, 0.0f, 0.0f).endVertex();
                bufferbuilder.pos(d2, d4, d3).n_1700_B(1.0f, 1.0f, 0.0f, 1.0f).endVertex();
                bufferbuilder.pos(d2, d4, d3 + 16.0).n_1700_B(1.0f, 1.0f, 0.0f, 1.0f).endVertex();
                bufferbuilder.pos(d2 + 16.0, d4, d3 + 16.0).n_1700_B(1.0f, 1.0f, 0.0f, 1.0f).endVertex();
                bufferbuilder.pos(d2 + 16.0, d4, d3).n_1700_B(1.0f, 1.0f, 0.0f, 1.0f).endVertex();
                bufferbuilder.pos(d2, d4, d3).n_1700_B(1.0f, 1.0f, 0.0f, 1.0f).endVertex();
                bufferbuilder.pos(d2, d4, d3).n_1700_B(1.0f, 1.0f, 0.0f, 0.0f).endVertex();
            }
            tessellator.J_1907_R();
            c_4037_x.G_564_y(2.0f);
            bufferbuilder.n_1700_B(3, E_688_b.Y_601_j);
            for (int j1 = 0; j1 <= 16; j1 += 16) {
                for (int l1 = 0; l1 <= 16; l1 += 16) {
                    bufferbuilder.pos(d2 + (double)j1, d0, d3 + (double)l1).n_1700_B(0.25f, 0.25f, 1.0f, 0.0f).endVertex();
                    bufferbuilder.pos(d2 + (double)j1, d0, d3 + (double)l1).n_1700_B(0.25f, 0.25f, 1.0f, 1.0f).endVertex();
                    bufferbuilder.pos(d2 + (double)j1, d1, d3 + (double)l1).n_1700_B(0.25f, 0.25f, 1.0f, 1.0f).endVertex();
                    bufferbuilder.pos(d2 + (double)j1, d1, d3 + (double)l1).n_1700_B(0.25f, 0.25f, 1.0f, 0.0f).endVertex();
                }
            }
            for (int k1 = 0; k1 <= 256; k1 += 16) {
                double d5 = (double)k1 - camY;
                bufferbuilder.pos(d2, d5, d3).n_1700_B(0.25f, 0.25f, 1.0f, 0.0f).endVertex();
                bufferbuilder.pos(d2, d5, d3).n_1700_B(0.25f, 0.25f, 1.0f, 1.0f).endVertex();
                bufferbuilder.pos(d2, d5, d3 + 16.0).n_1700_B(0.25f, 0.25f, 1.0f, 1.0f).endVertex();
                bufferbuilder.pos(d2 + 16.0, d5, d3 + 16.0).n_1700_B(0.25f, 0.25f, 1.0f, 1.0f).endVertex();
                bufferbuilder.pos(d2 + 16.0, d5, d3).n_1700_B(0.25f, 0.25f, 1.0f, 1.0f).endVertex();
                bufferbuilder.pos(d2, d5, d3).n_1700_B(0.25f, 0.25f, 1.0f, 1.0f).endVertex();
                bufferbuilder.pos(d2, d5, d3).n_1700_B(0.25f, 0.25f, 1.0f, 0.0f).endVertex();
            }
            tessellator.J_1907_R();
            c_4037_x.G_564_y(1.0f);
            c_4037_x.Y_601_j();
            c_4037_x.x_607_J();
            c_4037_x.w_1484_f(7424);
            if (Config.isShaders()) {
                Shaders.endLeash();
            }
        }
    }
}



