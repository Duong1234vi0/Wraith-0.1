/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.A_2352_Z;
import lightning.product.FluidTags;
import lightning.product.BlockStateProperties;
import lightning.product.D_3746_J;
import lightning.product.CropBlock;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.FenceGateBlock;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_88_D;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.s_3401_U;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.v_3760_Q;
import lightning.product.AttachedStemBlock;

public class y_2012_u
extends T_2915_h {
    public static final g_88_D P_4830_p = BlockStateProperties.PlayerInfo;
    protected static final s_1395_c h_1847_R = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 15.0, 16.0);

    protected y_2012_u(q_4293_E.P_1922_E builder) {
        super(builder);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, 0));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (facing == b_257_Y.J_1907_R && !stateIn.n_1700_B(worldIn, currentPos)) {
            worldIn.u_2550_I().n_1700_B(currentPos, this, 1);
        }
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        K_4074_S blockstate = worldIn.getBlockState(pos.up());
        return !blockstate.R_4764_Y().J_1907_R() || blockstate.J_1907_R() instanceof FenceGateBlock || blockstate.J_1907_R() instanceof s_3401_U;
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return !this.multiplayerClientSuggestionProvider().n_1700_B((T_1316_M)context.getWorld(), context.getPos()) ? a_3742_W.s_956_w.multiplayerClientSuggestionProvider() : super.n_1700_B(context);
    }

    @Override
    public boolean J_1907_R(K_4074_S state) {
        return true;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return h_1847_R;
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        if (!state.n_1700_B((T_1316_M)worldIn, pos)) {
            y_2012_u.R_4764_Y(state, worldIn, pos);
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        int i = state.R_4764_Y(P_4830_p);
        if (!y_2012_u.n_1700_B(worldIn, pos) && !worldIn.Q_2552_b(pos.up())) {
            if (i > 0) {
                worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, i - 1), 2);
            } else if (!y_2012_u.n_1700_B((BlockGetter)worldIn, pos)) {
                y_2012_u.R_4764_Y(state, worldIn, pos);
            }
        } else if (i < 7) {
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, 7), 2);
        }
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn, float fallDistance) {
        if (!worldIn.Y_259_p && worldIn.w_1457_N.nextFloat() < fallDistance - 0.5f && entityIn instanceof r_4811_B && (entityIn instanceof a_3913_L || worldIn.H_1990_U().J_1907_R(A_2352_Z.J_1907_R)) && entityIn.C_415_h() * entityIn.C_415_h() * entityIn.v_165_F() > 0.512f) {
            y_2012_u.R_4764_Y(worldIn.getBlockState(pos), worldIn, pos);
        }
        super.n_1700_B(worldIn, pos, entityIn, fallDistance);
    }

    public static void R_4764_Y(K_4074_S state, b_4507_u worldIn, c_1514_x pos) {
        worldIn.J_1907_R(pos, y_2012_u.n_1700_B(state, a_3742_W.s_956_w.multiplayerClientSuggestionProvider(), worldIn, pos));
    }

    private static boolean n_1700_B(BlockGetter worldIn, c_1514_x pos) {
        T_2915_h block = worldIn.getBlockState(pos.up()).J_1907_R();
        return block instanceof CropBlock || block instanceof D_3746_J || block instanceof AttachedStemBlock;
    }

    private static boolean n_1700_B(T_1316_M worldIn, c_1514_x pos) {
        for (c_1514_x blockpos : c_1514_x.getAllInBoxMutable(pos.add(-4, 0, -4), pos.add(4, 1, 4))) {
            if (!worldIn.getFluidState(blockpos).n_1700_B(FluidTags.J_1907_R)) continue;
            return true;
        }
        return false;
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


