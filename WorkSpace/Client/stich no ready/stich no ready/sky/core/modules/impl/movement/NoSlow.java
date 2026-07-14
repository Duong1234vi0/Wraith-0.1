package sky.core.modules.impl.movement;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.item.UseAction;
import net.minecraft.network.play.client.CHeldItemChangePacket;
import net.minecraft.network.play.client.CPlayerTryUseItemPacket;
import net.minecraft.util.Hand;
import sky.core.events.EventNoSlow;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;

public class NoSlow extends Module {
   private final ModeSetting mode = new ModeSetting("Режим", "ReallyWorld", new String[]{"ReallyWorld", "Spooky", "BravoHvH"});
   private final SliderSetting vanillaSpeed = new SliderSetting("Скорость", 0.6F, 0.1F, 1.0F, 0.05F, () -> {
      return this.mode.is("BravoHvH");
   });
   public static int ticks = 0;
   private static int cycleCounter = 0;

   public NoSlow() {
      super("No Slow", "Убирает замедление при использовании предмета", Category.Movement);
      this.addSettings(new Setting[]{this.mode, this.vanillaSpeed});
   }

   @EventTarget
   public void onNoSlow(EventNoSlow event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         var10000 = mc;
         if (!Minecraft.player.isElytraFlying()) {
            var10000 = mc;
            if (Minecraft.player.isHandActive()) {
               switch ((String)this.mode.get()) {
                  case "BravoHvH":
                     this.handleBravo(event);
                     break;
                  case "ReallyWorld":
                     this.handleReallyWorld(event);
                     break;
                  case "Spooky":
                     this.handleSpooky(event);
               }

            }
         }
      }
   }

   @EventTarget
   public void onEvent(EventUpdate event) {
      if (this.mode.is("ReallyWorld") || this.mode.is("Spooky")) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            var10000 = mc;
            if (!Minecraft.player.isElytraFlying()) {
               var10000 = mc;
               if (Minecraft.player.isHandActive()) {
                  ++ticks;
               } else {
                  ticks = 0;
                  cycleCounter = 0;
               }
            }
         }
      }

   }

   private void handleReallyWorld(EventNoSlow eventNoSlow) {
      int[] thresholds = new int[]{2, 3, 3};
      int threshold = thresholds[cycleCounter % 3];
      if (ticks >= threshold) {
         eventNoSlow.setCancelled(true);
         ticks = 0;
         ++cycleCounter;
      }

   }

   private void handleSpooky(EventNoSlow eventNoSlow) {
      int[] thresholds = new int[]{2, 2};
      int threshold = thresholds[cycleCounter % 2];
      if (ticks >= threshold) {
         eventNoSlow.setCancelled(true);
         ticks = 0;
         ++cycleCounter;
      }

   }

   private void handleBravo(EventNoSlow eventNoSlow) {
      eventNoSlow.setCancelled(true);
      Minecraft var10000 = mc;
      double vx = Minecraft.player.getMotion().x * (double)(Float)this.vanillaSpeed.get();
      var10000 = mc;
      double vz = Minecraft.player.getMotion().z * (double)(Float)this.vanillaSpeed.get();
      var10000 = mc;
      Minecraft var10002 = mc;
      Minecraft.player.setMotion(vx, Minecraft.player.getMotion().y, vz);
   }

   private void handleGrimOld(EventNoSlow eventNoSlow) {
      Minecraft var10000;
      boolean var3;
      label26: {
         label25: {
            var10000 = mc;
            if (Minecraft.player.getHeldItemOffhand().getUseAction() != UseAction.BLOCK) {
               var10000 = mc;
               if (Minecraft.player.getHeldItemOffhand().getUseAction() != UseAction.EAT) {
                  var10000 = mc;
                  if (Minecraft.player.getHeldItemOffhand().getUseAction() != UseAction.DRINK) {
                     break label25;
                  }
               }
            }

            var10000 = mc;
            if (Minecraft.player.getActiveHand() == Hand.MAIN_HAND) {
               var3 = true;
               break label26;
            }
         }

         var3 = false;
      }

      boolean isBlockingOrEatingOffhand = var3;
      if (!isBlockingOrEatingOffhand) {
         var10000 = mc;
         if (Minecraft.player.getActiveHand() == Hand.MAIN_HAND) {
            var10000 = mc;
            Minecraft.player.connection.sendPacket(new CPlayerTryUseItemPacket(Hand.OFF_HAND));
            eventNoSlow.setCancelled(true);
         } else {
            eventNoSlow.setCancelled(true);
            this.sendItemChangePacket();
         }
      }
   }

   private void sendItemChangePacket() {
      Minecraft var10000;
      boolean var3;
      label16: {
         var10000 = mc;
         if (Minecraft.player.moveForward == 0.0F) {
            var10000 = mc;
            if (Minecraft.player.moveStrafing == 0.0F) {
               var3 = false;
               break label16;
            }
         }

         var3 = true;
      }

      boolean isMoving = var3;
      if (isMoving) {
         var10000 = mc;
         int nextSlot = Minecraft.player.inventory.currentItem % 8 + 1;
         var10000 = mc;
         Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(nextSlot));
         var10000 = mc;
         Minecraft var10003 = mc;
         Minecraft.player.connection.sendPacket(new CHeldItemChangePacket(Minecraft.player.inventory.currentItem));
      }
   }
}
