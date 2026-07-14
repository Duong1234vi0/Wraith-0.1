/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.Y_1820_h;
import lightning.product.Y_1835_y;
import lightning.product.FenceGateBlock;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_563_h;
import lightning.product.SimpleWaterloggedBlock;
import lightning.product.BlockTags;
import lightning.product.IronBarsBlock;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.v_3760_Q;
import lightning.product.x_268_Y;
import lightning.product.BooleanOp;

public class W_3538_l
extends T_2915_h
implements SimpleWaterloggedBlock {
    public static final U_1266_O P_4830_p = BlockStateProperties.e_4240_b;
    public static final e_563_h<Y_1820_h> h_1847_R = BlockStateProperties.B_1668_F;
    public static final e_563_h<Y_1820_h> Q_4569_t = BlockStateProperties.g_164_R;
    public static final e_563_h<Y_1820_h> M_182_A = BlockStateProperties.X_933_l;
    public static final e_563_h<Y_1820_h> t_1786_h = BlockStateProperties.Z_976_R;
    public static final U_1266_O multiplayerClientSuggestionProvider = BlockStateProperties.A_4115_X;
    private final Map<K_4074_S, s_1395_c> w_1457_N;
    private final Map<K_4074_S, s_1395_c> Y_601_j;
    private static final s_1395_c Y_259_p = T_2915_h.n_1700_B(7.0, 0.0, 7.0, 9.0, 16.0, 9.0);
    private static final s_1395_c Q_2552_b = T_2915_h.n_1700_B(7.0, 0.0, 0.0, 9.0, 16.0, 9.0);
    private static final s_1395_c C_2741_M = T_2915_h.n_1700_B(7.0, 0.0, 7.0, 9.0, 16.0, 16.0);
    private static final s_1395_c k_2293_S = T_2915_h.n_1700_B(0.0, 0.0, 7.0, 9.0, 16.0, 9.0);
    private static final s_1395_c q_2307_F = T_2915_h.n_1700_B(7.0, 0.0, 7.0, 16.0, 16.0, 9.0);

    public W_3538_l(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, true)).n_1700_B(Q_4569_t, Y_1820_h.n_1700_B)).n_1700_B(h_1847_R, Y_1820_h.n_1700_B)).n_1700_B(M_182_A, Y_1820_h.n_1700_B)).n_1700_B(t_1786_h, Y_1820_h.n_1700_B)).n_1700_B(multiplayerClientSuggestionProvider, false));
        this.w_1457_N = this.n_1700_B(4.0f, 3.0f, 16.0f, 0.0f, 14.0f, 16.0f);
        this.Y_601_j = this.n_1700_B(4.0f, 3.0f, 24.0f, 0.0f, 24.0f, 24.0f);
    }

    private static s_1395_c n_1700_B(s_1395_c baseShape, Y_1820_h height, s_1395_c lowShape, s_1395_c tallShape) {
        if (height == Y_1820_h.R_4764_Y) {
            return x_268_Y.n_1700_B(baseShape, tallShape);
        }
        return height == Y_1820_h.J_1907_R ? x_268_Y.n_1700_B(baseShape, lowShape) : baseShape;
    }

    private Map<K_4074_S, s_1395_c> n_1700_B(float p_235624_1_, float p_235624_2_, float p_235624_3_, float p_235624_4_, float p_235624_5_, float p_235624_6_) {
        float f = 8.0f - p_235624_1_;
        float f1 = 8.0f + p_235624_1_;
        float f2 = 8.0f - p_235624_2_;
        float f3 = 8.0f + p_235624_2_;
        s_1395_c voxelshape = T_2915_h.n_1700_B(f, 0.0, f, f1, p_235624_3_, f1);
        s_1395_c voxelshape1 = T_2915_h.n_1700_B(f2, p_235624_4_, 0.0, f3, p_235624_5_, f3);
        s_1395_c voxelshape2 = T_2915_h.n_1700_B(f2, p_235624_4_, f2, f3, p_235624_5_, 16.0);
        s_1395_c voxelshape3 = T_2915_h.n_1700_B(0.0, p_235624_4_, f2, f3, p_235624_5_, f3);
        s_1395_c voxelshape4 = T_2915_h.n_1700_B(f2, p_235624_4_, f2, 16.0, p_235624_5_, f3);
        s_1395_c voxelshape5 = T_2915_h.n_1700_B(f2, p_235624_4_, 0.0, f3, p_235624_6_, f3);
        s_1395_c voxelshape6 = T_2915_h.n_1700_B(f2, p_235624_4_, f2, f3, p_235624_6_, 16.0);
        s_1395_c voxelshape7 = T_2915_h.n_1700_B(0.0, p_235624_4_, f2, f3, p_235624_6_, f3);
        s_1395_c voxelshape8 = T_2915_h.n_1700_B(f2, p_235624_4_, f2, 16.0, p_235624_6_, f3);
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (Boolean obool : P_4830_p.n_1700_B()) {
            for (Y_1820_h wallheight : h_1847_R.n_1700_B()) {
                for (Y_1820_h wallheight1 : Q_4569_t.n_1700_B()) {
                    for (Y_1820_h wallheight2 : t_1786_h.n_1700_B()) {
                        for (Y_1820_h wallheight3 : M_182_A.n_1700_B()) {
                            s_1395_c voxelshape9 = x_268_Y.n_1700_B();
                            voxelshape9 = W_3538_l.n_1700_B(voxelshape9, wallheight, voxelshape4, voxelshape8);
                            voxelshape9 = W_3538_l.n_1700_B(voxelshape9, wallheight2, voxelshape3, voxelshape7);
                            voxelshape9 = W_3538_l.n_1700_B(voxelshape9, wallheight1, voxelshape1, voxelshape5);
                            voxelshape9 = W_3538_l.n_1700_B(voxelshape9, wallheight3, voxelshape2, voxelshape6);
                            if (obool.booleanValue()) {
                                voxelshape9 = x_268_Y.n_1700_B(voxelshape9, voxelshape);
                            }
                            K_4074_S blockstate = (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, obool)).n_1700_B(h_1847_R, wallheight)).n_1700_B(t_1786_h, wallheight2)).n_1700_B(Q_4569_t, wallheight1)).n_1700_B(M_182_A, wallheight3);
                            builder.put((Object)((K_4074_S)blockstate.n_1700_B(multiplayerClientSuggestionProvider, false)), (Object)voxelshape9);
                            builder.put((Object)((K_4074_S)blockstate.n_1700_B(multiplayerClientSuggestionProvider, true)), (Object)voxelshape9);
                        }
                    }
                }
            }
        }
        return builder.build();
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return this.w_1457_N.get(state);
    }

    @Override
    public s_1395_c J_1907_R(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return this.Y_601_j.get(state);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }

    private boolean n_1700_B(K_4074_S state, boolean sideSolid, b_257_Y direction) {
        T_2915_h block = state.J_1907_R();
        boolean flag = block instanceof FenceGateBlock && FenceGateBlock.n_1700_B(state, direction);
        return state.n_1700_B(BlockTags.x_607_J) || !W_3538_l.R_4764_Y(block) && sideSolid || block instanceof IronBarsBlock || flag;
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        b_4507_u iworldreader = context.getWorld();
        c_1514_x blockpos = context.getPos();
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        c_1514_x blockpos1 = blockpos.north();
        c_1514_x blockpos2 = blockpos.east();
        c_1514_x blockpos3 = blockpos.south();
        c_1514_x blockpos4 = blockpos.west();
        c_1514_x blockpos5 = blockpos.up();
        K_4074_S blockstate = iworldreader.getBlockState(blockpos1);
        K_4074_S blockstate1 = iworldreader.getBlockState(blockpos2);
        K_4074_S blockstate2 = iworldreader.getBlockState(blockpos3);
        K_4074_S blockstate3 = iworldreader.getBlockState(blockpos4);
        K_4074_S blockstate4 = iworldreader.getBlockState(blockpos5);
        boolean flag = this.n_1700_B(blockstate, blockstate.G_564_y((BlockGetter)iworldreader, blockpos1, b_257_Y.G_564_y), b_257_Y.G_564_y);
        boolean flag1 = this.n_1700_B(blockstate1, blockstate1.G_564_y((BlockGetter)iworldreader, blockpos2, b_257_Y.P_1922_E), b_257_Y.P_1922_E);
        boolean flag2 = this.n_1700_B(blockstate2, blockstate2.G_564_y((BlockGetter)iworldreader, blockpos3, b_257_Y.R_4764_Y), b_257_Y.R_4764_Y);
        boolean flag3 = this.n_1700_B(blockstate3, blockstate3.G_564_y((BlockGetter)iworldreader, blockpos4, b_257_Y.u_1723_Y), b_257_Y.u_1723_Y);
        K_4074_S blockstate5 = (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(multiplayerClientSuggestionProvider, fluidstate.n_1700_B() == Fluids.R_4764_Y);
        return this.n_1700_B(iworldreader, blockstate5, blockpos5, blockstate4, flag, flag1, flag2, flag3);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (stateIn.R_4764_Y(multiplayerClientSuggestionProvider).booleanValue()) {
            worldIn.M_588_G().n_1700_B(currentPos, Fluids.R_4764_Y, Fluids.R_4764_Y.n_1700_B(worldIn));
        }
        if (facing == b_257_Y.n_1700_B) {
            return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
        return facing == b_257_Y.J_1907_R ? this.n_1700_B((T_1316_M)worldIn, stateIn, facingPos, facingState) : this.n_1700_B(worldIn, currentPos, stateIn, facingPos, facingState, facing);
    }

    private static boolean n_1700_B(K_4074_S state, v_3760_Q<Y_1820_h> heightProperty) {
        return state.R_4764_Y(heightProperty) != Y_1820_h.n_1700_B;
    }

    private static boolean n_1700_B(s_1395_c shape1, s_1395_c shape2) {
        return !x_268_Y.R_4764_Y(shape2, shape1, BooleanOp.P_1922_E);
    }

    private K_4074_S n_1700_B(T_1316_M reader, K_4074_S state1, c_1514_x pos, K_4074_S state2) {
        boolean flag = W_3538_l.n_1700_B(state1, Q_4569_t);
        boolean flag1 = W_3538_l.n_1700_B(state1, h_1847_R);
        boolean flag2 = W_3538_l.n_1700_B(state1, M_182_A);
        boolean flag3 = W_3538_l.n_1700_B(state1, t_1786_h);
        return this.n_1700_B(reader, state1, pos, state2, flag, flag1, flag2, flag3);
    }

    private K_4074_S n_1700_B(T_1316_M reader, c_1514_x p_235627_2_, K_4074_S p_235627_3_, c_1514_x p_235627_4_, K_4074_S p_235627_5_, b_257_Y directionIn) {
        b_257_Y direction = directionIn.u_1723_Y();
        boolean flag = directionIn == b_257_Y.R_4764_Y ? this.n_1700_B(p_235627_5_, p_235627_5_.G_564_y((BlockGetter)reader, p_235627_4_, direction), direction) : W_3538_l.n_1700_B(p_235627_3_, Q_4569_t);
        boolean flag1 = directionIn == b_257_Y.u_1723_Y ? this.n_1700_B(p_235627_5_, p_235627_5_.G_564_y((BlockGetter)reader, p_235627_4_, direction), direction) : W_3538_l.n_1700_B(p_235627_3_, h_1847_R);
        boolean flag2 = directionIn == b_257_Y.G_564_y ? this.n_1700_B(p_235627_5_, p_235627_5_.G_564_y((BlockGetter)reader, p_235627_4_, direction), direction) : W_3538_l.n_1700_B(p_235627_3_, M_182_A);
        boolean flag3 = directionIn == b_257_Y.P_1922_E ? this.n_1700_B(p_235627_5_, p_235627_5_.G_564_y((BlockGetter)reader, p_235627_4_, direction), direction) : W_3538_l.n_1700_B(p_235627_3_, t_1786_h);
        c_1514_x blockpos = p_235627_2_.up();
        K_4074_S blockstate = reader.getBlockState(blockpos);
        return this.n_1700_B(reader, p_235627_3_, blockpos, blockstate, flag, flag1, flag2, flag3);
    }

    private K_4074_S n_1700_B(T_1316_M reader, K_4074_S state, c_1514_x pos, K_4074_S collisionState, boolean connectedSouth, boolean connectedWest, boolean connectedNorth, boolean connectedEast) {
        s_1395_c voxelshape = collisionState.u_2550_I(reader, pos).n_1700_B(b_257_Y.n_1700_B);
        K_4074_S blockstate = this.n_1700_B(state, connectedSouth, connectedWest, connectedNorth, connectedEast, voxelshape);
        return (K_4074_S)blockstate.n_1700_B(P_4830_p, this.n_1700_B(blockstate, collisionState, voxelshape));
    }

    private boolean n_1700_B(K_4074_S p_235628_1_, K_4074_S p_235628_2_, s_1395_c shape) {
        boolean flag6;
        boolean flag5;
        boolean flag;
        boolean bl = flag = p_235628_2_.J_1907_R() instanceof W_3538_l && p_235628_2_.R_4764_Y(P_4830_p) != false;
        if (flag) {
            return true;
        }
        Y_1820_h wallheight = p_235628_1_.R_4764_Y(Q_4569_t);
        Y_1820_h wallheight1 = p_235628_1_.R_4764_Y(M_182_A);
        Y_1820_h wallheight2 = p_235628_1_.R_4764_Y(h_1847_R);
        Y_1820_h wallheight3 = p_235628_1_.R_4764_Y(t_1786_h);
        boolean flag1 = wallheight1 == Y_1820_h.n_1700_B;
        boolean flag2 = wallheight3 == Y_1820_h.n_1700_B;
        boolean flag3 = wallheight2 == Y_1820_h.n_1700_B;
        boolean flag4 = wallheight == Y_1820_h.n_1700_B;
        boolean bl2 = flag5 = flag4 && flag1 && flag2 && flag3 || flag4 != flag1 || flag2 != flag3;
        if (flag5) {
            return true;
        }
        boolean bl3 = flag6 = wallheight == Y_1820_h.R_4764_Y && wallheight1 == Y_1820_h.R_4764_Y || wallheight2 == Y_1820_h.R_4764_Y && wallheight3 == Y_1820_h.R_4764_Y;
        if (flag6) {
            return false;
        }
        return p_235628_2_.J_1907_R().n_1700_B(BlockTags.Ops) || W_3538_l.n_1700_B(shape, Y_259_p);
    }

    private K_4074_S n_1700_B(K_4074_S state, boolean connectedSouth, boolean connectedWest, boolean connectedNorth, boolean connectedEast, s_1395_c shape) {
        return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)state.n_1700_B(Q_4569_t, this.n_1700_B(connectedSouth, shape, Q_2552_b))).n_1700_B(h_1847_R, this.n_1700_B(connectedWest, shape, q_2307_F))).n_1700_B(M_182_A, this.n_1700_B(connectedNorth, shape, C_2741_M))).n_1700_B(t_1786_h, this.n_1700_B(connectedEast, shape, k_2293_S));
    }

    private Y_1820_h n_1700_B(boolean p_235633_1_, s_1395_c p_235633_2_, s_1395_c p_235633_3_) {
        if (p_235633_1_) {
            return W_3538_l.n_1700_B(p_235633_2_, p_235633_3_) ? Y_1820_h.R_4764_Y : Y_1820_h.J_1907_R;
        }
        return Y_1820_h.n_1700_B;
    }

    @Override
    public FluidState P_1922_E(K_4074_S state) {
        return state.R_4764_Y(multiplayerClientSuggestionProvider) != false ? Fluids.R_4764_Y.n_1700_B(false) : super.P_1922_E(state);
    }

    @Override
    public boolean a_(K_4074_S state, BlockGetter reader, c_1514_x pos) {
        return state.R_4764_Y(multiplayerClientSuggestionProvider) == false;
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(P_4830_p, Q_4569_t, h_1847_R, t_1786_h, M_182_A, multiplayerClientSuggestionProvider);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        switch (rot) {
            case R_4764_Y: {
                return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)state.n_1700_B(Q_4569_t, state.R_4764_Y(M_182_A))).n_1700_B(h_1847_R, state.R_4764_Y(t_1786_h))).n_1700_B(M_182_A, state.R_4764_Y(Q_4569_t))).n_1700_B(t_1786_h, state.R_4764_Y(h_1847_R));
            }
            case G_564_y: {
                return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)state.n_1700_B(Q_4569_t, state.R_4764_Y(h_1847_R))).n_1700_B(h_1847_R, state.R_4764_Y(M_182_A))).n_1700_B(M_182_A, state.R_4764_Y(t_1786_h))).n_1700_B(t_1786_h, state.R_4764_Y(Q_4569_t));
            }
            case J_1907_R: {
                return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)state.n_1700_B(Q_4569_t, state.R_4764_Y(t_1786_h))).n_1700_B(h_1847_R, state.R_4764_Y(Q_4569_t))).n_1700_B(M_182_A, state.R_4764_Y(h_1847_R))).n_1700_B(t_1786_h, state.R_4764_Y(M_182_A));
            }
        }
        return state;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        switch (mirrorIn) {
            case J_1907_R: {
                return (K_4074_S)((K_4074_S)state.n_1700_B(Q_4569_t, state.R_4764_Y(M_182_A))).n_1700_B(M_182_A, state.R_4764_Y(Q_4569_t));
            }
            case R_4764_Y: {
                return (K_4074_S)((K_4074_S)state.n_1700_B(h_1847_R, state.R_4764_Y(t_1786_h))).n_1700_B(t_1786_h, state.R_4764_Y(h_1847_R));
            }
        }
        return super.n_1700_B(state, mirrorIn);
    }
}


