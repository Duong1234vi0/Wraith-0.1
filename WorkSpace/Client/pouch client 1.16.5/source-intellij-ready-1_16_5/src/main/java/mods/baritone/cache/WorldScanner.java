/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import lightning.product.ChunkSource;
import lightning.product.H_1748_a;
import lightning.product.K_4074_S;
import lightning.product.M_4466_T;
import lightning.product.P_3550_Z;
import lightning.product.Y_1387_d;
import lightning.product.c_1514_x;
import lightning.product.r_4399_U;
import mods.baritone.api.api.java.baritone.api.cache.ICachedWorld;
import mods.baritone.api.api.java.baritone.api.cache.IWorldScanner;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;
import mods.baritone.api.api.java.baritone.api.utils.BlockOptionalMetaLookup;
import mods.baritone.api.api.java.baritone.api.utils.IPlayerContext;

public enum WorldScanner implements IWorldScanner
{
    INSTANCE;

    private static final int[] DEFAULT_COORDINATE_ITERATION_ORDER;

    @Override
    public List<c_1514_x> scanChunkRadius(IPlayerContext ctx, BlockOptionalMetaLookup filter, int max, int yLevelThreshold, int maxSearchRadius) {
        ArrayList<c_1514_x> res = new ArrayList<c_1514_x>();
        if (filter.blocks().isEmpty()) {
            return res;
        }
        r_4399_U chunkProvider = (r_4399_U)ctx.world().q_2307_F();
        int maxSearchRadiusSq = maxSearchRadius * maxSearchRadius;
        int playerChunkX = ctx.playerFeet().getX() >> 4;
        int playerChunkZ = ctx.playerFeet().getZ() >> 4;
        int playerY = ctx.playerFeet().getY();
        int playerYBlockStateContainerIndex = playerY >> 4;
        int[] coordinateIterationOrder = IntStream.range(0, 16).boxed().sorted(Comparator.comparingInt(y -> Math.abs(y - playerYBlockStateContainerIndex))).mapToInt(x -> x).toArray();
        int searchRadiusSq = 0;
        boolean foundWithinY = false;
        while (true) {
            boolean allUnloaded = true;
            boolean foundChunks = false;
            for (int xoff = -searchRadiusSq; xoff <= searchRadiusSq; ++xoff) {
                for (int zoff = -searchRadiusSq; zoff <= searchRadiusSq; ++zoff) {
                    int distance = xoff * xoff + zoff * zoff;
                    if (distance != searchRadiusSq) continue;
                    foundChunks = true;
                    int chunkX = xoff + playerChunkX;
                    int chunkZ = zoff + playerChunkZ;
                    H_1748_a chunk = chunkProvider.n_1700_B(chunkX, chunkZ, null, false);
                    if (chunk == null) continue;
                    allUnloaded = false;
                    if (!this.scanChunkInto(chunkX << 4, chunkZ << 4, chunk, filter, res, max, yLevelThreshold, playerY, coordinateIterationOrder)) continue;
                    foundWithinY = true;
                }
            }
            if (allUnloaded && foundChunks || res.size() >= max && (searchRadiusSq > maxSearchRadiusSq || searchRadiusSq > 1 && foundWithinY)) {
                return res;
            }
            ++searchRadiusSq;
        }
    }

    @Override
    public List<c_1514_x> scanChunk(IPlayerContext ctx, BlockOptionalMetaLookup filter, Y_1387_d pos, int max, int yLevelThreshold) {
        if (filter.blocks().isEmpty()) {
            return Collections.emptyList();
        }
        r_4399_U chunkProvider = (r_4399_U)ctx.world().q_2307_F();
        H_1748_a chunk = chunkProvider.n_1700_B(pos.J_1907_R, pos.R_4764_Y, null, false);
        int playerY = ctx.playerFeet().getY();
        if (chunk == null || chunk.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList<c_1514_x> res = new ArrayList<c_1514_x>();
        this.scanChunkInto(pos.J_1907_R << 4, pos.R_4764_Y << 4, chunk, filter, res, max, yLevelThreshold, playerY, DEFAULT_COORDINATE_ITERATION_ORDER);
        return res;
    }

    @Override
    public int repack(IPlayerContext ctx) {
        return this.repack(ctx, 40);
    }

    @Override
    public int repack(IPlayerContext ctx, int range) {
        ChunkSource chunkProvider = ctx.world().q_2307_F();
        ICachedWorld cachedWorld = ctx.worldData().getCachedWorld();
        BetterBlockPos playerPos = ctx.playerFeet();
        int playerChunkX = playerPos.getX() >> 4;
        int playerChunkZ = playerPos.getZ() >> 4;
        int minX = playerChunkX - range;
        int minZ = playerChunkZ - range;
        int maxX = playerChunkX + range;
        int maxZ = playerChunkZ + range;
        int queued = 0;
        for (int x = minX; x <= maxX; ++x) {
            for (int z = minZ; z <= maxZ; ++z) {
                H_1748_a chunk = chunkProvider.n_1700_B(x, z, false);
                if (chunk == null || chunk.isEmpty()) continue;
                ++queued;
                cachedWorld.queueForPacking(chunk);
            }
        }
        return queued;
    }

    private boolean scanChunkInto(int chunkX, int chunkZ, H_1748_a chunk, BlockOptionalMetaLookup filter, Collection<c_1514_x> result, int max, int yLevelThreshold, int playerY, int[] coordinateIterationOrder) {
        P_3550_Z[] chunkInternalStorageArray = chunk.getSections();
        boolean foundWithinY = false;
        for (int yIndex = 0; yIndex < 16; ++yIndex) {
            int y0 = coordinateIterationOrder[yIndex];
            P_3550_Z section = chunkInternalStorageArray[y0];
            if (section == null || P_3550_Z.n_1700_B(section)) continue;
            int yReal = y0 << 4;
            M_4466_T<K_4074_S> bsc = section.t_148_a();
            for (int yy = 0; yy < 16; ++yy) {
                for (int z = 0; z < 16; ++z) {
                    for (int x = 0; x < 16; ++x) {
                        K_4074_S state = bsc.n_1700_B(x, yy, z);
                        if (!filter.has(state)) continue;
                        int y = yReal | yy;
                        if (result.size() >= max) {
                            if (Math.abs(y - playerY) < yLevelThreshold) {
                                foundWithinY = true;
                            } else if (foundWithinY) {
                                return true;
                            }
                        }
                        result.add(new c_1514_x(chunkX | x, y, chunkZ | z));
                    }
                }
            }
        }
        return foundWithinY;
    }

    static {
        DEFAULT_COORDINATE_ITERATION_ORDER = IntStream.range(0, 16).toArray();
    }
}


