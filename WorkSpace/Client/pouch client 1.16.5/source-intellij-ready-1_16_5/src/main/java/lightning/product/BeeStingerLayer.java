/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_1098_v;
import lightning.product.D_4792_h;
import lightning.product.PlayerModel;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.W_1980_j;
import lightning.product.Z_3224_L;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_1290_k;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.o_4479_Q;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;

public class BeeStingerLayer<T extends r_4811_B, M extends PlayerModel<T>>
extends W_1980_j<T, M> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/bee/bee_stinger.png");

    public BeeStingerLayer(o_4479_Q<T, M> p_i226036_1_) {
        super(p_i226036_1_);
    }

    @Override
    protected int n_1700_B(T p_225631_1_) {
        return ((r_4811_B)p_225631_1_).U_4087_m();
    }

    @Override
    protected void n_1700_B(g_221_o p_225632_1_, o_3091_w p_225632_2_, int p_225632_3_, N_4263_v p_225632_4_, float p_225632_5_, float p_225632_6_, float p_225632_7_, float p_225632_8_) {
        float f = u_530_F.R_4764_Y(p_225632_5_ * p_225632_5_ + p_225632_7_ * p_225632_7_);
        float f1 = (float)(Math.atan2(p_225632_5_, p_225632_7_) * 57.2957763671875);
        float f2 = (float)(Math.atan2(p_225632_6_, f) * 57.2957763671875);
        p_225632_1_.n_1700_B(0.0, 0.0, 0.0);
        p_225632_1_.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f1 - 90.0f));
        p_225632_1_.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(f2));
        float f3 = 0.0f;
        float f4 = 0.125f;
        float f5 = 0.0f;
        float f6 = 0.0625f;
        float f7 = 0.03125f;
        p_225632_1_.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(45.0f));
        p_225632_1_.n_1700_B(0.03125f, 0.03125f, 0.03125f);
        p_225632_1_.n_1700_B(2.5, 0.0, 0.0);
        D_4792_h ivertexbuilder = p_225632_2_.getBuffer(o_2576_A.G_564_y(n_1700_B));
        for (int i = 0; i < 4; ++i) {
            p_225632_1_.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(90.0f));
            g_221_o.n_1700_B matrixstack$entry = p_225632_1_.R_4764_Y();
            D_1098_v matrix4f = matrixstack$entry.n_1700_B();
            o_1290_k matrix3f = matrixstack$entry.J_1907_R();
            BeeStingerLayer.n_1700_B(ivertexbuilder, matrix4f, matrix3f, -4.5f, -1, 0.0f, 0.0f, p_225632_3_);
            BeeStingerLayer.n_1700_B(ivertexbuilder, matrix4f, matrix3f, 4.5f, -1, 0.125f, 0.0f, p_225632_3_);
            BeeStingerLayer.n_1700_B(ivertexbuilder, matrix4f, matrix3f, 4.5f, 1, 0.125f, 0.0625f, p_225632_3_);
            BeeStingerLayer.n_1700_B(ivertexbuilder, matrix4f, matrix3f, -4.5f, 1, 0.0f, 0.0625f, p_225632_3_);
        }
    }

    private static void n_1700_B(D_4792_h p_229132_0_, D_1098_v p_229132_1_, o_1290_k p_229132_2_, float p_229132_3_, int p_229132_4_, float p_229132_5_, float p_229132_6_, int p_229132_7_) {
        p_229132_0_.n_1700_B(p_229132_1_, p_229132_3_, (float)p_229132_4_, 0.0f).color(255, 255, 255, 255).tex(p_229132_5_, p_229132_6_).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(p_229132_7_).n_1700_B(p_229132_2_, 0.0f, 1.0f, 0.0f).endVertex();
    }
}


