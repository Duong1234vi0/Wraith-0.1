/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.voice.client;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;
import mods.voicechat.Voicechat;
import mods.voicechat.gui.group.JoinGroupList;
import mods.voicechat.intercompatibility.ClientCompatibilityManager;
import mods.voicechat.intercompatibility.CommonCompatibilityManager;
import mods.voicechat.net.ClientServerNetManager;
import mods.voicechat.voice.common.ClientGroup;

public class ClientGroupManager {
    private Map<UUID, ClientGroup> groups = new ConcurrentHashMap<UUID, ClientGroup>();

    public ClientGroupManager() {
        ClientServerNetManager.setClientListener(CommonCompatibilityManager.INSTANCE.getNetManager().addGroupChannel, (client, handler, packet) -> {
            this.groups.put(packet.getGroup().getId(), packet.getGroup());
            Voicechat.LOGGER.debug("Added group '{}' ({})", packet.getGroup().getName(), packet.getGroup().getId());
            JoinGroupList.update();
        });
        ClientServerNetManager.setClientListener(CommonCompatibilityManager.INSTANCE.getNetManager().removeGroupChannel, (client, handler, packet) -> {
            this.groups.remove(packet.getGroupId());
            Voicechat.LOGGER.debug("Removed group {}", packet.getGroupId());
            JoinGroupList.update();
        });
        ClientCompatibilityManager.INSTANCE.onDisconnect(() -> this.groups.clear());
    }

    @Nullable
    public ClientGroup getGroup(UUID id) {
        return this.groups.get(id);
    }

    public Collection<ClientGroup> getGroups() {
        return this.groups.values();
    }
}

