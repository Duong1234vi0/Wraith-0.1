package sky.core.utils.managers.impl.dragmanager;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.utils.managers.BaseManager;
import sky.core.utils.misc.HasherUtil;

public class DraggingManager extends BaseManager {
   public DraggingManager() {
      super("temp\\drags.file");
   }

   protected void initializeData() {
      this.data = new DragData();
   }

   protected JsonObject serializeData() {
      JsonObject config = new JsonObject();
      JsonObject positions = new JsonObject();
      ((DragData)this.data).getDraggables().forEach((name, drag) -> {
         JsonObject pos = new JsonObject();
         pos.addProperty("x", drag.getTargetX());
         pos.addProperty("y", drag.getTargetY());
         positions.add(name, pos);
      });
      JsonArray order = new JsonArray();
      ((DragData)this.data).getRenderOrder().forEach((drag) -> {
         order.add(drag.getName());
      });
      JsonObject settings = new JsonObject();
      ((DragData)this.data).getDraggables().forEach((name, drag) -> {
         JsonObject dragSettings = new JsonObject();
         drag.getSettings().forEach((setting) -> {
            this.saveSetting(dragSettings, setting);
         });
         if (dragSettings.size() > 0) {
            settings.add(name, dragSettings);
         }

      });
      config.add("positions", positions);
      config.add("renderOrder", order);
      config.add("settings", settings);
      return config;
   }

   protected void deserializeData(JsonObject jsonObject) {
      this.loadPositions(jsonObject);
      this.loadRenderOrder(jsonObject);
      this.loadSettings(jsonObject);
   }

   private void loadPositions(JsonObject jsonObject) {
      this.loadProperty(jsonObject, "positions", (positionsElement) -> {
         ((DragData)this.data).getSavedX().clear();
         ((DragData)this.data).getSavedY().clear();
         JsonObject positions = positionsElement.getAsJsonObject();
         positions.entrySet().forEach((entry) -> {
            String name = (String)entry.getKey();
            JsonObject pos = ((JsonElement)entry.getValue()).getAsJsonObject();

            try {
               float x = pos.get("x").getAsFloat();
               float y = pos.get("y").getAsFloat();
               ((DragData)this.data).getSavedX().put(name, x);
               ((DragData)this.data).getSavedY().put(name, y);
               Dragging drag = (Dragging)((DragData)this.data).getDraggables().get(name);
               if (drag != null) {
                  drag.setX(x);
                  drag.setY(y);
                  drag.syncSmoothPosition();
               }
            } catch (Exception var7) {
               System.err.println("Failed to parse position for " + name + ": " + var7.getMessage());
            }

         });
      });
   }

   private void loadRenderOrder(JsonObject jsonObject) {
      this.loadProperty(jsonObject, "renderOrder", (orderElement) -> {
         ((DragData)this.data).getRenderOrder().clear();
         JsonArray renderOrder = orderElement.getAsJsonArray();
         renderOrder.forEach((element) -> {
            try {
               Dragging drag = (Dragging)((DragData)this.data).getDraggables().get(element.getAsString());
               if (drag != null) {
                  ((DragData)this.data).getRenderOrder().add(drag);
               }
            } catch (Exception var3) {
               System.err.println("Failed to parse render order element: " + var3.getMessage());
            }

         });
         ((DragData)this.data).getDraggables().values().forEach((drag) -> {
            if (!((DragData)this.data).getRenderOrder().contains(drag)) {
               ((DragData)this.data).getRenderOrder().add(drag);
            }

         });
      });
   }

   private void loadSettings(JsonObject jsonObject) {
      this.loadProperty(jsonObject, "settings", (settingsElement) -> {
         JsonObject settings = settingsElement.getAsJsonObject();
         settings.entrySet().forEach((entry) -> {
            Dragging drag = (Dragging)((DragData)this.data).getDraggables().get(entry.getKey());
            if (drag != null) {
               try {
                  JsonObject dragSettings = ((JsonElement)entry.getValue()).getAsJsonObject();
                  drag.getSettings().forEach((setting) -> {
                     this.loadSetting(dragSettings, setting);
                  });
               } catch (Exception var4) {
                  PrintStream var10000 = System.err;
                  String var10001 = (String)entry.getKey();
                  var10000.println("Failed to load settings for " + var10001 + ": " + var4.getMessage());
               }
            }

         });
      });
   }

   public void addDraggable(Dragging draggable) {
      String name = draggable.getName();
      if (((DragData)this.data).getSavedX().containsKey(name) && ((DragData)this.data).getSavedY().containsKey(name)) {
         draggable.setX((Float)((DragData)this.data).getSavedX().remove(name));
         draggable.setY((Float)((DragData)this.data).getSavedY().remove(name));
         draggable.syncSmoothPosition();
      }

      ((DragData)this.data).getDraggables().put(name, draggable);
      if (!((DragData)this.data).getRenderOrder().contains(draggable)) {
         ((DragData)this.data).getRenderOrder().add(draggable);
      }

   }

   public void bringToFront(Dragging draggable) {
      ((DragData)this.data).getRenderOrder().remove(draggable);
      ((DragData)this.data).getRenderOrder().add(draggable);
   }

   public void resetAllToInitialPositions() {
      ((DragData)this.data).getDraggables().values().forEach((drag) -> {
         drag.setX(drag.getInitialX());
         drag.setY(drag.getInitialY());
         drag.syncSmoothPosition();
      });
      this.save();
   }

   public LinkedHashMap getDraggables() {
      return ((DragData)this.data).getDraggables();
   }

   public List getRenderOrder() {
      return ((DragData)this.data).getRenderOrder();
   }

   public void loadSettingsFor(Dragging draggable) {
      try {
         JsonObject config = this.loadJsonConfig();
         if (config != null && config.has("settings")) {
            JsonObject settings = config.getAsJsonObject("settings");
            JsonObject dragSettings = settings.getAsJsonObject(draggable.getName());
            if (dragSettings != null) {
               draggable.getSettings().forEach((setting) -> {
                  this.loadSetting(dragSettings, setting);
               });
            }
         }
      } catch (Exception var5) {
         PrintStream var10000 = System.err;
         String var10001 = draggable.getName();
         var10000.println("Failed to load settings for draggable " + var10001 + ": " + var5.getMessage());
      }

   }

   private void saveSetting(JsonObject dragSettings, Setting setting) {
      try {
         if (setting instanceof BooleanSetting) {
            dragSettings.addProperty(setting.getName(), (Boolean)((BooleanSetting)setting).get());
         } else if (setting instanceof ModeSetting) {
            dragSettings.addProperty(setting.getName(), (String)((ModeSetting)setting).get());
         }
      } catch (Exception var4) {
         PrintStream var10000 = System.err;
         String var10001 = setting.getName();
         var10000.println("Failed to save setting " + var10001 + ": " + var4.getMessage());
      }

   }

   private void loadSetting(JsonObject dragSettings, Setting setting) {
      this.loadProperty(dragSettings, setting.getName(), (element) -> {
         try {
            if (setting instanceof BooleanSetting) {
               ((BooleanSetting)setting).set(element.getAsBoolean());
            } else if (setting instanceof ModeSetting) {
               ((ModeSetting)setting).set(element.getAsString());
            }
         } catch (Exception var3) {
            PrintStream var10000 = System.err;
            String var10001 = setting.getName();
            var10000.println("Failed to load setting " + var10001 + ": " + var3.getMessage());
         }

      });
   }

   private JsonObject loadJsonConfig() {
      try {
         if (!this.configFile.exists()) {
            return null;
         } else {
            String content = Files.readString(this.configFile.toPath());
            return (new JsonParser()).parse(this.encrypt ? HasherUtil.decrypt(content) : content).getAsJsonObject();
         }
      } catch (Exception var2) {
         System.err.println("Failed to load dragging config: " + var2.getMessage());
         return null;
      }
   }

   private void loadProperty(JsonObject object, String key, Consumer consumer) {
      JsonElement element = object.get(key);
      if (element != null && !element.isJsonNull()) {
         consumer.accept(element);
      }

   }

   public static class DragData {
      private final Map savedX = new LinkedHashMap();
      private final Map savedY = new LinkedHashMap();
      private final LinkedHashMap draggables = new LinkedHashMap();
      private final List renderOrder = new ArrayList();

      public Map getSavedX() {
         return this.savedX;
      }

      public Map getSavedY() {
         return this.savedY;
      }

      public LinkedHashMap getDraggables() {
         return this.draggables;
      }

      public List getRenderOrder() {
         return this.renderOrder;
      }
   }
}
