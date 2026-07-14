/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.V_4170_D;
import lightning.product.c_1325_f;
import lightning.product.C0Transformer;
import lightning.product.Context;

public class E_2648_v {

    public static final class R_4764_Y
    extends Enum<R_4764_Y>
    implements C0Transformer {
        public static final /* enum */ R_4764_Y n_1700_B = new R_4764_Y();
        private static final /* synthetic */ R_4764_Y[] J_1907_R;

        public static R_4764_Y[] values() {
            return (R_4764_Y[])J_1907_R.clone();
        }

        public static R_4764_Y valueOf(String name) {
            return Enum.valueOf(R_4764_Y.class, name);
        }

        @Override
        public int n_1700_B(Context context, int value) {
            if (!V_4170_D.J_1907_R(value) && context.n_1700_B(13) == 0) {
                value |= 1 + context.n_1700_B(15) << 8 & 0xF00;
            }
            return value;
        }

        private static /* synthetic */ R_4764_Y[] n_1700_B() {
            return new R_4764_Y[]{n_1700_B};
        }

        static {
            J_1907_R = R_4764_Y.n_1700_B();
        }
    }

    public static final class J_1907_R
    extends Enum<J_1907_R>
    implements c_1325_f {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R();
        private static final /* synthetic */ J_1907_R[] J_1907_R;

        public static J_1907_R[] values() {
            return (J_1907_R[])J_1907_R.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        @Override
        public int n_1700_B(Context context, int north, int west, int south, int east, int center) {
            return center != 4 || north != 1 && west != 1 && east != 1 && south != 1 && north != 2 && west != 2 && east != 2 && south != 2 ? center : 3;
        }

        private static /* synthetic */ J_1907_R[] n_1700_B() {
            return new J_1907_R[]{n_1700_B};
        }

        static {
            J_1907_R = lightning.product.E_2648_v$J_1907_R.n_1700_B();
        }
    }

    public static final class n_1700_B
    extends Enum<n_1700_B>
    implements c_1325_f {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] J_1907_R;

        public static n_1700_B[] values() {
            return (n_1700_B[])J_1907_R.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        @Override
        public int n_1700_B(Context context, int north, int west, int south, int east, int center) {
            return center != 1 || north != 3 && west != 3 && east != 3 && south != 3 && north != 4 && west != 4 && east != 4 && south != 4 ? center : 2;
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B};
        }

        static {
            J_1907_R = lightning.product.E_2648_v$n_1700_B.n_1700_B();
        }
    }
}


