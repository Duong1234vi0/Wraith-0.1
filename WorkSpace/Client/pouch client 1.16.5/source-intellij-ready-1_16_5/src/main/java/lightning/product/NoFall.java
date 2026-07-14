/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ElytraHelper;
import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.HitResult;
import lightning.product.N_3268_u;
import lightning.product.T_3952_j;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.a_178_J;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.ElytraItem;
import lightning.product.h_1015_G;
import lightning.product.k_4690_i;
import lightning.product.m_2262_U;
import lightning.product.ModeSetting;
import lightning.product.Items;
import lightning.product.u_1934_K;
import lightning.product.v_887_r;
import lightning.product.x_1688_C;
import lightning.product.ModuleCategory;

public class NoFall
extends Module {
    private final ModeSetting modMode = new ModeSetting("\u041c\u043e\u0434", "GrimJump", "GrimJump", "MLG", "GrimFlag", "Hay Bale", "Elytra");
    private boolean w_1484_f;
    private boolean t_148_a;

    public NoFall() {
        super("NoFall", ModuleCategory.J_1907_R);
        this.addSettings(this.modMode);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (NoFall.c_3005_b.Y_259_p == null || NoFall.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.modMode.isMode("GrimJump")) {
            boolean bl = this.w_1484_f = !NoFall.c_3005_b.Y_259_p.M_1641_O() && NoFall.c_3005_b.Y_259_p.U_1241_n > 2.5f;
        }
        if (this.modMode.isMode("Elytra")) {
            if (NoFall.c_3005_b.Y_259_p.U_1241_n <= 3.0f) {
                return;
            }
            if (NoFall.c_3005_b.Y_259_p.k_578_l()) {
                return;
            }
            int elytraSlot = ElytraHelper.n_1700_B(Items.NyliumBlock);
            if (elytraSlot == -1) {
                return;
            }
            if (NoFall.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E).J_1907_R() != Items.NyliumBlock) {
                int chestPlateSlot = ElytraHelper.M_182_A();
                if (chestPlateSlot != -1) {
                    ElytraHelper.n_1700_B(chestPlateSlot, 6);
                } else {
                    ElytraHelper.n_1700_B(elytraSlot, 6);
                }
            }
            if (NoFall.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E).J_1907_R() == Items.NyliumBlock && ElytraItem.G_564_y(NoFall.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E))) {
                NoFall.c_3005_b.Y_259_p.y_2447_C();
                NoFall.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new T_3952_j(NoFall.c_3005_b.Y_259_p, T_3952_j.n_1700_B.t_148_a));
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(m_2262_U event) {
        if (NoFall.c_3005_b.Y_259_p == null) {
            return;
        }
        if (this.modMode.isMode("GrimJump")) {
            if (!this.w_1484_f || !NoFall.c_3005_b.Y_259_p.M_1641_O()) {
                return;
            }
            event.n_1700_B(true);
            NoFall.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new N_3268_u(true));
            this.w_1484_f = false;
            return;
        }
        if (this.modMode.isMode("GrimFlag")) {
            event.J_1907_R(false);
            if (NoFall.c_3005_b.P_4830_p.k_3961_g.G_564_y()) {
                double add = NoFall.c_3005_b.Y_259_p.RealmsWorldResetDto % 2 != 1 ? (double)0.08f : (double)0.05f;
                NoFall.c_3005_b.Y_259_p.h_1847_R(NoFall.c_3005_b.Y_259_p.I_4348_c().J_1907_R, NoFall.c_3005_b.Y_259_p.I_4348_c().R_4764_Y + add, NoFall.c_3005_b.Y_259_p.I_4348_c().G_564_y);
            }
            return;
        }
        if (this.modMode.isMode("MLG")) {
            if (NoFall.c_3005_b.Y_259_p.U_1241_n <= 5.0f) {
                return;
            }
            e_2866_D start = NoFall.c_3005_b.Y_259_p.s_4990_V();
            e_2866_D end = start.J_1907_R(0.0, -15.0, 0.0);
            BlockHitResult result = NoFall.c_3005_b.Y_601_j.n_1700_B(new ClipContext(start, end, ClipContext.n_1700_B.n_1700_B, ClipContext.J_1907_R.n_1700_B, NoFall.c_3005_b.Y_259_p));
            int waterSlot = u_1934_K.n_1700_B(Items.W_2770_z);
            if (result != null && result.R_4764_Y() == HitResult.n_1700_B.J_1907_R && waterSlot >= 0) {
                int oldSlot = NoFall.c_3005_b.Y_259_p.l_1268_F.G_564_y;
                event.J_1907_R(86.0f);
                NoFall.c_3005_b.Y_259_p.l_1268_F.G_564_y = waterSlot;
                NoFall.c_3005_b.w_1457_N.processRightClick(NoFall.c_3005_b.Y_259_p, NoFall.c_3005_b.Y_601_j, x_1688_C.n_1700_B);
                NoFall.c_3005_b.Y_259_p.l_1268_F.G_564_y = oldSlot;
            }
            return;
        }
        if (this.modMode.isMode("Hay Bale")) {
            if (NoFall.c_3005_b.Y_259_p.U_1241_n <= 5.0f) {
                return;
            }
            e_2866_D start = NoFall.c_3005_b.Y_259_p.s_4990_V();
            e_2866_D end = start.J_1907_R(0.0, -15.0, 0.0);
            BlockHitResult result = NoFall.c_3005_b.Y_601_j.n_1700_B(new ClipContext(start, end, ClipContext.n_1700_B.n_1700_B, ClipContext.J_1907_R.n_1700_B, NoFall.c_3005_b.Y_259_p));
            int haySlot = u_1934_K.n_1700_B(Items.AntiSurround);
            if (result != null && result.R_4764_Y() == HitResult.n_1700_B.J_1907_R && haySlot >= 0) {
                int oldSlot = NoFall.c_3005_b.Y_259_p.l_1268_F.G_564_y;
                event.J_1907_R(86.0f);
                NoFall.c_3005_b.Y_259_p.l_1268_F.G_564_y = haySlot;
                if (NoFall.c_3005_b.Y_601_j instanceof k_4690_i) {
                    NoFall.c_3005_b.w_1457_N.func_217292_a(NoFall.c_3005_b.Y_259_p, NoFall.c_3005_b.Y_601_j, x_1688_C.n_1700_B, result);
                }
                NoFall.c_3005_b.Y_259_p.l_1268_F.G_564_y = oldSlot;
            }
            return;
        }
    }

    @Y_1740_V
    public void n_1700_B(v_887_r event) {
        if (!this.modMode.isMode("GrimJump")) {
            return;
        }
        if (event.J_1907_R() == v_887_r.n_1700_B.n_1700_B) {
            this.t_148_a = true;
        }
    }

    @Y_1740_V
    public void n_1700_B(a_178_J event) {
        if (!this.modMode.isMode("GrimJump")) {
            return;
        }
        if (NoFall.c_3005_b.Y_259_p == null || !this.t_148_a) {
            return;
        }
        event.P_1922_E(true);
        this.t_148_a = false;
    }
}



