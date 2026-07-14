package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import mods.particular.ParticularWaterSplash;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.item.ItemEntity;
import sky.core.events.EventSwapWorld;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;

public class ParticularWater extends Module {
   private final ParticularWaterSplash splash = new ParticularWaterSplash();
   private boolean wasInWater;
   private final Map itemWasInWater = new HashMap();
   private final Map itemVelocities = new HashMap();

   public ParticularWater() {
      super("Particular", "Брызги воды при прыжке", Category.Visuals);
   }

   public void onDisable() {
      super.onDisable();
      this.wasInWater = false;
      this.itemWasInWater.clear();
      this.itemVelocities.clear();
   }

   @EventTarget
   public void onSwapWorld(EventSwapWorld event) {
      this.wasInWater = false;
      this.itemWasInWater.clear();
      this.itemVelocities.clear();
   }

   @EventTarget
   public void onUpdate(EventUpdate event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         var10000 = mc;
         ClientPlayerEntity player = Minecraft.player;
         this.splash.trackVelocity(player);
         boolean inWater = player.isInWater();
         if (inWater && !this.wasInWater) {
            this.splash.trySpawnOnWaterEntry(player);
         }

         this.wasInWater = inWater;
         Minecraft var10002 = mc;

         UUID uuid;
         boolean currentlyInWater;
         for(Iterator var4 = mc.world.getEntitiesWithinAABB(ItemEntity.class, Minecraft.player.getBoundingBox().grow(64.0)).iterator(); var4.hasNext(); this.itemWasInWater.put(uuid, currentlyInWater)) {
            ItemEntity item = (ItemEntity)var4.next();
            uuid = item.getUniqueID();
            currentlyInWater = item.isInWater();
            boolean wasIn = (Boolean)this.itemWasInWater.getOrDefault(uuid, false);
            Queue velocities = (Queue)this.itemVelocities.computeIfAbsent(uuid, (k) -> {
               return new LinkedList();
            });
            velocities.offer(Math.abs((float)item.getMotion().y));
            if (velocities.size() > 4) {
               velocities.poll();
            }

            if (currentlyInWater && !wasIn) {
               float speed = velocities.isEmpty() ? 0.0F : (Float)Collections.max(velocities);
               ParticularWaterSplash.spawnEmitter(item.world, item.getPosX(), item.getPosY(), item.getPosZ(), item.getWidth() * 2.0F, speed);
            }
         }

      }
   }
}
