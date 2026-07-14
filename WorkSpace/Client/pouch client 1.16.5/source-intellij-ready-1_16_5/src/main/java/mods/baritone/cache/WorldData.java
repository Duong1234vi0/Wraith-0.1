/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.cache;

import java.nio.file.Path;
import lightning.product.b_4507_u;
import lightning.product.f_2392_k;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.cache.ICachedWorld;
import mods.baritone.api.api.java.baritone.api.cache.IWaypointCollection;
import mods.baritone.api.api.java.baritone.api.cache.IWorldData;
import mods.baritone.cache.CachedWorld;
import mods.baritone.cache.WaypointCollection;

public class WorldData
implements IWorldData {
    public final CachedWorld cache;
    private final WaypointCollection waypoints;
    public final Path directory;
    public final f_2392_k<b_4507_u> dimension;

    WorldData(Path directory, f_2392_k<b_4507_u> dimension) {
        this.directory = directory;
        this.cache = new CachedWorld(directory.resolve("cache"), dimension);
        this.waypoints = new WaypointCollection(directory.resolve("waypoints"));
        this.dimension = dimension;
    }

    public void onClose() {
        Baritone.getExecutor().execute(() -> {
            System.out.println("Started saving the world in a new thread");
            this.cache.save();
        });
    }

    @Override
    public ICachedWorld getCachedWorld() {
        return this.cache;
    }

    @Override
    public IWaypointCollection getWaypoints() {
        return this.waypoints;
    }
}

