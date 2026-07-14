/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.behavior;

import mods.baritone.api.api.java.baritone.api.behavior.IBehavior;
import mods.baritone.api.api.java.baritone.api.behavior.look.IAimProcessor;
import mods.baritone.api.api.java.baritone.api.utils.Rotation;

public interface ILookBehavior
extends IBehavior {
    public void updateTarget(Rotation var1, boolean var2);

    public IAimProcessor getAimProcessor();
}

