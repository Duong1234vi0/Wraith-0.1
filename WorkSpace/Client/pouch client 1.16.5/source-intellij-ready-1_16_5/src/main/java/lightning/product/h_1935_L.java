/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.G_901_u;
import lightning.product.I_4817_s;
import lightning.product.K_550_M;
import lightning.product.M_1336_P;
import lightning.product.CatModel;
import lightning.product.a_3913_L;
import lightning.product.c_1514_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.r_1334_c;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;

public class h_1935_L
extends r_1334_c<K_550_M, CatModel<K_550_M>> {
    public h_1935_L(w_2040_b renderManagerIn) {
        super(renderManagerIn, new CatModel(0.0f), 0.4f);
        this.n_1700_B(new G_901_u(this));
    }

    @Override
    public g_2336_b n_1700_B(K_550_M entity) {
        return entity.y_4642_Y();
    }

    @Override
    protected void n_1700_B(K_550_M entitylivingbaseIn, g_221_o matrixStackIn, float partialTickTime) {
        super.n_1700_B(entitylivingbaseIn, matrixStackIn, partialTickTime);
        matrixStackIn.n_1700_B(0.8f, 0.8f, 0.8f);
    }

    @Override
    protected void n_1700_B(K_550_M entityLiving, g_221_o matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.n_1700_B(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = entityLiving.c_3005_b(partialTicks);
        if (f > 0.0f) {
            matrixStackIn.n_1700_B((double)(0.4f * f), (double)(0.15f * f), (double)(0.1f * f));
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(u_530_F.w_1484_f(f, 0.0f, 90.0f)));
            c_1514_x blockpos = entityLiving.b_2312_j();
            for (a_3913_L playerentity : entityLiving.O_508_d.n_1700_B(a_3913_L.class, new I_4817_s(blockpos).grow(2.0, 2.0, 2.0))) {
                if (!playerentity.z_2372_L()) continue;
                matrixStackIn.n_1700_B((double)(0.15f * f), 0.0, 0.0);
                break;
            }
        }
    }
}


