/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.litematica.data;

import mods.baritone.litematica.schematic.placement.SchematicPlacementManager;

public class DataManager {
    public static final DataManager INSTANCE = new DataManager();
    private final SchematicPlacementManager schematicPlacementManager = new SchematicPlacementManager();

    private static DataManager getInstance() {
        return INSTANCE;
    }

    public static SchematicPlacementManager getSchematicPlacementManager() {
        return DataManager.getInstance().schematicPlacementManager;
    }
}

