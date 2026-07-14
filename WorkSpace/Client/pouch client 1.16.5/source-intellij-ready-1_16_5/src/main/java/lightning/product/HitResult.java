/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.e_2866_D;

public abstract class HitResult {
    protected final e_2866_D n_1700_B;

    protected HitResult(e_2866_D hitVec) {
        this.n_1700_B = hitVec;
    }

    public double n_1700_B(N_4263_v p_237486_1_) {
        double d0 = this.n_1700_B.J_1907_R - p_237486_1_.O_3598_v();
        double d1 = this.n_1700_B.R_4764_Y - p_237486_1_.X_2960_b();
        double d2 = this.n_1700_B.G_564_y - p_237486_1_.l_2647_k();
        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    public abstract n_1700_B R_4764_Y();

    public e_2866_D P_1922_E() {
        return this.n_1700_B;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] G_564_y;

        public static n_1700_B[] values() {
            return (n_1700_B[])G_564_y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.HitResult$n_1700_B.n_1700_B();
        }
    }
}


