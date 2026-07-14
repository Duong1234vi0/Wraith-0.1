/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.LongOpenHashSet
 *  it.unimi.dsi.fastutil.longs.LongSet
 */
package lightning.product;

import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import lightning.product.U_2912_j;
import lightning.product.SavedData;

public class ForcedChunksSavedData
extends SavedData {
    private LongSet n_1700_B = new LongOpenHashSet();

    public ForcedChunksSavedData() {
        super("chunks");
    }

    @Override
    public void n_1700_B(U_2912_j nbt) {
        this.n_1700_B = new LongOpenHashSet(nbt.Q_4569_t("Forced"));
    }

    @Override
    public U_2912_j R_4764_Y(U_2912_j compound) {
        compound.n_1700_B("Forced", this.n_1700_B.toLongArray());
        return compound;
    }

    public LongSet n_1700_B() {
        return this.n_1700_B;
    }
}


