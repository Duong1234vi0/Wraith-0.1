/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.SurfaceBuilder;
import lightning.product.K_4074_S;
import lightning.product.SurfaceBuilderBaseConfiguration;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.ChunkAccess;
import lightning.product.k_594_Q;

public class DefaultSurfaceBuilder
extends SurfaceBuilder<SurfaceBuilderBaseConfiguration> {
    public DefaultSurfaceBuilder(Codec<SurfaceBuilderBaseConfiguration> p_i232124_1_) {
        super(p_i232124_1_);
    }

    @Override
    public void n_1700_B(Random random, ChunkAccess chunkIn, k_594_Q biomeIn, int x, int z, int startHeight, double noise, K_4074_S defaultBlock, K_4074_S defaultFluid, int seaLevel, long seed, SurfaceBuilderBaseConfiguration config) {
        this.n_1700_B(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, config.n_1700_B(), config.J_1907_R(), config.R_4764_Y(), seaLevel);
    }

    protected void n_1700_B(Random random, ChunkAccess chunkIn, k_594_Q biomeIn, int x, int z, int startHeight, double noise, K_4074_S defaultBlock, K_4074_S defaultFluid, K_4074_S top, K_4074_S middle, K_4074_S bottom, int sealevel) {
        K_4074_S blockstate = top;
        K_4074_S blockstate1 = middle;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        int i = -1;
        int j = (int)(noise / 3.0 + 3.0 + random.nextDouble() * 0.25);
        int k = x & 0xF;
        int l = z & 0xF;
        for (int i1 = startHeight; i1 >= 0; --i1) {
            blockpos$mutable.n_1700_B(k, i1, l);
            K_4074_S blockstate2 = chunkIn.getBlockState(blockpos$mutable);
            if (blockstate2.v_4262_N()) {
                i = -1;
                continue;
            }
            if (!blockstate2.n_1700_B(defaultBlock.J_1907_R())) continue;
            if (i == -1) {
                if (j <= 0) {
                    blockstate = a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
                    blockstate1 = defaultBlock;
                } else if (i1 >= sealevel - 4 && i1 <= sealevel + 1) {
                    blockstate = top;
                    blockstate1 = middle;
                }
                if (i1 < sealevel && (blockstate == null || blockstate.v_4262_N())) {
                    blockstate = biomeIn.n_1700_B(blockpos$mutable.n_1700_B(x, i1, z)) < 0.15f ? a_3742_W.O_1795_e.multiplayerClientSuggestionProvider() : defaultFluid;
                    blockpos$mutable.n_1700_B(k, i1, l);
                }
                i = j;
                if (i1 >= sealevel - 1) {
                    chunkIn.setBlockState(blockpos$mutable, blockstate, false);
                    continue;
                }
                if (i1 < sealevel - 7 - j) {
                    blockstate = a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
                    blockstate1 = defaultBlock;
                    chunkIn.setBlockState(blockpos$mutable, bottom, false);
                    continue;
                }
                chunkIn.setBlockState(blockpos$mutable, blockstate1, false);
                continue;
            }
            if (i <= 0) continue;
            chunkIn.setBlockState(blockpos$mutable, blockstate1, false);
            if (--i != 0 || !blockstate1.n_1700_B(a_3742_W.A_4115_X) || j <= 1) continue;
            i = random.nextInt(4) + Math.max(0, i1 - 63);
            blockstate1 = blockstate1.n_1700_B(a_3742_W.Y_1740_V) ? a_3742_W.BlockFly.multiplayerClientSuggestionProvider() : a_3742_W.h_4320_q.multiplayerClientSuggestionProvider();
        }
    }
}



