/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nullable;
import lightning.product.T_335_n;
import lightning.product.PackResources;
import lightning.product.g_2336_b;
import lightning.product.i_4221_J;
import lightning.product.i_4431_W;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class r_2139_P
implements PackResources {
    private static final Logger LOGGER = LogManager.getLogger();
    public final File file;

    public r_2139_P(File resourcePackFileIn) {
        this.file = resourcePackFileIn;
    }

    private static String getFullPath(i_4221_J type, g_2336_b location) {
        return String.format("%s/%s/%s", type.n_1700_B(), location.R_4764_Y(), location.J_1907_R());
    }

    protected static String getRelativeString(File file1, File file2) {
        return file1.toURI().relativize(file2.toURI()).getPath();
    }

    @Override
    public InputStream getResourceStream(i_4221_J type, g_2336_b location) throws IOException {
        return this.getInputStream(r_2139_P.getFullPath(type, location));
    }

    @Override
    public boolean resourceExists(i_4221_J type, g_2336_b location) {
        return this.resourceExists(r_2139_P.getFullPath(type, location));
    }

    protected abstract InputStream getInputStream(String var1) throws IOException;

    @Override
    public InputStream getRootResourceStream(String fileName) throws IOException {
        if (!fileName.contains("/") && !fileName.contains("\\")) {
            return this.getInputStream(fileName);
        }
        throw new IllegalArgumentException("Root resources can only be filenames, not paths (no / allowed!)");
    }

    protected abstract boolean resourceExists(String var1);

    protected void onIgnoreNonLowercaseNamespace(String namespace) {
        LOGGER.warn("ResourcePack: ignored non-lowercase namespace: {} in {}", (Object)namespace, (Object)this.file);
    }

    @Override
    @Nullable
    public <T> T getMetadata(T_335_n<T> deserializer) throws IOException {
        T object;
        try (InputStream inputstream = this.getInputStream("pack.mcmeta");){
            object = r_2139_P.getResourceMetadata(deserializer, inputstream);
        }
        return object;
    }

    @Nullable
    public static <T> T getResourceMetadata(T_335_n<T> deserializer, InputStream inputStream) {
        JsonObject jsonobject;
        try (BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));){
            jsonobject = i_4431_W.n_1700_B(bufferedreader);
        }
        catch (JsonParseException | IOException jsonparseexception1) {
            LOGGER.error("Couldn't load {} metadata", (Object)deserializer.n_1700_B(), (Object)jsonparseexception1);
            return null;
        }
        if (!jsonobject.has(deserializer.n_1700_B())) {
            return null;
        }
        try {
            return deserializer.J_1907_R(i_4431_W.M_588_G(jsonobject, deserializer.n_1700_B()));
        }
        catch (JsonParseException jsonparseexception1) {
            LOGGER.error("Couldn't load {} metadata", (Object)deserializer.n_1700_B(), (Object)jsonparseexception1);
            return null;
        }
    }

    @Override
    public String getName() {
        return this.file.getName();
    }
}


