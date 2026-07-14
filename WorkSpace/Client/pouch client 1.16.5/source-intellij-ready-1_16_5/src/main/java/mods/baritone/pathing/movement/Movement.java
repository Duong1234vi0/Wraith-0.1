/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.pathing.movement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import lightning.product.I_4817_s;
import lightning.product.W_4464_I;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.pathing.movement.IMovement;
import mods.baritone.api.api.java.baritone.api.pathing.movement.MovementStatus;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;
import mods.baritone.api.api.java.baritone.api.utils.IPlayerContext;
import mods.baritone.api.api.java.baritone.api.utils.Rotation;
import mods.baritone.api.api.java.baritone.api.utils.RotationUtils;
import mods.baritone.api.api.java.baritone.api.utils.VecUtils;
import mods.baritone.api.api.java.baritone.api.utils.input.Input;
import mods.baritone.behavior.PathingBehavior;
import mods.baritone.pathing.movement.CalculationContext;
import mods.baritone.pathing.movement.MovementHelper;
import mods.baritone.pathing.movement.MovementState;
import mods.baritone.utils.BlockStateInterface;

public abstract class Movement
implements IMovement,
MovementHelper {
    public static final b_257_Y[] HORIZONTALS_BUT_ALSO_DOWN_____SO_EVERY_DIRECTION_EXCEPT_UP = new b_257_Y[]{b_257_Y.R_4764_Y, b_257_Y.G_564_y, b_257_Y.u_1723_Y, b_257_Y.P_1922_E, b_257_Y.n_1700_B};
    protected final IBaritone baritone;
    protected final IPlayerContext ctx;
    private MovementState currentState = new MovementState().setStatus(MovementStatus.PREPPING);
    protected final BetterBlockPos src;
    protected final BetterBlockPos dest;
    protected final BetterBlockPos[] positionsToBreak;
    protected final BetterBlockPos positionToPlace;
    private Double cost;
    public List<c_1514_x> toBreakCached = null;
    public List<c_1514_x> toPlaceCached = null;
    public List<c_1514_x> toWalkIntoCached = null;
    private Set<BetterBlockPos> validPositionsCached = null;
    private Boolean calculatedWhileLoaded;

    protected Movement(IBaritone baritone, BetterBlockPos src, BetterBlockPos dest, BetterBlockPos[] toBreak, BetterBlockPos toPlace) {
        this.baritone = baritone;
        this.ctx = baritone.getPlayerContext();
        this.src = src;
        this.dest = dest;
        this.positionsToBreak = toBreak;
        this.positionToPlace = toPlace;
    }

    protected Movement(IBaritone baritone, BetterBlockPos src, BetterBlockPos dest, BetterBlockPos[] toBreak) {
        this(baritone, src, dest, toBreak, null);
    }

    @Override
    public double getCost() throws NullPointerException {
        return this.cost;
    }

    public double getCost(CalculationContext context) {
        if (this.cost == null) {
            this.cost = this.calculateCost(context);
        }
        return this.cost;
    }

    public abstract double calculateCost(CalculationContext var1);

    public double recalculateCost(CalculationContext context) {
        this.cost = null;
        return this.getCost(context);
    }

    public void override(double cost) {
        this.cost = cost;
    }

    protected abstract Set<BetterBlockPos> calculateValidPositions();

    public Set<BetterBlockPos> getValidPositions() {
        if (this.validPositionsCached == null) {
            this.validPositionsCached = this.calculateValidPositions();
            Objects.requireNonNull(this.validPositionsCached);
        }
        return this.validPositionsCached;
    }

    protected boolean playerInValidPosition() {
        return this.getValidPositions().contains(this.ctx.playerFeet()) || this.getValidPositions().contains(((PathingBehavior)this.baritone.getPathingBehavior()).pathStart());
    }

    @Override
    public MovementStatus update() {
        this.ctx.player().C_415_h.J_1907_R = false;
        this.currentState = this.updateState(this.currentState);
        if (MovementHelper.isLiquid(this.ctx, this.ctx.playerFeet())) {
            this.currentState.setInput(Input.JUMP, true);
        }
        if (this.ctx.player().i_2993_w()) {
            this.ctx.getSelectedBlock().ifPresent(pos -> MovementHelper.switchToBestToolFor(this.ctx, BlockStateInterface.get(this.ctx, pos)));
            this.currentState.setInput(Input.CLICK_LEFT, true);
        }
        this.currentState.getTarget().getRotation().ifPresent(rotation -> this.baritone.getLookBehavior().updateTarget((Rotation)rotation, this.currentState.getTarget().hasToForceRotations()));
        this.baritone.getInputOverrideHandler().clearAllKeys();
        this.currentState.getInputStates().forEach((input, forced) -> this.baritone.getInputOverrideHandler().setInputForceState((Input)((Object)input), (boolean)forced));
        this.currentState.getInputStates().clear();
        if (this.currentState.getStatus().isComplete()) {
            this.baritone.getInputOverrideHandler().clearAllKeys();
        }
        return this.currentState.getStatus();
    }

    protected boolean prepared(MovementState state) {
        if (state.getStatus() == MovementStatus.WAITING) {
            return true;
        }
        boolean somethingInTheWay = false;
        for (BetterBlockPos blockPos : this.positionsToBreak) {
            if (!this.ctx.world().n_1700_B(W_4464_I.class, new I_4817_s(0.0, 0.0, 0.0, 1.0, 1.1, 1.0).offset(blockPos)).isEmpty() && ((Boolean)Baritone.settings().pauseMiningForFallingBlocks.value).booleanValue()) {
                return false;
            }
            if (MovementHelper.canWalkThrough(this.ctx, blockPos)) continue;
            somethingInTheWay = true;
            MovementHelper.switchToBestToolFor(this.ctx, BlockStateInterface.get(this.ctx, blockPos));
            Optional<Rotation> reachable = RotationUtils.reachable(this.ctx, (c_1514_x)blockPos, this.ctx.playerController().getBlockReachDistance());
            if (reachable.isPresent()) {
                Rotation rotTowardsBlock = reachable.get();
                state.setTarget(new MovementState.MovementTarget(rotTowardsBlock, true));
                if (this.ctx.isLookingAt(blockPos) || this.ctx.playerRotations().isReallyCloseTo(rotTowardsBlock)) {
                    state.setInput(Input.CLICK_LEFT, true);
                }
                return false;
            }
            state.setTarget(new MovementState.MovementTarget(RotationUtils.calcRotationFromVec3d(this.ctx.playerHead(), VecUtils.getBlockPosCenter(blockPos), this.ctx.playerRotations()), true));
            state.setInput(Input.CLICK_LEFT, true);
            return false;
        }
        if (somethingInTheWay) {
            state.setStatus(MovementStatus.UNREACHABLE);
            return true;
        }
        return true;
    }

    @Override
    public boolean safeToCancel() {
        return this.safeToCancel(this.currentState);
    }

    protected boolean safeToCancel(MovementState currentState) {
        return true;
    }

    @Override
    public BetterBlockPos getSrc() {
        return this.src;
    }

    @Override
    public BetterBlockPos getDest() {
        return this.dest;
    }

    @Override
    public void reset() {
        this.currentState = new MovementState().setStatus(MovementStatus.PREPPING);
    }

    public MovementState updateState(MovementState state) {
        if (!this.prepared(state)) {
            return state.setStatus(MovementStatus.PREPPING);
        }
        if (state.getStatus() == MovementStatus.PREPPING) {
            state.setStatus(MovementStatus.WAITING);
        }
        if (state.getStatus() == MovementStatus.WAITING) {
            state.setStatus(MovementStatus.RUNNING);
        }
        return state;
    }

    @Override
    public c_1514_x getDirection() {
        return this.getDest().subtract(this.getSrc());
    }

    public void checkLoadedChunk(CalculationContext context) {
        this.calculatedWhileLoaded = context.bsi.worldContainsLoadedChunk(this.dest.x, this.dest.z);
    }

    @Override
    public boolean calculatedWhileLoaded() {
        return this.calculatedWhileLoaded;
    }

    @Override
    public void resetBlockCache() {
        this.toBreakCached = null;
        this.toPlaceCached = null;
        this.toWalkIntoCached = null;
    }

    public List<c_1514_x> toBreak(BlockStateInterface bsi) {
        if (this.toBreakCached != null) {
            return this.toBreakCached;
        }
        ArrayList<c_1514_x> result = new ArrayList<c_1514_x>();
        for (BetterBlockPos positionToBreak : this.positionsToBreak) {
            if (MovementHelper.canWalkThrough(bsi, positionToBreak.x, positionToBreak.y, positionToBreak.z)) continue;
            result.add(positionToBreak);
        }
        this.toBreakCached = result;
        return result;
    }

    public List<c_1514_x> toPlace(BlockStateInterface bsi) {
        if (this.toPlaceCached != null) {
            return this.toPlaceCached;
        }
        ArrayList<c_1514_x> result = new ArrayList<c_1514_x>();
        if (this.positionToPlace != null && !MovementHelper.canWalkOn(bsi, this.positionToPlace.x, this.positionToPlace.y, this.positionToPlace.z)) {
            result.add(this.positionToPlace);
        }
        this.toPlaceCached = result;
        return result;
    }

    public List<c_1514_x> toWalkInto(BlockStateInterface bsi) {
        if (this.toWalkIntoCached == null) {
            this.toWalkIntoCached = new ArrayList<c_1514_x>();
        }
        return this.toWalkIntoCached;
    }

    public c_1514_x[] toBreakAll() {
        return this.positionsToBreak;
    }
}

