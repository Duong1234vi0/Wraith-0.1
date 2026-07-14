/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.T_603_v;

public interface BorderChangeListener {
    public void n_1700_B(T_603_v var1, double var2);

    public void n_1700_B(T_603_v var1, double var2, double var4, long var6);

    public void n_1700_B(T_603_v var1, double var2, double var4);

    public void n_1700_B(T_603_v var1, int var2);

    public void J_1907_R(T_603_v var1, int var2);

    public void J_1907_R(T_603_v var1, double var2);

    public void R_4764_Y(T_603_v var1, double var2);

    public static class n_1700_B
    implements BorderChangeListener {
        private final T_603_v n_1700_B;

        public n_1700_B(T_603_v border) {
            this.n_1700_B = border;
        }

        @Override
        public void n_1700_B(T_603_v border, double newSize) {
            this.n_1700_B.n_1700_B(newSize);
        }

        @Override
        public void n_1700_B(T_603_v border, double oldSize, double newSize, long time) {
            this.n_1700_B.n_1700_B(oldSize, newSize, time);
        }

        @Override
        public void n_1700_B(T_603_v border, double x, double z) {
            this.n_1700_B.J_1907_R(x, z);
        }

        @Override
        public void n_1700_B(T_603_v border, int newTime) {
            this.n_1700_B.J_1907_R(newTime);
        }

        @Override
        public void J_1907_R(T_603_v border, int newDistance) {
            this.n_1700_B.R_4764_Y(newDistance);
        }

        @Override
        public void J_1907_R(T_603_v border, double newAmount) {
            this.n_1700_B.R_4764_Y(newAmount);
        }

        @Override
        public void R_4764_Y(T_603_v border, double newSize) {
            this.n_1700_B.J_1907_R(newSize);
        }
    }
}


