/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.gui.group;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import lightning.product.MinecraftClient;
import lightning.product.k_2603_m;
import mods.voicechat.gui.group.GroupEntry;
import mods.voicechat.gui.group.GroupScreen;
import mods.voicechat.gui.widgets.ListScreenBase;
import mods.voicechat.gui.widgets.ListScreenListBase;
import mods.voicechat.voice.client.ClientManager;
import mods.voicechat.voice.common.PlayerState;

public class GroupList
extends ListScreenListBase<GroupEntry> {
    protected final ListScreenBase parent;

    public GroupList(ListScreenBase parent, int width, int height, int top, int size) {
        super(width, height, top, size);
        this.parent = parent;
        this.func_244605_b(false);
        this.func_244606_c(false);
        this.updateMembers();
    }

    public void updateMembers() {
        List<PlayerState> playerStates = ClientManager.getPlayerStateManager().getPlayerStates(true);
        UUID group = ClientManager.getPlayerStateManager().getGroupID();
        if (group == null) {
            this.clearEntries();
            this.minecraft.n_1700_B((k_2603_m)null);
            return;
        }
        boolean changed = false;
        LinkedList<GroupEntry> toRemove = new LinkedList<GroupEntry>();
        for (GroupEntry entry : this.getEventListeners()) {
            PlayerState state = ClientManager.getPlayerStateManager().getState(entry.getState().getUuid());
            if (state == null) {
                toRemove.add(entry);
                changed = true;
                continue;
            }
            entry.setState(state);
            if (this.isInGroup(state, group)) continue;
            toRemove.add(entry);
            changed = true;
        }
        for (GroupEntry entry : toRemove) {
            this.removeEntry(entry);
        }
        for (PlayerState state : playerStates) {
            if (!this.isInGroup(state, group) || !this.getEventListeners().stream().noneMatch(groupEntry -> groupEntry.getState().getUuid().equals(state.getUuid()))) continue;
            this.addEntry(new GroupEntry(this.parent, state));
            changed = true;
        }
        if (changed) {
            this.getEventListeners().sort(Comparator.comparing(o -> o.getState().getName()));
        }
    }

    public static void update() {
        k_2603_m screen = MinecraftClient.A_4115_X().Y_1740_V;
        if (screen instanceof GroupScreen) {
            GroupScreen groupScreen = (GroupScreen)screen;
            groupScreen.groupList.updateMembers();
        }
    }

    private boolean isInGroup(PlayerState state, UUID group) {
        return state.hasGroup() && state.getGroup().equals(group);
    }
}


