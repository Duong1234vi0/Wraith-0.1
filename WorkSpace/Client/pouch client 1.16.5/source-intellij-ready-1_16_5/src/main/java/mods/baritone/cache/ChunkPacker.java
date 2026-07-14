/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.cache;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import lightning.product.E_3601_d;
import lightning.product.H_1748_a;
import lightning.product.K_4074_S;
import lightning.product.M_4466_T;
import lightning.product.P_3550_Z;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.FlowerBlock;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.f_2392_k;
import lightning.product.AirBlock;
import lightning.product.DoublePlantBlock;
import mods.baritone.api.api.java.baritone.api.utils.BlockUtils;
import mods.baritone.cache.CachedChunk;
import mods.baritone.pathing.movement.MovementHelper;
import mods.baritone.utils.BlockStateInterface;
import mods.baritone.utils.pathing.PathingBlockType;

public final class ChunkPacker {
    private ChunkPacker() {
    }

    public static CachedChunk pack(H_1748_a chunk) {
        HashMap<String, List<c_1514_x>> specialBlocks = new HashMap<String, List<c_1514_x>>();
        BitSet bitSet = new BitSet(131072);
        try {
            P_3550_Z[] chunkInternalStorageArray = chunk.getSections();
            for (int y0 = 0; y0 < 16; ++y0) {
                P_3550_Z extendedblockstorage = chunkInternalStorageArray[y0];
                if (extendedblockstorage == null) continue;
                M_4466_T<K_4074_S> bsc = extendedblockstorage.t_148_a();
                int yReal = y0 << 4;
                for (int y1 = 0; y1 < 16; ++y1) {
                    int y = y1 | yReal;
                    for (int z = 0; z < 16; ++z) {
                        for (int x = 0; x < 16; ++x) {
                            int index = CachedChunk.getPositionIndex(x, y, z);
                            K_4074_S state = bsc.n_1700_B(x, y1, z);
                            boolean[] bits = ChunkPacker.getPathingBlockType(state, chunk, x, y, z).getBits();
                            bitSet.set(index, bits[0]);
                            bitSet.set(index + 1, bits[1]);
                            T_2915_h block = state.J_1907_R();
                            if (!CachedChunk.BLOCKS_TO_KEEP_TRACK_OF.contains((Object)block)) continue;
                            String name = BlockUtils.blockToString(block);
                            specialBlocks.computeIfAbsent(name, b -> new ArrayList()).add(new c_1514_x(x, y, z));
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        K_4074_S[] blocks = new K_4074_S[256];
        for (int z = 0; z < 16; ++z) {
            block7: for (int x = 0; x < 16; ++x) {
                for (int y = 255; y >= 0; --y) {
                    int index = CachedChunk.getPositionIndex(x, y, z);
                    if (!bitSet.get(index) && !bitSet.get(index + 1)) continue;
                    blocks[z << 4 | x] = BlockStateInterface.getFromChunk(chunk, x, y, z);
                    continue block7;
                }
                blocks[z << 4 | x] = a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
            }
        }
        return new CachedChunk(chunk.getPos().J_1907_R, chunk.getPos().R_4764_Y, bitSet, blocks, specialBlocks, System.currentTimeMillis());
    }

    private static PathingBlockType getPathingBlockType(K_4074_S state, H_1748_a chunk, int x, int y, int z) {
        T_2915_h block = state.J_1907_R();
        if (MovementHelper.isWater(state)) {
            if (MovementHelper.possiblyFlowing(state)) {
                return PathingBlockType.AVOID;
            }
            if (x != 15 && MovementHelper.possiblyFlowing(BlockStateInterface.getFromChunk(chunk, x + 1, y, z)) || x != 0 && MovementHelper.possiblyFlowing(BlockStateInterface.getFromChunk(chunk, x - 1, y, z)) || z != 15 && MovementHelper.possiblyFlowing(BlockStateInterface.getFromChunk(chunk, x, y, z + 1)) || z != 0 && MovementHelper.possiblyFlowing(BlockStateInterface.getFromChunk(chunk, x, y, z - 1))) {
                return PathingBlockType.AVOID;
            }
            if (x == 0 || x == 15 || z == 0 || z == 15) {
                e_2866_D flow = state.P_4830_p().R_4764_Y(chunk.getWorld(), new c_1514_x(x + (chunk.getPos().J_1907_R << 4), y, z + (chunk.getPos().R_4764_Y << 4)));
                if (flow.J_1907_R != 0.0 || flow.G_564_y != 0.0) {
                    return PathingBlockType.WATER;
                }
                return PathingBlockType.AVOID;
            }
            return PathingBlockType.WATER;
        }
        if (MovementHelper.avoidWalkingInto(state) || MovementHelper.isBottomSlab(state)) {
            return PathingBlockType.AVOID;
        }
        if (block instanceof AirBlock || block instanceof E_3601_d || block instanceof DoublePlantBlock || block instanceof FlowerBlock) {
            return PathingBlockType.AIR;
        }
        return PathingBlockType.SOLID;
    }

    public static K_4074_S pathingTypeToBlock(PathingBlockType type, f_2392_k<b_4507_u> dimension) {
        switch (type) {
            case AIR: {
                return a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
            }
            case WATER: {
                return a_3742_W.c_3005_b.multiplayerClientSuggestionProvider();
            }
            case AVOID: {
                return a_3742_W.H_2857_Y.multiplayerClientSuggestionProvider();
            }
            case SOLID: {
                if (dimension == b_4507_u.u_1723_Y) {
                    return a_3742_W.J_1907_R.multiplayerClientSuggestionProvider();
                }
                if (dimension == b_4507_u.v_4262_N) {
                    return a_3742_W.i_3196_G.multiplayerClientSuggestionProvider();
                }
                if (dimension != b_4507_u.w_1484_f) break;
                return a_3742_W.e_1231_S.multiplayerClientSuggestionProvider();
            }
        }
        return null;
    }
}


