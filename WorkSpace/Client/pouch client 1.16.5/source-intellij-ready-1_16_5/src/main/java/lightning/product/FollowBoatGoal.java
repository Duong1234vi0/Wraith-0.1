/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.F_578_E;
import lightning.product.L_461_d;
import lightning.product.N_4263_v;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.g_1462_f;
import lightning.product.PathfinderMob;
import lightning.product.Goal;
import lightning.product.u_530_F;

public class FollowBoatGoal
extends Goal {
    private int n_1700_B;
    private final PathfinderMob J_1907_R;
    private a_3913_L R_4764_Y;
    private F_578_E G_564_y;

    public FollowBoatGoal(PathfinderMob swimmer) {
        this.J_1907_R = swimmer;
    }

    @Override
    public boolean n_1700_B() {
        List<g_1462_f> list = this.J_1907_R.O_508_d.n_1700_B(g_1462_f.class, this.J_1907_R.i_601_W().grow(5.0));
        boolean flag = false;
        for (g_1462_f boatentity : list) {
            N_4263_v entity = boatentity.n_3864_h();
            if (!(entity instanceof a_3913_L) || !(u_530_F.P_1922_E(((a_3913_L)entity).L_1362_X) > 0.0f) && !(u_530_F.P_1922_E(((a_3913_L)entity).L_4248_u) > 0.0f)) continue;
            flag = true;
            break;
        }
        return this.R_4764_Y != null && (u_530_F.P_1922_E(this.R_4764_Y.L_1362_X) > 0.0f || u_530_F.P_1922_E(this.R_4764_Y.L_4248_u) > 0.0f) || flag;
    }

    @Override
    public boolean r_() {
        return true;
    }

    @Override
    public boolean J_1907_R() {
        return this.R_4764_Y != null && this.R_4764_Y.y_2772_m() && (u_530_F.P_1922_E(this.R_4764_Y.L_1362_X) > 0.0f || u_530_F.P_1922_E(this.R_4764_Y.L_4248_u) > 0.0f);
    }

    @Override
    public void R_4764_Y() {
        for (g_1462_f boatentity : this.J_1907_R.O_508_d.n_1700_B(g_1462_f.class, this.J_1907_R.i_601_W().grow(5.0))) {
            if (boatentity.n_3864_h() == null || !(boatentity.n_3864_h() instanceof a_3913_L)) continue;
            this.R_4764_Y = (a_3913_L)boatentity.n_3864_h();
            break;
        }
        this.n_1700_B = 0;
        this.G_564_y = F_578_E.n_1700_B;
    }

    @Override
    public void G_564_y() {
        this.R_4764_Y = null;
    }

    @Override
    public void P_1922_E() {
        boolean flag;
        boolean bl = flag = u_530_F.P_1922_E(this.R_4764_Y.L_1362_X) > 0.0f || u_530_F.P_1922_E(this.R_4764_Y.L_4248_u) > 0.0f;
        float f = this.G_564_y == F_578_E.J_1907_R ? (flag ? 0.01f : 0.0f) : 0.015f;
        this.J_1907_R.n_1700_B(f, new e_2866_D(this.J_1907_R.L_1362_X, this.J_1907_R.P_5000_x, this.J_1907_R.L_4248_u));
        this.J_1907_R.n_1700_B(L_461_d.n_1700_B, this.J_1907_R.I_4348_c());
        if (--this.n_1700_B <= 0) {
            this.n_1700_B = 10;
            if (this.G_564_y == F_578_E.n_1700_B) {
                c_1514_x blockpos = this.R_4764_Y.b_2312_j().offset(this.R_4764_Y.o_2767_H().u_1723_Y());
                blockpos = blockpos.add(0, -1, 0);
                this.J_1907_R.e_4240_b().n_1700_B((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), 1.0);
                if (this.J_1907_R.R_4764_Y((N_4263_v)this.R_4764_Y) < 4.0f) {
                    this.n_1700_B = 0;
                    this.G_564_y = F_578_E.J_1907_R;
                }
            } else if (this.G_564_y == F_578_E.J_1907_R) {
                b_257_Y direction = this.R_4764_Y.d_2545_n();
                c_1514_x blockpos1 = this.R_4764_Y.b_2312_j().offset(direction, 10);
                this.J_1907_R.e_4240_b().n_1700_B((double)blockpos1.getX(), (double)(blockpos1.getY() - 1), (double)blockpos1.getZ(), 1.0);
                if (this.J_1907_R.R_4764_Y((N_4263_v)this.R_4764_Y) > 12.0f) {
                    this.n_1700_B = 0;
                    this.G_564_y = F_578_E.n_1700_B;
                }
            }
        }
    }
}


