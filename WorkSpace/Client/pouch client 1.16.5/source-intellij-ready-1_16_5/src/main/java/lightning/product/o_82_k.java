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
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import lightning.product.I_1790_n;
import lightning.product.J_3635_s;
import lightning.product.W_1488_x;
import lightning.product.h_2367_h;
import lightning.product.Setting;
import lightning.product.BooleanSetting;
import lightning.product.ModeSetting;
import lombok.Generated;

public class o_82_k
extends I_1790_n<n_1700_B> {
    public o_82_k() {
        super("temp\\drags.file");
    }

    @Override
    protected void t_148_a() {
        this.P_1922_E = new n_1700_B();
    }

    @Override
    protected JsonObject s_956_w() {
        JsonObject config = new JsonObject();
        JsonObject positions = new JsonObject();
        ((n_1700_B)this.P_1922_E).R_4764_Y().forEach((name, drag) -> {
            JsonObject pos = new JsonObject();
            pos.addProperty("x", (Number)Float.valueOf(drag.J_1907_R()));
            pos.addProperty("y", (Number)Float.valueOf(drag.R_4764_Y()));
            positions.add(name, (JsonElement)pos);
        });
        JsonArray order = new JsonArray();
        ((n_1700_B)this.P_1922_E).G_564_y().forEach(drag -> order.add(drag.n_1700_B()));
        JsonObject settings = new JsonObject();
        ((n_1700_B)this.P_1922_E).R_4764_Y().forEach((name, drag) -> {
            JsonObject dragSettings = new JsonObject();
            drag.Y_601_j().forEach(setting -> this.n_1700_B(dragSettings, (Setting<?>)setting));
            if (dragSettings.size() > 0) {
                settings.add(name, (JsonElement)dragSettings);
            }
        });
        config.add("positions", (JsonElement)positions);
        config.add("renderOrder", (JsonElement)order);
        config.add("settings", (JsonElement)settings);
        return config;
    }

    @Override
    protected void n_1700_B(JsonObject jsonObject) {
        this.R_4764_Y(jsonObject);
        this.G_564_y(jsonObject);
        this.P_1922_E(jsonObject);
    }

    private void R_4764_Y(JsonObject jsonObject) {
        this.n_1700_B(jsonObject, "positions", positionsElement -> {
            ((n_1700_B)this.P_1922_E).n_1700_B().clear();
            ((n_1700_B)this.P_1922_E).J_1907_R().clear();
            JsonObject positions = positionsElement.getAsJsonObject();
            positions.entrySet().forEach(entry -> {
                String name = (String)entry.getKey();
                JsonObject pos = ((JsonElement)entry.getValue()).getAsJsonObject();
                try {
                    float x = pos.get("x").getAsFloat();
                    float y = pos.get("y").getAsFloat();
                    ((n_1700_B)this.P_1922_E).n_1700_B().put(name, Float.valueOf(x));
                    ((n_1700_B)this.P_1922_E).J_1907_R().put(name, Float.valueOf(y));
                    J_3635_s drag = ((n_1700_B)this.P_1922_E).R_4764_Y().get(name);
                    if (drag != null) {
                        drag.n_1700_B(x);
                        drag.J_1907_R(y);
                    }
                }
                catch (Exception e) {
                    System.err.println("Failed to parse position for " + name + ": " + e.getMessage());
                }
            });
        });
    }

    private void G_564_y(JsonObject jsonObject) {
        this.n_1700_B(jsonObject, "renderOrder", orderElement -> {
            ((n_1700_B)this.P_1922_E).G_564_y().clear();
            JsonArray renderOrder = orderElement.getAsJsonArray();
            renderOrder.forEach(element -> {
                try {
                    J_3635_s drag = ((n_1700_B)this.P_1922_E).R_4764_Y().get(element.getAsString());
                    if (drag != null) {
                        ((n_1700_B)this.P_1922_E).G_564_y().add(drag);
                    }
                }
                catch (Exception e) {
                    System.err.println("Failed to parse render order element: " + e.getMessage());
                }
            });
            ((n_1700_B)this.P_1922_E).R_4764_Y().values().forEach(drag -> {
                if (!((n_1700_B)this.P_1922_E).G_564_y().contains(drag)) {
                    ((n_1700_B)this.P_1922_E).G_564_y().add((J_3635_s)drag);
                }
            });
        });
    }

    private void P_1922_E(JsonObject jsonObject) {
        this.n_1700_B(jsonObject, "settings", settingsElement -> {
            JsonObject settings = settingsElement.getAsJsonObject();
            settings.entrySet().forEach(entry -> {
                J_3635_s drag = ((n_1700_B)this.P_1922_E).R_4764_Y().get(entry.getKey());
                if (drag != null) {
                    try {
                        JsonObject dragSettings = ((JsonElement)entry.getValue()).getAsJsonObject();
                        drag.Y_601_j().forEach(setting -> this.J_1907_R(dragSettings, (Setting<?>)setting));
                    }
                    catch (Exception e) {
                        System.err.println("Failed to load settings for " + (String)entry.getKey() + ": " + e.getMessage());
                    }
                }
            });
        });
    }

    @Override
    public void n_1700_B(J_3635_s draggable) {
        String name = draggable.n_1700_B();
        if (((n_1700_B)this.P_1922_E).n_1700_B().containsKey(name) && ((n_1700_B)this.P_1922_E).J_1907_R().containsKey(name)) {
            draggable.n_1700_B(((n_1700_B)this.P_1922_E).n_1700_B().remove(name).floatValue());
            draggable.J_1907_R(((n_1700_B)this.P_1922_E).J_1907_R().remove(name).floatValue());
        }
        ((n_1700_B)this.P_1922_E).R_4764_Y().put(name, draggable);
        if (!((n_1700_B)this.P_1922_E).G_564_y().contains(draggable)) {
            ((n_1700_B)this.P_1922_E).G_564_y().add(draggable);
        }
    }

    public void J_1907_R(J_3635_s draggable) {
        ((n_1700_B)this.P_1922_E).G_564_y().remove(draggable);
        ((n_1700_B)this.P_1922_E).G_564_y().add(draggable);
    }

    public void R_4764_Y(J_3635_s except) {
        this.h_1847_R().forEach(d -> {
            if (d != null && d != except) {
                d.R_4764_Y(false);
            }
        });
    }

    public void M_588_G() {
        ((n_1700_B)this.P_1922_E).R_4764_Y().values().forEach(drag -> {
            drag.n_1700_B(drag.u_1723_Y());
            drag.J_1907_R(drag.v_4262_N());
        });
        this.G_564_y();
    }

    public LinkedHashMap<String, J_3635_s> P_4830_p() {
        return ((n_1700_B)this.P_1922_E).R_4764_Y();
    }

    public List<J_3635_s> h_1847_R() {
        return ((n_1700_B)this.P_1922_E).G_564_y();
    }

    public void G_564_y(J_3635_s draggable) {
        try {
            JsonObject settings;
            JsonObject dragSettings;
            JsonObject config = this.M_182_A();
            if (config != null && config.has("settings") && (dragSettings = (settings = config.getAsJsonObject("settings")).getAsJsonObject(draggable.n_1700_B())) != null) {
                draggable.Y_601_j().forEach(setting -> this.J_1907_R(dragSettings, (Setting<?>)setting));
            }
        }
        catch (Exception e) {
            System.err.println("Failed to load settings for draggable " + draggable.n_1700_B() + ": " + e.getMessage());
        }
    }

    private void n_1700_B(JsonObject dragSettings, Setting<?> setting) {
        try {
            if (setting instanceof BooleanSetting) {
                dragSettings.addProperty(setting.n_1700_B(), ((BooleanSetting)setting).t_148_a());
            } else if (setting instanceof ModeSetting) {
                dragSettings.addProperty(setting.n_1700_B(), (String)((ModeSetting)setting).J_1907_R());
            } else if (setting instanceof h_2367_h) {
                dragSettings.addProperty(setting.n_1700_B(), (Number)((h_2367_h)setting).J_1907_R());
            }
        }
        catch (Exception e) {
            System.err.println("Failed to save setting " + setting.n_1700_B() + ": " + e.getMessage());
        }
    }

    private void J_1907_R(JsonObject dragSettings, Setting<?> setting) {
        this.n_1700_B(dragSettings, setting.n_1700_B(), element -> {
            try {
                if (setting instanceof BooleanSetting) {
                    ((BooleanSetting)setting).n_1700_B((Boolean)element.getAsBoolean());
                } else if (setting instanceof ModeSetting) {
                    ((ModeSetting)setting).n_1700_B(element.getAsString());
                } else if (setting instanceof h_2367_h) {
                    ((h_2367_h)setting).n_1700_B(element.getAsInt());
                }
            }
            catch (Exception e) {
                System.err.println("Failed to load setting " + setting.n_1700_B() + ": " + e.getMessage());
            }
        });
    }

    private JsonObject M_182_A() {
        try {
            if (!this.R_4764_Y.exists()) {
                return null;
            }
            String content = Files.readString(this.R_4764_Y.toPath());
            return new JsonParser().parse(this.G_564_y ? W_1488_x.J_1907_R(content) : content).getAsJsonObject();
        }
        catch (Exception e) {
            System.err.println("Failed to load dragging config: " + e.getMessage());
            return null;
        }
    }

    private void n_1700_B(JsonObject object, String key, Consumer<JsonElement> consumer) {
        JsonElement element = object.get(key);
        if (element != null && !element.isJsonNull()) {
            consumer.accept(element);
        }
    }

    public JsonObject Q_4569_t() {
        JsonObject allColors = new JsonObject();
        ((n_1700_B)this.P_1922_E).R_4764_Y().forEach((name, drag) -> {
            JsonObject dragColors = new JsonObject();
            drag.Y_601_j().forEach(setting -> {
                if (setting instanceof h_2367_h) {
                    h_2367_h cs = (h_2367_h)setting;
                    dragColors.addProperty(setting.n_1700_B(), (Number)cs.J_1907_R());
                }
            });
            if (dragColors.size() > 0) {
                allColors.add(name, (JsonElement)dragColors);
            }
        });
        return allColors;
    }

    public void J_1907_R(JsonObject colorSettings) {
        if (colorSettings == null) {
            return;
        }
        colorSettings.entrySet().forEach(entry -> {
            J_3635_s drag = ((n_1700_B)this.P_1922_E).R_4764_Y().get(entry.getKey());
            if (drag != null && ((JsonElement)entry.getValue()).isJsonObject()) {
                JsonObject dragColors = ((JsonElement)entry.getValue()).getAsJsonObject();
                drag.Y_601_j().forEach(setting -> {
                    if (setting instanceof h_2367_h) {
                        h_2367_h cs = (h_2367_h)setting;
                        JsonElement colorElement = dragColors.get(setting.n_1700_B());
                        if (colorElement != null && !colorElement.isJsonNull()) {
                            cs.n_1700_B(colorElement.getAsInt());
                        }
                    }
                });
            }
        });
        this.G_564_y();
    }

    public static class n_1700_B {
        private final Map<String, Float> n_1700_B = new LinkedHashMap<String, Float>();
        private final Map<String, Float> J_1907_R = new LinkedHashMap<String, Float>();
        private final LinkedHashMap<String, J_3635_s> R_4764_Y = new LinkedHashMap();
        private final List<J_3635_s> G_564_y = new ArrayList<J_3635_s>();

        @Generated
        public Map<String, Float> n_1700_B() {
            return this.n_1700_B;
        }

        @Generated
        public Map<String, Float> J_1907_R() {
            return this.J_1907_R;
        }

        @Generated
        public LinkedHashMap<String, J_3635_s> R_4764_Y() {
            return this.R_4764_Y;
        }

        @Generated
        public List<J_3635_s> G_564_y() {
            return this.G_564_y;
        }
    }
}

