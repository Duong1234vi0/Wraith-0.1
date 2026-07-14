/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_4355_q;
import lightning.product.M_1336_P;
import lightning.product.DrownedModel;
import lightning.product.S_3848_S;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.DrownedOuterLayer;
import lightning.product.u_530_F;
import lightning.product.AbstractZombieRenderer;
import lightning.product.w_2040_b;

public class DrownedRenderer
extends AbstractZombieRenderer<S_3848_S, DrownedModel<S_3848_S>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/zombie/drowned.png");

    public DrownedRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new DrownedModel(0.0f, 0.0f, 64, 64), new DrownedModel(0.5f, true), new DrownedModel(1.0f, true));
        this.n_1700_B(new DrownedOuterLayer<S_3848_S>(this));
    }

    @Override
    public g_2336_b n_1700_B(F_4355_q entity) {
        return n_1700_B;
    }

    @Override
    protected void n_1700_B(S_3848_S entityLiving, g_221_o matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.n_1700_B(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = entityLiving.u_1723_Y(partialTicks);
        if (f > 0.0f) {
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(u_530_F.v_4262_N(f, entityLiving.f_4016_n, -10.0f - entityLiving.f_4016_n)));
        }
    }
}


