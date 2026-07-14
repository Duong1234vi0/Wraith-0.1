/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  it.unimi.dsi.fastutil.objects.Object2IntMap$Entry
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 */
package lightning.product;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import lightning.product.U_2912_j;
import lightning.product.SavedData;

public class MapIndex
extends SavedData {
    private final Object2IntMap<String> n_1700_B = new Object2IntOpenHashMap();

    public MapIndex() {
        super("idcounts");
        this.n_1700_B.defaultReturnValue(-1);
    }

    @Override
    public void n_1700_B(U_2912_j nbt) {
        this.n_1700_B.clear();
        for (String s : nbt.G_564_y()) {
            if (!nbt.R_4764_Y(s, 99)) continue;
            this.n_1700_B.put((Object)s, nbt.w_1484_f(s));
        }
    }

    @Override
    public U_2912_j R_4764_Y(U_2912_j compound) {
        for (Object2IntMap.Entry entry : this.n_1700_B.object2IntEntrySet()) {
            compound.J_1907_R((String)entry.getKey(), entry.getIntValue());
        }
        return compound;
    }

    public int n_1700_B() {
        int i = this.n_1700_B.getInt((Object)"map") + 1;
        this.n_1700_B.put((Object)"map", i);
        this.R_4764_Y();
        return i;
    }
}


