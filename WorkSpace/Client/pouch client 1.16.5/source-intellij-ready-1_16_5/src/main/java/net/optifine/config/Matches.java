/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.config;

import lightning.product.B_3871_I;
import lightning.product.K_4074_S;
import lightning.product.k_594_Q;
import net.optifine.config.BiomeId;
import net.optifine.config.MatchBlock;

public class Matches {
    public static boolean block(K_4074_S blockStateBase, MatchBlock[] matchBlocks) {
        if (matchBlocks == null) {
            return true;
        }
        for (int i = 0; i < matchBlocks.length; ++i) {
            MatchBlock matchblock = matchBlocks[i];
            if (!matchblock.matches(blockStateBase)) continue;
            return true;
        }
        return false;
    }

    public static boolean block(int blockId, int metadata, MatchBlock[] matchBlocks) {
        if (matchBlocks == null) {
            return true;
        }
        for (int i = 0; i < matchBlocks.length; ++i) {
            MatchBlock matchblock = matchBlocks[i];
            if (!matchblock.matches(blockId, metadata)) continue;
            return true;
        }
        return false;
    }

    public static boolean blockId(int blockId, MatchBlock[] matchBlocks) {
        if (matchBlocks == null) {
            return true;
        }
        for (int i = 0; i < matchBlocks.length; ++i) {
            MatchBlock matchblock = matchBlocks[i];
            if (matchblock.getBlockId() != blockId) continue;
            return true;
        }
        return false;
    }

    public static boolean metadata(int metadata, int[] metadatas) {
        if (metadatas == null) {
            return true;
        }
        for (int i = 0; i < metadatas.length; ++i) {
            if (metadatas[i] != metadata) continue;
            return true;
        }
        return false;
    }

    public static boolean sprite(B_3871_I sprite, B_3871_I[] sprites) {
        if (sprites == null) {
            return true;
        }
        for (int i = 0; i < sprites.length; ++i) {
            if (sprites[i] != sprite) continue;
            return true;
        }
        return false;
    }

    public static boolean biome(k_594_Q biome, BiomeId[] biomes) {
        if (biomes == null) {
            return true;
        }
        for (int i = 0; i < biomes.length; ++i) {
            BiomeId biomeid = biomes[i];
            if (biomeid == null || biomeid.getBiome() != biome) continue;
            return true;
        }
        return false;
    }
}

