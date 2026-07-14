/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.render;

import lightning.product.K_4074_S;
import lightning.product.W_571_B;
import lightning.product.BlockAndTintGetter;
import lightning.product.c_1514_x;
import lightning.product.z_883_p;
import net.optifine.override.ChunkCacheOF;

public class LightCacheOF {
    public static final float getBrightness(K_4074_S blockStateIn, BlockAndTintGetter worldIn, c_1514_x blockPosIn) {
        float f = blockStateIn.u_1723_Y(worldIn, blockPosIn);
        return W_571_B.n_1700_B(f);
    }

    public static final int getPackedLight(K_4074_S blockStateIn, BlockAndTintGetter worldIn, c_1514_x blockPosIn) {
        if (worldIn instanceof ChunkCacheOF) {
            ChunkCacheOF chunkcacheof = (ChunkCacheOF)worldIn;
            int[] aint = chunkcacheof.getCombinedLights();
            int i = chunkcacheof.getPositionIndex(blockPosIn);
            if (i >= 0 && i < aint.length && aint != null) {
                int j = aint[i];
                if (j == -1) {
                    aint[i] = j = z_883_p.n_1700_B(worldIn, blockStateIn, blockPosIn);
                }
                return j;
            }
            return z_883_p.n_1700_B(worldIn, blockStateIn, blockPosIn);
        }
        return z_883_p.n_1700_B(worldIn, blockStateIn, blockPosIn);
    }
}


