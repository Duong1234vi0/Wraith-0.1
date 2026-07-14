/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.command.defaults;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lightning.product.D_4024_W;
import lightning.product.T_2915_h;
import lightning.product.U_2871_b;
import lightning.product.V_3137_a;
import lightning.product.c_973_a;
import lightning.product.i_2909_p;
import lightning.product.x_282_a;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.command.Command;
import mods.baritone.api.api.java.baritone.api.command.IBaritoneChatControl;
import mods.baritone.api.api.java.baritone.api.command.argument.IArgConsumer;
import mods.baritone.api.api.java.baritone.api.command.datatypes.BlockById;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.command.helpers.TabCompleteHelper;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;
import mods.baritone.cache.CachedChunk;

public class FindCommand
extends Command {
    public FindCommand(IBaritone baritone) {
        super(baritone, "find");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        args.requireMin(1);
        ArrayList<T_2915_h> toFind = new ArrayList<T_2915_h>();
        while (args.hasAny()) {
            toFind.add((T_2915_h)args.getDatatypeFor(BlockById.INSTANCE));
        }
        BetterBlockPos origin = this.ctx.playerFeet();
        x_282_a[] components = (x_282_a[])toFind.stream().flatMap(block -> this.ctx.worldData().getCachedWorld().getLocationsOf(V_3137_a.q_4610_l.J_1907_R((T_2915_h)block).J_1907_R(), Integer.MAX_VALUE, origin.x, origin.y, 4).stream()).map(BetterBlockPos::new).map(this::positionToComponent).toArray(x_282_a[]::new);
        if (components.length > 0) {
            Arrays.asList(components).forEach(xva$0 -> this.logDirect((x_282_a)xva$0));
        } else {
            this.logDirect("No positions known, are you sure the blocks are cached?");
        }
    }

    private x_282_a positionToComponent(BetterBlockPos pos) {
        String positionText = String.format("%s %s %s", pos.x, pos.y, pos.z);
        String command = String.format("%sgoal %s", IBaritoneChatControl.FORCE_COMMAND_PREFIX, positionText);
        U_2871_b baseComponent = new U_2871_b(pos.toString());
        U_2871_b hoverComponent = new U_2871_b("Click to set goal to this position");
        baseComponent.n_1700_B(baseComponent.n_1700_B().n_1700_B(D_4024_W.w_1484_f).n_1700_B(positionText).n_1700_B(new i_2909_p(i_2909_p.n_1700_B.R_4764_Y, command)).n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, hoverComponent)));
        return baseComponent;
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        return new TabCompleteHelper().append(CachedChunk.BLOCKS_TO_KEEP_TRACK_OF.stream().map(V_3137_a.q_4610_l::J_1907_R).map(Object::toString)).filterPrefixNamespaced(args.getString()).sortAlphabetically().stream();
    }

    @Override
    public String getShortDesc() {
        return "Find positions of a certain block";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("The find command searches through Baritone's cache and attempts to find the location of the block.", "Tab completion will suggest only cached blocks and uncached blocks can not be found.", "", "Usage:", "> find <block> [...] - Try finding the listed blocks");
    }
}

