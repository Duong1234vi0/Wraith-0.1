/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 */
package code.alphadlc.events.player;

import code.alphadlc.utils.client.managers.event.events.Event;
import net.minecraft.class_243;

public class EventOnTravelPost
implements Event {
    private class_243 oldVelocity;

    public class_243 getOldVelocity() {
        return this.oldVelocity;
    }

    public void setOldVelocity(class_243 oldVelocity) {
        this.oldVelocity = oldVelocity;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof EventOnTravelPost)) {
            return false;
        }
        EventOnTravelPost other = (EventOnTravelPost)o;
        if (!other.canEqual(this)) {
            return false;
        }
        class_243 this$oldVelocity = this.getOldVelocity();
        class_243 other$oldVelocity = other.getOldVelocity();
        return !(this$oldVelocity == null ? other$oldVelocity != null : !this$oldVelocity.equals(other$oldVelocity));
    }

    protected boolean canEqual(Object other) {
        return other instanceof EventOnTravelPost;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        class_243 $oldVelocity = this.getOldVelocity();
        result = result * 59 + ($oldVelocity == null ? 43 : $oldVelocity.hashCode());
        return result;
    }

    public String toString() {
        return "EventOnTravelPost(oldVelocity=" + String.valueOf(this.getOldVelocity()) + ")";
    }

    public EventOnTravelPost(class_243 oldVelocity) {
        this.oldVelocity = oldVelocity;
    }
}
