/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl;

import java.util.Objects;
import java.util.UUID;
import mods.voicechat.api.Group;
import mods.voicechat.voice.common.ClientGroup;

public class ClientGroupImpl
implements Group {
    private final ClientGroup group;

    public ClientGroupImpl(ClientGroup group) {
        this.group = group;
    }

    @Override
    public String getName() {
        return this.group.getName();
    }

    @Override
    public boolean hasPassword() {
        return this.group.hasPassword();
    }

    @Override
    public UUID getId() {
        return this.group.getId();
    }

    @Override
    public boolean isPersistent() {
        return this.group.isPersistent();
    }

    @Override
    public boolean isHidden() {
        return this.group.isHidden();
    }

    @Override
    public Group.Type getType() {
        return this.group.getType();
    }

    public ClientGroup getGroup() {
        return this.group;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        ClientGroupImpl that = (ClientGroupImpl)object;
        return Objects.equals(this.group.getId(), that.group.getId());
    }

    public int hashCode() {
        return this.group != null ? this.group.getId().hashCode() : 0;
    }
}

