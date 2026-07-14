/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Locale;

public final class H_1974_E
extends Enum<H_1974_E> {
    public static final /* enum */ H_1974_E n_1700_B = new H_1974_E();
    public static final /* enum */ H_1974_E J_1907_R = new H_1974_E();
    public static final /* enum */ H_1974_E R_4764_Y = new H_1974_E();
    public static final /* enum */ H_1974_E G_564_y = new H_1974_E();
    private static final /* synthetic */ H_1974_E[] P_1922_E;

    public static H_1974_E[] values() {
        return (H_1974_E[])P_1922_E.clone();
    }

    public static H_1974_E valueOf(String name) {
        return Enum.valueOf(H_1974_E.class, name);
    }

    public static H_1974_E n_1700_B(long p_237682_0_) {
        if (p_237682_0_ < 1024L) {
            return n_1700_B;
        }
        try {
            int i = (int)(Math.log(p_237682_0_) / Math.log(1024.0));
            String s = String.valueOf("KMGTPE".charAt(i - 1));
            return H_1974_E.valueOf(s + "B");
        }
        catch (Exception exception) {
            return G_564_y;
        }
    }

    public static double n_1700_B(long p_237683_0_, H_1974_E p_237683_2_) {
        return p_237683_2_ == n_1700_B ? (double)p_237683_0_ : (double)p_237683_0_ / Math.pow(1024.0, p_237683_2_.ordinal());
    }

    public static String J_1907_R(long p_237684_0_) {
        int i = 1024;
        if (p_237684_0_ < 1024L) {
            return p_237684_0_ + " B";
        }
        int j = (int)(Math.log(p_237684_0_) / Math.log(1024.0));
        String s = "" + "KMGTPE".charAt(j - 1);
        return String.format(Locale.ROOT, "%.1f %sB", (double)p_237684_0_ / Math.pow(1024.0, j), s);
    }

    public static String J_1907_R(long p_237685_0_, H_1974_E p_237685_2_) {
        return String.format("%." + (p_237685_2_ == G_564_y ? "1" : "0") + "f %s", H_1974_E.n_1700_B(p_237685_0_, p_237685_2_), p_237685_2_.name());
    }

    private static /* synthetic */ H_1974_E[] n_1700_B() {
        return new H_1974_E[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
    }

    static {
        P_1922_E = H_1974_E.n_1700_B();
    }
}

