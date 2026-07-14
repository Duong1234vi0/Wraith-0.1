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
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.util.List;
import java.util.function.Consumer;
import lightning.product.I_1790_n;
import lightning.product.NumberSetting;
import lightning.product.K_1200_E;
import lightning.product.MultiBooleanSetting;
import lightning.product.O_3016_i;
import lightning.product.S_4088_D;
import lightning.product.V_1176_p;
import lightning.product.W_1488_x;
import lightning.product.Module;
import lightning.product.c_2086_l;
import lightning.product.h_2367_h;
import lightning.product.Setting;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.q_3148_R;
import lightning.product.KeyBindSetting;
import lightning.product.ModeSetting;
import lightning.product.y_2622_c;
import lombok.Generated;

public class n_4915_F
extends I_1790_n<n_1700_B> {
    private static String u_1723_Y = null;

    public n_4915_F() {
        super("temp\\client_config.file");
    }

    @Override
    protected void t_148_a() {
        this.P_1922_E = new n_1700_B();
    }

    @Override
    protected JsonObject s_956_w() {
        JsonObject config = new JsonObject();
        config.add("modules", (JsonElement)this.Y_259_p());
        if (((n_1700_B)this.P_1922_E).n_1700_B() != null) {
            config.addProperty("selectedTheme", ((n_1700_B)this.P_1922_E).n_1700_B());
        }
        if (((n_1700_B)this.P_1922_E).J_1907_R() != null) {
            config.addProperty("selectedLanguage", ((n_1700_B)this.P_1922_E).J_1907_R());
        }
        if (((n_1700_B)this.P_1922_E).R_4764_Y() != null) {
            config.addProperty("selectedConfig", ((n_1700_B)this.P_1922_E).R_4764_Y());
        }
        if (((n_1700_B)this.P_1922_E).G_564_y() != null) {
            config.addProperty("favoriteConfig", ((n_1700_B)this.P_1922_E).G_564_y());
        }
        return config;
    }

    @Override
    protected void n_1700_B(JsonObject jsonObject) {
        this.n_1700_B(jsonObject, "selectedTheme", v -> ((n_1700_B)this.P_1922_E).n_1700_B(v.getAsString()));
        this.n_1700_B(jsonObject, "selectedLanguage", v -> ((n_1700_B)this.P_1922_E).J_1907_R(v.getAsString()));
        this.n_1700_B(jsonObject, "selectedConfig", v -> ((n_1700_B)this.P_1922_E).R_4764_Y(v.getAsString()));
        this.n_1700_B(jsonObject, "favoriteConfig", v -> ((n_1700_B)this.P_1922_E).G_564_y(v.getAsString()));
    }

    public void M_588_G() {
        try {
            this.G_564_y();
        }
        catch (Exception e) {
            System.err.println("\u041e\u0448\u0438\u0431\u043a\u0430 \u0441\u043e\u0445\u0440\u0430\u043d\u0435\u043d\u0438\u044f \u043d\u0430\u0441\u0442\u0440\u043e\u0435\u043a \u043a\u043b\u0438\u0435\u043d\u0442\u0430: " + e.getMessage());
        }
    }

    public void P_4830_p() {
        if (this.R_4764_Y.exists()) {
            try {
                String selectedLanguage;
                String fileContent = Files.readString(this.R_4764_Y.toPath());
                String processedContent = this.G_564_y ? W_1488_x.J_1907_R(fileContent) : fileContent;
                JsonObject config = new JsonParser().parse(processedContent).getAsJsonObject();
                if (config.has("modules")) {
                    this.J_1907_R(config.getAsJsonObject("modules"));
                }
                if ((selectedLanguage = ((n_1700_B)this.P_1922_E).J_1907_R()) != null && !selectedLanguage.trim().isEmpty()) {
                    c_2086_l.n_1700_B(selectedLanguage);
                } else {
                    c_2086_l.n_1700_B("ru");
                }
                this.h_1847_R();
                this.Q_4569_t();
            }
            catch (Exception e) {
                System.err.println("Failed to apply client config settings: " + e.getMessage());
            }
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

    private JsonObject Y_259_p() {
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

    public void h_1847_R() {
        if (((n_1700_B)this.P_1922_E).n_1700_B() == null || ((n_1700_B)this.P_1922_E).n_1700_B().trim().isEmpty()) {
            return;
        }
        V_1176_p themeManager = ClientBootstrap.Y_601_j().G_564_y();
        if (themeManager == null) {
            return;
        }
        V_1176_p.n_1700_B theme = themeManager.n_1700_B(((n_1700_B)this.P_1922_E).n_1700_B());
        if (theme == null) {
            String presetThemeName = ((n_1700_B)this.P_1922_E).n_1700_B();
            if (presetThemeName.startsWith("\u0418\u043c\u044f: ")) {
                presetThemeName = presetThemeName.substring(5);
            }
            theme = themeManager.n_1700_B(presetThemeName);
        }
        if (theme != null && theme.G_564_y() != null) {
            String themePresetName = theme.R_4764_Y() != null ? theme.R_4764_Y() : ((n_1700_B)this.P_1922_E).n_1700_B();
            this.n_1700_B(themePresetName, theme.G_564_y());
            n_4915_F.v_4262_N(themePresetName);
        }
    }

    @Override
    private void n_1700_B(int[] colors) {
        try {
            K_1200_E[] settings = K_1200_E.values();
            for (int i = 0; i < Math.min(colors.length, settings.length); ++i) {
                if (!q_3148_R.u_1723_Y.containsKey((Object)settings[i])) continue;
                h_2367_h colorSetting = q_3148_R.u_1723_Y.get((Object)settings[i]);
                Runnable originalCallback = colorSetting.u_1723_Y();
                colorSetting.n_1700_B((Runnable)null);
                colorSetting.n_1700_B(colors[i]);
                colorSetting.n_1700_B(originalCallback);
            }
        }
        catch (Exception e) {
            System.err.println("\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438\u043c\u0435\u043d\u0435\u043d\u0438\u044f \u0446\u0432\u0435\u0442\u043e\u0432 \u0442\u0435\u043c\u044b: " + e.getMessage());
        }
    }

    private void n_1700_B(String themeName, int[] colors) {
        try {
            this.n_1700_B(colors);
            this.J_1907_R(themeName, colors);
        }
        catch (Exception e) {
            System.err.println("\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438\u043c\u0435\u043d\u0435\u043d\u0438\u044f \u0442\u0435\u043c\u044b \u0441 preset: " + e.getMessage());
        }
    }

    private void J_1907_R(String themeName, int[] colors) {
        try {
            Field currentPresetField = q_3148_R.class.getDeclaredField("Y_259_p");
            currentPresetField.setAccessible(true);
            q_3148_R.n_1700_B newPreset = new q_3148_R.n_1700_B(themeName, colors, S_4088_D.n_1700_B(), true);
            if (Modifier.isStatic(currentPresetField.getModifiers())) {
                currentPresetField.set(null, newPreset);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Override
    public void n_1700_B(String themeName) {
        try {
            ((n_1700_B)this.P_1922_E).n_1700_B(themeName);
            n_4915_F.v_4262_N(themeName);
            this.G_564_y();
        }
        catch (Exception e) {
            System.err.println("\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u0441\u043e\u0445\u0440\u0430\u043d\u0435\u043d\u0438\u0438 \u043f\u0440\u0438\u043c\u0435\u043d\u0435\u043d\u043d\u043e\u0439 \u0442\u0435\u043c\u044b: " + e.getMessage());
        }
    }

    public void J_1907_R(String themeName) {
        try {
            ((n_1700_B)this.P_1922_E).n_1700_B(themeName);
            n_4915_F.v_4262_N(themeName);
            this.G_564_y();
        }
        catch (Exception e) {
            System.err.println("\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u0441\u043e\u0445\u0440\u0430\u043d\u0435\u043d\u0438\u0438 \u0441\u043e\u0437\u0434\u0430\u043d\u043d\u043e\u0439 \u0442\u0435\u043c\u044b: " + e.getMessage());
        }
    }

    public void R_4764_Y(String configName) {
        try {
            ((n_1700_B)this.P_1922_E).R_4764_Y(configName);
            this.G_564_y();
        }
        catch (Exception e) {
            System.err.println("\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u0441\u043e\u0445\u0440\u0430\u043d\u0435\u043d\u0438\u0438 \u0432\u044b\u0431\u0440\u0430\u043d\u043d\u043e\u0433\u043e \u043a\u043e\u043d\u0444\u0438\u0433\u0430: " + e.getMessage());
        }
    }

    public void Q_4569_t() {
        y_2622_c configManager = ClientBootstrap.Y_601_j().R_4764_Y();
        if (configManager == null) {
            return;
        }
        String favoriteConfig = ((n_1700_B)this.P_1922_E).G_564_y();
        if (favoriteConfig != null && !favoriteConfig.trim().isEmpty() && configManager.P_4830_p().contains(favoriteConfig)) {
            configManager.J_1907_R(favoriteConfig);
            return;
        }
        if (((n_1700_B)this.P_1922_E).R_4764_Y() != null && !((n_1700_B)this.P_1922_E).R_4764_Y().trim().isEmpty()) {
            configManager.J_1907_R(((n_1700_B)this.P_1922_E).R_4764_Y());
        }
    }

    public String M_182_A() {
        return ((n_1700_B)this.P_1922_E).R_4764_Y();
    }

    public void G_564_y(String configName) {
        ((n_1700_B)this.P_1922_E).R_4764_Y(configName);
        this.G_564_y();
    }

    public String t_1786_h() {
        return ((n_1700_B)this.P_1922_E).J_1907_R();
    }

    public void P_1922_E(String language) {
        ((n_1700_B)this.P_1922_E).J_1907_R(language);
        this.G_564_y();
        c_2086_l.n_1700_B(language);
    }

    public String multiplayerClientSuggestionProvider() {
        return ((n_1700_B)this.P_1922_E).G_564_y();
    }

    public void u_1723_Y(String configName) {
        ((n_1700_B)this.P_1922_E).G_564_y(configName);
        this.G_564_y();
    }

    public void w_1457_N() {
        V_1176_p themeManager = ClientBootstrap.Y_601_j().G_564_y();
        if (themeManager != null && (((n_1700_B)this.P_1922_E).n_1700_B() == null || ((n_1700_B)this.P_1922_E).n_1700_B().trim().isEmpty())) {
            ((n_1700_B)this.P_1922_E).n_1700_B("\u0418\u043c\u044f: \u0422\u0435\u043c\u043d\u0430\u044f");
            this.G_564_y();
        }
    }

    private void n_1700_B(JsonObject object, String key, Consumer<JsonElement> consumer) {
        JsonElement element = object.get(key);
        if (element != null && !element.isJsonNull()) {
            consumer.accept(element);
        }
    }

    @Generated
    public static void v_4262_N(String currentActiveTheme) {
        u_1723_Y = currentActiveTheme;
    }

    @Generated
    public static String Y_601_j() {
        return u_1723_Y;
    }

    public static class n_1700_B {
        private String n_1700_B;
        private String J_1907_R;
        private String R_4764_Y;
        private String G_564_y;

        @Generated
        public String n_1700_B() {
            return this.n_1700_B;
        }

        @Generated
        public String J_1907_R() {
            return this.J_1907_R;
        }

        @Generated
        public String R_4764_Y() {
            return this.R_4764_Y;
        }

        @Generated
        public String G_564_y() {
            return this.G_564_y;
        }

        @Generated
        public void n_1700_B(String selectedTheme) {
            this.n_1700_B = selectedTheme;
        }

        @Generated
        public void J_1907_R(String selectedLanguage) {
            this.J_1907_R = selectedLanguage;
        }

        @Generated
        public void R_4764_Y(String selectedConfig) {
            this.R_4764_Y = selectedConfig;
        }

        @Generated
        public void G_564_y(String favoriteConfig) {
            this.G_564_y = favoriteConfig;
        }
    }
}



