/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 */
package code.alphadlc.utils.client.managers.event.impl;

import code.alphadlc.utils.client.managers.event.impl.EventLayer;
import net.minecraft.class_1297;

public class EventAttack
extends EventLayer {
    private class_1297 entity;
    boolean pre;

    public EventAttack(class_1297 entity, boolean pre) {
        this.entity = entity;
        this.pre = pre;
    }

    public class_1297 getEntity() {
        return this.entity;
    }

    public boolean isPre() {
        return this.pre;
    }
}
