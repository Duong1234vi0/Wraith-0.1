/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.util.Pair
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import java.util.List;
import lightning.product.k_2610_C;

public class FoodProperties {
    private final int n_1700_B;
    private final float J_1907_R;
    private final boolean R_4764_Y;
    private final boolean G_564_y;
    private final boolean P_1922_E;
    private final List<Pair<k_2610_C, Float>> u_1723_Y;

    private FoodProperties(int healing, float saturationIn, boolean isMeat, boolean alwaysEdible, boolean fastEdible, List<Pair<k_2610_C, Float>> effectsIn) {
        this.n_1700_B = healing;
        this.J_1907_R = saturationIn;
        this.R_4764_Y = isMeat;
        this.G_564_y = alwaysEdible;
        this.P_1922_E = fastEdible;
        this.u_1723_Y = effectsIn;
    }

    public int n_1700_B() {
        return this.n_1700_B;
    }

    public float J_1907_R() {
        return this.J_1907_R;
    }

    public boolean R_4764_Y() {
        return this.R_4764_Y;
    }

    public boolean G_564_y() {
        return this.G_564_y;
    }

    public boolean P_1922_E() {
        return this.P_1922_E;
    }

    public List<Pair<k_2610_C, Float>> u_1723_Y() {
        return this.u_1723_Y;
    }

    public static class n_1700_B {
        private int n_1700_B;
        private float J_1907_R;
        private boolean R_4764_Y;
        private boolean G_564_y;
        private boolean P_1922_E;
        private final List<Pair<k_2610_C, Float>> u_1723_Y = Lists.newArrayList();

        public n_1700_B n_1700_B(int hungerIn) {
            this.n_1700_B = hungerIn;
            return this;
        }

        public n_1700_B n_1700_B(float saturationIn) {
            this.J_1907_R = saturationIn;
            return this;
        }

        public n_1700_B n_1700_B() {
            this.R_4764_Y = true;
            return this;
        }

        public n_1700_B J_1907_R() {
            this.G_564_y = true;
            return this;
        }

        public n_1700_B R_4764_Y() {
            this.P_1922_E = true;
            return this;
        }

        public n_1700_B n_1700_B(k_2610_C effectIn, float probability) {
            this.u_1723_Y.add((Pair<k_2610_C, Float>)Pair.of((Object)effectIn, (Object)Float.valueOf(probability)));
            return this;
        }

        public FoodProperties G_564_y() {
            return new FoodProperties(this.n_1700_B, this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y);
        }
    }
}


