/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Monster;
import lightning.product.n_1658_l;
import lightning.product.AnimationUtils;

public abstract class AbstractZombieModel<T extends Monster>
extends n_1658_l<T> {
    protected AbstractZombieModel(float modelSize, float yOffsetIn, int textureWidthIn, int textureHeightIn) {
        super(modelSize, yOffsetIn, textureWidthIn, textureHeightIn);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        AnimationUtils.n_1700_B(this.P_1922_E, this.G_564_y, this.n_1700_B(entityIn), this.h_1847_R, ageInTicks);
    }

    public abstract boolean n_1700_B(T var1);
}


