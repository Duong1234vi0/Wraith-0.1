/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.SquidModel;
import lightning.product.Squid;
import lightning.product.M_1336_P;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.r_1334_c;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;

public class SquidRenderer
extends r_1334_c<Squid, SquidModel<Squid>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/squid.png");

    public SquidRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new SquidModel(), 0.7f);
    }

    @Override
    public g_2336_b n_1700_B(Squid entity) {
        return n_1700_B;
    }

    @Override
    protected void n_1700_B(Squid entityLiving, g_221_o matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        float f = u_530_F.v_4262_N(partialTicks, entityLiving.J_1907_R, entityLiving.n_1700_B);
        float f1 = u_530_F.v_4262_N(partialTicks, entityLiving.h_1847_R, entityLiving.R_4764_Y);
        matrixStackIn.n_1700_B(0.0, 0.5, 0.0);
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(180.0f - rotationYaw));
        matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(f));
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f1));
        matrixStackIn.n_1700_B(0.0, (double)-1.2f, 0.0);
    }

    @Override
    protected float n_1700_B(Squid livingBase, float partialTicks) {
        return u_530_F.v_4262_N(partialTicks, livingBase.multiplayerClientSuggestionProvider, livingBase.t_1786_h);
    }
}


