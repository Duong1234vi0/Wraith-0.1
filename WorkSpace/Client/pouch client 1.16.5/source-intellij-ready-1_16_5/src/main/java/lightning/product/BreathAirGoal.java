/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.L_461_d;
import lightning.product.T_1316_M;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.PathfinderMob;
import lightning.product.Goal;
import lightning.product.t_3546_P;
import lightning.product.u_530_F;
import lightning.product.z_3539_x;

public class BreathAirGoal
extends Goal {
    private final PathfinderMob n_1700_B;

    public BreathAirGoal(PathfinderMob p_i48940_1_) {
        this.n_1700_B = p_i48940_1_;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R));
    }

    @Override
    public boolean n_1700_B() {
        return this.n_1700_B.L_4248_u() < 140;
    }

    @Override
    public boolean J_1907_R() {
        return this.n_1700_B();
    }

    @Override
    public boolean r_() {
        return false;
    }

    @Override
    public void R_4764_Y() {
        this.v_4262_N();
    }

    private void v_4262_N() {
        Iterable<c_1514_x> iterable = c_1514_x.getAllInBoxMutable(u_530_F.R_4764_Y(this.n_1700_B.O_3598_v() - 1.0), u_530_F.R_4764_Y(this.n_1700_B.X_2960_b()), u_530_F.R_4764_Y(this.n_1700_B.l_2647_k() - 1.0), u_530_F.R_4764_Y(this.n_1700_B.O_3598_v() + 1.0), u_530_F.R_4764_Y(this.n_1700_B.X_2960_b() + 8.0), u_530_F.R_4764_Y(this.n_1700_B.l_2647_k() + 1.0));
        z_3539_x blockpos = null;
        for (c_1514_x blockpos1 : iterable) {
            if (!this.n_1700_B(this.n_1700_B.O_508_d, blockpos1)) continue;
            blockpos = blockpos1;
            break;
        }
        if (blockpos == null) {
            blockpos = new c_1514_x(this.n_1700_B.O_3598_v(), this.n_1700_B.X_2960_b() + 8.0, this.n_1700_B.l_2647_k());
        }
        this.n_1700_B.e_4240_b().n_1700_B((double)blockpos.getX(), (double)(blockpos.getY() + 1), (double)blockpos.getZ(), 1.0);
    }

    @Override
    public void P_1922_E() {
        this.v_4262_N();
        this.n_1700_B.n_1700_B(0.02f, new e_2866_D(this.n_1700_B.L_1362_X, this.n_1700_B.P_5000_x, this.n_1700_B.L_4248_u));
        this.n_1700_B.n_1700_B(L_461_d.n_1700_B, this.n_1700_B.I_4348_c());
    }

    private boolean n_1700_B(T_1316_M worldIn, c_1514_x pos) {
        K_4074_S blockstate = worldIn.getBlockState(pos);
        return (worldIn.getFluidState(pos).R_4764_Y() || blockstate.n_1700_B(a_3742_W.S_4325_V)) && blockstate.n_1700_B((BlockGetter)worldIn, pos, t_3546_P.n_1700_B);
    }
}


