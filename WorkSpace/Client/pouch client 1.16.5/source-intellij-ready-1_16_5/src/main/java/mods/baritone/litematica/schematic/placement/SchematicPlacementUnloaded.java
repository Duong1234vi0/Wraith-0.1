/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.baritone.litematica.schematic.placement;

import java.io.File;
import javax.annotation.Nullable;
import lightning.product.c_1514_x;

public class SchematicPlacementUnloaded {
    protected String name = "?";
    @Nullable
    protected File schematicFile;
    protected c_1514_x origin = c_1514_x.ZERO;

    public String getName() {
        return this.name;
    }

    @Nullable
    public File getSchematicFile() {
        return this.schematicFile;
    }

    public c_1514_x getOrigin() {
        return this.origin;
    }
}

