/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.b_257_Y;

public abstract sealed class p_602_A
extends Enum<p_602_A> {
    public static final /* enum */ p_602_A n_1700_B = new p_602_A(){

        @Override
        public int n_1700_B(int x, int y, int z, b_257_Y.n_1700_B axis) {
            return axis.n_1700_B(x, y, z);
        }

        @Override
        public b_257_Y.n_1700_B n_1700_B(b_257_Y.n_1700_B axisIn) {
            return axisIn;
        }

        @Override
        public p_602_A n_1700_B() {
            return this;
        }
    };
    public static final /* enum */ p_602_A J_1907_R = new p_602_A(){

        @Override
        public int n_1700_B(int x, int y, int z, b_257_Y.n_1700_B axis) {
            return axis.n_1700_B(z, x, y);
        }

        @Override
        public b_257_Y.n_1700_B n_1700_B(b_257_Y.n_1700_B axisIn) {
            return G_564_y[Math.floorMod(axisIn.ordinal() + 1, 3)];
        }

        @Override
        public p_602_A n_1700_B() {
            return R_4764_Y;
        }
    };
    public static final /* enum */ p_602_A R_4764_Y = new p_602_A(){

        @Override
        public int n_1700_B(int x, int y, int z, b_257_Y.n_1700_B axis) {
            return axis.n_1700_B(y, z, x);
        }

        @Override
        public b_257_Y.n_1700_B n_1700_B(b_257_Y.n_1700_B axisIn) {
            return G_564_y[Math.floorMod(axisIn.ordinal() - 1, 3)];
        }

        @Override
        public p_602_A n_1700_B() {
            return J_1907_R;
        }
    };
    public static final b_257_Y.n_1700_B[] G_564_y;
    public static final p_602_A[] P_1922_E;
    private static final /* synthetic */ p_602_A[] u_1723_Y;

    public static p_602_A[] values() {
        return (p_602_A[])u_1723_Y.clone();
    }

    public static p_602_A valueOf(String name) {
        return Enum.valueOf(p_602_A.class, name);
    }

    public abstract int n_1700_B(int var1, int var2, int var3, b_257_Y.n_1700_B var4);

    public abstract b_257_Y.n_1700_B n_1700_B(b_257_Y.n_1700_B var1);

    public abstract p_602_A n_1700_B();

    public static p_602_A n_1700_B(b_257_Y.n_1700_B axis1, b_257_Y.n_1700_B axis2) {
        return P_1922_E[Math.floorMod(axis2.ordinal() - axis1.ordinal(), 3)];
    }

    private static /* synthetic */ p_602_A[] J_1907_R() {
        return new p_602_A[]{n_1700_B, J_1907_R, R_4764_Y};
    }

    static {
        u_1723_Y = p_602_A.J_1907_R();
        G_564_y = b_257_Y.n_1700_B.values();
        P_1922_E = p_602_A.values();
    }
}

