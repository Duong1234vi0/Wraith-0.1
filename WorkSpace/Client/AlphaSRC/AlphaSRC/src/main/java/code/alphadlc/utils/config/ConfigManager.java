/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.utils.config;

import code.alphadlc.AlphaDLC;
import code.alphadlc.display.hud.Notifications;
import code.alphadlc.features.impl.render.Hud;
import code.alphadlc.utils.client.managers.file.exception.FileLoadException;
import code.alphadlc.utils.client.managers.file.exception.FileSaveException;
import code.alphadlc.utils.client.sound.SoundManager;
import java.io.File;
import java.util.ArrayList;

public class ConfigManager {
    private static final String CONFIG_EXTENSION = ".json";

    public static boolean saveConfig(String configName) {
        try {
            AlphaDLC.getInstance().getFileController().saveFile(configName + CONFIG_EXTENSION);
            System.out.println("Config saved: " + configName);
            if (!configName.equals("default")) {
                Hud hud = Hud.getInstance();
                if (hud != null) {
                    SoundManager.playSound(SoundManager.ENABLE_MODULE);
                }
                Notifications.getInstance().addList("\u041a\u043e\u043d\u0444\u0438\u0433 \u00ab" + configName + "\u00bb \u0441\u043e\u0445\u0440\u0430\u043d\u0435\u043d!", 2000, false);
            }
            return true;
        }
        catch (FileSaveException e) {
            System.err.println("Failed to save config: " + e.getMessage());
            return false;
        }
    }

    public static boolean loadConfig(String configName) {
        try {
            AlphaDLC.getInstance().getFileController().loadFile(configName + CONFIG_EXTENSION);
            return true;
        }
        catch (FileLoadException e) {
            System.err.println("Failed to load config: " + e.getMessage());
            return false;
        }
    }

    public static void saveDefaultConfig() {
        try {
            AlphaDLC.getInstance().getFileController().saveFile("default.json");
        }
        catch (FileSaveException e) {
            System.err.println("Failed to auto-save default config: " + e.getMessage());
        }
    }

    public static String[] getAvailableConfigs() {
        File[] configFiles = AlphaDLC.getInstance().getClientInfoProvider().configsDir().listFiles();
        if (configFiles == null) {
            return new String[0];
        }
        ArrayList<String> configs = new ArrayList<String>();
        for (File configFile : configFiles) {
            if (!configFile.isFile() || !configFile.getName().endsWith(CONFIG_EXTENSION)) continue;
            String configName = configFile.getName().replace(CONFIG_EXTENSION, "");
            configs.add(configName);
        }
        return configs.toArray(new String[0]);
    }

    public static boolean deleteConfig(String configName) {
        try {
            File configFile = new File(AlphaDLC.getInstance().getClientInfoProvider().configsDir(), configName + CONFIG_EXTENSION);
            if (configFile.exists()) {
                boolean deleted = configFile.delete();
                if (deleted) {
                    Hud hud = Hud.getInstance();
                    if (hud != null) {
                        SoundManager.playSound(SoundManager.DISABLE_MODULE);
                    }
                    Notifications.getInstance().addList("\u041a\u043e\u043d\u0444\u0438\u0433 \u00ab" + configName + "\u00bb \u0443\u0434\u0430\u043b\u0435\u043d!", 2000, true);
                }
                return deleted;
            }
            return false;
        }
        catch (Exception e) {
            System.err.println("Failed to delete config: " + e.getMessage());
            return false;
        }
    }

    public static boolean configExists(String configName) {
        File configFile = new File(AlphaDLC.getInstance().getClientInfoProvider().configsDir(), configName + CONFIG_EXTENSION);
        return configFile.exists();
    }

    public static void loadDefaultConfig() {
        if (ConfigManager.configExists("default")) {
            ConfigManager.loadConfig("default");
            System.out.println("Default config loaded");
        } else {
            System.out.println("Default config not found, creating new one");
            ConfigManager.saveDefaultConfig();
        }
    }

    public static boolean resetConfig() {
        try {
            AlphaDLC.getInstance().getFileController().resetConfig();
            return true;
        }
        catch (FileLoadException | FileSaveException e) {
            System.err.println("Failed to reset config: " + e.getMessage());
            return false;
        }
    }
}
