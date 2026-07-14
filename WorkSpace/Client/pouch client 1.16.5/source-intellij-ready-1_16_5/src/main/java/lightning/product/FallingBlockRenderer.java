/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.K_4074_S;
import lightning.product.L_3848_p;
import lightning.product.O_2369_F;
import lightning.product.W_4464_I;
import lightning.product.BlockAndTintGetter;
import lightning.product.Z_2049_e;
import lightning.product.Z_3224_L;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.d_1620_j;
import lightning.product.e_3977_C;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_3091_w;
import lightning.product.w_2040_b;

public class FallingBlockRenderer
extends Z_2049_e<W_4464_I> {
    public FallingBlockRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn);
        this.R_4764_Y = 0.5f;
    }

    @Override
    public void n_1700_B(W_4464_I entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        b_4507_u world;
        K_4074_S blockstate = entityIn.v_4262_N();
        if (blockstate.w_1484_f() == O_2369_F.R_4764_Y && blockstate != (world = entityIn.u_1723_Y()).getBlockState(entityIn.b_2312_j()) && blockstate.w_1484_f() != O_2369_F.n_1700_B) {
            matrixStackIn.n_1700_B();
            c_1514_x blockpos = new c_1514_x(entityIn.O_3598_v(), entityIn.i_601_W().maxY, entityIn.l_2647_k());
            matrixStackIn.n_1700_B(-0.5, 0.0, -0.5);
            e_3977_C blockrendererdispatcher = MinecraftClient.A_4115_X().z_1333_t();
            blockrendererdispatcher.R_4764_Y().n_1700_B((BlockAndTintGetter)world, blockrendererdispatcher.n_1700_B(blockstate), blockstate, blockpos, matrixStackIn, bufferIn.getBuffer(d_1620_j.J_1907_R(blockstate)), false, new Random(), blockstate.n_1700_B(entityIn.P_1922_E()), Z_3224_L.n_1700_B);
            matrixStackIn.J_1907_R();
            super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        }
    }

    @Override
    public g_2336_b n_1700_B(W_4464_I entity) {
        return L_3848_p.n_1700_B;
    }
}



