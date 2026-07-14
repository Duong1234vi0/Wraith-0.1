/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.E_4700_p;
import lightning.product.Z_1993_T;
import lightning.product.DyeItem;
import lightning.product.g_2336_b;
import lightning.product.q_1613_l;
import lightning.product.MaterialColor;
import net.minecraftforge.common.Tags;
import net.optifine.reflect.Reflector;

public final class e_933_M
extends Enum<e_933_M>
implements E_4700_p {
    public static final /* enum */ e_933_M n_1700_B = new e_933_M(0, "white", 0xF9FFFE, MaterialColor.s_956_w, 0xF0F0F0, 0xFFFFFF);
    public static final /* enum */ e_933_M J_1907_R = new e_933_M(1, "orange", 16351261, MaterialColor.t_1786_h, 15435844, 16738335);
    public static final /* enum */ e_933_M R_4764_Y = new e_933_M(2, "magenta", 13061821, MaterialColor.multiplayerClientSuggestionProvider, 12801229, 0xFF00FF);
    public static final /* enum */ e_933_M G_564_y = new e_933_M(3, "light_blue", 3847130, MaterialColor.w_1457_N, 6719955, 10141901);
    public static final /* enum */ e_933_M P_1922_E = new e_933_M(4, "yellow", 16701501, MaterialColor.Y_601_j, 14602026, 0xFFFF00);
    public static final /* enum */ e_933_M u_1723_Y = new e_933_M(5, "lime", 8439583, MaterialColor.Y_259_p, 4312372, 0xBFFF00);
    public static final /* enum */ e_933_M v_4262_N = new e_933_M(6, "pink", 15961002, MaterialColor.Q_2552_b, 14188952, 16738740);
    public static final /* enum */ e_933_M w_1484_f = new e_933_M(7, "gray", 4673362, MaterialColor.C_2741_M, 0x434343, 0x808080);
    public static final /* enum */ e_933_M t_148_a = new e_933_M(8, "light_gray", 0x9D9D97, MaterialColor.k_2293_S, 0xABABAB, 0xD3D3D3);
    public static final /* enum */ e_933_M s_956_w = new e_933_M(9, "cyan", 1481884, MaterialColor.q_2307_F, 2651799, 65535);
    public static final /* enum */ e_933_M u_2550_I = new e_933_M(10, "purple", 8991416, MaterialColor.Z_875_P, 8073150, 10494192);
    public static final /* enum */ e_933_M M_588_G = new e_933_M(11, "blue", 3949738, MaterialColor.c_3005_b, 2437522, 255);
    public static final /* enum */ e_933_M P_4830_p = new e_933_M(12, "brown", 8606770, MaterialColor.H_2857_Y, 5320730, 9127187);
    public static final /* enum */ e_933_M h_1847_R = new e_933_M(13, "green", 6192150, MaterialColor.A_4115_X, 3887386, 65280);
    public static final /* enum */ e_933_M Q_4569_t = new e_933_M(14, "red", 11546150, MaterialColor.Y_1740_V, 11743532, 0xFF0000);
    public static final /* enum */ e_933_M M_182_A = new e_933_M(15, "black", 0x1D1D21, MaterialColor.t_4043_B, 0x1E1B1B, 0);
    private static final e_933_M[] t_1786_h;
    private static final Int2ObjectOpenHashMap<e_933_M> multiplayerClientSuggestionProvider;
    private final int w_1457_N;
    private final String Y_601_j;
    private final MaterialColor Y_259_p;
    private final int Q_2552_b;
    private final int C_2741_M;
    private float[] k_2293_S;
    private final int q_2307_F;
    private final Tags.IOptionalNamedTag<q_1613_l> Z_875_P;
    private final int c_3005_b;
    private static final /* synthetic */ e_933_M[] H_2857_Y;

    public static e_933_M[] values() {
        return (e_933_M[])H_2857_Y.clone();
    }

    public static e_933_M valueOf(String name) {
        return Enum.valueOf(e_933_M.class, name);
    }

    private e_933_M(int idIn, String translationKeyIn, int colorValueIn, MaterialColor mapColorIn, int fireworkColorIn, int textColorIn) {
        this.w_1457_N = idIn;
        this.Y_601_j = translationKeyIn;
        this.Q_2552_b = colorValueIn;
        this.Y_259_p = mapColorIn;
        this.c_3005_b = textColorIn;
        int i = (colorValueIn & 0xFF0000) >> 16;
        int j = (colorValueIn & 0xFF00) >> 8;
        int k = (colorValueIn & 0xFF) >> 0;
        this.C_2741_M = k << 16 | j << 8 | i << 0;
        this.Z_875_P = (Tags.IOptionalNamedTag)Reflector.ForgeItemTags_createOptional.call((Object)new g_2336_b("forge", "dyes/" + translationKeyIn));
        this.k_2293_S = new float[]{(float)i / 255.0f, (float)j / 255.0f, (float)k / 255.0f};
        this.q_2307_F = fireworkColorIn;
    }

    public int J_1907_R() {
        return this.w_1457_N;
    }

    public String R_4764_Y() {
        return this.Y_601_j;
    }

    public float[] G_564_y() {
        return this.k_2293_S;
    }

    public MaterialColor P_1922_E() {
        return this.Y_259_p;
    }

    public int u_1723_Y() {
        return this.q_2307_F;
    }

    public int v_4262_N() {
        return this.c_3005_b;
    }

    public static e_933_M n_1700_B(int colorId) {
        if (colorId < 0 || colorId >= t_1786_h.length) {
            colorId = 0;
        }
        return t_1786_h[colorId];
    }

    public static e_933_M n_1700_B(String translationKeyIn, e_933_M fallback) {
        for (e_933_M dyecolor : e_933_M.values()) {
            if (!dyecolor.Y_601_j.equals(translationKeyIn)) continue;
            return dyecolor;
        }
        return fallback;
    }

    @Nullable
    public static e_933_M J_1907_R(int fireworkColorIn) {
        return (e_933_M)multiplayerClientSuggestionProvider.get(fireworkColorIn);
    }

    public String toString() {
        return this.Y_601_j;
    }

    @Override
    public String n_1700_B() {
        return this.Y_601_j;
    }

    public void n_1700_B(float[] p_setColorComponentValues_1_) {
        this.k_2293_S = p_setColorComponentValues_1_;
    }

    public int w_1484_f() {
        return this.Q_2552_b;
    }

    public Tags.IOptionalNamedTag<q_1613_l> t_148_a() {
        return this.Z_875_P;
    }

    @Nullable
    public static e_933_M n_1700_B(Z_1993_T p_getColor_0_) {
        if (p_getColor_0_.J_1907_R() instanceof DyeItem) {
            return ((DyeItem)p_getColor_0_.J_1907_R()).R_4764_Y();
        }
        for (e_933_M dyecolor : t_1786_h) {
            if (!p_getColor_0_.J_1907_R().n_1700_B(dyecolor.t_148_a())) continue;
            return dyecolor;
        }
        return null;
    }

    private static /* synthetic */ e_933_M[] s_956_w() {
        return new e_933_M[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a, s_956_w, u_2550_I, M_588_G, P_4830_p, h_1847_R, Q_4569_t, M_182_A};
    }

    static {
        H_2857_Y = e_933_M.s_956_w();
        t_1786_h = (e_933_M[])Arrays.stream(e_933_M.values()).sorted(Comparator.comparingInt(e_933_M::J_1907_R)).toArray(e_933_M[]::new);
        multiplayerClientSuggestionProvider = new Int2ObjectOpenHashMap(Arrays.stream(e_933_M.values()).collect(Collectors.toMap(p_lambda$static$1_0_ -> p_lambda$static$1_0_.q_2307_F, p_lambda$static$2_0_ -> p_lambda$static$2_0_)));
    }
}


