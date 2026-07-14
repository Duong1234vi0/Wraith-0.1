/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.floats.Float2FloatFunction
 *  javax.annotation.Nullable
 */
package lightning.product;

import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import lightning.product.A_4919_q;
import lightning.product.BlockStateProperties;
import lightning.product.F_2904_S;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.I_4817_s;
import lightning.product.Fluids;
import lightning.product.K_3065_y;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.K_550_M;
import lightning.product.Container;
import lightning.product.O_2369_F;
import lightning.product.ChestMenu;
import lightning.product.DoubleBlockCombiner;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.U_1266_O;
import lightning.product.W_2163_m;
import lightning.product.W_3491_f;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.LidBlockEntity;
import lightning.product.a_2900_S;
import lightning.product.a_3913_L;
import lightning.product.a_4391_G;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_563_h;
import lightning.product.SimpleWaterloggedBlock;
import lightning.product.g_2336_b;
import lightning.product.DirectionProperty;
import lightning.product.i_2154_H;
import lightning.product.AbstractChestBlock;
import lightning.product.m_3054_I;
import lightning.product.o_98_P;
import lightning.product.p_1429_o;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.BlockEntityType;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.t_3286_u;
import lightning.product.t_3546_P;
import lightning.product.t_693_s;
import lightning.product.x_1688_C;
import lightning.product.x_282_a;

public class v_3445_Z
extends AbstractChestBlock<t_693_s>
implements SimpleWaterloggedBlock {
    public static final DirectionProperty h_1847_R = HorizontalDirectionalBlock.w_612_n;
    public static final e_563_h<p_1429_o> Q_4569_t = BlockStateProperties.RealmsWorldOptions;
    public static final U_1266_O M_182_A = BlockStateProperties.A_4115_X;
    protected static final s_1395_c t_1786_h = T_2915_h.n_1700_B(1.0, 0.0, 0.0, 15.0, 14.0, 15.0);
    protected static final s_1395_c multiplayerClientSuggestionProvider = T_2915_h.n_1700_B(1.0, 0.0, 1.0, 15.0, 14.0, 16.0);
    protected static final s_1395_c w_1457_N = T_2915_h.n_1700_B(0.0, 0.0, 1.0, 15.0, 14.0, 15.0);
    protected static final s_1395_c Y_601_j = T_2915_h.n_1700_B(1.0, 0.0, 1.0, 16.0, 14.0, 15.0);
    protected static final s_1395_c Y_259_p = T_2915_h.n_1700_B(1.0, 0.0, 1.0, 15.0, 14.0, 15.0);
    private static final DoubleBlockCombiner.n_1700_B<t_693_s, Optional<Container>> Q_2552_b = new DoubleBlockCombiner.n_1700_B<t_693_s, Optional<Container>>(){

        @Override
        public Optional<Container> n_1700_B(t_693_s p_225539_1_, t_693_s p_225539_2_) {
            return Optional.of(new a_4391_G(p_225539_1_, p_225539_2_));
        }

        @Override
        public Optional<Container> n_1700_B(t_693_s p_225538_1_) {
            return Optional.of(p_225538_1_);
        }

        public Optional<Container> n_1700_B() {
            return Optional.empty();
        }

        @Override
        public /* synthetic */ Object J_1907_R() {
            return this.n_1700_B();
        }
    };
    private static final DoubleBlockCombiner.n_1700_B<t_693_s, Optional<t_3286_u>> C_2741_M = new DoubleBlockCombiner.n_1700_B<t_693_s, Optional<t_3286_u>>(){

        @Override
        public Optional<t_3286_u> n_1700_B(final t_693_s p_225539_1_, final t_693_s p_225539_2_) {
            final a_4391_G iinventory = new a_4391_G(p_225539_1_, p_225539_2_);
            return Optional.of(new t_3286_u(){

                @Override
                @Nullable
                public a_2900_S createMenu(int p_createMenu_1_, W_3491_f p_createMenu_2_, a_3913_L p_createMenu_3_) {
                    if (p_225539_1_.P_1922_E(p_createMenu_3_) && p_225539_2_.P_1922_E(p_createMenu_3_)) {
                        p_225539_1_.G_564_y(p_createMenu_2_.P_1922_E);
                        p_225539_2_.G_564_y(p_createMenu_2_.P_1922_E);
                        return ChestMenu.J_1907_R(p_createMenu_1_, p_createMenu_2_, iinventory);
                    }
                    return null;
                }

                @Override
                public x_282_a c_() {
                    if (p_225539_1_.t_3452_g()) {
                        return p_225539_1_.c_();
                    }
                    return p_225539_2_.t_3452_g() ? p_225539_2_.c_() : new F_2904_S("container.chestDouble");
                }
            });
        }

        @Override
        public Optional<t_3286_u> n_1700_B(t_693_s p_225538_1_) {
            return Optional.of(p_225538_1_);
        }

        public Optional<t_3286_u> n_1700_B() {
            return Optional.empty();
        }

        @Override
        public /* synthetic */ Object J_1907_R() {
            return this.n_1700_B();
        }
    };

    protected v_3445_Z(q_4293_E.P_1922_E builder, Supplier<BlockEntityType<? extends t_693_s>> tileEntityTypeIn) {
        super(builder, tileEntityTypeIn);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(h_1847_R, b_257_Y.R_4764_Y)).n_1700_B(Q_4569_t, p_1429_o.n_1700_B)).n_1700_B(M_182_A, false));
    }

    public static DoubleBlockCombiner.R_4764_Y w_1484_f(K_4074_S state) {
        p_1429_o chesttype = state.R_4764_Y(Q_4569_t);
        if (chesttype == p_1429_o.n_1700_B) {
            return DoubleBlockCombiner.R_4764_Y.n_1700_B;
        }
        return chesttype == p_1429_o.R_4764_Y ? DoubleBlockCombiner.R_4764_Y.J_1907_R : DoubleBlockCombiner.R_4764_Y.R_4764_Y;
    }

    @Override
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.J_1907_R;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (stateIn.R_4764_Y(M_182_A).booleanValue()) {
            worldIn.M_588_G().n_1700_B(currentPos, Fluids.R_4764_Y, Fluids.R_4764_Y.n_1700_B(worldIn));
        }
        if (facingState.n_1700_B(this) && facing.h_1847_R().G_564_y()) {
            p_1429_o chesttype = facingState.R_4764_Y(Q_4569_t);
            if (stateIn.R_4764_Y(Q_4569_t) == p_1429_o.n_1700_B && chesttype != p_1429_o.n_1700_B && stateIn.R_4764_Y(h_1847_R) == facingState.R_4764_Y(h_1847_R) && v_3445_Z.t_148_a(facingState) == facing.u_1723_Y()) {
                return (K_4074_S)stateIn.n_1700_B(Q_4569_t, chesttype.J_1907_R());
            }
        } else if (v_3445_Z.t_148_a(stateIn) == facing) {
            return (K_4074_S)stateIn.n_1700_B(Q_4569_t, p_1429_o.n_1700_B);
        }
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        if (state.R_4764_Y(Q_4569_t) == p_1429_o.n_1700_B) {
            return Y_259_p;
        }
        switch (v_3445_Z.t_148_a(state)) {
            default: {
                return t_1786_h;
            }
            case G_564_y: {
                return multiplayerClientSuggestionProvider;
            }
            case P_1922_E: {
                return w_1457_N;
            }
            case u_1723_Y: 
        }
        return Y_601_j;
    }

    public static b_257_Y t_148_a(K_4074_S state) {
        b_257_Y direction = state.R_4764_Y(h_1847_R);
        return state.R_4764_Y(Q_4569_t) == p_1429_o.J_1907_R ? direction.v_4262_N() : direction.w_1484_f();
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        b_257_Y direction2;
        p_1429_o chesttype = p_1429_o.n_1700_B;
        b_257_Y direction = context.getPlacementHorizontalFacing().u_1723_Y();
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        boolean flag = context.hasSecondaryUseForPlayer();
        b_257_Y direction1 = context.getFace();
        if (direction1.h_1847_R().G_564_y() && flag && (direction2 = this.n_1700_B(context, direction1.u_1723_Y())) != null && direction2.h_1847_R() != direction1.h_1847_R()) {
            direction = direction2;
            p_1429_o p_1429_o2 = chesttype = direction2.w_1484_f() == direction1.u_1723_Y() ? p_1429_o.R_4764_Y : p_1429_o.J_1907_R;
        }
        if (chesttype == p_1429_o.n_1700_B && !flag) {
            if (direction == this.n_1700_B(context, direction.v_4262_N())) {
                chesttype = p_1429_o.J_1907_R;
            } else if (direction == this.n_1700_B(context, direction.w_1484_f())) {
                chesttype = p_1429_o.R_4764_Y;
            }
        }
        return (K_4074_S)((K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(h_1847_R, direction)).n_1700_B(Q_4569_t, chesttype)).n_1700_B(M_182_A, fluidstate.n_1700_B() == Fluids.R_4764_Y);
    }

    @Override
    public FluidState P_1922_E(K_4074_S state) {
        return state.R_4764_Y(M_182_A) != false ? Fluids.R_4764_Y.n_1700_B(false) : super.P_1922_E(state);
    }

    @Nullable
    private b_257_Y n_1700_B(BlockPlaceContext context, b_257_Y direction) {
        K_4074_S blockstate = context.getWorld().getBlockState(context.getPos().offset(direction));
        return blockstate.n_1700_B(this) && blockstate.R_4764_Y(Q_4569_t) == p_1429_o.n_1700_B ? blockstate.R_4764_Y(h_1847_R) : null;
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, r_4811_B placer, Z_1993_T stack) {
        i_2154_H tileentity;
        if (stack.Y_601_j() && (tileentity = worldIn.getTileEntity(pos)) instanceof t_693_s) {
            ((t_693_s)tileentity).n_1700_B(stack.multiplayerClientSuggestionProvider());
        }
    }

    @Override
    public void J_1907_R(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S newState, boolean isMoving) {
        if (!state.n_1700_B(newState.J_1907_R())) {
            i_2154_H tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof Container) {
                K_3065_y.n_1700_B(worldIn, pos, (Container)((Object)tileentity));
                worldIn.R_4764_Y(pos, this);
            }
            super.J_1907_R(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (worldIn.Y_259_p) {
            return m_3054_I.n_1700_B;
        }
        t_3286_u inamedcontainerprovider = this.n_1700_B(state, worldIn, pos);
        if (inamedcontainerprovider != null) {
            player.n_1700_B(inamedcontainerprovider);
            player.n_1700_B(this.J_1907_R());
            A_4919_q.n_1700_B(player, true);
        }
        return m_3054_I.J_1907_R;
    }

    protected o_98_P<g_2336_b> J_1907_R() {
        return Stats.t_148_a.J_1907_R(Stats.j_276_v);
    }

    @Nullable
    public static Container n_1700_B(v_3445_Z chest, K_4074_S state, b_4507_u world, c_1514_x pos, boolean override) {
        return chest.n_1700_B(state, world, pos, override).apply(Q_2552_b).orElse(null);
    }

    @Override
    public DoubleBlockCombiner.J_1907_R<? extends t_693_s> n_1700_B(K_4074_S state, b_4507_u world, c_1514_x pos, boolean override) {
        BiPredicate<LevelAccessor, c_1514_x> bipredicate = override ? (worldIn, posIn) -> false : v_3445_Z::n_1700_B;
        return DoubleBlockCombiner.n_1700_B((BlockEntityType)this.P_4830_p.get(), v_3445_Z::w_1484_f, v_3445_Z::t_148_a, h_1847_R, state, world, pos, bipredicate);
    }

    @Override
    @Nullable
    public t_3286_u n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos) {
        return this.n_1700_B(state, worldIn, pos, false).apply(C_2741_M).orElse(null);
    }

    public static DoubleBlockCombiner.n_1700_B<t_693_s, Float2FloatFunction> n_1700_B(final LidBlockEntity lid) {
        return new DoubleBlockCombiner.n_1700_B<t_693_s, Float2FloatFunction>(){

            @Override
            public Float2FloatFunction n_1700_B(t_693_s p_225539_1_, t_693_s p_225539_2_) {
                return angle -> Math.max(p_225539_1_.n_1700_B(angle), p_225539_2_.n_1700_B(angle));
            }

            @Override
            public Float2FloatFunction n_1700_B(t_693_s p_225538_1_) {
                return p_225538_1_::n_1700_B;
            }

            public Float2FloatFunction n_1700_B() {
                return lid::n_1700_B;
            }

            @Override
            public /* synthetic */ Object J_1907_R() {
                return this.n_1700_B();
            }
        };
    }

    @Override
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new t_693_s();
    }

    public static boolean n_1700_B(LevelAccessor world, c_1514_x pos) {
        return v_3445_Z.n_1700_B((BlockGetter)world, pos) || v_3445_Z.J_1907_R(world, pos);
    }

    private static boolean n_1700_B(BlockGetter reader, c_1514_x worldIn) {
        c_1514_x blockpos = worldIn.up();
        return reader.getBlockState(blockpos).v_4262_N(reader, blockpos);
    }

    private static boolean J_1907_R(LevelAccessor world, c_1514_x pos) {
        List<K_550_M> list = world.n_1700_B(K_550_M.class, new I_4817_s(pos.getX(), pos.getY() + 1, pos.getZ(), pos.getX() + 1, pos.getY() + 2, pos.getZ() + 1));
        if (!list.isEmpty()) {
            for (K_550_M catentity : list) {
                if (!catentity.z_2372_L()) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean u_1723_Y(K_4074_S state) {
        return true;
    }

    @Override
    public int J_1907_R(K_4074_S blockState, b_4507_u worldIn, c_1514_x pos) {
        return a_2900_S.J_1907_R(v_3445_Z.n_1700_B(this, blockState, worldIn, pos, false));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        return (K_4074_S)state.n_1700_B(h_1847_R, rot.n_1700_B(state.R_4764_Y(h_1847_R)));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        return state.n_1700_B(mirrorIn.n_1700_B(state.R_4764_Y(h_1847_R)));
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(h_1847_R, Q_4569_t, M_182_A);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


