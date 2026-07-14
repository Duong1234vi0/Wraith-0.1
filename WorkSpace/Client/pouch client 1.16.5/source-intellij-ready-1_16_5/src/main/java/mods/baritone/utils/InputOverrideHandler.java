/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.utils;

import java.util.HashMap;
import java.util.Map;
import lightning.product.e_869_m;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;
import mods.baritone.api.api.java.baritone.api.event.events.TickEvent;
import mods.baritone.api.api.java.baritone.api.utils.IInputOverrideHandler;
import mods.baritone.api.api.java.baritone.api.utils.input.Input;
import mods.baritone.behavior.Behavior;
import mods.baritone.utils.BlockBreakHelper;
import mods.baritone.utils.BlockPlaceHelper;
import mods.baritone.utils.PlayerMovementInput;

public final class InputOverrideHandler
extends Behavior
implements IInputOverrideHandler {
    private final Map<Input, Boolean> inputForceStateMap = new HashMap<Input, Boolean>();
    private final BlockBreakHelper blockBreakHelper;
    private final BlockPlaceHelper blockPlaceHelper;

    public InputOverrideHandler(Baritone baritone) {
        super(baritone);
        this.blockBreakHelper = new BlockBreakHelper(baritone.getPlayerContext());
        this.blockPlaceHelper = new BlockPlaceHelper(baritone.getPlayerContext());
    }

    @Override
    public final boolean isInputForcedDown(Input input) {
        return input == null ? false : this.inputForceStateMap.getOrDefault((Object)input, false);
    }

    @Override
    public final void setInputForceState(Input input, boolean forced) {
        this.inputForceStateMap.put(input, forced);
    }

    @Override
    public final void clearAllKeys() {
        this.inputForceStateMap.clear();
    }

    @Override
    public final void onTick(TickEvent event) {
        if (event.getType() == TickEvent.Type.OUT) {
            return;
        }
        if (this.isInputForcedDown(Input.CLICK_LEFT)) {
            this.setInputForceState(Input.CLICK_RIGHT, false);
        }
        this.blockBreakHelper.tick(this.isInputForcedDown(Input.CLICK_LEFT));
        this.blockPlaceHelper.tick(this.isInputForcedDown(Input.CLICK_RIGHT));
        if (this.inControl()) {
            if (this.ctx.player().G_564_y.getClass() != PlayerMovementInput.class) {
                this.ctx.player().G_564_y = new PlayerMovementInput(this);
            }
        } else if (this.ctx.player().G_564_y.getClass() == PlayerMovementInput.class) {
            this.ctx.player().G_564_y = new e_869_m(this.ctx.minecraft().P_4830_p);
        }
    }

    private boolean inControl() {
        for (Input input : new Input[]{Input.MOVE_FORWARD, Input.MOVE_BACK, Input.MOVE_LEFT, Input.MOVE_RIGHT, Input.SNEAK}) {
            if (!this.isInputForcedDown(input)) continue;
            return true;
        }
        return this.baritone.getPathingBehavior().isPathing() || this.baritone != BaritoneAPI.getProvider().getPrimaryBaritone();
    }

    public BlockBreakHelper getBlockBreakHelper() {
        return this.blockBreakHelper;
    }
}

