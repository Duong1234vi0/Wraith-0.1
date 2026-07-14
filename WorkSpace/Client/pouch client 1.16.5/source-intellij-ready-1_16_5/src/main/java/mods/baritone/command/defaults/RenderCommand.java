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
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;

public class RenderCommand
extends Command {
    public RenderCommand(IBaritone baritone) {
        super(baritone, "render");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        args.requireMax(0);
        BetterBlockPos origin = this.ctx.playerFeet();
        int renderDistance = (this.ctx.minecraft().P_4830_p.J_1907_R + 1) * 16;
        this.ctx.minecraft().u_1723_Y.n_1700_B(origin.x - renderDistance, 0, origin.z - renderDistance, origin.x + renderDistance, 255, origin.z + renderDistance);
        this.logDirect("Done");
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) {
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "Fix glitched chunks";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("The render command fixes glitched chunk rendering without having to reload all of them.", "", "Usage:", "> render");
    }
}

