/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.G_4536_S;
import lightning.product.QuadrupedModel;
import lightning.product.e_4189_z;

public class v_1296_A<T extends G_4536_S>
extends QuadrupedModel<T> {
    private float v_4262_N;

    public v_1296_A() {
        super(12, 0.0f, false, 8.0f, 4.0f, 2.0f, 2.0f, 24);
        this.n_1700_B = new e_4189_z(this, 0, 0);
        this.n_1700_B.n_1700_B(-3.0f, -4.0f, -6.0f, 6.0f, 6.0f, 8.0f, 0.0f);
        this.n_1700_B.n_1700_B(0.0f, 6.0f, -8.0f);
        this.J_1907_R = new e_4189_z(this, 28, 8);
        this.J_1907_R.n_1700_B(-4.0f, -10.0f, -7.0f, 8.0f, 16.0f, 6.0f, 0.0f);
        this.J_1907_R.n_1700_B(0.0f, 5.0f, 2.0f);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, partialTick);
        this.n_1700_B.G_564_y = 6.0f + ((G_4536_S)entityIn).c_3005_b(partialTick) * 9.0f;
        this.v_4262_N = ((G_4536_S)entityIn).H_2857_Y(partialTick);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.n_1700_B.u_1723_Y = this.v_4262_N;
    }
}


