/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.q_4293_E;
import lightning.product.ParticleTypes;

public class CryingObsidianBlock
extends T_2915_h {
    public CryingObsidianBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        b_257_Y direction;
        if (rand.nextInt(5) == 0 && (direction = b_257_Y.n_1700_B(rand)) != b_257_Y.J_1907_R) {
            c_1514_x blockpos = pos.offset(direction);
            K_4074_S blockstate = worldIn.getBlockState(blockpos);
            if (!stateIn.M_588_G() || !blockstate.G_564_y((BlockGetter)worldIn, blockpos, direction.u_1723_Y())) {
                double d0 = direction.t_148_a() == 0 ? rand.nextDouble() : 0.5 + (double)direction.t_148_a() * 0.6;
                double d1 = direction.s_956_w() == 0 ? rand.nextDouble() : 0.5 + (double)direction.s_956_w() * 0.6;
                double d2 = direction.u_2550_I() == 0 ? rand.nextDouble() : 0.5 + (double)direction.u_2550_I() * 0.6;
                worldIn.n_1700_B(ParticleTypes.e_1992_r, (double)pos.getX() + d0, (double)pos.getY() + d1, (double)pos.getZ() + d2, 0.0, 0.0, 0.0);
            }
        }
    }
}


