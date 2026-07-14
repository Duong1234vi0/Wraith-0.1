package sky.core.modules.impl.movement;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.play.client.CPlayerPacket;
import sky.core.events.EventMotion;
import sky.core.events.EventPacket;
import sky.core.events.EventSwapWorld;
import sky.core.events.EventTravel;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;

public class AirStuck extends Module {
   private final BooleanSetting changeAuraDistance = new BooleanSetting("Изменять дистанцию киллауры", false);
   private final SliderSetting auraDistance;
   private final BooleanSetting catchMoment;
   private double peakY;
   private boolean frozen;

   public AirStuck() {
      super("Air Stuck", "Можно зависнуть в воздухе", Category.Movement);
      BooleanSetting var10008 = this.changeAuraDistance;
      Objects.requireNonNull(var10008);
      this.auraDistance = new SliderSetting("Дистанция киллауры", 3.5F, 2.0F, 6.0F, 0.1F, var10008::get);
      this.catchMoment = new BooleanSetting("Ловить момент", true);
      this.peakY = Double.NaN;
      this.addSettings(new Setting[]{this.changeAuraDistance, this.auraDistance, this.catchMoment});
   }

   public void onEnable() {
      super.onEnable();
      if ((Boolean)this.catchMoment.get()) {
         double var1;
         label15: {
            Minecraft var10001 = mc;
            if (Minecraft.player != null) {
               var10001 = mc;
               if (!Minecraft.player.isOnGround()) {
                  var10001 = mc;
                  var1 = Minecraft.player.getPosY();
                  break label15;
               }
            }

            var1 = Double.NaN;
         }

         this.peakY = var1;
         this.frozen = false;
      } else {
         this.frozen = true;
      }

   }

   public void onDisable() {
      this.frozen = false;
      super.onDisable();
   }

   public float getKillAuraDistance() {
      return this.isEnabled() && (Boolean)this.changeAuraDistance.get() ? (Float)this.auraDistance.get() : -1.0F;
   }

   public boolean isFrozen() {
      return this.isEnabled() && this.frozen;
   }

   @EventTarget
   public void onUpdate(EventUpdate event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && (Boolean)this.catchMoment.get()) {
         var10000 = mc;
         double currentY = Minecraft.player.getPosY();
         var10000 = mc;
         if (Minecraft.player.isOnGround()) {
            this.peakY = Double.NaN;
            this.frozen = false;
         } else if (!this.frozen) {
            if (Double.isNaN(this.peakY)) {
               this.peakY = currentY;
            } else if (currentY > this.peakY) {
               this.peakY = currentY;
            } else if (currentY < this.peakY) {
               this.frozen = true;
            }
         }

      }
   }

   @EventTarget
   public void onSwapWorld(EventSwapWorld event) {
      this.toggle();
   }

   @EventTarget
   public void onPacket(EventPacket event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && this.frozen) {
         if (event.getPacket() instanceof CPlayerPacket) {
            event.setCancelled(true);
         }

      }
   }

   @EventTarget
   public void onMotion(EventMotion event) {
      if (this.frozen) {
         event.setY(0.0);
         event.setCancelled(true);
      }
   }

   @EventTarget
   public void onTravel(EventTravel event) {
      if (this.frozen) {
         LivingEntity var10000 = event.getEntity();
         Minecraft var10001 = mc;
         if (var10000 == Minecraft.player) {
            event.setCancelled(true);
         }
      }

   }
}
