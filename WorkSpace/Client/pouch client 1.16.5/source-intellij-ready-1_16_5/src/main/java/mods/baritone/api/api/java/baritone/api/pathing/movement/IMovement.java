/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.pathing.movement;

import lightning.product.c_1514_x;
import mods.baritone.api.api.java.baritone.api.pathing.movement.MovementStatus;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;

public interface IMovement {
    public double getCost();

    public MovementStatus update();

    public void reset();

    public void resetBlockCache();

    public boolean safeToCancel();

    public boolean calculatedWhileLoaded();

    public BetterBlockPos getSrc();

    public BetterBlockPos getDest();

    public c_1514_x getDirection();
}

