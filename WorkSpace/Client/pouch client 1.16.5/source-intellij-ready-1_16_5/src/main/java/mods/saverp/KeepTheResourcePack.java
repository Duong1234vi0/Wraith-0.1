/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  org.apache.commons.io.FileUtils
 */
package mods.saverp;

import com.google.common.base.Charsets;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import lightning.product.MinecraftAccess;
import lightning.product.MinecraftClient;
import lightning.product.PackSource;
import org.apache.commons.io.FileUtils;

public class KeepTheResourcePack {
    private static final File latestServerResourcePack = new File(MinecraftAccess.c_3005_b.M_182_A.toPath().toFile(), "latestServerResourcePack.json");
    public static File cacheResourcePackFile = null;

    public KeepTheResourcePack() {
        if (latestServerResourcePack.exists()) {
            try {
                JsonObject jsonObject = new JsonParser().parse(FileUtils.readFileToString((File)latestServerResourcePack, (Charset)Charsets.UTF_8)).getAsJsonObject();
                File resourcePack = new File(jsonObject.get("file").getAsString());
                if (resourcePack.exists()) {
                    cacheResourcePackFile = resourcePack;
                    MinecraftClient.A_4115_X().z_4693_k().n_1700_B(resourcePack, PackSource.G_564_y);
                    MinecraftClient.A_4115_X().Y_1740_V();
                } else {
                    KeepTheResourcePack.setLatestServerResourcePack(null);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setLatestServerResourcePack(File file) {
        if (file == null) {
            latestServerResourcePack.delete();
        } else {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("file", file.getPath());
            cacheResourcePackFile = file;
            try {
                FileUtils.writeStringToFile((File)latestServerResourcePack, (String)jsonObject.toString(), (Charset)Charsets.UTF_8);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



