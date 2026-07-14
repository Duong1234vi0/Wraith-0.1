/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.sun.jna.Platform
 *  de.maxhenkel.configbuilder.ConfigBuilder
 *  de.maxhenkel.configbuilder.entry.ConfigEntry
 *  de.maxhenkel.configbuilder.entry.DoubleConfigEntry
 */
package mods.voicechat.config;

import com.sun.jna.Platform;
import de.maxhenkel.configbuilder.ConfigBuilder;
import de.maxhenkel.configbuilder.entry.ConfigEntry;
import de.maxhenkel.configbuilder.entry.DoubleConfigEntry;
import mods.voicechat.integration.freecam.FreecamMode;
import mods.voicechat.intercompatibility.CommonCompatibilityManager;
import mods.voicechat.voice.client.GroupPlayerIconOrientation;
import mods.voicechat.voice.client.MicrophoneActivationType;
import mods.voicechat.voice.client.speaker.AudioType;

public class ClientConfig {
    private static final int CONFIG_VERSION = 1;
    public ConfigEntry<Integer> configVersion;
    public ConfigEntry<Boolean> onboardingFinished;
    public DoubleConfigEntry voiceChatVolume;
    public ConfigEntry<Double> voiceActivationThreshold;
    public ConfigEntry<Boolean> vad;
    public ConfigEntry<Double> microphoneGain;
    public ConfigEntry<Boolean> agc;
    public ConfigEntry<MicrophoneActivationType> microphoneActivationType;
    public ConfigEntry<Integer> outputBufferSize;
    public ConfigEntry<Integer> audioPacketThreshold;
    public ConfigEntry<Integer> voiceDeactivationDelay;
    public ConfigEntry<Integer> pttDeactivationDelay;
    public ConfigEntry<String> microphone;
    public ConfigEntry<String> speaker;
    public ConfigEntry<Boolean> muted;
    public ConfigEntry<Boolean> disabled;
    public ConfigEntry<Boolean> hideIcons;
    public ConfigEntry<Boolean> showNametagIcons;
    public ConfigEntry<Boolean> showHudIcons;
    public ConfigEntry<Boolean> showGroupHud;
    public ConfigEntry<Boolean> showOwnGroupIcon;
    public ConfigEntry<Double> groupHudIconScale;
    public ConfigEntry<GroupPlayerIconOrientation> groupPlayerIconOrientation;
    public ConfigEntry<Integer> groupPlayerIconPosX;
    public ConfigEntry<Integer> groupPlayerIconPosY;
    public ConfigEntry<Integer> hudIconPosX;
    public ConfigEntry<Integer> hudIconPosY;
    public ConfigEntry<Double> hudIconScale;
    public ConfigEntry<String> recordingDestination;
    public ConfigEntry<Integer> recordingQuality;
    public ConfigEntry<Boolean> denoiser;
    public ConfigEntry<Boolean> runLocalServer;
    public ConfigEntry<Boolean> javaMicrophoneImplementation;
    public ConfigEntry<Boolean> showFakePlayersDisconnected;
    public ConfigEntry<Boolean> offlinePlayerVolumeAdjustment;
    public ConfigEntry<AudioType> audioType;
    public ConfigEntry<Boolean> useNatives;
    public ConfigEntry<FreecamMode> freecamMode;
    public ConfigEntry<Boolean> muteOnJoin;

    public ClientConfig(ConfigBuilder builder) {
        builder.header(new String[]{String.format("%s client config v%s", CommonCompatibilityManager.INSTANCE.getModName(), CommonCompatibilityManager.INSTANCE.getModVersion())});
        this.configVersion = builder.integerEntry("config_version", Integer.valueOf(1), new String[]{"The config version - Used for migration", "WARNING: DO NOT CHANGE THIS VALUE"});
        this.onboardingFinished = builder.booleanEntry("onboarding_finished", Boolean.valueOf(false), new String[]{"If the voice chat onboarding process has been finished"});
        this.voiceChatVolume = builder.doubleEntry("voice_chat_volume", Double.valueOf(1.0), Double.valueOf(0.0), Double.valueOf(3.0), new String[]{"The voice chat volume"});
        this.voiceActivationThreshold = builder.doubleEntry("voice_activation_threshold", Double.valueOf(-50.0), Double.valueOf(-127.0), Double.valueOf(0.0), new String[]{"The threshold for the voice activation method (in dB)"});
        this.vad = builder.booleanEntry("voice_activity_detection", Boolean.valueOf(true), new String[]{"If automatic voice detection should be used"});
        this.microphoneGain = builder.doubleEntry("microphone_gain", Double.valueOf(0.0), Double.valueOf(-40.0), Double.valueOf(24.0), new String[]{"The voice chat microphone gain"});
        this.agc = builder.booleanEntry("automatic_gain_control", Boolean.valueOf(true), new String[]{"Enable automatic gain control"});
        this.microphoneActivationType = builder.enumEntry("microphone_activation_type", (Enum)MicrophoneActivationType.PTT, new String[]{"The microphone activation method", "Valid values are 'PTT' and 'VOICE'"});
        this.outputBufferSize = builder.integerEntry("output_buffer_size", Integer.valueOf(5), Integer.valueOf(1), Integer.valueOf(16), new String[]{"The size of the audio output buffer (in packets)", "Higher values mean a higher latency but less crackling", "Increase this value if you have an unstable internet connection"});
        this.audioPacketThreshold = builder.integerEntry("audio_packet_threshold", Integer.valueOf(3), Integer.valueOf(0), Integer.valueOf(16), new String[]{"The maximum number of audio packets that should be held back if a packet arrives out of order or is dropped", "This prevents audio packets that are only slightly out of order from being discarded", "Set this to 0 to disable"});
        this.voiceDeactivationDelay = builder.integerEntry("voice_deactivation_delay", Integer.valueOf(25), Integer.valueOf(0), Integer.valueOf(100), new String[]{"The time it takes for the microphone to deactivate when using voice activation", "A value of 1 means 20 milliseconds, 2=40 ms, 3=60 ms, and so on"});
        this.pttDeactivationDelay = builder.integerEntry("ptt_deactivation_delay", Integer.valueOf(5), Integer.valueOf(0), Integer.valueOf(100), new String[]{"The time it takes for the microphone to deactivate when using push to talk", "A value of 1 means 20 milliseconds, 2=40 ms, 3=60 ms, and so on"});
        this.microphone = builder.stringEntry("microphone", "", new String[]{"The microphone used by the voice chat", "Leave blank to use the default device"});
        this.speaker = builder.stringEntry("speaker", "", new String[]{"The speaker used by the voice chat", "Leave blank to use the default device"});
        this.muted = builder.booleanEntry("muted", Boolean.valueOf(true), new String[]{"If the microphone is muted (only relevant for the voice activation method)"});
        this.disabled = builder.booleanEntry("disabled", Boolean.valueOf(false), new String[]{"If the voice chat is disabled (both sound and microphone off)"});
        this.hideIcons = builder.booleanEntry("hide_icons", Boolean.valueOf(false), new String[]{"If the voice chat HUD, group chat HUD, and other in-game icons should be hidden"});
        this.showNametagIcons = builder.booleanEntry("show_nametag_icons", Boolean.valueOf(true), new String[]{"If the voice chat icons next to player names should be visible"});
        this.showHudIcons = builder.booleanEntry("show_hud_icons", Boolean.valueOf(true), new String[]{"If the voice chat icons on the HUD should be visible"});
        this.showGroupHud = builder.booleanEntry("show_group_hud", Boolean.valueOf(true), new String[]{"If the group chat HUD should be visible"});
        this.showOwnGroupIcon = builder.booleanEntry("show_own_group_icon", Boolean.valueOf(true), new String[]{"If your own player icon should be displayed in the group chat HUD when you are in a group"});
        this.groupHudIconScale = builder.doubleEntry("group_hud_icon_scale", Double.valueOf(2.0), Double.valueOf(0.01), Double.valueOf(10.0), new String[]{"The scale of the player icons in the group chat HUD"});
        this.groupPlayerIconOrientation = builder.enumEntry("group_player_icon_orientation", (Enum)GroupPlayerIconOrientation.VERTICAL, new String[]{"The orientation of the player icons in the group chat HUD", "Valid values are 'VERTICAL' and 'HORIZONTAL'"});
        this.groupPlayerIconPosX = builder.integerEntry("group_player_icon_pos_x", Integer.valueOf(4), Integer.valueOf(Integer.MIN_VALUE), Integer.valueOf(Integer.MAX_VALUE), new String[]{"The X position of the player icons in the group chat HUD", "Negative values mean anchoring to the right instead"});
        this.groupPlayerIconPosY = builder.integerEntry("group_player_icon_pos_y", Integer.valueOf(4), Integer.valueOf(Integer.MIN_VALUE), Integer.valueOf(Integer.MAX_VALUE), new String[]{"The Y position of the player icons in the group chat HUD", "Negative values mean anchoring to the bottom instead"});
        this.hudIconPosX = builder.integerEntry("hud_icon_pos_x", Integer.valueOf(16), Integer.valueOf(Integer.MIN_VALUE), Integer.valueOf(Integer.MAX_VALUE), new String[]{"The X position of the icons in the voice chat HUD", "Negative values mean anchoring to the right instead"});
        this.hudIconPosY = builder.integerEntry("hud_icon_pos_y", Integer.valueOf(-16), Integer.valueOf(Integer.MIN_VALUE), Integer.valueOf(Integer.MAX_VALUE), new String[]{"The Y position of the icons in the voice chat HUD", "Negative values mean anchoring to the bottom instead"});
        this.hudIconScale = builder.doubleEntry("hud_icon_scale", Double.valueOf(1.0), Double.valueOf(0.01), Double.valueOf(10.0), new String[]{"The scale of the icons in the voice chat HUD, such as microphone or connection status"});
        this.recordingDestination = builder.stringEntry("recording_destination", "", new String[]{"The location where recordings should be saved", "Leave blank to use the default location"});
        this.recordingQuality = builder.integerEntry("recording_quality", Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(9), new String[]{"The quality of the recorded voice chat audio", "0 = highest quality, 9 = lowest quality"});
        this.denoiser = builder.booleanEntry("denoiser", Boolean.valueOf(false), new String[]{"If noise suppression should be enabled"});
        this.runLocalServer = builder.booleanEntry("run_local_server", Boolean.valueOf(true), new String[]{"If the voice chat should work in singleplayer or in worlds shared over LAN"});
        this.javaMicrophoneImplementation = builder.booleanEntry("java_microphone_implementation", Boolean.valueOf(Platform.isMac()), new String[]{"Whether to use the Java implementation of microphone capture instead of OpenAL"});
        this.showFakePlayersDisconnected = builder.booleanEntry("show_fake_players_disconnected", Boolean.valueOf(false), new String[]{"If fake players should have the disconnected icon above their head"});
        this.offlinePlayerVolumeAdjustment = builder.booleanEntry("offline_player_volume_adjustment", Boolean.valueOf(false), new String[]{"If the volume adjustment interface should also display offline players"});
        this.audioType = builder.enumEntry("audio_type", (Enum)AudioType.NORMAL, new String[]{"The 3D audio type", "Valid values are 'NORMAL', 'REDUCED', and 'OFF'"});
        this.useNatives = builder.booleanEntry("use_natives", Boolean.valueOf(true), new String[]{"If the mod should load native libraries on the client", "When disabled, the Java Opus implementation will be used instead, the denoiser won't be available, and you won't be able to record the voice chat audio"});
        this.freecamMode = builder.enumEntry("freecam_mode", (Enum)FreecamMode.CAMERA, new String[]{"How listening to other players should work when using freecam mods", "Valid values are 'CAMERA' and 'PLAYER'", "CAMERA: You will hear the voice chat around your camera. Whether you will still be able to hear the voice chat when the camera is far away from your character depends on the voice chat broadcast range of the server", "PLAYER: You will hear the voice chat around your character no matter where your camera is"});
        this.muteOnJoin = builder.booleanEntry("mute_on_join", Boolean.valueOf(false), new String[]{"If enabled, you will be automatically muted when joining a world"});
    }
}

