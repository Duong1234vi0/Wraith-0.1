package sky.core.utils.managers.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import sky.core.utils.managers.BaseManager;

public class NukerManager extends BaseManager {
   public NukerManager() {
      super("temp\\nuker.file");
   }

   protected void initializeData() {
      this.data = new ArrayList();
   }

   protected JsonObject serializeData() {
      JsonObject config = new JsonObject();
      JsonArray arr = new JsonArray();
      Iterator var3 = ((List)this.data).iterator();

      while(var3.hasNext()) {
         String blockName = (String)var3.next();
         arr.add(blockName);
      }

      config.add("nuker_blocks", arr);
      return config;
   }

   protected void deserializeData(JsonObject jsonObject) {
      ((List)this.data).clear();
      JsonArray arr = jsonObject.getAsJsonArray("nuker_blocks");
      if (arr != null) {
         Iterator var3 = arr.iterator();

         while(var3.hasNext()) {
            JsonElement el = (JsonElement)var3.next();
            if (el.isJsonPrimitive()) {
               ((List)this.data).add(el.getAsString());
            }
         }
      }

   }

   protected void handleLoadError(Exception exception) {
      if (this.data == null) {
         this.initializeData();
      }

   }

   public List getTargetBlocks() {
      List blocks = new ArrayList();
      Iterator var2 = ((List)this.data).iterator();

      while(var2.hasNext()) {
         String blockName = (String)var2.next();

         try {
            ResourceLocation location = new ResourceLocation(blockName);
            Block block = (Block)Registry.BLOCK.getOptional(location).orElse((Object)null);
            if (block != null) {
               blocks.add(block);
            }
         } catch (Exception var6) {
         }
      }

      return blocks;
   }

   public boolean addTargetBlock(ResourceLocation resourceLocation) {
      if (resourceLocation == null) {
         return false;
      } else {
         String blockName = resourceLocation.toString();
         Block block = (Block)Registry.BLOCK.getOptional(resourceLocation).orElse((Object)null);
         if (block != null && !((List)this.data).contains(blockName)) {
            ((List)this.data).add(blockName);
            this.save();
            return true;
         } else {
            return false;
         }
      }
   }

   public boolean removeTargetBlock(ResourceLocation resourceLocation) {
      if (resourceLocation == null) {
         return false;
      } else {
         String blockName = resourceLocation.toString();
         boolean removed = ((List)this.data).remove(blockName);
         if (removed) {
            this.save();
         }

         return removed;
      }
   }

   public boolean isTargetBlock(Block block) {
      if (block == null) {
         return false;
      } else {
         ResourceLocation location = Registry.BLOCK.getKey(block);
         return ((List)this.data).contains(location.toString());
      }
   }

   public void clearTargetBlocks() {
      if (!((List)this.data).isEmpty()) {
         ((List)this.data).clear();
         this.save();
      }

   }

   public boolean isEmpty() {
      return ((List)this.data).isEmpty();
   }

   public int size() {
      return ((List)this.data).size();
   }
}
