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

public class BlockESPManager extends BaseManager {
   public BlockESPManager() {
      super("temp\\blockesp.file");
   }

   @Native
   protected void initializeData() {
      this.data = new ArrayList();
   }

   protected JsonObject serializeData() {
      JsonObject config = new JsonObject();
      JsonArray arr = new JsonArray();
      Iterator var3 = ((List)this.data).iterator();

      while(var3.hasNext()) {
         BlockEntry entry = (BlockEntry)var3.next();
         JsonObject o = new JsonObject();
         o.addProperty("block", entry.getBlock());
         o.addProperty("color", entry.getColor());
         arr.add(o);
      }

      config.add("blockesp", arr);
      return config;
   }

   protected void deserializeData(JsonObject jsonObject) {
      ((List)this.data).clear();
      JsonArray arr = jsonObject.getAsJsonArray("blockesp");
      if (arr != null) {
         Iterator var3 = arr.iterator();

         while(var3.hasNext()) {
            JsonElement el = (JsonElement)var3.next();
            if (el.isJsonObject()) {
               JsonObject o = el.getAsJsonObject();
               this.loadProperty(o, "block", (b) -> {
                  this.loadProperty(o, "color", (c) -> {
                     try {
                        ((List)this.data).add(new BlockEntry(b.getAsString(), c.getAsInt()));
                     } catch (Exception var4) {
                     }

                  });
               });
            }
         }
      }

   }

   protected void handleLoadError(Exception exception) {
      if (this.data == null) {
         this.initializeData();
      }

   }

   public List getBlocks() {
      return new ArrayList((Collection)this.data);
   }

   public void addBlock(String block, int color) {
      if (block != null) {
         String normalized = block.trim().toLowerCase();
         ((List)this.data).removeIf((b) -> {
            return b.getBlock().equalsIgnoreCase(normalized);
         });
         ((List)this.data).add(new BlockEntry(normalized, color));
         this.save();
      }
   }

   public boolean removeBlock(String block) {
      if (block == null) {
         return false;
      } else {
         boolean removed = ((List)this.data).removeIf((b) -> {
            return b.getBlock().equalsIgnoreCase(block.trim());
         });
         if (removed) {
            this.save();
         }

         return removed;
      }
   }

   public void clear() {
      if (!((List)this.data).isEmpty()) {
         ((List)this.data).clear();
         this.save();
      }

   }

   private void loadProperty(JsonObject object, String key, Consumer consumer) {
      JsonElement element = object.get(key);
      if (element != null && !element.isJsonNull()) {
         consumer.accept(element);
      }

   }

   public static class BlockEntry {
      private final String block;
      private final int color;

      public BlockEntry(String block, int color) {
         this.block = block;
         this.color = color;
      }

      public String getBlock() {
         return this.block;
      }

      public int getColor() {
         return this.color;
      }
   }
}
