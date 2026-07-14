/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.EnumSet;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.Animal;
import lightning.product.N_4263_v;
import lightning.product.b_4507_u;
import lightning.product.TargetingConditions;
import lightning.product.e_3591_l;
import lightning.product.Goal;

public class BreedGoal
extends Goal {
    private static final TargetingConditions G_564_y = new TargetingConditions().n_1700_B(8.0).n_1700_B().J_1907_R().R_4764_Y();
    protected final Animal n_1700_B;
    private final Class<? extends Animal> P_1922_E;
    protected final b_4507_u J_1907_R;
    protected Animal R_4764_Y;
    private int u_1723_Y;
    private final double v_4262_N;

    public BreedGoal(Animal animal, double speedIn) {
        this(animal, speedIn, animal.getClass());
    }

    public BreedGoal(Animal animal, double moveSpeed, Class<? extends Animal> mateClass) {
        this.n_1700_B = animal;
        this.J_1907_R = animal.O_508_d;
        this.P_1922_E = mateClass;
        this.v_4262_N = moveSpeed;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R));
    }

    @Override
    public boolean n_1700_B() {
        if (!this.n_1700_B.P_2295_B()) {
            return false;
        }
        this.R_4764_Y = this.w_1484_f();
        return this.R_4764_Y != null;
    }

    @Override
    public boolean J_1907_R() {
        return this.R_4764_Y.RealmsLongRunningMcoTaskScreen() && this.R_4764_Y.P_2295_B() && this.u_1723_Y < 60;
    }

    @Override
    public void G_564_y() {
        this.R_4764_Y = null;
        this.u_1723_Y = 0;
    }

    @Override
    public void P_1922_E() {
        this.n_1700_B.c_3005_b().n_1700_B(this.R_4764_Y, 10.0f, (float)this.n_1700_B.Z_976_R());
        this.n_1700_B.e_4240_b().n_1700_B((N_4263_v)this.R_4764_Y, this.v_4262_N);
        ++this.u_1723_Y;
        if (this.u_1723_Y >= 60 && this.n_1700_B.G_564_y((N_4263_v)this.R_4764_Y) < 9.0) {
            this.v_4262_N();
        }
    }

    @Nullable
    private Animal w_1484_f() {
        List<? extends Animal> list = this.J_1907_R.n_1700_B(this.P_1922_E, G_564_y, this.n_1700_B, this.n_1700_B.i_601_W().grow(8.0));
        double d0 = Double.MAX_VALUE;
        Animal animalentity = null;
        for (Animal n_1021_F : list) {
            if (!this.n_1700_B.n_1700_B(n_1021_F) || !(this.n_1700_B.G_564_y((N_4263_v)n_1021_F) < d0)) continue;
            animalentity = n_1021_F;
            d0 = this.n_1700_B.G_564_y((N_4263_v)n_1021_F);
        }
        return animalentity;
    }

    protected void v_4262_N() {
        this.n_1700_B.n_1700_B((e_3591_l)this.J_1907_R, this.R_4764_Y);
    }
}


