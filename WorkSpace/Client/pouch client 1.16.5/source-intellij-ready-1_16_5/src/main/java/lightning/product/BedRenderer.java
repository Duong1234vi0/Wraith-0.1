/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2IntFunction
 */
package lightning.product;

import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import lightning.product.D_4792_h;
import lightning.product.J_2868_p;
import lightning.product.K_4074_S;
import lightning.product.M_1336_P;
import lightning.product.N_4989_q;
import lightning.product.DoubleBlockCombiner;
import lightning.product.T_2910_P;
import lightning.product.b_257_Y;
import lightning.product.b_4440_Q;
import lightning.product.b_4507_u;
import lightning.product.e_4189_z;
import lightning.product.f_2689_h;
import lightning.product.g_221_o;
import lightning.product.h_2829_o;
import lightning.product.BedBlockEntity;
import lightning.product.l_1802_R;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.BlockEntityType;
import lightning.product.v_3445_Z;

public class BedRenderer
extends l_1802_R<BedBlockEntity> {
    private final e_4189_z n_1700_B;
    private final e_4189_z J_1907_R;
    private final e_4189_z[] R_4764_Y = new e_4189_z[4];

    public BedRenderer(f_2689_h p_i226004_1_) {
        super(p_i226004_1_);
        this.n_1700_B = new e_4189_z(64, 64, 0, 0);
        this.n_1700_B.n_1700_B(0.0f, 0.0f, 0.0f, 16.0f, 16.0f, 6.0f, 0.0f);
        this.J_1907_R = new e_4189_z(64, 64, 0, 22);
        this.J_1907_R.n_1700_B(0.0f, 0.0f, 0.0f, 16.0f, 16.0f, 6.0f, 0.0f);
        this.R_4764_Y[0] = new e_4189_z(64, 64, 50, 0);
        this.R_4764_Y[1] = new e_4189_z(64, 64, 50, 6);
        this.R_4764_Y[2] = new e_4189_z(64, 64, 50, 12);
        this.R_4764_Y[3] = new e_4189_z(64, 64, 50, 18);
        this.R_4764_Y[0].n_1700_B(0.0f, 6.0f, -16.0f, 3.0f, 3.0f, 3.0f);
        this.R_4764_Y[1].n_1700_B(0.0f, 6.0f, 0.0f, 3.0f, 3.0f, 3.0f);
        this.R_4764_Y[2].n_1700_B(-16.0f, 6.0f, -16.0f, 3.0f, 3.0f, 3.0f);
        this.R_4764_Y[3].n_1700_B(-16.0f, 6.0f, 0.0f, 3.0f, 3.0f, 3.0f);
        this.R_4764_Y[0].u_1723_Y = 1.5707964f;
        this.R_4764_Y[1].u_1723_Y = 1.5707964f;
        this.R_4764_Y[2].u_1723_Y = 1.5707964f;
        this.R_4764_Y[3].u_1723_Y = 1.5707964f;
        this.R_4764_Y[0].w_1484_f = 0.0f;
        this.R_4764_Y[1].w_1484_f = 1.5707964f;
        this.R_4764_Y[2].w_1484_f = 4.712389f;
        this.R_4764_Y[3].w_1484_f = (float)Math.PI;
    }

    @Override
    public void n_1700_B(BedBlockEntity tileEntityIn, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, int combinedOverlayIn) {
        T_2910_P rendermaterial = b_4440_Q.s_956_w[tileEntityIn.n_1700_B().J_1907_R()];
        b_4507_u world = tileEntityIn.c_3005_b();
        if (world != null) {
            K_4074_S blockstate = tileEntityIn.e_4240_b();
            DoubleBlockCombiner.J_1907_R<BedBlockEntity> icallbackwrapper = DoubleBlockCombiner.n_1700_B(BlockEntityType.k_2293_S, J_2868_p::t_148_a, J_2868_p::w_1484_f, v_3445_Z.h_1847_R, blockstate, world, tileEntityIn.x_607_J(), (p_228846_0_, p_228846_1_) -> false);
            int i = ((Int2IntFunction)icallbackwrapper.apply(new N_4989_q())).get(combinedLightIn);
            this.n_1700_B(matrixStackIn, bufferIn, blockstate.R_4764_Y(J_2868_p.P_4830_p) == h_2829_o.n_1700_B, blockstate.R_4764_Y(J_2868_p.w_612_n), rendermaterial, i, combinedOverlayIn, false);
        } else {
            this.n_1700_B(matrixStackIn, bufferIn, true, b_257_Y.G_564_y, rendermaterial, combinedLightIn, combinedOverlayIn, false);
            this.n_1700_B(matrixStackIn, bufferIn, false, b_257_Y.G_564_y, rendermaterial, combinedLightIn, combinedOverlayIn, true);
        }
    }

    private void n_1700_B(g_221_o p_228847_1_, o_3091_w p_228847_2_, boolean p_228847_3_, b_257_Y p_228847_4_, T_2910_P p_228847_5_, int p_228847_6_, int p_228847_7_, boolean p_228847_8_) {
        this.n_1700_B.s_956_w = p_228847_3_;
        this.J_1907_R.s_956_w = !p_228847_3_;
        this.R_4764_Y[0].s_956_w = !p_228847_3_;
        this.R_4764_Y[1].s_956_w = p_228847_3_;
        this.R_4764_Y[2].s_956_w = !p_228847_3_;
        this.R_4764_Y[3].s_956_w = p_228847_3_;
        p_228847_1_.n_1700_B();
        p_228847_1_.n_1700_B(0.0, 0.5625, p_228847_8_ ? -1.0 : 0.0);
        p_228847_1_.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(90.0f));
        p_228847_1_.n_1700_B(0.5, 0.5, 0.5);
        p_228847_1_.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(180.0f + p_228847_4_.Q_4569_t()));
        p_228847_1_.n_1700_B(-0.5, -0.5, -0.5);
        D_4792_h ivertexbuilder = p_228847_5_.n_1700_B(p_228847_2_, o_2576_A::J_1907_R);
        this.n_1700_B.n_1700_B(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
        this.J_1907_R.n_1700_B(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
        this.R_4764_Y[0].n_1700_B(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
        this.R_4764_Y[1].n_1700_B(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
        this.R_4764_Y[2].n_1700_B(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
        this.R_4764_Y[3].n_1700_B(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
        p_228847_1_.J_1907_R();
    }
}


