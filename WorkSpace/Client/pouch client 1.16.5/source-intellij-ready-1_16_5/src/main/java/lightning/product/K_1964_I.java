/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.List;
import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.O_922_L;
import lightning.product.U_2871_b;
import lightning.product.MinecraftAccess;
import lightning.product.g_221_o;
import lightning.product.Easing;
import lightning.product.i_4833_u;
import lightning.product.Animation;
import lightning.product.k_2603_m;
import lightning.product.l_3370_o;
import lightning.product.u_530_F;
import lightning.product.y_4842_Z;

public class K_1964_I
extends k_2603_m {
    private static final int n_1700_B = H_2506_c.n_1700_B("#6F5EF6FF");
    private static final int J_1907_R = H_2506_c.n_1700_B("#FFFFFF18");
    private static final int R_4764_Y = H_2506_c.n_1700_B("#FFFFFF15");
    private static final int G_564_y = H_2506_c.n_1700_B("#FFFFFF05");
    private static final int P_1922_E = H_2506_c.n_1700_B("#1A1A1AFF");
    private static final int u_1723_Y = H_2506_c.n_1700_B("#D0DAF0FF");
    private static final int v_4262_N = H_2506_c.n_1700_B("#4A4A4AFF");
    private static final int w_1484_f = H_2506_c.n_1700_B("#A4ABABA8");
    private static final float t_148_a = 400.0f;
    private static final float s_956_w = 380.0f;
    private static final float u_2550_I = 20.0f;
    private static final float M_588_G = 23.0f;
    private static final float P_4830_p = 4.0f;
    private static final float h_1847_R = 23.0f;
    private static final float Q_4569_t = 8.0f;
    private static final float M_182_A = 3.0f;
    private static final float t_1786_h = 10.0f;
    private static final float multiplayerClientSuggestionProvider = 20.0f;
    private static final float w_1457_N = 18.0f;
    private static final float Y_601_j = 12.0f;
    private final k_2603_m Y_259_p;
    private boolean Q_2552_b = false;
    private final Animation C_2741_M = new Animation(0.0f, 6.0f, Easing.u_2550_I);
    private float k_2293_S = 0.0f;
    private float q_2307_F = 0.0f;
    private final Animation Z_875_P = new Animation(0.0f, 6.0f, Easing.u_2550_I);
    private boolean c_3005_b = false;
    private float H_2857_Y;
    private float A_4115_X;
    private final List<n_1700_B> Y_1740_V = new ArrayList<n_1700_B>();

    private static int n_1700_B(int light, int dark) {
        return O_922_L.n_1700_B() ? dark : light;
    }

    public K_1964_I(k_2603_m parentScreen) {
        super(new U_2871_b("Changelog"));
        this.Y_259_p = parentScreen;
        this.Y_1740_V.add(new n_1700_B("Pouch Beta Release (1.3)", lightning.product.K_1964_I$J_1907_R.n_1700_B));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("\u0418\u0437\u043c\u0435\u043d\u0435\u043d\u0438\u044f", lightning.product.K_1964_I$J_1907_R.J_1907_R));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- Neuro", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u0414\u043e\u0431\u0430\u0432\u043b\u0435\u043d\u043e.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- AttackAura", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("  - \u0414\u043e\u0431\u0430\u0432\u043b\u0435\u043d Holyworld new.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- AntiBot", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- AutoCrystal", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- AutoSwap", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- AutoTotem", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- AutoTrap", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- TargetPearl", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- TriggerBot", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- BedrockProxy", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- BetterMinecraft", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- Bots", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- DiscordRPC", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- ElytraHelper", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- FlagDetector", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- NameProtect", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- TPLoot", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- ItemHelper", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d\u044b \u0445\u0435\u043b\u043f\u0435\u0440\u044b \u0441\u0435\u0440\u0432\u0435\u0440\u043e\u0432.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- ServerHelper", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u0414\u043e\u0431\u0430\u0432\u043b\u0435\u043d. \u0412 \u0440\u0435\u0436\u0438\u043c Holyworld '\u0420\u044e\u043a\u0437\u0430\u043a'", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- HolyWorldHelper", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- LonyGriefHelper", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- ReallyworldHelper", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- SpookyTimeHelper", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- SunriseHelper", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- BravoHvHHelper", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- MineBlazeHelper", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- RakNetClient", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- ElytraMotion", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- GrimGlide", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- GuiMove", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- NoWeb", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- Speed", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- Spider", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- SuperFirework", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u0414\u043e\u0431\u0430\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- Timer", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- WaterSpeed", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- Ambience", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- Arrows", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- BlockOverlay", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- Chams", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- ChatBubbles", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- Cosmetics", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- Emotions", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- Hands", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d\u044b \u0448\u0435\u0439\u0434\u0435\u0440\u044b.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- HitMarkers", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- ItemRadius", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- MediaPlayer", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- Particles", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- Removals", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- SwordAnimations", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- DonateItemDetector", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u0414\u043e\u0431\u0430\u0432\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- \u0428\u0435\u0439\u0434\u0435\u0440\u044b/\u043d\u0435\u0431\u043e", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u0414\u043e\u0431\u0430\u0432\u043b\u0435\u043d\u044b.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- BaseFinder", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u0423\u0434\u0430\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("", lightning.product.K_1964_I$J_1907_R.G_564_y));
        this.Y_1740_V.add(new n_1700_B("- HoleESP", lightning.product.K_1964_I$J_1907_R.R_4764_Y));
        this.Y_1740_V.add(new n_1700_B("  - \u0423\u0434\u0430\u043b\u0435\u043d.", lightning.product.K_1964_I$J_1907_R.G_564_y));
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (!this.Q_2552_b) {
            this.C_2741_M.n_1700_B(1.0f);
        } else {
            this.C_2741_M.n_1700_B(0.0f);
        }
        float alpha = this.C_2741_M.n_1700_B();
        if (this.Q_2552_b && this.C_2741_M.R_4764_Y()) {
            super.closeScreen();
            return;
        }
        int baseScale = (int)MinecraftAccess.c_3005_b.RealmsServerPing().w_1457_N();
        MinecraftAccess.c_3005_b.s_956_w.n_1700_B(2.0f);
        float factor = (float)baseScale / 2.0f;
        int sx = (int)((float)mouseX * factor);
        int sy = (int)((float)mouseY * factor);
        if (this.Y_259_p != null) {
            this.Y_259_p.render(matrixStack, mouseX, mouseY, partialTicks);
        }
        y_4842_Z.n_1700_B.n_1700_B(2.0f, 4);
        this.n_1700_B(matrixStack, sx, sy, alpha);
        super.render(matrixStack, sx, sy, partialTicks);
        MinecraftAccess.c_3005_b.s_956_w.R_4764_Y();
    }

    @Override
    public void closeScreen() {
        this.Q_2552_b = true;
    }

    private void n_1700_B(g_221_o ms, int mouseX, int mouseY, float alpha) {
        float w = MinecraftAccess.c_3005_b.RealmsServerPing().Q_4569_t();
        float h = MinecraftAccess.c_3005_b.RealmsServerPing().M_182_A();
        float centerX = (w - 400.0f) / 2.0f;
        float centerY = (h - 380.0f) / 2.0f;
        boolean darkTheme = O_922_L.n_1700_B();
        int headerBg = G_564_y;
        int contentBg = G_564_y;
        int textPrimary = H_2506_c.n_1700_B(K_1964_I.n_1700_B(P_1922_E, u_1723_Y), alpha);
        int textInactive = H_2506_c.n_1700_B(K_1964_I.n_1700_B(v_4262_N, w_1484_f), alpha);
        float headerX = centerX + 20.0f;
        float headerY = centerY;
        float headerW = 333.0f;
        float headerH = 23.0f;
        F_489_x.n_1700_B(headerX, headerY, headerW, headerH, 8.0f, headerBg, alpha);
        F_489_x.J_1907_R(headerX, headerY, headerW, headerH, 8.0f, J_1907_R, (float)H_2506_c.G_564_y(J_1907_R) * alpha);
        float iconW = l_3370_o.t_148_a[18].n_1700_B("d");
        float titleW = l_3370_o.G_564_y[18].n_1700_B("Ch") + l_3370_o.G_564_y[18].n_1700_B("angelog") - 2.0f;
        float totalW = iconW + 6.0f + titleW;
        float offX = headerX + (headerW - totalW) / 2.0f;
        l_3370_o.t_148_a[18].n_1700_B(ms, "d", (double)offX, (double)(headerY + (headerH - l_3370_o.t_148_a[18].h_1847_R()) / 2.0f + 1.0f), H_2506_c.n_1700_B(n_1700_B, alpha));
        l_3370_o.G_564_y[18].n_1700_B(ms, "Ch", (double)(offX + iconW + 6.0f), (double)(headerY + (headerH - l_3370_o.G_564_y[18].h_1847_R()) / 2.0f + 1.0f), textPrimary);
        l_3370_o.G_564_y[18].n_1700_B(ms, "angelog", (double)(offX + iconW + 7.5f + l_3370_o.G_564_y[18].n_1700_B("Ch") - 2.0f), (double)(headerY + (headerH - l_3370_o.G_564_y[18].h_1847_R()) / 2.0f + 1.0f), textPrimary);
        float closeX = headerX + headerW + 4.0f;
        float closeY = headerY;
        boolean closeHovered = (float)mouseX >= closeX && (float)mouseX <= closeX + 23.0f && (float)mouseY >= closeY && (float)mouseY <= closeY + headerH;
        int closeBg = closeHovered ? R_4764_Y : G_564_y;
        F_489_x.n_1700_B(closeX, closeY, 23.0f, headerH, 8.0f, closeBg, alpha);
        F_489_x.J_1907_R(closeX, closeY, 23.0f, headerH, 8.0f, J_1907_R, (float)H_2506_c.G_564_y(J_1907_R) * alpha);
        l_3370_o.t_148_a[18].n_1700_B(ms, "z", (double)(closeX + (23.0f - l_3370_o.t_148_a[18].n_1700_B("z")) / 2.0f), (double)(closeY + (headerH - l_3370_o.t_148_a[18].h_1847_R()) / 2.0f + 1.0f), textPrimary);
        float contentX = centerX + 20.0f;
        float contentY = centerY + headerH + 8.0f;
        float contentW = 360.0f;
        float contentH = 380.0f - headerH - 8.0f - 20.0f;
        float totalContentH = (float)this.Y_1740_V.size() * 18.0f;
        if (totalContentH > contentH) {
            this.k_2293_S = u_530_F.n_1700_B(this.k_2293_S, -totalContentH + contentH, 0.0f);
            this.q_2307_F = u_530_F.n_1700_B(this.q_2307_F, -totalContentH + contentH, 0.0f);
        } else {
            this.q_2307_F = 0.0f;
            this.k_2293_S = 0.0f;
        }
        if (this.c_3005_b) {
            this.Z_875_P.J_1907_R(this.k_2293_S);
        }
        this.Z_875_P.n_1700_B(this.k_2293_S);
        this.q_2307_F = this.Z_875_P.n_1700_B();
        F_489_x.n_1700_B(contentX, contentY, contentW, contentH, 6.0f, contentBg, alpha);
        F_489_x.J_1907_R(contentX, contentY, contentW, contentH, 6.0f, J_1907_R, (float)H_2506_c.G_564_y(J_1907_R) * alpha);
        i_4833_u.n_1700_B(contentX, contentY, contentW, contentH);
        float lineY = contentY + 12.0f + this.q_2307_F;
        for (n_1700_B entry : this.Y_1740_V) {
            String line = entry.n_1700_B;
            switch (entry.J_1907_R.ordinal()) {
                case 0: {
                    float versionTitleW = l_3370_o.G_564_y[16].n_1700_B(line);
                    float titleX = contentX + (contentW - versionTitleW) / 2.0f;
                    l_3370_o.G_564_y[16].n_1700_B(ms, line, (double)titleX, (double)lineY, H_2506_c.n_1700_B(n_1700_B, alpha));
                    break;
                }
                case 1: {
                    float sectionW = l_3370_o.G_564_y[14].n_1700_B(line);
                    float sectionX = contentX + (contentW - sectionW) / 2.0f;
                    l_3370_o.G_564_y[14].n_1700_B(ms, line, (double)sectionX, (double)lineY, textPrimary);
                    break;
                }
                case 2: {
                    l_3370_o.G_564_y[14].n_1700_B(ms, line, (double)(contentX + 12.0f), (double)lineY, H_2506_c.n_1700_B(n_1700_B, alpha));
                    break;
                }
                default: {
                    l_3370_o.R_4764_Y[14].n_1700_B(ms, line, (double)(contentX + 12.0f), (double)lineY, textPrimary);
                }
            }
            lineY += 18.0f;
        }
        i_4833_u.n_1700_B();
        if (totalContentH > contentH) {
            float scrollbarX = contentX + contentW - 3.0f - 4.0f;
            float trackTop = contentY + 4.0f;
            float trackH = contentH - 8.0f;
            float thumbH = Math.max(20.0f, trackH * (trackH / totalContentH));
            float scrollRange = trackH - thumbH;
            float thumbY = trackTop + (scrollRange > 0.0f ? this.q_2307_F / (contentH - totalContentH) * scrollRange : 0.0f);
            int trackAlpha = u_530_F.u_1723_Y((float)(this.c_3005_b ? 20 : 10) * alpha);
            int thumbAlpha = u_530_F.u_1723_Y((float)(this.c_3005_b ? 60 : 25) * alpha);
            F_489_x.n_1700_B(scrollbarX, trackTop, 3.0f, trackH, 1.5f, H_2506_c.n_1700_B(n_1700_B, trackAlpha));
            F_489_x.J_1907_R(scrollbarX, trackTop, 3.0f, trackH, 1.5f, J_1907_R, (float)H_2506_c.G_564_y(J_1907_R) * alpha);
            F_489_x.n_1700_B(scrollbarX, thumbY, 3.0f, thumbH, 1.5f, H_2506_c.n_1700_B(n_1700_B, thumbAlpha));
            F_489_x.J_1907_R(scrollbarX, thumbY, 3.0f, thumbH, 1.5f, J_1907_R, (float)H_2506_c.G_564_y(J_1907_R) * alpha);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button != 0) {
            return super.mouseClicked(mouseX, mouseY, button);
        }
        int baseScale = (int)MinecraftAccess.c_3005_b.RealmsServerPing().w_1457_N();
        double factor = (double)baseScale / 2.0;
        double sx = mouseX * factor;
        double sy = mouseY * factor;
        float w = MinecraftAccess.c_3005_b.RealmsServerPing().Q_4569_t();
        float h = MinecraftAccess.c_3005_b.RealmsServerPing().M_182_A();
        float centerX = (w - 400.0f) / 2.0f;
        float centerY = (h - 380.0f) / 2.0f;
        float headerX = centerX + 20.0f;
        float headerW = 333.0f;
        float headerH = 23.0f;
        float closeX = headerX + headerW + 4.0f;
        if (sx >= (double)closeX && sx <= (double)(closeX + 23.0f) && sy >= (double)centerY && sy <= (double)(centerY + headerH)) {
            this.closeScreen();
            return true;
        }
        float contentX = centerX + 20.0f;
        float contentY = centerY + headerH + 8.0f;
        float contentW = 360.0f;
        float contentH = 380.0f - headerH - 8.0f - 20.0f;
        float totalContentH = (float)this.Y_1740_V.size() * 18.0f;
        if (totalContentH > contentH) {
            float scrollbarX = contentX + contentW - 3.0f - 4.0f;
            float trackTop = contentY + 4.0f;
            float trackH = contentH - 8.0f;
            float thumbH = Math.max(20.0f, trackH * (trackH / totalContentH));
            float scrollRange = trackH - thumbH;
            float thumbY = trackTop + (scrollRange > 0.0f ? this.q_2307_F / (contentH - totalContentH) * scrollRange : 0.0f);
            if (sx >= (double)(scrollbarX - 3.5f) && sx <= (double)(scrollbarX + 3.0f + 3.5f) && sy >= (double)contentY && sy <= (double)(contentY + contentH)) {
                if (sy >= (double)thumbY && sy <= (double)(thumbY + thumbH)) {
                    this.c_3005_b = true;
                    this.H_2857_Y = (float)sy;
                    this.A_4115_X = this.k_2293_S;
                } else {
                    double clickRatio = (sy - (double)trackTop - (double)(thumbH / 2.0f)) / (double)(trackH - thumbH);
                    clickRatio = u_530_F.n_1700_B(clickRatio, 0.0, 1.0);
                    float maxScroll = -totalContentH + contentH;
                    this.k_2293_S = (float)(clickRatio * (double)maxScroll);
                    this.c_3005_b = true;
                    this.H_2857_Y = (float)sy;
                    this.A_4115_X = this.k_2293_S;
                }
                return true;
            }
        }
        return super.mouseClicked(sx, sy, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (this.c_3005_b) {
            this.c_3005_b = false;
            return true;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.c_3005_b && button == 0) {
            int baseScale = (int)MinecraftAccess.c_3005_b.RealmsServerPing().w_1457_N();
            double factor = (double)baseScale / 2.0;
            double sy = mouseY * factor;
            float w = MinecraftAccess.c_3005_b.RealmsServerPing().Q_4569_t();
            float h = MinecraftAccess.c_3005_b.RealmsServerPing().M_182_A();
            float centerY = (h - 380.0f) / 2.0f;
            float contentY = centerY + 23.0f + 8.0f;
            float contentH = 329.0f;
            float totalContentH = (float)this.Y_1740_V.size() * 18.0f;
            float maxScroll = -totalContentH + contentH;
            float trackH = contentH - 8.0f;
            float thumbH = Math.max(20.0f, trackH * (trackH / totalContentH));
            float scrollRange = trackH - thumbH;
            if (scrollRange > 0.0f) {
                float mouseDelta = (float)sy - this.H_2857_Y;
                this.k_2293_S = u_530_F.n_1700_B(this.A_4115_X + mouseDelta * (maxScroll / scrollRange), maxScroll, 0.0f);
            }
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        int baseScale = (int)MinecraftAccess.c_3005_b.RealmsServerPing().w_1457_N();
        double factor = (double)baseScale / 2.0;
        double sx = mouseX * factor;
        double sy = mouseY * factor;
        float w = MinecraftAccess.c_3005_b.RealmsServerPing().Q_4569_t();
        float h = MinecraftAccess.c_3005_b.RealmsServerPing().M_182_A();
        float centerX = (w - 400.0f) / 2.0f;
        float centerY = (h - 380.0f) / 2.0f;
        float contentX = centerX + 20.0f;
        float contentY = centerY + 23.0f + 8.0f;
        float contentW = 360.0f;
        float contentH = 329.0f;
        boolean hovered = sx >= (double)contentX && sx <= (double)(contentX + contentW) && sy >= (double)contentY && sy <= (double)(contentY + contentH);
        float totalContentH = (float)this.Y_1740_V.size() * 18.0f;
        if (hovered && totalContentH > contentH) {
            this.k_2293_S += (float)(delta * 20.0);
            this.k_2293_S = u_530_F.n_1700_B(this.k_2293_S, -totalContentH + contentH, 0.0f);
            return true;
        }
        return false;
    }

    private static class n_1700_B {
        final String n_1700_B;
        final J_1907_R J_1907_R;

        n_1700_B(String text, J_1907_R type) {
            this.n_1700_B = text.replaceAll("\u00a7.", "");
            this.J_1907_R = type;
        }
    }

    private static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R();
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R();
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R();
        public static final /* enum */ J_1907_R G_564_y = new J_1907_R();
        private static final /* synthetic */ J_1907_R[] P_1922_E;

        public static J_1907_R[] values() {
            return (J_1907_R[])P_1922_E.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private static /* synthetic */ J_1907_R[] n_1700_B() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            P_1922_E = lightning.product.K_1964_I$J_1907_R.n_1700_B();
        }
    }
}



