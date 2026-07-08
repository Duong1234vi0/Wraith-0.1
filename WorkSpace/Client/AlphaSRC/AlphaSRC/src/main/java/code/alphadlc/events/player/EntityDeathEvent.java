/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1282
 *  net.minecraft.class_1297
 */
package code.alphadlc.events.player;

import code.alphadlc.utils.client.managers.event.events.Event;
import net.minecraft.class_1282;
import net.minecraft.class_1297;

public class EntityDeathEvent
implements Event {
    private final class_1297 entity;
    private final class_1282 source;

    public EntityDeathEvent(class_1297 entity, class_1282 source) {
        this.entity = entity;
        this.source = source;
    }

    public class_1297 getEntity() {
        return this.entity;
    }

    public class_1282 getSource() {
        return this.source;
    }
}
