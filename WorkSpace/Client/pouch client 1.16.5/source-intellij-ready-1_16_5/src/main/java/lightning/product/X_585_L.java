/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import lightning.product.j_3341_s;

public interface X_585_L {
    public static final DecimalFormat n_1700_B = j_3341_s.n_1700_B(new DecimalFormat("########0.00"), p_223254_0_ -> p_223254_0_.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ROOT)));
    public static final X_585_L J_1907_R = NumberFormat.getIntegerInstance(Locale.US)::format;
    public static final X_585_L R_4764_Y = p_223256_0_ -> n_1700_B.format((double)p_223256_0_ * 0.1);
    public static final X_585_L G_564_y = p_223255_0_ -> {
        double d0 = (double)p_223255_0_ / 100.0;
        double d1 = d0 / 1000.0;
        if (d1 > 0.5) {
            return n_1700_B.format(d1) + " km";
        }
        return d0 > 0.5 ? n_1700_B.format(d0) + " m" : p_223255_0_ + " cm";
    };
    public static final X_585_L P_1922_E = p_223253_0_ -> {
        double d0 = (double)p_223253_0_ / 20.0;
        double d1 = d0 / 60.0;
        double d2 = d1 / 60.0;
        double d3 = d2 / 24.0;
        double d4 = d3 / 365.0;
        if (d4 > 0.5) {
            return n_1700_B.format(d4) + " y";
        }
        if (d3 > 0.5) {
            return n_1700_B.format(d3) + " d";
        }
        if (d2 > 0.5) {
            return n_1700_B.format(d2) + " h";
        }
        return d1 > 0.5 ? n_1700_B.format(d1) + " m" : d0 + " s";
    };

    public String format(int var1);
}

