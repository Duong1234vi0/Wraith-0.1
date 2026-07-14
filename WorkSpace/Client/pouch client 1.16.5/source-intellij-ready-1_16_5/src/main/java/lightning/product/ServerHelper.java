/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lightning.product.A_4115_X;
import lightning.product.C_2712_Y;
import lightning.product.H_2506_c;
import lightning.product.I_1654_f;
import lightning.product.NumberSetting;
import lightning.product.M_1321_u;
import lightning.product.P_4526_H;
import lightning.product.Q_1939_l;
import lightning.product.Q_2753_H;
import lightning.product.Interface;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Y_4144_v;
import lightning.product.a_1255_F;
import lightning.product.a_178_J;
import lightning.product.GuiMove;
import lightning.product.a_408_T;
import lightning.product.b_3528_u;
import lightning.product.f_1574_f;
import lightning.product.f_360_U;
import lightning.product.ClientboundExplodePacket;
import lightning.product.h_1015_G;
import lightning.product.h_2367_h;
import lightning.product.i_4434_b;
import lightning.product.k_2603_m;
import lightning.product.n_3932_q;
import lightning.product.o_1343_U;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.p_4879_r;
import lightning.product.q_3115_L;
import lightning.product.KeyBindSetting;
import lightning.product.q_3386_W;
import lightning.product.ModeSetting;
import lightning.product.Packet;
import lightning.product.w_1672_Y;
import lightning.product.w_3483_v;
import lightning.product.x_1688_C;
import lightning.product.ModuleCategory;
import lightning.product.y_4642_Y;

public class ServerHelper
extends Module {
    private final ModeSetting serverMode = new ModeSetting("\u0421\u0435\u0440\u0432\u0435\u0440", "SpookyTime", "SpookyTime", "HolyWorld", "LonyGrief", "Reallyworld", "MetaHvH", "BravoHvH", "Sunrise", "MineBlaze", "FunTime", "Custom");
    private final BooleanSetting tolkoSHotbaraEnabled = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u0441 \u0445\u043e\u0442\u0431\u0430\u0440\u0430", false);
    private final KeyBindSetting dezorientaciyaKeyBind = new KeyBindSetting("\u0414\u0435\u0437\u043e\u0440\u0438\u0435\u043d\u0442\u0430\u0446\u0438\u044f", () -> ((String)this.serverMode.getValue()).equals("SpookyTime"));
    private final KeyBindSetting yavnayaPylKeyBind = new KeyBindSetting("\u042f\u0432\u043d\u0430\u044f \u043f\u044b\u043b\u044c", () -> ((String)this.serverMode.getValue()).equals("SpookyTime"));
    private final KeyBindSetting plastKeyBind = new KeyBindSetting("\u041f\u043b\u0430\u0441\u0442", () -> ((String)this.serverMode.getValue()).equals("SpookyTime"));
    private final KeyBindSetting trapkaSpookyKeyBind = new KeyBindSetting("\u0422\u0440\u0430\u043f\u043a\u0430 Spooky", () -> ((String)this.serverMode.getValue()).equals("SpookyTime"));
    private final KeyBindSetting ognennyySmerchKeyBind = new KeyBindSetting("\u041e\u0433\u043d\u0435\u043d\u043d\u044b\u0439 \u0441\u043c\u0435\u0440\u0447", () -> ((String)this.serverMode.getValue()).equals("SpookyTime"));
    private final KeyBindSetting snezhokZamorozkaKeyBind = new KeyBindSetting("\u0421\u043d\u0435\u0436\u043e\u043a \u0437\u0430\u043c\u043e\u0440\u043e\u0437\u043a\u0430", () -> ((String)this.serverMode.getValue()).equals("SpookyTime"));
    private final KeyBindSetting bozhyaAuraKeyBind = new KeyBindSetting("\u0411\u043e\u0436\u044c\u044f \u0430\u0443\u0440\u0430", () -> ((String)this.serverMode.getValue()).equals("SpookyTime"));
    private final KeyBindSetting obychnayaLivalkaKeyBind = new KeyBindSetting("\u041e\u0431\u044b\u0447\u043d\u0430\u044f \u043b\u0438\u0432\u0430\u043b\u043a\u0430", () -> ((String)this.serverMode.getValue()).equals("LonyGrief"));
    private final KeyBindSetting unikalnoePeryshkoKeyBind = new KeyBindSetting("\u0423\u043d\u0438\u043a\u0430\u043b\u044c\u043d\u043e\u0435 \u043f\u0435\u0440\u044b\u0448\u043a\u043e", () -> ((String)this.serverMode.getValue()).equals("LonyGrief"));
    private final KeyBindSetting livalkaSPlatformoyKeyBind = new KeyBindSetting("\u041b\u0438\u0432\u0430\u043b\u043a\u0430 \u0441 \u043f\u043b\u0430\u0442\u0444\u043e\u0440\u043c\u043e\u0439", () -> ((String)this.serverMode.getValue()).equals("LonyGrief"));
    private final KeyBindSetting unikalnayaTrapkaKeyBind = new KeyBindSetting("\u0423\u043d\u0438\u043a\u0430\u043b\u044c\u043d\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", () -> ((String)this.serverMode.getValue()).equals("LonyGrief"));
    private final BooleanSetting avtoChatIgraUravnenieEnabled = new BooleanSetting("\u0410\u0432\u0442\u043e \u0447\u0430\u0442-\u0438\u0433\u0440\u0430 (\u0443\u0440\u0430\u0432\u043d\u0435\u043d\u0438\u0435)", false, () -> ((String)this.serverMode.getValue()).equals("LonyGrief"));
    private final KeyBindSetting puzyrekOpytaKeyBind = new KeyBindSetting("\u041f\u0443\u0437\u044b\u0440\u0451\u043a \u043e\u043f\u044b\u0442\u0430", () -> ((String)this.serverMode.getValue()).equals("HolyWorld"));
    private final KeyBindSetting trapkaKeyBind = new KeyBindSetting("\u0422\u0440\u0430\u043f\u043a\u0430", () -> ((String)this.serverMode.getValue()).equals("HolyWorld"));
    private final KeyBindSetting vzryvnayaTrapkaKeyBind = new KeyBindSetting("\u0412\u0437\u0440\u044b\u0432\u043d\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", () -> ((String)this.serverMode.getValue()).equals("HolyWorld"));
    private final KeyBindSetting proschalnyyGulKeyBind = new KeyBindSetting("\u041f\u0440\u043e\u0449\u0430\u043b\u044c\u043d\u044b\u0439 \u0433\u0443\u043b", () -> ((String)this.serverMode.getValue()).equals("HolyWorld"));
    private final KeyBindSetting vzryvnayaShtuchkaKeyBind = new KeyBindSetting("\u0412\u0437\u0440\u044b\u0432\u043d\u0430\u044f \u0448\u0442\u0443\u0447\u043a\u0430", () -> ((String)this.serverMode.getValue()).equals("HolyWorld"));
    private final KeyBindSetting stanKeyBind = new KeyBindSetting("\u0421\u0442\u0430\u043d", () -> ((String)this.serverMode.getValue()).equals("HolyWorld"));
    private final KeyBindSetting komSnegaKeyBind = new KeyBindSetting("\u041a\u043e\u043c \u0441\u043d\u0435\u0433\u0430", () -> ((String)this.serverMode.getValue()).equals("HolyWorld"));
    private final KeyBindSetting ryukzakKeyBind = new KeyBindSetting("\u0420\u044e\u043a\u0437\u0430\u043a", () -> ((String)this.serverMode.getValue()).equals("HolyWorld"));
    private final BooleanSetting antiCrashEnabled = new BooleanSetting("Anti-Crash", true, () -> ((String)this.serverMode.getValue()).equals("HolyWorld"));
    private final KeyBindSetting antiPoletKeyBind = new KeyBindSetting("\u0410\u043d\u0442\u0438 \u043f\u043e\u043b\u0435\u0442", () -> ((String)this.serverMode.getValue()).equals("Reallyworld"));
    private final KeyBindSetting zeleGrinchaKeyBind = new KeyBindSetting("\u0417\u0435\u043b\u044c\u0435 \u0413\u0440\u0438\u043d\u0447\u0430", () -> ((String)this.serverMode.getValue()).equals("Reallyworld"));
    private final KeyBindSetting autoshiftShiftDlyaSharaKeyBind = new KeyBindSetting("AutoShift (\u0428\u0438\u0444\u0442 \u0434\u043b\u044f \u0448\u0430\u0440\u0430)", () -> ((String)this.serverMode.getValue()).equals("Reallyworld"));
    private final KeyBindSetting novogodniyUzhasKeyBind = new KeyBindSetting("\u041d\u043e\u0432\u043e\u0433\u043e\u0434\u043d\u0438\u0439 \u0443\u0436\u0430\u0441", () -> ((String)this.serverMode.getValue()).equals("Reallyworld"));
    private final KeyBindSetting essenciyaKromeshnikaKeyBind = new KeyBindSetting("\u042d\u0441\u0441\u0435\u043d\u0446\u0438\u044f \u043a\u0440\u043e\u043c\u0435\u0448\u043d\u0438\u043a\u0430", () -> ((String)this.serverMode.getValue()).equals("Reallyworld"));
    private final KeyBindSetting snezhokKeyBind = new KeyBindSetting("\u0421\u043d\u0435\u0436\u043e\u043a", () -> ((String)this.serverMode.getValue()).equals("Reallyworld"));
    private final KeyBindSetting lovushkaKeyBind = new KeyBindSetting("\u041b\u043e\u0432\u0443\u0448\u043a\u0430", () -> ((String)this.serverMode.getValue()).equals("Reallyworld"));
    private final BooleanSetting zakryvatMenyuEnabled = new BooleanSetting("\u0417\u0430\u043a\u0440\u044b\u0432\u0430\u0442\u044c \u043c\u0435\u043d\u044e", false, () -> ((String)this.serverMode.getValue()).equals("Reallyworld"));
    private final BooleanSetting filtrChataEnabled = new BooleanSetting("\u0424\u0438\u043b\u044c\u0442\u0440 \u0447\u0430\u0442\u0430", false, () -> ((String)this.serverMode.getValue()).equals("Reallyworld"));
    private final BooleanSetting autoFixAllEnabled = new BooleanSetting("Auto /fix all", false, () -> ((String)this.serverMode.getValue()).equals("Reallyworld"));
    private final KeyBindSetting svapGolovyKeyBind = new KeyBindSetting("\u0421\u0432\u0430\u043f \u0433\u043e\u043b\u043e\u0432\u044b", () -> ((String)this.serverMode.getValue()).equals("MetaHvH"));
    private final KeyBindSetting svapBotinokKeyBind = new KeyBindSetting("\u0421\u0432\u0430\u043f \u0431\u043e\u0442\u0438\u043d\u043e\u043a", () -> ((String)this.serverMode.getValue()).equals("MetaHvH"));
    private final KeyBindSetting avtoEcVklVyklKeyBind = new KeyBindSetting("\u0410\u0432\u0442\u043e EC (\u0432\u043a\u043b/\u0432\u044b\u043a\u043b)", () -> ((String)this.serverMode.getValue()).equals("MetaHvH"));
    private final ModeSetting vyborShlemaMode = new ModeSetting("\u0412\u044b\u0431\u043e\u0440 \u0448\u043b\u0435\u043c\u0430", "\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0439", () -> ((String)this.serverMode.getValue()).equals("MetaHvH"), "\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0439", "\u0417\u043e\u043b\u043e\u0442\u043e\u0439");
    private final NumberSetting zaderzhkaSvapaSetting = new NumberSetting("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u0441\u0432\u0430\u043f\u0430", 2.0f, 1.0f, 5.0f, 1.0f, () -> ((String)this.serverMode.getValue()).equals("MetaHvH"));
    private final BooleanSetting avtoBotinkiVodaEnabled = new BooleanSetting("\u0410\u0432\u0442\u043e \u0431\u043e\u0442\u0438\u043d\u043a\u0438 (\u0432\u043e\u0434\u0430)", false, () -> ((String)this.serverMode.getValue()).equals("MetaHvH"));
    private final NumberSetting radiusEcSetting = new NumberSetting("\u0420\u0430\u0434\u0438\u0443\u0441 EC", 3.0f, 1.0f, 5.0f, 1.0f, () -> ((String)this.serverMode.getValue()).equals("MetaHvH"));
    private final KeyBindSetting zeleKilleraKeyBind = new KeyBindSetting("\u0417\u0435\u043b\u044c\u0435 \u043a\u0438\u043b\u043b\u0435\u0440\u0430", () -> ((String)this.serverMode.getValue()).equals("BravoHvH"));
    private final KeyBindSetting zeleMedikaKeyBind = new KeyBindSetting("\u0417\u0435\u043b\u044c\u0435 \u043c\u0435\u0434\u0438\u043a\u0430", () -> ((String)this.serverMode.getValue()).equals("BravoHvH"));
    private final KeyBindSetting pozvatPomoschKeyBind = new KeyBindSetting("\u041f\u043e\u0437\u0432\u0430\u0442\u044c \u043f\u043e\u043c\u043e\u0449\u044c", () -> ((String)this.serverMode.getValue()).equals("BravoHvH"));
    private final NumberSetting porogHpDlyaAvtoSetting = new NumberSetting("\u041f\u043e\u0440\u043e\u0433 HP \u0434\u043b\u044f \u0430\u0432\u0442\u043e", 6.0f, 1.0f, 20.0f, 1.0f, () -> ((String)this.serverMode.getValue()).equals("BravoHvH"));
    private final BooleanSetting avtoMedikPriNizkomHpEnabled = new BooleanSetting("\u0410\u0432\u0442\u043e \u043c\u0435\u0434\u0438\u043a \u043f\u0440\u0438 \u043d\u0438\u0437\u043a\u043e\u043c HP", false, () -> ((String)this.serverMode.getValue()).equals("BravoHvH"));
    private final KeyBindSetting obychnayaTrapkaKeyBind = new KeyBindSetting("\u041e\u0431\u044b\u0447\u043d\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", () -> ((String)this.serverMode.getValue()).equals("Sunrise"));
    private final KeyBindSetting horoshayaTrapkaKeyBind = new KeyBindSetting("\u0425\u043e\u0440\u043e\u0448\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", () -> ((String)this.serverMode.getValue()).equals("Sunrise"));
    private final KeyBindSetting otlichnayaTrapkaKeyBind = new KeyBindSetting("\u041e\u0442\u043b\u0438\u0447\u043d\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", () -> ((String)this.serverMode.getValue()).equals("Sunrise"));
    private final KeyBindSetting epicheskayaTrapkaKeyBind = new KeyBindSetting("\u042d\u043f\u0438\u0447\u0435\u0441\u043a\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", () -> ((String)this.serverMode.getValue()).equals("Sunrise"));
    private final KeyBindSetting legendarnayaTrapkaKeyBind = new KeyBindSetting("\u041b\u0435\u0433\u0435\u043d\u0434\u0430\u0440\u043d\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", () -> ((String)this.serverMode.getValue()).equals("Sunrise"));
    private final KeyBindSetting livalkaKeyBind = new KeyBindSetting("\u041b\u0438\u0432\u0430\u043b\u043a\u0430", () -> ((String)this.serverMode.getValue()).equals("Sunrise"));
    private final KeyBindSetting amuletTeleportaciiKeyBind = new KeyBindSetting("\u0410\u043c\u0443\u043b\u0435\u0442 \u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0430\u0446\u0438\u0438", () -> ((String)this.serverMode.getValue()).equals("Sunrise"));
    private final KeyBindSetting skoplenieSvetaKeyBind = new KeyBindSetting("\u0421\u043a\u043e\u043f\u043b\u0435\u043d\u0438\u0435 \u0441\u0432\u0435\u0442\u0430", () -> ((String)this.serverMode.getValue()).equals("Sunrise"));
    private final KeyBindSetting volnaOgnyaKeyBind = new KeyBindSetting("\u0412\u043e\u043b\u043d\u0430 \u043e\u0433\u043d\u044f", () -> ((String)this.serverMode.getValue()).equals("Sunrise"));
    private final KeyBindSetting poroshokVozvrascheniyaKeyBind = new KeyBindSetting("\u041f\u043e\u0440\u043e\u0448\u043e\u043a \u0432\u043e\u0437\u0432\u0440\u0430\u0449\u0435\u043d\u0438\u044f", () -> ((String)this.serverMode.getValue()).equals("Sunrise"));
    private final KeyBindSetting poroshokDezorganizaciiKeyBind = new KeyBindSetting("\u041f\u043e\u0440\u043e\u0448\u043e\u043a \u0434\u0435\u0437\u043e\u0440\u0433\u0430\u043d\u0438\u0437\u0430\u0446\u0438\u0438", () -> ((String)this.serverMode.getValue()).equals("Sunrise"));
    private final KeyBindSetting razrushitelStoekKeyBind = new KeyBindSetting("\u0420\u0430\u0437\u0440\u0443\u0448\u0438\u0442\u0435\u043b\u044c \u0421\u0442\u043e\u0435\u043a", () -> ((String)this.serverMode.getValue()).equals("Sunrise"));
    private final KeyBindSetting fitilKeyBind = new KeyBindSetting("\u0424\u0438\u0442\u0438\u043b\u044c", () -> ((String)this.serverMode.getValue()).equals("MineBlaze"));
    private final KeyBindSetting perestavlyayuscheeZeleKeyBind = new KeyBindSetting("\u041f\u0435\u0440\u0435\u0441\u0442\u0430\u0432\u043b\u044f\u044e\u0449\u0435\u0435 \u0437\u0435\u043b\u044c\u0435", () -> ((String)this.serverMode.getValue()).equals("MineBlaze"));
    private final KeyBindSetting vzryvnoeZeleKeyBind = new KeyBindSetting("\u0412\u0437\u0440\u044b\u0432\u043d\u043e\u0435 \u0437\u0435\u043b\u044c\u0435", () -> ((String)this.serverMode.getValue()).equals("MineBlaze"));
    private final KeyBindSetting trapaKeyBind = new KeyBindSetting("\u0422\u0440\u0430\u043f\u0430", () -> ((String)this.serverMode.getValue()).equals("FunTime"));
    private final KeyBindSetting dezorentKeyBind = new KeyBindSetting("\u0414\u0435\u0437\u043e\u0440\u0435\u043d\u0442", () -> ((String)this.serverMode.getValue()).equals("FunTime"));
    private final KeyBindSetting plastKeyBind2 = new KeyBindSetting("\u041f\u043b\u0430\u0441\u0442", () -> ((String)this.serverMode.getValue()).equals("FunTime"));
    private final KeyBindSetting yavnayaPylKeyBind2 = new KeyBindSetting("\u042f\u0432\u043d\u0430\u044f \u043f\u044b\u043b\u044c", () -> ((String)this.serverMode.getValue()).equals("FunTime"));
    private final KeyBindSetting bozhyaAuraKeyBind2 = new KeyBindSetting("\u0411\u043e\u0436\u044c\u044f \u0430\u0443\u0440\u0430", () -> ((String)this.serverMode.getValue()).equals("FunTime"));
    private final KeyBindSetting ognenSmerchKeyBind = new KeyBindSetting("\u041e\u0433\u043d\u0435\u043d \u0441\u043c\u0435\u0440\u0447", () -> ((String)this.serverMode.getValue()).equals("FunTime"));
    private final KeyBindSetting snezhokKeyBind2 = new KeyBindSetting("\u0421\u043d\u0435\u0436\u043e\u043a", () -> ((String)this.serverMode.getValue()).equals("FunTime"));
    private o_1343_U M_1641_O;
    private final List<KeyBindSetting> RealmsWorldOptions = new ArrayList<KeyBindSetting>();
    public static h_2367_h v_4262_N = new h_2367_h("\u0425\u0435\u0434\u0435\u0440", true, H_2506_c.n_1700_B(30, 25, 40, 255));
    public static h_2367_h w_1484_f = new h_2367_h("\u0424\u043e\u043d", true, H_2506_c.n_1700_B(30, 25, 40, 255));
    public static h_2367_h t_148_a = new h_2367_h("\u0422\u0435\u043a\u0441\u0442", true, H_2506_c.n_1700_B(180, 140, 255, 255));
    public static h_2367_h s_956_w = new h_2367_h("\u041e\u0431\u0432\u043e\u0434\u043a\u0430", true, H_2506_c.n_1700_B(120, 80, 160, 255));
    public static h_2367_h u_2550_I = new h_2367_h("\u0421\u0432\u0435\u0447\u0435\u043d\u0438\u0435", true, H_2506_c.n_1700_B(120, 80, 160, 100));
    private final Map<String, n_3932_q> RealmsWorldResetDto = new HashMap<String, n_3932_q>();
    private int RegionPingResult = 0;
    private int H_1083_k = -1;
    private int R_3908_n = -1;
    private int ValueObject = 0;
    private int F_1410_V = 0;
    private boolean S_4022_R = false;
    private int l_4537_E = 0;
    private q_3386_W F_2624_D;
    private List<q_3386_W.n_1700_B> RealmsDefaultUncaughtExceptionHandler = List.of();
    private n_3932_q y_1700_S;
    private String u_744_e = "";
    private boolean RetryCallException;
    private int r_3651_U = -1;

    public ServerHelper() {
        super("ServerHelper", ModuleCategory.P_1922_E);
        this.RealmsWorldResetDto.put("SpookyTime", new C_2712_Y(List.of(this.dezorientaciyaKeyBind, this.yavnayaPylKeyBind, this.plastKeyBind, this.trapkaSpookyKeyBind, this.ognennyySmerchKeyBind, this.snezhokZamorozkaKeyBind, this.bozhyaAuraKeyBind)));
        f_360_U lonyGriefHelper = new f_360_U(List.of(this.obychnayaLivalkaKeyBind, this.unikalnoePeryshkoKeyBind, this.livalkaSPlatformoyKeyBind, this.unikalnayaTrapkaKeyBind), () -> this.w_1484_f() && this.avtoChatIgraUravnenieEnabled.isEnabled() != false && ((String)this.serverMode.getValue()).equals("LonyGrief"));
        this.RealmsWorldResetDto.put("LonyGrief", lonyGriefHelper);
        A_4115_X.n_1700_B(lonyGriefHelper);
        w_1672_Y holyWorldHelper = new w_1672_Y(List.of(this.puzyrekOpytaKeyBind, this.trapkaKeyBind, this.vzryvnayaTrapkaKeyBind, this.proschalnyyGulKeyBind, this.vzryvnayaShtuchkaKeyBind, this.stanKeyBind, this.komSnegaKeyBind), this.antiCrashEnabled, this.ryukzakKeyBind);
        this.RealmsWorldResetDto.put("HolyWorld", holyWorldHelper);
        I_1654_f rwHelper = new I_1654_f(List.of(this.antiPoletKeyBind, this.zeleGrinchaKeyBind, this.autoshiftShiftDlyaSharaKeyBind, this.novogodniyUzhasKeyBind, this.essenciyaKromeshnikaKeyBind, this.snezhokKeyBind, this.lovushkaKeyBind), this.zakryvatMenyuEnabled, this.filtrChataEnabled);
        this.RealmsWorldResetDto.put("Reallyworld", rwHelper);
        A_4115_X.n_1700_B(rwHelper);
        M_1321_u metaHelper = new M_1321_u(List.of(this.svapGolovyKeyBind, this.svapBotinokKeyBind, this.avtoEcVklVyklKeyBind), this.vyborShlemaMode, this.zaderzhkaSvapaSetting, this.avtoBotinkiVodaEnabled, this.radiusEcSetting);
        this.RealmsWorldResetDto.put("MetaHvH", metaHelper);
        w_3483_v bravoHelper = new w_3483_v(List.of(this.zeleKilleraKeyBind, this.zeleMedikaKeyBind, this.pozvatPomoschKeyBind), this.porogHpDlyaAvtoSetting, this.avtoMedikPriNizkomHpEnabled);
        this.RealmsWorldResetDto.put("BravoHvH", bravoHelper);
        a_1255_F sunriseHelper = new a_1255_F(List.of(this.obychnayaTrapkaKeyBind, this.horoshayaTrapkaKeyBind, this.otlichnayaTrapkaKeyBind, this.epicheskayaTrapkaKeyBind, this.legendarnayaTrapkaKeyBind, this.livalkaKeyBind, this.amuletTeleportaciiKeyBind, this.skoplenieSvetaKeyBind, this.volnaOgnyaKeyBind, this.poroshokVozvrascheniyaKeyBind, this.poroshokDezorganizaciiKeyBind, this.razrushitelStoekKeyBind));
        this.RealmsWorldResetDto.put("Sunrise", sunriseHelper);
        Y_4144_v mineBlazeHelper = new Y_4144_v(List.of(this.fitilKeyBind, this.perestavlyayuscheeZeleKeyBind, this.vzryvnoeZeleKeyBind));
        this.RealmsWorldResetDto.put("MineBlaze", mineBlazeHelper);
        p_4879_r funTimeHelper = new p_4879_r(List.of(this.trapaKeyBind, this.dezorentKeyBind, this.plastKeyBind2, this.yavnayaPylKeyBind2, this.bozhyaAuraKeyBind2, this.ognenSmerchKeyBind, this.snezhokKeyBind2));
        this.RealmsWorldResetDto.put("FunTime", funTimeHelper);
        this.M_1641_O = new o_1343_U();
        this.RealmsWorldResetDto.put("Custom", this.M_1641_O);
        this.addSettings(this.serverMode, this.tolkoSHotbaraEnabled, this.dezorientaciyaKeyBind, this.yavnayaPylKeyBind, this.plastKeyBind, this.trapkaSpookyKeyBind, this.ognennyySmerchKeyBind, this.snezhokZamorozkaKeyBind, this.bozhyaAuraKeyBind, this.obychnayaLivalkaKeyBind, this.unikalnoePeryshkoKeyBind, this.livalkaSPlatformoyKeyBind, this.unikalnayaTrapkaKeyBind, this.avtoChatIgraUravnenieEnabled, this.puzyrekOpytaKeyBind, this.trapkaKeyBind, this.vzryvnayaTrapkaKeyBind, this.proschalnyyGulKeyBind, this.vzryvnayaShtuchkaKeyBind, this.stanKeyBind, this.komSnegaKeyBind, this.ryukzakKeyBind, this.antiCrashEnabled, this.antiPoletKeyBind, this.zeleGrinchaKeyBind, this.autoshiftShiftDlyaSharaKeyBind, this.novogodniyUzhasKeyBind, this.essenciyaKromeshnikaKeyBind, this.snezhokKeyBind, this.lovushkaKeyBind, this.zakryvatMenyuEnabled, this.filtrChataEnabled, this.autoFixAllEnabled, this.svapGolovyKeyBind, this.svapBotinokKeyBind, this.avtoEcVklVyklKeyBind, this.vyborShlemaMode, this.zaderzhkaSvapaSetting, this.avtoBotinkiVodaEnabled, this.radiusEcSetting, this.zeleKilleraKeyBind, this.zeleMedikaKeyBind, this.pozvatPomoschKeyBind, this.porogHpDlyaAvtoSetting, this.avtoMedikPriNizkomHpEnabled, this.obychnayaTrapkaKeyBind, this.horoshayaTrapkaKeyBind, this.otlichnayaTrapkaKeyBind, this.epicheskayaTrapkaKeyBind, this.legendarnayaTrapkaKeyBind, this.livalkaKeyBind, this.amuletTeleportaciiKeyBind, this.skoplenieSvetaKeyBind, this.volnaOgnyaKeyBind, this.poroshokVozvrascheniyaKeyBind, this.poroshokDezorganizaciiKeyBind, this.razrushitelStoekKeyBind, this.fitilKeyBind, this.perestavlyayuscheeZeleKeyBind, this.vzryvnoeZeleKeyBind, this.trapaKeyBind, this.dezorentKeyBind, this.plastKeyBind2, this.yavnayaPylKeyBind2, this.bozhyaAuraKeyBind2, this.ognenSmerchKeyBind, this.snezhokKeyBind2, this.M_1641_O.M_588_G());
        this.h_1847_R();
    }

    public void h_1847_R() {
        this.u_2550_I().removeAll(this.RealmsWorldOptions);
        this.RealmsWorldOptions.clear();
        if (this.M_1641_O != null) {
            this.RealmsWorldOptions.addAll(this.M_1641_O.t_148_a().values());
            if (!this.RealmsWorldOptions.isEmpty()) {
                this.addSettings(this.RealmsWorldOptions.toArray(new KeyBindSetting[0]));
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(b_3528_u.J_1907_R e) {
        if (ServerHelper.c_3005_b.Y_259_p == null || ServerHelper.c_3005_b.P_4830_p.r_3651_U || y_4642_Y.R_4764_Y()) {
            return;
        }
        if (!Interface.t_148_a.J_1907_R("\u0410\u0439\u0442\u0435\u043c \u0425\u0435\u043b\u043f\u0435\u0440").booleanValue()) {
            return;
        }
        if (this.F_2624_D == null && Interface.k_2293_S != null) {
            this.F_2624_D = new q_3386_W(Interface.k_2293_S, v_4262_N, w_1484_f, t_148_a, s_956_w, u_2550_I);
        }
        if (this.F_2624_D == null) {
            return;
        }
        n_3932_q currentHelper = this.M_182_A();
        if (currentHelper == null) {
            return;
        }
        boolean onlyBarValue = this.tolkoSHotbaraEnabled.isEnabled();
        if (currentHelper instanceof C_2712_Y) {
            ((C_2712_Y)currentHelper).J_1907_R(onlyBarValue);
        } else if (currentHelper instanceof f_360_U) {
            ((f_360_U)currentHelper).J_1907_R(onlyBarValue);
        } else if (currentHelper instanceof w_1672_Y) {
            ((w_1672_Y)currentHelper).J_1907_R(onlyBarValue);
        } else if (currentHelper instanceof a_1255_F) {
            ((a_1255_F)currentHelper).J_1907_R(onlyBarValue);
        } else if (currentHelper instanceof Y_4144_v) {
            ((Y_4144_v)currentHelper).J_1907_R(onlyBarValue);
        } else if (currentHelper instanceof p_4879_r) {
            ((p_4879_r)currentHelper).J_1907_R(onlyBarValue);
        } else if (currentHelper instanceof o_1343_U) {
            ((o_1343_U)currentHelper).J_1907_R(onlyBarValue);
        }
        List<q_3386_W.n_1700_B> renderItems = this.n_1700_B(currentHelper, onlyBarValue);
        if (!renderItems.isEmpty()) {
            this.F_2624_D.n_1700_B(e.J_1907_R(), currentHelper.R_4764_Y(), renderItems);
        }
    }

    private List<q_3386_W.n_1700_B> n_1700_B(n_3932_q currentHelper, boolean onlyBarValue) {
        int tick = ServerHelper.c_3005_b.Y_259_p.RealmsWorldResetDto;
        String mode = (String)this.serverMode.getValue();
        if (currentHelper == this.y_1700_S && tick == this.r_3651_U && onlyBarValue == this.RetryCallException && mode.equals(this.u_744_e)) {
            return this.RealmsDefaultUncaughtExceptionHandler;
        }
        this.y_1700_S = currentHelper;
        this.r_3651_U = tick;
        this.RetryCallException = onlyBarValue;
        this.u_744_e = mode;
        this.RealmsDefaultUncaughtExceptionHandler = currentHelper.n_1700_B(onlyBarValue);
        return this.RealmsDefaultUncaughtExceptionHandler;
    }

    private n_3932_q M_182_A() {
        return this.RealmsWorldResetDto.get(this.serverMode.getValue());
    }

    private boolean t_1786_h() {
        GuiMove guiWalk = ClientBootstrap.Y_601_j().J_1907_R().P_4830_p;
        return guiWalk != null && guiWalk.w_1484_f() && guiWalk.v_4262_N.J_1907_R("Funtime");
    }

    private boolean multiplayerClientSuggestionProvider() {
        GuiMove guiWalk = ClientBootstrap.Y_601_j().J_1907_R().P_4830_p;
        return guiWalk != null && guiWalk.M_182_A();
    }

    public String Q_4569_t() {
        return (String)this.serverMode.getValue();
    }

    @Y_1740_V
    public void n_1700_B(i_4434_b e) {
        if (ServerHelper.c_3005_b.Y_1740_V != null) {
            return;
        }
        if (!e.J_1907_R()) {
            n_3932_q currentHelper = this.M_182_A();
            if (currentHelper == null) {
                return;
            }
            boolean onlyBarValue = this.tolkoSHotbaraEnabled.isEnabled();
            if (currentHelper instanceof C_2712_Y) {
                ((C_2712_Y)currentHelper).J_1907_R(onlyBarValue);
            } else if (currentHelper instanceof f_360_U) {
                ((f_360_U)currentHelper).J_1907_R(onlyBarValue);
            } else if (currentHelper instanceof w_1672_Y) {
                ((w_1672_Y)currentHelper).J_1907_R(onlyBarValue);
            } else if (currentHelper instanceof a_1255_F) {
                ((a_1255_F)currentHelper).J_1907_R(onlyBarValue);
            } else if (currentHelper instanceof Y_4144_v) {
                ((Y_4144_v)currentHelper).J_1907_R(onlyBarValue);
            } else if (currentHelper instanceof p_4879_r) {
                ((p_4879_r)currentHelper).J_1907_R(onlyBarValue);
            } else if (currentHelper instanceof o_1343_U) {
                ((o_1343_U)currentHelper).J_1907_R(onlyBarValue);
            }
            currentHelper.n_1700_B(e.n_1700_B());
        }
    }

    @Y_1740_V
    private void n_1700_B(h_1015_G event) {
        n_3932_q helper;
        GuiMove guiWalk;
        w_1672_Y holyHelper;
        if (this.F_1410_V > 0) {
            --this.F_1410_V;
        }
        this.w_1457_N();
        n_3932_q currentHelper = this.M_182_A();
        if (currentHelper instanceof w_1672_Y && (holyHelper = (w_1672_Y)currentHelper).w_1484_f()) {
            return;
        }
        if (this.RegionPingResult == 6 && ServerHelper.c_3005_b.Y_259_p.RealmsWorldResetDto >= this.ValueObject) {
            ServerHelper.c_3005_b.w_1457_N.processRightClick(ServerHelper.c_3005_b.Y_259_p, ServerHelper.c_3005_b.Y_601_j, x_1688_C.n_1700_B);
            this.RegionPingResult = 5;
            this.ValueObject = ServerHelper.c_3005_b.Y_259_p.RealmsWorldResetDto + 2;
        }
        if (this.RegionPingResult == 5 && ServerHelper.c_3005_b.Y_259_p.RealmsWorldResetDto >= this.ValueObject) {
            ServerHelper.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.H_1083_k;
            ServerHelper.c_3005_b.w_1457_N.syncCurrentPlayItem();
            this.H_1083_k = -1;
            this.RegionPingResult = 0;
        }
        if (this.RegionPingResult == 1 && this.F_1410_V == 0) {
            this.RegionPingResult = 2;
        }
        if (this.RegionPingResult == 2 && this.R_3908_n != -1) {
            guiWalk = ClientBootstrap.Y_601_j().J_1907_R().P_4830_p;
            if (this.t_1786_h()) {
                c_3005_b.n_1700_B(new Q_1939_l(ServerHelper.c_3005_b.Y_259_p));
            }
            if (guiWalk != null && guiWalk.M_182_A()) {
                guiWalk.s_956_w = true;
            }
            ServerHelper.c_3005_b.w_1457_N.windowClick(ServerHelper.c_3005_b.Y_259_p.o_1800_r.u_1723_Y, this.R_3908_n, this.H_1083_k, a_408_T.R_4764_Y, ServerHelper.c_3005_b.Y_259_p);
            ServerHelper.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(ServerHelper.c_3005_b.Y_259_p.o_1800_r.u_1723_Y));
            if (guiWalk != null && guiWalk.M_182_A()) {
                guiWalk.s_956_w = false;
            }
            if (this.t_1786_h()) {
                c_3005_b.n_1700_B((k_2603_m)null);
            }
            this.RegionPingResult = 3;
            this.ValueObject = ServerHelper.c_3005_b.Y_259_p.RealmsWorldResetDto + 2;
        }
        if (this.RegionPingResult == 3 && ServerHelper.c_3005_b.Y_259_p.RealmsWorldResetDto >= this.ValueObject) {
            ServerHelper.c_3005_b.w_1457_N.processRightClick(ServerHelper.c_3005_b.Y_259_p, ServerHelper.c_3005_b.Y_601_j, x_1688_C.n_1700_B);
            this.RegionPingResult = 4;
            this.ValueObject = ServerHelper.c_3005_b.Y_259_p.RealmsWorldResetDto + 3;
        }
        if (this.RegionPingResult == 4 && ServerHelper.c_3005_b.Y_259_p.RealmsWorldResetDto >= this.ValueObject) {
            guiWalk = ClientBootstrap.Y_601_j().J_1907_R().P_4830_p;
            if (this.t_1786_h()) {
                c_3005_b.n_1700_B(new Q_1939_l(ServerHelper.c_3005_b.Y_259_p));
            }
            if (guiWalk != null && guiWalk.M_182_A()) {
                guiWalk.s_956_w = true;
            }
            ServerHelper.c_3005_b.w_1457_N.windowClick(ServerHelper.c_3005_b.Y_259_p.o_1800_r.u_1723_Y, this.R_3908_n, this.H_1083_k, a_408_T.R_4764_Y, ServerHelper.c_3005_b.Y_259_p);
            ServerHelper.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(ServerHelper.c_3005_b.Y_259_p.o_1800_r.u_1723_Y));
            if (guiWalk != null && guiWalk.M_182_A()) {
                guiWalk.s_956_w = false;
            }
            if (this.t_1786_h()) {
                c_3005_b.n_1700_B((k_2603_m)null);
            }
            this.RegionPingResult = 0;
            this.R_3908_n = -1;
            this.H_1083_k = -1;
            if (currentHelper != null) {
                currentHelper.u_1723_Y();
            }
        }
        if ((helper = this.M_182_A()) == null || !helper.P_1922_E()) {
            return;
        }
        int slot = -1;
        if (helper instanceof C_2712_Y) {
            slot = ((C_2712_Y)helper).u_2550_I();
        } else if (helper instanceof f_360_U) {
            slot = ((f_360_U)helper).M_588_G();
        } else if (helper instanceof w_1672_Y) {
            slot = ((w_1672_Y)helper).h_1847_R();
        } else if (helper instanceof a_1255_F) {
            slot = ((a_1255_F)helper).M_588_G();
        } else if (helper instanceof Y_4144_v) {
            slot = ((Y_4144_v)helper).w_1484_f();
        } else if (helper instanceof p_4879_r) {
            slot = ((p_4879_r)helper).t_148_a();
        } else if (helper instanceof o_1343_U) {
            slot = ((o_1343_U)helper).u_2550_I();
        }
        if (slot == -1) {
            helper.u_1723_Y();
            return;
        }
        int original = ServerHelper.c_3005_b.Y_259_p.l_1268_F.G_564_y;
        if (slot < 9) {
            ServerHelper.c_3005_b.Y_259_p.l_1268_F.G_564_y = slot;
            ServerHelper.c_3005_b.w_1457_N.syncCurrentPlayItem();
            this.H_1083_k = original;
            this.RegionPingResult = 6;
            this.ValueObject = ServerHelper.c_3005_b.Y_259_p.RealmsWorldResetDto + 2;
        } else {
            this.F_1410_V = 2;
            this.RegionPingResult = 1;
            this.R_3908_n = slot;
            this.H_1083_k = original;
        }
        if (helper instanceof C_2712_Y) {
            ((C_2712_Y)helper).w_1484_f();
        } else if (helper instanceof f_360_U) {
            ((f_360_U)helper).w_1484_f();
        } else if (helper instanceof w_1672_Y) {
            ((w_1672_Y)helper).s_956_w();
        } else if (helper instanceof a_1255_F) {
            ((a_1255_F)helper).w_1484_f();
        } else if (helper instanceof Y_4144_v) {
            ((Y_4144_v)helper).t_148_a();
        } else if (helper instanceof p_4879_r) {
            ((p_4879_r)helper).w_1484_f();
        } else if (helper instanceof o_1343_U) {
            ((o_1343_U)helper).s_956_w();
        }
    }

    private void w_1457_N() {
        if (ServerHelper.c_3005_b.Y_259_p == null) {
            return;
        }
        boolean isReallyworldMode = "Reallyworld".equals(this.serverMode.getValue());
        if (!isReallyworldMode || !this.autoFixAllEnabled.isEnabled().booleanValue()) {
            this.S_4022_R = q_3115_L.n_1700_B();
            return;
        }
        boolean inPvPNow = q_3115_L.n_1700_B();
        if (this.S_4022_R && !inPvPNow && ServerHelper.c_3005_b.Y_259_p.RealmsWorldResetDto >= this.l_4537_E) {
            ServerHelper.c_3005_b.Y_259_p.n_1700_B("/fix all");
            this.l_4537_E = ServerHelper.c_3005_b.Y_259_p.RealmsWorldResetDto + 40;
        }
        this.S_4022_R = inPvPNow;
    }

    public void J_1907_R(int slot) {
        if (slot < 9) {
            int original = ServerHelper.c_3005_b.Y_259_p.l_1268_F.G_564_y;
            ServerHelper.c_3005_b.Y_259_p.l_1268_F.G_564_y = slot;
            ServerHelper.c_3005_b.w_1457_N.syncCurrentPlayItem();
            this.H_1083_k = original;
            this.RegionPingResult = 6;
            this.ValueObject = ServerHelper.c_3005_b.Y_259_p.RealmsWorldResetDto + 2;
        } else {
            this.F_1410_V = 2;
            this.RegionPingResult = 1;
            this.R_3908_n = slot;
            this.H_1083_k = ServerHelper.c_3005_b.Y_259_p.l_1268_F.G_564_y;
        }
    }

    @Y_1740_V
    public void n_1700_B(a_178_J e) {
        if (this.F_1410_V > 0 || this.RegionPingResult >= 1 && this.RegionPingResult <= 4) {
            e.n_1700_B(0.0f);
            e.J_1907_R(0.0f);
            e.P_1922_E(false);
            e.u_1723_Y(false);
        }
    }

    @Y_1740_V
    public void n_1700_B(f_1574_f e) {
        if (this.F_1410_V > 0 || this.RegionPingResult >= 1 && this.RegionPingResult <= 6) {
            e.n_1700_B(true);
        }
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H event) {
        ClientboundExplodePacket packet;
        Packet<?> t_3138_Z2;
        BooleanSetting antiCrashSetting;
        if (ServerHelper.c_3005_b.Y_259_p == null || ServerHelper.c_3005_b.Y_601_j == null) {
            return;
        }
        if (!event.J_1907_R()) {
            return;
        }
        n_3932_q currentHelper = this.M_182_A();
        if (currentHelper instanceof w_1672_Y && (antiCrashSetting = currentHelper.v_4262_N()) != null && antiCrashSetting.t_148_a().booleanValue() && (t_3138_Z2 = event.G_564_y()) instanceof ClientboundExplodePacket && (Double.isInfinite((packet = (ClientboundExplodePacket)t_3138_Z2).P_1922_E()) || Double.isInfinite(packet.u_1723_Y()) || Double.isInfinite(packet.v_4262_N()))) {
            event.n_1700_B(true);
            return;
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.RegionPingResult = 0;
        this.H_1083_k = -1;
        this.R_3908_n = -1;
        this.ValueObject = 0;
        this.F_1410_V = 0;
        this.S_4022_R = false;
        this.l_4537_E = 0;
        this.RealmsDefaultUncaughtExceptionHandler = List.of();
        this.y_1700_S = null;
        this.r_3651_U = -1;
        n_3932_q currentHelper = this.M_182_A();
        if (currentHelper != null) {
            currentHelper.u_1723_Y();
        }
    }
}



