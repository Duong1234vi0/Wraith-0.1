/*
 * Decompiled with CFR 0.152.
 */
package mods.proxy;

import lightning.product.Button;
import mods.proxy.Proxy;

public class ProxyServer {
    public static boolean proxyEnabled = false;
    public static Proxy proxy = new Proxy();
    public static Proxy lastUsedProxy = new Proxy();
    public static Button proxyMenuButton;

    public static String getLastUsedProxyIp() {
        return ProxyServer.lastUsedProxy.ipPort.isEmpty() ? "none" : lastUsedProxy.getIp();
    }
}


