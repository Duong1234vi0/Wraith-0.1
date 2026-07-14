/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.C_4998_y;
import lightning.product.ItemTransforms;
import lightning.product.G_2722_I;
import lightning.product.M_1336_P;
import lightning.product.NonNullList;
import lightning.product.Z_1993_T;
import lightning.product.b_257_Y;
import lightning.product.MinecraftClient;
import lightning.product.f_2689_h;
import lightning.product.g_221_o;
import lightning.product.l_1802_R;
import lightning.product.o_3091_w;

public class CampfireRenderer
extends l_1802_R<G_2722_I> {
    public CampfireRenderer(f_2689_h p_i226007_1_) {
        super(p_i226007_1_);
    }

    @Override
    public void n_1700_B(G_2722_I tileEntityIn, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, int combinedOverlayIn) {
        b_257_Y direction = tileEntityIn.e_4240_b().R_4764_Y(C_4998_y.t_1786_h);
        NonNullList<Z_1993_T> nonnulllist = tileEntityIn.n_1700_B();
        for (int i = 0; i < nonnulllist.size(); ++i) {
            Z_1993_T itemstack = nonnulllist.get(i);
            if (itemstack == Z_1993_T.J_1907_R) continue;
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(0.5, 0.44921875, 0.5);
            b_257_Y direction1 = b_257_Y.J_1907_R((i + direction.G_564_y()) % 4);
            float f = -direction1.Q_4569_t();
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f));
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(90.0f));
            matrixStackIn.n_1700_B(-0.3125, -0.3125, 0.0);
            matrixStackIn.n_1700_B(0.375f, 0.375f, 0.375f);
            MinecraftClient.A_4115_X().r_715_M().n_1700_B(itemstack, ItemTransforms.J_1907_R.t_148_a, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
            matrixStackIn.J_1907_R();
        }
    }
}



