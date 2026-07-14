/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.gson.JsonObject;
import lightning.product.A_2629_w;
import lightning.product.S_4998_h;
import lightning.product.g_2336_b;
import lightning.product.h_1723_G;
import lightning.product.DeserializationContext;

public interface CriterionTrigger<T extends h_1723_G> {
    public g_2336_b n_1700_B();

    public void n_1700_B(S_4998_h var1, n_1700_B<T> var2);

    public void J_1907_R(S_4998_h var1, n_1700_B<T> var2);

    public void n_1700_B(S_4998_h var1);

    public T n_1700_B(JsonObject var1, DeserializationContext var2);

    public static class n_1700_B<T extends h_1723_G> {
        private final T n_1700_B;
        private final A_2629_w J_1907_R;
        private final String R_4764_Y;

        public n_1700_B(T criterionInstanceIn, A_2629_w advancementIn, String criterionNameIn) {
            this.n_1700_B = criterionInstanceIn;
            this.J_1907_R = advancementIn;
            this.R_4764_Y = criterionNameIn;
        }

        public T n_1700_B() {
            return this.n_1700_B;
        }

        public void n_1700_B(S_4998_h playerAdvancementsIn) {
            playerAdvancementsIn.n_1700_B(this.J_1907_R, this.R_4764_Y);
        }

        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
                n_1700_B listener = (n_1700_B)p_equals_1_;
                if (!this.n_1700_B.equals(listener.n_1700_B)) {
                    return false;
                }
                return !this.J_1907_R.equals(listener.J_1907_R) ? false : this.R_4764_Y.equals(listener.R_4764_Y);
            }
            return false;
        }

        public int hashCode() {
            int i = this.n_1700_B.hashCode();
            i = 31 * i + this.J_1907_R.hashCode();
            return 31 * i + this.R_4764_Y.hashCode();
        }
    }
}


