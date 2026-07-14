package sky.core.utils.managers.impl;

import com.adl.nativeprotect.Native;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import sky.core.SkyCore;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BindSetting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ColorSetting;
import sky.core.modules.api.constructors.impl.ItemSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.modules.api.constructors.impl.StringSetting;
import sky.core.ui.gui.autobuy.ItemList;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.managers.BaseManager;
import sky.core.utils.math.NumberUtil;
import sky.core.utils.misc.HasherUtil;

public class ClientConfig extends BaseManager {
   private static String currentActiveTheme = null;

   public ClientConfig() {
      super("temp\\client_config.file");
   }

   @Native
   protected void initializeData() {
      this.data = new ClientConfigData();
   }

   protected JsonObject serializeData() {
      JsonObject config = new JsonObject();
      config.add("modules", this.saveModules());
      config.add("autoBuyItems", this.saveAutoBuyItems());
      if (((ClientConfigData)this.data).getSelectedTheme() != null) {
         config.addProperty("selectedTheme", ((ClientConfigData)this.data).getSelectedTheme());
      }

      if (((ClientConfigData)this.data).getSelectedConfig() != null) {
         config.addProperty("selectedConfig", ((ClientConfigData)this.data).getSelectedConfig());
      }

      return config;
   }

   protected void deserializeData(JsonObject jsonObject) {
      this.loadProperty(jsonObject, "selectedTheme", (v) -> {
         ((ClientConfigData)this.data).setSelectedTheme(v.getAsString());
      });
      this.loadProperty(jsonObject, "selectedConfig", (v) -> {
         ((ClientConfigData)this.data).setSelectedConfig(v.getAsString());
      });
   }

   public void saveCurrentSettings() {
      try {
         this.save();
      } catch (Exception var2) {
         System.err.println("Error saving client settings: " + var2.getMessage());
      }

   }

   public void applySettings() {
      if (this.configFile.exists()) {
         try {
            String fileContent = Files.readString(this.configFile.toPath());
            String processedContent = this.encrypt ? HasherUtil.decrypt(fileContent) : fileContent;
            JsonObject config = (new JsonParser()).parse(processedContent).getAsJsonObject();
            if (config.has("modules")) {
               this.loadModules(config.getAsJsonObject("modules"));
            }

            if (config.has("autoBuyItems")) {
               this.loadAutoBuyItems(config.getAsJsonObject("autoBuyItems"));
            }

            this.applyThemeSettings();
            this.loadSelectedConfig();
         } catch (Exception var4) {
            System.err.println("Failed to apply client config settings: " + var4.getMessage());
         }
      }

   }

   private JsonObject saveAutoBuyItems() {
      JsonObject itemsObj = new JsonObject();
      Iterator var2 = ItemList.getItems().iterator();

      while(var2.hasNext()) {
         ItemSetting item = (ItemSetting)var2.next();
         JsonObject itemData = new JsonObject();
         itemData.addProperty("maxPrice", item.getMaxPrice());
         itemData.addProperty("autoSetup", item.isSellEnabled());
         itemData.addProperty("enabled", (Boolean)item.get());
         itemsObj.add(item.getName(), itemData);
      }

      return itemsObj;
   }

   private void loadAutoBuyItems(JsonObject itemsObj) {
      Iterator var2 = itemsObj.entrySet().iterator();

      while(var2.hasNext()) {
         Map.Entry entry = (Map.Entry)var2.next();
         String itemName = (String)entry.getKey();
         ItemSetting item = ItemList.getByName(itemName);
         if (item != null && ((JsonElement)entry.getValue()).isJsonObject()) {
            JsonObject itemData = ((JsonElement)entry.getValue()).getAsJsonObject();
            if (itemData.has("maxPrice")) {
               long price = itemData.get("maxPrice").getAsLong();
               item.setMaxPrice(price);
               item.setMaxPriceFromString(NumberUtil.formatThousands(String.valueOf(price)));
            }

            if (itemData.has("autoSetup")) {
               item.setSellEnabled(itemData.get("autoSetup").getAsBoolean());
            }

            if (itemData.has("enabled")) {
               item.set(itemData.get("enabled").getAsBoolean());
            }
         }
      }

   }

   private void loadModules(JsonObject modules) {
      boolean prev = Module.isSuppressToggleEffects();
      Module.setSuppressToggleEffects(true);

      try {
         SkyCore.getInstance().getModuleManager().getModules().forEach((module) -> {
            JsonObject moduleData = modules.getAsJsonObject(module.getName().toLowerCase());
            if (moduleData != null) {
               module.settoggled(false);
               this.loadProperty(moduleData, "key", (v) -> {
                  module.setBind(v.getAsInt());
               });
               this.loadProperty(moduleData, "enabled", (v) -> {
                  module.settoggled(v.getAsBoolean());
               });
               this.loadProperty(moduleData, "togglemode", (v) -> {
                  try {
                     module.setToggleMode(Module.ToggleMode.valueOf(v.getAsString()));
                  } catch (Exception var3) {
                     module.setToggleMode(Module.ToggleMode.TOGGLE);
                  }

               });
               this.loadProperty(moduleData, "keybindvisible", (v) -> {
                  module.setKeybindvisible(v.getAsBoolean());
               });
               module.getSettings().forEach((setting) -> {
                  this.loadSetting(moduleData, setting);
               });
            }
         });
      } finally {
         Module.setSuppressToggleEffects(prev);
      }

   }

   private void loadSetting(JsonObject moduleData, Setting setting) {
      JsonElement element = moduleData.get(setting.getName());
      if (element != null && !element.isJsonNull()) {
         try {
            if (setting instanceof SliderSetting) {
               ((SliderSetting)setting).set(element.getAsFloat());
            } else {
               JsonObject multiData;
               if (setting instanceof BooleanSetting) {
                  if (element.isJsonObject()) {
                     multiData = element.getAsJsonObject();
                     BooleanSetting boolSetting = (BooleanSetting)setting;
                     this.loadProperty(multiData, "value", (v) -> {
                        boolSetting.set(v.getAsBoolean());
                     });
                     this.loadProperty(multiData, "bind", (v) -> {
                        boolSetting.setBind(v.getAsInt());
                     });
                     this.loadProperty(multiData, "keybindvisible", (v) -> {
                        boolSetting.setKeybindvisible(v.getAsBoolean());
                     });
                     this.loadProperty(multiData, "togglemode", (v) -> {
                        try {
                           boolSetting.setToggleMode(Module.ToggleMode.valueOf(v.getAsString()));
                        } catch (Exception var3) {
                           boolSetting.setToggleMode(Module.ToggleMode.TOGGLE);
                        }

                     });
                  }
               } else if (setting instanceof ColorSetting) {
                  ((ColorSetting)setting).set(element.getAsInt());
               } else if (setting instanceof ModeSetting) {
                  ((ModeSetting)setting).set(element.getAsString());
               } else if (setting instanceof BindSetting) {
                  ((BindSetting)setting).set(element.getAsInt());
               } else if (setting instanceof StringSetting) {
                  ((StringSetting)setting).set(element.getAsString());
               } else if (setting instanceof MultiBooleanSetting && element.isJsonObject()) {
                  multiData = element.getAsJsonObject();
                  MultiBooleanSetting multiSetting = (MultiBooleanSetting)setting;
                  ((List)multiSetting.get()).forEach((option) -> {
                     JsonElement optionElement = multiData.get(option.getName());
                     if (optionElement != null && !optionElement.isJsonNull()) {
                        option.set(optionElement.getAsBoolean());
                     }

                  });
               }
            }
         } catch (Exception var6) {
            PrintStream var10000 = System.err;
            String var10001 = setting.getName();
            var10000.println("Failed to load setting " + var10001 + ": " + var6.getMessage());
         }

      }
   }

   private JsonObject saveModules() {
      JsonObject modules = new JsonObject();
      SkyCore.getInstance().getModuleManager().getModules().forEach((module) -> {
         JsonObject moduleData = new JsonObject();
         moduleData.addProperty("key", module.getBind());
         moduleData.addProperty("enabled", module.isEnabled());
         moduleData.addProperty("togglemode", module.getToggleMode().name());
         moduleData.addProperty("keybindvisible", module.isKeybindvisible());
         module.getSettings().forEach((setting) -> {
            this.saveSetting(moduleData, setting);
         });
         modules.add(module.getName().toLowerCase(), moduleData);
      });
      return modules;
   }

   private void saveSetting(JsonObject moduleData, Setting setting) {
      try {
         JsonObject multiData;
         if (setting instanceof BooleanSetting boolSetting) {
            multiData = new JsonObject();
            multiData.addProperty("value", (Boolean)boolSetting.get());
            multiData.addProperty("bind", boolSetting.getBind());
            multiData.addProperty("keybindvisible", boolSetting.isKeybindvisible());
            multiData.addProperty("togglemode", boolSetting.getToggleMode().name());
            moduleData.add(setting.getName(), multiData);
         } else if (setting instanceof SliderSetting) {
            moduleData.addProperty(setting.getName(), (Number)((SliderSetting)setting).get());
         } else if (setting instanceof ModeSetting) {
            moduleData.addProperty(setting.getName(), (String)((ModeSetting)setting).get());
         } else if (setting instanceof ColorSetting) {
            moduleData.addProperty(setting.getName(), (Number)((ColorSetting)setting).get());
         } else if (setting instanceof BindSetting) {
            moduleData.addProperty(setting.getName(), (Number)((BindSetting)setting).get());
         } else if (setting instanceof StringSetting) {
            moduleData.addProperty(setting.getName(), (String)((StringSetting)setting).get());
         } else if (setting instanceof MultiBooleanSetting) {
            MultiBooleanSetting multiSetting = (MultiBooleanSetting)setting;
            multiData = new JsonObject();
            ((List)multiSetting.get()).forEach((option) -> {
               multiData.addProperty(option.getName(), (Boolean)option.get());
            });
            moduleData.add(setting.getName(), multiData);
         }
      } catch (Exception var5) {
         PrintStream var10000 = System.err;
         String var10001 = setting.getName();
         var10000.println("Failed to save setting " + var10001 + ": " + var5.getMessage());
      }

   }

   public void applyThemeSettings() {
      if (((ClientConfigData)this.data).getSelectedTheme() != null && !((ClientConfigData)this.data).getSelectedTheme().trim().isEmpty()) {
         ThemeManager themeManager = SkyCore.getInstance().getThemeManager();
         if (themeManager != null) {
            ThemeManager.Theme theme = themeManager.loadTheme(((ClientConfigData)this.data).getSelectedTheme());
            String themePresetName;
            if (theme == null) {
               themePresetName = ((ClientConfigData)this.data).getSelectedTheme();
               if (themePresetName.startsWith("Название: ")) {
                  themePresetName = themePresetName.substring(10);
               }

               theme = themeManager.loadTheme(themePresetName);
            }

            if (theme != null && theme.getPresetColors() != null) {
               themePresetName = theme.getPresetName() != null ? theme.getPresetName() : ((ClientConfigData)this.data).getSelectedTheme();
               this.applyThemeColorsAndUpdatePreset(themePresetName, theme.getPresetColors());
               setCurrentActiveTheme(themePresetName);
            }

         }
      }
   }

   private void applyThemeColors(int[] colors) {
      try {
         ThemeSettings[] settings = ThemeSettings.values();

         for(int i = 0; i < Math.min(colors.length, settings.length); ++i) {
            if (ThemeEditor.colorSettings.containsKey(settings[i])) {
               ColorSetting colorSetting = (ColorSetting)ThemeEditor.colorSettings.get(settings[i]);
               Runnable originalCallback = colorSetting.getOnChange();
               colorSetting.setOnChange((Runnable)null);
               colorSetting.set(colors[i]);
               colorSetting.setOnChange(originalCallback);
            }
         }
      } catch (Exception var6) {
         System.err.println("Error applying theme colors: " + var6.getMessage());
      }

   }

   private void applyThemeColorsAndUpdatePreset(String themeName, int[] colors) {
      try {
         this.applyThemeColors(colors);
         this.updateThemeEditorPreset(themeName, colors);
      } catch (Exception var4) {
         System.err.println("Error applying theme with preset: " + var4.getMessage());
      }

   }

   private void updateThemeEditorPreset(String themeName, int[] colors) {
      try {
         Field currentPresetField = ThemeEditor.class.getDeclaredField("currentPreset");
         currentPresetField.setAccessible(true);
         if (Modifier.isStatic(currentPresetField.getModifiers())) {
         }
      } catch (Exception var4) {
      }

   }

   public void onThemeApplied(String themeName) {
      try {
         ((ClientConfigData)this.data).setSelectedTheme(themeName);
         setCurrentActiveTheme(themeName);
         this.save();
      } catch (Exception var3) {
         System.err.println("Error saving applied theme: " + var3.getMessage());
      }

   }

   public void onThemeCreated(String themeName) {
      try {
         ((ClientConfigData)this.data).setSelectedTheme(themeName);
         setCurrentActiveTheme(themeName);
         this.save();
      } catch (Exception var3) {
         System.err.println("Error saving created theme: " + var3.getMessage());
      }

   }

   public void onConfigSelected(String configName) {
      try {
         ((ClientConfigData)this.data).setSelectedConfig(configName);
         this.save();
      } catch (Exception var3) {
         System.err.println("Error saving selected config: " + var3.getMessage());
      }

   }

   public void loadSelectedConfig() {
      if (((ClientConfigData)this.data).getSelectedConfig() != null && !((ClientConfigData)this.data).getSelectedConfig().trim().isEmpty()) {
         ConfigManager configManager = SkyCore.getInstance().getConfigManager();
         if (configManager != null) {
            configManager.loadConfig("default");
         }
      }

   }

   public String getSelectedConfig() {
      return ((ClientConfigData)this.data).getSelectedConfig();
   }

   public void setSelectedConfig(String configName) {
      ((ClientConfigData)this.data).setSelectedConfig(configName);
      this.save();
   }

   public void initializeDefaultTheme() {
      ThemeManager themeManager = SkyCore.getInstance().getThemeManager();
      if (themeManager != null && (((ClientConfigData)this.data).getSelectedTheme() == null || ((ClientConfigData)this.data).getSelectedTheme().trim().isEmpty())) {
         List availableThemes = themeManager.getThemeNames();
         if (!availableThemes.isEmpty()) {
            String defaultTheme = (String)availableThemes.get(0);
            ((ClientConfigData)this.data).setSelectedTheme(defaultTheme);
            this.save();
         }
      }

   }

   private void loadProperty(JsonObject object, String key, Consumer consumer) {
      JsonElement element = object.get(key);
      if (element != null && !element.isJsonNull()) {
         consumer.accept(element);
      }

   }

   public static void setCurrentActiveTheme(String currentActiveTheme) {
      ClientConfig.currentActiveTheme = currentActiveTheme;
   }

   public static String getCurrentActiveTheme() {
      return currentActiveTheme;
   }

   public static class ClientConfigData {
      private String selectedTheme;
      private String selectedConfig;

      public String getSelectedTheme() {
         return this.selectedTheme;
      }

      public String getSelectedConfig() {
         return this.selectedConfig;
      }

      public void setSelectedTheme(String selectedTheme) {
         this.selectedTheme = selectedTheme;
      }

      public void setSelectedConfig(String selectedConfig) {
         this.selectedConfig = selectedConfig;
      }
   }
}
