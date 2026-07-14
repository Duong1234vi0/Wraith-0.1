/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import lightning.product.N_4263_v;
import lightning.product.b_1913_J;
import lightning.product.AgeableListModel;
import lightning.product.e_4189_z;
import lightning.product.u_530_F;
import lightning.product.ModelUtils;

public class BeeModel<T extends b_1913_J>
extends AgeableListModel<T> {
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
    private float u_2550_I;

    public BeeModel() {
        super(false, 24.0f, 0.0f);
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.n_1700_B = new e_4189_z(this);
        this.n_1700_B.n_1700_B(0.0f, 19.0f, 0.0f);
        this.J_1907_R = new e_4189_z(this, 0, 0);
        this.J_1907_R.n_1700_B(0.0f, 0.0f, 0.0f);
        this.n_1700_B.J_1907_R(this.J_1907_R);
        this.J_1907_R.n_1700_B(-3.5f, -4.0f, -5.0f, 7.0f, 7.0f, 10.0f, 0.0f);
        this.w_1484_f = new e_4189_z(this, 26, 7);
        this.w_1484_f.n_1700_B(0.0f, -1.0f, 5.0f, 0.0f, 1.0f, 2.0f, 0.0f);
        this.J_1907_R.J_1907_R(this.w_1484_f);
        this.t_148_a = new e_4189_z(this, 2, 0);
        this.t_148_a.n_1700_B(0.0f, -2.0f, -5.0f);
        this.t_148_a.n_1700_B(1.5f, -2.0f, -3.0f, 1.0f, 2.0f, 3.0f, 0.0f);
        this.s_956_w = new e_4189_z(this, 2, 3);
        this.s_956_w.n_1700_B(0.0f, -2.0f, -5.0f);
        this.s_956_w.n_1700_B(-2.5f, -2.0f, -3.0f, 1.0f, 2.0f, 3.0f, 0.0f);
        this.J_1907_R.J_1907_R(this.t_148_a);
        this.J_1907_R.J_1907_R(this.s_956_w);
        this.R_4764_Y = new e_4189_z(this, 0, 18);
        this.R_4764_Y.n_1700_B(-1.5f, -4.0f, -3.0f);
        this.R_4764_Y.u_1723_Y = 0.0f;
        this.R_4764_Y.v_4262_N = -0.2618f;
        this.R_4764_Y.w_1484_f = 0.0f;
        this.n_1700_B.J_1907_R(this.R_4764_Y);
        this.R_4764_Y.n_1700_B(-9.0f, 0.0f, 0.0f, 9.0f, 0.0f, 6.0f, 0.001f);
        this.G_564_y = new e_4189_z(this, 0, 18);
        this.G_564_y.n_1700_B(1.5f, -4.0f, -3.0f);
        this.G_564_y.u_1723_Y = 0.0f;
        this.G_564_y.v_4262_N = 0.2618f;
        this.G_564_y.w_1484_f = 0.0f;
        this.G_564_y.t_148_a = true;
        this.n_1700_B.J_1907_R(this.G_564_y);
        this.G_564_y.n_1700_B(0.0f, 0.0f, 0.0f, 9.0f, 0.0f, 6.0f, 0.001f);
        this.P_1922_E = new e_4189_z(this);
        this.P_1922_E.n_1700_B(1.5f, 3.0f, -2.0f);
        this.n_1700_B.J_1907_R(this.P_1922_E);
        this.P_1922_E.n_1700_B("frontLegBox", -5.0f, 0.0f, 0.0f, 7, 2, 0, 0.0f, 26, 1);
        this.u_1723_Y = new e_4189_z(this);
        this.u_1723_Y.n_1700_B(1.5f, 3.0f, 0.0f);
        this.n_1700_B.J_1907_R(this.u_1723_Y);
        this.u_1723_Y.n_1700_B("midLegBox", -5.0f, 0.0f, 0.0f, 7, 2, 0, 0.0f, 26, 3);
        this.v_4262_N = new e_4189_z(this);
        this.v_4262_N.n_1700_B(1.5f, 3.0f, 2.0f);
        this.n_1700_B.J_1907_R(this.v_4262_N);
        this.v_4262_N.n_1700_B("backLegBox", -5.0f, 0.0f, 0.0f, 7, 2, 0, 0.0f, 26, 5);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, partialTick);
        this.u_2550_I = ((b_1913_J)entityIn).c_3005_b(partialTick);
        this.w_1484_f.s_956_w = !((b_1913_J)entityIn).c_2086_l();
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag;
        this.R_4764_Y.u_1723_Y = 0.0f;
        this.t_148_a.u_1723_Y = 0.0f;
        this.s_956_w.u_1723_Y = 0.0f;
        this.n_1700_B.u_1723_Y = 0.0f;
        this.n_1700_B.G_564_y = 19.0f;
        boolean bl = flag = ((N_4263_v)entityIn).M_1641_O() && ((N_4263_v)entityIn).I_4348_c().v_4262_N() < 1.0E-7;
        if (flag) {
            this.R_4764_Y.v_4262_N = -0.2618f;
            this.R_4764_Y.w_1484_f = 0.0f;
            this.G_564_y.u_1723_Y = 0.0f;
            this.G_564_y.v_4262_N = 0.2618f;
            this.G_564_y.w_1484_f = 0.0f;
            this.P_1922_E.u_1723_Y = 0.0f;
            this.u_1723_Y.u_1723_Y = 0.0f;
            this.v_4262_N.u_1723_Y = 0.0f;
        } else {
            float f = ageInTicks * 2.1f;
            this.R_4764_Y.v_4262_N = 0.0f;
            this.R_4764_Y.w_1484_f = u_530_F.J_1907_R(f) * (float)Math.PI * 0.15f;
            this.G_564_y.u_1723_Y = this.R_4764_Y.u_1723_Y;
            this.G_564_y.v_4262_N = this.R_4764_Y.v_4262_N;
            this.G_564_y.w_1484_f = -this.R_4764_Y.w_1484_f;
            this.P_1922_E.u_1723_Y = 0.7853982f;
            this.u_1723_Y.u_1723_Y = 0.7853982f;
            this.v_4262_N.u_1723_Y = 0.7853982f;
            this.n_1700_B.u_1723_Y = 0.0f;
            this.n_1700_B.v_4262_N = 0.0f;
            this.n_1700_B.w_1484_f = 0.0f;
        }
        if (!entityIn.B_()) {
            this.n_1700_B.u_1723_Y = 0.0f;
            this.n_1700_B.v_4262_N = 0.0f;
            this.n_1700_B.w_1484_f = 0.0f;
            if (!flag) {
                float f1 = u_530_F.J_1907_R(ageInTicks * 0.18f);
                this.n_1700_B.u_1723_Y = 0.1f + f1 * (float)Math.PI * 0.025f;
                this.t_148_a.u_1723_Y = f1 * (float)Math.PI * 0.03f;
                this.s_956_w.u_1723_Y = f1 * (float)Math.PI * 0.03f;
                this.P_1922_E.u_1723_Y = -f1 * (float)Math.PI * 0.1f + 0.3926991f;
                this.v_4262_N.u_1723_Y = -f1 * (float)Math.PI * 0.05f + 0.7853982f;
                this.n_1700_B.G_564_y = 19.0f - u_530_F.J_1907_R(ageInTicks * 0.18f) * 0.9f;
            }
        }
        if (this.u_2550_I > 0.0f) {
            this.n_1700_B.u_1723_Y = ModelUtils.n_1700_B(this.n_1700_B.u_1723_Y, 3.0915928f, this.u_2550_I);
        }
    }

    @Override
    protected Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<e_4189_z> J_1907_R() {
        return ImmutableList.of((Object)this.n_1700_B);
    }
}


