/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 */
package lightning.product;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import lightning.product.D_1098_v;
import lightning.product.D_4792_h;
import lightning.product.ItemTransforms;
import lightning.product.H_3330_w;
import lightning.product.Z_1993_T;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.i_2518_W;
import lightning.product.o_1290_k;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.w_3785_E;

public class z_2759_Q {
    private static final float n_1700_B = 0.0625f;
    private static final float J_1907_R = 0.5f;
    private static final int R_4764_Y = 8;
    private static final int G_564_y = 0;
    private static final int P_1922_E = 1;
    private static final int u_1723_Y = 2;
    private static final int v_4262_N = 3;
    private g_2336_b w_1484_f;
    private o_2576_A t_148_a;
    private float[] s_956_w = new float[0];
    private int u_2550_I = 0;
    private final ArrayList<n_1700_B> M_588_G = new ArrayList();
    private final J_1907_R[] P_4830_p = new J_1907_R[8];
    private boolean h_1847_R = false;
    private final String Q_4569_t;

    public z_2759_Q(String modelName) {
        this.Q_4569_t = "Pouch/item_replacer/sword/models/" + modelName + ".json";
    }

    private void n_1700_B() {
        if (this.h_1847_R) {
            return;
        }
        this.h_1847_R = true;
        try (InputStream is = MinecraftClient.A_4115_X().T_2506_i().n_1700_B(new g_2336_b("minecraft", this.Q_4569_t)).J_1907_R();
             InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);){
            JsonObject root = new JsonParser().parse((Reader)reader).getAsJsonObject();
            this.n_1700_B(root);
        }
        catch (Exception e) {
            System.err.println("[ItemReplacer] Failed to load model: " + this.Q_4569_t + " - " + e.getMessage());
        }
    }

    private void n_1700_B(JsonObject root) {
        JsonObject texturesObj = root.getAsJsonObject("textures");
        if (texturesObj == null) {
            return;
        }
        HashMap<String, String> texMap = new HashMap<String, String>();
        for (Map.Entry e : texturesObj.entrySet()) {
            texMap.put((String)e.getKey(), ((JsonElement)e.getValue()).getAsString());
        }
        String mainKey = this.n_1700_B(texMap, root);
        String mainTexPath = (String)texMap.get(mainKey);
        if (mainTexPath == null) {
            return;
        }
        String texName = mainTexPath.contains("/") ? mainTexPath.substring(mainTexPath.lastIndexOf(47) + 1) : mainTexPath;
        this.w_1484_f = new g_2336_b("minecraft", "Pouch/item_replacer/sword/textures/" + texName + ".png");
        this.t_148_a = this.n_1700_B(this.w_1484_f) ? o_2576_A.w_1484_f(this.w_1484_f) : o_2576_A.G_564_y(this.w_1484_f);
        JsonArray elements = root.getAsJsonArray("elements");
        if (elements == null) {
            return;
        }
        String mainRef = "#" + mainKey;
        for (JsonElement elem : elements) {
            R_4764_Y f;
            JsonObject obj = elem.getAsJsonObject();
            JsonArray from = obj.getAsJsonArray("from");
            JsonArray to = obj.getAsJsonArray("to");
            float x1 = from.get(0).getAsFloat() * 0.0625f;
            float y1 = from.get(1).getAsFloat() * 0.0625f;
            float z1 = from.get(2).getAsFloat() * 0.0625f;
            float x2 = to.get(0).getAsFloat() * 0.0625f;
            float y2 = to.get(1).getAsFloat() * 0.0625f;
            float z2 = to.get(2).getAsFloat() * 0.0625f;
            JsonObject faces = obj.getAsJsonObject("faces");
            n_1700_B cube = new n_1700_B(x1, y1, z1, x2, y2, z2);
            boolean hasFace = false;
            if (faces.has("north") && (f = this.n_1700_B(faces.getAsJsonObject("north"), mainRef)) != null) {
                cube.v_4262_N = f;
                hasFace = true;
            }
            if (faces.has("south") && (f = this.n_1700_B(faces.getAsJsonObject("south"), mainRef)) != null) {
                cube.w_1484_f = f;
                hasFace = true;
            }
            if (faces.has("east") && (f = this.n_1700_B(faces.getAsJsonObject("east"), mainRef)) != null) {
                cube.t_148_a = f;
                hasFace = true;
            }
            if (faces.has("west") && (f = this.n_1700_B(faces.getAsJsonObject("west"), mainRef)) != null) {
                cube.s_956_w = f;
                hasFace = true;
            }
            if (faces.has("up") && (f = this.n_1700_B(faces.getAsJsonObject("up"), mainRef)) != null) {
                cube.u_2550_I = f;
                hasFace = true;
            }
            if (faces.has("down") && (f = this.n_1700_B(faces.getAsJsonObject("down"), mainRef)) != null) {
                cube.M_588_G = f;
                hasFace = true;
            }
            if (!hasFace) continue;
            if (obj.has("rotation")) {
                JsonObject rot = obj.getAsJsonObject("rotation");
                JsonArray origin = rot.getAsJsonArray("origin");
                cube.P_4830_p = origin.get(0).getAsFloat() * 0.0625f;
                cube.h_1847_R = origin.get(1).getAsFloat() * 0.0625f;
                cube.Q_4569_t = origin.get(2).getAsFloat() * 0.0625f;
                cube.n_1700_B(rot.get("axis").getAsString(), rot.get("angle").getAsFloat());
            }
            this.M_588_G.add(cube);
        }
        this.J_1907_R();
        if (root.has("display")) {
            JsonObject display = root.getAsJsonObject("display");
            this.P_4830_p[0] = this.J_1907_R(display, "thirdperson_righthand");
            this.P_4830_p[1] = this.J_1907_R(display, "thirdperson_lefthand");
            this.P_4830_p[2] = this.J_1907_R(display, "firstperson_righthand");
            this.P_4830_p[3] = this.J_1907_R(display, "firstperson_lefthand");
            this.P_4830_p[4] = this.J_1907_R(display, "ground");
            this.P_4830_p[5] = this.J_1907_R(display, "gui");
            this.P_4830_p[6] = this.J_1907_R(display, "head");
            this.P_4830_p[7] = this.J_1907_R(display, "fixed");
        }
    }

    private String n_1700_B(Map<String, String> texMap, JsonObject root) {
        if (texMap.containsKey("0")) {
            return "0";
        }
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        JsonArray elements = root.getAsJsonArray("elements");
        if (elements != null) {
            for (JsonElement elem : elements) {
                JsonObject faces = elem.getAsJsonObject().getAsJsonObject("faces");
                if (faces == null) continue;
                for (Map.Entry fe : faces.entrySet()) {
                    JsonObject face = ((JsonElement)fe.getValue()).getAsJsonObject();
                    if (!face.has("texture")) continue;
                    String ref = face.get("texture").getAsString().replace("#", "");
                    counts.merge(ref, 1, Integer::sum);
                }
            }
        }
        String best = null;
        int bestCount = -1;
        for (Map.Entry e : counts.entrySet()) {
            if (!texMap.containsKey(e.getKey()) || ((String)e.getKey()).equals("particle") || (Integer)e.getValue() <= bestCount) continue;
            best = (String)e.getKey();
            bestCount = (Integer)e.getValue();
        }
        if (best != null) {
            return best;
        }
        for (String k : texMap.keySet()) {
            if (k.equals("particle")) continue;
            return k;
        }
        return texMap.keySet().iterator().next();
    }

    private R_4764_Y n_1700_B(JsonObject obj, String mainRef) {
        String ref;
        if (obj.has("texture") && !(ref = obj.get("texture").getAsString()).equals(mainRef)) {
            return null;
        }
        JsonArray uv = obj.getAsJsonArray("uv");
        return new R_4764_Y(uv.get(0).getAsFloat() / 16.0f, uv.get(1).getAsFloat() / 16.0f, uv.get(2).getAsFloat() / 16.0f, uv.get(3).getAsFloat() / 16.0f);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean n_1700_B(g_2336_b location) {
        try (i_2518_W image = i_2518_W.n_1700_B(MinecraftClient.A_4115_X().T_2506_i().n_1700_B(location).J_1907_R());){
            int width = image.n_1700_B();
            int height = image.J_1907_R();
            int y = 0;
            while (y < height) {
                for (int x = 0; x < width; ++x) {
                    int alpha = image.n_1700_B(x, y) >>> 24 & 0xFF;
                    if (alpha <= 0 || alpha >= 255) continue;
                    boolean bl = true;
                    return bl;
                }
                ++y;
            }
            return false;
        }
        catch (Exception ignored) {
            return true;
        }
    }

    private void J_1907_R() {
        int faces = 0;
        for (n_1700_B cube : this.M_588_G) {
            faces += this.n_1700_B(cube);
        }
        float[] baked = new float[faces * 4 * 8];
        int offset = 0;
        for (n_1700_B cube : this.M_588_G) {
            offset = this.n_1700_B(baked, offset, cube);
        }
        this.s_956_w = baked;
        this.u_2550_I = offset / 8;
        this.M_588_G.clear();
        this.M_588_G.trimToSize();
    }

    private int n_1700_B(n_1700_B cube) {
        int faces = 0;
        if (cube.v_4262_N != null) {
            ++faces;
        }
        if (cube.w_1484_f != null) {
            ++faces;
        }
        if (cube.t_148_a != null) {
            ++faces;
        }
        if (cube.s_956_w != null) {
            ++faces;
        }
        if (cube.u_2550_I != null) {
            ++faces;
        }
        if (cube.M_588_G != null) {
            ++faces;
        }
        return faces;
    }

    private int n_1700_B(float[] out, int offset, n_1700_B cube) {
        R_4764_Y f;
        float x1 = cube.n_1700_B;
        float y1 = cube.J_1907_R;
        float z1 = cube.R_4764_Y;
        float x2 = cube.G_564_y;
        float y2 = cube.P_1922_E;
        float z2 = cube.u_1723_Y;
        if (cube.v_4262_N != null) {
            f = cube.v_4262_N;
            offset = this.n_1700_B(out, offset, cube, x2, y1, z1, f.n_1700_B, f.G_564_y, 0.0f, 0.0f, -1.0f);
            offset = this.n_1700_B(out, offset, cube, x1, y1, z1, f.R_4764_Y, f.G_564_y, 0.0f, 0.0f, -1.0f);
            offset = this.n_1700_B(out, offset, cube, x1, y2, z1, f.R_4764_Y, f.J_1907_R, 0.0f, 0.0f, -1.0f);
            offset = this.n_1700_B(out, offset, cube, x2, y2, z1, f.n_1700_B, f.J_1907_R, 0.0f, 0.0f, -1.0f);
        }
        if (cube.w_1484_f != null) {
            f = cube.w_1484_f;
            offset = this.n_1700_B(out, offset, cube, x1, y1, z2, f.n_1700_B, f.G_564_y, 0.0f, 0.0f, 1.0f);
            offset = this.n_1700_B(out, offset, cube, x2, y1, z2, f.R_4764_Y, f.G_564_y, 0.0f, 0.0f, 1.0f);
            offset = this.n_1700_B(out, offset, cube, x2, y2, z2, f.R_4764_Y, f.J_1907_R, 0.0f, 0.0f, 1.0f);
            offset = this.n_1700_B(out, offset, cube, x1, y2, z2, f.n_1700_B, f.J_1907_R, 0.0f, 0.0f, 1.0f);
        }
        if (cube.t_148_a != null) {
            f = cube.t_148_a;
            offset = this.n_1700_B(out, offset, cube, x2, y1, z2, f.n_1700_B, f.G_564_y, 1.0f, 0.0f, 0.0f);
            offset = this.n_1700_B(out, offset, cube, x2, y1, z1, f.R_4764_Y, f.G_564_y, 1.0f, 0.0f, 0.0f);
            offset = this.n_1700_B(out, offset, cube, x2, y2, z1, f.R_4764_Y, f.J_1907_R, 1.0f, 0.0f, 0.0f);
            offset = this.n_1700_B(out, offset, cube, x2, y2, z2, f.n_1700_B, f.J_1907_R, 1.0f, 0.0f, 0.0f);
        }
        if (cube.s_956_w != null) {
            f = cube.s_956_w;
            offset = this.n_1700_B(out, offset, cube, x1, y1, z1, f.n_1700_B, f.G_564_y, -1.0f, 0.0f, 0.0f);
            offset = this.n_1700_B(out, offset, cube, x1, y1, z2, f.R_4764_Y, f.G_564_y, -1.0f, 0.0f, 0.0f);
            offset = this.n_1700_B(out, offset, cube, x1, y2, z2, f.R_4764_Y, f.J_1907_R, -1.0f, 0.0f, 0.0f);
            offset = this.n_1700_B(out, offset, cube, x1, y2, z1, f.n_1700_B, f.J_1907_R, -1.0f, 0.0f, 0.0f);
        }
        if (cube.u_2550_I != null) {
            f = cube.u_2550_I;
            offset = this.n_1700_B(out, offset, cube, x1, y2, z1, f.n_1700_B, f.J_1907_R, 0.0f, 1.0f, 0.0f);
            offset = this.n_1700_B(out, offset, cube, x1, y2, z2, f.n_1700_B, f.G_564_y, 0.0f, 1.0f, 0.0f);
            offset = this.n_1700_B(out, offset, cube, x2, y2, z2, f.R_4764_Y, f.G_564_y, 0.0f, 1.0f, 0.0f);
            offset = this.n_1700_B(out, offset, cube, x2, y2, z1, f.R_4764_Y, f.J_1907_R, 0.0f, 1.0f, 0.0f);
        }
        if (cube.M_588_G != null) {
            f = cube.M_588_G;
            offset = this.n_1700_B(out, offset, cube, x1, y1, z2, f.n_1700_B, f.J_1907_R, 0.0f, -1.0f, 0.0f);
            offset = this.n_1700_B(out, offset, cube, x1, y1, z1, f.n_1700_B, f.G_564_y, 0.0f, -1.0f, 0.0f);
            offset = this.n_1700_B(out, offset, cube, x2, y1, z1, f.R_4764_Y, f.G_564_y, 0.0f, -1.0f, 0.0f);
            offset = this.n_1700_B(out, offset, cube, x2, y1, z2, f.R_4764_Y, f.J_1907_R, 0.0f, -1.0f, 0.0f);
        }
        return offset;
    }

    private int n_1700_B(float[] out, int offset, n_1700_B cube, float x, float y, float z, float u, float v, float nx, float ny, float nz) {
        if (cube.M_182_A != 0) {
            float dx = x - cube.P_4830_p;
            float dy = y - cube.h_1847_R;
            float dz = z - cube.Q_4569_t;
            float sin = cube.t_1786_h;
            float cos = cube.multiplayerClientSuggestionProvider;
            switch (cube.M_182_A) {
                case 1: {
                    y = cube.h_1847_R + dy * cos - dz * sin;
                    z = cube.Q_4569_t + dy * sin + dz * cos;
                    float rny = ny * cos - nz * sin;
                    float rnz = ny * sin + nz * cos;
                    ny = rny;
                    nz = rnz;
                    break;
                }
                case 2: {
                    x = cube.P_4830_p + dx * cos + dz * sin;
                    z = cube.Q_4569_t - dx * sin + dz * cos;
                    float rnx = nx * cos + nz * sin;
                    float rnz = -nx * sin + nz * cos;
                    nx = rnx;
                    nz = rnz;
                    break;
                }
                case 3: {
                    x = cube.P_4830_p + dx * cos - dy * sin;
                    y = cube.h_1847_R + dx * sin + dy * cos;
                    float rnx = nx * cos - ny * sin;
                    float rny = nx * sin + ny * cos;
                    nx = rnx;
                    ny = rny;
                    break;
                }
            }
        }
        out[offset++] = x - 0.5f;
        out[offset++] = y - 0.5f;
        out[offset++] = z - 0.5f;
        out[offset++] = u;
        out[offset++] = v;
        out[offset++] = nx;
        out[offset++] = ny;
        out[offset++] = nz;
        return offset;
    }

    private J_1907_R J_1907_R(JsonObject display, String key) {
        JsonArray a;
        if (!display.has(key)) {
            return null;
        }
        JsonObject obj = display.getAsJsonObject(key);
        float[] rot = new float[]{0.0f, 0.0f, 0.0f};
        float[] trans = new float[]{0.0f, 0.0f, 0.0f};
        float[] scale = new float[]{1.0f, 1.0f, 1.0f};
        if (obj.has("rotation")) {
            a = obj.getAsJsonArray("rotation");
            rot[0] = a.get(0).getAsFloat();
            rot[1] = a.get(1).getAsFloat();
            rot[2] = a.get(2).getAsFloat();
        }
        if (obj.has("translation")) {
            a = obj.getAsJsonArray("translation");
            trans[0] = a.get(0).getAsFloat();
            trans[1] = a.get(1).getAsFloat();
            trans[2] = a.get(2).getAsFloat();
        }
        if (obj.has("scale")) {
            a = obj.getAsJsonArray("scale");
            scale[0] = a.get(0).getAsFloat();
            scale[1] = a.get(1).getAsFloat();
            scale[2] = a.get(2).getAsFloat();
        }
        return new J_1907_R(rot, trans, scale);
    }

    public void n_1700_B(Z_1993_T stack, ItemTransforms.J_1907_R transformType, g_221_o matrixStack, o_3091_w buffer, int combinedLight, int combinedOverlay) {
        this.n_1700_B();
        if (this.u_2550_I == 0 || this.t_148_a == null) {
            return;
        }
        D_4792_h builder = H_3330_w.R_4764_Y(buffer, this.t_148_a, true, stack.Y_259_p());
        matrixStack.n_1700_B();
        boolean leftHand = transformType == ItemTransforms.J_1907_R.J_1907_R || transformType == ItemTransforms.J_1907_R.G_564_y;
        this.n_1700_B(matrixStack, transformType, leftHand);
        g_221_o.n_1700_B entry = matrixStack.R_4764_Y();
        this.n_1700_B(builder, entry.n_1700_B(), entry.J_1907_R(), combinedLight, combinedOverlay);
        matrixStack.J_1907_R();
    }

    private void n_1700_B(g_221_o matrixStack, ItemTransforms.J_1907_R type, boolean leftHand) {
        J_1907_R dt = this.n_1700_B(type);
        if (dt == null) {
            return;
        }
        dt.n_1700_B(matrixStack, leftHand);
    }

    private J_1907_R n_1700_B(ItemTransforms.J_1907_R type) {
        switch (type) {
            case R_4764_Y: {
                return this.P_4830_p[0];
            }
            case J_1907_R: {
                return this.P_4830_p[1];
            }
            case P_1922_E: {
                return this.P_4830_p[2];
            }
            case G_564_y: {
                return this.P_4830_p[3];
            }
            case w_1484_f: {
                return this.P_4830_p[4];
            }
            case v_4262_N: {
                return this.P_4830_p[5];
            }
            case u_1723_Y: {
                return this.P_4830_p[6];
            }
            case t_148_a: {
                return this.P_4830_p[7];
            }
        }
        return null;
    }

    private void n_1700_B(D_4792_h builder, D_1098_v matrix, o_1290_k normal, int light, int overlay) {
        float[] mesh = this.s_956_w;
        int limit = this.u_2550_I * 8;
        for (int i = 0; i < limit; i += 8) {
            builder.n_1700_B(matrix, mesh[i], mesh[i + 1], mesh[i + 2]).color(255, 255, 255, 255).tex(mesh[i + 3], mesh[i + 4]).R_4764_Y(overlay).J_1907_R(light).n_1700_B(normal, mesh[i + 5], mesh[i + 6], mesh[i + 7]).endVertex();
        }
    }

    private static class J_1907_R {
        final double n_1700_B;
        final double J_1907_R;
        final double R_4764_Y;
        final double G_564_y;
        final w_3785_E P_1922_E;
        final w_3785_E u_1723_Y;
        final float v_4262_N;
        final float w_1484_f;
        final float t_148_a;
        final boolean s_956_w;
        final boolean u_2550_I;
        final boolean M_588_G;

        J_1907_R(float[] rotation, float[] translation, float[] scale) {
            this.n_1700_B = translation[0] * 0.0625f;
            this.J_1907_R = -this.n_1700_B;
            this.R_4764_Y = translation[1] * 0.0625f;
            this.G_564_y = translation[2] * 0.0625f;
            this.P_1922_E = new w_3785_E(rotation[0], rotation[1], rotation[2], true);
            this.u_1723_Y = new w_3785_E(rotation[0], -rotation[1], -rotation[2], true);
            this.v_4262_N = scale[0];
            this.w_1484_f = scale[1];
            this.t_148_a = scale[2];
            this.s_956_w = translation[0] != 0.0f || translation[1] != 0.0f || translation[2] != 0.0f;
            this.u_2550_I = rotation[0] != 0.0f || rotation[1] != 0.0f || rotation[2] != 0.0f;
            this.M_588_G = scale[0] != 1.0f || scale[1] != 1.0f || scale[2] != 1.0f;
        }

        void n_1700_B(g_221_o matrixStack, boolean leftHand) {
            if (this.s_956_w) {
                matrixStack.n_1700_B(leftHand ? this.J_1907_R : this.n_1700_B, this.R_4764_Y, this.G_564_y);
            }
            if (this.u_2550_I) {
                matrixStack.n_1700_B(leftHand ? this.u_1723_Y : this.P_1922_E);
            }
            if (this.M_588_G) {
                matrixStack.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a);
            }
        }
    }

    private static class n_1700_B {
        final float n_1700_B;
        final float J_1907_R;
        final float R_4764_Y;
        final float G_564_y;
        final float P_1922_E;
        final float u_1723_Y;
        R_4764_Y v_4262_N;
        R_4764_Y w_1484_f;
        R_4764_Y t_148_a;
        R_4764_Y s_956_w;
        R_4764_Y u_2550_I;
        R_4764_Y M_588_G;
        float P_4830_p;
        float h_1847_R;
        float Q_4569_t;
        int M_182_A = 0;
        float t_1786_h;
        float multiplayerClientSuggestionProvider = 1.0f;

        n_1700_B(float x1, float y1, float z1, float x2, float y2, float z2) {
            this.n_1700_B = x1;
            this.J_1907_R = y1;
            this.R_4764_Y = z1;
            this.G_564_y = x2;
            this.P_1922_E = y2;
            this.u_1723_Y = z2;
        }

        void n_1700_B(String axis, float angle) {
            switch (axis) {
                case "x": {
                    this.M_182_A = 1;
                    break;
                }
                case "y": {
                    this.M_182_A = 2;
                    break;
                }
                case "z": {
                    this.M_182_A = 3;
                    break;
                }
                default: {
                    this.M_182_A = 0;
                    return;
                }
            }
            float radians = (float)Math.toRadians(angle);
            this.t_1786_h = (float)Math.sin(radians);
            this.multiplayerClientSuggestionProvider = (float)Math.cos(radians);
        }
    }

    private static class R_4764_Y {
        final float n_1700_B;
        final float J_1907_R;
        final float R_4764_Y;
        final float G_564_y;

        R_4764_Y(float u1, float v1, float u2, float v2) {
            this.n_1700_B = u1;
            this.J_1907_R = v1;
            this.R_4764_Y = u2;
            this.G_564_y = v2;
        }
    }
}



