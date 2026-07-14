/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.U_2912_j;
import lightning.product.Z_1125_b;
import lightning.product.g_2336_b;

@FunctionalInterface
public interface TimerCallback<T> {
    public void n_1700_B(T var1, Z_1125_b<T> var2, long var3);

    public static abstract class n_1700_B<T, C extends TimerCallback<T>> {
        private final g_2336_b n_1700_B;
        private final Class<?> J_1907_R;

        public n_1700_B(g_2336_b p_i51270_1_, Class<?> p_i51270_2_) {
            this.n_1700_B = p_i51270_1_;
            this.J_1907_R = p_i51270_2_;
        }

        public g_2336_b n_1700_B() {
            return this.n_1700_B;
        }

        public Class<?> J_1907_R() {
            return this.J_1907_R;
        }

        public abstract void n_1700_B(U_2912_j var1, C var2);

        public abstract C n_1700_B(U_2912_j var1);
    }
}


