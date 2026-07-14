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

public class StructureFeatureIndexSavedData
extends SavedData {
    private LongSet n_1700_B = new LongOpenHashSet();
    private LongSet J_1907_R = new LongOpenHashSet();

    public StructureFeatureIndexSavedData(String p_i48654_1_) {
        super(p_i48654_1_);
    }

    @Override
    public void n_1700_B(U_2912_j nbt) {
        this.n_1700_B = new LongOpenHashSet(nbt.Q_4569_t("All"));
        this.J_1907_R = new LongOpenHashSet(nbt.Q_4569_t("Remaining"));
    }

    @Override
    public U_2912_j R_4764_Y(U_2912_j compound) {
        compound.n_1700_B("All", this.n_1700_B.toLongArray());
        compound.n_1700_B("Remaining", this.J_1907_R.toLongArray());
        return compound;
    }

    public void n_1700_B(long p_201763_1_) {
        this.n_1700_B.add(p_201763_1_);
        this.J_1907_R.add(p_201763_1_);
    }

    public boolean J_1907_R(long p_208024_1_) {
        return this.n_1700_B.contains(p_208024_1_);
    }

    public boolean R_4764_Y(long p_208023_1_) {
        return this.J_1907_R.contains(p_208023_1_);
    }

    public void G_564_y(long p_201762_1_) {
        this.J_1907_R.remove(p_201762_1_);
    }

    public LongSet n_1700_B() {
        return this.n_1700_B;
    }
}


