/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_238
 */
package code.alphadlc.events.player;

import code.alphadlc.utils.client.managers.event.events.callables.EventCancellable;
import net.minecraft.class_1297;
import net.minecraft.class_238;

public class BoundingBoxControlEvent
extends EventCancellable {
    public class_238 box;
    public class_1297 entity;

    public class_238 getBox() {
        return this.box;
    }

    public class_1297 getEntity() {
        return this.entity;
    }

    public void setBox(class_238 box) {
        this.box = box;
    }

    public void setEntity(class_1297 entity) {
        this.entity = entity;
    }

    public BoundingBoxControlEvent(class_238 box, class_1297 entity) {
        this.box = box;
        this.entity = entity;
    }
}
