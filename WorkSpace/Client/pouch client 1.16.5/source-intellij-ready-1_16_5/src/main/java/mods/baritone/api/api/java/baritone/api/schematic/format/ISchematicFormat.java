/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.schematic.format;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import mods.baritone.api.api.java.baritone.api.schematic.IStaticSchematic;

public interface ISchematicFormat {
    public IStaticSchematic parse(InputStream var1) throws IOException;

    public boolean isFileType(File var1);
}

