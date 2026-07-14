/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  it.unimi.dsi.fastutil.objects.Object2IntMaps
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 */
package lightning.product;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMaps;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import lightning.product.a_3913_L;
import lightning.product.o_98_P;
import lightning.product.q_3277_O;

public class StatsCounter {
    protected final Object2IntMap<o_98_P<?>> n_1700_B = Object2IntMaps.synchronize((Object2IntMap)new Object2IntOpenHashMap());

    public StatsCounter() {
        this.n_1700_B.defaultReturnValue(0);
    }

    public void J_1907_R(a_3913_L player, o_98_P<?> stat, int amount) {
        int i = (int)Math.min((long)this.n_1700_B(stat) + (long)amount, Integer.MAX_VALUE);
        this.n_1700_B(player, stat, i);
    }

    public void n_1700_B(a_3913_L playerIn, o_98_P<?> statIn, int p_150873_3_) {
        this.n_1700_B.put(statIn, p_150873_3_);
    }

    public <T> int n_1700_B(q_3277_O<T> p_199060_1_, T p_199060_2_) {
        return p_199060_1_.n_1700_B(p_199060_2_) ? this.n_1700_B(p_199060_1_.J_1907_R(p_199060_2_)) : 0;
    }

    public int n_1700_B(o_98_P<?> stat) {
        return this.n_1700_B.getInt(stat);
    }
}


