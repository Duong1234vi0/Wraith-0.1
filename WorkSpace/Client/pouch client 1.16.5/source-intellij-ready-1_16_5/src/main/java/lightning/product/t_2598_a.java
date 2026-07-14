/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.List;
import lightning.product.Z_1993_T;

public class t_2598_a {
    private static final List<J_1907_R> n_1700_B = new ArrayList<J_1907_R>();
    private static final int J_1907_R = 10;
    private static n_1700_B R_4764_Y = null;

    public static void n_1700_B(Z_1993_T item, long price, int count) {
        n_1700_B.add(0, new J_1907_R(item.t_148_a(), price, count));
        if (n_1700_B.size() > 10) {
            n_1700_B.remove(n_1700_B.size() - 1);
        }
    }

    public static void n_1700_B(Z_1993_T item, long price, int count, boolean isSpookyTime) {
        R_4764_Y = new n_1700_B(item.t_148_a(), price, count, isSpookyTime);
    }

    public static n_1700_B n_1700_B() {
        return R_4764_Y;
    }

    public static void J_1907_R() {
        R_4764_Y = null;
    }

    public static List<J_1907_R> R_4764_Y() {
        return new ArrayList<J_1907_R>(n_1700_B);
    }

    public static void G_564_y() {
        n_1700_B.clear();
    }

    public static class J_1907_R {
        private final Z_1993_T n_1700_B;
        private final long J_1907_R;
        private final int R_4764_Y;
        private final long G_564_y;

        public J_1907_R(Z_1993_T item, long price, int count) {
            this.n_1700_B = item;
            this.J_1907_R = price;
            this.R_4764_Y = count;
            this.G_564_y = System.currentTimeMillis();
        }

        public Z_1993_T n_1700_B() {
            return this.n_1700_B;
        }

        public long J_1907_R() {
            return this.J_1907_R;
        }

        public int R_4764_Y() {
            return this.R_4764_Y;
        }

        public long G_564_y() {
            return this.G_564_y;
        }
    }

    public static class n_1700_B {
        private final Z_1993_T n_1700_B;
        private final long J_1907_R;
        private final int R_4764_Y;
        private int G_564_y = 0;
        private final boolean P_1922_E;

        public n_1700_B(Z_1993_T item, long price, int count, boolean isSpookyTime) {
            this.n_1700_B = item;
            this.J_1907_R = price;
            this.R_4764_Y = count;
            this.P_1922_E = isSpookyTime;
        }

        public Z_1993_T n_1700_B() {
            return this.n_1700_B;
        }

        public long J_1907_R() {
            return this.J_1907_R;
        }

        public int R_4764_Y() {
            return this.R_4764_Y;
        }

        public int G_564_y() {
            return this.G_564_y;
        }

        public void P_1922_E() {
            ++this.G_564_y;
        }

        public boolean u_1723_Y() {
            return this.P_1922_E;
        }
    }
}

