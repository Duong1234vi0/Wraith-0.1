/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.DefaultDispenseItemBehavior;
import lightning.product.BlockSource;

public abstract class OptionalDispenseItemBehavior
extends DefaultDispenseItemBehavior {
    private boolean J_1907_R = true;

    public boolean J_1907_R() {
        return this.J_1907_R;
    }

    public void n_1700_B(boolean success) {
        this.J_1907_R = success;
    }

    @Override
    protected void n_1700_B(BlockSource source) {
        source.v_4262_N().R_4764_Y(this.J_1907_R() ? 1000 : 1001, source.G_564_y(), 0);
    }
}


