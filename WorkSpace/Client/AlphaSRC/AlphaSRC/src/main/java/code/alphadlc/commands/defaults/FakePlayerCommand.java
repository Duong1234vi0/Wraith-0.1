/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 */
package code.alphadlc.commands.defaults;

import code.alphadlc.utils.client.FakePlayerManager;
import code.alphadlc.utils.client.managers.api.command.Command;
import code.alphadlc.utils.client.managers.api.command.argument.IArgConsumer;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;
import code.alphadlc.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import net.minecraft.class_124;

public class FakePlayerCommand
extends Command {
    public FakePlayerCommand() {
        super("fakeplayer", "fakeplyer");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        String action;
        switch (action = args.hasAny() ? args.getString().toLowerCase(Locale.US) : "add") {
            case "add": 
            case "spawn": {
                String name;
                String string = name = args.hasAny() ? args.getString() : "FakePlayer";
                if (FakePlayerManager.add(name)) {
                    this.logDirect("FakePlayer spawned. He is invulnerable.", class_124.field_1060);
                    break;
                }
                this.logDirect("Join a world first.", class_124.field_1061);
                break;
            }
            case "del": 
            case "delete": 
            case "remove": {
                if (FakePlayerManager.remove()) {
                    this.logDirect("FakePlayer removed.", class_124.field_1060);
                    break;
                }
                this.logDirect("FakePlayer is not spawned.", class_124.field_1054);
                break;
            }
            default: {
                this.logDirect("Usage: ." + label + " add [name]", class_124.field_1080);
                this.logDirect("Usage: ." + label + " del", class_124.field_1080);
            }
        }
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        if (args.hasAny()) {
            String arg = args.getString();
            if (!args.hasAny()) {
                return new TabCompleteHelper().sortAlphabetically().prepend("add", "del").filterPrefix(arg).stream();
            }
        }
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "Spawn a client-side fake player target";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("Spawns a client-side invulnerable fake player for training.", "", "Usage:", "> fakeplyer add [name]", "> fakeplyer del");
    }
}
