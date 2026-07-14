/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_4390_i;
import lightning.product.M_1336_P;
import lightning.product.TropicalFishPatternLayer;
import lightning.product.EntityModel;
import lightning.product.TropicalFishModelB;
import lightning.product.TropicalFishModelA;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_3091_w;
import lightning.product.r_1334_c;
import lightning.product.r_2604_d;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;

public class TropicalFishRenderer
extends r_1334_c<A_4390_i, EntityModel<A_4390_i>> {
    private final TropicalFishModelA<A_4390_i> n_1700_B = new TropicalFishModelA(0.0f);
    private final TropicalFishModelB<A_4390_i> t_1786_h = new TropicalFishModelB(0.0f);

    public TropicalFishRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new TropicalFishModelA(0.0f), 0.15f);
        this.n_1700_B(new TropicalFishPatternLayer(this));
    }

    @Override
    public g_2336_b n_1700_B(A_4390_i entity) {
        return entity.A_1306_N();
    }

    @Override
    public void n_1700_B(A_4390_i entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        r_2604_d abstracttropicalfishmodel;
        this.v_4262_N = abstracttropicalfishmodel = entityIn.U_3758_B() == 0 ? this.n_1700_B : this.t_1786_h;
        float[] afloat = entityIn.c_2086_l();
        abstracttropicalfishmodel.n_1700_B(afloat[0], afloat[1], afloat[2]);
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        abstracttropicalfishmodel.n_1700_B(1.0f, 1.0f, 1.0f);
    }

    @Override
    protected void n_1700_B(A_4390_i entityLiving, g_221_o matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.n_1700_B(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3f * u_530_F.n_1700_B(0.6f * ageInTicks);
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f));
        if (!entityLiving.RowButton()) {
            matrixStackIn.n_1700_B((double)0.2f, (double)0.1f, 0.0);
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(90.0f));
        }
    }
}


