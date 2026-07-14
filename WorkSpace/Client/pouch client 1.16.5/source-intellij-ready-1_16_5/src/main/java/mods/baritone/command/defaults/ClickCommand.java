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

public class ClickCommand
extends Command {
    public ClickCommand(IBaritone baritone) {
        super(baritone, "click");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        args.requireMax(0);
        this.baritone.openClick();
        this.logDirect("aight dude");
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) {
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "Open click";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("Opens click dude", "", "Usage:", "> click");
    }
}

