/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10185
 *  net.minecraft.class_3532
 */
package code.alphadlc.events.player;

import code.alphadlc.utils.client.managers.event.events.callables.EventCancellable;
import net.minecraft.class_10185;
import net.minecraft.class_3532;

public class InputEvent
extends EventCancellable {
    private class_10185 input;

    public void setJumping(boolean jump) {
        this.input = new class_10185(this.input.comp_3159(), this.input.comp_3160(), this.input.comp_3161(), this.input.comp_3162(), jump, this.input.comp_3164(), this.input.comp_3165());
    }

    public void setDirectional(boolean forward, boolean backward, boolean left, boolean right) {
        this.input = new class_10185(forward, backward, left, right, this.input.comp_3163(), this.input.comp_3164(), this.input.comp_3165());
    }

    public void setSneak(boolean sneak) {
        this.input = new class_10185(this.input.comp_3159(), this.input.comp_3160(), this.input.comp_3161(), this.input.comp_3162(), this.input.comp_3163(), sneak, this.input.comp_3165());
    }

    public void inputNone() {
        this.input = new class_10185(false, false, false, false, false, false, false);
    }

    public int forward() {
        return this.input.comp_3159() ? 1 : (this.input.comp_3160() ? -1 : 0);
    }

    public float sideways() {
        return this.input.comp_3161() ? 1.0f : (this.input.comp_3162() ? -1.0f : 0.0f);
    }

    public float getForward() {
        return this.input.comp_3159() ? 1.0f : (this.input.comp_3160() ? -1.0f : 0.0f);
    }

    public float getStrafe() {
        return this.input.comp_3161() ? 1.0f : (this.input.comp_3162() ? -1.0f : 0.0f);
    }

    public boolean isJump() {
        return this.input.comp_3163();
    }

    public boolean isSneak() {
        return this.input.comp_3164();
    }

    public void setForward(float forward) {
        boolean forwardKey = forward > 0.0f;
        boolean backwardKey = forward < 0.0f;
        this.input = new class_10185(forwardKey, backwardKey, this.input.comp_3161(), this.input.comp_3162(), this.input.comp_3163(), this.input.comp_3164(), this.input.comp_3165());
    }

    public void setStrafe(float strafe) {
        boolean leftKey = strafe > 0.0f;
        boolean rightKey = strafe < 0.0f;
        this.input = new class_10185(this.input.comp_3159(), this.input.comp_3160(), leftKey, rightKey, this.input.comp_3163(), this.input.comp_3164(), this.input.comp_3165());
    }

    public void setJump(boolean jump) {
        this.setJumping(jump);
    }

    public void correctMovement(float correctionYaw, float directionYaw) {
        float forward = this.getForward();
        float sideways = this.getStrafe();
        if (forward == 0.0f && sideways == 0.0f) {
            return;
        }
        double angle = class_3532.method_15338((double)Math.toDegrees(InputEvent.direction(directionYaw, forward, sideways)));
        float closestForward = 0.0f;
        float closestSideways = 0.0f;
        float closestDifference = Float.MAX_VALUE;
        for (float predictedForward = -1.0f; predictedForward <= 1.0f; predictedForward += 1.0f) {
            for (float predictedSideways = -1.0f; predictedSideways <= 1.0f; predictedSideways += 1.0f) {
                double predictedAngle;
                float difference;
                if (predictedForward == 0.0f && predictedSideways == 0.0f || !((difference = Math.abs(class_3532.method_15393((float)((float)(angle - (predictedAngle = class_3532.method_15338((double)Math.toDegrees(InputEvent.direction(correctionYaw, predictedForward, predictedSideways))))))))) < closestDifference)) continue;
                closestDifference = difference;
                closestForward = predictedForward;
                closestSideways = predictedSideways;
            }
        }
        this.setForward(closestForward);
        this.setStrafe(closestSideways);
    }

    private static double direction(float yaw, float movementForward, float movementSideways) {
        if (movementForward < 0.0f) {
            yaw += 180.0f;
        }
        float forward = 1.0f;
        if (movementForward < 0.0f) {
            forward = -0.5f;
        } else if (movementForward > 0.0f) {
            forward = 0.5f;
        }
        if (movementSideways > 0.0f) {
            yaw -= 90.0f * forward;
        }
        if (movementSideways < 0.0f) {
            yaw += 90.0f * forward;
        }
        return Math.toRadians(yaw);
    }

    public class_10185 getInput() {
        return this.input;
    }

    public void setInput(class_10185 input) {
        this.input = input;
    }

    public InputEvent(class_10185 input) {
        this.input = input;
    }
}
