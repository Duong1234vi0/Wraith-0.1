/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.utils.schematic;

import java.util.List;
import lightning.product.K_4074_S;
import mods.baritone.api.api.java.baritone.api.schematic.AbstractSchematic;
import mods.baritone.api.api.java.baritone.api.schematic.IStaticSchematic;

public class StaticSchematic
extends AbstractSchematic
implements IStaticSchematic {
    protected K_4074_S[][][] states;

    @Override
    public K_4074_S desiredState(int x, int y, int z, K_4074_S current, List<K_4074_S> approxPlaceable) {
        return this.states[x][z][y];
    }

    @Override
    public K_4074_S getDirect(int x, int y, int z) {
        return this.states[x][z][y];
    }

    @Override
    public K_4074_S[] getColumn(int x, int z) {
        return this.states[x][z];
    }
}

