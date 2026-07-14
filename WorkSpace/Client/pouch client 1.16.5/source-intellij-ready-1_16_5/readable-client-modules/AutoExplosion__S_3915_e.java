/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_1446_q;
import lightning.product.G_3416_z;
import lightning.product.I_3710_B;
import lightning.product.I_4817_s;
import lightning.product.I_686_h;
import lightning.product.N_4263_v;
import lightning.product.N_4463_r;
import lightning.product.T_2915_h;
import lightning.product.V_3354_l;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.a_1344_X;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.a_408_T;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.g_4560_H;
import lightning.product.h_1015_G;
import lightning.product.i_4434_b;
import lightning.product.n_1494_c;
import lightning.product.o_148_s;
import lightning.product.o_1800_r;
import lightning.product.p_1977_n;
import lightning.product.q_1613_l;
import lightning.product.q_3206_W;
import lightning.product.q_366_O;
import lightning.product.q_4592_V;
import lightning.product.r_4790_y;
import lightning.product.u_1934_K;
import lightning.product.u_530_F;
import lightning.product.x_1688_C;
import lightning.product.y_2603_k;

public class S_3915_e
extends X_3546_T {
    private c_1514_x v_4262_N;
    private int w_1484_f;
    private int t_148_a;
    private boolean s_956_w;
    private I_4817_s u_2550_I;
    private boolean M_588_G;
    private long P_4830_p;
    private N_4463_r h_1847_R = new N_4463_r("\u041d\u0435 \u0432\u0437\u0440\u044b\u0432\u0430\u0442\u044c", new p_1977_n("\u0421\u0435\u0431\u044f", true), new p_1977_n("\u0414\u0440\u0443\u0437\u0435\u0439", false), new p_1977_n("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b", true));
    private final I_686_h Q_4569_t = new I_686_h("\u0420\u0430\u0434\u0438\u0443\u0441 \u043f\u0440\u043e\u0432\u0435\u0440\u043a\u0438", 6.0f, 1.0f, 20.0f, 0.5f);
    private final p_1977_n M_182_A = new p_1977_n("\u0421\u0442\u0430\u0432\u0438\u0442\u044c \u043e\u0431\u0441\u0438\u0434\u0438\u0430\u043d \u0438 \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b", false);
    private final q_3206_W t_1786_h = new q_3206_W("\u0421\u0442\u0430\u0432\u0438\u0442\u044c \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b", () -> this.M_182_A.t_148_a() == false);
    private final q_3206_W N_4405_n = new q_3206_W("\u041e\u0431\u0441\u0438\u0434\u0438\u0430\u043d", () -> this.M_182_A.t_148_a());
    private final q_3206_W w_1457_N = new q_3206_W("\u041a\u0440\u0438\u0441\u0442\u0430\u043b\u043b", () -> this.M_182_A.t_148_a());
    private final q_366_O Y_601_j = new q_366_O("\u0418\u0441\u0442\u043e\u0447\u043d\u0438\u043a \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u0430", "\u0412 \u0440\u0443\u043a\u0443 (\u0445\u043e\u0442\u0431\u0430\u0440)", () -> this.M_182_A.t_148_a() == false, "\u0412 \u0440\u0443\u043a\u0443 (\u0445\u043e\u0442\u0431\u0430\u0440)", "\u041f\u0430\u043a\u0435\u0442\u043d\u044b\u0439 \u0441\u0432\u0430\u043f (\u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u044c)");

    public S_3915_e() {
        super("AutoExplosion", y_2603_k.n_1700_B);
        this.n_1700_B(this.h_1847_R, this.Q_4569_t, this.M_182_A, this.t_1786_h, this.N_4405_n, this.w_1457_N, this.Y_601_j);
    }

    @Y_1740_V
    public void n_1700_B(g_4560_H e) {
        if (e.J_1907_R() == a_3742_W.o_148_s) {
            if (S_3915_e.c_3005_b.Y_259_p.p_1458_L().n_1700_B(q_4592_V.m_2206_m)) {
                return;
            }
            int slotInHotBar = u_1934_K.n_1700_B(q_4592_V.m_2206_m);
            if (slotInHotBar == -1) {
                return;
            }
            if (slotInHotBar < 9) {
                this.v_4262_N = e.R_4764_Y();
                this.w_1484_f = slotInHotBar;
            }
        }
        this.M_588_G = true;
    }

    @Y_1740_V
    private void n_1700_B(h_1015_G e) {
        float targetPitch;
        float targetYaw;
        if (this.s_956_w) {
            this.s_956_w = false;
            S_3915_e.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.t_148_a;
            S_3915_e.c_3005_b.w_1457_N.syncCurrentPlayItem();
        } else if (this.v_4262_N != null) {
            if (S_3915_e.c_3005_b.Y_601_j.getBlockState(this.v_4262_N).v_4262_N()) {
                this.v_4262_N = null;
            } else if (this.M_588_G) {
                this.M_588_G = false;
            } else {
                if (!this.n_1700_B(this.v_4262_N)) {
                    this.v_4262_N = null;
                    return;
                }
                e_2866_D eyeVec = S_3915_e.c_3005_b.Y_259_p.u_2550_I(1.0f);
                e_2866_D hitVec = a_1344_X.n_1700_B(eyeVec, new I_4817_s(this.v_4262_N));
                e_2866_D offset = hitVec.G_564_y(eyeVec);
                targetYaw = (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(offset.G_564_y, offset.J_1907_R)) - 90.0);
                targetPitch = (float)(-Math.toDegrees(Math.atan2(offset.R_4764_Y, Math.hypot(offset.J_1907_R, offset.G_564_y))));
                r_4790_y.n_1700_B(new F_1446_q(targetYaw, targetPitch), 180.0f, 1, 6);
                this.t_148_a = S_3915_e.c_3005_b.Y_259_p.l_1268_F.G_564_y;
                S_3915_e.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.w_1484_f;
                S_3915_e.c_3005_b.w_1457_N.syncCurrentPlayItem();
                offset = offset.P_1922_E();
                S_3915_e.c_3005_b.w_1457_N.func_217292_a(S_3915_e.c_3005_b.Y_259_p, S_3915_e.c_3005_b.Y_601_j, x_1688_C.n_1700_B, new G_3416_z(hitVec, b_257_Y.n_1700_B(offset.J_1907_R, offset.R_4764_Y, offset.G_564_y), this.v_4262_N, false));
                S_3915_e.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
                this.s_956_w = true;
                this.v_4262_N = null;
            }
        }
        if (this.u_2550_I != null) {
            for (N_4263_v entity : S_3915_e.c_3005_b.Y_601_j.J_1907_R()) {
                if (!(entity instanceof V_3354_l) || !this.u_2550_I.contains(entity.s_4990_V())) continue;
                if (!this.n_1700_B(entity)) {
                    this.u_2550_I = null;
                    return;
                }
                if (!entity.i_601_W().contains(S_3915_e.c_3005_b.Y_259_p.u_2550_I(1.0f))) {
                    e_2866_D direction = a_1344_X.n_1700_B(entity);
                    targetYaw = (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(direction.G_564_y, direction.J_1907_R)) - 90.0);
                    targetPitch = (float)(-Math.toDegrees(Math.atan2(direction.R_4764_Y, Math.hypot(direction.J_1907_R, direction.G_564_y))));
                    r_4790_y.n_1700_B(new F_1446_q(targetYaw, targetPitch), 180.0f, 1, 6);
                }
                S_3915_e.c_3005_b.w_1457_N.attackEntity(S_3915_e.c_3005_b.Y_259_p, entity);
                S_3915_e.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
                this.u_2550_I = null;
                return;
            }
        }
    }

    @Y_1740_V
    private void n_1700_B(o_1800_r e) {
        T_2915_h block;
        if (!(S_3915_e.c_3005_b.Y_259_p.p_1458_L().n_1700_B(q_4592_V.m_2206_m) || (block = e.R_4764_Y().getBlockState(e.P_1922_E().n_1700_B()).J_1907_R()) != a_3742_W.o_148_s && block != a_3742_W.Z_875_P)) {
            c_1514_x pos = e.P_1922_E().n_1700_B();
            if (!this.n_1700_B(pos)) {
                return;
            }
            this.u_2550_I = new I_4817_s(pos.up()).grow(0.1);
        }
    }

    @Y_1740_V
    private void n_1700_B(i_4434_b e) {
        if (e.J_1907_R()) {
            return;
        }
        if (S_3915_e.c_3005_b.Y_259_p == null || S_3915_e.c_3005_b.Y_601_j == null) {
            return;
        }
        if (S_3915_e.c_3005_b.Y_1740_V != null) {
            return;
        }
        if (this.M_182_A.t_148_a().booleanValue()) {
            int key = e.n_1700_B();
            if (key == (Integer)this.w_1457_N.J_1907_R()) {
                this.J_1907_R(e);
                return;
            }
            if (key == (Integer)this.N_4405_n.J_1907_R()) {
                this.R_4764_Y(e);
                return;
            }
            return;
        }
        if ((Integer)this.t_1786_h.J_1907_R() == -1 || e.n_1700_B() != ((Integer)this.t_1786_h.J_1907_R()).intValue()) {
            return;
        }
        this.J_1907_R(e);
    }

    private void J_1907_R(i_4434_b e) {
        long now = System.currentTimeMillis();
        if (now - this.P_4830_p < 100L) {
            return;
        }
        this.P_4830_p = now;
        if (S_3915_e.c_3005_b.Z_875_P == null || S_3915_e.c_3005_b.Z_875_P.R_4764_Y() != I_3710_B.n_1700_B.J_1907_R) {
            return;
        }
        G_3416_z blockResult = (G_3416_z)S_3915_e.c_3005_b.Z_875_P;
        c_1514_x pos = blockResult.n_1700_B();
        T_2915_h block = S_3915_e.c_3005_b.Y_601_j.getBlockState(pos).J_1907_R();
        if (block != a_3742_W.o_148_s && block != a_3742_W.Z_875_P) {
            return;
        }
        c_1514_x abovePos = pos.up();
        if (!S_3915_e.c_3005_b.Y_601_j.getBlockState(abovePos).v_4262_N() || !S_3915_e.c_3005_b.Y_601_j.getBlockState(abovePos.up()).v_4262_N()) {
            return;
        }
        if (S_3915_e.c_3005_b.Y_259_p.p_1458_L().n_1700_B(q_4592_V.m_2206_m)) {
            return;
        }
        int crystalSlot = u_1934_K.n_1700_B(q_4592_V.m_2206_m);
        if (crystalSlot == -1) {
            return;
        }
        if (this.M_182_A.t_148_a().booleanValue()) {
            this.n_1700_B(crystalSlot, () -> this.n_1700_B(pos, b_257_Y.J_1907_R, x_1688_C.n_1700_B));
        } else if (this.Y_601_j.J_1907_R("\u0412 \u0440\u0443\u043a\u0443 (\u0445\u043e\u0442\u0431\u0430\u0440)")) {
            int oldSlotTemp = S_3915_e.c_3005_b.Y_259_p.l_1268_F.G_564_y;
            if (crystalSlot == oldSlotTemp) {
                this.n_1700_B(pos, b_257_Y.J_1907_R, x_1688_C.n_1700_B);
            } else {
                S_3915_e.c_3005_b.Y_259_p.l_1268_F.G_564_y = crystalSlot;
                S_3915_e.c_3005_b.w_1457_N.syncCurrentPlayItem();
                this.n_1700_B(pos, b_257_Y.J_1907_R, x_1688_C.n_1700_B);
                S_3915_e.c_3005_b.Y_259_p.l_1268_F.G_564_y = oldSlotTemp;
                S_3915_e.c_3005_b.w_1457_N.syncCurrentPlayItem();
            }
        } else {
            this.n_1700_B(crystalSlot, () -> this.n_1700_B(pos, b_257_Y.J_1907_R, x_1688_C.n_1700_B));
        }
        if (this.n_1700_B(pos)) {
            this.u_2550_I = new I_4817_s(pos.up()).grow(0.1);
        }
    }

    private void R_4764_Y(i_4434_b e) {
        b_257_Y face;
        if (S_3915_e.c_3005_b.Z_875_P == null || S_3915_e.c_3005_b.Z_875_P.R_4764_Y() != I_3710_B.n_1700_B.J_1907_R) {
            return;
        }
        G_3416_z blockResult = (G_3416_z)S_3915_e.c_3005_b.Z_875_P;
        c_1514_x hitPos = blockResult.n_1700_B();
        c_1514_x placePos = hitPos.offset(face = blockResult.J_1907_R());
        if (!S_3915_e.c_3005_b.Y_601_j.getBlockState(placePos).R_4764_Y().P_1922_E()) {
            return;
        }
        int obsidianSlot = u_1934_K.n_1700_B(q_4592_V.d_2545_n);
        if (obsidianSlot == -1) {
            return;
        }
        G_3416_z result = blockResult;
        this.n_1700_B(obsidianSlot, () -> {
            S_3915_e.c_3005_b.w_1457_N.func_217292_a(S_3915_e.c_3005_b.Y_259_p, S_3915_e.c_3005_b.Y_601_j, x_1688_C.n_1700_B, result);
            S_3915_e.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
        });
    }

    private void n_1700_B(int itemSlot, Runnable action) {
        int currentSlot = S_3915_e.c_3005_b.Y_259_p.l_1268_F.G_564_y;
        if (itemSlot < 9) {
            S_3915_e.c_3005_b.Y_259_p.l_1268_F.G_564_y = itemSlot;
            S_3915_e.c_3005_b.w_1457_N.syncCurrentPlayItem();
            action.run();
            S_3915_e.c_3005_b.Y_259_p.l_1268_F.G_564_y = currentSlot;
            S_3915_e.c_3005_b.w_1457_N.syncCurrentPlayItem();
        } else {
            int containerSlot = itemSlot;
            S_3915_e.c_3005_b.w_1457_N.windowClick(S_3915_e.c_3005_b.Y_259_p.o_1800_r.u_1723_Y, containerSlot, currentSlot, a_408_T.R_4764_Y, S_3915_e.c_3005_b.Y_259_p);
            action.run();
            S_3915_e.c_3005_b.w_1457_N.windowClick(S_3915_e.c_3005_b.Y_259_p.o_1800_r.u_1723_Y, containerSlot, currentSlot, a_408_T.R_4764_Y, S_3915_e.c_3005_b.Y_259_p);
        }
    }

    private void n_1700_B(c_1514_x pos, b_257_Y face, x_1688_C hand) {
        e_2866_D hitVec = new e_2866_D((double)pos.getX() + 0.5, (double)pos.getY() + 1.0, (double)pos.getZ() + 0.5);
        G_3416_z result = new G_3416_z(hitVec, face, pos, false);
        S_3915_e.c_3005_b.w_1457_N.func_217292_a(S_3915_e.c_3005_b.Y_259_p, S_3915_e.c_3005_b.Y_601_j, hand, result);
        S_3915_e.c_3005_b.Y_259_p.n_1700_B(hand);
    }

    private boolean n_1700_B(c_1514_x pos) {
        if (pos == null) {
            return false;
        }
        if (this.h_1847_R.J_1907_R("\u0421\u0435\u0431\u044f").booleanValue() && S_3915_e.c_3005_b.Y_259_p.X_2960_b() > (double)pos.getY()) {
            return false;
        }
        if (this.h_1847_R.J_1907_R("\u0414\u0440\u0443\u0437\u0435\u0439").booleanValue() && this.J_1907_R(pos)) {
            return false;
        }
        return this.h_1847_R.J_1907_R("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b") == false || !this.R_4764_Y(pos);
    }

    private boolean n_1700_B(N_4263_v crystal) {
        if (crystal == null) {
            return false;
        }
        c_1514_x crystalPos = new c_1514_x(crystal.s_4990_V());
        if (this.h_1847_R.J_1907_R("\u0414\u0440\u0443\u0437\u0435\u0439").booleanValue() && this.J_1907_R(crystalPos)) {
            return false;
        }
        return this.h_1847_R.J_1907_R("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b") == false || !this.R_4764_Y(crystalPos);
    }

    private boolean J_1907_R(c_1514_x pos) {
        if (S_3915_e.c_3005_b.Y_601_j == null || S_3915_e.c_3005_b.Y_259_p == null) {
            return false;
        }
        e_2866_D checkPos = new e_2866_D((double)pos.getX() + 0.5, pos.getY() + 1, (double)pos.getZ() + 0.5);
        double radius = ((Float)this.Q_4569_t.J_1907_R()).floatValue();
        double radiusSq = radius * radius;
        for (a_3913_L a_3913_L2 : S_3915_e.c_3005_b.Y_601_j.N_4405_n()) {
            double distanceSq;
            if (a_3913_L2 == S_3915_e.c_3005_b.Y_259_p || !o_148_s.Y_601_j().v_4262_N().R_4764_Y(String.valueOf(a_3913_L2)) || !((distanceSq = a_3913_L2.s_4990_V().v_4262_N(checkPos)) <= radiusSq)) continue;
            return true;
        }
        return false;
    }

    private boolean R_4764_Y(c_1514_x pos) {
        if (S_3915_e.c_3005_b.Y_601_j == null) {
            return false;
        }
        double radius = ((Float)this.Q_4569_t.J_1907_R()).floatValue();
        I_4817_s checkArea = new I_4817_s((double)pos.getX() - radius, (double)pos.getY() - radius, (double)pos.getZ() - radius, (double)pos.getX() + radius + 1.0, (double)pos.getY() + radius + 2.0, (double)pos.getZ() + radius + 1.0);
        for (N_4263_v entity : S_3915_e.c_3005_b.Y_601_j.n_1700_B((N_4263_v)null, checkArea)) {
            n_1494_c itemEntity;
            q_1613_l item;
            if (!(entity instanceof n_1494_c) || (item = (itemEntity = (n_1494_c)entity).P_1922_E().J_1907_R()) != q_4592_V.u_488_m && item != q_4592_V.O_1043_U && item != q_4592_V.v_1900_v && item != q_4592_V.j_2129_E && item != q_4592_V.q_4361_M && item != q_4592_V.f_508_U && item != q_4592_V.A_1603_w && item != q_4592_V.V_4557_X && item != q_4592_V.K_1200_E && item != q_4592_V.N_2592_G && item != q_4592_V.K_1964_I && item != q_4592_V.C_1577_A && item != q_4592_V.D_563_q && item != q_4592_V.s_1124_y && item != q_4592_V.O_922_L && item != q_4592_V.M_2029_A && item != q_4592_V.N_81_X && item != q_4592_V.m_2206_m && item != q_4592_V.E_4612_l && item != q_4592_V.p_863_D && item != q_4592_V.v_2746_S && item != q_4592_V.P_2605_j && item != q_4592_V.V_2454_J && item != q_4592_V.B_1548_Z) continue;
            return true;
        }
        return false;
    }
}

