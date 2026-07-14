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

public class SnowGolemModel<T extends N_4263_v>
extends ListModel<T> {
    private final e_4189_z n_1700_B;
    private final e_4189_z J_1907_R;
    private final e_4189_z R_4764_Y;
    private final e_4189_z G_564_y;
    private final e_4189_z P_1922_E;

    public SnowGolemModel() {
        float f = 4.0f;
        float f1 = 0.0f;
        this.R_4764_Y = new e_4189_z(this, 0, 0).J_1907_R(64, 64);
        this.R_4764_Y.n_1700_B(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, -0.5f);
        this.R_4764_Y.n_1700_B(0.0f, 4.0f, 0.0f);
        this.G_564_y = new e_4189_z(this, 32, 0).J_1907_R(64, 64);
        this.G_564_y.n_1700_B(-1.0f, 0.0f, -1.0f, 12.0f, 2.0f, 2.0f, -0.5f);
        this.G_564_y.n_1700_B(0.0f, 6.0f, 0.0f);
        this.P_1922_E = new e_4189_z(this, 32, 0).J_1907_R(64, 64);
        this.P_1922_E.n_1700_B(-1.0f, 0.0f, -1.0f, 12.0f, 2.0f, 2.0f, -0.5f);
        this.P_1922_E.n_1700_B(0.0f, 6.0f, 0.0f);
        this.n_1700_B = new e_4189_z(this, 0, 16).J_1907_R(64, 64);
        this.n_1700_B.n_1700_B(-5.0f, -10.0f, -5.0f, 10.0f, 10.0f, 10.0f, -0.5f);
        this.n_1700_B.n_1700_B(0.0f, 13.0f, 0.0f);
        this.J_1907_R = new e_4189_z(this, 0, 36).J_1907_R(64, 64);
        this.J_1907_R.n_1700_B(-6.0f, -12.0f, -6.0f, 12.0f, 12.0f, 12.0f, -0.5f);
        this.J_1907_R.n_1700_B(0.0f, 24.0f, 0.0f);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.R_4764_Y.v_4262_N = netHeadYaw * ((float)Math.PI / 180);
        this.R_4764_Y.u_1723_Y = headPitch * ((float)Math.PI / 180);
        this.n_1700_B.v_4262_N = netHeadYaw * ((float)Math.PI / 180) * 0.25f;
        float f = u_530_F.n_1700_B(this.n_1700_B.v_4262_N);
        float f1 = u_530_F.J_1907_R(this.n_1700_B.v_4262_N);
        this.G_564_y.w_1484_f = 1.0f;
        this.P_1922_E.w_1484_f = -1.0f;
        this.G_564_y.v_4262_N = 0.0f + this.n_1700_B.v_4262_N;
        this.P_1922_E.v_4262_N = (float)Math.PI + this.n_1700_B.v_4262_N;
        this.G_564_y.R_4764_Y = f1 * 5.0f;
        this.G_564_y.P_1922_E = -f * 5.0f;
        this.P_1922_E.R_4764_Y = -f1 * 5.0f;
        this.P_1922_E.P_1922_E = f * 5.0f;
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.n_1700_B, (Object)this.J_1907_R, (Object)this.R_4764_Y, (Object)this.G_564_y, (Object)this.P_1922_E);
    }

    public e_4189_z J_1907_R() {
        return this.R_4764_Y;
    }
}


