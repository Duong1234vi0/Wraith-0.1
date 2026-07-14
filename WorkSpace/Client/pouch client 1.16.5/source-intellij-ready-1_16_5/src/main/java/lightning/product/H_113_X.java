/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.N_4263_v;
import lightning.product.AgeableListModel;
import lightning.product.g_221_o;

public abstract class H_113_X<E extends N_4263_v>
extends AgeableListModel<E> {
    private float n_1700_B = 1.0f;
    private float J_1907_R = 1.0f;
    private float R_4764_Y = 1.0f;

    public void n_1700_B(float p_228253_1_, float p_228253_2_, float p_228253_3_) {
        this.n_1700_B = p_228253_1_;
        this.J_1907_R = p_228253_2_;
        this.R_4764_Y = p_228253_3_;
    }

    @Override
    public void render(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        super.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, this.n_1700_B * red, this.J_1907_R * green, this.R_4764_Y * blue, alpha);
    }
}


