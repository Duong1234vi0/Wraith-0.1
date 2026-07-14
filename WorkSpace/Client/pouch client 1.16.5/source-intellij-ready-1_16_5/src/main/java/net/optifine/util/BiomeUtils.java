/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package net.optifine.util;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import lightning.product.T_1316_M;
import lightning.product.biomeBiomes;
import lightning.product.V_3137_a;
import lightning.product.BuiltinRegistries;
import lightning.product.BlockAndTintGetter;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.f_2392_k;
import lightning.product.g_2336_b;
import lightning.product.j_154_J;
import lightning.product.k_594_Q;
import net.optifine.config.BiomeId;
import net.optifine.override.ChunkCacheOF;

public class BiomeUtils {
    private static V_3137_a<k_594_Q> biomeRegistry = BiomeUtils.getBiomeRegistry(MinecraftClient.A_4115_X().Y_601_j);
    public static k_594_Q PLAINS = BiomeUtils.getBiomeSafe(biomeRegistry, biomeBiomes.J_1907_R, () -> j_154_J.n_1700_B(false));
    public static k_594_Q SWAMP = BiomeUtils.getBiomeSafe(biomeRegistry, biomeBiomes.v_4262_N, () -> j_154_J.G_564_y(-0.2f, 0.1f, false));
    public static k_594_Q SWAMP_HILLS = BiomeUtils.getBiomeSafe(biomeRegistry, biomeBiomes.O_508_d, () -> j_154_J.G_564_y(-0.1f, 0.3f, true));

    public static void onWorldChanged(b_4507_u worldIn) {
        biomeRegistry = BiomeUtils.getBiomeRegistry(worldIn);
        PLAINS = BiomeUtils.getBiomeSafe(biomeRegistry, biomeBiomes.J_1907_R, () -> j_154_J.n_1700_B(false));
        SWAMP = BiomeUtils.getBiomeSafe(biomeRegistry, biomeBiomes.v_4262_N, () -> j_154_J.G_564_y(-0.2f, 0.1f, false));
        SWAMP_HILLS = BiomeUtils.getBiomeSafe(biomeRegistry, biomeBiomes.O_508_d, () -> j_154_J.G_564_y(-0.1f, 0.3f, true));
    }

    private static k_594_Q getBiomeSafe(V_3137_a<k_594_Q> registry, f_2392_k<k_594_Q> biomeKey, Supplier<k_594_Q> biomeDefault) {
        k_594_Q biome = registry.n_1700_B(biomeKey);
        if (biome == null) {
            biome = biomeDefault.get();
        }
        return biome;
    }

    public static V_3137_a<k_594_Q> getBiomeRegistry(b_4507_u worldIn) {
        return worldIn != null ? worldIn.t_1786_h().J_1907_R(V_3137_a.PlayerInfo) : BuiltinRegistries.t_148_a;
    }

    public static V_3137_a<k_594_Q> getBiomeRegistry() {
        return biomeRegistry;
    }

    public static g_2336_b getLocation(k_594_Q biome) {
        return BiomeUtils.getBiomeRegistry().J_1907_R(biome);
    }

    public static int getId(k_594_Q biome) {
        return BiomeUtils.getBiomeRegistry().n_1700_B(biome);
    }

    public static int getId(g_2336_b loc) {
        k_594_Q biome = BiomeUtils.getBiome(loc);
        return BiomeUtils.getBiomeRegistry().n_1700_B(biome);
    }

    public static BiomeId getBiomeId(g_2336_b loc) {
        return BiomeId.make(loc);
    }

    public static k_594_Q getBiome(g_2336_b loc) {
        return BiomeUtils.getBiomeRegistry().n_1700_B(loc);
    }

    public static Set<g_2336_b> getLocations() {
        return BiomeUtils.getBiomeRegistry().G_564_y();
    }

    public static List<k_594_Q> getBiomes() {
        return Lists.newArrayList(biomeRegistry);
    }

    public static List<BiomeId> getBiomeIds() {
        return BiomeUtils.getBiomeIds(BiomeUtils.getLocations());
    }

    public static List<BiomeId> getBiomeIds(Collection<g_2336_b> locations) {
        ArrayList<BiomeId> list = new ArrayList<BiomeId>();
        for (g_2336_b resourcelocation : locations) {
            BiomeId biomeid = BiomeId.make(resourcelocation);
            if (biomeid == null) continue;
            list.add(biomeid);
        }
        return list;
    }

    public static k_594_Q getBiome(BlockAndTintGetter lightReader, c_1514_x blockPos) {
        k_594_Q biome = PLAINS;
        if (lightReader instanceof ChunkCacheOF) {
            biome = ((ChunkCacheOF)lightReader).getBiome(blockPos);
        } else if (lightReader instanceof T_1316_M) {
            biome = ((T_1316_M)lightReader).P_1922_E(blockPos);
        }
        return biome;
    }
}



