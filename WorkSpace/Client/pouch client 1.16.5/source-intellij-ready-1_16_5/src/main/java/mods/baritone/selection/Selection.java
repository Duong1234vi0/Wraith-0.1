/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.selection;

import lightning.product.I_4817_s;
import lightning.product.b_257_Y;
import lightning.product.z_3539_x;
import mods.baritone.api.api.java.baritone.api.selection.ISelection;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;

public class Selection
implements ISelection {
    private final BetterBlockPos pos1;
    private final BetterBlockPos pos2;
    private final BetterBlockPos min;
    private final BetterBlockPos max;
    private final z_3539_x size;
    private final I_4817_s aabb;

    public Selection(BetterBlockPos pos1, BetterBlockPos pos2) {
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.min = new BetterBlockPos(Math.min(pos1.x, pos2.x), Math.min(pos1.y, pos2.y), Math.min(pos1.z, pos2.z));
        this.max = new BetterBlockPos(Math.max(pos1.x, pos2.x), Math.max(pos1.y, pos2.y), Math.max(pos1.z, pos2.z));
        this.size = new z_3539_x(this.max.x - this.min.x + 1, this.max.y - this.min.y + 1, this.max.z - this.min.z + 1);
        this.aabb = new I_4817_s(this.min, this.max.add(1, 1, 1));
    }

    @Override
    public BetterBlockPos pos1() {
        return this.pos1;
    }

    @Override
    public BetterBlockPos pos2() {
        return this.pos2;
    }

    @Override
    public BetterBlockPos min() {
        return this.min;
    }

    @Override
    public BetterBlockPos max() {
        return this.max;
    }

    @Override
    public z_3539_x size() {
        return this.size;
    }

    @Override
    public I_4817_s aabb() {
        return this.aabb;
    }

    public int hashCode() {
        return this.pos1.hashCode() ^ this.pos2.hashCode();
    }

    public String toString() {
        return String.format("Selection{pos1=%s,pos2=%s}", this.pos1, this.pos2);
    }

    private boolean isPos2(b_257_Y facing) {
        boolean negative = facing.P_1922_E().n_1700_B() < 0;
        switch (facing.h_1847_R()) {
            case n_1700_B: {
                return this.pos2.x > this.pos1.x ^ negative;
            }
            case J_1907_R: {
                return this.pos2.y > this.pos1.y ^ negative;
            }
            case R_4764_Y: {
                return this.pos2.z > this.pos1.z ^ negative;
            }
        }
        throw new IllegalStateException("Bad Direction.Axis");
    }

    @Override
    public ISelection expand(b_257_Y direction, int blocks) {
        if (this.isPos2(direction)) {
            return new Selection(this.pos1, this.pos2.offset(direction, blocks));
        }
        return new Selection(this.pos1.offset(direction, blocks), this.pos2);
    }

    @Override
    public ISelection contract(b_257_Y direction, int blocks) {
        if (this.isPos2(direction)) {
            return new Selection(this.pos1.offset(direction, blocks), this.pos2);
        }
        return new Selection(this.pos1, this.pos2.offset(direction, blocks));
    }

    @Override
    public ISelection shift(b_257_Y direction, int blocks) {
        return new Selection(this.pos1.offset(direction, blocks), this.pos2.offset(direction, blocks));
    }
}

