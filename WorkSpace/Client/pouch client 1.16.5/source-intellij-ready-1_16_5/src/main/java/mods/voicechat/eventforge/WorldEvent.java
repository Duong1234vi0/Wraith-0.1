/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.eventforge;

import lightning.product.LevelAccessor;
import lightning.product.x_607_J;

public class WorldEvent
implements x_607_J {
    private final LevelAccessor world;

    public WorldEvent(LevelAccessor world) {
        this.world = world;
    }

    public LevelAccessor getWorld() {
        return this.world;
    }

    public static class Unload
    extends WorldEvent {
        public Unload(LevelAccessor world) {
            super(world);
        }
    }

    public static class Load
    extends WorldEvent {
        public Load(LevelAccessor world) {
            super(world);
        }
    }
}


