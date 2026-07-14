/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import lightning.product.K_550_M;
import lightning.product.MoveToBlockGoal;
import lightning.product.T_1316_M;
import lightning.product.c_1514_x;
import lightning.product.PathfinderMob;
import lightning.product.BlockTags;
import lightning.product.Goal;

public class CatLieOnBedGoal
extends MoveToBlockGoal {
    private final K_550_M v_4262_N;

    public CatLieOnBedGoal(K_550_M catIn, double speed, int length) {
        super(catIn, speed, length, 6);
        this.v_4262_N = catIn;
        this.u_1723_Y = -2;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.R_4764_Y, Goal.n_1700_B.n_1700_B));
    }

    @Override
    public boolean n_1700_B() {
        return this.v_4262_N.U_3758_B() && !this.v_4262_N.D_3612_q() && !this.v_4262_N.V_1176_p() && super.n_1700_B();
    }

    @Override
    public void R_4764_Y() {
        super.R_4764_Y();
        this.v_4262_N.C_2741_M(false);
    }

    @Override
    protected int n_1700_B(PathfinderMob creatureIn) {
        return 40;
    }

    @Override
    public void G_564_y() {
        super.G_564_y();
        this.v_4262_N.w_1457_N(false);
    }

    @Override
    public void P_1922_E() {
        super.P_1922_E();
        this.v_4262_N.C_2741_M(false);
        if (!this.M_588_G()) {
            this.v_4262_N.w_1457_N(false);
        } else if (!this.v_4262_N.V_1176_p()) {
            this.v_4262_N.w_1457_N(true);
        }
    }

    @Override
    protected boolean n_1700_B(T_1316_M worldIn, c_1514_x pos) {
        return worldIn.u_1723_Y(pos.up()) && worldIn.getBlockState(pos).J_1907_R().n_1700_B(BlockTags.d_2461_k);
    }
}


