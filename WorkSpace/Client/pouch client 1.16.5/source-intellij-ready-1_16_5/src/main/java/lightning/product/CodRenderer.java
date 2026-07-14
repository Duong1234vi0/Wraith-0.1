/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.M_1336_P;
import lightning.product.CodModel;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.Cod;
import lightning.product.r_1334_c;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;

public class CodRenderer
extends r_1334_c<Cod, CodModel<Cod>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/fish/cod.png");

    public CodRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new CodModel(), 0.3f);
    }

    @Override
    public g_2336_b n_1700_B(Cod entity) {
        return n_1700_B;
    }

    @Override
    protected void n_1700_B(Cod entityLiving, g_221_o matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.n_1700_B(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3f * u_530_F.n_1700_B(0.6f * ageInTicks);
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f));
        if (!entityLiving.RowButton()) {
            matrixStackIn.n_1700_B((double)0.1f, (double)0.1f, (double)-0.1f);
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(90.0f));
        }
    }
}


