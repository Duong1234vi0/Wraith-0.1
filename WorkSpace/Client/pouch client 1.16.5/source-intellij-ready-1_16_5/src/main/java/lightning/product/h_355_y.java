/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.BlockStateProperties;
import lightning.product.D_38_f;
import lightning.product.D_4899_Z;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.BaseEntityBlock;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.ItemTags;
import lightning.product.O_2369_F;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.U_1266_O;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.DirectionProperty;
import lightning.product.i_2154_H;
import lightning.product.m_3054_I;
import lightning.product.n_1494_c;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.t_3286_u;
import lightning.product.t_3546_P;
import lightning.product.x_1688_C;
import lightning.product.x_268_Y;

public class h_355_y
extends BaseEntityBlock {
    public static final DirectionProperty P_4830_p = HorizontalDirectionalBlock.w_612_n;
    public static final U_1266_O h_1847_R = BlockStateProperties.C_2741_M;
    public static final U_1266_O Q_4569_t = BlockStateProperties.Q_4569_t;
    public static final s_1395_c M_182_A = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 2.0, 16.0);
    public static final s_1395_c t_1786_h = T_2915_h.n_1700_B(4.0, 2.0, 4.0, 12.0, 14.0, 12.0);
    public static final s_1395_c multiplayerClientSuggestionProvider = x_268_Y.n_1700_B(M_182_A, t_1786_h);
    public static final s_1395_c w_1457_N = T_2915_h.n_1700_B(0.0, 15.0, 0.0, 16.0, 15.0, 16.0);
    public static final s_1395_c Y_601_j = x_268_Y.n_1700_B(multiplayerClientSuggestionProvider, w_1457_N);
    public static final s_1395_c Y_259_p = x_268_Y.n_1700_B(T_2915_h.n_1700_B(1.0, 10.0, 0.0, 5.333333, 14.0, 16.0), T_2915_h.n_1700_B(5.333333, 12.0, 0.0, 9.666667, 16.0, 16.0), T_2915_h.n_1700_B(9.666667, 14.0, 0.0, 14.0, 18.0, 16.0), multiplayerClientSuggestionProvider);
    public static final s_1395_c Q_2552_b = x_268_Y.n_1700_B(T_2915_h.n_1700_B(0.0, 10.0, 1.0, 16.0, 14.0, 5.333333), T_2915_h.n_1700_B(0.0, 12.0, 5.333333, 16.0, 16.0, 9.666667), T_2915_h.n_1700_B(0.0, 14.0, 9.666667, 16.0, 18.0, 14.0), multiplayerClientSuggestionProvider);
    public static final s_1395_c C_2741_M = x_268_Y.n_1700_B(T_2915_h.n_1700_B(15.0, 10.0, 0.0, 10.666667, 14.0, 16.0), T_2915_h.n_1700_B(10.666667, 12.0, 0.0, 6.333333, 16.0, 16.0), T_2915_h.n_1700_B(6.333333, 14.0, 0.0, 2.0, 18.0, 16.0), multiplayerClientSuggestionProvider);
    public static final s_1395_c k_2293_S = x_268_Y.n_1700_B(T_2915_h.n_1700_B(0.0, 10.0, 15.0, 16.0, 14.0, 10.666667), T_2915_h.n_1700_B(0.0, 12.0, 10.666667, 16.0, 16.0, 6.333333), T_2915_h.n_1700_B(0.0, 14.0, 6.333333, 16.0, 18.0, 2.0), multiplayerClientSuggestionProvider);

    protected h_355_y(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, b_257_Y.R_4764_Y)).n_1700_B(h_1847_R, false)).n_1700_B(Q_4569_t, false));
    }

    @Override
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.R_4764_Y;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        return multiplayerClientSuggestionProvider;
    }

    @Override
    public boolean J_1907_R(K_4074_S state) {
        return true;
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        U_2912_j compoundnbt1;
        b_4507_u world = context.getWorld();
        Z_1993_T itemstack = context.getItem();
        U_2912_j compoundnbt = itemstack.Q_4569_t();
        a_3913_L playerentity = context.getPlayer();
        boolean flag = false;
        if (!world.Y_259_p && playerentity != null && compoundnbt != null && playerentity.ModuleManager() && compoundnbt.P_1922_E("BlockEntityTag") && (compoundnbt1 = compoundnbt.M_182_A("BlockEntityTag")).P_1922_E("Book")) {
            flag = true;
        }
        return (K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, context.getPlacementHorizontalFacing().u_1723_Y())).n_1700_B(Q_4569_t, flag);
    }

    @Override
    public s_1395_c J_1907_R(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return Y_601_j;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        switch (state.R_4764_Y(P_4830_p)) {
            case R_4764_Y: {
                return Q_2552_b;
            }
            case G_564_y: {
                return k_2293_S;
            }
            case u_1723_Y: {
                return C_2741_M;
            }
            case P_1922_E: {
                return Y_259_p;
            }
        }
        return multiplayerClientSuggestionProvider;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        return (K_4074_S)state.n_1700_B(P_4830_p, rot.n_1700_B(state.R_4764_Y(P_4830_p)));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        return state.n_1700_B(mirrorIn.n_1700_B(state.R_4764_Y(P_4830_p)));
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(P_4830_p, h_1847_R, Q_4569_t);
    }

    @Override
    @Nullable
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new D_4899_Z();
    }

    public static boolean n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, Z_1993_T stack) {
        if (!state.R_4764_Y(Q_4569_t).booleanValue()) {
            if (!worldIn.Y_259_p) {
                h_355_y.J_1907_R(worldIn, pos, state, stack);
            }
            return true;
        }
        return false;
    }

    private static void J_1907_R(b_4507_u worldIn, c_1514_x pos, K_4074_S state, Z_1993_T stack) {
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof D_4899_Z) {
            D_4899_Z lecterntileentity = (D_4899_Z)tileentity;
            lecterntileentity.n_1700_B(stack.n_1700_B(1));
            h_355_y.n_1700_B(worldIn, pos, state, true);
            worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.R_3077_Z, D_38_f.P_1922_E, 1.0f, 1.0f);
        }
    }

    public static void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, boolean hasBook) {
        worldIn.n_1700_B(pos, (K_4074_S)((K_4074_S)state.n_1700_B(h_1847_R, false)).n_1700_B(Q_4569_t, hasBook), 3);
        h_355_y.J_1907_R(worldIn, pos, state);
    }

    public static void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state) {
        h_355_y.J_1907_R(worldIn, pos, state, true);
        worldIn.u_2550_I().n_1700_B(pos, state.J_1907_R(), 2);
        worldIn.R_4764_Y(1043, pos, 0);
    }

    private static void J_1907_R(b_4507_u worldIn, c_1514_x pos, K_4074_S state, boolean powered) {
        worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(h_1847_R, powered), 3);
        h_355_y.J_1907_R(worldIn, pos, state);
    }

    private static void J_1907_R(b_4507_u worldIn, c_1514_x pos, K_4074_S state) {
        worldIn.J_1907_R(pos.down(), state.J_1907_R());
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        h_355_y.J_1907_R((b_4507_u)worldIn, pos, state, false);
    }

    @Override
    public void J_1907_R(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S newState, boolean isMoving) {
        if (!state.n_1700_B(newState.J_1907_R())) {
            if (state.R_4764_Y(Q_4569_t).booleanValue()) {
                this.R_4764_Y(state, worldIn, pos);
            }
            if (state.R_4764_Y(h_1847_R).booleanValue()) {
                worldIn.J_1907_R(pos.down(), this);
            }
            super.J_1907_R(state, worldIn, pos, newState, isMoving);
        }
    }

    private void R_4764_Y(K_4074_S state, b_4507_u world, c_1514_x pos) {
        i_2154_H tileentity = world.getTileEntity(pos);
        if (tileentity instanceof D_4899_Z) {
            D_4899_Z lecterntileentity = (D_4899_Z)tileentity;
            b_257_Y direction = state.R_4764_Y(P_4830_p);
            Z_1993_T itemstack = lecterntileentity.P_1922_E().t_148_a();
            float f = 0.25f * (float)direction.t_148_a();
            float f1 = 0.25f * (float)direction.u_2550_I();
            n_1494_c itementity = new n_1494_c(world, (double)pos.getX() + 0.5 + (double)f, pos.getY() + 1, (double)pos.getZ() + 0.5 + (double)f1, itemstack);
            itementity.t_148_a();
            world.a_(itementity);
            lecterntileentity.C_2741_M();
        }
    }

    @Override
    public boolean R_4764_Y(K_4074_S state) {
        return true;
    }

    @Override
    public int n_1700_B(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        return blockState.R_4764_Y(h_1847_R) != false ? 15 : 0;
    }

    @Override
    public int J_1907_R(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        return side == b_257_Y.J_1907_R && blockState.R_4764_Y(h_1847_R) != false ? 15 : 0;
    }

    @Override
    public boolean u_1723_Y(K_4074_S state) {
        return true;
    }

    @Override
    public int J_1907_R(K_4074_S blockState, b_4507_u worldIn, c_1514_x pos) {
        i_2154_H tileentity;
        if (blockState.R_4764_Y(Q_4569_t).booleanValue() && (tileentity = worldIn.getTileEntity(pos)) instanceof D_4899_Z) {
            return ((D_4899_Z)tileentity).s_956_w();
        }
        return 0;
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (state.R_4764_Y(Q_4569_t).booleanValue()) {
            if (!worldIn.Y_259_p) {
                this.n_1700_B(worldIn, pos, player);
            }
            return m_3054_I.n_1700_B(worldIn.Y_259_p);
        }
        Z_1993_T itemstack = player.R_4764_Y(handIn);
        return !itemstack.n_1700_B() && !itemstack.J_1907_R().n_1700_B(ItemTags.g_2268_R) ? m_3054_I.J_1907_R : m_3054_I.R_4764_Y;
    }

    @Override
    @Nullable
    public t_3286_u n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos) {
        return state.R_4764_Y(Q_4569_t) == false ? null : super.n_1700_B(state, worldIn, pos);
    }

    private void n_1700_B(b_4507_u world, c_1514_x pos, a_3913_L player) {
        i_2154_H tileentity = world.getTileEntity(pos);
        if (tileentity instanceof D_4899_Z) {
            player.n_1700_B((D_4899_Z)tileentity);
            player.J_1907_R(Stats.h_4320_q);
        }
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}



