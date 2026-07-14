/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.Y_1835_y;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_88_D;
import lightning.product.BlockTags;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.x_268_Y;

public class LeavesBlock
extends T_2915_h {
    public static final g_88_D P_4830_p = BlockStateProperties.j_276_v;
    public static final U_1266_O h_1847_R = BlockStateProperties.Q_2552_b;

    public LeavesBlock(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, 7)).n_1700_B(h_1847_R, false));
    }

    @Override
    public s_1395_c J_1907_R(K_4074_S state, BlockGetter reader, c_1514_x pos) {
        return x_268_Y.n_1700_B();
    }

    @Override
    public boolean a_(K_4074_S state) {
        return state.R_4764_Y(P_4830_p) == 7 && state.R_4764_Y(h_1847_R) == false;
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        if (!state.R_4764_Y(h_1847_R).booleanValue() && state.R_4764_Y(P_4830_p) == 7) {
            LeavesBlock.G_564_y(state, worldIn, pos);
            worldIn.n_1700_B(pos, false);
        }
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        worldIn.n_1700_B(pos, LeavesBlock.n_1700_B(state, worldIn, pos), 3);
    }

    @Override
    public int G_564_y(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        return 1;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        int i = LeavesBlock.w_1484_f(facingState) + 1;
        if (i != 1 || stateIn.R_4764_Y(P_4830_p) != i) {
            worldIn.u_2550_I().n_1700_B(currentPos, this, 1);
        }
        return stateIn;
    }

    private static K_4074_S n_1700_B(K_4074_S state, LevelAccessor worldIn, c_1514_x pos) {
        int i = 7;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (b_257_Y direction : b_257_Y.values()) {
            blockpos$mutable.n_1700_B(pos, direction);
            i = Math.min(i, LeavesBlock.w_1484_f(worldIn.getBlockState(blockpos$mutable)) + 1);
            if (i == 1) break;
        }
        return (K_4074_S)state.n_1700_B(P_4830_p, i);
    }

    private static int w_1484_f(K_4074_S neighbor) {
        if (BlockTags.w_1457_N.n_1700_B(neighbor.J_1907_R())) {
            return 0;
        }
        return neighbor.J_1907_R() instanceof LeavesBlock ? neighbor.R_4764_Y(P_4830_p) : 7;
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        c_1514_x blockpos;
        K_4074_S blockstate;
        if (!(!worldIn.Q_2552_b(pos.up()) || rand.nextInt(15) != 1 || (blockstate = worldIn.getBlockState(blockpos = pos.down())).M_588_G() && blockstate.G_564_y((BlockGetter)worldIn, blockpos, b_257_Y.J_1907_R))) {
            double d0 = (double)pos.getX() + rand.nextDouble();
            double d1 = (double)pos.getY() - 0.05;
            double d2 = (double)pos.getZ() + rand.nextDouble();
            worldIn.n_1700_B(ParticleTypes.P_4830_p, d0, d1, d2, 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(P_4830_p, h_1847_R);
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return LeavesBlock.n_1700_B((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(h_1847_R, true), context.getWorld(), context.getPos());
    }
}


