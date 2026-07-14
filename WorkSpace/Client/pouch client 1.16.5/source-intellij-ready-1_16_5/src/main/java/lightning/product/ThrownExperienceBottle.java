/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.HitResult;
import lightning.product.L_1875_m;
import lightning.product.Potions;
import lightning.product.ThrowableItemProjectile;
import lightning.product.b_4507_u;
import lightning.product.n_4637_L;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;

public class ThrownExperienceBottle
extends ThrowableItemProjectile {
    public ThrownExperienceBottle(t_5_h<? extends ThrownExperienceBottle> p_i50152_1_, b_4507_u world) {
        super((t_5_h<? extends ThrowableItemProjectile>)p_i50152_1_, world);
    }

    public ThrownExperienceBottle(b_4507_u worldIn, r_4811_B throwerIn) {
        super((t_5_h<? extends ThrowableItemProjectile>)t_5_h.H_1083_k, throwerIn, worldIn);
    }

    public ThrownExperienceBottle(b_4507_u worldIn, double x, double y, double z) {
        super((t_5_h<? extends ThrowableItemProjectile>)t_5_h.H_1083_k, x, y, z, worldIn);
    }

    @Override
    protected q_1613_l P_1922_E() {
        return Items.s_3084_y;
    }

    @Override
    protected float u_1723_Y() {
        return 0.07f;
    }

    @Override
    protected void n_1700_B(HitResult result) {
        super.n_1700_B(result);
        if (!this.O_508_d.Y_259_p) {
            int j;
            this.O_508_d.R_4764_Y(2002, this.b_2312_j(), L_1875_m.n_1700_B(Potions.J_1907_R));
            for (int i = 3 + this.O_508_d.w_1457_N.nextInt(5) + this.O_508_d.w_1457_N.nextInt(5); i > 0; i -= j) {
                j = n_4637_L.n_1700_B(i);
                this.O_508_d.a_(new n_4637_L(this.O_508_d, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), j));
            }
            this.Ops();
        }
    }
}


