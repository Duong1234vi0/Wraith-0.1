/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.C_2741_M;
import lightning.product.N_4263_v;
import lightning.product.Y_601_j;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_869_m;
import lightning.product.n_1700_B;

public class Q_2552_b {
    private final n_1700_B n_1700_B;
    private static final double J_1907_R = 1.0;
    private final Random R_4764_Y = new Random();
    private int G_564_y = 0;
    private boolean P_1922_E = false;
    private final Y_601_j u_1723_Y = new Y_601_j();
    private final C_2741_M v_4262_N;

    public Q_2552_b(n_1700_B bot) {
        this.n_1700_B = bot;
        this.v_4262_N = new C_2741_M(this.u_1723_Y);
        if (bot != null && bot.R_4764_Y != null) {
            bot.R_4764_Y.Y_601_j = this.v_4262_N;
        }
    }

    public void n_1700_B(e_2866_D target) {
        float yaw;
        if (this.n_1700_B == null || this.n_1700_B.R_4764_Y == null) {
            return;
        }
        e_2866_D botPos = this.n_1700_B.R_4764_Y.s_4990_V();
        double deltaX = target.J_1907_R - botPos.J_1907_R;
        double deltaZ = target.G_564_y - botPos.G_564_y;
        double distance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
        double angle = Math.atan2(deltaZ, deltaX);
        this.n_1700_B.R_4764_Y.p_178_J = yaw = (float)Math.toDegrees(angle) - 90.0f;
        this.u_1723_Y.n_1700_B();
        this.u_1723_Y.n_1700_B(true);
        if (distance > 4.0) {
            this.u_1723_Y.v_4262_N(true);
        } else {
            this.u_1723_Y.v_4262_N(false);
        }
        this.u_1723_Y();
        this.v_4262_N.tickMovement(this.n_1700_B.R_4764_Y.c_3005_b());
    }

    private void u_1723_Y() {
        c_1514_x frontBlockPos;
        if (this.G_564_y > 0) {
            --this.G_564_y;
            return;
        }
        if (this.n_1700_B.R_4764_Y.RowButton()) {
            this.u_1723_Y.P_1922_E(false);
            this.P_1922_E = false;
            return;
        }
        if (this.n_1700_B.R_4764_Y.M_1641_O() && this.R_4764_Y.nextInt(100) < 5) {
            this.P_1922_E = true;
            this.G_564_y = 20 + this.R_4764_Y.nextInt(40);
        }
        if (!(this.n_1700_B.J_1907_R == null || !this.n_1700_B.R_4764_Y.M_1641_O() || this.n_1700_B.J_1907_R.getBlockState((frontBlockPos = new c_1514_x(this.n_1700_B.R_4764_Y.O_3598_v() + Math.sin(Math.toRadians(this.n_1700_B.R_4764_Y.p_178_J + 90.0f)) * 0.5, this.n_1700_B.R_4764_Y.X_2960_b(), this.n_1700_B.R_4764_Y.l_2647_k() + Math.cos(Math.toRadians(this.n_1700_B.R_4764_Y.p_178_J + 90.0f)) * 0.5)).up()).v_4262_N() && this.n_1700_B.J_1907_R.getBlockState(frontBlockPos).v_4262_N())) {
            this.P_1922_E = true;
        }
        this.u_1723_Y.P_1922_E(this.P_1922_E && this.n_1700_B.R_4764_Y.M_1641_O());
        if (this.P_1922_E && this.n_1700_B.R_4764_Y.M_1641_O()) {
            this.P_1922_E = false;
        }
    }

    public void n_1700_B(N_4263_v entity) {
        if (entity == null) {
            return;
        }
        this.n_1700_B(entity.s_4990_V());
    }

    public void n_1700_B(e_2866_D target, double speed) {
        if (this.n_1700_B == null || this.n_1700_B.R_4764_Y == null) {
            return;
        }
        this.R_4764_Y(target);
        this.n_1700_B(target);
    }

    public void J_1907_R(e_2866_D target) {
        this.n_1700_B(target, 1.0);
    }

    public void n_1700_B(c_1514_x blockPos, double speed) {
        e_2866_D target = new e_2866_D((double)blockPos.getX() + 0.5, blockPos.getY(), (double)blockPos.getZ() + 0.5);
        this.n_1700_B(target, speed);
    }

    public void n_1700_B(c_1514_x blockPos) {
        this.n_1700_B(blockPos, 1.0);
    }

    public void n_1700_B(N_4263_v entity, double speed) {
        if (entity == null) {
            return;
        }
        this.n_1700_B(entity.s_4990_V(), speed);
    }

    public void R_4764_Y(e_2866_D target) {
        if (this.n_1700_B == null || this.n_1700_B.R_4764_Y == null) {
            return;
        }
        e_2866_D botPos = this.n_1700_B.R_4764_Y.s_4990_V().J_1907_R(0.0, this.n_1700_B.R_4764_Y.X_1313_W(), 0.0);
        double deltaX = target.J_1907_R - botPos.J_1907_R;
        double deltaY = target.R_4764_Y - botPos.R_4764_Y;
        double deltaZ = target.G_564_y - botPos.G_564_y;
        double horizontalDistance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
        float yaw = (float)Math.toDegrees(Math.atan2(deltaZ, deltaX)) - 90.0f;
        float pitch = (float)(-Math.toDegrees(Math.atan2(deltaY, horizontalDistance)));
        this.n_1700_B.R_4764_Y.p_178_J = yaw;
        this.n_1700_B.R_4764_Y.f_4016_n = pitch;
    }

    public void J_1907_R(c_1514_x blockPos) {
        e_2866_D target = new e_2866_D((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5);
        this.R_4764_Y(target);
    }

    public void J_1907_R(N_4263_v entity) {
        if (entity == null) {
            return;
        }
        e_2866_D target = entity.s_4990_V().J_1907_R(0.0, entity.X_1313_W(), 0.0);
        this.R_4764_Y(target);
    }

    public void n_1700_B() {
        if (this.n_1700_B == null || this.n_1700_B.R_4764_Y == null) {
            return;
        }
        if (this.n_1700_B.R_4764_Y.M_1641_O()) {
            this.u_1723_Y.P_1922_E(true);
            this.v_4262_N.tickMovement(this.n_1700_B.R_4764_Y.c_3005_b());
        }
    }

    public void J_1907_R() {
        if (this.n_1700_B == null || this.n_1700_B.R_4764_Y == null) {
            return;
        }
        this.u_1723_Y.n_1700_B();
        this.u_1723_Y.v_4262_N(false);
        this.v_4262_N.tickMovement(this.n_1700_B.R_4764_Y.c_3005_b());
        this.n_1700_B.R_4764_Y.L_4248_u = 0.0f;
        this.n_1700_B.R_4764_Y.L_1362_X = 0.0f;
    }

    public Y_601_j R_4764_Y() {
        return this.u_1723_Y;
    }

    public C_2741_M G_564_y() {
        return this.v_4262_N;
    }

    public void P_1922_E() {
        if (this.n_1700_B != null && this.n_1700_B.R_4764_Y != null) {
            if (this.n_1700_B.R_4764_Y.A_4115_X()) {
                this.n_1700_B.R_4764_Y.Y_601_j = new e_869_m(MinecraftClient.A_4115_X().P_4830_p);
            } else {
                this.n_1700_B.R_4764_Y.Y_601_j = this.v_4262_N;
                if (this.n_1700_B.R_4764_Y.Y_601_j instanceof C_2741_M) {
                    this.v_4262_N.tickMovement(this.n_1700_B.R_4764_Y.c_3005_b());
                    this.n_1700_B.R_4764_Y.L_4248_u = this.v_4262_N.moveForward;
                    this.n_1700_B.R_4764_Y.L_1362_X = this.v_4262_N.moveStrafe;
                    this.n_1700_B.R_4764_Y.t_1786_h(this.v_4262_N.jump);
                }
            }
        }
    }
}



