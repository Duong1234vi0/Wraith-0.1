/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.C_4114_x;
import lightning.product.U_2912_j;
import lightning.product.h_256_u;

public class ItemBasedSteering {
    private final C_4114_x G_564_y;
    private final h_256_u<Integer> P_1922_E;
    private final h_256_u<Boolean> u_1723_Y;
    public boolean n_1700_B;
    public int J_1907_R;
    public int R_4764_Y;

    public ItemBasedSteering(C_4114_x manager, h_256_u<Integer> boostTime, h_256_u<Boolean> saddled) {
        this.G_564_y = manager;
        this.P_1922_E = boostTime;
        this.u_1723_Y = saddled;
    }

    public void n_1700_B() {
        this.n_1700_B = true;
        this.J_1907_R = 0;
        this.R_4764_Y = this.G_564_y.n_1700_B(this.P_1922_E);
    }

    public boolean n_1700_B(Random rand) {
        if (this.n_1700_B) {
            return false;
        }
        this.n_1700_B = true;
        this.J_1907_R = 0;
        this.R_4764_Y = rand.nextInt(841) + 140;
        this.G_564_y.J_1907_R(this.P_1922_E, this.R_4764_Y);
        return true;
    }

    public void n_1700_B(U_2912_j nbt) {
        nbt.n_1700_B("Saddle", this.J_1907_R());
    }

    public void J_1907_R(U_2912_j nbt) {
        this.n_1700_B(nbt.t_1786_h("Saddle"));
    }

    public void n_1700_B(boolean saddled) {
        this.G_564_y.J_1907_R(this.u_1723_Y, saddled);
    }

    public boolean J_1907_R() {
        return this.G_564_y.n_1700_B(this.u_1723_Y);
    }
}


