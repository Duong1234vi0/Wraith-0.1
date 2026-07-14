/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.maxhenkel.configbuilder.entry.ConfigEntry
 *  javax.annotation.Nullable
 */
package mods.voicechat.gui.onboarding;

import de.maxhenkel.configbuilder.entry.ConfigEntry;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.g_2336_b;
import lightning.product.k_2603_m;
import lightning.product.x_282_a;
import mods.voicechat.VoicechatClient;
import mods.voicechat.gui.audiodevice.SelectMicrophoneScreen;
import mods.voicechat.gui.onboarding.DeviceOnboardingScreen;
import mods.voicechat.gui.onboarding.SpeakerOnboardingScreen;
import mods.voicechat.voice.client.microphone.MicrophoneManager;

public class MicOnboardingScreen
extends DeviceOnboardingScreen {
    private static final x_282_a TITLE = new F_2904_S("message.voicechat.onboarding.microphone").n_1700_B(D_4024_W.multiplayerClientSuggestionProvider);

    public MicOnboardingScreen(@Nullable k_2603_m previous) {
        super(TITLE, previous);
    }

    @Override
    public List<String> getNames() {
        return MicrophoneManager.deviceNames();
    }

    @Override
    public g_2336_b getIcon() {
        return SelectMicrophoneScreen.MICROPHONE_ICON;
    }

    @Override
    public ConfigEntry<String> getConfigEntry() {
        return VoicechatClient.CLIENT_CONFIG.microphone;
    }

    @Override
    public k_2603_m getNextScreen() {
        return new SpeakerOnboardingScreen(this);
    }
}


