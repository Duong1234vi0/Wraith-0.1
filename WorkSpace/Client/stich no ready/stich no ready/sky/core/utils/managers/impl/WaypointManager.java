package sky.core.utils.managers.impl;

import com.adl.nativeprotect.Native;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import sky.core.utils.managers.BaseManager;

public class WaypointManager extends BaseManager {
   public WaypointManager() {
      super("temp\\waypoints.file");
   }

   @Native
   protected void initializeData() {
      this.data = new HashMap();
   }

   @Native
   protected JsonObject serializeData() {
      JsonObject root = new JsonObject();
      Iterator var2 = ((Map)this.data).entrySet().iterator();

      while(var2.hasNext()) {
         Map.Entry byServer = (Map.Entry)var2.next();
         String server = (String)byServer.getKey();
         JsonArray arr = new JsonArray();
         Iterator var6 = ((List)byServer.getValue()).iterator();

         while(var6.hasNext()) {
            WaypointEntry wp = (WaypointEntry)var6.next();
            JsonObject o = new JsonObject();
            o.addProperty("name", wp.getName());
            o.addProperty("x", wp.getX());
            o.addProperty("y", wp.getY());
            o.addProperty("z", wp.getZ());
            arr.add(o);
         }

         root.add(server, arr);
      }

      return root;
   }

   @Native
   protected void deserializeData(JsonObject jsonObject) {
      ((Map)this.data).clear();
      Iterator var2 = jsonObject.entrySet().iterator();

      while(true) {
         String server;
         JsonElement element;
         do {
            if (!var2.hasNext()) {
               return;
            }

            Map.Entry entry = (Map.Entry)var2.next();
            server = (String)entry.getKey();
            element = (JsonElement)entry.getValue();
         } while(!element.isJsonArray());

         JsonArray arr = element.getAsJsonArray();
         List list = new ArrayList();
         Iterator var8 = arr.iterator();

         while(var8.hasNext()) {
            JsonElement el = (JsonElement)var8.next();
            if (el.isJsonObject()) {
               JsonObject o = el.getAsJsonObject();
               this.loadProperty(o, "name", (n) -> {
                  this.loadProperty(o, "x", (x) -> {
                     this.loadProperty(o, "y", (y) -> {
                        this.loadProperty(o, "z", (z) -> {
                           try {
                              list.add(new WaypointEntry(n.getAsString(), x.getAsDouble(), y.getAsDouble(), z.getAsDouble()));
                           } catch (Exception var6) {
                           }

                        });
                     });
                  });
               });
            }
         }

         ((Map)this.data).put(server, list);
      }
   }

   @Native
   protected void handleLoadError(Exception exception) {
      if (this.data == null) {
         this.initializeData();
      }

   }

   @Native
   public List getWaypoints(String serverKey) {
      return new ArrayList((Collection)((Map)this.data).getOrDefault(serverKey, Collections.emptyList()));
   }

   @Native
   public void addWaypoint(String serverKey, String name, double x, double y, double z) {
      if (serverKey != null && name != null) {
         String normalized = name.trim();
         if (!normalized.isEmpty()) {
            List list = (List)((Map)this.data).computeIfAbsent(serverKey, (k) -> {
               return new ArrayList();
            });
            list.removeIf((w) -> {
               return w.getName().equalsIgnoreCase(normalized);
            });
            list.add(new WaypointEntry(normalized, x, y, z));
            this.save();
         }
      }
   }

   @Native
   public boolean removeWaypoint(String serverKey, String name) {
      if (serverKey != null && name != null) {
         List list = (List)((Map)this.data).get(serverKey);
         if (list == null) {
            return false;
         } else {
            boolean removed = list.removeIf((w) -> {
               return w.getName().equalsIgnoreCase(name.trim());
            });
            if (removed) {
               this.save();
            }

            return removed;
         }
      } else {
         return false;
      }
   }

   @Native
   public void clear(String serverKey) {
      if (serverKey != null) {
         List list = (List)((Map)this.data).get(serverKey);
         if (list != null && !list.isEmpty()) {
            list.clear();
            this.save();
         }

      }
   }

   @Native
   private void loadProperty(JsonObject object, String key, Consumer consumer) {
      JsonElement element = object.get(key);
      if (element != null && !element.isJsonNull()) {
         consumer.accept(element);
      }

   }

   public static class WaypointEntry {
      private final String name;
      private final double x;
      private final double y;
      private final double z;

      public WaypointEntry(String name, double x, double y, double z) {
         this.name = name;
         this.x = x;
         this.y = y;
         this.z = z;
      }

      public String getName() {
         return this.name;
      }

      public double getX() {
         return this.x;
      }

      public double getY() {
         return this.y;
      }

      public double getZ() {
         return this.z;
      }
   }
}
