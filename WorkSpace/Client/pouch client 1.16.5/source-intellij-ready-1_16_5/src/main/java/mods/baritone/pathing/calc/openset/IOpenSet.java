/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.pathing.calc.openset;

import mods.baritone.pathing.calc.PathNode;

public interface IOpenSet {
    public void insert(PathNode var1);

    public boolean isEmpty();

    public PathNode removeLowest();

    public void update(PathNode var1);
}

