/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.utils.pathing;

import lightning.product.c_1514_x;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;
import mods.baritone.api.api.java.baritone.api.pathing.calc.IPath;
import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.pathing.path.CutoffPath;
import mods.baritone.utils.BlockStateInterface;

public abstract class PathBase
implements IPath {
    @Override
    public PathBase cutoffAtLoadedChunks(Object bsi0) {
        if (!((Boolean)Baritone.settings().cutoffAtLoadBoundary.value).booleanValue()) {
            return this;
        }
        BlockStateInterface bsi = (BlockStateInterface)bsi0;
        for (int i = 0; i < this.positions().size(); ++i) {
            c_1514_x pos = this.positions().get(i);
            if (bsi.worldContainsLoadedChunk(pos.getX(), pos.getZ())) continue;
            return new CutoffPath(this, i);
        }
        return this;
    }

    @Override
    public PathBase staticCutoff(Goal destination) {
        int min = (Integer)BaritoneAPI.getSettings().pathCutoffMinimumLength.value;
        if (this.length() < min) {
            return this;
        }
        if (destination == null || destination.isInGoal(this.getDest())) {
            return this;
        }
        double factor = (Double)BaritoneAPI.getSettings().pathCutoffFactor.value;
        int newLength = (int)((double)(this.length() - min) * factor) + min - 1;
        return new CutoffPath(this, newLength);
    }
}

