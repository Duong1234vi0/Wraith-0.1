/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  net.fabricmc.fabric.impl.tag.convention.v2.TagRegistration
 *  net.minecraft.world.item.Item
 *  net.minecraft.registry.RegistryKey
 *  net.minecraft.registry.RegistryKeys
 *  net.minecraft.registry.tag.TagKey
 *  net.minecraft.resources.Identifier
 */
package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.scripting.script_wrappers;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.annotation.Safe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.Identifier;

public class JSTags {
    @Safe
    public TagKey<Item> getVanillaTag(String id) {
        return TagKey.create(Registries.ITEM, Identifier.withDefaultNamespace(id));
    }

    @Safe
    public TagKey<Item> getFabricTag(String id) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", id));
    }
}
