/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  com.google.gson.reflect.TypeToken
 *  org.apache.commons.io.FileUtils
 *  org.apache.commons.io.IOUtils
 */
package mods.proxy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import lightning.product.MinecraftAccess;
import mods.proxy.Proxy;
import mods.proxy.ProxyServer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class Config {
    private static final String CONFIG_PATH = String.valueOf(MinecraftAccess.c_3005_b.M_182_A.toPath()) + "/config/ProxyServerConfig.json";
    public static HashMap<String, Proxy> accounts = new HashMap();
    public static String lastPlayerName = "";
    public static final List<Proxy> SHARED_FREE_PROXIES = new ArrayList<Proxy>();
    private static final int MAX_PROXIES_PER_SOURCE = 120;
    private static final Map<String, Integer> SOURCE_SCORE = new HashMap<String, Integer>();
    private static final Map<String, String> PROXY_SOURCE_BY_KEY = new HashMap<String, String>();
    private static String lastWorkingProxyKey = "";
    private static final String[] FREE_SOCKS4_SOURCES = new String[]{"https://api.proxyscrape.com/v4/free-proxy-list/get?request=displayproxies&protocol=socks4&timeout=7000&country=all&format=text", "https://raw.githubusercontent.com/TheSpeedX/SOCKS-List/master/socks4.txt", "https://raw.githubusercontent.com/ShiftyTR/Proxy-List/master/socks4.txt", "https://raw.githubusercontent.com/monosans/proxy-list/main/proxies/socks4.txt", "https://raw.githubusercontent.com/Zaeem20/FREE_PROXIES_LIST/master/socks4.txt", "https://raw.githubusercontent.com/roosterkid/openproxylist/main/SOCKS4_RAW.txt", "https://raw.githubusercontent.com/saschazesiger/Free-Proxies/master/proxies/socks4.txt"};
    private static final String[] FREE_SOCKS5_SOURCES = new String[]{"https://api.proxyscrape.com/v4/free-proxy-list/get?request=displayproxies&protocol=socks5&timeout=7000&country=all&format=text", "https://raw.githubusercontent.com/TheSpeedX/SOCKS-List/master/socks5.txt", "https://raw.githubusercontent.com/ShiftyTR/Proxy-List/master/socks5.txt", "https://raw.githubusercontent.com/monosans/proxy-list/main/proxies/socks5.txt", "https://raw.githubusercontent.com/Zaeem20/FREE_PROXIES_LIST/master/socks5.txt", "https://raw.githubusercontent.com/roosterkid/openproxylist/main/SOCKS5_RAW.txt", "https://raw.githubusercontent.com/saschazesiger/Free-Proxies/master/proxies/socks5.txt", "https://raw.githubusercontent.com/hookzof/socks5_list/master/proxy.txt"};

    public static void loadConfig() {
        File configFile = new File(CONFIG_PATH);
        try {
            if (!configFile.exists()) {
                if (!configFile.createNewFile()) {
                    System.out.println("Error creating ProxyServerConfig.json file");
                }
                if (SHARED_FREE_PROXIES.isEmpty()) {
                    Config.refreshSharedFreeProxies();
                }
                return;
            }
            String configString = FileUtils.readFileToString((File)configFile, (String)"UTF-8");
            if (!configString.isEmpty()) {
                JsonObject configJson = new JsonParser().parse(configString).getAsJsonObject();
                ProxyServer.proxyEnabled = configJson.get("proxy-enabled").getAsBoolean();
                Type type = new TypeToken<HashMap<String, Proxy>>(){}.getType();
                accounts = (HashMap)new Gson().fromJson(configJson.get("accounts"), type);
                if (accounts == null) {
                    accounts = new HashMap();
                }
                Config.loadOptionalStats(configJson);
            }
        }
        catch (Exception e) {
            System.out.println("Error reading ProxyServerConfig.json file");
            e.printStackTrace();
        }
        if (SHARED_FREE_PROXIES.isEmpty()) {
            Config.refreshSharedFreeProxies();
        }
    }

    public static void setDefaultProxy(Proxy proxy) {
        accounts.put("", proxy);
    }

    public static void saveConfig() {
        try {
            JsonElement accountsJsonObject = new Gson().toJsonTree(accounts);
            JsonObject configJson = new JsonObject();
            configJson.addProperty("proxy-enabled", Boolean.valueOf(ProxyServer.proxyEnabled));
            configJson.add("accounts", accountsJsonObject);
            Config.saveOptionalStats(configJson);
            Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();
            FileUtils.write((File)new File(CONFIG_PATH), (CharSequence)gsonPretty.toJson((JsonElement)configJson), (Charset)StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            System.out.println("Error writing ProxyServerConfig.json file");
            e.printStackTrace();
        }
    }

    public static synchronized void refreshSharedFreeProxies() {
        SHARED_FREE_PROXIES.clear();
        PROXY_SOURCE_BY_KEY.clear();
        Config.loadFromSources(FREE_SOCKS4_SOURCES, Proxy.ProxyType.SOCKS4);
        Config.loadFromSources(FREE_SOCKS5_SOURCES, Proxy.ProxyType.SOCKS5);
        Config.sortSharedProxyPool();
    }

    public static synchronized List<Proxy> getSharedFreeProxies() {
        if (SHARED_FREE_PROXIES.isEmpty()) {
            Config.refreshSharedFreeProxies();
        }
        return Collections.unmodifiableList(SHARED_FREE_PROXIES);
    }

    private static void loadFromSources(String[] sources, Proxy.ProxyType type) {
        block7: for (int sourceIndex = 0; sourceIndex < sources.length; ++sourceIndex) {
            String source = sources[sourceIndex];
            try {
                String response;
                try (InputStream stream = new URL(source).openStream();){
                    byte[] bytes = IOUtils.toByteArray((InputStream)stream);
                    response = new String(bytes, StandardCharsets.UTF_8);
                }
                if (response == null || response.isEmpty()) continue;
                String[] lines = response.split("\\r?\\n");
                int added = 0;
                for (String rawLine : lines) {
                    String key;
                    String line = rawLine == null ? "" : rawLine.trim();
                    Proxy parsed = Config.parseProxyLine(line, type);
                    if (parsed == null || Config.containsProxyKey(key = Config.proxyKey(parsed))) continue;
                    SHARED_FREE_PROXIES.add(parsed);
                    PROXY_SOURCE_BY_KEY.put(key, Config.sourceId(type, sourceIndex, source));
                    if (++added >= 120) continue block7;
                }
                continue;
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    private static boolean containsProxyKey(String key) {
        if (key == null || key.isEmpty()) {
            return false;
        }
        for (Proxy proxy : SHARED_FREE_PROXIES) {
            if (proxy == null || !key.equals(Config.proxyKey(proxy))) continue;
            return true;
        }
        return false;
    }

    private static boolean isValidIpPort(String ipPort) {
        if (ipPort == null) {
            return false;
        }
        String value = ipPort.trim();
        int sep = value.lastIndexOf(58);
        if (sep <= 0 || sep >= value.length() - 1) {
            return false;
        }
        String host = value.substring(0, sep).trim();
        String portStr = value.substring(sep + 1).trim();
        if (host.isEmpty()) {
            return false;
        }
        try {
            int port = Integer.parseInt(portStr);
            return port >= 1 && port <= 65535;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    private static Proxy parseProxyLine(String line, Proxy.ProxyType defaultType) {
        Object ipPort;
        if (line == null) {
            return null;
        }
        String value = line.trim();
        if (value.isEmpty()) {
            return null;
        }
        if (value.startsWith("socks5://")) {
            value = value.substring("socks5://".length());
            defaultType = Proxy.ProxyType.SOCKS5;
        } else if (value.startsWith("socks4://")) {
            value = value.substring("socks4://".length());
            defaultType = Proxy.ProxyType.SOCKS4;
        }
        String username = "";
        String password = "";
        int at = value.lastIndexOf(64);
        if (at > 0 && at < value.length() - 1) {
            String auth = value.substring(0, at);
            ipPort = value.substring(at + 1);
            String[] authParts = auth.split(":", 2);
            username = authParts[0];
            if (authParts.length > 1) {
                password = authParts[1];
            }
        } else {
            String[] parts = value.split(":");
            if (parts.length >= 4) {
                ipPort = parts[0] + ":" + parts[1];
                username = parts[2];
                password = parts[3];
            } else {
                ipPort = value;
            }
        }
        if (!Config.isValidIpPort((String)ipPort)) {
            return null;
        }
        Proxy proxy = new Proxy();
        proxy.type = defaultType;
        proxy.ipPort = ipPort;
        proxy.username = username == null ? "" : username.trim();
        proxy.password = password == null ? "" : password.trim();
        return proxy;
    }

    public static synchronized void markProxyWorking(Proxy proxy, long pingMs) {
        if (proxy == null || !Config.isValidIpPort(proxy.ipPort)) {
            return;
        }
        String key = Config.proxyKey(proxy);
        if (key == null || key.isEmpty()) {
            return;
        }
        lastWorkingProxyKey = key;
        String source = PROXY_SOURCE_BY_KEY.get(key);
        if (source != null) {
            int bonus = pingMs > 0L && pingMs <= 300L ? 3 : (pingMs > 0L && pingMs <= 800L ? 2 : 1);
            SOURCE_SCORE.put(source, SOURCE_SCORE.getOrDefault(source, 0) + bonus);
        }
        Config.saveConfig();
        Config.sortSharedProxyPool();
    }

    private static void sortSharedProxyPool() {
        SHARED_FREE_PROXIES.sort(Comparator.comparing(p -> !Config.isLastWorking(p)).thenComparing(p -> -Config.sourceScore(p)).thenComparing(p -> p.type.name()).thenComparing(p -> p.ipPort == null ? "" : p.ipPort));
    }

    private static boolean isLastWorking(Proxy p) {
        return p != null && Config.proxyKey(p).equals(lastWorkingProxyKey);
    }

    private static int sourceScore(Proxy p) {
        if (p == null) {
            return 0;
        }
        String source = PROXY_SOURCE_BY_KEY.get(Config.proxyKey(p));
        if (source == null) {
            return 0;
        }
        return SOURCE_SCORE.getOrDefault(source, 0);
    }

    private static String proxyKey(Proxy p) {
        if (p == null || p.ipPort == null) {
            return "";
        }
        String type = p.type == null ? Proxy.ProxyType.SOCKS5.name() : p.type.name();
        String user = p.username == null ? "" : p.username;
        String pass = p.password == null ? "" : p.password;
        return type + "|" + p.ipPort.trim().toLowerCase(Locale.ROOT) + "|" + user + "|" + pass;
    }

    private static String sourceId(Proxy.ProxyType type, int index, String url) {
        return type.name() + "#" + index + "#" + (url == null ? "" : url);
    }

    private static void loadOptionalStats(JsonObject configJson) {
        JsonObject sourceScoreJson;
        if (configJson.has("last-working-proxy") && !configJson.get("last-working-proxy").isJsonNull()) {
            lastWorkingProxyKey = configJson.get("last-working-proxy").getAsString();
        }
        SOURCE_SCORE.clear();
        JsonObject jsonObject = sourceScoreJson = configJson.has("source-scores") && configJson.get("source-scores").isJsonObject() ? configJson.getAsJsonObject("source-scores") : null;
        if (sourceScoreJson != null) {
            for (Map.Entry e : sourceScoreJson.entrySet()) {
                try {
                    SOURCE_SCORE.put((String)e.getKey(), ((JsonElement)e.getValue()).getAsInt());
                }
                catch (Exception exception) {}
            }
        }
    }

    private static void saveOptionalStats(JsonObject configJson) {
        configJson.addProperty("last-working-proxy", lastWorkingProxyKey == null ? "" : lastWorkingProxyKey);
        JsonObject scores = new JsonObject();
        LinkedHashMap sorted = new LinkedHashMap();
        SOURCE_SCORE.entrySet().stream().sorted((a, b) -> Integer.compare((Integer)b.getValue(), (Integer)a.getValue())).forEach(entry -> sorted.put((String)entry.getKey(), (Integer)entry.getValue()));
        for (Map.Entry e : sorted.entrySet()) {
            scores.addProperty((String)e.getKey(), (Number)e.getValue());
        }
        configJson.add("source-scores", (JsonElement)scores);
    }
}


