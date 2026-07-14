/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.process;

import lightning.product.BaritoneSettings;
import lightning.product.ClientBootstrap;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.process.PathingCommand;
import mods.baritone.api.api.java.baritone.api.process.PathingCommandType;
import mods.baritone.utils.BaritoneProcessHelper;

public class InventoryPauserProcess
extends BaritoneProcessHelper {
    boolean pauseRequestedLastTick;
    boolean safeToCancelLastTick;
    int ticksOfStationary;

    public InventoryPauserProcess(Baritone baritone) {
        super(baritone);
    }

    @Override
    public boolean isActive() {
        if (this.ctx.player() == null || this.ctx.world() == null) {
            return false;
        }
        try {
            if (ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(BaritoneSettings.class) != null && ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(BaritoneSettings.class).w_1484_f() && BaritoneSettings.v_4262_N.t_148_a().booleanValue()) {
                return false;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return true;
    }

    private double motion() {
        return this.ctx.player().I_4348_c().G_564_y(1.0, 0.0, 1.0).u_1723_Y();
    }

    private boolean stationaryNow() {
        return this.motion() < 1.0E-5;
    }

    public boolean stationaryForInventoryMove() {
        this.pauseRequestedLastTick = true;
        return this.safeToCancelLastTick && this.ticksOfStationary > 1;
    }

    @Override
    public PathingCommand onTick(boolean calcFailed, boolean isSafeToCancel) {
        this.safeToCancelLastTick = isSafeToCancel;
        if (this.pauseRequestedLastTick) {
            this.pauseRequestedLastTick = false;
            if (this.stationaryNow()) {
                ++this.ticksOfStationary;
            }
            return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
        }
        this.ticksOfStationary = 0;
        return new PathingCommand(null, PathingCommandType.DEFER);
    }

    @Override
    public void onLostControl() {
    }

    @Override
    public String displayName0() {
        return "inventory pauser";
    }

    @Override
    public double priority() {
        return 5.1;
    }

    @Override
    public boolean isTemporary() {
        return true;
    }
}


