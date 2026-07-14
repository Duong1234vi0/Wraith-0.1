/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.maxhenkel.configbuilder.entry.ConfigEntry
 *  javax.annotation.Nullable
 */
package mods.voicechat.gui.audiodevice;

import de.maxhenkel.configbuilder.entry.ConfigEntry;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.g_2336_b;
import lightning.product.k_2603_m;
import lightning.product.x_282_a;
import mods.voicechat.VoicechatClient;
import mods.voicechat.gui.audiodevice.SelectDeviceScreen;
import mods.voicechat.voice.client.SoundManager;

public class SelectSpeakerScreen
extends SelectDeviceScreen {
    public static final g_2336_b SPEAKER_ICON = new g_2336_b("voicechat/textures/icons/speaker.png");
    public static final x_282_a TITLE = new F_2904_S("gui.voicechat.select_speaker.title");
    public static final x_282_a NO_SPEAKER = new F_2904_S("message.voicechat.no_speaker").n_1700_B(D_4024_W.w_1484_f);

    public SelectSpeakerScreen(@Nullable k_2603_m parent) {
        super(TITLE, parent);
    }

    @Override
    public List<String> getDevices() {
        return SoundManager.getAllSpeakers();
    }

    @Override
    public g_2336_b getIcon() {
        return SPEAKER_ICON;
    }

    @Override
    public x_282_a getEmptyListComponent() {
        return NO_SPEAKER;
    }

    @Override
    public ConfigEntry<String> getConfigEntry() {
        return VoicechatClient.CLIENT_CONFIG.speaker;
    }
}

