/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl;

import java.util.Objects;
import java.util.UUID;
import lightning.product.N_4263_v;
import mods.voicechat.api.Entity;
import mods.voicechat.api.Position;
import mods.voicechat.intercompatibility.CommonCompatibilityManager;
import mods.voicechat.plugins.impl.PositionImpl;

public class EntityImpl
implements Entity {
    protected N_4263_v entity;

    public EntityImpl(N_4263_v entity) {
        this.entity = entity;
    }

    @Override
    public UUID getUuid() {
        return this.entity.w_2705_t();
    }

    @Override
    public Object getEntity() {
        return CommonCompatibilityManager.INSTANCE.createRawApiEntity(this.entity);
    }

    @Override
    public Position getPosition() {
        return new PositionImpl(this.entity.s_4990_V());
    }

    public N_4263_v getRealEntity() {
        return this.entity;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        EntityImpl entity1 = (EntityImpl)object;
        return Objects.equals(this.entity, entity1.entity);
    }

    public int hashCode() {
        return this.entity != null ? this.entity.hashCode() : 0;
    }
}

