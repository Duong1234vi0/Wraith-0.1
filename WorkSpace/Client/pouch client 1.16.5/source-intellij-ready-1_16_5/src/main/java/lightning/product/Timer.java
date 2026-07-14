/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.NumberSetting;
import lightning.product.Q_2753_H;
import lightning.product.V_4557_X;
import lightning.product.V_674_I;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.ClientboundPlayerPositionPacket;
import lightning.product.h_1015_G;
import lightning.product.i_4434_b;
import lightning.product.m_2262_U;
import lightning.product.ClientboundSetEntityMotionPacket;
import lightning.product.BooleanSetting;
import lightning.product.KeyBindSetting;
import lightning.product.ModeSetting;
import lightning.product.Packet;
import lightning.product.u_530_F;
import lightning.product.ModuleCategory;

public class Timer
extends Module {
    public static Timer v_4262_N;
    public static final float w_1484_f = 100.0f;
    private static float M_588_G;
    private static double P_4830_p;
    private static double h_1847_R;
    private static double Q_4569_t;
    private static float M_182_A;
    private static float t_1786_h;
    public final ModeSetting rezhimMode = new ModeSetting("\u0420\u0435\u0436\u0438\u043c", "Matrix", "Matrix", "Grim");
    public final KeyBindSetting bindGrimKeyBind = new KeyBindSetting("\u0411\u0438\u043d\u0434 Grim", () -> this.rezhimMode.isMode("Grim"));
    public static final NumberSetting u_2550_I;
    private final BooleanSetting smartEnabled = new BooleanSetting("\u0421\u043c\u0430\u0440\u0442", true);
    private final BooleanSetting podemPriDvizheniiEnabled = new BooleanSetting("\u041f\u043e\u0434\u044a\u0451\u043c \u043f\u0440\u0438 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0438", false, () -> !this.rezhimMode.isMode("Grim"));
    private final NumberSetting znacheniePodemaSetting = new NumberSetting("\u0417\u043d\u0430\u0447\u0435\u043d\u0438\u0435 \u043f\u043e\u0434\u044a\u0451\u043c\u0430", 0.02f, 0.01f, 0.5f, 0.01f, () -> this.podemPriDvizheniiEnabled.isEnabled());
    private final NumberSetting nakoplenieTikovSetting = new NumberSetting("\u041d\u0430\u043a\u043e\u043f\u043b\u0435\u043d\u0438\u0435 \u0442\u0438\u043a\u043e\u0432", 1.0f, 0.15f, 3.0f, 0.1f, () -> !this.rezhimMode.isMode("Grim"));
    private boolean Q_2552_b;
    private final V_4557_X C_2741_M = new V_4557_X();

    public Timer() {
        super("Timer", ModuleCategory.J_1907_R);
        v_4262_N = this;
        this.addSettings(this.rezhimMode, u_2550_I, this.bindGrimKeyBind, this.smartEnabled, this.podemPriDvizheniiEnabled, this.znacheniePodemaSetting, this.nakoplenieTikovSetting);
    }

    public static float h_1847_R() {
        return M_588_G;
    }

    public static void J_1907_R(int i) {
    }

    @Y_1740_V
    public void n_1700_B(i_4434_b e) {
        if (!this.w_1484_f() || !this.rezhimMode.isMode("Grim")) {
            return;
        }
        if ((Integer)this.bindGrimKeyBind.getKey() >= 0 && e.n_1700_B() == ((Integer)this.bindGrimKeyBind.getKey()).intValue()) {
            this.Q_2552_b = true;
        }
    }

    @Y_1740_V
    public void n_1700_B(m_2262_U eventPre) {
        if (!this.w_1484_f() || !this.rezhimMode.isMode("Grim")) {
            return;
        }
        this.n_1700_B(eventPre.P_1922_E(), eventPre.u_1723_Y(), eventPre.J_1907_R(), eventPre.R_4764_Y(), eventPre.G_564_y());
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
        if (!this.w_1484_f() || !this.rezhimMode.isMode("Grim")) {
            return;
        }
        if (e.J_1907_R()) {
            this.G_564_y(e);
            this.R_4764_Y(e);
        }
        if (e.R_4764_Y()) {
            this.J_1907_R(e);
        }
    }

    private void J_1907_R(Q_2753_H e) {
        if (e.G_564_y() instanceof V_674_I) {
            e.n_1700_B(true);
        }
    }

    private void R_4764_Y(Q_2753_H e) {
        ClientboundSetEntityMotionPacket p;
        Packet<?> t_3138_Z2 = e.G_564_y();
        if (t_3138_Z2 instanceof ClientboundSetEntityMotionPacket && (p = (ClientboundSetEntityMotionPacket)t_3138_Z2).J_1907_R() == Timer.c_3005_b.Y_259_p.j_276_v()) {
            this.Q_4569_t();
        }
    }

    private void G_564_y(Q_2753_H e) {
        if (e.G_564_y() instanceof ClientboundPlayerPositionPacket && this.Q_2552_b) {
            this.Q_4569_t();
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (!this.w_1484_f() || Timer.c_3005_b.Y_259_p == null) {
            return;
        }
        if (this.C_2741_M.J_1907_R(25000L)) {
            this.M_182_A();
            this.C_2741_M.n_1700_B();
        }
        if (!Timer.c_3005_b.Y_259_p.M_1641_O() && !this.Q_2552_b) {
            M_588_G += 0.1f;
            M_588_G = u_530_F.n_1700_B(M_588_G, 0.0f, 100.0f / (this.rezhimMode.isMode("Grim") ? 1.0f : ((Float)u_2550_I.J_1907_R()).floatValue()));
        }
        if (this.rezhimMode.isMode("Grim") && !this.Q_2552_b) {
            Timer.c_3005_b.P_1922_E.n_1700_B();
            return;
        }
        Timer.c_3005_b.P_1922_E.n_1700_B(((Float)u_2550_I.J_1907_R()).floatValue());
        if (!this.smartEnabled.isEnabled().booleanValue() || Timer.c_3005_b.P_1922_E.J_1907_R() <= 1.0f) {
            return;
        }
        if (M_588_G < 100.0f / ((Float)u_2550_I.J_1907_R()).floatValue()) {
            M_588_G += this.rezhimMode.isMode("Grim") ? 0.05f : ((Float)this.nakoplenieTikovSetting.getValue()).floatValue();
            M_588_G = u_530_F.n_1700_B(M_588_G, 0.0f, 100.0f / (this.rezhimMode.isMode("Grim") ? 1.0f : ((Float)u_2550_I.J_1907_R()).floatValue()));
        } else {
            this.Q_4569_t();
        }
    }

    public void n_1700_B(float yaw, float pitch, double posX, double posY, double posZ) {
        if (this.t_1786_h()) {
            M_588_G = this.rezhimMode.isMode("Grim") ? (M_588_G -= 0.05f) : (M_588_G -= ((Float)this.nakoplenieTikovSetting.getValue()).floatValue() + 0.4f);
        } else if (this.podemPriDvizheniiEnabled.isEnabled().booleanValue() && !this.rezhimMode.isMode("Grim")) {
            M_588_G -= ((Float)this.znacheniePodemaSetting.getValue()).floatValue();
        }
        M_588_G = u_530_F.n_1700_B(M_588_G, 0.0f, (float)Math.floor(100.0));
        P_4830_p = posX;
        h_1847_R = posY;
        Q_4569_t = posZ;
        M_182_A = yaw;
        t_1786_h = pitch;
    }

    private boolean t_1786_h() {
        return P_4830_p == Timer.c_3005_b.Y_259_p.O_3598_v() && h_1847_R == Timer.c_3005_b.Y_259_p.X_2960_b() && Q_4569_t == Timer.c_3005_b.Y_259_p.l_2647_k() && M_182_A == Timer.c_3005_b.Y_259_p.p_178_J && t_1786_h == Timer.c_3005_b.Y_259_p.f_4016_n;
    }

    public void Q_4569_t() {
        this.n_1700_B(false);
        Timer.c_3005_b.P_1922_E.n_1700_B();
    }

    public void M_182_A() {
        if (this.rezhimMode.isMode("Grim")) {
            M_588_G = 100.0f / ((Float)u_2550_I.J_1907_R()).floatValue();
            this.Q_2552_b = false;
        }
    }

    @Override
    public void onDisable() {
        this.M_182_A();
        Timer.c_3005_b.P_1922_E.n_1700_B();
        this.C_2741_M.n_1700_B();
        super.onDisable();
    }

    @Override
    public void onEnable() {
        this.M_182_A();
        Timer.c_3005_b.P_1922_E.n_1700_B();
        this.C_2741_M.n_1700_B();
        if (Timer.c_3005_b.Y_259_p != null) {
            P_4830_p = Timer.c_3005_b.Y_259_p.O_3598_v();
            h_1847_R = Timer.c_3005_b.Y_259_p.X_2960_b();
            Q_4569_t = Timer.c_3005_b.Y_259_p.l_2647_k();
            M_182_A = Timer.c_3005_b.Y_259_p.p_178_J;
            t_1786_h = Timer.c_3005_b.Y_259_p.f_4016_n;
        }
        super.onEnable();
    }

    static {
        M_588_G = 0.0f;
        u_2550_I = new NumberSetting("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", 2.0f, 1.0f, 10.0f, 0.01f);
    }
}



