/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.process;

import java.nio.file.Path;
import mods.baritone.api.api.java.baritone.api.process.IBaritoneProcess;

public interface IExploreProcess
extends IBaritoneProcess {
    public void explore(int var1, int var2);

    public void applyJsonFilter(Path var1, boolean var2) throws Exception;
}

