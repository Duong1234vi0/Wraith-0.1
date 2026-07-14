/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.command.defaults;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.cache.IWaypoint;
import mods.baritone.api.api.java.baritone.api.command.Command;
import mods.baritone.api.api.java.baritone.api.command.argument.IArgConsumer;
import mods.baritone.api.api.java.baritone.api.command.datatypes.ForWaypoints;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandInvalidStateException;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;

public class FarmCommand
extends Command {
    public FarmCommand(IBaritone baritone) {
        super(baritone, "farm");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        args.requireMax(2);
        int range = 0;
        BetterBlockPos origin = null;
        if (args.has(1)) {
            range = args.getAs(Integer.class);
        }
        if (args.has(1)) {
            IWaypoint[] waypoints = (IWaypoint[])args.getDatatypeFor(ForWaypoints.INSTANCE);
            IWaypoint waypoint = null;
            switch (waypoints.length) {
                case 0: {
                    throw new CommandInvalidStateException("No waypoints found");
                }
                case 1: {
                    waypoint = waypoints[0];
                    break;
                }
                default: {
                    throw new CommandInvalidStateException("Multiple waypoints were found");
                }
            }
            origin = waypoint.getLocation();
        }
        this.baritone.getFarmProcess().farm(range, origin);
        this.logDirect("Farming");
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) {
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "Farm nearby crops";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("The farm command starts farming nearby plants. It harvests mature crops and plants new ones.", "", "Usage:", "> farm - farms every crop it can find.", "> farm <range> - farm crops within range from the starting position.", "> farm <range> <waypoint> - farm crops within range from waypoint.");
    }
}

