/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.pathing.calc;

import java.util.Optional;
import mods.baritone.api.api.java.baritone.api.process.IBaritoneProcess;
import mods.baritone.api.api.java.baritone.api.process.PathingCommand;

public interface IPathingControlManager {
    public void registerProcess(IBaritoneProcess var1);

    public Optional<IBaritoneProcess> mostRecentInControl();

    public Optional<PathingCommand> mostRecentCommand();
}

