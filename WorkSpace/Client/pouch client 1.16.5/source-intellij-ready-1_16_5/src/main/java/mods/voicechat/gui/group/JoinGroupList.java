/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.gui.group;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lightning.product.MinecraftClient;
import lightning.product.k_2603_m;
import mods.voicechat.gui.group.JoinGroupEntry;
import mods.voicechat.gui.group.JoinGroupScreen;
import mods.voicechat.gui.widgets.ListScreenBase;
import mods.voicechat.gui.widgets.ListScreenListBase;
import mods.voicechat.voice.client.ClientManager;
import mods.voicechat.voice.common.ClientGroup;
import mods.voicechat.voice.common.PlayerState;

public class JoinGroupList
extends ListScreenListBase<JoinGroupEntry> {
    protected final ListScreenBase parent;

    public JoinGroupList(ListScreenBase parent, int width, int height, int top, int size) {
        super(width, height, top, size);
        this.parent = parent;
        this.func_244605_b(false);
        this.func_244606_c(false);
        this.updateGroups();
    }

    private void updateGroups() {
        Map<UUID, JoinGroupEntry.Group> groups = ClientManager.getGroupManager().getGroups().stream().filter(clientGroup -> !clientGroup.isHidden()).collect(Collectors.toMap(ClientGroup::getId, JoinGroupEntry.Group::new));
        List<PlayerState> playerStates = ClientManager.getPlayerStateManager().getPlayerStates(true);
        for (PlayerState state : playerStates) {
            JoinGroupEntry.Group group2;
            if (!state.hasGroup() || (group2 = groups.get(state.getGroup())) == null) continue;
            group2.getMembers().add(state);
        }
        groups.values().forEach(group -> group.getMembers().sort(Comparator.comparing(PlayerState::getName)));
        this.replaceEntries(groups.values().stream().map(group -> new JoinGroupEntry(this.parent, (JoinGroupEntry.Group)group)).sorted(Comparator.comparing(o -> o.getGroup().getGroup().getName())).collect(Collectors.toList()));
    }

    public static void update() {
        k_2603_m screen = MinecraftClient.A_4115_X().Y_1740_V;
        if (screen instanceof JoinGroupScreen) {
            JoinGroupScreen joinGroupScreen = (JoinGroupScreen)screen;
            joinGroupScreen.groupList.updateGroups();
        }
    }

    public boolean isEmpty() {
        return this.getEventListeners().isEmpty();
    }
}


