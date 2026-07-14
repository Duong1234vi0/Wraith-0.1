/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.D_38_f;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.SoundEvents;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.q_4293_E;
import lightning.product.ParticleTypes;

public class n_290_G
extends T_2915_h {
    protected n_290_G(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        if (worldIn.G_624_v().G_564_y()) {
            worldIn.n_1700_B(pos, a_3742_W.j_276_v.multiplayerClientSuggestionProvider(), 3);
            worldIn.R_4764_Y(2009, pos, 0);
            worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.V_1665_T, D_38_f.P_1922_E, 1.0f, (1.0f + worldIn.e_4240_b().nextFloat() * 0.2f) * 0.7f);
        }
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        b_257_Y direction = b_257_Y.n_1700_B(rand);
        if (direction != b_257_Y.J_1907_R) {
            c_1514_x blockpos = pos.offset(direction);
            K_4074_S blockstate = worldIn.getBlockState(blockpos);
            if (!stateIn.M_588_G() || !blockstate.G_564_y((BlockGetter)worldIn, blockpos, direction.u_1723_Y())) {
                double d0 = pos.getX();
                double d1 = pos.getY();
                double d2 = pos.getZ();
                if (direction == b_257_Y.n_1700_B) {
                    d1 -= 0.05;
                    d0 += rand.nextDouble();
                    d2 += rand.nextDouble();
                } else {
                    d1 += rand.nextDouble() * 0.8;
                    if (direction.h_1847_R() == b_257_Y.n_1700_B.n_1700_B) {
                        d2 += rand.nextDouble();
                        d0 = direction == b_257_Y.u_1723_Y ? (d0 += 1.0) : (d0 += 0.05);
                    } else {
                        d0 += rand.nextDouble();
                        d2 = direction == b_257_Y.G_564_y ? (d2 += 1.0) : (d2 += 0.05);
                    }
                }
                worldIn.n_1700_B(ParticleTypes.P_4830_p, d0, d1, d2, 0.0, 0.0, 0.0);
            }
        }
    }
}


