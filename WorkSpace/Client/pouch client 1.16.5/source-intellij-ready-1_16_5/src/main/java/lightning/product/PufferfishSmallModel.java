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

public class PufferfishSmallModel<T extends N_4263_v>
extends ListModel<T> {
    private final e_4189_z n_1700_B;
    private final e_4189_z J_1907_R;
    private final e_4189_z R_4764_Y;
    private final e_4189_z G_564_y;
    private final e_4189_z P_1922_E;
    private final e_4189_z u_1723_Y;

    public PufferfishSmallModel() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        int i = 23;
        this.n_1700_B = new e_4189_z(this, 0, 27);
        this.n_1700_B.n_1700_B(-1.5f, -2.0f, -1.5f, 3.0f, 2.0f, 3.0f);
        this.n_1700_B.n_1700_B(0.0f, 23.0f, 0.0f);
        this.J_1907_R = new e_4189_z(this, 24, 6);
        this.J_1907_R.n_1700_B(-1.5f, 0.0f, -1.5f, 1.0f, 1.0f, 1.0f);
        this.J_1907_R.n_1700_B(0.0f, 20.0f, 0.0f);
        this.R_4764_Y = new e_4189_z(this, 28, 6);
        this.R_4764_Y.n_1700_B(0.5f, 0.0f, -1.5f, 1.0f, 1.0f, 1.0f);
        this.R_4764_Y.n_1700_B(0.0f, 20.0f, 0.0f);
        this.u_1723_Y = new e_4189_z(this, -3, 0);
        this.u_1723_Y.n_1700_B(-1.5f, 0.0f, 0.0f, 3.0f, 0.0f, 3.0f);
        this.u_1723_Y.n_1700_B(0.0f, 22.0f, 1.5f);
        this.G_564_y = new e_4189_z(this, 25, 0);
        this.G_564_y.n_1700_B(-1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 2.0f);
        this.G_564_y.n_1700_B(-1.5f, 22.0f, -1.5f);
        this.P_1922_E = new e_4189_z(this, 25, 0);
        this.P_1922_E.n_1700_B(0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 2.0f);
        this.P_1922_E.n_1700_B(1.5f, 22.0f, -1.5f);
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.n_1700_B, (Object)this.J_1907_R, (Object)this.R_4764_Y, (Object)this.u_1723_Y, (Object)this.G_564_y, (Object)this.P_1922_E);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.G_564_y.w_1484_f = -0.2f + 0.4f * u_530_F.n_1700_B(ageInTicks * 0.2f);
        this.P_1922_E.w_1484_f = 0.2f - 0.4f * u_530_F.n_1700_B(ageInTicks * 0.2f);
    }
}


