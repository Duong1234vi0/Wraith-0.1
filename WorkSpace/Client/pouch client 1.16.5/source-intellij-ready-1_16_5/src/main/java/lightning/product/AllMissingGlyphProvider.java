/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.IntSet
 *  it.unimi.dsi.fastutil.ints.IntSets
 *  javax.annotation.Nullable
 */
package lightning.product;

import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.ints.IntSets;
import javax.annotation.Nullable;
import lightning.product.c_4447_Z;
import lightning.product.e_3495_r;
import lightning.product.s_3940_w;

public class AllMissingGlyphProvider
implements e_3495_r {
    @Override
    @Nullable
    public s_3940_w n_1700_B(int character) {
        return c_4447_Z.n_1700_B;
    }

    @Override
    public IntSet n_1700_B() {
        return IntSets.EMPTY_SET;
    }
}


