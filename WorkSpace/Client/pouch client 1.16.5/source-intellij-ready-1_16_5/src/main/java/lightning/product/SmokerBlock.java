/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.D_38_f;
import lightning.product.BlockGetter;
import lightning.product.SmokerBlockEntity;
import lightning.product.K_4074_S;
import lightning.product.AbstractFurnaceBlock;
import lightning.product.Stats;
import lightning.product.SoundEvents;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.i_2154_H;
import lightning.product.q_4293_E;
import lightning.product.ParticleTypes;
import lightning.product.t_3286_u;

public class SmokerBlock
extends AbstractFurnaceBlock {
    protected SmokerBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new SmokerBlockEntity();
    }

    @Override
    protected void n_1700_B(b_4507_u worldIn, c_1514_x pos, a_3913_L player) {
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof SmokerBlockEntity) {
            player.n_1700_B((t_3286_u)((Object)tileentity));
            player.J_1907_R(Stats.Ops);
        }
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        if (stateIn.R_4764_Y(h_1847_R).booleanValue()) {
            double d0 = (double)pos.getX() + 0.5;
            double d1 = pos.getY();
            double d2 = (double)pos.getZ() + 0.5;
            if (rand.nextDouble() < 0.1) {
                worldIn.n_1700_B(d0, d1, d2, SoundEvents.K_4074_S, D_38_f.P_1922_E, 1.0f, 1.0f, false);
            }
            worldIn.n_1700_B(ParticleTypes.B_1668_F, d0, d1 + 1.1, d2, 0.0, 0.0, 0.0);
        }
    }
}


