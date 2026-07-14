/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.schematic;

import java.io.File;
import java.util.Optional;
import mods.baritone.api.api.java.baritone.api.command.registry.Registry;
import mods.baritone.api.api.java.baritone.api.schematic.format.ISchematicFormat;

public interface ISchematicSystem {
    public Registry<ISchematicFormat> getRegistry();

    public Optional<ISchematicFormat> getByFile(File var1);
}

