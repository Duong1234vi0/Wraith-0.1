/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.base.MoreObjects;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.BlockStateProperties;
import lightning.product.D_38_f;
import lightning.product.E_1708_F;
import lightning.product.BlockGetter;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.SoundEvents;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.DirectionProperty;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;

public class l_311_L
extends T_2915_h {
    public static final DirectionProperty P_4830_p = HorizontalDirectionalBlock.w_612_n;
    public static final U_1266_O h_1847_R = BlockStateProperties.C_2741_M;
    public static final U_1266_O Q_4569_t = BlockStateProperties.n_1700_B;
    protected static final s_1395_c M_182_A = T_2915_h.n_1700_B(5.0, 0.0, 10.0, 11.0, 10.0, 16.0);
    protected static final s_1395_c t_1786_h = T_2915_h.n_1700_B(5.0, 0.0, 0.0, 11.0, 10.0, 6.0);
    protected static final s_1395_c multiplayerClientSuggestionProvider = T_2915_h.n_1700_B(10.0, 0.0, 5.0, 16.0, 10.0, 11.0);
    protected static final s_1395_c w_1457_N = T_2915_h.n_1700_B(0.0, 0.0, 5.0, 6.0, 10.0, 11.0);

    public l_311_L(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, b_257_Y.R_4764_Y)).n_1700_B(h_1847_R, false)).n_1700_B(Q_4569_t, false));
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        switch (state.R_4764_Y(P_4830_p)) {
            default: {
                return w_1457_N;
            }
            case P_1922_E: {
                return multiplayerClientSuggestionProvider;
            }
            case G_564_y: {
                return t_1786_h;
            }
            case R_4764_Y: 
        }
        return M_182_A;
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        b_257_Y direction = state.R_4764_Y(P_4830_p);
        c_1514_x blockpos = pos.offset(direction.u_1723_Y());
        K_4074_S blockstate = worldIn.getBlockState(blockpos);
        return direction.h_1847_R().G_564_y() && blockstate.G_564_y((BlockGetter)worldIn, blockpos, direction);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        return facing.u_1723_Y() == stateIn.R_4764_Y(P_4830_p) && !stateIn.n_1700_B(worldIn, currentPos) ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        b_257_Y[] adirection;
        K_4074_S blockstate = (K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(h_1847_R, false)).n_1700_B(Q_4569_t, false);
        b_4507_u iworldreader = context.getWorld();
        c_1514_x blockpos = context.getPos();
        for (b_257_Y direction : adirection = context.G_564_y()) {
            b_257_Y direction1;
            if (!direction.h_1847_R().G_564_y() || !(blockstate = (K_4074_S)blockstate.n_1700_B(P_4830_p, direction1 = direction.u_1723_Y())).n_1700_B((T_1316_M)iworldreader, blockpos)) continue;
            return blockstate;
        }
        return null;
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, r_4811_B placer, Z_1993_T stack) {
        this.n_1700_B(worldIn, pos, state, false, false, -1, null);
    }

    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S hookState, boolean attaching, boolean shouldNotifyNeighbours, int searchRange, @Nullable K_4074_S state) {
        b_257_Y direction = hookState.R_4764_Y(P_4830_p);
        boolean flag = hookState.R_4764_Y(Q_4569_t);
        boolean flag1 = hookState.R_4764_Y(h_1847_R);
        boolean flag2 = !attaching;
        boolean flag3 = false;
        int i = 0;
        K_4074_S[] ablockstate = new K_4074_S[42];
        for (int j = 1; j < 42; ++j) {
            c_1514_x blockpos = pos.offset(direction, j);
            K_4074_S blockstate = worldIn.getBlockState(blockpos);
            if (blockstate.n_1700_B(a_3742_W.d_2169_p)) {
                if (blockstate.R_4764_Y(P_4830_p) != direction.u_1723_Y()) break;
                i = j;
                break;
            }
            if (!blockstate.n_1700_B(a_3742_W.I_3637_j) && j != searchRange) {
                ablockstate[j] = null;
                flag2 = false;
                continue;
            }
            if (j == searchRange) {
                blockstate = (K_4074_S)MoreObjects.firstNonNull((Object)state, (Object)blockstate);
            }
            boolean flag4 = blockstate.R_4764_Y(E_1708_F.Q_4569_t) == false;
            boolean flag5 = blockstate.R_4764_Y(E_1708_F.P_4830_p);
            flag3 |= flag4 && flag5;
            ablockstate[j] = blockstate;
            if (j != searchRange) continue;
            worldIn.u_2550_I().n_1700_B(pos, this, 10);
            flag2 &= flag4;
        }
        K_4074_S blockstate1 = (K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(Q_4569_t, flag2)).n_1700_B(h_1847_R, flag3 &= (flag2 &= i > 1));
        if (i > 0) {
            c_1514_x blockpos1 = pos.offset(direction, i);
            b_257_Y direction1 = direction.u_1723_Y();
            worldIn.n_1700_B(blockpos1, (K_4074_S)blockstate1.n_1700_B(P_4830_p, direction1), 3);
            this.n_1700_B(worldIn, blockpos1, direction1);
            this.n_1700_B(worldIn, blockpos1, flag2, flag3, flag, flag1);
        }
        this.n_1700_B(worldIn, pos, flag2, flag3, flag, flag1);
        if (!attaching) {
            worldIn.n_1700_B(pos, (K_4074_S)blockstate1.n_1700_B(P_4830_p, direction), 3);
            if (shouldNotifyNeighbours) {
                this.n_1700_B(worldIn, pos, direction);
            }
        }
        if (flag != flag2) {
            for (int k = 1; k < i; ++k) {
                c_1514_x blockpos2 = pos.offset(direction, k);
                K_4074_S blockstate2 = ablockstate[k];
                if (blockstate2 == null) continue;
                worldIn.n_1700_B(blockpos2, (K_4074_S)blockstate2.n_1700_B(Q_4569_t, flag2), 3);
                if (worldIn.getBlockState(blockpos2).v_4262_N()) continue;
            }
        }
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        this.n_1700_B(worldIn, pos, state, false, true, -1, null);
    }

    private void n_1700_B(b_4507_u worldIn, c_1514_x pos, boolean attaching, boolean activated, boolean detaching, boolean deactivating) {
        if (activated && !deactivating) {
            worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.y_2012_u, D_38_f.P_1922_E, 0.4f, 0.6f);
        } else if (!activated && deactivating) {
            worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.FallingBlock, D_38_f.P_1922_E, 0.4f, 0.5f);
        } else if (attaching && !detaching) {
            worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.j_3599_p, D_38_f.P_1922_E, 0.4f, 0.7f);
        } else if (!attaching && detaching) {
            worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.FenceBlock, D_38_f.P_1922_E, 0.4f, 1.2f / (worldIn.w_1457_N.nextFloat() * 0.2f + 0.9f));
        }
    }

    private void n_1700_B(b_4507_u worldIn, c_1514_x pos, b_257_Y side) {
        worldIn.J_1907_R(pos, this);
        worldIn.J_1907_R(pos.offset(side.u_1723_Y()), this);
    }

    @Override
    public void J_1907_R(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S newState, boolean isMoving) {
        if (!isMoving && !state.n_1700_B(newState.J_1907_R())) {
            boolean flag = state.R_4764_Y(Q_4569_t);
            boolean flag1 = state.R_4764_Y(h_1847_R);
            if (flag || flag1) {
                this.n_1700_B(worldIn, pos, state, true, false, -1, null);
            }
            if (flag1) {
                worldIn.J_1907_R(pos, this);
                worldIn.J_1907_R(pos.offset(state.R_4764_Y(P_4830_p).u_1723_Y()), this);
            }
            super.J_1907_R(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public int n_1700_B(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        return blockState.R_4764_Y(h_1847_R) != false ? 15 : 0;
    }

    @Override
    public int J_1907_R(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        if (!blockState.R_4764_Y(h_1847_R).booleanValue()) {
            return 0;
        }
        return blockState.R_4764_Y(P_4830_p) == side ? 15 : 0;
    }

    @Override
    public boolean R_4764_Y(K_4074_S state) {
        return true;
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
}


