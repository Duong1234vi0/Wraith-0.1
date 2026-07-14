/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.utils;

import lightning.product.e_869_m;
import lightning.product.Input;
import mods.baritone.api.api.java.baritone.api.utils.input.Input;
import mods.baritone.utils.InputOverrideHandler;

public class PlayerMovementInput
extends Input {
    private final InputOverrideHandler handler;

    PlayerMovementInput(InputOverrideHandler handler) {
        this.handler = handler;
    }

    @Override
    public void tickMovement(boolean p_225607_1_) {
        e_869_m base = new e_869_m(this.handler.ctx.minecraft().P_4830_p);
        base.tickMovement(p_225607_1_);
        this.moveStrafe = base.moveStrafe;
        this.moveForward = base.moveForward;
        this.jump = base.jump;
        this.sneaking = base.sneaking;
        this.forwardKeyDown = base.forwardKeyDown;
        this.backKeyDown = base.backKeyDown;
        this.leftKeyDown = base.leftKeyDown;
        this.rightKeyDown = base.rightKeyDown;
        if (this.handler.isInputForcedDown(Input.MOVE_FORWARD)) {
            this.moveForward = Math.max(this.moveForward, 1.0f);
            this.forwardKeyDown = true;
        }
        if (this.handler.isInputForcedDown(Input.MOVE_BACK)) {
            this.moveForward = Math.min(this.moveForward, -1.0f);
            this.backKeyDown = true;
        }
        if (this.handler.isInputForcedDown(Input.MOVE_LEFT)) {
            this.moveStrafe = Math.max(this.moveStrafe, 1.0f);
            this.leftKeyDown = true;
        }
        if (this.handler.isInputForcedDown(Input.MOVE_RIGHT)) {
            this.moveStrafe = Math.min(this.moveStrafe, -1.0f);
            this.rightKeyDown = true;
        }
        if (this.handler.isInputForcedDown(Input.JUMP)) {
            this.jump = true;
        }
        if (this.handler.isInputForcedDown(Input.SNEAK)) {
            this.sneaking = true;
        }
        if (this.moveForward > 1.0f) {
            this.moveForward = 1.0f;
        }
        if (this.moveForward < -1.0f) {
            this.moveForward = -1.0f;
        }
        if (this.moveStrafe > 1.0f) {
            this.moveStrafe = 1.0f;
        }
        if (this.moveStrafe < -1.0f) {
            this.moveStrafe = -1.0f;
        }
        if (this.sneaking) {
            this.moveStrafe = (float)((double)this.moveStrafe * 0.3);
            this.moveForward = (float)((double)this.moveForward * 0.3);
        }
    }
}


