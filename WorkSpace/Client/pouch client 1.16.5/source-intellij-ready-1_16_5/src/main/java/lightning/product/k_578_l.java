/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_1603_w;
import lightning.product.H_1468_N;
import lightning.product.H_2506_c;
import lightning.product.Q_2753_H;
import lightning.product.U_3758_B;
import lightning.product.W_3491_f;
import lightning.product.Y_1740_V;
import lightning.product.a_2900_S;
import lightning.product.a_408_T;
import lightning.product.MinecraftAccess;
import lightning.product.h_1015_G;
import lightning.product.ClientboundChatPacket;
import lightning.product.k_2603_m;
import lightning.product.q_3115_L;
import lightning.product.r_2090_h;
import lightning.product.Packet;
import lightning.product.z_3427_G;

public class k_578_l
implements MinecraftAccess {
    private static final String n_1700_B = "\u0412\u044b\u0431\u043e\u0440 \u041b\u0430\u0439\u0442 \u0430\u043d\u0430\u0440\u0445\u0438\u0438";
    private static final int J_1907_R = 18;
    private final A_1603_w R_4764_Y = new A_1603_w();
    private boolean G_564_y;
    private int P_1922_E;
    private long u_1723_Y;
    private boolean v_4262_N;
    private long w_1484_f;
    private static final long t_148_a = 90000L;

    private static boolean n_1700_B(String title) {
        return H_1468_N.n_1700_B(title).contains(n_1700_B);
    }

    private static int n_1700_B(a_2900_S container, W_3491_f playerInv) {
        int n = 0;
        for (int i = 0; i < container.P_1922_E.size(); ++i) {
            if (container.P_1922_E.get((int)i).R_4764_Y == playerInv) continue;
            ++n;
        }
        return n;
    }

    private static int J_1907_R(a_2900_S c, W_3491_f inv) {
        int firstPlayer = Integer.MAX_VALUE;
        for (int i = 0; i < c.P_1922_E.size(); ++i) {
            if (c.P_1922_E.get((int)i).R_4764_Y != inv) continue;
            firstPlayer = Math.min(firstPlayer, i);
        }
        if (firstPlayer == Integer.MAX_VALUE) {
            return c.P_1922_E.size() - 1;
        }
        return firstPlayer - 1;
    }

    private static int n_1700_B(int targetAnarchy, a_2900_S menu, W_3491_f inv) {
        if (targetAnarchy < 1 || targetAnarchy > 63) {
            return -1;
        }
        int slot = 18 + (targetAnarchy - 1);
        int lastMenu = k_578_l.J_1907_R(menu, inv);
        if (slot > lastMenu) {
            return -1;
        }
        return slot;
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
        Packet<?> t_3138_Z2;
        if (this.P_1922_E == 0 || !e.J_1907_R() || !((t_3138_Z2 = e.G_564_y()) instanceof ClientboundChatPacket)) {
            return;
        }
        ClientboundChatPacket message = (ClientboundChatPacket)t_3138_Z2;
        String text = message.J_1907_R().getString().toLowerCase();
        if (!text.contains("\u0445\u0430\u0431") && text.contains("\u043d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c")) {
            U_3758_B.n_1700_B("[RCT]", "\u041d\u0430 \u0434\u0430\u043d\u043d\u0443\u044e \u0430\u043d\u0430\u0440\u0445\u0438\u044e \u043d\u0435\u043b\u044c\u0437\u044f \u0437\u0430\u0439\u0442\u0438", H_2506_c.n_1700_B(255, 85, 85));
            this.P_1922_E = 0;
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (this.P_1922_E == 0) {
            return;
        }
        if (k_578_l.c_3005_b.Y_259_p == null) {
            return;
        }
        boolean liteMenuOpen = false;
        k_2603_m k_2603_m2 = k_578_l.c_3005_b.Y_1740_V;
        if (k_2603_m2 instanceof z_3427_G) {
            z_3427_G cs = (z_3427_G)k_2603_m2;
            liteMenuOpen = k_578_l.n_1700_B(cs.getTitle().getString());
        }
        if (liteMenuOpen && !this.v_4262_N) {
            this.u_1723_Y = 0L;
        }
        if (!q_3115_L.u_1723_Y()) {
            this.P_1922_E = 0;
            this.v_4262_N = false;
            return;
        }
        if (this.P_1922_E != 0 && System.currentTimeMillis() > this.w_1484_f) {
            U_3758_B.n_1700_B("[RCT]", "\u0422\u0430\u0439\u043c\u0430\u0443\u0442 \u0440\u0435\u043a\u043e\u043d\u043d\u0435\u043a\u0442\u0430", H_2506_c.n_1700_B(255, 160, 60));
            this.P_1922_E = 0;
            this.v_4262_N = false;
            return;
        }
        int currentAnarchy = q_3115_L.v_4262_N();
        if (this.G_564_y) {
            if (currentAnarchy == -1) {
                this.G_564_y = false;
                this.u_1723_Y = 0L;
                this.R_4764_Y.n_1700_B();
            } else {
                k_578_l.c_3005_b.Y_259_p.n_1700_B("/hub");
            }
            return;
        }
        if (currentAnarchy == this.P_1922_E) {
            this.P_1922_E = 0;
            this.v_4262_N = liteMenuOpen;
            return;
        }
        if (!liteMenuOpen && this.v_4262_N && !this.G_564_y) {
            int afterClose = q_3115_L.v_4262_N();
            if (afterClose == this.P_1922_E) {
                this.P_1922_E = 0;
            }
            this.v_4262_N = false;
            if (this.P_1922_E == 0) {
                return;
            }
        }
        if (liteMenuOpen) {
            int inMenuAnarchy = q_3115_L.v_4262_N();
            if (inMenuAnarchy == this.P_1922_E) {
                this.P_1922_E = 0;
                this.v_4262_N = liteMenuOpen;
                return;
            }
            z_3427_G screen = (z_3427_G)k_578_l.c_3005_b.Y_1740_V;
            Object menu = screen.n_();
            W_3491_f inv = k_578_l.c_3005_b.Y_259_p.l_1268_F;
            int nonPlayer = k_578_l.n_1700_B(menu, inv);
            long now = System.currentTimeMillis();
            if (now < this.u_1723_Y) {
                this.v_4262_N = liteMenuOpen;
                return;
            }
            if (nonPlayer <= 12) {
                int[] nArray;
                if (this.P_1922_E < 15) {
                    int[] nArray2 = new int[2];
                    nArray2[0] = 0;
                    nArray = nArray2;
                    nArray2[1] = 0;
                } else if (this.P_1922_E < 33) {
                    int[] nArray3 = new int[2];
                    nArray3[0] = 1;
                    nArray = nArray3;
                    nArray3[1] = 14;
                } else if (this.P_1922_E < 48) {
                    int[] nArray4 = new int[2];
                    nArray4[0] = 2;
                    nArray = nArray4;
                    nArray4[1] = 32;
                } else {
                    int[] nArray5 = new int[2];
                    nArray5[0] = 3;
                    nArray = nArray5;
                    nArray5[1] = 47;
                }
                int[] slots = nArray;
                boolean secondScreen = nonPlayer < 10;
                int slot = secondScreen ? slots[0] : 17 + this.P_1922_E - slots[1];
                r_2090_h.n_1700_B(slot, 0, a_408_T.n_1700_B, false);
                this.u_1723_Y = now + 200L;
                this.v_4262_N = liteMenuOpen;
                return;
            }
            int slot = k_578_l.n_1700_B(this.P_1922_E, menu, inv);
            if (slot >= 18) {
                r_2090_h.n_1700_B(slot, 0, a_408_T.n_1700_B, false);
            }
            this.u_1723_Y = now + 220L;
            this.v_4262_N = liteMenuOpen;
            return;
        }
        if (this.R_4764_Y.n_1700_B(500L)) {
            k_578_l.c_3005_b.Y_259_p.n_1700_B("/lite");
        }
        this.v_4262_N = liteMenuOpen;
    }

    public void n_1700_B(int anarchy) {
        if (anarchy > 0 && anarchy < 64) {
            this.P_1922_E = anarchy;
            this.G_564_y = true;
            this.u_1723_Y = 0L;
            this.w_1484_f = System.currentTimeMillis() + 90000L;
            this.R_4764_Y.n_1700_B();
        } else {
            U_3758_B.n_1700_B("[RCT]", "\u041d\u0435 \u0432\u0435\u0440\u043d\u044b\u0439 \u043b\u0430\u0439\u0442", H_2506_c.n_1700_B(255, 85, 85));
        }
    }
}



