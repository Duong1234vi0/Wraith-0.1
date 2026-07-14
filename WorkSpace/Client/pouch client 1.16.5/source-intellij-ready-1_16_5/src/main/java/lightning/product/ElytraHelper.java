/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.H_2506_c;
import lightning.product.NumberSetting;
import lightning.product.MobEffects;
import lightning.product.P_4526_H;
import lightning.product.Q_1939_l;
import lightning.product.R_2515_i;
import lightning.product.T_3952_j;
import lightning.product.U_3758_B;
import lightning.product.V_4557_X;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.Z_3504_M;
import lightning.product.a_178_J;
import lightning.product.GuiMove;
import lightning.product.a_408_T;
import lightning.product.ElytraJump;
import lightning.product.e_1174_E;
import lightning.product.ElytraItem;
import lightning.product.h_1015_G;
import lightning.product.i_4434_b;
import lightning.product.k_2603_m;
import lightning.product.ClientBootstrap;
import lightning.product.p_1183_T;
import lightning.product.BooleanSetting;
import lightning.product.q_1613_l;
import lightning.product.KeyBindSetting;
import lightning.product.Items;
import lightning.product.v_1900_v;
import lightning.product.x_1688_C;
import lightning.product.ModuleCategory;

public class ElytraHelper
extends Module {
    private final KeyBindSetting knopkaSvapaKeyBind = new KeyBindSetting("\u041a\u043d\u043e\u043f\u043a\u0430 \u0441\u0432\u0430\u043f\u0430");
    private final KeyBindSetting knopkaFeyeraKeyBind = new KeyBindSetting("\u041a\u043d\u043e\u043f\u043a\u0430 \u0444\u0435\u0439\u0435\u0440\u0430");
    private final BooleanSetting feyeverkiTolkoSHotbaraEnabled = new BooleanSetting("\u0424\u0435\u0439\u0435\u0432\u0435\u0440\u043a\u0438 \u0442\u043e\u043b\u044c\u043a\u043e \u0441 \u0445\u043e\u0442\u0431\u0430\u0440\u0430", false, () -> (Integer)this.knopkaFeyeraKeyBind.getKey() != -1);
    private final NumberSetting slotHotbara19Setting = new NumberSetting("\u0421\u043b\u043e\u0442 \u0445\u043e\u0442\u0431\u0430\u0440\u0430 (1-9)", 1.0f, 1.0f, 9.0f, 1.0f, () -> this.feyeverkiTolkoSHotbaraEnabled.isEnabled());
    private final BooleanSetting obhodFeyeraHwEnabled = new BooleanSetting("\u041e\u0431\u0445\u043e\u0434 \u0444\u0435\u0439\u0435\u0440\u0430 HW", false, () -> (Integer)this.knopkaFeyeraKeyBind.getKey() != -1);
    private final NumberSetting zaderzhkaSvapaSetting = new NumberSetting("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u0441\u0432\u0430\u043f\u0430", 1.0f, 0.0f, 9.0f, 1.0f);
    private final BooleanSetting snimatElitryPriPrizemleniiEnabled = new BooleanSetting("\u0421\u043d\u0438\u043c\u0430\u0442\u044c \u044d\u043b\u0438\u0442\u0440\u044b \u043f\u0440\u0438 \u043f\u0440\u0438\u0437\u0435\u043c\u043b\u0435\u043d\u0438\u0438", false, () -> (Integer)this.knopkaSvapaKeyBind.getKey() != -1);
    private final BooleanSetting avtomaticheskiVzletatEnabled = new BooleanSetting("\u0410\u0432\u0442\u043e\u043c\u0430\u0442\u0438\u0447\u0435\u0441\u043a\u0438 \u0432\u0437\u043b\u0435\u0442\u0430\u0442\u044c", false);
    private final BooleanSetting feyeverkPriVzleteEnabled = new BooleanSetting("\u0424\u0435\u0439\u0435\u0432\u0435\u0440\u043a \u043f\u0440\u0438 \u0432\u0437\u043b\u0435\u0442\u0435", false);
    private final BooleanSetting vklyuchatUvedomleniyaEnabled = new BooleanSetting("\u0412\u043a\u043b\u044e\u0447\u0430\u0442\u044c \u0443\u0432\u0435\u0434\u043e\u043c\u043b\u0435\u043d\u0438\u044f", true);
    private boolean t_1786_h = false;
    private boolean multiplayerClientSuggestionProvider = false;
    private int w_1457_N = 0;
    private int Y_601_j = 0;
    private final V_4557_X Y_259_p = new V_4557_X();
    private boolean Q_2552_b = false;
    private boolean C_2741_M = false;
    private Boolean k_2293_S = null;
    private int q_2307_F = 0;
    private int Z_875_P = -1;

    public ElytraHelper() {
        super("ElytraHelper", ModuleCategory.P_1922_E);
        this.addSettings(this.knopkaSvapaKeyBind, this.knopkaFeyeraKeyBind, this.feyeverkiTolkoSHotbaraEnabled, this.slotHotbara19Setting, this.obhodFeyeraHwEnabled, this.zaderzhkaSvapaSetting, this.snimatElitryPriPrizemleniiEnabled, this.avtomaticheskiVzletatEnabled, this.feyeverkPriVzleteEnabled, this.vklyuchatUvedomleniyaEnabled);
    }

    private boolean t_1786_h() {
        GuiMove guiWalk = ClientBootstrap.Y_601_j().J_1907_R().P_4830_p;
        return guiWalk != null && guiWalk.w_1484_f() && guiWalk.v_4262_N.J_1907_R("Funtime");
    }

    private boolean multiplayerClientSuggestionProvider() {
        GuiMove guiWalk = ClientBootstrap.Y_601_j().J_1907_R().P_4830_p;
        return guiWalk != null && guiWalk.Q_4569_t();
    }

    private boolean w_1457_N() {
        GuiMove guiWalk = ClientBootstrap.Y_601_j().J_1907_R().P_4830_p;
        return guiWalk != null && guiWalk.h_1847_R();
    }

    private boolean Y_601_j() {
        GuiMove guiWalk = ClientBootstrap.Y_601_j().J_1907_R().P_4830_p;
        return guiWalk != null && guiWalk.M_182_A();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        GuiMove guiWalk;
        if (this.Y_601_j > 0) {
            --this.Y_601_j;
        }
        if (this.w_1457_N > 0) {
            --this.w_1457_N;
        }
        if (this.q_2307_F > 0) {
            --this.q_2307_F;
            if (this.q_2307_F == 0 && this.Z_875_P >= 0 && ElytraHelper.c_3005_b.Y_259_p != null) {
                ElytraHelper.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(this.Z_875_P));
                ElytraHelper.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.Z_875_P;
                this.Z_875_P = -1;
            }
        }
        if (this.Q_2552_b && this.Y_259_p.J_1907_R(100L) && this.w_1457_N == 0) {
            c_3005_b.n_1700_B(new Q_1939_l(ElytraHelper.c_3005_b.Y_259_p));
            this.Y_259_p();
            ElytraHelper.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(ElytraHelper.c_3005_b.Y_259_p.H_1873_g.u_1723_Y));
            c_3005_b.n_1700_B((k_2603_m)null);
            this.Q_2552_b = false;
            this.t_1786_h = false;
            this.k_2293_S = null;
            this.w_1457_N = ((Float)this.zaderzhkaSvapaSetting.getValue()).intValue();
        }
        if (this.C_2741_M && this.Y_601_j == 0 && this.w_1457_N == 0) {
            boolean shouldBypassGuiMove;
            guiWalk = ClientBootstrap.Y_601_j().J_1907_R().P_4830_p;
            boolean bl = shouldBypassGuiMove = guiWalk != null && (guiWalk.h_1847_R() || guiWalk.M_182_A());
            if (shouldBypassGuiMove) {
                guiWalk.s_956_w = true;
            }
            try {
                this.Y_259_p();
                ElytraHelper.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(ElytraHelper.c_3005_b.Y_259_p.H_1873_g.u_1723_Y));
            }
            finally {
                if (shouldBypassGuiMove) {
                    guiWalk.s_956_w = false;
                }
            }
            this.C_2741_M = false;
            this.t_1786_h = false;
            this.k_2293_S = null;
            this.w_1457_N = ((Float)this.zaderzhkaSvapaSetting.getValue()).intValue();
        }
        if (this.Q_2552_b || this.C_2741_M) {
            this.multiplayerClientSuggestionProvider = ElytraHelper.c_3005_b.Y_259_p.M_1641_O();
            return;
        }
        if (this.t_1786_h && this.w_1457_N == 0) {
            if (this.t_1786_h()) {
                this.Y_259_p.n_1700_B();
                this.Q_2552_b = true;
                this.Y_601_j = 2;
            } else if (this.w_1457_N() || this.Y_601_j()) {
                this.Y_601_j = 2;
                this.C_2741_M = true;
            } else {
                if (this.multiplayerClientSuggestionProvider()) {
                    guiWalk = ClientBootstrap.Y_601_j().J_1907_R().P_4830_p;
                    guiWalk.multiplayerClientSuggestionProvider();
                }
                this.Y_259_p();
                ElytraHelper.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(ElytraHelper.c_3005_b.Y_259_p.H_1873_g.u_1723_Y));
                this.t_1786_h = false;
                this.k_2293_S = null;
                this.w_1457_N = ((Float)this.zaderzhkaSvapaSetting.getValue()).intValue();
            }
        }
        if (this.snimatElitryPriPrizemleniiEnabled.isEnabled().booleanValue() && !this.multiplayerClientSuggestionProvider && ElytraHelper.c_3005_b.Y_259_p.M_1641_O() && ElytraHelper.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E).J_1907_R() == Items.NyliumBlock && ElytraHelper.M_182_A() != -1 && ElytraHelper.c_3005_b.Y_259_p.k_578_l() && this.w_1457_N == 0) {
            this.t_1786_h = true;
        }
        ElytraJump elytraJump = (ElytraJump)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(ElytraJump.class);
        if (!(!this.avtomaticheskiVzletatEnabled.isEnabled().booleanValue() || elytraJump != null && elytraJump.w_1484_f() || ElytraHelper.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E).J_1907_R() != Items.NyliumBlock || ElytraHelper.c_3005_b.Y_259_p.k_578_l() || ElytraHelper.c_3005_b.Y_259_p.RowButton() || ElytraHelper.c_3005_b.Y_259_p.J_1907_R(MobEffects.q_2307_F))) {
            if (ElytraHelper.c_3005_b.Y_259_p.M_1641_O()) {
                if (!ElytraHelper.c_3005_b.P_4830_p.Ping.G_564_y()) {
                    ElytraHelper.c_3005_b.Y_259_p.e_837_t();
                }
            } else if (ElytraItem.G_564_y(ElytraHelper.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E)) && !ElytraHelper.c_3005_b.Y_259_p.C_415_h.J_1907_R) {
                ElytraHelper.c_3005_b.Y_259_p.y_2447_C();
                ElytraHelper.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new T_3952_j(ElytraHelper.c_3005_b.Y_259_p, T_3952_j.n_1700_B.t_148_a));
                if (this.feyeverkPriVzleteEnabled.isEnabled().booleanValue() && !this.Q_2552_b()) {
                    ElytraHelper.J_1907_R(Items.FenceBlock);
                }
            }
        }
        this.multiplayerClientSuggestionProvider = ElytraHelper.c_3005_b.Y_259_p.M_1641_O();
    }

    @Y_1740_V
    public void n_1700_B(i_4434_b e) {
        boolean hasChestplate;
        if (e.J_1907_R()) {
            return;
        }
        boolean hasElytra = ElytraHelper.n_1700_B(Items.NyliumBlock) != -1;
        boolean bl = hasChestplate = ElytraHelper.M_182_A() != -1;
        if (e.n_1700_B() == ((Integer)this.knopkaSvapaKeyBind.getKey()).intValue() && (hasElytra || hasChestplate) && this.w_1457_N == 0) {
            this.t_1786_h = true;
        }
        if (e.n_1700_B() == ((Integer)this.knopkaFeyeraKeyBind.getKey()).intValue() && ElytraHelper.c_3005_b.Y_259_p.k_578_l()) {
            if (this.Q_2552_b()) {
                return;
            }
            if (this.obhodFeyeraHwEnabled.isEnabled().booleanValue() && this.k_2293_S()) {
                return;
            }
            if (this.feyeverkiTolkoSHotbaraEnabled.isEnabled().booleanValue()) {
                int slot = ElytraHelper.n_1700_B(Items.FenceBlock);
                if (slot >= 0 && slot < 9) {
                    ElytraHelper.J_1907_R(Items.FenceBlock);
                } else if (slot >= 9) {
                    int targetHotbar = Math.max(1, Math.min(9, ((Float)this.slotHotbara19Setting.getValue()).intValue())) - 1;
                    int invFrom = slot < 9 ? slot + 36 : slot;
                    try {
                        ElytraHelper.c_3005_b.w_1457_N.windowClick(ElytraHelper.c_3005_b.Y_259_p.H_1873_g.u_1723_Y, invFrom, targetHotbar, a_408_T.R_4764_Y, ElytraHelper.c_3005_b.Y_259_p);
                        ElytraHelper.J_1907_R(Items.FenceBlock);
                    }
                    catch (Exception ex) {
                        v_1900_v.n_1700_B("\u00a7c\u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u043f\u0435\u0440\u0435\u043c\u0435\u0441\u0442\u0438\u0442\u044c \u0444\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a \u0432 \u0445\u043e\u0442\u0431\u0430\u0440: " + ex.getMessage(), new Object[0]);
                    }
                } else {
                    v_1900_v.n_1700_B("\u00a7c\u0424\u0435\u0439\u0432\u0435\u0440\u043a\u0438 \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u044b \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435", new Object[0]);
                }
            } else {
                ElytraHelper.J_1907_R(Items.FenceBlock);
            }
        }
    }

    @Y_1740_V
    private void n_1700_B(a_178_J e) {
        if (this.Y_601_j > 0) {
            e.n_1700_B(0.0f);
            e.J_1907_R(0.0f);
            e.u_1723_Y(false);
            e.P_1922_E(false);
        }
        if (this.Q_2552_b && !this.Y_259_p.J_1907_R(190L)) {
            e.n_1700_B(0.0f);
            e.J_1907_R(0.0f);
            e.u_1723_Y(false);
            e.P_1922_E(false);
        }
    }

    private void Y_259_p() {
        this.n_1700_B(this.k_2293_S);
    }

    private void n_1700_B(Boolean forceToChestplate) {
        boolean swapToChestplate = forceToChestplate != null ? forceToChestplate : ElytraHelper.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E).J_1907_R() == Items.NyliumBlock;
        int targetSlot = swapToChestplate ? ElytraHelper.M_182_A() : ElytraHelper.n_1700_B(Items.NyliumBlock);
        if (targetSlot == -1) {
            return;
        }
        ElytraHelper.n_1700_B(targetSlot, 6);
        if (this.vklyuchatUvedomleniyaEnabled.isEnabled().booleanValue()) {
            if (swapToChestplate) {
                v_1900_v.n_1700_B("\u00a7a\u0421\u0432\u0430\u043f\u043d\u0443\u043b \u043d\u0430 \u043d\u0430\u0433\u0440\u0443\u0434\u043d\u0438\u043a", new Object[0]);
                U_3758_B.n_1700_B("J", "\u0421\u0432\u0430\u043f\u043d\u0443\u043b \u043d\u0430 \u043d\u0430\u0433\u0440\u0443\u0434\u043d\u0438\u043a", H_2506_c.n_1700_B(100, 255, 100));
            } else {
                v_1900_v.n_1700_B("\u00a7a\u0421\u0432\u0430\u043f\u043d\u0443\u043b \u043d\u0430 \u044d\u043b\u0438\u0442\u0440\u0443", new Object[0]);
                U_3758_B.n_1700_B("J", "\u0421\u0432\u0430\u043f\u043d\u0443\u043b \u043d\u0430 \u044d\u043b\u0438\u0442\u0440\u0443", H_2506_c.n_1700_B(100, 200, 255));
            }
        }
    }

    private boolean Q_2552_b() {
        return ElytraHelper.c_3005_b.Y_259_p != null && ElytraHelper.c_3005_b.Y_259_p.p_1458_L().n_1700_B(Items.FenceBlock);
    }

    public void h_1847_R() {
        this.P_1922_E(true);
    }

    public void Q_4569_t() {
        this.P_1922_E(false);
    }

    private void P_1922_E(boolean toChestplate) {
        if (ElytraHelper.c_3005_b.Y_259_p == null || ElytraHelper.c_3005_b.w_1457_N == null) {
            return;
        }
        this.k_2293_S = toChestplate;
        if (!this.w_1484_f()) {
            this.n_1700_B(this.k_2293_S);
            this.k_2293_S = null;
            return;
        }
        this.t_1786_h = true;
    }

    public static int M_182_A() {
        if (ElytraHelper.c_3005_b.Y_259_p == null) {
            return -1;
        }
        for (int i = 0; i < ElytraHelper.c_3005_b.Y_259_p.l_1268_F.n_1700_B.size(); ++i) {
            Z_1993_T itemStack = ElytraHelper.c_3005_b.Y_259_p.l_1268_F.n_1700_B.get(i);
            if (itemStack.n_1700_B() || !(itemStack.J_1907_R() instanceof R_2515_i) || ((R_2515_i)itemStack.J_1907_R()).R_4764_Y() != e_1174_E.P_1922_E) continue;
            return i;
        }
        return -1;
    }

    public static void n_1700_B(int fromSlot, int armorSlot) {
        if (ElytraHelper.c_3005_b.Y_259_p == null || ElytraHelper.c_3005_b.w_1457_N == null) {
            return;
        }
        int invFrom = fromSlot < 9 ? fromSlot + 36 : fromSlot;
        ElytraHelper.c_3005_b.w_1457_N.windowClick(0, invFrom, ElytraHelper.c_3005_b.Y_259_p.l_1268_F.G_564_y % 8 + 1, a_408_T.R_4764_Y, ElytraHelper.c_3005_b.Y_259_p);
        ElytraHelper.c_3005_b.w_1457_N.windowClick(0, armorSlot, ElytraHelper.c_3005_b.Y_259_p.l_1268_F.G_564_y % 8 + 1, a_408_T.R_4764_Y, ElytraHelper.c_3005_b.Y_259_p);
        ElytraHelper.c_3005_b.w_1457_N.windowClick(0, invFrom, ElytraHelper.c_3005_b.Y_259_p.l_1268_F.G_564_y % 8 + 1, a_408_T.R_4764_Y, ElytraHelper.c_3005_b.Y_259_p);
    }

    public static int n_1700_B(q_1613_l item) {
        if (c_3005_b == null || ElytraHelper.c_3005_b.Y_259_p == null) {
            return -1;
        }
        for (int i = 0; i < 36; ++i) {
            Z_1993_T stack = ElytraHelper.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (stack.n_1700_B() || stack.J_1907_R() != item) continue;
            return i;
        }
        return -1;
    }

    public static void J_1907_R(q_1613_l item) {
        boolean isInHotbar;
        if (ElytraHelper.c_3005_b.Y_259_p == null || ElytraHelper.c_3005_b.w_1457_N == null) {
            return;
        }
        int slot = ElytraHelper.n_1700_B(item);
        if (slot == -1) {
            return;
        }
        int currentSlot = ElytraHelper.c_3005_b.Y_259_p.l_1268_F.G_564_y;
        boolean bl = isInHotbar = slot < 9;
        if (isInHotbar && slot == currentSlot) {
            ElytraHelper.C_2741_M();
        } else if (isInHotbar) {
            ElytraHelper.R_4764_Y(slot, currentSlot);
        } else {
            int hotbarSlot = currentSlot % 8 + 1;
            ElytraHelper.c_3005_b.w_1457_N.windowClick(0, slot, hotbarSlot, a_408_T.R_4764_Y, ElytraHelper.c_3005_b.Y_259_p);
            ElytraHelper.R_4764_Y(hotbarSlot, currentSlot);
            ElytraHelper.c_3005_b.w_1457_N.windowClick(0, slot, hotbarSlot, a_408_T.R_4764_Y, ElytraHelper.c_3005_b.Y_259_p);
        }
    }

    private static void C_2741_M() {
        if (ElytraHelper.c_3005_b.Y_259_p == null) {
            return;
        }
        if (ElytraHelper.c_3005_b.Y_259_p.Y_601_j() && !ElytraHelper.c_3005_b.Y_259_p.I_1790_n()) {
            ElytraHelper.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.J_1907_R));
        } else {
            ElytraHelper.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.n_1700_B));
        }
    }

    private static void R_4764_Y(int targetSlot, int currentSlot) {
        if (ElytraHelper.c_3005_b.Y_259_p == null) {
            return;
        }
        if (ElytraHelper.c_3005_b.Y_259_p.Y_601_j() && !ElytraHelper.c_3005_b.Y_259_p.I_1790_n()) {
            ElytraHelper.J_1907_R(targetSlot, 45);
            ElytraHelper.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.J_1907_R));
            ElytraHelper.J_1907_R(targetSlot, 45);
        } else {
            ElytraHelper.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(targetSlot));
            ElytraHelper.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.n_1700_B));
            ElytraHelper.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(currentSlot));
        }
    }

    private boolean k_2293_S() {
        if (ElytraHelper.c_3005_b.Y_259_p == null) {
            return false;
        }
        int fireworkSlot = ElytraHelper.n_1700_B(Items.FenceBlock);
        if (fireworkSlot < 0 || fireworkSlot >= 9) {
            return false;
        }
        int currentSlot = ElytraHelper.c_3005_b.Y_259_p.l_1268_F.G_564_y;
        if (currentSlot != fireworkSlot) {
            if (this.q_2307_F <= 0) {
                this.Z_875_P = currentSlot;
            }
            ElytraHelper.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(fireworkSlot));
            ElytraHelper.c_3005_b.Y_259_p.l_1268_F.G_564_y = fireworkSlot;
            this.q_2307_F = 10;
        }
        ElytraHelper.C_2741_M();
        return true;
    }

    public static void J_1907_R(int from, int to) {
        if (from == to) {
            return;
        }
        from = from < 9 ? from + 36 : from;
        ElytraHelper.c_3005_b.w_1457_N.windowClick(ElytraHelper.c_3005_b.Y_259_p.H_1873_g.u_1723_Y, from, 0, a_408_T.R_4764_Y, ElytraHelper.c_3005_b.Y_259_p);
        ElytraHelper.c_3005_b.w_1457_N.windowClick(ElytraHelper.c_3005_b.Y_259_p.H_1873_g.u_1723_Y, to, 0, a_408_T.R_4764_Y, ElytraHelper.c_3005_b.Y_259_p);
        ElytraHelper.c_3005_b.w_1457_N.windowClick(ElytraHelper.c_3005_b.Y_259_p.H_1873_g.u_1723_Y, from, 0, a_408_T.R_4764_Y, ElytraHelper.c_3005_b.Y_259_p);
    }
}



