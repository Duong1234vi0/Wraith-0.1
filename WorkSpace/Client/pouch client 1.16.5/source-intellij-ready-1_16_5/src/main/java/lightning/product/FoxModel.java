/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import lightning.product.AgeableListModel;
import lightning.product.e_4189_z;
import lightning.product.g_1253_u;
import lightning.product.u_530_F;

public class FoxModel<T extends g_1253_u>
extends AgeableListModel<T> {
    public final e_4189_z n_1700_B;
    private final e_4189_z J_1907_R;
    private final e_4189_z R_4764_Y;
    private final e_4189_z G_564_y;
    private final e_4189_z P_1922_E;
    private final e_4189_z u_1723_Y;
    private final e_4189_z v_4262_N;
    private final e_4189_z w_1484_f;
    private final e_4189_z t_148_a;
    private final e_4189_z s_956_w;
    private float u_2550_I;

    public FoxModel() {
        super(true, 8.0f, 3.35f);
        this.textureWidth = 48;
        this.textureHeight = 32;
        this.n_1700_B = new e_4189_z(this, 1, 5);
        this.n_1700_B.n_1700_B(-3.0f, -2.0f, -5.0f, 8.0f, 6.0f, 6.0f);
        this.n_1700_B.n_1700_B(-1.0f, 16.5f, -3.0f);
        this.J_1907_R = new e_4189_z(this, 8, 1);
        this.J_1907_R.n_1700_B(-3.0f, -4.0f, -4.0f, 2.0f, 2.0f, 1.0f);
        this.R_4764_Y = new e_4189_z(this, 15, 1);
        this.R_4764_Y.n_1700_B(3.0f, -4.0f, -4.0f, 2.0f, 2.0f, 1.0f);
        this.G_564_y = new e_4189_z(this, 6, 18);
        this.G_564_y.n_1700_B(-1.0f, 2.01f, -8.0f, 4.0f, 2.0f, 3.0f);
        this.n_1700_B.J_1907_R(this.J_1907_R);
        this.n_1700_B.J_1907_R(this.R_4764_Y);
        this.n_1700_B.J_1907_R(this.G_564_y);
        this.P_1922_E = new e_4189_z(this, 24, 15);
        this.P_1922_E.n_1700_B(-3.0f, 3.999f, -3.5f, 6.0f, 11.0f, 6.0f);
        this.P_1922_E.n_1700_B(0.0f, 16.0f, -6.0f);
        float f = 0.001f;
        this.u_1723_Y = new e_4189_z(this, 13, 24);
        this.u_1723_Y.n_1700_B(2.0f, 0.5f, -1.0f, 2.0f, 6.0f, 2.0f, 0.001f);
        this.u_1723_Y.n_1700_B(-5.0f, 17.5f, 7.0f);
        this.v_4262_N = new e_4189_z(this, 4, 24);
        this.v_4262_N.n_1700_B(2.0f, 0.5f, -1.0f, 2.0f, 6.0f, 2.0f, 0.001f);
        this.v_4262_N.n_1700_B(-1.0f, 17.5f, 7.0f);
        this.w_1484_f = new e_4189_z(this, 13, 24);
        this.w_1484_f.n_1700_B(2.0f, 0.5f, -1.0f, 2.0f, 6.0f, 2.0f, 0.001f);
        this.w_1484_f.n_1700_B(-5.0f, 17.5f, 0.0f);
        this.t_148_a = new e_4189_z(this, 4, 24);
        this.t_148_a.n_1700_B(2.0f, 0.5f, -1.0f, 2.0f, 6.0f, 2.0f, 0.001f);
        this.t_148_a.n_1700_B(-1.0f, 17.5f, 0.0f);
        this.s_956_w = new e_4189_z(this, 30, 0);
        this.s_956_w.n_1700_B(2.0f, 0.0f, -1.0f, 4.0f, 9.0f, 5.0f);
        this.s_956_w.n_1700_B(-4.0f, 15.0f, -1.0f);
        this.P_1922_E.J_1907_R(this.s_956_w);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        this.P_1922_E.u_1723_Y = 1.5707964f;
        this.s_956_w.u_1723_Y = -0.05235988f;
        this.u_1723_Y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
        this.v_4262_N.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount;
        this.w_1484_f.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount;
        this.t_148_a.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
        this.n_1700_B.n_1700_B(-1.0f, 16.5f, -3.0f);
        this.n_1700_B.v_4262_N = 0.0f;
        this.n_1700_B.w_1484_f = ((g_1253_u)entityIn).c_3005_b(partialTick);
        this.u_1723_Y.s_956_w = true;
        this.v_4262_N.s_956_w = true;
        this.w_1484_f.s_956_w = true;
        this.t_148_a.s_956_w = true;
        this.P_1922_E.n_1700_B(0.0f, 16.0f, -6.0f);
        this.P_1922_E.w_1484_f = 0.0f;
        this.u_1723_Y.n_1700_B(-5.0f, 17.5f, 7.0f);
        this.v_4262_N.n_1700_B(-1.0f, 17.5f, 7.0f);
        if (((g_1253_u)entityIn).Z_875_P()) {
            this.P_1922_E.u_1723_Y = 1.6755161f;
            float f = ((g_1253_u)entityIn).H_2857_Y(partialTick);
            this.P_1922_E.n_1700_B(0.0f, 16.0f + ((g_1253_u)entityIn).H_2857_Y(partialTick), -6.0f);
            this.n_1700_B.n_1700_B(-1.0f, 16.5f + f, -3.0f);
            this.n_1700_B.v_4262_N = 0.0f;
        } else if (((g_1253_u)entityIn).z_2372_L()) {
            this.P_1922_E.w_1484_f = -1.5707964f;
            this.P_1922_E.n_1700_B(0.0f, 21.0f, -6.0f);
            this.s_956_w.u_1723_Y = -2.6179938f;
            if (this.M_182_A) {
                this.s_956_w.u_1723_Y = -2.1816616f;
                this.P_1922_E.n_1700_B(0.0f, 21.0f, -2.0f);
            }
            this.n_1700_B.n_1700_B(1.0f, 19.49f, -3.0f);
            this.n_1700_B.u_1723_Y = 0.0f;
            this.n_1700_B.v_4262_N = -2.0943952f;
            this.n_1700_B.w_1484_f = 0.0f;
            this.u_1723_Y.s_956_w = false;
            this.v_4262_N.s_956_w = false;
            this.w_1484_f.s_956_w = false;
            this.t_148_a.s_956_w = false;
        } else if (((g_1253_u)entityIn).V_1176_p()) {
            this.P_1922_E.u_1723_Y = 0.5235988f;
            this.P_1922_E.n_1700_B(0.0f, 9.0f, -3.0f);
            this.s_956_w.u_1723_Y = 0.7853982f;
            this.s_956_w.n_1700_B(-4.0f, 15.0f, -2.0f);
            this.n_1700_B.n_1700_B(-1.0f, 10.0f, -0.25f);
            this.n_1700_B.u_1723_Y = 0.0f;
            this.n_1700_B.v_4262_N = 0.0f;
            if (this.M_182_A) {
                this.n_1700_B.n_1700_B(-1.0f, 13.0f, -3.75f);
            }
            this.u_1723_Y.u_1723_Y = -1.3089969f;
            this.u_1723_Y.n_1700_B(-5.0f, 21.5f, 6.75f);
            this.v_4262_N.u_1723_Y = -1.3089969f;
            this.v_4262_N.n_1700_B(-1.0f, 21.5f, 6.75f);
            this.w_1484_f.u_1723_Y = -0.2617994f;
            this.t_148_a.u_1723_Y = -0.2617994f;
        }
    }

    @Override
    protected Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.n_1700_B);
    }

    @Override
    protected Iterable<e_4189_z> J_1907_R() {
        return ImmutableList.of((Object)this.P_1922_E, (Object)this.u_1723_Y, (Object)this.v_4262_N, (Object)this.w_1484_f, (Object)this.t_148_a);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!(((g_1253_u)entityIn).z_2372_L() || ((g_1253_u)entityIn).y_2447_C() || ((g_1253_u)entityIn).Z_875_P())) {
            this.n_1700_B.u_1723_Y = headPitch * ((float)Math.PI / 180);
            this.n_1700_B.v_4262_N = netHeadYaw * ((float)Math.PI / 180);
        }
        if (((g_1253_u)entityIn).z_2372_L()) {
            this.n_1700_B.u_1723_Y = 0.0f;
            this.n_1700_B.v_4262_N = -2.0943952f;
            this.n_1700_B.w_1484_f = u_530_F.J_1907_R(ageInTicks * 0.027f) / 22.0f;
        }
        if (((g_1253_u)entityIn).Z_875_P()) {
            float f;
            this.P_1922_E.v_4262_N = f = u_530_F.J_1907_R(ageInTicks) * 0.01f;
            this.u_1723_Y.w_1484_f = f;
            this.v_4262_N.w_1484_f = f;
            this.w_1484_f.w_1484_f = f / 2.0f;
            this.t_148_a.w_1484_f = f / 2.0f;
        }
        if (((g_1253_u)entityIn).y_2447_C()) {
            float f1 = 0.1f;
            this.u_2550_I += 0.67f;
            this.u_1723_Y.u_1723_Y = u_530_F.J_1907_R(this.u_2550_I * 0.4662f) * 0.1f;
            this.v_4262_N.u_1723_Y = u_530_F.J_1907_R(this.u_2550_I * 0.4662f + (float)Math.PI) * 0.1f;
            this.w_1484_f.u_1723_Y = u_530_F.J_1907_R(this.u_2550_I * 0.4662f + (float)Math.PI) * 0.1f;
            this.t_148_a.u_1723_Y = u_530_F.J_1907_R(this.u_2550_I * 0.4662f) * 0.1f;
        }
    }
}


