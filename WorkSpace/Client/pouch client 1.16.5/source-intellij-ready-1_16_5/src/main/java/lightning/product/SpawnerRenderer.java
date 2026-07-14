/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.Q_584_o;
import lightning.product.MinecraftClient;
import lightning.product.f_2689_h;
import lightning.product.g_221_o;
import lightning.product.SpawnerBlockEntity;
import lightning.product.l_1802_R;
import lightning.product.o_3091_w;
import lightning.product.u_530_F;

public class SpawnerRenderer
extends l_1802_R<SpawnerBlockEntity> {
    public SpawnerRenderer(f_2689_h p_i226016_1_) {
        super(p_i226016_1_);
    }

    @Override
    public void n_1700_B(SpawnerBlockEntity tileEntityIn, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.n_1700_B();
        matrixStackIn.n_1700_B(0.5, 0.0, 0.5);
        Q_584_o abstractspawner = tileEntityIn.v_4262_N();
        N_4263_v entity = abstractspawner.G_564_y();
        if (entity != null) {
            float f = 0.53125f;
            float f1 = Math.max(entity.C_415_h(), entity.v_165_F());
            if ((double)f1 > 1.0) {
                f /= f1;
            }
            matrixStackIn.n_1700_B(0.0, (double)0.4f, 0.0);
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y((float)u_530_F.G_564_y((double)partialTicks, abstractspawner.u_1723_Y(), abstractspawner.P_1922_E()) * 10.0f));
            matrixStackIn.n_1700_B(0.0, (double)-0.2f, 0.0);
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-30.0f));
            matrixStackIn.n_1700_B(f, f, f);
            MinecraftClient.A_4115_X().O_508_d().n_1700_B(entity, 0.0, 0.0, 0.0, 0.0f, partialTicks, matrixStackIn, bufferIn, combinedLightIn);
        }
        matrixStackIn.J_1907_R();
    }
}



