/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.FilenameUtils
 */
package mods.baritone.command.defaults;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lightning.product.z_3539_x;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.command.Command;
import mods.baritone.api.api.java.baritone.api.command.argument.IArgConsumer;
import mods.baritone.api.api.java.baritone.api.command.datatypes.RelativeBlockPos;
import mods.baritone.api.api.java.baritone.api.command.datatypes.RelativeFile;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandInvalidStateException;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;
import org.apache.commons.io.FilenameUtils;

public class BuildCommand
extends Command {
    private final File schematicsDir;

    public BuildCommand(IBaritone baritone) {
        super(baritone, "build");
        this.schematicsDir = new File(baritone.getPlayerContext().minecraft().M_182_A, "schematics");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        BetterBlockPos buildOrigin;
        File file = ((File)args.getDatatypePost(RelativeFile.INSTANCE, this.schematicsDir)).getAbsoluteFile();
        if (FilenameUtils.getExtension((String)file.getAbsolutePath()).isEmpty()) {
            file = new File(file.getAbsolutePath() + "." + (String)Baritone.settings().schematicFallbackExtension.value);
        }
        BetterBlockPos origin = this.ctx.playerFeet();
        if (args.hasAny()) {
            args.requireMax(3);
            buildOrigin = (BetterBlockPos)args.getDatatypePost(RelativeBlockPos.INSTANCE, origin);
        } else {
            args.requireMax(0);
            buildOrigin = origin;
        }
        boolean success = this.baritone.getBuilderProcess().build(file.getName(), file, (z_3539_x)buildOrigin);
        if (!success) {
            throw new CommandInvalidStateException("Couldn't load the schematic. Make sure to use the FULL file name, including the extension (e.g. blah.schematic).");
        }
        this.logDirect(String.format("Successfully loaded schematic for building\nOrigin: %s", buildOrigin));
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        if (args.hasExactlyOne()) {
            return RelativeFile.tabComplete(args, this.schematicsDir);
        }
        if (args.has(2)) {
            args.get();
            return args.tabCompleteDatatype(RelativeBlockPos.INSTANCE);
        }
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "Build a schematic";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("Build a schematic from a file.", "", "Usage:", "> build <filename> - Loads and builds '<filename>.schematic'", "> build <filename> <x> <y> <z> - Custom position");
    }
}

