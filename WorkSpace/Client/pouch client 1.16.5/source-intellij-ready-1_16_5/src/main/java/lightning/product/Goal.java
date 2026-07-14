/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;

public abstract class Goal {
    private final EnumSet<n_1700_B> n_1700_B = EnumSet.noneOf(n_1700_B.class);

    public abstract boolean n_1700_B();

    public boolean J_1907_R() {
        return this.n_1700_B();
    }

    public boolean r_() {
        return true;
    }

    public void R_4764_Y() {
    }

    public void G_564_y() {
    }

    public void P_1922_E() {
    }

    public void n_1700_B(EnumSet<n_1700_B> flagSet) {
        this.n_1700_B.clear();
        this.n_1700_B.addAll(flagSet);
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }

    public EnumSet<n_1700_B> t_148_a() {
        return this.n_1700_B;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] P_1922_E;

        public static n_1700_B[] values() {
            return (n_1700_B[])P_1922_E.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            P_1922_E = lightning.product.Goal$n_1700_B.n_1700_B();
        }
    }
}


