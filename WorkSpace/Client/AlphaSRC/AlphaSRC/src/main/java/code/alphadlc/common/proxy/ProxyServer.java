/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.ModInitializer
 *  net.minecraft.class_4185
 */
package code.alphadlc.common.proxy;

import code.alphadlc.common.proxy.Config;
import code.alphadlc.common.proxy.Proxy;
import net.fabricmc.api.ModInitializer;
import net.minecraft.class_4185;

public class ProxyServer
implements ModInitializer {
    public static boolean proxyEnabled = false;
    public static Proxy proxy = new Proxy();
    public static Proxy lastUsedProxy = new Proxy();
    public static class_4185 proxyMenuButton;

    public static String getLastUsedProxyIp() {
        return ProxyServer.lastUsedProxy.ipPort.isEmpty() ? "none" : lastUsedProxy.getIp();
    }

    public void onInitialize() {
        Config.loadConfig();
    }
}
