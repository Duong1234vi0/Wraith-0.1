/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_4074_S;
import lightning.product.WoolCarpetBlock;
import lightning.product.T_2915_h;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.a_178_J;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.AirBlock;
import lightning.product.m_2262_U;
import lightning.product.BooleanSetting;
import lightning.product.Material;
import lightning.product.u_925_K;
import lightning.product.ModuleCategory;
import lightning.product.y_3008_A;
import lightning.product.z_2909_G;

public class MoveHelper
extends Module {
    private final BooleanSetting nezametnyePryzhkiNaStupenkahEnabled = new BooleanSetting("\u041d\u0435\u0437\u0430\u043c\u0435\u0442\u043d\u044b\u0435 \u043f\u0440\u044b\u0436\u043a\u0438 \u043d\u0430 \u0441\u0442\u0443\u043f\u0435\u043d\u044c\u043a\u0430\u0445", true);
    private final BooleanSetting prygatNaKrayuBlokaEnabled = new BooleanSetting("\u041f\u0440\u044b\u0433\u0430\u0442\u044c \u043d\u0430 \u043a\u0440\u0430\u044e \u0431\u043b\u043e\u043a\u0430", false);
    private final BooleanSetting prisedatNaKrayuBlokaEnabled = new BooleanSetting("\u041f\u0440\u0438\u0441\u0435\u0434\u0430\u0442\u044c \u043d\u0430 \u043a\u0440\u0430\u044e \u0431\u043b\u043e\u043a\u0430", false);
    private final BooleanSetting avtoPrisedanieVNizkihProhodahEnabled = new BooleanSetting("\u0410\u0432\u0442\u043e \u043f\u0440\u0438\u0441\u0435\u0434\u0430\u043d\u0438\u0435 \u0432 \u043d\u0438\u0437\u043a\u0438\u0445 \u043f\u0440\u043e\u0445\u043e\u0434\u0430\u0445", false);
    private final BooleanSetting uskorenieNaLduEnabled = new BooleanSetting("\u0423\u0441\u043a\u043e\u0440\u0435\u043d\u0438\u0435 \u043d\u0430 \u043b\u044c\u0434\u0443", false);
    private final BooleanSetting pryzhkiNaSlaymBlokahEnabled = new BooleanSetting("\u041f\u0440\u044b\u0436\u043a\u0438 \u043d\u0430 \u0441\u043b\u0430\u0439\u043c \u0431\u043b\u043e\u043a\u0430\u0445", false);
    private boolean P_4830_p;

    public MoveHelper() {
        super("MoveHelper", ModuleCategory.J_1907_R);
        this.addSettings(this.nezametnyePryzhkiNaStupenkahEnabled, this.prygatNaKrayuBlokaEnabled, this.prisedatNaKrayuBlokaEnabled, this.avtoPrisedanieVNizkihProhodahEnabled, this.uskorenieNaLduEnabled, this.pryzhkiNaSlaymBlokahEnabled);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        c_1514_x belowPos;
        K_4074_S belowState;
        if (MoveHelper.c_3005_b.Y_259_p == null || MoveHelper.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.nezametnyePryzhkiNaStupenkahEnabled.isEnabled().booleanValue() && u_925_K.n_1700_B() && !MoveHelper.c_3005_b.Y_259_p.Z_875_P() && this.h_1847_R() && MoveHelper.c_3005_b.Y_259_p.M_1641_O() && !MoveHelper.c_3005_b.P_4830_p.Ping.G_564_y()) {
            MoveHelper.c_3005_b.Y_259_p.e_837_t();
        }
        if (this.prygatNaKrayuBlokaEnabled.isEnabled().booleanValue() && u_925_K.n_1700_B(0.001f) && MoveHelper.c_3005_b.Y_259_p.M_1641_O()) {
            MoveHelper.c_3005_b.Y_259_p.e_837_t();
        }
        if (this.pryzhkiNaSlaymBlokahEnabled.isEnabled().booleanValue() && MoveHelper.c_3005_b.Y_259_p.M_1641_O() && (belowState = MoveHelper.c_3005_b.Y_601_j.getBlockState(belowPos = new c_1514_x(MoveHelper.c_3005_b.Y_259_p.s_4990_V()).down())).J_1907_R() == a_3742_W.g_4841_c && u_925_K.n_1700_B()) {
            MoveHelper.c_3005_b.Y_259_p.e_837_t();
        }
    }

    @Y_1740_V
    public void n_1700_B(m_2262_U e) {
        T_2915_h block;
        K_4074_S belowState;
        c_1514_x belowPos;
        if (MoveHelper.c_3005_b.Y_259_p == null || MoveHelper.c_3005_b.Y_601_j == null) {
            return;
        }
        if (MoveHelper.c_3005_b.Y_259_p.M_1641_O()) {
            belowPos = new c_1514_x(MoveHelper.c_3005_b.Y_259_p.s_4990_V()).add(0, -1, 0);
            belowState = MoveHelper.c_3005_b.Y_601_j.getBlockState(belowPos);
            if (this.n_1700_B(belowState)) {
                if (!this.P_4830_p) {
                    this.P_4830_p = true;
                }
            } else if (this.P_4830_p) {
                this.P_4830_p = false;
            }
        } else if (this.P_4830_p) {
            this.P_4830_p = false;
        }
        if (this.uskorenieNaLduEnabled.isEnabled().booleanValue() && MoveHelper.c_3005_b.Y_259_p.M_1641_O() && u_925_K.n_1700_B() && ((block = (belowState = MoveHelper.c_3005_b.Y_601_j.getBlockState(belowPos = new c_1514_x(MoveHelper.c_3005_b.Y_259_p.s_4990_V()).down())).J_1907_R()) == a_3742_W.O_1795_e || block == a_3742_W.ServerHelper || block == a_3742_W.G_4691_Q)) {
            double speed = 0.3;
            if (block == a_3742_W.ServerHelper) {
                speed = 0.35;
            } else if (block == a_3742_W.G_4691_Q) {
                speed = 0.4;
            }
            e_2866_D motion = MoveHelper.c_3005_b.Y_259_p.I_4348_c();
            double forward = MoveHelper.c_3005_b.Y_259_p.G_564_y.moveForward;
            double strafe = MoveHelper.c_3005_b.Y_259_p.G_564_y.moveStrafe;
            if (forward != 0.0 || strafe != 0.0) {
                double z;
                double yaw = Math.toRadians(MoveHelper.c_3005_b.Y_259_p.p_178_J);
                double x = -Math.sin(yaw) * forward + Math.cos(yaw) * strafe;
                double len = Math.sqrt(x * x + (z = Math.cos(yaw) * forward + Math.sin(yaw) * strafe) * z);
                if (len > 0.0) {
                    x /= len;
                    z /= len;
                }
                MoveHelper.c_3005_b.Y_259_p.h_1847_R(x * speed, motion.R_4764_Y, z * speed);
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(a_178_J event) {
        if (this.prisedatNaKrayuBlokaEnabled.isEnabled().booleanValue() && !MoveHelper.c_3005_b.P_4830_p.p_178_J.G_564_y()) {
            event.u_1723_Y(this.P_4830_p);
        }
        if (this.avtoPrisedanieVNizkihProhodahEnabled.isEnabled().booleanValue() && !MoveHelper.c_3005_b.P_4830_p.p_178_J.G_564_y() && this.Q_4569_t()) {
            event.u_1723_Y(true);
        }
    }

    private boolean h_1847_R() {
        e_2866_D playerPos = MoveHelper.c_3005_b.Y_259_p.s_4990_V();
        double yaw = Math.toRadians(MoveHelper.c_3005_b.Y_259_p.p_178_J);
        double x = -Math.sin(yaw);
        double z = Math.cos(yaw);
        c_1514_x playerBlockPos = new c_1514_x(playerPos.J_1907_R, playerPos.R_4764_Y, playerPos.G_564_y);
        c_1514_x frontPos = new c_1514_x(playerPos.J_1907_R + x, playerPos.R_4764_Y, playerPos.G_564_y + z);
        c_1514_x frontUpPos = frontPos.up();
        c_1514_x belowPos = playerBlockPos.down();
        boolean isOnStairsOrSlab = MoveHelper.c_3005_b.Y_601_j.getBlockState(belowPos).J_1907_R() instanceof z_2909_G || MoveHelper.c_3005_b.Y_601_j.getBlockState(belowPos).J_1907_R() instanceof y_3008_A;
        boolean isFrontClimbable = MoveHelper.c_3005_b.Y_601_j.getBlockState(frontPos).J_1907_R() instanceof z_2909_G || MoveHelper.c_3005_b.Y_601_j.getBlockState(frontPos).J_1907_R() instanceof y_3008_A || MoveHelper.c_3005_b.Y_601_j.getBlockState(frontUpPos).J_1907_R() instanceof z_2909_G || MoveHelper.c_3005_b.Y_601_j.getBlockState(frontUpPos).J_1907_R() instanceof y_3008_A;
        return isOnStairsOrSlab && isFrontClimbable;
    }

    private boolean n_1700_B(K_4074_S state) {
        return state.J_1907_R() instanceof AirBlock || state.R_4764_Y() == Material.v_4262_N || state.R_4764_Y() == Material.P_1922_E || state.R_4764_Y() == Material.s_956_w || state.R_4764_Y() == Material.M_588_G || state.J_1907_R() == a_3742_W.X_290_I || state.J_1907_R() instanceof WoolCarpetBlock || state.J_1907_R() instanceof y_3008_A || state.u_2550_I(MoveHelper.c_3005_b.Y_601_j, new c_1514_x(MoveHelper.c_3005_b.Y_259_p.s_4990_V()).add(0, -1, 0)).J_1907_R();
    }

    private boolean Q_4569_t() {
        e_2866_D playerPos = MoveHelper.c_3005_b.Y_259_p.s_4990_V();
        c_1514_x headPos = new c_1514_x(playerPos.J_1907_R, playerPos.R_4764_Y + 1.5, playerPos.G_564_y);
        K_4074_S headState = MoveHelper.c_3005_b.Y_601_j.getBlockState(headPos);
        return !headState.v_4262_N() && !headState.u_2550_I(MoveHelper.c_3005_b.Y_601_j, headPos).J_1907_R();
    }

    private c_1514_x M_182_A() {
        e_2866_D playerPos = MoveHelper.c_3005_b.Y_259_p.s_4990_V();
        double yaw = Math.toRadians(MoveHelper.c_3005_b.Y_259_p.p_178_J);
        double x = -Math.sin(yaw);
        double z = Math.cos(yaw);
        return new c_1514_x(playerPos.J_1907_R + x, playerPos.R_4764_Y, playerPos.G_564_y + z);
    }
}



