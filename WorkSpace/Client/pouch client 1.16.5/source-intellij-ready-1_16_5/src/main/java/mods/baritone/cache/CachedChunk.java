/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 */
package mods.baritone.cache;

import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.f_2392_k;
import mods.baritone.api.api.java.baritone.api.utils.BlockUtils;
import mods.baritone.cache.ChunkPacker;
import mods.baritone.utils.pathing.PathingBlockType;

public final class CachedChunk {
    public static final ImmutableSet<T_2915_h> BLOCKS_TO_KEEP_TRACK_OF = ImmutableSet.of((Object)a_3742_W.k_2348_i, (Object)a_3742_W.P_925_e, (Object)a_3742_W.L_1362_X, (Object)a_3742_W.NumberSetting, (Object)a_3742_W.M_2562_s, (Object)a_3742_W.l_2995_s, (Object[])new T_2915_h[]{a_3742_W.j_306_t, a_3742_W.N_4890_q, a_3742_W.RegionExploit, a_3742_W.Ambience, a_3742_W.x_555_z, a_3742_W.AnomalyESP, a_3742_W.ArmorDurability, a_3742_W.Arrows, a_3742_W.AspectRatio, a_3742_W.BlockESP, a_3742_W.BlockOverlay, a_3742_W.Chams, a_3742_W.ChatBubbles, a_3742_W.Cosmetics, a_3742_W.Crosshair, a_3742_W.CrystalESP, a_3742_W.DistantAlpha, a_3742_W.Emotions, a_3742_W.EntityESP, a_3742_W.M_766_z, a_3742_W.p_3749_n, a_3742_W.k_578_l, a_3742_W.e_837_t, a_3742_W.BooleanSetting, a_3742_W.SoundEventRegistration, a_3742_W.H_1491_c, a_3742_W.h_2367_h, a_3742_W.Setting, a_3742_W.KeyBindSetting, a_3742_W.Module, a_3742_W.ModuleManager, a_3742_W.D_3612_q, a_3742_W.R_2822_N, a_3742_W.ModuleCategory, a_3742_W.p_1458_L, a_3742_W.E_453_w, a_3742_W.c_1608_O, a_3742_W.V_1225_t, a_3742_W.U_1241_n, a_3742_W.q_1982_R, a_3742_W.dtoRealmsServerAddress, a_3742_W.w_612_n, a_3742_W.RealmsServerPing, a_3742_W.j_1564_a, a_3742_W.M_1641_O, a_3742_W.RealmsWorldOptions, a_3742_W.RealmsWorldResetDto, a_3742_W.RegionPingResult, a_3742_W.H_1083_k, a_3742_W.R_3908_n, a_3742_W.ValueObject, a_3742_W.F_1410_V, a_3742_W.S_4022_R, a_3742_W.F_391_H, a_3742_W.r_2478_U, a_3742_W.ItemRelease, a_3742_W.y_1700_S, a_3742_W.W_3729_Q, a_3742_W.L_3570_A, a_3742_W.U_4087_m});
    public static final int SIZE = 131072;
    public static final int SIZE_IN_BYTES = 16384;
    public final int x;
    public final int z;
    private final BitSet data;
    private final Int2ObjectOpenHashMap<String> special;
    private final K_4074_S[] overview;
    private final int[] heightMap;
    private final Map<String, List<c_1514_x>> specialBlockLocations;
    public final long cacheTimestamp;

    CachedChunk(int x, int z, BitSet data, K_4074_S[] overview, Map<String, List<c_1514_x>> specialBlockLocations, long cacheTimestamp) {
        CachedChunk.validateSize(data);
        this.x = x;
        this.z = z;
        this.data = data;
        this.overview = overview;
        this.heightMap = new int[256];
        this.specialBlockLocations = specialBlockLocations;
        this.cacheTimestamp = cacheTimestamp;
        if (specialBlockLocations.isEmpty()) {
            this.special = null;
        } else {
            this.special = new Int2ObjectOpenHashMap();
            this.setSpecial();
        }
        this.calculateHeightMap();
    }

    private final void setSpecial() {
        for (Map.Entry<String, List<c_1514_x>> entry : this.specialBlockLocations.entrySet()) {
            for (c_1514_x pos : entry.getValue()) {
                this.special.put(CachedChunk.getPositionIndex(pos.getX(), pos.getY(), pos.getZ()), (Object)entry.getKey());
            }
        }
    }

    public final K_4074_S getBlock(int x, int y, int z, f_2392_k<b_4507_u> dimension) {
        String str;
        int index = CachedChunk.getPositionIndex(x, y, z);
        PathingBlockType type = this.getType(index);
        int internalPos = z << 4 | x;
        if (this.heightMap[internalPos] == y && type != PathingBlockType.AVOID) {
            return this.overview[internalPos];
        }
        if (this.special != null && (str = (String)this.special.get(index)) != null) {
            return BlockUtils.stringToBlockRequired(str).multiplayerClientSuggestionProvider();
        }
        if (type == PathingBlockType.SOLID) {
            if (y == 127 && dimension == b_4507_u.v_4262_N) {
                return a_3742_W.Z_875_P.multiplayerClientSuggestionProvider();
            }
            if (y < 5 && dimension == b_4507_u.u_1723_Y) {
                return a_3742_W.ClientBootstrap.multiplayerClientSuggestionProvider();
            }
        }
        return ChunkPacker.pathingTypeToBlock(type, dimension);
    }

    private PathingBlockType getType(int index) {
        return PathingBlockType.fromBits(this.data.get(index), this.data.get(index + 1));
    }

    private void calculateHeightMap() {
        for (int z = 0; z < 16; ++z) {
            block1: for (int x = 0; x < 16; ++x) {
                int index = z << 4 | x;
                this.heightMap[index] = 0;
                for (int y = 256; y >= 0; --y) {
                    int i = CachedChunk.getPositionIndex(x, y, z);
                    if (!this.data.get(i) && !this.data.get(i + 1)) continue;
                    this.heightMap[index] = y;
                    continue block1;
                }
            }
        }
    }

    public final K_4074_S[] getOverview() {
        return this.overview;
    }

    public final Map<String, List<c_1514_x>> getRelativeBlocks() {
        return this.specialBlockLocations;
    }

    public final ArrayList<c_1514_x> getAbsoluteBlocks(String blockType) {
        if (this.specialBlockLocations.get(blockType) == null) {
            return null;
        }
        ArrayList<c_1514_x> res = new ArrayList<c_1514_x>();
        for (c_1514_x pos : this.specialBlockLocations.get(blockType)) {
            res.add(new c_1514_x(pos.getX() + this.x * 16, pos.getY(), pos.getZ() + this.z * 16));
        }
        return res;
    }

    public final byte[] toByteArray() {
        return this.data.toByteArray();
    }

    public static int getPositionIndex(int x, int y, int z) {
        return x << 1 | z << 5 | y << 9;
    }

    private static void validateSize(BitSet data) {
        if (data.size() > 131072) {
            throw new IllegalArgumentException("BitSet of invalid length provided");
        }
    }
}



