/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_719_q;
import lightning.product.D_4024_W;
import lightning.product.G_3416_z;
import lightning.product.H_2034_c;
import lightning.product.I_3710_B;
import lightning.product.N_4263_v;
import lightning.product.O_1309_Q;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.o_148_s;
import lightning.product.p_1977_n;
import lightning.product.r_3979_X;
import lightning.product.v_1900_v;
import lightning.product.w_2989_N;
import lightning.product.y_2603_k;

public class O_4101_I
extends X_3546_T {
    private final p_1977_n v_4262_N = new p_1977_n("\u0420\u0435\u0430\u0433\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u043d\u0430 \u0432\u0430\u0441", false);
    private final p_1977_n w_1484_f = new p_1977_n("\u0410\u0432\u0442\u043e GPS \u0434\u043e \u043f\u0451\u0440\u043b\u0430", false);
    private boolean t_148_a = true;
    private a_3913_L s_956_w = null;
    private double u_2550_I = 0.0;
    private double M_588_G = 0.0;
    private boolean P_4830_p = false;

    public O_4101_I() {
        super("PearlLogger", y_2603_k.G_564_y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f);
    }

    @Override
    public void n_1700_B() {
        this.t_148_a = true;
        this.s_956_w = null;
        this.P_4830_p = false;
        this.u_2550_I = 0.0;
        this.M_588_G = 0.0;
        super.n_1700_B();
    }

    @Override
    public void J_1907_R() {
        if (this.P_4830_p && O_1309_Q.J_1907_R && Math.abs(O_1309_Q.R_4764_Y - this.u_2550_I) < 0.1 && Math.abs(O_1309_Q.G_564_y - this.M_588_G) < 0.1) {
            O_1309_Q.J_1907_R = false;
            O_1309_Q.R_4764_Y = 0.0;
            O_1309_Q.G_564_y = 0.0;
        }
        this.P_4830_p = false;
        super.J_1907_R();
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        r_3979_X attackAura;
        if (O_4101_I.c_3005_b.Y_601_j == null || O_4101_I.c_3005_b.Y_259_p == null) {
            return;
        }
        B_719_q pearl = null;
        for (N_4263_v entity : O_4101_I.c_3005_b.Y_601_j.J_1907_R()) {
            if (!(entity instanceof w_2989_N)) continue;
            pearl = (w_2989_N)entity;
            break;
        }
        if (pearl == null) {
            this.t_148_a = true;
            this.s_956_w = null;
            return;
        }
        a_3913_L thrower = null;
        N_4263_v owner = pearl.Y_601_j();
        if (owner instanceof a_3913_L) {
            thrower = (a_3913_L)owner;
        } else {
            for (a_3913_L a_3913_L2 : O_4101_I.c_3005_b.Y_601_j.N_4405_n()) {
                if (thrower != null && !(a_3913_L2.G_564_y(pearl) < thrower.G_564_y(pearl))) continue;
                thrower = a_3913_L2;
            }
        }
        if (thrower == null) {
            return;
        }
        if (thrower == O_4101_I.c_3005_b.Y_259_p && !this.v_4262_N.t_148_a().booleanValue()) {
            return;
        }
        if (!this.t_148_a && thrower == this.s_956_w) {
            return;
        }
        e_2866_D landingPos = this.n_1700_B((w_2989_N)pearl);
        String string = String.valueOf((Object)D_4024_W.Q_4569_t) + String.valueOf((int)thrower.O_3598_v()) + " " + (int)thrower.X_2960_b() + " " + (int)thrower.l_2647_k();
        String toCoords = String.valueOf((Object)D_4024_W.h_1847_R) + String.valueOf((int)landingPos.n_1700_B()) + " " + (int)landingPos.J_1907_R() + " " + (int)landingPos.R_4764_Y();
        boolean isFriend = o_148_s.Y_601_j().v_4262_N().R_4764_Y(thrower.O_1309_Q().getString());
        String nameColored = String.valueOf((Object)(isFriend ? D_4024_W.u_2550_I : D_4024_W.P_4830_p)) + thrower.O_1309_Q().getString();
        String message = nameColored + String.valueOf((Object)D_4024_W.M_182_A) + " \u043a\u0438\u043d\u0443\u043b \u043f\u0435\u0440\u043b \u0438\u0437 " + string + String.valueOf((Object)D_4024_W.M_182_A) + " \u0432 " + toCoords;
        if (this.t_148_a) {
            v_1900_v.n_1700_B(message, new Object[0]);
            this.t_148_a = false;
            this.s_956_w = thrower;
            if (this.w_1484_f.t_148_a().booleanValue() && (attackAura = o_148_s.Y_601_j().J_1907_R().n_1700_B) != null && attackAura.w_1484_f() && attackAura.h_1847_R() != null && thrower == attackAura.h_1847_R()) {
                this.u_2550_I = landingPos.n_1700_B();
                this.M_588_G = landingPos.R_4764_Y();
                O_1309_Q.J_1907_R = true;
                O_1309_Q.R_4764_Y = this.u_2550_I;
                O_1309_Q.G_564_y = this.M_588_G;
                this.P_4830_p = true;
            }
        }
        if (this.P_4830_p && this.w_1484_f.t_148_a().booleanValue() && O_1309_Q.J_1907_R) {
            attackAura = o_148_s.Y_601_j().J_1907_R().n_1700_B;
            if (attackAura != null && attackAura.h_1847_R() != null && this.s_956_w != null && attackAura.h_1847_R() != this.s_956_w) {
                O_1309_Q.J_1907_R = false;
                O_1309_Q.R_4764_Y = 0.0;
                O_1309_Q.G_564_y = 0.0;
                this.P_4830_p = false;
                return;
            }
            double playerX = O_4101_I.c_3005_b.Y_259_p.O_3598_v();
            double playerZ = O_4101_I.c_3005_b.Y_259_p.l_2647_k();
            double distance = Math.sqrt(Math.pow(this.u_2550_I - playerX, 2.0) + Math.pow(this.M_588_G - playerZ, 2.0));
            if (distance <= 5.0) {
                O_1309_Q.J_1907_R = false;
                O_1309_Q.R_4764_Y = 0.0;
                O_1309_Q.G_564_y = 0.0;
                this.P_4830_p = false;
            }
        }
    }

    private e_2866_D n_1700_B(w_2989_N pearl) {
        e_2866_D pearlPosition = pearl.s_4990_V();
        e_2866_D pearlMotion = pearl.I_4348_c();
        e_2866_D lastPosition = pearlPosition;
        for (int i = 0; i <= 300; ++i) {
            lastPosition = pearlPosition;
            pearlPosition = pearlPosition.P_1922_E(pearlMotion);
            e_2866_D motionUpdated = pearlMotion;
            if (pearl.a_2180_A() || O_4101_I.c_3005_b.Y_601_j.getBlockState(new c_1514_x(pearlPosition)).J_1907_R() == a_3742_W.c_3005_b) {
                float scale = pearl instanceof w_2989_N ? 0.8f : 0.6f;
                motionUpdated = motionUpdated.n_1700_B((double)scale);
            } else {
                motionUpdated = motionUpdated.n_1700_B((double)0.99f);
            }
            if (!pearl.u_744_e()) {
                motionUpdated = motionUpdated.n_1700_B(0.0, pearl instanceof w_2989_N ? 0.03 : 0.05, 0.0);
            }
            pearlMotion = motionUpdated;
            H_2034_c rayTraceContext = new H_2034_c(lastPosition, pearlPosition, H_2034_c.n_1700_B.n_1700_B, H_2034_c.J_1907_R.n_1700_B, O_4101_I.c_3005_b.Y_259_p);
            G_3416_z blockHitResult = O_4101_I.c_3005_b.Y_601_j.n_1700_B(rayTraceContext);
            if (blockHitResult.R_4764_Y() != I_3710_B.n_1700_B.J_1907_R && !(pearlPosition.R_4764_Y <= 0.0)) continue;
            return blockHitResult.P_1922_E();
        }
        return lastPosition;
    }
}

