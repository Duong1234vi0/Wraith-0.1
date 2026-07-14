package sky.core.utils.managers.impl;

import com.adl.nativeprotect.Native;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.utils.managers.BaseManager;
import sky.core.utils.managers.FilePath;
import sky.core.utils.misc.HasherUtil;

public class ThemeManager extends BaseManager {
   private static final File THEME_DIR;

   public ThemeManager() {
      super("themes\\");
   }

   public void init() {
      THEME_DIR.mkdirs();
      super.init();
   }

   @Native
   protected void initializeData() {
   }

   @Native
   protected JsonObject serializeData() {
      return null;
   }

   @Native
   protected void deserializeData(JsonObject jsonObject) {
   }

   @Native
   public List getThemeNames() {
      File[] files = THEME_DIR.listFiles((dir, name) -> {
         return name.endsWith(".file");
      });
      if (files == null) {
         return Collections.emptyList();
      } else {
         List names = new ArrayList();
         File[] var3 = files;
         int var4 = files.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            File file = var3[var5];
            names.add(file.getName().replace(".file", ""));
         }

         return names;
      }
   }

   @Native
   public Theme loadTheme(String name) {
      if (name != null && !name.trim().isEmpty()) {
         File file = new File(THEME_DIR, name.trim() + ".file");
         if (!file.exists()) {
            return null;
         } else {
            Theme theme = new Theme(name);

            try {
               String fileContent = Files.readString(file.toPath());
               String processedContent = this.encrypt ? HasherUtil.decrypt(fileContent) : fileContent;
               JsonObject config = (new JsonParser()).parse(processedContent).getAsJsonObject();
               this.loadProperty(config, "presetName", (v) -> {
                  theme.presetName = v.getAsString();
               });
               this.loadProperty(config, "creator", (v) -> {
                  theme.creator = v.getAsString();
               });
               JsonArray colorsArray = config.getAsJsonArray("presetColors");
               if (colorsArray != null) {
                  theme.presetColors = new int[colorsArray.size()];

                  for(int i = 0; i < colorsArray.size(); ++i) {
                     theme.presetColors[i] = colorsArray.get(i).getAsInt();
                  }
               }
            } catch (Exception var9) {
               System.err.println("Failed to load theme: " + name + " - " + var9.getMessage());
            }

            return theme;
         }
      } else {
         System.err.println("Cannot load theme: name is null or empty");
         return null;
      }
   }

   @Native
   public void saveTheme(String name, ThemeEditor.ThemePreset preset, String creator) {
      if (name != null && !name.trim().isEmpty()) {
         if (preset == null) {
            System.err.println("Cannot save theme: preset is null");
         } else {
            try {
               THEME_DIR.mkdirs();
               File var10002 = THEME_DIR;
               String var10003 = name.trim();
               File file = new File(var10002, var10003 + ".file");
               JsonObject config = new JsonObject();
               config.addProperty("presetName", preset.getName());
               config.addProperty("creator", creator != null ? creator : "Unknown");
               JsonArray colorsArray = new JsonArray();
               int[] var7 = preset.getColors();
               int var8 = var7.length;

               for(int var9 = 0; var9 < var8; ++var9) {
                  int color = var7[var9];
                  colorsArray.add(color);
               }

               config.add("presetColors", colorsArray);
               String jsonContent = GSON.toJson(config);
               String outputContent = this.encrypt ? HasherUtil.encrypt(jsonContent) : jsonContent;
               Files.writeString(file.toPath(), outputContent);
            } catch (Exception var11) {
               System.err.println("Failed to save theme: " + name + " - " + var11.getMessage());
            }

         }
      } else {
         System.err.println("Cannot save theme: name is null or empty");
      }
   }

   @Native
   public boolean deleteTheme(String name) {
      if (name != null && !name.trim().isEmpty()) {
         File file = new File(THEME_DIR, name.trim() + ".file");
         return file.exists() && file.delete();
      } else {
         System.err.println("Cannot delete theme: name is null or empty");
         return false;
      }
   }

   @Native
   private void loadProperty(JsonObject object, String key, Consumer consumer) {
      JsonElement element = object.get(key);
      if (element != null && !element.isJsonNull()) {
         consumer.accept(element);
      }

   }

   static {
      THEME_DIR = new File(FilePath.BASE_PATH + "themes\\");
   }

   public static class Theme {
      private final String name;
      private final File file;
      private String presetName;
      private int[] presetColors;
      private String creator;

      public Theme(String name) {
         this.name = name;
         this.file = new File(ThemeManager.THEME_DIR, name + ".file");
      }

      public String getName() {
         return this.name;
      }

      public File getFile() {
         return this.file;
      }

      public String getPresetName() {
         return this.presetName;
      }

      public int[] getPresetColors() {
         return this.presetColors;
      }

      public String getCreator() {
         return this.creator;
      }
   }
}
