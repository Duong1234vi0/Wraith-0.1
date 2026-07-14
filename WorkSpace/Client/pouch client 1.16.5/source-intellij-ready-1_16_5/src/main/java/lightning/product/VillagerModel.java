/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import lightning.product.VillagerHeadModel;
import lightning.product.ListModel;
import lightning.product.N_4263_v;
import lightning.product.e_4189_z;
import lightning.product.g_4621_i;
import lightning.product.u_530_F;
import lightning.product.w_720_O;

public class VillagerModel<T extends N_4263_v>
extends ListModel<T>
implements VillagerHeadModel,
w_720_O {
    protected e_4189_z n_1700_B;
    protected e_4189_z J_1907_R;
    protected final e_4189_z R_4764_Y;
    protected final e_4189_z G_564_y;
    protected final e_4189_z P_1922_E;
    protected final e_4189_z u_1723_Y;
    protected final e_4189_z v_4262_N;
    protected final e_4189_z w_1484_f;
    protected final e_4189_z t_148_a;

    public VillagerModel(float scale) {
        this(scale, 64, 64);
    }

    public VillagerModel(float p_i51059_1_, int p_i51059_2_, int p_i51059_3_) {
        float f = 0.5f;
        this.n_1700_B = new e_4189_z(this).J_1907_R(p_i51059_2_, p_i51059_3_);
        this.n_1700_B.n_1700_B(0.0f, 0.0f, 0.0f);
        this.n_1700_B.n_1700_B(0, 0).n_1700_B(-4.0f, -10.0f, -4.0f, 8.0f, 10.0f, 8.0f, p_i51059_1_);
        this.J_1907_R = new e_4189_z(this).J_1907_R(p_i51059_2_, p_i51059_3_);
        this.J_1907_R.n_1700_B(0.0f, 0.0f, 0.0f);
        this.J_1907_R.n_1700_B(32, 0).n_1700_B(-4.0f, -10.0f, -4.0f, 8.0f, 10.0f, 8.0f, p_i51059_1_ + 0.5f);
        this.n_1700_B.J_1907_R(this.J_1907_R);
        this.R_4764_Y = new e_4189_z(this).J_1907_R(p_i51059_2_, p_i51059_3_);
        this.R_4764_Y.n_1700_B(0.0f, 0.0f, 0.0f);
        this.R_4764_Y.n_1700_B(30, 47).n_1700_B(-8.0f, -8.0f, -6.0f, 16.0f, 16.0f, 1.0f, p_i51059_1_);
        this.R_4764_Y.u_1723_Y = -1.5707964f;
        this.J_1907_R.J_1907_R(this.R_4764_Y);
        this.t_148_a = new e_4189_z(this).J_1907_R(p_i51059_2_, p_i51059_3_);
        this.t_148_a.n_1700_B(0.0f, -2.0f, 0.0f);
        this.t_148_a.n_1700_B(24, 0).n_1700_B(-1.0f, -1.0f, -6.0f, 2.0f, 4.0f, 2.0f, p_i51059_1_);
        this.n_1700_B.J_1907_R(this.t_148_a);
        this.G_564_y = new e_4189_z(this).J_1907_R(p_i51059_2_, p_i51059_3_);
        this.G_564_y.n_1700_B(0.0f, 0.0f, 0.0f);
        this.G_564_y.n_1700_B(16, 20).n_1700_B(-4.0f, 0.0f, -3.0f, 8.0f, 12.0f, 6.0f, p_i51059_1_);
        this.P_1922_E = new e_4189_z(this).J_1907_R(p_i51059_2_, p_i51059_3_);
        this.P_1922_E.n_1700_B(0.0f, 0.0f, 0.0f);
        this.P_1922_E.n_1700_B(0, 38).n_1700_B(-4.0f, 0.0f, -3.0f, 8.0f, 18.0f, 6.0f, p_i51059_1_ + 0.5f);
        this.G_564_y.J_1907_R(this.P_1922_E);
        this.u_1723_Y = new e_4189_z(this).J_1907_R(p_i51059_2_, p_i51059_3_);
        this.u_1723_Y.n_1700_B(0.0f, 2.0f, 0.0f);
        this.u_1723_Y.n_1700_B(44, 22).n_1700_B(-8.0f, -2.0f, -2.0f, 4.0f, 8.0f, 4.0f, p_i51059_1_);
        this.u_1723_Y.n_1700_B(44, 22).n_1700_B(4.0f, -2.0f, -2.0f, 4.0f, 8.0f, 4.0f, p_i51059_1_, true);
        this.u_1723_Y.n_1700_B(40, 38).n_1700_B(-4.0f, 2.0f, -2.0f, 8.0f, 4.0f, 4.0f, p_i51059_1_);
        this.v_4262_N = new e_4189_z(this, 0, 22).J_1907_R(p_i51059_2_, p_i51059_3_);
        this.v_4262_N.n_1700_B(-2.0f, 12.0f, 0.0f);
        this.v_4262_N.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, p_i51059_1_);
        this.w_1484_f = new e_4189_z(this, 0, 22).J_1907_R(p_i51059_2_, p_i51059_3_);
        this.w_1484_f.t_148_a = true;
        this.w_1484_f.n_1700_B(2.0f, 12.0f, 0.0f);
        this.w_1484_f.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, p_i51059_1_);
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.n_1700_B, (Object)this.G_564_y, (Object)this.v_4262_N, (Object)this.w_1484_f, (Object)this.u_1723_Y);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag = false;
        if (entityIn instanceof g_4621_i) {
            flag = ((g_4621_i)entityIn).y_4642_Y() > 0;
        }
        this.n_1700_B.v_4262_N = netHeadYaw * ((float)Math.PI / 180);
        this.n_1700_B.u_1723_Y = headPitch * ((float)Math.PI / 180);
        if (flag) {
            this.n_1700_B.w_1484_f = 0.3f * u_530_F.n_1700_B(0.45f * ageInTicks);
            this.n_1700_B.u_1723_Y = 0.4f;
        } else {
            this.n_1700_B.w_1484_f = 0.0f;
        }
        this.u_1723_Y.G_564_y = 3.0f;
        this.u_1723_Y.P_1922_E = -1.0f;
        this.u_1723_Y.u_1723_Y = -0.75f;
        this.v_4262_N.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * 1.4f * limbSwingAmount * 0.5f;
        this.w_1484_f.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount * 0.5f;
        this.v_4262_N.v_4262_N = 0.0f;
        this.w_1484_f.v_4262_N = 0.0f;
    }

    @Override
    public e_4189_z R_4764_Y() {
        return this.n_1700_B;
    }

    @Override
    public void n_1700_B(boolean p_217146_1_) {
        this.n_1700_B.s_956_w = p_217146_1_;
        this.J_1907_R.s_956_w = p_217146_1_;
        this.R_4764_Y.s_956_w = p_217146_1_;
    }
}


