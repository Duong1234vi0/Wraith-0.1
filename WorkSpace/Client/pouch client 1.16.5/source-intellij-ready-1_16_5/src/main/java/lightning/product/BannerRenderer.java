/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 */
package lightning.product;

import com.mojang.datafixers.util.Pair;
import java.util.List;
import lightning.product.D_4792_h;
import lightning.product.J_2538_C;
import lightning.product.K_4074_S;
import lightning.product.M_1336_P;
import lightning.product.WallBannerBlock;
import lightning.product.T_2910_P;
import lightning.product.b_1320_N;
import lightning.product.b_4440_Q;
import lightning.product.c_1514_x;
import lightning.product.e_4189_z;
import lightning.product.e_933_M;
import lightning.product.f_2689_h;
import lightning.product.g_221_o;
import lightning.product.g_2561_p;
import lightning.product.l_1802_R;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.r_4889_F;
import lightning.product.u_530_F;

public class BannerRenderer
extends l_1802_R<r_4889_F> {
    private final e_4189_z n_1700_B = BannerRenderer.n_1700_B();
    private final e_4189_z J_1907_R = new e_4189_z(64, 64, 44, 0);
    private final e_4189_z R_4764_Y;

    public BannerRenderer(f_2689_h p_i226002_1_) {
        super(p_i226002_1_);
        this.J_1907_R.n_1700_B(-1.0f, -30.0f, -1.0f, 2.0f, 42.0f, 2.0f, 0.0f);
        this.R_4764_Y = new e_4189_z(64, 64, 0, 42);
        this.R_4764_Y.n_1700_B(-10.0f, -32.0f, -1.0f, 20.0f, 2.0f, 2.0f, 0.0f);
    }

    public static e_4189_z n_1700_B() {
        e_4189_z modelrenderer = new e_4189_z(64, 64, 0, 0);
        modelrenderer.n_1700_B(-10.0f, 0.0f, -2.0f, 20.0f, 40.0f, 1.0f, 0.0f);
        return modelrenderer;
    }

    @Override
    public void n_1700_B(r_4889_F tileEntityIn, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, int combinedOverlayIn) {
        List<Pair<J_2538_C, e_933_M>> list = tileEntityIn.P_1922_E();
        if (list != null) {
            long i;
            float f = 0.6666667f;
            boolean flag = tileEntityIn.c_3005_b() == null;
            matrixStackIn.n_1700_B();
            if (flag) {
                i = 0L;
                matrixStackIn.n_1700_B(0.5, 0.5, 0.5);
                this.J_1907_R.s_956_w = true;
            } else {
                i = tileEntityIn.c_3005_b().X_933_l();
                K_4074_S blockstate = tileEntityIn.e_4240_b();
                if (blockstate.J_1907_R() instanceof b_1320_N) {
                    matrixStackIn.n_1700_B(0.5, 0.5, 0.5);
                    float f1 = (float)(-blockstate.R_4764_Y(b_1320_N.P_4830_p).intValue() * 360) / 16.0f;
                    matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f1));
                    this.J_1907_R.s_956_w = true;
                } else {
                    matrixStackIn.n_1700_B(0.5, -0.1666666716337204, 0.5);
                    float f3 = -blockstate.R_4764_Y(WallBannerBlock.P_4830_p).Q_4569_t();
                    matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f3));
                    matrixStackIn.n_1700_B(0.0, -0.3125, -0.4375);
                    this.J_1907_R.s_956_w = false;
                }
            }
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(0.6666667f, -0.6666667f, -0.6666667f);
            D_4792_h ivertexbuilder = g_2561_p.u_1723_Y.n_1700_B(bufferIn, o_2576_A::J_1907_R);
            this.J_1907_R.n_1700_B(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
            this.R_4764_Y.n_1700_B(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
            c_1514_x blockpos = tileEntityIn.x_607_J();
            float f2 = ((float)Math.floorMod((long)(blockpos.getX() * 7 + blockpos.getY() * 9 + blockpos.getZ() * 13) + i, 100L) + partialTicks) / 100.0f;
            this.n_1700_B.u_1723_Y = (-0.0125f + 0.01f * u_530_F.J_1907_R((float)Math.PI * 2 * f2)) * (float)Math.PI;
            this.n_1700_B.G_564_y = -32.0f;
            BannerRenderer.n_1700_B(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, this.n_1700_B, g_2561_p.u_1723_Y, true, list);
            matrixStackIn.J_1907_R();
            matrixStackIn.J_1907_R();
        }
    }

    public static void n_1700_B(g_221_o p_230180_0_, o_3091_w p_230180_1_, int p_230180_2_, int p_230180_3_, e_4189_z p_230180_4_, T_2910_P p_230180_5_, boolean p_230180_6_, List<Pair<J_2538_C, e_933_M>> p_230180_7_) {
        BannerRenderer.n_1700_B(p_230180_0_, p_230180_1_, p_230180_2_, p_230180_3_, p_230180_4_, p_230180_5_, p_230180_6_, p_230180_7_, false);
    }

    public static void n_1700_B(g_221_o p_241717_0_, o_3091_w p_241717_1_, int p_241717_2_, int p_241717_3_, e_4189_z p_241717_4_, T_2910_P p_241717_5_, boolean p_241717_6_, List<Pair<J_2538_C, e_933_M>> p_241717_7_, boolean p_241717_8_) {
        p_241717_4_.n_1700_B(p_241717_0_, p_241717_5_.n_1700_B(p_241717_1_, o_2576_A::J_1907_R, p_241717_8_), p_241717_2_, p_241717_3_);
        for (int i = 0; i < 17 && i < p_241717_7_.size(); ++i) {
            Pair<J_2538_C, e_933_M> pair = p_241717_7_.get(i);
            float[] afloat = ((e_933_M)pair.getSecond()).G_564_y();
            T_2910_P rendermaterial = new T_2910_P(p_241717_6_ ? b_4440_Q.R_4764_Y : b_4440_Q.G_564_y, ((J_2538_C)((Object)pair.getFirst())).n_1700_B(p_241717_6_));
            p_241717_4_.n_1700_B(p_241717_0_, rendermaterial.n_1700_B(p_241717_1_, o_2576_A::u_2550_I), p_241717_2_, p_241717_3_, afloat[0], afloat[1], afloat[2], 1.0f);
        }
    }
}


