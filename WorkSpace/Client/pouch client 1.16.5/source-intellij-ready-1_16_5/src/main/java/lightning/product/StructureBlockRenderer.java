/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.K_4074_S;
import lightning.product.M_3212_T;
import lightning.product.W_2163_m;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.f_2689_h;
import lightning.product.g_221_o;
import lightning.product.j_2644_e;
import lightning.product.l_1802_R;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.q_4099_E;
import lightning.product.z_883_p;

public class StructureBlockRenderer
extends l_1802_R<j_2644_e> {
    public StructureBlockRenderer(f_2689_h p_i226017_1_) {
        super(p_i226017_1_);
    }

    @Override
    public void n_1700_B(j_2644_e tileEntityIn, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (MinecraftClient.A_4115_X().Y_259_p.ModuleManager() || MinecraftClient.A_4115_X().Y_259_p.d_2461_k()) {
            c_1514_x blockpos = tileEntityIn.s_956_w();
            c_1514_x blockpos1 = tileEntityIn.u_2550_I();
            if (blockpos1.getX() >= 1 && blockpos1.getY() >= 1 && blockpos1.getZ() >= 1 && (tileEntityIn.Q_4569_t() == M_3212_T.n_1700_B || tileEntityIn.Q_4569_t() == M_3212_T.J_1907_R)) {
                double d7;
                double d6;
                double d4;
                double d2;
                double d0 = blockpos.getX();
                double d1 = blockpos.getZ();
                double d5 = blockpos.getY();
                double d8 = d5 + (double)blockpos1.getY();
                double d3 = switch (tileEntityIn.M_588_G()) {
                    case q_4099_E.J_1907_R -> {
                        d2 = blockpos1.getX();
                        yield -blockpos1.getZ();
                    }
                    case q_4099_E.R_4764_Y -> {
                        d2 = -blockpos1.getX();
                        yield blockpos1.getZ();
                    }
                    default -> {
                        d2 = blockpos1.getX();
                        yield blockpos1.getZ();
                    }
                };
                double d9 = switch (tileEntityIn.P_4830_p()) {
                    case W_2163_m.J_1907_R -> {
                        d4 = d3 < 0.0 ? d0 : d0 + 1.0;
                        d6 = d2 < 0.0 ? d1 + 1.0 : d1;
                        d7 = d4 - d3;
                        yield d6 + d2;
                    }
                    case W_2163_m.R_4764_Y -> {
                        d4 = d2 < 0.0 ? d0 : d0 + 1.0;
                        d6 = d3 < 0.0 ? d1 : d1 + 1.0;
                        d7 = d4 - d2;
                        yield d6 - d3;
                    }
                    case W_2163_m.G_564_y -> {
                        d4 = d3 < 0.0 ? d0 + 1.0 : d0;
                        d6 = d2 < 0.0 ? d1 : d1 + 1.0;
                        d7 = d4 + d3;
                        yield d6 - d2;
                    }
                    default -> {
                        d4 = d2 < 0.0 ? d0 + 1.0 : d0;
                        d6 = d3 < 0.0 ? d1 + 1.0 : d1;
                        d7 = d4 + d2;
                        yield d6 + d3;
                    }
                };
                float f = 1.0f;
                float f1 = 0.9f;
                float f2 = 0.5f;
                D_4792_h ivertexbuilder = bufferIn.getBuffer(o_2576_A.C_2741_M());
                if (tileEntityIn.Q_4569_t() == M_3212_T.n_1700_B || tileEntityIn.H_2857_Y()) {
                    z_883_p.n_1700_B(matrixStackIn, ivertexbuilder, d4, d5, d6, d7, d8, d9, 0.9f, 0.9f, 0.9f, 1.0f, 0.5f, 0.5f, 0.5f);
                }
                if (tileEntityIn.Q_4569_t() == M_3212_T.n_1700_B && tileEntityIn.Z_875_P()) {
                    this.n_1700_B(tileEntityIn, ivertexbuilder, blockpos, true, matrixStackIn);
                    this.n_1700_B(tileEntityIn, ivertexbuilder, blockpos, false, matrixStackIn);
                }
            }
        }
    }

    private void n_1700_B(j_2644_e p_228880_1_, D_4792_h p_228880_2_, c_1514_x p_228880_3_, boolean p_228880_4_, g_221_o p_228880_5_) {
        b_4507_u iblockreader = p_228880_1_.c_3005_b();
        c_1514_x blockpos = p_228880_1_.x_607_J();
        c_1514_x blockpos1 = blockpos.add(p_228880_3_);
        for (c_1514_x blockpos2 : c_1514_x.getAllInBoxMutable(blockpos1, blockpos1.add(p_228880_1_.u_2550_I()).add(-1, -1, -1))) {
            K_4074_S blockstate = iblockreader.getBlockState(blockpos2);
            boolean flag = blockstate.v_4262_N();
            boolean flag1 = blockstate.n_1700_B(a_3742_W.PearlLogger);
            if (!flag && !flag1) continue;
            float f = flag ? 0.05f : 0.0f;
            double d0 = (float)(blockpos2.getX() - blockpos.getX()) + 0.45f - f;
            double d1 = (float)(blockpos2.getY() - blockpos.getY()) + 0.45f - f;
            double d2 = (float)(blockpos2.getZ() - blockpos.getZ()) + 0.45f - f;
            double d3 = (float)(blockpos2.getX() - blockpos.getX()) + 0.55f + f;
            double d4 = (float)(blockpos2.getY() - blockpos.getY()) + 0.55f + f;
            double d5 = (float)(blockpos2.getZ() - blockpos.getZ()) + 0.55f + f;
            if (p_228880_4_) {
                z_883_p.n_1700_B(p_228880_5_, p_228880_2_, d0, d1, d2, d3, d4, d5, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f);
                continue;
            }
            if (flag) {
                z_883_p.n_1700_B(p_228880_5_, p_228880_2_, d0, d1, d2, d3, d4, d5, 0.5f, 0.5f, 1.0f, 1.0f, 0.5f, 0.5f, 1.0f);
                continue;
            }
            z_883_p.n_1700_B(p_228880_5_, p_228880_2_, d0, d1, d2, d3, d4, d5, 1.0f, 0.25f, 0.25f, 1.0f, 1.0f, 0.25f, 0.25f);
        }
    }

    @Override
    public boolean n_1700_B(j_2644_e te) {
        return true;
    }
}



