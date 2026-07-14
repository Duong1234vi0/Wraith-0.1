/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import lightning.product.PathNavigation;
import lightning.product.C_3622_I;
import lightning.product.LeavesBlock;
import lightning.product.BlockGetter;
import lightning.product.I_1869_h;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.T_1316_M;
import lightning.product.Z_535_q;
import lightning.product.FlyingPathNavigation;
import lightning.product.c_1514_x;
import lightning.product.i_2099_H;
import lightning.product.Goal;
import lightning.product.r_4811_B;

public class f_4739_a
extends Goal {
    private final C_3622_I n_1700_B;
    private r_4811_B J_1907_R;
    private final T_1316_M R_4764_Y;
    private final double G_564_y;
    private final PathNavigation P_1922_E;
    private int u_1723_Y;
    private final float v_4262_N;
    private final float w_1484_f;
    private float t_148_a;
    private final boolean s_956_w;

    public f_4739_a(C_3622_I tameable, double speed, float minDist, float maxDist, boolean teleportToLeaves) {
        this.n_1700_B = tameable;
        this.R_4764_Y = tameable.O_508_d;
        this.G_564_y = speed;
        this.P_1922_E = tameable.e_4240_b();
        this.w_1484_f = minDist;
        this.v_4262_N = maxDist;
        this.s_956_w = teleportToLeaves;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R));
        if (!(tameable.e_4240_b() instanceof i_2099_H) && !(tameable.e_4240_b() instanceof FlyingPathNavigation)) {
            throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
        }
    }

    @Override
    public boolean n_1700_B() {
        r_4811_B livingentity = this.n_1700_B.A_1306_N();
        if (livingentity == null) {
            return false;
        }
        if (livingentity.d_2461_k()) {
            return false;
        }
        if (this.n_1700_B.D_3612_q()) {
            return false;
        }
        if (this.n_1700_B.G_564_y((N_4263_v)livingentity) < (double)(this.w_1484_f * this.w_1484_f)) {
            return false;
        }
        this.J_1907_R = livingentity;
        return true;
    }

    @Override
    public boolean J_1907_R() {
        if (this.P_1922_E.M_588_G()) {
            return false;
        }
        if (this.n_1700_B.D_3612_q()) {
            return false;
        }
        return !(this.n_1700_B.G_564_y((N_4263_v)this.J_1907_R) <= (double)(this.v_4262_N * this.v_4262_N));
    }

    @Override
    public void R_4764_Y() {
        this.u_1723_Y = 0;
        this.t_148_a = this.n_1700_B.n_1700_B(I_1869_h.w_1484_f);
        this.n_1700_B.n_1700_B(I_1869_h.w_1484_f, 0.0f);
    }

    @Override
    public void G_564_y() {
        this.J_1907_R = null;
        this.P_1922_E.h_1847_R();
        this.n_1700_B.n_1700_B(I_1869_h.w_1484_f, this.t_148_a);
    }

    @Override
    public void P_1922_E() {
        this.n_1700_B.c_3005_b().n_1700_B(this.J_1907_R, 10.0f, (float)this.n_1700_B.Z_976_R());
        if (--this.u_1723_Y <= 0) {
            this.u_1723_Y = 10;
            if (!this.n_1700_B.n_4915_F() && !this.n_1700_B.y_2772_m()) {
                if (this.n_1700_B.G_564_y((N_4263_v)this.J_1907_R) >= 144.0) {
                    this.v_4262_N();
                } else {
                    this.P_1922_E.n_1700_B((N_4263_v)this.J_1907_R, this.G_564_y);
                }
            }
        }
    }

    private void v_4262_N() {
        c_1514_x blockpos = this.J_1907_R.b_2312_j();
        for (int i = 0; i < 10; ++i) {
            int j = this.n_1700_B(-3, 3);
            int k = this.n_1700_B(-1, 1);
            int l = this.n_1700_B(-3, 3);
            boolean flag = this.n_1700_B(blockpos.getX() + j, blockpos.getY() + k, blockpos.getZ() + l);
            if (!flag) continue;
            return;
        }
    }

    private boolean n_1700_B(int x, int y, int z) {
        if (Math.abs((double)x - this.J_1907_R.O_3598_v()) < 2.0 && Math.abs((double)z - this.J_1907_R.l_2647_k()) < 2.0) {
            return false;
        }
        if (!this.n_1700_B(new c_1514_x(x, y, z))) {
            return false;
        }
        this.n_1700_B.J_1907_R((double)x + 0.5, y, (double)z + 0.5, this.n_1700_B.p_178_J, this.n_1700_B.f_4016_n);
        this.P_1922_E.h_1847_R();
        return true;
    }

    private boolean n_1700_B(c_1514_x pos) {
        I_1869_h pathnodetype = Z_535_q.n_1700_B((BlockGetter)this.R_4764_Y, pos.toMutable());
        if (pathnodetype != I_1869_h.R_4764_Y) {
            return false;
        }
        K_4074_S blockstate = this.R_4764_Y.getBlockState(pos.down());
        if (!this.s_956_w && blockstate.J_1907_R() instanceof LeavesBlock) {
            return false;
        }
        c_1514_x blockpos = pos.subtract(this.n_1700_B.b_2312_j());
        return this.R_4764_Y.a_(this.n_1700_B, this.n_1700_B.i_601_W().offset(blockpos));
    }

    private int n_1700_B(int min, int max) {
        return this.n_1700_B.M_3508_C().nextInt(max - min + 1) + min;
    }
}


