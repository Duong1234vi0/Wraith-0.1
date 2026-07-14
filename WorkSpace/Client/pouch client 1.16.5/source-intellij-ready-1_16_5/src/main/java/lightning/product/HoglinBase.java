/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Attributes;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.e_2866_D;
import lightning.product.r_4811_B;

public interface HoglinBase {
    public int h_1640_b();

    public static boolean n_1700_B(r_4811_B p_234403_0_, r_4811_B p_234403_1_) {
        float f1 = (float)p_234403_0_.J_1907_R(Attributes.u_1723_Y);
        float f = !p_234403_0_.d_() && (int)f1 > 0 ? f1 / 2.0f + (float)p_234403_0_.O_508_d.w_1457_N.nextInt((int)f1) : f1;
        boolean flag = p_234403_1_.n_1700_B(P_11_z.R_4764_Y(p_234403_0_), f);
        if (flag) {
            p_234403_0_.n_1700_B(p_234403_0_, (N_4263_v)p_234403_1_);
            if (!p_234403_0_.d_()) {
                HoglinBase.J_1907_R(p_234403_0_, p_234403_1_);
            }
        }
        return flag;
    }

    public static void J_1907_R(r_4811_B p_234404_0_, r_4811_B p_234404_1_) {
        double d1;
        double d0 = p_234404_0_.J_1907_R(Attributes.v_4262_N);
        double d2 = d0 - (d1 = p_234404_1_.J_1907_R(Attributes.R_4764_Y));
        if (!(d2 <= 0.0)) {
            double d3 = p_234404_1_.O_3598_v() - p_234404_0_.O_3598_v();
            double d4 = p_234404_1_.l_2647_k() - p_234404_0_.l_2647_k();
            float f = p_234404_0_.O_508_d.w_1457_N.nextInt(21) - 10;
            double d5 = d2 * (double)(p_234404_0_.O_508_d.w_1457_N.nextFloat() * 0.5f + 0.2f);
            e_2866_D vector3d = new e_2866_D(d3, 0.0, d4).G_564_y().n_1700_B(d5).J_1907_R(f);
            double d6 = d2 * (double)p_234404_0_.O_508_d.w_1457_N.nextFloat() * 0.5;
            p_234404_1_.w_1484_f(vector3d.J_1907_R, d6, vector3d.G_564_y);
            p_234404_1_.Ops = true;
        }
    }
}


