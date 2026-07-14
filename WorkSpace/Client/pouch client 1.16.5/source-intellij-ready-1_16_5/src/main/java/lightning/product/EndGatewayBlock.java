/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.A_4605_O;
import lightning.product.BlockGetter;
import lightning.product.BaseEntityBlock;
import lightning.product.K_4074_S;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.i_2154_H;
import lightning.product.q_4293_E;
import lightning.product.Fluid;
import lightning.product.ParticleTypes;

public class EndGatewayBlock
extends BaseEntityBlock {
    protected EndGatewayBlock(q_4293_E.P_1922_E builder) {
        super(builder);
    }

    @Override
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new A_4605_O();
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof A_4605_O) {
            int i = ((A_4605_O)tileentity).u_2550_I();
            for (int j = 0; j < i; ++j) {
                double d0 = (double)pos.getX() + rand.nextDouble();
                double d1 = (double)pos.getY() + rand.nextDouble();
                double d2 = (double)pos.getZ() + rand.nextDouble();
                double d3 = (rand.nextDouble() - 0.5) * 0.5;
                double d4 = (rand.nextDouble() - 0.5) * 0.5;
                double d5 = (rand.nextDouble() - 0.5) * 0.5;
                int k = rand.nextInt(2) * 2 - 1;
                if (rand.nextBoolean()) {
                    d2 = (double)pos.getZ() + 0.5 + 0.25 * (double)k;
                    d5 = rand.nextFloat() * 2.0f * (float)k;
                } else {
                    d0 = (double)pos.getX() + 0.5 + 0.25 * (double)k;
                    d3 = rand.nextFloat() * 2.0f * (float)k;
                }
                worldIn.n_1700_B(ParticleTypes.g_221_o, d0, d1, d2, d3, d4, d5);
            }
        }
    }

    @Override
    public Z_1993_T n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state) {
        return Z_1993_T.J_1907_R;
    }

    @Override
    public boolean n_1700_B(K_4074_S state, Fluid fluid) {
        return false;
    }
}


