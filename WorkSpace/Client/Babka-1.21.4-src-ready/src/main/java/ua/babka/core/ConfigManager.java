package ua.babka.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import ua.babka.Babka;
import ua.babka.module.Module;
import ua.babka.module.setting.BindSetting;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ModeListSetting;
import ua.babka.module.setting.ModeSetting;
import ua.babka.module.setting.Setting;
import ua.babka.module.setting.SliderSetting;
import ua.babka.module.setting.StringSetting;
import ua.babka.module.setting.ThemeSetting;
import ua.babka.theme.Theme;

public class ConfigManager {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_FOLDER = Paths.get("BabkaCleint/configs", new String[0]);

    public static void save(String name) {
        JsonObject root = new JsonObject();
        for (Module module : Babka.getInstance().getModuleStorage().getModules()) {
            JsonObject moduleObject = new JsonObject();
            moduleObject.addProperty("enabled", Boolean.valueOf(module.isEnabled()));
            moduleObject.addProperty("keybind", (Number)module.getKey());
            JsonObject settingsObject = new JsonObject();
            for (Setting setting : module.getSettings()) {
                if (setting instanceof BooleanSetting) {
                    BooleanSetting s2 = (BooleanSetting)setting;
                    JsonObject boolObj = new JsonObject();
                    boolObj.addProperty("value", Boolean.valueOf(s2.getValue()));
                    boolObj.addProperty("key", (Number)s2.getKey());
                    settingsObject.add(setting.getName(), (JsonElement)boolObj);
                    continue;
                }
                if (setting instanceof BindSetting) {
                    BindSetting s3 = (BindSetting)setting;
                    settingsObject.addProperty(setting.getName(), (Number)s3.getValue());
                    continue;
                }
                if (setting instanceof ModeSetting) {
                    ModeSetting s4 = (ModeSetting)setting;
                    settingsObject.addProperty(setting.getName(), s4.getValue());
                    continue;
                }
                if (setting instanceof StringSetting) {
                    StringSetting s5 = (StringSetting)setting;
                    settingsObject.addProperty(setting.getName(), s5.getValue());
                    continue;
                }
                if (setting instanceof SliderSetting) {
                    SliderSetting s6 = (SliderSetting)setting;
                    settingsObject.addProperty(setting.getName(), (Number)s6.getValue());
                    continue;
                }
                if (setting instanceof ThemeSetting) {
                    ThemeSetting s7 = (ThemeSetting)setting;
                    settingsObject.addProperty(setting.getName(), s7.getValue().name);
                    continue;
                }
                if (!(setting instanceof ModeListSetting)) continue;
                ModeListSetting s8 = (ModeListSetting)setting;
                JsonArray enabledModes = new JsonArray();
                for (String name2 : s8.getEnabledModules()) {
                    enabledModes.add(name2);
                }
                settingsObject.add(setting.getName(), (JsonElement)enabledModes);
            }
            moduleObject.add("settings", (JsonElement)settingsObject);
            root.add(module.getName(), (JsonElement)moduleObject);
        }
        try {
            Files.createDirectories(CONFIG_FOLDER, new FileAttribute[0]);
            Path configFile = CONFIG_FOLDER.resolve(name + ".json");
            Files.write(configFile, gson.toJson((JsonElement)root).getBytes(StandardCharsets.UTF_8), new OpenOption[0]);
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static void load(String name) {
        Path configFile = CONFIG_FOLDER.resolve(name + ".json");
        if (!Files.exists(configFile, new LinkOption[0])) {
            return;
        }
        try (BufferedReader reader = Files.newBufferedReader(configFile, StandardCharsets.UTF_8);){
            JsonObject root = (JsonObject)gson.fromJson((Reader)reader, JsonObject.class);
            for (Module module : Babka.getInstance().getModuleStorage().getModules()) {
                if (!root.has(module.getName())) continue;
                JsonObject moduleObject = root.getAsJsonObject(module.getName());
                if (moduleObject.has("enabled")) {
                    boolean enabled = moduleObject.get("enabled").getAsBoolean();
                    module.setEnabled(enabled);
                }
                if (moduleObject.has("keybind")) {
                    int keybind = moduleObject.get("keybind").getAsInt();
                    module.setKey(keybind);
                }
                if (!moduleObject.has("settings")) continue;
                JsonObject settingsObject = moduleObject.getAsJsonObject("settings");
                block8: for (Setting setting : module.getSettings()) {
                    if (!settingsObject.has(setting.getName())) continue;
                    JsonElement element = settingsObject.get(setting.getName());
                    if (setting instanceof BooleanSetting) {
                        BooleanSetting s2 = (BooleanSetting)setting;
                        if (element.isJsonObject()) {
                            JsonObject boolObj = element.getAsJsonObject();
                            if (boolObj.has("value")) {
                                s2.setValue(boolObj.get("value").getAsBoolean());
                            }
                            if (!boolObj.has("key")) continue;
                            s2.setKey(boolObj.get("key").getAsInt());
                            continue;
                        }
                        s2.setValue(element.getAsBoolean());
                        continue;
                    }
                    if (setting instanceof BindSetting) {
                        BindSetting s3 = (BindSetting)setting;
                        s3.setValue(element.getAsInt());
                        continue;
                    }
                    if (setting instanceof ModeSetting) {
                        ModeSetting s4 = (ModeSetting)setting;
                        s4.setValue(element.getAsString());
                        continue;
                    }
                    if (setting instanceof StringSetting) {
                        StringSetting s5 = (StringSetting)setting;
                        s5.setValue(element.getAsString());
                        continue;
                    }
                    if (setting instanceof SliderSetting) {
                        SliderSetting s6 = (SliderSetting)setting;
                        s6.setValue(element.getAsDouble());
                        continue;
                    }
                    if (setting instanceof ThemeSetting) {
                        ThemeSetting s7 = (ThemeSetting)setting;
                        String themeName = element.getAsString();
                        for (Theme theme : s7.getThemes()) {
                            if (!theme.name.equals(themeName)) continue;
                            s7.setValue(theme);
                            continue block8;
                        }
                        continue;
                    }
                    if (!(setting instanceof ModeListSetting)) continue;
                    ModeListSetting s8 = (ModeListSetting)setting;
                    if (!element.isJsonArray()) continue;
                    JsonArray array = element.getAsJsonArray();
                    ArrayList<String> enabled = new ArrayList<String>();
                    for (JsonElement e2 : array) {
                        enabled.add(e2.getAsString());
                    }
                    for (BooleanSetting subSetting : s8.getSettings()) {
                        subSetting.setValue(enabled.contains(subSetting.getName()));
                    }
                }
            }
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    public static List<String> getConfigs() {
        ArrayList<String> configs;
        block9: {
            configs = new ArrayList<String>();
            try {
                if (!Files.exists(CONFIG_FOLDER, new LinkOption[0])) break block9;
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(CONFIG_FOLDER, "*.json");){
                    for (Path path : stream) {
                        String fileName = path.getFileName().toString();
                        if (!fileName.endsWith(".json")) continue;
                        configs.add(fileName.substring(0, fileName.length() - 5));
                    }
                }
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return configs;
    }

    public static List<ConfigEntry> getConfigsWithDate() {
        ArrayList<ConfigEntry> entries;
        block9: {
            entries = new ArrayList<ConfigEntry>();
            try {
                if (!Files.exists(CONFIG_FOLDER, new LinkOption[0])) break block9;
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(CONFIG_FOLDER, "*.json");){
                    for (Path path : stream) {
                        String fileName = path.getFileName().toString();
                        if (!fileName.endsWith(".json")) continue;
                        String name = fileName.substring(0, fileName.length() - 5);
                        Instant modified = Files.getLastModifiedTime(path, new LinkOption[0]).toInstant();
                        entries.add(new ConfigEntry(name, modified));
                    }
                }
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        entries.sort(Comparator.comparing(ConfigEntry::modified).reversed());
        return entries;
    }

    public static boolean exists(String name) {
        return Files.exists(CONFIG_FOLDER.resolve(name + ".json"), new LinkOption[0]);
    }

    public static boolean delete(String name) {
        try {
            return Files.deleteIfExists(CONFIG_FOLDER.resolve(name + ".json"));
        }
        catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public record ConfigEntry(String name, Instant modified) {
    }
}

