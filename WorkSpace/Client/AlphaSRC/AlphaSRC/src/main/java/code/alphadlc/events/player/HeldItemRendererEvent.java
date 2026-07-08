/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_4587
 */
package code.alphadlc.events.player;

import code.alphadlc.utils.client.managers.event.events.Event;
import net.minecraft.class_1268;
import net.minecraft.class_4587;

public class HeldItemRendererEvent
implements Event {
    public class_1268 hand;
    public class_4587 matrix;

    public HeldItemRendererEvent(class_1268 hand, class_4587 matrix) {
        this.hand = hand;
        this.matrix = matrix;
    }

    public class_1268 getHand() {
        return this.hand;
    }

    public class_4587 getMatrix() {
        return this.matrix;
    }

    public void setHand(class_1268 hand) {
        this.hand = hand;
    }

    public void setMatrix(class_4587 matrix) {
        this.matrix = matrix;
    }
}
