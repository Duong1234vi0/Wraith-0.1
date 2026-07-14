/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_4481_g;
import lightning.product.NumberSetting;
import lightning.product.MobEffects;
import lightning.product.M_2562_s;
import lightning.product.Flight;
import lightning.product.Q_2753_H;
import lightning.product.T_2915_h;
import lightning.product.T_3952_j;
import lightning.product.W_2770_z;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.a_3742_W;
import lightning.product.ClientboundPlayerPositionPacket;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.i_770_g;
import lightning.product.AirBlock;
import lightning.product.m_2262_U;
import lightning.product.SoulSandBlock;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.q_817_e;
import lightning.product.Material;
import lightning.product.v_887_r;
import lightning.product.ModuleCategory;

public class Strafe
extends Module {
    private final BooleanSetting uskorenieOtUronaEnabled = new BooleanSetting("\u0423\u0441\u043a\u043e\u0440\u0435\u043d\u0438\u0435 \u043e\u0442 \u0443\u0440\u043e\u043d\u0430", false);
    private final NumberSetting skorostUskoreniyaSetting = new NumberSetting("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0443\u0441\u043a\u043e\u0440\u0435\u043d\u0438\u044f", 0.7f, 0.1f, 5.0f, 0.1f, this.uskorenieOtUronaEnabled::isEnabled);
    private final NumberSetting obychnoeUskorenieSetting = new NumberSetting("\u041e\u0431\u044b\u0447\u043d\u043e\u0435 \u0443\u0441\u043a\u043e\u0440\u0435\u043d\u0438\u0435", 3.0f, 1.0f, 10.0f, 1.0f);
    private final W_2770_z s_956_w = new W_2770_z();
    private final i_770_g u_2550_I = new i_770_g();

    public Strafe() {
        super("Strafe", ModuleCategory.J_1907_R);
        this.addSettings(this.uskorenieOtUronaEnabled, this.skorostUskoreniyaSetting, this.obychnoeUskorenieSetting);
    }

    @Y_1740_V
    private void n_1700_B(m_2262_U e) {
        Flight flight = ClientBootstrap.Y_601_j().J_1907_R().M_588_G;
        if (Strafe.c_3005_b.Y_259_p.C_415_h.J_1907_R || flight.w_1484_f() && !flight.v_4262_N.J_1907_R("ReallyWorld Dragon")) {
            return;
        }
        if (this.h_1847_R()) {
            return;
        }
        if (T_3952_j.n_1700_B != this.u_2550_I.G_564_y() || this.u_2550_I.R_4764_Y()) {
            this.u_2550_I.n_1700_B(false);
        }
    }

    @Y_1740_V
    private void n_1700_B(M_2562_s e) {
        Flight flight = ClientBootstrap.Y_601_j().J_1907_R().M_588_G;
        if (Strafe.c_3005_b.Y_259_p.C_415_h.J_1907_R || flight.w_1484_f() && !flight.v_4262_N.J_1907_R("ReallyWorld Dragon")) {
            return;
        }
        if (this.h_1847_R()) {
            return;
        }
        if (this.uskorenieOtUronaEnabled.isEnabled().booleanValue()) {
            this.s_956_w.n_1700_B(700L);
        }
        double speed = this.u_2550_I.n_1700_B(e, this.uskorenieOtUronaEnabled.isEnabled(), this.s_956_w.R_4764_Y(), false, ((Float)this.skorostUskoreniyaSetting.getValue()).floatValue() / 10.0f, Strafe.c_3005_b.Y_259_p.J_1907_R(MobEffects.n_1700_B) && Strafe.c_3005_b.Y_259_p.R_4764_Y(MobEffects.n_1700_B).R_4764_Y() >= 1 ? ((Float)this.obychnoeUskorenieSetting.getValue()).floatValue() * 100.0f : 0.0255f);
        this.n_1700_B(e, speed);
    }

    @Y_1740_V
    public void n_1700_B(q_817_e e) {
        Flight flight = ClientBootstrap.Y_601_j().J_1907_R().M_588_G;
        if (Strafe.c_3005_b.Y_259_p.C_415_h.J_1907_R || flight.w_1484_f() && !flight.v_4262_N.J_1907_R("ReallyWorld Dragon")) {
            return;
        }
        e.n_1700_B(true);
    }

    @Y_1740_V
    private void n_1700_B(I_4481_g e) {
        Flight flight = ClientBootstrap.Y_601_j().J_1907_R().M_588_G;
        if (Strafe.c_3005_b.Y_259_p.C_415_h.J_1907_R || flight.w_1484_f() && !flight.v_4262_N.J_1907_R("ReallyWorld Dragon")) {
            return;
        }
        if (this.h_1847_R()) {
            return;
        }
        this.u_2550_I.n_1700_B(e.J_1907_R());
    }

    @Y_1740_V
    private void n_1700_B(Q_2753_H e) {
        Flight flight = ClientBootstrap.Y_601_j().J_1907_R().M_588_G;
        if (Strafe.c_3005_b.Y_259_p.C_415_h.J_1907_R || flight.w_1484_f() && !flight.v_4262_N.J_1907_R("ReallyWorld Dragon")) {
            return;
        }
        if (this.h_1847_R()) {
            return;
        }
        if (e.G_564_y() instanceof ClientboundPlayerPositionPacket) {
            this.u_2550_I.J_1907_R(0.0);
        }
        if (this.uskorenieOtUronaEnabled.isEnabled().booleanValue()) {
            this.s_956_w.n_1700_B(e);
        }
    }

    @Y_1740_V
    private void n_1700_B(v_887_r e) {
        Flight flight = ClientBootstrap.Y_601_j().J_1907_R().M_588_G;
        if (Strafe.c_3005_b.Y_259_p.C_415_h.J_1907_R || flight.w_1484_f() && !flight.v_4262_N.J_1907_R("ReallyWorld Dragon")) {
            return;
        }
        if (this.h_1847_R()) {
            return;
        }
        if (this.uskorenieOtUronaEnabled.isEnabled().booleanValue()) {
            this.s_956_w.n_1700_B(e);
        }
    }

    private boolean h_1847_R() {
        if (Strafe.c_3005_b.Y_259_p == null || Strafe.c_3005_b.Y_601_j == null) {
            return true;
        }
        if (Strafe.c_3005_b.Y_259_p.q_2307_F() || Strafe.c_3005_b.Y_259_p.k_578_l() || Strafe.c_3005_b.Y_259_p.RowButton() || Strafe.c_3005_b.Y_259_p.W_3464_O()) {
            return true;
        }
        c_1514_x pos = new c_1514_x(Strafe.c_3005_b.Y_259_p.s_4990_V());
        T_2915_h blockBelow = Strafe.c_3005_b.Y_601_j.getBlockState(pos.down()).J_1907_R();
        T_2915_h blockAbove = Strafe.c_3005_b.Y_601_j.getBlockState(pos.up()).J_1907_R();
        Material material = Strafe.c_3005_b.Y_601_j.getBlockState(pos).R_4764_Y();
        return blockAbove instanceof AirBlock && blockBelow == a_3742_W.c_3005_b || material == Material.M_182_A || blockBelow instanceof SoulSandBlock;
    }

    private void n_1700_B(M_2562_s move, double motion) {
        double forward = Strafe.c_3005_b.Y_259_p.G_564_y.moveForward;
        double strafe = Strafe.c_3005_b.Y_259_p.G_564_y.moveStrafe;
        float yaw = Strafe.c_3005_b.Y_259_p.p_178_J;
        if (forward != 0.0) {
            if (strafe > 0.0) {
                yaw += forward > 0.0 ? -45.0f : 45.0f;
            } else if (strafe < 0.0) {
                yaw += forward > 0.0 ? 45.0f : -45.0f;
            }
            strafe = 0.0;
            forward = Math.signum(forward);
        }
        double rad = Math.toRadians(yaw + 90.0f);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);
        double newX = forward * motion * cos + strafe * motion * sin;
        double newZ = forward * motion * sin - strafe * motion * cos;
        move.R_4764_Y(new e_2866_D(newX, move.R_4764_Y().R_4764_Y, newZ));
    }
}



