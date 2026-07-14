/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  com.google.common.base.Splitter
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  com.google.common.io.Files
 *  com.google.gson.Gson
 *  com.google.gson.reflect.TypeToken
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.ArrayUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import lightning.product.ServerboundClientInformationPacket;
import lightning.product.D_2103_L;
import lightning.product.D_38_f;
import lightning.product.D_590_W;
import lightning.product.E_4346_v;
import lightning.product.E_4704_H;
import lightning.product.PackRepository;
import lightning.product.G_463_a;
import lightning.product.I_2212_R;
import lightning.product.SharedConstants;
import lightning.product.J_1907_R;
import lightning.product.K_1289_S;
import lightning.product.K_2069_m;
import lightning.product.M_2935_g;
import lightning.product.P_3084_J;
import lightning.product.P_4249_L;
import lightning.product.Q_4113_P;
import lightning.product.R_2450_T;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.MinecraftClient;
import lightning.product.ToggleKeyMapping;
import lightning.product.e_3022_i;
import lightning.product.g_4418_P;
import lightning.product.i_4431_W;
import lightning.product.j_3341_s;
import lightning.product.j_4067_x;
import lightning.product.k_4231_L;
import lightning.product.n_1700_B;
import lightning.product.n_3832_I;
import lightning.product.o_1967_f;
import lightning.product.t_1920_R;
import lightning.product.t_4467_k;
import lightning.product.u_530_F;
import lightning.product.x_282_a;
import lightning.product.z_883_p;
import net.optifine.Config;
import net.optifine.CustomColors;
import net.optifine.CustomGuis;
import net.optifine.CustomSky;
import net.optifine.DynamicLights;
import net.optifine.Lang;
import net.optifine.NaturalTextures;
import net.optifine.RandomEntities;
import net.optifine.config.FloatOptions;
import net.optifine.reflect.Reflector;
import net.optifine.shaders.Shaders;
import net.optifine.util.FontUtils;
import net.optifine.util.KeyUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class V_4423_d {
    private static final Logger i_3196_G = LogManager.getLogger();
    private static final Gson C_415_h = new Gson();
    private static final TypeToken<List<String>> v_165_F = new TypeToken<List<String>>(){};
    private static final Splitter s_4990_V = Splitter.on((char)':').limit(2);
    public double n_1700_B = 0.5;
    public int J_1907_R = -1;
    public float R_4764_Y = 1.0f;
    public int G_564_y = 120;
    public K_2069_m P_1922_E = K_2069_m.R_4764_Y;
    public P_3084_J u_1723_Y = P_3084_J.J_1907_R;
    public G_463_a v_4262_N = G_463_a.R_4764_Y;
    public List<String> w_1484_f = Lists.newArrayList();
    public List<String> t_148_a = Lists.newArrayList();
    public g_4418_P s_956_w = g_4418_P.n_1700_B;
    public double u_2550_I = 1.0;
    public double M_588_G = 0.0;
    public double P_4830_p = 0.5;
    @Nullable
    public String h_1847_R;
    public boolean Q_4569_t;
    public boolean M_182_A;
    public boolean t_1786_h = true;
    private final Set<E_4346_v> b_2312_j = Sets.newHashSet((Object[])E_4346_v.values());
    public k_4231_L multiplayerClientSuggestionProvider = k_4231_L.J_1907_R;
    public int w_1457_N;
    public int Y_601_j;
    public boolean Y_259_p = true;
    public double Q_2552_b = 1.0;
    public double C_2741_M = 1.0;
    public double k_2293_S = 0.44366195797920227;
    public double q_2307_F = 1.0;
    public double Z_875_P = 0.0;
    public int c_3005_b = 4;
    private final Map<D_38_f, Float> I_4348_c = Maps.newEnumMap(D_38_f.class);
    public boolean H_2857_Y = true;
    public E_4704_H A_4115_X = E_4704_H.J_1907_R;
    public t_4467_k Y_1740_V = t_4467_k.n_1700_B;
    public boolean t_4043_B = false;
    public int x_607_J = 2;
    public double e_4240_b = 1.0;
    public boolean n_3318_d = true;
    public int d_2427_y = 1;
    public boolean z_1737_N = true;
    public boolean v_4276_D = true;
    public boolean d_2461_k = true;
    public boolean G_624_v = true;
    public boolean T_2506_i = true;
    public boolean q_4610_l = true;
    public boolean z_4693_k = true;
    public boolean g_221_o;
    public boolean e_2887_G;
    public boolean B_1668_F;
    public boolean g_164_R = true;
    public boolean X_933_l;
    public boolean Z_976_R = true;
    public boolean H_1990_U;
    public boolean N_2525_X = true;
    public boolean c_4037_x;
    public boolean g_2268_R;
    public boolean T_3594_S = true;
    public boolean D_4792_h;
    public boolean s_2632_s;
    public boolean l_1233_K;
    public boolean z_1333_t = true;
    public final D_590_W O_508_d = new D_590_W("key.forward", 87, "key.categories.movement");
    public final D_590_W r_715_M = new D_590_W("key.left", 65, "key.categories.movement");
    public final D_590_W A_1038_p = new D_590_W("key.back", 83, "key.categories.movement");
    public final D_590_W i_1637_u = new D_590_W("key.right", 68, "key.categories.movement");
    public final D_590_W Ping = new D_590_W("key.jump", 32, "key.categories.movement");
    public final D_590_W p_178_J = new ToggleKeyMapping("key.sneak", 340, "key.categories.movement", () -> this.D_4792_h);
    public final D_590_W RealmsClientConfig = new ToggleKeyMapping("key.sprint", 341, "key.categories.movement", () -> this.s_2632_s);
    public final D_590_W f_4016_n = new D_590_W("key.inventory", 69, "key.categories.inventory");
    public final D_590_W j_276_v = new D_590_W("key.swapOffhand", 70, "key.categories.inventory");
    public final D_590_W UploadStatus = new D_590_W("key.drop", 81, "key.categories.inventory");
    public final D_590_W e_1992_r = new D_590_W("key.use", Q_4113_P.J_1907_R.R_4764_Y, 1, "key.categories.gameplay");
    public final D_590_W D_60_a = new D_590_W("key.attack", Q_4113_P.J_1907_R.R_4764_Y, 0, "key.categories.gameplay");
    public final D_590_W k_3961_g = new D_590_W("key.pickItem", Q_4113_P.J_1907_R.R_4764_Y, 2, "key.categories.gameplay");
    public final D_590_W Ops = new D_590_W("key.chat", 84, "key.categories.multiplayer");
    public final D_590_W h_4320_q = new D_590_W("key.playerlist", 258, "key.categories.multiplayer");
    public final D_590_W t_4219_U = new D_590_W("key.command", 47, "key.categories.multiplayer");
    public final D_590_W V_1446_Y = new D_590_W("key.socialInteractions", 80, "key.categories.multiplayer");
    public final D_590_W PlayerInfo = new D_590_W("key.screenshot", 291, "key.categories.misc");
    public final D_590_W V_1225_t = new D_590_W("key.togglePerspective", 294, "key.categories.misc");
    public final D_590_W U_1241_n = new D_590_W("key.smoothCamera", Q_4113_P.n_1700_B.J_1907_R(), "key.categories.misc");
    public final D_590_W q_1982_R = new D_590_W("key.fullscreen", 300, "key.categories.misc");
    public final D_590_W dtoRealmsServerAddress = new D_590_W("key.spectatorOutlines", Q_4113_P.n_1700_B.J_1907_R(), "key.categories.misc");
    public final D_590_W w_612_n = new D_590_W("key.advancements", 76, "key.categories.misc");
    public final D_590_W[] RealmsServerPing = new D_590_W[]{new D_590_W("key.hotbar.1", 49, "key.categories.inventory"), new D_590_W("key.hotbar.2", 50, "key.categories.inventory"), new D_590_W("key.hotbar.3", 51, "key.categories.inventory"), new D_590_W("key.hotbar.4", 52, "key.categories.inventory"), new D_590_W("key.hotbar.5", 53, "key.categories.inventory"), new D_590_W("key.hotbar.6", 54, "key.categories.inventory"), new D_590_W("key.hotbar.7", 55, "key.categories.inventory"), new D_590_W("key.hotbar.8", 56, "key.categories.inventory"), new D_590_W("key.hotbar.9", 57, "key.categories.inventory")};
    public final D_590_W j_1564_a = new D_590_W("key.saveToolbarActivator", 67, "key.categories.creative");
    public final D_590_W M_1641_O = new D_590_W("key.loadToolbarActivator", 88, "key.categories.creative");
    public static D_590_W RealmsWorldOptions = new D_590_W("key.push_to_talk", Q_4113_P.n_1700_B.J_1907_R(), "key.categories.voicechat");
    public static D_590_W RealmsWorldResetDto = new D_590_W("key.whisper", Q_4113_P.n_1700_B.J_1907_R(), "key.categories.voicechat");
    public static D_590_W RegionPingResult = new D_590_W("key.mute_microphone", Q_4113_P.n_1700_B.J_1907_R(), "key.categories.voicechat");
    public static D_590_W H_1083_k = new D_590_W("key.disable_voice_chat", Q_4113_P.n_1700_B.J_1907_R(), "key.categories.voicechat");
    public static D_590_W R_3908_n = new D_590_W("key.hide_icons", Q_4113_P.n_1700_B.J_1907_R(), "key.categories.voicechat");
    public static D_590_W ValueObject = new D_590_W("key.voice_chat", Q_4113_P.n_1700_B.J_1907_R(), "key.categories.voicechat");
    public static D_590_W F_1410_V = new D_590_W("key.voice_chat_settings", Q_4113_P.n_1700_B.J_1907_R(), "key.categories.voicechat");
    public static D_590_W S_4022_R = new D_590_W("key.voice_chat_group", Q_4113_P.n_1700_B.J_1907_R(), "key.categories.voicechat");
    public static D_590_W l_4537_E = new D_590_W("key.voice_chat_toggle_recording", Q_4113_P.n_1700_B.J_1907_R(), "key.categories.voicechat");
    public static D_590_W F_2624_D = new D_590_W("key.voice_chat_adjust_volumes", Q_4113_P.n_1700_B.J_1907_R(), "key.categories.voicechat");
    public D_590_W[] RealmsDefaultUncaughtExceptionHandler;
    protected MinecraftClient y_1700_S;
    private final File O_3598_v;
    public R_2450_T u_744_e;
    public boolean RetryCallException;
    private t_1920_R X_2960_b;
    public boolean r_3651_U;
    public boolean RowButton;
    public boolean LongRunningTask;
    public String j_2266_I;
    public boolean S_980_j;
    public double R_3077_Z;
    public float RealmsScreenWithCallback;
    public float M_2677_i;
    public double c_132_F;
    public int g_4106_L;
    public j_4067_x RealmsClientOutdatedScreen;
    public e_3022_i W_3464_O;
    public String RealmsConfirmScreen;
    public boolean RealmsCreateRealmScreen;
    public int C_290_v;
    public float w_728_N;
    public int J_4256_G;
    public boolean RealmsLongConfirmationScreen;
    public boolean RealmsLongRunningMcoTaskScreen;
    public boolean i_2993_w;
    public boolean RealmsParentalConsentScreen;
    public boolean O_2151_c;
    public boolean s_1671_u;
    public double RealmsResetNormalWorldScreen;
    public int C_3538_G;
    public int A_3959_N;
    public int G_424_k;
    public double RealmsSettingsScreen;
    public int f_1043_S;
    public int F_4247_a;
    public int J_739_q;
    public int C_1162_e;
    public int D_4361_a;
    public boolean f_3449_S;
    public boolean u_55_V;
    public boolean JsonUtils;
    public boolean RealmsPersistence;
    public boolean y_2772_m;
    public boolean H_1883_T;
    public boolean d_4007_L;
    public int TextRenderingUtils;
    public int UploadTokenCache;
    public boolean U_1341_G;
    public int ClientBootstrap;
    public boolean o_2341_D;
    public boolean C_1269_X;
    public boolean x_612_B;
    public boolean t_1446_I;
    public boolean j_306_t;
    public boolean F_3572_x;
    public boolean L_1362_X;
    public int P_5000_x;
    public boolean L_4248_u;
    public boolean O_1309_Q;
    public boolean O_2934_T;
    public boolean l_4088_R;
    public boolean Z_735_d;
    public int P_925_e;
    public boolean X_4895_T;
    public boolean L_103_L;
    public int n_3197_X;
    public boolean P_2947_S;
    public boolean O_4761_U;
    public boolean w_2705_t;
    public int F_518_D;
    public int L_3570_A;
    public boolean Y_776_s;
    public int S_3139_t;
    public int k_2302_P;
    public boolean t_3452_g;
    public boolean V_118_c;
    public boolean I_1407_m;
    public boolean o_2767_H;
    public boolean d_2545_n;
    public boolean x_92_N;
    public boolean i_601_W;
    public boolean h_2739_B;
    public boolean X_1313_W;
    public boolean x_4991_F;
    public boolean Z_759_W;
    public boolean f_1574_f;
    public boolean l_1268_F;
    public boolean J_303_C;
    public boolean o_1800_r;
    public static final int H_1873_g = 0;
    public static final int n_3864_h = 1;
    public static final int o_3599_Z = 2;
    public static final int X_290_I = 3;
    public static final int O_1795_e = 4;
    public static final int l_697_B = 5;
    public static final int d_3244_b = 0;
    public static final int v_887_r = 1;
    public static final int l_3609_d = 2;
    public static final String r_2478_U = "Default";
    public static final double h_2848_I = 4.0571431;
    private static final int[] M_766_z = new int[]{0, 1, 4, 2};
    private static final int[] X_2048_Y = new int[]{3, 1, 2};
    private static final String[] l_2647_k = new String[]{"options.off", "options.graphics.fast", "options.graphics.fancy"};
    public D_590_W A_3244_K;
    private File a_178_J;

    public V_4423_d(MinecraftClient mcIn, File mcDataDir) {
        Object[] objectArray = new D_590_W[35];
        objectArray[0] = this.D_60_a;
        objectArray[1] = this.e_1992_r;
        objectArray[2] = this.O_508_d;
        objectArray[3] = this.r_715_M;
        objectArray[4] = this.A_1038_p;
        objectArray[5] = this.i_1637_u;
        objectArray[6] = this.Ping;
        objectArray[7] = this.p_178_J;
        objectArray[8] = this.RealmsClientConfig;
        objectArray[9] = this.UploadStatus;
        objectArray[10] = this.f_4016_n;
        objectArray[11] = this.Ops;
        objectArray[12] = this.h_4320_q;
        objectArray[13] = this.k_3961_g;
        objectArray[14] = this.t_4219_U;
        objectArray[15] = this.V_1446_Y;
        objectArray[16] = this.PlayerInfo;
        objectArray[17] = this.V_1225_t;
        objectArray[18] = this.U_1241_n;
        objectArray[19] = this.q_1982_R;
        objectArray[20] = this.dtoRealmsServerAddress;
        objectArray[21] = this.j_276_v;
        objectArray[22] = this.j_1564_a;
        objectArray[23] = this.M_1641_O;
        objectArray[24] = this.w_612_n;
        objectArray[25] = RealmsWorldOptions;
        objectArray[26] = RealmsWorldResetDto;
        objectArray[27] = RegionPingResult;
        objectArray[28] = H_1083_k;
        objectArray[29] = R_3908_n;
        objectArray[30] = ValueObject;
        objectArray[31] = F_1410_V;
        objectArray[32] = S_4022_R;
        objectArray[33] = l_4537_E;
        objectArray[34] = F_2624_D;
        this.RealmsDefaultUncaughtExceptionHandler = (D_590_W[])ArrayUtils.addAll((Object[])objectArray, (Object[])this.RealmsServerPing);
        this.u_744_e = R_2450_T.R_4764_Y;
        this.X_2960_b = t_1920_R.n_1700_B;
        this.j_2266_I = "";
        this.R_3077_Z = 70.0;
        this.RealmsScreenWithCallback = 1.0f;
        this.M_2677_i = 1.0f;
        this.RealmsClientOutdatedScreen = j_4067_x.n_1700_B;
        this.W_3464_O = e_3022_i.n_1700_B;
        this.RealmsConfirmScreen = "en_us";
        this.C_290_v = 1;
        this.w_728_N = 0.8f;
        this.J_4256_G = 0;
        this.RealmsLongConfirmationScreen = false;
        this.RealmsLongRunningMcoTaskScreen = false;
        this.i_2993_w = Config.isSingleProcessor();
        this.RealmsParentalConsentScreen = Config.isSingleProcessor();
        this.O_2151_c = false;
        this.s_1671_u = false;
        this.RealmsResetNormalWorldScreen = 1.0;
        this.C_3538_G = 0;
        this.A_3959_N = 1;
        this.G_424_k = 0;
        this.RealmsSettingsScreen = 0.0;
        this.f_1043_S = 0;
        this.F_4247_a = 0;
        this.J_739_q = 0;
        this.C_1162_e = 3;
        this.D_4361_a = 4000;
        this.f_3449_S = false;
        this.u_55_V = false;
        this.JsonUtils = false;
        this.RealmsPersistence = true;
        this.y_2772_m = true;
        this.H_1883_T = true;
        this.d_4007_L = true;
        this.TextRenderingUtils = 0;
        this.UploadTokenCache = 1;
        this.U_1341_G = false;
        this.ClientBootstrap = 0;
        this.o_2341_D = false;
        this.C_1269_X = true;
        this.x_612_B = true;
        this.t_1446_I = true;
        this.j_306_t = true;
        this.F_3572_x = true;
        this.L_1362_X = true;
        this.P_5000_x = 2;
        this.L_4248_u = true;
        this.O_1309_Q = false;
        this.O_2934_T = true;
        this.l_4088_R = false;
        this.Z_735_d = false;
        this.P_925_e = 0;
        this.X_4895_T = true;
        this.L_103_L = true;
        this.n_3197_X = 3;
        this.P_2947_S = true;
        this.O_4761_U = true;
        this.w_2705_t = true;
        this.F_518_D = 1;
        this.L_3570_A = 0;
        this.Y_776_s = true;
        this.S_3139_t = 0;
        this.k_2302_P = 0;
        this.t_3452_g = true;
        this.V_118_c = true;
        this.I_1407_m = true;
        this.o_2767_H = true;
        this.d_2545_n = true;
        this.x_92_N = true;
        this.i_601_W = true;
        this.h_2739_B = true;
        this.X_1313_W = true;
        this.x_4991_F = true;
        this.Z_759_W = true;
        this.f_1574_f = true;
        this.l_1268_F = true;
        this.J_303_C = true;
        this.o_1800_r = true;
        this.h_1847_R();
        this.y_1700_S = mcIn;
        this.O_3598_v = new File(mcDataDir, "options.txt");
        if (mcIn.B_1668_F() && Runtime.getRuntime().maxMemory() >= 1000000000L) {
            M_2935_g.RENDER_DISTANCE.setMaxValue(32.0f);
            long i = 1000000L;
            if (Runtime.getRuntime().maxMemory() >= 1500L * i) {
                M_2935_g.RENDER_DISTANCE.setMaxValue(48.0f);
            }
            if (Runtime.getRuntime().maxMemory() >= 2500L * i) {
                M_2935_g.RENDER_DISTANCE.setMaxValue(64.0f);
            }
        } else {
            M_2935_g.RENDER_DISTANCE.setMaxValue(16.0f);
        }
        this.J_1907_R = mcIn.B_1668_F() ? 12 : 8;
        this.RealmsCreateRealmScreen = j_3341_s.t_148_a() == j_3341_s.J_1907_R.R_4764_Y;
        this.a_178_J = new File(mcDataDir, "optionsof.txt");
        this.G_564_y = (int)M_2935_g.FRAMERATE_LIMIT.getMaxValue();
        this.A_3244_K = new D_590_W("of.key.zoom", 67, "key.categories.misc");
        this.RealmsDefaultUncaughtExceptionHandler = (D_590_W[])ArrayUtils.add((Object[])this.RealmsDefaultUncaughtExceptionHandler, (Object)this.A_3244_K);
        KeyUtils.fixKeyConflicts(this.RealmsDefaultUncaughtExceptionHandler, new D_590_W[]{this.A_3244_K});
        this.J_1907_R = 8;
        this.n_1700_B();
        Config.initGameSettings(this);
    }

    public float n_1700_B(float opacity) {
        return this.N_2525_X ? opacity : (float)this.P_4830_p;
    }

    public int J_1907_R(float opacity) {
        return (int)(this.n_1700_B(opacity) * 255.0f) << 24 & 0xFF000000;
    }

    public int n_1700_B(int chatColor) {
        return this.N_2525_X ? chatColor : (int)(this.P_4830_p * 255.0) << 24 & 0xFF000000;
    }

    public void n_1700_B(D_590_W keyBindingIn, Q_4113_P.n_1700_B inputIn) {
        keyBindingIn.J_1907_R(inputIn);
        this.J_1907_R();
    }

    public void n_1700_B() {
        try {
            if (!this.O_3598_v.exists()) {
                return;
            }
            this.I_4348_c.clear();
            U_2912_j compoundnbt = new U_2912_j();
            try (BufferedReader bufferedreader = Files.newReader((File)this.O_3598_v, (Charset)Charsets.UTF_8);){
                bufferedreader.lines().forEach(p_lambda$loadOptions$2_1_ -> {
                    try {
                        Iterator iterator = s_4990_V.split((CharSequence)p_lambda$loadOptions$2_1_).iterator();
                        compoundnbt.n_1700_B((String)iterator.next(), (String)iterator.next());
                    }
                    catch (Exception exception21) {
                        i_3196_G.warn("Skipping bad option: {}", p_lambda$loadOptions$2_1_);
                    }
                });
            }
            U_2912_j compoundnbt1 = this.n_1700_B(compoundnbt);
            if (!compoundnbt1.P_1922_E("graphicsMode") && compoundnbt1.P_1922_E("fancyGraphics")) {
                this.u_1723_Y = "true".equals(compoundnbt1.M_588_G("fancyGraphics")) ? P_3084_J.J_1907_R : P_3084_J.n_1700_B;
            }
            for (String s : compoundnbt1.G_564_y()) {
                String s1 = compoundnbt1.M_588_G(s);
                try {
                    if ("autoJump".equals(s)) {
                        M_2935_g.AUTO_JUMP.n_1700_B(this, s1);
                    }
                    if ("autoSuggestions".equals(s)) {
                        M_2935_g.AUTO_SUGGEST_COMMANDS.n_1700_B(this, s1);
                    }
                    if ("chatColors".equals(s)) {
                        M_2935_g.CHAT_COLOR.n_1700_B(this, s1);
                    }
                    if ("chatLinks".equals(s)) {
                        M_2935_g.CHAT_LINKS.n_1700_B(this, s1);
                    }
                    if ("chatLinksPrompt".equals(s)) {
                        M_2935_g.CHAT_LINKS_PROMPT.n_1700_B(this, s1);
                    }
                    if ("enableVsync".equals(s)) {
                        M_2935_g.VSYNC.n_1700_B(this, s1);
                        if (this.q_4610_l) {
                            this.G_564_y = (int)M_2935_g.FRAMERATE_LIMIT.getMaxValue();
                        }
                        this.u_2550_I();
                    }
                    if ("entityShadows".equals(s)) {
                        M_2935_g.ENTITY_SHADOWS.n_1700_B(this, s1);
                    }
                    if ("forceUnicodeFont".equals(s)) {
                        M_2935_g.FORCE_UNICODE_FONT.n_1700_B(this, s1);
                    }
                    if ("discrete_mouse_scroll".equals(s)) {
                        M_2935_g.DISCRETE_MOUSE_SCROLL.n_1700_B(this, s1);
                    }
                    if ("invertYMouse".equals(s)) {
                        M_2935_g.INVERT_MOUSE.n_1700_B(this, s1);
                    }
                    if ("realmsNotifications".equals(s)) {
                        M_2935_g.REALMS_NOTIFICATIONS.n_1700_B(this, s1);
                    }
                    if ("reducedDebugInfo".equals(s)) {
                        M_2935_g.REDUCED_DEBUG_INFO.n_1700_B(this, s1);
                    }
                    if ("showSubtitles".equals(s)) {
                        M_2935_g.SHOW_SUBTITLES.n_1700_B(this, s1);
                    }
                    if ("snooperEnabled".equals(s)) {
                        M_2935_g.SNOOPER.n_1700_B(this, s1);
                    }
                    if ("touchscreen".equals(s)) {
                        M_2935_g.TOUCHSCREEN.n_1700_B(this, s1);
                    }
                    if ("fullscreen".equals(s)) {
                        M_2935_g.FULLSCREEN.n_1700_B(this, s1);
                    }
                    if ("bobView".equals(s)) {
                        M_2935_g.VIEW_BOBBING.n_1700_B(this, s1);
                    }
                    if ("toggleCrouch".equals(s)) {
                        this.D_4792_h = "true".equals(s1);
                    }
                    if ("toggleSprint".equals(s)) {
                        this.s_2632_s = "true".equals(s1);
                    }
                    if ("mouseSensitivity".equals(s)) {
                        this.n_1700_B = V_4423_d.n_1700_B(s1);
                    }
                    if ("fov".equals(s)) {
                        this.R_3077_Z = V_4423_d.n_1700_B(s1) * 40.0f + 70.0f;
                    }
                    if ("screenEffectScale".equals(s)) {
                        this.RealmsScreenWithCallback = V_4423_d.n_1700_B(s1);
                    }
                    if ("fovEffectScale".equals(s)) {
                        this.M_2677_i = V_4423_d.n_1700_B(s1);
                    }
                    if ("gamma".equals(s)) {
                        this.c_132_F = V_4423_d.n_1700_B(s1);
                    }
                    if ("renderDistance".equals(s)) {
                        this.J_1907_R = Integer.parseInt(s1);
                    }
                    if ("entityDistanceScaling".equals(s)) {
                        this.R_4764_Y = Float.parseFloat(s1);
                    }
                    if ("guiScale".equals(s)) {
                        this.g_4106_L = Integer.parseInt(s1);
                    }
                    if ("particles".equals(s)) {
                        this.RealmsClientOutdatedScreen = j_4067_x.n_1700_B(Integer.parseInt(s1));
                    }
                    if ("maxFps".equals(s)) {
                        this.G_564_y = Integer.parseInt(s1);
                        if (this.q_4610_l) {
                            this.G_564_y = (int)M_2935_g.FRAMERATE_LIMIT.getMaxValue();
                        }
                        if (this.G_564_y <= 0) {
                            this.G_564_y = (int)M_2935_g.FRAMERATE_LIMIT.getMaxValue();
                        }
                        if (this.y_1700_S.RealmsServerPing() != null) {
                            this.y_1700_S.RealmsServerPing().n_1700_B(this.G_564_y);
                        }
                    }
                    if ("difficulty".equals(s)) {
                        this.u_744_e = R_2450_T.n_1700_B(Integer.parseInt(s1));
                    }
                    if ("graphicsMode".equals(s)) {
                        this.u_1723_Y = P_3084_J.n_1700_B(Integer.parseInt(s1));
                        this.t_148_a();
                    }
                    if ("tutorialStep".equals(s)) {
                        this.Y_1740_V = t_4467_k.n_1700_B(s1);
                    }
                    if ("ao".equals(s)) {
                        this.v_4262_N = "true".equals(s1) ? G_463_a.R_4764_Y : ("false".equals(s1) ? G_463_a.n_1700_B : G_463_a.n_1700_B(Integer.parseInt(s1)));
                    }
                    if ("renderClouds".equals(s)) {
                        if ("true".equals(s1)) {
                            this.P_1922_E = K_2069_m.R_4764_Y;
                        } else if ("false".equals(s1)) {
                            this.P_1922_E = K_2069_m.n_1700_B;
                        } else if ("fast".equals(s1)) {
                            this.P_1922_E = K_2069_m.J_1907_R;
                        }
                    }
                    if ("attackIndicator".equals(s)) {
                        this.A_4115_X = E_4704_H.n_1700_B(Integer.parseInt(s1));
                    }
                    if ("resourcePacks".equals(s)) {
                        this.w_1484_f = i_4431_W.n_1700_B(C_415_h, s1, v_165_F);
                        if (this.w_1484_f == null) {
                            this.w_1484_f = Lists.newArrayList();
                        }
                    }
                    if ("incompatibleResourcePacks".equals(s)) {
                        this.t_148_a = i_4431_W.n_1700_B(C_415_h, s1, v_165_F);
                        if (this.t_148_a == null) {
                            this.t_148_a = Lists.newArrayList();
                        }
                    }
                    if ("lastServer".equals(s)) {
                        this.j_2266_I = s1;
                    }
                    if ("lang".equals(s)) {
                        this.RealmsConfirmScreen = s1;
                    }
                    if ("chatVisibility".equals(s)) {
                        this.s_956_w = g_4418_P.n_1700_B(Integer.parseInt(s1));
                    }
                    if ("chatOpacity".equals(s)) {
                        this.u_2550_I = V_4423_d.n_1700_B(s1);
                    }
                    if ("chatLineSpacing".equals(s)) {
                        this.M_588_G = V_4423_d.n_1700_B(s1);
                    }
                    if ("textBackgroundOpacity".equals(s)) {
                        this.P_4830_p = V_4423_d.n_1700_B(s1);
                    }
                    if ("backgroundForChatOnly".equals(s)) {
                        this.N_2525_X = "true".equals(s1);
                    }
                    if ("fullscreenResolution".equals(s)) {
                        this.h_1847_R = s1;
                    }
                    if ("hideServerAddress".equals(s)) {
                        this.Q_4569_t = "true".equals(s1);
                    }
                    if ("advancedItemTooltips".equals(s)) {
                        this.M_182_A = "true".equals(s1);
                    }
                    if ("pauseOnLostFocus".equals(s)) {
                        this.t_1786_h = "true".equals(s1);
                    }
                    if ("overrideHeight".equals(s)) {
                        this.Y_601_j = Integer.parseInt(s1);
                    }
                    if ("overrideWidth".equals(s)) {
                        this.w_1457_N = Integer.parseInt(s1);
                    }
                    if ("heldItemTooltips".equals(s)) {
                        this.Y_259_p = "true".equals(s1);
                    }
                    if ("chatHeightFocused".equals(s)) {
                        this.q_2307_F = V_4423_d.n_1700_B(s1);
                    }
                    if ("chatDelay".equals(s)) {
                        this.Z_875_P = V_4423_d.n_1700_B(s1);
                    }
                    if ("chatHeightUnfocused".equals(s)) {
                        this.k_2293_S = V_4423_d.n_1700_B(s1);
                    }
                    if ("chatScale".equals(s)) {
                        this.Q_2552_b = V_4423_d.n_1700_B(s1);
                    }
                    if ("chatWidth".equals(s)) {
                        this.C_2741_M = V_4423_d.n_1700_B(s1);
                    }
                    if ("mipmapLevels".equals(s)) {
                        this.c_3005_b = Integer.parseInt(s1);
                    }
                    if ("useNativeTransport".equals(s)) {
                        this.H_2857_Y = "true".equals(s1);
                    }
                    if ("mainHand".equals(s)) {
                        k_4231_L k_4231_L2 = this.multiplayerClientSuggestionProvider = "left".equals(s1) ? k_4231_L.n_1700_B : k_4231_L.J_1907_R;
                    }
                    if ("narrator".equals(s)) {
                        this.W_3464_O = e_3022_i.n_1700_B(Integer.parseInt(s1));
                    }
                    if ("biomeBlendRadius".equals(s)) {
                        this.x_607_J = Integer.parseInt(s1);
                    }
                    if ("mouseWheelSensitivity".equals(s)) {
                        this.e_4240_b = V_4423_d.n_1700_B(s1);
                    }
                    if ("rawMouseInput".equals(s)) {
                        this.n_3318_d = "true".equals(s1);
                    }
                    if ("glDebugVerbosity".equals(s)) {
                        this.d_2427_y = Integer.parseInt(s1);
                    }
                    if ("skipMultiplayerWarning".equals(s)) {
                        this.l_1233_K = "true".equals(s1);
                    }
                    if ("hideMatchedNames".equals(s)) {
                        this.z_1333_t = "true".equals(s1);
                    }
                    if ("joinedFirstServer".equals(s)) {
                        this.t_4043_B = "true".equals(s1);
                    }
                    if ("syncChunkWrites".equals(s)) {
                        this.RealmsCreateRealmScreen = "true".equals(s1);
                    }
                    for (D_590_W d_590_W : this.RealmsDefaultUncaughtExceptionHandler) {
                        if (!s.equals("key_" + d_590_W.v_4262_N())) continue;
                        if (Reflector.KeyModifier_valueFromString.exists()) {
                            if (s1.indexOf(58) != -1) {
                                String[] astring = s1.split(":");
                                Object object = Reflector.call(Reflector.KeyModifier_valueFromString, astring[1]);
                                Reflector.call(d_590_W, Reflector.ForgeKeyBinding_setKeyModifierAndCode, object, Q_4113_P.n_1700_B(astring[0]));
                                continue;
                            }
                            Object object1 = Reflector.getFieldValue(Reflector.KeyModifier_NONE);
                            Reflector.call(d_590_W, Reflector.ForgeKeyBinding_setKeyModifierAndCode, object1, Q_4113_P.n_1700_B(s1));
                            continue;
                        }
                        d_590_W.J_1907_R(Q_4113_P.n_1700_B(s1));
                    }
                    for (D_38_f d_38_f : D_38_f.values()) {
                        if (!s.equals("soundCategory_" + d_38_f.n_1700_B())) continue;
                        this.I_4348_c.put(d_38_f, Float.valueOf(V_4423_d.n_1700_B(s1)));
                    }
                    for (E_4346_v e_4346_v : E_4346_v.values()) {
                        if (!s.equals("modelPart_" + e_4346_v.J_1907_R())) continue;
                        this.n_1700_B(e_4346_v, "true".equals(s1));
                    }
                }
                catch (Exception exception) {
                    i_3196_G.warn("Skipping bad option: {}:{}", (Object)s, (Object)s1);
                    exception.printStackTrace();
                }
            }
            D_590_W.R_4764_Y();
        }
        catch (Exception exception11) {
            i_3196_G.error("Failed to load options", (Throwable)exception11);
        }
        this.v_4262_N();
    }

    private U_2912_j n_1700_B(U_2912_j nbt) {
        int i = 0;
        try {
            i = Integer.parseInt(nbt.M_588_G("version"));
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
        return n_3832_I.n_1700_B(this.y_1700_S.p_178_J(), o_1967_f.P_1922_E, nbt, i);
    }

    private static float n_1700_B(String floatString) {
        if ("true".equals(floatString)) {
            return 1.0f;
        }
        return "false".equals(floatString) ? 0.0f : Float.parseFloat(floatString);
    }

    public void J_1907_R() {
        if (!Reflector.ClientModLoader_isLoading.exists() || !Reflector.callBoolean(Reflector.ClientModLoader_isLoading, new Object[0])) {
            try (PrintWriter printwriter = new PrintWriter(new OutputStreamWriter((OutputStream)new FileOutputStream(this.O_3598_v), StandardCharsets.UTF_8));){
                printwriter.println("version:" + SharedConstants.n_1700_B().getWorldVersion());
                printwriter.println("autoJump:" + M_2935_g.AUTO_JUMP.J_1907_R(this));
                printwriter.println("autoSuggestions:" + M_2935_g.AUTO_SUGGEST_COMMANDS.J_1907_R(this));
                printwriter.println("chatColors:" + M_2935_g.CHAT_COLOR.J_1907_R(this));
                printwriter.println("chatLinks:" + M_2935_g.CHAT_LINKS.J_1907_R(this));
                printwriter.println("chatLinksPrompt:" + M_2935_g.CHAT_LINKS_PROMPT.J_1907_R(this));
                printwriter.println("enableVsync:" + M_2935_g.VSYNC.J_1907_R(this));
                printwriter.println("entityShadows:" + M_2935_g.ENTITY_SHADOWS.J_1907_R(this));
                printwriter.println("forceUnicodeFont:" + M_2935_g.FORCE_UNICODE_FONT.J_1907_R(this));
                printwriter.println("discrete_mouse_scroll:" + M_2935_g.DISCRETE_MOUSE_SCROLL.J_1907_R(this));
                printwriter.println("invertYMouse:" + M_2935_g.INVERT_MOUSE.J_1907_R(this));
                printwriter.println("realmsNotifications:" + M_2935_g.REALMS_NOTIFICATIONS.J_1907_R(this));
                printwriter.println("reducedDebugInfo:" + M_2935_g.REDUCED_DEBUG_INFO.J_1907_R(this));
                printwriter.println("snooperEnabled:" + M_2935_g.SNOOPER.J_1907_R(this));
                printwriter.println("showSubtitles:" + M_2935_g.SHOW_SUBTITLES.J_1907_R(this));
                printwriter.println("touchscreen:" + M_2935_g.TOUCHSCREEN.J_1907_R(this));
                printwriter.println("fullscreen:" + M_2935_g.FULLSCREEN.J_1907_R(this));
                printwriter.println("bobView:" + M_2935_g.VIEW_BOBBING.J_1907_R(this));
                printwriter.println("toggleCrouch:" + this.D_4792_h);
                printwriter.println("toggleSprint:" + this.s_2632_s);
                printwriter.println("mouseSensitivity:" + this.n_1700_B);
                printwriter.println("fov:" + (this.R_3077_Z - 70.0) / 40.0);
                printwriter.println("screenEffectScale:" + this.RealmsScreenWithCallback);
                printwriter.println("fovEffectScale:" + this.M_2677_i);
                printwriter.println("gamma:" + this.c_132_F);
                printwriter.println("renderDistance:" + this.J_1907_R);
                printwriter.println("entityDistanceScaling:" + this.R_4764_Y);
                printwriter.println("guiScale:" + this.g_4106_L);
                printwriter.println("particles:" + this.RealmsClientOutdatedScreen.J_1907_R());
                printwriter.println("maxFps:" + this.G_564_y);
                printwriter.println("difficulty:" + this.u_744_e.n_1700_B());
                printwriter.println("graphicsMode:" + this.u_1723_Y.n_1700_B());
                printwriter.println("ao:" + this.v_4262_N.n_1700_B());
                printwriter.println("biomeBlendRadius:" + this.x_607_J);
                switch (this.P_1922_E) {
                    case R_4764_Y: {
                        printwriter.println("renderClouds:true");
                        break;
                    }
                    case J_1907_R: {
                        printwriter.println("renderClouds:fast");
                        break;
                    }
                    case n_1700_B: {
                        printwriter.println("renderClouds:false");
                    }
                }
                printwriter.println("resourcePacks:" + C_415_h.toJson(this.w_1484_f));
                printwriter.println("incompatibleResourcePacks:" + C_415_h.toJson(this.t_148_a));
                printwriter.println("lastServer:" + this.j_2266_I);
                printwriter.println("lang:" + this.RealmsConfirmScreen);
                printwriter.println("chatVisibility:" + this.s_956_w.n_1700_B());
                printwriter.println("chatOpacity:" + this.u_2550_I);
                printwriter.println("chatLineSpacing:" + this.M_588_G);
                printwriter.println("textBackgroundOpacity:" + this.P_4830_p);
                printwriter.println("backgroundForChatOnly:" + this.N_2525_X);
                if (this.y_1700_S.RealmsServerPing().u_1723_Y().isPresent()) {
                    printwriter.println("fullscreenResolution:" + this.y_1700_S.RealmsServerPing().u_1723_Y().get().v_4262_N());
                }
                printwriter.println("hideServerAddress:" + this.Q_4569_t);
                printwriter.println("advancedItemTooltips:" + this.M_182_A);
                printwriter.println("pauseOnLostFocus:" + this.t_1786_h);
                printwriter.println("overrideWidth:" + this.w_1457_N);
                printwriter.println("overrideHeight:" + this.Y_601_j);
                printwriter.println("heldItemTooltips:" + this.Y_259_p);
                printwriter.println("chatHeightFocused:" + this.q_2307_F);
                printwriter.println("chatDelay: " + this.Z_875_P);
                printwriter.println("chatHeightUnfocused:" + this.k_2293_S);
                printwriter.println("chatScale:" + this.Q_2552_b);
                printwriter.println("chatWidth:" + (float)this.C_2741_M);
                printwriter.println("mipmapLevels:" + this.c_3005_b);
                printwriter.println("useNativeTransport:" + this.H_2857_Y);
                printwriter.println("mainHand:" + (this.multiplayerClientSuggestionProvider == k_4231_L.n_1700_B ? "left" : "right"));
                printwriter.println("attackIndicator:" + this.A_4115_X.n_1700_B());
                printwriter.println("narrator:" + this.W_3464_O.n_1700_B());
                printwriter.println("tutorialStep:" + this.Y_1740_V.n_1700_B());
                printwriter.println("mouseWheelSensitivity:" + this.e_4240_b);
                printwriter.println("rawMouseInput:" + M_2935_g.RAW_MOUSE_INPUT.J_1907_R(this));
                printwriter.println("glDebugVerbosity:" + this.d_2427_y);
                printwriter.println("skipMultiplayerWarning:" + this.l_1233_K);
                printwriter.println("hideMatchedNames:" + this.z_1333_t);
                printwriter.println("joinedFirstServer:" + this.t_4043_B);
                printwriter.println("syncChunkWrites:" + this.RealmsCreateRealmScreen);
                for (D_590_W d_590_W : this.RealmsDefaultUncaughtExceptionHandler) {
                    if (Reflector.ForgeKeyBinding_getKeyModifier.exists()) {
                        Object object1;
                        String s = "key_" + d_590_W.v_4262_N() + ":" + d_590_W.P_4830_p();
                        Object object = Reflector.call(d_590_W, Reflector.ForgeKeyBinding_getKeyModifier, new Object[0]);
                        printwriter.println(object != (object1 = Reflector.getFieldValue(Reflector.KeyModifier_NONE)) ? s + ":" + String.valueOf(object) : s);
                        continue;
                    }
                    printwriter.println("key_" + d_590_W.v_4262_N() + ":" + d_590_W.P_4830_p());
                }
                for (D_38_f d_38_f : D_38_f.values()) {
                    printwriter.println("soundCategory_" + d_38_f.n_1700_B() + ":" + this.n_1700_B(d_38_f));
                }
                for (E_4346_v e_4346_v : E_4346_v.values()) {
                    printwriter.println("modelPart_" + e_4346_v.J_1907_R() + ":" + this.b_2312_j.contains((Object)e_4346_v));
                }
            }
            catch (Exception exception1) {
                i_3196_G.error("Failed to save options", (Throwable)exception1);
            }
            this.w_1484_f();
            this.R_4764_Y();
        }
    }

    public float n_1700_B(D_38_f category) {
        return this.I_4348_c.containsKey((Object)category) ? this.I_4348_c.get((Object)category).floatValue() : 1.0f;
    }

    public void n_1700_B(D_38_f category, float volume) {
        this.I_4348_c.put(category, Float.valueOf(volume));
        this.y_1700_S.Z_976_R().n_1700_B(category, volume);
    }

    public void R_4764_Y() {
        if (this.y_1700_S.Y_259_p != null) {
            int i = 0;
            for (E_4346_v playermodelpart : this.b_2312_j) {
                i |= playermodelpart.n_1700_B();
            }
            this.y_1700_S.Y_259_p.n_1700_B.n_1700_B(new ServerboundClientInformationPacket(this.RealmsConfirmScreen, this.J_1907_R, this.s_956_w, this.d_2461_k, i, this.multiplayerClientSuggestionProvider));
            for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                bot.P_1922_E.Q_2552_b.n_1700_B.n_1700_B(new ServerboundClientInformationPacket(this.RealmsConfirmScreen, this.J_1907_R, this.s_956_w, this.d_2461_k, i, this.multiplayerClientSuggestionProvider));
            }
        }
    }

    public Set<E_4346_v> G_564_y() {
        return ImmutableSet.copyOf(this.b_2312_j);
    }

    public void n_1700_B(E_4346_v modelPart, boolean enable) {
        if (enable) {
            this.b_2312_j.add(modelPart);
        } else {
            this.b_2312_j.remove((Object)modelPart);
        }
        this.R_4764_Y();
    }

    public void n_1700_B(E_4346_v modelPart) {
        if (this.G_564_y().contains((Object)modelPart)) {
            this.b_2312_j.remove((Object)modelPart);
        } else {
            this.b_2312_j.add(modelPart);
        }
        this.R_4764_Y();
    }

    public K_2069_m P_1922_E() {
        return this.J_1907_R >= 4 ? this.P_1922_E : K_2069_m.n_1700_B;
    }

    public boolean u_1723_Y() {
        return this.H_2857_Y;
    }

    public void n_1700_B(M_2935_g p_setOptionFloatValueOF_1_, double p_setOptionFloatValueOF_2_) {
        if (p_setOptionFloatValueOF_1_ == M_2935_g.CLOUD_HEIGHT) {
            this.RealmsSettingsScreen = p_setOptionFloatValueOF_2_;
        }
        if (p_setOptionFloatValueOF_1_ == M_2935_g.AO_LEVEL) {
            this.RealmsResetNormalWorldScreen = p_setOptionFloatValueOF_2_;
            this.y_1700_S.u_1723_Y.P_1922_E();
        }
        if (p_setOptionFloatValueOF_1_ == M_2935_g.AA_LEVEL) {
            int i = (int)p_setOptionFloatValueOF_2_;
            if (i > 0 && Config.isShaders()) {
                Config.showGuiMessage(Lang.get("of.message.aa.shaders1"), Lang.get("of.message.aa.shaders2"));
                return;
            }
            if (i > 0 && Config.isGraphicsFabulous()) {
                Config.showGuiMessage(Lang.get("of.message.aa.gf1"), Lang.get("of.message.aa.gf2"));
                return;
            }
            this.C_3538_G = i;
            this.C_3538_G = Config.limit(this.C_3538_G, 0, 16);
        }
        if (p_setOptionFloatValueOF_1_ == M_2935_g.AF_LEVEL) {
            int j;
            this.A_3959_N = j = (int)p_setOptionFloatValueOF_2_;
            this.A_3959_N = Config.limit(this.A_3959_N, 1, 16);
            this.y_1700_S.Y_1740_V();
            Shaders.uninit();
        }
        if (p_setOptionFloatValueOF_1_ == M_2935_g.MIPMAP_TYPE) {
            int k = (int)p_setOptionFloatValueOF_2_;
            this.J_4256_G = Config.limit(k, 0, 3);
            this.M_588_G();
        }
    }

    public double n_1700_B(M_2935_g p_getOptionFloatValueOF_1_) {
        if (p_getOptionFloatValueOF_1_ == M_2935_g.CLOUD_HEIGHT) {
            return this.RealmsSettingsScreen;
        }
        if (p_getOptionFloatValueOF_1_ == M_2935_g.AO_LEVEL) {
            return this.RealmsResetNormalWorldScreen;
        }
        if (p_getOptionFloatValueOF_1_ == M_2935_g.AA_LEVEL) {
            return this.C_3538_G;
        }
        if (p_getOptionFloatValueOF_1_ == M_2935_g.AF_LEVEL) {
            return this.A_3959_N;
        }
        if (p_getOptionFloatValueOF_1_ == M_2935_g.MIPMAP_TYPE) {
            return this.J_4256_G;
        }
        if (p_getOptionFloatValueOF_1_ == M_2935_g.FRAMERATE_LIMIT) {
            return (double)this.G_564_y == M_2935_g.FRAMERATE_LIMIT.getMaxValue() && this.q_4610_l ? 0.0 : (double)this.G_564_y;
        }
        return 3.4028234663852886E38;
    }

    public void n_1700_B(M_2935_g p_setOptionValueOF_1_, int p_setOptionValueOF_2_) {
        if (p_setOptionValueOF_1_ == M_2935_g.FOG_FANCY) {
            switch (this.C_290_v) {
                case 1: {
                    this.C_290_v = 2;
                    if (Config.isFancyFogAvailable()) break;
                    this.C_290_v = 3;
                    break;
                }
                case 2: {
                    this.C_290_v = 3;
                    break;
                }
                case 3: {
                    this.C_290_v = 1;
                    break;
                }
                default: {
                    this.C_290_v = 1;
                }
            }
        }
        if (p_setOptionValueOF_1_ == M_2935_g.FOG_START) {
            this.w_728_N += 0.2f;
            if (this.w_728_N > 0.81f) {
                this.w_728_N = 0.2f;
            }
        }
        if (p_setOptionValueOF_1_ == M_2935_g.SMOOTH_FPS) {
            boolean bl = this.RealmsLongRunningMcoTaskScreen = !this.RealmsLongRunningMcoTaskScreen;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.SMOOTH_WORLD) {
            this.i_2993_w = !this.i_2993_w;
            Config.updateThreadPriorities();
        }
        if (p_setOptionValueOF_1_ == M_2935_g.CLOUDS) {
            ++this.G_424_k;
            if (this.G_424_k > 3) {
                this.G_424_k = 0;
            }
            this.t_148_a();
        }
        if (p_setOptionValueOF_1_ == M_2935_g.TREES) {
            this.f_1043_S = V_4423_d.n_1700_B(this.f_1043_S, M_766_z);
            this.y_1700_S.u_1723_Y.P_1922_E();
        }
        if (p_setOptionValueOF_1_ == M_2935_g.DROPPED_ITEMS) {
            ++this.J_739_q;
            if (this.J_739_q > 2) {
                this.J_739_q = 0;
            }
        }
        if (p_setOptionValueOF_1_ == M_2935_g.RAIN) {
            ++this.F_4247_a;
            if (this.F_4247_a > 3) {
                this.F_4247_a = 0;
            }
        }
        if (p_setOptionValueOF_1_ == M_2935_g.ANIMATED_WATER) {
            ++this.S_3139_t;
            if (this.S_3139_t == 1) {
                ++this.S_3139_t;
            }
            if (this.S_3139_t > 2) {
                this.S_3139_t = 0;
            }
        }
        if (p_setOptionValueOF_1_ == M_2935_g.ANIMATED_LAVA) {
            ++this.k_2302_P;
            if (this.k_2302_P == 1) {
                ++this.k_2302_P;
            }
            if (this.k_2302_P > 2) {
                this.k_2302_P = 0;
            }
        }
        if (p_setOptionValueOF_1_ == M_2935_g.ANIMATED_FIRE) {
            boolean bl = this.t_3452_g = !this.t_3452_g;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.ANIMATED_PORTAL) {
            boolean bl = this.V_118_c = !this.V_118_c;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.ANIMATED_REDSTONE) {
            boolean bl = this.I_1407_m = !this.I_1407_m;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.ANIMATED_EXPLOSION) {
            boolean bl = this.o_2767_H = !this.o_2767_H;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.ANIMATED_FLAME) {
            boolean bl = this.d_2545_n = !this.d_2545_n;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.ANIMATED_SMOKE) {
            boolean bl = this.x_92_N = !this.x_92_N;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.VOID_PARTICLES) {
            boolean bl = this.i_601_W = !this.i_601_W;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.WATER_PARTICLES) {
            boolean bl = this.h_2739_B = !this.h_2739_B;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.PORTAL_PARTICLES) {
            boolean bl = this.x_4991_F = !this.x_4991_F;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.POTION_PARTICLES) {
            boolean bl = this.Z_759_W = !this.Z_759_W;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.FIREWORK_PARTICLES) {
            boolean bl = this.f_1574_f = !this.f_1574_f;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.DRIPPING_WATER_LAVA) {
            boolean bl = this.l_1268_F = !this.l_1268_F;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.ANIMATED_TERRAIN) {
            boolean bl = this.J_303_C = !this.J_303_C;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.ANIMATED_TEXTURES) {
            boolean bl = this.o_1800_r = !this.o_1800_r;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.RAIN_SPLASH) {
            boolean bl = this.X_1313_W = !this.X_1313_W;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.LAGOMETER) {
            boolean bl = this.f_3449_S = !this.f_3449_S;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.SHOW_FPS) {
            boolean bl = this.JsonUtils = !this.JsonUtils;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.AUTOSAVE_TICKS) {
            int i = 900;
            this.D_4361_a = Math.max(this.D_4361_a / i * i, i);
            this.D_4361_a *= 2;
            if (this.D_4361_a > 32 * i) {
                this.D_4361_a = i;
            }
        }
        if (p_setOptionValueOF_1_ == M_2935_g.BETTER_GRASS) {
            ++this.C_1162_e;
            if (this.C_1162_e > 3) {
                this.C_1162_e = 1;
            }
            this.y_1700_S.u_1723_Y.P_1922_E();
        }
        if (p_setOptionValueOF_1_ == M_2935_g.CONNECTED_TEXTURES) {
            ++this.P_5000_x;
            if (this.P_5000_x > 3) {
                this.P_5000_x = 1;
            }
            if (this.P_5000_x == 2) {
                this.y_1700_S.u_1723_Y.P_1922_E();
            } else {
                this.y_1700_S.Y_1740_V();
            }
        }
        if (p_setOptionValueOF_1_ == M_2935_g.WEATHER) {
            boolean bl = this.RealmsPersistence = !this.RealmsPersistence;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.SKY) {
            boolean bl = this.y_2772_m = !this.y_2772_m;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.STARS) {
            boolean bl = this.H_1883_T = !this.H_1883_T;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.SUN_MOON) {
            boolean bl = this.d_4007_L = !this.d_4007_L;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.VIGNETTE) {
            ++this.TextRenderingUtils;
            if (this.TextRenderingUtils > 2) {
                this.TextRenderingUtils = 0;
            }
        }
        if (p_setOptionValueOF_1_ == M_2935_g.CHUNK_UPDATES) {
            ++this.UploadTokenCache;
            if (this.UploadTokenCache > 5) {
                this.UploadTokenCache = 1;
            }
        }
        if (p_setOptionValueOF_1_ == M_2935_g.CHUNK_UPDATES_DYNAMIC) {
            boolean bl = this.U_1341_G = !this.U_1341_G;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.TIME) {
            ++this.ClientBootstrap;
            if (this.ClientBootstrap > 2) {
                this.ClientBootstrap = 0;
            }
        }
        if (p_setOptionValueOF_1_ == M_2935_g.PROFILER) {
            boolean bl = this.u_55_V = !this.u_55_V;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.BETTER_SNOW) {
            this.o_2341_D = !this.o_2341_D;
            this.y_1700_S.u_1723_Y.P_1922_E();
        }
        if (p_setOptionValueOF_1_ == M_2935_g.SWAMP_COLORS) {
            this.C_1269_X = !this.C_1269_X;
            CustomColors.updateUseDefaultGrassFoliageColors();
            this.y_1700_S.u_1723_Y.P_1922_E();
        }
        if (p_setOptionValueOF_1_ == M_2935_g.RANDOM_ENTITIES) {
            this.x_612_B = !this.x_612_B;
            RandomEntities.update();
        }
        if (p_setOptionValueOF_1_ == M_2935_g.CUSTOM_FONTS) {
            this.t_1446_I = !this.t_1446_I;
            FontUtils.reloadFonts();
        }
        if (p_setOptionValueOF_1_ == M_2935_g.CUSTOM_COLORS) {
            this.j_306_t = !this.j_306_t;
            CustomColors.update();
            this.y_1700_S.u_1723_Y.P_1922_E();
        }
        if (p_setOptionValueOF_1_ == M_2935_g.CUSTOM_ITEMS) {
            this.L_4248_u = !this.L_4248_u;
            this.y_1700_S.Y_1740_V();
        }
        if (p_setOptionValueOF_1_ == M_2935_g.CUSTOM_SKY) {
            this.F_3572_x = !this.F_3572_x;
            CustomSky.update();
        }
        if (p_setOptionValueOF_1_ == M_2935_g.SHOW_CAPES) {
            boolean bl = this.L_1362_X = !this.L_1362_X;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.NATURAL_TEXTURES) {
            this.O_1309_Q = !this.O_1309_Q;
            NaturalTextures.update();
            this.y_1700_S.u_1723_Y.P_1922_E();
        }
        if (p_setOptionValueOF_1_ == M_2935_g.EMISSIVE_TEXTURES) {
            this.O_2934_T = !this.O_2934_T;
            this.y_1700_S.Y_1740_V();
        }
        if (p_setOptionValueOF_1_ == M_2935_g.FAST_MATH) {
            u_530_F.u_1723_Y = this.l_4088_R = !this.l_4088_R;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.FAST_RENDER) {
            boolean bl = this.Z_735_d = !this.Z_735_d;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.TRANSLUCENT_BLOCKS) {
            this.P_925_e = this.P_925_e == 0 ? 1 : (this.P_925_e == 1 ? 2 : (this.P_925_e == 2 ? 0 : 0));
            this.y_1700_S.u_1723_Y.P_1922_E();
        }
        if (p_setOptionValueOF_1_ == M_2935_g.LAZY_CHUNK_LOADING) {
            boolean bl = this.RealmsParentalConsentScreen = !this.RealmsParentalConsentScreen;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.RENDER_REGIONS) {
            this.O_2151_c = !this.O_2151_c;
            this.y_1700_S.u_1723_Y.P_1922_E();
        }
        if (p_setOptionValueOF_1_ == M_2935_g.SMART_ANIMATIONS) {
            this.s_1671_u = !this.s_1671_u;
            this.y_1700_S.u_1723_Y.P_1922_E();
        }
        if (p_setOptionValueOF_1_ == M_2935_g.DYNAMIC_FOV) {
            boolean bl = this.X_4895_T = !this.X_4895_T;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.ALTERNATE_BLOCKS) {
            this.L_103_L = !this.L_103_L;
            this.y_1700_S.u_1723_Y.P_1922_E();
        }
        if (p_setOptionValueOF_1_ == M_2935_g.DYNAMIC_LIGHTS) {
            this.n_3197_X = V_4423_d.n_1700_B(this.n_3197_X, X_2048_Y);
            DynamicLights.removeLights(this.y_1700_S.u_1723_Y);
        }
        if (p_setOptionValueOF_1_ == M_2935_g.SCREENSHOT_SIZE) {
            ++this.F_518_D;
            if (this.F_518_D > 4) {
                this.F_518_D = 1;
            }
        }
        if (p_setOptionValueOF_1_ == M_2935_g.CUSTOM_ENTITY_MODELS) {
            this.P_2947_S = !this.P_2947_S;
            this.y_1700_S.Y_1740_V();
        }
        if (p_setOptionValueOF_1_ == M_2935_g.CUSTOM_GUIS) {
            this.O_4761_U = !this.O_4761_U;
            CustomGuis.update();
        }
        if (p_setOptionValueOF_1_ == M_2935_g.SHOW_GL_ERRORS) {
            boolean bl = this.w_2705_t = !this.w_2705_t;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.HELD_ITEM_TOOLTIPS) {
            boolean bl = this.Y_259_p = !this.Y_259_p;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.ADVANCED_TOOLTIPS) {
            boolean bl = this.M_182_A = !this.M_182_A;
        }
        if (p_setOptionValueOF_1_ == M_2935_g.CHAT_BACKGROUND) {
            this.L_3570_A = this.L_3570_A == 0 ? 5 : (this.L_3570_A == 5 ? 3 : 0);
        }
        if (p_setOptionValueOF_1_ == M_2935_g.CHAT_SHADOW) {
            this.Y_776_s = !this.Y_776_s;
        }
    }

    public x_282_a J_1907_R(M_2935_g p_getKeyComponentOF_1_) {
        String s = this.R_4764_Y(p_getKeyComponentOF_1_);
        U_2871_b itextcomponent = new U_2871_b(s);
        return itextcomponent;
    }

    public String R_4764_Y(M_2935_g p_getKeyBindingOF_1_) {
        Object s = K_1289_S.n_1700_B(p_getKeyBindingOF_1_.getResourceKey(), new Object[0]) + ": ";
        if (s == null) {
            s = p_getKeyBindingOF_1_.getResourceKey();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.RENDER_DISTANCE) {
            int i1 = (int)M_2935_g.RENDER_DISTANCE.get(this);
            String s2 = K_1289_S.n_1700_B("of.options.renderDistance.tiny", new Object[0]);
            int i = 2;
            if (i1 >= 4) {
                s2 = K_1289_S.n_1700_B("of.options.renderDistance.short", new Object[0]);
                i = 4;
            }
            if (i1 >= 8) {
                s2 = K_1289_S.n_1700_B("of.options.renderDistance.normal", new Object[0]);
                i = 8;
            }
            if (i1 >= 16) {
                s2 = K_1289_S.n_1700_B("of.options.renderDistance.far", new Object[0]);
                i = 16;
            }
            if (i1 >= 32) {
                s2 = Lang.get("of.options.renderDistance.extreme");
                i = 32;
            }
            if (i1 >= 48) {
                s2 = Lang.get("of.options.renderDistance.insane");
                i = 48;
            }
            if (i1 >= 64) {
                s2 = Lang.get("of.options.renderDistance.ludicrous");
                i = 64;
            }
            int j = this.J_1907_R - i;
            Object s1 = s2;
            if (j > 0) {
                s1 = s2 + "+";
            }
            return (String)s + i1 + " " + (String)s1;
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.FOG_FANCY) {
            switch (this.C_290_v) {
                case 1: {
                    return (String)s + Lang.getFast();
                }
                case 2: {
                    return (String)s + Lang.getFancy();
                }
                case 3: {
                    return (String)s + Lang.getOff();
                }
            }
            return (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.FOG_START) {
            return (String)s + this.w_728_N;
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.MIPMAP_TYPE) {
            return FloatOptions.getText(p_getKeyBindingOF_1_, this.J_4256_G);
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.SMOOTH_FPS) {
            return this.RealmsLongRunningMcoTaskScreen ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.SMOOTH_WORLD) {
            return this.i_2993_w ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.CLOUDS) {
            switch (this.G_424_k) {
                case 1: {
                    return (String)s + Lang.getFast();
                }
                case 2: {
                    return (String)s + Lang.getFancy();
                }
                case 3: {
                    return (String)s + Lang.getOff();
                }
            }
            return (String)s + Lang.getDefault();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.TREES) {
            switch (this.f_1043_S) {
                case 1: {
                    return (String)s + Lang.getFast();
                }
                case 2: {
                    return (String)s + Lang.getFancy();
                }
                default: {
                    return (String)s + Lang.getDefault();
                }
                case 4: 
            }
            return (String)s + Lang.get("of.general.smart");
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.DROPPED_ITEMS) {
            switch (this.J_739_q) {
                case 1: {
                    return (String)s + Lang.getFast();
                }
                case 2: {
                    return (String)s + Lang.getFancy();
                }
            }
            return (String)s + Lang.getDefault();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.RAIN) {
            switch (this.F_4247_a) {
                case 1: {
                    return (String)s + Lang.getFast();
                }
                case 2: {
                    return (String)s + Lang.getFancy();
                }
                case 3: {
                    return (String)s + Lang.getOff();
                }
            }
            return (String)s + Lang.getDefault();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.ANIMATED_WATER) {
            switch (this.S_3139_t) {
                case 1: {
                    return (String)s + Lang.get("of.options.animation.dynamic");
                }
                case 2: {
                    return (String)s + Lang.getOff();
                }
            }
            return (String)s + Lang.getOn();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.ANIMATED_LAVA) {
            switch (this.k_2302_P) {
                case 1: {
                    return (String)s + Lang.get("of.options.animation.dynamic");
                }
                case 2: {
                    return (String)s + Lang.getOff();
                }
            }
            return (String)s + Lang.getOn();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.ANIMATED_FIRE) {
            return this.t_3452_g ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.ANIMATED_PORTAL) {
            return this.V_118_c ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.ANIMATED_REDSTONE) {
            return this.I_1407_m ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.ANIMATED_EXPLOSION) {
            return this.o_2767_H ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.ANIMATED_FLAME) {
            return this.d_2545_n ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.ANIMATED_SMOKE) {
            return this.x_92_N ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.VOID_PARTICLES) {
            return this.i_601_W ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.WATER_PARTICLES) {
            return this.h_2739_B ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.PORTAL_PARTICLES) {
            return this.x_4991_F ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.POTION_PARTICLES) {
            return this.Z_759_W ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.FIREWORK_PARTICLES) {
            return this.f_1574_f ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.DRIPPING_WATER_LAVA) {
            return this.l_1268_F ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.ANIMATED_TERRAIN) {
            return this.J_303_C ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.ANIMATED_TEXTURES) {
            return this.o_1800_r ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.RAIN_SPLASH) {
            return this.X_1313_W ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.LAGOMETER) {
            return this.f_3449_S ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.SHOW_FPS) {
            return this.JsonUtils ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.AUTOSAVE_TICKS) {
            int l = 900;
            if (this.D_4361_a <= l) {
                return (String)s + Lang.get("of.options.save.45s");
            }
            if (this.D_4361_a <= 2 * l) {
                return (String)s + Lang.get("of.options.save.90s");
            }
            if (this.D_4361_a <= 4 * l) {
                return (String)s + Lang.get("of.options.save.3min");
            }
            if (this.D_4361_a <= 8 * l) {
                return (String)s + Lang.get("of.options.save.6min");
            }
            return this.D_4361_a <= 16 * l ? (String)s + Lang.get("of.options.save.12min") : (String)s + Lang.get("of.options.save.24min");
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.BETTER_GRASS) {
            switch (this.C_1162_e) {
                case 1: {
                    return (String)s + Lang.getFast();
                }
                case 2: {
                    return (String)s + Lang.getFancy();
                }
            }
            return (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.CONNECTED_TEXTURES) {
            switch (this.P_5000_x) {
                case 1: {
                    return (String)s + Lang.getFast();
                }
                case 2: {
                    return (String)s + Lang.getFancy();
                }
            }
            return (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.WEATHER) {
            return this.RealmsPersistence ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.SKY) {
            return this.y_2772_m ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.STARS) {
            return this.H_1883_T ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.SUN_MOON) {
            return this.d_4007_L ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.VIGNETTE) {
            switch (this.TextRenderingUtils) {
                case 1: {
                    return (String)s + Lang.getFast();
                }
                case 2: {
                    return (String)s + Lang.getFancy();
                }
            }
            return (String)s + Lang.getDefault();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.CHUNK_UPDATES) {
            return (String)s + this.UploadTokenCache;
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.CHUNK_UPDATES_DYNAMIC) {
            return this.U_1341_G ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.TIME) {
            if (this.ClientBootstrap == 1) {
                return (String)s + Lang.get("of.options.time.dayOnly");
            }
            return this.ClientBootstrap == 2 ? (String)s + Lang.get("of.options.time.nightOnly") : (String)s + Lang.getDefault();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.AA_LEVEL) {
            return FloatOptions.getText(p_getKeyBindingOF_1_, this.C_3538_G);
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.AF_LEVEL) {
            return FloatOptions.getText(p_getKeyBindingOF_1_, this.A_3959_N);
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.PROFILER) {
            return this.u_55_V ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.BETTER_SNOW) {
            return this.o_2341_D ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.SWAMP_COLORS) {
            return this.C_1269_X ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.RANDOM_ENTITIES) {
            return this.x_612_B ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.CUSTOM_FONTS) {
            return this.t_1446_I ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.CUSTOM_COLORS) {
            return this.j_306_t ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.CUSTOM_SKY) {
            return this.F_3572_x ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.SHOW_CAPES) {
            return this.L_1362_X ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.CUSTOM_ITEMS) {
            return this.L_4248_u ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.NATURAL_TEXTURES) {
            return this.O_1309_Q ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.EMISSIVE_TEXTURES) {
            return this.O_2934_T ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.FAST_MATH) {
            return this.l_4088_R ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.FAST_RENDER) {
            return this.Z_735_d ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.TRANSLUCENT_BLOCKS) {
            if (this.P_925_e == 1) {
                return (String)s + Lang.getFast();
            }
            return this.P_925_e == 2 ? (String)s + Lang.getFancy() : (String)s + Lang.getDefault();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.LAZY_CHUNK_LOADING) {
            return this.RealmsParentalConsentScreen ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.RENDER_REGIONS) {
            return this.O_2151_c ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.SMART_ANIMATIONS) {
            return this.s_1671_u ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.DYNAMIC_FOV) {
            return this.X_4895_T ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.ALTERNATE_BLOCKS) {
            return this.L_103_L ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.DYNAMIC_LIGHTS) {
            int k = V_4423_d.R_4764_Y(this.n_3197_X, X_2048_Y);
            return (String)s + V_4423_d.n_1700_B(l_2647_k, k);
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.SCREENSHOT_SIZE) {
            return this.F_518_D <= 1 ? (String)s + Lang.getDefault() : (String)s + this.F_518_D + "x";
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.CUSTOM_ENTITY_MODELS) {
            return this.P_2947_S ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.CUSTOM_GUIS) {
            return this.O_4761_U ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.SHOW_GL_ERRORS) {
            return this.w_2705_t ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.HELD_ITEM_TOOLTIPS) {
            return this.Y_259_p ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.ADVANCED_TOOLTIPS) {
            return this.M_182_A ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.FRAMERATE_LIMIT) {
            double d1 = M_2935_g.FRAMERATE_LIMIT.get(this);
            if (d1 == 0.0) {
                return (String)s + Lang.get("of.options.framerateLimit.vsync");
            }
            return d1 == M_2935_g.FRAMERATE_LIMIT.getMaxValue() ? (String)s + K_1289_S.n_1700_B("options.framerateLimit.max", new Object[0]) : (String)s + (int)d1 + " fps";
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.CHAT_BACKGROUND) {
            if (this.L_3570_A == 3) {
                return (String)s + Lang.getOff();
            }
            return this.L_3570_A == 5 ? (String)s + Lang.get("of.general.compact") : (String)s + Lang.getDefault();
        }
        if (p_getKeyBindingOF_1_ == M_2935_g.CHAT_SHADOW) {
            return this.Y_776_s ? (String)s + Lang.getOn() : (String)s + Lang.getOff();
        }
        if (p_getKeyBindingOF_1_ instanceof I_2212_R) {
            I_2212_R sliderpercentageoption = (I_2212_R)p_getKeyBindingOF_1_;
            double d0 = sliderpercentageoption.get(this);
            return d0 == 0.0 ? (String)s + K_1289_S.n_1700_B("options.off", new Object[0]) : (String)s + (int)(d0 * 100.0) + "%";
        }
        return null;
    }

    public void v_4262_N() {
        try {
            File file1 = this.a_178_J;
            if (!file1.exists()) {
                file1 = this.O_3598_v;
            }
            if (!file1.exists()) {
                return;
            }
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader((InputStream)new FileInputStream(file1), StandardCharsets.UTF_8));
            String s = "";
            while ((s = bufferedreader.readLine()) != null) {
                try {
                    String[] astring = s.split(":");
                    if (astring[0].equals("ofRenderDistanceChunks") && astring.length >= 2) {
                        this.J_1907_R = Integer.valueOf(astring[1]);
                        this.J_1907_R = Config.limit(this.J_1907_R, 2, 1024);
                    }
                    if (astring[0].equals("ofFogType") && astring.length >= 2) {
                        this.C_290_v = Integer.valueOf(astring[1]);
                        this.C_290_v = Config.limit(this.C_290_v, 1, 3);
                    }
                    if (astring[0].equals("ofFogStart") && astring.length >= 2) {
                        this.w_728_N = Float.valueOf(astring[1]).floatValue();
                        if (this.w_728_N < 0.2f) {
                            this.w_728_N = 0.2f;
                        }
                        if (this.w_728_N > 0.81f) {
                            this.w_728_N = 0.8f;
                        }
                    }
                    if (astring[0].equals("ofMipmapType") && astring.length >= 2) {
                        this.J_4256_G = Integer.valueOf(astring[1]);
                        this.J_4256_G = Config.limit(this.J_4256_G, 0, 3);
                    }
                    if (astring[0].equals("ofOcclusionFancy") && astring.length >= 2) {
                        this.RealmsLongConfirmationScreen = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofSmoothFps") && astring.length >= 2) {
                        this.RealmsLongRunningMcoTaskScreen = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofSmoothWorld") && astring.length >= 2) {
                        this.i_2993_w = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofAoLevel") && astring.length >= 2) {
                        this.RealmsResetNormalWorldScreen = Float.valueOf(astring[1]).floatValue();
                        this.RealmsResetNormalWorldScreen = Config.limit(this.RealmsResetNormalWorldScreen, 0.0, 1.0);
                    }
                    if (astring[0].equals("ofClouds") && astring.length >= 2) {
                        this.G_424_k = Integer.valueOf(astring[1]);
                        this.G_424_k = Config.limit(this.G_424_k, 0, 3);
                        this.t_148_a();
                    }
                    if (astring[0].equals("ofCloudsHeight") && astring.length >= 2) {
                        this.RealmsSettingsScreen = Float.valueOf(astring[1]).floatValue();
                        this.RealmsSettingsScreen = Config.limit(this.RealmsSettingsScreen, 0.0, 1.0);
                    }
                    if (astring[0].equals("ofTrees") && astring.length >= 2) {
                        this.f_1043_S = Integer.valueOf(astring[1]);
                        this.f_1043_S = V_4423_d.J_1907_R(this.f_1043_S, M_766_z);
                    }
                    if (astring[0].equals("ofDroppedItems") && astring.length >= 2) {
                        this.J_739_q = Integer.valueOf(astring[1]);
                        this.J_739_q = Config.limit(this.J_739_q, 0, 2);
                    }
                    if (astring[0].equals("ofRain") && astring.length >= 2) {
                        this.F_4247_a = Integer.valueOf(astring[1]);
                        this.F_4247_a = Config.limit(this.F_4247_a, 0, 3);
                    }
                    if (astring[0].equals("ofAnimatedWater") && astring.length >= 2) {
                        this.S_3139_t = Integer.valueOf(astring[1]);
                        this.S_3139_t = Config.limit(this.S_3139_t, 0, 2);
                    }
                    if (astring[0].equals("ofAnimatedLava") && astring.length >= 2) {
                        this.k_2302_P = Integer.valueOf(astring[1]);
                        this.k_2302_P = Config.limit(this.k_2302_P, 0, 2);
                    }
                    if (astring[0].equals("ofAnimatedFire") && astring.length >= 2) {
                        this.t_3452_g = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofAnimatedPortal") && astring.length >= 2) {
                        this.V_118_c = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofAnimatedRedstone") && astring.length >= 2) {
                        this.I_1407_m = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofAnimatedExplosion") && astring.length >= 2) {
                        this.o_2767_H = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofAnimatedFlame") && astring.length >= 2) {
                        this.d_2545_n = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofAnimatedSmoke") && astring.length >= 2) {
                        this.x_92_N = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofVoidParticles") && astring.length >= 2) {
                        this.i_601_W = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofWaterParticles") && astring.length >= 2) {
                        this.h_2739_B = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofPortalParticles") && astring.length >= 2) {
                        this.x_4991_F = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofPotionParticles") && astring.length >= 2) {
                        this.Z_759_W = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofFireworkParticles") && astring.length >= 2) {
                        this.f_1574_f = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofDrippingWaterLava") && astring.length >= 2) {
                        this.l_1268_F = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofAnimatedTerrain") && astring.length >= 2) {
                        this.J_303_C = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofAnimatedTextures") && astring.length >= 2) {
                        this.o_1800_r = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofRainSplash") && astring.length >= 2) {
                        this.X_1313_W = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofLagometer") && astring.length >= 2) {
                        this.f_3449_S = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofShowFps") && astring.length >= 2) {
                        this.JsonUtils = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofAutoSaveTicks") && astring.length >= 2) {
                        this.D_4361_a = Integer.valueOf(astring[1]);
                        this.D_4361_a = Config.limit(this.D_4361_a, 40, 40000);
                    }
                    if (astring[0].equals("ofBetterGrass") && astring.length >= 2) {
                        this.C_1162_e = Integer.valueOf(astring[1]);
                        this.C_1162_e = Config.limit(this.C_1162_e, 1, 3);
                    }
                    if (astring[0].equals("ofConnectedTextures") && astring.length >= 2) {
                        this.P_5000_x = Integer.valueOf(astring[1]);
                        this.P_5000_x = Config.limit(this.P_5000_x, 1, 3);
                    }
                    if (astring[0].equals("ofWeather") && astring.length >= 2) {
                        this.RealmsPersistence = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofSky") && astring.length >= 2) {
                        this.y_2772_m = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofStars") && astring.length >= 2) {
                        this.H_1883_T = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofSunMoon") && astring.length >= 2) {
                        this.d_4007_L = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofVignette") && astring.length >= 2) {
                        this.TextRenderingUtils = Integer.valueOf(astring[1]);
                        this.TextRenderingUtils = Config.limit(this.TextRenderingUtils, 0, 2);
                    }
                    if (astring[0].equals("ofChunkUpdates") && astring.length >= 2) {
                        this.UploadTokenCache = Integer.valueOf(astring[1]);
                        this.UploadTokenCache = Config.limit(this.UploadTokenCache, 1, 5);
                    }
                    if (astring[0].equals("ofChunkUpdatesDynamic") && astring.length >= 2) {
                        this.U_1341_G = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofTime") && astring.length >= 2) {
                        this.ClientBootstrap = Integer.valueOf(astring[1]);
                        this.ClientBootstrap = Config.limit(this.ClientBootstrap, 0, 2);
                    }
                    if (astring[0].equals("ofAaLevel") && astring.length >= 2) {
                        this.C_3538_G = Integer.valueOf(astring[1]);
                        this.C_3538_G = Config.limit(this.C_3538_G, 0, 16);
                    }
                    if (astring[0].equals("ofAfLevel") && astring.length >= 2) {
                        this.A_3959_N = Integer.valueOf(astring[1]);
                        this.A_3959_N = Config.limit(this.A_3959_N, 1, 16);
                    }
                    if (astring[0].equals("ofProfiler") && astring.length >= 2) {
                        this.u_55_V = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofBetterSnow") && astring.length >= 2) {
                        this.o_2341_D = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofSwampColors") && astring.length >= 2) {
                        this.C_1269_X = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofRandomEntities") && astring.length >= 2) {
                        this.x_612_B = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofCustomFonts") && astring.length >= 2) {
                        this.t_1446_I = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofCustomColors") && astring.length >= 2) {
                        this.j_306_t = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofCustomItems") && astring.length >= 2) {
                        this.L_4248_u = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofCustomSky") && astring.length >= 2) {
                        this.F_3572_x = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofShowCapes") && astring.length >= 2) {
                        this.L_1362_X = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofNaturalTextures") && astring.length >= 2) {
                        this.O_1309_Q = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofEmissiveTextures") && astring.length >= 2) {
                        this.O_2934_T = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofLazyChunkLoading") && astring.length >= 2) {
                        this.RealmsParentalConsentScreen = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofRenderRegions") && astring.length >= 2) {
                        this.O_2151_c = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofSmartAnimations") && astring.length >= 2) {
                        this.s_1671_u = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofDynamicFov") && astring.length >= 2) {
                        this.X_4895_T = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofAlternateBlocks") && astring.length >= 2) {
                        this.L_103_L = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofDynamicLights") && astring.length >= 2) {
                        this.n_3197_X = Integer.valueOf(astring[1]);
                        this.n_3197_X = V_4423_d.J_1907_R(this.n_3197_X, X_2048_Y);
                    }
                    if (astring[0].equals("ofScreenshotSize") && astring.length >= 2) {
                        this.F_518_D = Integer.valueOf(astring[1]);
                        this.F_518_D = Config.limit(this.F_518_D, 1, 4);
                    }
                    if (astring[0].equals("ofCustomEntityModels") && astring.length >= 2) {
                        this.P_2947_S = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofCustomGuis") && astring.length >= 2) {
                        this.O_4761_U = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofShowGlErrors") && astring.length >= 2) {
                        this.w_2705_t = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofFastMath") && astring.length >= 2) {
                        u_530_F.u_1723_Y = this.l_4088_R = Boolean.valueOf(astring[1]).booleanValue();
                    }
                    if (astring[0].equals("ofFastRender") && astring.length >= 2) {
                        this.Z_735_d = Boolean.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofTranslucentBlocks") && astring.length >= 2) {
                        this.P_925_e = Integer.valueOf(astring[1]);
                        this.P_925_e = Config.limit(this.P_925_e, 0, 2);
                    }
                    if (astring[0].equals("ofChatBackground") && astring.length >= 2) {
                        this.L_3570_A = Integer.valueOf(astring[1]);
                    }
                    if (astring[0].equals("ofChatShadow") && astring.length >= 2) {
                        this.Y_776_s = Boolean.valueOf(astring[1]);
                    }
                    if (!astring[0].equals("key_" + this.A_3244_K.v_4262_N())) continue;
                    this.A_3244_K.J_1907_R(Q_4113_P.n_1700_B(astring[1]));
                }
                catch (Exception exception1) {
                    Config.dbg("Skipping bad option: " + s);
                    exception1.printStackTrace();
                }
            }
            KeyUtils.fixKeyConflicts(this.RealmsDefaultUncaughtExceptionHandler, new D_590_W[]{this.A_3244_K});
            D_590_W.R_4764_Y();
            bufferedreader.close();
        }
        catch (Exception exception11) {
            Config.warn("Failed to load options");
            exception11.printStackTrace();
        }
    }

    public void w_1484_f() {
        try {
            PrintWriter printwriter = new PrintWriter(new OutputStreamWriter((OutputStream)new FileOutputStream(this.a_178_J), StandardCharsets.UTF_8));
            printwriter.println("ofFogType:" + this.C_290_v);
            printwriter.println("ofFogStart:" + this.w_728_N);
            printwriter.println("ofMipmapType:" + this.J_4256_G);
            printwriter.println("ofOcclusionFancy:" + this.RealmsLongConfirmationScreen);
            printwriter.println("ofSmoothFps:" + this.RealmsLongRunningMcoTaskScreen);
            printwriter.println("ofSmoothWorld:" + this.i_2993_w);
            printwriter.println("ofAoLevel:" + this.RealmsResetNormalWorldScreen);
            printwriter.println("ofClouds:" + this.G_424_k);
            printwriter.println("ofCloudsHeight:" + this.RealmsSettingsScreen);
            printwriter.println("ofTrees:" + this.f_1043_S);
            printwriter.println("ofDroppedItems:" + this.J_739_q);
            printwriter.println("ofRain:" + this.F_4247_a);
            printwriter.println("ofAnimatedWater:" + this.S_3139_t);
            printwriter.println("ofAnimatedLava:" + this.k_2302_P);
            printwriter.println("ofAnimatedFire:" + this.t_3452_g);
            printwriter.println("ofAnimatedPortal:" + this.V_118_c);
            printwriter.println("ofAnimatedRedstone:" + this.I_1407_m);
            printwriter.println("ofAnimatedExplosion:" + this.o_2767_H);
            printwriter.println("ofAnimatedFlame:" + this.d_2545_n);
            printwriter.println("ofAnimatedSmoke:" + this.x_92_N);
            printwriter.println("ofVoidParticles:" + this.i_601_W);
            printwriter.println("ofWaterParticles:" + this.h_2739_B);
            printwriter.println("ofPortalParticles:" + this.x_4991_F);
            printwriter.println("ofPotionParticles:" + this.Z_759_W);
            printwriter.println("ofFireworkParticles:" + this.f_1574_f);
            printwriter.println("ofDrippingWaterLava:" + this.l_1268_F);
            printwriter.println("ofAnimatedTerrain:" + this.J_303_C);
            printwriter.println("ofAnimatedTextures:" + this.o_1800_r);
            printwriter.println("ofRainSplash:" + this.X_1313_W);
            printwriter.println("ofLagometer:" + this.f_3449_S);
            printwriter.println("ofShowFps:" + this.JsonUtils);
            printwriter.println("ofAutoSaveTicks:" + this.D_4361_a);
            printwriter.println("ofBetterGrass:" + this.C_1162_e);
            printwriter.println("ofConnectedTextures:" + this.P_5000_x);
            printwriter.println("ofWeather:" + this.RealmsPersistence);
            printwriter.println("ofSky:" + this.y_2772_m);
            printwriter.println("ofStars:" + this.H_1883_T);
            printwriter.println("ofSunMoon:" + this.d_4007_L);
            printwriter.println("ofVignette:" + this.TextRenderingUtils);
            printwriter.println("ofChunkUpdates:" + this.UploadTokenCache);
            printwriter.println("ofChunkUpdatesDynamic:" + this.U_1341_G);
            printwriter.println("ofTime:" + this.ClientBootstrap);
            printwriter.println("ofAaLevel:" + this.C_3538_G);
            printwriter.println("ofAfLevel:" + this.A_3959_N);
            printwriter.println("ofProfiler:" + this.u_55_V);
            printwriter.println("ofBetterSnow:" + this.o_2341_D);
            printwriter.println("ofSwampColors:" + this.C_1269_X);
            printwriter.println("ofRandomEntities:" + this.x_612_B);
            printwriter.println("ofCustomFonts:" + this.t_1446_I);
            printwriter.println("ofCustomColors:" + this.j_306_t);
            printwriter.println("ofCustomItems:" + this.L_4248_u);
            printwriter.println("ofCustomSky:" + this.F_3572_x);
            printwriter.println("ofShowCapes:" + this.L_1362_X);
            printwriter.println("ofNaturalTextures:" + this.O_1309_Q);
            printwriter.println("ofEmissiveTextures:" + this.O_2934_T);
            printwriter.println("ofLazyChunkLoading:" + this.RealmsParentalConsentScreen);
            printwriter.println("ofRenderRegions:" + this.O_2151_c);
            printwriter.println("ofSmartAnimations:" + this.s_1671_u);
            printwriter.println("ofDynamicFov:" + this.X_4895_T);
            printwriter.println("ofAlternateBlocks:" + this.L_103_L);
            printwriter.println("ofDynamicLights:" + this.n_3197_X);
            printwriter.println("ofScreenshotSize:" + this.F_518_D);
            printwriter.println("ofCustomEntityModels:" + this.P_2947_S);
            printwriter.println("ofCustomGuis:" + this.O_4761_U);
            printwriter.println("ofShowGlErrors:" + this.w_2705_t);
            printwriter.println("ofFastMath:" + this.l_4088_R);
            printwriter.println("ofFastRender:" + this.Z_735_d);
            printwriter.println("ofTranslucentBlocks:" + this.P_925_e);
            printwriter.println("ofChatBackground:" + this.L_3570_A);
            printwriter.println("ofChatShadow:" + this.Y_776_s);
            printwriter.println("key_" + this.A_3244_K.v_4262_N() + ":" + this.A_3244_K.P_4830_p());
            printwriter.close();
        }
        catch (Exception exception1) {
            Config.warn("Failed to save options");
            exception1.printStackTrace();
        }
    }

    public void t_148_a() {
        P_4249_L framebuffer;
        z_883_p worldrenderer;
        switch (this.G_424_k) {
            case 1: {
                this.P_1922_E = K_2069_m.J_1907_R;
                break;
            }
            case 2: {
                this.P_1922_E = K_2069_m.R_4764_Y;
                break;
            }
            case 3: {
                this.P_1922_E = K_2069_m.n_1700_B;
                break;
            }
            default: {
                this.P_1922_E = this.u_1723_Y != P_3084_J.n_1700_B ? K_2069_m.R_4764_Y : K_2069_m.J_1907_R;
            }
        }
        if (this.u_1723_Y == P_3084_J.R_4764_Y && (worldrenderer = MinecraftClient.A_4115_X().u_1723_Y) != null && (framebuffer = worldrenderer.z_1737_N()) != null) {
            framebuffer.R_4764_Y(MinecraftClient.n_1700_B);
        }
    }

    public void s_956_w() {
        this.J_1907_R = 8;
        this.R_4764_Y = 1.0f;
        this.T_3594_S = true;
        this.G_564_y = (int)M_2935_g.FRAMERATE_LIMIT.getMaxValue();
        this.q_4610_l = false;
        this.u_2550_I();
        this.c_3005_b = 4;
        this.u_1723_Y = P_3084_J.J_1907_R;
        this.v_4262_N = G_463_a.R_4764_Y;
        this.P_1922_E = K_2069_m.R_4764_Y;
        this.R_3077_Z = 70.0;
        this.c_132_F = 0.0;
        this.g_4106_L = 0;
        this.RealmsClientOutdatedScreen = j_4067_x.n_1700_B;
        this.Y_259_p = true;
        this.g_221_o = false;
        this.C_290_v = 1;
        this.w_728_N = 0.8f;
        this.J_4256_G = 0;
        this.RealmsLongConfirmationScreen = false;
        this.s_1671_u = false;
        this.RealmsLongRunningMcoTaskScreen = false;
        Config.updateAvailableProcessors();
        this.i_2993_w = Config.isSingleProcessor();
        this.RealmsParentalConsentScreen = false;
        this.O_2151_c = false;
        this.l_4088_R = false;
        this.Z_735_d = false;
        this.P_925_e = 0;
        this.X_4895_T = true;
        this.L_103_L = true;
        this.n_3197_X = 3;
        this.F_518_D = 1;
        this.P_2947_S = true;
        this.O_4761_U = true;
        this.w_2705_t = true;
        this.L_3570_A = 0;
        this.Y_776_s = true;
        this.RealmsResetNormalWorldScreen = 1.0;
        this.C_3538_G = 0;
        this.A_3959_N = 1;
        this.G_424_k = 0;
        this.RealmsSettingsScreen = 0.0;
        this.f_1043_S = 0;
        this.F_4247_a = 0;
        this.C_1162_e = 3;
        this.D_4361_a = 4000;
        this.f_3449_S = false;
        this.JsonUtils = false;
        this.u_55_V = false;
        this.RealmsPersistence = true;
        this.y_2772_m = true;
        this.H_1883_T = true;
        this.d_4007_L = true;
        this.TextRenderingUtils = 0;
        this.UploadTokenCache = 1;
        this.U_1341_G = false;
        this.ClientBootstrap = 0;
        this.o_2341_D = false;
        this.C_1269_X = true;
        this.x_612_B = true;
        this.x_607_J = 2;
        this.t_1446_I = true;
        this.j_306_t = true;
        this.L_4248_u = true;
        this.F_3572_x = true;
        this.L_1362_X = true;
        this.P_5000_x = 2;
        this.O_1309_Q = false;
        this.O_2934_T = true;
        this.S_3139_t = 0;
        this.k_2302_P = 0;
        this.t_3452_g = true;
        this.V_118_c = true;
        this.I_1407_m = true;
        this.o_2767_H = true;
        this.d_2545_n = true;
        this.x_92_N = true;
        this.i_601_W = true;
        this.h_2739_B = true;
        this.X_1313_W = true;
        this.x_4991_F = true;
        this.Z_759_W = true;
        this.f_1574_f = true;
        this.l_1268_F = true;
        this.J_303_C = true;
        this.o_1800_r = true;
        Shaders.setShaderPack("OFF");
        Shaders.configAntialiasingLevel = 0;
        Shaders.uninit();
        Shaders.storeConfig();
        this.y_1700_S.Y_1740_V();
        this.J_1907_R();
    }

    public void u_2550_I() {
        if (this.y_1700_S.RealmsServerPing() != null) {
            this.y_1700_S.RealmsServerPing().J_1907_R(this.q_4610_l);
        }
    }

    public void M_588_G() {
        this.y_1700_S.J_1907_R(this.c_3005_b);
        this.y_1700_S.Y_1740_V();
    }

    public void n_1700_B(boolean p_setAllAnimations_1_) {
        int i;
        this.S_3139_t = i = p_setAllAnimations_1_ ? 0 : 2;
        this.k_2302_P = i;
        this.t_3452_g = p_setAllAnimations_1_;
        this.V_118_c = p_setAllAnimations_1_;
        this.I_1407_m = p_setAllAnimations_1_;
        this.o_2767_H = p_setAllAnimations_1_;
        this.d_2545_n = p_setAllAnimations_1_;
        this.x_92_N = p_setAllAnimations_1_;
        this.i_601_W = p_setAllAnimations_1_;
        this.h_2739_B = p_setAllAnimations_1_;
        this.X_1313_W = p_setAllAnimations_1_;
        this.x_4991_F = p_setAllAnimations_1_;
        this.Z_759_W = p_setAllAnimations_1_;
        this.f_1574_f = p_setAllAnimations_1_;
        this.RealmsClientOutdatedScreen = p_setAllAnimations_1_ ? j_4067_x.n_1700_B : j_4067_x.R_4764_Y;
        this.l_1268_F = p_setAllAnimations_1_;
        this.J_303_C = p_setAllAnimations_1_;
        this.o_1800_r = p_setAllAnimations_1_;
    }

    private static int n_1700_B(int p_nextValue_0_, int[] p_nextValue_1_) {
        int i = V_4423_d.R_4764_Y(p_nextValue_0_, p_nextValue_1_);
        if (i < 0) {
            return p_nextValue_1_[0];
        }
        if (++i >= p_nextValue_1_.length) {
            i = 0;
        }
        return p_nextValue_1_[i];
    }

    private static int J_1907_R(int p_limit_0_, int[] p_limit_1_) {
        int i = V_4423_d.R_4764_Y(p_limit_0_, p_limit_1_);
        return i < 0 ? p_limit_1_[0] : p_limit_0_;
    }

    private static int R_4764_Y(int p_indexOf_0_, int[] p_indexOf_1_) {
        for (int i = 0; i < p_indexOf_1_.length; ++i) {
            if (p_indexOf_1_[i] != p_indexOf_0_) continue;
            return i;
        }
        return -1;
    }

    private static String n_1700_B(String[] p_getTranslation_0_, int p_getTranslation_1_) {
        if (p_getTranslation_1_ < 0 || p_getTranslation_1_ >= p_getTranslation_0_.length) {
            p_getTranslation_1_ = 0;
        }
        return K_1289_S.n_1700_B(p_getTranslation_0_[p_getTranslation_1_], new Object[0]);
    }

    private void h_1847_R() {
        if (Reflector.KeyConflictContext_IN_GAME.exists() && Reflector.ForgeKeyBinding_setKeyConflictContext.exists()) {
            Object object = Reflector.getFieldValue(Reflector.KeyConflictContext_IN_GAME);
            Reflector.call(this.O_508_d, Reflector.ForgeKeyBinding_setKeyConflictContext, object);
            Reflector.call(this.r_715_M, Reflector.ForgeKeyBinding_setKeyConflictContext, object);
            Reflector.call(this.A_1038_p, Reflector.ForgeKeyBinding_setKeyConflictContext, object);
            Reflector.call(this.i_1637_u, Reflector.ForgeKeyBinding_setKeyConflictContext, object);
            Reflector.call(this.Ping, Reflector.ForgeKeyBinding_setKeyConflictContext, object);
            Reflector.call(this.p_178_J, Reflector.ForgeKeyBinding_setKeyConflictContext, object);
            Reflector.call(this.RealmsClientConfig, Reflector.ForgeKeyBinding_setKeyConflictContext, object);
            Reflector.call(this.D_60_a, Reflector.ForgeKeyBinding_setKeyConflictContext, object);
            Reflector.call(this.Ops, Reflector.ForgeKeyBinding_setKeyConflictContext, object);
            Reflector.call(this.h_4320_q, Reflector.ForgeKeyBinding_setKeyConflictContext, object);
            Reflector.call(this.t_4219_U, Reflector.ForgeKeyBinding_setKeyConflictContext, object);
            Reflector.call(this.V_1225_t, Reflector.ForgeKeyBinding_setKeyConflictContext, object);
            Reflector.call(this.U_1241_n, Reflector.ForgeKeyBinding_setKeyConflictContext, object);
        }
    }

    public void n_1700_B(PackRepository resourcePackListIn) {
        LinkedHashSet set = Sets.newLinkedHashSet();
        Iterator<String> iterator = this.w_1484_f.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            D_2103_L resourcepackinfo = resourcePackListIn.n_1700_B(s);
            if (resourcepackinfo == null && !s.startsWith("file/")) {
                resourcepackinfo = resourcePackListIn.n_1700_B("file/" + s);
            }
            if (resourcepackinfo == null) {
                i_3196_G.warn("Removed resource pack {} from options because it doesn't seem to exist anymore", (Object)s);
                iterator.remove();
                continue;
            }
            if (!resourcepackinfo.R_4764_Y().n_1700_B() && !this.t_148_a.contains(s)) {
                i_3196_G.warn("Removed resource pack {} from options because it is no longer compatible", (Object)s);
                iterator.remove();
                continue;
            }
            if (resourcepackinfo.R_4764_Y().n_1700_B() && this.t_148_a.contains(s)) {
                i_3196_G.info("Removed resource pack {} from incompatibility list because it's now compatible", (Object)s);
                this.t_148_a.remove(s);
                continue;
            }
            set.add(resourcepackinfo.P_1922_E());
        }
        resourcePackListIn.n_1700_B(set);
    }

    public t_1920_R P_4830_p() {
        return this.X_2960_b;
    }

    public void n_1700_B(t_1920_R pointOfView) {
        this.X_2960_b = pointOfView;
    }
}



