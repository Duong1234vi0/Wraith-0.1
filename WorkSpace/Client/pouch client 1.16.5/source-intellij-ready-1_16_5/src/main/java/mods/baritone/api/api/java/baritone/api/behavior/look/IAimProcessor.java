/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.behavior.look;

import mods.baritone.api.api.java.baritone.api.behavior.look.ITickableAimProcessor;
import mods.baritone.api.api.java.baritone.api.utils.Rotation;

public interface IAimProcessor {
    public Rotation peekRotation(Rotation var1);

    public ITickableAimProcessor fork();
}

