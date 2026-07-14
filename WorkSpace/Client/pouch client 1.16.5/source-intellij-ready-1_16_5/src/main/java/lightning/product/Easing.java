/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.H_274_C;
import lombok.Generated;

public final class Easing {
    public static final double n_1700_B = 1.70158;
    public static final double J_1907_R = 2.5949095;
    public static final double R_4764_Y = 2.70158;
    public static final double G_564_y = 2.0943951023931953;
    public static final double P_1922_E = 1.3962634015954636;
    public static final H_274_C u_1723_Y = value -> value;
    public static final H_274_C v_4262_N = Easing.n_1700_B(2);
    public static final H_274_C w_1484_f = Easing.J_1907_R(2);
    public static final H_274_C t_148_a = Easing.R_4764_Y(2.0);
    public static final H_274_C s_956_w = Easing.n_1700_B(3);
    public static final H_274_C u_2550_I = Easing.J_1907_R(3);
    public static final H_274_C M_588_G = Easing.R_4764_Y(3.0);
    public static final H_274_C P_4830_p = Easing.n_1700_B(4);
    public static final H_274_C h_1847_R = Easing.J_1907_R(4);
    public static final H_274_C Q_4569_t = Easing.R_4764_Y(4.0);
    public static final H_274_C M_182_A = Easing.n_1700_B(5);
    public static final H_274_C t_1786_h = Easing.J_1907_R(5);
    public static final H_274_C multiplayerClientSuggestionProvider = Easing.R_4764_Y(5.0);
    public static final H_274_C w_1457_N = value -> 1.0 - Math.cos(value * Math.PI / 2.0);
    public static final H_274_C Y_601_j = value -> Math.sin(value * Math.PI / 2.0);
    public static final H_274_C Y_259_p = value -> -(Math.cos(Math.PI * value) - 1.0) / 2.0;
    public static final H_274_C Q_2552_b = value -> 1.0 - Math.sqrt(1.0 - Math.pow(value, 2.0));
    public static final H_274_C C_2741_M = value -> Math.sqrt(1.0 - Math.pow(value - 1.0, 2.0));
    public static final H_274_C k_2293_S = value -> value < 0.5 ? (1.0 - Math.sqrt(1.0 - Math.pow(2.0 * value, 2.0))) / 2.0 : (Math.sqrt(1.0 - Math.pow(-2.0 * value + 2.0, 2.0)) + 1.0) / 2.0;
    public static final H_274_C q_2307_F = value -> value != 0.0 && value != 1.0 ? Math.pow(-2.0, 10.0 * value - 10.0) * Math.sin((value * 10.0 - 10.75) * 2.0943951023931953) : value;
    public static final H_274_C Z_875_P = value -> value != 0.0 && value != 1.0 ? Math.pow(2.0, -10.0 * value) * Math.sin((value * 10.0 - 0.75) * 2.0943951023931953) + 1.0 : value;
    public static final H_274_C c_3005_b = value -> {
        if (value != 0.0 && value != 1.0) {
            return value < 0.5 ? -(Math.pow(2.0, 20.0 * value - 10.0) * Math.sin((20.0 * value - 11.125) * 1.3962634015954636)) / 2.0 : Math.pow(2.0, -20.0 * value + 10.0) * Math.sin((20.0 * value - 11.125) * 1.3962634015954636) / 2.0 + 1.0;
        }
        return value;
    };
    public static final H_274_C H_2857_Y = value -> value != 0.0 ? Math.pow(2.0, 10.0 * value - 10.0) : value;
    public static final H_274_C A_4115_X = value -> value != 1.0 ? 1.0 - Math.pow(2.0, -10.0 * value) : value;
    public static final H_274_C Y_1740_V = value -> {
        if (value != 0.0 && value != 1.0) {
            return value < 0.5 ? Math.pow(2.0, 20.0 * value - 10.0) / 2.0 : (2.0 - Math.pow(2.0, -20.0 * value + 10.0)) / 2.0;
        }
        return value;
    };
    public static final H_274_C t_4043_B = value -> 2.70158 * Math.pow(value, 3.0) - 1.70158 * Math.pow(value, 2.0);
    public static final H_274_C x_607_J = value -> 1.0 + 2.70158 * Math.pow(value - 1.0, 3.0) + 1.70158 * Math.pow(value - 1.0, 2.0);
    public static final H_274_C e_4240_b = value -> value < 0.5 ? Math.pow(2.0 * value, 2.0) * (7.189819 * value - 2.5949095) / 2.0 : (Math.pow(2.0 * value - 2.0, 2.0) * (3.5949095 * (value * 2.0 - 2.0) + 2.5949095) + 2.0) / 2.0;
    public static final H_274_C n_3318_d = x -> {
        double n1 = 7.5625;
        double d1 = 2.75;
        if (x < 1.0 / d1) {
            return n1 * Math.pow(x, 2.0);
        }
        if (x < 2.0 / d1) {
            return n1 * Math.pow(x - 1.5 / d1, 2.0) + 0.75;
        }
        return x < 2.5 / d1 ? n1 * Math.pow(x - 2.25 / d1, 2.0) + 0.9375 : n1 * Math.pow(x - 2.625 / d1, 2.0) + 0.984375;
    };
    public static final H_274_C d_2427_y = value -> 1.0 - n_3318_d.ease(1.0 - value);
    public static final H_274_C z_1737_N = value -> value < 0.5 ? (1.0 - n_3318_d.ease(1.0 - 2.0 * value)) / 2.0 : (1.0 + n_3318_d.ease(2.0 * value - 1.0)) / 2.0;

    public static H_274_C n_1700_B(double n) {
        return value -> Math.pow(value, n);
    }

    public static H_274_C n_1700_B(int n) {
        return Easing.n_1700_B((double)n);
    }

    public static H_274_C J_1907_R(double n) {
        return value -> 1.0 - Math.pow(1.0 - value, n);
    }

    public static H_274_C J_1907_R(int n) {
        return Easing.J_1907_R((double)n);
    }

    public static H_274_C R_4764_Y(double n) {
        return value -> value < 0.5 ? Math.pow(2.0, n - 1.0) * Math.pow(value, n) : 1.0 - Math.pow(-2.0 * value + 2.0, n) / 2.0;
    }

    @Generated
    private Easing() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}


