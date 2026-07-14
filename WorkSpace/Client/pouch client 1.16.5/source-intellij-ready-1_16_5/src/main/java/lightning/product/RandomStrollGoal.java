/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.EnumSet;
import javax.annotation.Nullable;
import lightning.product.W_3371_U;
import lightning.product.e_2866_D;
import lightning.product.PathfinderMob;
import lightning.product.Goal;

public class RandomStrollGoal
extends Goal {
    protected final PathfinderMob n_1700_B;
    protected double J_1907_R;
    protected double R_4764_Y;
    protected double G_564_y;
    protected final double P_1922_E;
    protected int u_1723_Y;
    protected boolean v_4262_N;
    private boolean w_1484_f;

    public RandomStrollGoal(PathfinderMob creatureIn, double speedIn) {
        this(creatureIn, speedIn, 120);
    }

    public RandomStrollGoal(PathfinderMob creatureIn, double speedIn, int chance) {
        this(creatureIn, speedIn, chance, true);
    }

    public RandomStrollGoal(PathfinderMob creature, double speed, int chance, boolean p_i231550_5_) {
        this.n_1700_B = creature;
        this.P_1922_E = speed;
        this.u_1723_Y = chance;
        this.w_1484_f = p_i231550_5_;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
    }

    @Override
    public boolean n_1700_B() {
        e_2866_D vector3d;
        if (this.n_1700_B.H_1883_T()) {
            return false;
        }
        if (!this.v_4262_N) {
            if (this.w_1484_f && this.n_1700_B.g_4560_H() >= 100) {
                return false;
            }
            if (this.n_1700_B.M_3508_C().nextInt(this.u_1723_Y) != 0) {
                return false;
            }
        }
        if ((vector3d = this.v_4262_N()) == null) {
            return false;
        }
        this.J_1907_R = vector3d.J_1907_R;
        this.R_4764_Y = vector3d.R_4764_Y;
        this.G_564_y = vector3d.G_564_y;
        this.v_4262_N = false;
        return true;
    }

    @Nullable
    protected e_2866_D v_4262_N() {
        return W_3371_U.n_1700_B(this.n_1700_B, 10, 7);
    }

    @Override
    public boolean J_1907_R() {
        return !this.n_1700_B.e_4240_b().M_588_G() && !this.n_1700_B.H_1883_T();
    }

    @Override
    public void R_4764_Y() {
        this.n_1700_B.e_4240_b().n_1700_B(this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E);
    }

    @Override
    public void G_564_y() {
        this.n_1700_B.e_4240_b().h_1847_R();
        super.G_564_y();
    }

    public void w_1484_f() {
        this.v_4262_N = true;
    }

    public void n_1700_B(int newchance) {
        this.u_1723_Y = newchance;
    }
}


