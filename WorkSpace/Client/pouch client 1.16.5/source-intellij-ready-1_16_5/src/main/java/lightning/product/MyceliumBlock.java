/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.SpreadingSnowyDirtBlock;
import lightning.product.K_4074_S;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.q_4293_E;
import lightning.product.ParticleTypes;

public class MyceliumBlock
extends SpreadingSnowyDirtBlock {
    public MyceliumBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        super.n_1700_B(stateIn, worldIn, pos, rand);
        if (rand.nextInt(10) == 0) {
            worldIn.n_1700_B(ParticleTypes.T_2506_i, (double)pos.getX() + rand.nextDouble(), (double)pos.getY() + 1.1, (double)pos.getZ() + rand.nextDouble(), 0.0, 0.0, 0.0);
        }
    }
}


