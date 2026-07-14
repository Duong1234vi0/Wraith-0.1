/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_2364_U;
import lightning.product.M_1336_P;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.IronGolemModel;
import lightning.product.q_2700_F;
import lightning.product.r_1334_c;
import lightning.product.w_2040_b;
import lightning.product.IronGolemCrackinessLayer;

public class IronGolemRenderer
extends r_1334_c<D_2364_U, IronGolemModel<D_2364_U>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/iron_golem/iron_golem.png");

    public IronGolemRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new IronGolemModel(), 0.7f);
        this.n_1700_B(new IronGolemCrackinessLayer(this));
        this.n_1700_B(new q_2700_F(this));
    }

    @Override
    public g_2336_b n_1700_B(D_2364_U entity) {
        return n_1700_B;
    }

    @Override
    protected void n_1700_B(D_2364_U entityLiving, g_221_o matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.n_1700_B(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        if (!((double)entityLiving.G_424_k < 0.01)) {
            float f = 13.0f;
            float f1 = entityLiving.RealmsSettingsScreen - entityLiving.G_424_k * (1.0f - partialTicks) + 6.0f;
            float f2 = (Math.abs(f1 % 13.0f - 6.5f) - 3.25f) / 3.25f;
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(6.5f * f2));
        }
    }
}


