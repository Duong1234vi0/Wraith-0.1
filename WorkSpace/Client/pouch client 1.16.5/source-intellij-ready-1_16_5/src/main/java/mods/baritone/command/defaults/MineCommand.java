/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.command.defaults;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.command.Command;
import mods.baritone.api.api.java.baritone.api.command.argument.IArgConsumer;
import mods.baritone.api.api.java.baritone.api.command.datatypes.ForBlockOptionalMeta;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.utils.BlockOptionalMeta;

public class MineCommand
extends Command {
    public MineCommand(IBaritone baritone) {
        super(baritone, "mine");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        int quantity = args.getAsOrDefault(Integer.class, 0);
        args.requireMin(1);
        ArrayList<BlockOptionalMeta> boms = new ArrayList<BlockOptionalMeta>();
        while (args.hasAny()) {
            boms.add((BlockOptionalMeta)args.getDatatypeFor(ForBlockOptionalMeta.INSTANCE));
        }
        BaritoneAPI.getProvider().getWorldScanner().repack(this.ctx);
        this.logDirect(String.format("Mining %s", ((Object)boms).toString()));
        this.baritone.getMineProcess().mine(quantity, boms.toArray(new BlockOptionalMeta[0]));
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        args.getAsOrDefault(Integer.class, 0);
        while (args.has(2)) {
            args.getDatatypeFor(ForBlockOptionalMeta.INSTANCE);
        }
        return args.tabCompleteDatatype(ForBlockOptionalMeta.INSTANCE);
    }

    @Override
    public String getShortDesc() {
        return "Mine some blocks";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("The mine command allows you to tell Baritone to search for and mine individual blocks.", "", "The specified blocks can be ores, or any other block.", "", "Also see the legitMine settings (see #set l legitMine).", "", "Usage:", "> mine diamond_ore - Mines all diamonds it can find.");
    }
}

