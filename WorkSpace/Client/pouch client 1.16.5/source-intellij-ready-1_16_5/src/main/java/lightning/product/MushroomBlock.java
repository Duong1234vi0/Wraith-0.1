/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockGetter;
import lightning.product.BonemealableBlock;
import lightning.product.BushBlock;
import lightning.product.K_4074_S;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.Features;
import lightning.product.CollisionContext;
import lightning.product.a_3742_W;
import lightning.product.ConfiguredFeature;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.BlockTags;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;

public class MushroomBlock
extends BushBlock
implements BonemealableBlock {
    protected static final s_1395_c P_4830_p = T_2915_h.n_1700_B(5.0, 0.0, 5.0, 11.0, 6.0, 11.0);

    public MushroomBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return P_4830_p;
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        if (random.nextInt(25) == 0) {
            int i = 5;
            int j = 4;
            for (c_1514_x blockpos : c_1514_x.getAllInBoxMutable(pos.add(-4, -1, -4), pos.add(4, 1, 4))) {
                if (!worldIn.getBlockState(blockpos).n_1700_B(this) || --i > 0) continue;
                return;
            }
            c_1514_x blockpos1 = pos.add(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);
            for (int k = 0; k < 4; ++k) {
                if (worldIn.u_1723_Y(blockpos1) && state.n_1700_B((T_1316_M)worldIn, blockpos1)) {
                    pos = blockpos1;
                }
                blockpos1 = pos.add(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);
            }
            if (worldIn.u_1723_Y(blockpos1) && state.n_1700_B((T_1316_M)worldIn, blockpos1)) {
                worldIn.n_1700_B(blockpos1, state, 2);
            }
        }
    }

    @Override
    protected boolean v_4262_N(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        return state.t_148_a(worldIn, pos);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        c_1514_x blockpos = pos.down();
        K_4074_S blockstate = worldIn.getBlockState(blockpos);
        if (blockstate.n_1700_B(BlockTags.j_1564_a)) {
            return true;
        }
        return worldIn.n_1700_B(pos, 0) < 13 && this.v_4262_N(blockstate, worldIn, blockpos);
    }

    public boolean n_1700_B(e_3591_l world, c_1514_x pos, K_4074_S state, Random rand) {
        ConfiguredFeature<?, ?> configuredfeature;
        world.n_1700_B(pos, false);
        if (this == a_3742_W.JsonUtils) {
            configuredfeature = Features.H_1883_T;
        } else {
            if (this != a_3742_W.RealmsPersistence) {
                world.n_1700_B(pos, state, 3);
                return false;
            }
            configuredfeature = Features.d_4007_L;
        }
        if (configuredfeature.n_1700_B(world, world.Y_259_p().t_148_a(), rand, pos)) {
            return true;
        }
        world.n_1700_B(pos, state, 3);
        return false;
    }

    @Override
    public boolean n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state, boolean isClient) {
        return true;
    }

    @Override
    public boolean n_1700_B(b_4507_u worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        return (double)rand.nextFloat() < 0.4;
    }

    @Override
    public void n_1700_B(e_3591_l worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        this.n_1700_B(worldIn, pos, state, rand);
    }
}


