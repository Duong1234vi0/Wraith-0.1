/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.L_3848_p;
import lightning.product.T_2910_P;
import lightning.product.b_257_Y;
import lightning.product.e_4189_z;
import lightning.product.f_2689_h;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.h_113_g;
import lightning.product.l_1802_R;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.u_530_F;

public class BellRenderer
extends l_1802_R<h_113_g> {
    public static final T_2910_P n_1700_B = new T_2910_P(L_3848_p.n_1700_B, new g_2336_b("entity/bell/bell_body"));
    private final e_4189_z J_1907_R = new e_4189_z(32, 32, 0, 0);

    public BellRenderer(f_2689_h p_i226005_1_) {
        super(p_i226005_1_);
        this.J_1907_R.n_1700_B(-3.0f, -6.0f, -3.0f, 6.0f, 7.0f, 6.0f);
        this.J_1907_R.n_1700_B(8.0f, 12.0f, 8.0f);
        e_4189_z modelrenderer = new e_4189_z(32, 32, 0, 13);
        modelrenderer.n_1700_B(4.0f, 4.0f, 4.0f, 8.0f, 2.0f, 8.0f);
        modelrenderer.n_1700_B(-8.0f, -12.0f, -8.0f);
        this.J_1907_R.J_1907_R(modelrenderer);
    }

    @Override
    public void n_1700_B(h_113_g tileEntityIn, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, int combinedOverlayIn) {
        float f = (float)tileEntityIn.n_1700_B + partialTicks;
        float f1 = 0.0f;
        float f2 = 0.0f;
        if (tileEntityIn.J_1907_R) {
            float f3 = u_530_F.n_1700_B(f / (float)Math.PI) / (4.0f + f / 3.0f);
            if (tileEntityIn.R_4764_Y == b_257_Y.R_4764_Y) {
                f1 = -f3;
            } else if (tileEntityIn.R_4764_Y == b_257_Y.G_564_y) {
                f1 = f3;
            } else if (tileEntityIn.R_4764_Y == b_257_Y.u_1723_Y) {
                f2 = -f3;
            } else if (tileEntityIn.R_4764_Y == b_257_Y.P_1922_E) {
                f2 = f3;
            }
        }
        this.J_1907_R.u_1723_Y = f1;
        this.J_1907_R.w_1484_f = f2;
        D_4792_h ivertexbuilder = n_1700_B.n_1700_B(bufferIn, o_2576_A::J_1907_R);
        this.J_1907_R.n_1700_B(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
    }
}


