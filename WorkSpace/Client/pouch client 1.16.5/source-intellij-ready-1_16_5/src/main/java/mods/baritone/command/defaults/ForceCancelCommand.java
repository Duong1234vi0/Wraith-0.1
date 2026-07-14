/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.command.defaults;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.behavior.IPathingBehavior;
import mods.baritone.api.api.java.baritone.api.command.Command;
import mods.baritone.api.api.java.baritone.api.command.argument.IArgConsumer;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;

public class ForceCancelCommand
extends Command {
    public ForceCancelCommand(IBaritone baritone) {
        super(baritone, "forcecancel");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        args.requireMax(0);
        IPathingBehavior pathingBehavior = this.baritone.getPathingBehavior();
        pathingBehavior.cancelEverything();
        pathingBehavior.forceCancel();
        this.logDirect("ok force canceled");
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) {
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "Force cancel";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("Like cancel, but more forceful.", "", "Usage:", "> forcecancel");
    }
}

