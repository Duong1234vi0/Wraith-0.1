/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.N_4263_v;
import lightning.product.Z_1993_T;
import lightning.product.f_2785_f;
import lightning.product.r_4811_B;
import lightning.product.x_282_a;

public class y_4711_y
extends f_2785_f {
    private final N_4263_v k_2293_S;

    public y_4711_y(String damageTypeIn, N_4263_v source, @Nullable N_4263_v indirectEntityIn) {
        super(damageTypeIn, source);
        this.k_2293_S = indirectEntityIn;
    }

    @Override
    @Nullable
    public N_4263_v s_956_w() {
        return this.C_2741_M;
    }

    @Override
    @Nullable
    public N_4263_v u_2550_I() {
        return this.k_2293_S;
    }

    @Override
    public x_282_a n_1700_B(r_4811_B entityLivingBaseIn) {
        x_282_a itextcomponent = this.k_2293_S == null ? this.C_2741_M.c_() : this.k_2293_S.c_();
        Z_1993_T itemstack = this.k_2293_S instanceof r_4811_B ? ((r_4811_B)this.k_2293_S).A_2714_y() : Z_1993_T.J_1907_R;
        String s = "death.attack." + this.Q_2552_b;
        String s1 = s + ".item";
        return !itemstack.n_1700_B() && itemstack.Y_601_j() ? new F_2904_S(s1, entityLivingBaseIn.c_(), itextcomponent, itemstack.A_4115_X()) : new F_2904_S(s, entityLivingBaseIn.c_(), itextcomponent);
    }
}

