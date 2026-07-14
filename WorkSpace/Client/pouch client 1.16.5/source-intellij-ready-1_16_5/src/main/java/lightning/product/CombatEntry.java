/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.P_11_z;
import lightning.product.r_4811_B;
import lightning.product.x_282_a;

public class CombatEntry {
    private final P_11_z n_1700_B;
    private final int J_1907_R;
    private final float R_4764_Y;
    private final float G_564_y;
    private final String P_1922_E;
    private final float u_1723_Y;

    public CombatEntry(P_11_z damageSrcIn, int timeIn, float healthAmount, float damageAmount, String fallSuffixIn, float fallDistanceIn) {
        this.n_1700_B = damageSrcIn;
        this.J_1907_R = timeIn;
        this.R_4764_Y = damageAmount;
        this.G_564_y = healthAmount;
        this.P_1922_E = fallSuffixIn;
        this.u_1723_Y = fallDistanceIn;
    }

    public P_11_z n_1700_B() {
        return this.n_1700_B;
    }

    public float J_1907_R() {
        return this.R_4764_Y;
    }

    public boolean R_4764_Y() {
        return this.n_1700_B.u_2550_I() instanceof r_4811_B;
    }

    @Nullable
    public String G_564_y() {
        return this.P_1922_E;
    }

    @Nullable
    public x_282_a P_1922_E() {
        return this.n_1700_B().u_2550_I() == null ? null : this.n_1700_B().u_2550_I().c_();
    }

    public float u_1723_Y() {
        return this.n_1700_B == P_11_z.P_4830_p ? Float.MAX_VALUE : this.u_1723_Y;
    }
}


