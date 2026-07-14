/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import lightning.product.g_2336_b;
import net.optifine.Config;
import net.optifine.util.PropertiesOrdered;

public class ConfigUtils {
    public static String readString(String fileName, String property) {
        Properties properties = ConfigUtils.readProperties(fileName);
        if (properties == null) {
            return null;
        }
        String s = properties.getProperty(property);
        if (s != null) {
            s = s.trim();
        }
        return s;
    }

    public static Properties readProperties(String fileName) {
        try {
            g_2336_b resourcelocation = new g_2336_b(fileName);
            InputStream inputstream = Config.getResourceStream(resourcelocation);
            if (inputstream == null) {
                return null;
            }
            PropertiesOrdered properties = new PropertiesOrdered();
            properties.load(inputstream);
            inputstream.close();
            return properties;
        }
        catch (FileNotFoundException filenotfoundexception) {
            return null;
        }
        catch (IOException ioexception) {
            Config.warn("Error parsing: " + fileName);
            Config.warn(ioexception.getClass().getName() + ": " + ioexception.getMessage());
            return null;
        }
    }
}

