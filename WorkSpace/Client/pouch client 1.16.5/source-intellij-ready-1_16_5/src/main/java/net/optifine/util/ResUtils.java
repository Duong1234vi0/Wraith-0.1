/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import lightning.product.S_4169_p;
import lightning.product.PackResources;
import lightning.product.g_2336_b;
import lightning.product.i_4221_J;
import lightning.product.j_1086_C;
import lightning.product.r_2139_P;
import lightning.product.r_3127_A;
import net.optifine.Config;
import net.optifine.reflect.Reflector;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.StrUtils;

public class ResUtils {
    public static String[] collectFiles(String prefix, String suffix) {
        return ResUtils.collectFiles(new String[]{prefix}, new String[]{suffix});
    }

    public static String[] collectFiles(String[] prefixes, String[] suffixes) {
        LinkedHashSet<String> set = new LinkedHashSet<String>();
        PackResources[] airesourcepack = Config.getResourcePacks();
        for (int i = 0; i < airesourcepack.length; ++i) {
            PackResources iresourcepack = airesourcepack[i];
            String[] astring = ResUtils.collectFiles(iresourcepack, prefixes, suffixes, (String[])null);
            set.addAll(Arrays.asList(astring));
        }
        return set.toArray(new String[set.size()]);
    }

    public static String[] collectFiles(PackResources rp, String prefix, String suffix, String[] defaultPaths) {
        return ResUtils.collectFiles(rp, new String[]{prefix}, new String[]{suffix}, defaultPaths);
    }

    public static String[] collectFiles(PackResources rp, String[] prefixes, String[] suffixes) {
        return ResUtils.collectFiles(rp, prefixes, suffixes, (String[])null);
    }

    public static String[] collectFiles(PackResources rp, String[] prefixes, String[] suffixes, String[] defaultPaths) {
        if (rp instanceof S_4169_p) {
            return ResUtils.collectFilesFixed(rp, defaultPaths);
        }
        if (rp instanceof r_3127_A) {
            PackResources iresourcepack = (PackResources)Reflector.getFieldValue(rp, Reflector.LegacyResourcePackWrapper_pack);
            if (iresourcepack == null) {
                Config.warn("LegacyResourcePackWrapper base resource pack not found: " + String.valueOf(rp));
                return new String[0];
            }
            rp = iresourcepack;
        }
        if (rp instanceof j_1086_C) {
            PackResources iresourcepack1 = (PackResources)Reflector.getFieldValue(rp, Reflector.LegacyResourcePackWrapperV4_pack);
            if (iresourcepack1 == null) {
                Config.warn("LegacyResourcePackWrapperV4 base resource pack not found: " + String.valueOf(rp));
                return new String[0];
            }
            rp = iresourcepack1;
        }
        if (!(rp instanceof r_2139_P)) {
            Config.warn("Unknown resource pack type: " + String.valueOf(rp));
            return new String[0];
        }
        r_2139_P resourcepack = (r_2139_P)rp;
        File file1 = resourcepack.file;
        if (file1 == null) {
            return new String[0];
        }
        if (file1.isDirectory()) {
            return ResUtils.collectFilesFolder(file1, "", prefixes, suffixes);
        }
        if (file1.isFile()) {
            return ResUtils.collectFilesZIP(file1, prefixes, suffixes);
        }
        Config.warn("Unknown resource pack file: " + String.valueOf(file1));
        return new String[0];
    }

    private static String[] collectFilesFixed(PackResources rp, String[] paths) {
        if (paths == null) {
            return new String[0];
        }
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < paths.length; ++i) {
            String s = paths[i];
            if (!ResUtils.isLowercase(s)) {
                Config.warn("Skipping non-lowercase path: " + s);
                continue;
            }
            g_2336_b resourcelocation = new g_2336_b(s);
            if (!rp.resourceExists(i_4221_J.n_1700_B, resourcelocation)) continue;
            list.add(s);
        }
        return list.toArray(new String[list.size()]);
    }

    private static String[] collectFilesFolder(File tpFile, String basePath, String[] prefixes, String[] suffixes) {
        ArrayList<Object> list = new ArrayList<Object>();
        String s = "assets/minecraft/";
        File[] afile = tpFile.listFiles();
        if (afile == null) {
            return new String[0];
        }
        for (int i = 0; i < afile.length; ++i) {
            File file1 = afile[i];
            if (file1.isFile()) {
                Object s3 = basePath + file1.getName();
                if (!((String)s3).startsWith(s) || !StrUtils.startsWith((String)(s3 = ((String)s3).substring(s.length())), prefixes) || !StrUtils.endsWith((String)s3, suffixes)) continue;
                if (!ResUtils.isLowercase((String)s3)) {
                    Config.warn("Skipping non-lowercase path: " + (String)s3);
                    continue;
                }
                list.add(s3);
                continue;
            }
            if (!file1.isDirectory()) continue;
            String s1 = basePath + file1.getName() + "/";
            String[] astring = ResUtils.collectFilesFolder(file1, s1, prefixes, suffixes);
            for (int j = 0; j < astring.length; ++j) {
                String s2 = astring[j];
                list.add(s2);
            }
        }
        return list.toArray(new String[list.size()]);
    }

    private static String[] collectFilesZIP(File tpFile, String[] prefixes, String[] suffixes) {
        ArrayList<String> list = new ArrayList<String>();
        String s = "assets/minecraft/";
        try {
            ZipFile zipfile = new ZipFile(tpFile);
            Enumeration<? extends ZipEntry> enumeration = zipfile.entries();
            while (enumeration.hasMoreElements()) {
                ZipEntry zipentry = enumeration.nextElement();
                String s1 = zipentry.getName();
                if (!s1.startsWith(s) || !StrUtils.startsWith(s1 = s1.substring(s.length()), prefixes) || !StrUtils.endsWith(s1, suffixes)) continue;
                if (!ResUtils.isLowercase(s1)) {
                    Config.warn("Skipping non-lowercase path: " + s1);
                    continue;
                }
                list.add(s1);
            }
            zipfile.close();
            String[] astring = list.toArray(new String[list.size()]);
            return astring;
        }
        catch (IOException ioexception) {
            ioexception.printStackTrace();
            return new String[0];
        }
    }

    private static boolean isLowercase(String str) {
        return str.equals(str.toLowerCase(Locale.ROOT));
    }

    public static Properties readProperties(String path, String module) {
        g_2336_b resourcelocation = new g_2336_b(path);
        try {
            InputStream inputstream = Config.getResourceStream(resourcelocation);
            if (inputstream == null) {
                return null;
            }
            PropertiesOrdered properties = new PropertiesOrdered();
            properties.load(inputstream);
            inputstream.close();
            Config.dbg(module + ": Loading " + path);
            return properties;
        }
        catch (FileNotFoundException filenotfoundexception) {
            return null;
        }
        catch (IOException ioexception) {
            Config.warn(module + ": Error reading " + path);
            return null;
        }
    }

    public static Properties readProperties(InputStream in, String module) {
        if (in == null) {
            return null;
        }
        try {
            PropertiesOrdered properties = new PropertiesOrdered();
            properties.load(in);
            in.close();
            return properties;
        }
        catch (FileNotFoundException filenotfoundexception) {
            return null;
        }
        catch (IOException ioexception) {
            return null;
        }
    }
}


