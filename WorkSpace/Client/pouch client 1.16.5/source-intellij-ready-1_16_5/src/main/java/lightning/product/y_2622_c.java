/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  lombok.Generated
 */
package lightning.product;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import lightning.product.C_332_W;
import lightning.product.I_1790_n;
import lightning.product.NumberSetting;
import lightning.product.M_2029_A;
import lightning.product.MultiBooleanSetting;
import lightning.product.O_3016_i;
import lightning.product.S_4088_D;
import lightning.product.V_1176_p;
import lightning.product.W_1488_x;
import lightning.product.Module;
import lightning.product.h_2367_h;
import lightning.product.Setting;
import lightning.product.n_4915_F;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.q_3148_R;
import lightning.product.KeyBindSetting;
import lightning.product.ModeSetting;
import lightning.product.w_2223_C;
import lombok.Generated;

public class y_2622_c
extends I_1790_n<J_1907_R> {
    private static final File u_1723_Y = new File(C_332_W.n_1700_B + "custom\\");

    public y_2622_c() {
        super("temp\\config_manager.file");
    }

    @Override
    @w_2223_C
    public void n_1700_B() {
        u_1723_Y.mkdirs();
        super.n_1700_B();
    }

    @Override
    protected void t_148_a() {
        this.P_1922_E = new J_1907_R();
    }

    @Override
    protected JsonObject s_956_w() {
        JsonObject config = new JsonObject();
        if (((J_1907_R)this.P_1922_E).n_1700_B() != null) {
            config.addProperty("lastLoadedConfig", ((J_1907_R)this.P_1922_E).n_1700_B());
        }
        return config;
    }

    @Override
    protected void n_1700_B(JsonObject jsonObject) {
        if (jsonObject.has("lastLoadedConfig") && !jsonObject.get("lastLoadedConfig").isJsonNull()) {
            ((J_1907_R)this.P_1922_E).n_1700_B(jsonObject.get("lastLoadedConfig").getAsString());
        } else {
            ((J_1907_R)this.P_1922_E).n_1700_B(null);
        }
    }

    @Override
    public void n_1700_B(String name) {
        n_4915_F clientConfig = ClientBootstrap.Y_601_j().u_1723_Y();
        if (clientConfig != null) {
            clientConfig.u_1723_Y(name);
        }
    }

    public String M_588_G() {
        n_4915_F clientConfig = ClientBootstrap.Y_601_j().u_1723_Y();
        if (clientConfig != null) {
            return clientConfig.multiplayerClientSuggestionProvider();
        }
        return null;
    }

    public List<String> P_4830_p() {
        File[] files = u_1723_Y.listFiles((dir, name) -> name.endsWith(".cfg"));
        if (files == null) {
            return Collections.emptyList();
        }
        ArrayList<String> names = new ArrayList<String>();
        for (File file : files) {
            names.add(file.getName().replace(".cfg", ""));
        }
        return names;
    }

    public void J_1907_R(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.err.println("Cannot load config: name is null or empty");
            return;
        }
        File file = new File(u_1723_Y, name.trim() + ".cfg");
        if (!file.exists()) {
            return;
        }
        try {
            ClientBootstrap instance = ClientBootstrap.Y_601_j();
            if (instance == null || instance.J_1907_R() == null || instance.J_1907_R().u_1723_Y().isEmpty()) {
                return;
            }
            String fileContent = Files.readString(file.toPath());
            String processedContent = this.G_564_y ? W_1488_x.J_1907_R(fileContent) : fileContent;
            JsonObject config = new JsonParser().parse(processedContent).getAsJsonObject();
            if (config.has("module")) {
                String savedTheme;
                this.J_1907_R(config.getAsJsonObject("module"));
                ((J_1907_R)this.P_1922_E).n_1700_B(name);
                this.G_564_y();
                n_4915_F clientConfig = instance.u_1723_Y();
                if (clientConfig != null) {
                    clientConfig.R_4764_Y(name);
                }
                if (config.has("selectedTheme") && !config.get("selectedTheme").isJsonNull() && (savedTheme = config.get("selectedTheme").getAsString()) != null && !savedTheme.trim().isEmpty()) {
                    this.u_1723_Y(savedTheme);
                }
            }
        }
        catch (Exception e) {
            System.err.println("Failed to load config: " + name + " - " + e.getMessage());
        }
    }

    public void R_4764_Y(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.err.println("Cannot save config: name is null or empty");
            return;
        }
        u_1723_Y.mkdirs();
        File file = new File(u_1723_Y, name.trim() + ".cfg");
        try {
            JsonObject config = new JsonObject();
            config.add("module", (JsonElement)this.Q_4569_t());
            config.addProperty("creationDate", (Number)System.currentTimeMillis());
            config.addProperty("creator", S_4088_D.n_1700_B());
            String currentTheme = n_4915_F.Y_601_j();
            if (currentTheme != null && !currentTheme.trim().isEmpty()) {
                config.addProperty("selectedTheme", currentTheme);
            }
            String jsonContent = n_1700_B.toJson((JsonElement)config);
            String outputContent = this.G_564_y ? W_1488_x.n_1700_B(jsonContent) : jsonContent;
            Files.writeString(file.toPath(), (CharSequence)outputContent, new OpenOption[0]);
        }
        catch (Exception e) {
            System.err.println("Failed to save config: " + name + " - " + e.getMessage());
        }
    }

    public boolean G_564_y(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.err.println("Cannot delete config: name is null or empty");
            return false;
        }
        boolean deleted = new File(u_1723_Y, name.trim() + ".cfg").delete();
        if (deleted && name.equals(((J_1907_R)this.P_1922_E).n_1700_B())) {
            ((J_1907_R)this.P_1922_E).n_1700_B(null);
            this.G_564_y();
        }
        return deleted;
    }

    public n_1700_B P_1922_E(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.err.println("Cannot get config info: name is null or empty");
            return null;
        }
        File file = new File(u_1723_Y, name.trim() + ".cfg");
        if (!file.exists()) {
            return null;
        }
        try {
            String fileContent = Files.readString(file.toPath());
            String processedContent = this.G_564_y ? W_1488_x.J_1907_R(fileContent) : fileContent;
            JsonObject config = new JsonParser().parse(processedContent).getAsJsonObject();
            long creationDate = config.has("creationDate") && !config.get("creationDate").isJsonNull() ? config.get("creationDate").getAsLong() : System.currentTimeMillis();
            String creator = config.has("creator") && !config.get("creator").isJsonNull() ? config.get("creator").getAsString() : "Unknown";
            return new n_1700_B(name, creationDate, creator);
        }
        catch (Exception e) {
            return new n_1700_B(name);
        }
    }

    private void J_1907_R(JsonObject modules) {
        boolean prev = Module.P_4830_p();
        Module.G_564_y(true);
        try {
            ClientBootstrap.Y_601_j().J_1907_R().u_1723_Y().forEach(module -> {
                JsonObject moduleData = modules.getAsJsonObject(module.G_564_y().toLowerCase());
                if (moduleData == null) {
                    return;
                }
                module.n_1700_B(false);
                this.n_1700_B(moduleData, "key", v -> module.n_1700_B(v.getAsInt()));
                this.n_1700_B(moduleData, "enabled", v -> module.n_1700_B(v.getAsBoolean()));
                this.n_1700_B(moduleData, "togglemode", v -> {
                    try {
                        module.n_1700_B(Module.n_1700_B.valueOf(v.getAsString()));
                    }
                    catch (Exception e) {
                        module.n_1700_B(Module.n_1700_B.n_1700_B);
                    }
                });
                this.n_1700_B(moduleData, "keybindvisible", v -> module.R_4764_Y(v.getAsBoolean()));
                module.u_2550_I().forEach(setting -> this.n_1700_B(moduleData, (Setting<?>)setting));
            });
        }
        finally {
            Module.G_564_y(prev);
        }
    }

    private void n_1700_B(JsonObject moduleData, Setting<?> setting) {
        JsonElement element = moduleData.get(setting.n_1700_B());
        if (element == null || element.isJsonNull()) {
            return;
        }
        try {
            if (setting instanceof NumberSetting) {
                ((NumberSetting)setting).n_1700_B(Float.valueOf(element.getAsFloat()));
            } else if (setting instanceof BooleanSetting) {
                if (element.isJsonObject()) {
                    JsonObject boolData = element.getAsJsonObject();
                    BooleanSetting boolSetting = (BooleanSetting)setting;
                    this.n_1700_B(boolData, "value", v -> boolSetting.n_1700_B((Boolean)v.getAsBoolean()));
                    this.n_1700_B(boolData, "bind", v -> boolSetting.n_1700_B(v.getAsInt()));
                    this.n_1700_B(boolData, "keybindvisible", v -> boolSetting.R_4764_Y(v.getAsBoolean()));
                    this.n_1700_B(boolData, "togglemode", v -> {
                        try {
                            boolSetting.n_1700_B(Module.n_1700_B.valueOf(v.getAsString()));
                        }
                        catch (Exception e) {
                            boolSetting.n_1700_B(Module.n_1700_B.n_1700_B);
                        }
                    });
                }
            } else if (setting instanceof h_2367_h) {
                ((h_2367_h)setting).n_1700_B(element.getAsInt());
            } else if (setting instanceof ModeSetting) {
                ModeSetting modeSetting = (ModeSetting)setting;
                String loadedValue = element.getAsString();
                boolean isValid = false;
                for (String validValue : modeSetting.G_564_y) {
                    if (!validValue.equalsIgnoreCase(loadedValue)) continue;
                    isValid = true;
                    break;
                }
                if (isValid) {
                    modeSetting.n_1700_B(loadedValue);
                } else {
                    modeSetting.n_1700_B(modeSetting.P_1922_E);
                }
            } else if (setting instanceof KeyBindSetting) {
                ((KeyBindSetting)setting).n_1700_B(element.getAsInt());
            } else if (setting instanceof O_3016_i) {
                ((O_3016_i)setting).n_1700_B(element.getAsString());
            } else if (setting instanceof MultiBooleanSetting && element.isJsonObject()) {
                JsonObject multiData = element.getAsJsonObject();
                MultiBooleanSetting multiSetting = (MultiBooleanSetting)setting;
                ((List)multiSetting.J_1907_R()).forEach(option -> {
                    JsonElement optionElement = multiData.get(option.n_1700_B());
                    if (optionElement != null && !optionElement.isJsonNull()) {
                        option.n_1700_B((Boolean)optionElement.getAsBoolean());
                    }
                });
            }
        }
        catch (Exception e) {
            System.err.println("Failed to load setting " + setting.n_1700_B() + ": " + e.getMessage());
        }
    }

    private JsonObject Q_4569_t() {
        JsonObject modules = new JsonObject();
        ClientBootstrap.Y_601_j().J_1907_R().u_1723_Y().forEach(module -> {
            JsonObject moduleData = new JsonObject();
            moduleData.addProperty("key", (Number)module.v_4262_N());
            moduleData.addProperty("enabled", Boolean.valueOf(module.w_1484_f()));
            moduleData.addProperty("togglemode", module.t_148_a().name());
            moduleData.addProperty("keybindvisible", Boolean.valueOf(module.s_956_w()));
            module.u_2550_I().forEach(setting -> this.J_1907_R(moduleData, (Setting<?>)setting));
            modules.add(module.G_564_y().toLowerCase(), (JsonElement)moduleData);
        });
        return modules;
    }

    private void J_1907_R(JsonObject moduleData, Setting<?> setting) {
        try {
            if (setting instanceof BooleanSetting) {
                BooleanSetting boolSetting = (BooleanSetting)setting;
                JsonObject boolData = new JsonObject();
                boolData.addProperty("value", boolSetting.t_148_a());
                boolData.addProperty("bind", (Number)boolSetting.u_2550_I());
                boolData.addProperty("keybindvisible", Boolean.valueOf(boolSetting.P_4830_p()));
                boolData.addProperty("togglemode", boolSetting.M_588_G().name());
                moduleData.add(setting.n_1700_B(), (JsonElement)boolData);
            } else if (setting instanceof NumberSetting) {
                moduleData.addProperty(setting.n_1700_B(), (Number)((NumberSetting)setting).J_1907_R());
            } else if (setting instanceof ModeSetting) {
                moduleData.addProperty(setting.n_1700_B(), (String)((ModeSetting)setting).J_1907_R());
            } else if (setting instanceof h_2367_h) {
                moduleData.addProperty(setting.n_1700_B(), (Number)((h_2367_h)setting).J_1907_R());
            } else if (setting instanceof KeyBindSetting) {
                moduleData.addProperty(setting.n_1700_B(), (Number)((KeyBindSetting)setting).J_1907_R());
            } else if (setting instanceof O_3016_i) {
                moduleData.addProperty(setting.n_1700_B(), (String)((O_3016_i)setting).J_1907_R());
            } else if (setting instanceof MultiBooleanSetting) {
                MultiBooleanSetting multiSetting = (MultiBooleanSetting)setting;
                JsonObject multiData = new JsonObject();
                ((List)multiSetting.J_1907_R()).forEach(option -> multiData.addProperty(option.n_1700_B(), option.t_148_a()));
                moduleData.add(setting.n_1700_B(), (JsonElement)multiData);
            }
        }
        catch (Exception e) {
            System.err.println("Failed to save setting " + setting.n_1700_B() + ": " + e.getMessage());
        }
    }

    private void n_1700_B(JsonObject object, String key, Consumer<JsonElement> consumer) {
        JsonElement element = object.get(key);
        if (element != null && !element.isJsonNull()) {
            consumer.accept(element);
        }
    }

    private void u_1723_Y(String themeName) {
        try {
            String cleanName;
            V_1176_p.n_1700_B theme;
            q_3148_R themeEditor = q_3148_R.P_4830_p();
            if (themeEditor == null) {
                return;
            }
            Map<String, int[]> defaultPresets = M_2029_A.n_1700_B();
            if (defaultPresets.containsKey(themeName)) {
                q_3148_R.n_1700_B(themeName, defaultPresets.get(themeName));
                n_4915_F.v_4262_N(themeName);
                JsonObject elementColors = M_2029_A.J_1907_R().get(themeName);
                if (elementColors != null) {
                    ClientBootstrap.Y_601_j().M_182_A().J_1907_R(elementColors);
                }
                return;
            }
            V_1176_p themeManager = ClientBootstrap.Y_601_j().G_564_y();
            if (themeManager != null && (theme = themeManager.n_1700_B(cleanName = themeName.replace("\u0418\u043c\u044f: ", ""))) != null && theme.G_564_y() != null) {
                q_3148_R.n_1700_B(themeName, theme.G_564_y());
                n_4915_F.v_4262_N(themeName);
                if (theme.u_1723_Y() != null) {
                    ClientBootstrap.Y_601_j().M_182_A().J_1907_R(theme.u_1723_Y());
                }
            }
        }
        catch (Exception e) {
            System.err.println("Failed to apply theme from config: " + themeName + " - " + e.getMessage());
        }
    }

    public void h_1847_R() {
        boolean prev = Module.P_4830_p();
        Module.G_564_y(true);
        try {
            ClientBootstrap.Y_601_j().J_1907_R().u_1723_Y().forEach(module -> {
                module.n_1700_B(false);
                module.n_1700_B(-100);
                module.n_1700_B(Module.n_1700_B.n_1700_B);
                module.R_4764_Y(true);
                module.u_2550_I().forEach(setting -> {
                    try {
                        if (setting instanceof BooleanSetting) {
                            BooleanSetting s = (BooleanSetting)setting;
                            s.n_1700_B((Boolean)s.G_564_y);
                            s.n_1700_B(-100);
                            s.n_1700_B(Module.n_1700_B.n_1700_B);
                            s.R_4764_Y(true);
                        } else if (setting instanceof NumberSetting) {
                            NumberSetting s = (NumberSetting)setting;
                            s.n_1700_B(Float.valueOf(s.v_4262_N));
                        } else if (setting instanceof ModeSetting) {
                            ModeSetting s = (ModeSetting)setting;
                            s.n_1700_B(s.P_1922_E);
                        } else if (setting instanceof h_2367_h) {
                            h_2367_h s = (h_2367_h)setting;
                            s.n_1700_B(s.G_564_y);
                        } else if (setting instanceof KeyBindSetting) {
                            KeyBindSetting s = (KeyBindSetting)setting;
                            s.n_1700_B(-1);
                        } else if (setting instanceof O_3016_i) {
                            O_3016_i s = (O_3016_i)setting;
                            s.n_1700_B("");
                        } else if (setting instanceof MultiBooleanSetting) {
                            MultiBooleanSetting s = (MultiBooleanSetting)setting;
                            ((List)s.J_1907_R()).forEach(opt -> opt.n_1700_B((Boolean)opt.G_564_y));
                        }
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                });
            });
        }
        finally {
            Module.G_564_y(prev);
        }
        n_4915_F clientConfig = ClientBootstrap.Y_601_j().u_1723_Y();
        if (clientConfig != null) {
            clientConfig.M_588_G();
        }
    }

    public static class J_1907_R {
        private String n_1700_B;

        @Generated
        public String n_1700_B() {
            return this.n_1700_B;
        }

        @Generated
        public void n_1700_B(String lastLoadedConfig) {
            this.n_1700_B = lastLoadedConfig;
        }
    }

    public static class n_1700_B {
        private final String n_1700_B;
        private final File J_1907_R;
        private final long R_4764_Y;
        private final String G_564_y;

        public n_1700_B(String name) {
            this.n_1700_B = name;
            this.J_1907_R = new File(u_1723_Y, name + ".cfg");
            this.R_4764_Y = System.currentTimeMillis();
            this.G_564_y = S_4088_D.n_1700_B() != null ? S_4088_D.n_1700_B() : "Unknown";
        }

        public n_1700_B(String name, long creationDate, String creator) {
            this.n_1700_B = name;
            this.J_1907_R = new File(u_1723_Y, name + ".cfg");
            this.R_4764_Y = creationDate;
            this.G_564_y = creator != null ? creator : "Unknown";
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
        public long R_4764_Y() {
            return this.R_4764_Y;
        }

        @Generated
        public String G_564_y() {
            return this.G_564_y;
        }
    }
}



