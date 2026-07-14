/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.cache;

import java.util.function.Consumer;
import mods.baritone.api.api.java.baritone.api.cache.IWorldData;

public interface IWorldProvider {
    public IWorldData getCurrentWorld();

    default public void ifWorldLoaded(Consumer<IWorldData> callback) {
        IWorldData currentWorld = this.getCurrentWorld();
        if (currentWorld != null) {
            callback.accept(currentWorld);
        }
    }
}

