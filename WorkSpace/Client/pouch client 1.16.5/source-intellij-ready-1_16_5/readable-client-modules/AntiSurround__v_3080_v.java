/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lightning.product.F_1446_q;
import lightning.product.I_686_h;
import lightning.product.J_588_u;
import lightning.product.K_4074_S;
import lightning.product.K_4096_w;
import lightning.product.N_4263_v;
import lightning.product.P_3504_Q;
import lightning.product.T_2915_h;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_2152_i;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.d_2992_c;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.j_4680_H;
import lightning.product.m_2262_U;
import lightning.product.o_148_s;
import lightning.product.p_1183_T;
import lightning.product.p_1977_n;
import lightning.product.q_366_O;
import lightning.product.r_3979_X;
import lightning.product.r_4790_y;
import lightning.product.s_2612_h;
import lightning.product.u_530_F;
import lightning.product.x_1688_C;
import lightning.product.y_2603_k;

public class v_3080_v
extends X_3546_T {
    private final I_686_h v_4262_N = new I_686_h("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", 4.0f, 1.0f, 6.0f, 0.1f);
    private final I_686_h w_1484_f = new I_686_h("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", 1.0f, 0.1f, 1.0f, 0.05f);
    private final q_366_O t_148_a = new q_366_O("\u0420\u0435\u0436\u0438\u043c", "\u0412\u043e\u043a\u0440\u0443\u0433 \u0441\u0435\u0431\u044f", "\u0412\u043e\u043a\u0440\u0443\u0433 \u0441\u0435\u0431\u044f", "\u0412\u043e\u043a\u0440\u0443\u0433 \u0446\u0435\u043b\u0438", "\u041e\u0431\u0430");
    private final q_366_O s_956_w = new q_366_O("\u0420\u043e\u0442\u0430\u0446\u0438\u044f", "\u041f\u043b\u0430\u0432\u043d\u0430\u044f", "\u041f\u043b\u0430\u0432\u043d\u0430\u044f", "\u041c\u0433\u043d\u043e\u0432\u0435\u043d\u043d\u0430\u044f", "\u041e\u0442\u043a\u043b\u044e\u0447\u0435\u043d\u0430");
    private final p_1977_n u_2550_I = new p_1977_n("\u0422\u043e\u043b\u044c\u043a\u043e \u043e\u0431\u0441\u0438\u0434\u0438\u0430\u043d", false);
    private final p_1977_n M_588_G = new p_1977_n("\u0410\u0432\u0442\u043e-\u0441\u0432\u0438\u0442\u0447 \u043a\u0438\u0440\u043a\u0438", true);
    private final p_1977_n P_4830_p = new p_1977_n("\u0421\u0432\u0438\u043d\u0433", true);
    private final p_1977_n h_1847_R = new p_1977_n("\u0422\u043e\u043b\u044c\u043a\u043e \u043a\u043e\u0433\u0434\u0430 \u0437\u0430\u0431\u043b\u043e\u043a\u0438\u0440\u043e\u0432\u0430\u043d", true);
    private final p_1977_n Q_4569_t = new p_1977_n("\u041b\u043e\u043c\u0430\u0442\u044c \u043d\u0430\u0434 \u0433\u043e\u043b\u043e\u0432\u043e\u0439", true);
    private n_1700_B M_182_A = null;
    private boolean t_1786_h = false;
    private P_3504_Q N_4405_n = null;

    public v_3080_v() {
        super("AntiSurround", y_2603_k.n_1700_B);
        this.n_1700_B(this.t_148_a, this.v_4262_N, this.w_1484_f, this.s_956_w, this.u_2550_I, this.M_588_G, this.P_4830_p, this.h_1847_R, this.Q_4569_t);
    }

    @Override
    public void J_1907_R() {
        if (this.M_182_A != null) {
            this.M_182_A.J_1907_R();
            this.M_182_A = null;
        }
        this.t_1786_h = false;
        this.N_4405_n = null;
        super.J_1907_R();
    }

    @Y_1740_V
    public void n_1700_B(m_2262_U e) {
        if (this.t_1786_h && this.N_4405_n != null && !this.s_956_w.J_1907_R("\u041e\u0442\u043a\u043b\u044e\u0447\u0435\u043d\u0430")) {
            e.n_1700_B(this.N_4405_n.t_148_a);
            e.J_1907_R(this.N_4405_n.s_956_w);
            v_3080_v.c_3005_b.Y_259_p.f_3449_S = this.N_4405_n.t_148_a;
            v_3080_v.c_3005_b.Y_259_p.C_1162_e = this.N_4405_n.t_148_a;
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        a_3913_L target;
        if (v_3080_v.c_3005_b.Y_259_p == null || v_3080_v.c_3005_b.Y_601_j == null) {
            return;
        }
        this.t_1786_h = false;
        if (this.h_1847_R.t_148_a().booleanValue() && !this.J_1907_R(v_3080_v.c_3005_b.Y_259_p)) {
            this.Q_4569_t();
            return;
        }
        ArrayList<c_1514_x> targets = new ArrayList<c_1514_x>();
        if (this.t_148_a.J_1907_R("\u0412\u043e\u043a\u0440\u0443\u0433 \u0441\u0435\u0431\u044f") || this.t_148_a.J_1907_R("\u041e\u0431\u0430")) {
            targets.addAll(this.n_1700_B(v_3080_v.c_3005_b.Y_259_p));
        }
        if ((this.t_148_a.J_1907_R("\u0412\u043e\u043a\u0440\u0443\u0433 \u0446\u0435\u043b\u0438") || this.t_148_a.J_1907_R("\u041e\u0431\u0430")) && (target = this.t_1786_h()) != null) {
            targets.addAll(this.n_1700_B(target));
        }
        if (targets.isEmpty()) {
            this.Q_4569_t();
            return;
        }
        targets.sort(Comparator.comparingDouble(pos -> v_3080_v.c_3005_b.Y_259_p.s_4990_V().R_4764_Y((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5)));
        c_1514_x best = (c_1514_x)targets.get(0);
        if (this.M_182_A == null || !this.M_182_A.n_1700_B.equals(best)) {
            this.Q_4569_t();
            this.M_182_A = new n_1700_B(best);
        }
        this.n_1700_B((double)best.getX() + 0.5, (double)best.getY() + 0.5, (double)best.getZ() + 0.5);
        if (this.M_182_A.n_1700_B()) {
            this.M_182_A = null;
        }
    }

    private float n_1700_B(K_4074_S state, int slotIndex) {
        int eff;
        Z_1993_T stack = v_3080_v.c_3005_b.Y_259_p.l_1268_F.s_956_w(slotIndex);
        float sp = stack.n_1700_B(state);
        if (sp > 1.0f && (eff = K_4096_w.n_1700_B(d_2992_c.Y_601_j, stack)) > 0) {
            sp += (float)(eff * eff + 1);
        }
        if (v_3080_v.c_3005_b.Y_259_p.J_1907_R(J_588_u.R_4764_Y)) {
            sp *= 1.0f + (float)(v_3080_v.c_3005_b.Y_259_p.R_4764_Y(J_588_u.R_4764_Y).R_4764_Y() + 1) * 0.2f;
        }
        if (v_3080_v.c_3005_b.Y_259_p.J_1907_R(J_588_u.G_564_y)) {
            float[] fatPenalty = new float[]{0.3f, 0.09f, 0.0027f, 8.1E-4f};
            int amp = v_3080_v.c_3005_b.Y_259_p.R_4764_Y(J_588_u.G_564_y).R_4764_Y();
            sp *= fatPenalty[Math.min(amp, 3)];
        }
        if (v_3080_v.c_3005_b.Y_259_p.a_2180_A() && K_4096_w.n_1700_B(d_2992_c.v_4262_N, v_3080_v.c_3005_b.Y_259_p.l_1268_F.J_1907_R.get(3)) == 0) {
            sp /= 5.0f;
        }
        if (!v_3080_v.c_3005_b.Y_259_p.M_1641_O()) {
            sp /= 5.0f;
        }
        return Math.max(sp, 0.0f);
    }

    private void Q_4569_t() {
        if (this.M_182_A != null) {
            this.M_182_A.J_1907_R();
            this.M_182_A = null;
        }
    }

    private List<c_1514_x> n_1700_B(a_3913_L player) {
        c_1514_x head;
        ArrayList<c_1514_x> blocks = new ArrayList<c_1514_x>();
        c_1514_x pos = player.b_2312_j();
        for (b_257_Y dir : b_257_Y.R_4764_Y.n_1700_B) {
            c_1514_x side = pos.offset(dir);
            if (!this.n_1700_B(side)) continue;
            blocks.add(side);
        }
        if (this.Q_4569_t.t_148_a().booleanValue() && this.n_1700_B(head = pos.up(2))) {
            blocks.add(head);
        }
        blocks.removeIf(p -> v_3080_v.c_3005_b.Y_259_p.s_4990_V().R_4764_Y((double)p.getX() + 0.5, (double)p.getY() + 0.5, (double)p.getZ() + 0.5) > (double)(((Float)this.v_4262_N.J_1907_R()).floatValue() * ((Float)this.v_4262_N.J_1907_R()).floatValue()));
        return blocks;
    }

    private boolean n_1700_B(c_1514_x pos) {
        K_4074_S state = v_3080_v.c_3005_b.Y_601_j.getBlockState(pos);
        T_2915_h block = state.J_1907_R();
        if (block == a_3742_W.Z_875_P) {
            return false;
        }
        if (state.v_4262_N()) {
            return false;
        }
        if (this.u_2550_I.t_148_a().booleanValue()) {
            return block == a_3742_W.o_148_s || block == a_3742_W.t_1528_W || block == a_3742_W.k_2348_i;
        }
        return state.R_4764_Y().J_1907_R();
    }

    private boolean J_1907_R(a_3913_L player) {
        c_1514_x pos = player.b_2312_j();
        for (b_257_Y dir : b_257_Y.R_4764_Y.n_1700_B) {
            if (v_3080_v.c_3005_b.Y_601_j.u_1723_Y(pos.offset(dir))) continue;
            return true;
        }
        return !v_3080_v.c_3005_b.Y_601_j.u_1723_Y(pos.up(2));
    }

    private void n_1700_B(double x, double y, double z) {
        if (this.s_956_w.J_1907_R("\u041e\u0442\u043a\u043b\u044e\u0447\u0435\u043d\u0430")) {
            return;
        }
        e_2866_D eye = v_3080_v.c_3005_b.Y_259_p.u_2550_I(1.0f);
        double dx = x - eye.J_1907_R;
        double dy = y - eye.R_4764_Y;
        double dz = z - eye.G_564_y;
        float yaw = (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(dz, dx)) - 90.0);
        float pitch = (float)(-Math.toDegrees(Math.atan2(dy, Math.hypot(dx, dz))));
        if (this.s_956_w.J_1907_R("\u041f\u043b\u0430\u0432\u043d\u0430\u044f")) {
            r_4790_y.n_1700_B(new F_1446_q(yaw, pitch), 15.0f, 10, 6);
        }
        this.N_4405_n = new P_3504_Q(yaw, u_530_F.n_1700_B(pitch, -90.0f, 90.0f));
        this.t_1786_h = true;
    }

    private b_257_Y J_1907_R(c_1514_x pos) {
        e_2866_D playerPos = v_3080_v.c_3005_b.Y_259_p.s_4990_V();
        e_2866_D center = new e_2866_D((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5);
        e_2866_D diff = playerPos.G_564_y(center);
        b_257_Y best = b_257_Y.J_1907_R;
        double maxDot = Double.NEGATIVE_INFINITY;
        for (b_257_Y dir : b_257_Y.values()) {
            double dot = diff.J_1907_R * (double)dir.t_148_a() + diff.R_4764_Y * (double)dir.s_956_w() + diff.G_564_y * (double)dir.u_2550_I();
            if (!(dot > maxDot)) continue;
            maxDot = dot;
            best = dir;
        }
        return best;
    }

    private int M_182_A() {
        int bestSlot = -1;
        float bestEff = -1.0f;
        for (int i = 0; i < 9; ++i) {
            int enchEff;
            float eff;
            float total;
            Z_1993_T stack = v_3080_v.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (!(stack.J_1907_R() instanceof s_2612_h) || !((total = (eff = ((s_2612_h)stack.J_1907_R()).w_1484_f().J_1907_R()) + (float)((enchEff = K_4096_w.n_1700_B(d_2992_c.Y_601_j, stack)) * enchEff)) > bestEff)) continue;
            bestEff = total;
            bestSlot = i;
        }
        return bestSlot;
    }

    private a_3913_L t_1786_h() {
        r_3979_X aura = o_148_s.Y_601_j().J_1907_R().J_1907_R();
        if (aura != null && aura.w_1484_f() && aura.h_1847_R() instanceof a_3913_L) {
            return (a_3913_L)aura.h_1847_R();
        }
        return v_3080_v.c_3005_b.Y_601_j.N_4405_n().stream().filter(p -> p != v_3080_v.c_3005_b.Y_259_p && p.H_3699_F() && !p.d_2461_k()).filter(p -> v_3080_v.c_3005_b.Y_259_p.R_4764_Y((N_4263_v)p) <= ((Float)this.v_4262_N.J_1907_R()).floatValue()).filter(p -> !o_148_s.Y_601_j().v_4262_N().R_4764_Y(p.y_4642_Y().getName())).min(Comparator.comparingDouble(p -> v_3080_v.c_3005_b.Y_259_p.R_4764_Y((N_4263_v)p))).orElse(null);
    }

    public c_1514_x h_1847_R() {
        return this.M_182_A != null ? this.M_182_A.n_1700_B : null;
    }

    private class n_1700_B {
        final c_1514_x n_1700_B;
        private final K_4074_S R_4764_Y;
        private float G_564_y = 0.0f;
        private final int P_1922_E;
        private final int u_1723_Y;
        private final boolean v_4262_N;

        n_1700_B(c_1514_x pos) {
            int found;
            this.n_1700_B = pos;
            this.R_4764_Y = b_2152_i.c_3005_b.Y_601_j.getBlockState(pos);
            this.u_1723_Y = b_2152_i.c_3005_b.Y_259_p.l_1268_F.G_564_y;
            int n = found = v_3080_v.this.M_588_G.t_148_a() != false ? v_3080_v.this.M_182_A() : -1;
            if (found != -1 && found != this.u_1723_Y) {
                b_2152_i.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(found));
                this.P_1922_E = found;
                this.v_4262_N = true;
            } else {
                this.P_1922_E = this.u_1723_Y;
                this.v_4262_N = false;
            }
            this.R_4764_Y();
        }

        private void R_4764_Y() {
            b_257_Y face = v_3080_v.this.J_1907_R(this.n_1700_B);
            b_2152_i.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new j_4680_H(j_4680_H.n_1700_B.n_1700_B, this.n_1700_B, face));
            b_2152_i.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new j_4680_H(j_4680_H.n_1700_B.J_1907_R, this.n_1700_B, face));
            if (v_3080_v.this.P_4830_p.t_148_a().booleanValue()) {
                b_2152_i.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
            }
            this.G_564_y = 0.0f;
        }

        boolean n_1700_B() {
            if (b_2152_i.c_3005_b.Y_601_j.u_1723_Y(this.n_1700_B)) {
                this.G_564_y();
                return true;
            }
            float hardness = this.R_4764_Y.w_1484_f(b_2152_i.c_3005_b.Y_601_j, this.n_1700_B);
            if (hardness < 0.0f) {
                return false;
            }
            float delta = v_3080_v.this.n_1700_B(this.R_4764_Y, this.P_1922_E) / hardness / 30.0f;
            this.G_564_y += delta;
            if (this.G_564_y >= ((Float)v_3080_v.this.w_1484_f.J_1907_R()).floatValue()) {
                b_257_Y face = v_3080_v.this.J_1907_R(this.n_1700_B);
                b_2152_i.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new j_4680_H(j_4680_H.n_1700_B.R_4764_Y, this.n_1700_B, face));
                if (v_3080_v.this.P_4830_p.t_148_a().booleanValue()) {
                    b_2152_i.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
                }
                this.G_564_y();
                return true;
            }
            return false;
        }

        void J_1907_R() {
            b_257_Y face = v_3080_v.this.J_1907_R(this.n_1700_B);
            b_2152_i.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new j_4680_H(j_4680_H.n_1700_B.J_1907_R, this.n_1700_B, face));
            this.P_1922_E();
        }

        private void G_564_y() {
            this.P_1922_E();
        }

        private void P_1922_E() {
            if (this.v_4262_N) {
                b_2152_i.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(this.u_1723_Y));
            }
        }
    }
}

