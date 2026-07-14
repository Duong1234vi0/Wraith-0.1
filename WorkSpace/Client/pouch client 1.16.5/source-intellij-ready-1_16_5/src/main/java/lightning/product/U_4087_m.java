/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.d_2427_y;
import lightning.product.r_4811_B;
import lightning.product.x_607_J;
import lombok.Generated;

public class U_4087_m
extends d_2427_y
implements x_607_J {
    private final r_4811_B n_1700_B;
    private final N_4263_v J_1907_R;
    private final int R_4764_Y;
    private float G_564_y;

    public U_4087_m(r_4811_B user, N_4263_v attacker, int level, float damage) {
        this.n_1700_B = user;
        this.J_1907_R = attacker;
        this.R_4764_Y = level;
        this.G_564_y = damage;
    }

    @Generated
    public r_4811_B J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public N_4263_v R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public int G_564_y() {
        return this.R_4764_Y;
    }

    @Generated
    public float P_1922_E() {
        return this.G_564_y;
    }

    @Generated
    public void n_1700_B(float damage) {
        this.G_564_y = damage;
    }
}

