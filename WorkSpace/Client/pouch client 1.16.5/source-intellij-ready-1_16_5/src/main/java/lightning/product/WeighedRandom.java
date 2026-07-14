/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import java.util.Random;
import lightning.product.j_3341_s;

public class WeighedRandom {
    public static int n_1700_B(List<? extends n_1700_B> collection) {
        int i = 0;
        int k = collection.size();
        for (int j = 0; j < k; ++j) {
            n_1700_B weightedrandom$item = collection.get(j);
            i += weightedrandom$item.R_4764_Y;
        }
        return i;
    }

    public static <T extends n_1700_B> T n_1700_B(Random random, List<T> collection, int totalWeight) {
        if (totalWeight <= 0) {
            throw j_3341_s.R_4764_Y(new IllegalArgumentException());
        }
        int i = random.nextInt(totalWeight);
        return WeighedRandom.n_1700_B(collection, i);
    }

    public static <T extends n_1700_B> T n_1700_B(List<T> collection, int weight) {
        int j = collection.size();
        for (int i = 0; i < j; ++i) {
            n_1700_B t = (n_1700_B)collection.get(i);
            if ((weight -= t.R_4764_Y) >= 0) continue;
            return (T)t;
        }
        return (T)((n_1700_B)null);
    }

    public static <T extends n_1700_B> T n_1700_B(Random random, List<T> collection) {
        return WeighedRandom.n_1700_B(random, collection, WeighedRandom.n_1700_B(collection));
    }

    public static class n_1700_B {
        protected final int R_4764_Y;

        public n_1700_B(int itemWeightIn) {
            this.R_4764_Y = itemWeightIn;
        }
    }
}


