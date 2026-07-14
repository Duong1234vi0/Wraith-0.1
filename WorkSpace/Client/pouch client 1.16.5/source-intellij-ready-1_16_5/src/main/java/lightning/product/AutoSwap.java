/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.H_2506_c;
import lightning.product.MultiBooleanSetting;
import lightning.product.P_4526_H;
import lightning.product.Q_1939_l;
import lightning.product.U_3758_B;
import lightning.product.V_4557_X;
import lightning.product.W_2756_H;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.a_178_J;
import lightning.product.GuiMove;
import lightning.product.a_3913_L;
import lightning.product.a_408_T;
import lightning.product.h_1015_G;
import lightning.product.i_1894_C;
import lightning.product.i_4434_b;
import lightning.product.k_2603_m;
import lightning.product.AirItem;
import lightning.product.m_3147_m;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.q_1613_l;
import lightning.product.KeyBindSetting;
import lightning.product.ModeSetting;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.t_2932_z;
import lightning.product.SwordItem;
import lightning.product.u_1934_K;
import lightning.product.ModuleCategory;
import lightning.product.AutoTotem;

public class AutoSwap
extends Module {
    private final ModeSetting rezhimMode = new ModeSetting("\u0420\u0435\u0436\u0438\u043c", "\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b", "\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b", "\u041a\u043e\u043b\u0435\u0441\u043e");
    private final KeyBindSetting knopkaSvapaKeyBind = new KeyBindSetting("\u041a\u043d\u043e\u043f\u043a\u0430 \u0441\u0432\u0430\u043f\u0430", () -> this.rezhimMode.isMode("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b"));
    private final KeyBindSetting knopkaKolesaKeyBind = new KeyBindSetting("\u041a\u043d\u043e\u043f\u043a\u0430 \u043a\u043e\u043b\u0435\u0441\u0430", () -> this.rezhimMode.isMode("\u041a\u043e\u043b\u0435\u0441\u043e"));
    private final KeyBindSetting kolesoNaGolovuKeyBind = new KeyBindSetting("\u041a\u043e\u043b\u0435\u0441\u043e \u043d\u0430 \u0433\u043e\u043b\u043e\u0432\u0443", () -> this.rezhimMode.isMode("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b") || this.rezhimMode.isMode("\u041a\u043e\u043b\u0435\u0441\u043e"));
    private final ModeSetting pervyyPredmetMode = new ModeSetting("\u041f\u0435\u0440\u0432\u044b\u0439 \u043f\u0440\u0435\u0434\u043c\u0435\u0442", "\u0422\u043e\u0442\u0435\u043c", () -> this.rezhimMode.isMode("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b"), "\u0429\u0438\u0442", "\u0422\u043e\u0442\u0435\u043c", "\u0424\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a", "\u042f\u0431\u043b\u043e\u043a\u043e", "\u041b\u044e\u0431\u0430\u044f \u0435\u0434\u0430", "\u0428\u0430\u0440, C\u0444\u0435\u0440\u0430");
    private final ModeSetting vtoroyPredmetMode = new ModeSetting("\u0412\u0442\u043e\u0440\u043e\u0439 \u043f\u0440\u0435\u0434\u043c\u0435\u0442", "\u0428\u0430\u0440, C\u0444\u0435\u0440\u0430", () -> this.rezhimMode.isMode("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b"), "\u0429\u0438\u0442", "\u0422\u043e\u0442\u0435\u043c", "\u0424\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a", "\u042f\u0431\u043b\u043e\u043a\u043e", "\u041b\u044e\u0431\u0430\u044f \u0435\u0434\u0430", "\u0428\u0430\u0440, C\u0444\u0435\u0440\u0430");
    private final BooleanSetting ignorirovatObychnyeTotemyEnabled = new BooleanSetting("\u0418\u0433\u043d\u043e\u0440\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u043e\u0431\u044b\u0447\u043d\u044b\u0435 \u0442\u043e\u0442\u0435\u043c\u044b", true, () -> this.rezhimMode.isMode("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b"));
    private final KeyBindSetting avtoSharKnopkaKeyBind = new KeyBindSetting("\u0410\u0432\u0442\u043e-\u0448\u0430\u0440 \u043a\u043d\u043e\u043f\u043a\u0430");
    private final MultiBooleanSetting bratSharOptions = new MultiBooleanSetting("\u0411\u0440\u0430\u0442\u044c \u0448\u0430\u0440", new BooleanSetting("\u0412\u0441\u0435\u0433\u0434\u0430", false), new BooleanSetting("\u041a\u043e\u0433\u0434\u0430 \u0431\u0435\u0437 \u043c\u0435\u0447\u0430", true), new BooleanSetting("\u041a\u043e\u0433\u0434\u0430 \u043b\u0438\u0432\u0430\u0435\u0442", true), new BooleanSetting("\u041a\u043e\u0433\u0434\u0430 \u0432 \u044d\u043b\u0438\u0442\u0440\u0435", true));
    private final BooleanSetting ubiratBezTargetaEnabled = new BooleanSetting("\u0423\u0431\u0438\u0440\u0430\u0442\u044c \u0431\u0435\u0437 \u0442\u0430\u0440\u0433\u0435\u0442\u0430", true, () -> this.bratSharOptions.isOptionEnabled("\u0412\u0441\u0435\u0433\u0434\u0430"));
    private final BooleanSetting svapatPeredUdaromEnabled = new BooleanSetting("\u0421\u0432\u0430\u043f\u0430\u0442\u044c \u043f\u0435\u0440\u0435\u0434 \u0443\u0434\u0430\u0440\u043e\u043c", false);
    private boolean multiplayerClientSuggestionProvider = false;
    private boolean w_1457_N = false;
    private int Y_601_j = 0;
    private int Y_259_p = -1;
    private int Q_2552_b = 0;
    private int C_2741_M = 0;
    private int k_2293_S = -1;
    private int q_2307_F = 0;
    private final V_4557_X Z_875_P = new V_4557_X();
    private boolean t_4043_B = false;
    private int x_607_J = -1;
    private boolean e_4240_b = false;
    private int n_3318_d = -1;
    private boolean d_2427_y = false;
    private boolean z_1737_N = false;
    private int v_4276_D = -1;
    private final V_4557_X d_2461_k = new V_4557_X();
    private boolean G_624_v = false;
    private boolean T_2506_i = false;
    private boolean q_4610_l = false;

    public AutoSwap() {
        super("AutoSwap", ModuleCategory.n_1700_B);
        this.addSettings(this.rezhimMode, this.knopkaSvapaKeyBind, this.knopkaKolesaKeyBind, this.kolesoNaGolovuKeyBind, this.pervyyPredmetMode, this.vtoroyPredmetMode, this.ignorirovatObychnyeTotemyEnabled, this.avtoSharKnopkaKeyBind, this.bratSharOptions, this.ubiratBezTargetaEnabled, this.svapatPeredUdaromEnabled);
    }

    private boolean w_1457_N() {
        GuiMove guiWalk = ClientBootstrap.Y_601_j().J_1907_R().P_4830_p;
        return guiWalk != null && guiWalk.w_1484_f() && guiWalk.v_4262_N.J_1907_R("Funtime");
    }

    private boolean Y_601_j() {
        GuiMove guiWalk = ClientBootstrap.Y_601_j().J_1907_R().P_4830_p;
        return guiWalk != null && guiWalk.Q_4569_t();
    }

    private boolean Y_259_p() {
        GuiMove guiWalk = ClientBootstrap.Y_601_j().J_1907_R().P_4830_p;
        return guiWalk != null && guiWalk.h_1847_R();
    }

    private boolean Q_2552_b() {
        GuiMove guiWalk = ClientBootstrap.Y_601_j().J_1907_R().P_4830_p;
        return guiWalk != null && guiWalk.M_182_A();
    }

    private void J_1907_R(int playerInvSlot) {
        this.n_1700_B(() -> {
            u_1934_K.J_1907_R(playerInvSlot);
            AutoSwap.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(AutoSwap.c_3005_b.Y_259_p.H_1873_g.u_1723_Y));
        });
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void n_1700_B(Runnable action) {
        boolean prev;
        GuiMove g = ClientBootstrap.Y_601_j().J_1907_R().P_4830_p;
        boolean bl = prev = g != null && g.s_956_w;
        if (g != null && g.w_1484_f()) {
            g.s_956_w = true;
        }
        try {
            action.run();
        }
        finally {
            if (g != null) {
                g.s_956_w = prev;
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        t_2932_z headHandler;
        m_3147_m handler;
        int invFrom;
        boolean noTargetSwapBack;
        boolean cerberInOffhand;
        if (AutoSwap.c_3005_b.Y_259_p == null) {
            return;
        }
        if (this.Q_2552_b > 0) {
            --this.Q_2552_b;
        }
        if (this.q_2307_F > 0) {
            --this.q_2307_F;
        }
        if (AutoSwap.c_3005_b.Y_259_p.RealmsLongRunningMcoTaskScreen > 0) {
            this.d_2461_k.n_1700_B();
        }
        if (!(cerberInOffhand = this.n_3318_d())) {
            this.z_1737_N = false;
        }
        r_4811_B target = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B != null ? ClientBootstrap.Y_601_j().J_1907_R().n_1700_B.h_1847_R() : null;
        boolean bl = noTargetSwapBack = target == null && (this.bratSharOptions.isOptionEnabled("\u0412\u0441\u0435\u0433\u0434\u0430") == false || this.ubiratBezTargetaEnabled.isEnabled() != false);
        if (!this.svapatPeredUdaromEnabled.isEnabled().booleanValue()) {
            if (this.z_1737_N && this.v_4276_D != -1 && (noTargetSwapBack || target != null && !this.J_1907_R(target))) {
                this.P_1922_E(this.v_4276_D);
                this.v_4276_D = -1;
                this.z_1737_N = false;
                return;
            }
            if (this.d_2427_y && target != null && this.J_1907_R(target) && !this.z_1737_N && this.d_2427_y()) {
                this.t_4043_B();
                this.z_1737_N = true;
            }
        }
        if (this.svapatPeredUdaromEnabled.isEnabled().booleanValue()) {
            if (this.T_2506_i) {
                if (!this.n_3318_d()) {
                    int slot = this.x_607_J();
                    if (slot >= 0) {
                        this.v_4276_D = slot;
                        this.P_1922_E(slot);
                    }
                } else {
                    this.G_624_v = true;
                    this.T_2506_i = false;
                }
            } else if (this.q_4610_l) {
                if (this.n_3318_d() && this.v_4276_D >= 0) {
                    this.P_1922_E(this.v_4276_D);
                } else {
                    this.q_4610_l = false;
                    this.v_4276_D = -1;
                    this.G_624_v = false;
                }
            }
        }
        if (this.t_4043_B && this.x_607_J != -1 && this.Z_875_P.J_1907_R(100L)) {
            invFrom = u_1934_K.n_1700_B(this.x_607_J);
            c_3005_b.n_1700_B(new Q_1939_l(AutoSwap.c_3005_b.Y_259_p));
            this.n_1700_B(() -> AutoSwap.c_3005_b.w_1457_N.windowClick(AutoSwap.c_3005_b.Y_259_p.o_1800_r.u_1723_Y, invFrom, 40, a_408_T.R_4764_Y, AutoSwap.c_3005_b.Y_259_p));
            AutoSwap.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(AutoSwap.c_3005_b.Y_259_p.o_1800_r.u_1723_Y));
            c_3005_b.n_1700_B((k_2603_m)null);
            this.t_4043_B = false;
            this.x_607_J = -1;
            this.multiplayerClientSuggestionProvider = false;
        }
        if (this.e_4240_b && this.n_3318_d != -1 && this.Z_875_P.J_1907_R(100L)) {
            c_3005_b.n_1700_B(new Q_1939_l(AutoSwap.c_3005_b.Y_259_p));
            this.J_1907_R(this.n_3318_d);
            c_3005_b.n_1700_B((k_2603_m)null);
            this.e_4240_b = false;
            this.n_3318_d = -1;
            this.w_1457_N = false;
        }
        if (this.t_4043_B || this.e_4240_b) {
            return;
        }
        if (this.Y_601_j == 1 && this.Y_259_p != -1 && this.Q_2552_b == 0) {
            invFrom = u_1934_K.n_1700_B(this.Y_259_p);
            this.n_1700_B(() -> {
                AutoSwap.c_3005_b.w_1457_N.windowClick(AutoSwap.c_3005_b.Y_259_p.o_1800_r.u_1723_Y, invFrom, 40, a_408_T.R_4764_Y, AutoSwap.c_3005_b.Y_259_p);
                AutoSwap.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(AutoSwap.c_3005_b.Y_259_p.o_1800_r.u_1723_Y));
            });
            this.Y_601_j = 0;
            this.Y_259_p = -1;
            this.multiplayerClientSuggestionProvider = false;
            return;
        }
        if (this.C_2741_M == 1 && this.k_2293_S != -1 && this.q_2307_F == 0) {
            this.J_1907_R(this.k_2293_S);
            this.C_2741_M = 0;
            this.k_2293_S = -1;
            this.w_1457_N = false;
            return;
        }
        if (this.Y_601_j > 0 || this.C_2741_M > 0) {
            return;
        }
        if (this.rezhimMode.isMode("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b")) {
            if (this.multiplayerClientSuggestionProvider) {
                this.k_2293_S();
            }
        } else if (this.rezhimMode.isMode("\u041a\u043e\u043b\u0435\u0441\u043e") && (handler = this.A_4115_X()) != null) {
            int selected = handler.G_564_y();
            if (selected >= 0) {
                boolean offhandDiff;
                Z_1993_T configured = handler.J_1907_R(selected);
                boolean missing = configured.n_1700_B();
                boolean bl2 = offhandDiff = !missing && !this.n_1700_B(AutoSwap.c_3005_b.Y_259_p.S_4035_N(), configured);
                if (!handler.R_4764_Y() && (missing || offhandDiff)) {
                    handler.P_1922_E(-1);
                }
            }
            if (handler.J_1907_R() && this.multiplayerClientSuggestionProvider) {
                this.q_2307_F();
            }
        }
        if ((this.rezhimMode.isMode("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b") || this.rezhimMode.isMode("\u041a\u043e\u043b\u0435\u0441\u043e")) && (headHandler = this.Y_1740_V()) != null) {
            int headSel = headHandler.R_4764_Y();
            if (headSel >= 0) {
                boolean headDiff;
                Z_1993_T headCfg = headHandler.J_1907_R(headSel);
                boolean headMissing = headCfg.n_1700_B();
                Z_1993_T onHead = AutoSwap.c_3005_b.Y_259_p.l_1268_F.s_956_w(39);
                boolean bl3 = headDiff = !headMissing && !this.n_1700_B(onHead, headCfg);
                if (!headHandler.J_1907_R() && (headMissing || headDiff)) {
                    headHandler.G_564_y(-1);
                }
            }
            if (headHandler.n_1700_B() && this.w_1457_N) {
                this.Z_875_P();
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(a_178_J e) {
        if (this.Q_2552_b > 0 || this.q_2307_F > 0) {
            e.n_1700_B(0.0f);
            e.J_1907_R(0.0f);
            e.P_1922_E(false);
            e.u_1723_Y(false);
        }
        if (this.t_4043_B || this.e_4240_b) {
            if (!this.Z_875_P.J_1907_R(190L)) {
                e.n_1700_B(0.0f);
                e.J_1907_R(0.0f);
                e.u_1723_Y(false);
            }
            if (!this.Z_875_P.J_1907_R(350L)) {
                e.P_1922_E(false);
            }
        }
    }

    private q_1613_l R_4764_Y(String mode) {
        return switch (mode) {
            case "\u0422\u043e\u0442\u0435\u043c" -> Items.N_81_X;
            case "\u0429\u0438\u0442" -> Items.NoteBlock;
            case "\u0424\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a" -> Items.FenceBlock;
            case "\u042f\u0431\u043b\u043e\u043a\u043e" -> Items.p_863_D;
            case "\u0428\u0430\u0440, C\u0444\u0435\u0440\u0430" -> Items.C_3560_B;
            case "\u041b\u044e\u0431\u0430\u044f \u0435\u0434\u0430" -> null;
            default -> Items.n_1700_B;
        };
    }

    private int n_1700_B(q_1613_l item) {
        if (item == Items.N_81_X && this.ignorirovatObychnyeTotemyEnabled.isEnabled().booleanValue()) {
            return u_1934_K.P_1922_E();
        }
        if (item == null) {
            return u_1934_K.G_564_y();
        }
        return u_1934_K.u_1723_Y(item);
    }

    private int C_2741_M() {
        Z_1993_T offhandItemStack = AutoSwap.c_3005_b.Y_259_p.S_4035_N();
        q_1613_l currentOffhandItem = offhandItemStack.J_1907_R();
        q_1613_l first = this.R_4764_Y((String)this.pervyyPredmetMode.getValue());
        q_1613_l second = this.R_4764_Y((String)this.vtoroyPredmetMode.getValue());
        if (currentOffhandItem instanceof AirItem) {
            int firstSlot = this.n_1700_B(first);
            if (firstSlot >= 0) {
                return firstSlot;
            }
            return this.n_1700_B(second);
        }
        if (first != null && currentOffhandItem == first || first == null && currentOffhandItem.Y_259_p()) {
            return this.n_1700_B(second);
        }
        if (second != null && currentOffhandItem == second || second == null && currentOffhandItem.Y_259_p()) {
            return this.n_1700_B(first);
        }
        int firstSlot = this.n_1700_B(first);
        return firstSlot >= 0 ? firstSlot : this.n_1700_B(second);
    }

    @Y_1740_V
    public void n_1700_B(i_4434_b e) {
        Z_1993_T stack;
        k_2603_m screen;
        if (AutoSwap.c_3005_b.Y_1740_V != null && !(AutoSwap.c_3005_b.Y_1740_V instanceof i_1894_C) && !(AutoSwap.c_3005_b.Y_1740_V instanceof W_2756_H)) {
            return;
        }
        int pressed = e.n_1700_B();
        if (this.rezhimMode.isMode("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b") && pressed == (Integer)this.knopkaSvapaKeyBind.getKey() && e.J_1907_R()) {
            this.multiplayerClientSuggestionProvider = true;
        }
        if (this.rezhimMode.isMode("\u041a\u043e\u043b\u0435\u0441\u043e") && pressed == (Integer)this.knopkaKolesaKeyBind.getKey()) {
            if (e.J_1907_R()) {
                this.multiplayerClientSuggestionProvider = true;
                if (!(AutoSwap.c_3005_b.Y_1740_V instanceof i_1894_C)) {
                    c_3005_b.n_1700_B(new i_1894_C());
                }
            } else {
                k_2603_m k_2603_m2 = AutoSwap.c_3005_b.Y_1740_V;
                if (k_2603_m2 instanceof i_1894_C) {
                    m_3147_m handler;
                    screen = (i_1894_C)k_2603_m2;
                    int hovered = ((i_1894_C)screen).n_1700_B();
                    if (hovered >= 0 && (handler = this.A_4115_X()) != null && !(stack = handler.J_1907_R(hovered)).n_1700_B()) {
                        handler.P_1922_E(hovered);
                    }
                    c_3005_b.n_1700_B((k_2603_m)null);
                }
            }
        }
        if ((this.rezhimMode.isMode("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b") || this.rezhimMode.isMode("\u041a\u043e\u043b\u0435\u0441\u043e")) && pressed == (Integer)this.kolesoNaGolovuKeyBind.getKey()) {
            if (e.J_1907_R()) {
                this.w_1457_N = true;
                if (!(AutoSwap.c_3005_b.Y_1740_V instanceof W_2756_H)) {
                    c_3005_b.n_1700_B(new W_2756_H());
                }
            } else {
                k_2603_m hovered = AutoSwap.c_3005_b.Y_1740_V;
                if (hovered instanceof W_2756_H) {
                    t_2932_z headHandler;
                    screen = (W_2756_H)hovered;
                    int hovered2 = ((W_2756_H)screen).n_1700_B();
                    if (hovered2 >= 0 && (headHandler = this.Y_1740_V()) != null && !(stack = headHandler.J_1907_R(hovered2)).n_1700_B()) {
                        headHandler.G_564_y(hovered2);
                    }
                    c_3005_b.n_1700_B((k_2603_m)null);
                }
            }
        }
        if (!e.J_1907_R() && (Integer)this.avtoSharKnopkaKeyBind.getKey() == pressed) {
            boolean bl = this.d_2427_y = !this.d_2427_y;
            if (this.d_2427_y) {
                Z_1993_T sphere = this.e_4240_b();
                String sphereName = sphere.n_1700_B() ? "\u043d\u0435 \u0432\u044b\u0431\u0440\u0430\u043d" : sphere.multiplayerClientSuggestionProvider().getString();
                U_3758_B.n_1700_B("J", "\u0410\u0432\u0442\u043e-\u0448\u0430\u0440 \u0432\u043a\u043b\u044e\u0447\u0435\u043d: " + sphereName, H_2506_c.n_1700_B(100, 255, 100));
            } else {
                U_3758_B.n_1700_B("K", "\u0410\u0432\u0442\u043e-\u0448\u0430\u0440 \u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d", H_2506_c.n_1700_B(255, 80, 80));
            }
        }
    }

    private void k_2293_S() {
        int targetSlot = this.C_2741_M();
        if (targetSlot < 0) {
            this.multiplayerClientSuggestionProvider = false;
            return;
        }
        if (this.w_1457_N()) {
            this.Z_875_P.n_1700_B();
            this.t_4043_B = true;
            this.x_607_J = targetSlot;
            return;
        }
        if (this.Y_259_p() || this.Q_2552_b()) {
            this.Q_2552_b = 2;
            this.Y_601_j = 1;
            this.Y_259_p = targetSlot;
        } else {
            if (this.Y_601_j()) {
                GuiMove guiWalk = ClientBootstrap.Y_601_j().J_1907_R().P_4830_p;
                guiWalk.multiplayerClientSuggestionProvider();
            }
            int invFrom = u_1934_K.n_1700_B(targetSlot);
            this.n_1700_B(() -> {
                AutoSwap.c_3005_b.w_1457_N.windowClick(0, invFrom, 40, a_408_T.R_4764_Y, AutoSwap.c_3005_b.Y_259_p);
                AutoSwap.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(AutoSwap.c_3005_b.Y_259_p.H_1873_g.u_1723_Y));
            });
            this.multiplayerClientSuggestionProvider = false;
        }
    }

    private void q_2307_F() {
        Z_1993_T target = this.H_2857_Y();
        Z_1993_T off = AutoSwap.c_3005_b.Y_259_p.S_4035_N();
        if (target.n_1700_B() || this.n_1700_B(off, target)) {
            return;
        }
        int targetSlot = this.n_1700_B(target);
        if (targetSlot < 0) {
            this.multiplayerClientSuggestionProvider = false;
            return;
        }
        this.G_564_y(targetSlot);
    }

    private void Z_875_P() {
        Z_1993_T target = this.c_3005_b();
        Z_1993_T onHead = AutoSwap.c_3005_b.Y_259_p.l_1268_F.s_956_w(39);
        if (target.n_1700_B() || this.n_1700_B(onHead, target)) {
            return;
        }
        int targetSlot = this.J_1907_R(target);
        if (targetSlot < 0) {
            this.w_1457_N = false;
            return;
        }
        this.R_4764_Y(targetSlot);
    }

    private Z_1993_T c_3005_b() {
        t_2932_z handler = this.Y_1740_V();
        if (handler == null || AutoSwap.c_3005_b.Y_259_p == null) {
            return Z_1993_T.J_1907_R;
        }
        int index = handler.R_4764_Y();
        if (index < 0) {
            return Z_1993_T.J_1907_R;
        }
        return handler.J_1907_R(index);
    }

    private void R_4764_Y(int targetSlot) {
        if (this.w_1457_N()) {
            this.Z_875_P.n_1700_B();
            this.e_4240_b = true;
            this.n_3318_d = targetSlot;
            return;
        }
        if (this.Y_259_p() || this.Q_2552_b()) {
            this.q_2307_F = 2;
            this.C_2741_M = 1;
            this.k_2293_S = targetSlot;
        } else {
            if (this.Y_601_j()) {
                GuiMove guiWalk = ClientBootstrap.Y_601_j().J_1907_R().P_4830_p;
                guiWalk.multiplayerClientSuggestionProvider();
            }
            this.J_1907_R(targetSlot);
            this.w_1457_N = false;
        }
    }

    private Z_1993_T H_2857_Y() {
        m_3147_m handler = this.A_4115_X();
        if (handler == null || AutoSwap.c_3005_b.Y_259_p == null) {
            return Z_1993_T.J_1907_R;
        }
        int index = handler.G_564_y();
        if (index < 0) {
            return Z_1993_T.J_1907_R;
        }
        return handler.J_1907_R(index);
    }

    private m_3147_m A_4115_X() {
        return ClientBootstrap.Y_601_j().t_1786_h().J_1907_R();
    }

    private t_2932_z Y_1740_V() {
        return ClientBootstrap.Y_601_j().t_1786_h().R_4764_Y();
    }

    private int n_1700_B(Z_1993_T template) {
        if (template == null || template.n_1700_B()) {
            return -1;
        }
        if (this.rezhimMode.isMode("\u041a\u043e\u043b\u0435\u0441\u043e")) {
            for (int i = 0; i < AutoSwap.c_3005_b.Y_259_p.l_1268_F.Y_259_p(); ++i) {
                Z_1993_T stack = AutoSwap.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
                if (!this.n_1700_B(stack, template)) continue;
                return i;
            }
            return -1;
        }
        for (int i = 0; i < AutoSwap.c_3005_b.Y_259_p.l_1268_F.Y_259_p(); ++i) {
            Z_1993_T stack = AutoSwap.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (!this.n_1700_B(stack, template)) continue;
            return i;
        }
        return this.n_1700_B(template.J_1907_R());
    }

    private boolean n_1700_B(Z_1993_T a, Z_1993_T b) {
        if (a == null || b == null || a.n_1700_B() || b.n_1700_B()) {
            return false;
        }
        if (!Z_1993_T.J_1907_R(a, b)) {
            return false;
        }
        return a.multiplayerClientSuggestionProvider().getString().equals(b.multiplayerClientSuggestionProvider().getString());
    }

    private void G_564_y(int targetSlot) {
        if (this.w_1457_N()) {
            this.Z_875_P.n_1700_B();
            this.t_4043_B = true;
            this.x_607_J = targetSlot;
            return;
        }
        if (this.Y_259_p() || this.Q_2552_b()) {
            this.Q_2552_b = 2;
            this.Y_601_j = 1;
            this.Y_259_p = targetSlot;
        } else {
            if (this.Y_601_j()) {
                GuiMove guiWalk = ClientBootstrap.Y_601_j().J_1907_R().P_4830_p;
                guiWalk.multiplayerClientSuggestionProvider();
            }
            int invFrom = u_1934_K.n_1700_B(targetSlot);
            this.n_1700_B(() -> {
                AutoSwap.c_3005_b.w_1457_N.windowClick(AutoSwap.c_3005_b.Y_259_p.o_1800_r.u_1723_Y, invFrom, 40, a_408_T.R_4764_Y, AutoSwap.c_3005_b.Y_259_p);
                AutoSwap.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(AutoSwap.c_3005_b.Y_259_p.o_1800_r.u_1723_Y));
            });
            this.multiplayerClientSuggestionProvider = false;
        }
    }

    private int J_1907_R(Z_1993_T template) {
        if (template == null || template.n_1700_B()) {
            return -1;
        }
        for (int i = 0; i < AutoSwap.c_3005_b.Y_259_p.l_1268_F.Y_259_p(); ++i) {
            Z_1993_T stack;
            if (i == 39 || !this.n_1700_B(stack = AutoSwap.c_3005_b.Y_259_p.l_1268_F.s_956_w(i), template)) continue;
            return i;
        }
        Z_1993_T onHead = AutoSwap.c_3005_b.Y_259_p.l_1268_F.s_956_w(39);
        if (this.n_1700_B(onHead, template)) {
            return 39;
        }
        return this.n_1700_B(template.J_1907_R());
    }

    private boolean J_1907_R(r_4811_B target) {
        if (!this.d_2427_y) {
            return false;
        }
        if (this.bratSharOptions.isOptionEnabled("\u0412\u0441\u0435\u0433\u0434\u0430").booleanValue()) {
            return true;
        }
        if (AutoSwap.c_3005_b.Y_259_p.RealmsLongRunningMcoTaskScreen > 0 || target == null) {
            return false;
        }
        if (this.bratSharOptions.isOptionEnabled("\u041a\u043e\u0433\u0434\u0430 \u0431\u0435\u0437 \u043c\u0435\u0447\u0430").booleanValue() && !(target.A_2714_y().J_1907_R() instanceof SwordItem)) {
            return true;
        }
        if (this.bratSharOptions.isOptionEnabled("\u041a\u043e\u0433\u0434\u0430 \u043b\u0438\u0432\u0430\u0435\u0442").booleanValue() && this.R_4764_Y(target) && !AutoSwap.c_3005_b.Y_259_p.RowButton()) {
            return true;
        }
        if (this.bratSharOptions.isOptionEnabled("\u041a\u043e\u0433\u0434\u0430 \u0432 \u044d\u043b\u0438\u0442\u0440\u0435").booleanValue() && target instanceof a_3913_L) {
            a_3913_L player = (a_3913_L)target;
            Z_1993_T chestplate = player.l_1268_F.J_1907_R.get(2);
            if (chestplate.J_1907_R() == Items.NyliumBlock) {
                return true;
            }
        }
        return false;
    }

    private void t_4043_B() {
        if (AutoSwap.c_3005_b.Y_1740_V != null) {
            return;
        }
        int cerberSlot = this.x_607_J();
        if (cerberSlot < 0) {
            return;
        }
        this.v_4276_D = cerberSlot;
        this.P_1922_E(cerberSlot);
    }

    private int x_607_J() {
        Z_1993_T selectedSphere = this.e_4240_b();
        if (selectedSphere.n_1700_B()) {
            return -1;
        }
        for (int i = 0; i < 36; ++i) {
            Z_1993_T stack = AutoSwap.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (!this.n_1700_B(stack, selectedSphere)) continue;
            return i;
        }
        return -1;
    }

    private Z_1993_T e_4240_b() {
        m_3147_m handler = this.A_4115_X();
        if (handler == null) {
            return Z_1993_T.J_1907_R;
        }
        return handler.n_1700_B();
    }

    private boolean n_3318_d() {
        Z_1993_T selectedSphere = this.e_4240_b();
        if (selectedSphere.n_1700_B()) {
            return false;
        }
        return this.n_1700_B(AutoSwap.c_3005_b.Y_259_p.S_4035_N(), selectedSphere);
    }

    private void P_1922_E(int slot) {
        int invFrom = u_1934_K.n_1700_B(slot);
        this.n_1700_B(() -> {
            AutoSwap.c_3005_b.w_1457_N.windowClick(0, invFrom, 40, a_408_T.R_4764_Y, AutoSwap.c_3005_b.Y_259_p);
            AutoSwap.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(AutoSwap.c_3005_b.Y_259_p.H_1873_g.u_1723_Y));
        });
    }

    private boolean d_2427_y() {
        AutoTotem autoTotem = (AutoTotem)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(AutoTotem.class);
        if (autoTotem == null || !autoTotem.w_1484_f()) {
            return true;
        }
        float currentHealth = AutoSwap.c_3005_b.Y_259_p.g_46_E() + AutoSwap.c_3005_b.Y_259_p.U_3823_u();
        return currentHealth > 4.0f;
    }

    private boolean R_4764_Y(r_4811_B target) {
        double dz;
        if (AutoSwap.c_3005_b.Y_259_p == null) {
            return false;
        }
        double targetVelocity = Math.sqrt(target.I_4348_c().J_1907_R * target.I_4348_c().J_1907_R + target.I_4348_c().G_564_y * target.I_4348_c().G_564_y);
        if (targetVelocity < 0.1) {
            return false;
        }
        double dx = target.O_3598_v() - AutoSwap.c_3005_b.Y_259_p.O_3598_v();
        double distance = Math.sqrt(dx * dx + (dz = target.l_2647_k() - AutoSwap.c_3005_b.Y_259_p.l_2647_k()) * dz);
        if (distance < 0.1) {
            return false;
        }
        double directionX = dx / distance;
        double velocityX = target.I_4348_c().J_1907_R;
        double directionZ = dz / distance;
        double velocityZ = target.I_4348_c().G_564_y;
        double dotProduct = directionX * velocityX + directionZ * velocityZ;
        return dotProduct > 0.15;
    }

    public boolean h_1847_R() {
        return this.d_2427_y;
    }

    public boolean Q_4569_t() {
        return this.z_1737_N;
    }

    public boolean n_1700_B(r_4811_B target) {
        return this.d_2427_y && this.svapatPeredUdaromEnabled.isEnabled() != false && target != null && this.d_2427_y() && !this.G_624_v;
    }

    public boolean M_182_A() {
        return this.G_624_v && this.n_3318_d();
    }

    public void t_1786_h() {
        if (!this.svapatPeredUdaromEnabled.isEnabled().booleanValue() || !this.d_2427_y) {
            return;
        }
        this.T_2506_i = true;
        this.q_4610_l = false;
    }

    public void multiplayerClientSuggestionProvider() {
        if (!this.svapatPeredUdaromEnabled.isEnabled().booleanValue() || !this.d_2427_y) {
            return;
        }
        if (!this.G_624_v) {
            return;
        }
        this.q_4610_l = true;
        this.T_2506_i = false;
    }

    private boolean G_564_y(r_4811_B target) {
        if (!this.d_2427_y) {
            return false;
        }
        if (this.bratSharOptions.isOptionEnabled("\u0412\u0441\u0435\u0433\u0434\u0430").booleanValue()) {
            return true;
        }
        if (target == null) {
            return false;
        }
        if (this.bratSharOptions.isOptionEnabled("\u041a\u043e\u0433\u0434\u0430 \u0431\u0435\u0437 \u043c\u0435\u0447\u0430").booleanValue() && !(target.A_2714_y().J_1907_R() instanceof SwordItem)) {
            return true;
        }
        if (this.bratSharOptions.isOptionEnabled("\u041a\u043e\u0433\u0434\u0430 \u043b\u0438\u0432\u0430\u0435\u0442").booleanValue() && this.R_4764_Y(target) && !AutoSwap.c_3005_b.Y_259_p.RowButton()) {
            return true;
        }
        if (this.bratSharOptions.isOptionEnabled("\u041a\u043e\u0433\u0434\u0430 \u0432 \u044d\u043b\u0438\u0442\u0440\u0435").booleanValue() && target instanceof a_3913_L) {
            a_3913_L player = (a_3913_L)target;
            Z_1993_T chestplate = player.l_1268_F.J_1907_R.get(2);
            if (chestplate.J_1907_R() == Items.NyliumBlock) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onDisable() {
        this.multiplayerClientSuggestionProvider = false;
        this.w_1457_N = false;
        this.Y_601_j = 0;
        this.Y_259_p = -1;
        this.Q_2552_b = 0;
        this.C_2741_M = 0;
        this.k_2293_S = -1;
        this.q_2307_F = 0;
        this.t_4043_B = false;
        this.x_607_J = -1;
        this.e_4240_b = false;
        this.n_3318_d = -1;
        this.d_2427_y = false;
        this.z_1737_N = false;
        this.v_4276_D = -1;
        this.G_624_v = false;
        this.T_2506_i = false;
        this.q_4610_l = false;
        super.onDisable();
    }
}



