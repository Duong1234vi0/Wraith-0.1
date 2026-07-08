/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.reflect.TypeToken
 *  net.minecraft.class_2960
 *  net.minecraft.class_3298
 */
package code.alphadlc.common.localization;

import code.alphadlc.common.localization.Language;
import code.alphadlc.utils.display.interfaces.QuickImports;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.class_2960;
import net.minecraft.class_3298;

public class Localization
implements QuickImports {
    private static final Map<Language, Map<String, String>> cache = new ConcurrentHashMap<Language, Map<String, String>>();

    public static String get(String key) {
        Map translations = cache.computeIfAbsent(Language.ENG, Localization::loadTranslations);
        return translations.getOrDefault(key, key);
    }

    private static Map<String, String> loadTranslations(Language language) {
        class_2960 identifier = class_2960.method_60654((String)("translations/" + language.getFile() + ".json"));
        InputStream stream = ((class_3298)mc.method_1478().method_14486(identifier).get()).method_14482();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        return (Map)gson.fromJson((Reader)reader, new TypeToken<Map<String, String>>(){}.getType());
    }
}
