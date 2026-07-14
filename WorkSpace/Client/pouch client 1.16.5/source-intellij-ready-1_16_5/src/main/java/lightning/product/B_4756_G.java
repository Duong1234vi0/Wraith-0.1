/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import lightning.product.C_3240_x;
import lightning.product.D_1098_v;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_2336_b;
import lightning.product.l_3747_P;

public class B_4756_G {
    private final g_2336_b[] n_1700_B = new g_2336_b[6];

    public B_4756_G(g_2336_b texture) {
        for (int i = 0; i < 6; ++i) {
            this.n_1700_B[i] = new g_2336_b(texture.R_4764_Y(), texture.J_1907_R() + "_" + i + ".png");
        }
    }

    public void n_1700_B(MinecraftClient mc, float pitch, float yaw, float alpha) {
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        c_4037_x.u_2550_I(5889);
        c_4037_x.v_4276_D();
        c_4037_x.z_1737_N();
        c_4037_x.n_1700_B(D_1098_v.n_1700_B(85.0, (float)mc.RealmsServerPing().u_2550_I() / (float)mc.RealmsServerPing().M_588_G(), 0.05f, 10.0f));
        c_4037_x.u_2550_I(5888);
        c_4037_x.v_4276_D();
        c_4037_x.z_1737_N();
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        c_4037_x.R_4764_Y(180.0f, 1.0f, 0.0f, 0.0f);
        c_4037_x.Y_601_j();
        c_4037_x.u_2550_I();
        c_4037_x.q_2307_F();
        c_4037_x.J_1907_R(false);
        c_4037_x.s_2632_s();
        int i = 2;
        for (int j = 0; j < 4; ++j) {
            c_4037_x.v_4276_D();
            float f = ((float)(j % 2) / 2.0f - 0.5f) / 256.0f;
            float f1 = ((float)(j / 2) / 2.0f - 0.5f) / 256.0f;
            float f2 = 0.0f;
            c_4037_x.R_4764_Y(f, f1, 0.0f);
            c_4037_x.R_4764_Y(pitch, 1.0f, 0.0f, 0.0f);
            c_4037_x.R_4764_Y(yaw, 0.0f, 1.0f, 0.0f);
            for (int k = 0; k < 6; ++k) {
                mc.G_624_v().n_1700_B(this.n_1700_B[k]);
                bufferbuilder.n_1700_B(7, E_688_b.k_2293_S);
                int l = Math.round(255.0f * alpha) / (j + 1);
                if (k == 0) {
                    bufferbuilder.pos(-1.0, -1.0, 1.0).tex(0.0f, 0.0f).color(255, 255, 255, l).endVertex();
                    bufferbuilder.pos(-1.0, 1.0, 1.0).tex(0.0f, 1.0f).color(255, 255, 255, l).endVertex();
                    bufferbuilder.pos(1.0, 1.0, 1.0).tex(1.0f, 1.0f).color(255, 255, 255, l).endVertex();
                    bufferbuilder.pos(1.0, -1.0, 1.0).tex(1.0f, 0.0f).color(255, 255, 255, l).endVertex();
                }
                if (k == 1) {
                    bufferbuilder.pos(1.0, -1.0, 1.0).tex(0.0f, 0.0f).color(255, 255, 255, l).endVertex();
                    bufferbuilder.pos(1.0, 1.0, 1.0).tex(0.0f, 1.0f).color(255, 255, 255, l).endVertex();
                    bufferbuilder.pos(1.0, 1.0, -1.0).tex(1.0f, 1.0f).color(255, 255, 255, l).endVertex();
                    bufferbuilder.pos(1.0, -1.0, -1.0).tex(1.0f, 0.0f).color(255, 255, 255, l).endVertex();
                }
                if (k == 2) {
                    bufferbuilder.pos(1.0, -1.0, -1.0).tex(0.0f, 0.0f).color(255, 255, 255, l).endVertex();
                    bufferbuilder.pos(1.0, 1.0, -1.0).tex(0.0f, 1.0f).color(255, 255, 255, l).endVertex();
                    bufferbuilder.pos(-1.0, 1.0, -1.0).tex(1.0f, 1.0f).color(255, 255, 255, l).endVertex();
                    bufferbuilder.pos(-1.0, -1.0, -1.0).tex(1.0f, 0.0f).color(255, 255, 255, l).endVertex();
                }
                if (k == 3) {
                    bufferbuilder.pos(-1.0, -1.0, -1.0).tex(0.0f, 0.0f).color(255, 255, 255, l).endVertex();
                    bufferbuilder.pos(-1.0, 1.0, -1.0).tex(0.0f, 1.0f).color(255, 255, 255, l).endVertex();
                    bufferbuilder.pos(-1.0, 1.0, 1.0).tex(1.0f, 1.0f).color(255, 255, 255, l).endVertex();
                    bufferbuilder.pos(-1.0, -1.0, 1.0).tex(1.0f, 0.0f).color(255, 255, 255, l).endVertex();
                }
                if (k == 4) {
                    bufferbuilder.pos(-1.0, -1.0, -1.0).tex(0.0f, 0.0f).color(255, 255, 255, l).endVertex();
                    bufferbuilder.pos(-1.0, -1.0, 1.0).tex(0.0f, 1.0f).color(255, 255, 255, l).endVertex();
                    bufferbuilder.pos(1.0, -1.0, 1.0).tex(1.0f, 1.0f).color(255, 255, 255, l).endVertex();
                    bufferbuilder.pos(1.0, -1.0, -1.0).tex(1.0f, 0.0f).color(255, 255, 255, l).endVertex();
                }
                if (k == 5) {
                    bufferbuilder.pos(-1.0, 1.0, 1.0).tex(0.0f, 0.0f).color(255, 255, 255, l).endVertex();
                    bufferbuilder.pos(-1.0, 1.0, -1.0).tex(0.0f, 1.0f).color(255, 255, 255, l).endVertex();
                    bufferbuilder.pos(1.0, 1.0, -1.0).tex(1.0f, 1.0f).color(255, 255, 255, l).endVertex();
                    bufferbuilder.pos(1.0, 1.0, 1.0).tex(1.0f, 0.0f).color(255, 255, 255, l).endVertex();
                }
                tessellator.J_1907_R();
            }
            c_4037_x.d_2461_k();
            c_4037_x.n_1700_B(true, true, true, false);
        }
        c_4037_x.n_1700_B(true, true, true, true);
        c_4037_x.u_2550_I(5889);
        c_4037_x.d_2461_k();
        c_4037_x.u_2550_I(5888);
        c_4037_x.d_2461_k();
        c_4037_x.J_1907_R(true);
        c_4037_x.k_2293_S();
        c_4037_x.multiplayerClientSuggestionProvider();
    }

    public CompletableFuture<Void> n_1700_B(C_3240_x texMngr, Executor backgroundExecutor) {
        CompletableFuture[] completablefuture = new CompletableFuture[6];
        for (int i = 0; i < completablefuture.length; ++i) {
            completablefuture[i] = texMngr.n_1700_B(this.n_1700_B[i], backgroundExecutor);
        }
        return CompletableFuture.allOf(completablefuture);
    }
}



