/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import lightning.product.N_4263_v;
import lightning.product.X_4340_E;
import lightning.product.AgeableListModel;
import lightning.product.e_2866_D;
import lightning.product.e_4189_z;
import lightning.product.r_4811_B;

public class D_4472_w<T extends r_4811_B>
extends AgeableListModel<T> {
    private final e_4189_z n_1700_B;
    private final e_4189_z J_1907_R = new e_4189_z(this, 22, 0);

    public D_4472_w() {
        this.J_1907_R.n_1700_B(-10.0f, 0.0f, 0.0f, 10.0f, 20.0f, 2.0f, 1.0f);
        this.n_1700_B = new e_4189_z(this, 22, 0);
        this.n_1700_B.t_148_a = true;
        this.n_1700_B.n_1700_B(0.0f, 0.0f, 0.0f, 10.0f, 20.0f, 2.0f, 1.0f);
    }

    @Override
    protected Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<e_4189_z> J_1907_R() {
        return ImmutableList.of((Object)this.J_1907_R, (Object)this.n_1700_B);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = 0.2617994f;
        float f1 = -0.2617994f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        if (((r_4811_B)entityIn).k_578_l()) {
            float f4 = 1.0f;
            e_2866_D vector3d = ((N_4263_v)entityIn).I_4348_c();
            if (vector3d.R_4764_Y < 0.0) {
                e_2866_D vector3d1 = vector3d.G_564_y();
                f4 = 1.0f - (float)Math.pow(-vector3d1.R_4764_Y, 1.5);
            }
            f = f4 * 0.34906584f + (1.0f - f4) * f;
            f1 = f4 * -1.5707964f + (1.0f - f4) * f1;
        } else if (((N_4263_v)entityIn).Z_875_P()) {
            f = 0.69813174f;
            f1 = -0.7853982f;
            f2 = 3.0f;
            f3 = 0.08726646f;
        }
        this.J_1907_R.R_4764_Y = 5.0f;
        this.J_1907_R.G_564_y = f2;
        if (entityIn instanceof X_4340_E) {
            X_4340_E abstractclientplayerentity = (X_4340_E)entityIn;
            abstractclientplayerentity.G_624_v = (float)((double)abstractclientplayerentity.G_624_v + (double)(f - abstractclientplayerentity.G_624_v) * 0.1);
            abstractclientplayerentity.T_2506_i = (float)((double)abstractclientplayerentity.T_2506_i + (double)(f3 - abstractclientplayerentity.T_2506_i) * 0.1);
            abstractclientplayerentity.q_4610_l = (float)((double)abstractclientplayerentity.q_4610_l + (double)(f1 - abstractclientplayerentity.q_4610_l) * 0.1);
            this.J_1907_R.u_1723_Y = abstractclientplayerentity.G_624_v;
            this.J_1907_R.v_4262_N = abstractclientplayerentity.T_2506_i;
            this.J_1907_R.w_1484_f = abstractclientplayerentity.q_4610_l;
        } else {
            this.J_1907_R.u_1723_Y = f;
            this.J_1907_R.w_1484_f = f1;
            this.J_1907_R.v_4262_N = f3;
        }
        this.n_1700_B.R_4764_Y = -this.J_1907_R.R_4764_Y;
        this.n_1700_B.v_4262_N = -this.J_1907_R.v_4262_N;
        this.n_1700_B.G_564_y = this.J_1907_R.G_564_y;
        this.n_1700_B.u_1723_Y = this.J_1907_R.u_1723_Y;
        this.n_1700_B.w_1484_f = -this.J_1907_R.w_1484_f;
    }
}


