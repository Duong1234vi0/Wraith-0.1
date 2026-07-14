/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public interface g_3316_o {
    public boolean n_1700_B();

    public static final class n_1700_B
    extends Enum<n_1700_B>
    implements g_3316_o {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B(false);
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B(true);
        private final boolean R_4764_Y;
        private static final /* synthetic */ n_1700_B[] G_564_y;

        public static n_1700_B[] values() {
            return (n_1700_B[])G_564_y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(boolean advanced) {
            this.R_4764_Y = advanced;
        }

        @Override
        public boolean n_1700_B() {
            return this.R_4764_Y;
        }

        private static /* synthetic */ n_1700_B[] J_1907_R() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            G_564_y = lightning.product.g_3316_o$n_1700_B.J_1907_R();
        }
    }
}

