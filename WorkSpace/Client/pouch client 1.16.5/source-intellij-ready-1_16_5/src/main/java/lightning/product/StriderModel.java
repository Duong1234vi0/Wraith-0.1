/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import lightning.product.L_3233_K;
import lightning.product.ListModel;
import lightning.product.e_4189_z;
import lightning.product.u_530_F;

public class StriderModel<T extends L_3233_K>
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

    public StriderModel() {
        this.textureWidth = 64;
        this.textureHeight = 128;
        this.n_1700_B = new e_4189_z(this, 0, 32);
        this.n_1700_B.n_1700_B(-4.0f, 8.0f, 0.0f);
        this.n_1700_B.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 16.0f, 4.0f, 0.0f);
        this.J_1907_R = new e_4189_z(this, 0, 55);
        this.J_1907_R.n_1700_B(4.0f, 8.0f, 0.0f);
        this.J_1907_R.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 16.0f, 4.0f, 0.0f);
        this.R_4764_Y = new e_4189_z(this, 0, 0);
        this.R_4764_Y.n_1700_B(0.0f, 1.0f, 0.0f);
        this.R_4764_Y.n_1700_B(-8.0f, -6.0f, -8.0f, 16.0f, 14.0f, 16.0f, 0.0f);
        this.G_564_y = new e_4189_z(this, 16, 65);
        this.G_564_y.n_1700_B(-8.0f, 4.0f, -8.0f);
        this.G_564_y.n_1700_B(-12.0f, 0.0f, 0.0f, 12.0f, 0.0f, 16.0f, 0.0f, true);
        this.n_1700_B(this.G_564_y, 0.0f, 0.0f, -1.2217305f);
        this.P_1922_E = new e_4189_z(this, 16, 49);
        this.P_1922_E.n_1700_B(-8.0f, -1.0f, -8.0f);
        this.P_1922_E.n_1700_B(-12.0f, 0.0f, 0.0f, 12.0f, 0.0f, 16.0f, 0.0f, true);
        this.n_1700_B(this.P_1922_E, 0.0f, 0.0f, -1.134464f);
        this.u_1723_Y = new e_4189_z(this, 16, 33);
        this.u_1723_Y.n_1700_B(-8.0f, -5.0f, -8.0f);
        this.u_1723_Y.n_1700_B(-12.0f, 0.0f, 0.0f, 12.0f, 0.0f, 16.0f, 0.0f, true);
        this.n_1700_B(this.u_1723_Y, 0.0f, 0.0f, -0.87266463f);
        this.v_4262_N = new e_4189_z(this, 16, 33);
        this.v_4262_N.n_1700_B(8.0f, -6.0f, -8.0f);
        this.v_4262_N.n_1700_B(0.0f, 0.0f, 0.0f, 12.0f, 0.0f, 16.0f, 0.0f);
        this.n_1700_B(this.v_4262_N, 0.0f, 0.0f, 0.87266463f);
        this.w_1484_f = new e_4189_z(this, 16, 49);
        this.w_1484_f.n_1700_B(8.0f, -2.0f, -8.0f);
        this.w_1484_f.n_1700_B(0.0f, 0.0f, 0.0f, 12.0f, 0.0f, 16.0f, 0.0f);
        this.n_1700_B(this.w_1484_f, 0.0f, 0.0f, 1.134464f);
        this.t_148_a = new e_4189_z(this, 16, 65);
        this.t_148_a.n_1700_B(8.0f, 3.0f, -8.0f);
        this.t_148_a.n_1700_B(0.0f, 0.0f, 0.0f, 12.0f, 0.0f, 16.0f, 0.0f);
        this.n_1700_B(this.t_148_a, 0.0f, 0.0f, 1.2217305f);
        this.R_4764_Y.J_1907_R(this.G_564_y);
        this.R_4764_Y.J_1907_R(this.P_1922_E);
        this.R_4764_Y.J_1907_R(this.u_1723_Y);
        this.R_4764_Y.J_1907_R(this.v_4262_N);
        this.R_4764_Y.J_1907_R(this.w_1484_f);
        this.R_4764_Y.J_1907_R(this.t_148_a);
    }

    @Override
    public void n_1700_B(L_3233_K entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        limbSwingAmount = Math.min(0.25f, limbSwingAmount);
        if (entityIn.o_3599_Z().size() <= 0) {
            this.R_4764_Y.u_1723_Y = headPitch * ((float)Math.PI / 180);
            this.R_4764_Y.v_4262_N = netHeadYaw * ((float)Math.PI / 180);
        } else {
            this.R_4764_Y.u_1723_Y = 0.0f;
            this.R_4764_Y.v_4262_N = 0.0f;
        }
        float f = 1.5f;
        this.R_4764_Y.w_1484_f = 0.1f * u_530_F.n_1700_B(limbSwing * 1.5f) * 4.0f * limbSwingAmount;
        this.R_4764_Y.G_564_y = 2.0f;
        this.R_4764_Y.G_564_y -= 2.0f * u_530_F.J_1907_R(limbSwing * 1.5f) * 2.0f * limbSwingAmount;
        this.J_1907_R.u_1723_Y = u_530_F.n_1700_B(limbSwing * 1.5f * 0.5f) * 2.0f * limbSwingAmount;
        this.n_1700_B.u_1723_Y = u_530_F.n_1700_B(limbSwing * 1.5f * 0.5f + (float)Math.PI) * 2.0f * limbSwingAmount;
        this.J_1907_R.w_1484_f = 0.17453292f * u_530_F.J_1907_R(limbSwing * 1.5f * 0.5f) * limbSwingAmount;
        this.n_1700_B.w_1484_f = 0.17453292f * u_530_F.J_1907_R(limbSwing * 1.5f * 0.5f + (float)Math.PI) * limbSwingAmount;
        this.J_1907_R.G_564_y = 8.0f + 2.0f * u_530_F.n_1700_B(limbSwing * 1.5f * 0.5f + (float)Math.PI) * 2.0f * limbSwingAmount;
        this.n_1700_B.G_564_y = 8.0f + 2.0f * u_530_F.n_1700_B(limbSwing * 1.5f * 0.5f) * 2.0f * limbSwingAmount;
        this.G_564_y.w_1484_f = -1.2217305f;
        this.P_1922_E.w_1484_f = -1.134464f;
        this.u_1723_Y.w_1484_f = -0.87266463f;
        this.v_4262_N.w_1484_f = 0.87266463f;
        this.w_1484_f.w_1484_f = 1.134464f;
        this.t_148_a.w_1484_f = 1.2217305f;
        float f1 = u_530_F.J_1907_R(limbSwing * 1.5f + (float)Math.PI) * limbSwingAmount;
        this.G_564_y.w_1484_f += f1 * 1.3f;
        this.P_1922_E.w_1484_f += f1 * 1.2f;
        this.u_1723_Y.w_1484_f += f1 * 0.6f;
        this.v_4262_N.w_1484_f += f1 * 0.6f;
        this.w_1484_f.w_1484_f += f1 * 1.2f;
        this.t_148_a.w_1484_f += f1 * 1.3f;
        float f2 = 1.0f;
        float f3 = 1.0f;
        this.G_564_y.w_1484_f += 0.05f * u_530_F.n_1700_B(ageInTicks * 1.0f * -0.4f);
        this.P_1922_E.w_1484_f += 0.1f * u_530_F.n_1700_B(ageInTicks * 1.0f * 0.2f);
        this.u_1723_Y.w_1484_f += 0.1f * u_530_F.n_1700_B(ageInTicks * 1.0f * 0.4f);
        this.v_4262_N.w_1484_f += 0.1f * u_530_F.n_1700_B(ageInTicks * 1.0f * 0.4f);
        this.w_1484_f.w_1484_f += 0.1f * u_530_F.n_1700_B(ageInTicks * 1.0f * 0.2f);
        this.t_148_a.w_1484_f += 0.05f * u_530_F.n_1700_B(ageInTicks * 1.0f * -0.4f);
    }

    @Override
    public void n_1700_B(e_4189_z p_239127_1_, float p_239127_2_, float p_239127_3_, float p_239127_4_) {
        p_239127_1_.u_1723_Y = p_239127_2_;
        p_239127_1_.v_4262_N = p_239127_3_;
        p_239127_1_.w_1484_f = p_239127_4_;
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.R_4764_Y, (Object)this.J_1907_R, (Object)this.n_1700_B);
    }
}


