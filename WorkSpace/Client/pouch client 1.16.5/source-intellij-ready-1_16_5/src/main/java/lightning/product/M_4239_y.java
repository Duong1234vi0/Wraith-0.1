/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public class M_4239_y {

    public static class n_1700_B {
        public static int n_1700_B(int packedColor) {
            return packedColor >>> 24;
        }

        public static int J_1907_R(int packedColor) {
            return packedColor >> 16 & 0xFF;
        }

        public static int R_4764_Y(int packedColor) {
            return packedColor >> 8 & 0xFF;
        }

        public static int G_564_y(int packedColor) {
            return packedColor & 0xFF;
        }

        public static int n_1700_B(int alpha, int red, int green, int blue) {
            return alpha << 24 | red << 16 | green << 8 | blue;
        }

        public static int n_1700_B(int packedColourOne, int packedColorTwo) {
            return n_1700_B.n_1700_B(n_1700_B.n_1700_B(packedColourOne) * n_1700_B.n_1700_B(packedColorTwo) / 255, n_1700_B.J_1907_R(packedColourOne) * n_1700_B.J_1907_R(packedColorTwo) / 255, n_1700_B.R_4764_Y(packedColourOne) * n_1700_B.R_4764_Y(packedColorTwo) / 255, n_1700_B.G_564_y(packedColourOne) * n_1700_B.G_564_y(packedColorTwo) / 255);
        }
    }
}

