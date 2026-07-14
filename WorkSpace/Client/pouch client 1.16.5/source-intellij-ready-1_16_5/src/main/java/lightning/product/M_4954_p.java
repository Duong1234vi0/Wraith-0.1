/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.Projectile;
import lightning.product.H_2333_J;
import lightning.product.M_1336_P;
import lightning.product.SoundEvents;
import lightning.product.Z_1630_j;
import lightning.product.Z_1993_T;
import lightning.product.e_2866_D;
import lightning.product.RangedAttackMob;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.w_3785_E;
import lightning.product.x_1688_C;

public interface M_4954_p
extends RangedAttackMob {
    public void J_1907_R(boolean var1);

    public void n_1700_B(r_4811_B var1, Z_1993_T var2, Projectile var3, float var4);

    @Nullable
    public r_4811_B t_148_a();

    public void n_1700_B();

    default public void n_1700_B(r_4811_B p_234281_1_, float p_234281_2_) {
        x_1688_C hand = H_2333_J.n_1700_B(p_234281_1_, Items.V_2454_J);
        Z_1993_T itemstack = p_234281_1_.R_4764_Y(hand);
        if (p_234281_1_.n_1700_B(Items.V_2454_J)) {
            Z_1630_j.n_1700_B(p_234281_1_.O_508_d, p_234281_1_, hand, itemstack, p_234281_2_, 14 - p_234281_1_.O_508_d.x_607_J().n_1700_B() * 4);
        }
        this.n_1700_B();
    }

    default public void n_1700_B(r_4811_B p_234279_1_, r_4811_B p_234279_2_, Projectile p_234279_3_, float p_234279_4_, float p_234279_5_) {
        double d0 = p_234279_2_.O_3598_v() - p_234279_1_.O_3598_v();
        double d1 = p_234279_2_.l_2647_k() - p_234279_1_.l_2647_k();
        double d2 = u_530_F.n_1700_B(d0 * d0 + d1 * d1);
        double d3 = p_234279_2_.P_1922_E(0.3333333333333333) - p_234279_3_.X_2960_b() + d2 * (double)0.2f;
        M_1336_P vector3f = this.n_1700_B(p_234279_1_, new e_2866_D(d0, d3, d1), p_234279_4_);
        p_234279_3_.R_4764_Y(vector3f.n_1700_B(), vector3f.J_1907_R(), vector3f.R_4764_Y(), p_234279_5_, 14 - p_234279_1_.O_508_d.x_607_J().n_1700_B() * 4);
        p_234279_1_.n_1700_B(SoundEvents.H_1873_g, 1.0f, 1.0f / (p_234279_1_.M_3508_C().nextFloat() * 0.4f + 0.8f));
    }

    default public M_1336_P n_1700_B(r_4811_B p_234280_1_, e_2866_D p_234280_2_, float p_234280_3_) {
        e_2866_D vector3d = p_234280_2_.G_564_y();
        e_2866_D vector3d1 = vector3d.R_4764_Y(new e_2866_D(0.0, 1.0, 0.0));
        if (vector3d1.v_4262_N() <= 1.0E-7) {
            vector3d1 = vector3d.R_4764_Y(p_234280_1_.s_956_w(1.0f));
        }
        w_3785_E quaternion = new w_3785_E(new M_1336_P(vector3d1), 90.0f, true);
        M_1336_P vector3f = new M_1336_P(vector3d);
        vector3f.n_1700_B(quaternion);
        w_3785_E quaternion1 = new w_3785_E(vector3f, p_234280_3_, true);
        M_1336_P vector3f1 = new M_1336_P(vector3d);
        vector3f1.n_1700_B(quaternion1);
        return vector3f1;
    }
}


