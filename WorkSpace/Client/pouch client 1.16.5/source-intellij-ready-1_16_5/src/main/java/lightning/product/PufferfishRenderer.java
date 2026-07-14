/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.PufferfishSmallModel;
import lightning.product.EntityModel;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_3091_w;
import lightning.product.r_1334_c;
import lightning.product.u_530_F;
import lightning.product.PufferfishMidModel;
import lightning.product.w_2040_b;
import lightning.product.PufferfishBigModel;
import lightning.product.x_742_i;

public class PufferfishRenderer
extends r_1334_c<x_742_i, EntityModel<x_742_i>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/fish/pufferfish.png");
    private int t_1786_h = 3;
    private final PufferfishSmallModel<x_742_i> multiplayerClientSuggestionProvider = new PufferfishSmallModel();
    private final PufferfishMidModel<x_742_i> w_1457_N = new PufferfishMidModel();
    private final PufferfishBigModel<x_742_i> Y_601_j = new PufferfishBigModel();

    public PufferfishRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new PufferfishBigModel(), 0.2f);
    }

    @Override
    public g_2336_b n_1700_B(x_742_i entity) {
        return n_1700_B;
    }

    @Override
    public void n_1700_B(x_742_i entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        int i = entityIn.y_2447_C();
        if (i != this.t_1786_h) {
            this.v_4262_N = i == 0 ? this.multiplayerClientSuggestionProvider : (i == 1 ? this.w_1457_N : this.Y_601_j);
        }
        this.t_1786_h = i;
        this.R_4764_Y = 0.1f + 0.1f * (float)i;
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    protected void n_1700_B(x_742_i entityLiving, g_221_o matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        matrixStackIn.n_1700_B(0.0, (double)(u_530_F.J_1907_R(ageInTicks * 0.05f) * 0.08f), 0.0);
        super.n_1700_B(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
    }
}


