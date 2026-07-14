/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.command.defaults;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lightning.product.AirBlock;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.command.Command;
import mods.baritone.api.api.java.baritone.api.command.argument.IArgConsumer;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalBlock;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;

public class SurfaceCommand
extends Command {
    protected SurfaceCommand(IBaritone baritone) {
        super(baritone, "surface", "top");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        int startingYPos;
        BetterBlockPos playerPos = this.ctx.playerFeet();
        int surfaceLevel = this.ctx.world().d_2461_k();
        int worldHeight = this.ctx.world().c_3005_b();
        if (playerPos.getY() > surfaceLevel && this.ctx.world().getBlockState(playerPos.up()).J_1907_R() instanceof AirBlock) {
            this.logDirect("Already at surface");
            return;
        }
        for (int currentIteratedY = startingYPos = Math.max(playerPos.getY(), surfaceLevel); currentIteratedY < worldHeight; ++currentIteratedY) {
            BetterBlockPos newPos = new BetterBlockPos(playerPos.getX(), currentIteratedY, playerPos.getZ());
            if (this.ctx.world().getBlockState(newPos).J_1907_R() instanceof AirBlock || newPos.getY() <= playerPos.getY()) continue;
            GoalBlock goal = new GoalBlock(newPos.up());
            this.logDirect(String.format("Going to: %s", ((Object)goal).toString()));
            this.baritone.getCustomGoalProcess().setGoalAndPath(goal);
            return;
        }
        this.logDirect("No higher location found");
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) {
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "Used to get out of caves, mines, ...";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("The surface/top command tells Baritone to head towards the closest surface-like area.", "", "This can be the surface or the highest available air space, depending on circumstances.", "", "Usage:", "> surface - Used to get out of caves, mines, ...", "> top - Used to get out of caves, mines, ...");
    }
}


