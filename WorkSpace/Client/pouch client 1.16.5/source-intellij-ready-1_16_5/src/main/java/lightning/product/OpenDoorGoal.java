/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Z_530_i;
import lightning.product.c_1219_i;

public class OpenDoorGoal
extends c_1219_i {
    private final boolean n_1700_B;
    private int J_1907_R;

    public OpenDoorGoal(Z_530_i entitylivingIn, boolean shouldClose) {
        super(entitylivingIn);
        this.G_564_y = entitylivingIn;
        this.n_1700_B = shouldClose;
    }

    @Override
    public boolean J_1907_R() {
        return this.n_1700_B && this.J_1907_R > 0 && super.J_1907_R();
    }

    @Override
    public void R_4764_Y() {
        this.J_1907_R = 20;
        this.n_1700_B(true);
    }

    @Override
    public void G_564_y() {
        this.n_1700_B(false);
    }

    @Override
    public void P_1922_E() {
        --this.J_1907_R;
        super.P_1922_E();
    }
}


