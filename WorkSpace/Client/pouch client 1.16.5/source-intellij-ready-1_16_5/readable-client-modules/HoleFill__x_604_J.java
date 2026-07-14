/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.G_3416_z;
import lightning.product.H_2506_c;
import lightning.product.I_4477_R;
import lightning.product.I_4817_s;
import lightning.product.I_686_h;
import lightning.product.N_4263_v;
import lightning.product.P_3504_Q;
import lightning.product.T_2915_h;
import lightning.product.V_3354_l;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.h_2367_h;
import lightning.product.l_3747_P;
import lightning.product.m_2262_U;
import lightning.product.o_148_s;
import lightning.product.p_1183_T;
import lightning.product.p_1977_n;
import lightning.product.q_1613_l;
import lightning.product.q_366_O;
import lightning.product.q_4592_V;
import lightning.product.u_530_F;
import lightning.product.v_1669_V;
import lightning.product.x_1688_C;
import lightning.product.y_2603_k;
import org.lwjgl.opengl.GL11;

public class x_604_J
extends X_3546_T {
    private final q_366_O v_4262_N = new q_366_O("\u0410\u0432\u0442\u043e-\u0441\u0432\u0438\u0442\u0447", "Silent", "None", "Normal", "Silent");
    private final q_366_O w_1484_f = new q_366_O("\u0420\u0435\u0436\u0438\u043c", "Smart", "Normal", "Smart");
    private final p_1977_n t_148_a = new p_1977_n("\u041f\u0430\u0443\u0442\u0438\u043d\u0430", false);
    private final p_1977_n s_956_w = new p_1977_n("\u0410\u0441\u0438\u043d\u0445\u0440\u043e\u043d\u043d\u043e", true);
    private final I_686_h u_2550_I = new I_686_h("\u0411\u043b\u043e\u043a\u043e\u0432/\u0442\u0438\u043a", 1.0f, 1.0f, 20.0f, 1.0f);
    private final I_686_h M_588_G = new I_686_h("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430", 0.0f, 0.0f, 20.0f, 1.0f);
    private final I_686_h P_4830_p = new I_686_h("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", 5.0f, 0.0f, 12.0f, 0.5f);
    private final I_686_h h_1847_R = new I_686_h("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0432\u0440\u0430\u0433\u0430", 8.0f, 0.0f, 16.0f, 0.5f);
    private final I_686_h Q_4569_t = new I_686_h("Smart \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", 3.0f, 0.0f, 6.0f, 0.5f);
    private final p_1977_n M_182_A = new p_1977_n("\u0411\u0435\u0437\u043e\u043f\u0430\u0441\u043d\u043e\u0441\u0442\u044c", true);
    private final I_686_h t_1786_h = new I_686_h("\u0411\u0435\u0437\u043e\u043f\u0430\u0441\u043d\u0430\u044f \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", 2.0f, 0.0f, 6.0f, 0.5f);
    private final p_1977_n N_4405_n = new p_1977_n("\u0420\u043e\u0442\u0430\u0446\u0438\u044f", true);
    private final p_1977_n w_1457_N = new p_1977_n("\u0421\u0442\u0440\u043e\u0433\u043e\u0435 \u043d\u0430\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435", false);
    private final p_1977_n Y_601_j = new p_1977_n("\u041b\u043e\u043c\u0430\u0442\u044c \u043a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u044b", true);
    private final p_1977_n Y_259_p = new p_1977_n("\u0414\u0432\u043e\u0439\u043d\u044b\u0435 \u0434\u044b\u0440\u044b", false);
    private final p_1977_n Q_2552_b = new p_1977_n("\u041f\u0440\u043e\u0432\u0435\u0440\u043a\u0430 \u0434\u044b\u0440\u044b", true);
    private final p_1977_n C_2741_M = new p_1977_n("\u041f\u0440\u0438 \u0435\u0434\u0435", true);
    private final p_1977_n k_2293_S = new p_1977_n("\u0410\u0432\u0442\u043e-\u0432\u044b\u043a\u043b.", false);
    private final p_1977_n q_2307_F = new p_1977_n("\u0412\u044b\u043a\u043b. \u0431\u0435\u0437 \u0431\u043b\u043e\u043a\u043e\u0432", true);
    private final p_1977_n Z_875_P = new p_1977_n("\u041e\u0442\u0440\u0438\u0441\u043e\u0432\u043a\u0430", true);
    private final h_2367_h t_4043_B = new h_2367_h("\u0426\u0432\u0435\u0442", true, new Color(255, 100, 0, 100).getRGB(), this.Z_875_P::t_148_a);
    private final ExecutorService x_607_J = Executors.newSingleThreadExecutor();
    private List<c_1514_x> e_4240_b = new ArrayList<c_1514_x>();
    private P_3504_Q n_3318_d;
    private boolean d_2427_y = false;
    private int z_1737_N = 0;
    private int v_4276_D = 0;

    public x_604_J() {
        super("HoleFill", y_2603_k.n_1700_B);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G, this.P_4830_p, this.h_1847_R, this.Q_4569_t, this.M_182_A, this.t_1786_h, this.N_4405_n, this.w_1457_N, this.Y_601_j, this.Y_259_p, this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F, this.Z_875_P, this.t_4043_B);
    }

    @Override
    public void J_1907_R() {
        this.e_4240_b.clear();
        this.n_3318_d = null;
        this.d_2427_y = false;
        this.z_1737_N = 0;
        this.v_4276_D = 0;
        super.J_1907_R();
    }

    @Y_1740_V
    public void n_1700_B(m_2262_U e) {
        if (this.d_2427_y && this.n_3318_d != null && this.N_4405_n.t_148_a().booleanValue()) {
            e.n_1700_B(this.n_3318_d.t_148_a);
            e.J_1907_R(this.n_3318_d.s_956_w);
            x_604_J.c_3005_b.Y_259_p.f_3449_S = this.n_3318_d.t_148_a;
            x_604_J.c_3005_b.Y_259_p.C_1162_e = this.n_3318_d.t_148_a;
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (x_604_J.c_3005_b.Y_259_p == null || x_604_J.c_3005_b.Y_601_j == null) {
            return;
        }
        if (!this.C_2741_M.t_148_a().booleanValue() && x_604_J.c_3005_b.Y_259_p.Y_601_j()) {
            return;
        }
        Runnable runnable = () -> {
            n_1700_B target;
            boolean needBlock;
            this.v_4276_D = 0;
            if (this.z_1737_N < ((Float)this.M_588_G.J_1907_R()).intValue()) {
                ++this.z_1737_N;
                return;
            }
            boolean bl = this.t_148_a.t_148_a().booleanValue() ? x_604_J.c_3005_b.Y_259_p.A_2714_y().J_1907_R() != q_4592_V.D_1056_T : (needBlock = !(x_604_J.c_3005_b.Y_259_p.A_2714_y().J_1907_R() instanceof v_1669_V));
            if (this.v_4262_N.J_1907_R("None") && needBlock) {
                if (this.q_2307_F.t_148_a().booleanValue()) {
                    this.R_4764_Y();
                }
                this.e_4240_b = new ArrayList<c_1514_x>();
                return;
            }
            int slot = this.Q_4569_t();
            int previousSlot = x_604_J.c_3005_b.Y_259_p.l_1268_F.G_564_y;
            if (slot == -1) {
                if (this.q_2307_F.t_148_a().booleanValue()) {
                    this.R_4764_Y();
                }
                this.e_4240_b = new ArrayList<c_1514_x>();
                return;
            }
            this.e_4240_b = this.w_1484_f.J_1907_R("Smart") ? ((target = this.h_1847_R()) == null ? new ArrayList<c_1514_x>() : target.J_1907_R) : this.n_1700_B((a_3913_L)null);
            if (this.e_4240_b.isEmpty()) {
                if (this.k_2293_S.t_148_a().booleanValue()) {
                    this.R_4764_Y();
                }
                return;
            }
            c_3005_b.execute(() -> {
                this.n_1700_B(slot, previousSlot);
                for (c_1514_x position : this.e_4240_b) {
                    if (this.v_4276_D >= ((Float)this.u_2550_I.J_1907_R()).intValue()) break;
                    b_257_Y direction = this.G_564_y(position);
                    if (direction == null) continue;
                    this.n_1700_B(position, direction);
                    ++this.v_4276_D;
                }
                this.J_1907_R(slot, previousSlot);
            });
            this.z_1737_N = 0;
        };
        if (this.s_956_w.t_148_a().booleanValue()) {
            this.x_607_J.submit(runnable);
        } else {
            runnable.run();
        }
        this.d_2427_y = false;
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R event) {
        if (x_604_J.c_3005_b.Y_259_p == null || x_604_J.c_3005_b.Y_601_j == null) {
            return;
        }
        if (!this.Z_875_P.t_148_a().booleanValue()) {
            return;
        }
        if (this.e_4240_b.isEmpty()) {
            return;
        }
        double renderX = c_3005_b.O_508_d().renderPosX();
        double renderY = c_3005_b.O_508_d().renderPosY();
        double renderZ = c_3005_b.O_508_d().renderPosZ();
        c_4037_x.v_4276_D();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.t_1786_h();
        c_4037_x.e_4240_b();
        c_4037_x.q_2307_F();
        int color = (Integer)this.t_4043_B.J_1907_R();
        float r = (float)H_2506_c.n_1700_B(color) / 255.0f;
        float g = (float)H_2506_c.J_1907_R(color) / 255.0f;
        float b = (float)H_2506_c.R_4764_Y(color) / 255.0f;
        float a = (float)H_2506_c.G_564_y(color) / 255.0f;
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r buffer = tessellator.R_4764_Y();
        for (c_1514_x pos : this.e_4240_b) {
            I_4817_s box = new I_4817_s(pos);
            I_4817_s renderBox = box.offset(-renderX, -renderY, -renderZ);
            buffer.n_1700_B(7, E_688_b.Y_601_j);
            this.n_1700_B(buffer, renderBox, r, g, b, a * 0.3f);
            tessellator.J_1907_R();
            c_4037_x.G_564_y(2.0f);
            GL11.glEnable((int)2848);
            buffer.n_1700_B(1, E_688_b.Y_601_j);
            this.J_1907_R(buffer, renderBox, r, g, b, a);
            tessellator.J_1907_R();
            GL11.glDisable((int)2848);
        }
        c_4037_x.k_2293_S();
        c_4037_x.x_607_J();
        c_4037_x.N_4405_n();
        c_4037_x.Y_259_p();
        c_4037_x.d_2461_k();
    }

    private n_1700_B h_1847_R() {
        n_1700_B optimalTarget = null;
        for (a_3913_L a_3913_L2 : x_604_J.c_3005_b.Y_601_j.N_4405_n()) {
            List<c_1514_x> positions;
            if (a_3913_L2 == x_604_J.c_3005_b.Y_259_p || !a_3913_L2.H_3699_F() || a_3913_L2.g_46_E() <= 0.0f || x_604_J.c_3005_b.Y_259_p.R_4764_Y((N_4263_v)a_3913_L2) > ((Float)this.h_1847_R.J_1907_R()).floatValue() || o_148_s.Y_601_j().v_4262_N().R_4764_Y(a_3913_L2.y_4642_Y().getName()) || this.Q_2552_b.t_148_a().booleanValue() && this.J_1907_R(a_3913_L2) || (positions = this.n_1700_B(a_3913_L2)).isEmpty()) continue;
            if (optimalTarget == null) {
                optimalTarget = new n_1700_B(a_3913_L2, positions);
                continue;
            }
            if (!(x_604_J.c_3005_b.Y_259_p.R_4764_Y((N_4263_v)a_3913_L2) < x_604_J.c_3005_b.Y_259_p.R_4764_Y((N_4263_v)optimalTarget.n_1700_B))) continue;
            optimalTarget = new n_1700_B(a_3913_L2, positions);
        }
        return optimalTarget;
    }

    private List<c_1514_x> n_1700_B(a_3913_L player) {
        ArrayList<c_1514_x> positions = new ArrayList<c_1514_x>();
        c_1514_x playerPos = x_604_J.c_3005_b.Y_259_p.b_2312_j();
        int rangeInt = (int)Math.ceil(((Float)this.P_4830_p.J_1907_R()).doubleValue());
        for (int x = -rangeInt; x <= rangeInt; ++x) {
            for (int y = -2; y <= 1; ++y) {
                for (int z = -rangeInt; z <= rangeInt; ++z) {
                    c_1514_x position = playerPos.add(x, y, z);
                    e_2866_D vec = e_2866_D.n_1700_B(position);
                    if (!x_604_J.c_3005_b.Y_601_j.getBlockState(position).R_4764_Y().P_1922_E() || x_604_J.c_3005_b.Y_259_p.v_4262_N(vec.J_1907_R, vec.R_4764_Y, vec.G_564_y) > (double)(((Float)this.P_4830_p.J_1907_R()).floatValue() * ((Float)this.P_4830_p.J_1907_R()).floatValue()) || this.w_1484_f.J_1907_R("Smart") && player != null && player.v_4262_N(vec.J_1907_R, vec.R_4764_Y, vec.G_564_y) > (double)(((Float)this.Q_4569_t.J_1907_R()).floatValue() * ((Float)this.Q_4569_t.J_1907_R()).floatValue()) || this.M_182_A.t_148_a().booleanValue() && !this.J_1907_R(x_604_J.c_3005_b.Y_259_p) && x_604_J.c_3005_b.Y_259_p.v_4262_N(vec.J_1907_R, vec.R_4764_Y, vec.G_564_y) <= (double)(((Float)this.t_1786_h.J_1907_R()).floatValue() * ((Float)this.t_1786_h.J_1907_R()).floatValue()) || !this.n_1700_B(position) && (!this.Y_259_p.t_148_a().booleanValue() || !this.J_1907_R(position)) || !this.R_4764_Y(position)) continue;
                    positions.add(position);
                }
            }
        }
        positions.sort(Comparator.comparingDouble(pos -> x_604_J.c_3005_b.Y_259_p.v_4262_N((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5)));
        return positions;
    }

    private boolean n_1700_B(c_1514_x pos) {
        if (!x_604_J.c_3005_b.Y_601_j.u_1723_Y(pos)) {
            return false;
        }
        T_2915_h below = x_604_J.c_3005_b.Y_601_j.getBlockState(pos.down()).J_1907_R();
        if (below != a_3742_W.o_148_s && below != a_3742_W.Z_875_P) {
            return false;
        }
        int solidSides = 0;
        for (b_257_Y dir : b_257_Y.R_4764_Y.n_1700_B) {
            T_2915_h side = x_604_J.c_3005_b.Y_601_j.getBlockState(pos.offset(dir)).J_1907_R();
            if (side != a_3742_W.o_148_s && side != a_3742_W.Z_875_P) continue;
            ++solidSides;
        }
        return solidSides >= 4;
    }

    private boolean J_1907_R(c_1514_x pos) {
        if (!x_604_J.c_3005_b.Y_601_j.u_1723_Y(pos)) {
            return false;
        }
        T_2915_h below = x_604_J.c_3005_b.Y_601_j.getBlockState(pos.down()).J_1907_R();
        if (below != a_3742_W.o_148_s && below != a_3742_W.Z_875_P) {
            return false;
        }
        for (b_257_Y dir : b_257_Y.R_4764_Y.n_1700_B) {
            T_2915_h adjacentBelow;
            c_1514_x adjacent = pos.offset(dir);
            if (!x_604_J.c_3005_b.Y_601_j.u_1723_Y(adjacent) || (adjacentBelow = x_604_J.c_3005_b.Y_601_j.getBlockState(adjacent.down()).J_1907_R()) != a_3742_W.o_148_s && adjacentBelow != a_3742_W.Z_875_P) continue;
            int solidSides = 0;
            for (b_257_Y checkDir : b_257_Y.R_4764_Y.n_1700_B) {
                if (checkDir == dir.u_1723_Y()) continue;
                T_2915_h side1 = x_604_J.c_3005_b.Y_601_j.getBlockState(pos.offset(checkDir)).J_1907_R();
                T_2915_h side2 = x_604_J.c_3005_b.Y_601_j.getBlockState(adjacent.offset(checkDir)).J_1907_R();
                if (side1 != a_3742_W.o_148_s && side1 != a_3742_W.Z_875_P || side2 != a_3742_W.o_148_s && side2 != a_3742_W.Z_875_P) continue;
                ++solidSides;
            }
            T_2915_h end1 = x_604_J.c_3005_b.Y_601_j.getBlockState(pos.offset(dir.u_1723_Y())).J_1907_R();
            T_2915_h end2 = x_604_J.c_3005_b.Y_601_j.getBlockState(adjacent.offset(dir)).J_1907_R();
            if (!(end1 != a_3742_W.o_148_s && end1 != a_3742_W.Z_875_P || end2 != a_3742_W.o_148_s && end2 != a_3742_W.Z_875_P)) {
                ++solidSides;
            }
            if (solidSides < 3) continue;
            return true;
        }
        return false;
    }

    private boolean J_1907_R(a_3913_L player) {
        c_1514_x pos = player.b_2312_j();
        return this.n_1700_B(pos);
    }

    private boolean R_4764_Y(c_1514_x pos) {
        return this.G_564_y(pos) != null;
    }

    private b_257_Y G_564_y(c_1514_x pos) {
        for (b_257_Y dir : b_257_Y.values()) {
            c_1514_x supportPos = pos.offset(dir);
            if (x_604_J.c_3005_b.Y_601_j.u_1723_Y(supportPos) || !x_604_J.c_3005_b.Y_601_j.getBlockState(supportPos).M_588_G()) continue;
            if (this.w_1457_N.t_148_a().booleanValue()) {
                e_2866_D playerPos = x_604_J.c_3005_b.Y_259_p.u_2550_I(1.0f);
                e_2866_D blockCenter = e_2866_D.n_1700_B(supportPos);
                e_2866_D dirVec = new e_2866_D(dir.u_1723_Y().t_148_a(), dir.u_1723_Y().s_956_w(), dir.u_1723_Y().u_2550_I());
                if (playerPos.G_564_y(blockCenter).J_1907_R(dirVec) <= 0.0) continue;
            }
            return dir;
        }
        return null;
    }

    private void n_1700_B(c_1514_x pos, b_257_Y dir) {
        if (this.Y_601_j.t_148_a().booleanValue()) {
            I_4817_s box = new I_4817_s(pos);
            for (N_4263_v entity : x_604_J.c_3005_b.Y_601_j.n_1700_B((N_4263_v)null, box)) {
                if (!(entity instanceof V_3354_l)) continue;
                x_604_J.c_3005_b.w_1457_N.attackEntity(x_604_J.c_3005_b.Y_259_p, entity);
                x_604_J.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
            }
        }
        if (this.N_4405_n.t_148_a().booleanValue()) {
            this.n_1700_B((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5);
        }
        c_1514_x supportPos = pos.offset(dir);
        G_3416_z result = new G_3416_z(e_2866_D.n_1700_B(supportPos).J_1907_R((double)dir.u_1723_Y().t_148_a() * 0.5, (double)dir.u_1723_Y().s_956_w() * 0.5, (double)dir.u_1723_Y().u_2550_I() * 0.5), dir.u_1723_Y(), supportPos, false);
        x_604_J.c_3005_b.w_1457_N.func_217292_a(x_604_J.c_3005_b.Y_259_p, x_604_J.c_3005_b.Y_601_j, x_1688_C.n_1700_B, result);
        x_604_J.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
    }

    private void n_1700_B(double x, double y, double z) {
        e_2866_D eyePos = x_604_J.c_3005_b.Y_259_p.u_2550_I(1.0f);
        double diffX = x - eyePos.J_1907_R;
        double diffY = y - eyePos.R_4764_Y;
        double diffZ = z - eyePos.G_564_y;
        float yaw = (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0);
        float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, Math.hypot(diffX, diffZ))));
        this.n_3318_d = new P_3504_Q(yaw, u_530_F.n_1700_B(pitch, -90.0f, 90.0f));
        this.d_2427_y = true;
    }

    private int Q_4569_t() {
        if (this.t_148_a.t_148_a().booleanValue()) {
            for (int i = 0; i < 9; ++i) {
                if (x_604_J.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).J_1907_R() != q_4592_V.D_1056_T) continue;
                return i;
            }
        } else {
            q_1613_l item;
            int i;
            for (i = 0; i < 9; ++i) {
                item = x_604_J.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).J_1907_R();
                if (item != q_4592_V.d_2545_n && item != q_4592_V.N_260_m && item != q_4592_V.S_4896_C) continue;
                return i;
            }
            for (i = 0; i < 9; ++i) {
                item = x_604_J.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).J_1907_R();
                if (!(item instanceof v_1669_V)) continue;
                return i;
            }
        }
        return -1;
    }

    private void n_1700_B(int slot, int previousSlot) {
        if (slot == previousSlot) {
            return;
        }
        switch ((String)this.v_4262_N.J_1907_R()) {
            case "Normal": {
                x_604_J.c_3005_b.Y_259_p.l_1268_F.G_564_y = slot;
                break;
            }
            case "Silent": {
                x_604_J.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(slot));
            }
        }
    }

    private void J_1907_R(int slot, int previousSlot) {
        if (slot == previousSlot) {
            return;
        }
        switch ((String)this.v_4262_N.J_1907_R()) {
            case "Normal": {
                break;
            }
            case "Silent": {
                x_604_J.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(previousSlot));
            }
        }
    }

    private void n_1700_B(D_3318_r buffer, I_4817_s box, float r, float g, float b, float a) {
        float x0 = (float)box.minX;
        float x1 = (float)box.maxX;
        float y0 = (float)box.minY;
        float y1 = (float)box.maxY;
        float z0 = (float)box.minZ;
        float z1 = (float)box.maxZ;
        buffer.pos(x0, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z1).n_1700_B(r, g, b, a).endVertex();
    }

    private void J_1907_R(D_3318_r buffer, I_4817_s box, float r, float g, float b, float a) {
        float x0 = (float)box.minX;
        float x1 = (float)box.maxX;
        float y0 = (float)box.minY;
        float y1 = (float)box.maxY;
        float z0 = (float)box.minZ;
        float z1 = (float)box.maxZ;
        buffer.pos(x0, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z0).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x1, y1, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y0, z1).n_1700_B(r, g, b, a).endVertex();
        buffer.pos(x0, y1, z1).n_1700_B(r, g, b, a).endVertex();
    }

    private static class n_1700_B {
        a_3913_L n_1700_B;
        List<c_1514_x> J_1907_R;

        n_1700_B(a_3913_L player, List<c_1514_x> positions) {
            this.n_1700_B = player;
            this.J_1907_R = positions;
        }
    }
}

