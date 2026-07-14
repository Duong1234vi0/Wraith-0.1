/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.EnumSet;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.PathfinderMob;
import lightning.product.Goal;

public class S_199_U
extends Goal {
    protected final PathfinderMob n_1700_B;
    private double J_1907_R;
    private double R_4764_Y;
    private double G_564_y;
    private final double P_1922_E;
    private final b_4507_u u_1723_Y;

    public S_199_U(PathfinderMob theCreatureIn, double movementSpeedIn) {
        this.n_1700_B = theCreatureIn;
        this.P_1922_E = movementSpeedIn;
        this.u_1723_Y = theCreatureIn.O_508_d;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
    }

    @Override
    public boolean n_1700_B() {
        if (this.n_1700_B.t_148_a() != null) {
            return false;
        }
        if (!this.u_1723_Y.q_4610_l()) {
            return false;
        }
        if (!this.n_1700_B.RealmsPersistence()) {
            return false;
        }
        if (!this.u_1723_Y.canSeeSky(this.n_1700_B.b_2312_j())) {
            return false;
        }
        return !this.n_1700_B.J_1907_R(e_1174_E.u_1723_Y).n_1700_B() ? false : this.v_4262_N();
    }

    protected boolean v_4262_N() {
        e_2866_D vector3d = this.w_1484_f();
        if (vector3d == null) {
            return false;
        }
        this.J_1907_R = vector3d.J_1907_R;
        this.R_4764_Y = vector3d.R_4764_Y;
        this.G_564_y = vector3d.G_564_y;
        return true;
    }

    @Override
    public boolean J_1907_R() {
        return !this.n_1700_B.e_4240_b().M_588_G();
    }

    @Override
    public void R_4764_Y() {
        this.n_1700_B.e_4240_b().n_1700_B(this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E);
    }

    @Nullable
    protected e_2866_D w_1484_f() {
        Random random = this.n_1700_B.M_3508_C();
        c_1514_x blockpos = this.n_1700_B.b_2312_j();
        for (int i = 0; i < 10; ++i) {
            c_1514_x blockpos1 = blockpos.add(random.nextInt(20) - 10, random.nextInt(6) - 3, random.nextInt(20) - 10);
            if (this.u_1723_Y.canSeeSky(blockpos1) || !(this.n_1700_B.n_1700_B(blockpos1) < 0.0f)) continue;
            return e_2866_D.R_4764_Y(blockpos1);
        }
        return null;
    }
}


