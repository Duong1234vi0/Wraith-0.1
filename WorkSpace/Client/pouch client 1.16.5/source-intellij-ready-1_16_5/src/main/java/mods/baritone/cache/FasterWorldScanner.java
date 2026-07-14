/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.Unpooled
 */
package mods.baritone.cache;

import io.netty.buffer.Unpooled;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lightning.product.ChunkSource;
import lightning.product.H_1748_a;
import lightning.product.J_270_s;
import lightning.product.K_4074_S;
import lightning.product.M_4466_T;
import lightning.product.P_3550_Z;
import lightning.product.T_2915_h;
import lightning.product.GlobalPalette;
import lightning.product.Y_1387_d;
import lightning.product.b_2585_i;
import lightning.product.c_1514_x;
import lightning.product.Palette;
import lightning.product.w_424_u;
import mods.baritone.api.api.java.baritone.api.cache.ICachedWorld;
import mods.baritone.api.api.java.baritone.api.cache.IWorldScanner;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;
import mods.baritone.api.api.java.baritone.api.utils.BlockOptionalMetaLookup;
import mods.baritone.api.api.java.baritone.api.utils.IPlayerContext;

public final class FasterWorldScanner
extends Enum<FasterWorldScanner>
implements IWorldScanner {
    public static final /* enum */ FasterWorldScanner INSTANCE = new FasterWorldScanner();
    private static final /* synthetic */ FasterWorldScanner[] $VALUES;

    public static FasterWorldScanner[] values() {
        return (FasterWorldScanner[])$VALUES.clone();
    }

    public static FasterWorldScanner valueOf(String name) {
        return Enum.valueOf(FasterWorldScanner.class, name);
    }

    @Override
    public List<c_1514_x> scanChunkRadius(IPlayerContext ctx, BlockOptionalMetaLookup filter, int max, int yLevelThreshold, int maxSearchRadius) {
        assert (ctx.world() != null);
        if (maxSearchRadius < 0) {
            throw new IllegalArgumentException("chunkRange must be >= 0");
        }
        return this.scanChunksInternal(ctx, filter, FasterWorldScanner.getChunkRange(ctx.playerFeet().x >> 4, ctx.playerFeet().z >> 4, maxSearchRadius), max);
    }

    @Override
    public List<c_1514_x> scanChunk(IPlayerContext ctx, BlockOptionalMetaLookup filter, Y_1387_d pos, int max, int yLevelThreshold) {
        Stream<c_1514_x> stream = this.scanChunkInternal(ctx, filter, pos);
        if (max >= 0) {
            stream = stream.limit(max);
        }
        return stream.collect(Collectors.toList());
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

    public static List<Y_1387_d> getChunkRange(int centerX, int centerZ, int chunkRadius) {
        ArrayList<Y_1387_d> chunks = new ArrayList<Y_1387_d>();
        chunks.add(new Y_1387_d(centerX, centerZ));
        for (int i = 1; i < chunkRadius; ++i) {
            for (int j = 0; j <= i; ++j) {
                chunks.add(new Y_1387_d(centerX - j, centerZ - i));
                if (j != 0) {
                    chunks.add(new Y_1387_d(centerX + j, centerZ - i));
                    chunks.add(new Y_1387_d(centerX - j, centerZ + i));
                }
                chunks.add(new Y_1387_d(centerX + j, centerZ + i));
                if (j == i) continue;
                chunks.add(new Y_1387_d(centerX - i, centerZ - j));
                chunks.add(new Y_1387_d(centerX + i, centerZ - j));
                if (j == 0) continue;
                chunks.add(new Y_1387_d(centerX - i, centerZ + j));
                chunks.add(new Y_1387_d(centerX + i, centerZ + j));
            }
        }
        return chunks;
    }

    private List<c_1514_x> scanChunksInternal(IPlayerContext ctx, BlockOptionalMetaLookup lookup, List<Y_1387_d> chunkPositions, int maxBlocks) {
        assert (ctx.world() != null);
        try {
            Stream posStream = chunkPositions.parallelStream().flatMap(p -> this.scanChunkInternal(ctx, lookup, (Y_1387_d)p));
            if (maxBlocks >= 0) {
                posStream = posStream.limit(maxBlocks);
            }
            return posStream.collect(Collectors.toList());
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private Stream<c_1514_x> scanChunkInternal(IPlayerContext ctx, BlockOptionalMetaLookup lookup, Y_1387_d pos) {
        ChunkSource chunkProvider = ctx.world().q_2307_F();
        if (!chunkProvider.P_1922_E(pos.J_1907_R, pos.R_4764_Y)) {
            return Stream.empty();
        }
        long chunkX = (long)pos.J_1907_R << 4;
        long chunkZ = (long)pos.R_4764_Y << 4;
        int playerSectionY = ctx.playerFeet().y >> 4;
        return this.collectChunkSections(lookup, chunkProvider.n_1700_B(pos.J_1907_R, pos.R_4764_Y, false), chunkX, chunkZ, playerSectionY).stream();
    }

    private List<c_1514_x> collectChunkSections(BlockOptionalMetaLookup lookup, H_1748_a chunk, long chunkX, long chunkZ, int playerSection) {
        ArrayList<c_1514_x> blocks = new ArrayList<c_1514_x>();
        P_3550_Z[] sections = chunk.getSections();
        int l = sections.length;
        int i = playerSection - 1;
        for (int j = playerSection; i >= 0 || j < l; ++j, --i) {
            if (j < l) {
                this.visitSection(lookup, sections[j], blocks, chunkX, chunkZ);
            }
            if (i < 0) continue;
            this.visitSection(lookup, sections[i], blocks, chunkX, chunkZ);
        }
        return blocks;
    }

    private void visitSection(BlockOptionalMetaLookup lookup, P_3550_Z section, List<c_1514_x> blocks, long chunkX, long chunkZ) {
        if (section == null || section.R_4764_Y()) {
            return;
        }
        M_4466_T<K_4074_S> sectionContainer = section.t_148_a();
        if (sectionContainer.getStorage() == null) {
            return;
        }
        boolean[] isInFilter = this.getIncludedFilterIndices(lookup, sectionContainer.getPalette());
        if (isInFilter.length == 0) {
            return;
        }
        J_270_s array = section.t_148_a().getStorage();
        long[] longArray = array.n_1700_B();
        int arraySize = array.J_1907_R();
        int bitsPerEntry = array.getBitsPerEntry();
        long maxEntryValue = array.getMaxEntryValue();
        int yOffset = section.v_4262_N();
        int idx = 0;
        for (int i = 0; i < longArray.length && idx < arraySize; ++i) {
            long l = longArray[i];
            for (int offset = 0; offset <= 64 - bitsPerEntry && idx < arraySize; offset += bitsPerEntry, ++idx) {
                int value = (int)(l >> offset & maxEntryValue);
                if (!isInFilter[value]) continue;
                blocks.add(new c_1514_x(chunkX + (long)(idx & 0xFF & 0xF), (double)(yOffset + (idx >> 8)), chunkZ + (long)((idx & 0xFF) >> 4)));
            }
        }
    }

    private boolean[] getIncludedFilterIndices(BlockOptionalMetaLookup lookup, Palette<K_4074_S> palette) {
        boolean commonBlockFound = false;
        w_424_u<K_4074_S> paletteMap = FasterWorldScanner.getPalette(palette);
        int size = paletteMap.n_1700_B();
        boolean[] isInFilter = new boolean[size];
        for (int i = 0; i < size; ++i) {
            K_4074_S state = paletteMap.n_1700_B(i);
            if (lookup.has(state)) {
                isInFilter[i] = true;
                commonBlockFound = true;
                continue;
            }
            isInFilter[i] = false;
        }
        if (!commonBlockFound) {
            return new boolean[0];
        }
        return isInFilter;
    }

    private static w_424_u<K_4074_S> getPalette(Palette<K_4074_S> palette) {
        if (palette instanceof GlobalPalette) {
            return T_2915_h.t_4043_B;
        }
        b_2585_i buf = new b_2585_i(Unpooled.buffer());
        palette.J_1907_R(buf);
        int size = buf.u_1723_Y();
        w_424_u<K_4074_S> states = new w_424_u<K_4074_S>();
        for (int i = 0; i < size; ++i) {
            K_4074_S state = T_2915_h.t_4043_B.n_1700_B(buf.u_1723_Y());
            assert (state != null);
            states.n_1700_B(state, i);
        }
        return states;
    }

    private static /* synthetic */ FasterWorldScanner[] $values() {
        return new FasterWorldScanner[]{INSTANCE};
    }

    static {
        $VALUES = FasterWorldScanner.$values();
    }
}


