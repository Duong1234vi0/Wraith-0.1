/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.I_686_h;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.o_148_s;
import lightning.product.p_1977_n;
import lightning.product.q_366_O;
import lightning.product.r_3979_X;
import lightning.product.r_4811_B;
import lightning.product.y_2603_k;
import lombok.Generated;

public class f_4762_w
extends X_3546_T {
    public final I_686_h v_4262_N = new I_686_h("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0430\u0442\u0430\u043a\u0438", 3.0f, 2.0f, 4.5f, 0.1f);
    public final I_686_h w_1484_f = new I_686_h("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043e\u0442\u043b\u0451\u0442\u0430", 8.0f, 5.0f, 12.0f, 0.5f);
    public final I_686_h t_148_a = new I_686_h("\u0423\u0433\u043e\u043b \u043e\u0442\u043b\u0451\u0442\u0430", 50.0f, 20.0f, 90.0f, 5.0f);
    public final q_366_O s_956_w = new q_366_O("\u0420\u0435\u0436\u0438\u043c \u043e\u0442\u043b\u0451\u0442\u0430", "\u0412 \u0441\u0442\u043e\u0440\u043e\u043d\u0443", "\u0412 \u0441\u0442\u043e\u0440\u043e\u043d\u0443", "\u0412\u0432\u0435\u0440\u0445", "\u0414\u0438\u0430\u0433\u043e\u043d\u0430\u043b\u044c");
    public final p_1977_n u_2550_I = new p_1977_n("\u0427\u0435\u0440\u0435\u0434\u043e\u0432\u0430\u0442\u044c \u0441\u0442\u043e\u0440\u043e\u043d\u044b", true);
    private n_1700_B M_588_G = lightning.product.f_4762_w$n_1700_B.n_1700_B;
    private r_4811_B P_4830_p = null;
    private e_2866_D h_1847_R = null;
    private e_2866_D Q_4569_t = null;
    private boolean M_182_A = false;
    private long t_1786_h = 0L;
    private String N_4405_n = null;

    public f_4762_w() {
        super("ElytraResolver", y_2603_k.J_1907_R);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (f_4762_w.c_3005_b.Y_259_p == null || f_4762_w.c_3005_b.Y_601_j == null || !f_4762_w.c_3005_b.Y_259_p.k_578_l()) {
            this.A_4115_X();
        }
    }

    public void n_1700_B(r_4811_B target, double distanceToTarget) {
        if (f_4762_w.c_3005_b.Y_259_p == null || !f_4762_w.c_3005_b.Y_259_p.k_578_l() || target == null) {
            this.A_4115_X();
            return;
        }
        this.P_4830_p = target;
        if (this.J_1907_R(target, distanceToTarget) && this.M_588_G == lightning.product.f_4762_w$n_1700_B.n_1700_B) {
            this.P_1922_E(target);
        }
        switch (this.M_588_G.ordinal()) {
            case 0: {
                if (!(distanceToTarget <= (double)((Float)this.v_4262_N.J_1907_R()).floatValue())) break;
                this.P_1922_E(target);
                break;
            }
            case 1: {
                if (distanceToTarget >= (double)((Float)this.w_1484_f.J_1907_R()).floatValue()) {
                    this.M_588_G = lightning.product.f_4762_w$n_1700_B.n_1700_B;
                    this.Q_4569_t = null;
                    this.t_1786_h = System.currentTimeMillis();
                }
                if (System.currentTimeMillis() - this.t_1786_h <= 2000L) break;
                this.M_588_G = lightning.product.f_4762_w$n_1700_B.n_1700_B;
                this.Q_4569_t = null;
            }
        }
        this.R_4764_Y(target);
    }

    private boolean J_1907_R(r_4811_B target, double distance) {
        e_2866_D toPlayer;
        if (distance > 4.0) {
            return false;
        }
        e_2866_D targetLook = target.i_1610_l();
        double dot = targetLook.J_1907_R(toPlayer = new e_2866_D(f_4762_w.c_3005_b.Y_259_p.V_118_c, f_4762_w.c_3005_b.Y_259_p.I_1407_m, f_4762_w.c_3005_b.Y_259_p.o_2767_H).G_564_y(this.J_1907_R(target)).G_564_y());
        return dot > 0.5 && distance < 3.5;
    }

    private void R_4764_Y(r_4811_B target) {
        e_2866_D targetPos = this.G_564_y(target);
        e_2866_D playerPos = new e_2866_D(f_4762_w.c_3005_b.Y_259_p.V_118_c, f_4762_w.c_3005_b.Y_259_p.I_1407_m, f_4762_w.c_3005_b.Y_259_p.o_2767_H);
        if (this.M_588_G == lightning.product.f_4762_w$n_1700_B.n_1700_B) {
            this.h_1847_R = targetPos.J_1907_R(0.0, (double)target.v_165_F() * 0.5, 0.0);
        } else {
            if (this.Q_4569_t == null) {
                this.Q_4569_t = this.n_1700_B(targetPos, playerPos);
            }
            double retDist = ((Float)this.w_1484_f.J_1907_R()).floatValue();
            double height = ((String)this.s_956_w.J_1907_R()).equals("\u0412\u0432\u0435\u0440\u0445") ? 6.0 : (((String)this.s_956_w.J_1907_R()).equals("\u0414\u0438\u0430\u0433\u043e\u043d\u0430\u043b\u044c") ? 3.0 : 1.5);
            this.h_1847_R = this.G_564_y(target).P_1922_E(this.Q_4569_t.n_1700_B(retDist)).J_1907_R(0.0, height, 0.0);
        }
    }

    private e_2866_D G_564_y(r_4811_B target) {
        e_2866_D pos = this.J_1907_R(target);
        e_2866_D vel = new e_2866_D(target.V_118_c - target.d_2545_n, target.I_1407_m - target.x_92_N, target.o_2767_H - target.i_601_W);
        return pos.P_1922_E(vel.n_1700_B(3.0));
    }

    private e_2866_D n_1700_B(e_2866_D targetPos, e_2866_D playerPos) {
        e_2866_D away = playerPos.G_564_y(targetPos);
        away = away.u_1723_Y() < 0.1 ? f_4762_w.c_3005_b.Y_259_p.i_1610_l().n_1700_B(-1.0) : away.G_564_y();
        double angle = ((Float)this.t_148_a.J_1907_R()).floatValue();
        if (this.u_2550_I.t_148_a().booleanValue()) {
            angle = this.M_182_A ? angle : -angle;
            this.M_182_A = !this.M_182_A;
        }
        double rad = Math.toRadians(angle);
        double newX = away.J_1907_R * Math.cos(rad) - away.G_564_y * Math.sin(rad);
        double newZ = away.J_1907_R * Math.sin(rad) + away.G_564_y * Math.cos(rad);
        return new e_2866_D(newX, switch ((String)this.s_956_w.J_1907_R()) {
            case "\u0412\u0432\u0435\u0440\u0445" -> 0.6;
            case "\u0414\u0438\u0430\u0433\u043e\u043d\u0430\u043b\u044c" -> 0.3;
            default -> 0.1;
        }, newZ).G_564_y();
    }

    private void P_1922_E(r_4811_B target) {
        this.M_588_G = lightning.product.f_4762_w$n_1700_B.J_1907_R;
        this.Q_4569_t = null;
        this.t_1786_h = System.currentTimeMillis();
    }

    public void n_1700_B(r_4811_B target) {
        if (this.M_588_G == lightning.product.f_4762_w$n_1700_B.n_1700_B) {
            this.P_1922_E(target);
        }
    }

    public e_2866_D J_1907_R(r_4811_B target) {
        return new e_2866_D(target.V_118_c, target.I_1407_m, target.o_2767_H);
    }

    public boolean h_1847_R() {
        return this.M_588_G == lightning.product.f_4762_w$n_1700_B.n_1700_B;
    }

    public boolean Q_4569_t() {
        return this.M_588_G == lightning.product.f_4762_w$n_1700_B.J_1907_R;
    }

    public boolean M_182_A() {
        return this.M_588_G == lightning.product.f_4762_w$n_1700_B.J_1907_R;
    }

    private void A_4115_X() {
        this.M_588_G = lightning.product.f_4762_w$n_1700_B.n_1700_B;
        this.P_4830_p = null;
        this.h_1847_R = null;
        this.Q_4569_t = null;
    }

    @Override
    public void J_1907_R() {
        this.A_4115_X();
        if (this.N_4405_n != null) {
            r_3979_X attackAura = o_148_s.Y_601_j().J_1907_R().J_1907_R();
            if (attackAura != null) {
                attackAura.Q_4569_t().n_1700_B(this.N_4405_n);
            }
            this.N_4405_n = null;
        }
        super.J_1907_R();
    }

    @Override
    public void n_1700_B() {
        this.A_4115_X();
        this.t_1786_h = 0L;
        r_3979_X attackAura = o_148_s.Y_601_j().J_1907_R().J_1907_R();
        if (attackAura != null) {
            this.N_4405_n = (String)attackAura.Q_4569_t().J_1907_R();
            attackAura.Q_4569_t().n_1700_B("Grim");
        }
        super.n_1700_B();
    }

    @Generated
    public I_686_h t_1786_h() {
        return this.v_4262_N;
    }

    @Generated
    public I_686_h N_4405_n() {
        return this.w_1484_f;
    }

    @Generated
    public I_686_h w_1457_N() {
        return this.t_148_a;
    }

    @Generated
    public q_366_O Y_601_j() {
        return this.s_956_w;
    }

    @Generated
    public p_1977_n Y_259_p() {
        return this.u_2550_I;
    }

    @Generated
    public n_1700_B Q_2552_b() {
        return this.M_588_G;
    }

    @Generated
    public r_4811_B C_2741_M() {
        return this.P_4830_p;
    }

    @Generated
    public e_2866_D k_2293_S() {
        return this.h_1847_R;
    }

    @Generated
    public e_2866_D q_2307_F() {
        return this.Q_4569_t;
    }

    @Generated
    public boolean Z_875_P() {
        return this.M_182_A;
    }

    @Generated
    public long c_3005_b() {
        return this.t_1786_h;
    }

    @Generated
    public String H_2857_Y() {
        return this.N_4405_n;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] R_4764_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])R_4764_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            R_4764_Y = lightning.product.f_4762_w$n_1700_B.n_1700_B();
        }
    }
}

