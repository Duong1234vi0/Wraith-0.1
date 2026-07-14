/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

public final class D_4024_W
extends Enum<D_4024_W> {
    public static final /* enum */ D_4024_W n_1700_B = new D_4024_W("BLACK", '0', 0, 0);
    public static final /* enum */ D_4024_W J_1907_R = new D_4024_W("DARK_BLUE", '1', 1, 170);
    public static final /* enum */ D_4024_W R_4764_Y = new D_4024_W("DARK_GREEN", '2', 2, 43520);
    public static final /* enum */ D_4024_W G_564_y = new D_4024_W("DARK_AQUA", '3', 3, 43690);
    public static final /* enum */ D_4024_W P_1922_E = new D_4024_W("DARK_RED", '4', 4, 0xAA0000);
    public static final /* enum */ D_4024_W u_1723_Y = new D_4024_W("DARK_PURPLE", '5', 5, 0xAA00AA);
    public static final /* enum */ D_4024_W v_4262_N = new D_4024_W("GOLD", '6', 6, 0xFFAA00);
    public static final /* enum */ D_4024_W w_1484_f = new D_4024_W("GRAY", '7', 7, 0xAAAAAA);
    public static final /* enum */ D_4024_W t_148_a = new D_4024_W("DARK_GRAY", '8', 8, 0x555555);
    public static final /* enum */ D_4024_W s_956_w = new D_4024_W("BLUE", '9', 9, 0x5555FF);
    public static final /* enum */ D_4024_W u_2550_I = new D_4024_W("GREEN", 'a', 10, 0x55FF55);
    public static final /* enum */ D_4024_W M_588_G = new D_4024_W("AQUA", 'b', 11, 0x55FFFF);
    public static final /* enum */ D_4024_W P_4830_p = new D_4024_W("RED", 'c', 12, 0xFF5555);
    public static final /* enum */ D_4024_W h_1847_R = new D_4024_W("LIGHT_PURPLE", 'd', 13, 0xFF55FF);
    public static final /* enum */ D_4024_W Q_4569_t = new D_4024_W("YELLOW", 'e', 14, 0xFFFF55);
    public static final /* enum */ D_4024_W M_182_A = new D_4024_W("WHITE", 'f', 15, 0xFFFFFF);
    public static final /* enum */ D_4024_W t_1786_h = new D_4024_W("OBFUSCATED", 'k', true);
    public static final /* enum */ D_4024_W multiplayerClientSuggestionProvider = new D_4024_W("BOLD", 'l', true);
    public static final /* enum */ D_4024_W w_1457_N = new D_4024_W("STRIKETHROUGH", 'm', true);
    public static final /* enum */ D_4024_W Y_601_j = new D_4024_W("UNDERLINE", 'n', true);
    public static final /* enum */ D_4024_W Y_259_p = new D_4024_W("ITALIC", 'o', true);
    public static final /* enum */ D_4024_W Q_2552_b = new D_4024_W("RESET", 'r', -1, null);
    private static final Map<String, D_4024_W> C_2741_M;
    private static final Pattern k_2293_S;
    private final String q_2307_F;
    private final char Z_875_P;
    private final boolean c_3005_b;
    private final String H_2857_Y;
    private final int A_4115_X;
    @Nullable
    private final Integer Y_1740_V;
    private static final /* synthetic */ D_4024_W[] t_4043_B;

    public static D_4024_W[] values() {
        return (D_4024_W[])t_4043_B.clone();
    }

    public static D_4024_W valueOf(String name) {
        return Enum.valueOf(D_4024_W.class, name);
    }

    private static String R_4764_Y(String string) {
        return string.toLowerCase(Locale.ROOT).replaceAll("[^a-z]", "");
    }

    private D_4024_W(String formattingName, @Nullable char formattingCodeIn, int index, Integer colorCode) {
        this(formattingName, formattingCodeIn, false, index, colorCode);
    }

    private D_4024_W(String formattingName, char formattingCodeIn, boolean fancyStylingIn) {
        this(formattingName, formattingCodeIn, fancyStylingIn, -1, null);
    }

    private D_4024_W(String formattingName, char formattingCodeIn, @Nullable boolean fancyStylingIn, int index, Integer colorCode) {
        this.q_2307_F = formattingName;
        this.Z_875_P = formattingCodeIn;
        this.c_3005_b = fancyStylingIn;
        this.A_4115_X = index;
        this.Y_1740_V = colorCode;
        this.H_2857_Y = "\u00a7" + formattingCodeIn;
    }

    public int n_1700_B() {
        return this.A_4115_X;
    }

    public boolean J_1907_R() {
        return this.c_3005_b;
    }

    public boolean R_4764_Y() {
        return !this.c_3005_b && this != Q_2552_b;
    }

    @Nullable
    public Integer G_564_y() {
        return this.Y_1740_V;
    }

    public String P_1922_E() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    public String toString() {
        return this.H_2857_Y;
    }

    @Nullable
    public static String n_1700_B(@Nullable String text) {
        return text == null ? null : k_2293_S.matcher(text).replaceAll("");
    }

    @Nullable
    public static D_4024_W J_1907_R(@Nullable String friendlyName) {
        return friendlyName == null ? null : C_2741_M.get(D_4024_W.R_4764_Y(friendlyName));
    }

    @Nullable
    public static D_4024_W n_1700_B(int index) {
        if (index < 0) {
            return Q_2552_b;
        }
        for (D_4024_W textformatting : D_4024_W.values()) {
            if (textformatting.n_1700_B() != index) continue;
            return textformatting;
        }
        return null;
    }

    @Nullable
    public static D_4024_W n_1700_B(char formattingCodeIn) {
        char c0 = Character.toString(formattingCodeIn).toLowerCase(Locale.ROOT).charAt(0);
        for (D_4024_W textformatting : D_4024_W.values()) {
            if (textformatting.Z_875_P != c0) continue;
            return textformatting;
        }
        return null;
    }

    public static Collection<String> n_1700_B(boolean getColor, boolean getFancyStyling) {
        ArrayList list = Lists.newArrayList();
        for (D_4024_W textformatting : D_4024_W.values()) {
            if (textformatting.R_4764_Y() && !getColor || textformatting.J_1907_R() && !getFancyStyling) continue;
            list.add(textformatting.P_1922_E());
        }
        return list;
    }

    private static /* synthetic */ D_4024_W[] u_1723_Y() {
        return new D_4024_W[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a, s_956_w, u_2550_I, M_588_G, P_4830_p, h_1847_R, Q_4569_t, M_182_A, t_1786_h, multiplayerClientSuggestionProvider, w_1457_N, Y_601_j, Y_259_p, Q_2552_b};
    }

    static {
        t_4043_B = D_4024_W.u_1723_Y();
        C_2741_M = Arrays.stream(D_4024_W.values()).collect(Collectors.toMap(p_199746_0_ -> D_4024_W.R_4764_Y(p_199746_0_.q_2307_F), p_199747_0_ -> p_199747_0_));
        k_2293_S = Pattern.compile("(?i)\u00a7[0-9A-FK-OR]");
    }
}


