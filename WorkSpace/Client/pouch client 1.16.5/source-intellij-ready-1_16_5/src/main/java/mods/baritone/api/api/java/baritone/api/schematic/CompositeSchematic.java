/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.schematic;

import java.util.ArrayList;
import java.util.List;
import lightning.product.K_4074_S;
import mods.baritone.api.api.java.baritone.api.schematic.AbstractSchematic;
import mods.baritone.api.api.java.baritone.api.schematic.CompositeSchematicEntry;
import mods.baritone.api.api.java.baritone.api.schematic.ISchematic;

public class CompositeSchematic
extends AbstractSchematic {
    private final List<CompositeSchematicEntry> schematics = new ArrayList<CompositeSchematicEntry>();
    private CompositeSchematicEntry[] schematicArr;

    private void recalcArr() {
        for (CompositeSchematicEntry entry : this.schematicArr = this.schematics.toArray(new CompositeSchematicEntry[0])) {
            this.x = Math.max(this.x, entry.x + entry.schematic.widthX());
            this.y = Math.max(this.y, entry.y + entry.schematic.heightY());
            this.z = Math.max(this.z, entry.z + entry.schematic.lengthZ());
        }
    }

    public CompositeSchematic(int x, int y, int z) {
        super(x, y, z);
        this.recalcArr();
    }

    public void put(ISchematic extra, int x, int y, int z) {
        this.schematics.add(new CompositeSchematicEntry(extra, x, y, z));
        this.recalcArr();
    }

    private CompositeSchematicEntry getSchematic(int x, int y, int z, K_4074_S currentState) {
        for (CompositeSchematicEntry entry : this.schematicArr) {
            if (x < entry.x || y < entry.y || z < entry.z || !entry.schematic.inSchematic(x - entry.x, y - entry.y, z - entry.z, currentState)) continue;
            return entry;
        }
        return null;
    }

    @Override
    public boolean inSchematic(int x, int y, int z, K_4074_S currentState) {
        CompositeSchematicEntry entry = this.getSchematic(x, y, z, currentState);
        return entry != null && entry.schematic.inSchematic(x - entry.x, y - entry.y, z - entry.z, currentState);
    }

    @Override
    public K_4074_S desiredState(int x, int y, int z, K_4074_S current, List<K_4074_S> approxPlaceable) {
        CompositeSchematicEntry entry = this.getSchematic(x, y, z, current);
        if (entry == null) {
            throw new IllegalStateException("couldn't find schematic for this position");
        }
        return entry.schematic.desiredState(x - entry.x, y - entry.y, z - entry.z, current, approxPlaceable);
    }

    @Override
    public void reset() {
        for (CompositeSchematicEntry entry : this.schematicArr) {
            entry.schematic.reset();
        }
    }
}

