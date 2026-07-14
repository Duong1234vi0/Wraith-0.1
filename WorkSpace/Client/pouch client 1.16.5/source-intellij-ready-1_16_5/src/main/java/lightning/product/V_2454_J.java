/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Iterator;
import java.util.Random;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.W_4464_I;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_3591_l;
import lightning.product.SimpleWaterloggedBlock;
import lightning.product.g_88_D;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.x_268_Y;

public class V_2454_J
extends T_2915_h
implements SimpleWaterloggedBlock {
    private static final s_1395_c M_182_A;
    private static final s_1395_c t_1786_h;
    private static final s_1395_c multiplayerClientSuggestionProvider;
    private static final s_1395_c w_1457_N;
    public static final g_88_D P_4830_p;
    public static final U_1266_O h_1847_R;
    public static final U_1266_O Q_4569_t;

    protected V_2454_J(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, 7)).n_1700_B(h_1847_R, false)).n_1700_B(Q_4569_t, false));
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(P_4830_p, h_1847_R, Q_4569_t);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        if (!context.n_1700_B(state.J_1907_R().u_1723_Y())) {
            return state.R_4764_Y(Q_4569_t) != false ? t_1786_h : M_182_A;
        }
        return x_268_Y.J_1907_R();
    }

    @Override
    public s_1395_c R_4764_Y(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        return x_268_Y.J_1907_R();
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockPlaceContext useContext) {
        return useContext.getItem().J_1907_R() == this.u_1723_Y();
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        c_1514_x blockpos = context.getPos();
        b_4507_u world = context.getWorld();
        int i = V_2454_J.n_1700_B(world, blockpos);
        return (K_4074_S)((K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(h_1847_R, world.getFluidState(blockpos).n_1700_B() == Fluids.R_4764_Y)).n_1700_B(P_4830_p, i)).n_1700_B(Q_4569_t, this.n_1700_B((BlockGetter)world, blockpos, i));
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        if (!worldIn.Y_259_p) {
            worldIn.u_2550_I().n_1700_B(pos, this, 1);
        }
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (stateIn.R_4764_Y(h_1847_R).booleanValue()) {
            worldIn.M_588_G().n_1700_B(currentPos, Fluids.R_4764_Y, Fluids.R_4764_Y.n_1700_B(worldIn));
        }
        if (!worldIn.v_4276_D()) {
            worldIn.u_2550_I().n_1700_B(currentPos, this, 1);
        }
        return stateIn;
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        int i = V_2454_J.n_1700_B(worldIn, pos);
        K_4074_S blockstate = (K_4074_S)((K_4074_S)state.n_1700_B(P_4830_p, i)).n_1700_B(Q_4569_t, this.n_1700_B((BlockGetter)worldIn, pos, i));
        if (blockstate.R_4764_Y(P_4830_p) == 7) {
            if (state.R_4764_Y(P_4830_p) == 7) {
                worldIn.a_(new W_4464_I(worldIn, (double)pos.getX() + 0.5, pos.getY(), (double)pos.getZ() + 0.5, (K_4074_S)blockstate.n_1700_B(h_1847_R, false)));
            } else {
                worldIn.J_1907_R(pos, true);
            }
        } else if (state != blockstate) {
            worldIn.n_1700_B(pos, blockstate, 3);
        }
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        return V_2454_J.n_1700_B(worldIn, pos) < 7;
    }

    @Override
    public s_1395_c J_1907_R(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        if (context.n_1700_B(x_268_Y.J_1907_R(), pos, true) && !context.n_1700_B()) {
            return M_182_A;
        }
        return state.R_4764_Y(P_4830_p) != 0 && state.R_4764_Y(Q_4569_t) != false && context.n_1700_B(w_1457_N, pos, true) ? multiplayerClientSuggestionProvider : x_268_Y.n_1700_B();
    }

    @Override
    public FluidState P_1922_E(K_4074_S state) {
        return state.R_4764_Y(h_1847_R) != false ? Fluids.R_4764_Y.n_1700_B(false) : super.P_1922_E(state);
    }

    private boolean n_1700_B(BlockGetter blockReader, c_1514_x pos, int distance) {
        return distance > 0 && !blockReader.getBlockState(pos.down()).n_1700_B(this);
    }

    public static int n_1700_B(BlockGetter blockReader, c_1514_x pos) {
        b_257_Y direction;
        K_4074_S blockstate1;
        c_1514_x.n_1700_B blockpos$mutable = pos.toMutable().n_1700_B(b_257_Y.n_1700_B);
        K_4074_S blockstate = blockReader.getBlockState(blockpos$mutable);
        int i = 7;
        if (blockstate.n_1700_B(a_3742_W.i_770_g)) {
            i = blockstate.R_4764_Y(P_4830_p);
        } else if (blockstate.G_564_y(blockReader, (c_1514_x)blockpos$mutable, b_257_Y.J_1907_R)) {
            return 0;
        }
        Iterator<b_257_Y> iterator = b_257_Y.R_4764_Y.n_1700_B.iterator();
        while (iterator.hasNext() && (!(blockstate1 = blockReader.getBlockState(blockpos$mutable.n_1700_B(pos, direction = iterator.next()))).n_1700_B(a_3742_W.i_770_g) || (i = Math.min(i, blockstate1.R_4764_Y(P_4830_p) + 1)) != 1)) {
        }
        return i;
    }

    static {
        multiplayerClientSuggestionProvider = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 2.0, 16.0);
        w_1457_N = x_268_Y.J_1907_R().n_1700_B(0.0, -1.0, 0.0);
        P_4830_p = BlockStateProperties.w_612_n;
        h_1847_R = BlockStateProperties.A_4115_X;
        Q_4569_t = BlockStateProperties.J_1907_R;
        s_1395_c voxelshape = T_2915_h.n_1700_B(0.0, 14.0, 0.0, 16.0, 16.0, 16.0);
        s_1395_c voxelshape1 = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 2.0, 16.0, 2.0);
        s_1395_c voxelshape2 = T_2915_h.n_1700_B(14.0, 0.0, 0.0, 16.0, 16.0, 2.0);
        s_1395_c voxelshape3 = T_2915_h.n_1700_B(0.0, 0.0, 14.0, 2.0, 16.0, 16.0);
        s_1395_c voxelshape4 = T_2915_h.n_1700_B(14.0, 0.0, 14.0, 16.0, 16.0, 16.0);
        M_182_A = x_268_Y.n_1700_B(voxelshape, voxelshape1, voxelshape2, voxelshape3, voxelshape4);
        s_1395_c voxelshape5 = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 2.0, 2.0, 16.0);
        s_1395_c voxelshape6 = T_2915_h.n_1700_B(14.0, 0.0, 0.0, 16.0, 2.0, 16.0);
        s_1395_c voxelshape7 = T_2915_h.n_1700_B(0.0, 0.0, 14.0, 16.0, 2.0, 16.0);
        s_1395_c voxelshape8 = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 2.0, 2.0);
        t_1786_h = x_268_Y.n_1700_B(multiplayerClientSuggestionProvider, M_182_A, voxelshape6, voxelshape5, voxelshape8, voxelshape7);
    }
}


