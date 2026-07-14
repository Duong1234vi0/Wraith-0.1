package mods.proxy;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Config {
    private static final File CONFIG_FILE = new File("config", "ProxyServerConfig.json");
    public static HashMap<String, Proxy> accounts = new HashMap<>();
    public static String lastPlayerName = "";
    public static final List<Proxy> SHARED_FREE_PROXIES = new ArrayList<>();

    public static void loadConfig() {
        ensureConfigFile();
        SHARED_FREE_PROXIES.clear();
    }

    public static void setDefaultProxy(Proxy proxy) {
        if (proxy != null) {
            accounts.put("", proxy);
        }
    }

    public static void saveConfig() {
        ensureConfigFile();
        try {
            Files.writeString(CONFIG_FILE.toPath(), "{\n  \"proxy-enabled\": false,\n  \"accounts\": {}\n}\n", StandardCharsets.UTF_8);
        } catch (IOException ignored) {
        }
    }

    public static synchronized void refreshSharedFreeProxies() {
        SHARED_FREE_PROXIES.clear();
    }

    public static synchronized List<Proxy> getSharedFreeProxies() {
        return Collections.unmodifiableList(SHARED_FREE_PROXIES);
    }

    public static synchronized void markProxyWorking(Proxy proxy, long pingMs) {
        saveConfig();
    }

    private static void ensureConfigFile() {
        try {
            File parent = CONFIG_FILE.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            if (!CONFIG_FILE.exists()) {
                Files.writeString(CONFIG_FILE.toPath(), "{\n  \"proxy-enabled\": false,\n  \"accounts\": {}\n}\n", StandardCharsets.UTF_8);
            }
        } catch (IOException ignored) {
        }
    }
}