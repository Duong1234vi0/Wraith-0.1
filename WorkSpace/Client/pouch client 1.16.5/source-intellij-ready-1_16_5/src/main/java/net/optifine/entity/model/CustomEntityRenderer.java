/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.g_2336_b;
import net.optifine.entity.model.CustomModelRenderer;

public class CustomEntityRenderer {
    private String name = null;
    private String basePath = null;
    private g_2336_b textureLocation = null;
    private CustomModelRenderer[] customModelRenderers = null;
    private float shadowSize = 0.0f;

    public CustomEntityRenderer(String name, String basePath, g_2336_b textureLocation, CustomModelRenderer[] customModelRenderers, float shadowSize) {
        this.name = name;
        this.basePath = basePath;
        this.textureLocation = textureLocation;
        this.customModelRenderers = customModelRenderers;
        this.shadowSize = shadowSize;
    }

    public String getName() {
        return this.name;
    }

    public String getBasePath() {
        return this.basePath;
    }

    public g_2336_b getTextureLocation() {
        return this.textureLocation;
    }

    public CustomModelRenderer[] getCustomModelRenderers() {
        return this.customModelRenderers;
    }

    public float getShadowSize() {
        return this.shadowSize;
    }
}

