/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.L_2532_m;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_563_h;
import lightning.product.g_3212_H;
import lightning.product.DirectionProperty;
import lightning.product.m_3054_I;
import lightning.product.DoublePlantBlock;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.Material;
import lightning.product.t_3546_P;
import lightning.product.u_530_F;
import lightning.product.w_1454_v;
import lightning.product.x_1688_C;

public class S_1431_H
extends T_2915_h {
    public static final DirectionProperty P_4830_p = HorizontalDirectionalBlock.w_612_n;
    public static final U_1266_O h_1847_R = BlockStateProperties.Y_259_p;
    public static final e_563_h<L_2532_m> Q_4569_t = BlockStateProperties.RegionPingResult;
    public static final U_1266_O M_182_A = BlockStateProperties.C_2741_M;
    public static final e_563_h<g_3212_H> t_1786_h = BlockStateProperties.T_3594_S;
    protected static final s_1395_c multiplayerClientSuggestionProvider = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 16.0, 3.0);
    protected static final s_1395_c w_1457_N = T_2915_h.n_1700_B(0.0, 0.0, 13.0, 16.0, 16.0, 16.0);
    protected static final s_1395_c Y_601_j = T_2915_h.n_1700_B(13.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    protected static final s_1395_c Y_259_p = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 3.0, 16.0, 16.0);

    protected S_1431_H(q_4293_E.P_1922_E builder) {
        super(builder);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, b_257_Y.R_4764_Y)).n_1700_B(h_1847_R, false)).n_1700_B(Q_4569_t, L_2532_m.n_1700_B)).n_1700_B(M_182_A, false)).n_1700_B(t_1786_h, g_3212_H.J_1907_R));
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        b_257_Y direction = state.R_4764_Y(P_4830_p);
        boolean flag = state.R_4764_Y(h_1847_R) == false;
        boolean flag1 = state.R_4764_Y(Q_4569_t) == L_2532_m.J_1907_R;
        switch (direction) {
            default: {
                return flag ? Y_259_p : (flag1 ? w_1457_N : multiplayerClientSuggestionProvider);
            }
            case G_564_y: {
                return flag ? multiplayerClientSuggestionProvider : (flag1 ? Y_259_p : Y_601_j);
            }
            case P_1922_E: {
                return flag ? Y_601_j : (flag1 ? multiplayerClientSuggestionProvider : w_1457_N);
            }
            case R_4764_Y: 
        }
        return flag ? w_1457_N : (flag1 ? Y_601_j : Y_259_p);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        g_3212_H doubleblockhalf = stateIn.R_4764_Y(t_1786_h);
        if (facing.h_1847_R() == b_257_Y.n_1700_B.J_1907_R && doubleblockhalf == g_3212_H.J_1907_R == (facing == b_257_Y.J_1907_R)) {
            return facingState.n_1700_B(this) && facingState.R_4764_Y(t_1786_h) != doubleblockhalf ? (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)stateIn.n_1700_B(P_4830_p, facingState.R_4764_Y(P_4830_p))).n_1700_B(h_1847_R, facingState.R_4764_Y(h_1847_R))).n_1700_B(Q_4569_t, facingState.R_4764_Y(Q_4569_t))).n_1700_B(M_182_A, facingState.R_4764_Y(M_182_A)) : a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
        }
        return doubleblockhalf == g_3212_H.J_1907_R && facing == b_257_Y.n_1700_B && !stateIn.n_1700_B(worldIn, currentPos) ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, a_3913_L player) {
        if (!worldIn.Y_259_p && player.G_624_v()) {
            DoublePlantBlock.J_1907_R(worldIn, pos, state, player);
        }
        super.n_1700_B(worldIn, pos, state, player);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        switch (type) {
            case n_1700_B: {
                return state.R_4764_Y(h_1847_R);
            }
            case J_1907_R: {
                return false;
            }
            case R_4764_Y: {
                return state.R_4764_Y(h_1847_R);
            }
        }
        return false;
    }

    private int J_1907_R() {
        return this.J_1907_R == Material.z_1737_N ? 1011 : 1012;
    }

    private int t_148_a() {
        return this.J_1907_R == Material.z_1737_N ? 1005 : 1006;
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        c_1514_x blockpos = context.getPos();
        if (blockpos.getY() < 255 && context.getWorld().getBlockState(blockpos.up()).n_1700_B(context)) {
            b_4507_u world = context.getWorld();
            boolean flag = world.Y_601_j(blockpos) || world.Y_601_j(blockpos.up());
            return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, context.getPlacementHorizontalFacing())).n_1700_B(Q_4569_t, this.J_1907_R(context))).n_1700_B(M_182_A, flag)).n_1700_B(h_1847_R, flag)).n_1700_B(t_1786_h, g_3212_H.J_1907_R);
        }
        return null;
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, r_4811_B placer, Z_1993_T stack) {
        worldIn.n_1700_B(pos.up(), (K_4074_S)state.n_1700_B(t_1786_h, g_3212_H.n_1700_B), 3);
    }

    private L_2532_m J_1907_R(BlockPlaceContext context) {
        boolean flag1;
        b_4507_u iblockreader = context.getWorld();
        c_1514_x blockpos = context.getPos();
        b_257_Y direction = context.getPlacementHorizontalFacing();
        c_1514_x blockpos1 = blockpos.up();
        b_257_Y direction1 = direction.w_1484_f();
        c_1514_x blockpos2 = blockpos.offset(direction1);
        K_4074_S blockstate = iblockreader.getBlockState(blockpos2);
        c_1514_x blockpos3 = blockpos1.offset(direction1);
        K_4074_S blockstate1 = iblockreader.getBlockState(blockpos3);
        b_257_Y direction2 = direction.v_4262_N();
        c_1514_x blockpos4 = blockpos.offset(direction2);
        K_4074_S blockstate2 = iblockreader.getBlockState(blockpos4);
        c_1514_x blockpos5 = blockpos1.offset(direction2);
        K_4074_S blockstate3 = iblockreader.getBlockState(blockpos5);
        int i = (blockstate.multiplayerClientSuggestionProvider(iblockreader, blockpos2) ? -1 : 0) + (blockstate1.multiplayerClientSuggestionProvider(iblockreader, blockpos3) ? -1 : 0) + (blockstate2.multiplayerClientSuggestionProvider(iblockreader, blockpos4) ? 1 : 0) + (blockstate3.multiplayerClientSuggestionProvider(iblockreader, blockpos5) ? 1 : 0);
        boolean flag = blockstate.n_1700_B(this) && blockstate.R_4764_Y(t_1786_h) == g_3212_H.J_1907_R;
        boolean bl = flag1 = blockstate2.n_1700_B(this) && blockstate2.R_4764_Y(t_1786_h) == g_3212_H.J_1907_R;
        if ((!flag || flag1) && i <= 0) {
            if ((!flag1 || flag) && i >= 0) {
                int j = direction.t_148_a();
                int k = direction.u_2550_I();
                e_2866_D vector3d = context.getHitVec();
                double d0 = vector3d.J_1907_R - (double)blockpos.getX();
                double d1 = vector3d.G_564_y - (double)blockpos.getZ();
                return !(j < 0 && d1 < 0.5 || j > 0 && d1 > 0.5 || k < 0 && d0 > 0.5 || k > 0 && d0 < 0.5) ? L_2532_m.n_1700_B : L_2532_m.J_1907_R;
            }
            return L_2532_m.n_1700_B;
        }
        return L_2532_m.J_1907_R;
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (this.J_1907_R == Material.z_1737_N) {
            return m_3054_I.R_4764_Y;
        }
        state = (K_4074_S)state.n_1700_B(h_1847_R);
        worldIn.n_1700_B(pos, state, 10);
        worldIn.n_1700_B(player, state.R_4764_Y(h_1847_R) != false ? this.t_148_a() : this.J_1907_R(), pos, 0);
        return m_3054_I.n_1700_B(worldIn.Y_259_p);
    }

    public boolean w_1484_f(K_4074_S state) {
        return state.R_4764_Y(h_1847_R);
    }

    public void n_1700_B(b_4507_u worldIn, K_4074_S state, c_1514_x pos, boolean open) {
        if (state.n_1700_B(this) && state.R_4764_Y(h_1847_R) != open) {
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(h_1847_R, open), 10);
            this.n_1700_B(worldIn, pos, open);
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, T_2915_h blockIn, c_1514_x fromPos, boolean isMoving) {
        boolean flag;
        boolean bl = worldIn.Y_601_j(pos) || worldIn.Y_601_j(pos.offset(state.R_4764_Y(t_1786_h) == g_3212_H.J_1907_R ? b_257_Y.J_1907_R : b_257_Y.n_1700_B)) ? true : (flag = false);
        if (blockIn != this && flag != state.R_4764_Y(M_182_A)) {
            if (flag != state.R_4764_Y(h_1847_R)) {
                this.n_1700_B(worldIn, pos, flag);
            }
            worldIn.n_1700_B(pos, (K_4074_S)((K_4074_S)state.n_1700_B(M_182_A, flag)).n_1700_B(h_1847_R, flag), 2);
        }
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        c_1514_x blockpos = pos.down();
        K_4074_S blockstate = worldIn.getBlockState(blockpos);
        return state.R_4764_Y(t_1786_h) == g_3212_H.J_1907_R ? blockstate.G_564_y((BlockGetter)worldIn, blockpos, b_257_Y.J_1907_R) : blockstate.n_1700_B(this);
    }

    private void n_1700_B(b_4507_u worldIn, c_1514_x pos, boolean isOpening) {
        worldIn.n_1700_B((a_3913_L)null, isOpening ? this.t_148_a() : this.J_1907_R(), pos, 0);
    }

    @Override
    public w_1454_v G_564_y(K_4074_S state) {
        return w_1454_v.J_1907_R;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        return (K_4074_S)state.n_1700_B(P_4830_p, rot.n_1700_B(state.R_4764_Y(P_4830_p)));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        return mirrorIn == q_4099_E.n_1700_B ? state : (K_4074_S)state.n_1700_B(mirrorIn.n_1700_B(state.R_4764_Y(P_4830_p))).n_1700_B(Q_4569_t);
    }

    @Override
    public long n_1700_B(K_4074_S state, c_1514_x pos) {
        return u_530_F.R_4764_Y(pos.getX(), pos.down(state.R_4764_Y(t_1786_h) == g_3212_H.J_1907_R ? 0 : 1).getY(), pos.getZ());
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(t_1786_h, P_4830_p, h_1847_R, Q_4569_t, M_182_A);
    }

    public static boolean n_1700_B(b_4507_u world, c_1514_x pos) {
        return S_1431_H.t_148_a(world.getBlockState(pos));
    }

    public static boolean t_148_a(K_4074_S state) {
        return state.J_1907_R() instanceof S_1431_H && (state.R_4764_Y() == Material.q_2307_F || state.R_4764_Y() == Material.Z_875_P);
    }
}


