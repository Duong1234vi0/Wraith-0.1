/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 */
package lightning.product;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.UUID;
import lightning.product.A_4115_X;
import lightning.product.D_38_f;
import lightning.product.D_4024_W;
import lightning.product.MobEffects;
import lightning.product.L_461_d;
import lightning.product.N_4263_v;
import lightning.product.Q_1187_u;
import lightning.product.U_2871_b;
import lightning.product.V_4217_p;
import lightning.product.SoundEvents;
import lightning.product.Y_1740_V;
import lightning.product.Z_1567_W;
import lightning.product.MinecraftAccess;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.h_2739_B;
import lightning.product.i_4434_b;
import lightning.product.k_2610_C;
import lightning.product.o_2341_D;
import lightning.product.v_1900_v;
import lightning.product.x_1688_C;

public class P_5000_x
extends o_2341_D
implements MinecraftAccess {
    private static P_5000_x J_1907_R;
    private Q_1187_u R_4764_Y;
    private float G_564_y = 0.0f;
    private float P_1922_E = 0.0f;
    private boolean u_1723_Y = false;

    public P_5000_x() {
        super("fakeplayer", "fp");
        J_1907_R = this;
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(ctx -> {
            v_1900_v.n_1700_B(new U_2871_b(".fakeplayer add - \u0441\u043e\u0437\u0434\u0430\u0442\u044c \u0444\u0435\u0439\u043a \u0438\u0433\u0440\u043e\u043a\u0430").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)), new Object[0]);
            v_1900_v.n_1700_B(new U_2871_b(".fakeplayer del - \u0443\u0434\u0430\u043b\u0438\u0442\u044c \u0444\u0435\u0439\u043a \u0438\u0433\u0440\u043e\u043a\u0430").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)), new Object[0]);
            v_1900_v.n_1700_B(new U_2871_b("\u0423\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435 \u0441\u0442\u0440\u0435\u043b\u043a\u0430\u043c\u0438 \u2190 \u2191 \u2192 \u2193").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.t_148_a)), new Object[0]);
            return 1;
        });
        builder.then(P_5000_x.n_1700_B("add").executes(ctx -> {
            this.J_1907_R();
            return 1;
        }));
        builder.then(P_5000_x.n_1700_B("del").executes(ctx -> {
            this.R_4764_Y();
            return 1;
        }));
        builder.then(P_5000_x.n_1700_B("remove").executes(ctx -> {
            this.R_4764_Y();
            return 1;
        }));
    }

    public void J_1907_R() {
        if (P_5000_x.c_3005_b.Y_259_p == null || P_5000_x.c_3005_b.Y_601_j == null) {
            v_1900_v.n_1700_B(new U_2871_b("\u041c\u0438\u0440 \u043d\u0435 \u0437\u0430\u0433\u0440\u0443\u0436\u0435\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p)), new Object[0]);
            return;
        }
        if (this.R_4764_Y != null) {
            P_5000_x.c_3005_b.Y_601_j.n_1700_B(this.R_4764_Y.j_276_v());
            this.R_4764_Y = null;
        }
        GameProfile profile = new GameProfile(UUID.fromString("66123666-6666-6666-6666-666666666600"), "FakePlayer");
        this.R_4764_Y = new Q_1187_u(P_5000_x.c_3005_b.Y_601_j, profile);
        this.R_4764_Y.n_1700_B(P_5000_x.c_3005_b.Y_259_p.O_3598_v(), P_5000_x.c_3005_b.Y_259_p.X_2960_b(), P_5000_x.c_3005_b.Y_259_p.l_2647_k(), P_5000_x.c_3005_b.Y_259_p.p_178_J, P_5000_x.c_3005_b.Y_259_p.f_4016_n);
        this.R_4764_Y.n_1700_B(x_1688_C.n_1700_B, P_5000_x.c_3005_b.Y_259_p.A_2714_y().t_148_a());
        this.R_4764_Y.n_1700_B(x_1688_C.J_1907_R, P_5000_x.c_3005_b.Y_259_p.S_4035_N().t_148_a());
        this.R_4764_Y.n_1700_B(e_1174_E.u_1723_Y, P_5000_x.c_3005_b.Y_259_p.J_1907_R(e_1174_E.u_1723_Y).t_148_a());
        this.R_4764_Y.n_1700_B(e_1174_E.P_1922_E, P_5000_x.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E).t_148_a());
        this.R_4764_Y.n_1700_B(e_1174_E.G_564_y, P_5000_x.c_3005_b.Y_259_p.J_1907_R(e_1174_E.G_564_y).t_148_a());
        this.R_4764_Y.n_1700_B(e_1174_E.R_4764_Y, P_5000_x.c_3005_b.Y_259_p.J_1907_R(e_1174_E.R_4764_Y).t_148_a());
        this.R_4764_Y.n_1700_B(new k_2610_C(MobEffects.s_956_w, 999999, 2));
        this.R_4764_Y.n_1700_B(new k_2610_C(MobEffects.Q_2552_b, 999999, 4));
        this.R_4764_Y.n_1700_B(new k_2610_C(MobEffects.u_2550_I, 999999, 1));
        this.R_4764_Y.t_1786_h(20.0f);
        this.R_4764_Y.Y_259_p(20.0f);
        P_5000_x.c_3005_b.Y_601_j.n_1700_B(this.R_4764_Y.j_276_v(), (N_4263_v)this.R_4764_Y);
        if (!this.u_1723_Y) {
            A_4115_X.n_1700_B(this);
            this.u_1723_Y = true;
        }
        v_1900_v.n_1700_B(new U_2871_b("\u0424\u0435\u0439\u043a \u0438\u0433\u0440\u043e\u043a \u0441\u043e\u0437\u0434\u0430\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.u_2550_I)), new Object[0]);
    }

    public void R_4764_Y() {
        if (this.R_4764_Y == null) {
            v_1900_v.n_1700_B(new U_2871_b("\u0424\u0435\u0439\u043a \u0438\u0433\u0440\u043e\u043a \u043d\u0435 \u0441\u0443\u0449\u0435\u0441\u0442\u0432\u0443\u0435\u0442!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p)), new Object[0]);
            return;
        }
        P_5000_x.c_3005_b.Y_601_j.n_1700_B(this.R_4764_Y.j_276_v());
        this.R_4764_Y = null;
        this.G_564_y = 0.0f;
        this.P_1922_E = 0.0f;
        v_1900_v.n_1700_B(new U_2871_b("\u0424\u0435\u0439\u043a \u0438\u0433\u0440\u043e\u043a \u0443\u0434\u0430\u043b\u0451\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.u_2550_I)), new Object[0]);
    }

    @Y_1740_V
    public void n_1700_B(h_2739_B event) {
        if (this.R_4764_Y == null || P_5000_x.c_3005_b.Y_259_p == null) {
            return;
        }
        if (event.J_1907_R() == this.R_4764_Y && this.R_4764_Y.RealmsLongRunningMcoTaskScreen == 0) {
            P_5000_x.c_3005_b.Y_601_j.n_1700_B(P_5000_x.c_3005_b.Y_259_p, this.R_4764_Y.O_3598_v(), this.R_4764_Y.X_2960_b(), this.R_4764_Y.l_2647_k(), SoundEvents.l_3729_r, D_38_f.w_1484_f, 1.0f, 1.0f);
            if (P_5000_x.c_3005_b.Y_259_p.U_1241_n > 0.0f) {
                P_5000_x.c_3005_b.Y_601_j.n_1700_B(P_5000_x.c_3005_b.Y_259_p, this.R_4764_Y.O_3598_v(), this.R_4764_Y.X_2960_b(), this.R_4764_Y.l_2647_k(), SoundEvents.h_3066_J, D_38_f.w_1484_f, 1.0f, 1.0f);
            } else {
                P_5000_x.c_3005_b.Y_601_j.n_1700_B(P_5000_x.c_3005_b.Y_259_p, this.R_4764_Y.O_3598_v(), this.R_4764_Y.X_2960_b(), this.R_4764_Y.l_2647_k(), SoundEvents.u_488_m, D_38_f.w_1484_f, 1.0f, 1.0f);
            }
            this.R_4764_Y.RealmsLongRunningMcoTaskScreen = 10;
            this.R_4764_Y.i_2993_w = 10;
            float newHealth = this.R_4764_Y.g_46_E() + this.R_4764_Y.U_3823_u() - 2.0f;
            if (newHealth <= 0.0f) {
                this.R_4764_Y.t_1786_h(20.0f);
                this.R_4764_Y.Y_259_p(20.0f);
                P_5000_x.c_3005_b.Y_601_j.n_1700_B(P_5000_x.c_3005_b.Y_259_p, this.R_4764_Y.O_3598_v(), this.R_4764_Y.X_2960_b(), this.R_4764_Y.l_2647_k(), SoundEvents.S_1431_H, D_38_f.w_1484_f, 1.0f, 1.0f);
            } else if (newHealth > 20.0f) {
                this.R_4764_Y.Y_259_p(newHealth - 20.0f);
                this.R_4764_Y.t_1786_h(20.0f);
            } else {
                this.R_4764_Y.Y_259_p(0.0f);
                this.R_4764_Y.t_1786_h(newHealth);
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(i_4434_b event) {
        if (this.R_4764_Y == null || P_5000_x.c_3005_b.Y_1740_V != null) {
            return;
        }
        int key = event.n_1700_B();
        boolean pressed = event.J_1907_R();
        if (key == 265) {
            this.G_564_y = pressed ? 1.0f : 0.0f;
        } else if (key == 264) {
            this.G_564_y = pressed ? -1.0f : 0.0f;
        } else if (key == 263) {
            this.P_1922_E = pressed ? 1.0f : 0.0f;
        } else if (key == 262) {
            this.P_1922_E = pressed ? -1.0f : 0.0f;
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (this.R_4764_Y == null || P_5000_x.c_3005_b.Y_259_p == null) {
            return;
        }
        if (this.G_564_y != 0.0f || this.P_1922_E != 0.0f) {
            float yaw = P_5000_x.c_3005_b.Y_259_p.p_178_J;
            double speed = 0.2;
            double motionX = (double)this.P_1922_E * Math.cos(Math.toRadians(yaw)) - (double)this.G_564_y * Math.sin(Math.toRadians(yaw));
            double motionZ = (double)this.G_564_y * Math.cos(Math.toRadians(yaw)) + (double)this.P_1922_E * Math.sin(Math.toRadians(yaw));
            e_2866_D velocity = new e_2866_D(motionX * speed, this.R_4764_Y.I_4348_c().R_4764_Y, motionZ * speed);
            this.R_4764_Y.v_4262_N(velocity);
            this.R_4764_Y.n_1700_B(L_461_d.n_1700_B, velocity);
            this.R_4764_Y.b_(true);
            if (this.G_564_y > 0.0f) {
                this.R_4764_Y.p_178_J = yaw;
            } else if (this.G_564_y < 0.0f) {
                this.R_4764_Y.p_178_J = yaw + 180.0f;
            }
        } else {
            this.R_4764_Y.b_(false);
            this.R_4764_Y.h_1847_R(0.0, this.R_4764_Y.I_4348_c().R_4764_Y, 0.0);
        }
        if (!this.R_4764_Y.M_1641_O()) {
            this.R_4764_Y.v_4262_N(this.R_4764_Y.I_4348_c().J_1907_R(0.0, -0.08, 0.0));
        }
    }

    public static P_5000_x G_564_y() {
        return J_1907_R;
    }

    public Q_1187_u P_1922_E() {
        return this.R_4764_Y;
    }
}



