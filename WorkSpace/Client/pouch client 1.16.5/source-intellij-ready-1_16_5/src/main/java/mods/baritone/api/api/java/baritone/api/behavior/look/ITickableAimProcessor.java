/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.behavior.look;

import mods.baritone.api.api.java.baritone.api.behavior.look.IAimProcessor;
import mods.baritone.api.api.java.baritone.api.utils.Rotation;

public interface ITickableAimProcessor
extends IAimProcessor {
    public void tick();

    public void advance(int var1);

    public Rotation nextRotation(Rotation var1);
}

