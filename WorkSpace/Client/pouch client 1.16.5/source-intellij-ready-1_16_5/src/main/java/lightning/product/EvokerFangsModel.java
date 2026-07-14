/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import lightning.product.ListModel;
import lightning.product.N_4263_v;
import lightning.product.e_4189_z;
import lightning.product.u_530_F;

public class EvokerFangsModel<T extends N_4263_v>
extends ListModel<T> {
    private final e_4189_z n_1700_B = new e_4189_z(this, 0, 0);
    private final e_4189_z J_1907_R;
    private final e_4189_z R_4764_Y;

    public EvokerFangsModel() {
        this.n_1700_B.n_1700_B(-5.0f, 22.0f, -5.0f);
        this.n_1700_B.n_1700_B(0.0f, 0.0f, 0.0f, 10.0f, 12.0f, 10.0f);
        this.J_1907_R = new e_4189_z(this, 40, 0);
        this.J_1907_R.n_1700_B(1.5f, 22.0f, -4.0f);
        this.J_1907_R.n_1700_B(0.0f, 0.0f, 0.0f, 4.0f, 14.0f, 8.0f);
        this.R_4764_Y = new e_4189_z(this, 40, 0);
        this.R_4764_Y.n_1700_B(-1.5f, 22.0f, 4.0f);
        this.R_4764_Y.n_1700_B(0.0f, 0.0f, 0.0f, 4.0f, 14.0f, 8.0f);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = limbSwing * 2.0f;
        if (f > 1.0f) {
            f = 1.0f;
        }
        f = 1.0f - f * f * f;
        this.J_1907_R.w_1484_f = (float)Math.PI - f * 0.35f * (float)Math.PI;
        this.R_4764_Y.w_1484_f = (float)Math.PI + f * 0.35f * (float)Math.PI;
        this.R_4764_Y.v_4262_N = (float)Math.PI;
        float f1 = (limbSwing + u_530_F.n_1700_B(limbSwing * 2.7f)) * 0.6f * 12.0f;
        this.R_4764_Y.G_564_y = this.J_1907_R.G_564_y = 24.0f - f1;
        this.n_1700_B.G_564_y = this.J_1907_R.G_564_y;
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.n_1700_B, (Object)this.J_1907_R, (Object)this.R_4764_Y);
    }
}


