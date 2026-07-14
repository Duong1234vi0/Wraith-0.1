/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.i_2154_H;

public final class NoiseColumn
implements BlockGetter {
    private final K_4074_S[] n_1700_B;

    public NoiseColumn(K_4074_S[] states) {
        this.n_1700_B = states;
    }

    @Override
    @Nullable
    public i_2154_H getTileEntity(c_1514_x pos) {
        return null;
    }

    @Override
    public K_4074_S getBlockState(c_1514_x pos) {
        int i = pos.getY();
        return i >= 0 && i < this.n_1700_B.length ? this.n_1700_B[i] : a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
    }

    @Override
    public FluidState getFluidState(c_1514_x pos) {
        return this.getBlockState(pos).P_4830_p();
    }
}


