package sky.core.utils.managers.impl.staffmanager;

import com.adl.nativeprotect.Native;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import sky.core.utils.managers.BaseManager;

public class StaffManager extends BaseManager {
   public StaffManager() {
      super("temp\\staff.file");
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
         StaffEntry entry = (StaffEntry)var3.next();
         arr.add(new JsonPrimitive(entry.getName()));
      }

      config.add("staff", arr);
      return config;
   }

   @Native
   protected void deserializeData(JsonObject jsonObject) {
      ((List)this.data).clear();
      JsonArray arr = jsonObject.getAsJsonArray("staff");
      if (arr != null) {
         Iterator var3 = arr.iterator();

         while(var3.hasNext()) {
            JsonElement element = (JsonElement)var3.next();
            if (element.isJsonPrimitive()) {
               ((List)this.data).add(new StaffEntry(element.getAsString()));
            } else if (element.isJsonObject()) {
               JsonObject obj = element.getAsJsonObject();
               this.loadProperty(obj, "name", (nameEl) -> {
                  ((List)this.data).add(new StaffEntry(nameEl.getAsString()));
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
   public void addStaff(String name) {
      if (name != null && !name.trim().isEmpty()) {
         String n = name.trim();
         if (!this.isStaff(n)) {
            ((List)this.data).add(new StaffEntry(n));
            this.save();
         }

      }
   }

   @Native
   public void removeStaff(String name) {
      if (name != null) {
         boolean removed = ((List)this.data).removeIf((e) -> {
            return e.getName().equalsIgnoreCase(name.trim());
         });
         if (removed) {
            this.save();
         }

      }
   }

   @Native
   public void clearStaff() {
      if (!((List)this.data).isEmpty()) {
         ((List)this.data).clear();
         this.save();
      }

   }

   public boolean isStaff(String name) {
      return name == null ? false : ((List)this.data).stream().anyMatch((e) -> {
         return e.getName().equalsIgnoreCase(name.trim());
      });
   }

   public List getStaff() {
      return new ArrayList((Collection)this.data);
   }

   @Native
   private void loadProperty(JsonObject object, String key, Consumer consumer) {
      JsonElement element = object.get(key);
      if (element != null && !element.isJsonNull()) {
         consumer.accept(element);
      }

   }

   public static class StaffEntry {
      private final String name;

      public StaffEntry(String name) {
         this.name = name;
      }

      public String getName() {
         return this.name;
      }
   }
}
