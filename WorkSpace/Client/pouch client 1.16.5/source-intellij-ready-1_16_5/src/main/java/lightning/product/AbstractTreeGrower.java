/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.TreeConfiguration;
import lightning.product.a_3742_W;
import lightning.product.ConfiguredFeature;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.BlockTags;
import lightning.product.LevelAccessor;
import lightning.product.z_1753_f;

public abstract class AbstractTreeGrower {
    @Nullable
    protected abstract ConfiguredFeature<TreeConfiguration, ?> n_1700_B(Random var1, boolean var2);

    public boolean n_1700_B(e_3591_l world, z_1753_f chunkGenerator, c_1514_x pos, K_4074_S state, Random rand) {
        ConfiguredFeature<TreeConfiguration, ?> configuredfeature = this.n_1700_B(rand, this.n_1700_B(world, pos));
        if (configuredfeature == null) {
            return false;
        }
        world.n_1700_B(pos, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 4);
        ((TreeConfiguration)configuredfeature.u_1723_Y).n_1700_B();
        if (configuredfeature.n_1700_B(world, chunkGenerator, rand, pos)) {
            return true;
        }
        world.n_1700_B(pos, state, 4);
        return false;
    }

    private boolean n_1700_B(LevelAccessor world, c_1514_x pos) {
        for (c_1514_x blockpos : c_1514_x.n_1700_B.getAllInBoxMutable(pos.down().north(2).west(2), pos.up().south(2).east(2))) {
            if (!world.getBlockState(blockpos).n_1700_B(BlockTags.q_4610_l)) continue;
            return true;
        }
        return false;
    }
}


