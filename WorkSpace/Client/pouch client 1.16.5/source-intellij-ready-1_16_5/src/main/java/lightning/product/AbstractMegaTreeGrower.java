/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.TreeConfiguration;
import lightning.product.T_2915_h;
import lightning.product.AbstractTreeGrower;
import lightning.product.a_3742_W;
import lightning.product.ConfiguredFeature;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.z_1753_f;

public abstract class AbstractMegaTreeGrower
extends AbstractTreeGrower {
    @Override
    public boolean n_1700_B(e_3591_l world, z_1753_f chunkGenerator, c_1514_x pos, K_4074_S state, Random rand) {
        for (int i = 0; i >= -1; --i) {
            for (int j = 0; j >= -1; --j) {
                if (!AbstractMegaTreeGrower.n_1700_B(state, world, pos, i, j)) continue;
                return this.n_1700_B(world, chunkGenerator, pos, state, rand, i, j);
            }
        }
        return super.n_1700_B(world, chunkGenerator, pos, state, rand);
    }

    @Nullable
    protected abstract ConfiguredFeature<TreeConfiguration, ?> n_1700_B(Random var1);

    public boolean n_1700_B(e_3591_l world, z_1753_f chunkGenerator, c_1514_x pos, K_4074_S state, Random rand, int branchX, int branchY) {
        ConfiguredFeature<TreeConfiguration, ?> configuredfeature = this.n_1700_B(rand);
        if (configuredfeature == null) {
            return false;
        }
        ((TreeConfiguration)configuredfeature.u_1723_Y).n_1700_B();
        K_4074_S blockstate = a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
        world.n_1700_B(pos.add(branchX, 0, branchY), blockstate, 4);
        world.n_1700_B(pos.add(branchX + 1, 0, branchY), blockstate, 4);
        world.n_1700_B(pos.add(branchX, 0, branchY + 1), blockstate, 4);
        world.n_1700_B(pos.add(branchX + 1, 0, branchY + 1), blockstate, 4);
        if (configuredfeature.n_1700_B(world, chunkGenerator, rand, pos.add(branchX, 0, branchY))) {
            return true;
        }
        world.n_1700_B(pos.add(branchX, 0, branchY), state, 4);
        world.n_1700_B(pos.add(branchX + 1, 0, branchY), state, 4);
        world.n_1700_B(pos.add(branchX, 0, branchY + 1), state, 4);
        world.n_1700_B(pos.add(branchX + 1, 0, branchY + 1), state, 4);
        return false;
    }

    public static boolean n_1700_B(K_4074_S blockUnder, BlockGetter worldIn, c_1514_x pos, int xOffset, int zOffset) {
        T_2915_h block = blockUnder.J_1907_R();
        return block == worldIn.getBlockState(pos.add(xOffset, 0, zOffset)).J_1907_R() && block == worldIn.getBlockState(pos.add(xOffset + 1, 0, zOffset)).J_1907_R() && block == worldIn.getBlockState(pos.add(xOffset, 0, zOffset + 1)).J_1907_R() && block == worldIn.getBlockState(pos.add(xOffset + 1, 0, zOffset + 1)).J_1907_R();
    }
}


