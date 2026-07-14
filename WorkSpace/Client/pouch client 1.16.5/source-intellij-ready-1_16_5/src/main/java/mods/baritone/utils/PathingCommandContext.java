/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.utils;

import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.api.api.java.baritone.api.process.PathingCommand;
import mods.baritone.api.api.java.baritone.api.process.PathingCommandType;
import mods.baritone.pathing.movement.CalculationContext;

public class PathingCommandContext
extends PathingCommand {
    public final CalculationContext desiredCalcContext;

    public PathingCommandContext(Goal goal, PathingCommandType commandType, CalculationContext context) {
        super(goal, commandType);
        this.desiredCalcContext = context;
    }
}

