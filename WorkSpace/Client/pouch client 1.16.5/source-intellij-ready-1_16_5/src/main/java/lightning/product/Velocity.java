/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.NumberSetting;
import lightning.product.Q_2753_H;
import lightning.product.W_2770_z;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.a_178_J;
import lightning.product.FreeCam;
import lightning.product.e_2866_D;
import lightning.product.ClientboundExplodePacket;
import lightning.product.h_1015_G;
import lightning.product.ClientboundSetEntityMotionPacket;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.ModeSetting;
import lightning.product.Packet;
import lightning.product.u_530_F;
import lightning.product.v_887_r;
import lightning.product.ModuleCategory;

public class Velocity
extends Module {
    private final ModeSetting modMode = new ModeSetting("\u041c\u043e\u0434", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "\u041b\u0435\u0433\u0438\u0442", "Reverse", "Tick", "Grim");
    private final BooleanSetting prygatEnabled = new BooleanSetting("\u041f\u0440\u044b\u0433\u0430\u0442\u044c", true, () -> this.modMode.isMode("\u041b\u0435\u0433\u0438\u0442"));
    private final NumberSetting silaReversaSetting = new NumberSetting("\u0421\u0438\u043b\u0430 \u0440\u0435\u0432\u0435\u0440\u0441\u0430", 0.5f, 0.1f, 1.0f, 0.1f, () -> this.modMode.isMode("Reverse"));
    private final BooleanSetting vertikalnyyReversEnabled = new BooleanSetting("\u0412\u0435\u0440\u0442\u0438\u043a\u0430\u043b\u044c\u043d\u044b\u0439 \u0440\u0435\u0432\u0435\u0440\u0441", false, () -> this.modMode.isMode("Reverse"));
    private final NumberSetting intervalTikovSetting = new NumberSetting("\u0418\u043d\u0442\u0435\u0440\u0432\u0430\u043b \u0442\u0438\u043a\u043e\u0432", 2.0f, 1.0f, 5.0f, 1.0f, () -> this.modMode.isMode("Tick"));
    private final BooleanSetting otmenyatVertikalnyyEnabled = new BooleanSetting("\u041e\u0442\u043c\u0435\u043d\u044f\u0442\u044c \u0432\u0435\u0440\u0442\u0438\u043a\u0430\u043b\u044c\u043d\u044b\u0439", true, () -> this.modMode.isMode("Tick"));
    private final BooleanSetting pryzhokEnabled = new BooleanSetting("\u041f\u0440\u044b\u0436\u043e\u043a", true, () -> this.modMode.isMode("Grim"));
    private final BooleanSetting streyfEnabled = new BooleanSetting("\u0421\u0442\u0440\u0435\u0439\u0444", true, () -> this.modMode.isMode("Grim"));
    private final NumberSetting dlitelnostSetting = new NumberSetting("\u0414\u043b\u0438\u0442\u0435\u043b\u044c\u043d\u043e\u0441\u0442\u044c", 6.0f, 1.0f, 12.0f, 1.0f, () -> this.modMode.isMode("Grim"));
    private e_2866_D M_182_A = e_2866_D.n_1700_B;
    private boolean t_1786_h = false;
    private final W_2770_z multiplayerClientSuggestionProvider = new W_2770_z();
    private int w_1457_N = 0;

    public Velocity() {
        super("Velocity", ModuleCategory.n_1700_B);
        this.addSettings(this.modMode, this.prygatEnabled, this.silaReversaSetting, this.vertikalnyyReversEnabled, this.intervalTikovSetting, this.otmenyatVertikalnyyEnabled, this.pryzhokEnabled, this.streyfEnabled, this.dlitelnostSetting);
    }

    @Override
    public void onDisable() {
        this.M_182_A = e_2866_D.n_1700_B;
        this.t_1786_h = false;
        this.w_1457_N = 0;
        super.onDisable();
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
        if (Velocity.c_3005_b.Y_259_p == null || !e.J_1907_R()) {
            return;
        }
        if (this.modMode.isMode("Grim")) {
            ClientboundExplodePacket explosion;
            e_2866_D expVel;
            ClientboundSetEntityMotionPacket p;
            Packet<?> t_3138_Z2 = e.G_564_y();
            if (t_3138_Z2 instanceof ClientboundSetEntityMotionPacket && (p = (ClientboundSetEntityMotionPacket)t_3138_Z2).J_1907_R() == Velocity.c_3005_b.Y_259_p.j_276_v()) {
                this.M_182_A = new e_2866_D((double)p.R_4764_Y() / 8000.0, (double)p.G_564_y() / 8000.0, (double)p.P_1922_E() / 8000.0);
                this.t_1786_h = true;
                this.w_1457_N = 0;
            }
            if ((t_3138_Z2 = e.G_564_y()) instanceof ClientboundExplodePacket && (expVel = new e_2866_D((explosion = (ClientboundExplodePacket)t_3138_Z2).J_1907_R(), explosion.R_4764_Y(), explosion.G_564_y())).v_4262_N() > 0.0) {
                this.M_182_A = expVel;
                this.t_1786_h = true;
                this.w_1457_N = 0;
            }
            return;
        }
        Packet<?> expVel = e.G_564_y();
        if (!(expVel instanceof ClientboundSetEntityMotionPacket)) {
            return;
        }
        ClientboundSetEntityMotionPacket p = (ClientboundSetEntityMotionPacket)expVel;
        if (p.J_1907_R() != Velocity.c_3005_b.Y_259_p.j_276_v()) {
            return;
        }
        if (this.modMode.isMode("\u041e\u0431\u044b\u0447\u043d\u044b\u0439")) {
            e.n_1700_B(true);
        }
        if (this.modMode.isMode("\u041b\u0435\u0433\u0438\u0442")) {
            this.multiplayerClientSuggestionProvider.n_1700_B(700L);
            if (this.multiplayerClientSuggestionProvider.R_4764_Y()) {
                this.M_182_A = new e_2866_D(p.R_4764_Y(), p.G_564_y(), p.P_1922_E());
            }
            this.multiplayerClientSuggestionProvider.n_1700_B(e);
        }
        if (this.modMode.isMode("Reverse")) {
            e.n_1700_B(true);
            double motionX = (double)p.R_4764_Y() / 8000.0;
            double motionY = (double)p.G_564_y() / 8000.0;
            double motionZ = (double)p.P_1922_E() / 8000.0;
            float strength = ((Float)this.silaReversaSetting.getValue()).floatValue();
            Velocity.c_3005_b.Y_259_p.s_956_w(-motionX * (double)strength, this.vertikalnyyReversEnabled.isEnabled() != false ? -motionY * (double)strength : motionY * 0.5, -motionZ * (double)strength);
        }
        if (this.modMode.isMode("Tick")) {
            ++this.w_1457_N;
            int interval = ((Float)this.intervalTikovSetting.getValue()).intValue();
            if (this.w_1457_N % interval == 0) {
                e.n_1700_B(true);
            } else {
                double motionX = (double)p.R_4764_Y() / 8000.0;
                double motionY = (double)p.G_564_y() / 8000.0;
                double motionZ = (double)p.P_1922_E() / 8000.0;
                Velocity.c_3005_b.Y_259_p.s_956_w(motionX * 0.3, this.otmenyatVertikalnyyEnabled.isEnabled() != false ? 0.0 : motionY * 0.5, motionZ * 0.3);
                e.n_1700_B(true);
            }
        }
    }

    @Y_1740_V
    private void n_1700_B(v_887_r e) {
        if (this.modMode.isMode("\u041b\u0435\u0433\u0438\u0442")) {
            this.multiplayerClientSuggestionProvider.n_1700_B(e);
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (!this.t_1786_h || Velocity.c_3005_b.Y_259_p == null || !this.modMode.isMode("Grim")) {
            return;
        }
        ++this.w_1457_N;
        if (this.w_1457_N > ((Float)this.dlitelnostSetting.getValue()).intValue()) {
            this.t_1786_h = false;
        }
    }

    @Y_1740_V
    public void n_1700_B(a_178_J e) {
        if (this.modMode.isMode("Grim")) {
            if (!this.t_1786_h || Velocity.c_3005_b.Y_259_p == null) {
                return;
            }
            if (Velocity.c_3005_b.Y_259_p.RealmsLongRunningMcoTaskScreen <= 0 && this.w_1457_N > 2) {
                this.t_1786_h = false;
                return;
            }
            double hLenSq = this.M_182_A.J_1907_R * this.M_182_A.J_1907_R + this.M_182_A.G_564_y * this.M_182_A.G_564_y;
            if (hLenSq < 0.001) {
                return;
            }
            if (this.streyfEnabled.isEnabled().booleanValue()) {
                double relativeAngle = this.h_1847_R();
                float forward = 0.0f;
                float strafe = 0.0f;
                if (relativeAngle > -45.0 && relativeAngle < 45.0) {
                    forward = 1.0f;
                } else if (relativeAngle >= 45.0 && relativeAngle <= 135.0) {
                    strafe = -1.0f;
                } else if (relativeAngle <= -45.0 && relativeAngle >= -135.0) {
                    strafe = 1.0f;
                } else {
                    forward = -1.0f;
                }
                e.n_1700_B(forward);
                e.J_1907_R(strafe);
            }
            if (this.pryzhokEnabled.isEnabled().booleanValue() && Velocity.c_3005_b.Y_259_p.M_1641_O()) {
                e.P_1922_E(true);
            }
            return;
        }
        if (!this.modMode.isMode("\u041b\u0435\u0433\u0438\u0442")) {
            return;
        }
        if (ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(FreeCam.class).w_1484_f()) {
            this.M_182_A = e_2866_D.n_1700_B;
            return;
        }
        if (Velocity.c_3005_b.Y_259_p.RealmsLongRunningMcoTaskScreen > 0 && this.M_182_A.v_4262_N() > 0.0) {
            double relativeAngle = this.h_1847_R();
            float forward = 0.0f;
            float strafe = 0.0f;
            if (relativeAngle > -45.0 && relativeAngle < 45.0) {
                forward = 1.0f;
            } else if (!(relativeAngle > 135.0) && !(relativeAngle < -135.0)) {
                if (relativeAngle >= 45.0 && relativeAngle <= 135.0) {
                    strafe = -1.0f;
                } else if (relativeAngle <= -45.0 && relativeAngle >= -135.0) {
                    strafe = 1.0f;
                }
            } else {
                forward = -1.0f;
            }
            e.n_1700_B(forward);
            e.J_1907_R(strafe);
            e.P_1922_E(this.prygatEnabled.isEnabled() != false && Velocity.c_3005_b.Y_259_p.M_1641_O());
        } else {
            this.M_182_A = e_2866_D.n_1700_B;
        }
    }

    private double h_1847_R() {
        double yaw = Velocity.c_3005_b.Y_259_p.p_178_J;
        double dx = -this.M_182_A.J_1907_R;
        double dz = -this.M_182_A.G_564_y;
        double attackerYaw = Math.toDegrees(Math.atan2(dz, dx)) - 90.0;
        return u_530_F.u_1723_Y(attackerYaw - yaw);
    }
}



