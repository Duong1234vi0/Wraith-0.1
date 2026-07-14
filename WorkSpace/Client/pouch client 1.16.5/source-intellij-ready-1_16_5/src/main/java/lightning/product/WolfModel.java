/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import lightning.product.C_3622_I;
import lightning.product.H_113_X;
import lightning.product.e_4189_z;
import lightning.product.q_2335_j;
import lightning.product.u_530_F;

public class WolfModel<T extends q_2335_j>
extends H_113_X<T> {
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

    public WolfModel() {
        float f = 0.0f;
        float f1 = 13.5f;
        this.n_1700_B = new e_4189_z(this, 0, 0);
        this.n_1700_B.n_1700_B(-1.0f, 13.5f, -7.0f);
        this.J_1907_R = new e_4189_z(this, 0, 0);
        this.J_1907_R.n_1700_B(-2.0f, -3.0f, -2.0f, 6.0f, 6.0f, 4.0f, 0.0f);
        this.n_1700_B.J_1907_R(this.J_1907_R);
        this.R_4764_Y = new e_4189_z(this, 18, 14);
        this.R_4764_Y.n_1700_B(-3.0f, -2.0f, -3.0f, 6.0f, 9.0f, 6.0f, 0.0f);
        this.R_4764_Y.n_1700_B(0.0f, 14.0f, 2.0f);
        this.s_956_w = new e_4189_z(this, 21, 0);
        this.s_956_w.n_1700_B(-3.0f, -3.0f, -3.0f, 8.0f, 6.0f, 7.0f, 0.0f);
        this.s_956_w.n_1700_B(-1.0f, 14.0f, 2.0f);
        this.G_564_y = new e_4189_z(this, 0, 18);
        this.G_564_y.n_1700_B(0.0f, 0.0f, -1.0f, 2.0f, 8.0f, 2.0f, 0.0f);
        this.G_564_y.n_1700_B(-2.5f, 16.0f, 7.0f);
        this.P_1922_E = new e_4189_z(this, 0, 18);
        this.P_1922_E.n_1700_B(0.0f, 0.0f, -1.0f, 2.0f, 8.0f, 2.0f, 0.0f);
        this.P_1922_E.n_1700_B(0.5f, 16.0f, 7.0f);
        this.u_1723_Y = new e_4189_z(this, 0, 18);
        this.u_1723_Y.n_1700_B(0.0f, 0.0f, -1.0f, 2.0f, 8.0f, 2.0f, 0.0f);
        this.u_1723_Y.n_1700_B(-2.5f, 16.0f, -4.0f);
        this.v_4262_N = new e_4189_z(this, 0, 18);
        this.v_4262_N.n_1700_B(0.0f, 0.0f, -1.0f, 2.0f, 8.0f, 2.0f, 0.0f);
        this.v_4262_N.n_1700_B(0.5f, 16.0f, -4.0f);
        this.w_1484_f = new e_4189_z(this, 9, 18);
        this.w_1484_f.n_1700_B(-1.0f, 12.0f, 8.0f);
        this.t_148_a = new e_4189_z(this, 9, 18);
        this.t_148_a.n_1700_B(0.0f, 0.0f, -1.0f, 2.0f, 8.0f, 2.0f, 0.0f);
        this.w_1484_f.J_1907_R(this.t_148_a);
        this.J_1907_R.n_1700_B(16, 14).n_1700_B(-2.0f, -5.0f, 0.0f, 2.0f, 2.0f, 1.0f, 0.0f);
        this.J_1907_R.n_1700_B(16, 14).n_1700_B(2.0f, -5.0f, 0.0f, 2.0f, 2.0f, 1.0f, 0.0f);
        this.J_1907_R.n_1700_B(0, 10).n_1700_B(-0.5f, 0.0f, -5.0f, 3.0f, 3.0f, 4.0f, 0.0f);
    }

    @Override
    protected Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.n_1700_B);
    }

    @Override
    protected Iterable<e_4189_z> J_1907_R() {
        return ImmutableList.of((Object)this.R_4764_Y, (Object)this.G_564_y, (Object)this.P_1922_E, (Object)this.u_1723_Y, (Object)this.v_4262_N, (Object)this.w_1484_f, (Object)this.s_956_w);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        this.w_1484_f.v_4262_N = entityIn.B_() ? 0.0f : u_530_F.J_1907_R(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
        if (((C_3622_I)entityIn).z_2372_L()) {
            this.s_956_w.n_1700_B(-1.0f, 16.0f, -3.0f);
            this.s_956_w.u_1723_Y = 1.2566371f;
            this.s_956_w.v_4262_N = 0.0f;
            this.R_4764_Y.n_1700_B(0.0f, 18.0f, 0.0f);
            this.R_4764_Y.u_1723_Y = 0.7853982f;
            this.w_1484_f.n_1700_B(-1.0f, 21.0f, 6.0f);
            this.G_564_y.n_1700_B(-2.5f, 22.7f, 2.0f);
            this.G_564_y.u_1723_Y = 4.712389f;
            this.P_1922_E.n_1700_B(0.5f, 22.7f, 2.0f);
            this.P_1922_E.u_1723_Y = 4.712389f;
            this.u_1723_Y.u_1723_Y = 5.811947f;
            this.u_1723_Y.n_1700_B(-2.49f, 17.0f, -4.0f);
            this.v_4262_N.u_1723_Y = 5.811947f;
            this.v_4262_N.n_1700_B(0.51f, 17.0f, -4.0f);
        } else {
            this.R_4764_Y.n_1700_B(0.0f, 14.0f, 2.0f);
            this.R_4764_Y.u_1723_Y = 1.5707964f;
            this.s_956_w.n_1700_B(-1.0f, 14.0f, -3.0f);
            this.s_956_w.u_1723_Y = this.R_4764_Y.u_1723_Y;
            this.w_1484_f.n_1700_B(-1.0f, 12.0f, 8.0f);
            this.G_564_y.n_1700_B(-2.5f, 16.0f, 7.0f);
            this.P_1922_E.n_1700_B(0.5f, 16.0f, 7.0f);
            this.u_1723_Y.n_1700_B(-2.5f, 16.0f, -4.0f);
            this.v_4262_N.n_1700_B(0.5f, 16.0f, -4.0f);
            this.G_564_y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
            this.P_1922_E.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount;
            this.u_1723_Y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount;
            this.v_4262_N.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
        }
        this.J_1907_R.w_1484_f = ((q_2335_j)entityIn).H_2857_Y(partialTick) + ((q_2335_j)entityIn).n_1700_B(partialTick, 0.0f);
        this.s_956_w.w_1484_f = ((q_2335_j)entityIn).n_1700_B(partialTick, -0.08f);
        this.R_4764_Y.w_1484_f = ((q_2335_j)entityIn).n_1700_B(partialTick, -0.16f);
        this.t_148_a.w_1484_f = ((q_2335_j)entityIn).n_1700_B(partialTick, -0.2f);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.n_1700_B.u_1723_Y = headPitch * ((float)Math.PI / 180);
        this.n_1700_B.v_4262_N = netHeadYaw * ((float)Math.PI / 180);
        this.w_1484_f.u_1723_Y = ageInTicks;
    }
}


