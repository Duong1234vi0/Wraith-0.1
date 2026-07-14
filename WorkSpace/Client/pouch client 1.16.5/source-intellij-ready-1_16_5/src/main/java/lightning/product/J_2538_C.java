/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.tuple.Pair
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.U_2912_j;
import lightning.product.e_933_M;
import lightning.product.g_2336_b;
import lightning.product.q_2896_o;
import org.apache.commons.lang3.tuple.Pair;

public final class J_2538_C
extends Enum<J_2538_C> {
    public static final /* enum */ J_2538_C n_1700_B = new J_2538_C("base", "b", false);
    public static final /* enum */ J_2538_C J_1907_R = new J_2538_C("square_bottom_left", "bl");
    public static final /* enum */ J_2538_C R_4764_Y = new J_2538_C("square_bottom_right", "br");
    public static final /* enum */ J_2538_C G_564_y = new J_2538_C("square_top_left", "tl");
    public static final /* enum */ J_2538_C P_1922_E = new J_2538_C("square_top_right", "tr");
    public static final /* enum */ J_2538_C u_1723_Y = new J_2538_C("stripe_bottom", "bs");
    public static final /* enum */ J_2538_C v_4262_N = new J_2538_C("stripe_top", "ts");
    public static final /* enum */ J_2538_C w_1484_f = new J_2538_C("stripe_left", "ls");
    public static final /* enum */ J_2538_C t_148_a = new J_2538_C("stripe_right", "rs");
    public static final /* enum */ J_2538_C s_956_w = new J_2538_C("stripe_center", "cs");
    public static final /* enum */ J_2538_C u_2550_I = new J_2538_C("stripe_middle", "ms");
    public static final /* enum */ J_2538_C M_588_G = new J_2538_C("stripe_downright", "drs");
    public static final /* enum */ J_2538_C P_4830_p = new J_2538_C("stripe_downleft", "dls");
    public static final /* enum */ J_2538_C h_1847_R = new J_2538_C("small_stripes", "ss");
    public static final /* enum */ J_2538_C Q_4569_t = new J_2538_C("cross", "cr");
    public static final /* enum */ J_2538_C M_182_A = new J_2538_C("straight_cross", "sc");
    public static final /* enum */ J_2538_C t_1786_h = new J_2538_C("triangle_bottom", "bt");
    public static final /* enum */ J_2538_C multiplayerClientSuggestionProvider = new J_2538_C("triangle_top", "tt");
    public static final /* enum */ J_2538_C w_1457_N = new J_2538_C("triangles_bottom", "bts");
    public static final /* enum */ J_2538_C Y_601_j = new J_2538_C("triangles_top", "tts");
    public static final /* enum */ J_2538_C Y_259_p = new J_2538_C("diagonal_left", "ld");
    public static final /* enum */ J_2538_C Q_2552_b = new J_2538_C("diagonal_up_right", "rd");
    public static final /* enum */ J_2538_C C_2741_M = new J_2538_C("diagonal_up_left", "lud");
    public static final /* enum */ J_2538_C k_2293_S = new J_2538_C("diagonal_right", "rud");
    public static final /* enum */ J_2538_C q_2307_F = new J_2538_C("circle", "mc");
    public static final /* enum */ J_2538_C Z_875_P = new J_2538_C("rhombus", "mr");
    public static final /* enum */ J_2538_C c_3005_b = new J_2538_C("half_vertical", "vh");
    public static final /* enum */ J_2538_C H_2857_Y = new J_2538_C("half_horizontal", "hh");
    public static final /* enum */ J_2538_C A_4115_X = new J_2538_C("half_vertical_right", "vhr");
    public static final /* enum */ J_2538_C Y_1740_V = new J_2538_C("half_horizontal_bottom", "hhb");
    public static final /* enum */ J_2538_C t_4043_B = new J_2538_C("border", "bo");
    public static final /* enum */ J_2538_C x_607_J = new J_2538_C("curly_border", "cbo");
    public static final /* enum */ J_2538_C e_4240_b = new J_2538_C("gradient", "gra");
    public static final /* enum */ J_2538_C n_3318_d = new J_2538_C("gradient_up", "gru");
    public static final /* enum */ J_2538_C d_2427_y = new J_2538_C("bricks", "bri");
    public static final /* enum */ J_2538_C z_1737_N = new J_2538_C("globe", "glb", true);
    public static final /* enum */ J_2538_C v_4276_D = new J_2538_C("creeper", "cre", true);
    public static final /* enum */ J_2538_C d_2461_k = new J_2538_C("skull", "sku", true);
    public static final /* enum */ J_2538_C G_624_v = new J_2538_C("flower", "flo", true);
    public static final /* enum */ J_2538_C T_2506_i = new J_2538_C("mojang", "moj", true);
    public static final /* enum */ J_2538_C q_4610_l = new J_2538_C("piglin", "pig", true);
    private static final J_2538_C[] B_1668_F;
    public static final int z_4693_k;
    public static final int g_221_o;
    public static final int e_2887_G;
    private final boolean g_164_R;
    private final String X_933_l;
    private final String Z_976_R;
    private static final /* synthetic */ J_2538_C[] H_1990_U;

    public static J_2538_C[] values() {
        return (J_2538_C[])H_1990_U.clone();
    }

    public static J_2538_C valueOf(String name) {
        return Enum.valueOf(J_2538_C.class, name);
    }

    private J_2538_C(String fileNameIn, String hashNameIn) {
        this(fileNameIn, hashNameIn, false);
    }

    private J_2538_C(String fileName, String hashname, boolean hasPatternItem) {
        this.X_933_l = fileName;
        this.Z_976_R = hashname;
        this.g_164_R = hasPatternItem;
    }

    public g_2336_b n_1700_B(boolean isBanner) {
        String s = isBanner ? "banner" : "shield";
        return new g_2336_b("entity/" + s + "/" + this.n_1700_B());
    }

    public String n_1700_B() {
        return this.X_933_l;
    }

    public String J_1907_R() {
        return this.Z_976_R;
    }

    @Nullable
    public static J_2538_C n_1700_B(String hash) {
        for (J_2538_C bannerpattern : J_2538_C.values()) {
            if (!bannerpattern.Z_976_R.equals(hash)) continue;
            return bannerpattern;
        }
        return null;
    }

    private static /* synthetic */ J_2538_C[] R_4764_Y() {
        return new J_2538_C[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a, s_956_w, u_2550_I, M_588_G, P_4830_p, h_1847_R, Q_4569_t, M_182_A, t_1786_h, multiplayerClientSuggestionProvider, w_1457_N, Y_601_j, Y_259_p, Q_2552_b, C_2741_M, k_2293_S, q_2307_F, Z_875_P, c_3005_b, H_2857_Y, A_4115_X, Y_1740_V, t_4043_B, x_607_J, e_4240_b, n_3318_d, d_2427_y, z_1737_N, v_4276_D, d_2461_k, G_624_v, T_2506_i, q_4610_l};
    }

    static {
        H_1990_U = J_2538_C.R_4764_Y();
        B_1668_F = J_2538_C.values();
        z_4693_k = B_1668_F.length;
        g_221_o = (int)Arrays.stream(B_1668_F).filter(pattern -> pattern.g_164_R).count();
        e_2887_G = z_4693_k - g_221_o - 1;
    }

    public static class n_1700_B {
        private final List<Pair<J_2538_C, e_933_M>> n_1700_B = Lists.newArrayList();

        public n_1700_B n_1700_B(J_2538_C pattern, e_933_M color) {
            this.n_1700_B.add((Pair<J_2538_C, e_933_M>)Pair.of((Object)((Object)pattern), (Object)color));
            return this;
        }

        public q_2896_o n_1700_B() {
            q_2896_o listnbt = new q_2896_o();
            for (Pair<J_2538_C, e_933_M> pair : this.n_1700_B) {
                U_2912_j compoundnbt = new U_2912_j();
                compoundnbt.n_1700_B("Pattern", ((J_2538_C)((Object)pair.getLeft())).Z_976_R);
                compoundnbt.J_1907_R("Color", ((e_933_M)pair.getRight()).J_1907_R());
                listnbt.add(compoundnbt);
            }
            return listnbt;
        }
    }
}


