/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import lightning.product.N_4263_v;
import lightning.product.e_4189_z;
import lightning.product.r_2604_d;
import lightning.product.u_530_F;

public class TropicalFishModelA<T extends N_4263_v>
extends r_2604_d<T> {
    private final e_4189_z n_1700_B;
    private final e_4189_z J_1907_R;
    private final e_4189_z R_4764_Y;
    private final e_4189_z G_564_y;
    private final e_4189_z P_1922_E;

    public TropicalFishModelA(float p_i48892_1_) {
        this.textureWidth = 32;
        this.textureHeight = 32;
        int i = 22;
        this.n_1700_B = new e_4189_z(this, 0, 0);
        this.n_1700_B.n_1700_B(-1.0f, -1.5f, -3.0f, 2.0f, 3.0f, 6.0f, p_i48892_1_);
        this.n_1700_B.n_1700_B(0.0f, 22.0f, 0.0f);
        this.J_1907_R = new e_4189_z(this, 22, -6);
        this.J_1907_R.n_1700_B(0.0f, -1.5f, 0.0f, 0.0f, 3.0f, 6.0f, p_i48892_1_);
        this.J_1907_R.n_1700_B(0.0f, 22.0f, 3.0f);
        this.R_4764_Y = new e_4189_z(this, 2, 16);
        this.R_4764_Y.n_1700_B(-2.0f, -1.0f, 0.0f, 2.0f, 2.0f, 0.0f, p_i48892_1_);
        this.R_4764_Y.n_1700_B(-1.0f, 22.5f, 0.0f);
        this.R_4764_Y.v_4262_N = 0.7853982f;
        this.G_564_y = new e_4189_z(this, 2, 12);
        this.G_564_y.n_1700_B(0.0f, -1.0f, 0.0f, 2.0f, 2.0f, 0.0f, p_i48892_1_);
        this.G_564_y.n_1700_B(1.0f, 22.5f, 0.0f);
        this.G_564_y.v_4262_N = -0.7853982f;
        this.P_1922_E = new e_4189_z(this, 10, -5);
        this.P_1922_E.n_1700_B(0.0f, -3.0f, 0.0f, 0.0f, 3.0f, 6.0f, p_i48892_1_);
        this.P_1922_E.n_1700_B(0.0f, 20.5f, -3.0f);
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.n_1700_B, (Object)this.J_1907_R, (Object)this.R_4764_Y, (Object)this.G_564_y, (Object)this.P_1922_E);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = 1.0f;
        if (!((N_4263_v)entityIn).RowButton()) {
            f = 1.5f;
        }
        this.J_1907_R.v_4262_N = -f * 0.45f * u_530_F.n_1700_B(0.6f * ageInTicks);
    }
}


