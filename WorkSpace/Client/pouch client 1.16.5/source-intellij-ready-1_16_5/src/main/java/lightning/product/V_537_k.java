/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import lightning.product.Module;
import lightning.product.c_2086_l;

public class V_537_k {
    private static final Map<String, Map<String, String>> n_1700_B = new ConcurrentHashMap<String, Map<String, String>>();

    public static Map<String, String> n_1700_B(String resourcePath) {
        if (resourcePath == null || resourcePath.isEmpty()) {
            return Map.of();
        }
        return n_1700_B.computeIfAbsent(resourcePath, path -> {
            try (InputStream stream = V_537_k.J_1907_R(path);){
                if (stream == null) {
                    Map map2 = Map.of();
                    return map2;
                }
                HashMap localeMap = new HashMap();
                new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8)).lines().map(String::trim).filter(line -> !line.isEmpty() && !line.startsWith("#")).forEach(line -> {
                    int separator = line.indexOf(61);
                    if (separator > 0) {
                        String key = line.substring(0, separator).trim();
                        String value = line.substring(separator + 1).trim();
                        if (!key.isEmpty()) {
                            localeMap.put(key, value);
                        }
                    }
                });
                Map map = Map.copyOf(localeMap);
                return map;
            }
            catch (Exception e) {
                return Map.of();
            }
        });
    }

    private static InputStream J_1907_R(String path) {
        InputStream stream = V_537_k.class.getClassLoader().getResourceAsStream(path);
        if (stream != null) {
            return stream;
        }
        stream = V_537_k.class.getResourceAsStream("/" + path);
        if (stream != null) {
            return stream;
        }
        stream = ClassLoader.getSystemResourceAsStream(path);
        if (stream != null) {
            return stream;
        }
        try {
            return Files.newInputStream(Path.of(path, new String[0]), new OpenOption[0]);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static String n_1700_B(Module module, float top, float bottom, float visibleTop, float visibleBottom) {
        String localized;
        Objects.requireNonNull(module);
        if (!(bottom > visibleTop) || !(top < visibleBottom)) {
            return null;
        }
        String desc = module.P_1922_E();
        if (desc != null && desc.startsWith("module.") && (localized = c_2086_l.J_1907_R(desc)) != null && !localized.isEmpty()) {
            return localized;
        }
        return desc == null || desc.isEmpty() ? null : desc;
    }

    public static String n_1700_B(String moduleName, String settingName) {
        if (moduleName == null || settingName == null) {
            return settingName;
        }
        String key = "module." + V_537_k.R_4764_Y(moduleName) + ".setting." + V_537_k.R_4764_Y(settingName);
        String localized = c_2086_l.J_1907_R(key);
        return localized != null && !localized.isEmpty() ? localized : settingName;
    }

    public static String n_1700_B(String moduleName, String settingName, String subSettingName) {
        if (moduleName == null || settingName == null || subSettingName == null) {
            return subSettingName;
        }
        String key = "module." + V_537_k.R_4764_Y(moduleName) + ".setting." + V_537_k.R_4764_Y(settingName) + "." + V_537_k.R_4764_Y(subSettingName);
        String localized = c_2086_l.J_1907_R(key);
        return localized != null && !localized.isEmpty() ? localized : subSettingName;
    }

    private static String R_4764_Y(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }
        return Character.toLowerCase(name.charAt(0)) + name.substring(1);
    }
}


