/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import lightning.product.K_4074_S;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.EmptyLevelChunk;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.process.PathingCommand;
import mods.baritone.api.api.java.baritone.api.process.PathingCommandType;
import mods.baritone.api.api.java.baritone.api.utils.input.Input;
import mods.baritone.pathing.movement.Movement;
import mods.baritone.pathing.movement.MovementHelper;
import mods.baritone.pathing.movement.MovementState;
import mods.baritone.pathing.path.PathExecutor;
import mods.baritone.utils.BaritoneProcessHelper;

public final class BackfillProcess
extends BaritoneProcessHelper {
    public HashMap<c_1514_x, K_4074_S> blocksToReplace = new HashMap();

    public BackfillProcess(Baritone baritone) {
        super(baritone);
    }

    @Override
    public boolean isActive() {
        if (this.ctx.player() == null || this.ctx.world() == null) {
            return false;
        }
        if (!((Boolean)Baritone.settings().backfill.value).booleanValue()) {
            return false;
        }
        if (((Boolean)Baritone.settings().allowParkour.value).booleanValue()) {
            this.logDirect("Backfill cannot be used with allowParkour true");
            Baritone.settings().backfill.value = false;
            return false;
        }
        for (c_1514_x pos : new ArrayList<c_1514_x>(this.blocksToReplace.keySet())) {
            if (!(this.ctx.world().t_148_a(pos) instanceof EmptyLevelChunk) && this.ctx.world().getBlockState(pos).J_1907_R() == a_3742_W.n_1700_B) continue;
            this.blocksToReplace.remove(pos);
        }
        this.amIBreakingABlockHMMMMMMM();
        this.baritone.getInputOverrideHandler().clearAllKeys();
        return !this.toFillIn().isEmpty();
    }

    @Override
    public PathingCommand onTick(boolean calcFailed, boolean isSafeToCancel) {
        if (!isSafeToCancel) {
            return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
        }
        this.baritone.getInputOverrideHandler().clearAllKeys();
        block5: for (c_1514_x toPlace : this.toFillIn()) {
            MovementState fake = new MovementState();
            switch (MovementHelper.attemptToPlaceABlock(fake, this.baritone, toPlace, false, false)) {
                case NO_OPTION: {
                    continue block5;
                }
                case READY_TO_PLACE: {
                    this.baritone.getInputOverrideHandler().setInputForceState(Input.CLICK_RIGHT, true);
                    return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
                }
                case ATTEMPTING: {
                    this.baritone.getLookBehavior().updateTarget(fake.getTarget().getRotation().get(), true);
                    return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
                }
            }
            throw new IllegalStateException();
        }
        return new PathingCommand(null, PathingCommandType.DEFER);
    }

    private void amIBreakingABlockHMMMMMMM() {
        if (!this.ctx.getSelectedBlock().isPresent() || !this.baritone.getPathingBehavior().isPathing()) {
            return;
        }
        this.blocksToReplace.put(this.ctx.getSelectedBlock().get(), this.ctx.world().getBlockState(this.ctx.getSelectedBlock().get()));
    }

    public List<c_1514_x> toFillIn() {
        return this.blocksToReplace.keySet().stream().filter(pos -> this.ctx.world().getBlockState((c_1514_x)pos).J_1907_R() == a_3742_W.n_1700_B).filter(pos -> this.baritone.getBuilderProcess().placementPlausible((c_1514_x)pos, a_3742_W.s_956_w.multiplayerClientSuggestionProvider())).filter(pos -> !this.partOfCurrentMovement((c_1514_x)pos)).sorted(Comparator.comparingDouble(this.ctx.playerFeet()::distanceSq).reversed()).collect(Collectors.toList());
    }

    private boolean partOfCurrentMovement(c_1514_x pos) {
        PathExecutor exec = this.baritone.getPathingBehavior().getCurrent();
        if (exec == null || exec.finished() || exec.failed()) {
            return false;
        }
        Movement movement = (Movement)exec.getPath().movements().get(exec.getPosition());
        return Arrays.asList(movement.toBreakAll()).contains(pos);
    }

    @Override
    public void onLostControl() {
        if (this.blocksToReplace != null && !this.blocksToReplace.isEmpty()) {
            this.blocksToReplace.clear();
        }
    }

    @Override
    public String displayName0() {
        return "Backfill";
    }

    @Override
    public boolean isTemporary() {
        return true;
    }

    @Override
    public double priority() {
        return 5.0;
    }
}


