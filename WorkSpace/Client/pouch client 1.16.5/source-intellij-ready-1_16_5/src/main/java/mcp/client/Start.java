/*
 * Decompiled with CFR 0.152.
 */
package mcp.client;

import java.util.Arrays;
import net.minecraft.client.main.Main;

public class Start {
    public static void main(String[] args) {
        String assets = System.getenv().containsKey("assetDirectory") ? System.getenv("assetDirectory") : "assets";
        Main.main(Start.n_1700_B(new String[]{"--version", "mcp", "--accessToken", "0", "--assetsDir", assets, "--assetIndex", "1.16", "--userProperties", "{}"}, args));
    }

    public static <T> T[] n_1700_B(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}

