/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.cache;

import java.util.ArrayList;
import lightning.product.H_1748_a;
import lightning.product.c_1514_x;
import mods.baritone.api.api.java.baritone.api.cache.ICachedRegion;

public interface ICachedWorld {
    public ICachedRegion getRegion(int var1, int var2);

    public void queueForPacking(H_1748_a var1);

    public boolean isCached(int var1, int var2);

    public ArrayList<c_1514_x> getLocationsOf(String var1, int var2, int var3, int var4, int var5);

    public void reloadAllFromDisk();

    public void save();
}

