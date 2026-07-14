/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.c_1514_x;

public interface LevelWriter {
    public boolean n_1700_B(c_1514_x var1, K_4074_S var2, int var3, int var4);

    default public boolean n_1700_B(c_1514_x pos, K_4074_S newState, int flags) {
        return this.n_1700_B(pos, newState, flags, 512);
    }

    public boolean n_1700_B(c_1514_x var1, boolean var2);

    default public boolean J_1907_R(c_1514_x pos, boolean dropBlock) {
        return this.n_1700_B(pos, dropBlock, null);
    }

    default public boolean n_1700_B(c_1514_x pos, boolean dropBlock, @Nullable N_4263_v entity) {
        return this.n_1700_B(pos, dropBlock, entity, 512);
    }

    public boolean n_1700_B(c_1514_x var1, boolean var2, @Nullable N_4263_v var3, int var4);

    default public boolean a_(N_4263_v entityIn) {
        return false;
    }
}


