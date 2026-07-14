/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.BlockGetter;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.i_2154_H;

public final class G_4961_S
extends Enum<G_4961_S>
implements BlockGetter {
    public static final /* enum */ G_4961_S n_1700_B = new G_4961_S();
    private static final /* synthetic */ G_4961_S[] J_1907_R;

    public static G_4961_S[] values() {
        return (G_4961_S[])J_1907_R.clone();
    }

    public static G_4961_S valueOf(String name) {
        return Enum.valueOf(G_4961_S.class, name);
    }

    @Override
    @Nullable
    public i_2154_H getTileEntity(c_1514_x pos) {
        return null;
    }

    @Override
    public K_4074_S getBlockState(c_1514_x pos) {
        return a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
    }

    @Override
    public FluidState getFluidState(c_1514_x pos) {
        return Fluids.n_1700_B.w_1484_f();
    }

    private static /* synthetic */ G_4961_S[] n_1700_B() {
        return new G_4961_S[]{n_1700_B};
    }

    static {
        J_1907_R = G_4961_S.n_1700_B();
    }
}


