/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.AbstractFlowerFeature;
import lightning.product.K_4074_S;
import lightning.product.c_1514_x;
import lightning.product.RandomPatchConfiguration;
import lightning.product.FeatureConfiguration;
import lightning.product.LevelAccessor;

public class DefaultFlowerFeature
extends AbstractFlowerFeature<RandomPatchConfiguration> {
    public DefaultFlowerFeature(Codec<RandomPatchConfiguration> p_i231945_1_) {
        super(p_i231945_1_);
    }

    @Override
    public boolean n_1700_B(LevelAccessor world, c_1514_x pos, RandomPatchConfiguration config) {
        return !config.P_1922_E.contains(world.getBlockState(pos));
    }

    @Override
    public int n_1700_B(RandomPatchConfiguration config) {
        return config.u_1723_Y;
    }

    public c_1514_x n_1700_B(Random rand, c_1514_x pos, RandomPatchConfiguration config) {
        return pos.add(rand.nextInt(config.v_4262_N) - rand.nextInt(config.v_4262_N), rand.nextInt(config.w_1484_f) - rand.nextInt(config.w_1484_f), rand.nextInt(config.t_148_a) - rand.nextInt(config.t_148_a));
    }

    public K_4074_S J_1907_R(Random rand, c_1514_x pos, RandomPatchConfiguration confgi) {
        return confgi.J_1907_R.n_1700_B(rand, pos);
    }

    @Override
    public /* synthetic */ K_4074_S n_1700_B(Random random, c_1514_x c_1514_x2, FeatureConfiguration s_3889_g2) {
        return this.J_1907_R(random, c_1514_x2, (RandomPatchConfiguration)s_3889_g2);
    }

    @Override
    public /* synthetic */ c_1514_x J_1907_R(Random random, c_1514_x c_1514_x2, FeatureConfiguration s_3889_g2) {
        return this.n_1700_B(random, c_1514_x2, (RandomPatchConfiguration)s_3889_g2);
    }
}


