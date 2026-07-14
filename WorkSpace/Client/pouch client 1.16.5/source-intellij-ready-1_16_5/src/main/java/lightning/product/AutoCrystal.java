/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lightning.product.CombatRules;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.F_1241_B;
import lightning.product.F_1446_q;
import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.H_2506_c;
import lightning.product.Attributes;
import lightning.product.HitResult;
import lightning.product.I_4477_R;
import lightning.product.I_4817_s;
import lightning.product.NumberSetting;
import lightning.product.MobEffects;
import lightning.product.ClientboundAddEntityPacket;
import lightning.product.K_4096_w;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.P_3504_Q;
import lightning.product.Q_2753_H;
import lightning.product.R_2515_i;
import lightning.product.V_3354_l;
import lightning.product.V_4557_X;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.h_2367_h;
import lightning.product.l_3747_P;
import lightning.product.m_2262_U;
import lightning.product.n_4637_L;
import lightning.product.ClientBootstrap;
import lightning.product.p_1183_T;
import lightning.product.BooleanSetting;
import lightning.product.ModeSetting;
import lightning.product.Items;
import lightning.product.r_4790_y;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.SwordItem;
import lightning.product.u_530_F;
import lightning.product.x_1688_C;
import lightning.product.ModuleCategory;
import org.lwjgl.opengl.GL11;

public class AutoCrystal
extends Module {
    private static final int v_4262_N = new Color(120, 0, 0, 50).getRGB();
    private static final int w_1484_f = new Color(255, 0, 0, 150).getRGB();
    private final ModeSetting presetMode = new ModeSetting("\u041f\u0440\u0435\u0441\u0435\u0442", "Custom", "Custom", "LonyGrief");
    private final BooleanSetting attackEnabled = new BooleanSetting("Attack", true, () -> this.presetMode.isMode("Custom"));
    private final NumberSetting attackspeedSetting = new NumberSetting("AttackSpeed", 20.0f, 0.1f, 20.0f, 0.1f, () -> this.presetMode.isMode("Custom") && this.attackEnabled.isEnabled() != false);
    private final NumberSetting attackrangeSetting = new NumberSetting("AttackRange", 4.5f, 0.0f, 8.0f, 0.1f, () -> this.presetMode.isMode("Custom") && this.attackEnabled.isEnabled() != false);
    private final NumberSetting attackwallsrangeSetting = new NumberSetting("AttackWallsRange", 4.5f, 0.0f, 8.0f, 0.1f, () -> this.presetMode.isMode("Custom") && this.attackEnabled.isEnabled() != false);
    private final ModeSetting antiweaknessMode = new ModeSetting("AntiWeakness", "None", () -> this.presetMode.isMode("Custom"), "None", "Normal", "Silent");
    private final BooleanSetting instantEnabled = new BooleanSetting("Instant", true, () -> this.presetMode.isMode("Custom") && this.attackEnabled.isEnabled() != false);
    private final BooleanSetting inhibitEnabled = new BooleanSetting("Inhibit", true, () -> this.presetMode.isMode("Custom") && this.attackEnabled.isEnabled() != false);
    private final BooleanSetting placeEnabled = new BooleanSetting("Place", true, () -> this.presetMode.isMode("Custom"));
    private final NumberSetting placespeedSetting = new NumberSetting("PlaceSpeed", 20.0f, 0.1f, 20.0f, 0.1f, () -> this.presetMode.isMode("Custom") && this.placeEnabled.isEnabled() != false);
    private final NumberSetting placerangeSetting = new NumberSetting("PlaceRange", 4.5f, 0.0f, 8.0f, 0.1f, () -> this.presetMode.isMode("Custom") && this.placeEnabled.isEnabled() != false);
    private final NumberSetting placewallsrangeSetting = new NumberSetting("PlaceWallsRange", 4.5f, 0.0f, 8.0f, 0.1f, () -> this.presetMode.isMode("Custom") && this.placeEnabled.isEnabled() != false);
    private final ModeSetting placementsMode = new ModeSetting("Placements", "Native", () -> this.presetMode.isMode("Custom"), "Native", "Protocol");
    private final ModeSetting switchMode = new ModeSetting("Switch", "Silent", () -> this.presetMode.isMode("Custom"), "None", "Normal", "Silent");
    private final ModeSetting sequentialMode = new ModeSetting("Sequential", "Strong", () -> this.presetMode.isMode("Custom"), "None", "Strict", "Strong");
    private final ModeSetting rotateMode = new ModeSetting("Rotate", "Smooth", () -> this.presetMode.isMode("Custom"), "None", "Normal", "Smooth");
    private final NumberSetting rotatespeedSetting = new NumberSetting("RotateSpeed", 120.0f, 10.0f, 180.0f, 5.0f, () -> this.presetMode.isMode("Custom") && this.rotateMode.isMode("Smooth"));
    private final ModeSetting swingMode = new ModeSetting("Swing", "Mainhand", () -> this.presetMode.isMode("Custom"), "None", "Mainhand", "Offhand", "Both");
    private final BooleanSetting raytraceEnabled = new BooleanSetting("Raytrace", false, () -> this.presetMode.isMode("Custom"));
    private final NumberSetting extrapolationSetting = new NumberSetting("Extrapolation", 0.0f, 0.0f, 20.0f, 1.0f, () -> this.presetMode.isMode("Custom"));
    private final NumberSetting enemyrangeSetting = new NumberSetting("EnemyRange", 10.0f, 0.0f, 24.0f, 0.5f, () -> this.presetMode.isMode("Custom"));
    private final BooleanSetting asyncEnabled = new BooleanSetting("Async", true, () -> this.presetMode.isMode("Custom"));
    private final BooleanSetting godsyncEnabled = new BooleanSetting("GodSync", false, () -> this.presetMode.isMode("Custom"));
    private final NumberSetting predictionsSetting = new NumberSetting("Predictions", 10.0f, 1.0f, 20.0f, 1.0f, () -> this.presetMode.isMode("Custom") && this.godsyncEnabled.isEnabled() != false);
    private final NumberSetting idoffsetSetting = new NumberSetting("IDOffset", 0.0f, 0.0f, 3.0f, 1.0f, () -> this.presetMode.isMode("Custom") && this.godsyncEnabled.isEnabled() != false);
    private final ModeSetting faceplacemodeMode = new ModeSetting("FaceplaceMode", "Dynamic", () -> this.presetMode.isMode("Custom"), "None", "Dynamic", "Always");
    private final NumberSetting faceplacehpSetting = new NumberSetting("FaceplaceHP", 8.0f, 0.0f, 36.0f, 0.5f, () -> this.presetMode.isMode("Custom") && this.faceplacemodeMode.isMode("Dynamic"));
    private final NumberSetting faceplacearmorSetting = new NumberSetting("FaceplaceArmor", 25.0f, 1.0f, 100.0f, 1.0f, () -> this.presetMode.isMode("Custom") && this.faceplacemodeMode.isMode("Dynamic"));
    private final NumberSetting mindamageSetting = new NumberSetting("MinDamage", 6.0f, 0.0f, 36.0f, 0.5f, () -> this.presetMode.isMode("Custom"));
    private final NumberSetting maxselfdamageSetting = new NumberSetting("MaxSelfDamage", 10.0f, 0.0f, 36.0f, 0.5f, () -> this.presetMode.isMode("Custom"));
    private final NumberSetting lethalmultiplierSetting = new NumberSetting("LethalMultiplier", 1.5f, 0.0f, 4.0f, 0.1f, () -> this.presetMode.isMode("Custom"));
    private final BooleanSetting antisuicideEnabled = new BooleanSetting("AntiSuicide", true, () -> this.presetMode.isMode("Custom"));
    private final BooleanSetting ignoreterrainEnabled = new BooleanSetting("IgnoreTerrain", true, () -> this.presetMode.isMode("Custom"));
    private final BooleanSetting renderEnabled = new BooleanSetting("Render", true, () -> this.presetMode.isMode("Custom"));
    private final ModeSetting rendermodeMode = new ModeSetting("RenderMode", "Fade", () -> this.presetMode.isMode("Custom"), "Fade", "Shrink", "Static");
    private final NumberSetting renderdurationSetting = new NumberSetting("RenderDuration", 300.0f, 0.0f, 1000.0f, 10.0f, () -> this.presetMode.isMode("Custom") && this.renderEnabled.isEnabled() != false);
    private final h_2367_h H_1990_U = new h_2367_h("FillColor", true, new Color(255, 0, 0, 50).getRGB(), () -> this.presetMode.isMode("Custom") && this.renderEnabled.isEnabled() != false);
    private final h_2367_h N_2525_X = new h_2367_h("OutlineColor", true, new Color(255, 0, 0, 150).getRGB(), () -> this.presetMode.isMode("Custom") && this.renderEnabled.isEnabled() != false);
    private final BooleanSetting renderdamageEnabled = new BooleanSetting("RenderDamage", true, () -> this.presetMode.isMode("Custom") && this.renderEnabled.isEnabled() != false);
    private final BooleanSetting ignorefriendsEnabled = new BooleanSetting("IgnoreFriends", true, () -> this.presetMode.isMode("Custom"));
    private final BooleanSetting onlyhotbarEnabled = new BooleanSetting("OnlyHotbar", true, () -> this.presetMode.isMode("Custom"));
    private final BooleanSetting whileeatingEnabled = new BooleanSetting("WhileEating", true, () -> this.presetMode.isMode("Custom"));
    private final ExecutorService s_2632_s = Executors.newSingleThreadExecutor();
    private final Map<Integer, Long> l_1233_K = new ConcurrentHashMap<Integer, Long>();
    private final Map<c_1514_x, Long> z_1333_t = new ConcurrentHashMap<c_1514_x, Long>();
    private final Map<c_1514_x, Long> O_508_d = new ConcurrentHashMap<c_1514_x, Long>();
    private final V_4557_X r_715_M = new V_4557_X();
    private final V_4557_X A_1038_p = new V_4557_X();
    private final V_4557_X i_1637_u = new V_4557_X();
    private a_3913_L Ping = null;
    private V_3354_l p_178_J = null;
    private n_1700_B RealmsClientConfig = null;
    private c_1514_x f_4016_n = null;
    private long j_276_v = 0L;
    private float UploadStatus = 0.0f;
    private P_3504_Q e_1992_r;
    private boolean D_60_a = false;
    private boolean k_3961_g = false;
    private boolean Ops = true;
    private boolean h_4320_q = false;
    private boolean t_4219_U = false;
    private int V_1446_Y = -100000;
    private String PlayerInfo = "0.00";

    public AutoCrystal() {
        super("AutoCrystal", ModuleCategory.n_1700_B);
        this.addSettings(this.presetMode, this.attackEnabled, this.attackspeedSetting, this.attackrangeSetting, this.attackwallsrangeSetting, this.antiweaknessMode, this.instantEnabled, this.inhibitEnabled, this.placeEnabled, this.placespeedSetting, this.placerangeSetting, this.placewallsrangeSetting, this.placementsMode, this.switchMode, this.sequentialMode, this.rotateMode, this.rotatespeedSetting, this.swingMode, this.raytraceEnabled, this.extrapolationSetting, this.enemyrangeSetting, this.asyncEnabled, this.godsyncEnabled, this.predictionsSetting, this.idoffsetSetting, this.faceplacemodeMode, this.faceplacehpSetting, this.faceplacearmorSetting, this.mindamageSetting, this.maxselfdamageSetting, this.lethalmultiplierSetting, this.antisuicideEnabled, this.ignoreterrainEnabled, this.renderEnabled, this.rendermodeMode, this.renderdurationSetting, this.H_1990_U, this.N_2525_X, this.renderdamageEnabled, this.ignorefriendsEnabled, this.onlyhotbarEnabled, this.whileeatingEnabled);
    }

    private boolean Q_4569_t() {
        return this.presetMode.isMode("LonyGrief");
    }

    private boolean M_182_A() {
        return this.Q_4569_t() || this.attackEnabled.isEnabled() != false;
    }

    private float t_1786_h() {
        return this.Q_4569_t() ? 14.4f : ((Float)this.attackspeedSetting.getValue()).floatValue();
    }

    private float multiplayerClientSuggestionProvider() {
        return this.Q_4569_t() ? 4.0f : ((Float)this.attackrangeSetting.getValue()).floatValue();
    }

    private float w_1457_N() {
        return this.Q_4569_t() ? 4.5f : ((Float)this.attackwallsrangeSetting.getValue()).floatValue();
    }

    private String Y_601_j() {
        return this.Q_4569_t() ? "None" : (String)this.antiweaknessMode.getValue();
    }

    private boolean Y_259_p() {
        return this.Q_4569_t() || this.instantEnabled.isEnabled() != false;
    }

    private boolean Q_2552_b() {
        return this.Q_4569_t() || this.inhibitEnabled.isEnabled() != false;
    }

    private boolean C_2741_M() {
        return this.Q_4569_t() || this.placeEnabled.isEnabled() != false;
    }

    private float k_2293_S() {
        return this.Q_4569_t() ? 18.2f : ((Float)this.placespeedSetting.getValue()).floatValue();
    }

    private float q_2307_F() {
        return this.Q_4569_t() ? 4.5f : ((Float)this.placerangeSetting.getValue()).floatValue();
    }

    private float Z_875_P() {
        return this.Q_4569_t() ? 4.5f : ((Float)this.placewallsrangeSetting.getValue()).floatValue();
    }

    private boolean c_3005_b() {
        return !this.Q_4569_t() && this.placementsMode.isMode("Protocol");
    }

    private String H_2857_Y() {
        return this.Q_4569_t() ? "Normal" : (String)this.switchMode.getValue();
    }

    private boolean A_4115_X() {
        return !this.Q_4569_t() && this.sequentialMode.isMode("None");
    }

    private boolean Y_1740_V() {
        return this.Q_4569_t() || this.sequentialMode.isMode("Strong");
    }

    private boolean t_4043_B() {
        return !this.Q_4569_t() && this.rotateMode.isMode("Normal");
    }

    private boolean x_607_J() {
        return !this.Q_4569_t() && this.rotateMode.isMode("None");
    }

    private boolean e_4240_b() {
        return this.Q_4569_t() || this.rotateMode.isMode("Smooth");
    }

    private float n_3318_d() {
        return this.Q_4569_t() ? 180.0f : ((Float)this.rotatespeedSetting.getValue()).floatValue();
    }

    private String d_2427_y() {
        return this.Q_4569_t() ? "Mainhand" : (String)this.swingMode.getValue();
    }

    private boolean z_1737_N() {
        return this.Q_4569_t() || this.raytraceEnabled.isEnabled() != false;
    }

    private float v_4276_D() {
        return this.Q_4569_t() ? 0.0f : ((Float)this.extrapolationSetting.getValue()).floatValue();
    }

    private float d_2461_k() {
        return this.Q_4569_t() ? 10.0f : ((Float)this.enemyrangeSetting.getValue()).floatValue();
    }

    private boolean G_624_v() {
        return this.Q_4569_t() || this.asyncEnabled.isEnabled() != false;
    }

    private boolean T_2506_i() {
        return !this.Q_4569_t() && this.godsyncEnabled.isEnabled() != false;
    }

    private boolean q_4610_l() {
        return !this.Q_4569_t() && this.faceplacemodeMode.isMode("None");
    }

    private boolean z_4693_k() {
        return !this.Q_4569_t() && this.faceplacemodeMode.isMode("Always");
    }

    private boolean g_221_o() {
        return this.Q_4569_t() || this.faceplacemodeMode.isMode("Dynamic");
    }

    private float e_2887_G() {
        return this.Q_4569_t() ? 8.0f : ((Float)this.faceplacehpSetting.getValue()).floatValue();
    }

    private float B_1668_F() {
        return this.Q_4569_t() ? 25.0f : ((Float)this.faceplacearmorSetting.getValue()).floatValue();
    }

    private float g_164_R() {
        return this.Q_4569_t() ? 6.0f : ((Float)this.mindamageSetting.getValue()).floatValue();
    }

    private float X_933_l() {
        return this.Q_4569_t() ? 10.0f : ((Float)this.maxselfdamageSetting.getValue()).floatValue();
    }

    private float Z_976_R() {
        return this.Q_4569_t() ? 1.5f : ((Float)this.lethalmultiplierSetting.getValue()).floatValue();
    }

    private boolean H_1990_U() {
        return this.Q_4569_t() || this.antisuicideEnabled.isEnabled() != false;
    }

    private boolean N_2525_X() {
        return this.Q_4569_t() || this.ignoreterrainEnabled.isEnabled() != false;
    }

    private boolean c_4037_x() {
        return this.Q_4569_t() || this.renderEnabled.isEnabled() != false;
    }

    private boolean g_2268_R() {
        return !this.Q_4569_t() && this.rendermodeMode.isMode("Shrink");
    }

    private boolean T_3594_S() {
        return this.Q_4569_t() || this.rendermodeMode.isMode("Fade");
    }

    private float D_4792_h() {
        return this.Q_4569_t() ? 300.0f : ((Float)this.renderdurationSetting.getValue()).floatValue();
    }

    private int s_2632_s() {
        return this.Q_4569_t() ? v_4262_N : (Integer)this.H_1990_U.J_1907_R();
    }

    private int l_1233_K() {
        return this.Q_4569_t() ? w_1484_f : (Integer)this.N_2525_X.J_1907_R();
    }

    private boolean z_1333_t() {
        return this.Q_4569_t() || this.renderdamageEnabled.isEnabled() != false;
    }

    private boolean O_508_d() {
        return this.Q_4569_t() || this.ignorefriendsEnabled.isEnabled() != false;
    }

    private boolean r_715_M() {
        return this.Q_4569_t() || this.onlyhotbarEnabled.isEnabled() != false;
    }

    private boolean A_1038_p() {
        return this.Q_4569_t() || this.whileeatingEnabled.isEnabled() != false;
    }

    @Override
    public void onDisable() {
        this.Ping = null;
        this.p_178_J = null;
        this.RealmsClientConfig = null;
        this.f_4016_n = null;
        this.e_1992_r = null;
        this.D_60_a = false;
        this.l_1233_K.clear();
        this.z_1333_t.clear();
        this.O_508_d.clear();
        this.h_4320_q = false;
        this.t_4219_U = false;
        this.V_1446_Y = -100000;
        this.PlayerInfo = "0.00";
        r_4790_y.J_1907_R();
        super.onDisable();
    }

    @Y_1740_V
    public void n_1700_B(m_2262_U e) {
        if (this.D_60_a && this.e_1992_r != null && this.t_4043_B()) {
            e.n_1700_B(this.e_1992_r.t_148_a);
            e.J_1907_R(this.e_1992_r.s_956_w);
            AutoCrystal.c_3005_b.Y_259_p.f_3449_S = this.e_1992_r.t_148_a;
            AutoCrystal.c_3005_b.Y_259_p.C_1162_e = this.e_1992_r.t_148_a;
        }
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
        if (AutoCrystal.c_3005_b.Y_259_p == null || AutoCrystal.c_3005_b.Y_601_j == null) {
            return;
        }
        if (e.J_1907_R() && e.G_564_y() instanceof ClientboundAddEntityPacket) {
            V_3354_l crystal;
            ClientboundAddEntityPacket packet = (ClientboundAddEntityPacket)e.G_564_y();
            if (packet.J_1907_R() > this.V_1446_Y) {
                this.V_1446_Y = packet.J_1907_R();
            }
            if (!this.M_182_A() || !this.Y_259_p()) {
                return;
            }
            if (!this.r_715_M.J_1907_R((long)(1000.0f - this.t_1786_h() * 50.0f))) {
                return;
            }
            if (packet.M_588_G() != t_5_h.w_1457_N) {
                return;
            }
            c_1514_x crystalPos = new c_1514_x(packet.G_564_y(), packet.P_1922_E(), packet.u_1723_Y());
            c_1514_x placePos = crystalPos.down();
            if (this.Q_2552_b() && this.l_1233_K.containsKey(packet.J_1907_R())) {
                return;
            }
            if (!this.z_1333_t.containsKey(placePos)) {
                return;
            }
            double distance = AutoCrystal.c_3005_b.Y_259_p.u_2550_I(1.0f).u_1723_Y(new e_2866_D(packet.G_564_y(), packet.P_1922_E(), packet.u_1723_Y()));
            if (distance > (double)this.multiplayerClientSuggestionProvider()) {
                return;
            }
            if (!this.n_1700_B(new e_2866_D(packet.G_564_y(), packet.P_1922_E(), packet.u_1723_Y())) && (this.z_1737_N() || distance > (double)this.w_1457_N())) {
                return;
            }
            N_4263_v spawnedEntity = AutoCrystal.c_3005_b.Y_601_j.J_1907_R(packet.J_1907_R());
            if (spawnedEntity instanceof V_3354_l) {
                crystal = (V_3354_l)spawnedEntity;
            } else {
                crystal = new V_3354_l(AutoCrystal.c_3005_b.Y_601_j, packet.G_564_y(), packet.P_1922_E(), packet.u_1723_Y());
                crystal.G_564_y(packet.J_1907_R());
            }
            this.n_1700_B(crystal);
            this.h_4320_q = true;
            if (this.Y_1740_V()) {
                this.P_1922_E(true);
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (AutoCrystal.c_3005_b.Y_259_p == null || AutoCrystal.c_3005_b.Y_601_j == null) {
            return;
        }
        long currentTime = System.currentTimeMillis();
        long timeout = 1000L;
        this.l_1233_K.entrySet().removeIf(entry -> currentTime - (Long)entry.getValue() > timeout);
        this.z_1333_t.entrySet().removeIf(entry -> currentTime - (Long)entry.getValue() > timeout);
        this.O_508_d.entrySet().removeIf(entry -> currentTime - (Long)entry.getValue() > timeout);
        Runnable calculation = () -> {
            this.p_178_J = this.p_178_J();
            this.RealmsClientConfig = this.RealmsClientConfig();
            this.Ping = this.RealmsClientConfig != null ? this.RealmsClientConfig.J_1907_R : null;
            this.PlayerInfo = this.RealmsClientConfig != null ? new DecimalFormat("0.00").format(this.RealmsClientConfig.R_4764_Y) : "0.00";
        };
        if (this.G_624_v()) {
            this.s_2632_s.submit(calculation);
        } else {
            calculation.run();
        }
        this.i_1637_u();
        this.D_60_a = false;
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R event) {
        float duration;
        if (AutoCrystal.c_3005_b.Y_259_p == null || AutoCrystal.c_3005_b.Y_601_j == null) {
            return;
        }
        if (!this.c_4037_x()) {
            return;
        }
        c_1514_x pos = this.f_4016_n;
        if (pos == null) {
            return;
        }
        long elapsed = System.currentTimeMillis() - this.j_276_v;
        if ((float)elapsed > (duration = this.D_4792_h()) && duration > 0.0f) {
            return;
        }
        float factor = duration > 0.0f ? 1.0f - (float)elapsed / duration : 1.0f;
        I_4817_s box = new I_4817_s(pos);
        if (this.g_2268_R()) {
            double shrink = (1.0 - (double)factor) * 0.5;
            box = box.shrink(shrink);
        }
        double renderX = c_3005_b.O_508_d().renderPosX();
        double renderY = c_3005_b.O_508_d().renderPosY();
        double renderZ = c_3005_b.O_508_d().renderPosZ();
        I_4817_s renderBox = box.offset(-renderX, -renderY, -renderZ);
        lightning.product.c_4037_x.v_4276_D();
        lightning.product.c_4037_x.Y_601_j();
        lightning.product.c_4037_x.s_2632_s();
        lightning.product.c_4037_x.t_1786_h();
        lightning.product.c_4037_x.e_4240_b();
        lightning.product.c_4037_x.q_2307_F();
        int fColor = this.s_2632_s();
        int oColor = this.l_1233_K();
        float alpha = this.T_3594_S() ? factor : 1.0f;
        float fr = (float)H_2506_c.n_1700_B(fColor) / 255.0f;
        float fg = (float)H_2506_c.J_1907_R(fColor) / 255.0f;
        float fb = (float)H_2506_c.R_4764_Y(fColor) / 255.0f;
        float fa = (float)H_2506_c.G_564_y(fColor) / 255.0f * alpha;
        float or = (float)H_2506_c.n_1700_B(oColor) / 255.0f;
        float og = (float)H_2506_c.J_1907_R(oColor) / 255.0f;
        float ob = (float)H_2506_c.R_4764_Y(oColor) / 255.0f;
        float oa = (float)H_2506_c.G_564_y(oColor) / 255.0f * alpha;
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r buffer = tessellator.R_4764_Y();
        buffer.n_1700_B(7, E_688_b.Y_601_j);
        this.n_1700_B(buffer, renderBox, fr, fg, fb, fa);
        tessellator.J_1907_R();
        lightning.product.c_4037_x.G_564_y(2.0f);
        GL11.glEnable((int)2848);
        buffer.n_1700_B(1, E_688_b.Y_601_j);
        this.J_1907_R(buffer, renderBox, or, og, ob, oa);
        tessellator.J_1907_R();
        GL11.glDisable((int)2848);
        lightning.product.c_4037_x.k_2293_S();
        lightning.product.c_4037_x.x_607_J();
        lightning.product.c_4037_x.multiplayerClientSuggestionProvider();
        lightning.product.c_4037_x.Y_259_p();
        lightning.product.c_4037_x.d_2461_k();
    }

    private void i_1637_u() {
        if (this.A_4115_X()) {
            if (this.k_3961_g) {
                this.k_3961_g = false;
                this.Ops = true;
                this.Ping();
                return;
            }
            if (this.Ops) {
                this.k_3961_g = true;
                this.Ops = false;
                this.P_1922_E(false);
            }
        } else {
            if (this.M_182_A()) {
                this.Ping();
            }
            if (this.C_2741_M()) {
                this.P_1922_E(false);
            }
        }
    }

    private void Ping() {
        V_3354_l crystal;
        if (!this.A_1038_p() && AutoCrystal.c_3005_b.Y_259_p.Y_601_j()) {
            return;
        }
        V_3354_l overrideCrystal = null;
        for (N_4263_v entity : AutoCrystal.c_3005_b.Y_601_j.J_1907_R()) {
            V_3354_l crystal2;
            if (!(entity instanceof V_3354_l) || !(crystal2 = (V_3354_l)entity).RealmsLongRunningMcoTaskScreen() || this.Q_2552_b() && this.l_1233_K.containsKey(entity.j_276_v())) continue;
            double distance = AutoCrystal.c_3005_b.Y_259_p.u_2550_I(1.0f).u_1723_Y(crystal2.s_4990_V().J_1907_R(0.0, 1.0, 0.0));
            boolean canSeeCrystal = this.n_1700_B(crystal2.s_4990_V());
            if (canSeeCrystal ? distance > (double)this.multiplayerClientSuggestionProvider() : this.z_1737_N() || distance > (double)this.w_1457_N()) continue;
            overrideCrystal = crystal2;
            break;
        }
        V_3354_l v_3354_l = crystal = overrideCrystal != null ? overrideCrystal : this.p_178_J;
        if (crystal == null) {
            return;
        }
        this.n_1700_B(crystal.O_3598_v(), crystal.X_2960_b() + 1.0, crystal.l_2647_k());
        if (!this.r_715_M.J_1907_R((long)(1000.0f - this.t_1786_h() * 50.0f)) || this.h_4320_q) {
            if (this.h_4320_q) {
                this.h_4320_q = false;
            }
            return;
        }
        if (!crystal.RealmsLongRunningMcoTaskScreen()) {
            return;
        }
        if (this.Q_2552_b() && this.l_1233_K.containsKey(crystal.j_276_v())) {
            return;
        }
        double distance = AutoCrystal.c_3005_b.Y_259_p.u_2550_I(1.0f).u_1723_Y(crystal.s_4990_V().J_1907_R(0.0, 1.0, 0.0));
        boolean canSeeCrystal = this.n_1700_B(crystal.s_4990_V());
        if (canSeeCrystal) {
            if (distance > (double)this.multiplayerClientSuggestionProvider()) {
                return;
            }
        } else {
            if (this.z_1737_N()) {
                return;
            }
            if (distance > (double)this.w_1457_N()) {
                return;
            }
        }
        this.n_1700_B(crystal);
    }

    private void n_1700_B(V_3354_l crystal) {
        int slot;
        this.n_1700_B(crystal.O_3598_v(), crystal.X_2960_b() + 1.0, crystal.l_2647_k());
        int previousSlot = AutoCrystal.c_3005_b.Y_259_p.l_1268_F.G_564_y;
        boolean switched = false;
        if (!this.Y_601_j().equals("None") && AutoCrystal.c_3005_b.Y_259_p.J_1907_R(MobEffects.multiplayerClientSuggestionProvider) && (slot = this.D_60_a()) != -1) {
            this.n_1700_B(slot, previousSlot, this.Y_601_j());
            switched = true;
        }
        AutoCrystal.c_3005_b.w_1457_N.attackEntity(AutoCrystal.c_3005_b.Y_259_p, crystal);
        this.UploadStatus();
        if (switched) {
            this.J_1907_R(0, previousSlot, this.Y_601_j());
        }
        this.l_1233_K.put(crystal.j_276_v(), System.currentTimeMillis());
        this.r_715_M.n_1700_B();
    }

    private void P_1922_E(boolean sequential) {
        c_1514_x position;
        if (!this.A_1038_p() && AutoCrystal.c_3005_b.Y_259_p.Y_601_j()) {
            return;
        }
        if (this.RealmsClientConfig == null || this.RealmsClientConfig.n_1700_B == null) {
            this.f_4016_n = null;
            return;
        }
        int slot = this.e_1992_r();
        int previousSlot = AutoCrystal.c_3005_b.Y_259_p.l_1268_F.G_564_y;
        if (!this.H_2857_Y().equals("None") && slot == -1 && AutoCrystal.c_3005_b.Y_259_p.A_2714_y().J_1907_R() != Items.LoomBlock && AutoCrystal.c_3005_b.Y_259_p.S_4035_N().J_1907_R() != Items.LoomBlock) {
            return;
        }
        this.f_4016_n = position = this.RealmsClientConfig.n_1700_B;
        this.j_276_v = System.currentTimeMillis();
        this.UploadStatus = this.RealmsClientConfig.R_4764_Y;
        double distance = AutoCrystal.c_3005_b.Y_259_p.u_2550_I(1.0f).u_1723_Y(e_2866_D.n_1700_B(position));
        boolean canSeePos = this.n_1700_B(e_2866_D.n_1700_B(position));
        if (canSeePos) {
            if (distance > (double)this.q_2307_F()) {
                return;
            }
        } else {
            if (this.z_1737_N()) {
                return;
            }
            if (distance > (double)this.Z_875_P()) {
                return;
            }
        }
        if (AutoCrystal.c_3005_b.Y_601_j.getBlockState(position).J_1907_R() != a_3742_W.ClientBootstrap && AutoCrystal.c_3005_b.Y_601_j.getBlockState(position).J_1907_R() != a_3742_W.Z_875_P) {
            return;
        }
        c_1514_x up1 = position.up();
        c_1514_x up2 = position.up(2);
        if (!AutoCrystal.c_3005_b.Y_601_j.u_1723_Y(up1)) {
            return;
        }
        if (this.c_3005_b() && !AutoCrystal.c_3005_b.Y_601_j.u_1723_Y(up2)) {
            return;
        }
        I_4817_s crystalBox = new I_4817_s(up1);
        for (N_4263_v entity : AutoCrystal.c_3005_b.Y_601_j.n_1700_B((N_4263_v)null, crystalBox)) {
            if (!entity.RealmsLongRunningMcoTaskScreen() || entity instanceof n_4637_L || entity instanceof V_3354_l) continue;
            return;
        }
        this.n_1700_B((double)position.getX() + 0.5, (double)(position.getY() + 1), (double)position.getZ() + 0.5);
        if (!this.A_1038_p.J_1907_R((long)(1000.0f - this.k_2293_S() * 50.0f))) {
            return;
        }
        if (!sequential && this.t_4219_U) {
            this.t_4219_U = false;
            return;
        }
        boolean switched = false;
        if (AutoCrystal.c_3005_b.Y_259_p.A_2714_y().J_1907_R() != Items.LoomBlock && AutoCrystal.c_3005_b.Y_259_p.S_4035_N().J_1907_R() != Items.LoomBlock) {
            if (slot != -1) {
                this.n_1700_B(slot, previousSlot, this.H_2857_Y());
                switched = true;
            } else {
                return;
            }
        }
        x_1688_C hand = AutoCrystal.c_3005_b.Y_259_p.S_4035_N().J_1907_R() == Items.LoomBlock ? x_1688_C.J_1907_R : x_1688_C.n_1700_B;
        this.n_1700_B((double)position.getX() + 0.5, (double)(position.getY() + 1), (double)position.getZ() + 0.5);
        BlockHitResult result = new BlockHitResult(e_2866_D.n_1700_B(position).J_1907_R(0.0, 0.5, 0.0), b_257_Y.J_1907_R, position, false);
        AutoCrystal.c_3005_b.w_1457_N.func_217292_a(AutoCrystal.c_3005_b.Y_259_p, AutoCrystal.c_3005_b.Y_601_j, hand, result);
        this.UploadStatus();
        this.z_1333_t.put(position, System.currentTimeMillis());
        this.O_508_d.put(position, System.currentTimeMillis());
        this.A_1038_p.n_1700_B();
        if (switched) {
            this.J_1907_R(slot, previousSlot, this.H_2857_Y());
        }
        if (this.T_2506_i()) {
            for (int i = 1 - ((Float)this.idoffsetSetting.getValue()).intValue(); i < ((Float)this.predictionsSetting.getValue()).intValue(); ++i) {
                int predictedId = this.V_1446_Y + i;
                N_4263_v entity = AutoCrystal.c_3005_b.Y_601_j.J_1907_R(predictedId);
                if (!(entity instanceof V_3354_l)) continue;
                AutoCrystal.c_3005_b.w_1457_N.attackEntity(AutoCrystal.c_3005_b.Y_259_p, entity);
                this.l_1233_K.put(predictedId, System.currentTimeMillis());
            }
            AutoCrystal.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
        }
        if (sequential) {
            this.t_4219_U = true;
        }
    }

    private V_3354_l p_178_J() {
        if (!this.M_182_A()) {
            return null;
        }
        List<a_3913_L> players = this.f_4016_n();
        if (players.isEmpty()) {
            return null;
        }
        V_3354_l optimalCrystal = null;
        float optimalDamage = 0.0f;
        for (N_4263_v entity : AutoCrystal.c_3005_b.Y_601_j.J_1907_R()) {
            V_3354_l crystal;
            if (!(entity instanceof V_3354_l) || !(crystal = (V_3354_l)entity).RealmsLongRunningMcoTaskScreen() || this.Q_2552_b() && this.l_1233_K.containsKey(entity.j_276_v())) continue;
            double distance = AutoCrystal.c_3005_b.Y_259_p.u_2550_I(1.0f).u_1723_Y(crystal.s_4990_V().J_1907_R(0.0, 1.0, 0.0));
            boolean canSeeCrystal = this.n_1700_B(crystal.s_4990_V());
            if (!canSeeCrystal ? this.z_1737_N() || distance > (double)this.w_1457_N() : distance > (double)this.multiplayerClientSuggestionProvider()) continue;
            float selfDamage = this.n_1700_B(crystal.s_4990_V(), (r_4811_B)AutoCrystal.c_3005_b.Y_259_p);
            if (selfDamage > this.X_933_l() || this.H_1990_U() && selfDamage >= AutoCrystal.c_3005_b.Y_259_p.g_46_E() + AutoCrystal.c_3005_b.Y_259_p.U_3823_u()) continue;
            boolean override = false;
            for (a_3913_L player : players) {
                e_2866_D extrapolatedPos = this.J_1907_R(player);
                float damage = this.n_1700_B(crystal.s_4990_V(), player, extrapolatedPos);
                float minDmg = this.n_1700_B(player);
                float targetHealth = player.g_46_E() + player.U_3823_u();
                if (damage < minDmg && damage < targetHealth && !(damage * this.Z_976_R() >= targetHealth) || !(damage > optimalDamage) && !(damage >= targetHealth)) continue;
                optimalCrystal = crystal;
                optimalDamage = damage;
                if (!(damage >= targetHealth)) continue;
                override = true;
                break;
            }
            if (!override) continue;
            break;
        }
        return optimalCrystal;
    }

    private n_1700_B RealmsClientConfig() {
        if (!this.C_2741_M()) {
            return null;
        }
        int slot = this.e_1992_r();
        if (this.H_2857_Y().equals("None") && slot == -1 && AutoCrystal.c_3005_b.Y_259_p.A_2714_y().J_1907_R() != Items.LoomBlock && AutoCrystal.c_3005_b.Y_259_p.S_4035_N().J_1907_R() != Items.LoomBlock) {
            return null;
        }
        List<a_3913_L> players = this.f_4016_n();
        if (players.isEmpty()) {
            return null;
        }
        c_1514_x optimalPosition = null;
        r_4811_B optimalPlayer = null;
        float optimalDamage = 0.0f;
        c_1514_x playerPos = AutoCrystal.c_3005_b.Y_259_p.b_2312_j();
        int range = (int)Math.ceil(Math.max(this.q_2307_F(), this.Z_875_P()));
        for (int x = -range; x <= range; ++x) {
            for (int y = -3; y <= 1; ++y) {
                for (int z = -range; z <= range; ++z) {
                    e_2866_D crystalPos;
                    float selfDamage;
                    c_1514_x position = playerPos.add(x, y, z);
                    double distance = AutoCrystal.c_3005_b.Y_259_p.u_2550_I(1.0f).u_1723_Y(e_2866_D.n_1700_B(position));
                    boolean canSeePos = this.n_1700_B(e_2866_D.n_1700_B(position));
                    if (!canSeePos ? this.z_1737_N() || distance > (double)this.Z_875_P() : distance > (double)this.q_2307_F()) continue;
                    if (AutoCrystal.c_3005_b.Y_601_j.getBlockState(position).J_1907_R() != a_3742_W.ClientBootstrap && AutoCrystal.c_3005_b.Y_601_j.getBlockState(position).J_1907_R() != a_3742_W.Z_875_P) continue;
                    c_1514_x up1 = position.up();
                    c_1514_x up2 = position.up(2);
                    if (!AutoCrystal.c_3005_b.Y_601_j.u_1723_Y(up1) || this.c_3005_b() && !AutoCrystal.c_3005_b.Y_601_j.u_1723_Y(up2)) continue;
                    I_4817_s crystalBox = new I_4817_s(up1);
                    boolean blocked = false;
                    for (N_4263_v entity : AutoCrystal.c_3005_b.Y_601_j.n_1700_B((N_4263_v)null, crystalBox)) {
                        if (!entity.RealmsLongRunningMcoTaskScreen() || entity instanceof n_4637_L || entity instanceof V_3354_l) continue;
                        blocked = true;
                        break;
                    }
                    if (blocked || (selfDamage = this.n_1700_B(crystalPos = new e_2866_D((double)position.getX() + 0.5, position.getY() + 1, (double)position.getZ() + 0.5), (r_4811_B)AutoCrystal.c_3005_b.Y_259_p)) > this.X_933_l() || this.H_1990_U() && selfDamage >= AutoCrystal.c_3005_b.Y_259_p.g_46_E() + AutoCrystal.c_3005_b.Y_259_p.U_3823_u()) continue;
                    boolean override = false;
                    for (a_3913_L player : players) {
                        e_2866_D extrapolatedPos = this.J_1907_R(player);
                        float damage = this.n_1700_B(crystalPos, player, extrapolatedPos);
                        float minDmg = this.n_1700_B(player);
                        float targetHealth = player.g_46_E() + player.U_3823_u();
                        if (damage < minDmg && damage < targetHealth && !(damage * this.Z_976_R() >= targetHealth) || !(damage > optimalDamage) && !(damage >= targetHealth)) continue;
                        optimalPosition = position;
                        optimalPlayer = player;
                        optimalDamage = damage;
                        if (!(damage >= targetHealth)) continue;
                        override = true;
                        break;
                    }
                    if (override) break;
                }
                if (optimalDamage >= (optimalPlayer != null ? optimalPlayer.g_46_E() + ((a_3913_L)optimalPlayer).U_3823_u() : Float.MAX_VALUE)) break;
            }
            if (optimalDamage >= (optimalPlayer != null ? optimalPlayer.g_46_E() + ((a_3913_L)optimalPlayer).U_3823_u() : Float.MAX_VALUE)) break;
        }
        if (optimalPosition == null) {
            return null;
        }
        return new n_1700_B(optimalPosition, (a_3913_L)optimalPlayer, optimalDamage);
    }

    private List<a_3913_L> f_4016_n() {
        ArrayList<a_3913_L> players = new ArrayList<a_3913_L>();
        for (a_3913_L a_3913_L2 : AutoCrystal.c_3005_b.Y_601_j.multiplayerClientSuggestionProvider()) {
            if (a_3913_L2 == AutoCrystal.c_3005_b.Y_259_p || !a_3913_L2.RealmsLongRunningMcoTaskScreen() || AutoCrystal.c_3005_b.Y_259_p.R_4764_Y((N_4263_v)a_3913_L2) > this.d_2461_k() || this.O_508_d() && ClientBootstrap.Y_601_j().v_4262_N().R_4764_Y(a_3913_L2.y_4642_Y().getName())) continue;
            players.add(a_3913_L2);
        }
        return players;
    }

    private float n_1700_B(a_3913_L player) {
        float minimum = this.g_164_R();
        if (this.q_4610_l()) {
            return minimum;
        }
        if (this.z_4693_k()) {
            return Math.min(minimum, 2.0f);
        }
        if (this.g_221_o()) {
            if (player.g_46_E() + player.U_3823_u() <= this.e_2887_G()) {
                return Math.min(minimum, 2.0f);
            }
            for (Z_1993_T stack : player.u_55_V()) {
                float percentage;
                if (stack.n_1700_B() || !(stack.J_1907_R() instanceof R_2515_i) || stack.w_1484_f() == 0 || !((percentage = (float)(stack.w_1484_f() - stack.v_4262_N()) / (float)stack.w_1484_f() * 100.0f) <= this.B_1668_F())) continue;
                return Math.min(minimum, 2.0f);
            }
        }
        return minimum;
    }

    private e_2866_D J_1907_R(a_3913_L player) {
        if (this.v_4276_D() == 0.0f) {
            return null;
        }
        e_2866_D motion = player.I_4348_c();
        e_2866_D currentPos = player.s_4990_V();
        int e = (int)this.v_4276_D();
        return currentPos.J_1907_R(motion.J_1907_R * (double)e, motion.R_4764_Y * (double)e, motion.G_564_y * (double)e);
    }

    private float n_1700_B(e_2866_D crystalPos, r_4811_B entity) {
        return this.n_1700_B(crystalPos, entity, null);
    }

    private float n_1700_B(e_2866_D crystalPos, r_4811_B entity, e_2866_D extrapolatedPos) {
        if (entity == null) {
            return 0.0f;
        }
        e_2866_D entityPos = extrapolatedPos != null ? extrapolatedPos : entity.s_4990_V();
        double distance = entityPos.u_1723_Y(crystalPos);
        if (distance > 12.0) {
            return 0.0f;
        }
        double exposure = this.J_1907_R(crystalPos, entity, extrapolatedPos);
        double impact = (1.0 - distance / 12.0) * exposure;
        float damage = (float)((impact * impact + impact) / 2.0 * 7.0 * 12.0 + 1.0);
        damage *= AutoCrystal.c_3005_b.Y_601_j.x_607_J().n_1700_B() == 0 ? 0.0f : (AutoCrystal.c_3005_b.Y_601_j.x_607_J().n_1700_B() == 1 ? 0.5f : (AutoCrystal.c_3005_b.Y_601_j.x_607_J().n_1700_B() == 2 ? 1.0f : 1.5f));
        damage = this.n_1700_B(damage, entity);
        return Math.max(0.0f, damage);
    }

    private float n_1700_B(float damage, r_4811_B entity) {
        int armor = entity.E_3343_g();
        float toughness = (float)entity.J_1907_R(Attributes.s_956_w);
        damage = CombatRules.n_1700_B(damage, armor, toughness);
        int protectionLevel = K_4096_w.n_1700_B(entity.u_55_V(), P_11_z.n_1700_B((F_1241_B)null));
        if (protectionLevel > 0) {
            damage *= 1.0f - (float)protectionLevel * 0.04f;
        }
        if (entity.J_1907_R(MobEffects.u_2550_I)) {
            int resistanceLevel = entity.R_4764_Y(MobEffects.u_2550_I).R_4764_Y() + 1;
            damage *= 1.0f - (float)resistanceLevel * 0.2f;
        }
        return damage;
    }

    private double J_1907_R(e_2866_D explosionPos, r_4811_B entity, e_2866_D extrapolatedPos) {
        I_4817_s bb = extrapolatedPos != null ? entity.i_601_W().offset(extrapolatedPos.G_564_y(entity.s_4990_V())) : entity.i_601_W();
        double xWidth = 1.0 / ((bb.maxX - bb.minX) * 2.0 + 1.0);
        double yWidth = 1.0 / ((bb.maxY - bb.minY) * 2.0 + 1.0);
        double zWidth = 1.0 / ((bb.maxZ - bb.minZ) * 2.0 + 1.0);
        if (xWidth > 0.0 && yWidth > 0.0 && zWidth > 0.0) {
            int total = 0;
            int hit = 0;
            for (double x = 0.0; x <= 1.0; x += xWidth) {
                for (double y = 0.0; y <= 1.0; y += yWidth) {
                    for (double z = 0.0; z <= 1.0; z += zWidth) {
                        double posX = bb.minX + (bb.maxX - bb.minX) * x;
                        double posY = bb.minY + (bb.maxY - bb.minY) * y;
                        double posZ = bb.minZ + (bb.maxZ - bb.minZ) * z;
                        BlockHitResult result = AutoCrystal.c_3005_b.Y_601_j.n_1700_B(new ClipContext(new e_2866_D(posX, posY, posZ), explosionPos, ClipContext.n_1700_B.n_1700_B, this.N_2525_X() ? ClipContext.J_1907_R.n_1700_B : ClipContext.J_1907_R.R_4764_Y, AutoCrystal.c_3005_b.Y_259_p));
                        if (((HitResult)result).R_4764_Y() == HitResult.n_1700_B.n_1700_B) {
                            ++hit;
                        }
                        ++total;
                    }
                }
            }
            return (double)hit / (double)total;
        }
        return 0.0;
    }

    private boolean n_1700_B(e_2866_D pos) {
        e_2866_D eyePos = AutoCrystal.c_3005_b.Y_259_p.u_2550_I(1.0f);
        BlockHitResult result = AutoCrystal.c_3005_b.Y_601_j.n_1700_B(new ClipContext(eyePos, pos, ClipContext.n_1700_B.n_1700_B, ClipContext.J_1907_R.n_1700_B, AutoCrystal.c_3005_b.Y_259_p));
        return ((HitResult)result).R_4764_Y() == HitResult.n_1700_B.n_1700_B;
    }

    private void n_1700_B(double x, double y, double z) {
        if (this.x_607_J()) {
            return;
        }
        e_2866_D eyePos = AutoCrystal.c_3005_b.Y_259_p.u_2550_I(1.0f);
        double diffX = x - eyePos.J_1907_R;
        double diffY = y - eyePos.R_4764_Y;
        double diffZ = z - eyePos.G_564_y;
        float yaw = (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0);
        float pitch = u_530_F.n_1700_B((float)(-Math.toDegrees(Math.atan2(diffY, Math.hypot(diffX, diffZ)))), -90.0f, 90.0f);
        float gcd = this.j_276_v();
        if (this.e_1992_r != null) {
            yaw -= (yaw - this.e_1992_r.t_148_a) % gcd;
            pitch -= (pitch - this.e_1992_r.s_956_w) % gcd;
        }
        pitch = u_530_F.n_1700_B(pitch, -90.0f, 90.0f);
        if (this.e_4240_b()) {
            r_4790_y.n_1700_B(new F_1446_q(yaw, pitch), this.n_3318_d(), this.n_3318_d(), 0, 6);
        }
        this.e_1992_r = new P_3504_Q(yaw, pitch);
        this.D_60_a = true;
    }

    private float j_276_v() {
        float sensitivity = (float)(AutoCrystal.c_3005_b.P_4830_p.n_1700_B * (double)0.6f + (double)0.2f);
        float gcd = sensitivity * sensitivity * sensitivity * 8.0f;
        return gcd * 0.15f;
    }

    private void UploadStatus() {
        switch (this.d_2427_y()) {
            case "Mainhand": {
                AutoCrystal.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
                break;
            }
            case "Offhand": {
                AutoCrystal.c_3005_b.Y_259_p.n_1700_B(x_1688_C.J_1907_R);
                break;
            }
            case "Both": {
                AutoCrystal.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
                AutoCrystal.c_3005_b.Y_259_p.n_1700_B(x_1688_C.J_1907_R);
            }
        }
    }

    private int e_1992_r() {
        int i;
        for (i = 0; i < 9; ++i) {
            if (AutoCrystal.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).J_1907_R() != Items.LoomBlock) continue;
            return i;
        }
        if (!this.r_715_M()) {
            for (i = 9; i < 36; ++i) {
                if (AutoCrystal.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).J_1907_R() != Items.LoomBlock) continue;
                return i;
            }
        }
        return -1;
    }

    private int D_60_a() {
        for (int i = 0; i < 9; ++i) {
            if (!(AutoCrystal.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).J_1907_R() instanceof SwordItem)) continue;
            return i;
        }
        return -1;
    }

    private void n_1700_B(int slot, int previousSlot, String mode) {
        switch (mode) {
            case "Normal": {
                AutoCrystal.c_3005_b.Y_259_p.l_1268_F.G_564_y = slot;
                break;
            }
            case "Silent": {
                AutoCrystal.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(slot));
            }
        }
    }

    private void J_1907_R(int slot, int previousSlot, String mode) {
        switch (mode) {
            case "Normal": {
                break;
            }
            case "Silent": {
                AutoCrystal.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(previousSlot));
            }
        }
    }

    public a_3913_L h_1847_R() {
        return this.Ping;
    }

    private void n_1700_B(D_3318_r buffer, I_4817_s box, float r, float g, float b, float a) {
        float x0 = (float)box.minX;
        float x1 = (float)box.maxX;
        float y0 = (float)box.minY;
        float y1 = (float)box.maxY;
        float z0 = (float)box.minZ;
        float z1 = (float)box.maxZ;
        buffer.pos(x0, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z1).n_1700_B(r, g, b, a).endVertex();
    }

    private void J_1907_R(D_3318_r buffer, I_4817_s box, float r, float g, float b, float a) {
        float x0 = (float)box.minX;
        float x1 = (float)box.maxX;
        float y0 = (float)box.minY;
        float y1 = (float)box.maxY;
        float z0 = (float)box.minZ;
        float z1 = (float)box.maxZ;
        buffer.pos(x0, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z1).n_1700_B(r, g, b, a).endVertex();
    }

    private static class n_1700_B {
        c_1514_x n_1700_B;
        a_3913_L J_1907_R;
        float R_4764_Y;

        n_1700_B(c_1514_x position, a_3913_L player, float damage) {
            this.n_1700_B = position;
            this.J_1907_R = player;
            this.R_4764_Y = damage;
        }
    }
}



