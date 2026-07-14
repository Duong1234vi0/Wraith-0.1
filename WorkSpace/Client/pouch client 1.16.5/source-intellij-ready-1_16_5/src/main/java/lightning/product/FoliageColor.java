/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public class FoliageColor {
    private static int[] n_1700_B = new int[65536];

    public static void n_1700_B(int[] foliageBufferIn) {
        n_1700_B = foliageBufferIn;
    }

    public static int n_1700_B(double temperature, double humidity) {
        int i = (int)((1.0 - temperature) * 255.0);
        int j = (int)((1.0 - (humidity *= temperature)) * 255.0);
        return n_1700_B[j << 8 | i];
    }

    public static int n_1700_B() {
        return 0x619961;
    }

    public static int J_1907_R() {
        return 8431445;
    }

    public static int R_4764_Y() {
        return 4764952;
    }
}


