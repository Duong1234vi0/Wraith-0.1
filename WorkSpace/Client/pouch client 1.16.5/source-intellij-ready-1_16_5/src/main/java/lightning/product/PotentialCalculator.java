/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import lightning.product.c_1514_x;

public class PotentialCalculator {
    private final List<n_1700_B> n_1700_B = Lists.newArrayList();

    public void n_1700_B(c_1514_x p_234998_1_, double p_234998_2_) {
        if (p_234998_2_ != 0.0) {
            this.n_1700_B.add(new n_1700_B(p_234998_1_, p_234998_2_));
        }
    }

    public double J_1907_R(c_1514_x p_234999_1_, double p_234999_2_) {
        if (p_234999_2_ == 0.0) {
            return 0.0;
        }
        double d0 = 0.0;
        for (n_1700_B mobdensitytracker$densityentry : this.n_1700_B) {
            d0 += mobdensitytracker$densityentry.n_1700_B(p_234999_1_);
        }
        return d0 * p_234999_2_;
    }

    static class n_1700_B {
        private final c_1514_x n_1700_B;
        private final double J_1907_R;

        public n_1700_B(c_1514_x p_i231624_1_, double p_i231624_2_) {
            this.n_1700_B = p_i231624_1_;
            this.J_1907_R = p_i231624_2_;
        }

        public double n_1700_B(c_1514_x p_235002_1_) {
            double d0 = this.n_1700_B.distanceSq(p_235002_1_);
            return d0 == 0.0 ? Double.POSITIVE_INFINITY : this.J_1907_R / Math.sqrt(d0);
        }
    }
}


