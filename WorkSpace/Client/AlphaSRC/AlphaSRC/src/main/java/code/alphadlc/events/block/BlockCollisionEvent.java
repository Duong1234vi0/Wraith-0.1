/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2680
 */
package code.alphadlc.events.block;

import code.alphadlc.utils.client.managers.event.events.Event;
import net.minecraft.class_2338;
import net.minecraft.class_2680;

public class BlockCollisionEvent
implements Event {
    private class_2338 blockPos;
    private class_2680 state;

    public BlockCollisionEvent(class_2338 blockPos, class_2680 state) {
        this.blockPos = blockPos;
        this.state = state;
    }

    public class_2338 getBlockPos() {
        return this.blockPos;
    }

    public class_2680 getState() {
        return this.state;
    }

    public void setBlockPos(class_2338 blockPos) {
        this.blockPos = blockPos;
    }

    public void setState(class_2680 state) {
        this.state = state;
    }
}
