/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Iterables
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import lightning.product.D_3833_N;
import lightning.product.e_4189_z;
import lightning.product.k_4231_L;
import lightning.product.n_1658_l;
import lightning.product.u_530_F;

public class VexModel
extends n_1658_l<D_3833_N> {
    private final e_4189_z M_588_G;
    private final e_4189_z P_4830_p;

    public VexModel() {
        super(0.0f, 0.0f, 64, 64);
        this.v_4262_N.s_956_w = false;
        this.J_1907_R.s_956_w = false;
        this.u_1723_Y = new e_4189_z(this, 32, 0);
        this.u_1723_Y.n_1700_B(-1.0f, -1.0f, -2.0f, 6.0f, 10.0f, 4.0f, 0.0f);
        this.u_1723_Y.n_1700_B(-1.9f, 12.0f, 0.0f);
        this.P_4830_p = new e_4189_z(this, 0, 32);
        this.P_4830_p.n_1700_B(-20.0f, 0.0f, 0.0f, 20.0f, 12.0f, 1.0f);
        this.M_588_G = new e_4189_z(this, 0, 32);
        this.M_588_G.t_148_a = true;
        this.M_588_G.n_1700_B(0.0f, 0.0f, 0.0f, 20.0f, 12.0f, 1.0f);
    }

    @Override
    protected Iterable<e_4189_z> J_1907_R() {
        return Iterables.concat(super.J_1907_R(), (Iterable)ImmutableList.of((Object)this.P_4830_p, (Object)this.M_588_G));
    }

    @Override
    public void n_1700_B(D_3833_N entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        if (entityIn.y_2447_C()) {
            if (entityIn.A_2714_y().n_1700_B()) {
                this.G_564_y.u_1723_Y = 4.712389f;
                this.P_1922_E.u_1723_Y = 4.712389f;
            } else if (entityIn.d_2169_p() == k_4231_L.J_1907_R) {
                this.G_564_y.u_1723_Y = 3.7699115f;
            } else {
                this.P_1922_E.u_1723_Y = 3.7699115f;
            }
        }
        this.u_1723_Y.u_1723_Y += 0.62831855f;
        this.P_4830_p.P_1922_E = 2.0f;
        this.M_588_G.P_1922_E = 2.0f;
        this.P_4830_p.G_564_y = 1.0f;
        this.M_588_G.G_564_y = 1.0f;
        this.P_4830_p.v_4262_N = 0.47123894f + u_530_F.J_1907_R(ageInTicks * 0.8f) * (float)Math.PI * 0.05f;
        this.M_588_G.v_4262_N = -this.P_4830_p.v_4262_N;
        this.M_588_G.w_1484_f = -0.47123894f;
        this.M_588_G.u_1723_Y = 0.47123894f;
        this.P_4830_p.u_1723_Y = 0.47123894f;
        this.P_4830_p.w_1484_f = 0.47123894f;
    }
}


