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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import lightning.product.C_332_W;
import lightning.product.R_2329_T;
import lombok.Generated;

public class P_2272_O {
    public static final float n_1700_B = 180.0f;
    public static final float J_1907_R = 90.0f;
    public static final float R_4764_Y = 6.0f;
    public static final float G_564_y = 30.0f;
    public static final float P_1922_E = 15.0f;
    public static final float u_1723_Y = 180.0f;
    public static final float v_4262_N = 60.0f;
    public static final float w_1484_f = 30.0f;
    public static final int t_148_a = 8;
    public static final int s_956_w = 2;
    public static final int u_2550_I = 32;
    private boolean M_588_G;
    private n_1700_B P_4830_p;
    private final Map<String, n_1700_B> h_1847_R = new HashMap<String, n_1700_B>();
    private final Map<String, R_2329_T> Q_4569_t = new HashMap<String, R_2329_T>();
    private final File M_182_A = new File(C_332_W.n_1700_B + "neuro");
    private final File t_1786_h = new File(this.M_182_A, "models");

    public P_2272_O() {
        this.u_1723_Y();
        this.R_4764_Y();
    }

    public void n_1700_B(String name) {
        this.P_4830_p = new n_1700_B(name);
        this.M_588_G = true;
    }

    public void n_1700_B() {
        this.M_588_G = false;
        if (this.P_4830_p != null && !this.P_4830_p.J_1907_R.isEmpty()) {
            this.h_1847_R.put(this.P_4830_p.n_1700_B, this.P_4830_p);
            this.n_1700_B(this.P_4830_p);
        }
        this.P_4830_p = null;
    }

    public void n_1700_B(float[] inputs, float[] outputs) {
        if (!this.M_588_G || this.P_4830_p == null) {
            return;
        }
        this.P_4830_p.J_1907_R.add(new J_1907_R((float[])inputs.clone(), (float[])outputs.clone()));
    }

    public boolean J_1907_R(String name) {
        n_1700_B p = this.h_1847_R.get(name);
        return p != null && !p.J_1907_R.isEmpty();
    }

    public int R_4764_Y(String name) {
        n_1700_B p = this.h_1847_R.get(name);
        return p != null ? p.J_1907_R.size() : 0;
    }

    public float[][][] G_564_y(String name) {
        n_1700_B p = this.h_1847_R.get(name);
        if (p == null || p.J_1907_R.isEmpty()) {
            return null;
        }
        float[][] inputs = new float[p.J_1907_R.size()][];
        float[][] targets = new float[p.J_1907_R.size()][];
        for (int i = 0; i < p.J_1907_R.size(); ++i) {
            inputs[i] = p.J_1907_R.get((int)i).n_1700_B;
            targets[i] = p.J_1907_R.get((int)i).J_1907_R;
        }
        return new float[][][]{inputs, targets};
    }

    public void n_1700_B(String name, R_2329_T nn) {
        this.Q_4569_t.put(name, nn);
        this.J_1907_R(name, nn);
    }

    public float[] n_1700_B(String name, float[] inputs) {
        R_2329_T nn = this.Q_4569_t.get(name);
        if (nn == null) {
            return null;
        }
        return nn.n_1700_B(inputs);
    }

    public void P_1922_E(String name) {
        R_2329_T nn = this.Q_4569_t.get(name);
        if (nn != null) {
            nn.n_1700_B();
        }
    }

    public boolean u_1723_Y(String name) {
        return this.Q_4569_t.containsKey(name);
    }

    public boolean v_4262_N(String name) {
        return this.h_1847_R.containsKey(name) || this.Q_4569_t.containsKey(name);
    }

    public Set<String> J_1907_R() {
        LinkedHashSet<String> all = new LinkedHashSet<String>();
        all.addAll(this.h_1847_R.keySet());
        all.addAll(this.Q_4569_t.keySet());
        return all;
    }

    public String w_1484_f(String name) {
        boolean data = this.h_1847_R.containsKey(name);
        boolean model = this.Q_4569_t.containsKey(name);
        if (data && model) {
            return "[\u043e\u0431\u0443\u0447\u0435\u043d\u0430] [\u0434\u0430\u043d\u043d\u044b\u0435: " + this.h_1847_R.get((Object)name).J_1907_R.size() + "]";
        }
        if (model) {
            return "[\u043e\u0431\u0443\u0447\u0435\u043d\u0430]";
        }
        if (data) {
            return "[\u0434\u0430\u043d\u043d\u044b\u0435: " + this.h_1847_R.get((Object)name).J_1907_R.size() + "]";
        }
        return "";
    }

    public void R_4764_Y() {
        File[] modelFiles;
        this.u_1723_Y();
        this.h_1847_R.clear();
        this.Q_4569_t.clear();
        File[] dataFiles = this.M_182_A.listFiles((d, n) -> n.toLowerCase(Locale.ROOT).endsWith(".neuro"));
        if (dataFiles != null) {
            for (File file : dataFiles) {
                try (BufferedReader reader2 = new BufferedReader(new FileReader(file));){
                    int version;
                    JsonObject obj = new JsonParser().parse((Reader)reader2).getAsJsonObject();
                    int n2 = version = obj.has("version") ? obj.get("version").getAsInt() : 1;
                    if (version < 3 || !obj.has("name") || !obj.has("samples")) continue;
                    String name = obj.get("name").getAsString();
                    n_1700_B profile = new n_1700_B(name);
                    JsonArray samples = obj.getAsJsonArray("samples");
                    for (JsonElement el : samples) {
                        int i;
                        JsonObject so;
                        if (!el.isJsonObject() || !(so = el.getAsJsonObject()).has("in") || !so.has("out")) continue;
                        JsonArray inArr = so.getAsJsonArray("in");
                        JsonArray outArr = so.getAsJsonArray("out");
                        float[] in = new float[inArr.size()];
                        float[] out = new float[outArr.size()];
                        for (i = 0; i < in.length; ++i) {
                            in[i] = inArr.get(i).getAsFloat();
                        }
                        for (i = 0; i < out.length; ++i) {
                            out[i] = outArr.get(i).getAsFloat();
                        }
                        profile.J_1907_R.add(new J_1907_R(in, out));
                    }
                    if (profile.J_1907_R.isEmpty()) continue;
                    this.h_1847_R.put(name, profile);
                }
                catch (Exception reader2) {
                    // empty catch block
                }
            }
        }
        if ((modelFiles = this.t_1786_h.listFiles((d, n) -> n.toLowerCase(Locale.ROOT).endsWith(".neuromodel"))) != null) {
            for (File file : modelFiles) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file));){
                    JsonObject netObj;
                    JsonObject obj = new JsonParser().parse((Reader)reader).getAsJsonObject();
                    if (!obj.has("name") || !obj.has("network") || !(netObj = obj.getAsJsonObject("network")).has("type") || !"lstm".equals(netObj.get("type").getAsString())) continue;
                    String name = obj.get("name").getAsString();
                    R_2329_T nn = R_2329_T.n_1700_B(netObj);
                    this.Q_4569_t.put(name, nn);
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }
    }

    private void n_1700_B(n_1700_B profile) {
        this.u_1723_Y();
        File file = new File(this.M_182_A, this.t_148_a(profile.n_1700_B) + ".neuro");
        JsonObject obj = new JsonObject();
        obj.addProperty("version", (Number)3);
        obj.addProperty("name", profile.n_1700_B);
        JsonArray samples = new JsonArray();
        for (J_1907_R s : profile.J_1907_R) {
            JsonObject so = new JsonObject();
            JsonArray inArr = new JsonArray();
            JsonArray outArr = new JsonArray();
            for (float v : s.n_1700_B) {
                inArr.add((Number)Float.valueOf(v));
            }
            for (float v : s.J_1907_R) {
                outArr.add((Number)Float.valueOf(v));
            }
            so.add("in", (JsonElement)inArr);
            so.add("out", (JsonElement)outArr);
            samples.add((JsonElement)so);
        }
        obj.add("samples", (JsonElement)samples);
        try (FileWriter w = new FileWriter(file);){
            w.write(obj.toString());
        }
        catch (IOException e) {
            System.out.println("[Neuro] Failed to save data: " + profile.n_1700_B);
        }
    }

    private void J_1907_R(String name, R_2329_T nn) {
        this.u_1723_Y();
        File file = new File(this.t_1786_h, this.t_148_a(name) + ".neuromodel");
        JsonObject obj = new JsonObject();
        obj.addProperty("name", name);
        obj.add("network", (JsonElement)nn.J_1907_R());
        try (FileWriter w = new FileWriter(file);){
            w.write(obj.toString());
        }
        catch (IOException e) {
            System.out.println("[Neuro] Failed to save model: " + name);
        }
    }

    private void u_1723_Y() {
        if (!this.M_182_A.exists()) {
            this.M_182_A.mkdirs();
        }
        if (!this.t_1786_h.exists()) {
            this.t_1786_h.mkdirs();
        }
    }

    private String t_148_a(String name) {
        return name.replaceAll("[\\\\/:*?\"<>|]", "_");
    }

    @Generated
    public boolean G_564_y() {
        return this.M_588_G;
    }

    @Generated
    public File P_1922_E() {
        return this.M_182_A;
    }

    private static class n_1700_B {
        final String n_1700_B;
        final List<J_1907_R> J_1907_R = new ArrayList<J_1907_R>();

        n_1700_B(String name) {
            this.n_1700_B = name;
        }
    }

    public record J_1907_R(float[] n_1700_B, float[] J_1907_R) {
        @Override
        public final String toString() {
            return ObjectMethods.bootstrap("toString", new MethodHandle[]{J_1907_R.class, "inputs;outputs", "n_1700_B", "J_1907_R"}, this);
        }

        @Override
        public final int hashCode() {
            return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{J_1907_R.class, "inputs;outputs", "n_1700_B", "J_1907_R"}, this);
        }

        @Override
        public final boolean equals(Object o) {
            return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{J_1907_R.class, "inputs;outputs", "n_1700_B", "J_1907_R"}, this, o);
        }
    }
}

