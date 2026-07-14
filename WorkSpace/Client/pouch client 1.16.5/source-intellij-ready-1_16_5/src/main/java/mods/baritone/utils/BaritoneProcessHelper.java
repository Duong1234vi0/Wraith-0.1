/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.utils;

import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.process.IBaritoneProcess;
import mods.baritone.api.api.java.baritone.api.utils.Helper;
import mods.baritone.api.api.java.baritone.api.utils.IPlayerContext;

public abstract class BaritoneProcessHelper
implements IBaritoneProcess,
Helper {
    protected final Baritone baritone;
    protected final IPlayerContext ctx;

    public BaritoneProcessHelper(Baritone baritone) {
        this.baritone = baritone;
        this.ctx = baritone.getPlayerContext();
    }

    @Override
    public boolean isTemporary() {
        return false;
    }
}

