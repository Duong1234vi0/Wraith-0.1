/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.utils.schematic.schematica;

import java.util.List;
import lightning.product.K_4074_S;
import lightning.product.c_1514_x;
import mods.baritone.api.api.java.baritone.api.schematic.IStaticSchematic;
import mods.baritone.lunatrius.schematica.client.world.SchematicWorld;

public final class SchematicAdapter
implements IStaticSchematic {
    private final SchematicWorld schematic;

    public SchematicAdapter(SchematicWorld schematicWorld) {
        this.schematic = schematicWorld;
    }

    @Override
    public K_4074_S desiredState(int x, int y, int z, K_4074_S current, List<K_4074_S> approxPlaceable) {
        return this.getDirect(x, y, z);
    }

    @Override
    public K_4074_S getDirect(int x, int y, int z) {
        return this.schematic.getSchematic().getBlockState(new c_1514_x(x, y, z));
    }

    @Override
    public int widthX() {
        return this.schematic.getSchematic().getWidth();
    }

    @Override
    public int heightY() {
        return this.schematic.getSchematic().getHeight();
    }

    @Override
    public int lengthZ() {
        return this.schematic.getSchematic().getLength();
    }
}

