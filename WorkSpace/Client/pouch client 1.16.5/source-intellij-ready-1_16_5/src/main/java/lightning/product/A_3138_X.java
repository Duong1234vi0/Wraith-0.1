/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.D_38_f;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.AbstractFurnaceBlock;
import lightning.product.Stats;
import lightning.product.SoundEvents;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.i_2154_H;
import lightning.product.q_4293_E;
import lightning.product.ParticleTypes;
import lightning.product.t_3286_u;
import lightning.product.FurnaceBlockEntity;

public class A_3138_X
extends AbstractFurnaceBlock {
    protected A_3138_X(q_4293_E.P_1922_E builder) {
        super(builder);
    }

    @Override
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new FurnaceBlockEntity();
    }

    @Override
    protected void n_1700_B(b_4507_u worldIn, c_1514_x pos, a_3913_L player) {
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof FurnaceBlockEntity) {
            player.n_1700_B((t_3286_u)((Object)tileentity));
            player.J_1907_R(Stats.RealmsClientConfig);
        }
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        if (stateIn.R_4764_Y(h_1847_R).booleanValue()) {
            double d0 = (double)pos.getX() + 0.5;
            double d1 = pos.getY();
            double d2 = (double)pos.getZ() + 0.5;
            if (rand.nextDouble() < 0.1) {
                worldIn.n_1700_B(d0, d1, d2, SoundEvents.z_3000_g, D_38_f.P_1922_E, 1.0f, 1.0f, false);
            }
            b_257_Y direction = stateIn.R_4764_Y(P_4830_p);
            b_257_Y.n_1700_B direction$axis = direction.h_1847_R();
            double d3 = 0.52;
            double d4 = rand.nextDouble() * 0.6 - 0.3;
            double d5 = direction$axis == b_257_Y.n_1700_B.n_1700_B ? (double)direction.t_148_a() * 0.52 : d4;
            double d6 = rand.nextDouble() * 6.0 / 16.0;
            double d7 = direction$axis == b_257_Y.n_1700_B.R_4764_Y ? (double)direction.u_2550_I() * 0.52 : d4;
            worldIn.n_1700_B(ParticleTypes.B_1668_F, d0 + d5, d1 + d6, d2 + d7, 0.0, 0.0, 0.0);
            worldIn.n_1700_B(ParticleTypes.c_3005_b, d0 + d5, d1 + d6, d2 + d7, 0.0, 0.0, 0.0);
        }
    }
}


