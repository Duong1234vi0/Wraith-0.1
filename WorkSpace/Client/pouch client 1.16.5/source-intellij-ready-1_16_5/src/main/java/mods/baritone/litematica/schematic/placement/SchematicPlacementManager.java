/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.litematica.schematic.placement;

import java.util.ArrayList;
import java.util.List;
import mods.baritone.litematica.schematic.placement.SchematicPlacement;

public class SchematicPlacementManager {
    private final List<SchematicPlacement> schematicPlacements = new ArrayList<SchematicPlacement>();

    public List<SchematicPlacement> getAllSchematicsPlacements() {
        return this.schematicPlacements;
    }
}

