/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins.impl;

import java.util.Objects;
import java.util.UUID;
import javax.annotation.Nullable;
import mods.voicechat.Voicechat;
import mods.voicechat.api.Group;
import mods.voicechat.voice.common.PlayerState;
import mods.voicechat.voice.server.Group;
import mods.voicechat.voice.server.Server;

public class GroupImpl
implements mods.voicechat.api.Group {
    private final Group group;

    public GroupImpl(Group group) {
        this.group = group;
    }

    @Override
    public String getName() {
        return this.group.getName();
    }

    @Override
    public boolean hasPassword() {
        return this.group.getPassword() != null;
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

    public Group getGroup() {
        return this.group;
    }

    @Nullable
    public static GroupImpl create(PlayerState state) {
        Group g;
        UUID groupId = state.getGroup();
        Server server = Voicechat.SERVER.getServer();
        if (server != null && groupId != null && (g = server.getGroupManager().getGroup(groupId)) != null) {
            return new GroupImpl(g);
        }
        return null;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        GroupImpl group1 = (GroupImpl)object;
        return Objects.equals(this.group.getId(), group1.group.getId());
    }

    public int hashCode() {
        return this.group != null ? this.group.getId().hashCode() : 0;
    }

    public static class TypeImpl
    implements Group.Type {
        public static short toInt(Group.Type type) {
            if (type == OPEN) {
                return 1;
            }
            if (type == ISOLATED) {
                return 2;
            }
            return 0;
        }

        public static Group.Type fromInt(short i) {
            if (i == 1) {
                return OPEN;
            }
            if (i == 2) {
                return ISOLATED;
            }
            return NORMAL;
        }
    }

    public static class BuilderImpl
    implements Group.Builder {
        @Nullable
        private UUID id;
        private String name;
        @Nullable
        private String password;
        private boolean persistent;
        private boolean hidden;
        private Group.Type type = Group.Type.NORMAL;

        @Override
        public Group.Builder setId(@Nullable UUID id) {
            this.id = id;
            return this;
        }

        @Override
        public Group.Builder setName(String name) {
            this.name = BuilderImpl.convertGroupName(name);
            return this;
        }

        private static String convertGroupName(String name) {
            if ((name = name.replaceAll("[\\n\\r\\t]", "")).matches("^\\s.*")) {
                name = name.replaceFirst("^\\s+", "");
            }
            if (name.length() > 16) {
                return name.substring(0, 16);
            }
            return name;
        }

        @Override
        public Group.Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        @Override
        public Group.Builder setPersistent(boolean persistent) {
            this.persistent = persistent;
            return this;
        }

        @Override
        public Group.Builder setHidden(boolean hidden) {
            this.hidden = hidden;
            return this;
        }

        @Override
        public Group.Builder setType(Group.Type type) {
            this.type = type;
            return this;
        }

        @Override
        public mods.voicechat.api.Group build() {
            if (this.name == null) {
                throw new IllegalStateException("Group is missing a name");
            }
            if (!Voicechat.GROUP_REGEX.matcher(this.name).matches()) {
                throw new IllegalStateException(String.format("Invalid group name: %s", this.name));
            }
            GroupImpl group = new GroupImpl(new Group(this.id == null ? UUID.randomUUID() : this.id, this.name, this.password, this.persistent, this.hidden, this.type));
            Server server = Voicechat.SERVER.getServer();
            if (server != null && this.persistent) {
                server.getGroupManager().addGroup(group.getGroup(), null);
            }
            return group;
        }
    }
}

