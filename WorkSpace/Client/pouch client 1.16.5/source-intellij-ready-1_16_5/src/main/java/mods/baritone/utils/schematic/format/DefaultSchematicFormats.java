/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.FilenameUtils
 */
package mods.baritone.utils.schematic.format;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import lightning.product.U_2912_j;
import lightning.product.r_1827_u;
import mods.baritone.api.api.java.baritone.api.schematic.IStaticSchematic;
import mods.baritone.api.api.java.baritone.api.schematic.format.ISchematicFormat;
import mods.baritone.utils.schematic.format.defaults.LitematicaSchematic;
import mods.baritone.utils.schematic.format.defaults.MCEditSchematic;
import mods.baritone.utils.schematic.format.defaults.SpongeSchematic;
import org.apache.commons.io.FilenameUtils;

public enum DefaultSchematicFormats implements ISchematicFormat
{
    MCEDIT("schematic"){

        @Override
        public IStaticSchematic parse(InputStream input) throws IOException {
            return new MCEditSchematic(r_1827_u.n_1700_B(input));
        }
    }
    ,
    SPONGE("schem"){

        @Override
        public IStaticSchematic parse(InputStream input) throws IOException {
            U_2912_j nbt = r_1827_u.n_1700_B(input);
            int version = nbt.w_1484_f("Version");
            switch (version) {
                case 1: 
                case 2: {
                    return new SpongeSchematic(nbt);
                }
            }
            throw new UnsupportedOperationException("Unsupported Version of a Sponge Schematic");
        }
    }
    ,
    LITEMATICA("litematic"){

        @Override
        public IStaticSchematic parse(InputStream input) throws IOException {
            U_2912_j nbt = r_1827_u.n_1700_B(input);
            int version = nbt.w_1484_f("Version");
            switch (version) {
                case 4: {
                    throw new UnsupportedOperationException("This litematic Version is too old.");
                }
                case 5: {
                    return new LitematicaSchematic(nbt, false);
                }
                case 6: {
                    throw new UnsupportedOperationException("This litematic Version is too new.");
                }
            }
            throw new UnsupportedOperationException("Unsuported Version of a Litematica Schematic");
        }
    };

    private final String extension;

    private DefaultSchematicFormats(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean isFileType(File file) {
        return this.extension.equalsIgnoreCase(FilenameUtils.getExtension((String)file.getAbsolutePath()));
    }
}

