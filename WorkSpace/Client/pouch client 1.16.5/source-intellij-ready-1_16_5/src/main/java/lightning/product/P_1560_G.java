/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.io.Files
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.StoredUserEntry;
import lightning.product.i_4431_W;
import lightning.product.j_3341_s;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class P_1560_G<K, V extends StoredUserEntry<K>> {
    protected static final Logger n_1700_B = LogManager.getLogger();
    private static final Gson J_1907_R = new GsonBuilder().setPrettyPrinting().create();
    private final File R_4764_Y;
    private final Map<String, V> G_564_y = Maps.newHashMap();

    public P_1560_G(File saveFile) {
        this.R_4764_Y = saveFile;
    }

    public File J_1907_R() {
        return this.R_4764_Y;
    }

    public void n_1700_B(V entry) {
        this.G_564_y.put(this.n_1700_B((K)((StoredUserEntry)entry).u_1723_Y()), entry);
        try {
            this.P_1922_E();
        }
        catch (IOException ioexception) {
            n_1700_B.warn("Could not save the list after adding a user.", (Throwable)ioexception);
        }
    }

    @Nullable
    public V J_1907_R(K obj) {
        this.v_4262_N();
        return (V)((StoredUserEntry)this.G_564_y.get(this.n_1700_B(obj)));
    }

    public void R_4764_Y(K entry) {
        this.G_564_y.remove(this.n_1700_B(entry));
        try {
            this.P_1922_E();
        }
        catch (IOException ioexception) {
            n_1700_B.warn("Could not save the list after removing a user.", (Throwable)ioexception);
        }
    }

    public void J_1907_R(StoredUserEntry<K> p_199042_1_) {
        this.R_4764_Y(p_199042_1_.u_1723_Y());
    }

    public String[] n_1700_B() {
        return this.G_564_y.keySet().toArray(new String[this.G_564_y.size()]);
    }

    public boolean R_4764_Y() {
        return this.G_564_y.size() < 1;
    }

    protected String n_1700_B(K obj) {
        return obj.toString();
    }

    protected boolean G_564_y(K entry) {
        return this.G_564_y.containsKey(this.n_1700_B(entry));
    }

    private void v_4262_N() {
        ArrayList list = Lists.newArrayList();
        for (StoredUserEntry v : this.G_564_y.values()) {
            if (!v.P_1922_E()) continue;
            list.add(v.u_1723_Y());
        }
        for (Object k : list) {
            this.G_564_y.remove(this.n_1700_B((K)k));
        }
    }

    protected abstract StoredUserEntry<K> n_1700_B(JsonObject var1);

    public Collection<V> G_564_y() {
        return this.G_564_y.values();
    }

    public void P_1922_E() throws IOException {
        JsonArray jsonarray = new JsonArray();
        this.G_564_y.values().stream().map(p_232646_0_ -> j_3341_s.n_1700_B(new JsonObject(), p_232646_0_::n_1700_B)).forEach(arg_0 -> ((JsonArray)jsonarray).add(arg_0));
        try (BufferedWriter bufferedwriter = Files.newWriter((File)this.R_4764_Y, (Charset)StandardCharsets.UTF_8);){
            J_1907_R.toJson((JsonElement)jsonarray, (Appendable)bufferedwriter);
        }
    }

    public void u_1723_Y() throws IOException {
        if (this.R_4764_Y.exists()) {
            try (BufferedReader bufferedreader = Files.newReader((File)this.R_4764_Y, (Charset)StandardCharsets.UTF_8);){
                JsonArray jsonarray = (JsonArray)J_1907_R.fromJson((Reader)bufferedreader, JsonArray.class);
                this.G_564_y.clear();
                for (JsonElement jsonelement : jsonarray) {
                    JsonObject jsonobject = i_4431_W.w_1484_f(jsonelement, "entry");
                    StoredUserEntry<K> userlistentry = this.n_1700_B(jsonobject);
                    if (userlistentry.u_1723_Y() == null) continue;
                    this.G_564_y.put(this.n_1700_B(userlistentry.u_1723_Y()), userlistentry);
                }
            }
        }
    }
}


