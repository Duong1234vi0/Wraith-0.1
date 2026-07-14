/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import lightning.product.N_4263_v;
import lightning.product.AgeableListModel;
import lightning.product.e_4189_z;
import lightning.product.u_530_F;

public class ChickenModel<T extends N_4263_v>
extends AgeableListModel<T> {
    private final e_4189_z n_1700_B;
    private final e_4189_z J_1907_R;
    private final e_4189_z R_4764_Y;
    private final e_4189_z G_564_y;
    private final e_4189_z P_1922_E;
    private final e_4189_z u_1723_Y;
    private final e_4189_z v_4262_N;
    private final e_4189_z w_1484_f;

    public ChickenModel() {
        int i = 16;
        this.n_1700_B = new e_4189_z(this, 0, 0);
        this.n_1700_B.n_1700_B(-2.0f, -6.0f, -2.0f, 4.0f, 6.0f, 3.0f, 0.0f);
        this.n_1700_B.n_1700_B(0.0f, 15.0f, -4.0f);
        this.v_4262_N = new e_4189_z(this, 14, 0);
        this.v_4262_N.n_1700_B(-2.0f, -4.0f, -4.0f, 4.0f, 2.0f, 2.0f, 0.0f);
        this.v_4262_N.n_1700_B(0.0f, 15.0f, -4.0f);
        this.w_1484_f = new e_4189_z(this, 14, 4);
        this.w_1484_f.n_1700_B(-1.0f, -2.0f, -3.0f, 2.0f, 2.0f, 2.0f, 0.0f);
        this.w_1484_f.n_1700_B(0.0f, 15.0f, -4.0f);
        this.J_1907_R = new e_4189_z(this, 0, 9);
        this.J_1907_R.n_1700_B(-3.0f, -4.0f, -3.0f, 6.0f, 8.0f, 6.0f, 0.0f);
        this.J_1907_R.n_1700_B(0.0f, 16.0f, 0.0f);
        this.R_4764_Y = new e_4189_z(this, 26, 0);
        this.R_4764_Y.n_1700_B(-1.0f, 0.0f, -3.0f, 3.0f, 5.0f, 3.0f);
        this.R_4764_Y.n_1700_B(-2.0f, 19.0f, 1.0f);
        this.G_564_y = new e_4189_z(this, 26, 0);
        this.G_564_y.n_1700_B(-1.0f, 0.0f, -3.0f, 3.0f, 5.0f, 3.0f);
        this.G_564_y.n_1700_B(1.0f, 19.0f, 1.0f);
        this.P_1922_E = new e_4189_z(this, 24, 13);
        this.P_1922_E.n_1700_B(0.0f, 0.0f, -3.0f, 1.0f, 4.0f, 6.0f);
        this.P_1922_E.n_1700_B(-4.0f, 13.0f, 0.0f);
        this.u_1723_Y = new e_4189_z(this, 24, 13);
        this.u_1723_Y.n_1700_B(-1.0f, 0.0f, -3.0f, 1.0f, 4.0f, 6.0f);
        this.u_1723_Y.n_1700_B(4.0f, 13.0f, 0.0f);
    }

    @Override
    protected Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.n_1700_B, (Object)this.v_4262_N, (Object)this.w_1484_f);
    }

    @Override
    protected Iterable<e_4189_z> J_1907_R() {
        return ImmutableList.of((Object)this.J_1907_R, (Object)this.R_4764_Y, (Object)this.G_564_y, (Object)this.P_1922_E, (Object)this.u_1723_Y);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.n_1700_B.u_1723_Y = headPitch * ((float)Math.PI / 180);
        this.n_1700_B.v_4262_N = netHeadYaw * ((float)Math.PI / 180);
        this.v_4262_N.u_1723_Y = this.n_1700_B.u_1723_Y;
        this.v_4262_N.v_4262_N = this.n_1700_B.v_4262_N;
        this.w_1484_f.u_1723_Y = this.n_1700_B.u_1723_Y;
        this.w_1484_f.v_4262_N = this.n_1700_B.v_4262_N;
        this.J_1907_R.u_1723_Y = 1.5707964f;
        this.R_4764_Y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
        this.G_564_y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount;
        this.P_1922_E.w_1484_f = ageInTicks;
        this.u_1723_Y.w_1484_f = -ageInTicks;
    }
}


