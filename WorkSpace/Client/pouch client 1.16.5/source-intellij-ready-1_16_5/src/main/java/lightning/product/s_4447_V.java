/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.C_1375_J;
import lightning.product.Q_2753_H;
import lightning.product.U_4087_m;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.h_1015_G;
import lightning.product.ClientboundSetEntityMotionPacket;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;
import lightning.product.ModuleCategory;

public class s_4447_V
extends Module {
    private static final int v_4262_N = 8;
    private static final byte w_1484_f = 33;
    private int t_148_a;

    public s_4447_V() {
        super("AntiThorns", "\u041e\u0442\u043a\u043b\u044e\u0447\u0430\u0435\u0442 \u0443\u0440\u043e\u043d/\u044d\u0444\u0444\u0435\u043a\u0442 \u0448\u0438\u043f\u043e\u0432 \u0438 \u0441\u0432\u044f\u0437\u0430\u043d\u043d\u044b\u0439 \u043e\u0442\u043a\u0438\u0434\u044b\u0432\u0430\u044e\u0449\u0438\u0439 \u0438\u043c\u043f\u0443\u043b\u044c\u0441 \u043d\u0430 \u044d\u043b\u0438\u0442\u0440\u0435", ModuleCategory.n_1700_B);
    }

    @Y_1740_V
    public void n_1700_B(U_4087_m event) {
        if (s_4447_V.c_3005_b.Y_259_p == null || event.R_4764_Y() != s_4447_V.c_3005_b.Y_259_p) {
            return;
        }
        if (s_4447_V.c_3005_b.Y_259_p.k_578_l()) {
            this.t_148_a = 8;
        }
        event.n_1700_B(true);
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H event) {
        Packet<ClientGamePacketListener> packet;
        if (s_4447_V.c_3005_b.Y_259_p == null || s_4447_V.c_3005_b.Y_601_j == null) {
            this.t_148_a = 0;
            return;
        }
        if (!event.J_1907_R()) {
            return;
        }
        Packet<?> t_3138_Z2 = event.G_564_y();
        if (t_3138_Z2 instanceof C_1375_J && ((C_1375_J)(packet = (C_1375_J)t_3138_Z2)).J_1907_R() == 33 && ((C_1375_J)packet).n_1700_B(s_4447_V.c_3005_b.Y_601_j) == s_4447_V.c_3005_b.Y_259_p && s_4447_V.c_3005_b.Y_259_p.k_578_l()) {
            this.t_148_a = 8;
            return;
        }
        t_3138_Z2 = event.G_564_y();
        if (t_3138_Z2 instanceof ClientboundSetEntityMotionPacket && ((ClientboundSetEntityMotionPacket)(packet = (ClientboundSetEntityMotionPacket)t_3138_Z2)).J_1907_R() == s_4447_V.c_3005_b.Y_259_p.j_276_v() && this.h_1847_R()) {
            event.n_1700_B(true);
            this.t_148_a = 0;
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (s_4447_V.c_3005_b.Y_259_p == null || !s_4447_V.c_3005_b.Y_259_p.k_578_l()) {
            this.t_148_a = 0;
            return;
        }
        if (this.t_148_a > 0) {
            --this.t_148_a;
        }
    }

    @Override
    public void n_1700_B() {
        this.t_148_a = 0;
        super.n_1700_B();
    }

    @Override
    public void J_1907_R() {
        this.t_148_a = 0;
        super.J_1907_R();
    }

    private boolean h_1847_R() {
        return this.t_148_a > 0 && s_4447_V.c_3005_b.Y_259_p.k_578_l();
    }
}



