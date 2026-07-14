/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.Long2ObjectMap$Entry
 *  it.unimi.dsi.fastutil.objects.ObjectIterator
 */
package mods.baritone.utils.accessor;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import mods.baritone.utils.accessor.IChunkArray;

public interface ISodiumChunkArray
extends IChunkArray {
    public ObjectIterator<Long2ObjectMap.Entry<Object>> callIterator();
}

