/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.eventforge;

import lightning.product.r_4811_B;
import mods.voicechat.eventforge.EntityEvent;

public class LivingEvent
extends EntityEvent {
    private final r_4811_B entityLiving;

    public LivingEvent(r_4811_B entity) {
        super(entity);
        this.entityLiving = entity;
    }

    public r_4811_B getEntityLiving() {
        return this.entityLiving;
    }
}

