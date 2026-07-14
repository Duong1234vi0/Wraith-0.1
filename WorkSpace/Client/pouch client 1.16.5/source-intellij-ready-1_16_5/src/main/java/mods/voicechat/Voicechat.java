/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.maxhenkel.configbuilder.ConfigBuilder
 */
package mods.voicechat;

import de.maxhenkel.configbuilder.ConfigBuilder;
import java.nio.file.Path;
import java.util.regex.Pattern;
import lightning.product.MinecraftClient;
import mods.voicechat.BuildConstants;
import mods.voicechat.command.VoicechatCommands;
import mods.voicechat.config.ServerConfig;
import mods.voicechat.config.Translations;
import mods.voicechat.intercompatibility.CommonCompatibilityManager;
import mods.voicechat.logging.Log4JVoicechatLogger;
import mods.voicechat.logging.VoicechatLogger;
import mods.voicechat.plugins.PluginManager;
import mods.voicechat.voice.server.ServerVoiceEvents;

public abstract class Voicechat {
    public static final String MODID = "voicechat";
    public static final VoicechatLogger LOGGER = new Log4JVoicechatLogger("voicechat");
    public static ServerVoiceEvents SERVER;
    public static ServerConfig SERVER_CONFIG;
    public static Translations TRANSLATIONS;
    public static int COMPATIBILITY_VERSION;
    public static final Pattern GROUP_REGEX;

    public void initialize() {
        if (Voicechat.debugMode()) {
            LOGGER.warn("Running in debug mode - Don't leave this enabled in production!", new Object[0]);
        }
        LOGGER.info("Compatibility version {}", COMPATIBILITY_VERSION);
        this.initializeConfigs();
        CommonCompatibilityManager.INSTANCE.getNetManager().init();
        SERVER = new ServerVoiceEvents();
        this.initPlugins();
        this.registerCommands();
    }

    protected void initPlugins() {
        PluginManager.instance().init();
    }

    protected void registerCommands() {
        CommonCompatibilityManager.INSTANCE.onRegisterServerCommands(VoicechatCommands::register);
    }

    public void initializeConfigs() {
        SERVER_CONFIG = (ServerConfig)ConfigBuilder.builder(ServerConfig::new).path(this.getVoicechatConfigFolderInternal().resolve("voicechat-server.properties")).build();
        TRANSLATIONS = (Translations)ConfigBuilder.builder(Translations::new).path(this.getVoicechatConfigFolderInternal().resolve("translations.properties")).build();
    }

    public static boolean debugMode() {
        return false;
    }

    protected Path getVoicechatConfigFolderInternal() {
        return Voicechat.getVoicechatConfigFolder();
    }

    public static Path getVoicechatConfigFolder() {
        return MinecraftClient.A_4115_X().M_182_A.toPath().resolve(MODID);
    }

    public static Path getConfigFolder() {
        return MinecraftClient.A_4115_X().M_182_A.toPath().resolve(MODID).resolve("config");
    }

    static {
        COMPATIBILITY_VERSION = BuildConstants.COMPATIBILITY_VERSION;
        GROUP_REGEX = Pattern.compile("^[^\\n\\r\\t\\s][^\\n\\r\\t]{0,23}$");
    }
}


