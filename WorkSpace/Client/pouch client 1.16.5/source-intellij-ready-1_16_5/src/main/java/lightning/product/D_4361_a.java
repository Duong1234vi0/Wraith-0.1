/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public class D_4361_a {
    public final int n_1700_B;
    public final String J_1907_R;

    private D_4361_a(int p_i51746_1_, String p_i51746_2_) {
        this.n_1700_B = p_i51746_1_;
        this.J_1907_R = p_i51746_2_;
    }

    public static class n_1700_B {
        private int n_1700_B = -1;
        private String J_1907_R;

        public n_1700_B n_1700_B(int p_225175_1_) {
            this.n_1700_B = p_225175_1_;
            return this;
        }

        public n_1700_B n_1700_B(String p_225176_1_) {
            this.J_1907_R = p_225176_1_;
            return this;
        }

        public D_4361_a n_1700_B() {
            return new D_4361_a(this.n_1700_B, this.J_1907_R);
        }
    }
}

