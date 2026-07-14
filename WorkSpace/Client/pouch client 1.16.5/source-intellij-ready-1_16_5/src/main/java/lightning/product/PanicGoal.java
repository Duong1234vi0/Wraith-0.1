/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.EnumSet;
import javax.annotation.Nullable;
import lightning.product.FluidTags;
import lightning.product.BlockGetter;
import lightning.product.N_4263_v;
import lightning.product.W_3371_U;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.PathfinderMob;
import lightning.product.Goal;

public class PanicGoal
extends Goal {
    protected final PathfinderMob n_1700_B;
    protected final double J_1907_R;
    protected double R_4764_Y;
    protected double G_564_y;
    protected double P_1922_E;
    protected boolean u_1723_Y;

    public PanicGoal(PathfinderMob creature, double speedIn) {
        this.n_1700_B = creature;
        this.J_1907_R = speedIn;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
    }

    @Override
    public boolean n_1700_B() {
        c_1514_x blockpos;
        if (this.n_1700_B.q_817_e() == null && !this.n_1700_B.RealmsPersistence()) {
            return false;
        }
        if (this.n_1700_B.RealmsPersistence() && (blockpos = this.n_1700_B(this.n_1700_B.O_508_d, this.n_1700_B, 5, 4)) != null) {
            this.R_4764_Y = blockpos.getX();
            this.G_564_y = blockpos.getY();
            this.P_1922_E = blockpos.getZ();
            return true;
        }
        return this.v_4262_N();
    }

    protected boolean v_4262_N() {
        e_2866_D vector3d = W_3371_U.n_1700_B(this.n_1700_B, 5, 4);
        if (vector3d == null) {
            return false;
        }
        this.R_4764_Y = vector3d.J_1907_R;
        this.G_564_y = vector3d.R_4764_Y;
        this.P_1922_E = vector3d.G_564_y;
        return true;
    }

    public boolean w_1484_f() {
        return this.u_1723_Y;
    }

    @Override
    public void R_4764_Y() {
        this.n_1700_B.e_4240_b().n_1700_B(this.R_4764_Y, this.G_564_y, this.P_1922_E, this.J_1907_R);
        this.u_1723_Y = true;
    }

    @Override
    public void G_564_y() {
        this.u_1723_Y = false;
    }

    @Override
    public boolean J_1907_R() {
        return !this.n_1700_B.e_4240_b().M_588_G();
    }

    @Nullable
    protected c_1514_x n_1700_B(BlockGetter worldIn, N_4263_v entityIn, int horizontalRange, int verticalRange) {
        c_1514_x blockpos = entityIn.b_2312_j();
        int i = blockpos.getX();
        int j = blockpos.getY();
        int k = blockpos.getZ();
        float f = horizontalRange * horizontalRange * verticalRange * 2;
        c_1514_x blockpos1 = null;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int l = i - horizontalRange; l <= i + horizontalRange; ++l) {
            for (int i1 = j - verticalRange; i1 <= j + verticalRange; ++i1) {
                for (int j1 = k - horizontalRange; j1 <= k + horizontalRange; ++j1) {
                    float f1;
                    blockpos$mutable.n_1700_B(l, i1, j1);
                    if (!worldIn.getFluidState(blockpos$mutable).n_1700_B(FluidTags.J_1907_R) || !((f1 = (float)((l - i) * (l - i) + (i1 - j) * (i1 - j) + (j1 - k) * (j1 - k))) < f)) continue;
                    f = f1;
                    blockpos1 = new c_1514_x(blockpos$mutable);
                }
            }
        }
        return blockpos1;
    }
}


