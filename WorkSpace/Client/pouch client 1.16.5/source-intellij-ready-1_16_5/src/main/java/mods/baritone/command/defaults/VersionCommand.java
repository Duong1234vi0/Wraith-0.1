/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.command.defaults;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.command.Command;
import mods.baritone.api.api.java.baritone.api.command.argument.IArgConsumer;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandInvalidStateException;

public class VersionCommand
extends Command {
    public VersionCommand(IBaritone baritone) {
        super(baritone, "version");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        args.requireMax(0);
        String version = this.getClass().getPackage().getImplementationVersion();
        if (version == null) {
            throw new CommandInvalidStateException("Null version (this is normal in a dev environment)");
        }
        this.logDirect(String.format("You are running Baritone v%s", version));
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) {
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "View the Baritone version";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("The version command prints the version of Baritone you're currently running.", "", "Usage:", "> version - View version information, if present");
    }
}

