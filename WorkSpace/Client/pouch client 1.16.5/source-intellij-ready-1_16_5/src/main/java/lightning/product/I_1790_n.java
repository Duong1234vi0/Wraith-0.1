/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 */
package lightning.product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import lightning.product.C_332_W;
import lightning.product.W_1488_x;
import lightning.product.w_2223_C;

public abstract class I_1790_n<T> {
    protected static final Gson n_1700_B = new GsonBuilder().setPrettyPrinting().create();
    protected static final String J_1907_R = C_332_W.n_1700_B;
    protected final File R_4764_Y;
    protected final boolean G_564_y;
    protected T P_1922_E;

    protected I_1790_n(String relativePath, boolean encrypt) {
        this.R_4764_Y = new File(J_1907_R + relativePath);
        this.G_564_y = encrypt;
    }

    protected I_1790_n(String relativePath) {
        this(relativePath, true);
    }

    @w_2223_C
    public void n_1700_B() {
        this.J_1907_R();
        this.t_148_a();
        this.R_4764_Y();
    }

    protected void J_1907_R() {
        File parentDir = this.R_4764_Y.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    public void R_4764_Y() {
        if (!this.R_4764_Y.exists()) {
            return;
        }
        try {
            String fileContent = Files.readString(this.R_4764_Y.toPath());
            String processedContent = this.G_564_y ? W_1488_x.J_1907_R(fileContent) : fileContent;
            JsonObject jsonObject = new JsonParser().parse(processedContent).getAsJsonObject();
            this.n_1700_B(jsonObject);
        }
        catch (Exception e) {
            System.err.println("Failed to load config from " + this.R_4764_Y.getName() + ": " + e.getMessage());
            this.n_1700_B(e);
        }
    }

    public void G_564_y() {
        try {
            this.J_1907_R();
            JsonObject jsonObject = this.s_956_w();
            String jsonContent = n_1700_B.toJson((JsonElement)jsonObject);
            String outputContent = this.G_564_y ? W_1488_x.n_1700_B(jsonContent) : jsonContent;
            Files.writeString(this.R_4764_Y.toPath(), (CharSequence)outputContent, new OpenOption[0]);
        }
        catch (Exception e) {
            System.err.println("Failed to save config to " + this.R_4764_Y.getName() + ": " + e.getMessage());
            this.J_1907_R(e);
        }
    }

    public boolean P_1922_E() {
        return this.R_4764_Y.exists();
    }

    public boolean u_1723_Y() {
        return this.R_4764_Y.exists() && this.R_4764_Y.delete();
    }

    public String v_4262_N() {
        return this.R_4764_Y.getAbsolutePath();
    }

    public String w_1484_f() {
        return this.R_4764_Y.getName();
    }

    protected abstract void t_148_a();

    protected abstract JsonObject s_956_w();

    protected abstract void n_1700_B(JsonObject var1);

    protected void n_1700_B(Exception exception) {
    }

    protected void J_1907_R(Exception exception) {
    }

    public T u_2550_I() {
        return this.P_1922_E;
    }

    protected void n_1700_B(T data) {
        this.P_1922_E = data;
    }
}

