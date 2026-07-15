/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  net.minecraft.resources.Identifier
 */
package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.patricles.scripting;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.annotation.Safe;
import net.minecraft.resources.Identifier;

public class Texture {
    @Safe
    public Identifier of(String namespace, String path) {
        return Identifier.fromNamespaceAndPath(namespace, path);
    }
}
