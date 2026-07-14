package sky.core.utils.player;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityStatusPacket;
import net.minecraft.network.play.server.SExplosionPacket;
import net.minecraft.potion.Effects;
import sky.core.events.EventDamage;
import sky.core.events.EventPacket;
import sky.core.utils.Wrapper;
import sky.core.utils.math.TimeUtil;

public class DamageUtil implements Wrapper {
   private final TimeUtil timeTracker = new TimeUtil();
   private boolean normalDamage;
   private boolean fallDamage;
   private boolean explosionDamage;
   private boolean arrowDamage;
   private boolean pearlDamage;
   private boolean blockDamage;

   public void onPacketEvent(EventPacket eventPacket) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         boolean isDamage = this.fallDamage || this.arrowDamage || this.explosionDamage || this.pearlDamage || this.blockDamage;
         if (!this.isBadEffects()) {
            if (eventPacket.getPacket() instanceof SExplosionPacket) {
               this.explosionDamage = true;
            }

            if (!isDamage) {
               IPacket packet = eventPacket.getPacket();
               if (packet instanceof SEntityStatusPacket) {
                  SEntityStatusPacket statusPacket = (SEntityStatusPacket)packet;
                  if (statusPacket.getOpCode() == 2) {
                     Entity var5 = statusPacket.getEntity(mc.world);
                     Minecraft var10001 = mc;
                     if (var5 == Minecraft.player) {
                        this.normalDamage = true;
                     }
                  }
               }
            } else {
               var10000 = mc;
               if (Minecraft.player.hurtTime > 0) {
                  this.normalDamage = false;
                  this.reset();
               }
            }
         }
      }

   }

   public boolean time(long time) {
      if (this.normalDamage) {
         if (this.timeTracker.hasTimeElapsed(time)) {
            this.normalDamage = false;
            this.timeTracker.reset();
            return true;
         }
      } else {
         this.timeTracker.reset();
      }

      return false;
   }

   public void processDamage(EventDamage damageEvent) {
      switch (damageEvent.getDamageType()) {
         case FALL:
            this.fallDamage = true;
            break;
         case ARROW:
            this.arrowDamage = true;
            break;
         case ENDER_PEARL:
            this.pearlDamage = true;
            break;
         case BLOCK:
            this.blockDamage = true;
      }

      this.normalDamage = false;
   }

   public void resetDamages() {
      this.normalDamage = false;
      this.reset();
      this.timeTracker.reset();
   }

   private void reset() {
      this.fallDamage = false;
      this.explosionDamage = false;
      this.arrowDamage = false;
      this.pearlDamage = false;
      this.blockDamage = false;
   }

   private boolean isBadEffects() {
      Minecraft var10000 = mc;
      if (Minecraft.player == null) {
         return false;
      } else {
         var10000 = mc;
         boolean var1;
         if (!Minecraft.player.isPotionActive(Effects.POISON)) {
            var10000 = mc;
            if (!Minecraft.player.isPotionActive(Effects.WITHER)) {
               var10000 = mc;
               if (!Minecraft.player.isPotionActive(Effects.INSTANT_DAMAGE)) {
                  var1 = false;
                  return var1;
               }
            }
         }

         var1 = true;
         return var1;
      }
   }

   public TimeUtil getTimeTracker() {
      return this.timeTracker;
   }

   public boolean isNormalDamage() {
      return this.normalDamage;
   }

   public boolean isFallDamage() {
      return this.fallDamage;
   }

   public boolean isExplosionDamage() {
      return this.explosionDamage;
   }

   public boolean isArrowDamage() {
      return this.arrowDamage;
   }

   public boolean isPearlDamage() {
      return this.pearlDamage;
   }

   public boolean isBlockDamage() {
      return this.blockDamage;
   }
}
