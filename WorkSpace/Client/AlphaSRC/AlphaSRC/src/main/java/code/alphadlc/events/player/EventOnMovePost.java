/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 */
package code.alphadlc.events.player;

import code.alphadlc.utils.client.managers.event.events.Event;
import net.minecraft.class_243;

public class EventOnMovePost
implements Event {
    private float speed;
    private class_243 movementInput;

    public float getSpeed() {
        return this.speed;
    }

    public class_243 getMovementInput() {
        return this.movementInput;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setMovementInput(class_243 movementInput) {
        this.movementInput = movementInput;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof EventOnMovePost)) {
            return false;
        }
        EventOnMovePost other = (EventOnMovePost)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (Float.compare(this.getSpeed(), other.getSpeed()) != 0) {
            return false;
        }
        class_243 this$movementInput = this.getMovementInput();
        class_243 other$movementInput = other.getMovementInput();
        return !(this$movementInput == null ? other$movementInput != null : !this$movementInput.equals(other$movementInput));
    }

    protected boolean canEqual(Object other) {
        return other instanceof EventOnMovePost;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + Float.floatToIntBits(this.getSpeed());
        class_243 $movementInput = this.getMovementInput();
        result = result * 59 + ($movementInput == null ? 43 : $movementInput.hashCode());
        return result;
    }

    public String toString() {
        return "EventOnMovePost(speed=" + this.getSpeed() + ", movementInput=" + String.valueOf(this.getMovementInput()) + ")";
    }

    public EventOnMovePost(float speed, class_243 movementInput) {
        this.speed = speed;
        this.movementInput = movementInput;
    }
}
