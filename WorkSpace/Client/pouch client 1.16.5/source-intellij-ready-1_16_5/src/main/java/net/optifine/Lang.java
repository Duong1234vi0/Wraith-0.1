/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Splitter
 *  com.google.common.collect.Iterables
 *  org.apache.commons.io.Charsets
 *  org.apache.commons.io.IOUtils
 */
package net.optifine;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.CallSite;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import lightning.product.F_2904_S;
import lightning.product.K_1289_S;
import lightning.product.Resource;
import lightning.product.ResourceManager;
import lightning.product.PackResources;
import lightning.product.g_2336_b;
import lightning.product.i_4221_J;
import net.optifine.Config;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

public class Lang {
    private static final Splitter splitter = Splitter.on((char)'=').limit(2);
    private static final Pattern pattern = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");

    public static void resourcesReloaded() {
        HashMap map = new HashMap();
        ArrayList<CallSite> list = new ArrayList<CallSite>();
        String s = "optifine/lang/";
        String s1 = "en_us";
        String s2 = ".lang";
        list.add((CallSite)((Object)(s + s1 + s2)));
        if (!Config.getGameSettings().RealmsConfirmScreen.equals(s1)) {
            list.add((CallSite)((Object)(s + Config.getGameSettings().RealmsConfirmScreen + s2)));
        }
        String[] astring = list.toArray(new String[list.size()]);
        Lang.loadResources(Config.getDefaultResourcePack(), astring, map);
        PackResources[] airesourcepack = Config.getResourcePacks();
        for (int i = 0; i < airesourcepack.length; ++i) {
            PackResources iresourcepack = airesourcepack[i];
            Lang.loadResources(iresourcepack, astring, map);
        }
    }

    private static void loadResources(PackResources rp, String[] files, Map localeProperties) {
        try {
            for (int i = 0; i < files.length; ++i) {
                InputStream inputstream;
                String s = files[i];
                g_2336_b resourcelocation = new g_2336_b(s);
                if (!rp.resourceExists(i_4221_J.n_1700_B, resourcelocation) || (inputstream = rp.getResourceStream(i_4221_J.n_1700_B, resourcelocation)) == null) continue;
                Lang.loadLocaleData(inputstream, localeProperties);
            }
        }
        catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
    }

    public static void loadLocaleData(InputStream is, Map localeProperties) throws IOException {
        Iterator iterator = IOUtils.readLines((InputStream)is, (Charset)Charsets.UTF_8).iterator();
        is.close();
        while (iterator.hasNext()) {
            String[] astring;
            String s = (String)iterator.next();
            if (s.isEmpty() || s.charAt(0) == '#' || (astring = (String[])Iterables.toArray((Iterable)splitter.split((CharSequence)s), String.class)) == null || astring.length != 2) continue;
            String s1 = astring[0];
            String s2 = pattern.matcher(astring[1]).replaceAll("%$1s");
            localeProperties.put(s1, s2);
        }
    }

    public static void loadResources(ResourceManager resourceManager, String langCode, Map<String, String> map) {
        try {
            String s = "optifine/lang/" + langCode + ".lang";
            g_2336_b resourcelocation = new g_2336_b(s);
            Resource iresource = resourceManager.n_1700_B(resourcelocation);
            InputStream inputstream = iresource.J_1907_R();
            Lang.loadLocaleData(inputstream, map);
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    public static String get(String key) {
        return K_1289_S.n_1700_B(key, new Object[0]);
    }

    public static F_2904_S getComponent(String key) {
        return new F_2904_S(key);
    }

    public static String get(String key, String def) {
        String s = K_1289_S.n_1700_B(key, new Object[0]);
        return s != null && !s.equals(key) ? s : def;
    }

    public static String getOn() {
        return K_1289_S.n_1700_B("options.on", new Object[0]);
    }

    public static String getOff() {
        return K_1289_S.n_1700_B("options.off", new Object[0]);
    }

    public static String getFast() {
        return K_1289_S.n_1700_B("options.graphics.fast", new Object[0]);
    }

    public static String getFancy() {
        return K_1289_S.n_1700_B("options.graphics.fancy", new Object[0]);
    }

    public static String getDefault() {
        return K_1289_S.n_1700_B("generator.default", new Object[0]);
    }
}


