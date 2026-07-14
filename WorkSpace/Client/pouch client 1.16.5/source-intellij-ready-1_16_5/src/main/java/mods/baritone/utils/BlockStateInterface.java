/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.utils;

import lightning.product.ChunkStatus;
import lightning.product.BlockGetter;
import lightning.product.H_1748_a;
import lightning.product.K_4074_S;
import lightning.product.P_3550_Z;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.r_4399_U;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.utils.IPlayerContext;
import mods.baritone.cache.CachedRegion;
import mods.baritone.cache.WorldData;
import mods.baritone.utils.BlockStateInterfaceAccessWrapper;
import mods.baritone.utils.accessor.IClientChunkProvider;
import mods.baritone.utils.pathing.BetterWorldBorder;

public class BlockStateInterface {
    private final r_4399_U provider;
    private final WorldData worldData;
    public final c_1514_x.n_1700_B isPassableBlockPos;
    public final BlockGetter access;
    public final BetterWorldBorder worldBorder;
    private H_1748_a prev = null;
    private CachedRegion prevCached = null;
    private final boolean useTheRealWorld;
    private static final K_4074_S AIR = a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();

    public BlockStateInterface(IPlayerContext ctx) {
        this(ctx, false);
    }

    public BlockStateInterface(IPlayerContext ctx, boolean copyLoadedChunks) {
        b_4507_u world = ctx.world();
        this.worldBorder = new BetterWorldBorder(world.H_2857_Y());
        this.worldData = (WorldData)ctx.worldData();
        this.provider = copyLoadedChunks ? ((IClientChunkProvider)((Object)world.q_2307_F())).createThreadSafeCopy() : (r_4399_U)world.q_2307_F();
        boolean bl = this.useTheRealWorld = (Boolean)Baritone.settings().pathThroughCachedOnly.value == false;
        if (!ctx.minecraft().RealmsLongConfirmationScreen()) {
            throw new IllegalStateException();
        }
        this.isPassableBlockPos = new c_1514_x.n_1700_B();
        this.access = new BlockStateInterfaceAccessWrapper(this);
    }

    public boolean worldContainsLoadedChunk(int blockX, int blockZ) {
        return this.provider.P_1922_E(blockX >> 4, blockZ >> 4);
    }

    public static T_2915_h getBlock(IPlayerContext ctx, c_1514_x pos) {
        return BlockStateInterface.get(ctx, pos).J_1907_R();
    }

    public static K_4074_S get(IPlayerContext ctx, c_1514_x pos) {
        return new BlockStateInterface(ctx).get0(pos.getX(), pos.getY(), pos.getZ());
    }

    public K_4074_S get0(c_1514_x pos) {
        return this.get0(pos.getX(), pos.getY(), pos.getZ());
    }

    public K_4074_S get0(int x, int y, int z) {
        K_4074_S type;
        Object cached;
        if (y < 0 || y >= 256) {
            return AIR;
        }
        if (this.useTheRealWorld) {
            cached = this.prev;
            if (cached != null && ((H_1748_a)cached).getPos().J_1907_R == x >> 4 && ((H_1748_a)cached).getPos().R_4764_Y == z >> 4) {
                return BlockStateInterface.getFromChunk((H_1748_a)cached, x, y, z);
            }
            H_1748_a chunk = this.provider.n_1700_B(x >> 4, z >> 4, ChunkStatus.P_4830_p, false);
            if (chunk != null && !chunk.isEmpty()) {
                this.prev = chunk;
                return BlockStateInterface.getFromChunk(chunk, x, y, z);
            }
        }
        if ((cached = this.prevCached) == null || ((CachedRegion)cached).getX() != x >> 9 || ((CachedRegion)cached).getZ() != z >> 9) {
            if (this.worldData == null) {
                return AIR;
            }
            CachedRegion region = this.worldData.cache.getRegion(x >> 9, z >> 9);
            if (region == null) {
                return AIR;
            }
            this.prevCached = region;
            cached = region;
        }
        if ((type = ((CachedRegion)cached).getBlock(x & 0x1FF, y, z & 0x1FF)) == null) {
            return AIR;
        }
        return type;
    }

    public boolean isLoaded(int x, int z) {
        H_1748_a prevChunk = this.prev;
        if (prevChunk != null && prevChunk.getPos().J_1907_R == x >> 4 && prevChunk.getPos().R_4764_Y == z >> 4) {
            return true;
        }
        prevChunk = this.provider.n_1700_B(x >> 4, z >> 4, ChunkStatus.P_4830_p, false);
        if (prevChunk != null && !prevChunk.isEmpty()) {
            this.prev = prevChunk;
            return true;
        }
        CachedRegion prevRegion = this.prevCached;
        if (prevRegion != null && prevRegion.getX() == x >> 9 && prevRegion.getZ() == z >> 9) {
            return prevRegion.isCached(x & 0x1FF, z & 0x1FF);
        }
        if (this.worldData == null) {
            return false;
        }
        prevRegion = this.worldData.cache.getRegion(x >> 9, z >> 9);
        if (prevRegion == null) {
            return false;
        }
        this.prevCached = prevRegion;
        return prevRegion.isCached(x & 0x1FF, z & 0x1FF);
    }

    public static K_4074_S getFromChunk(H_1748_a chunk, int x, int y, int z) {
        P_3550_Z section = chunk.getSections()[y >> 4];
        if (P_3550_Z.n_1700_B(section)) {
            return AIR;
        }
        return section.n_1700_B(x & 0xF, y & 0xF, z & 0xF);
    }
}


