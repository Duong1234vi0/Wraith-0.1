/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.io.File;
import java.security.SecureRandom;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import lightning.product.FluidTags;
import lightning.product.AxeItem;
import lightning.product.D_686_b;
import lightning.product.E_2115_e;
import lightning.product.AntiBot;
import lightning.product.E_3343_g;
import lightning.product.E_4612_l;
import lightning.product.F_1446_q;
import lightning.product.F_1573_j;
import lightning.product.AutoSwap;
import lightning.product.F_3698_k;
import lightning.product.F_747_P;
import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.Attributes;
import lightning.product.HitResult;
import lightning.product.AirStuck;
import lightning.product.I_4683_a;
import lightning.product.I_4817_s;
import lightning.product.NumberSetting;
import lightning.product.MobEffects;
import lightning.product.N_4263_v;
import lightning.product.MultiBooleanSetting;
import lightning.product.N_4890_q;
import lightning.product.ServerboundContainerClickPacket;
import lightning.product.P_3504_Q;
import lightning.product.P_4526_H;
import lightning.product.Q_1187_u;
import lightning.product.T_3952_j;
import lightning.product.T_437_o;
import lightning.product.V_4557_X;
import lightning.product.W_1707_M;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.a_1344_X;
import lightning.product.a_178_J;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.a_408_T;
import lightning.product.MinecraftAccess;
import lightning.product.b_2312_j;
import lightning.product.c_1514_x;
import lightning.product.d_2169_p;
import lightning.product.FreeCam;
import lightning.product.d_4500_Q;
import lightning.product.e_2866_D;
import lightning.product.e_4654_Y;
import lightning.product.f_2403_E;
import lightning.product.ElytraResolver;
import lightning.product.f_508_U;
import lightning.product.g_1734_y;
import lightning.product.g_4841_c;
import lightning.product.h_1015_G;
import lightning.product.i_4434_b;
import lightning.product.j_2302_z;
import lightning.product.Sprint;
import lightning.product.m_2262_U;
import lightning.product.m_4644_u;
import lightning.product.ClientBootstrap;
import lightning.product.o_2767_H;
import lightning.product.p_1183_T;
import lightning.product.BooleanSetting;
import lightning.product.q_1613_l;
import lightning.product.q_3115_L;
import lightning.product.KeyBindSetting;
import lightning.product.ModeSetting;
import lightning.product.Items;
import lightning.product.PacketCriticals;
import lightning.product.r_4790_y;
import lightning.product.r_4811_B;
import lightning.product.SwordItem;
import lightning.product.u_1934_K;
import lightning.product.u_488_m;
import lightning.product.u_530_F;
import lightning.product.u_925_K;
import lightning.product.v_570_f;
import lightning.product.x_1688_C;
import lightning.product.ModuleCategory;
import lightning.product.z_2025_Z;
import lightning.product.z_2311_U;
import lombok.Generated;

public class AttackAura
extends Module {
    public r_4811_B v_4262_N = null;
    private static boolean t_148_a = false;
    private final ModeSetting rotationMode = new ModeSetting("\u0420\u043e\u0442\u0430\u0446\u0438\u044f", "Reallyworld", "Reallyworld", "Grim", "LonyGrief", "Funtime Snap", "Fov90", "Snap Freeze", "Sloth test", "SpookyTime", "\u041d\u0435\u0439\u0440\u043e");
    private MultiBooleanSetting targetsOptions = new MultiBooleanSetting("\u041a\u043e\u0433\u043e \u0430\u0442\u0430\u043a\u043e\u0432\u0430\u0442\u044c", new BooleanSetting("\u0418\u0433\u0440\u043e\u043a\u043e\u0432", true), new BooleanSetting("\u0414\u0440\u0443\u0437\u0435\u0439", false), new BooleanSetting("\u0413\u043e\u043b\u044b\u0445", true), new BooleanSetting("\u0416\u0438\u0442\u0435\u043b\u0435\u0439", false), new BooleanSetting("\u0416\u0438\u0432\u043e\u0442\u043d\u044b\u0445", false), new BooleanSetting("\u041c\u043e\u0431\u043e\u0432", false));
    private final ModeSetting priorityMode = new ModeSetting("\u041f\u0440\u0438\u043e\u0440\u0438\u0442\u0435\u0442", "\u041e\u043f\u0442\u0438\u043c\u0430\u043b\u044c\u043d\u044b\u0439", "\u041e\u043f\u0442\u0438\u043c\u0430\u043b\u044c\u043d\u044b\u0439", "\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", "\u0417\u0434\u043e\u0440\u043e\u0432\u044c\u0435");
    private NumberSetting attackRangeSetting = new NumberSetting("\u0420\u0430\u0434\u0438\u0443\u0441 \u0430\u0442\u0430\u043a\u0438", 3.0f, 2.0f, 5.0f, 0.1f);
    private NumberSetting detectRangeSetting = new NumberSetting("\u0420\u0430\u0434\u0438\u0443\u0441 \u043e\u0431\u043d\u0430\u0440\u0443\u0436\u0435\u043d\u0438\u044f", 1.5f, 0.0f, 3.0f, 0.1f, () -> !this.rotationMode.isMode("Grim"));
    private MultiBooleanSetting skipAttackWhenOptions = new MultiBooleanSetting("\u041d\u0435 \u0431\u0438\u0442\u044c \u0435\u0441\u043b\u0438", new BooleanSetting("\u041e\u0442\u043a\u0440\u044b\u0442 \u043a\u043e\u043d\u0442\u0435\u0439\u043d\u0435\u0440", true), new BooleanSetting("\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0435\u0448\u044c \u0435\u0434\u0443", false), new BooleanSetting("\u0417\u0430\u0436\u0430\u0442 \u0449\u0438\u0442", false), new BooleanSetting("\u041d\u0430 \u044d\u043b\u0438\u0442\u0440\u0435", false));
    private ModeSetting movementCorrectionMode = new ModeSetting("\u041a\u043e\u0440\u0440\u0435\u043a\u0446\u0438\u044f \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u044f", "\u0421\u0444\u043e\u043a\u0443\u0441\u0438\u0440\u043e\u0432\u0430\u043d\u043d\u0430\u044f", "\u0421\u0444\u043e\u043a\u0443\u0441\u0438\u0440\u043e\u0432\u0430\u043d\u043d\u0430\u044f", "\u0421\u0432\u043e\u0431\u043e\u0434\u043d\u0430\u044f", "\u041f\u0440\u0435\u0441\u043b\u0435\u0434\u043e\u0432\u0430\u043d\u0438\u0435");
    private BooleanSetting onlyCriticalsEnabled = new BooleanSetting("\u0411\u0438\u0442\u044c \u0442\u043e\u043b\u044c\u043a\u043e \u043a\u0440\u0438\u0442\u0430\u043c\u0438", true);
    private BooleanSetting breakShieldEnabled = new BooleanSetting("\u041b\u043e\u043c\u0430\u0442\u044c \u0449\u0438\u0442", true);
    private BooleanSetting releaseShieldEnabled = new BooleanSetting("\u041e\u0442\u0436\u0438\u043c\u0430\u0442\u044c \u0449\u0438\u0442", true);
    private BooleanSetting raycastEnabled = new BooleanSetting("\u0420\u0430\u0439\u043a\u0430\u0441\u0442", true).R_4764_Y(() -> this.rotationMode.isMode("Vuntime"));
    private ModeSetting workOnMode = new ModeSetting("\u0420\u0430\u0431\u043e\u0442\u0430\u0442\u044c \u043d\u0430", "\u0412\u0435\u0437\u0434\u0435", () -> this.raycastEnabled.isEnabled(), "\u0412\u0435\u0437\u0434\u0435", "\u0417\u0435\u043c\u043b\u0435", "\u042d\u043b\u0438\u0442\u0440\u0435");
    private BooleanSetting resolverEnabled = new BooleanSetting("\u0420\u0435\u0437\u043e\u043b\u044c\u0432\u0435\u0440", true);
    private BooleanSetting resolverv2Enabled = new BooleanSetting("ResolverV2", false);
    private ModeSetting targetRenderMode = new ModeSetting("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0435\u043d\u0438\u0435 \u0446\u0435\u043b\u0438", "\u041a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u044b", "\u0420\u043e\u043c\u0431", "\u041a\u043e\u043b\u044c\u0446\u043e", "\u041a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u044b", "\u041f\u0440\u0438\u0437\u0440\u0430\u043a\u0438", "\u0420\u043e\u043c\u0431 New", "\u041f\u0435\u043d\u0442\u0430\u0433\u0440\u0430\u043c\u043c\u0430", "Triangle", "\u041d\u0435 \u043e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c");
    private BooleanSetting onlyWhenSpacePressedEnabled = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u043f\u0440\u0438 \u0437\u0430\u0436\u0430\u0442\u043e\u043c \u043f\u0440\u043e\u0431\u0435\u043b\u0435", false, () -> this.onlyCriticalsEnabled.isEnabled());
    private BooleanSetting noWallHitEnabled = new BooleanSetting("\u041d\u0435 \u0431\u0438\u0442\u044c \u0447\u0435\u0440\u0435\u0437 \u0441\u0442\u0435\u043d\u044b", false);
    private BooleanSetting allowDoorHitEnabled = new BooleanSetting("\u041f\u043e\u0437\u0432\u043e\u043b\u044f\u0442\u044c \u0431\u0438\u0442\u044c \u0447\u0435\u0440\u0435\u0437 \u0434\u0432\u0435\u0440\u0438", false, () -> this.noWallHitEnabled.isEnabled());
    private final ModeSetting wallHitBypassMode = new ModeSetting("Wall Hit Bypass", "Off", () -> this.noWallHitEnabled.isEnabled() == false, "Off", "Reallyworld", "Funtime", "Holyworld", "SpookyTime");
    private final KeyBindSetting holyworldBypassKeyKeyBind = new KeyBindSetting("Holyworld Bypass Key", () -> this.wallHitBypassMode.isMode("Holyworld"));
    private boolean n_3318_d;
    private boolean d_2427_y;
    private BooleanSetting tpsSyncEnabled = new BooleanSetting("\u0421\u0438\u043d\u0445\u0440\u043e\u043d\u0438\u0437\u0430\u0446\u0438\u044f \u0441 \u0422\u041f\u0421", false);
    private NumberSetting yawSpeedSetting = new NumberSetting("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c yaw", 0.9f, 0.1f, 2.0f, 0.1f, () -> this.rotationMode.isMode("\u041b\u0435\u0433\u0438\u0442\u043d\u044b\u0439"));
    private NumberSetting yawLimitSetting = new NumberSetting("\u041e\u0433\u0440\u0430\u043d\u0438\u0447\u0435\u043d\u0438\u0435 yaw", 10.0f, 1.0f, 180.0f, 1.0f, () -> this.rotationMode.isMode("\u041b\u0435\u0433\u0438\u0442\u043d\u044b\u0439"));
    private NumberSetting randomStrengthSetting = new NumberSetting("\u0421\u0438\u043b\u0430 \u0440\u0430\u043d\u0434\u043e\u043c\u0430", 0.3f, 0.0f, 2.0f, 0.1f, () -> this.rotationMode.isMode("\u041b\u0435\u0433\u0438\u0442\u043d\u044b\u0439"));
    private BooleanSetting rotatePitchEnabled = new BooleanSetting("\u041f\u043e\u0432\u043e\u0440\u0430\u0447\u0438\u0432\u0430\u0442\u044c pitch", true, () -> this.rotationMode.isMode("\u041b\u0435\u0433\u0438\u0442\u043d\u044b\u0439"));
    private BooleanSetting normalizeMouseEnabled = new BooleanSetting("\u041d\u043e\u0440\u043c\u0430\u043b\u0438\u0437\u043e\u0432\u0430\u0442\u044c \u043c\u044b\u0448\u044c", false, () -> this.rotationMode.isMode("\u041b\u0435\u0433\u0438\u0442\u043d\u044b\u0439"));
    private BooleanSetting snapOnAttackEnabled = new BooleanSetting("\u0421\u043d\u0430\u043f \u043d\u0430 \u0443\u0434\u0430\u0440", false, () -> this.rotationMode.isMode("\u041b\u0435\u0433\u0438\u0442\u043d\u044b\u0439"));
    private BooleanSetting onlyWithWeaponEnabled = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u0441 \u043e\u0440\u0443\u0436\u0438\u0435\u043c", false);
    private BooleanSetting autoTakeWeaponEnabled = new BooleanSetting("\u0410\u0432\u0442\u043e\u043c\u0430\u0442\u0438\u0447\u0435\u0441\u043a\u0438 \u0431\u0440\u0430\u0442\u044c \u043e\u0440\u0443\u0436\u0438\u0435", false);
    private ModeSetting onnxModelMode = new ModeSetting("ONNX \u041c\u043e\u0434\u0435\u043b\u044c", "\u0410\u0432\u0442\u043e", () -> this.rotationMode.isMode("\u041d\u0435\u0439\u0440\u043e"), "\u0410\u0432\u0442\u043e");
    private boolean g_164_R;
    private final z_2311_U X_933_l = new z_2311_U();
    private final N_4890_q Z_976_R = new N_4890_q();
    private long H_1990_U;
    private float N_2525_X;
    private float c_4037_x;
    private float g_2268_R;
    private float T_3594_S;
    private float D_4792_h;
    private float s_2632_s;
    private V_4557_X l_1233_K = new V_4557_X();
    private P_3504_Q z_1333_t;
    private final P_3504_Q O_508_d = new P_3504_Q(0.0f, 0.0f);
    private int r_715_M;
    private int A_1038_p = 0;
    private static final int i_1637_u = 1;
    private final P_3504_Q Ping = new P_3504_Q(0.0f, 0.0f);
    private boolean p_178_J;
    private int RealmsClientConfig = -1;
    private float f_4016_n;
    private float j_276_v;
    private boolean UploadStatus;
    public float[] w_1484_f = new float[10];
    private P_3504_Q e_1992_r = new P_3504_Q(0.0f, 0.0f);
    private int D_60_a;
    private boolean k_3961_g;
    private final f_508_U Ops = new f_508_U();
    private final m_4644_u h_4320_q = new m_4644_u();
    private P_3504_Q t_4219_U = null;
    private double V_1446_Y = 0.0;
    private double PlayerInfo = 0.0;
    private long V_1225_t = 0L;
    private float U_1241_n = 0.0f;
    private float q_1982_R = 0.0f;
    private long dtoRealmsServerAddress = 0L;
    private boolean w_612_n = false;
    private long RealmsServerPing = 0L;
    private boolean j_1564_a = false;
    private float M_1641_O = 0.0f;
    private float RealmsWorldOptions = 0.0f;
    private float RealmsWorldResetDto = 0.0f;
    private static final float RegionPingResult = 0.0034f;
    private static final float H_1083_k = 0.36f;
    private static final float R_3908_n = 0.0055f;
    private static final float ValueObject = 0.055f;
    private static final long F_1410_V = 65L;
    private static final float S_4022_R = -10.0f;
    private static final float l_4537_E = 89.0f;
    private boolean F_2624_D = false;
    private float RealmsDefaultUncaughtExceptionHandler = 0.0f;
    private float y_1700_S = 0.0f;
    private float u_744_e = 0.0f;
    private float RetryCallException = 0.0f;
    private long r_3651_U = 0L;
    private int RowButton = 0;
    private final SecureRandom LongRunningTask = new SecureRandom();
    private int j_2266_I = -1;
    private int S_980_j = 0;
    private long R_3077_Z = 0L;
    private int RealmsScreenWithCallback = 0;
    private float M_2677_i = 0.0f;
    private float c_132_F = 0.0f;
    private float g_4106_L = 0.0f;
    private float RealmsClientOutdatedScreen = 0.0f;
    private P_3504_Q W_3464_O = null;
    private Boolean RealmsConfirmScreen = null;
    private Boolean RealmsCreateRealmScreen = null;
    private r_4811_B C_290_v = null;
    private float w_728_N = 0.0f;
    private float J_4256_G = 0.0f;
    private float RealmsLongConfirmationScreen = 0.0f;
    private float RealmsLongRunningMcoTaskScreen = 0.0f;
    private double i_2993_w = 0.0;
    private double RealmsParentalConsentScreen = 0.0;
    private double O_2151_c = 0.0;
    private float s_1671_u = 0.0f;
    private float RealmsResetNormalWorldScreen = 1.8f;
    private int C_3538_G = 0;
    private int A_3959_N = 0;
    private float G_424_k = 0.0f;
    private float RealmsSettingsScreen = 0.0f;
    private float f_1043_S = 0.0f;
    private float F_4247_a = 0.0f;
    private float J_739_q = 0.0f;
    private long C_1162_e = 0L;
    private long D_4361_a = 0L;
    private int f_3449_S = 0;
    private boolean u_55_V = false;
    private P_3504_Q JsonUtils = null;
    private final Deque<J_1907_R> RealmsPersistence = new ArrayDeque<J_1907_R>();
    private e_2866_D y_2772_m = e_2866_D.n_1700_B;
    private e_2866_D H_1883_T = e_2866_D.n_1700_B;
    private r_4811_B d_4007_L;
    private float TextRenderingUtils;
    private float UploadTokenCache;
    private float U_1341_G;
    private float ClientBootstrap;
    private float o_2341_D;
    private float C_1269_X;
    private int x_612_B;
    private int t_1446_I;
    private n_1700_B j_306_t = lightning.product.AttackAura$n_1700_B.n_1700_B;
    private P_3504_Q F_3572_x;
    private e_2866_D L_1362_X = e_2866_D.n_1700_B;
    private float P_5000_x = 0.0f;

    public r_4811_B h_1847_R() {
        return this.v_4262_N;
    }

    public ModeSetting Q_4569_t() {
        return this.rotationMode;
    }

    public boolean M_182_A() {
        return this.raycastEnabled.isEnabled();
    }

    public P_3504_Q t_1786_h() {
        return this.z_1333_t;
    }

    public ModeSetting multiplayerClientSuggestionProvider() {
        return this.targetRenderMode;
    }

    public AttackAura() {
        super("AttackAura", ModuleCategory.n_1700_B);
        this.addSettings(this.rotationMode, this.yawSpeedSetting, this.yawLimitSetting, this.randomStrengthSetting, this.rotatePitchEnabled, this.normalizeMouseEnabled, this.snapOnAttackEnabled, this.onnxModelMode, this.targetsOptions, this.priorityMode, this.movementCorrectionMode, this.targetRenderMode, this.skipAttackWhenOptions, this.wallHitBypassMode, this.holyworldBypassKeyKeyBind, this.attackRangeSetting, this.detectRangeSetting, this.noWallHitEnabled, this.allowDoorHitEnabled, this.onlyCriticalsEnabled, this.onlyWhenSpacePressedEnabled, this.tpsSyncEnabled, this.raycastEnabled, this.workOnMode, this.breakShieldEnabled, this.releaseShieldEnabled, this.resolverEnabled, this.resolverv2Enabled, this.onlyWithWeaponEnabled, this.autoTakeWeaponEnabled);
        this.z_1737_N();
        this.Q_2552_b();
    }

    @Y_1740_V
    public void n_1700_B(m_2262_U e) {
        if (this.v_4262_N == null || AttackAura.c_3005_b.Y_259_p == null) {
            return;
        }
        if (this.A_1038_p()) {
            e.n_1700_B(this.e_1992_r.t_148_a);
            e.J_1907_R(this.e_1992_r.s_956_w);
        }
    }

    @Y_1740_V
    public void n_1700_B(z_2025_Z e) {
        if (e.n_1700_B() != AttackAura.c_3005_b.Y_259_p || !this.z_4693_k()) {
            return;
        }
        P_3504_Q visualRotation = this.g_221_o();
        e.J_1907_R(visualRotation.t_148_a);
        e.R_4764_Y(visualRotation.t_148_a);
        e.G_564_y(visualRotation.t_148_a);
        e.P_1922_E(visualRotation.t_148_a);
        e.u_1723_Y(visualRotation.s_956_w);
        e.v_4262_N(visualRotation.s_956_w);
        e.w_1484_f(visualRotation.s_956_w);
        e.t_148_a(visualRotation.s_956_w);
    }

    @Y_1740_V
    public void n_1700_B(i_4434_b e) {
        if (AttackAura.c_3005_b.Y_259_p == null) {
            return;
        }
        if ((Integer)this.holyworldBypassKeyKeyBind.getKey() == -1 || e.n_1700_B() != ((Integer)this.holyworldBypassKeyKeyBind.getKey()).intValue()) {
            return;
        }
        if (!this.wallHitBypassMode.isMode("Holyworld")) {
            return;
        }
        this.d_2427_y = e.J_1907_R();
        if (!this.d_2427_y) {
            this.n_3318_d = false;
        }
    }

    public static void w_1457_N() {
        t_148_a = true;
        try {
            AttackAura aura = (AttackAura)lightning.product.ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(AttackAura.class);
            if (aura != null) {
                aura.z_1737_N();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static boolean Y_601_j() {
        return t_148_a;
    }

    private void z_1737_N() {
        if (t_148_a) {
            this.rotationMode.setOptions("Reallyworld", "Grim", "LonyGrief", "Funtime Snap", "sex", "Fov90", "Snap Freeze", "Sloth test", "Holyworld", "SpookyTime", "\u041d\u0435\u0439\u0440\u043e");
        } else {
            this.rotationMode.setOptions("Reallyworld", "Grim", "LonyGrief", "Funtime Snap", "Fov90", "Snap Freeze", "Sloth test", "Holyworld", "SpookyTime", "\u041d\u0435\u0439\u0440\u043e");
        }
    }

    private float v_4276_D() {
        float customDistance;
        AirStuck airStuck = (AirStuck)lightning.product.ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(AirStuck.class);
        if (airStuck != null && (customDistance = airStuck.h_1847_R()) > 0.0f) {
            return customDistance;
        }
        return ((Float)this.attackRangeSetting.getValue()).floatValue();
    }

    private e_2866_D n_1700_B(r_4811_B entity) {
        if (entity == null) {
            return e_2866_D.n_1700_B;
        }
        return a_1344_X.n_1700_B((N_4263_v)entity);
    }

    private I_4817_s J_1907_R(r_4811_B entity) {
        if (entity == null) {
            return new I_4817_s(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        }
        return entity.i_601_W();
    }

    private e_2866_D R_4764_Y(r_4811_B entity) {
        return entity.s_4990_V();
    }

    private e_2866_D n_1700_B(r_4811_B entity, float partialTicks) {
        return entity.u_2550_I(partialTicks);
    }

    @Y_1740_V
    public void n_1700_B(T_437_o e) {
        if (this.v_4262_N != null) {
            e.n_1700_B(true);
        }
    }

    @Y_1740_V
    public void n_1700_B(a_178_J e) {
        if (this.rotationMode.isMode("SpookyTime") && this.D_60_a > 0 && q_3115_L.P_1922_E()) {
            e.n_1700_B(0.0f);
            e.J_1907_R(0.0f);
            --this.D_60_a;
            return;
        }
        if (r_4790_y.n_1700_B() && this.v_4262_N != null) {
            if (this.movementCorrectionMode.isMode("\u041f\u0440\u0435\u0441\u043b\u0435\u0434\u043e\u0432\u0430\u043d\u0438\u0435")) {
                e_2866_D chasePos = this.v_4262_N.s_4990_V();
                double dist = AttackAura.c_3005_b.Y_259_p.s_4990_V().u_1723_Y(chasePos);
                if (dist > 0.5) {
                    float toTargetYaw = (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(chasePos.G_564_y - AttackAura.c_3005_b.Y_259_p.l_2647_k(), chasePos.J_1907_R - AttackAura.c_3005_b.Y_259_p.O_3598_v())) - 90.0);
                    e.n_1700_B(1.0f);
                    e.J_1907_R(0.0f);
                    e.n_1700_B(true);
                    u_925_K.n_1700_B(e, toTargetYaw);
                    AttackAura.c_3005_b.P_4830_p.RealmsClientConfig.n_1700_B(true);
                    AttackAura.c_3005_b.Y_259_p.b_(true);
                } else {
                    e.n_1700_B(0.0f);
                    e.J_1907_R(0.0f);
                    e.n_1700_B(false);
                    e.J_1907_R(false);
                }
            } else if (this.movementCorrectionMode.isMode("\u0421\u0432\u043e\u0431\u043e\u0434\u043d\u0430\u044f")) {
                u_925_K.n_1700_B(e, d_2169_p.J_1907_R());
            } else {
                double dx = this.v_4262_N.O_3598_v() - AttackAura.c_3005_b.Y_259_p.O_3598_v();
                double dz = this.v_4262_N.l_2647_k() - AttackAura.c_3005_b.Y_259_p.l_2647_k();
                float targetYaw = (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(dz, dx)) - 90.0);
                u_925_K.n_1700_B(e, targetYaw);
            }
        }
        if (this.k_3961_g) {
            e.n_1700_B(0.0f);
            e.J_1907_R(0.0f);
            this.k_3961_g = false;
        }
    }

    @Y_1740_V
    public void n_1700_B(E_3343_g e) {
        this.v_4262_N = null;
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        this.V_1446_Y();
        r_4811_B oldTarget = this.v_4262_N;
        if (this.v_4262_N == null || !this.Q_4569_t(this.v_4262_N)) {
            this.v_4262_N = this.U_1241_n();
        }
        if (oldTarget != this.v_4262_N) {
            if (this.rotationMode.isMode("\u041d\u0435\u0439\u0440\u043e")) {
                this.r_715_M();
            }
            if (this.rotationMode.isMode("sex")) {
                this.g_2268_R();
            }
            if (this.rotationMode.isMode("Holyworld") && this.v_4262_N != null) {
                this.B_1668_F();
            }
            if (this.g_164_R()) {
                this.X_933_l();
            }
        }
        if (oldTarget != null && this.v_4262_N == null && (this.Ops() || this.h_4320_q() || this.k_3961_g())) {
            this.i_1637_u();
        }
        if (oldTarget != null && this.v_4262_N == null) {
            this.T_2506_i();
        }
        if (this.v_4262_N == null) {
            return;
        }
        if (this.noWallHitEnabled.isEnabled().booleanValue() && !this.allowDoorHitEnabled.isEnabled().booleanValue() && !AttackAura.c_3005_b.Y_259_p.c_3005_b(this.v_4262_N)) {
            this.v_4262_N = null;
            return;
        }
        if (this.wallHitBypassMode.isMode("Holyworld") && this.d_2427_y && !this.D_60_a()) {
            return;
        }
        F_3698_k elytraSample = lightning.product.ClientBootstrap.Y_601_j().J_1907_R().R_4764_Y();
        if (!(AttackAura.c_3005_b.Y_259_p.k_578_l() && this.v_4262_N.k_578_l() && elytraSample != null && elytraSample.n_1700_B((N_4263_v)this.v_4262_N))) {
            if (this.raycastEnabled.isEnabled().booleanValue() && this.PlayerInfo()) {
                float[] fArray;
                boolean inCobweb;
                boolean bl = inCobweb = this.J_1907_R((N_4263_v)AttackAura.c_3005_b.Y_259_p) || this.J_1907_R((N_4263_v)this.v_4262_N);
                if (this.g_164_R()) {
                    fArray = this.N_2525_X();
                } else {
                    float[] fArray2 = new float[2];
                    fArray2[0] = AttackAura.c_3005_b.Y_259_p.p_178_J;
                    fArray = fArray2;
                    fArray2[1] = AttackAura.c_3005_b.Y_259_p.f_4016_n;
                }
                float[] rayRot = fArray;
                boolean rayHits = inCobweb || v_570_f.n_1700_B(rayRot[0], rayRot[1], this.v_4276_D() + 1.0f, this.v_4262_N, this.j_276_v(), this.UploadStatus());
                this.UploadStatus = !rayHits && !this.u_2550_I(this.v_4262_N);
            } else {
                this.UploadStatus = false;
            }
        } else {
            this.UploadStatus = false;
        }
        if (!(this.l_1233_K() || this.t_4219_U() && this.d_2461_k())) {
            this.RealmsClientConfig();
        }
        if (this.g_164_R()) {
            this.H_1990_U();
        }
        this.z_1333_t();
    }

    @Y_1740_V
    public void n_1700_B(d_4500_Q e) {
        if (this.v_4262_N == null) {
            return;
        }
        e_2866_D vector3d = this.n_1700_B(this.v_4262_N);
        if (this.rotationMode.isMode("LonyGrief")) {
            this.t_1786_h(vector3d);
        } else if (this.rotationMode.isMode("Sloth test")) {
            this.M_182_A(vector3d);
        } else if (this.rotationMode.isMode("sex")) {
            this.T_3594_S();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Y_1740_V
    public void n_1700_B(o_2767_H event) {
        if (this.v_4262_N == null) return;
        if (AttackAura.c_3005_b.Y_259_p == null) {
            return;
        }
        F_3698_k predict = lightning.product.ClientBootstrap.Y_601_j().J_1907_R().R_4764_Y();
        if (predict != null && !this.rotationMode.isMode("Funtime Snap") && !this.rotationMode.isMode("SpookyTime") && predict.w_1484_f() && AttackAura.c_3005_b.Y_259_p.k_578_l() && predict.n_1700_B((N_4263_v)this.v_4262_N)) {
            e_2866_D e_2866_D2 = new e_2866_D(this.v_4262_N.q_1982_R, this.v_4262_N.dtoRealmsServerAddress, this.v_4262_N.w_612_n);
            if (this.v_4262_N.i_4434_b().G_564_y(e_2866_D2).u_1723_Y() >= 0.09) {
                this.O_508_d.t_148_a = AttackAura.c_3005_b.Y_259_p.p_178_J;
                this.O_508_d.s_956_w = AttackAura.c_3005_b.Y_259_p.f_4016_n;
                new j_2302_z();
                j_2302_z.n_1700_B(this.v_4262_N, this.O_508_d);
                this.O_508_d.s_956_w = u_530_F.n_1700_B(this.O_508_d.s_956_w, -90.0f, 90.0f);
                this.z_1333_t = new P_3504_Q(this.O_508_d.t_148_a, this.O_508_d.s_956_w);
                r_4790_y.n_1700_B(new F_1446_q(this.O_508_d.t_148_a, this.O_508_d.s_956_w), 180.0f, 1, 6);
                AttackAura.c_3005_b.Y_259_p.p_178_J = this.O_508_d.t_148_a;
                AttackAura.c_3005_b.Y_259_p.j_276_v = this.O_508_d.t_148_a;
                AttackAura.c_3005_b.Y_259_p.f_3449_S = this.O_508_d.t_148_a;
                AttackAura.c_3005_b.Y_259_p.C_1162_e = this.O_508_d.t_148_a;
                AttackAura.c_3005_b.Y_259_p.D_4361_a = this.O_508_d.t_148_a;
                AttackAura.c_3005_b.Y_259_p.JsonUtils = this.O_508_d.t_148_a;
                AttackAura.c_3005_b.Y_259_p.f_4016_n = this.O_508_d.s_956_w;
                AttackAura.c_3005_b.Y_259_p.UploadStatus = this.O_508_d.s_956_w;
                return;
            }
        }
        this.G_624_v();
    }

    @Y_1740_V
    public void n_1700_B(g_1734_y e) {
        if (this.v_4262_N == null) {
            return;
        }
        if (!this.l_1233_K() && this.t_4219_U() && this.d_2461_k()) {
            this.RealmsClientConfig();
        }
    }

    private boolean d_2461_k() {
        return c_3005_b != null && AttackAura.c_3005_b.Y_259_p != null && (AttackAura.c_3005_b.Y_259_p.J_1907_R(MobEffects.H_2857_Y) || this.J_1907_R((N_4263_v)AttackAura.c_3005_b.Y_259_p));
    }

    private void G_624_v() {
        if (this.v_4262_N == null) {
            return;
        }
        e_2866_D vector3d = this.n_1700_B(this.v_4262_N);
        P_3504_Q currentRot = new P_3504_Q(AttackAura.c_3005_b.Y_259_p.p_178_J, AttackAura.c_3005_b.Y_259_p.f_4016_n);
        if (this.A_1038_p()) {
            this.n_1700_B(currentRot, this.A_4115_X());
            return;
        }
        if (this.wallHitBypassMode.isMode("Reallyworld") && !this.noWallHitEnabled.isEnabled().booleanValue() && !AttackAura.c_3005_b.Y_259_p.c_3005_b(this.v_4262_N)) {
            this.h_1847_R(this.v_4262_N);
            return;
        }
        if (System.currentTimeMillis() - this.V_1225_t >= 200L) {
            double dx = this.v_4262_N.O_3598_v() - this.v_4262_N.r_715_M;
            double dy = this.v_4262_N.X_2960_b() - this.v_4262_N.A_1038_p;
            double dz = this.v_4262_N.l_2647_k() - this.v_4262_N.i_1637_u;
            double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
            this.PlayerInfo = this.V_1446_Y;
            this.V_1446_Y = 20.0 * distance;
            this.V_1225_t = System.currentTimeMillis();
        }
        switch ((String)this.rotationMode.getValue()) {
            case "Grim": {
                this.n_1700_B(currentRot);
                break;
            }
            case "Reallyworld": {
                this.u_2550_I(vector3d);
                break;
            }
            case "Holyworld": {
                this.M_588_G(vector3d);
                break;
            }
            case "Fov90": 
            case "Snap Freeze": {
                this.Q_4569_t(vector3d);
                break;
            }
            case "SpookyTime": {
                this.n_1700_B(this.v_4262_N, currentRot, this.A_4115_X());
                break;
            }
            case "\u041d\u0435\u0439\u0440\u043e": {
                this.multiplayerClientSuggestionProvider(vector3d);
                break;
            }
            case "LonyGrief": {
                break;
            }
            case "sex": {
                break;
            }
            case "Funtime Snap": {
                this.n_1700_B(this.v_4262_N, currentRot, vector3d);
                break;
            }
            case "Sloth test": {
                this.M_182_A(vector3d);
            }
        }
    }

    private void T_2506_i() {
        this.RealmsPersistence.clear();
        this.y_2772_m = e_2866_D.n_1700_B;
        this.H_1883_T = e_2866_D.n_1700_B;
        this.UploadTokenCache = 0.0f;
        this.U_1341_G = 0.0f;
        this.ClientBootstrap = 0.0f;
        this.o_2341_D = 0.0f;
        this.C_1269_X = 0.0f;
        this.x_612_B = 0;
        this.t_1446_I = 0;
        this.j_306_t = lightning.product.AttackAura$n_1700_B.n_1700_B;
    }

    private F_1446_q n_1700_B(e_2866_D point) {
        if (AttackAura.c_3005_b.Y_259_p == null) {
            return new F_1446_q(0.0f, 0.0f);
        }
        e_2866_D eyePos = AttackAura.c_3005_b.Y_259_p.u_2550_I(1.0f);
        e_2866_D diff = point.G_564_y(eyePos);
        double dist = Math.hypot(diff.J_1907_R, diff.G_564_y);
        float yaw = (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(diff.G_564_y, diff.J_1907_R)) - 90.0);
        float pitch = (float)(-Math.toDegrees(Math.atan2(diff.R_4764_Y, dist)));
        return new F_1446_q(yaw, u_530_F.n_1700_B(pitch, -89.0f, 89.0f));
    }

    private F_1446_q J_1907_R(e_2866_D position) {
        if (position == null || AttackAura.c_3005_b.Y_259_p == null) {
            return new F_1446_q(0.0f, 0.0f);
        }
        e_2866_D playerPos = AttackAura.c_3005_b.Y_259_p.s_4990_V().J_1907_R(0.0, AttackAura.c_3005_b.Y_259_p.P_1922_E(AttackAura.c_3005_b.Y_259_p.h_4320_q()), 0.0);
        float diffX = (float)(position.J_1907_R - playerPos.J_1907_R);
        float diffY = (float)(position.R_4764_Y - playerPos.R_4764_Y);
        float diffZ = (float)(position.G_564_y - playerPos.G_564_y);
        float dist = (float)Math.sqrt(diffX * diffX + diffZ * diffZ);
        float yaw = (float)(Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0);
        float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, dist)));
        return new F_1446_q(yaw, u_530_F.n_1700_B(pitch, -89.0f, 89.0f));
    }

    private boolean n_1700_B(e_2866_D point, float fov) {
        if (AttackAura.c_3005_b.Y_259_p == null) {
            return false;
        }
        F_1446_q rotation = this.J_1907_R(point);
        float deltaYaw = u_530_F.v_4262_N(rotation.R_4764_Y() - AttackAura.c_3005_b.Y_259_p.p_178_J);
        float deltaPitch = u_530_F.v_4262_N(rotation.G_564_y() - AttackAura.c_3005_b.Y_259_p.f_4016_n);
        return Math.abs(deltaYaw) <= fov && Math.abs(deltaPitch) <= fov;
    }

    private e_2866_D n_1700_B(N_4263_v entity) {
        if (AttackAura.c_3005_b.Y_259_p == null) {
            return e_2866_D.n_1700_B;
        }
        e_2866_D eye = AttackAura.c_3005_b.Y_259_p.u_2550_I(1.0f);
        I_4817_s box = entity.i_601_W();
        return new e_2866_D(u_530_F.n_1700_B(eye.J_1907_R, box.minX, box.maxX), u_530_F.n_1700_B(eye.R_4764_Y, box.minY, box.maxY), u_530_F.n_1700_B(eye.G_564_y, box.minZ, box.maxZ));
    }

    private e_2866_D n_1700_B(N_4263_v entity, e_2866_D end) {
        if (AttackAura.c_3005_b.Y_259_p == null || c_3005_b.g_2268_R() == null) {
            return end;
        }
        I_4817_s box = entity.i_601_W();
        e_2866_D start = c_3005_b.g_2268_R().u_2550_I(1.0f);
        e_2866_D direction = end.G_564_y(start).G_564_y();
        double tMin = -1.7976931348623157E308;
        double tMax = Double.MAX_VALUE;
        block5: for (int axis = 0; axis < 3; ++axis) {
            double startVal;
            double maxVal;
            double minVal;
            double d;
            switch (axis) {
                case 0: {
                    d = direction.J_1907_R;
                    minVal = box.minX;
                    maxVal = box.maxX;
                    startVal = start.J_1907_R;
                    break;
                }
                case 1: {
                    d = direction.R_4764_Y;
                    minVal = box.minY;
                    maxVal = box.maxY;
                    startVal = start.R_4764_Y;
                    break;
                }
                case 2: {
                    d = direction.G_564_y;
                    minVal = box.minZ;
                    maxVal = box.maxZ;
                    startVal = start.G_564_y;
                    break;
                }
                default: {
                    continue block5;
                }
            }
            if (Math.abs(d) < 1.0E-7) {
                if (!(startVal < minVal) && !(startVal > maxVal)) continue;
                return end;
            }
            double t1 = (minVal - startVal) / d;
            double t2 = (maxVal - startVal) / d;
            if (t1 > t2) {
                double t = t1;
                t1 = t2;
                t2 = t;
            }
            if (!((tMin = Math.max(tMin, t1)) > (tMax = Math.min(tMax, t2)))) continue;
            return end;
        }
        double distance = start.u_1723_Y(end);
        if (tMin > distance || tMin < 0.0) {
            return end;
        }
        return start.J_1907_R(direction.J_1907_R * tMin, direction.R_4764_Y * tMin, direction.G_564_y * tMin);
    }

    private static e_2866_D n_1700_B(N_4263_v entity, e_2866_D point, double padding) {
        I_4817_s box = entity.i_601_W();
        double minX = box.minX + padding;
        double minY = box.minY + padding;
        double minZ = box.minZ + padding;
        double maxX = box.maxX - padding;
        double maxY = box.maxY - padding;
        double maxZ = box.maxZ - padding;
        double cx = (box.minX + box.maxX) * 0.5;
        double cy = (box.minY + box.maxY) * 0.5;
        double cz = (box.minZ + box.maxZ) * 0.5;
        if (minX > maxX) {
            minX = maxX = cx;
        }
        if (minY > maxY) {
            minY = maxY = cy;
        }
        if (minZ > maxZ) {
            minZ = maxZ = cz;
        }
        return new e_2866_D(u_530_F.n_1700_B(point.J_1907_R, minX, maxX), u_530_F.n_1700_B(point.R_4764_Y, minY, maxY), u_530_F.n_1700_B(point.G_564_y, minZ, maxZ));
    }

    private static e_2866_D n_1700_B(r_4811_B target, e_2866_D point, double lead) {
        e_2866_D vel = target.I_4348_c();
        return point.J_1907_R(vel.J_1907_R * lead, vel.R_4764_Y * lead, vel.G_564_y * lead);
    }

    private static e_2866_D n_1700_B(r_4811_B target, ThreadLocalRandom random, double padding, e_2866_D bias) {
        I_4817_s box = target.i_601_W();
        double cx = (box.minX + box.maxX) * 0.5;
        double cy = (box.minY + box.maxY) * 0.5;
        double cz = (box.minZ + box.maxZ) * 0.5;
        double w = target.C_415_h();
        double h = target.v_165_F();
        if (random.nextDouble() < 0.88) {
            double centerYOffset = h * random.nextDouble(0.25, 0.65);
            e_2866_D centerPoint = new e_2866_D(cx + bias.J_1907_R, box.minY + centerYOffset + bias.R_4764_Y, cz + bias.G_564_y);
            return AttackAura.n_1700_B((N_4263_v)target, centerPoint, padding);
        }
        e_2866_D jitter = new e_2866_D(random.nextDouble(-w * 0.1, w * 0.1), random.nextDouble(-h * 0.12, h * 0.26), random.nextDouble(-w * 0.1, w * 0.1));
        return AttackAura.n_1700_B((N_4263_v)target, new e_2866_D(cx + bias.J_1907_R + jitter.J_1907_R, cy + bias.R_4764_Y + jitter.R_4764_Y, cz + bias.G_564_y + jitter.G_564_y), padding);
    }

    private boolean R_4764_Y(e_2866_D point) {
        if (AttackAura.c_3005_b.Y_259_p == null || AttackAura.c_3005_b.Y_601_j == null) {
            return false;
        }
        e_2866_D eyePos = AttackAura.c_3005_b.Y_259_p.u_2550_I(1.0f);
        BlockHitResult result = AttackAura.c_3005_b.Y_601_j.n_1700_B(new ClipContext(eyePos, point, ClipContext.n_1700_B.n_1700_B, ClipContext.J_1907_R.n_1700_B, AttackAura.c_3005_b.Y_259_p));
        return ((HitResult)result).R_4764_Y() == HitResult.n_1700_B.n_1700_B;
    }

    private static e_2866_D n_1700_B(e_2866_D from, e_2866_D to, double factor) {
        double f = u_530_F.n_1700_B(factor, 0.0, 1.0);
        return from.J_1907_R((to.J_1907_R - from.J_1907_R) * f, (to.R_4764_Y - from.R_4764_Y) * f, (to.G_564_y - from.G_564_y) * f);
    }

    private static float R_4764_Y(float from, float to, float factor) {
        return from + (to - from) * u_530_F.n_1700_B(factor, 0.0f, 1.0f);
    }

    private static float G_564_y(float value, float min, float max) {
        float abs = Math.abs(value);
        if (abs < min) {
            return Math.copySign(min, value == 0.0f ? 1.0f : value);
        }
        return Math.copySign(Math.min(abs, max), value);
    }

    private static float n_1700_B(float value) {
        float t = u_530_F.n_1700_B(value, 0.0f, 1.0f);
        return 1.0f - (float)Math.pow(1.0f - t, 3.0);
    }

    private void n_1700_B(r_4811_B target, ThreadLocalRandom random) {
        if (this.t_1446_I-- <= 0) {
            this.t_1446_I = 5 + random.nextInt(7);
            double w = target.C_415_h();
            double h = target.v_165_F();
            this.H_1883_T = new e_2866_D(random.nextDouble(-w * 0.06, w * 0.06), random.nextDouble(-h * 0.06, h * 0.22), random.nextDouble(-w * 0.06, w * 0.06));
        }
    }

    private void n_1700_B(ThreadLocalRandom random, float totalError) {
        if (this.x_612_B > 0) {
            --this.x_612_B;
        }
        switch (this.j_306_t.ordinal()) {
            case 1: {
                if (this.x_612_B > 0) break;
                this.j_306_t = lightning.product.AttackAura$n_1700_B.R_4764_Y;
                this.x_612_B = 3 + random.nextInt(3);
                this.ClientBootstrap = F_747_P.G_564_y(0.02f, 0.14f);
                this.o_2341_D = F_747_P.G_564_y(-0.05f, 0.08f);
                break;
            }
            case 2: {
                if (this.x_612_B > 0) break;
                this.j_306_t = lightning.product.AttackAura$n_1700_B.G_564_y;
                this.x_612_B = 2 + random.nextInt(3);
                break;
            }
            case 3: {
                if (totalError < 4.0f) {
                    this.j_306_t = lightning.product.AttackAura$n_1700_B.P_1922_E;
                    this.x_612_B = 4 + random.nextInt(3);
                    break;
                }
                if (this.x_612_B > 0) break;
                this.j_306_t = lightning.product.AttackAura$n_1700_B.n_1700_B;
                break;
            }
            case 4: {
                if (totalError > 10.0f) {
                    this.j_306_t = lightning.product.AttackAura$n_1700_B.n_1700_B;
                    break;
                }
                if (this.x_612_B > 0) break;
                this.j_306_t = lightning.product.AttackAura$n_1700_B.G_564_y;
                this.x_612_B = 2 + random.nextInt(2);
                break;
            }
            case 0: {
                if (totalError > 32.0f) {
                    this.j_306_t = lightning.product.AttackAura$n_1700_B.J_1907_R;
                    this.x_612_B = 1 + random.nextInt(3);
                    break;
                }
                if (!(totalError < 6.0f)) break;
                this.j_306_t = lightning.product.AttackAura$n_1700_B.P_1922_E;
                this.x_612_B = 4 + random.nextInt(2);
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(b_2312_j e) {
        e_2866_D compensated;
        e_2866_D lastDirNorm;
        double cosAngle;
        e_2866_D current = e.n_1700_B();
        if (!this.q_4610_l() || this.v_4262_N == null || AttackAura.c_3005_b.Y_259_p == null || !AttackAura.c_3005_b.Y_259_p.k_578_l()) {
            this.L_1362_X = current;
            return;
        }
        F_3698_k es = lightning.product.ClientBootstrap.Y_601_j().J_1907_R().R_4764_Y();
        if (es != null && es.Q_4569_t() && es.n_1700_B(this.v_4262_N)) {
            e_2866_D forward = this.P_1922_E(current);
            if (forward != null) {
                this.L_1362_X = forward;
                e.n_1700_B(forward);
                return;
            }
            this.L_1362_X = current;
            return;
        }
        e_2866_D toTarget = this.n_1700_B(this.v_4262_N);
        if (toTarget == null || toTarget.v_4262_N() < 1.0E-4) {
            this.L_1362_X = current;
            return;
        }
        e_2866_D horizDir = new e_2866_D(toTarget.J_1907_R, 0.0, toTarget.G_564_y);
        if (horizDir.v_4262_N() < 1.0E-4) {
            this.L_1362_X = current;
            return;
        }
        horizDir = horizDir.G_564_y();
        e_2866_D lastHoriz = new e_2866_D(this.L_1362_X.J_1907_R, 0.0, this.L_1362_X.G_564_y);
        double lastLen = Math.sqrt(lastHoriz.J_1907_R * lastHoriz.J_1907_R + lastHoriz.G_564_y * lastHoriz.G_564_y);
        double currentLen = Math.sqrt(current.J_1907_R * current.J_1907_R + current.G_564_y * current.G_564_y);
        if (lastLen < 1.0E-4 && currentLen < 1.0E-4) {
            this.L_1362_X = current;
            return;
        }
        double baseSpeed = currentLen;
        if (lastLen > 1.0E-4 && (cosAngle = (lastDirNorm = lastHoriz.G_564_y()).J_1907_R(horizDir)) < -0.5) {
            e_2866_D compensated2;
            double limitedSpeed = Math.min(baseSpeed, lastLen);
            e_2866_D newHoriz = horizDir.n_1700_B(limitedSpeed);
            this.L_1362_X = compensated2 = new e_2866_D(newHoriz.J_1907_R, current.R_4764_Y, newHoriz.G_564_y);
            e.n_1700_B(compensated2);
            return;
        }
        e_2866_D desiredHoriz = horizDir.n_1700_B(baseSpeed);
        double blend = 0.5;
        e_2866_D blendedHoriz = lastLen > 1.0E-4 ? new e_2866_D(u_530_F.G_564_y(blend, lastHoriz.J_1907_R, desiredHoriz.J_1907_R), 0.0, u_530_F.G_564_y(blend, lastHoriz.G_564_y, desiredHoriz.G_564_y)) : desiredHoriz;
        this.L_1362_X = compensated = new e_2866_D(blendedHoriz.J_1907_R, current.R_4764_Y, blendedHoriz.G_564_y);
        e.n_1700_B(compensated);
    }

    private void G_564_y(e_2866_D vector3d) {
        float yawToTarget = (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(vector3d.G_564_y, vector3d.J_1907_R)) - 90.0);
        float pitchToTarget = (float)(-Math.toDegrees(Math.atan2(vector3d.R_4764_Y, Math.hypot(vector3d.J_1907_R, vector3d.G_564_y))));
        float random1 = F_747_P.G_564_y(-3.0f, 3.0f);
        this.F_3572_x = new P_3504_Q(yawToTarget + random1, pitchToTarget + random1);
        r_4790_y.n_1700_B(new F_1446_q(this.F_3572_x.t_148_a, this.F_3572_x.s_956_w), 180.0f, 1, 12);
    }

    private e_2866_D P_1922_E(e_2866_D impulse) {
        if (AttackAura.c_3005_b.Y_259_p == null) {
            return null;
        }
        e_2866_D m = AttackAura.c_3005_b.Y_259_p.I_4348_c();
        e_2866_D h = new e_2866_D(m.J_1907_R, 0.0, m.G_564_y);
        double len = h.u_1723_Y();
        if (len < 0.03) {
            double dx = AttackAura.c_3005_b.Y_259_p.O_3598_v() - AttackAura.c_3005_b.Y_259_p.r_715_M;
            double dz = AttackAura.c_3005_b.Y_259_p.l_2647_k() - AttackAura.c_3005_b.Y_259_p.i_1637_u;
            h = new e_2866_D(dx, 0.0, dz);
            len = h.u_1723_Y();
        }
        if (len < 1.0E-4) {
            return null;
        }
        h = h.G_564_y();
        double spd = Math.sqrt(impulse.J_1907_R * impulse.J_1907_R + impulse.G_564_y * impulse.G_564_y);
        e_2866_D out = h.n_1700_B(spd);
        return new e_2866_D(out.J_1907_R, impulse.R_4764_Y, out.G_564_y);
    }

    private boolean q_4610_l() {
        F_3698_k elytraForward = lightning.product.ClientBootstrap.Y_601_j().J_1907_R().R_4764_Y();
        return elytraForward != null && AttackAura.c_3005_b.Y_259_p != null && AttackAura.c_3005_b.Y_259_p.k_578_l() && this.v_4262_N != null && elytraForward.n_1700_B((N_4263_v)this.v_4262_N);
    }

    private boolean z_4693_k() {
        F_3698_k elytraSample = lightning.product.ClientBootstrap.Y_601_j().J_1907_R().R_4764_Y();
        return elytraSample != null && this.v_4262_N != null && AttackAura.c_3005_b.Y_259_p != null && AttackAura.c_3005_b.Y_259_p.k_578_l() && this.v_4262_N.k_578_l() && elytraSample.Q_4569_t() && elytraSample.n_1700_B(this.v_4262_N);
    }

    private P_3504_Q g_221_o() {
        e_2866_D eye = AttackAura.c_3005_b.Y_259_p.u_2550_I(1.0f);
        e_2866_D pos = this.v_4262_N.s_4990_V().J_1907_R(0.0, (double)this.v_4262_N.v_165_F() * 0.5, 0.0);
        e_2866_D delta = pos.G_564_y(eye);
        float yaw = (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(delta.G_564_y, delta.J_1907_R)) - 90.0);
        float pitch = (float)(-Math.toDegrees(Math.atan2(delta.R_4764_Y, Math.hypot(delta.J_1907_R, delta.G_564_y))));
        return new P_3504_Q(yaw, u_530_F.n_1700_B(pitch, -90.0f, 90.0f));
    }

    private boolean e_2887_G() {
        if (!this.resolverv2Enabled.isEnabled().booleanValue() || this.v_4262_N == null || AttackAura.c_3005_b.Y_259_p == null) {
            return true;
        }
        if (!AttackAura.c_3005_b.Y_259_p.k_578_l() || !this.v_4262_N.k_578_l()) {
            return true;
        }
        F_3698_k elytraSample = lightning.product.ClientBootstrap.Y_601_j().J_1907_R().R_4764_Y();
        if (elytraSample != null && !elytraSample.n_1700_B(this.v_4262_N)) {
            return false;
        }
        I_4817_s flyBox = this.G_564_y(this.v_4262_N);
        return flyBox.intersects(AttackAura.c_3005_b.Y_259_p.i_601_W()) || flyBox.contains(AttackAura.c_3005_b.Y_259_p.u_2550_I(1.0f));
    }

    private I_4817_s G_564_y(r_4811_B entity) {
        I_4817_s currentBox = entity.i_601_W();
        e_2866_D targetMotion = this.P_1922_E(entity);
        if (targetMotion.v_4262_N() < 1.0E-4) {
            return currentBox.grow(0.1);
        }
        double lead = Math.max(1.0, (double)((Float)F_3698_k.u_2550_I.J_1907_R()).floatValue());
        return currentBox.offset(targetMotion.G_564_y().n_1700_B(lead)).grow(0.2, 0.12, 0.2);
    }

    private e_2866_D P_1922_E(r_4811_B entity) {
        e_2866_D motion = entity.I_4348_c();
        if (motion.v_4262_N() > 1.0E-4) {
            return motion;
        }
        return entity.s_4990_V().G_564_y(new e_2866_D(entity.r_715_M, entity.A_1038_p, entity.i_1637_u));
    }

    private void u_1723_Y(e_2866_D vector3d) {
        if (!v_570_f.n_1700_B(AttackAura.c_3005_b.Y_259_p.p_178_J, AttackAura.c_3005_b.Y_259_p.f_4016_n, this.v_4276_D(), this.v_4262_N, false)) {
            float targetYaw = (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(vector3d.G_564_y, vector3d.J_1907_R)) - 90.0);
            float targetPitch = (float)(-Math.toDegrees(Math.atan2(vector3d.R_4764_Y, Math.hypot(vector3d.J_1907_R, vector3d.G_564_y))));
            this.z_1333_t = new P_3504_Q(targetYaw, targetPitch);
        }
        r_4790_y.n_1700_B(new F_1446_q(this.z_1333_t.t_148_a, this.z_1333_t.s_956_w), 120.0f, 1, 6);
    }

    private float v_4262_N(e_2866_D vector3d) {
        return (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(vector3d.G_564_y, vector3d.J_1907_R)) - 90.0);
    }

    private float w_1484_f(e_2866_D vector3d) {
        return (float)(-Math.toDegrees(Math.atan2(vector3d.R_4764_Y, Math.hypot(vector3d.J_1907_R, vector3d.G_564_y))));
    }

    private float t_148_a(e_2866_D vector3d) {
        float amplitudeYaw = F_747_P.G_564_y(0.0f, 3.0f);
        return this.v_4262_N(vector3d) + F_747_P.G_564_y(-amplitudeYaw, amplitudeYaw);
    }

    private float s_956_w(e_2866_D vector3d) {
        float amplitudePitch = F_747_P.G_564_y(0.0f, 2.0f);
        return this.w_1484_f(vector3d) + F_747_P.G_564_y(-amplitudePitch, amplitudePitch);
    }

    private void u_2550_I(e_2866_D vector3d) {
        float targetYaw = this.v_4262_N(vector3d);
        float targetPitch = this.w_1484_f(vector3d);
        if (this.wallHitBypassMode.isMode("Reallyworld") && !this.noWallHitEnabled.isEnabled().booleanValue() && this.v_4262_N != null && !AttackAura.c_3005_b.Y_259_p.c_3005_b(this.v_4262_N) && !this.wallHitBypassMode.isMode("SpookyTime")) {
            if (this.A_4115_X() && vector3d.u_1723_Y() <= (double)this.v_4276_D()) {
                this.G_564_y(targetYaw, targetPitch);
            } else {
                this.G_564_y(d_2169_p.J_1907_R(), d_2169_p.R_4764_Y());
            }
            return;
        }
        r_4790_y.n_1700_B(new F_1446_q(this.t_148_a(vector3d), this.s_956_w(vector3d)), 360.0f, 1, 6);
    }

    private void B_1668_F() {
        if (AttackAura.c_3005_b.Y_259_p != null) {
            this.h_4320_q.n_1700_B(AttackAura.c_3005_b.Y_259_p);
        }
    }

    private void M_588_G(e_2866_D vector3d) {
        if (this.v_4262_N == null || AttackAura.c_3005_b.Y_259_p == null) {
            return;
        }
        float aimYaw = m_4644_u.J_1907_R(vector3d);
        float aimPitch = m_4644_u.n_1700_B(vector3d);
        boolean inRange = vector3d.u_1723_Y() <= (double)this.v_4276_D();
        m_4644_u.J_1907_R result = this.h_4320_q.n_1700_B(AttackAura.c_3005_b.Y_259_p, aimYaw, aimPitch, inRange, (float)vector3d.u_1723_Y());
        float turnSpeed = result.P_1922_E();
        r_4790_y.n_1700_B(new F_1446_q(result.n_1700_B(), result.J_1907_R()), turnSpeed, turnSpeed, 12, 6);
        this.z_1333_t = new P_3504_Q(result.R_4764_Y(), result.G_564_y());
        this.t_4219_U = new P_3504_Q(AttackAura.c_3005_b.Y_259_p.p_178_J, AttackAura.c_3005_b.Y_259_p.f_4016_n);
    }

    private boolean g_164_R() {
        return this.rotationMode.isMode("Fov90") || this.rotationMode.isMode("Snap Freeze");
    }

    private void X_933_l() {
        this.p_178_J = false;
        this.RealmsClientConfig = -1;
    }

    private boolean Z_976_R() {
        return this.RealmsClientConfig >= 0;
    }

    private float P_1922_E(float from, float to, float t) {
        return u_530_F.v_4262_N(from + u_530_F.v_4262_N(to - from) * u_530_F.n_1700_B(t, 0.0f, 1.0f));
    }

    private void P_4830_p(e_2866_D vector3d) {
        this.f_4016_n = this.p_178_J ? this.Ping.t_148_a : AttackAura.c_3005_b.Y_259_p.p_178_J;
        this.j_276_v = this.h_1847_R(vector3d)[0];
        this.RealmsClientConfig = 0;
        this.p_178_J = false;
    }

    private void H_1990_U() {
        if (this.RealmsClientConfig < 0) {
            return;
        }
        float t = (float)(this.RealmsClientConfig + 1) / 1.0f;
        this.Ping.t_148_a = this.P_1922_E(this.f_4016_n, this.j_276_v, t);
        ++this.RealmsClientConfig;
        if (this.RealmsClientConfig >= 1) {
            this.Ping.t_148_a = this.j_276_v;
            this.p_178_J = true;
            this.RealmsClientConfig = -1;
        }
    }

    private float[] h_1847_R(e_2866_D vector3d) {
        float yaw = (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(vector3d.G_564_y, vector3d.J_1907_R)) - 90.0);
        float pitch = (float)(-Math.toDegrees(Math.atan2(vector3d.R_4764_Y, Math.hypot(vector3d.J_1907_R, vector3d.G_564_y))));
        return new float[]{yaw, pitch};
    }

    private float[] N_2525_X() {
        if (this.v_4262_N == null || AttackAura.c_3005_b.Y_259_p == null) {
            return new float[]{AttackAura.c_3005_b.Y_259_p.p_178_J, AttackAura.c_3005_b.Y_259_p.f_4016_n};
        }
        e_2866_D vector3d = this.n_1700_B(this.v_4262_N);
        if (this.A_4115_X() && vector3d.u_1723_Y() <= (double)this.v_4276_D()) {
            float[] angles = this.h_1847_R(vector3d);
            float rayYaw = this.Z_976_R() ? this.j_276_v : angles[0];
            return new float[]{rayYaw, angles[1]};
        }
        if (this.Z_976_R()) {
            return new float[]{this.Ping.t_148_a, this.s_956_w(vector3d)};
        }
        if (this.p_178_J) {
            return new float[]{this.Ping.t_148_a, this.s_956_w(vector3d)};
        }
        return new float[]{AttackAura.c_3005_b.Y_259_p.p_178_J, AttackAura.c_3005_b.Y_259_p.f_4016_n};
    }

    private void n_1700_B(float yaw, float pitch) {
        AttackAura.c_3005_b.Y_259_p.p_178_J = yaw;
        AttackAura.c_3005_b.Y_259_p.f_4016_n = pitch;
        AttackAura.c_3005_b.Y_259_p.f_3449_S = yaw;
        AttackAura.c_3005_b.Y_259_p.C_1162_e = yaw;
        AttackAura.c_3005_b.Y_259_p.j_276_v = yaw;
        AttackAura.c_3005_b.Y_259_p.UploadStatus = pitch;
        AttackAura.c_3005_b.Y_259_p.JsonUtils = yaw;
        AttackAura.c_3005_b.Y_259_p.D_4361_a = yaw;
    }

    private void Q_4569_t(e_2866_D vector3d) {
        float pitch = this.s_956_w(vector3d);
        if (this.Z_976_R() || this.p_178_J) {
            this.n_1700_B(this.Ping.t_148_a, pitch);
            float yawSpeed = this.Z_976_R() ? 120.0f : 360.0f;
            r_4790_y.n_1700_B(new F_1446_q(this.Ping.t_148_a, pitch), yawSpeed, 1, 6);
        }
    }

    private void u_1723_Y(r_4811_B e) {
        I_4817_s bb = e.i_601_W();
        double w = bb.maxX - bb.minX;
        double h = bb.maxY - bb.minY;
        double d = bb.maxZ - bb.minZ;
        this.i_2993_w = (Math.random() - 0.5) * w * 0.12;
        this.RealmsParentalConsentScreen = (Math.random() - 0.5) * h * 0.11;
        this.O_2151_c = (Math.random() - 0.5) * d * 0.12;
    }

    public void Y_259_p() {
        if (this.h_4320_q() && this.v_4262_N != null) {
            this.C_3538_G = 1;
            this.A_3959_N = 0;
            this.G_424_k = this.J_4256_G;
        }
    }

    private float v_4262_N(r_4811_B e) {
        if (AttackAura.c_3005_b.Y_259_p == null) {
            return 0.0f;
        }
        e_2866_D eyes = AttackAura.c_3005_b.Y_259_p.u_2550_I(1.0f);
        I_4817_s bb = e.i_601_W();
        e_2866_D mid = new e_2866_D((bb.minX + bb.maxX) * 0.5, (bb.minY + bb.maxY) * 0.5, (bb.minZ + bb.maxZ) * 0.5);
        e_2866_D delta = mid.G_564_y(eyes);
        float needYaw = (float)Math.toDegrees(Math.atan2(delta.G_564_y, delta.J_1907_R)) - 90.0f;
        float needPitch = (float)(-Math.toDegrees(Math.atan2(delta.R_4764_Y, Math.hypot(delta.J_1907_R, delta.G_564_y))));
        return Math.abs(u_530_F.v_4262_N(needYaw - AttackAura.c_3005_b.Y_259_p.p_178_J)) + Math.abs(needPitch - AttackAura.c_3005_b.Y_259_p.f_4016_n);
    }

    private int J_1907_R(float angle) {
        if (angle > 130.0f) {
            return 140 + (int)(Math.random() * 90.0);
        }
        if (angle > 70.0f) {
            return 90 + (int)(Math.random() * 60.0);
        }
        if (angle > 30.0f) {
            return 45 + (int)(Math.random() * 35.0);
        }
        return 12 + (int)(Math.random() * 20.0);
    }

    private float[] R_4764_Y(float dist) {
        this.s_1671_u += 0.042f + (float)(Math.random() * 0.018);
        float scale = u_530_F.n_1700_B(dist / 4.5f, 0.25f, 1.0f);
        float amp = this.RealmsResetNormalWorldScreen * scale;
        float n1 = (float)Math.sin((double)this.s_1671_u * 0.87) * 0.38f;
        float n2 = (float)Math.sin((double)this.s_1671_u * 1.43 + 0.75) * 0.28f;
        float n3 = (float)Math.cos((double)this.s_1671_u * 1.18 + 0.35) * 0.32f;
        float n4 = (float)Math.cos((double)this.s_1671_u * 1.76 + 1.42) * 0.23f;
        float yN = (n1 + n2) * amp + ((float)Math.random() - 0.5f) * amp * 0.13f;
        float pN = (n3 + n4) * amp * 0.52f + ((float)Math.random() - 0.5f) * amp * 0.09f;
        return new float[]{yN, pN};
    }

    private float G_564_y(float x) {
        x = u_530_F.n_1700_B(x, 0.0f, 1.0f);
        return x * x * (3.0f - 2.0f * x);
    }

    private float P_1922_E(float x) {
        x = u_530_F.n_1700_B(x, 0.0f, 1.0f);
        return 1.0f - (1.0f - x) * (1.0f - x);
    }

    private float n_1700_B(float current, float target, float vel, float stiffness, float damping) {
        float diff = target - current;
        return vel + diff * stiffness - vel * damping;
    }

    private float u_1723_Y(float from, float to, float alpha) {
        alpha = u_530_F.n_1700_B(alpha, 0.0f, 1.0f);
        return from + u_530_F.v_4262_N(to - from) * alpha;
    }

    private boolean c_4037_x() {
        return AttackAura.c_3005_b.Y_259_p != null && AttackAura.c_3005_b.P_4830_p.O_508_d.G_564_y();
    }

    private boolean w_1484_f(r_4811_B t) {
        if (AttackAura.c_3005_b.Y_259_p == null || t == null) {
            return false;
        }
        e_2866_D pPos = AttackAura.c_3005_b.Y_259_p.s_4990_V();
        e_2866_D tPos = t.s_4990_V();
        e_2866_D pVel = new e_2866_D(AttackAura.c_3005_b.Y_259_p.O_3598_v() - AttackAura.c_3005_b.Y_259_p.r_715_M, AttackAura.c_3005_b.Y_259_p.X_2960_b() - AttackAura.c_3005_b.Y_259_p.A_1038_p, AttackAura.c_3005_b.Y_259_p.l_2647_k() - AttackAura.c_3005_b.Y_259_p.i_1637_u);
        e_2866_D tVel = new e_2866_D(t.O_3598_v() - t.r_715_M, t.X_2960_b() - t.A_1038_p, t.l_2647_k() - t.i_1637_u);
        e_2866_D toTarget = tPos.G_564_y(pPos).G_564_y();
        double rel = pVel.J_1907_R(toTarget) + tVel.J_1907_R(toTarget.n_1700_B(-1.0));
        double dist = Math.hypot(pPos.J_1907_R - tPos.J_1907_R, pPos.G_564_y - tPos.G_564_y);
        return rel > 0.05 && dist < 4.0;
    }

    private void n_1700_B(F_1446_q rot, float yawSpeed, float pitchSpeed) {
        F_1446_q cur = new F_1446_q(AttackAura.c_3005_b.Y_259_p);
        float yawDelta = u_530_F.v_4262_N(rot.R_4764_Y() - cur.R_4764_Y());
        float pitchDelta = rot.G_564_y() - cur.G_564_y();
        float clampedYaw = Math.min(Math.abs(yawDelta), yawSpeed);
        float clampedPitch = Math.min(Math.abs(pitchDelta), pitchSpeed);
        float newYaw = AttackAura.c_3005_b.Y_259_p.p_178_J + r_4790_y.n_1700_B(0.0f, u_530_F.n_1700_B(yawDelta, -clampedYaw, clampedYaw));
        float newPitch = AttackAura.c_3005_b.Y_259_p.f_4016_n + r_4790_y.n_1700_B(0.0f, u_530_F.n_1700_B(pitchDelta, -clampedPitch, clampedPitch));
        AttackAura.c_3005_b.Y_259_p.p_178_J = newYaw;
        AttackAura.c_3005_b.Y_259_p.f_4016_n = u_530_F.n_1700_B(newPitch, -90.0f, 90.0f);
        if (!d_2169_p.n_1700_B()) {
            d_2169_p.n_1700_B(true);
        }
    }

    private e_2866_D t_148_a(r_4811_B t) {
        I_4817_s bb = t.i_601_W();
        e_2866_D center = new e_2866_D((bb.minX + bb.maxX) * 0.5, (bb.minY + bb.maxY) * 0.5, (bb.minZ + bb.maxZ) * 0.5);
        e_2866_D vel = new e_2866_D(t.O_3598_v() - t.r_715_M, t.X_2960_b() - t.A_1038_p, t.l_2647_k() - t.i_1637_u);
        return center.P_1922_E(vel.n_1700_B(2.0));
    }

    private void M_182_A(e_2866_D vector3d) {
        boolean bothGliding;
        if (this.v_4262_N == null || AttackAura.c_3005_b.Y_259_p == null) {
            return;
        }
        boolean playerFlying = AttackAura.c_3005_b.Y_259_p.k_578_l();
        boolean bl = bothGliding = playerFlying && this.v_4262_N.k_578_l();
        if (this.C_290_v != this.v_4262_N) {
            this.C_290_v = this.v_4262_N;
            this.w_728_N = AttackAura.c_3005_b.Y_259_p.p_178_J;
            this.J_4256_G = AttackAura.c_3005_b.Y_259_p.f_4016_n;
            this.RealmsSettingsScreen = this.w_728_N;
            this.f_1043_S = this.J_4256_G;
            this.F_4247_a = this.w_728_N;
            this.J_739_q = this.J_4256_G;
            this.RealmsLongRunningMcoTaskScreen = 0.0f;
            this.RealmsLongConfirmationScreen = 0.0f;
            this.u_1723_Y(this.v_4262_N);
            this.A_3959_N = 0;
            this.C_3538_G = 0;
            this.s_1671_u = (float)(Math.random() * Math.PI * 2.0);
            float angleDiff = this.v_4262_N(this.v_4262_N);
            this.f_3449_S = this.J_1907_R(angleDiff);
            this.C_1162_e = System.currentTimeMillis();
            this.u_55_V = false;
        }
        e_2866_D eyePos = AttackAura.c_3005_b.Y_259_p.u_2550_I(1.0f);
        e_2866_D targetCenter = this.t_148_a(this.v_4262_N);
        float distance = (float)eyePos.u_1723_Y(targetCenter);
        if (!this.u_55_V) {
            long elapsed = System.currentTimeMillis() - this.C_1162_e;
            if (elapsed < (long)this.f_3449_S) {
                float jY = ((float)Math.random() - 0.5f) * 0.22f;
                float jP = ((float)Math.random() - 0.5f) * 0.14f;
                float outY = this.RealmsSettingsScreen + jY;
                float outP = u_530_F.n_1700_B(this.f_1043_S + jP, -89.0f, 89.0f);
                this.n_1700_B(new F_1446_q(outY, outP), 360.0f, 45.0f);
                this.RealmsSettingsScreen = AttackAura.c_3005_b.Y_259_p.p_178_J;
                this.f_1043_S = AttackAura.c_3005_b.Y_259_p.f_4016_n;
                return;
            }
            this.u_55_V = true;
        }
        float[] noise = this.R_4764_Y(distance);
        if (this.C_3538_G > 0) {
            ++this.A_3959_N;
            int upDuration = 25;
            int downDuration = 20;
            float targetPitchUp = -89.0f;
            if (this.C_3538_G == 1) {
                t = u_530_F.n_1700_B((float)this.A_3959_N / (float)upDuration, 0.0f, 1.0f);
                this.J_4256_G = u_530_F.v_4262_N(this.P_1922_E(t), this.G_424_k, targetPitchUp);
                if (this.A_3959_N >= upDuration) {
                    this.C_3538_G = 2;
                    this.A_3959_N = 0;
                }
            } else if (this.C_3538_G == 2) {
                t = u_530_F.n_1700_B((float)this.A_3959_N / (float)downDuration, 0.0f, 1.0f);
                this.J_4256_G = u_530_F.v_4262_N(this.G_564_y(t), targetPitchUp, this.G_424_k);
                if (this.A_3959_N >= downDuration) {
                    this.C_3538_G = 0;
                    this.A_3959_N = 0;
                }
            }
            float outY = this.w_728_N + noise[0];
            float outP = u_530_F.n_1700_B(this.J_4256_G + noise[1], -89.0f, 89.0f);
            this.n_1700_B(new F_1446_q(outY, outP), 360.0f, 45.0f);
            this.RealmsSettingsScreen = AttackAura.c_3005_b.Y_259_p.p_178_J;
            this.f_1043_S = AttackAura.c_3005_b.Y_259_p.f_4016_n;
            return;
        }
        if (Math.random() < 0.015) {
            this.u_1723_Y(this.v_4262_N);
        }
        e_2866_D aimPos = targetCenter.J_1907_R(this.i_2993_w, this.RealmsParentalConsentScreen, this.O_2151_c);
        e_2866_D direction = aimPos.G_564_y(eyePos);
        float wantYaw = (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(direction.G_564_y, direction.J_1907_R)) - 90.0);
        float wantPitch = (float)(-Math.toDegrees(Math.atan2(direction.R_4764_Y, Math.hypot(direction.J_1907_R, direction.G_564_y))));
        float diffYaw = u_530_F.v_4262_N(wantYaw - this.w_728_N);
        float diffPitch = wantPitch - this.J_4256_G;
        float speedMultiplier = 1.0f;
        boolean movingFwd = this.c_4037_x();
        boolean overtaking = this.w_1484_f(this.v_4262_N);
        if (movingFwd || overtaking) {
            speedMultiplier = 0.5f;
        }
        if (bothGliding) {
            float currentAngle = Math.abs(diffYaw) + Math.abs(diffPitch);
            if (currentAngle > 120.0f) {
                speedMultiplier = 0.18f;
            } else if (currentAngle > 80.0f) {
                p = this.G_564_y((currentAngle - 80.0f) / 40.0f);
                speedMultiplier = u_530_F.v_4262_N(p, 0.35f, 0.18f);
            } else if (currentAngle > 25.0f) {
                p = this.G_564_y((currentAngle - 25.0f) / 55.0f);
                speedMultiplier = u_530_F.v_4262_N(p, 0.65f, 0.35f);
            } else {
                speedMultiplier = 0.65f + 0.35f * (1.0f - currentAngle / 25.0f);
            }
        }
        float stiffness = (0.038f + (float)Math.random() * 0.009f) * speedMultiplier;
        float damping = 0.68f + 0.12f * (1.0f - speedMultiplier);
        float totalDiff = (float)Math.sqrt(diffYaw * diffYaw + diffPitch * diffPitch);
        if (totalDiff > 32.0f) {
            stiffness += 0.018f * speedMultiplier;
        } else if (totalDiff < 4.2f) {
            stiffness *= 0.48f;
        }
        this.RealmsLongConfirmationScreen = this.n_1700_B(this.w_728_N, this.w_728_N + diffYaw, this.RealmsLongConfirmationScreen, stiffness += u_530_F.n_1700_B((distance - 1.6f) / 7.5f, 0.0f, 0.045f) * speedMultiplier, damping);
        this.RealmsLongRunningMcoTaskScreen = this.n_1700_B(this.J_4256_G, wantPitch, this.RealmsLongRunningMcoTaskScreen, stiffness * 0.87f, damping);
        float maxVY = 7.5f * speedMultiplier;
        float maxVP = 5.8f * speedMultiplier;
        this.RealmsLongConfirmationScreen = u_530_F.n_1700_B(this.RealmsLongConfirmationScreen, -maxVY, maxVY);
        this.RealmsLongRunningMcoTaskScreen = u_530_F.n_1700_B(this.RealmsLongRunningMcoTaskScreen, -maxVP, maxVP);
        this.w_728_N += this.RealmsLongConfirmationScreen;
        this.J_4256_G = u_530_F.n_1700_B(this.J_4256_G + this.RealmsLongRunningMcoTaskScreen, -89.0f, 89.0f);
        float smoothFactor = bothGliding ? 0.3f + speedMultiplier * 0.4f : 0.85f;
        this.F_4247_a = this.u_1723_Y(this.F_4247_a, this.w_728_N, smoothFactor);
        this.J_739_q = this.u_1723_Y(this.J_739_q, this.J_4256_G, smoothFactor * 0.95f);
        float outY = this.F_4247_a + noise[0];
        float outP = u_530_F.n_1700_B(this.J_739_q + noise[1], -89.0f, 89.0f);
        this.n_1700_B(new F_1446_q(outY, outP), 360.0f, 45.0f);
        this.RealmsSettingsScreen = AttackAura.c_3005_b.Y_259_p.p_178_J;
        this.f_1043_S = AttackAura.c_3005_b.Y_259_p.f_4016_n;
        this.JsonUtils = new P_3504_Q(AttackAura.c_3005_b.Y_259_p.p_178_J, AttackAura.c_3005_b.Y_259_p.f_4016_n);
    }

    private void t_1786_h(e_2866_D vector3d) {
        if (this.v_4262_N == null) {
            return;
        }
        I_4817_s box = this.J_1907_R(this.v_4262_N);
        e_2866_D eyePos = AttackAura.c_3005_b.Y_259_p.u_2550_I(1.0f);
        e_2866_D centerPoint = box.getCenter();
        e_2866_D toTarget = centerPoint.G_564_y(eyePos);
        float centerYaw = (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(toTarget.G_564_y, toTarget.J_1907_R)) - 90.0);
        float centerPitch = (float)(-Math.toDegrees(Math.atan2(toTarget.R_4764_Y, Math.hypot(toTarget.J_1907_R, toTarget.G_564_y))));
        e_2866_D lookVec = AttackAura.c_3005_b.Y_259_p.G_564_y(AttackAura.c_3005_b.Y_259_p.f_4016_n, AttackAura.c_3005_b.Y_259_p.p_178_J);
        e_2866_D endVec = eyePos.P_1922_E(lookVec.n_1700_B(999.0));
        I_4817_s shrunkBox = box.shrink(0.5);
        boolean inBox = shrunkBox.rayTrace(eyePos, endVec).isPresent();
        if (this.j_1564_a) {
            if (this.M_1641_O >= -0.01f) {
                this.M_1641_O -= Math.abs(u_530_F.v_4262_N(centerYaw - this.RealmsWorldOptions)) > 80.0f ? 0.1f : 0.01f;
            }
            if (this.M_1641_O <= -0.01f) {
                this.j_1564_a = false;
            }
        } else {
            this.M_1641_O += 0.005f;
            if (this.M_1641_O >= 0.22f || inBox) {
                this.j_1564_a = true;
            }
        }
        float deltaYaw = u_530_F.v_4262_N(centerYaw - this.RealmsWorldOptions);
        float deltaPitch = centerPitch - this.RealmsWorldResetDto;
        float smooth = Math.max(this.M_1641_O, 0.0f);
        float newYaw = this.RealmsWorldOptions + deltaYaw * u_530_F.n_1700_B(smooth * 1.3f, 0.0f, 1.0f);
        float newPitch = this.RealmsWorldResetDto + deltaPitch * u_530_F.n_1700_B(smooth / 1.7f, 0.0f, 1.0f);
        float gcd = this.Ping();
        newYaw -= (newYaw - this.RealmsWorldOptions) % gcd;
        newPitch -= (newPitch - this.RealmsWorldResetDto) % gcd;
        newPitch = u_530_F.n_1700_B(newPitch, -89.0f, 89.0f);
        this.RealmsWorldOptions = newYaw;
        this.RealmsWorldResetDto = newPitch;
        r_4790_y.n_1700_B(new F_1446_q(newYaw, newPitch), 360.0f, 45.0f, 1, 1);
    }

    private void g_2268_R() {
        this.F_2624_D = false;
        this.RealmsDefaultUncaughtExceptionHandler = 0.0f;
        this.r_3651_U = 0L;
        if (AttackAura.c_3005_b.Y_259_p != null) {
            this.y_1700_S = AttackAura.c_3005_b.Y_259_p.p_178_J;
            this.RetryCallException = this.u_744_e = u_530_F.n_1700_B(AttackAura.c_3005_b.Y_259_p.f_4016_n, -10.0f, 89.0f);
        } else {
            this.y_1700_S = 0.0f;
            this.u_744_e = 0.0f;
            this.RetryCallException = 0.0f;
        }
    }

    private void T_3594_S() {
        if (this.v_4262_N == null || AttackAura.c_3005_b.Y_259_p == null) {
            return;
        }
        I_4817_s box = this.J_1907_R(this.v_4262_N);
        e_2866_D eyePos = AttackAura.c_3005_b.Y_259_p.u_2550_I(1.0f);
        e_2866_D centerPoint = box.getCenter();
        e_2866_D feetPoint = new e_2866_D(centerPoint.J_1907_R, box.minY + 0.04, centerPoint.G_564_y);
        e_2866_D yawVec = centerPoint.G_564_y(eyePos);
        float targetYaw = (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(yawVec.G_564_y, yawVec.J_1907_R)) - 90.0);
        long now = System.currentTimeMillis();
        if (this.r_3651_U == 0L || now - this.r_3651_U >= 65L) {
            e_2866_D pitchVec = feetPoint.G_564_y(eyePos);
            float feetPitch = (float)(-Math.toDegrees(Math.atan2(pitchVec.R_4764_Y, Math.hypot(pitchVec.J_1907_R, pitchVec.G_564_y))));
            this.RetryCallException = u_530_F.n_1700_B(feetPitch + F_747_P.G_564_y(-1.8f, 0.9f), -10.0f, 89.0f);
            this.r_3651_U = now;
        }
        e_2866_D lookVec = AttackAura.c_3005_b.Y_259_p.G_564_y(AttackAura.c_3005_b.Y_259_p.f_4016_n, AttackAura.c_3005_b.Y_259_p.p_178_J);
        e_2866_D endVec = eyePos.P_1922_E(lookVec.n_1700_B(999.0));
        I_4817_s shrunkBox = box.shrink(0.5);
        boolean inBox = shrunkBox.rayTrace(eyePos, endVec).isPresent();
        if (this.F_2624_D) {
            if (this.RealmsDefaultUncaughtExceptionHandler >= -0.01f) {
                this.RealmsDefaultUncaughtExceptionHandler -= Math.abs(u_530_F.v_4262_N(targetYaw - this.y_1700_S)) > 80.0f ? 0.06f : 0.006f;
            }
            if (this.RealmsDefaultUncaughtExceptionHandler <= -0.01f) {
                this.F_2624_D = false;
            }
        } else {
            this.RealmsDefaultUncaughtExceptionHandler += 0.0034f;
            if (this.RealmsDefaultUncaughtExceptionHandler >= 0.31f || inBox) {
                this.F_2624_D = true;
            }
        }
        float deltaYaw = u_530_F.v_4262_N(targetYaw - this.y_1700_S);
        float deltaPitch = this.RetryCallException - this.u_744_e;
        float smooth = Math.max(this.RealmsDefaultUncaughtExceptionHandler, 0.0f);
        float newYaw = this.y_1700_S + deltaYaw * u_530_F.n_1700_B(smooth * 1.25f, 0.0f, 1.0f);
        float newPitch = this.u_744_e + deltaPitch * u_530_F.n_1700_B(smooth / 1.85f, 0.0f, 1.0f);
        float gcd = this.Ping();
        newYaw -= (newYaw - this.y_1700_S) % gcd;
        newPitch -= (newPitch - this.u_744_e) % gcd;
        newPitch = u_530_F.n_1700_B(newPitch, -10.0f, 89.0f);
        this.y_1700_S = newYaw;
        this.u_744_e = newPitch;
        r_4790_y.n_1700_B(new F_1446_q(newYaw, newPitch), 55.0f, 18.0f, 1, 1);
    }

    public void Q_2552_b() {
        E_2115_e.n_1700_B();
        File modelsFolder = E_2115_e.R_4764_Y();
        if (modelsFolder == null || !modelsFolder.exists()) {
            return;
        }
        ArrayList<String> names = new ArrayList<String>();
        names.add("\u0410\u0432\u0442\u043e");
        File[] children = modelsFolder.listFiles();
        if (children != null) {
            for (File child : children) {
                String name;
                String fname = child.getName();
                if (child.isFile() && fname.toLowerCase(Locale.ROOT).endsWith(".onnx")) {
                    name = fname.substring(0, fname.length() - 5);
                } else {
                    File[] inside;
                    if (!child.isDirectory() || (inside = child.listFiles((d, n) -> n.toLowerCase(Locale.ROOT).endsWith(".onnx"))) == null || inside.length == 0) continue;
                    name = fname;
                }
                if (names.contains(name)) continue;
                names.add(name);
            }
        }
        this.onnxModelMode.setOptions(names.toArray(new String[0]));
    }

    public void C_2741_M() {
        this.Q_2552_b();
    }

    private void D_4792_h() {
        String selected = (String)this.onnxModelMode.getValue();
        if (selected.equals("\u0410\u0432\u0442\u043e")) {
            W_1707_M.J_1907_R();
        } else {
            W_1707_M.R_4764_Y(selected);
        }
    }

    public void k_2293_S() {
        this.Z_976_R.n_1700_B();
        this.X_933_l.n_1700_B();
        this.H_1990_U = 0L;
        this.g_164_R = true;
    }

    public void q_2307_F() {
        this.g_164_R = false;
        this.Z_976_R.J_1907_R();
    }

    public int Z_875_P() {
        return this.Z_976_R.P_1922_E();
    }

    public float[][] c_3005_b() {
        return (float[][])this.Z_976_R.R_4764_Y().toArray((T[])new float[0][]);
    }

    public float[][] H_2857_Y() {
        return (float[][])this.Z_976_R.G_564_y().toArray((T[])new float[0][]);
    }

    private boolean s_2632_s() {
        return "\u0410\u0432\u0442\u043e".equals(this.onnxModelMode.getValue());
    }

    private boolean l_1233_K() {
        return this.rotationMode.isMode("\u041d\u0435\u0439\u0440\u043e") && this.s_2632_s();
    }

    private void z_1333_t() {
        if (AttackAura.c_3005_b.P_4830_p == null || AttackAura.c_3005_b.P_4830_p.D_60_a == null) {
            return;
        }
        boolean down = AttackAura.c_3005_b.P_4830_p.D_60_a.G_564_y();
        this.Z_976_R.n_1700_B(this.g_164_R, this.rotationMode.isMode("\u041d\u0435\u0439\u0440\u043e"), this.s_2632_s(), this.v_4262_N, this.H_1990_U, down, AttackAura.c_3005_b.Y_259_p != null);
    }

    private float n_1700_B(r_4811_B t, float historyYaw0, float historyPitch0) {
        if (t == null || AttackAura.c_3005_b.Y_259_p == null) {
            return 0.0f;
        }
        e_2866_D eye = AttackAura.c_3005_b.Y_259_p.u_2550_I(1.0f);
        e_2866_D targetEye = this.n_1700_B(t, 1.0f);
        e_2866_D diff = targetEye.G_564_y(eye);
        if (diff.v_4262_N() < 1.0E-6) {
            return 0.0f;
        }
        float desiredYaw = (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(diff.G_564_y, diff.J_1907_R)) - 90.0);
        float desiredPitch = (float)(-Math.toDegrees(Math.atan2(diff.R_4764_Y, Math.sqrt(diff.J_1907_R * diff.J_1907_R + diff.G_564_y * diff.G_564_y))));
        float errYaw = Math.abs(u_530_F.v_4262_N(desiredYaw - historyYaw0));
        float errPitch = Math.abs(desiredPitch - historyPitch0);
        return errYaw + errPitch;
    }

    private void multiplayerClientSuggestionProvider(e_2866_D vector3d) {
        if (this.v_4262_N == null || AttackAura.c_3005_b.Y_259_p == null) {
            return;
        }
        if (this.g_164_R) {
            this.O_508_d();
            return;
        }
        if (this.s_2632_s()) {
            return;
        }
        String selectedProfile = (String)this.onnxModelMode.getValue();
        e_2866_D playerEye = AttackAura.c_3005_b.Y_259_p.u_2550_I(1.0f);
        I_4817_s aimBox = this.J_1907_R(this.v_4262_N);
        float lookPitch = this.X_933_l.J_1907_R() >= 1 ? this.X_933_l.J_1907_R(0) : AttackAura.c_3005_b.Y_259_p.f_4016_n;
        e_2866_D aimPoint = I_4683_a.n_1700_B(aimBox, playerEye, lookPitch);
        e_2866_D diff = I_4683_a.n_1700_B(aimPoint, playerEye, aimBox, this.v_4262_N.v_165_F(), this.D_4792_h);
        float[] neuroWindow = this.X_933_l.J_1907_R() >= 8 ? this.X_933_l.G_564_y() : null;
        float sens = e_4654_Y.n_1700_B(AttackAura.c_3005_b.P_4830_p != null ? (float)AttackAura.c_3005_b.P_4830_p.n_1700_B : 0.5f);
        g_4841_c.n_1700_B result = g_4841_c.n_1700_B(this.D_4792_h, this.s_2632_s, diff, selectedProfile, neuroWindow, this.H_1990_U, sens, this.Ping(), -30.0f);
        this.g_2268_R = this.N_2525_X;
        this.T_3594_S = this.c_4037_x;
        this.N_2525_X = result.n_1700_B;
        this.c_4037_x = result.J_1907_R;
        this.D_4792_h = result.n_1700_B;
        this.s_2632_s = result.J_1907_R;
        r_4790_y.n_1700_B(new F_1446_q(result.n_1700_B, result.J_1907_R), 360.0f, 1, 6);
        this.O_508_d();
    }

    private void O_508_d() {
        if (AttackAura.c_3005_b.Y_259_p == null) {
            return;
        }
        this.X_933_l.n_1700_B(AttackAura.c_3005_b.Y_259_p.p_178_J, AttackAura.c_3005_b.Y_259_p.f_4016_n);
        ++this.H_1990_U;
        if (this.g_164_R) {
            this.Z_976_R.n_1700_B(true, this.X_933_l, this.H_1990_U, this.v_4262_N, this::n_1700_B);
        }
    }

    private e_2866_D J_1907_R(float yaw, float pitch) {
        float yawRad = yaw * ((float)Math.PI / 180);
        float pitchRad = pitch * ((float)Math.PI / 180);
        float x = -u_530_F.n_1700_B(yawRad) * u_530_F.J_1907_R(pitchRad);
        float y = -u_530_F.n_1700_B(pitchRad);
        float z = u_530_F.J_1907_R(yawRad) * u_530_F.J_1907_R(pitchRad);
        return new e_2866_D(x, y, z).G_564_y();
    }

    private float[] w_1457_N(e_2866_D dir) {
        float yaw = (float)Math.toDegrees(Math.atan2(-dir.J_1907_R, dir.G_564_y));
        float pitch = (float)Math.toDegrees(Math.atan2(-dir.R_4764_Y, Math.sqrt(dir.J_1907_R * dir.J_1907_R + dir.G_564_y * dir.G_564_y)));
        return new float[]{yaw, pitch};
    }

    private P_3504_Q n_1700_B(float fromYaw, float fromPitch, float toYaw, float toPitch) {
        return new P_3504_Q(u_530_F.v_4262_N(toYaw - fromYaw), toPitch - fromPitch);
    }

    private void r_715_M() {
        this.X_933_l.n_1700_B();
        this.H_1990_U = 0L;
        if (AttackAura.c_3005_b.Y_259_p != null) {
            this.D_4792_h = AttackAura.c_3005_b.Y_259_p.p_178_J;
            this.s_2632_s = AttackAura.c_3005_b.Y_259_p.f_4016_n;
            this.N_2525_X = this.D_4792_h;
            this.c_4037_x = this.s_2632_s;
            this.g_2268_R = this.N_2525_X;
            this.T_3594_S = this.c_4037_x;
        }
    }

    private void n_1700_B(r_4811_B entity, P_3504_Q currentRotation, boolean isAttack) {
        if (this.A_1038_p()) {
            this.n_1700_B(currentRotation, isAttack);
            return;
        }
        u_488_m.n_1700_B(entity, isAttack, false, this.RowButton, this.e_1992_r);
        currentRotation.t_148_a = this.e_1992_r.t_148_a;
        currentRotation.s_956_w = this.e_1992_r.s_956_w;
    }

    private boolean A_1038_p() {
        return this.wallHitBypassMode.isMode("SpookyTime") && this.noWallHitEnabled.isEnabled() == false && this.v_4262_N != null && AttackAura.c_3005_b.Y_259_p != null && !AttackAura.c_3005_b.Y_259_p.c_3005_b(this.v_4262_N);
    }

    private void n_1700_B(P_3504_Q currentRotation, boolean isAttack) {
        u_488_m.n_1700_B(this.v_4262_N, isAttack, this.RowButton, this.e_1992_r);
        currentRotation.t_148_a = this.e_1992_r.t_148_a;
        currentRotation.s_956_w = this.e_1992_r.s_956_w;
    }

    private void n_1700_B(r_4811_B entity, P_3504_Q currentRotation, e_2866_D vector3d) {
        if (entity == null || AttackAura.c_3005_b.Y_259_p == null || vector3d == null) {
            return;
        }
        F_1446_q currentAngle = new F_1446_q(AttackAura.c_3005_b.Y_259_p.p_178_J, AttackAura.c_3005_b.Y_259_p.f_4016_n);
        int count = this.RowButton;
        long now = System.currentTimeMillis();
        if (count != this.j_2266_I) {
            ++this.S_980_j;
            this.j_2266_I = count;
        }
        if (this.S_980_j >= 40 && this.R_3077_Z == 0L) {
            this.R_3077_Z = now + 350L;
            this.S_980_j = 0;
            this.RealmsScreenWithCallback = 0;
        }
        if (this.R_3077_Z != 0L) {
            if (now < this.R_3077_Z) {
                long elapsed = now - (this.R_3077_Z - 350L);
                if (this.RealmsScreenWithCallback == 0 && elapsed >= 50L) {
                    AttackAura.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
                    this.RealmsScreenWithCallback = 1;
                } else if (this.RealmsScreenWithCallback == 1 && elapsed >= 180L) {
                    AttackAura.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
                    this.RealmsScreenWithCallback = 2;
                }
                float missYaw = currentAngle.R_4764_Y() + this.LongRunningTask.nextFloat() * 6.0f - 3.0f;
                r_4790_y.n_1700_B(new F_1446_q(missYaw, -80.0f), 360.0f, 1, 6);
                return;
            }
            this.R_3077_Z = 0L;
        }
        float targetYaw = (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(vector3d.G_564_y, vector3d.J_1907_R)) - 90.0);
        float targetPitch = (float)(-Math.toDegrees(Math.atan2(vector3d.R_4764_Y, Math.hypot(vector3d.J_1907_R, vector3d.G_564_y))));
        F_1446_q targetAngle = new F_1446_q(targetYaw, targetPitch);
        F_1446_q angleDelta = F_1446_q.n_1700_B(currentAngle, targetAngle);
        float yawDelta = angleDelta.R_4764_Y();
        float pitchDelta = angleDelta.G_564_y();
        float rotationDifference = (float)Math.hypot(Math.abs(yawDelta), Math.abs(pitchDelta));
        if (rotationDifference < 0.01f) {
            rotationDifference = 1.0f;
        }
        int suck = count % 3;
        float timeRandom = (float)this.l_1233_K.J_1907_R() / 80.0f + (float)(count % 6);
        P_3504_Q randomAngle = switch (suck) {
            case 0 -> new P_3504_Q((float)Math.cos(timeRandom), (float)Math.sin(timeRandom));
            case 1 -> new P_3504_Q((float)Math.sin(timeRandom), (float)Math.cos(timeRandom));
            case 2 -> new P_3504_Q((float)Math.sin(timeRandom), (float)(-Math.cos(timeRandom)));
            default -> new P_3504_Q((float)(-Math.cos(timeRandom)), (float)Math.sin(timeRandom));
        };
        this.g_4106_L = this.R_4764_Y(11.0f, 20.0f) * randomAngle.t_148_a;
        this.RealmsClientOutdatedScreen = this.R_4764_Y(1.0f, 6.0f) * randomAngle.s_956_w + this.R_4764_Y(2.0f, 1.0f) * (float)Math.cos((double)System.currentTimeMillis() / 8000.0);
        this.M_2677_i += this.g_4106_L - this.M_2677_i;
        this.c_132_F += this.RealmsClientOutdatedScreen - this.c_132_F;
        float speed = this.A_4115_X() ? 0.9f : (this.LongRunningTask.nextBoolean() ? 0.1f : 0.2f);
        float lineYaw = Math.abs(yawDelta / rotationDifference) * 180.0f;
        float linePitch = Math.abs(pitchDelta / rotationDifference) * 180.0f;
        float moveYaw = u_530_F.n_1700_B(yawDelta, -lineYaw, lineYaw);
        float movePitch = u_530_F.n_1700_B(pitchDelta, -linePitch, linePitch);
        float lerpSpeed = this.R_4764_Y(speed, speed + 0.6f);
        float newYaw = u_530_F.v_4262_N(lerpSpeed, currentAngle.R_4764_Y(), currentAngle.R_4764_Y() + moveYaw) + this.M_2677_i;
        float newPitch = u_530_F.v_4262_N(lerpSpeed, currentAngle.G_564_y(), currentAngle.G_564_y() + movePitch) + this.c_132_F;
        r_4790_y.n_1700_B(new F_1446_q(newYaw, u_530_F.n_1700_B(newPitch, -89.0f, 89.0f)), 360.0f, 1, 6);
    }

    private float R_4764_Y(float min, float max) {
        return u_530_F.v_4262_N(this.LongRunningTask.nextFloat(), min, max);
    }

    private void i_1637_u() {
        if (AttackAura.c_3005_b.Y_259_p == null) {
            return;
        }
        P_3504_Q look = null;
        if (this.Ops()) {
            look = this.W_3464_O;
            this.W_3464_O = null;
            this.RealmsConfirmScreen = null;
            this.RealmsCreateRealmScreen = null;
        } else if (this.h_4320_q()) {
            look = this.JsonUtils;
            this.JsonUtils = null;
            this.C_290_v = null;
            this.RealmsLongRunningMcoTaskScreen = 0.0f;
            this.RealmsLongConfirmationScreen = 0.0f;
            this.A_3959_N = 0;
            this.C_3538_G = 0;
            this.u_55_V = false;
        } else if (this.k_3961_g()) {
            look = this.t_4219_U;
            this.t_4219_U = null;
            this.B_1668_F();
        }
        if (look == null) {
            return;
        }
        AttackAura.c_3005_b.Y_259_p.p_178_J = look.t_148_a;
        AttackAura.c_3005_b.Y_259_p.f_3449_S = look.t_148_a;
        AttackAura.c_3005_b.Y_259_p.C_1162_e = look.t_148_a;
        AttackAura.c_3005_b.Y_259_p.f_4016_n = look.s_956_w;
        AttackAura.c_3005_b.Y_259_p.j_276_v = look.t_148_a;
        AttackAura.c_3005_b.Y_259_p.UploadStatus = look.s_956_w;
        d_2169_p.n_1700_B(look.t_148_a);
        d_2169_p.J_1907_R(look.s_956_w);
        r_4790_y.J_1907_R();
    }

    private float Ping() {
        return AttackAura.p_178_J();
    }

    private static float p_178_J() {
        double sensitivity = MinecraftAccess.c_3005_b.P_4830_p.n_1700_B;
        double value = sensitivity * 0.6 + 0.2;
        double result = Math.pow(value, 3.0) * 0.8;
        return (float)result * 0.15f;
    }

    private void n_1700_B(P_3504_Q currentRot) {
        if (this.v_4262_N == null) {
            return;
        }
        ElytraResolver elytraFakeLag = lightning.product.ClientBootstrap.Y_601_j().J_1907_R().P_1922_E();
        if (!AttackAura.c_3005_b.Y_259_p.k_578_l()) {
            P_3504_Q targetRotations = this.s_956_w(this.v_4262_N);
            float yawDiff = u_530_F.v_4262_N(targetRotations.t_148_a - currentRot.t_148_a);
            float pitchDiff = targetRotations.s_956_w - currentRot.s_956_w;
            float speed = (float)(0.1 + Math.random() * 0.4);
            currentRot.t_148_a += yawDiff * speed;
            currentRot.s_956_w += pitchDiff * speed;
            currentRot.s_956_w = u_530_F.n_1700_B(currentRot.s_956_w, -90.0f, 90.0f);
            float gcd = this.Ping();
            currentRot.t_148_a -= currentRot.t_148_a % gcd;
            currentRot.s_956_w -= currentRot.s_956_w % gcd;
            r_4790_y.n_1700_B(new F_1446_q(currentRot.t_148_a, currentRot.s_956_w), 360.0f, 1, 6);
        } else if (elytraFakeLag != null && elytraFakeLag.w_1484_f()) {
            float effectiveDistance = this.v_4276_D();
            this.Ops.n_1700_B(this.v_4262_N, currentRot, effectiveDistance * effectiveDistance, this.V_1446_Y, this.PlayerInfo);
            float gcd = this.Ping();
            currentRot.t_148_a -= currentRot.t_148_a % gcd;
            currentRot.s_956_w -= currentRot.s_956_w % gcd;
            currentRot.s_956_w = u_530_F.n_1700_B(currentRot.s_956_w, -90.0f, 90.0f);
            r_4790_y.n_1700_B(new F_1446_q(currentRot.t_148_a, currentRot.s_956_w), 180.0f, 1, 6);
            this.F_3572_x = currentRot;
            AttackAura.c_3005_b.Y_259_p.p_178_J = currentRot.t_148_a;
            AttackAura.c_3005_b.Y_259_p.f_4016_n = currentRot.s_956_w;
        } else {
            P_3504_Q targetRotations = this.s_956_w(this.v_4262_N);
            float yawDiff = u_530_F.v_4262_N(targetRotations.t_148_a - currentRot.t_148_a);
            float pitchDiff = targetRotations.s_956_w - currentRot.s_956_w;
            float speed = (float)(0.6 + Math.random() * 0.3);
            currentRot.t_148_a += yawDiff * speed;
            currentRot.s_956_w += pitchDiff * speed;
            currentRot.s_956_w = u_530_F.n_1700_B(currentRot.s_956_w, -90.0f, 90.0f);
            float gcd = this.Ping();
            currentRot.t_148_a -= currentRot.t_148_a % gcd;
            currentRot.s_956_w -= currentRot.s_956_w % gcd;
            r_4790_y.n_1700_B(new F_1446_q(currentRot.t_148_a, currentRot.s_956_w), 180.0f, 1, 6);
            this.F_3572_x = currentRot;
            AttackAura.c_3005_b.Y_259_p.p_178_J = currentRot.t_148_a;
            AttackAura.c_3005_b.Y_259_p.f_4016_n = currentRot.s_956_w;
        }
    }

    private P_3504_Q s_956_w(r_4811_B target) {
        e_2866_D eyePos = AttackAura.c_3005_b.Y_259_p.u_2550_I(1.0f);
        e_2866_D targetPos = this.R_4764_Y(target).J_1907_R(0.0, (double)target.v_165_F() * 0.5, 0.0);
        double diffX = targetPos.J_1907_R - eyePos.J_1907_R;
        double diffY = targetPos.R_4764_Y - eyePos.R_4764_Y;
        double diffZ = targetPos.G_564_y - eyePos.G_564_y;
        double dist = Math.sqrt(diffX * diffX + diffZ * diffZ);
        float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f;
        float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, dist)));
        return new P_3504_Q(u_530_F.v_4262_N(yaw), u_530_F.n_1700_B(pitch, -90.0f, 90.0f));
    }

    private void RealmsClientConfig() {
        ElytraResolver fakeLag;
        boolean didPreHitSwap;
        AutoSwap autoSwap;
        boolean shield;
        boolean keepSprintOnHit;
        int weaponSlot;
        if (this.UploadStatus) {
            return;
        }
        if (this.autoTakeWeaponEnabled.isEnabled().booleanValue() && !u_1934_K.J_1907_R() && (weaponSlot = this.f_4016_n()) != -1 && weaponSlot < 9) {
            AttackAura.c_3005_b.Y_259_p.l_1268_F.G_564_y = weaponSlot;
            return;
        }
        if (!this.e_2887_G()) {
            return;
        }
        if (!this.A_4115_X() || this.n_1700_B(this.v_4262_N).u_1723_Y() > (double)this.v_4276_D()) {
            return;
        }
        boolean isInLiquid = AttackAura.c_3005_b.Y_259_p.RowButton() || AttackAura.c_3005_b.Y_259_p.W_3464_O() || AttackAura.c_3005_b.Y_259_p.C_1269_X();
        boolean wallBypassAllowed = this.P_4830_p(this.v_4262_N);
        if (this.raycastEnabled.isEnabled().booleanValue() && this.PlayerInfo()) {
            boolean rayOk;
            float[] fArray;
            if (this.g_164_R()) {
                fArray = this.N_2525_X();
            } else {
                float[] fArray2 = new float[2];
                fArray2[0] = AttackAura.c_3005_b.Y_259_p.p_178_J;
                fArray = fArray2;
                fArray2[1] = AttackAura.c_3005_b.Y_259_p.f_4016_n;
            }
            float[] rayRot = fArray;
            boolean bl = rayOk = this.J_1907_R((N_4263_v)AttackAura.c_3005_b.Y_259_p) || this.J_1907_R((N_4263_v)this.v_4262_N) || v_570_f.n_1700_B(rayRot[0], rayRot[1], this.v_4276_D() + 1.0f, this.v_4262_N, this.j_276_v(), this.UploadStatus());
            if (!rayOk && !wallBypassAllowed) {
                return;
            }
        }
        boolean inCobwebNow = this.J_1907_R((N_4263_v)AttackAura.c_3005_b.Y_259_p);
        Sprint sprintMod = (Sprint)lightning.product.ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(Sprint.class);
        boolean bl = keepSprintOnHit = sprintMod != null && sprintMod.h_1847_R();
        if (this.rotationMode.isMode("SpookyTime") && !isInLiquid && AttackAura.c_3005_b.Y_259_p.o_2341_D()) {
            this.D_60_a = 1;
        }
        if (!keepSprintOnHit && !isInLiquid && !inCobwebNow && AttackAura.c_3005_b.Y_259_p.o_2341_D()) {
            this.k_3961_g = true;
            if (AttackAura.c_3005_b.Y_259_p.R_4764_Y) {
                if (!this.e_1992_r()) {
                    return;
                }
                AttackAura.c_3005_b.Y_259_p.P_1922_E(false);
                AttackAura.c_3005_b.Y_259_p.b_(false);
                AttackAura.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new T_3952_j(AttackAura.c_3005_b.Y_259_p, T_3952_j.n_1700_B.P_1922_E));
            }
        }
        boolean bl2 = shield = AttackAura.c_3005_b.Y_259_p.Y_601_j() && AttackAura.c_3005_b.Y_259_p.B_2580_P().J_1907_R().R_4764_Y(AttackAura.c_3005_b.Y_259_p.B_2580_P()) == F_1573_j.G_564_y && this.releaseShieldEnabled.isEnabled() != false;
        if (shield) {
            AttackAura.c_3005_b.w_1457_N.onStoppedUsingItem(AttackAura.c_3005_b.Y_259_p);
        }
        if (this.resolverEnabled.isEnabled().booleanValue()) {
            this.t_4043_B();
        }
        if ((autoSwap = (AutoSwap)lightning.product.ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(AutoSwap.class)) != null && autoSwap.w_1484_f() && autoSwap.n_1700_B(this.v_4262_N) && !autoSwap.M_182_A()) {
            autoSwap.t_1786_h();
            return;
        }
        boolean bl3 = didPreHitSwap = autoSwap != null && autoSwap.w_1484_f() && autoSwap.M_182_A();
        if (this.g_164_R()) {
            this.P_4830_p(this.n_1700_B(this.v_4262_N));
        }
        AttackAura.c_3005_b.w_1457_N.attackEntity(AttackAura.c_3005_b.Y_259_p, this.v_4262_N);
        AttackAura.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
        this.Y_259_p();
        this.D_4361_a = System.currentTimeMillis() + 460L;
        if (didPreHitSwap) {
            autoSwap.multiplayerClientSuggestionProvider();
        }
        if (this.resolverEnabled.isEnabled().booleanValue()) {
            this.x_607_J();
        }
        if (AttackAura.c_3005_b.Y_259_p.k_578_l() && this.rotationMode.isMode("Grim") && (fakeLag = lightning.product.ClientBootstrap.Y_601_j().J_1907_R().P_1922_E()) != null && fakeLag.w_1484_f()) {
            fakeLag.n_1700_B(this.v_4262_N);
        }
        this.l_1233_K.n_1700_B();
        ++this.RowButton;
        if (this.k_3961_g() && this.v_4262_N != null) {
            e_2866_D hitVec = this.n_1700_B(this.v_4262_N);
            this.h_4320_q.n_1700_B(m_4644_u.J_1907_R(hitVec), m_4644_u.n_1700_B(hitVec));
        }
        this.Z_976_R.n_1700_B(this.g_164_R, this.rotationMode.isMode("\u041d\u0435\u0439\u0440\u043e"), this.s_2632_s(), this.H_1990_U);
        this.V_1225_t();
    }

    private int f_4016_n() {
        int bestSlot = -1;
        float bestDamage = 0.0f;
        for (int i = 0; i < 9; ++i) {
            AxeItem axe;
            Z_1993_T stack = AttackAura.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (stack.n_1700_B()) continue;
            q_1613_l q_1613_l2 = stack.J_1907_R();
            if (q_1613_l2 instanceof SwordItem) {
                SwordItem sword = (SwordItem)q_1613_l2;
                float damage = sword.v_4262_N();
                if (!(damage > bestDamage)) continue;
                bestDamage = damage;
                bestSlot = i;
                continue;
            }
            q_1613_l damage = stack.J_1907_R();
            if (!(damage instanceof AxeItem) || !((damage = (axe = (AxeItem)damage).v_4262_N()) > bestDamage)) continue;
            bestDamage = damage;
            bestSlot = i;
        }
        return bestSlot;
    }

    public boolean A_4115_X() {
        boolean fallDistanceCondition;
        float minCooldown;
        boolean isForwardingSprintActive;
        boolean bl;
        if (System.currentTimeMillis() < this.D_4361_a) {
            return false;
        }
        if (this.l_1233_K.J_1907_R() < 50L) {
            return false;
        }
        boolean onlyCrits = this.onlyCriticalsEnabled.isEnabled();
        boolean bl2 = bl = !onlyCrits || !this.Y_1740_V();
        if (AttackAura.c_3005_b.Y_259_p == null || AttackAura.c_3005_b.Y_601_j == null) {
            return false;
        }
        if (this.skipAttackWhenOptions.isOptionEnabled("\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0435\u0448\u044c \u0435\u0434\u0443").booleanValue() && AttackAura.c_3005_b.Y_259_p.C_332_W() || this.skipAttackWhenOptions.isOptionEnabled("\u041e\u0442\u043a\u0440\u044b\u0442 \u043a\u043e\u043d\u0442\u0435\u0439\u043d\u0435\u0440").booleanValue() && AttackAura.c_3005_b.Y_1740_V != null && AttackAura.c_3005_b.Y_1740_V != lightning.product.ClientBootstrap.Y_601_j().multiplayerClientSuggestionProvider()) {
            return false;
        }
        if (this.skipAttackWhenOptions.isOptionEnabled("\u0417\u0430\u0436\u0430\u0442 \u0449\u0438\u0442").booleanValue() && AttackAura.c_3005_b.Y_259_p.Y_601_j() && AttackAura.c_3005_b.Y_259_p.B_2580_P().J_1907_R() == Items.NoteBlock) {
            return false;
        }
        if (this.skipAttackWhenOptions.isOptionEnabled("\u041d\u0430 \u044d\u043b\u0438\u0442\u0440\u0435").booleanValue() && AttackAura.c_3005_b.Y_259_p.k_578_l()) {
            return false;
        }
        if (this.onlyWithWeaponEnabled.isEnabled().booleanValue() && !u_1934_K.J_1907_R() && !this.autoTakeWeaponEnabled.isEnabled().booleanValue()) {
            return false;
        }
        if (this.v_4262_N != null && this.n_1700_B(this.v_4262_N).u_1723_Y() > (double)this.v_4276_D()) {
            return false;
        }
        boolean isJumpCritical = this.onlyWhenSpacePressedEnabled.isEnabled() != false && !AttackAura.c_3005_b.P_4830_p.Ping.G_564_y();
        boolean bl3 = isForwardingSprintActive = AttackAura.c_3005_b.Y_259_p.R_4764_Y && !AttackAura.c_3005_b.Y_259_p.C_1269_X();
        if (!isJumpCritical && AttackAura.c_3005_b.Y_259_p.M_1641_O() && onlyCrits) {
            return false;
        }
        double tps = this.tpsSyncEnabled.isEnabled() != false ? (double)f_2403_E.J_1907_R() : 20.0;
        boolean inSlowFallOrCobweb = AttackAura.c_3005_b.Y_259_p.J_1907_R(MobEffects.H_2857_Y) || this.J_1907_R((N_4263_v)AttackAura.c_3005_b.Y_259_p) || this.v_4262_N != null && this.J_1907_R((N_4263_v)this.v_4262_N);
        float f = minCooldown = !onlyCrits && isForwardingSprintActive && !inSlowFallOrCobweb ? 0.1f : 0.92f;
        if (this.n_1700_B(0.5f, tps) < minCooldown) {
            return false;
        }
        double fallDistance = AttackAura.c_3005_b.Y_259_p.U_1241_n;
        boolean bl4 = fallDistanceCondition = fallDistance > 0.0;
        if (isForwardingSprintActive) {
            fallDistanceCondition = fallDistanceCondition || AttackAura.c_3005_b.Y_259_p.M_1641_O() && !AttackAura.c_3005_b.Y_601_j.a_(AttackAura.c_3005_b.Y_259_p, AttackAura.c_3005_b.Y_259_p.i_601_W().offset(0.0, -0.08, 0.0)) || AttackAura.c_3005_b.Y_259_p.I_4348_c().R_4764_Y < -0.05;
        }
        return bl || isJumpCritical || fallDistanceCondition || a_1344_X.n_1700_B();
    }

    public boolean Y_1740_V() {
        if (AttackAura.c_3005_b.Y_259_p.W_3464_O() || AttackAura.c_3005_b.Y_259_p.y_2772_m() || AttackAura.c_3005_b.Y_259_p.e_() || AttackAura.c_3005_b.Y_259_p.J_1907_R(MobEffects.Q_4569_t) || AttackAura.c_3005_b.Y_259_p.C_415_h.J_1907_R) {
            return false;
        }
        if (AttackAura.c_3005_b.Y_259_p.M_1641_O() && AttackAura.c_3005_b.Y_259_p.k_578_l()) {
            return false;
        }
        if (AttackAura.c_3005_b.Y_259_p.M_1641_O() && !AttackAura.c_3005_b.Y_601_j.a_(AttackAura.c_3005_b.Y_259_p, AttackAura.c_3005_b.Y_259_p.i_601_W().grow(0.0, 0.1f, 0.0))) {
            return false;
        }
        return !((N_4263_v)AttackAura.c_3005_b.Y_259_p).n_1700_B(FluidTags.J_1907_R) && (!AttackAura.c_3005_b.Y_259_p.RowButton() || !AttackAura.c_3005_b.Y_259_p.M_1641_O());
    }

    public float n_1700_B(double d) {
        return (float)(1.0 / AttackAura.c_3005_b.Y_259_p.J_1907_R(Attributes.w_1484_f) * d);
    }

    public float n_1700_B(float f, double d) {
        float cooled = AttackAura.c_3005_b.Y_259_p.k_2293_S(f);
        float periodByTps = this.n_1700_B(40.0 - d);
        float basePeriod = (float)(1.0 / AttackAura.c_3005_b.Y_259_p.J_1907_R(Attributes.w_1484_f) * 20.0);
        if (periodByTps <= 0.0f || basePeriod <= 0.0f) {
            return cooled;
        }
        return u_530_F.n_1700_B(cooled * (basePeriod / periodByTps), 0.0f, 1.0f);
    }

    private boolean j_276_v() {
        return this.noWallHitEnabled.isEnabled();
    }

    private boolean UploadStatus() {
        return this.noWallHitEnabled.isEnabled() != false && this.allowDoorHitEnabled.isEnabled() != false;
    }

    private boolean e_1992_r() {
        AirStuck airStuck = (AirStuck)lightning.product.ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(AirStuck.class);
        return airStuck != null && airStuck.w_1484_f();
    }

    private boolean u_2550_I(r_4811_B entity) {
        if (entity == null || AttackAura.c_3005_b.Y_259_p == null || this.j_276_v()) {
            return false;
        }
        if (AttackAura.c_3005_b.Y_259_p.c_3005_b(entity)) {
            return false;
        }
        if (this.wallHitBypassMode.isMode("Reallyworld")) {
            return true;
        }
        if (this.wallHitBypassMode.isMode("Funtime")) {
            return this.M_588_G(entity);
        }
        if (this.wallHitBypassMode.isMode("Holyworld")) {
            return this.d_2427_y && this.n_3318_d;
        }
        return this.wallHitBypassMode.isMode("SpookyTime");
    }

    private boolean M_588_G(r_4811_B entity) {
        if (AttackAura.c_3005_b.Y_259_p == null || entity == null) {
            return false;
        }
        boolean targetAbove = entity.X_2048_Y() > AttackAura.c_3005_b.Y_259_p.X_2048_Y() + 0.05;
        boolean behindBlocks = !AttackAura.c_3005_b.Y_259_p.c_3005_b(entity);
        return targetAbove && behindBlocks;
    }

    private boolean P_4830_p(r_4811_B entity) {
        if (entity == null || AttackAura.c_3005_b.Y_259_p == null || AttackAura.c_3005_b.Y_601_j == null) {
            return false;
        }
        if (this.j_276_v()) {
            return false;
        }
        if (AttackAura.c_3005_b.Y_259_p.c_3005_b(entity)) {
            return false;
        }
        if (this.wallHitBypassMode.isMode("Reallyworld")) {
            this.h_1847_R(entity);
            return true;
        }
        if (this.wallHitBypassMode.isMode("Funtime")) {
            return this.M_588_G(entity);
        }
        if (this.wallHitBypassMode.isMode("Holyworld")) {
            return this.d_2427_y && this.n_3318_d;
        }
        return this.wallHitBypassMode.isMode("SpookyTime");
    }

    private void h_1847_R(r_4811_B entity) {
        if (entity == null || AttackAura.c_3005_b.Y_259_p == null) {
            return;
        }
        e_2866_D vector3d = this.n_1700_B(entity);
        float targetYaw = (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(vector3d.G_564_y, vector3d.J_1907_R)) - 90.0);
        float targetPitch = (float)(-Math.toDegrees(Math.atan2(vector3d.R_4764_Y, Math.hypot(vector3d.J_1907_R, vector3d.G_564_y))));
        this.G_564_y(targetYaw, targetPitch);
    }

    private void G_564_y(float yaw, float pitch) {
        pitch = u_530_F.n_1700_B(pitch, -89.0f, 89.0f);
        if (!d_2169_p.n_1700_B()) {
            d_2169_p.n_1700_B(AttackAura.c_3005_b.Y_259_p.p_178_J);
            d_2169_p.J_1907_R(AttackAura.c_3005_b.Y_259_p.f_4016_n);
            d_2169_p.n_1700_B(true);
        }
        float y = r_4790_y.n_1700_B(AttackAura.c_3005_b.Y_259_p.p_178_J, yaw);
        float p = r_4790_y.n_1700_B(AttackAura.c_3005_b.Y_259_p.f_4016_n, pitch);
        AttackAura.c_3005_b.Y_259_p.p_178_J = y;
        AttackAura.c_3005_b.Y_259_p.j_276_v = y;
        AttackAura.c_3005_b.Y_259_p.f_3449_S = y;
        AttackAura.c_3005_b.Y_259_p.C_1162_e = y;
        AttackAura.c_3005_b.Y_259_p.D_4361_a = y;
        AttackAura.c_3005_b.Y_259_p.JsonUtils = y;
        AttackAura.c_3005_b.Y_259_p.f_4016_n = p;
        AttackAura.c_3005_b.Y_259_p.UploadStatus = p;
        r_4790_y.n_1700_B(new F_1446_q(y, p), 360.0f, 1, 6);
    }

    private boolean D_60_a() {
        boolean behindBlocks;
        if (AttackAura.c_3005_b.Y_259_p == null || this.v_4262_N == null) {
            return false;
        }
        boolean bl = behindBlocks = !AttackAura.c_3005_b.Y_259_p.c_3005_b(this.v_4262_N);
        if (!behindBlocks) {
            this.n_3318_d = false;
            return true;
        }
        if (AttackAura.c_3005_b.Y_259_p.M_1641_O()) {
            this.n_3318_d = false;
            return false;
        }
        if (!this.n_3318_d) {
            if (AttackAura.c_3005_b.Y_259_p.U_1241_n > 0.1f && AttackAura.c_3005_b.Y_259_p.R_4764_Y(this.v_4262_N) <= 3.0f) {
                this.n_3318_d = true;
            } else {
                return false;
            }
        }
        AttackAura.c_3005_b.Y_259_p.h_1847_R(0.0, AttackAura.c_3005_b.Y_259_p.I_4348_c().R_4764_Y, 0.0);
        return true;
    }

    private boolean k_3961_g() {
        return this.rotationMode.isMode("Holyworld");
    }

    private boolean Ops() {
        return false;
    }

    private boolean h_4320_q() {
        return this.rotationMode.isMode("Sloth test");
    }

    private boolean t_4219_U() {
        PacketCriticals packetCriticals = (PacketCriticals)lightning.product.ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(PacketCriticals.class);
        return packetCriticals != null && packetCriticals.w_1484_f() && !this.k_3961_g() && !this.Ops() && !this.h_4320_q();
    }

    private void V_1446_Y() {
        if (!(this.k_3961_g() || this.Ops() || this.h_4320_q())) {
            return;
        }
        PacketCriticals packetCriticals = (PacketCriticals)lightning.product.ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(PacketCriticals.class);
        if (packetCriticals != null && packetCriticals.w_1484_f()) {
            packetCriticals.n_1700_B(false);
        }
    }

    private boolean PlayerInfo() {
        boolean isOnElytra = AttackAura.c_3005_b.Y_259_p.k_578_l();
        switch ((String)this.workOnMode.getValue()) {
            case "\u0417\u0435\u043c\u043b\u0435": {
                return !isOnElytra;
            }
            case "\u042d\u043b\u0438\u0442\u0440\u0435": {
                return isOnElytra;
            }
        }
        return true;
    }

    private boolean J_1907_R(N_4263_v entity) {
        if (entity == null || AttackAura.c_3005_b.Y_601_j == null) {
            return false;
        }
        I_4817_s box = entity.i_601_W();
        int minX = (int)Math.floor(box.minX);
        int minY = (int)Math.floor(box.minY);
        int minZ = (int)Math.floor(box.minZ);
        int maxX = (int)Math.ceil(box.maxX);
        int maxY = (int)Math.ceil(box.maxY);
        int maxZ = (int)Math.ceil(box.maxZ);
        for (int x = minX; x < maxX; ++x) {
            for (int y = minY; y < maxY; ++y) {
                for (int z = minZ; z < maxZ; ++z) {
                    if (AttackAura.c_3005_b.Y_601_j.getBlockState(new c_1514_x(x, y, z)).J_1907_R() != a_3742_W.y_1700_S) continue;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean V_1225_t() {
        if (!this.breakShieldEnabled.isEnabled().booleanValue()) {
            return false;
        }
        int axeSlot = u_1934_K.n_1700_B();
        if (this.v_4262_N.B_2580_P().J_1907_R() == Items.NoteBlock && axeSlot != -1) {
            if (axeSlot < 9) {
                c_3005_b.k_2293_S().n_1700_B(new p_1183_T(axeSlot));
                AttackAura.c_3005_b.w_1457_N.attackEntity(AttackAura.c_3005_b.Y_259_p, this.v_4262_N);
                AttackAura.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
                c_3005_b.k_2293_S().n_1700_B(new p_1183_T(AttackAura.c_3005_b.Y_259_p.l_1268_F.G_564_y));
            } else {
                c_3005_b.k_2293_S().n_1700_B(new ServerboundContainerClickPacket(AttackAura.c_3005_b.Y_259_p.o_1800_r.u_1723_Y, axeSlot, AttackAura.c_3005_b.Y_259_p.l_1268_F.G_564_y, a_408_T.R_4764_Y, Z_1993_T.J_1907_R, AttackAura.c_3005_b.Y_259_p.H_1873_g.n_1700_B(AttackAura.c_3005_b.Y_259_p.l_1268_F)));
                c_3005_b.k_2293_S().n_1700_B(new P_4526_H(AttackAura.c_3005_b.Y_259_p.o_1800_r.u_1723_Y));
                AttackAura.c_3005_b.w_1457_N.attackEntity(AttackAura.c_3005_b.Y_259_p, this.v_4262_N);
                AttackAura.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
                c_3005_b.k_2293_S().n_1700_B(new ServerboundContainerClickPacket(AttackAura.c_3005_b.Y_259_p.o_1800_r.u_1723_Y, axeSlot, AttackAura.c_3005_b.Y_259_p.l_1268_F.G_564_y, a_408_T.R_4764_Y, Z_1993_T.J_1907_R, AttackAura.c_3005_b.Y_259_p.H_1873_g.n_1700_B(AttackAura.c_3005_b.Y_259_p.l_1268_F)));
                c_3005_b.k_2293_S().n_1700_B(new P_4526_H(AttackAura.c_3005_b.Y_259_p.o_1800_r.u_1723_Y));
            }
            return true;
        }
        return false;
    }

    private r_4811_B U_1241_n() {
        if (AttackAura.c_3005_b.Y_601_j == null || AttackAura.c_3005_b.Y_259_p == null) {
            return null;
        }
        r_4811_B bestTarget = null;
        double bestDistanceSq = Double.MAX_VALUE;
        float bestHealth = Float.MAX_VALUE;
        boolean bestElytraPreferred = false;
        F_3698_k elytraSample = lightning.product.ClientBootstrap.Y_601_j().J_1907_R().R_4764_Y();
        boolean preferElytraTargets = elytraSample != null && elytraSample.w_1484_f() && AttackAura.c_3005_b.Y_259_p.k_578_l() && F_3698_k.w_1484_f.J_1907_R("\u0421\u043f\u0435\u0440\u0432\u0430 \u044d\u043b\u0438\u0442\u0440\u044b");
        String auraPriorityMode = (String)this.priorityMode.getValue();
        for (N_4263_v entity : AttackAura.c_3005_b.Y_601_j.J_1907_R()) {
            boolean betterByAuraPriority;
            r_4811_B livingEntity;
            if (!(entity instanceof r_4811_B) || !this.Q_4569_t(livingEntity = (r_4811_B)entity)) continue;
            double distanceSq = livingEntity.G_564_y((N_4263_v)AttackAura.c_3005_b.Y_259_p);
            boolean elytraPreferred = preferElytraTargets && livingEntity.k_578_l();
            float health = livingEntity.g_46_E() + livingEntity.U_3823_u();
            boolean bl = "\u0417\u0434\u043e\u0440\u043e\u0432\u044c\u0435".equals(auraPriorityMode) ? health < bestHealth || health == bestHealth && distanceSq < bestDistanceSq : (betterByAuraPriority = distanceSq < bestDistanceSq);
            if (bestTarget != null && (!elytraPreferred || bestElytraPreferred) && (elytraPreferred != bestElytraPreferred || !betterByAuraPriority)) continue;
            bestDistanceSq = distanceSq;
            bestHealth = health;
            bestElytraPreferred = elytraPreferred;
            bestTarget = livingEntity;
        }
        return bestTarget;
    }

    private boolean Q_4569_t(r_4811_B entity) {
        if (entity == null || !entity.RealmsLongRunningMcoTaskScreen() || entity == AttackAura.c_3005_b.Y_259_p || entity instanceof D_686_b) {
            return false;
        }
        FreeCam freeCam = (FreeCam)lightning.product.ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(FreeCam.class);
        if (freeCam != null && freeCam.w_1484_f() && freeCam.h_1847_R() != null && entity == freeCam.h_1847_R()) {
            return false;
        }
        F_3698_k elytraTarget = lightning.product.ClientBootstrap.Y_601_j().J_1907_R().R_4764_Y();
        boolean isElytraMode = elytraTarget != null && elytraTarget.w_1484_f() && AttackAura.c_3005_b.Y_259_p.k_578_l();
        float baseDistance = this.v_4276_D();
        float maxDistance = isElytraMode ? 64.0f : (this.rotationMode.isMode("Grim") ? baseDistance : baseDistance + ((Float)this.detectRangeSetting.getValue()).floatValue());
        float aimDist = (float)a_1344_X.J_1907_R(entity).u_1723_Y();
        if (aimDist > maxDistance) {
            return false;
        }
        if (entity instanceof a_3913_L) {
            a_3913_L player = (a_3913_L)entity;
            if (lightning.product.ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(AntiBot.class).w_1484_f() && player.g_4106_L) {
                return false;
            }
        }
        return E_4612_l.n_1700_B(entity, this.targetsOptions, true) || E_4612_l.n_1700_B(entity, this.targetsOptions) || E_4612_l.J_1907_R(entity, this.targetsOptions) || E_4612_l.R_4764_Y(entity, this.targetsOptions);
    }

    public void t_4043_B() {
        for (a_3913_L a_3913_L2 : AttackAura.c_3005_b.Y_601_j.multiplayerClientSuggestionProvider()) {
            if (!(a_3913_L2 instanceof Q_1187_u)) continue;
            ((Q_1187_u)a_3913_L2).R_4764_Y();
        }
    }

    public void x_607_J() {
        for (a_3913_L a_3913_L2 : AttackAura.c_3005_b.Y_601_j.multiplayerClientSuggestionProvider()) {
            if (!(a_3913_L2 instanceof Q_1187_u)) continue;
            ((Q_1187_u)a_3913_L2).u_1723_Y();
        }
    }

    @Override
    public void onEnable() {
        a_1344_X.G_564_y();
        this.B_1668_F();
        this.r_715_M();
        this.n_3318_d = false;
        this.d_2427_y = false;
        this.D_4361_a = System.currentTimeMillis();
        this.Q_2552_b();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        if ((this.Ops() || this.h_4320_q() || this.k_3961_g()) && this.v_4262_N != null) {
            this.i_1637_u();
        }
        this.v_4262_N = null;
        this.e_1992_r = new P_3504_Q(0.0f, 0.0f);
        this.D_60_a = 0;
        this.g_164_R = false;
        this.Z_976_R.n_1700_B(false);
        this.r_715_M();
        this.j_1564_a = false;
        this.M_1641_O = 0.0f;
        this.RealmsWorldOptions = 0.0f;
        this.RealmsWorldResetDto = 0.0f;
        this.F_2624_D = false;
        this.RealmsDefaultUncaughtExceptionHandler = 0.0f;
        this.y_1700_S = 0.0f;
        this.u_744_e = 0.0f;
        this.RetryCallException = 0.0f;
        this.r_3651_U = 0L;
        this.n_3318_d = false;
        this.d_2427_y = false;
        this.D_4361_a = System.currentTimeMillis();
        this.RowButton = 0;
        this.r_715_M = 0;
        this.A_1038_p = 0;
        this.X_933_l();
        this.C_290_v = null;
        this.w_728_N = 0.0f;
        this.J_4256_G = 0.0f;
        this.RealmsLongConfirmationScreen = 0.0f;
        this.RealmsLongRunningMcoTaskScreen = 0.0f;
        this.i_2993_w = 0.0;
        this.RealmsParentalConsentScreen = 0.0;
        this.O_2151_c = 0.0;
        this.s_1671_u = 0.0f;
        this.C_3538_G = 0;
        this.A_3959_N = 0;
        this.G_424_k = 0.0f;
        this.RealmsSettingsScreen = 0.0f;
        this.f_1043_S = 0.0f;
        this.F_4247_a = 0.0f;
        this.J_739_q = 0.0f;
        this.C_1162_e = 0L;
        this.f_3449_S = 0;
        this.u_55_V = false;
        this.JsonUtils = null;
        this.t_4219_U = null;
        this.d_4007_L = null;
        this.TextRenderingUtils = 0.0f;
        this.B_1668_F();
        super.onDisable();
    }

    public float n_1700_B(float start, float end, float speed) {
        float delta = u_530_F.v_4262_N(end - start);
        float step = u_530_F.n_1700_B(delta, -speed, speed);
        return u_530_F.v_4262_N(start + step);
    }

    public float J_1907_R(float start, float end, float amount) {
        float a = u_530_F.n_1700_B(amount, 0.0f, 1.0f);
        float d = u_530_F.v_4262_N(end - start);
        if (Math.abs(d) < 0.5f) {
            return end;
        }
        return u_530_F.v_4262_N(start + d * a);
    }

    public float e_4240_b() {
        return (float)((double)F_747_P.G_564_y(30.0f + F_747_P.G_564_y(0.0f, 10.0f), 30.0f + F_747_P.G_564_y(0.0f, 10.0f)) * Math.sin((double)System.currentTimeMillis() / 75.0));
    }

    public float n_3318_d() {
        return (float)((double)F_747_P.G_564_y(F_747_P.G_564_y(0.0f, 5.0f), F_747_P.G_564_y(0.0f, 5.0f)) * Math.cos((double)System.currentTimeMillis() / 75.0));
    }

    @Generated
    public boolean d_2427_y() {
        return this.g_164_R;
    }

    private static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] u_1723_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])u_1723_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E};
        }

        static {
            u_1723_Y = lightning.product.AttackAura$n_1700_B.n_1700_B();
        }
    }

    private static final class J_1907_R {
        final long n_1700_B;
        final e_2866_D J_1907_R;

        J_1907_R(long time, e_2866_D point) {
            this.n_1700_B = time;
            this.J_1907_R = point;
        }
    }
}



