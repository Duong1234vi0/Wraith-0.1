/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import lightning.product.ListModel;
import lightning.product.e_4189_z;
import lightning.product.m_1605_o;
import lightning.product.o_2576_A;
import lightning.product.u_530_F;

public class ShulkerModel<T extends m_1605_o>
extends ListModel<T> {
    private final e_4189_z n_1700_B;
    private final e_4189_z J_1907_R = new e_4189_z(64, 64, 0, 0);
    private final e_4189_z R_4764_Y;

    public ShulkerModel() {
        super(o_2576_A::P_1922_E);
        this.n_1700_B = new e_4189_z(64, 64, 0, 28);
        this.R_4764_Y = new e_4189_z(64, 64, 0, 52);
        this.J_1907_R.n_1700_B(-8.0f, -16.0f, -8.0f, 16.0f, 12.0f, 16.0f);
        this.J_1907_R.n_1700_B(0.0f, 24.0f, 0.0f);
        this.n_1700_B.n_1700_B(-8.0f, -8.0f, -8.0f, 16.0f, 8.0f, 16.0f);
        this.n_1700_B.n_1700_B(0.0f, 24.0f, 0.0f);
        this.R_4764_Y.n_1700_B(-3.0f, 0.0f, -3.0f, 6.0f, 6.0f, 6.0f);
        this.R_4764_Y.n_1700_B(0.0f, 12.0f, 0.0f);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = ageInTicks - (float)((m_1605_o)entityIn).RealmsWorldResetDto;
        float f1 = (0.5f + ((m_1605_o)entityIn).c_3005_b(f)) * (float)Math.PI;
        float f2 = -1.0f + u_530_F.n_1700_B(f1);
        float f3 = 0.0f;
        if (f1 > (float)Math.PI) {
            f3 = u_530_F.n_1700_B(ageInTicks * 0.1f) * 0.7f;
        }
        this.J_1907_R.n_1700_B(0.0f, 16.0f + u_530_F.n_1700_B(f1) * 8.0f + f3, 0.0f);
        this.J_1907_R.v_4262_N = ((m_1605_o)entityIn).c_3005_b(f) > 0.3f ? f2 * f2 * f2 * f2 * (float)Math.PI * 0.125f : 0.0f;
        this.R_4764_Y.u_1723_Y = headPitch * ((float)Math.PI / 180);
        this.R_4764_Y.v_4262_N = (((m_1605_o)entityIn).f_3449_S - 180.0f - ((m_1605_o)entityIn).C_1162_e) * ((float)Math.PI / 180);
    }

    @Override
    public Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.n_1700_B, (Object)this.J_1907_R);
    }

    public e_4189_z J_1907_R() {
        return this.n_1700_B;
    }

    public e_4189_z R_4764_Y() {
        return this.J_1907_R;
    }

    public e_4189_z G_564_y() {
        return this.R_4764_Y;
    }
}


