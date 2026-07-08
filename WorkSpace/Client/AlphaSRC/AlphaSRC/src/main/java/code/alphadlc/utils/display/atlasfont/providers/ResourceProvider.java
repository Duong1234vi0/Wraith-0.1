/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_3298
 *  net.minecraft.class_3300
 */
package code.alphadlc.utils.display.atlasfont.providers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.stream.Collectors;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3298;
import net.minecraft.class_3300;

public final class ResourceProvider {
    private static final Gson GSON = new Gson();

    public static class_2960 getShaderIdentifier(String name) {
        return class_2960.method_60655((String)"mre", (String)("core/" + name));
    }

    public static JsonObject toJson(class_2960 identifier) {
        return JsonParser.parseString((String)ResourceProvider.toString(identifier)).getAsJsonObject();
    }

    public static <T> T fromJsonToInstance(class_2960 identifier, Class<T> clazz) {
        return (T)GSON.fromJson(ResourceProvider.toString(identifier), clazz);
    }

    public static String toString(class_2960 identifier) {
        return ResourceProvider.toString(identifier, "\n");
    }

    /*
     * Enabled aggressive exception aggregation
     */
    public static String toString(class_2960 identifier, String delimiter) {
        class_310 client = class_310.method_1551();
        if (client == null) {
            throw new IllegalStateException("MinecraftClient is not initialized yet for resource: " + String.valueOf(identifier));
        }
        class_3300 resourceManager = client.method_1478();
        if (resourceManager == null) {
            throw new IllegalStateException("ResourceManager is not ready yet for resource: " + String.valueOf(identifier));
        }
        try {
            Optional resourceOpt = resourceManager.method_14486(identifier);
            if (resourceOpt.isEmpty()) {
                throw new RuntimeException("Resource not found: " + String.valueOf(identifier) + "; Make sure the file exists in assets/" + identifier.method_12836() + "/" + identifier.method_12832());
            }
            try (InputStream inputStream = ((class_3298)resourceOpt.get()).method_14482();){
                String string;
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));){
                    string = reader.lines().collect(Collectors.joining(delimiter));
                }
                return string;
            }
        }
        catch (IOException ex) {
            System.err.println("[ResourceProvider] CRITICAL: Failed to load resource: " + String.valueOf(identifier));
            ex.printStackTrace();
            throw new RuntimeException("Failed to load resource: " + String.valueOf(identifier), ex);
        }
    }
}
