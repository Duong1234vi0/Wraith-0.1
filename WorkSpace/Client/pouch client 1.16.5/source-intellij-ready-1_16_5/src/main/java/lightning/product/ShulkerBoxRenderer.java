/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.K_4074_S;
import lightning.product.M_1336_P;
import lightning.product.T_2910_P;
import lightning.product.Y_3462_U;
import lightning.product.a_433_S;
import lightning.product.b_257_Y;
import lightning.product.b_4440_Q;
import lightning.product.e_933_M;
import lightning.product.f_2689_h;
import lightning.product.g_221_o;
import lightning.product.l_1802_R;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.ShulkerModel;

public class ShulkerBoxRenderer
extends l_1802_R<a_433_S> {
    private final ShulkerModel<?> n_1700_B;

    public ShulkerBoxRenderer(ShulkerModel<?> p_i226013_1_, f_2689_h p_i226013_2_) {
        super(p_i226013_2_);
        this.n_1700_B = p_i226013_1_;
    }

    @Override
    public void n_1700_B(a_433_S tileEntityIn, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, int combinedOverlayIn) {
        e_933_M dyecolor;
        K_4074_S blockstate;
        b_257_Y direction = b_257_Y.J_1907_R;
        if (tileEntityIn.t_4043_B() && (blockstate = tileEntityIn.c_3005_b().getBlockState(tileEntityIn.x_607_J())).J_1907_R() instanceof Y_3462_U) {
            direction = blockstate.R_4764_Y(Y_3462_U.P_4830_p);
        }
        T_2910_P rendermaterial = (dyecolor = tileEntityIn.s_956_w()) == null ? b_4440_Q.v_4262_N : b_4440_Q.w_1484_f.get(dyecolor.J_1907_R());
        matrixStackIn.n_1700_B();
        matrixStackIn.n_1700_B(0.5, 0.5, 0.5);
        float f = 0.9995f;
        matrixStackIn.n_1700_B(0.9995f, 0.9995f, 0.9995f);
        matrixStackIn.n_1700_B(direction.J_1907_R());
        matrixStackIn.n_1700_B(1.0f, -1.0f, -1.0f);
        matrixStackIn.n_1700_B(0.0, -1.0, 0.0);
        D_4792_h ivertexbuilder = rendermaterial.n_1700_B(bufferIn, o_2576_A::G_564_y);
        this.n_1700_B.J_1907_R().n_1700_B(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
        matrixStackIn.n_1700_B(0.0, (double)(-tileEntityIn.n_1700_B(partialTicks) * 0.5f), 0.0);
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(270.0f * tileEntityIn.n_1700_B(partialTicks)));
        this.n_1700_B.R_4764_Y().n_1700_B(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
        matrixStackIn.J_1907_R();
    }
}


