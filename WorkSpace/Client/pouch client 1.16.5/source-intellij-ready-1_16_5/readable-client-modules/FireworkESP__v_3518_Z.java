/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.joml.Vector2f
 */
package lightning.product;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lightning.product.D_38_f;
import lightning.product.E_3343_g;
import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.Q_2753_H;
import lightning.product.V_4286_F;
import lightning.product.V_4557_X;
import lightning.product.X_3546_T;
import lightning.product.X_3744_n;
import lightning.product.Y_1740_V;
import lightning.product.b_3528_u;
import lightning.product.e_2866_D;
import lightning.product.g_2336_b;
import lightning.product.h_2367_h;
import lightning.product.i_4482_j;
import lightning.product.j_755_i;
import lightning.product.l_3370_o;
import lightning.product.p_1977_n;
import lightning.product.t_3138_Z;
import lightning.product.v_2826_q;
import lightning.product.y_2603_k;
import lombok.Generated;
import org.joml.Vector2f;

public class v_3518_Z
extends X_3546_T {
    private final p_1977_n v_4262_N = new p_1977_n("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u0432\u0440\u0435\u043c\u044f", true);
    private final p_1977_n w_1484_f = new p_1977_n("\u0411\u043b\u044e\u0440 \u0444\u043e\u043d", false);
    private final h_2367_h t_148_a = new h_2367_h("\u0424\u043e\u043d", true, H_2506_c.n_1700_B("#141416FF"));
    private final List<n_1700_B> s_956_w = new CopyOnWriteArrayList<n_1700_B>();

    public v_3518_Z() {
        super("FireworkESP", y_2603_k.R_4764_Y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a);
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H eventPacket) {
        e_2866_D newPosition;
        X_3744_n packet;
        t_3138_Z<?> t_3138_Z2 = eventPacket.G_564_y();
        if (t_3138_Z2 instanceof X_3744_n && (packet = (X_3744_n)t_3138_Z2).R_4764_Y() == D_38_f.t_148_a && packet.J_1907_R() == V_4286_F.e_1231_S && !this.n_1700_B(newPosition = new e_2866_D(packet.G_564_y(), packet.P_1922_E(), packet.u_1723_Y()), 5.0)) {
            this.s_956_w.add(new n_1700_B(newPosition));
        }
    }

    @Y_1740_V
    public void n_1700_B(b_3528_u e) {
        if (this.s_956_w.isEmpty()) {
            return;
        }
        this.h_1847_R();
        for (n_1700_B firework : this.s_956_w) {
            firework.G_564_y();
            e_2866_D pos = firework.u_1723_Y();
            Vector2f screenPos = v_2826_q.n_1700_B(pos.J_1907_R, pos.R_4764_Y, pos.G_564_y);
            if (screenPos.x == Float.MAX_VALUE || screenPos.y == Float.MAX_VALUE) continue;
            this.n_1700_B(e, firework, screenPos, this.v_4262_N.t_148_a());
        }
    }

    private void n_1700_B(b_3528_u event, n_1700_B firework, Vector2f screenPos, boolean showTime) {
        float posX = screenPos.x - 20.0f;
        float posY = screenPos.y;
        String timeText = String.format("%.1f \u0441\u0435\u043a.", Math.max(0.0, (double)(firework.w_1484_f() - firework.J_1907_R.J_1907_R()) / 1000.0));
        float rectWidth = showTime ? l_3370_o.J_1907_R[14].n_1700_B(timeText) + 14.0f : 10.0f;
        float alpha = firework.R_4764_Y();
        if (this.w_1484_f.t_148_a().booleanValue()) {
            F_489_x.n_1700_B(event.J_1907_R(), posX, posY, rectWidth, 11.0f, 2.0f, (int)((Integer)this.t_148_a.J_1907_R()), alpha);
        } else {
            F_489_x.n_1700_B(event.J_1907_R(), posX, posY, rectWidth, 11.0f, 2.0f, H_2506_c.n_1700_B((int)((Integer)this.t_148_a.J_1907_R()), alpha));
        }
        F_489_x.n_1700_B(new g_2336_b("textures/item/firework_rocket.png"), posX + 0.5f, posY + 1.5f, 8.0f, 8.0f, H_2506_c.n_1700_B(255, 255, 255, (int)(255.0f * alpha)));
        if (showTime) {
            l_3370_o.J_1907_R[14].n_1700_B(event.J_1907_R(), timeText, (double)(posX + 11.0f), (double)(posY + 4.0f), H_2506_c.n_1700_B(255, 255, 255, (int)(255.0f * alpha)));
        }
    }

    private boolean n_1700_B(e_2866_D position, double radius) {
        double radiusSquared = radius * radius;
        return this.s_956_w.stream().filter(n_1700_B::P_1922_E).anyMatch(firework -> {
            double distanceSquared = position.v_4262_N(firework.u_1723_Y());
            return distanceSquared <= radiusSquared;
        });
    }

    private void h_1847_R() {
        this.s_956_w.removeIf(firework -> {
            if (firework.J_1907_R.J_1907_R() >= firework.w_1484_f()) {
                if (!firework.t_148_a()) {
                    firework.n_1700_B();
                }
                return firework.J_1907_R();
            }
            return false;
        });
    }

    @Y_1740_V
    public void n_1700_B(E_3343_g e) {
        this.s_956_w.clear();
    }

    @Override
    public void J_1907_R() {
        this.s_956_w.clear();
        super.J_1907_R();
    }

    public static class n_1700_B {
        private final e_2866_D n_1700_B;
        private final V_4557_X J_1907_R = new V_4557_X();
        private final long R_4764_Y = 5000L;
        private boolean G_564_y = false;
        private final j_755_i P_1922_E = new j_755_i(1.0f, 6.0f, i_4482_j.u_1723_Y);

        public n_1700_B(e_2866_D position) {
            this.n_1700_B = position;
        }

        public void n_1700_B() {
            if (!this.G_564_y) {
                this.G_564_y = true;
                this.P_1922_E.n_1700_B(0.0f);
            }
        }

        public boolean J_1907_R() {
            return this.G_564_y && this.P_1922_E.R_4764_Y();
        }

        public float R_4764_Y() {
            return this.P_1922_E.n_1700_B();
        }

        public void G_564_y() {
            if (this.G_564_y) {
                this.P_1922_E.n_1700_B(0.0f);
            }
        }

        public boolean P_1922_E() {
            return !this.G_564_y || this.R_4764_Y() > 0.1f;
        }

        @Generated
        public e_2866_D u_1723_Y() {
            return this.n_1700_B;
        }

        @Generated
        public V_4557_X v_4262_N() {
            return this.J_1907_R;
        }

        @Generated
        public long w_1484_f() {
            return this.R_4764_Y;
        }

        @Generated
        public boolean t_148_a() {
            return this.G_564_y;
        }
    }
}

