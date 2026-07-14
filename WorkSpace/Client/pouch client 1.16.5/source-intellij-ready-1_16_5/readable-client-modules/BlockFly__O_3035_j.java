/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.concurrent.ConcurrentLinkedQueue;
import lightning.product.G_3416_z;
import lightning.product.I_3710_B;
import lightning.product.M_2562_s;
import lightning.product.O_1043_U;
import lightning.product.P_3504_Q;
import lightning.product.Q_2753_H;
import lightning.product.T_3558_p;
import lightning.product.T_3952_j;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.a_178_J;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.d_2545_n;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.m_1679_b;
import lightning.product.m_2262_U;
import lightning.product.t_2650_P;
import lightning.product.t_3138_Z;
import lightning.product.u_530_F;
import lightning.product.u_925_K;
import lightning.product.v_1669_V;
import lightning.product.v_1900_v;
import lightning.product.v_570_f;
import lightning.product.x_1688_C;
import lightning.product.y_2603_k;

public class O_3035_j
extends X_3546_T {
    public static final ConcurrentLinkedQueue<J_1907_R> v_4262_N = new ConcurrentLinkedQueue();
    public P_3504_Q w_1484_f;
    public boolean t_148_a;
    public O_1043_U s_956_w = new O_1043_U();
    private n_1700_B u_2550_I;
    private n_1700_B M_588_G;
    private float P_4830_p;

    public O_3035_j() {
        super("BlockFly", y_2603_k.J_1907_R);
    }

    @Override
    public void J_1907_R() {
        super.J_1907_R();
        O_3035_j.c_3005_b.P_1922_E.n_1700_B();
        for (J_1907_R p : v_4262_N) {
            O_3035_j.c_3005_b.Y_259_p.n_1700_B.getNetworkManager().J_1907_R(p.n_1700_B());
        }
        v_4262_N.clear();
    }

    @Override
    public void n_1700_B() {
        super.n_1700_B();
        if (O_3035_j.c_3005_b.Y_259_p != null) {
            this.P_4830_p = (float)O_3035_j.c_3005_b.Y_259_p.X_2960_b();
        }
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
        if (O_3035_j.c_3005_b.Y_259_p != null && O_3035_j.c_3005_b.Y_601_j != null && !c_3005_b.e_4240_b() && !O_3035_j.c_3005_b.Y_259_p.Z_2812_M()) {
            if (e.R_4764_Y()) {
                T_3952_j p;
                t_3138_Z<?> packet = e.G_564_y();
                v_4262_N.add(new J_1907_R(packet, System.currentTimeMillis()));
                e.n_1700_B(true);
                if (packet instanceof T_3952_j && ((p = (T_3952_j)packet).J_1907_R() == T_3952_j.n_1700_B.G_564_y || p.J_1907_R() == T_3952_j.n_1700_B.P_1922_E)) {
                    e.n_1700_B(true);
                }
            }
        } else {
            this.n_1700_B(false);
        }
    }

    @Y_1740_V
    public void n_1700_B(m_2262_U e) {
        for (J_1907_R timedPacket : v_4262_N) {
            if (System.currentTimeMillis() - timedPacket.J_1907_R() < 1000L) continue;
            O_3035_j.c_3005_b.Y_259_p.n_1700_B.getNetworkManager().J_1907_R(timedPacket.n_1700_B());
            v_4262_N.remove(timedPacket);
        }
        e.G_564_y(false);
        if (O_3035_j.c_3005_b.Y_259_p.M_1641_O()) {
            this.P_4830_p = (float)Math.floor(O_3035_j.c_3005_b.Y_259_p.X_2960_b() - 1.0);
        }
        this.u_2550_I = this.h_1847_R();
        if (this.u_2550_I == null) {
            return;
        }
        this.M_588_G = this.u_2550_I;
        if (O_3035_j.c_3005_b.Y_601_j.getBlockState(O_3035_j.c_3005_b.Y_259_p.b_2312_j().add(0.0, -0.5, 0.0)).J_1907_R() == a_3742_W.n_1700_B) {
            float[] rot = this.n_1700_B(this.u_2550_I.n_1700_B, this.u_2550_I.J_1907_R);
            this.w_1484_f = new P_3504_Q(rot[0], rot[1]);
            e.n_1700_B(this.w_1484_f.t_148_a);
            e.J_1907_R(this.w_1484_f.s_956_w);
            O_3035_j.c_3005_b.Y_259_p.u_55_V = this.w_1484_f.s_956_w;
            O_3035_j.c_3005_b.Y_259_p.f_3449_S = this.w_1484_f.t_148_a;
        }
    }

    @Y_1740_V
    public void n_1700_B(d_2545_n e) {
        e.n_1700_B(false);
    }

    @Y_1740_V
    public void n_1700_B(a_178_J e) {
        G_3416_z result;
        if (u_925_K.n_1700_B()) {
            e.P_1922_E(false);
        }
        e.u_1723_Y(false);
        if (!O_3035_j.c_3005_b.Y_259_p.M_1641_O()) {
            e.n_1700_B(0.0f);
            e.J_1907_R(0.0f);
        }
        if (this.w_1484_f != null && (result = v_570_f.n_1700_B(3.0, this.w_1484_f.t_148_a, this.w_1484_f.s_956_w)).R_4764_Y() != I_3710_B.n_1700_B.J_1907_R && O_3035_j.c_3005_b.Y_601_j.getBlockState(O_3035_j.c_3005_b.Y_259_p.b_2312_j().add(0.0, -0.5, 0.0)).J_1907_R() == a_3742_W.n_1700_B) {
            e.u_1723_Y(true);
        }
    }

    @Y_1740_V
    public void n_1700_B(M_2562_s e) {
        double currentSpeed;
        if (O_3035_j.c_3005_b.Y_259_p.M_1641_O() && (currentSpeed = Math.min(0.16779580771923064, this.Q_4569_t())) > 0.0) {
            this.n_1700_B(e, currentSpeed);
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (this.u_2550_I == null || this.M_588_G == null) {
            return;
        }
        int block = -1;
        for (int i = 0; i < 9; ++i) {
            Z_1993_T s = O_3035_j.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (!(s.J_1907_R() instanceof v_1669_V)) continue;
            block = i;
            break;
        }
        if (block == -1) {
            v_1900_v.n_1700_B("\u0414\u043b\u044f \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u044f \u044d\u0442\u043e\u0439 \u0444\u0443\u043d\u043a\u0446\u0438\u0438 \u0443 \u0432\u0430\u0441 \u0434\u043e\u043b\u0436\u0435\u043d \u0431\u044b\u0442\u044c \u0431\u043b\u043e\u043a \u0432 \u0445\u043e\u0442\u0431\u0430\u0440\u0435!", new Object[0]);
            this.R_4764_Y();
            return;
        }
        if (this.w_1484_f == null) {
            return;
        }
        G_3416_z result = v_570_f.n_1700_B(3.0, this.w_1484_f.t_148_a, this.w_1484_f.s_956_w);
        if (O_3035_j.c_3005_b.Y_601_j.getBlockState(O_3035_j.c_3005_b.Y_259_p.b_2312_j().add(0.0, -0.5, 0.0)).J_1907_R() == a_3742_W.n_1700_B && result.R_4764_Y() == I_3710_B.n_1700_B.J_1907_R) {
            int last = O_3035_j.c_3005_b.Y_259_p.l_1268_F.G_564_y;
            O_3035_j.c_3005_b.Y_259_p.l_1268_F.G_564_y = block;
            O_3035_j.c_3005_b.w_1457_N.func_217292_a(O_3035_j.c_3005_b.Y_259_p, O_3035_j.c_3005_b.Y_601_j, x_1688_C.n_1700_B, new G_3416_z(this.n_1700_B(this.M_588_G), this.M_588_G.J_1907_R(), this.M_588_G.n_1700_B(), false));
            O_3035_j.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new T_3558_p(x_1688_C.n_1700_B));
            this.u_2550_I = null;
            O_3035_j.c_3005_b.Y_259_p.l_1268_F.G_564_y = last;
        }
    }

    public float[] n_1700_B(c_1514_x blockPos, b_257_Y enumFacing) {
        double d = (double)blockPos.getX() + 0.5 - O_3035_j.c_3005_b.Y_259_p.O_3598_v() + (double)enumFacing.t_148_a() * 0.25;
        double d2 = (double)blockPos.getZ() + 0.5 - O_3035_j.c_3005_b.Y_259_p.l_2647_k() + (double)enumFacing.u_2550_I() * 0.25;
        double d3 = O_3035_j.c_3005_b.Y_259_p.X_2960_b() + (double)O_3035_j.c_3005_b.Y_259_p.X_1313_W() - (double)blockPos.getY() - (double)enumFacing.s_956_w() * 0.25;
        double d4 = u_530_F.n_1700_B(d * d + d2 * d2);
        float f = (float)(Math.atan2(d2, d) * 180.0 / Math.PI) - 90.0f;
        float f2 = (float)(Math.atan2(d3, d4) * 180.0 / Math.PI);
        return new float[]{u_530_F.v_4262_N(f), f2};
    }

    public n_1700_B h_1847_R() {
        int y = (int)(O_3035_j.c_3005_b.Y_259_p.X_2960_b() - 1.0 >= (double)this.P_4830_p && Math.max(O_3035_j.c_3005_b.Y_259_p.X_2960_b(), (double)this.P_4830_p) - Math.min(O_3035_j.c_3005_b.Y_259_p.X_2960_b(), (double)this.P_4830_p) <= 3.0 && !O_3035_j.c_3005_b.P_4830_p.T_69_K.G_564_y() ? (double)this.P_4830_p : O_3035_j.c_3005_b.Y_259_p.X_2960_b() - 1.0);
        c_1514_x belowBlockPos = new c_1514_x(O_3035_j.c_3005_b.Y_259_p.O_3598_v(), (double)(y - (O_3035_j.c_3005_b.Y_259_p.q_2307_F() ? -1 : 0)), O_3035_j.c_3005_b.Y_259_p.l_2647_k());
        if (O_3035_j.c_3005_b.Y_601_j.getBlockState(belowBlockPos).J_1907_R() instanceof m_1679_b) {
            for (int x = 0; x < 3; ++x) {
                for (int z = 0; z < 3; ++z) {
                    for (int i = -1; i < 1; ++i) {
                        c_1514_x blockPos = belowBlockPos.add(x * i, 0, z * i);
                        if (!(O_3035_j.c_3005_b.Y_601_j.getBlockState(blockPos).J_1907_R() instanceof m_1679_b)) continue;
                        for (b_257_Y direction : b_257_Y.values()) {
                            c_1514_x block = blockPos.offset(direction);
                            t_2650_P material = O_3035_j.c_3005_b.Y_601_j.getBlockState(block).J_1907_R().N_4405_n().R_4764_Y();
                            if (!material.J_1907_R() || material.n_1700_B()) continue;
                            return new n_1700_B(this, block, direction.u_1723_Y());
                        }
                    }
                }
            }
        }
        return null;
    }

    public e_2866_D n_1700_B(n_1700_B data) {
        c_1514_x pos = data.n_1700_B;
        b_257_Y face = data.J_1907_R;
        double x = (double)pos.getX() + 0.5;
        double y = (double)pos.getY() + 0.5;
        double z = (double)pos.getZ() + 0.5;
        if (face != b_257_Y.J_1907_R && face != b_257_Y.n_1700_B) {
            y += 0.5;
        } else {
            x += 0.3;
            z += 0.3;
        }
        if (face == b_257_Y.P_1922_E || face == b_257_Y.u_1723_Y) {
            z += 0.15;
        }
        if (face == b_257_Y.G_564_y || face == b_257_Y.R_4764_Y) {
            x += 0.15;
        }
        return new e_2866_D(x, y, z);
    }

    private void n_1700_B(M_2562_s move, double motion) {
        double forward = O_3035_j.c_3005_b.Y_259_p.G_564_y.moveForward;
        double strafe = O_3035_j.c_3005_b.Y_259_p.G_564_y.moveStrafe;
        float yaw = O_3035_j.c_3005_b.Y_259_p.p_178_J;
        if (forward == 0.0 && strafe == 0.0) {
            move.R_4764_Y().J_1907_R = 0.0;
            move.R_4764_Y().G_564_y = 0.0;
        } else {
            if (forward != 0.0) {
                if (strafe > 0.0) {
                    yaw += (float)(forward > 0.0 ? -45 : 45);
                } else if (strafe < 0.0) {
                    yaw += (float)(forward > 0.0 ? 45 : -45);
                }
                strafe = 0.0;
                if (forward > 0.0) {
                    forward = 1.0;
                } else if (forward < 0.0) {
                    forward = -1.0;
                }
            }
            double cos = Math.cos(Math.toRadians(yaw + 90.0f));
            double sin = Math.sin(Math.toRadians(yaw + 90.0f));
            move.R_4764_Y().J_1907_R = forward * motion * cos + strafe * motion * sin;
            move.R_4764_Y().G_564_y = forward * motion * sin - strafe * motion * cos;
        }
    }

    private double Q_4569_t() {
        return Math.sqrt(O_3035_j.c_3005_b.Y_259_p.I_4348_c().J_1907_R * O_3035_j.c_3005_b.Y_259_p.I_4348_c().J_1907_R + O_3035_j.c_3005_b.Y_259_p.I_4348_c().G_564_y * O_3035_j.c_3005_b.Y_259_p.I_4348_c().G_564_y);
    }

    public static class J_1907_R {
        private final t_3138_Z<?> n_1700_B;
        private final long J_1907_R;

        public J_1907_R(t_3138_Z<?> packet, long time) {
            this.n_1700_B = packet;
            this.J_1907_R = time;
        }

        public t_3138_Z<?> n_1700_B() {
            return this.n_1700_B;
        }

        public long J_1907_R() {
            return this.J_1907_R;
        }
    }

    public class n_1700_B {
        private final c_1514_x n_1700_B;
        private final b_257_Y J_1907_R;

        public n_1700_B(O_3035_j this$0, c_1514_x position, b_257_Y facing) {
            this.n_1700_B = position;
            this.J_1907_R = facing;
        }

        public c_1514_x n_1700_B() {
            return this.n_1700_B;
        }

        public b_257_Y J_1907_R() {
            return this.J_1907_R;
        }
    }
}

