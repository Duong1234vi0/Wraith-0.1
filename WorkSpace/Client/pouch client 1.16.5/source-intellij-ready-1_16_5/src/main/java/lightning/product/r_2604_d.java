/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.ListModel;
import lightning.product.N_4263_v;
import lightning.product.g_221_o;

public abstract class r_2604_d<E extends N_4263_v>
extends ListModel<E> {
    private float n_1700_B = 1.0f;
    private float J_1907_R = 1.0f;
    private float R_4764_Y = 1.0f;

    public void n_1700_B(float p_228257_1_, float p_228257_2_, float p_228257_3_) {
        this.n_1700_B = p_228257_1_;
        this.J_1907_R = p_228257_2_;
        this.R_4764_Y = p_228257_3_;
    }

    @Override
    public void render(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        super.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, this.n_1700_B * red, this.J_1907_R * green, this.R_4764_Y * blue, alpha);
    }
}


