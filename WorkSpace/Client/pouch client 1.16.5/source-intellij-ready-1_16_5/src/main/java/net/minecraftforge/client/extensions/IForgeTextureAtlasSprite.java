/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package net.minecraftforge.client.extensions;

import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.function.Function;
import lightning.product.B_3871_I;
import lightning.product.ResourceManager;
import lightning.product.g_2336_b;

public interface IForgeTextureAtlasSprite {
    default public boolean hasCustomLoader(ResourceManager manager, g_2336_b location) {
        return false;
    }

    default public boolean load(ResourceManager manager, g_2336_b location, Function<g_2336_b, B_3871_I> textureGetter) {
        return true;
    }

    default public Collection<g_2336_b> getDependencies() {
        return ImmutableList.of();
    }
}


