/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.D_4899_Z;
import lightning.product.K_4074_S;
import lightning.product.M_1336_P;
import lightning.product.f_2689_h;
import lightning.product.g_221_o;
import lightning.product.BookModel;
import lightning.product.h_355_y;
import lightning.product.l_1802_R;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.EnchantTableRenderer;

public class LecternRenderer
extends l_1802_R<D_4899_Z> {
    private final BookModel n_1700_B = new BookModel();

    public LecternRenderer(f_2689_h p_i226011_1_) {
        super(p_i226011_1_);
    }

    @Override
    public void n_1700_B(D_4899_Z tileEntityIn, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, int combinedOverlayIn) {
        K_4074_S blockstate = tileEntityIn.e_4240_b();
        if (blockstate.R_4764_Y(h_355_y.Q_4569_t).booleanValue()) {
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(0.5, 1.0625, 0.5);
            float f = blockstate.R_4764_Y(h_355_y.P_4830_p).v_4262_N().Q_4569_t();
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-f));
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(67.5f));
            matrixStackIn.n_1700_B(0.0, -0.125, 0.0);
            this.n_1700_B.n_1700_B(0.0f, 0.1f, 0.9f, 1.2f);
            D_4792_h ivertexbuilder = EnchantTableRenderer.n_1700_B.n_1700_B(bufferIn, o_2576_A::J_1907_R);
            this.n_1700_B.n_1700_B(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn, 1.0f, 1.0f, 1.0f, 1.0f);
            matrixStackIn.J_1907_R();
        }
    }
}


