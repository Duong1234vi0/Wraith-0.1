/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.concurrent.ThreadLocalRandom;
import lightning.product.E_3434_d;
import lightning.product.I_4817_s;
import lightning.product.J_588_u;
import lightning.product.N_3268_u;
import lightning.product.N_4263_v;
import lightning.product.Q_2753_H;
import lightning.product.V_3354_l;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.j_4464_q;
import lightning.product.o_148_s;
import lightning.product.q_366_O;
import lightning.product.r_3979_X;
import lightning.product.t_3138_Z;
import lightning.product.y_2603_k;

public class r_2768_V
extends X_3546_T {
    private final q_366_O w_1484_f = new q_366_O("\u041c\u043e\u0434", "Grim", "Grim");
    public static boolean v_4262_N;

    public r_2768_V() {
        super("PacketCriticals", y_2603_k.n_1700_B);
        this.n_1700_B(this.w_1484_f);
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
        if (!e.R_4764_Y() || r_2768_V.c_3005_b.Y_259_p == null || r_2768_V.c_3005_b.Y_601_j == null) {
            return;
        }
        t_3138_Z<?> t_3138_Z2 = e.G_564_y();
        if (!(t_3138_Z2 instanceof E_3434_d)) {
            return;
        }
        E_3434_d packet = (E_3434_d)t_3138_Z2;
        if (packet.J_1907_R() != E_3434_d.n_1700_B.J_1907_R || v_4262_N) {
            return;
        }
        N_4263_v entity = packet.n_1700_B(r_2768_V.c_3005_b.Y_601_j);
        if (entity == null || entity instanceof V_3354_l) {
            return;
        }
        this.Q_4569_t();
    }

    private void Q_4569_t() {
        float fallDist;
        boolean webOrLava;
        boolean hasTarget;
        r_3979_X aura = o_148_s.Y_601_j().J_1907_R().J_1907_R();
        j_4464_q trigger = o_148_s.Y_601_j().J_1907_R().J_1907_R;
        boolean bl = hasTarget = aura != null && aura.w_1484_f() && aura.v_4262_N != null || trigger != null && trigger.w_1484_f() && trigger.v_4262_N != null;
        if (!hasTarget) {
            return;
        }
        if (r_2768_V.c_3005_b.Y_259_p.M_1641_O()) {
            return;
        }
        double y = r_2768_V.c_3005_b.Y_259_p.X_2960_b();
        if (y == (double)((int)y)) {
            return;
        }
        boolean bl2 = webOrLava = r_2768_V.c_3005_b.Y_259_p.s_2121_j || r_2768_V.c_3005_b.Y_259_p.W_3464_O();
        if (!webOrLava && !this.M_182_A()) {
            return;
        }
        r_2768_V.c_3005_b.Y_259_p.U_1241_n = fallDist = ThreadLocalRandom.current().nextFloat() * 9.0E-7f + 1.0E-7f;
        r_2768_V.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new N_3268_u.J_1907_R(r_2768_V.c_3005_b.Y_259_p.O_3598_v(), r_2768_V.c_3005_b.Y_259_p.X_2960_b() - (double)fallDist, r_2768_V.c_3005_b.Y_259_p.l_2647_k(), r_2768_V.c_3005_b.Y_259_p.p_178_J, r_2768_V.c_3005_b.Y_259_p.f_4016_n, false));
    }

    public boolean h_1847_R() {
        return this.w_1484_f() && c_3005_b != null && r_2768_V.c_3005_b.Y_259_p != null && (r_2768_V.c_3005_b.Y_259_p.J_1907_R(J_588_u.H_2857_Y) || this.M_182_A());
    }

    private boolean M_182_A() {
        if (r_2768_V.c_3005_b.Y_259_p == null || r_2768_V.c_3005_b.Y_601_j == null) {
            return false;
        }
        I_4817_s box = r_2768_V.c_3005_b.Y_259_p.i_601_W();
        int minX = (int)Math.floor(box.minX);
        int minY = (int)Math.floor(box.minY);
        int minZ = (int)Math.floor(box.minZ);
        int maxX = (int)Math.ceil(box.maxX);
        int maxY = (int)Math.ceil(box.maxY);
        int maxZ = (int)Math.ceil(box.maxZ);
        for (int x = minX; x < maxX; ++x) {
            for (int y = minY; y < maxY; ++y) {
                for (int z = minZ; z < maxZ; ++z) {
                    if (r_2768_V.c_3005_b.Y_601_j.getBlockState(new c_1514_x(x, y, z)).J_1907_R() != a_3742_W.y_1700_S) continue;
                    return true;
                }
            }
        }
        return false;
    }
}

