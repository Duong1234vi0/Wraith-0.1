/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.common.io.Files
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  javax.annotation.Nullable
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class x_1356_s {
    protected static final Logger n_1700_B = LogManager.getLogger();
    private final Map<String, File> J_1907_R = Maps.newHashMap();
    private final Map<g_2336_b, File> R_4764_Y = Maps.newHashMap();

    protected x_1356_s() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public x_1356_s(File assetsFolder, String indexName) {
        File file1 = new File(assetsFolder, "objects");
        File file2 = new File(assetsFolder, "indexes/" + indexName + ".json");
        BufferedReader bufferedreader = null;
        try {
            bufferedreader = Files.newReader((File)file2, (Charset)StandardCharsets.UTF_8);
            JsonObject jsonobject = i_4431_W.n_1700_B(bufferedreader);
            JsonObject jsonobject1 = i_4431_W.n_1700_B(jsonobject, "objects", (JsonObject)null);
            if (jsonobject1 != null) {
                for (Map.Entry entry : jsonobject1.entrySet()) {
                    JsonObject jsonobject2 = (JsonObject)entry.getValue();
                    String s = (String)entry.getKey();
                    String[] astring = s.split("/", 2);
                    String s1 = i_4431_W.u_1723_Y(jsonobject2, "hash");
                    File file3 = new File(file1, s1.substring(0, 2) + "/" + s1);
                    if (astring.length == 1) {
                        this.J_1907_R.put(astring[0], file3);
                        continue;
                    }
                    this.R_4764_Y.put(new g_2336_b(astring[0], astring[1]), file3);
                }
            }
        }
        catch (JsonParseException jsonparseexception) {
            n_1700_B.error("Unable to parse resource index file: {}", (Object)file2);
        }
        catch (FileNotFoundException filenotfoundexception) {
            n_1700_B.error("Can't find the resource index file: {}", (Object)file2);
        }
        finally {
            IOUtils.closeQuietly((Reader)bufferedreader);
        }
    }

    @Nullable
    public File n_1700_B(g_2336_b location) {
        return this.R_4764_Y.get(location);
    }

    @Nullable
    public File n_1700_B(String p_225638_1_) {
        return this.J_1907_R.get(p_225638_1_);
    }

    public Collection<g_2336_b> n_1700_B(String p_225639_1_, String p_225639_2_, int p_225639_3_, Predicate<String> p_225639_4_) {
        return this.R_4764_Y.keySet().stream().filter(p_229273_3_ -> {
            String s = p_229273_3_.J_1907_R();
            return p_229273_3_.R_4764_Y().equals(p_225639_2_) && !s.endsWith(".mcmeta") && s.startsWith(p_225639_1_ + "/") && p_225639_4_.test(s);
        }).collect(Collectors.toList());
    }
}

