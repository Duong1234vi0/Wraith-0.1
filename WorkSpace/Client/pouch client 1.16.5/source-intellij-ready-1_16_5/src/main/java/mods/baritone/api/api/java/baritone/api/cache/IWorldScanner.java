/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.cache;

import java.util.List;
import lightning.product.T_2915_h;
import lightning.product.Y_1387_d;
import lightning.product.c_1514_x;
import mods.baritone.api.api.java.baritone.api.utils.BlockOptionalMetaLookup;
import mods.baritone.api.api.java.baritone.api.utils.IPlayerContext;

public interface IWorldScanner {
    public List<c_1514_x> scanChunkRadius(IPlayerContext var1, BlockOptionalMetaLookup var2, int var3, int var4, int var5);

    default public List<c_1514_x> scanChunkRadius(IPlayerContext ctx, List<T_2915_h> filter, int max, int yLevelThreshold, int maxSearchRadius) {
        return this.scanChunkRadius(ctx, new BlockOptionalMetaLookup(filter.toArray(new T_2915_h[0])), max, yLevelThreshold, maxSearchRadius);
    }

    public List<c_1514_x> scanChunk(IPlayerContext var1, BlockOptionalMetaLookup var2, Y_1387_d var3, int var4, int var5);

    default public List<c_1514_x> scanChunk(IPlayerContext ctx, List<T_2915_h> blocks, Y_1387_d pos, int max, int yLevelThreshold) {
        return this.scanChunk(ctx, new BlockOptionalMetaLookup(blocks), pos, max, yLevelThreshold);
    }

    public int repack(IPlayerContext var1);

    public int repack(IPlayerContext var1, int var2);
}

