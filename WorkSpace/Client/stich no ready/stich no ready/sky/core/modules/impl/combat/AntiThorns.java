package sky.core.modules.impl.combat;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityStatusPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import sky.core.events.EventPacket;
import sky.core.events.EventThornsDamage;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;

public class AntiThorns extends Module {
   private static final int THORNS_VELOCITY_SUPPRESS_TICKS = 8;
   private static final int THORNS_ENTITY_STATUS_OPCODE = 33;
   private static AntiThorns instance;
   private int suppressVelocityTicks;

   public AntiThorns() {
      super("AntiThorns", "Убирает урон и откидывание от шипов", Category.Combat);
      instance = this;
   }

   public static AntiThorns getInstance() {
      return instance;
   }

   @EventTarget
   public void onThornsDamage(EventThornsDamage event) {
      if (this.shouldCancelThornsDamage(event.getAttacker())) {
         event.setCancelled(true);
      }

   }

   @EventTarget
   public void onPacket(EventPacket event) {
      if (event.isReceive()) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null && mc.world != null) {
            IPacket var3 = event.getPacket();
            if (var3 instanceof SEntityStatusPacket) {
               SEntityStatusPacket packet = (SEntityStatusPacket)var3;
               if (packet.getOpCode() == 33) {
                  Entity var5 = packet.getEntity(mc.world);
                  Minecraft var10001 = mc;
                  if (var5 == Minecraft.player && this.isLocalPlayerElytraFlying()) {
                     this.armVelocitySuppress();
                  }
               }

               return;
            }

            var3 = event.getPacket();
            if (var3 instanceof SEntityVelocityPacket) {
               SEntityVelocityPacket packet = (SEntityVelocityPacket)var3;
               if (this.shouldCancelVelocityPacket(packet)) {
                  event.setCancelled(true);
                  this.resetVelocitySuppress();
               }
            }

            return;
         }
      }

   }

   @EventTarget
   public void onUpdate(EventUpdate event) {
      this.tickSuppressTimer();
   }

   public void tickSuppressTimer() {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && this.isLocalPlayerElytraFlying()) {
         if (this.suppressVelocityTicks > 0) {
            --this.suppressVelocityTicks;
         }

      } else {
         this.resetVelocitySuppress();
      }
   }

   public boolean shouldCancelThornsDamage(Entity attacker) {
      if (this.isEnabled()) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            Minecraft var10001 = mc;
            if (attacker == Minecraft.player) {
               if (this.isLocalPlayerElytraFlying()) {
                  this.armVelocitySuppress();
               }

               return true;
            }
         }
      }

      return false;
   }

   public boolean shouldCancelVelocityPacket(SEntityVelocityPacket packet) {
      boolean var3;
      if (this.isVelocitySuppressActive() && packet != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            int var2 = packet.getEntityID();
            Minecraft var10001 = mc;
            if (var2 == Minecraft.player.getEntityId()) {
               var3 = true;
               return var3;
            }
         }
      }

      var3 = false;
      return var3;
   }

   public boolean isVelocitySuppressActive() {
      return this.suppressVelocityTicks > 0 && this.isLocalPlayerElytraFlying();
   }

   public int getSuppressVelocityTicks() {
      return this.suppressVelocityTicks;
   }

   public void armVelocitySuppress() {
      this.suppressVelocityTicks = 8;
   }

   public void resetVelocitySuppress() {
      this.suppressVelocityTicks = 0;
   }

   public void onEnable() {
      this.resetVelocitySuppress();
      super.onEnable();
   }

   public void onDisable() {
      this.resetVelocitySuppress();
      super.onDisable();
   }

   private boolean isLocalPlayerElytraFlying() {
      Minecraft var10000 = mc;
      LivingEntity player = Minecraft.player;
      return player != null && player.isElytraFlying();
   }
}
