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
import lightning.product.BlockGetter;
import lightning.product.BonemealableBlock;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.d_2484_X;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.e_563_h;
import lightning.product.g_88_D;
import lightning.product.BlockTags;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.SwordItem;

public class E_872_n
extends T_2915_h
implements BonemealableBlock {
    protected static final s_1395_c P_4830_p = T_2915_h.n_1700_B(5.0, 0.0, 5.0, 11.0, 16.0, 11.0);
    protected static final s_1395_c h_1847_R = T_2915_h.n_1700_B(3.0, 0.0, 3.0, 13.0, 16.0, 13.0);
    protected static final s_1395_c Q_4569_t = T_2915_h.n_1700_B(6.5, 0.0, 6.5, 9.5, 16.0, 9.5);
    public static final g_88_D M_182_A = BlockStateProperties.z_1333_t;
    public static final e_563_h<d_2484_X> t_1786_h = BlockStateProperties.l_4537_E;
    public static final g_88_D multiplayerClientSuggestionProvider = BlockStateProperties.dtoRealmsServerAddress;

    public E_872_n(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(M_182_A, 0)).n_1700_B(t_1786_h, d_2484_X.n_1700_B)).n_1700_B(multiplayerClientSuggestionProvider, 0));
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(M_182_A, t_1786_h, multiplayerClientSuggestionProvider);
    }

    @Override
    public q_4293_E.G_564_y R_4764_Y() {
        return q_4293_E.G_564_y.J_1907_R;
    }

    @Override
    public boolean a_(K_4074_S state, BlockGetter reader, c_1514_x pos) {
        return true;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        s_1395_c voxelshape = state.R_4764_Y(t_1786_h) == d_2484_X.R_4764_Y ? h_1847_R : P_4830_p;
        e_2866_D vector3d = state.h_1847_R(worldIn, pos);
        return voxelshape.n_1700_B(vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }

    @Override
    public s_1395_c J_1907_R(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        e_2866_D vector3d = state.h_1847_R(worldIn, pos);
        return Q_4569_t.n_1700_B(vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y);
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        if (!fluidstate.R_4764_Y()) {
            return null;
        }
        K_4074_S blockstate = context.getWorld().getBlockState(context.getPos().down());
        if (blockstate.n_1700_B(BlockTags.s_2632_s)) {
            if (blockstate.n_1700_B(a_3742_W.m_3828_C)) {
                return (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(M_182_A, 0);
            }
            if (blockstate.n_1700_B(a_3742_W.t_1509_b)) {
                int i = blockstate.R_4764_Y(M_182_A) > 0 ? 1 : 0;
                return (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(M_182_A, i);
            }
            K_4074_S blockstate1 = context.getWorld().getBlockState(context.getPos().up());
            return !blockstate1.n_1700_B(a_3742_W.t_1509_b) && !blockstate1.n_1700_B(a_3742_W.m_3828_C) ? a_3742_W.m_3828_C.multiplayerClientSuggestionProvider() : (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(M_182_A, blockstate1.R_4764_Y(M_182_A));
        }
        return null;
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        if (!state.n_1700_B((T_1316_M)worldIn, pos)) {
            worldIn.J_1907_R(pos, true);
        }
    }

    @Override
    public boolean a_(K_4074_S state) {
        return state.R_4764_Y(multiplayerClientSuggestionProvider) == 0;
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        int i;
        if (state.R_4764_Y(multiplayerClientSuggestionProvider) == 0 && random.nextInt(3) == 0 && worldIn.u_1723_Y(pos.up()) && worldIn.n_1700_B(pos.up(), 0) >= 9 && (i = this.J_1907_R(worldIn, pos) + 1) < 16) {
            this.n_1700_B(state, (b_4507_u)worldIn, pos, random, i);
        }
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        return worldIn.getBlockState(pos.down()).n_1700_B(BlockTags.s_2632_s);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (!stateIn.n_1700_B(worldIn, currentPos)) {
            worldIn.u_2550_I().n_1700_B(currentPos, this, 1);
        }
        if (facing == b_257_Y.J_1907_R && facingState.n_1700_B(a_3742_W.t_1509_b) && facingState.R_4764_Y(M_182_A) > stateIn.R_4764_Y(M_182_A)) {
            worldIn.n_1700_B(currentPos, (K_4074_S)stateIn.n_1700_B(M_182_A), 2);
        }
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state, boolean isClient) {
        int j;
        int i = this.n_1700_B(worldIn, pos);
        return i + (j = this.J_1907_R(worldIn, pos)) + 1 < 16 && worldIn.getBlockState(pos.up(i)).R_4764_Y(multiplayerClientSuggestionProvider) != 1;
    }

    @Override
    public boolean n_1700_B(b_4507_u worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        return true;
    }

    @Override
    public void n_1700_B(e_3591_l worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        int i = this.n_1700_B((BlockGetter)worldIn, pos);
        int j = this.J_1907_R(worldIn, pos);
        int k = i + j + 1;
        int l = 1 + rand.nextInt(2);
        for (int i1 = 0; i1 < l; ++i1) {
            c_1514_x blockpos = pos.up(i);
            K_4074_S blockstate = worldIn.getBlockState(blockpos);
            if (k >= 16 || blockstate.R_4764_Y(multiplayerClientSuggestionProvider) == 1 || !worldIn.u_1723_Y(blockpos.up())) {
                return;
            }
            this.n_1700_B(blockstate, (b_4507_u)worldIn, blockpos, rand, k);
            ++i;
            ++k;
        }
    }

    @Override
    public float n_1700_B(K_4074_S state, a_3913_L player, BlockGetter worldIn, c_1514_x pos) {
        return player.A_2714_y().J_1907_R() instanceof SwordItem ? 1.0f : super.n_1700_B(state, player, worldIn, pos);
    }

    protected void n_1700_B(K_4074_S blockStateIn, b_4507_u worldIn, c_1514_x posIn, Random rand, int maxTotalSize) {
        K_4074_S blockstate = worldIn.getBlockState(posIn.down());
        c_1514_x blockpos = posIn.down(2);
        K_4074_S blockstate1 = worldIn.getBlockState(blockpos);
        d_2484_X bambooleaves = d_2484_X.n_1700_B;
        if (maxTotalSize >= 1) {
            if (blockstate.n_1700_B(a_3742_W.t_1509_b) && blockstate.R_4764_Y(t_1786_h) != d_2484_X.n_1700_B) {
                if (blockstate.n_1700_B(a_3742_W.t_1509_b) && blockstate.R_4764_Y(t_1786_h) != d_2484_X.n_1700_B) {
                    bambooleaves = d_2484_X.R_4764_Y;
                    if (blockstate1.n_1700_B(a_3742_W.t_1509_b)) {
                        worldIn.n_1700_B(posIn.down(), (K_4074_S)blockstate.n_1700_B(t_1786_h, d_2484_X.J_1907_R), 3);
                        worldIn.n_1700_B(blockpos, (K_4074_S)blockstate1.n_1700_B(t_1786_h, d_2484_X.n_1700_B), 3);
                    }
                }
            } else {
                bambooleaves = d_2484_X.J_1907_R;
            }
        }
        int i = blockStateIn.R_4764_Y(M_182_A) != 1 && !blockstate1.n_1700_B(a_3742_W.t_1509_b) ? 0 : 1;
        int j = !(maxTotalSize >= 11 && rand.nextFloat() < 0.25f || maxTotalSize == 15) ? 0 : 1;
        worldIn.n_1700_B(posIn.up(), (K_4074_S)((K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(M_182_A, i)).n_1700_B(t_1786_h, bambooleaves)).n_1700_B(multiplayerClientSuggestionProvider, j), 3);
    }

    protected int n_1700_B(BlockGetter worldIn, c_1514_x pos) {
        int i;
        for (i = 0; i < 16 && worldIn.getBlockState(pos.up(i + 1)).n_1700_B(a_3742_W.t_1509_b); ++i) {
        }
        return i;
    }

    protected int J_1907_R(BlockGetter worldIn, c_1514_x pos) {
        int i;
        for (i = 0; i < 16 && worldIn.getBlockState(pos.down(i + 1)).n_1700_B(a_3742_W.t_1509_b); ++i) {
        }
        return i;
    }
}


