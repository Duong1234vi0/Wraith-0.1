/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.IceBlock;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_88_D;
import lightning.product.q_4293_E;
import lightning.product.u_530_F;
import lightning.product.v_3760_Q;

public class FrostedIceBlock
extends IceBlock {
    public static final g_88_D P_4830_p = BlockStateProperties.r_715_M;

    public FrostedIceBlock(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, 0));
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        this.J_1907_R(state, worldIn, pos, random);
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        if ((rand.nextInt(3) == 0 || this.n_1700_B((BlockGetter)worldIn, pos, 4)) && worldIn.u_2550_I(pos) > 11 - state.R_4764_Y(P_4830_p) - state.J_1907_R((BlockGetter)worldIn, pos) && this.P_1922_E(state, worldIn, pos)) {
            c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
            for (b_257_Y direction : b_257_Y.values()) {
                blockpos$mutable.n_1700_B(pos, direction);
                K_4074_S blockstate = worldIn.getBlockState(blockpos$mutable);
                if (!blockstate.n_1700_B(this) || this.P_1922_E(blockstate, worldIn, (c_1514_x)blockpos$mutable)) continue;
                worldIn.Q_2552_b().n_1700_B(blockpos$mutable, this, u_530_F.n_1700_B(rand, 20, 40));
            }
        } else {
            worldIn.Q_2552_b().n_1700_B(pos, this, u_530_F.n_1700_B(rand, 20, 40));
        }
    }

    private boolean P_1922_E(K_4074_S state, b_4507_u worldIn, c_1514_x pos) {
        int i = state.R_4764_Y(P_4830_p);
        if (i < 3) {
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, i + 1), 2);
            return false;
        }
        this.R_4764_Y(state, worldIn, pos);
        return true;
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, T_2915_h blockIn, c_1514_x fromPos, boolean isMoving) {
        if (blockIn == this && this.n_1700_B((BlockGetter)worldIn, pos, 2)) {
            this.R_4764_Y(state, worldIn, pos);
        }
        super.n_1700_B(state, worldIn, pos, blockIn, fromPos, isMoving);
    }

    private boolean n_1700_B(BlockGetter worldIn, c_1514_x pos, int neighborsRequired) {
        int i = 0;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (b_257_Y direction : b_257_Y.values()) {
            blockpos$mutable.n_1700_B(pos, direction);
            if (!worldIn.getBlockState(blockpos$mutable).n_1700_B(this) || ++i < neighborsRequired) continue;
            return false;
        }
        return true;
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }

    @Override
    public Z_1993_T n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state) {
        return Z_1993_T.J_1907_R;
    }
}


