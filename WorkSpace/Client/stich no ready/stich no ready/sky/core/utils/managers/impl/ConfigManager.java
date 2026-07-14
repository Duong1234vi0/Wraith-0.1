package sky.core.utils.managers.impl;

import com.adl.nativeprotect.Native;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
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
import sky.core.utils.managers.BaseManager;
import sky.core.utils.managers.FilePath;
import sky.core.utils.misc.HasherUtil;

public class ConfigManager extends BaseManager {
   private static final File CONFIG_DIR;

   public ConfigManager() {
      super("custom\\");
   }

   public void init() {
      CONFIG_DIR.mkdirs();
      super.init();
   }

   @Native
   protected void initializeData() {
      this.data = new ConfigData();
   }

   @Native
   protected JsonObject serializeData() {
      JsonObject config = new JsonObject();
      if (((ConfigData)this.data).getLastLoadedConfig() != null) {
         config.addProperty("lastLoadedConfig", ((ConfigData)this.data).getLastLoadedConfig());
      }

      return config;
   }

   protected void deserializeData(JsonObject jsonObject) {
      if (jsonObject.has("lastLoadedConfig") && !jsonObject.get("lastLoadedConfig").isJsonNull()) {
         ((ConfigData)this.data).setLastLoadedConfig(jsonObject.get("lastLoadedConfig").getAsString());
      }

   }

   public List getConfigNames() {
      File[] files = CONFIG_DIR.listFiles((dir, name) -> {
         return name.endsWith(".cfg");
      });
      if (files == null) {
         return Collections.emptyList();
      } else {
         List names = new ArrayList();
         File[] var3 = files;
         int var4 = files.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            File file = var3[var5];
            names.add(file.getName().replace(".cfg", ""));
         }

         return names;
      }
   }

   public void loadConfig(String name) {
      if (name != null && !name.trim().isEmpty()) {
         File file = new File(CONFIG_DIR, name.trim() + ".cfg");
         if (file.exists()) {
            try {
               String fileContent = Files.readString(file.toPath());
               String processedContent = this.encrypt ? HasherUtil.decrypt(fileContent) : fileContent;
               JsonObject config = (new JsonParser()).parse(processedContent).getAsJsonObject();
               if (config.has("module")) {
                  try {
                     this.loadModules(config.getAsJsonObject("module"));
                  } catch (Exception var8) {
                     System.err.println("Failed to load modules: " + var8.getMessage());
                  }
               }

               if (config.has("autoBuyItems")) {
                  try {
                     this.loadAutoBuyItems(config.getAsJsonObject("autoBuyItems"));
                  } catch (Exception var7) {
                     System.err.println("Failed to load autoBuyItems: " + var7.getMessage());
                  }
               }

               ((ConfigData)this.data).setLastLoadedConfig(name);
               this.save();
               ClientConfig clientConfig = SkyCore.getInstance().getClientConfig();
               if (clientConfig != null) {
                  clientConfig.onConfigSelected(name);
               }
            } catch (Exception var9) {
               System.err.println("Failed to load config file: " + name + " - " + var9.getMessage());
            }

         }
      }
   }

   public void saveConfig(String name) {
      if (name != null && !name.trim().isEmpty()) {
         CONFIG_DIR.mkdirs();
         File file = new File(CONFIG_DIR, name.trim() + ".cfg");

         try {
            JsonObject config = new JsonObject();
            config.add("module", this.saveModules());
            config.add("autoBuyItems", this.saveAutoBuyItems());
            config.addProperty("creationDate", System.currentTimeMillis());
            config.addProperty("creator", "NexusUser");
            String jsonContent = GSON.toJson(config);
            String outputContent = this.encrypt ? HasherUtil.encrypt(jsonContent) : jsonContent;
            Files.writeString(file.toPath(), outputContent);
         } catch (Exception var6) {
            System.err.println("Failed to save config: " + name + " - " + var6.getMessage());
         }

      }
   }

   @Native
   public boolean deleteConfig(String name) {
      if (name != null && !name.trim().isEmpty()) {
         boolean deleted = (new File(CONFIG_DIR, name.trim() + ".cfg")).delete();
         if (deleted && name.equals(((ConfigData)this.data).getLastLoadedConfig())) {
            ((ConfigData)this.data).setLastLoadedConfig((String)null);
            this.save();
         }

         return deleted;
      } else {
         return false;
      }
   }

   public Config getConfigInfo(String name) {
      if (name != null && !name.trim().isEmpty()) {
         File file = new File(CONFIG_DIR, name.trim() + ".cfg");
         if (!file.exists()) {
            return null;
         } else {
            try {
               String fileContent = Files.readString(file.toPath());
               String processedContent = this.encrypt ? HasherUtil.decrypt(fileContent) : fileContent;
               JsonObject config = (new JsonParser()).parse(processedContent).getAsJsonObject();
               long creationDate = config.has("creationDate") && !config.get("creationDate").isJsonNull() ? config.get("creationDate").getAsLong() : System.currentTimeMillis();
               String creator = config.has("creator") && !config.get("creator").isJsonNull() ? config.get("creator").getAsString() : "Unknown";
               return new Config(name, creationDate, creator);
            } catch (Exception var9) {
               return new Config(name);
            }
         }
      } else {
         return null;
      }
   }

   @Native
   private JsonObject saveAutoBuyItems() {
      JsonObject itemsObj = new JsonObject();
      int savedCount = 0;
      Iterator var3 = ItemList.getItems().iterator();

      while(true) {
         ItemSetting item;
         do {
            if (!var3.hasNext()) {
               return itemsObj;
            }

            item = (ItemSetting)var3.next();
            JsonObject itemData = new JsonObject();
            itemData.addProperty("maxPrice", item.getMaxPrice());
            itemData.addProperty("autoSetup", item.isSellEnabled());
            itemData.addProperty("enabled", (Boolean)item.get());
            itemsObj.add(item.getName(), itemData);
         } while(!(Boolean)item.get() && item.getMaxPrice() <= 0L && !item.isSellEnabled());

         ++savedCount;
      }
   }

   private void loadAutoBuyItems(JsonObject itemsObj) {
      Iterator var2 = itemsObj.entrySet().iterator();

      while(var2.hasNext()) {
         Map.Entry entry = (Map.Entry)var2.next();
         String itemName = (String)entry.getKey();
         ItemSetting item = ItemList.getByName(itemName);
         if (item != null && ((JsonElement)entry.getValue()).isJsonObject()) {
            JsonObject itemData = ((JsonElement)entry.getValue()).getAsJsonObject();
            boolean autoSetup;
            if (itemData.has("enabled")) {
               autoSetup = itemData.get("enabled").getAsBoolean();
               item.set(autoSetup);
            }

            if (itemData.has("maxPrice")) {
               long price = itemData.get("maxPrice").getAsLong();
               item.setMaxPrice(price);
            }

            if (itemData.has("autoSetup")) {
               autoSetup = itemData.get("autoSetup").getAsBoolean();
               item.setSellEnabled(autoSetup);
            }

            if (!(Boolean)item.get() && item.getMaxPrice() <= 0L && item.isSellEnabled()) {
            }
         }
      }

   }

   @Native
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

   @Native
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

   @Native
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

   @Native
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

   @Native
   private void loadProperty(JsonObject object, String key, Consumer consumer) {
      JsonElement element = object.get(key);
      if (element != null && !element.isJsonNull()) {
         consumer.accept(element);
      }

   }

   @Native
   public void resetToDefaults() {
      boolean prev = Module.isSuppressToggleEffects();
      Module.setSuppressToggleEffects(true);

      try {
         SkyCore.getInstance().getModuleManager().getModules().forEach((module) -> {
            module.settoggled(false);
            module.setBind(-100);
            module.setToggleMode(Module.ToggleMode.TOGGLE);
            module.setKeybindvisible(true);
            module.getSettings().forEach((setting) -> {
               try {
                  if (setting instanceof BooleanSetting sx) {
                     sx.set(sx.defaultVal);
                     sx.setBind(-100);
                     sx.setToggleMode(Module.ToggleMode.TOGGLE);
                     sx.setKeybindvisible(true);
                  } else if (setting instanceof SliderSetting sxxx) {
                     sxxx.set(sxxx.defaultVal);
                  } else if (setting instanceof ModeSetting sxxxx) {
                     sxxxx.set(sxxxx.defaultVal);
                  } else if (setting instanceof ColorSetting sxxxxx) {
                     sxxxxx.set(sxxxxx.defaultVal);
                  } else if (setting instanceof BindSetting sxxxxxx) {
                     sxxxxxx.set(-1);
                  } else if (setting instanceof StringSetting s) {
                     s.set("");
                  } else if (setting instanceof MultiBooleanSetting sxx) {
                     ((List)sxx.get()).forEach((opt) -> {
                        opt.set(opt.defaultVal);
                     });
                  }
               } catch (Exception var8) {
               }

            });
         });
      } finally {
         Module.setSuppressToggleEffects(prev);
      }

      ClientConfig clientConfig = SkyCore.getInstance().getClientConfig();
      if (clientConfig != null) {
         clientConfig.saveCurrentSettings();
      }

   }

   static {
      CONFIG_DIR = new File(FilePath.BASE_PATH + "custom\\");
   }

   public static class ConfigData {
      private String lastLoadedConfig;

      public String getLastLoadedConfig() {
         return this.lastLoadedConfig;
      }

      public void setLastLoadedConfig(String lastLoadedConfig) {
         this.lastLoadedConfig = lastLoadedConfig;
      }
   }

   public static class Config {
      private final String name;
      private final File file;
      private final long creationDate;
      private final String creator;

      public Config(String name) {
         this.name = name;
         this.file = new File(ConfigManager.CONFIG_DIR, name + ".cfg");
         this.creationDate = System.currentTimeMillis();
         this.creator = "NexusUser";
      }

      public Config(String name, long creationDate, String creator) {
         this.name = name;
         this.file = new File(ConfigManager.CONFIG_DIR, name + ".cfg");
         this.creationDate = creationDate;
         this.creator = creator != null ? creator : "Unknown";
      }

      public String getName() {
         return this.name;
      }

      public File getFile() {
         return this.file;
      }

      public long getCreationDate() {
         return this.creationDate;
      }

      public String getCreator() {
         return this.creator;
      }
   }
}
