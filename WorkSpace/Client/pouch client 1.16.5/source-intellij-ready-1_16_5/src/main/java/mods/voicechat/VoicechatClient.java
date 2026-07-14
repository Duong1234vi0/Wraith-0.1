/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.sun.jna.Platform
 *  de.maxhenkel.configbuilder.ConfigBuilder
 */
package mods.voicechat;

import com.sun.jna.Platform;
import de.maxhenkel.configbuilder.ConfigBuilder;
import mods.voicechat.Voicechat;
import mods.voicechat.config.CategoryVolumeConfig;
import mods.voicechat.config.ClientConfig;
import mods.voicechat.config.PlayerVolumeConfig;
import mods.voicechat.intercompatibility.CommonCompatibilityManager;
import mods.voicechat.macos.VersionCheck;
import mods.voicechat.plugins.impl.opus.OpusManager;
import mods.voicechat.profile.UsernameCache;
import mods.voicechat.voice.client.ClientManager;

public abstract class VoicechatClient {
    public static ClientConfig CLIENT_CONFIG;
    public static PlayerVolumeConfig PLAYER_VOLUME_CONFIG;
    public static CategoryVolumeConfig CATEGORY_VOLUME_CONFIG;
    public static UsernameCache USERNAME_CACHE;

    public void initializeConfigs() {
        CLIENT_CONFIG = (ClientConfig)ConfigBuilder.builder(ClientConfig::new).path(Voicechat.getVoicechatConfigFolder().resolve("voicechat-client.properties")).build();
        if (VoicechatClient.CLIENT_CONFIG.configVersion.get() == null || (Integer)VoicechatClient.CLIENT_CONFIG.configVersion.get() < 1) {
            this.migrateConfigVersion();
        }
        PLAYER_VOLUME_CONFIG = new PlayerVolumeConfig(Voicechat.getVoicechatConfigFolder().resolve("player-volumes.properties"));
        CATEGORY_VOLUME_CONFIG = new CategoryVolumeConfig(Voicechat.getVoicechatConfigFolder().resolve("category-volumes.properties"));
        USERNAME_CACHE = new UsernameCache(Voicechat.getVoicechatConfigFolder().resolve("username-cache.json").toFile());
    }

    private void migrateConfigVersion() {
        int configVersion;
        Integer version = (Integer)VoicechatClient.CLIENT_CONFIG.configVersion.get();
        int n = configVersion = version != null ? version : 0;
        if (configVersion >= 1) {
            return;
        }
        Voicechat.LOGGER.info("Migrating config from version 0 to 1", new Object[0]);
        VoicechatClient.CLIENT_CONFIG.configVersion.set((Object)1).save();
        VoicechatClient.CLIENT_CONFIG.denoiser.set((Object)true).save();
        VoicechatClient.CLIENT_CONFIG.voiceActivationThreshold.set((Object)-50.0).save();
        VoicechatClient.CLIENT_CONFIG.onboardingFinished.set((Object)false).save();
    }

    public void initializeClient() {
        this.initializeConfigs();
        ClientManager.instance();
        OpusManager.opusNativeCheck();
        if (Platform.isMac()) {
            if (!VersionCheck.isMacOSNativeCompatible()) {
                Voicechat.LOGGER.warn("Your MacOS version is incompatible with {}", CommonCompatibilityManager.INSTANCE.getModName());
            }
            if (!((Boolean)VoicechatClient.CLIENT_CONFIG.javaMicrophoneImplementation.get()).booleanValue()) {
                VoicechatClient.CLIENT_CONFIG.javaMicrophoneImplementation.set((Object)true).save();
            }
        }
    }
}

