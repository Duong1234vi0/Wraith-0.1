/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 */
package net.optifine.util;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import lightning.product.InactiveProfiler;
import lightning.product.PreparableReloadListener;
import lightning.product.MinecraftClient;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.o_3912_n;
import net.optifine.Config;
import net.optifine.reflect.Reflector;
import net.optifine.util.PropertiesOrdered;

public class FontUtils {
    public static Properties readFontProperties(g_2336_b locationFontTexture) {
        String s = locationFontTexture.J_1907_R();
        PropertiesOrdered properties = new PropertiesOrdered();
        String s1 = ".png";
        if (!s.endsWith(s1)) {
            return properties;
        }
        String s2 = s.substring(0, s.length() - s1.length()) + ".properties";
        try {
            g_2336_b resourcelocation = new g_2336_b(locationFontTexture.R_4764_Y(), s2);
            InputStream inputstream = Config.getResourceStream(Config.getResourceManager(), resourcelocation);
            if (inputstream == null) {
                return properties;
            }
            Config.log("Loading " + s2);
            properties.load(inputstream);
            inputstream.close();
        }
        catch (FileNotFoundException resourcelocation) {
        }
        catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
        return properties;
    }

    public static Int2ObjectMap<Float> readCustomCharWidths(Properties props) {
        Int2ObjectOpenHashMap int2objectmap = new Int2ObjectOpenHashMap();
        for (String string : props.keySet()) {
            String s3;
            float f;
            String s2;
            int i;
            String s1;
            if (!string.startsWith(s1 = "width.") || (i = Config.parseInt(s2 = string.substring(s1.length()), -1)) < 0 || !((f = Config.parseFloat(s3 = props.getProperty(string), -1.0f)) >= 0.0f)) continue;
            char c0 = (char)i;
            int2objectmap.put((int)c0, (Object)new Float(f));
        }
        return int2objectmap;
    }

    public static float readFloat(Properties props, String key, float defOffset) {
        String s = props.getProperty(key);
        if (s == null) {
            return defOffset;
        }
        float f = Config.parseFloat(s, Float.MIN_VALUE);
        if (f == Float.MIN_VALUE) {
            Config.warn("Invalid value for " + key + ": " + s);
            return defOffset;
        }
        return f;
    }

    public static boolean readBoolean(Properties props, String key, boolean defVal) {
        String s = props.getProperty(key);
        if (s == null) {
            return defVal;
        }
        String s1 = s.toLowerCase().trim();
        if (!s1.equals("true") && !s1.equals("on")) {
            if (!s1.equals("false") && !s1.equals("off")) {
                Config.warn("Invalid value for " + key + ": " + s);
                return defVal;
            }
            return false;
        }
        return true;
    }

    public static g_2336_b getHdFontLocation(g_2336_b fontLoc) {
        if (!Config.isCustomFonts()) {
            return fontLoc;
        }
        if (fontLoc == null) {
            return fontLoc;
        }
        if (!Config.isMinecraftThread()) {
            return fontLoc;
        }
        Object s = fontLoc.J_1907_R();
        String s1 = "textures/";
        String s2 = "optifine/";
        if (!((String)s).startsWith(s1)) {
            return fontLoc;
        }
        s = ((String)s).substring(s1.length());
        s = s2 + (String)s;
        g_2336_b resourcelocation = new g_2336_b(fontLoc.R_4764_Y(), (String)s);
        return Config.hasResource(Config.getResourceManager(), resourcelocation) ? resourcelocation : fontLoc;
    }

    public static void reloadFonts() {
        PreparableReloadListener.n_1700_B ifuturereloadlistener$istage = new PreparableReloadListener.n_1700_B(){

            @Override
            public <T> CompletableFuture<T> markCompleteAwaitingOthers(T x) {
                return CompletableFuture.completedFuture(x);
            }
        };
        Executor executor = j_3341_s.u_1723_Y();
        MinecraftClient minecraft = MinecraftClient.A_4115_X();
        o_3912_n fontresourcemanager = (o_3912_n)Reflector.getFieldValue(minecraft, Reflector.Minecraft_fontResourceManager);
        if (fontresourcemanager != null) {
            fontresourcemanager.J_1907_R().reload(ifuturereloadlistener$istage, Config.getResourceManager(), InactiveProfiler.n_1700_B, InactiveProfiler.n_1700_B, executor, minecraft);
        }
    }
}



