/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  lombok.Generated
 */
package lightning.product;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import lightning.product.C_332_W;
import lightning.product.I_1790_n;
import lightning.product.W_1488_x;
import lightning.product.q_3148_R;
import lombok.Generated;

public class V_1176_p
extends I_1790_n<V_1176_p> {
    private static final File u_1723_Y = new File(C_332_W.n_1700_B + "themes\\");

    public V_1176_p() {
        super("themes\\");
    }

    @Override
    public void n_1700_B() {
        u_1723_Y.mkdirs();
        super.n_1700_B();
    }

    @Override
    protected void t_148_a() {
    }

    @Override
    protected JsonObject s_956_w() {
        return null;
    }

    @Override
    protected void n_1700_B(JsonObject jsonObject) {
    }

    public List<String> M_588_G() {
        File[] files = u_1723_Y.listFiles((dir, name) -> name.endsWith(".file"));
        if (files == null) {
            return Collections.emptyList();
        }
        ArrayList<String> names = new ArrayList<String>();
        for (File file : files) {
            names.add(file.getName().replace(".file", ""));
        }
        return names;
    }

    public n_1700_B n_1700_B(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.err.println("Cannot load theme: name is null or empty");
            return null;
        }
        File file = new File(u_1723_Y, name.trim() + ".file");
        if (!file.exists()) {
            return null;
        }
        n_1700_B theme = new n_1700_B(name);
        try {
            String fileContent = Files.readString(file.toPath());
            String processedContent = this.G_564_y ? W_1488_x.J_1907_R(fileContent) : fileContent;
            JsonObject config = new JsonParser().parse(processedContent).getAsJsonObject();
            this.n_1700_B(config, "presetName", (JsonElement v) -> {
                theme.R_4764_Y = v.getAsString();
            });
            this.n_1700_B(config, "creator", (JsonElement v) -> {
                theme.P_1922_E = v.getAsString();
            });
            JsonArray colorsArray = config.getAsJsonArray("presetColors");
            if (colorsArray != null) {
                theme.G_564_y = new int[colorsArray.size()];
                for (int i = 0; i < colorsArray.size(); ++i) {
                    theme.G_564_y[i] = colorsArray.get(i).getAsInt();
                }
            }
            if (config.has("elementColors") && config.get("elementColors").isJsonObject()) {
                theme.u_1723_Y = config.getAsJsonObject("elementColors");
            }
        }
        catch (Exception e) {
            System.err.println("Failed to load theme: " + name + " - " + e.getMessage());
        }
        return theme;
    }

    public void n_1700_B(String name, q_3148_R.n_1700_B preset, String creator) {
        this.n_1700_B(name, preset, creator, null);
    }

    public void n_1700_B(String name, q_3148_R.n_1700_B preset, String creator, JsonObject elementColors) {
        if (name == null || name.trim().isEmpty()) {
            System.err.println("Cannot save theme: name is null or empty");
            return;
        }
        if (preset == null) {
            System.err.println("Cannot save theme: preset is null");
            return;
        }
        try {
            u_1723_Y.mkdirs();
            File file = new File(u_1723_Y, name.trim() + ".file");
            JsonObject config = new JsonObject();
            config.addProperty("presetName", preset.n_1700_B());
            config.addProperty("creator", creator != null ? creator : "Unknown");
            JsonArray colorsArray = new JsonArray();
            for (int color : preset.J_1907_R()) {
                colorsArray.add((Number)color);
            }
            config.add("presetColors", (JsonElement)colorsArray);
            if (elementColors != null && elementColors.size() > 0) {
                config.add("elementColors", (JsonElement)elementColors);
            }
            String jsonContent = n_1700_B.toJson((JsonElement)config);
            String outputContent = this.G_564_y ? W_1488_x.n_1700_B(jsonContent) : jsonContent;
            Files.writeString(file.toPath(), (CharSequence)outputContent, new OpenOption[0]);
        }
        catch (Exception e) {
            System.err.println("Failed to save theme: " + name + " - " + e.getMessage());
        }
    }

    public boolean J_1907_R(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.err.println("Cannot delete theme: name is null or empty");
            return false;
        }
        File file = new File(u_1723_Y, name.trim() + ".file");
        return file.exists() && file.delete();
    }

    private void n_1700_B(JsonObject object, String key, Consumer<JsonElement> consumer) {
        JsonElement element = object.get(key);
        if (element != null && !element.isJsonNull()) {
            consumer.accept(element);
        }
    }

    public static class n_1700_B {
        private final String n_1700_B;
        private final File J_1907_R;
        private String R_4764_Y;
        private int[] G_564_y;
        private String P_1922_E;
        private JsonObject u_1723_Y;

        public n_1700_B(String name) {
            this.n_1700_B = name;
            this.J_1907_R = new File(u_1723_Y, name + ".file");
        }

        @Generated
        public String n_1700_B() {
            return this.n_1700_B;
        }

        @Generated
        public File J_1907_R() {
            return this.J_1907_R;
        }

        @Generated
        public String R_4764_Y() {
            return this.R_4764_Y;
        }

        @Generated
        public int[] G_564_y() {
            return this.G_564_y;
        }

        @Generated
        public String P_1922_E() {
            return this.P_1922_E;
        }

        @Generated
        public JsonObject u_1723_Y() {
            return this.u_1723_Y;
        }
    }
}

