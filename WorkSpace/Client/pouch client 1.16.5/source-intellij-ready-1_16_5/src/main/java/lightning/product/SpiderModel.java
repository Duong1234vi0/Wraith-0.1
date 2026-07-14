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

public class SpiderModel<T extends N_4263_v>
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

    public SpiderModel() {
        float f = 0.0f;
        int i = 15;
        this.n_1700_B = new e_4189_z(this, 32, 4);
        this.n_1700_B.n_1700_B(-4.0f, -4.0f, -8.0f, 8.0f, 8.0f, 8.0f, 0.0f);
        this.n_1700_B.n_1700_B(0.0f, 15.0f, -3.0f);
        this.J_1907_R = new e_4189_z(this, 0, 0);
        this.J_1907_R.n_1700_B(-3.0f, -3.0f, -3.0f, 6.0f, 6.0f, 6.0f, 0.0f);
        this.J_1907_R.n_1700_B(0.0f, 15.0f, 0.0f);
        this.R_4764_Y = new e_4189_z(this, 0, 12);
        this.R_4764_Y.n_1700_B(-5.0f, -4.0f, -6.0f, 10.0f, 8.0f, 12.0f, 0.0f);
        this.R_4764_Y.n_1700_B(0.0f, 15.0f, 9.0f);
        this.G_564_y = new e_4189_z(this, 18, 0);
        this.G_564_y.n_1700_B(-15.0f, -1.0f, -1.0f, 16.0f, 2.0f, 2.0f, 0.0f);
        this.G_564_y.n_1700_B(-4.0f, 15.0f, 2.0f);
        this.P_1922_E = new e_4189_z(this, 18, 0);
        this.P_1922_E.n_1700_B(-1.0f, -1.0f, -1.0f, 16.0f, 2.0f, 2.0f, 0.0f);
        this.P_1922_E.n_1700_B(4.0f, 15.0f, 2.0f);
        this.u_1723_Y = new e_4189_z(this, 18, 0);
        this.u_1723_Y.n_1700_B(-15.0f, -1.0f, -1.0f, 16.0f, 2.0f, 2.0f, 0.0f);
        this.u_1723_Y.n_1700_B(-4.0f, 15.0f, 1.0f);
        this.v_4262_N = new e_4189_z(this, 18, 0);
        this.v_4262_N.n_1700_B(-1.0f, -1.0f, -1.0f, 16.0f, 2.0f, 2.0f, 0.0f);
        this.v_4262_N.n_1700_B(4.0f, 15.0f, 1.0f);
        this.w_1484_f = new e_4189_z(this, 18, 0);
        this.w_1484_f.n_1700_B(-15.0f, -1.0f, -1.0f, 16.0f, 2.0f, 2.0f, 0.0f);
        this.w_1484_f.n_1700_B(-4.0f, 15.0f, 0.0f);
        this.t_148_a = new e_4189_z(this, 18, 0);
        this.t_148_a.n_1700_B(-1.0f, -1.0f, -1.0f, 16.0f, 2.0f, 2.0f, 0.0f);
        this.t_148_a.n_1700_B(4.0f, 15.0f, 0.0f);
        this.s_956_w = new e_4189_z(this, 18, 0);
        this.s_956_w.n_1700_B(-15.0f, -1.0f, -1.0f, 16.0f, 2.0f, 2.0f, 0.0f);
        this.s_956_w.n_1700_B(-4.0f, 15.0f, -1.0f);
        this.u_2550_I = new e_4189_z(this, 18, 0);
        this.u_2550_I.n_1700_B(-1.0f, -1.0f, -1.0f, 16.0f, 2.0f, 2.0f, 0.0f);
        this.u_2550_I.n_1700_B(4.0f, 15.0f, -1.0f);
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.n_1700_B, (Object)this.J_1907_R, (Object)this.R_4764_Y, (Object)this.G_564_y, (Object)this.P_1922_E, (Object)this.u_1723_Y, (Object)this.v_4262_N, (Object)this.w_1484_f, (Object)this.t_148_a, (Object)this.s_956_w, (Object)this.u_2550_I);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.n_1700_B.v_4262_N = netHeadYaw * ((float)Math.PI / 180);
        this.n_1700_B.u_1723_Y = headPitch * ((float)Math.PI / 180);
        float f = 0.7853982f;
        this.G_564_y.w_1484_f = -0.7853982f;
        this.P_1922_E.w_1484_f = 0.7853982f;
        this.u_1723_Y.w_1484_f = -0.58119464f;
        this.v_4262_N.w_1484_f = 0.58119464f;
        this.w_1484_f.w_1484_f = -0.58119464f;
        this.t_148_a.w_1484_f = 0.58119464f;
        this.s_956_w.w_1484_f = -0.7853982f;
        this.u_2550_I.w_1484_f = 0.7853982f;
        float f1 = -0.0f;
        float f2 = 0.3926991f;
        this.G_564_y.v_4262_N = 0.7853982f;
        this.P_1922_E.v_4262_N = -0.7853982f;
        this.u_1723_Y.v_4262_N = 0.3926991f;
        this.v_4262_N.v_4262_N = -0.3926991f;
        this.w_1484_f.v_4262_N = -0.3926991f;
        this.t_148_a.v_4262_N = 0.3926991f;
        this.s_956_w.v_4262_N = -0.7853982f;
        this.u_2550_I.v_4262_N = 0.7853982f;
        float f3 = -(u_530_F.J_1907_R(limbSwing * 0.6662f * 2.0f + 0.0f) * 0.4f) * limbSwingAmount;
        float f4 = -(u_530_F.J_1907_R(limbSwing * 0.6662f * 2.0f + (float)Math.PI) * 0.4f) * limbSwingAmount;
        float f5 = -(u_530_F.J_1907_R(limbSwing * 0.6662f * 2.0f + 1.5707964f) * 0.4f) * limbSwingAmount;
        float f6 = -(u_530_F.J_1907_R(limbSwing * 0.6662f * 2.0f + 4.712389f) * 0.4f) * limbSwingAmount;
        float f7 = Math.abs(u_530_F.n_1700_B(limbSwing * 0.6662f + 0.0f) * 0.4f) * limbSwingAmount;
        float f8 = Math.abs(u_530_F.n_1700_B(limbSwing * 0.6662f + (float)Math.PI) * 0.4f) * limbSwingAmount;
        float f9 = Math.abs(u_530_F.n_1700_B(limbSwing * 0.6662f + 1.5707964f) * 0.4f) * limbSwingAmount;
        float f10 = Math.abs(u_530_F.n_1700_B(limbSwing * 0.6662f + 4.712389f) * 0.4f) * limbSwingAmount;
        this.G_564_y.v_4262_N += f3;
        this.P_1922_E.v_4262_N += -f3;
        this.u_1723_Y.v_4262_N += f4;
        this.v_4262_N.v_4262_N += -f4;
        this.w_1484_f.v_4262_N += f5;
        this.t_148_a.v_4262_N += -f5;
        this.s_956_w.v_4262_N += f6;
        this.u_2550_I.v_4262_N += -f6;
        this.G_564_y.w_1484_f += f7;
        this.P_1922_E.w_1484_f += -f7;
        this.u_1723_Y.w_1484_f += f8;
        this.v_4262_N.w_1484_f += -f8;
        this.w_1484_f.w_1484_f += f9;
        this.t_148_a.w_1484_f += -f9;
        this.s_956_w.w_1484_f += f10;
        this.u_2550_I.w_1484_f += -f10;
    }
}


