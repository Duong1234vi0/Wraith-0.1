/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.D_4792_h;
import lightning.product.K_4074_S;
import lightning.product.N_295_T;
import lightning.product.N_81_X;
import lightning.product.W_571_B;
import lightning.product.BlockAndTintGetter;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.d_1620_j;
import lightning.product.e_3977_C;
import lightning.product.f_2689_h;
import lightning.product.g_221_o;
import lightning.product.h_4152_b;
import lightning.product.l_1802_R;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.y_1539_W;

public class PistonHeadRenderer
extends l_1802_R<N_295_T> {
    private final e_3977_C n_1700_B = MinecraftClient.A_4115_X().z_1333_t();

    public PistonHeadRenderer(f_2689_h p_i226012_1_) {
        super(p_i226012_1_);
    }

    @Override
    public void n_1700_B(N_295_T tileEntityIn, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, int combinedOverlayIn) {
        b_4507_u world = tileEntityIn.c_3005_b();
        if (world != null) {
            c_1514_x blockpos = tileEntityIn.x_607_J().offset(tileEntityIn.u_2550_I().u_1723_Y());
            K_4074_S blockstate = tileEntityIn.M_588_G();
            if (!blockstate.v_4262_N()) {
                W_571_B.n_1700_B();
                matrixStackIn.n_1700_B();
                matrixStackIn.n_1700_B((double)tileEntityIn.J_1907_R(partialTicks), (double)tileEntityIn.R_4764_Y(partialTicks), (double)tileEntityIn.G_564_y(partialTicks));
                if (blockstate.n_1700_B(a_3742_W.S_980_j) && tileEntityIn.n_1700_B(partialTicks) <= 4.0f) {
                    blockstate = (K_4074_S)blockstate.n_1700_B(N_81_X.Q_4569_t, tileEntityIn.n_1700_B(partialTicks) <= 0.5f);
                    this.n_1700_B(blockpos, blockstate, matrixStackIn, bufferIn, world, false, combinedOverlayIn);
                } else if (tileEntityIn.s_956_w() && !tileEntityIn.v_4262_N()) {
                    y_1539_W pistontype = blockstate.n_1700_B(a_3742_W.RealmsDefaultUncaughtExceptionHandler) ? y_1539_W.J_1907_R : y_1539_W.n_1700_B;
                    K_4074_S blockstate1 = (K_4074_S)((K_4074_S)a_3742_W.S_980_j.multiplayerClientSuggestionProvider().n_1700_B(N_81_X.h_1847_R, pistontype)).n_1700_B(N_81_X.P_4830_p, blockstate.R_4764_Y(h_4152_b.P_4830_p));
                    blockstate1 = (K_4074_S)blockstate1.n_1700_B(N_81_X.Q_4569_t, tileEntityIn.n_1700_B(partialTicks) >= 0.5f);
                    this.n_1700_B(blockpos, blockstate1, matrixStackIn, bufferIn, world, false, combinedOverlayIn);
                    c_1514_x blockpos1 = blockpos.offset(tileEntityIn.u_2550_I());
                    matrixStackIn.J_1907_R();
                    matrixStackIn.n_1700_B();
                    blockstate = (K_4074_S)blockstate.n_1700_B(h_4152_b.h_1847_R, true);
                    this.n_1700_B(blockpos1, blockstate, matrixStackIn, bufferIn, world, true, combinedOverlayIn);
                } else {
                    this.n_1700_B(blockpos, blockstate, matrixStackIn, bufferIn, world, false, combinedOverlayIn);
                }
                matrixStackIn.J_1907_R();
                W_571_B.J_1907_R();
            }
        }
    }

    private void n_1700_B(c_1514_x p_228876_1_, K_4074_S p_228876_2_, g_221_o p_228876_3_, o_3091_w p_228876_4_, b_4507_u p_228876_5_, boolean p_228876_6_, int p_228876_7_) {
        o_2576_A rendertype = d_1620_j.J_1907_R(p_228876_2_);
        D_4792_h ivertexbuilder = p_228876_4_.getBuffer(rendertype);
        this.n_1700_B.R_4764_Y().n_1700_B((BlockAndTintGetter)p_228876_5_, this.n_1700_B.n_1700_B(p_228876_2_), p_228876_2_, p_228876_1_, p_228876_3_, ivertexbuilder, p_228876_6_, new Random(), p_228876_2_.n_1700_B(p_228876_1_), p_228876_7_);
    }
}



