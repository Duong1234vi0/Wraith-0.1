/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.maxhenkel.configbuilder.entry.ConfigEntry
 *  de.maxhenkel.configbuilder.entry.EnumConfigEntry
 *  javax.annotation.Nullable
 */
package mods.voicechat.config;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import de.maxhenkel.configbuilder.entry.ConfigEntry;
import de.maxhenkel.configbuilder.entry.EnumConfigEntry;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import javax.annotation.Nullable;
import lightning.product.H_4757_Q;
import lightning.product.Y_1740_V;
import lightning.product.e_3591_l;
import mods.voicechat.Voicechat;
import mods.voicechat.VoicechatClient;
import mods.voicechat.config.ClientConfig;
import mods.voicechat.config.ServerConfig;
import mods.voicechat.eventforge.WorldEvent;

public class ConfigMigrator {
    private static final String MOVED_CONFIG_KEY = "moved";
    private static final H_4757_Q SERVERCONFIG = new H_4757_Q("serverconfig");

    public static void migrateClientConfig() {
        ConfigMigrator.migrateConfig(ClientConfig.class, VoicechatClient.CLIENT_CONFIG, Voicechat.getConfigFolder().resolve("voicechat-client.toml"), true, "config/voicechat/voicechat-client.properties");
    }

    @Y_1740_V
    public void onLoadLevel(WorldEvent.Load event) {
        if (!(event.getWorld() instanceof e_3591_l)) {
            return;
        }
        e_3591_l serverLevel = (e_3591_l)event.getWorld();
        ConfigMigrator.migrateConfig(ServerConfig.class, Voicechat.SERVER_CONFIG, serverLevel.T_2506_i().n_1700_B(SERVERCONFIG).resolve("voicechat-server.toml"), serverLevel.T_2506_i().g_164_R(), "config/voicechat/voicechat-server.properties", "voice_chat");
    }

    public static <T> void migrateConfig(Class<T> configClass, T modConfig, Path forgeConfig, boolean copyValues, String newPath) {
        ConfigMigrator.migrateConfig(configClass, modConfig, forgeConfig, copyValues, newPath, null);
    }

    public static <T> void migrateConfig(Class<T> configClass, T modConfig, Path forgeConfig, boolean copyValues, String newPath, @Nullable String configKey) {
        try (CommentedFileConfig commentedConfig = (CommentedFileConfig)CommentedFileConfig.builder(forgeConfig).build();){
            CommentedFileConfig config;
            Boolean migrated;
            if (Files.isRegularFile(forgeConfig, new LinkOption[0])) {
                commentedConfig.load();
            }
            if ((migrated = (Boolean)commentedConfig.get(MOVED_CONFIG_KEY)) != null && migrated.booleanValue()) {
                return;
            }
            CommentedConfig commentedConfig2 = config = configKey == null ? commentedConfig : (CommentedConfig)commentedConfig.get(configKey);
            if (config != null && copyValues) {
                ConfigMigrator.migrateConfigValues(configClass, modConfig, config);
            }
            commentedConfig.clear();
            commentedConfig.set(MOVED_CONFIG_KEY, (Object)true);
            commentedConfig.setComment(MOVED_CONFIG_KEY, String.format(" This config has been moved to %s", newPath));
            commentedConfig.save();
            Voicechat.LOGGER.info("Successfully migrated config {}", forgeConfig.getFileName());
        }
    }

    public static <T> void migrateConfigValues(Class<T> configClass, T config, CommentedConfig forgeConfig) {
        Field[] declaredFields = configClass.getDeclaredFields();
        ConfigEntry randomEntry = null;
        for (Field field : declaredFields) {
            try {
                field.setAccessible(true);
                Object configEntry = field.get(config);
                if (!(configEntry instanceof ConfigEntry)) continue;
                ConfigEntry entry = (ConfigEntry)configEntry;
                if (randomEntry == null) {
                    randomEntry = entry;
                }
                Object forgeValue = forgeConfig.get(entry.getKey());
                ConfigMigrator.copyEntry(forgeValue, entry);
            }
            catch (IllegalAccessException e) {
                Voicechat.LOGGER.error("Failed to migrate config entry {}", field.getName(), e);
            }
        }
        if (randomEntry != null) {
            randomEntry.save();
        }
    }

    private static void copyEntry(Object forgeValue, ConfigEntry configEntry) {
        try {
            if (forgeValue == null) {
                return;
            }
            if (configEntry instanceof EnumConfigEntry) {
                EnumConfigEntry enumConfigEntry = (EnumConfigEntry)configEntry;
            }
            if (configEntry.getDefault().equals(forgeValue)) {
                return;
            }
            configEntry.set(forgeValue);
            Voicechat.LOGGER.debug("Migrated config entry '{}' with value '{}'", configEntry.getKey(), forgeValue);
        }
        catch (Throwable e) {
            Voicechat.LOGGER.error("Failed to migrate config entry {}", configEntry.getKey(), e);
        }
    }
}

