/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.pathing.movement;

import lightning.product.b_257_Y;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;
import mods.baritone.pathing.movement.CalculationContext;
import mods.baritone.pathing.movement.Movement;
import mods.baritone.pathing.movement.movements.MovementAscend;
import mods.baritone.pathing.movement.movements.MovementDescend;
import mods.baritone.pathing.movement.movements.MovementDiagonal;
import mods.baritone.pathing.movement.movements.MovementDownward;
import mods.baritone.pathing.movement.movements.MovementFall;
import mods.baritone.pathing.movement.movements.MovementParkour;
import mods.baritone.pathing.movement.movements.MovementPillar;
import mods.baritone.pathing.movement.movements.MovementTraverse;
import mods.baritone.utils.pathing.MutableMoveResult;

public enum Moves {
    DOWNWARD(0, -1, 0){

        @Override
        public Movement apply0(CalculationContext context, BetterBlockPos src) {
            return new MovementDownward(context.getBaritone(), src, src.down());
        }

        @Override
        public double cost(CalculationContext context, int x, int y, int z) {
            return MovementDownward.cost(context, x, y, z);
        }
    }
    ,
    PILLAR(0, 1, 0){

        @Override
        public Movement apply0(CalculationContext context, BetterBlockPos src) {
            return new MovementPillar(context.getBaritone(), src, src.up());
        }

        @Override
        public double cost(CalculationContext context, int x, int y, int z) {
            return MovementPillar.cost(context, x, y, z);
        }
    }
    ,
    TRAVERSE_NORTH(0, 0, -1){

        @Override
        public Movement apply0(CalculationContext context, BetterBlockPos src) {
            return new MovementTraverse(context.getBaritone(), src, src.north());
        }

        @Override
        public double cost(CalculationContext context, int x, int y, int z) {
            return MovementTraverse.cost(context, x, y, z, x, z - 1);
        }
    }
    ,
    TRAVERSE_SOUTH(0, 0, 1){

        @Override
        public Movement apply0(CalculationContext context, BetterBlockPos src) {
            return new MovementTraverse(context.getBaritone(), src, src.south());
        }

        @Override
        public double cost(CalculationContext context, int x, int y, int z) {
            return MovementTraverse.cost(context, x, y, z, x, z + 1);
        }
    }
    ,
    TRAVERSE_EAST(1, 0, 0){

        @Override
        public Movement apply0(CalculationContext context, BetterBlockPos src) {
            return new MovementTraverse(context.getBaritone(), src, src.east());
        }

        @Override
        public double cost(CalculationContext context, int x, int y, int z) {
            return MovementTraverse.cost(context, x, y, z, x + 1, z);
        }
    }
    ,
    TRAVERSE_WEST(-1, 0, 0){

        @Override
        public Movement apply0(CalculationContext context, BetterBlockPos src) {
            return new MovementTraverse(context.getBaritone(), src, src.west());
        }

        @Override
        public double cost(CalculationContext context, int x, int y, int z) {
            return MovementTraverse.cost(context, x, y, z, x - 1, z);
        }
    }
    ,
    ASCEND_NORTH(0, 1, -1){

        @Override
        public Movement apply0(CalculationContext context, BetterBlockPos src) {
            return new MovementAscend(context.getBaritone(), src, new BetterBlockPos(src.x, src.y + 1, src.z - 1));
        }

        @Override
        public double cost(CalculationContext context, int x, int y, int z) {
            return MovementAscend.cost(context, x, y, z, x, z - 1);
        }
    }
    ,
    ASCEND_SOUTH(0, 1, 1){

        @Override
        public Movement apply0(CalculationContext context, BetterBlockPos src) {
            return new MovementAscend(context.getBaritone(), src, new BetterBlockPos(src.x, src.y + 1, src.z + 1));
        }

        @Override
        public double cost(CalculationContext context, int x, int y, int z) {
            return MovementAscend.cost(context, x, y, z, x, z + 1);
        }
    }
    ,
    ASCEND_EAST(1, 1, 0){

        @Override
        public Movement apply0(CalculationContext context, BetterBlockPos src) {
            return new MovementAscend(context.getBaritone(), src, new BetterBlockPos(src.x + 1, src.y + 1, src.z));
        }

        @Override
        public double cost(CalculationContext context, int x, int y, int z) {
            return MovementAscend.cost(context, x, y, z, x + 1, z);
        }
    }
    ,
    ASCEND_WEST(-1, 1, 0){

        @Override
        public Movement apply0(CalculationContext context, BetterBlockPos src) {
            return new MovementAscend(context.getBaritone(), src, new BetterBlockPos(src.x - 1, src.y + 1, src.z));
        }

        @Override
        public double cost(CalculationContext context, int x, int y, int z) {
            return MovementAscend.cost(context, x, y, z, x - 1, z);
        }
    }
    ,
    DESCEND_EAST(1, -1, 0, false, true){

        @Override
        public Movement apply0(CalculationContext context, BetterBlockPos src) {
            MutableMoveResult res = new MutableMoveResult();
            this.apply(context, src.x, src.y, src.z, res);
            if (res.y == src.y - 1) {
                return new MovementDescend(context.getBaritone(), src, new BetterBlockPos(res.x, res.y, res.z));
            }
            return new MovementFall(context.getBaritone(), src, new BetterBlockPos(res.x, res.y, res.z));
        }

        @Override
        public void apply(CalculationContext context, int x, int y, int z, MutableMoveResult result) {
            MovementDescend.cost(context, x, y, z, x + 1, z, result);
        }
    }
    ,
    DESCEND_WEST(-1, -1, 0, false, true){

        @Override
        public Movement apply0(CalculationContext context, BetterBlockPos src) {
            MutableMoveResult res = new MutableMoveResult();
            this.apply(context, src.x, src.y, src.z, res);
            if (res.y == src.y - 1) {
                return new MovementDescend(context.getBaritone(), src, new BetterBlockPos(res.x, res.y, res.z));
            }
            return new MovementFall(context.getBaritone(), src, new BetterBlockPos(res.x, res.y, res.z));
        }

        @Override
        public void apply(CalculationContext context, int x, int y, int z, MutableMoveResult result) {
            MovementDescend.cost(context, x, y, z, x - 1, z, result);
        }
    }
    ,
    DESCEND_NORTH(0, -1, -1, false, true){

        @Override
        public Movement apply0(CalculationContext context, BetterBlockPos src) {
            MutableMoveResult res = new MutableMoveResult();
            this.apply(context, src.x, src.y, src.z, res);
            if (res.y == src.y - 1) {
                return new MovementDescend(context.getBaritone(), src, new BetterBlockPos(res.x, res.y, res.z));
            }
            return new MovementFall(context.getBaritone(), src, new BetterBlockPos(res.x, res.y, res.z));
        }

        @Override
        public void apply(CalculationContext context, int x, int y, int z, MutableMoveResult result) {
            MovementDescend.cost(context, x, y, z, x, z - 1, result);
        }
    }
    ,
    DESCEND_SOUTH(0, -1, 1, false, true){

        @Override
        public Movement apply0(CalculationContext context, BetterBlockPos src) {
            MutableMoveResult res = new MutableMoveResult();
            this.apply(context, src.x, src.y, src.z, res);
            if (res.y == src.y - 1) {
                return new MovementDescend(context.getBaritone(), src, new BetterBlockPos(res.x, res.y, res.z));
            }
            return new MovementFall(context.getBaritone(), src, new BetterBlockPos(res.x, res.y, res.z));
        }

        @Override
        public void apply(CalculationContext context, int x, int y, int z, MutableMoveResult result) {
            MovementDescend.cost(context, x, y, z, x, z + 1, result);
        }
    }
    ,
    DIAGONAL_NORTHEAST(1, 0, -1, false, true){

        @Override
        public Movement apply0(CalculationContext context, BetterBlockPos src) {
            MutableMoveResult res = new MutableMoveResult();
            this.apply(context, src.x, src.y, src.z, res);
            return new MovementDiagonal(context.getBaritone(), src, b_257_Y.R_4764_Y, b_257_Y.u_1723_Y, res.y - src.y);
        }

        @Override
        public void apply(CalculationContext context, int x, int y, int z, MutableMoveResult result) {
            MovementDiagonal.cost(context, x, y, z, x + 1, z - 1, result);
        }
    }
    ,
    DIAGONAL_NORTHWEST(-1, 0, -1, false, true){

        @Override
        public Movement apply0(CalculationContext context, BetterBlockPos src) {
            MutableMoveResult res = new MutableMoveResult();
            this.apply(context, src.x, src.y, src.z, res);
            return new MovementDiagonal(context.getBaritone(), src, b_257_Y.R_4764_Y, b_257_Y.P_1922_E, res.y - src.y);
        }

        @Override
        public void apply(CalculationContext context, int x, int y, int z, MutableMoveResult result) {
            MovementDiagonal.cost(context, x, y, z, x - 1, z - 1, result);
        }
    }
    ,
    DIAGONAL_SOUTHEAST(1, 0, 1, false, true){

        @Override
        public Movement apply0(CalculationContext context, BetterBlockPos src) {
            MutableMoveResult res = new MutableMoveResult();
            this.apply(context, src.x, src.y, src.z, res);
            return new MovementDiagonal(context.getBaritone(), src, b_257_Y.G_564_y, b_257_Y.u_1723_Y, res.y - src.y);
        }

        @Override
        public void apply(CalculationContext context, int x, int y, int z, MutableMoveResult result) {
            MovementDiagonal.cost(context, x, y, z, x + 1, z + 1, result);
        }
    }
    ,
    DIAGONAL_SOUTHWEST(-1, 0, 1, false, true){

        @Override
        public Movement apply0(CalculationContext context, BetterBlockPos src) {
            MutableMoveResult res = new MutableMoveResult();
            this.apply(context, src.x, src.y, src.z, res);
            return new MovementDiagonal(context.getBaritone(), src, b_257_Y.G_564_y, b_257_Y.P_1922_E, res.y - src.y);
        }

        @Override
        public void apply(CalculationContext context, int x, int y, int z, MutableMoveResult result) {
            MovementDiagonal.cost(context, x, y, z, x - 1, z + 1, result);
        }
    }
    ,
    PARKOUR_NORTH(0, 0, -4, true, true){

        @Override
        public Movement apply0(CalculationContext context, BetterBlockPos src) {
            return MovementParkour.cost(context, src, b_257_Y.R_4764_Y);
        }

        @Override
        public void apply(CalculationContext context, int x, int y, int z, MutableMoveResult result) {
            MovementParkour.cost(context, x, y, z, b_257_Y.R_4764_Y, result);
        }
    }
    ,
    PARKOUR_SOUTH(0, 0, 4, true, true){

        @Override
        public Movement apply0(CalculationContext context, BetterBlockPos src) {
            return MovementParkour.cost(context, src, b_257_Y.G_564_y);
        }

        @Override
        public void apply(CalculationContext context, int x, int y, int z, MutableMoveResult result) {
            MovementParkour.cost(context, x, y, z, b_257_Y.G_564_y, result);
        }
    }
    ,
    PARKOUR_EAST(4, 0, 0, true, true){

        @Override
        public Movement apply0(CalculationContext context, BetterBlockPos src) {
            return MovementParkour.cost(context, src, b_257_Y.u_1723_Y);
        }

        @Override
        public void apply(CalculationContext context, int x, int y, int z, MutableMoveResult result) {
            MovementParkour.cost(context, x, y, z, b_257_Y.u_1723_Y, result);
        }
    }
    ,
    PARKOUR_WEST(-4, 0, 0, true, true){

        @Override
        public Movement apply0(CalculationContext context, BetterBlockPos src) {
            return MovementParkour.cost(context, src, b_257_Y.P_1922_E);
        }

        @Override
        public void apply(CalculationContext context, int x, int y, int z, MutableMoveResult result) {
            MovementParkour.cost(context, x, y, z, b_257_Y.P_1922_E, result);
        }
    };

    public final boolean dynamicXZ;
    public final boolean dynamicY;
    public final int xOffset;
    public final int yOffset;
    public final int zOffset;

    private Moves(int x, int y, int z, boolean dynamicXZ, boolean dynamicY) {
        this.xOffset = x;
        this.yOffset = y;
        this.zOffset = z;
        this.dynamicXZ = dynamicXZ;
        this.dynamicY = dynamicY;
    }

    private Moves(int x, int y, int z) {
        this(x, y, z, false, false);
    }

    public abstract Movement apply0(CalculationContext var1, BetterBlockPos var2);

    public void apply(CalculationContext context, int x, int y, int z, MutableMoveResult result) {
        if (this.dynamicXZ || this.dynamicY) {
            throw new UnsupportedOperationException();
        }
        result.x = x + this.xOffset;
        result.y = y + this.yOffset;
        result.z = z + this.zOffset;
        result.cost = this.cost(context, x, y, z);
    }

    public double cost(CalculationContext context, int x, int y, int z) {
        throw new UnsupportedOperationException();
    }
}

