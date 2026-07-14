/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.Projectile;
import lightning.product.BlockStateProperties;
import lightning.product.D_38_f;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.BaseEntityBlock;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.O_2369_F;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.U_1266_O;
import lightning.product.SoundEvents;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_563_h;
import lightning.product.h_113_g;
import lightning.product.DirectionProperty;
import lightning.product.i_2154_H;
import lightning.product.l_1530_z;
import lightning.product.m_3054_I;
import lightning.product.FaceAttachedHorizontalDirectionalBlock;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.w_1454_v;
import lightning.product.x_1688_C;
import lightning.product.x_268_Y;

public class u_954_J
extends BaseEntityBlock {
    public static final DirectionProperty P_4830_p = HorizontalDirectionalBlock.w_612_n;
    public static final e_563_h<l_1530_z> h_1847_R = BlockStateProperties.e_2887_G;
    public static final U_1266_O Q_4569_t = BlockStateProperties.C_2741_M;
    private static final s_1395_c M_182_A = T_2915_h.n_1700_B(0.0, 0.0, 4.0, 16.0, 16.0, 12.0);
    private static final s_1395_c t_1786_h = T_2915_h.n_1700_B(4.0, 0.0, 0.0, 12.0, 16.0, 16.0);
    private static final s_1395_c multiplayerClientSuggestionProvider = T_2915_h.n_1700_B(5.0, 6.0, 5.0, 11.0, 13.0, 11.0);
    private static final s_1395_c w_1457_N = T_2915_h.n_1700_B(4.0, 4.0, 4.0, 12.0, 6.0, 12.0);
    private static final s_1395_c Y_601_j = x_268_Y.n_1700_B(w_1457_N, multiplayerClientSuggestionProvider);
    private static final s_1395_c Y_259_p = x_268_Y.n_1700_B(Y_601_j, T_2915_h.n_1700_B(7.0, 13.0, 0.0, 9.0, 15.0, 16.0));
    private static final s_1395_c Q_2552_b = x_268_Y.n_1700_B(Y_601_j, T_2915_h.n_1700_B(0.0, 13.0, 7.0, 16.0, 15.0, 9.0));
    private static final s_1395_c C_2741_M = x_268_Y.n_1700_B(Y_601_j, T_2915_h.n_1700_B(0.0, 13.0, 7.0, 13.0, 15.0, 9.0));
    private static final s_1395_c k_2293_S = x_268_Y.n_1700_B(Y_601_j, T_2915_h.n_1700_B(3.0, 13.0, 7.0, 16.0, 15.0, 9.0));
    private static final s_1395_c q_2307_F = x_268_Y.n_1700_B(Y_601_j, T_2915_h.n_1700_B(7.0, 13.0, 0.0, 9.0, 15.0, 13.0));
    private static final s_1395_c Z_875_P = x_268_Y.n_1700_B(Y_601_j, T_2915_h.n_1700_B(7.0, 13.0, 3.0, 9.0, 15.0, 16.0));
    private static final s_1395_c c_3005_b = x_268_Y.n_1700_B(Y_601_j, T_2915_h.n_1700_B(7.0, 13.0, 7.0, 9.0, 16.0, 9.0));

    public u_954_J(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, b_257_Y.R_4764_Y)).n_1700_B(h_1847_R, l_1530_z.n_1700_B)).n_1700_B(Q_4569_t, false));
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, T_2915_h blockIn, c_1514_x fromPos, boolean isMoving) {
        boolean flag = worldIn.Y_601_j(pos);
        if (flag != state.R_4764_Y(Q_4569_t)) {
            if (flag) {
                this.n_1700_B(worldIn, pos, (b_257_Y)null);
            }
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(Q_4569_t, flag), 3);
        }
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, K_4074_S state, BlockHitResult hit, Projectile projectile) {
        N_4263_v entity = projectile.Y_601_j();
        a_3913_L playerentity = entity instanceof a_3913_L ? (a_3913_L)entity : null;
        this.n_1700_B(worldIn, state, hit, playerentity, true);
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        return this.n_1700_B(worldIn, state, hit, player, true) ? m_3054_I.n_1700_B(worldIn.Y_259_p) : m_3054_I.R_4764_Y;
    }

    public boolean n_1700_B(b_4507_u world, K_4074_S state, BlockHitResult result, @Nullable a_3913_L player, boolean canRingBell) {
        boolean flag;
        b_257_Y direction = result.J_1907_R();
        c_1514_x blockpos = result.n_1700_B();
        boolean bl = flag = !canRingBell || this.n_1700_B(state, direction, result.P_1922_E().R_4764_Y - (double)blockpos.getY());
        if (flag) {
            boolean flag1 = this.n_1700_B(world, blockpos, direction);
            if (flag1 && player != null) {
                player.J_1907_R(Stats.U_1241_n);
            }
            return true;
        }
        return false;
    }

    private boolean n_1700_B(K_4074_S pos, b_257_Y directionIn, double distanceY) {
        if (directionIn.h_1847_R() != b_257_Y.n_1700_B.J_1907_R && !(distanceY > (double)0.8124f)) {
            b_257_Y direction = pos.R_4764_Y(P_4830_p);
            l_1530_z bellattachment = pos.R_4764_Y(h_1847_R);
            switch (bellattachment) {
                case n_1700_B: {
                    return direction.h_1847_R() == directionIn.h_1847_R();
                }
                case R_4764_Y: 
                case G_564_y: {
                    return direction.h_1847_R() != directionIn.h_1847_R();
                }
                case J_1907_R: {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public boolean n_1700_B(b_4507_u world, c_1514_x pos, @Nullable b_257_Y direction) {
        i_2154_H tileentity = world.getTileEntity(pos);
        if (!world.Y_259_p && tileentity instanceof h_113_g) {
            if (direction == null) {
                direction = world.getBlockState(pos).R_4764_Y(P_4830_p);
            }
            ((h_113_g)tileentity).n_1700_B(direction);
            world.n_1700_B((a_3913_L)null, pos, SoundEvents.R_3908_n, D_38_f.P_1922_E, 2.0f, 1.0f);
            return true;
        }
        return false;
    }

    private s_1395_c w_1484_f(K_4074_S state) {
        b_257_Y direction = state.R_4764_Y(P_4830_p);
        l_1530_z bellattachment = state.R_4764_Y(h_1847_R);
        if (bellattachment == l_1530_z.n_1700_B) {
            return direction != b_257_Y.R_4764_Y && direction != b_257_Y.G_564_y ? t_1786_h : M_182_A;
        }
        if (bellattachment == l_1530_z.J_1907_R) {
            return c_3005_b;
        }
        if (bellattachment == l_1530_z.G_564_y) {
            return direction != b_257_Y.R_4764_Y && direction != b_257_Y.G_564_y ? Q_2552_b : Y_259_p;
        }
        if (direction == b_257_Y.R_4764_Y) {
            return q_2307_F;
        }
        if (direction == b_257_Y.G_564_y) {
            return Z_875_P;
        }
        return direction == b_257_Y.u_1723_Y ? k_2293_S : C_2741_M;
    }

    @Override
    public s_1395_c J_1907_R(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return this.w_1484_f(state);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return this.w_1484_f(state);
    }

    @Override
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.R_4764_Y;
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        b_257_Y direction = context.getFace();
        c_1514_x blockpos = context.getPos();
        b_4507_u world = context.getWorld();
        b_257_Y.n_1700_B direction$axis = direction.h_1847_R();
        if (direction$axis == b_257_Y.n_1700_B.J_1907_R) {
            K_4074_S blockstate = (K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(h_1847_R, direction == b_257_Y.n_1700_B ? l_1530_z.J_1907_R : l_1530_z.n_1700_B)).n_1700_B(P_4830_p, context.getPlacementHorizontalFacing());
            if (blockstate.n_1700_B((T_1316_M)context.getWorld(), blockpos)) {
                return blockstate;
            }
        } else {
            boolean flag = direction$axis == b_257_Y.n_1700_B.n_1700_B && world.getBlockState(blockpos.west()).G_564_y((BlockGetter)world, blockpos.west(), b_257_Y.u_1723_Y) && world.getBlockState(blockpos.east()).G_564_y((BlockGetter)world, blockpos.east(), b_257_Y.P_1922_E) || direction$axis == b_257_Y.n_1700_B.R_4764_Y && world.getBlockState(blockpos.north()).G_564_y((BlockGetter)world, blockpos.north(), b_257_Y.G_564_y) && world.getBlockState(blockpos.south()).G_564_y((BlockGetter)world, blockpos.south(), b_257_Y.R_4764_Y);
            K_4074_S blockstate1 = (K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, direction.u_1723_Y())).n_1700_B(h_1847_R, flag ? l_1530_z.G_564_y : l_1530_z.R_4764_Y);
            if (blockstate1.n_1700_B((T_1316_M)context.getWorld(), context.getPos())) {
                return blockstate1;
            }
            boolean flag1 = world.getBlockState(blockpos.down()).G_564_y((BlockGetter)world, blockpos.down(), b_257_Y.J_1907_R);
            if ((blockstate1 = (K_4074_S)blockstate1.n_1700_B(h_1847_R, flag1 ? l_1530_z.n_1700_B : l_1530_z.J_1907_R)).n_1700_B((T_1316_M)context.getWorld(), context.getPos())) {
                return blockstate1;
            }
        }
        return null;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        l_1530_z bellattachment = stateIn.R_4764_Y(h_1847_R);
        b_257_Y direction = u_954_J.t_148_a(stateIn).u_1723_Y();
        if (direction == facing && !stateIn.n_1700_B(worldIn, currentPos) && bellattachment != l_1530_z.G_564_y) {
            return a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
        }
        if (facing.h_1847_R() == stateIn.R_4764_Y(P_4830_p).h_1847_R()) {
            if (bellattachment == l_1530_z.G_564_y && !facingState.G_564_y((BlockGetter)worldIn, facingPos, facing)) {
                return (K_4074_S)((K_4074_S)stateIn.n_1700_B(h_1847_R, l_1530_z.R_4764_Y)).n_1700_B(P_4830_p, facing.u_1723_Y());
            }
            if (bellattachment == l_1530_z.R_4764_Y && direction.u_1723_Y() == facing && facingState.G_564_y((BlockGetter)worldIn, facingPos, stateIn.R_4764_Y(P_4830_p))) {
                return (K_4074_S)stateIn.n_1700_B(h_1847_R, l_1530_z.G_564_y);
            }
        }
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        b_257_Y direction = u_954_J.t_148_a(state).u_1723_Y();
        return direction == b_257_Y.J_1907_R ? T_2915_h.n_1700_B(worldIn, pos.up(), b_257_Y.n_1700_B) : FaceAttachedHorizontalDirectionalBlock.J_1907_R(worldIn, pos, direction);
    }

    private static b_257_Y t_148_a(K_4074_S state) {
        switch (state.R_4764_Y(h_1847_R)) {
            case n_1700_B: {
                return b_257_Y.J_1907_R;
            }
            case J_1907_R: {
                return b_257_Y.n_1700_B;
            }
        }
        return state.R_4764_Y(P_4830_p).u_1723_Y();
    }

    @Override
    public w_1454_v G_564_y(K_4074_S state) {
        return w_1454_v.J_1907_R;
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(P_4830_p, h_1847_R, Q_4569_t);
    }

    @Override
    @Nullable
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new h_113_g();
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


