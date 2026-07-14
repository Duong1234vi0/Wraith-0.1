/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ElytraHelper;
import lightning.product.F_747_P;
import lightning.product.T_3952_j;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.ElytraItem;
import lightning.product.h_1015_G;
import lightning.product.m_2262_U;
import lightning.product.BooleanSetting;
import lightning.product.ModeSetting;
import lightning.product.Items;
import lightning.product.ModuleCategory;

public class ElytraJump
extends Module {
    private final ModeSetting rezhimMode = new ModeSetting("\u0420\u0435\u0436\u0438\u043c", "BravoFFA", "BravoFFA", "ReallyWorld", "LonyGrief");
    private final BooleanSetting svapatElitruEnabled = new BooleanSetting("\u0421\u0432\u0430\u043f\u0430\u0442\u044c \u044d\u043b\u0438\u0442\u0440\u0443", false);
    private boolean t_148_a = false;
    private float s_956_w = 0.0f;

    public ElytraJump() {
        super("ElytraJump", ModuleCategory.J_1907_R);
        this.addSettings(this.rezhimMode, this.svapatElitruEnabled);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (ElytraJump.c_3005_b.Y_259_p == null) {
            return;
        }
        if (!(ElytraJump.c_3005_b.Y_259_p.C_415_h.J_1907_R || !ElytraJump.c_3005_b.Y_259_p.M_1641_O() || ElytraJump.c_3005_b.Y_259_p.RowButton() || ElytraJump.c_3005_b.Y_259_p.W_3464_O() || ElytraJump.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E).J_1907_R() != Items.NyliumBlock || ElytraJump.c_3005_b.P_4830_p.Ping.G_564_y())) {
            ElytraJump.c_3005_b.Y_259_p.e_837_t();
        }
        if (!(ElytraJump.c_3005_b.Y_259_p.C_415_h.J_1907_R || ElytraJump.c_3005_b.Y_259_p.M_1641_O() || ElytraJump.c_3005_b.Y_259_p.RowButton() || ElytraJump.c_3005_b.Y_259_p.k_578_l() || ElytraJump.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E).J_1907_R() != Items.NyliumBlock || !ElytraItem.G_564_y(ElytraJump.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E)))) {
            ElytraJump.c_3005_b.Y_259_p.y_2447_C();
            ElytraJump.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new T_3952_j(ElytraJump.c_3005_b.Y_259_p, T_3952_j.n_1700_B.t_148_a));
        }
        if (ElytraJump.c_3005_b.Y_259_p.M_1641_O() || ElytraJump.c_3005_b.Y_259_p.RowButton() || ElytraJump.c_3005_b.Y_259_p.W_3464_O()) {
            ElytraJump.c_3005_b.P_4830_p.Ping.n_1700_B(false);
            this.t_148_a = false;
        }
        if (ElytraJump.c_3005_b.Y_259_p.RealmsLongRunningMcoTaskScreen > 0 && this.svapatElitruEnabled.isEnabled().booleanValue() && ElytraJump.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E).J_1907_R() == Items.NyliumBlock) {
            this.Q_4569_t();
            return;
        }
        int elytraSlot = ElytraHelper.n_1700_B(Items.NyliumBlock);
        if (ElytraJump.c_3005_b.Y_259_p.G_564_y != null && elytraSlot >= 0 && (elytraSlot < 9 ? elytraSlot + 36 : elytraSlot) == 38 && ElytraJump.c_3005_b.Y_259_p.G_564_y.jump && !ElytraJump.c_3005_b.Y_259_p.k_578_l()) {
            ElytraJump.c_3005_b.Y_259_p.G_564_y.jump = false;
        }
        if (ElytraJump.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E).J_1907_R() == Items.NyliumBlock) {
            ElytraJump.c_3005_b.P_4830_p.Ping.n_1700_B(true);
            if (ElytraJump.c_3005_b.Y_259_p.k_578_l()) {
                e_2866_D motion = ElytraJump.c_3005_b.Y_259_p.I_4348_c();
                if (this.rezhimMode.isMode("ReallyWorld")) {
                    ElytraJump.c_3005_b.Y_259_p.h_1847_R(motion.J_1907_R, motion.R_4764_Y + 0.02658, motion.G_564_y);
                } else if (this.rezhimMode.isMode("BravoFFA") || this.rezhimMode.isMode("LonyGrief")) {
                    ElytraJump.c_3005_b.Y_259_p.h_1847_R(motion.J_1907_R, motion.R_4764_Y + (double)F_747_P.G_564_y(0.06f, 0.061f), motion.G_564_y);
                }
            }
        } else if (this.svapatElitruEnabled.isEnabled().booleanValue()) {
            this.h_1847_R();
        } else {
            ElytraJump.c_3005_b.P_4830_p.Ping.n_1700_B(false);
            this.t_148_a = false;
        }
    }

    @Y_1740_V
    public void n_1700_B(m_2262_U e) {
        if (ElytraJump.c_3005_b.Y_259_p == null || !ElytraJump.c_3005_b.Y_259_p.k_578_l()) {
            this.t_148_a = false;
            return;
        }
        if (!this.t_148_a) {
            this.t_148_a = true;
            this.s_956_w = ElytraJump.c_3005_b.Y_259_p.p_178_J;
        }
        float fixedPitch = 10.0f;
        e.n_1700_B(this.s_956_w);
        e.J_1907_R(fixedPitch);
        ElytraJump.c_3005_b.Y_259_p.p_178_J = this.s_956_w;
        ElytraJump.c_3005_b.Y_259_p.f_3449_S = this.s_956_w;
        ElytraJump.c_3005_b.Y_259_p.C_1162_e = this.s_956_w;
        ElytraJump.c_3005_b.Y_259_p.f_4016_n = fixedPitch;
    }

    private void h_1847_R() {
        int slot = ElytraHelper.n_1700_B(Items.NyliumBlock);
        if (slot >= 0) {
            ElytraHelper.n_1700_B(slot, 6);
        }
    }

    private void Q_4569_t() {
        int slot = ElytraHelper.M_182_A();
        if (slot >= 0) {
            ElytraHelper.n_1700_B(slot, 6);
        }
    }

    @Override
    public void onEnable() {
        if (this.svapatElitruEnabled.isEnabled().booleanValue() && ElytraJump.c_3005_b.Y_259_p != null) {
            this.h_1847_R();
        }
        super.onEnable();
    }

    @Override
    public void onDisable() {
        ElytraJump.c_3005_b.P_4830_p.Ping.n_1700_B(false);
        if (this.svapatElitruEnabled.isEnabled().booleanValue() && ElytraJump.c_3005_b.Y_259_p != null && ElytraJump.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E).J_1907_R() == Items.NyliumBlock) {
            this.Q_4569_t();
        }
        super.onDisable();
    }
}



