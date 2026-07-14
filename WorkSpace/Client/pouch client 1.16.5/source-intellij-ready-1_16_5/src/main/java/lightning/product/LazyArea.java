/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.Long2IntLinkedOpenHashMap
 */
package lightning.product;

import it.unimi.dsi.fastutil.longs.Long2IntLinkedOpenHashMap;
import lightning.product.Y_1387_d;
import lightning.product.s_1037_T;
import lightning.product.t_4013_W;

public final class LazyArea
implements t_4013_W {
    private final s_1037_T n_1700_B;
    private final Long2IntLinkedOpenHashMap J_1907_R;
    private final int R_4764_Y;

    public LazyArea(Long2IntLinkedOpenHashMap cachedValues, int maxCacheSize, s_1037_T pixelTransformer) {
        this.J_1907_R = cachedValues;
        this.R_4764_Y = maxCacheSize;
        this.n_1700_B = pixelTransformer;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int n_1700_B(int x, int z) {
        long i = Y_1387_d.n_1700_B(x, z);
        Long2IntLinkedOpenHashMap long2IntLinkedOpenHashMap = this.J_1907_R;
        synchronized (long2IntLinkedOpenHashMap) {
            int j = this.J_1907_R.get(i);
            if (j != Integer.MIN_VALUE) {
                return j;
            }
            int k = this.n_1700_B.apply(x, z);
            this.J_1907_R.put(i, k);
            if (this.J_1907_R.size() > this.R_4764_Y) {
                for (int l = 0; l < this.R_4764_Y / 16; ++l) {
                    this.J_1907_R.removeFirstInt();
                }
            }
            return k;
        }
    }

    public int n_1700_B() {
        return this.R_4764_Y;
    }
}


