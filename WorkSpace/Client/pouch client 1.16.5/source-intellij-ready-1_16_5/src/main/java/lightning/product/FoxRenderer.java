/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.M_1336_P;
import lightning.product.FoxHeldItemLayer;
import lightning.product.g_1253_u;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.r_1334_c;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;
import lightning.product.FoxModel;

public class FoxRenderer
extends r_1334_c<g_1253_u, FoxModel<g_1253_u>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/fox/fox.png");
    private static final g_2336_b t_1786_h = new g_2336_b("textures/entity/fox/fox_sleep.png");
    private static final g_2336_b multiplayerClientSuggestionProvider = new g_2336_b("textures/entity/fox/snow_fox.png");
    private static final g_2336_b w_1457_N = new g_2336_b("textures/entity/fox/snow_fox_sleep.png");

    public FoxRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new FoxModel(), 0.4f);
        this.n_1700_B(new FoxHeldItemLayer(this));
    }

    @Override
    protected void n_1700_B(g_1253_u entityLiving, g_221_o matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.n_1700_B(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        if (entityLiving.J_3635_s() || entityLiving.y_2447_C()) {
            float f = -u_530_F.v_4262_N(partialTicks, entityLiving.UploadStatus, entityLiving.f_4016_n);
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(f));
        }
    }

    @Override
    public g_2336_b n_1700_B(g_1253_u entity) {
        if (entity.h_1640_b() == g_1253_u.Y_259_p.n_1700_B) {
            return entity.z_2372_L() ? t_1786_h : n_1700_B;
        }
        return entity.z_2372_L() ? w_1457_N : multiplayerClientSuggestionProvider;
    }
}


