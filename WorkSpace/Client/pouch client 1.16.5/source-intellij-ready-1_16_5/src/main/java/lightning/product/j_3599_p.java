/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.A_4919_q;
import lightning.product.BlockStateProperties;
import lightning.product.F_2904_S;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.Fluids;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.O_2369_F;
import lightning.product.ChestMenu;
import lightning.product.DoubleBlockCombiner;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.U_1266_O;
import lightning.product.W_2163_m;
import lightning.product.PlayerEnderChestContainer;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.SimpleMenuProvider;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.SimpleWaterloggedBlock;
import lightning.product.DirectionProperty;
import lightning.product.i_2154_H;
import lightning.product.AbstractChestBlock;
import lightning.product.m_3054_I;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.BlockEntityType;
import lightning.product.s_1395_c;
import lightning.product.s_3081_t;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.t_693_s;
import lightning.product.x_1688_C;
import lightning.product.x_282_a;

public class j_3599_p
extends AbstractChestBlock<s_3081_t>
implements SimpleWaterloggedBlock {
    public static final DirectionProperty h_1847_R = HorizontalDirectionalBlock.w_612_n;
    public static final U_1266_O Q_4569_t = BlockStateProperties.A_4115_X;
    protected static final s_1395_c M_182_A = T_2915_h.n_1700_B(1.0, 0.0, 1.0, 15.0, 14.0, 15.0);
    private static final x_282_a t_1786_h = new F_2904_S("container.enderchest");

    protected j_3599_p(q_4293_E.P_1922_E builder) {
        super(builder, () -> BlockEntityType.G_564_y);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(h_1847_R, b_257_Y.R_4764_Y)).n_1700_B(Q_4569_t, false));
    }

    @Override
    public DoubleBlockCombiner.J_1907_R<? extends t_693_s> n_1700_B(K_4074_S state, b_4507_u world, c_1514_x pos, boolean override) {
        return DoubleBlockCombiner.n_1700_B::J_1907_R;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return M_182_A;
    }

    @Override
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.J_1907_R;
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return (K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(h_1847_R, context.getPlacementHorizontalFacing().u_1723_Y())).n_1700_B(Q_4569_t, fluidstate.n_1700_B() == Fluids.R_4764_Y);
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        PlayerEnderChestContainer enderchestinventory = player.c_2086_l();
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        if (enderchestinventory != null && tileentity instanceof s_3081_t) {
            c_1514_x blockpos = pos.up();
            if (worldIn.getBlockState(blockpos).v_4262_N(worldIn, blockpos)) {
                return m_3054_I.n_1700_B(worldIn.Y_259_p);
            }
            if (worldIn.Y_259_p) {
                return m_3054_I.n_1700_B;
            }
            s_3081_t enderchesttileentity = (s_3081_t)tileentity;
            enderchestinventory.n_1700_B(enderchesttileentity);
            player.n_1700_B(new SimpleMenuProvider((id, inventory, playerIn) -> ChestMenu.n_1700_B(id, inventory, enderchestinventory), t_1786_h));
            player.J_1907_R(Stats.i_1637_u);
            A_4919_q.n_1700_B(player, true);
            return m_3054_I.J_1907_R;
        }
        return m_3054_I.n_1700_B(worldIn.Y_259_p);
    }

    @Override
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new s_3081_t();
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        for (int i = 0; i < 3; ++i) {
            int j = rand.nextInt(2) * 2 - 1;
            int k = rand.nextInt(2) * 2 - 1;
            double d0 = (double)pos.getX() + 0.5 + 0.25 * (double)j;
            double d1 = (float)pos.getY() + rand.nextFloat();
            double d2 = (double)pos.getZ() + 0.5 + 0.25 * (double)k;
            double d3 = rand.nextFloat() * (float)j;
            double d4 = ((double)rand.nextFloat() - 0.5) * 0.125;
            double d5 = rand.nextFloat() * (float)k;
            worldIn.n_1700_B(ParticleTypes.g_221_o, d0, d1, d2, d3, d4, d5);
        }
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
        builder.n_1700_B(h_1847_R, Q_4569_t);
    }

    @Override
    public FluidState P_1922_E(K_4074_S state) {
        return state.R_4764_Y(Q_4569_t) != false ? Fluids.R_4764_Y.n_1700_B(false) : super.P_1922_E(state);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (stateIn.R_4764_Y(Q_4569_t).booleanValue()) {
            worldIn.M_588_G().n_1700_B(currentPos, Fluids.R_4764_Y, Fluids.R_4764_Y.n_1700_B(worldIn));
        }
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


