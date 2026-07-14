/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.SalmonModel;
import lightning.product.M_1336_P;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.Salmon;
import lightning.product.r_1334_c;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;

public class SalmonRenderer
extends r_1334_c<Salmon, SalmonModel<Salmon>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/fish/salmon.png");

    public SalmonRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new SalmonModel(), 0.4f);
    }

    @Override
    public g_2336_b n_1700_B(Salmon entity) {
        return n_1700_B;
    }

    @Override
    protected void n_1700_B(Salmon entityLiving, g_221_o matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.n_1700_B(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = 1.0f;
        float f1 = 1.0f;
        if (!entityLiving.RowButton()) {
            f = 1.3f;
            f1 = 1.7f;
        }
        float f2 = f * 4.3f * u_530_F.n_1700_B(f1 * 0.6f * ageInTicks);
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f2));
        matrixStackIn.n_1700_B(0.0, 0.0, (double)-0.4f);
        if (!entityLiving.RowButton()) {
            matrixStackIn.n_1700_B((double)0.2f, (double)0.1f, 0.0);
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(90.0f));
        }
    }
}


