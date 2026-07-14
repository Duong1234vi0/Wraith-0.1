/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Optional;
import java.util.Random;
import lightning.product.GrowingPlantBlock;
import lightning.product.BlockGetter;
import lightning.product.BonemealableBlock;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.Z_1993_T;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.GrowingPlantHeadBlock;
import lightning.product.e_3591_l;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;

public abstract class h_4327_W
extends GrowingPlantBlock
implements BonemealableBlock {
    protected h_4327_W(q_4293_E.P_1922_E properties, b_257_Y growthDirection, s_1395_c shape, boolean waterloggable) {
        super(properties, growthDirection, shape, waterloggable);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        T_2915_h block;
        if (facing == this.P_4830_p.u_1723_Y() && !stateIn.n_1700_B(worldIn, currentPos)) {
            worldIn.u_2550_I().n_1700_B(currentPos, this, 1);
        }
        GrowingPlantHeadBlock abstracttopplantblock = this.t_148_a();
        if (facing == this.P_4830_p && (block = facingState.J_1907_R()) != this && block != abstracttopplantblock) {
            return abstracttopplantblock.n_1700_B(worldIn);
        }
        if (this.h_1847_R) {
            worldIn.M_588_G().n_1700_B(currentPos, Fluids.R_4764_Y, Fluids.R_4764_Y.n_1700_B(worldIn));
        }
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public Z_1993_T n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state) {
        return new Z_1993_T(this.t_148_a());
    }

    @Override
    public boolean n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state, boolean isClient) {
        Optional<c_1514_x> optional = this.J_1907_R(worldIn, pos, state);
        return optional.isPresent() && this.t_148_a().w_1484_f(worldIn.getBlockState(optional.get().offset(this.P_4830_p)));
    }

    @Override
    public boolean n_1700_B(b_4507_u worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        return true;
    }

    @Override
    public void n_1700_B(e_3591_l worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        Optional<c_1514_x> optional = this.J_1907_R(worldIn, pos, state);
        if (optional.isPresent()) {
            K_4074_S blockstate = worldIn.getBlockState(optional.get());
            ((GrowingPlantHeadBlock)blockstate.J_1907_R()).n_1700_B(worldIn, rand, optional.get(), blockstate);
        }
    }

    private Optional<c_1514_x> J_1907_R(BlockGetter reader, c_1514_x pos, K_4074_S state) {
        T_2915_h block;
        c_1514_x blockpos = pos;
        while ((block = reader.getBlockState(blockpos = blockpos.offset(this.P_4830_p)).J_1907_R()) == state.J_1907_R()) {
        }
        return block == this.t_148_a() ? Optional.of(blockpos) : Optional.empty();
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockPlaceContext useContext) {
        boolean flag = super.n_1700_B(state, useContext);
        return flag && useContext.getItem().J_1907_R() == this.t_148_a().u_1723_Y() ? false : flag;
    }

    @Override
    protected T_2915_h J_1907_R() {
        return this;
    }
}


