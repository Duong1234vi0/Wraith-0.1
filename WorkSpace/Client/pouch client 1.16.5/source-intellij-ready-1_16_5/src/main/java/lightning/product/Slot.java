/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.datafixers.util.Pair;
import javax.annotation.Nullable;
import lightning.product.Container;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.g_2336_b;

public class Slot {
    private final int n_1700_B;
    public final Container R_4764_Y;
    public int G_564_y;
    public final int P_1922_E;
    public final int u_1723_Y;

    public Slot(Container inventoryIn, int index, int xPosition, int yPosition) {
        this.R_4764_Y = inventoryIn;
        this.n_1700_B = index;
        this.P_1922_E = xPosition;
        this.u_1723_Y = yPosition;
    }

    public void n_1700_B(Z_1993_T oldStackIn, Z_1993_T newStackIn) {
        int i = newStackIn.t_4043_B() - oldStackIn.t_4043_B();
        if (i > 0) {
            this.n_1700_B(newStackIn, i);
        }
    }

    protected void n_1700_B(Z_1993_T stack, int amount) {
    }

    protected void J_1907_R(int numItemsCrafted) {
    }

    protected void b_(Z_1993_T stack) {
    }

    public Z_1993_T n_1700_B(a_3913_L thePlayer, Z_1993_T stack) {
        this.R_4764_Y();
        return stack;
    }

    public boolean n_1700_B(Z_1993_T stack) {
        return true;
    }

    public Z_1993_T n_1700_B() {
        return this.R_4764_Y.s_956_w(this.n_1700_B);
    }

    public boolean J_1907_R() {
        return !this.n_1700_B().n_1700_B();
    }

    public void J_1907_R(Z_1993_T stack) {
        this.R_4764_Y.J_1907_R(this.n_1700_B, stack);
        this.R_4764_Y();
    }

    public void R_4764_Y() {
        this.R_4764_Y.J_1907_R();
    }

    public int G_564_y() {
        return this.R_4764_Y.J_();
    }

    public int R_4764_Y(Z_1993_T stack) {
        return this.G_564_y();
    }

    @Nullable
    public Pair<g_2336_b, g_2336_b> P_1922_E() {
        return null;
    }

    public Z_1993_T n_1700_B(int amount) {
        return this.R_4764_Y.n_1700_B(this.n_1700_B, amount);
    }

    public boolean n_1700_B(a_3913_L playerIn) {
        return true;
    }

    public boolean u_1723_Y() {
        return true;
    }
}


