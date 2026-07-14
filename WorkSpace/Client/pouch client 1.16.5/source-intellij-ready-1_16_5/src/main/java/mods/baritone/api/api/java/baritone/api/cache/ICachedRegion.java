/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.cache;

import mods.baritone.api.api.java.baritone.api.cache.IBlockTypeAccess;

public interface ICachedRegion
extends IBlockTypeAccess {
    public boolean isCached(int var1, int var2);

    public int getX();

    public int getZ();
}

