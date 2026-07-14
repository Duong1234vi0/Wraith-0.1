/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.override;

import java.util.Arrays;
import lightning.product.H_1748_a;
import lightning.product.K_4074_S;
import lightning.product.K_4719_o;
import lightning.product.R_1900_x;
import lightning.product.Y_3830_x;
import lightning.product.BlockAndTintGetter;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.i_2154_H;
import lightning.product.k_594_Q;
import lightning.product.ColorResolver;
import net.optifine.BlockPosM;
import net.optifine.render.RenderEnv;
import net.optifine.util.ArrayCache;

public class ChunkCacheOF
implements BlockAndTintGetter {
    private final Y_3830_x chunkCache;
    private final int posX;
    private final int posY;
    private final int posZ;
    private final int sizeX;
    private final int sizeY;
    private final int sizeZ;
    private final int sizeXZ;
    private int[] combinedLights;
    private K_4074_S[] blockStates;
    private k_594_Q[] biomes;
    private final int arraySize;
    private RenderEnv renderEnv;
    private static final ArrayCache cacheCombinedLights = new ArrayCache(Integer.TYPE, 16);
    private static final ArrayCache cacheBlockStates = new ArrayCache(K_4074_S.class, 16);
    private static final ArrayCache cacheBiomes = new ArrayCache(k_594_Q.class, 16);

    public ChunkCacheOF(Y_3830_x chunkCache, c_1514_x posFromIn, c_1514_x posToIn, int subIn) {
        this.chunkCache = chunkCache;
        int i = posFromIn.getX() - subIn >> 4;
        int j = posFromIn.getY() - subIn >> 4;
        int k = posFromIn.getZ() - subIn >> 4;
        int l = posToIn.getX() + subIn >> 4;
        int i1 = posToIn.getY() + subIn >> 4;
        int j1 = posToIn.getZ() + subIn >> 4;
        this.sizeX = l - i + 1 << 4;
        this.sizeY = i1 - j + 1 << 4;
        this.sizeZ = j1 - k + 1 << 4;
        this.sizeXZ = this.sizeX * this.sizeZ;
        this.arraySize = this.sizeX * this.sizeY * this.sizeZ;
        this.posX = i << 4;
        this.posY = j << 4;
        this.posZ = k << 4;
    }

    public int getPositionIndex(c_1514_x pos) {
        int i = pos.getX() - this.posX;
        if (i >= 0 && i < this.sizeX) {
            int j = pos.getY() - this.posY;
            if (j >= 0 && j < this.sizeY) {
                int k = pos.getZ() - this.posZ;
                return k >= 0 && k < this.sizeZ ? j * this.sizeXZ + k * this.sizeX + i : -1;
            }
            return -1;
        }
        return -1;
    }

    @Override
    public int getLightFor(K_4719_o type, c_1514_x pos) {
        return this.chunkCache.getLightFor(type, pos);
    }

    @Override
    public K_4074_S getBlockState(c_1514_x pos) {
        int i = this.getPositionIndex(pos);
        if (i >= 0 && i < this.arraySize && this.blockStates != null) {
            K_4074_S blockstate = this.blockStates[i];
            if (blockstate == null) {
                this.blockStates[i] = blockstate = this.chunkCache.getBlockState(pos);
            }
            return blockstate;
        }
        return this.chunkCache.getBlockState(pos);
    }

    public void renderStart() {
        if (this.combinedLights == null) {
            this.combinedLights = (int[])cacheCombinedLights.allocate(this.arraySize);
        }
        if (this.blockStates == null) {
            this.blockStates = (K_4074_S[])cacheBlockStates.allocate(this.arraySize);
        }
        if (this.biomes == null) {
            this.biomes = (k_594_Q[])cacheBiomes.allocate(this.arraySize);
        }
        Arrays.fill(this.combinedLights, -1);
        Arrays.fill(this.blockStates, null);
        Arrays.fill(this.biomes, null);
        this.loadBlockStates();
    }

    private void loadBlockStates() {
        if (this.sizeX == 48 && this.sizeY == 48 && this.sizeZ == 48) {
            H_1748_a chunk = this.chunkCache.n_1700_B(1, 1);
            BlockPosM blockposm = new BlockPosM();
            for (int i = 16; i < 32; ++i) {
                int j = i * this.sizeXZ;
                for (int k = 16; k < 32; ++k) {
                    int l = k * this.sizeX;
                    for (int i1 = 16; i1 < 32; ++i1) {
                        K_4074_S blockstate;
                        blockposm.setXyz(this.posX + i1, this.posY + i, this.posZ + k);
                        int j1 = j + l + i1;
                        this.blockStates[j1] = blockstate = chunk.getBlockState(blockposm);
                    }
                }
            }
        }
    }

    public void renderFinish() {
        cacheCombinedLights.free(this.combinedLights);
        this.combinedLights = null;
        cacheBlockStates.free(this.blockStates);
        this.blockStates = null;
        cacheBiomes.free(this.biomes);
        this.biomes = null;
    }

    public int[] getCombinedLights() {
        return this.combinedLights;
    }

    public k_594_Q getBiome(c_1514_x pos) {
        int i = this.getPositionIndex(pos);
        if (i >= 0 && i < this.arraySize && this.biomes != null) {
            k_594_Q biome = this.biomes[i];
            if (biome == null) {
                this.biomes[i] = biome = this.chunkCache.J_1907_R(pos);
            }
            return biome;
        }
        return this.chunkCache.J_1907_R(pos);
    }

    @Override
    public i_2154_H getTileEntity(c_1514_x pos) {
        return this.chunkCache.n_1700_B(pos, H_1748_a.n_1700_B.R_4764_Y);
    }

    public i_2154_H getTileEntity(c_1514_x pos, H_1748_a.n_1700_B type) {
        return this.chunkCache.n_1700_B(pos, type);
    }

    @Override
    public boolean canSeeSky(c_1514_x pos) {
        return this.chunkCache.canSeeSky(pos);
    }

    @Override
    public FluidState getFluidState(c_1514_x pos) {
        return this.getBlockState(pos).P_4830_p();
    }

    @Override
    public int getBlockColor(c_1514_x blockPosIn, ColorResolver colorResolverIn) {
        return this.chunkCache.getBlockColor(blockPosIn, colorResolverIn);
    }

    @Override
    public R_1900_x getLightManager() {
        return this.chunkCache.getLightManager();
    }

    public RenderEnv getRenderEnv() {
        return this.renderEnv;
    }

    public void setRenderEnv(RenderEnv renderEnv) {
        this.renderEnv = renderEnv;
    }

    @Override
    public float func_230487_a_(b_257_Y directionIn, boolean shadeIn) {
        return this.chunkCache.func_230487_a_(directionIn, shadeIn);
    }
}


