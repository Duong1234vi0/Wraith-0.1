/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.R_3217_O;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.FenceGateBlock;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.m_3054_I;
import lightning.product.BlockTags;
import lightning.product.q_4293_E;
import lightning.product.Items;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.v_3760_Q;
import lightning.product.v_4620_e;
import lightning.product.x_1688_C;

public class FenceBlock
extends v_4620_e {
    private final s_1395_c[] Y_259_p;

    public FenceBlock(q_4293_E.P_1922_E properties) {
        super(2.0f, 2.0f, 16.0f, 16.0f, 24.0f, properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, false)).n_1700_B(h_1847_R, false)).n_1700_B(Q_4569_t, false)).n_1700_B(M_182_A, false)).n_1700_B(t_1786_h, false));
        this.Y_259_p = this.n_1700_B(2.0f, 1.0f, 16.0f, 6.0f, 15.0f);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        return this.Y_259_p[this.w_1484_f(state)];
    }

    @Override
    public s_1395_c R_4764_Y(K_4074_S state, BlockGetter reader, c_1514_x pos, CollisionContext context) {
        return this.n_1700_B(state, reader, pos, context);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }

    public boolean n_1700_B(K_4074_S state, boolean isSideSolid, b_257_Y direction) {
        T_2915_h block = state.J_1907_R();
        boolean flag = this.n_1700_B(block);
        boolean flag1 = block instanceof FenceGateBlock && FenceGateBlock.n_1700_B(state, direction);
        return !FenceBlock.R_4764_Y(block) && isSideSolid || flag || flag1;
    }

    private boolean n_1700_B(T_2915_h block) {
        return block.n_1700_B(BlockTags.G_624_v) && block.n_1700_B(BlockTags.u_2550_I) == this.multiplayerClientSuggestionProvider().n_1700_B(BlockTags.u_2550_I);
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (worldIn.Y_259_p) {
            Z_1993_T itemstack = player.R_4764_Y(handIn);
            return itemstack.J_1907_R() == Items.c_1788_D ? m_3054_I.n_1700_B : m_3054_I.R_4764_Y;
        }
        return R_3217_O.n_1700_B(player, worldIn, pos);
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        b_4507_u iblockreader = context.getWorld();
        c_1514_x blockpos = context.getPos();
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        c_1514_x blockpos1 = blockpos.north();
        c_1514_x blockpos2 = blockpos.east();
        c_1514_x blockpos3 = blockpos.south();
        c_1514_x blockpos4 = blockpos.west();
        K_4074_S blockstate = iblockreader.getBlockState(blockpos1);
        K_4074_S blockstate1 = iblockreader.getBlockState(blockpos2);
        K_4074_S blockstate2 = iblockreader.getBlockState(blockpos3);
        K_4074_S blockstate3 = iblockreader.getBlockState(blockpos4);
        return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)super.n_1700_B(context).n_1700_B(P_4830_p, this.n_1700_B(blockstate, blockstate.G_564_y((BlockGetter)iblockreader, blockpos1, b_257_Y.G_564_y), b_257_Y.G_564_y))).n_1700_B(h_1847_R, this.n_1700_B(blockstate1, blockstate1.G_564_y((BlockGetter)iblockreader, blockpos2, b_257_Y.P_1922_E), b_257_Y.P_1922_E))).n_1700_B(Q_4569_t, this.n_1700_B(blockstate2, blockstate2.G_564_y((BlockGetter)iblockreader, blockpos3, b_257_Y.R_4764_Y), b_257_Y.R_4764_Y))).n_1700_B(M_182_A, this.n_1700_B(blockstate3, blockstate3.G_564_y((BlockGetter)iblockreader, blockpos4, b_257_Y.u_1723_Y), b_257_Y.u_1723_Y))).n_1700_B(t_1786_h, fluidstate.n_1700_B() == Fluids.R_4764_Y);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (stateIn.R_4764_Y(t_1786_h).booleanValue()) {
            worldIn.M_588_G().n_1700_B(currentPos, Fluids.R_4764_Y, Fluids.R_4764_Y.n_1700_B(worldIn));
        }
        return facing.h_1847_R().P_1922_E() == b_257_Y.R_4764_Y.n_1700_B ? (K_4074_S)stateIn.n_1700_B((v_3760_Q)multiplayerClientSuggestionProvider.get(facing), this.n_1700_B(facingState, facingState.G_564_y((BlockGetter)worldIn, facingPos, facing.u_1723_Y()), facing.u_1723_Y())) : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(P_4830_p, h_1847_R, M_182_A, Q_4569_t, t_1786_h);
    }
}


