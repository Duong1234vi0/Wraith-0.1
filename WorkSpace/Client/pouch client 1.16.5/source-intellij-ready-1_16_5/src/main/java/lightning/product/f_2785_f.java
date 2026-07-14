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
import lightning.product.P_11_z;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.e_2866_D;
import lightning.product.r_4811_B;
import lightning.product.x_282_a;

public class f_2785_f
extends P_11_z {
    @Nullable
    protected final N_4263_v C_2741_M;
    private boolean k_2293_S;

    public f_2785_f(String damageTypeIn, @Nullable N_4263_v damageSourceEntityIn) {
        super(damageTypeIn);
        this.C_2741_M = damageSourceEntityIn;
    }

    public f_2785_f k_2293_S() {
        this.k_2293_S = true;
        return this;
    }

    public boolean q_2307_F() {
        return this.k_2293_S;
    }

    @Override
    @Nullable
    public N_4263_v u_2550_I() {
        return this.C_2741_M;
    }

    @Override
    public x_282_a n_1700_B(r_4811_B entityLivingBaseIn) {
        Z_1993_T itemstack = this.C_2741_M instanceof r_4811_B ? ((r_4811_B)this.C_2741_M).A_2714_y() : Z_1993_T.J_1907_R;
        String s = "death.attack." + this.Q_2552_b;
        return !itemstack.n_1700_B() && itemstack.Y_601_j() ? new F_2904_S(s + ".item", entityLivingBaseIn.c_(), this.C_2741_M.c_(), itemstack.A_4115_X()) : new F_2904_S(s, entityLivingBaseIn.c_(), this.C_2741_M.c_());
    }

    @Override
    public boolean w_1457_N() {
        return this.C_2741_M != null && this.C_2741_M instanceof r_4811_B && !(this.C_2741_M instanceof a_3913_L);
    }

    @Override
    @Nullable
    public e_2866_D C_2741_M() {
        return this.C_2741_M != null ? this.C_2741_M.s_4990_V() : null;
    }

    @Override
    public String toString() {
        return "EntityDamageSource (" + String.valueOf(this.C_2741_M) + ")";
    }
}

