/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.command.defaults;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.command.Command;
import mods.baritone.api.api.java.baritone.api.command.argument.IArgConsumer;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalStrictDirection;

public class TunnelCommand
extends Command {
    public TunnelCommand(IBaritone baritone) {
        super(baritone, "tunnel");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        args.requireMax(3);
        if (args.hasExactly(3)) {
            boolean cont = true;
            int height = Integer.parseInt(args.getArgs().get(0).getValue());
            int width = Integer.parseInt(args.getArgs().get(1).getValue());
            int depth = Integer.parseInt(args.getArgs().get(2).getValue());
            if (width < 1 || height < 2 || depth < 1 || height > 255) {
                this.logDirect("Width and depth must at least be 1 block; Height must at least be 2 blocks, and cannot be greater than the build limit.");
                cont = false;
            }
            if (cont) {
                c_1514_x corner1;
                --height;
                b_257_Y enumFacing = this.ctx.player().o_2767_H();
                int addition = --width % 2 == 0 ? 0 : 1;
                c_1514_x corner2 = switch (enumFacing) {
                    case b_257_Y.u_1723_Y -> {
                        corner1 = new c_1514_x(this.ctx.playerFeet().x, this.ctx.playerFeet().y, this.ctx.playerFeet().z - width / 2);
                        yield new c_1514_x(this.ctx.playerFeet().x + depth, this.ctx.playerFeet().y + height, this.ctx.playerFeet().z + width / 2 + addition);
                    }
                    case b_257_Y.P_1922_E -> {
                        corner1 = new c_1514_x(this.ctx.playerFeet().x, this.ctx.playerFeet().y, this.ctx.playerFeet().z + width / 2 + addition);
                        yield new c_1514_x(this.ctx.playerFeet().x - depth, this.ctx.playerFeet().y + height, this.ctx.playerFeet().z - width / 2);
                    }
                    case b_257_Y.R_4764_Y -> {
                        corner1 = new c_1514_x(this.ctx.playerFeet().x - width / 2, this.ctx.playerFeet().y, this.ctx.playerFeet().z);
                        yield new c_1514_x(this.ctx.playerFeet().x + width / 2 + addition, this.ctx.playerFeet().y + height, this.ctx.playerFeet().z - depth);
                    }
                    case b_257_Y.G_564_y -> {
                        corner1 = new c_1514_x(this.ctx.playerFeet().x + width / 2 + addition, this.ctx.playerFeet().y, this.ctx.playerFeet().z);
                        yield new c_1514_x(this.ctx.playerFeet().x - width / 2, this.ctx.playerFeet().y + height, this.ctx.playerFeet().z + depth);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + String.valueOf(enumFacing));
                };
                this.logDirect(String.format("Creating a tunnel %s block(s) high, %s block(s) wide, and %s block(s) deep", height + 1, width + 1, depth));
                this.baritone.getBuilderProcess().clearArea(corner1, corner2);
            }
        } else {
            GoalStrictDirection goal = new GoalStrictDirection(this.ctx.playerFeet(), this.ctx.player().o_2767_H());
            this.baritone.getCustomGoalProcess().setGoalAndPath(goal);
            this.logDirect(String.format("Goal: %s", ((Object)goal).toString()));
        }
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) {
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "Set a goal to tunnel in your current direction";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("The tunnel command sets a goal that tells Baritone to mine completely straight in the direction that you're facing.", "", "Usage:", "> tunnel - No arguments, mines in a 1x2 radius.", "> tunnel <height> <width> <depth> - Tunnels in a user defined height, width and depth.");
    }
}

