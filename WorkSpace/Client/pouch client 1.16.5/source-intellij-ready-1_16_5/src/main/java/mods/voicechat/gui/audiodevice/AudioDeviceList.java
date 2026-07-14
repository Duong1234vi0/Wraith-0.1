/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.maxhenkel.configbuilder.entry.ConfigEntry
 *  javax.annotation.Nullable
 */
package mods.voicechat.gui.audiodevice;

import de.maxhenkel.configbuilder.entry.ConfigEntry;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.SimpleSoundInstance;
import lightning.product.SoundEvents;
import lightning.product.g_2336_b;
import mods.voicechat.gui.audiodevice.AudioDeviceEntry;
import mods.voicechat.gui.widgets.ListScreenListBase;
import mods.voicechat.voice.client.ClientManager;
import mods.voicechat.voice.client.ClientVoicechat;
import mods.voicechat.voice.client.SoundManager;

public class AudioDeviceList
extends ListScreenListBase<AudioDeviceEntry> {
    public static final int CELL_HEIGHT = 36;
    @Nullable
    protected g_2336_b icon;
    @Nullable
    protected ConfigEntry<String> configEntry;

    public AudioDeviceList(int width, int height, int top) {
        super(width, height, top, 36);
        this.func_244605_b(false);
        this.func_244606_c(false);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (super.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }
        AudioDeviceEntry entry = (AudioDeviceEntry)this.getEntryAtPosition(mouseX, mouseY);
        if (entry == null) {
            return false;
        }
        if (!this.isMouseOver(mouseX, mouseY)) {
            return false;
        }
        if (!this.isSelected(entry.getDevice())) {
            this.minecraft.Z_976_R().n_1700_B(SimpleSoundInstance.n_1700_B(SoundEvents.HayBlock, 1.0f));
            this.onSelect(entry);
            return true;
        }
        return false;
    }

    protected void onSelect(AudioDeviceEntry entry) {
        ClientVoicechat client;
        if (this.configEntry != null) {
            this.configEntry.set((Object)entry.device).save();
        }
        if ((client = ClientManager.getClient()) != null) {
            client.reloadAudio();
        }
    }

    public AudioDeviceList setIcon(@Nullable g_2336_b icon) {
        this.icon = icon;
        return this;
    }

    public AudioDeviceList setConfigEntry(@Nullable ConfigEntry<String> configEntry) {
        this.configEntry = configEntry;
        return this;
    }

    @Override
    public void replaceEntries(Collection<AudioDeviceEntry> entries) {
        super.replaceEntries(entries);
    }

    public void setAudioDevices(Collection<String> entries) {
        this.replaceEntries(entries.stream().map(s -> new AudioDeviceEntry((String)s, this.getVisibleName((String)s), this.icon, () -> this.isSelected((String)s))).collect(Collectors.toList()));
    }

    public boolean isSelected(String name) {
        if (this.configEntry == null) {
            return false;
        }
        return ((String)this.configEntry.get()).equals(name);
    }

    public String getVisibleName(String device) {
        return SoundManager.cleanDeviceName(device);
    }

    public boolean isEmpty() {
        return this.getEventListeners().isEmpty();
    }
}


