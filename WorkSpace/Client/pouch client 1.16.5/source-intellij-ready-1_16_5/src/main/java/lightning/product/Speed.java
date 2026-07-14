/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import lightning.product.D_686_b;
import lightning.product.I_4817_s;
import lightning.product.NumberSetting;
import lightning.product.MobEffects;
import lightning.product.N_3268_u;
import lightning.product.Q_2753_H;
import lightning.product.W_2770_z;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.ClientboundPlayerPositionPacket;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.k_2610_C;
import lightning.product.BooleanSetting;
import lightning.product.ModeSetting;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.Packet;
import lightning.product.u_2124_A;
import lightning.product.u_925_K;
import lightning.product.v_887_r;
import lightning.product.x_2401_v;
import lightning.product.ModuleCategory;

public class Speed
extends Module {
    public ModeSetting rezhimMode = new ModeSetting("\u0420\u0435\u0436\u0438\u043c", "Strafe", "Strafe", "StrafeStrict", "Motion", "Matrix", "GrimCollision", "MetaHvH", "HolyWorld", "Grim Timer");
    private final BooleanSetting skorostVVodeEnabled = new BooleanSetting("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0432 \u0432\u043e\u0434\u0435", false, () -> this.c_3005_b());
    private final BooleanSetting taymerEnabled = new BooleanSetting("\u0422\u0430\u0439\u043c\u0435\u0440", false, () -> this.c_3005_b());
    private final BooleanSetting obhodTaymeraEnabled = new BooleanSetting("\u041e\u0431\u0445\u043e\u0434 \u0442\u0430\u0439\u043c\u0435\u0440\u0430", true, () -> this.c_3005_b() && this.taymerEnabled.isEnabled() != false);
    private final NumberSetting porogObhodaSetting = new NumberSetting("\u041f\u043e\u0440\u043e\u0433 \u043e\u0431\u0445\u043e\u0434\u0430", 25.0f, 15.0f, 30.0f, 1.0f, () -> this.c_3005_b() && this.taymerEnabled.isEnabled() != false && this.obhodTaymeraEnabled.isEnabled() != false);
    private final NumberSetting mnozhitelTaymeraSetting = new NumberSetting("\u041c\u043d\u043e\u0436\u0438\u0442\u0435\u043b\u044c \u0442\u0430\u0439\u043c\u0435\u0440\u0430", 1.08f, 1.0f, 1.2f, 0.01f, () -> this.c_3005_b() && this.taymerEnabled.isEnabled() != false);
    private final NumberSetting skorostSetting = new NumberSetting("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", 0.15f, 0.1f, 0.5f, 0.01f, () -> this.rezhimMode.isMode("Motion"));
    private final BooleanSetting avtoPryzhokEnabled = new BooleanSetting("\u0410\u0432\u0442\u043e \u043f\u0440\u044b\u0436\u043e\u043a", true, () -> this.rezhimMode.isMode("Motion"));
    private final ModeSetting metaRezhimMode = new ModeSetting("Meta \u0440\u0435\u0436\u0438\u043c", "Default", () -> this.rezhimMode.isMode("MetaHvH"), "Default", "Custom");
    private final NumberSetting metaSkorostSetting = new NumberSetting("Meta \u0441\u043a\u043e\u0440\u043e\u0441\u0442\u044c", 0.2f, 0.2f, 1.05f, 0.01f, () -> this.rezhimMode.isMode("MetaHvH") && this.metaRezhimMode.isMode("Custom"));
    private final BooleanSetting metaBustSDamagomEnabled = new BooleanSetting("Meta \u0431\u0443\u0441\u0442 \u0441 \u0434\u0430\u043c\u0430\u0433\u043e\u043c", false, () -> this.rezhimMode.isMode("MetaHvH"));
    private final NumberSetting metaZnachenieBustaSetting = new NumberSetting("Meta \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u0435 \u0431\u0443\u0441\u0442\u0430", 0.7f, 0.1f, 5.0f, 0.1f, () -> this.rezhimMode.isMode("MetaHvH") && this.metaBustSDamagomEnabled.isEnabled() != false);
    private final NumberSetting metaDlitelnostBustaSetting = new NumberSetting("Meta \u0434\u043b\u0438\u0442\u0435\u043b\u044c\u043d\u043e\u0441\u0442\u044c \u0431\u0443\u0441\u0442\u0430", 700.0f, 100.0f, 2000.0f, 100.0f, () -> this.rezhimMode.isMode("MetaHvH") && this.metaBustSDamagomEnabled.isEnabled() != false);
    private final NumberSetting skorostBustaSetting = new NumberSetting("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0431\u0443\u0441\u0442\u0430", 8.0f, 0.1f, 8.0f, 0.1f, () -> this.rezhimMode.isMode("GrimCollision"));
    private final NumberSetting radiusSetting = new NumberSetting("\u0420\u0430\u0434\u0438\u0443\u0441", 1.0f, 0.5f, 1.5f, 0.1f, () -> this.rezhimMode.isMode("GrimCollision"));
    private final W_2770_z Q_2552_b = new W_2770_z();
    private double C_2741_M = 0.0;
    private double k_2293_S = 0.0;
    private int q_2307_F = 1;
    private int Z_875_P = 0;
    private int t_4043_B = 0;
    private int x_607_J = 0;
    private int e_4240_b = 0;
    private int n_3318_d = 0;
    private float d_2427_y = 0.0f;
    private boolean z_1737_N = false;
    private boolean v_4276_D = false;
    private boolean d_2461_k = false;
    private boolean G_624_v = false;
    private final ArrayDeque<Packet<?>> T_2506_i = new ArrayDeque();
    private static final int q_4610_l = 5;
    private static final int z_4693_k = 20;
    private static final double g_221_o = 0.36;
    private static final int e_2887_G = 8;
    private static final float B_1668_F = 1.08f;

    public Speed() {
        super("Speed", ModuleCategory.J_1907_R);
        this.addSettings(this.rezhimMode, this.skorostVVodeEnabled, this.taymerEnabled, this.obhodTaymeraEnabled, this.porogObhodaSetting, this.mnozhitelTaymeraSetting, this.skorostSetting, this.avtoPryzhokEnabled, this.metaRezhimMode, this.metaSkorostSetting, this.metaBustSDamagomEnabled, this.metaZnachenieBustaSetting, this.metaDlitelnostBustaSetting, this.skorostBustaSetting, this.radiusSetting);
    }

    @Override
    public void onEnable() {
        this.q_2307_F = 1;
        this.Z_875_P = 0;
        this.t_4043_B = 0;
        this.x_607_J = 0;
        this.e_4240_b = 0;
        this.n_3318_d = 0;
        this.z_1737_N = false;
        this.v_4276_D = false;
        this.d_2461_k = false;
        this.G_624_v = false;
        this.T_2506_i.clear();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.n_1700_B(1.0f);
        this.Z_875_P();
        this.z_1737_N = false;
        this.v_4276_D = false;
        this.d_2461_k = false;
        this.G_624_v = false;
        this.n_3318_d = 0;
        super.onDisable();
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (Speed.c_3005_b.Y_259_p == null || Speed.c_3005_b.Y_601_j == null) {
            return;
        }
        switch ((String)this.rezhimMode.getValue()) {
            case "Strafe": {
                this.P_1922_E(false);
                break;
            }
            case "StrafeStrict": {
                this.P_1922_E(true);
                break;
            }
            case "Motion": {
                this.h_1847_R();
                break;
            }
            case "Matrix": {
                this.Q_4569_t();
                break;
            }
            case "GrimCollision": {
                this.M_182_A();
                break;
            }
            case "MetaHvH": {
                this.t_1786_h();
                break;
            }
            case "HolyWorld": {
                this.multiplayerClientSuggestionProvider();
                break;
            }
            case "Grim Boost": {
                this.Y_259_p();
                break;
            }
            case "Grim Timer": {
                this.Q_2552_b();
            }
        }
    }

    private void P_1922_E(boolean strict) {
        boolean inCobweb;
        boolean inFluid = Speed.c_3005_b.Y_259_p.RowButton() || Speed.c_3005_b.Y_259_p.W_3464_O();
        boolean bl = inCobweb = Speed.c_3005_b.Y_601_j.getBlockState(Speed.c_3005_b.Y_259_p.b_2312_j()).J_1907_R() == a_3742_W.y_1700_S;
        if (Speed.c_3005_b.Y_259_p.q_2307_F() || Speed.c_3005_b.Y_259_p.k_578_l() || Speed.c_3005_b.Y_259_p.e_() || inFluid && !this.skorostVVodeEnabled.isEnabled().booleanValue() || inCobweb || Speed.c_3005_b.Y_259_p.C_415_h.J_1907_R || Speed.c_3005_b.Y_259_p.U_1241_n >= 5.0f) {
            this.q_2307_F = 1;
            this.n_1700_B(1.0f);
            return;
        }
        if (!u_925_K.n_1700_B()) {
            this.q_2307_F = 1;
            this.n_1700_B(1.0f);
            return;
        }
        double dx = Speed.c_3005_b.Y_259_p.O_3598_v() - Speed.c_3005_b.Y_259_p.r_715_M;
        double dz = Speed.c_3005_b.Y_259_p.l_2647_k() - Speed.c_3005_b.Y_259_p.i_1637_u;
        this.C_2741_M = Math.sqrt(dx * dx + dz * dz);
        double base = this.J_1907_R(0.2873);
        float fwd = Speed.c_3005_b.Y_259_p.G_564_y.moveForward;
        this.k_2293_S = base * (fwd <= 0.0f && this.d_2427_y > 0.0f ? 0.66 : 1.0);
        if (this.q_2307_F == 1 && Speed.c_3005_b.Y_259_p.k_3961_g) {
            e_2866_D cur = Speed.c_3005_b.Y_259_p.I_4348_c();
            Speed.c_3005_b.Y_259_p.h_1847_R(cur.J_1907_R, this.R_4764_Y(0.42), cur.G_564_y);
            this.k_2293_S *= 2.149;
            this.q_2307_F = 2;
        } else if (this.q_2307_F == 2) {
            this.k_2293_S = this.C_2741_M - 0.66 * (this.C_2741_M - base);
            this.q_2307_F = 3;
        } else {
            if (Speed.c_3005_b.Y_259_p.k_3961_g) {
                this.q_2307_F = 1;
            }
            this.k_2293_S = this.C_2741_M > 0.0 ? this.C_2741_M - this.C_2741_M / 159.0 : base;
        }
        this.k_2293_S = Math.max(this.k_2293_S, base);
        double ncpCap = this.J_1907_R(strict || fwd < 1.0f ? 0.465 : 0.576);
        double bypassCap = this.J_1907_R(strict || fwd < 1.0f ? 0.44 : 0.57);
        this.k_2293_S = Math.min(this.k_2293_S, (float)this.Z_875_P > ((Float)this.porogObhodaSetting.getValue()).floatValue() ? ncpCap : bypassCap);
        if (++this.Z_875_P > 50) {
            this.Z_875_P = 0;
        }
        u_925_K.n_1700_B(this.k_2293_S);
        this.d_2427_y = fwd;
        if (this.taymerEnabled.isEnabled().booleanValue()) {
            boolean canBoost = !Speed.c_3005_b.Y_259_p.q_2307_F() && !inFluid && Speed.c_3005_b.Y_259_p.U_1241_n < 5.0f;
            boolean applyTimer = canBoost && ((float)this.Z_875_P > ((Float)this.porogObhodaSetting.getValue()).floatValue() || this.obhodTaymeraEnabled.isEnabled() == false);
            this.addSettings(applyTimer ? ((Float)this.mnozhitelTaymeraSetting.getValue()).floatValue() : 1.0f);
        }
    }

    private void h_1847_R() {
        if (Speed.c_3005_b.Y_259_p.k_578_l()) {
            return;
        }
        if (!u_925_K.n_1700_B()) {
            return;
        }
        if (Speed.c_3005_b.Y_259_p.M_1641_O() && this.avtoPryzhokEnabled.isEnabled().booleanValue()) {
            Speed.c_3005_b.Y_259_p.e_837_t();
        }
        u_925_K.n_1700_B((double)((Float)this.skorostSetting.getValue()).floatValue());
    }

    private void Q_4569_t() {
        if (Speed.c_3005_b.Y_259_p.k_578_l()) {
            return;
        }
        if (Speed.c_3005_b.Y_259_p.M_1641_O() && u_925_K.n_1700_B()) {
            Speed.c_3005_b.Y_259_p.e_837_t();
        }
        e_2866_D m = Speed.c_3005_b.Y_259_p.I_4348_c();
        if (m.R_4764_Y == -0.4448259643949201) {
            Speed.c_3005_b.Y_259_p.h_1847_R(m.J_1907_R * 2.4, m.R_4764_Y, m.G_564_y * 2.4);
        }
    }

    private void M_182_A() {
        boolean canBoost;
        I_4817_s aabb = Speed.c_3005_b.Y_259_p.i_601_W().grow(((Float)this.radiusSetting.getValue()).floatValue());
        int armorStands = Speed.c_3005_b.Y_601_j.n_1700_B(D_686_b.class, aabb).size();
        int living = Speed.c_3005_b.Y_601_j.n_1700_B(r_4811_B.class, aabb).size();
        boolean bl = canBoost = armorStands > 1 || living > 1;
        if (canBoost && !Speed.c_3005_b.Y_259_p.M_1641_O()) {
            Speed.c_3005_b.Y_259_p.y_2772_m = armorStands > 1 ? ((Float)this.skorostBustaSetting.getValue()).floatValue() / (float)armorStands : ((Float)this.skorostBustaSetting.getValue()).floatValue() * 0.16f;
        }
    }

    private void t_1786_h() {
        if (Speed.c_3005_b.Y_259_p == null) {
            return;
        }
        if (Speed.c_3005_b.Y_259_p.k_578_l()) {
            return;
        }
        Z_1993_T offHandItem = Speed.c_3005_b.Y_259_p.S_4035_N();
        k_2610_C speedEffect = Speed.c_3005_b.Y_259_p.R_4764_Y(MobEffects.n_1700_B);
        k_2610_C slownessEffect = Speed.c_3005_b.Y_259_p.R_4764_Y(MobEffects.J_1907_R);
        String itemName = offHandItem.multiplayerClientSuggestionProvider().getString();
        float speedToApply = 0.0f;
        if (this.metaRezhimMode.isMode("Default")) {
            int amp;
            speedToApply = speedEffect != null ? ((amp = speedEffect.R_4764_Y()) == 2 ? (this.R_4764_Y(itemName) ? 0.49665f : 0.41598004f) : (amp == 1 ? (this.R_4764_Y(itemName) ? 0.43f : 0.36f) : (this.R_4764_Y(itemName) ? 0.2924f : 0.24480002f))) : (this.R_4764_Y(itemName) ? 0.2924f : 0.24480002f);
        } else if (this.metaRezhimMode.isMode("Custom")) {
            speedToApply = ((Float)this.metaSkorostSetting.getValue()).floatValue();
        }
        if (slownessEffect != null) {
            speedToApply *= 0.835f;
        }
        if (!Speed.c_3005_b.Y_259_p.M_1641_O()) {
            speedToApply *= 1.435f;
        }
        if (this.metaBustSDamagomEnabled.isEnabled().booleanValue()) {
            this.Q_2552_b.n_1700_B(((Float)this.metaDlitelnostBustaSetting.getValue()).longValue());
            if (this.Q_2552_b.R_4764_Y()) {
                speedToApply += ((Float)this.metaZnachenieBustaSetting.getValue()).floatValue() / 10.0f;
            }
        }
        if (this.H_2857_Y()) {
            speedToApply /= 1.3f;
        }
        u_925_K.n_1700_B((double)speedToApply);
    }

    private void multiplayerClientSuggestionProvider() {
        if (!u_925_K.n_1700_B() || Speed.c_3005_b.Y_259_p.k_578_l() || Speed.c_3005_b.Y_259_p.e_() || Speed.c_3005_b.Y_259_p.RowButton() || Speed.c_3005_b.Y_259_p.W_3464_O()) {
            this.t_4043_B = 0;
            return;
        }
        n_1700_B ctx = this.w_1457_N();
        if (ctx.n_1700_B <= 0 || ctx.G_564_y == null) {
            this.t_4043_B = 0;
            return;
        }
        Speed.c_3005_b.Y_259_p.b_(false);
        ++this.t_4043_B;
        if (!this.Y_601_j()) {
            return;
        }
        e_2866_D self = Speed.c_3005_b.Y_259_p.s_4990_V();
        double yaw = Math.atan2(ctx.G_564_y.G_564_y - self.G_564_y, ctx.G_564_y.J_1907_R - self.J_1907_R) - 1.5707963267948966;
        double baseBoost = 0.05500000000000001;
        double dynamicBoost = baseBoost * (0.55 + 0.45 * ctx.J_1907_R) * (0.7 + 0.3 * ctx.R_4764_Y);
        e_2866_D motion = Speed.c_3005_b.Y_259_p.I_4348_c();
        double addX = -Math.sin(yaw) * dynamicBoost;
        double addZ = Math.cos(yaw) * dynamicBoost;
        double newX = motion.J_1907_R + addX;
        double newZ = motion.G_564_y + addZ;
        double maxHorizontal = 0.82 + 0.24 * ctx.J_1907_R + 0.1 * ctx.R_4764_Y;
        double horizontal = Math.sqrt(newX * newX + newZ * newZ);
        if (horizontal > maxHorizontal) {
            double scale = maxHorizontal / horizontal;
            newX *= scale;
            newZ *= scale;
        }
        Speed.c_3005_b.Y_259_p.h_1847_R(newX, motion.R_4764_Y, newZ);
    }

    private n_1700_B w_1457_N() {
        double baseRadius;
        double effectiveRadius = baseRadius = 0.55;
        double radiusSq = Math.max(0.01, effectiveRadius * effectiveRadius);
        e_2866_D self = Speed.c_3005_b.Y_259_p.s_4990_V();
        int nearby = 0;
        double proximityAccumulator = 0.0;
        double bestSq = Double.MAX_VALUE;
        e_2866_D bestPos = null;
        for (a_3913_L a_3913_L2 : Speed.c_3005_b.Y_601_j.multiplayerClientSuggestionProvider()) {
            if (a_3913_L2 == null || a_3913_L2 == Speed.c_3005_b.Y_259_p || !a_3913_L2.RealmsLongRunningMcoTaskScreen()) continue;
            e_2866_D trackPos = a_3913_L2.s_4990_V();
            double dx = trackPos.J_1907_R - self.J_1907_R;
            double dz = trackPos.G_564_y - self.G_564_y;
            double dSq = dx * dx + dz * dz;
            if (dSq > radiusSq) continue;
            ++nearby;
            proximityAccumulator += 1.0 - Math.min(dSq / radiusSq, 1.0);
            if (!(dSq < bestSq)) continue;
            bestSq = dSq;
            bestPos = trackPos;
        }
        double density = Math.min(1.0, (double)nearby / 4.0);
        double proximity = nearby > 0 ? Math.min(1.0, proximityAccumulator / (double)nearby) : 0.0;
        return new n_1700_B(nearby, density, proximity, bestPos);
    }

    private boolean Y_601_j() {
        int cycle = 25;
        return this.t_4043_B % cycle < 5;
    }

    private void Y_259_p() {
        if (Speed.c_3005_b.Y_259_p == null) {
            return;
        }
        this.C_2741_M();
        if (!this.q_2307_F()) {
            this.d_2461_k = false;
            this.Z_875_P();
            return;
        }
        if (this.z_1737_N) {
            this.n_1700_B(0.36);
            this.z_1737_N = false;
            this.d_2461_k = true;
            return;
        }
        if (Speed.c_3005_b.Y_259_p.M_1641_O()) {
            if (!this.k_2293_S()) {
                this.d_2461_k = false;
                this.Z_875_P();
                return;
            }
            Speed.c_3005_b.Y_259_p.e_837_t();
            this.d_2461_k = true;
            this.n_1700_B(0.36);
            return;
        }
        if (this.d_2461_k && this.x_607_J <= 8) {
            this.n_1700_B(0.29519999999999996);
        } else {
            this.d_2461_k = false;
            this.Z_875_P();
        }
    }

    private void Q_2552_b() {
        if (Speed.c_3005_b.Y_259_p == null) {
            return;
        }
        this.C_2741_M();
        if (this.k_2293_S() && !this.v_4276_D) {
            this.n_1700_B(1.08f);
        } else {
            this.n_1700_B(1.0f);
        }
        if (this.v_4276_D) {
            this.n_1700_B(1.0f);
            if (this.n_3318_d > 4) {
                this.v_4276_D = false;
                this.n_3318_d = 0;
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
        if (this.rezhimMode.isMode("MetaHvH") && this.metaBustSDamagomEnabled.isEnabled().booleanValue()) {
            this.Q_2552_b.n_1700_B(e);
        }
        if (!this.rezhimMode.isMode("Grim Boost") && !this.rezhimMode.isMode("Grim Timer")) {
            return;
        }
        Packet<?> packet = e.G_564_y();
        if (Speed.c_3005_b.Y_259_p == null || Speed.c_3005_b.Y_259_p.n_1700_B == null) {
            return;
        }
        if (e.J_1907_R() && packet instanceof ClientboundPlayerPositionPacket) {
            ClientboundPlayerPositionPacket spp = (ClientboundPlayerPositionPacket)packet;
            this.v_4276_D = true;
            this.n_3318_d = 0;
            this.z_1737_N = true;
            this.d_2461_k = true;
            this.G_624_v = true;
            this.x_607_J = 0;
            Speed.c_3005_b.Y_259_p.n_1700_B.J_1907_R(new x_2401_v(spp.v_4262_N()));
            Speed.c_3005_b.Y_259_p.n_1700_B.J_1907_R(new N_3268_u.J_1907_R(Speed.c_3005_b.Y_259_p.O_3598_v(), Speed.c_3005_b.Y_259_p.X_2960_b(), Speed.c_3005_b.Y_259_p.l_2647_k(), Speed.c_3005_b.Y_259_p.p_178_J, Speed.c_3005_b.Y_259_p.f_4016_n, Speed.c_3005_b.Y_259_p.M_1641_O()));
        }
        if (e.R_4764_Y() && this.G_624_v && packet instanceof N_3268_u) {
            this.T_2506_i.add(packet);
            e.n_1700_B(true);
        }
    }

    private void C_2741_M() {
        if (Speed.c_3005_b.Y_259_p == null) {
            return;
        }
        if (Speed.c_3005_b.Y_259_p.M_1641_O()) {
            ++this.e_4240_b;
            this.x_607_J = 0;
            if (this.v_4276_D) {
                ++this.n_3318_d;
            }
        } else {
            ++this.x_607_J;
            this.e_4240_b = 0;
            if (this.v_4276_D) {
                this.n_3318_d = 0;
            }
        }
    }

    private void n_1700_B(double speed) {
        double z;
        if (Speed.c_3005_b.Y_259_p == null || Speed.c_3005_b.Y_259_p.G_564_y == null) {
            return;
        }
        float forward = Speed.c_3005_b.Y_259_p.G_564_y.moveForward;
        float strafe = Speed.c_3005_b.Y_259_p.G_564_y.moveStrafe;
        if (forward == 0.0f && strafe == 0.0f) {
            return;
        }
        float yaw = Speed.c_3005_b.Y_259_p.p_178_J;
        if (forward != 0.0f) {
            if (strafe > 0.0f) {
                yaw += forward > 0.0f ? -45.0f : 45.0f;
            } else if (strafe < 0.0f) {
                yaw += forward > 0.0f ? 45.0f : -45.0f;
            }
            strafe = 0.0f;
            forward = forward > 0.0f ? 1.0f : -1.0f;
        }
        double rad = Math.toRadians(yaw + 90.0f);
        double sin = Math.sin(rad);
        double cos = Math.cos(rad);
        double x = (double)forward * cos + (double)strafe * sin;
        double len = Math.sqrt(x * x + (z = (double)forward * sin - (double)strafe * cos) * z);
        if (len < 1.0E-6) {
            return;
        }
        e_2866_D motion = Speed.c_3005_b.Y_259_p.I_4348_c();
        Speed.c_3005_b.Y_259_p.h_1847_R(x / len * speed, motion.R_4764_Y, z / len * speed);
    }

    private boolean k_2293_S() {
        return Speed.c_3005_b.Y_259_p != null && Speed.c_3005_b.Y_259_p.G_564_y != null && (Speed.c_3005_b.Y_259_p.G_564_y.moveForward != 0.0f || Speed.c_3005_b.Y_259_p.G_564_y.moveStrafe != 0.0f);
    }

    private boolean q_2307_F() {
        return Speed.c_3005_b.Y_259_p != null && this.k_2293_S() && !Speed.c_3005_b.Y_259_p.q_2307_F() && !Speed.c_3005_b.Y_259_p.k_578_l() && !Speed.c_3005_b.Y_259_p.e_() && !Speed.c_3005_b.Y_259_p.RowButton() && !Speed.c_3005_b.Y_259_p.W_3464_O() && !Speed.c_3005_b.Y_259_p.C_415_h.J_1907_R;
    }

    private void Z_875_P() {
        if (Speed.c_3005_b.Y_259_p == null || Speed.c_3005_b.Y_259_p.n_1700_B == null) {
            this.T_2506_i.clear();
            this.G_624_v = false;
            return;
        }
        while (!this.T_2506_i.isEmpty()) {
            Packet<?> queued = this.T_2506_i.pollFirst();
            if (queued == null) continue;
            Speed.c_3005_b.Y_259_p.n_1700_B.J_1907_R(queued);
        }
        this.G_624_v = false;
    }

    private boolean c_3005_b() {
        return this.rezhimMode.isMode("Strafe") || this.rezhimMode.isMode("StrafeStrict");
    }

    private double J_1907_R(double speed) {
        if (Speed.c_3005_b.Y_259_p.J_1907_R(MobEffects.n_1700_B)) {
            speed *= 1.0 + 0.2 * (double)(Speed.c_3005_b.Y_259_p.R_4764_Y(MobEffects.n_1700_B).R_4764_Y() + 1);
        }
        if (Speed.c_3005_b.Y_259_p.J_1907_R(MobEffects.J_1907_R)) {
            speed /= 1.0 + 0.2 * (double)(Speed.c_3005_b.Y_259_p.R_4764_Y(MobEffects.J_1907_R).R_4764_Y() + 1);
        }
        return speed;
    }

    private double R_4764_Y(double jump) {
        if (Speed.c_3005_b.Y_259_p.J_1907_R(MobEffects.w_1484_f)) {
            jump += (double)(Speed.c_3005_b.Y_259_p.R_4764_Y(MobEffects.w_1484_f).R_4764_Y() + 1) * 0.1;
        }
        return jump;
    }

    private void n_1700_B(float multiplier) {
        try {
            Field timerField = c_3005_b.getClass().getDeclaredField("timer");
            timerField.setAccessible(true);
            u_2124_A timer = (u_2124_A)timerField.get(c_3005_b);
            Field tickLen = u_2124_A.class.getDeclaredField("G_564_y");
            tickLen.setAccessible(true);
            tickLen.setFloat(timer, 50.0f / multiplier);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private boolean R_4764_Y(String itemName) {
        return itemName.contains("\u0428\u0430\u0440 \u0413\u0435\u0440\u0430\u043a\u043b\u0430 2") || itemName.contains("\u0428\u0430\u0440 CHAMPION") || itemName.contains("\u0428\u0430\u0440 GOD") || itemName.contains("\u0422\u0430\u043b\u0438\u0441\u043c\u0430\u043d \u0412\u0435\u043d\u043e\u043c\u0430") || itemName.contains("\u041a\u0423\u0411\u0418\u041a-\u0420\u0423\u0411\u0418\u041a");
    }

    private boolean H_2857_Y() {
        Z_1993_T headStack = Speed.c_3005_b.Y_259_p.J_1907_R(e_1174_E.u_1723_Y);
        return this.n_1700_B(headStack) || this.J_1907_R(headStack);
    }

    private boolean n_1700_B(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.C_3560_B || !stack.h_1847_R()) {
            return false;
        }
        String tag = stack.Q_4569_t().toString();
        return tag.contains("AttributeModifiers") && tag.split("AttributeModifiers", 2)[1].startsWith(":[{Amount:3.0d,Slot:\"head\",AttributeName:\"minecraft:generic.armor\",Operation:0,UUID:[I;427683850,761809167,-1124274585,-962634053]");
    }

    private boolean J_1907_R(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.C_3560_B || !stack.h_1847_R()) {
            return false;
        }
        String tag = stack.Q_4569_t().toString();
        return tag.contains("AttributeModifiers") && tag.split("AttributeModifiers", 2)[1].startsWith(":[{Amount:3.5d,Slot:\"head\",AttributeName:\"minecraft:generic.armor\",Operation:0,UUID:[I;-368453572,-1112977890,-1779712266,-159547550]");
    }

    @Y_1740_V
    public void n_1700_B(v_887_r e) {
        if (this.rezhimMode.isMode("MetaHvH") && this.metaBustSDamagomEnabled.isEnabled().booleanValue()) {
            this.Q_2552_b.n_1700_B(e);
        }
    }

    private static final class n_1700_B {
        private final int n_1700_B;
        private final double J_1907_R;
        private final double R_4764_Y;
        private final e_2866_D G_564_y;

        private n_1700_B(int nearbyCount, double densityFactor, double proximityFactor, e_2866_D primaryTrackPos) {
            this.n_1700_B = nearbyCount;
            this.J_1907_R = densityFactor;
            this.R_4764_Y = proximityFactor;
            this.G_564_y = primaryTrackPos;
        }
    }
}



