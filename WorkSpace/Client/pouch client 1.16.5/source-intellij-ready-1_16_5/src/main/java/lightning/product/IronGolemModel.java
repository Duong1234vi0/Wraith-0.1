/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import lightning.product.D_2364_U;
import lightning.product.ListModel;
import lightning.product.e_4189_z;
import lightning.product.u_530_F;

public class IronGolemModel<T extends D_2364_U>
extends ListModel<T> {
    private final e_4189_z n_1700_B;
    private final e_4189_z J_1907_R;
    private final e_4189_z R_4764_Y;
    private final e_4189_z G_564_y;
    private final e_4189_z P_1922_E;
    private final e_4189_z u_1723_Y;

    public IronGolemModel() {
        int i = 128;
        int j = 128;
        this.n_1700_B = new e_4189_z(this).J_1907_R(128, 128);
        this.n_1700_B.n_1700_B(0.0f, -7.0f, -2.0f);
        this.n_1700_B.n_1700_B(0, 0).n_1700_B(-4.0f, -12.0f, -5.5f, 8.0f, 10.0f, 8.0f, 0.0f);
        this.n_1700_B.n_1700_B(24, 0).n_1700_B(-1.0f, -5.0f, -7.5f, 2.0f, 4.0f, 2.0f, 0.0f);
        this.J_1907_R = new e_4189_z(this).J_1907_R(128, 128);
        this.J_1907_R.n_1700_B(0.0f, -7.0f, 0.0f);
        this.J_1907_R.n_1700_B(0, 40).n_1700_B(-9.0f, -2.0f, -6.0f, 18.0f, 12.0f, 11.0f, 0.0f);
        this.J_1907_R.n_1700_B(0, 70).n_1700_B(-4.5f, 10.0f, -3.0f, 9.0f, 5.0f, 6.0f, 0.5f);
        this.R_4764_Y = new e_4189_z(this).J_1907_R(128, 128);
        this.R_4764_Y.n_1700_B(0.0f, -7.0f, 0.0f);
        this.R_4764_Y.n_1700_B(60, 21).n_1700_B(-13.0f, -2.5f, -3.0f, 4.0f, 30.0f, 6.0f, 0.0f);
        this.G_564_y = new e_4189_z(this).J_1907_R(128, 128);
        this.G_564_y.n_1700_B(0.0f, -7.0f, 0.0f);
        this.G_564_y.n_1700_B(60, 58).n_1700_B(9.0f, -2.5f, -3.0f, 4.0f, 30.0f, 6.0f, 0.0f);
        this.P_1922_E = new e_4189_z(this, 0, 22).J_1907_R(128, 128);
        this.P_1922_E.n_1700_B(-4.0f, 11.0f, 0.0f);
        this.P_1922_E.n_1700_B(37, 0).n_1700_B(-3.5f, -3.0f, -3.0f, 6.0f, 16.0f, 5.0f, 0.0f);
        this.u_1723_Y = new e_4189_z(this, 0, 22).J_1907_R(128, 128);
        this.u_1723_Y.t_148_a = true;
        this.u_1723_Y.n_1700_B(60, 0).n_1700_B(5.0f, 11.0f, 0.0f);
        this.u_1723_Y.n_1700_B(-3.5f, -3.0f, -3.0f, 6.0f, 16.0f, 5.0f, 0.0f);
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.n_1700_B, (Object)this.J_1907_R, (Object)this.P_1922_E, (Object)this.u_1723_Y, (Object)this.R_4764_Y, (Object)this.G_564_y);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.n_1700_B.v_4262_N = netHeadYaw * ((float)Math.PI / 180);
        this.n_1700_B.u_1723_Y = headPitch * ((float)Math.PI / 180);
        this.P_1922_E.u_1723_Y = -1.5f * u_530_F.P_1922_E(limbSwing, 13.0f) * limbSwingAmount;
        this.u_1723_Y.u_1723_Y = 1.5f * u_530_F.P_1922_E(limbSwing, 13.0f) * limbSwingAmount;
        this.P_1922_E.v_4262_N = 0.0f;
        this.u_1723_Y.v_4262_N = 0.0f;
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        int i = ((D_2364_U)entityIn).V_1176_p();
        if (i > 0) {
            this.R_4764_Y.u_1723_Y = -2.0f + 1.5f * u_530_F.P_1922_E((float)i - partialTick, 10.0f);
            this.G_564_y.u_1723_Y = -2.0f + 1.5f * u_530_F.P_1922_E((float)i - partialTick, 10.0f);
        } else {
            int j = ((D_2364_U)entityIn).y_2447_C();
            if (j > 0) {
                this.R_4764_Y.u_1723_Y = -0.8f + 0.025f * u_530_F.P_1922_E(j, 70.0f);
                this.G_564_y.u_1723_Y = 0.0f;
            } else {
                this.R_4764_Y.u_1723_Y = (-0.2f + 1.5f * u_530_F.P_1922_E(limbSwing, 13.0f)) * limbSwingAmount;
                this.G_564_y.u_1723_Y = (-0.2f - 1.5f * u_530_F.P_1922_E(limbSwing, 13.0f)) * limbSwingAmount;
            }
        }
    }

    public e_4189_z J_1907_R() {
        return this.R_4764_Y;
    }
}


