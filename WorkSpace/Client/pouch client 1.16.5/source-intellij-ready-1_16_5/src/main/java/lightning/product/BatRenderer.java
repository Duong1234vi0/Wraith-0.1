/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BatModel;
import lightning.product.Bat;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.r_1334_c;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;

public class BatRenderer
extends r_1334_c<Bat, BatModel> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/bat.png");

    public BatRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new BatModel(), 0.25f);
    }

    @Override
    public g_2336_b n_1700_B(Bat entity) {
        return n_1700_B;
    }

    @Override
    protected void n_1700_B(Bat entitylivingbaseIn, g_221_o matrixStackIn, float partialTickTime) {
        matrixStackIn.n_1700_B(0.35f, 0.35f, 0.35f);
    }

    @Override
    protected void n_1700_B(Bat entityLiving, g_221_o matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        if (entityLiving.w_1484_f()) {
            matrixStackIn.n_1700_B(0.0, (double)-0.1f, 0.0);
        } else {
            matrixStackIn.n_1700_B(0.0, (double)(u_530_F.J_1907_R(ageInTicks * 0.3f) * 0.1f), 0.0);
        }
        super.n_1700_B(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
    }
}


