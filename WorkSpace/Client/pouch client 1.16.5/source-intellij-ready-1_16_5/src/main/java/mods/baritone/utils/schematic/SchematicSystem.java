/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.utils.schematic;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;
import mods.baritone.api.api.java.baritone.api.command.registry.Registry;
import mods.baritone.api.api.java.baritone.api.schematic.ISchematicSystem;
import mods.baritone.api.api.java.baritone.api.schematic.format.ISchematicFormat;
import mods.baritone.utils.schematic.format.DefaultSchematicFormats;

public enum SchematicSystem implements ISchematicSystem
{
    INSTANCE;

    private final Registry<ISchematicFormat> registry = new Registry();

    private SchematicSystem() {
        Arrays.stream(DefaultSchematicFormats.values()).forEach(this.registry::register);
    }

    @Override
    public Registry<ISchematicFormat> getRegistry() {
        return this.registry;
    }

    @Override
    public Optional<ISchematicFormat> getByFile(File file) {
        return this.registry.stream().filter(format -> format.isFileType(file)).findFirst();
    }
}

