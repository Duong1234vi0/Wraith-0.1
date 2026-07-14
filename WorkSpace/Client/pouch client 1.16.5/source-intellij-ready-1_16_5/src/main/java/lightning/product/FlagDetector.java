/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Timer;
import lightning.product.Flight;
import lightning.product.MultiBooleanSetting;
import lightning.product.Phase;
import lightning.product.Velocity;
import lightning.product.Q_2753_H;
import lightning.product.Blink;
import lightning.product.HighJump;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.GuiMove;
import lightning.product.ClientboundPlayerPositionPacket;
import lightning.product.ModuleManager;
import lightning.product.e_2866_D;
import lightning.product.ClientboundSetEntityMotionPacket;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.Packet;
import lightning.product.v_1900_v;
import lightning.product.Speed;
import lightning.product.ModuleCategory;

public class FlagDetector
extends Module {
    private static final double v_4262_N = 0.5;
    private final BooleanSetting logVChatEnabled = new BooleanSetting("\u041b\u043e\u0433 \u0432 \u0447\u0430\u0442", true);
    private final MultiBooleanSetting otkatDlyaOptions = new MultiBooleanSetting("\u041e\u0442\u043a\u0430\u0442 \u0434\u043b\u044f", new BooleanSetting("Flight", true), new BooleanSetting("Speed", true), new BooleanSetting("Phase", true), new BooleanSetting("Timer", true), new BooleanSetting("GuiMove", true), new BooleanSetting("Blink", true), new BooleanSetting("HighJump", true));
    private final MultiBooleanSetting velositiSeyfOptions = new MultiBooleanSetting("\u0412\u0435\u043b\u043e\u0441\u0438\u0442\u0438 \u0441\u0435\u0439\u0444", new BooleanSetting("NoVelocity", true));

    public FlagDetector() {
        super("FlagDetector", ModuleCategory.P_1922_E);
        this.addSettings(this.logVChatEnabled, this.otkatDlyaOptions, this.velositiSeyfOptions);
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
        if (!e.J_1907_R() || FlagDetector.c_3005_b.Y_259_p == null) {
            return;
        }
        Packet<?> packet = e.G_564_y();
        String name = packet.getClass().getSimpleName();
        if (packet instanceof ClientboundPlayerPositionPacket) {
            ClientboundPlayerPositionPacket p = (ClientboundPlayerPositionPacket)packet;
            double targetX = p.w_1484_f().contains((Object)ClientboundPlayerPositionPacket.n_1700_B.n_1700_B) ? FlagDetector.c_3005_b.Y_259_p.O_3598_v() + p.J_1907_R() : p.J_1907_R();
            double targetY = p.w_1484_f().contains((Object)ClientboundPlayerPositionPacket.n_1700_B.J_1907_R) ? FlagDetector.c_3005_b.Y_259_p.X_2960_b() + p.R_4764_Y() : p.R_4764_Y();
            double targetZ = p.w_1484_f().contains((Object)ClientboundPlayerPositionPacket.n_1700_B.R_4764_Y) ? FlagDetector.c_3005_b.Y_259_p.l_2647_k() + p.G_564_y() : p.G_564_y();
            e_2866_D current = FlagDetector.c_3005_b.Y_259_p.s_4990_V();
            double distance = current.u_1723_Y(new e_2866_D(targetX, targetY, targetZ));
            if (distance >= 0.5) {
                this.n_1700_B(name, "\u041e\u0442\u043a\u0430\u0442 \u043f\u043e\u0437\u0438\u0446\u0438\u0438: " + String.format("%.1f \u0431\u043b\u043e\u043a\u043e\u0432", distance));
                this.h_1847_R();
            }
            return;
        }
        if (packet instanceof ClientboundSetEntityMotionPacket) {
            double vz;
            double vy;
            ClientboundSetEntityMotionPacket p = (ClientboundSetEntityMotionPacket)packet;
            if (p.J_1907_R() != FlagDetector.c_3005_b.Y_259_p.j_276_v()) {
                return;
            }
            double vx = (double)p.R_4764_Y() / 8000.0;
            double len = Math.sqrt(vx * vx + (vy = (double)p.G_564_y() / 8000.0) * vy + (vz = (double)p.P_1922_E() / 8000.0) * vz);
            if (len < 0.01 && FlagDetector.c_3005_b.Y_259_p.I_4348_c().u_1723_Y() > 0.05) {
                this.n_1700_B(name, "\u041e\u0431\u043d\u0443\u043b\u0435\u043d\u0438\u0435 \u0441\u043a\u043e\u0440\u043e\u0441\u0442\u0438");
                this.Q_4569_t();
            }
        }
    }

    private void h_1847_R() {
        ModuleManager mgr = ClientBootstrap.Y_601_j().J_1907_R();
        boolean prev = Module.P_4830_p();
        Module.G_564_y(true);
        if (Boolean.TRUE.equals(this.otkatDlyaOptions.isOptionEnabled("Flight"))) {
            this.n_1700_B(mgr.n_1700_B(Flight.class));
        }
        if (Boolean.TRUE.equals(this.otkatDlyaOptions.isOptionEnabled("Speed"))) {
            this.n_1700_B(mgr.n_1700_B(Speed.class));
        }
        if (Boolean.TRUE.equals(this.otkatDlyaOptions.isOptionEnabled("Phase"))) {
            this.n_1700_B(mgr.n_1700_B(Phase.class));
        }
        if (Boolean.TRUE.equals(this.otkatDlyaOptions.isOptionEnabled("Timer"))) {
            this.n_1700_B(mgr.n_1700_B(Timer.class));
        }
        if (Boolean.TRUE.equals(this.otkatDlyaOptions.isOptionEnabled("GuiMove"))) {
            this.n_1700_B(mgr.n_1700_B(GuiMove.class));
        }
        if (Boolean.TRUE.equals(this.otkatDlyaOptions.isOptionEnabled("Blink"))) {
            this.n_1700_B(mgr.n_1700_B(Blink.class));
        }
        if (Boolean.TRUE.equals(this.otkatDlyaOptions.isOptionEnabled("HighJump"))) {
            this.n_1700_B(mgr.n_1700_B(HighJump.class));
        }
        Module.G_564_y(prev);
    }

    private void Q_4569_t() {
        if (!Boolean.TRUE.equals(this.velositiSeyfOptions.isOptionEnabled("NoVelocity"))) {
            return;
        }
        Module m = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(Velocity.class);
        if (m == null) {
            return;
        }
        boolean prev = Module.P_4830_p();
        Module.G_564_y(true);
        this.n_1700_B(m);
        Module.G_564_y(prev);
    }

    private void n_1700_B(Module m) {
        if (m != null && m.w_1484_f()) {
            m.n_1700_B(false);
        }
    }

    private void n_1700_B(String packetName, String reason) {
        if (!this.logVChatEnabled.isEnabled().booleanValue()) {
            return;
        }
        v_1900_v.n_1700_B("\u00a7c[Flag] \u00a77\u041f\u0430\u043a\u0435\u0442: \u00a7f" + packetName + " \u00a77\u2014 " + reason, new Object[0]);
    }
}



