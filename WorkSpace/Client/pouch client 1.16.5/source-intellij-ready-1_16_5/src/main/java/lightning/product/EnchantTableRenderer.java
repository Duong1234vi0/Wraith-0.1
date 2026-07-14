/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.L_3848_p;
import lightning.product.M_1336_P;
import lightning.product.T_2910_P;
import lightning.product.c_1869_W;
import lightning.product.f_2689_h;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.BookModel;
import lightning.product.l_1802_R;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.u_530_F;

public class EnchantTableRenderer
extends l_1802_R<c_1869_W> {
    public static final T_2910_P n_1700_B = new T_2910_P(L_3848_p.n_1700_B, new g_2336_b("entity/enchanting_table_book"));
    private final BookModel J_1907_R = new BookModel();

    public EnchantTableRenderer(f_2689_h p_i226010_1_) {
        super(p_i226010_1_);
    }

    @Override
    public void n_1700_B(c_1869_W tileEntityIn, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, int combinedOverlayIn) {
        float f1;
        matrixStackIn.n_1700_B();
        matrixStackIn.n_1700_B(0.5, 0.75, 0.5);
        float f = (float)tileEntityIn.n_1700_B + partialTicks;
        matrixStackIn.n_1700_B(0.0, (double)(0.1f + u_530_F.n_1700_B(f * 0.1f) * 0.01f), 0.0);
        for (f1 = tileEntityIn.w_1484_f - tileEntityIn.t_148_a; f1 >= (float)Math.PI; f1 -= (float)Math.PI * 2) {
        }
        while (f1 < (float)(-Math.PI)) {
            f1 += (float)Math.PI * 2;
        }
        float f2 = tileEntityIn.t_148_a + f1 * partialTicks;
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.J_1907_R(-f2));
        matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(80.0f));
        float f3 = u_530_F.v_4262_N(partialTicks, tileEntityIn.R_4764_Y, tileEntityIn.J_1907_R);
        float f4 = u_530_F.w_1484_f(f3 + 0.25f) * 1.6f - 0.3f;
        float f5 = u_530_F.w_1484_f(f3 + 0.75f) * 1.6f - 0.3f;
        float f6 = u_530_F.v_4262_N(partialTicks, tileEntityIn.v_4262_N, tileEntityIn.u_1723_Y);
        this.J_1907_R.n_1700_B(f, u_530_F.n_1700_B(f4, 0.0f, 1.0f), u_530_F.n_1700_B(f5, 0.0f, 1.0f), f6);
        D_4792_h ivertexbuilder = n_1700_B.n_1700_B(bufferIn, o_2576_A::J_1907_R);
        this.J_1907_R.n_1700_B(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStackIn.J_1907_R();
    }
}


