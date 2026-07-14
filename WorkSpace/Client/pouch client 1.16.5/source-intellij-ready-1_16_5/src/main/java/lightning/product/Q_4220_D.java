/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.PipeBlock;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.j_3341_s;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.x_268_Y;

public class Q_4220_D
extends T_2915_h {
    public static final U_1266_O P_4830_p = PipeBlock.t_1786_h;
    public static final U_1266_O h_1847_R = PipeBlock.P_4830_p;
    public static final U_1266_O Q_4569_t = PipeBlock.h_1847_R;
    public static final U_1266_O M_182_A = PipeBlock.Q_4569_t;
    public static final U_1266_O t_1786_h = PipeBlock.M_182_A;
    public static final Map<b_257_Y, U_1266_O> multiplayerClientSuggestionProvider = PipeBlock.w_1457_N.entrySet().stream().filter(facingProperty -> facingProperty.getKey() != b_257_Y.n_1700_B).collect(j_3341_s.n_1700_B());
    private static final s_1395_c w_1457_N = T_2915_h.n_1700_B(0.0, 15.0, 0.0, 16.0, 16.0, 16.0);
    private static final s_1395_c Y_601_j = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 1.0, 16.0, 16.0);
    private static final s_1395_c Y_259_p = T_2915_h.n_1700_B(15.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    private static final s_1395_c Q_2552_b = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 16.0, 1.0);
    private static final s_1395_c C_2741_M = T_2915_h.n_1700_B(0.0, 0.0, 15.0, 16.0, 16.0, 16.0);
    private final Map<K_4074_S, s_1395_c> k_2293_S;

    public Q_4220_D(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, false)).n_1700_B(h_1847_R, false)).n_1700_B(Q_4569_t, false)).n_1700_B(M_182_A, false)).n_1700_B(t_1786_h, false));
        this.k_2293_S = ImmutableMap.copyOf(this.x_607_J.n_1700_B().stream().collect(Collectors.toMap(Function.identity(), Q_4220_D::w_1484_f)));
    }

    private static s_1395_c w_1484_f(K_4074_S state) {
        s_1395_c voxelshape = x_268_Y.n_1700_B();
        if (state.R_4764_Y(P_4830_p).booleanValue()) {
            voxelshape = w_1457_N;
        }
        if (state.R_4764_Y(h_1847_R).booleanValue()) {
            voxelshape = x_268_Y.n_1700_B(voxelshape, Q_2552_b);
        }
        if (state.R_4764_Y(M_182_A).booleanValue()) {
            voxelshape = x_268_Y.n_1700_B(voxelshape, C_2741_M);
        }
        if (state.R_4764_Y(Q_4569_t).booleanValue()) {
            voxelshape = x_268_Y.n_1700_B(voxelshape, Y_259_p);
        }
        if (state.R_4764_Y(t_1786_h).booleanValue()) {
            voxelshape = x_268_Y.n_1700_B(voxelshape, Y_601_j);
        }
        return voxelshape;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return this.k_2293_S.get(state);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        return this.t_148_a(this.v_4262_N(state, worldIn, pos));
    }

    private boolean t_148_a(K_4074_S state) {
        return this.P_4830_p(state) > 0;
    }

    private int P_4830_p(K_4074_S state) {
        int i = 0;
        for (U_1266_O booleanproperty : multiplayerClientSuggestionProvider.values()) {
            if (!state.R_4764_Y(booleanproperty).booleanValue()) continue;
            ++i;
        }
        return i;
    }

    private boolean J_1907_R(BlockGetter blockReader, c_1514_x pos, b_257_Y direction) {
        if (direction == b_257_Y.n_1700_B) {
            return false;
        }
        c_1514_x blockpos = pos.offset(direction);
        if (Q_4220_D.n_1700_B(blockReader, blockpos, direction)) {
            return true;
        }
        if (direction.h_1847_R() == b_257_Y.n_1700_B.J_1907_R) {
            return false;
        }
        U_1266_O booleanproperty = multiplayerClientSuggestionProvider.get(direction);
        K_4074_S blockstate = blockReader.getBlockState(pos.up());
        return blockstate.n_1700_B(this) && blockstate.R_4764_Y(booleanproperty) != false;
    }

    public static boolean n_1700_B(BlockGetter blockReader, c_1514_x worldIn, b_257_Y neighborPos) {
        K_4074_S blockstate = blockReader.getBlockState(worldIn);
        return T_2915_h.n_1700_B(blockstate.u_2550_I(blockReader, worldIn), neighborPos.u_1723_Y());
    }

    private K_4074_S v_4262_N(K_4074_S state, BlockGetter blockReader, c_1514_x pos) {
        c_1514_x blockpos = pos.up();
        if (state.R_4764_Y(P_4830_p).booleanValue()) {
            state = (K_4074_S)state.n_1700_B(P_4830_p, Q_4220_D.n_1700_B(blockReader, blockpos, b_257_Y.n_1700_B));
        }
        q_4293_E.n_1700_B blockstate = null;
        for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
            U_1266_O booleanproperty = Q_4220_D.n_1700_B(direction);
            if (!state.R_4764_Y(booleanproperty).booleanValue()) continue;
            boolean flag = this.J_1907_R(blockReader, pos, direction);
            if (!flag) {
                if (blockstate == null) {
                    blockstate = blockReader.getBlockState(blockpos);
                }
                flag = blockstate.n_1700_B(this) && blockstate.R_4764_Y(booleanproperty) != false;
            }
            state = (K_4074_S)state.n_1700_B(booleanproperty, flag);
        }
        return state;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (facing == b_257_Y.n_1700_B) {
            return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
        K_4074_S blockstate = this.v_4262_N(stateIn, worldIn, currentPos);
        return !this.t_148_a(blockstate) ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : blockstate;
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        if (worldIn.w_1457_N.nextInt(4) == 0) {
            b_257_Y direction = b_257_Y.n_1700_B(random);
            c_1514_x blockpos = pos.up();
            if (direction.h_1847_R().G_564_y() && !state.R_4764_Y(Q_4220_D.n_1700_B(direction)).booleanValue()) {
                if (this.n_1700_B((BlockGetter)worldIn, pos)) {
                    c_1514_x blockpos4 = pos.offset(direction);
                    K_4074_S blockstate4 = worldIn.getBlockState(blockpos4);
                    if (blockstate4.v_4262_N()) {
                        b_257_Y direction3 = direction.v_4262_N();
                        b_257_Y direction4 = direction.w_1484_f();
                        boolean flag = state.R_4764_Y(Q_4220_D.n_1700_B(direction3));
                        boolean flag1 = state.R_4764_Y(Q_4220_D.n_1700_B(direction4));
                        c_1514_x blockpos2 = blockpos4.offset(direction3);
                        c_1514_x blockpos3 = blockpos4.offset(direction4);
                        if (flag && Q_4220_D.n_1700_B((BlockGetter)worldIn, blockpos2, direction3)) {
                            worldIn.n_1700_B(blockpos4, (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(Q_4220_D.n_1700_B(direction3), true), 2);
                        } else if (flag1 && Q_4220_D.n_1700_B((BlockGetter)worldIn, blockpos3, direction4)) {
                            worldIn.n_1700_B(blockpos4, (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(Q_4220_D.n_1700_B(direction4), true), 2);
                        } else {
                            b_257_Y direction1 = direction.u_1723_Y();
                            if (flag && worldIn.u_1723_Y(blockpos2) && Q_4220_D.n_1700_B((BlockGetter)worldIn, pos.offset(direction3), direction1)) {
                                worldIn.n_1700_B(blockpos2, (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(Q_4220_D.n_1700_B(direction1), true), 2);
                            } else if (flag1 && worldIn.u_1723_Y(blockpos3) && Q_4220_D.n_1700_B((BlockGetter)worldIn, pos.offset(direction4), direction1)) {
                                worldIn.n_1700_B(blockpos3, (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(Q_4220_D.n_1700_B(direction1), true), 2);
                            } else if ((double)worldIn.w_1457_N.nextFloat() < 0.05 && Q_4220_D.n_1700_B((BlockGetter)worldIn, blockpos4.up(), b_257_Y.J_1907_R)) {
                                worldIn.n_1700_B(blockpos4, (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, true), 2);
                            }
                        }
                    } else if (Q_4220_D.n_1700_B((BlockGetter)worldIn, blockpos4, direction)) {
                        worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(Q_4220_D.n_1700_B(direction), true), 2);
                    }
                }
            } else {
                K_4074_S blockstate2;
                K_4074_S blockstate1;
                c_1514_x blockpos1;
                K_4074_S blockstate;
                if (direction == b_257_Y.J_1907_R && pos.getY() < 255) {
                    if (this.J_1907_R(worldIn, pos, direction)) {
                        worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, true), 2);
                        return;
                    }
                    if (worldIn.u_1723_Y(blockpos)) {
                        if (!this.n_1700_B((BlockGetter)worldIn, pos)) {
                            return;
                        }
                        K_4074_S blockstate3 = state;
                        for (b_257_Y direction2 : b_257_Y.R_4764_Y.n_1700_B) {
                            if (!random.nextBoolean() && Q_4220_D.n_1700_B((BlockGetter)worldIn, blockpos.offset(direction2), b_257_Y.J_1907_R)) continue;
                            blockstate3 = (K_4074_S)blockstate3.n_1700_B(Q_4220_D.n_1700_B(direction2), false);
                        }
                        if (this.h_1847_R(blockstate3)) {
                            worldIn.n_1700_B(blockpos, blockstate3, 2);
                        }
                        return;
                    }
                }
                if (pos.getY() > 0 && ((blockstate = worldIn.getBlockState(blockpos1 = pos.down())).v_4262_N() || blockstate.n_1700_B(this)) && (blockstate1 = blockstate.v_4262_N() ? this.multiplayerClientSuggestionProvider() : blockstate) != (blockstate2 = this.n_1700_B(state, blockstate1, random)) && this.h_1847_R(blockstate2)) {
                    worldIn.n_1700_B(blockpos1, blockstate2, 2);
                }
            }
        }
    }

    private K_4074_S n_1700_B(K_4074_S state, K_4074_S state2, Random rand) {
        for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
            U_1266_O booleanproperty;
            if (!rand.nextBoolean() || !state.R_4764_Y(booleanproperty = Q_4220_D.n_1700_B(direction)).booleanValue()) continue;
            state2 = (K_4074_S)state2.n_1700_B(booleanproperty, true);
        }
        return state2;
    }

    private boolean h_1847_R(K_4074_S state) {
        return state.R_4764_Y(h_1847_R) != false || state.R_4764_Y(Q_4569_t) != false || state.R_4764_Y(M_182_A) != false || state.R_4764_Y(t_1786_h) != false;
    }

    private boolean n_1700_B(BlockGetter blockReader, c_1514_x pos) {
        int i = 4;
        Iterable<c_1514_x> iterable = c_1514_x.getAllInBoxMutable(pos.getX() - 4, pos.getY() - 1, pos.getZ() - 4, pos.getX() + 4, pos.getY() + 1, pos.getZ() + 4);
        int j = 5;
        for (c_1514_x blockpos : iterable) {
            if (!blockReader.getBlockState(blockpos).n_1700_B(this) || --j > 0) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockPlaceContext useContext) {
        K_4074_S blockstate = useContext.getWorld().getBlockState(useContext.getPos());
        if (blockstate.n_1700_B(this)) {
            return this.P_4830_p(blockstate) < multiplayerClientSuggestionProvider.size();
        }
        return super.n_1700_B(state, useContext);
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        K_4074_S blockstate = context.getWorld().getBlockState(context.getPos());
        boolean flag = blockstate.n_1700_B(this);
        K_4074_S blockstate1 = flag ? blockstate : this.multiplayerClientSuggestionProvider();
        for (b_257_Y direction : context.G_564_y()) {
            boolean flag1;
            if (direction == b_257_Y.n_1700_B) continue;
            U_1266_O booleanproperty = Q_4220_D.n_1700_B(direction);
            boolean bl = flag1 = flag && blockstate.R_4764_Y(booleanproperty) != false;
            if (flag1 || !this.J_1907_R(context.getWorld(), context.getPos(), direction)) continue;
            return (K_4074_S)blockstate1.n_1700_B(booleanproperty, true);
        }
        return flag ? blockstate1 : null;
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(P_4830_p, h_1847_R, Q_4569_t, M_182_A, t_1786_h);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        switch (rot) {
            case R_4764_Y: {
                return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)state.n_1700_B(h_1847_R, state.R_4764_Y(M_182_A))).n_1700_B(Q_4569_t, state.R_4764_Y(t_1786_h))).n_1700_B(M_182_A, state.R_4764_Y(h_1847_R))).n_1700_B(t_1786_h, state.R_4764_Y(Q_4569_t));
            }
            case G_564_y: {
                return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)state.n_1700_B(h_1847_R, state.R_4764_Y(Q_4569_t))).n_1700_B(Q_4569_t, state.R_4764_Y(M_182_A))).n_1700_B(M_182_A, state.R_4764_Y(t_1786_h))).n_1700_B(t_1786_h, state.R_4764_Y(h_1847_R));
            }
            case J_1907_R: {
                return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)state.n_1700_B(h_1847_R, state.R_4764_Y(t_1786_h))).n_1700_B(Q_4569_t, state.R_4764_Y(h_1847_R))).n_1700_B(M_182_A, state.R_4764_Y(Q_4569_t))).n_1700_B(t_1786_h, state.R_4764_Y(M_182_A));
            }
        }
        return state;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        switch (mirrorIn) {
            case J_1907_R: {
                return (K_4074_S)((K_4074_S)state.n_1700_B(h_1847_R, state.R_4764_Y(M_182_A))).n_1700_B(M_182_A, state.R_4764_Y(h_1847_R));
            }
            case R_4764_Y: {
                return (K_4074_S)((K_4074_S)state.n_1700_B(Q_4569_t, state.R_4764_Y(t_1786_h))).n_1700_B(t_1786_h, state.R_4764_Y(Q_4569_t));
            }
        }
        return super.n_1700_B(state, mirrorIn);
    }

    public static U_1266_O n_1700_B(b_257_Y side) {
        return multiplayerClientSuggestionProvider.get(side);
    }
}


