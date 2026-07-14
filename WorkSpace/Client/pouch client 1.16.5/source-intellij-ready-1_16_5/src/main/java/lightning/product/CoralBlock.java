/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.FluidTags;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_3591_l;
import lightning.product.q_4293_E;
import lightning.product.LevelAccessor;

public class CoralBlock
extends T_2915_h {
    private final T_2915_h P_4830_p;

    public CoralBlock(T_2915_h deadBlock, q_4293_E.P_1922_E properties) {
        super(properties);
        this.P_4830_p = deadBlock;
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        if (!this.n_1700_B((BlockGetter)worldIn, pos)) {
            worldIn.n_1700_B(pos, this.P_4830_p.multiplayerClientSuggestionProvider(), 2);
        }
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (!this.n_1700_B((BlockGetter)worldIn, currentPos)) {
            worldIn.u_2550_I().n_1700_B(currentPos, this, 60 + worldIn.e_4240_b().nextInt(40));
        }
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    protected boolean n_1700_B(BlockGetter reader, c_1514_x pos) {
        for (b_257_Y direction : b_257_Y.values()) {
            FluidState fluidstate = reader.getFluidState(pos.offset(direction));
            if (!fluidstate.n_1700_B(FluidTags.J_1907_R)) continue;
            return true;
        }
        return false;
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        if (!this.n_1700_B((BlockGetter)context.getWorld(), context.getPos())) {
            context.getWorld().u_2550_I().n_1700_B(context.getPos(), this, 60 + context.getWorld().e_4240_b().nextInt(40));
        }
        return this.multiplayerClientSuggestionProvider();
    }
}


