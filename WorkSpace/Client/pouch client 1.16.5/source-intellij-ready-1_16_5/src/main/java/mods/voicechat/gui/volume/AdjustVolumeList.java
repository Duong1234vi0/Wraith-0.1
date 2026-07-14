/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package mods.voicechat.gui.volume;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import lightning.product.MinecraftClient;
import lightning.product.j_3341_s;
import mods.voicechat.VoicechatClient;
import mods.voicechat.gui.volume.AdjustVolumesScreen;
import mods.voicechat.gui.volume.CategoryVolumeEntry;
import mods.voicechat.gui.volume.PlayerVolumeEntry;
import mods.voicechat.gui.volume.VolumeEntry;
import mods.voicechat.gui.widgets.ListScreenListBase;
import mods.voicechat.plugins.impl.VolumeCategoryImpl;
import mods.voicechat.voice.client.ClientManager;
import mods.voicechat.voice.common.PlayerState;

public class AdjustVolumeList
extends ListScreenListBase<VolumeEntry> {
    protected AdjustVolumesScreen screen;
    protected final List<VolumeEntry> entries;
    protected String filter;

    public AdjustVolumeList(int width, int height, int top, int size, AdjustVolumesScreen screen) {
        super(width, height, top, size);
        this.screen = screen;
        this.entries = Lists.newArrayList();
        this.filter = "";
        this.func_244605_b(false);
        this.func_244606_c(false);
        this.updateEntryList();
    }

    public static void update() {
        if (MinecraftClient.A_4115_X().Y_1740_V instanceof AdjustVolumesScreen) {
            ((AdjustVolumesScreen)MinecraftClient.A_4115_X().Y_1740_V).volumeList.updateEntryList();
        }
    }

    public void updateEntryList() {
        List<PlayerState> onlinePlayers = ClientManager.getPlayerStateManager().getPlayerStates(false);
        this.entries.clear();
        for (VolumeCategoryImpl category : ClientManager.getCategoryManager().getCategories()) {
            this.entries.add(new CategoryVolumeEntry(category, this.screen));
        }
        for (PlayerState state : onlinePlayers) {
            this.entries.add(new PlayerVolumeEntry(state, this.screen));
        }
        if (((Boolean)VoicechatClient.CLIENT_CONFIG.offlinePlayerVolumeAdjustment.get()).booleanValue()) {
            this.addOfflinePlayers(onlinePlayers);
        }
        this.updateFilter();
    }

    private void addOfflinePlayers(Collection<PlayerState> onlinePlayers) {
        for (UUID uuid : VoicechatClient.PLAYER_VOLUME_CONFIG.getVolumes().keySet()) {
            String name;
            if (uuid.equals(j_3341_s.J_1907_R) || onlinePlayers.stream().anyMatch(state -> uuid.equals(state.getUuid())) || (name = VoicechatClient.USERNAME_CACHE.getUsername(uuid)) == null) continue;
            this.entries.add(new PlayerVolumeEntry(new PlayerState(uuid, name, false, true), this.screen));
        }
    }

    public void updateFilter() {
        this.clearEntries();
        ArrayList<VolumeEntry> filteredEntries = new ArrayList<VolumeEntry>(this.entries);
        if (!this.filter.isEmpty()) {
            filteredEntries.removeIf(volumeEntry -> {
                if (volumeEntry instanceof PlayerVolumeEntry) {
                    PlayerVolumeEntry playerVolumeEntry = (PlayerVolumeEntry)volumeEntry;
                    return playerVolumeEntry.getState() == null || !playerVolumeEntry.getState().getName().toLowerCase(Locale.ROOT).contains(this.filter);
                }
                if (volumeEntry instanceof CategoryVolumeEntry) {
                    CategoryVolumeEntry categoryVolumeEntry = (CategoryVolumeEntry)volumeEntry;
                    return !categoryVolumeEntry.getCategory().getName().toLowerCase(Locale.ROOT).contains(this.filter);
                }
                return true;
            });
        }
        filteredEntries.sort((e1, e2) -> {
            if (!e1.getClass().equals(e2.getClass())) {
                if (e1 instanceof PlayerVolumeEntry) {
                    return 1;
                }
                return -1;
            }
            return this.volumeEntryToString((VolumeEntry)e1).compareToIgnoreCase(this.volumeEntryToString((VolumeEntry)e2));
        });
        if (this.filter.isEmpty()) {
            filteredEntries.add(0, new PlayerVolumeEntry(null, this.screen));
        }
        this.replaceEntries(filteredEntries);
    }

    private String volumeEntryToString(VolumeEntry entry) {
        if (entry instanceof PlayerVolumeEntry) {
            PlayerVolumeEntry playerVolumeEntry = (PlayerVolumeEntry)entry;
            return playerVolumeEntry.getState() == null ? "" : playerVolumeEntry.getState().getName();
        }
        if (entry instanceof CategoryVolumeEntry) {
            CategoryVolumeEntry categoryVolumeEntry = (CategoryVolumeEntry)entry;
            return categoryVolumeEntry.getCategory().getName();
        }
        return "";
    }

    public void setFilter(String filter) {
        this.filter = filter;
        this.updateFilter();
    }

    public boolean isEmpty() {
        return this.getEventListeners().isEmpty();
    }
}


