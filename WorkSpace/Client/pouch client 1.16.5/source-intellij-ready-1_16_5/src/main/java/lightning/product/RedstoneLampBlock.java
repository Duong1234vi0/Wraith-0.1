/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.RedstoneTorchBlock;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.Y_1835_y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.q_4293_E;
import lightning.product.v_3760_Q;

public class RedstoneLampBlock
extends T_2915_h {
    public static final U_1266_O P_4830_p = RedstoneTorchBlock.P_4830_p;

    public RedstoneLampBlock(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, false));
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, context.getWorld().Y_601_j(context.getPos()));
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, T_2915_h blockIn, c_1514_x fromPos, boolean isMoving) {
        boolean flag;
        if (!worldIn.Y_259_p && (flag = state.R_4764_Y(P_4830_p).booleanValue()) != worldIn.Y_601_j(pos)) {
            if (flag) {
                worldIn.u_2550_I().n_1700_B(pos, this, 4);
            } else {
                worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p), 2);
            }
        }
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        if (state.R_4764_Y(P_4830_p).booleanValue() && !worldIn.Y_601_j(pos)) {
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p), 2);
        }
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }
}


