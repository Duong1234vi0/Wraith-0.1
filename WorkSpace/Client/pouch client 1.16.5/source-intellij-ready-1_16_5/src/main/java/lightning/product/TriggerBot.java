/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lightning.product.FluidTags;
import lightning.product.D_686_b;
import lightning.product.AntiBot;
import lightning.product.E_3343_g;
import lightning.product.E_4612_l;
import lightning.product.Attributes;
import lightning.product.I_4817_s;
import lightning.product.NumberSetting;
import lightning.product.MobEffects;
import lightning.product.N_4263_v;
import lightning.product.MultiBooleanSetting;
import lightning.product.ServerboundContainerClickPacket;
import lightning.product.P_4526_H;
import lightning.product.Q_1187_u;
import lightning.product.T_3952_j;
import lightning.product.T_437_o;
import lightning.product.V_4557_X;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.a_1344_X;
import lightning.product.a_178_J;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.a_408_T;
import lightning.product.c_1514_x;
import lightning.product.f_2403_E;
import lightning.product.g_1734_y;
import lightning.product.h_1015_G;
import lightning.product.Sprint;
import lightning.product.ClientBootstrap;
import lightning.product.p_1183_T;
import lightning.product.BooleanSetting;
import lightning.product.Items;
import lightning.product.PacketCriticals;
import lightning.product.r_4811_B;
import lightning.product.u_1934_K;
import lightning.product.u_530_F;
import lightning.product.v_570_f;
import lightning.product.x_1688_C;
import lightning.product.ModuleCategory;
import lombok.Generated;

public class TriggerBot
extends Module {
    public r_4811_B v_4262_N = null;
    private MultiBooleanSetting targetsOptions = new MultiBooleanSetting("\u041a\u043e\u0433\u043e \u0430\u0442\u0430\u043a\u043e\u0432\u0430\u0442\u044c", new BooleanSetting("\u0418\u0433\u0440\u043e\u043a\u043e\u0432", true), new BooleanSetting("\u0414\u0440\u0443\u0437\u0435\u0439", false), new BooleanSetting("\u0413\u043e\u043b\u044b\u0445", true), new BooleanSetting("\u0416\u0438\u0442\u0435\u043b\u0435\u0439", false), new BooleanSetting("\u0416\u0438\u0432\u043e\u0442\u043d\u044b\u0445", false), new BooleanSetting("\u041c\u043e\u0431\u043e\u0432", false));
    private NumberSetting attackRangeSetting = new NumberSetting("\u0420\u0430\u0434\u0438\u0443\u0441 \u0430\u0442\u0430\u043a\u0438", 3.0f, 2.0f, 5.0f, 0.1f);
    private MultiBooleanSetting skipAttackWhenOptions = new MultiBooleanSetting("\u041d\u0435 \u0431\u0438\u0442\u044c \u0435\u0441\u043b\u0438", new BooleanSetting("\u041e\u0442\u043a\u0440\u044b\u0442 \u043a\u043e\u043d\u0442\u0435\u0439\u043d\u0435\u0440", true), new BooleanSetting("\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0435\u0448\u044c \u0435\u0434\u0443", false));
    private BooleanSetting onlyCriticalsEnabled = new BooleanSetting("\u0411\u0438\u0442\u044c \u0442\u043e\u043b\u044c\u043a\u043e \u043a\u0440\u0438\u0442\u0430\u043c\u0438", true);
    private BooleanSetting breakShieldEnabled = new BooleanSetting("\u041b\u043e\u043c\u0430\u0442\u044c \u0449\u0438\u0442", true);
    private BooleanSetting onlyWhenSpacePressedEnabled = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u043f\u0440\u0438 \u0437\u0430\u0436\u0430\u0442\u043e\u043c \u043f\u0440\u043e\u0431\u0435\u043b\u0435", false, () -> this.onlyCriticalsEnabled.isEnabled());
    private BooleanSetting noWallHitEnabled = new BooleanSetting("\u041d\u0435 \u0431\u0438\u0442\u044c \u0447\u0435\u0440\u0435\u0437 \u0441\u0442\u0435\u043d\u044b", false);
    private BooleanSetting allowDoorHitEnabled = new BooleanSetting("\u041f\u043e\u0437\u0432\u043e\u043b\u044f\u0442\u044c \u0431\u0438\u0442\u044c \u0447\u0435\u0440\u0435\u0437 \u0434\u0432\u0435\u0440\u0438", false, () -> this.noWallHitEnabled.isEnabled() == false);
    private BooleanSetting tpsSyncEnabled = new BooleanSetting("\u0421\u0438\u043d\u0445\u0440\u043e\u043d\u0438\u0437\u0430\u0446\u0438\u044f \u0441 \u0422\u041f\u0421", false);
    private V_4557_X t_1786_h = new V_4557_X();
    private int multiplayerClientSuggestionProvider;
    private boolean w_1457_N;

    public TriggerBot() {
        super("TriggerBot", ModuleCategory.n_1700_B);
        this.addSettings(this.targetsOptions, this.skipAttackWhenOptions, this.attackRangeSetting, this.noWallHitEnabled, this.allowDoorHitEnabled, this.onlyCriticalsEnabled, this.onlyWhenSpacePressedEnabled, this.tpsSyncEnabled, this.breakShieldEnabled);
    }

    @Y_1740_V
    public void n_1700_B(T_437_o e) {
        if (this.v_4262_N != null) {
            e.n_1700_B(true);
        }
    }

    @Y_1740_V
    public void n_1700_B(E_3343_g e) {
        this.v_4262_N = null;
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        this.v_4262_N = this.z_1737_N();
        this.w_1457_N = this.v_4262_N == null || !v_570_f.n_1700_B(TriggerBot.c_3005_b.Y_259_p.p_178_J, TriggerBot.c_3005_b.Y_259_p.f_4016_n, ((Float)this.attackRangeSetting.getValue()).floatValue(), this.v_4262_N, this.e_4240_b(), this.n_3318_d());
        PacketCriticals packetCriticals = (PacketCriticals)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(PacketCriticals.class);
        if (packetCriticals == null || !packetCriticals.w_1484_f() || !this.t_4043_B()) {
            this.x_607_J();
        }
    }

    @Y_1740_V
    public void n_1700_B(g_1734_y e) {
        PacketCriticals packetCriticals = (PacketCriticals)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(PacketCriticals.class);
        if (packetCriticals != null && packetCriticals.w_1484_f() && this.t_4043_B()) {
            this.x_607_J();
        }
    }

    private boolean t_4043_B() {
        return c_3005_b != null && TriggerBot.c_3005_b.Y_259_p != null && (TriggerBot.c_3005_b.Y_259_p.J_1907_R(MobEffects.H_2857_Y) || this.n_1700_B((N_4263_v)TriggerBot.c_3005_b.Y_259_p));
    }

    private boolean n_1700_B(N_4263_v entity) {
        if (entity == null || TriggerBot.c_3005_b.Y_601_j == null) {
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
                    if (TriggerBot.c_3005_b.Y_601_j.getBlockState(new c_1514_x(x, y, z)).J_1907_R() != a_3742_W.y_1700_S) continue;
                    return true;
                }
            }
        }
        return false;
    }

    @Y_1740_V
    private void n_1700_B(a_178_J e) {
        if (this.multiplayerClientSuggestionProvider > 0) {
            e.n_1700_B(0.0f);
            e.J_1907_R(0.0f);
            --this.multiplayerClientSuggestionProvider;
        }
    }

    private void x_607_J() {
        boolean keepSprintOnHit;
        if (this.w_1457_N || this.v_4262_N == null) {
            return;
        }
        if (!this.h_1847_R()) {
            return;
        }
        if (this.v_4262_N.R_4764_Y((N_4263_v)TriggerBot.c_3005_b.Y_259_p) > ((Float)this.attackRangeSetting.getValue()).floatValue()) {
            return;
        }
        boolean isInLiquid = TriggerBot.c_3005_b.Y_259_p.RowButton() || TriggerBot.c_3005_b.Y_259_p.W_3464_O() || TriggerBot.c_3005_b.Y_259_p.C_1269_X();
        boolean inCobwebNow = this.n_1700_B((N_4263_v)TriggerBot.c_3005_b.Y_259_p);
        Sprint sprintMod = (Sprint)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(Sprint.class);
        boolean bl = keepSprintOnHit = sprintMod != null && sprintMod.h_1847_R();
        if (!keepSprintOnHit && !isInLiquid && !inCobwebNow && TriggerBot.c_3005_b.Y_259_p.o_2341_D()) {
            this.multiplayerClientSuggestionProvider = 1;
            if (TriggerBot.c_3005_b.Y_259_p.R_4764_Y) {
                TriggerBot.c_3005_b.Y_259_p.P_1922_E(false);
                TriggerBot.c_3005_b.Y_259_p.b_(false);
                TriggerBot.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new T_3952_j(TriggerBot.c_3005_b.Y_259_p, T_3952_j.n_1700_B.P_1922_E));
            }
        }
        this.M_182_A();
        TriggerBot.c_3005_b.w_1457_N.attackEntity(TriggerBot.c_3005_b.Y_259_p, this.v_4262_N);
        TriggerBot.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
        this.t_1786_h();
        this.t_1786_h.n_1700_B();
        this.d_2427_y();
    }

    public boolean h_1847_R() {
        boolean fallDistanceCondition;
        boolean isForwardingSprintActive;
        boolean bl;
        if (this.t_1786_h.J_1907_R() < 50L) {
            return false;
        }
        if (TriggerBot.c_3005_b.Y_259_p == null || TriggerBot.c_3005_b.Y_601_j == null || this.v_4262_N == null) {
            return false;
        }
        boolean onlyCrits = this.onlyCriticalsEnabled.isEnabled();
        boolean bl2 = bl = !onlyCrits || !this.Q_4569_t();
        if (this.skipAttackWhenOptions.isOptionEnabled("\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0435\u0448\u044c \u0435\u0434\u0443").booleanValue() && TriggerBot.c_3005_b.Y_259_p.C_332_W() || this.skipAttackWhenOptions.isOptionEnabled("\u041e\u0442\u043a\u0440\u044b\u0442 \u043a\u043e\u043d\u0442\u0435\u0439\u043d\u0435\u0440").booleanValue() && TriggerBot.c_3005_b.Y_1740_V != null && TriggerBot.c_3005_b.Y_1740_V != ClientBootstrap.Y_601_j().multiplayerClientSuggestionProvider()) {
            return false;
        }
        if (this.v_4262_N.R_4764_Y((N_4263_v)TriggerBot.c_3005_b.Y_259_p) > ((Float)this.attackRangeSetting.getValue()).floatValue()) {
            return false;
        }
        if (this.noWallHitEnabled.isEnabled().booleanValue() && !TriggerBot.c_3005_b.Y_259_p.c_3005_b(this.v_4262_N)) {
            return false;
        }
        boolean isJumpCritical = this.onlyWhenSpacePressedEnabled.isEnabled() != false && !TriggerBot.c_3005_b.P_4830_p.Ping.G_564_y();
        boolean bl3 = isForwardingSprintActive = TriggerBot.c_3005_b.Y_259_p.R_4764_Y && !TriggerBot.c_3005_b.Y_259_p.C_1269_X();
        if (!isJumpCritical && TriggerBot.c_3005_b.Y_259_p.M_1641_O() && onlyCrits) {
            return false;
        }
        double tps = this.tpsSyncEnabled.isEnabled() != false ? (double)f_2403_E.J_1907_R() : 20.0;
        float f = this.n_1700_B(0.5f, tps);
        float f2 = isForwardingSprintActive ? 0.1f : 0.92f;
        if (f < f2) {
            return false;
        }
        double fallDistance = TriggerBot.c_3005_b.Y_259_p.U_1241_n;
        boolean bl4 = fallDistanceCondition = fallDistance > 0.0;
        if (isForwardingSprintActive) {
            fallDistanceCondition = fallDistanceCondition || TriggerBot.c_3005_b.Y_259_p.M_1641_O() && !TriggerBot.c_3005_b.Y_601_j.a_(TriggerBot.c_3005_b.Y_259_p, TriggerBot.c_3005_b.Y_259_p.i_601_W().offset(0.0, -0.08, 0.0)) || TriggerBot.c_3005_b.Y_259_p.I_4348_c().R_4764_Y < -0.05;
        }
        return bl || isJumpCritical || fallDistanceCondition || a_1344_X.n_1700_B();
    }

    public boolean Q_4569_t() {
        if (TriggerBot.c_3005_b.Y_259_p.W_3464_O() || TriggerBot.c_3005_b.Y_259_p.y_2772_m() || TriggerBot.c_3005_b.Y_259_p.e_() || TriggerBot.c_3005_b.Y_259_p.J_1907_R(MobEffects.Q_4569_t) || TriggerBot.c_3005_b.Y_259_p.J_1907_R(MobEffects.H_2857_Y) || TriggerBot.c_3005_b.Y_259_p.C_415_h.J_1907_R) {
            return false;
        }
        if (TriggerBot.c_3005_b.Y_259_p.M_1641_O() && TriggerBot.c_3005_b.Y_259_p.k_578_l()) {
            return false;
        }
        if (TriggerBot.c_3005_b.Y_259_p.M_1641_O() && !TriggerBot.c_3005_b.Y_601_j.a_(TriggerBot.c_3005_b.Y_259_p, TriggerBot.c_3005_b.Y_259_p.i_601_W().grow(0.0, 0.1f, 0.0))) {
            return false;
        }
        return !((N_4263_v)TriggerBot.c_3005_b.Y_259_p).n_1700_B(FluidTags.J_1907_R) && (!TriggerBot.c_3005_b.Y_259_p.RowButton() || !TriggerBot.c_3005_b.Y_259_p.M_1641_O());
    }

    public float n_1700_B(double d) {
        return (float)(1.0 / TriggerBot.c_3005_b.Y_259_p.J_1907_R(Attributes.w_1484_f) * d);
    }

    public float n_1700_B(float f, double d) {
        float cooled = TriggerBot.c_3005_b.Y_259_p.k_2293_S(f);
        float periodByTps = this.n_1700_B(40.0 - d);
        float basePeriod = (float)(1.0 / TriggerBot.c_3005_b.Y_259_p.J_1907_R(Attributes.w_1484_f) * 20.0);
        if (periodByTps <= 0.0f || basePeriod <= 0.0f) {
            return cooled;
        }
        return u_530_F.n_1700_B(cooled * (basePeriod / periodByTps), 0.0f, 1.0f);
    }

    private boolean e_4240_b() {
        return this.noWallHitEnabled.isEnabled();
    }

    private boolean n_3318_d() {
        return this.noWallHitEnabled.isEnabled() != false && this.allowDoorHitEnabled.isEnabled() != false;
    }

    private boolean d_2427_y() {
        if (!this.breakShieldEnabled.isEnabled().booleanValue()) {
            return false;
        }
        int axeSlot = u_1934_K.n_1700_B();
        if (this.v_4262_N.B_2580_P().J_1907_R() == Items.NoteBlock && axeSlot != -1) {
            if (axeSlot < 9) {
                c_3005_b.k_2293_S().n_1700_B(new p_1183_T(axeSlot));
                TriggerBot.c_3005_b.w_1457_N.attackEntity(TriggerBot.c_3005_b.Y_259_p, this.v_4262_N);
                TriggerBot.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
                c_3005_b.k_2293_S().n_1700_B(new p_1183_T(TriggerBot.c_3005_b.Y_259_p.l_1268_F.G_564_y));
            } else {
                c_3005_b.k_2293_S().n_1700_B(new ServerboundContainerClickPacket(TriggerBot.c_3005_b.Y_259_p.o_1800_r.u_1723_Y, axeSlot, TriggerBot.c_3005_b.Y_259_p.l_1268_F.G_564_y, a_408_T.R_4764_Y, Z_1993_T.J_1907_R, TriggerBot.c_3005_b.Y_259_p.H_1873_g.n_1700_B(TriggerBot.c_3005_b.Y_259_p.l_1268_F)));
                c_3005_b.k_2293_S().n_1700_B(new P_4526_H(TriggerBot.c_3005_b.Y_259_p.o_1800_r.u_1723_Y));
                TriggerBot.c_3005_b.w_1457_N.attackEntity(TriggerBot.c_3005_b.Y_259_p, this.v_4262_N);
                TriggerBot.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
                c_3005_b.k_2293_S().n_1700_B(new ServerboundContainerClickPacket(TriggerBot.c_3005_b.Y_259_p.o_1800_r.u_1723_Y, axeSlot, TriggerBot.c_3005_b.Y_259_p.l_1268_F.G_564_y, a_408_T.R_4764_Y, Z_1993_T.J_1907_R, TriggerBot.c_3005_b.Y_259_p.H_1873_g.n_1700_B(TriggerBot.c_3005_b.Y_259_p.l_1268_F)));
                c_3005_b.k_2293_S().n_1700_B(new P_4526_H(TriggerBot.c_3005_b.Y_259_p.o_1800_r.u_1723_Y));
            }
            return true;
        }
        return false;
    }

    private r_4811_B z_1737_N() {
        try {
            List entities = StreamSupport.stream(TriggerBot.c_3005_b.Y_601_j.J_1907_R().spliterator(), false).collect(Collectors.toList());
            return entities.stream().filter(e -> e instanceof r_4811_B).map(e -> (r_4811_B)e).filter(this::n_1700_B).filter(entity -> v_570_f.n_1700_B(TriggerBot.c_3005_b.Y_259_p.p_178_J, TriggerBot.c_3005_b.Y_259_p.f_4016_n, ((Float)this.attackRangeSetting.getValue()).floatValue(), entity, this.e_4240_b(), this.n_3318_d())).sorted(Comparator.comparingDouble(entity -> entity.R_4764_Y(TriggerBot.c_3005_b.Y_259_p))).findFirst().orElse(null);
        }
        catch (Exception e2) {
            return null;
        }
    }

    private boolean n_1700_B(r_4811_B entity) {
        if (entity == null || !entity.RealmsLongRunningMcoTaskScreen() || entity == TriggerBot.c_3005_b.Y_259_p || entity instanceof D_686_b) {
            return false;
        }
        if (entity instanceof a_3913_L) {
            a_3913_L player = (a_3913_L)entity;
            if (ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(AntiBot.class).w_1484_f() && player.g_4106_L) {
                return false;
            }
        }
        return E_4612_l.n_1700_B(entity, this.targetsOptions, true) || E_4612_l.n_1700_B(entity, this.targetsOptions) || E_4612_l.J_1907_R(entity, this.targetsOptions) || E_4612_l.R_4764_Y(entity, this.targetsOptions);
    }

    public void M_182_A() {
        for (a_3913_L a_3913_L2 : TriggerBot.c_3005_b.Y_601_j.multiplayerClientSuggestionProvider()) {
            if (!(a_3913_L2 instanceof Q_1187_u)) continue;
            ((Q_1187_u)a_3913_L2).R_4764_Y();
        }
    }

    public void t_1786_h() {
        for (a_3913_L a_3913_L2 : TriggerBot.c_3005_b.Y_601_j.multiplayerClientSuggestionProvider()) {
            if (!(a_3913_L2 instanceof Q_1187_u)) continue;
            ((Q_1187_u)a_3913_L2).u_1723_Y();
        }
    }

    @Override
    public void onDisable() {
        this.v_4262_N = null;
        super.onDisable();
    }

    @Generated
    public r_4811_B multiplayerClientSuggestionProvider() {
        return this.v_4262_N;
    }

    @Generated
    public MultiBooleanSetting w_1457_N() {
        return this.targetsOptions;
    }

    @Generated
    public NumberSetting Y_601_j() {
        return this.attackRangeSetting;
    }

    @Generated
    public MultiBooleanSetting Y_259_p() {
        return this.skipAttackWhenOptions;
    }

    @Generated
    public BooleanSetting Q_2552_b() {
        return this.onlyCriticalsEnabled;
    }

    @Generated
    public BooleanSetting C_2741_M() {
        return this.breakShieldEnabled;
    }

    @Generated
    public BooleanSetting k_2293_S() {
        return this.onlyWhenSpacePressedEnabled;
    }

    @Generated
    public BooleanSetting q_2307_F() {
        return this.noWallHitEnabled;
    }

    @Generated
    public BooleanSetting Z_875_P() {
        return this.allowDoorHitEnabled;
    }

    @Generated
    public BooleanSetting c_3005_b() {
        return this.tpsSyncEnabled;
    }

    @Generated
    public V_4557_X H_2857_Y() {
        return this.t_1786_h;
    }

    @Generated
    public int A_4115_X() {
        return this.multiplayerClientSuggestionProvider;
    }

    @Generated
    public boolean Y_1740_V() {
        return this.w_1457_N;
    }
}



