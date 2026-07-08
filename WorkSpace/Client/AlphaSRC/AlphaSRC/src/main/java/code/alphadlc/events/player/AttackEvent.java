/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 */
package code.alphadlc.events.player;

import code.alphadlc.utils.client.managers.event.events.callables.EventCancellable;
import net.minecraft.class_1297;

public class AttackEvent
extends EventCancellable {
    private class_1297 entity;

    public AttackEvent(class_1297 entity) {
        this.entity = entity;
    }

    public class_1297 getEntity() {
        return this.entity;
    }

    public void setEntity(class_1297 entity) {
        this.entity = entity;
    }
}
