package sky.core.utils.managers.impl;

import com.adl.nativeprotect.Native;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import sky.core.utils.managers.BaseManager;

public class MacroManager extends BaseManager {
   public MacroManager() {
      super("temp\\macros.file");
   }

   @Native
   protected void initializeData() {
      this.data = new ArrayList();
   }

   @Native
   protected JsonObject serializeData() {
      JsonObject config = new JsonObject();
      JsonArray arr = new JsonArray();
      Iterator var3 = ((List)this.data).iterator();

      while(var3.hasNext()) {
         MacroEntry entry = (MacroEntry)var3.next();
         JsonObject obj = new JsonObject();
         obj.addProperty("name", entry.getName());
         obj.addProperty("key", entry.getKeyCode());
         obj.addProperty("cmd", entry.getCommand());
         arr.add(obj);
      }

      config.add("macros", arr);
      return config;
   }

   @Native
   protected void deserializeData(JsonObject jsonObject) {
      ((List)this.data).clear();
      JsonArray arr = jsonObject.getAsJsonArray("macros");
      if (arr != null) {
         Iterator var3 = arr.iterator();

         while(var3.hasNext()) {
            JsonElement el = (JsonElement)var3.next();
            if (el.isJsonObject()) {
               JsonObject o = el.getAsJsonObject();
               this.loadProperty(o, "name", (n) -> {
                  this.loadProperty(o, "key", (k) -> {
                     this.loadProperty(o, "cmd", (c) -> {
                        try {
                           ((List)this.data).add(new MacroEntry(n.getAsString(), k.getAsInt(), c.getAsString()));
                        } catch (Exception var5) {
                        }

                     });
                  });
               });
            }
         }
      }

   }

   @Native
   protected void handleLoadError(Exception exception) {
      if (this.data == null) {
         this.initializeData();
      }

   }

   @Native
   public void addMacro(String name, int keyCode, String command) {
      if (name != null && command != null) {
         this.removeMacro(name);
         ((List)this.data).add(new MacroEntry(name.trim(), keyCode, command));
         this.save();
      }
   }

   @Native
   public void removeMacro(String name) {
      if (name != null) {
         boolean removed = ((List)this.data).removeIf((m) -> {
            return m.getName().equalsIgnoreCase(name.trim());
         });
         if (removed) {
            this.save();
         }

      }
   }

   @Native
   public void clear() {
      if (!((List)this.data).isEmpty()) {
         ((List)this.data).clear();
         this.save();
      }

   }

   @Native
   public List getMacros() {
      return new ArrayList((Collection)this.data);
   }

   @Native
   private void loadProperty(JsonObject object, String key, Consumer consumer) {
      JsonElement element = object.get(key);
      if (element != null && !element.isJsonNull()) {
         consumer.accept(element);
      }

   }

   public static class MacroEntry {
      private final String name;
      private final int keyCode;
      private final String command;

      public MacroEntry(String name, int keyCode, String command) {
         this.name = name;
         this.keyCode = keyCode;
         this.command = command;
      }

      public String getName() {
         return this.name;
      }

      public int getKeyCode() {
         return this.keyCode;
      }

      public String getCommand() {
         return this.command;
      }
   }
}
