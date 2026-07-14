/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public class GrassColor {
    private static int[] n_1700_B = new int[65536];

    public static void n_1700_B(int[] grassBufferIn) {
        n_1700_B = grassBufferIn;
    }

    public static int n_1700_B(double temperature, double humidity) {
        int j = (int)((1.0 - (humidity *= temperature)) * 255.0);
        int i = (int)((1.0 - temperature) * 255.0);
        int k = j << 8 | i;
        return k > n_1700_B.length ? -65281 : n_1700_B[k];
    }
}


