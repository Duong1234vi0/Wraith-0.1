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

public class PufferfishMidModel<T extends N_4263_v>
extends ListModel<T> {
    private final e_4189_z n_1700_B;
    private final e_4189_z J_1907_R;
    private final e_4189_z R_4764_Y;
    private final e_4189_z G_564_y;
    private final e_4189_z P_1922_E;
    private final e_4189_z u_1723_Y;
    private final e_4189_z v_4262_N;
    private final e_4189_z w_1484_f;
    private final e_4189_z t_148_a;
    private final e_4189_z s_956_w;
    private final e_4189_z u_2550_I;

    public PufferfishMidModel() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        int i = 22;
        this.n_1700_B = new e_4189_z(this, 12, 22);
        this.n_1700_B.n_1700_B(-2.5f, -5.0f, -2.5f, 5.0f, 5.0f, 5.0f);
        this.n_1700_B.n_1700_B(0.0f, 22.0f, 0.0f);
        this.J_1907_R = new e_4189_z(this, 24, 0);
        this.J_1907_R.n_1700_B(-2.0f, 0.0f, 0.0f, 2.0f, 0.0f, 2.0f);
        this.J_1907_R.n_1700_B(-2.5f, 17.0f, -1.5f);
        this.R_4764_Y = new e_4189_z(this, 24, 3);
        this.R_4764_Y.n_1700_B(0.0f, 0.0f, 0.0f, 2.0f, 0.0f, 2.0f);
        this.R_4764_Y.n_1700_B(2.5f, 17.0f, -1.5f);
        this.G_564_y = new e_4189_z(this, 15, 16);
        this.G_564_y.n_1700_B(-2.5f, -1.0f, 0.0f, 5.0f, 1.0f, 1.0f);
        this.G_564_y.n_1700_B(0.0f, 17.0f, -2.5f);
        this.G_564_y.u_1723_Y = 0.7853982f;
        this.P_1922_E = new e_4189_z(this, 10, 16);
        this.P_1922_E.n_1700_B(-2.5f, -1.0f, -1.0f, 5.0f, 1.0f, 1.0f);
        this.P_1922_E.n_1700_B(0.0f, 17.0f, 2.5f);
        this.P_1922_E.u_1723_Y = -0.7853982f;
        this.u_1723_Y = new e_4189_z(this, 8, 16);
        this.u_1723_Y.n_1700_B(-1.0f, -5.0f, 0.0f, 1.0f, 5.0f, 1.0f);
        this.u_1723_Y.n_1700_B(-2.5f, 22.0f, -2.5f);
        this.u_1723_Y.v_4262_N = -0.7853982f;
        this.v_4262_N = new e_4189_z(this, 8, 16);
        this.v_4262_N.n_1700_B(-1.0f, -5.0f, 0.0f, 1.0f, 5.0f, 1.0f);
        this.v_4262_N.n_1700_B(-2.5f, 22.0f, 2.5f);
        this.v_4262_N.v_4262_N = 0.7853982f;
        this.w_1484_f = new e_4189_z(this, 4, 16);
        this.w_1484_f.n_1700_B(0.0f, -5.0f, 0.0f, 1.0f, 5.0f, 1.0f);
        this.w_1484_f.n_1700_B(2.5f, 22.0f, 2.5f);
        this.w_1484_f.v_4262_N = -0.7853982f;
        this.t_148_a = new e_4189_z(this, 0, 16);
        this.t_148_a.n_1700_B(0.0f, -5.0f, 0.0f, 1.0f, 5.0f, 1.0f);
        this.t_148_a.n_1700_B(2.5f, 22.0f, -2.5f);
        this.t_148_a.v_4262_N = 0.7853982f;
        this.s_956_w = new e_4189_z(this, 8, 22);
        this.s_956_w.n_1700_B(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        this.s_956_w.n_1700_B(0.5f, 22.0f, 2.5f);
        this.s_956_w.u_1723_Y = 0.7853982f;
        this.u_2550_I = new e_4189_z(this, 17, 21);
        this.u_2550_I.n_1700_B(-2.5f, 0.0f, 0.0f, 5.0f, 1.0f, 1.0f);
        this.u_2550_I.n_1700_B(0.0f, 22.0f, -2.5f);
        this.u_2550_I.u_1723_Y = -0.7853982f;
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.n_1700_B, (Object)this.J_1907_R, (Object)this.R_4764_Y, (Object)this.G_564_y, (Object)this.P_1922_E, (Object)this.u_1723_Y, (Object)this.v_4262_N, (Object)this.w_1484_f, (Object)this.t_148_a, (Object)this.s_956_w, (Object)this.u_2550_I);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.J_1907_R.w_1484_f = -0.2f + 0.4f * u_530_F.n_1700_B(ageInTicks * 0.2f);
        this.R_4764_Y.w_1484_f = 0.2f - 0.4f * u_530_F.n_1700_B(ageInTicks * 0.2f);
    }
}


