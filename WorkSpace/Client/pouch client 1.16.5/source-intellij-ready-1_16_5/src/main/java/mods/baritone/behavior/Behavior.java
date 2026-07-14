/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.behavior;

import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.behavior.IBehavior;
import mods.baritone.api.api.java.baritone.api.utils.IPlayerContext;

public class Behavior
implements IBehavior {
    public final Baritone baritone;
    public final IPlayerContext ctx;

    protected Behavior(Baritone baritone) {
        this.baritone = baritone;
        this.ctx = baritone.getPlayerContext();
    }
}

