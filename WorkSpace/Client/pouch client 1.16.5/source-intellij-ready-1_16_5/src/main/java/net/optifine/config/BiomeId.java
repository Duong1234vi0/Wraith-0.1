/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.config;

import lightning.product.V_3137_a;
import lightning.product.MinecraftClient;
import lightning.product.g_2336_b;
import lightning.product.k_4690_i;
import lightning.product.k_594_Q;
import net.optifine.util.BiomeUtils;

public class BiomeId {
    private final g_2336_b resourceLocation;
    private k_4690_i world;
    private k_594_Q biome;
    private static MinecraftClient minecraft = MinecraftClient.A_4115_X();

    private BiomeId(g_2336_b resourceLocation) {
        this.resourceLocation = resourceLocation;
        this.world = BiomeId.minecraft.Y_601_j;
        this.updateBiome();
    }

    private void updateBiome() {
        this.biome = null;
        V_3137_a<k_594_Q> registry = BiomeUtils.getBiomeRegistry(this.world);
        if (registry.R_4764_Y(this.resourceLocation)) {
            this.biome = registry.n_1700_B(this.resourceLocation);
        }
    }

    public k_594_Q getBiome() {
        if (this.world != BiomeId.minecraft.Y_601_j) {
            this.world = BiomeId.minecraft.Y_601_j;
            this.updateBiome();
        }
        return this.biome;
    }

    public g_2336_b getResourceLocation() {
        return this.resourceLocation;
    }

    public String toString() {
        return String.valueOf(this.resourceLocation);
    }

    public static BiomeId make(g_2336_b resourceLocation) {
        BiomeId biomeid = new BiomeId(resourceLocation);
        return biomeid.biome == null ? null : biomeid;
    }
}


