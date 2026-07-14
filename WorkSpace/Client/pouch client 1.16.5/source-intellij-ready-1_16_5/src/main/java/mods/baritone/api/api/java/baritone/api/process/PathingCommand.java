/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.process;

import java.util.Objects;
import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.api.api.java.baritone.api.process.PathingCommandType;

public class PathingCommand {
    public final Goal goal;
    public final PathingCommandType commandType;

    public PathingCommand(Goal goal, PathingCommandType commandType) {
        Objects.requireNonNull(commandType);
        this.goal = goal;
        this.commandType = commandType;
    }

    public String toString() {
        return String.valueOf((Object)this.commandType) + " " + String.valueOf(this.goal);
    }
}

