package sky.core.modules.impl.player;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.modules.impl.movement.GrimGlide;

public class AutoEatGApple extends Module {
   private final SliderSetting health = new SliderSetting("Здоровье", 14.0F, 1.0F, 20.0F, 0.5F);
   private boolean isEating = false;

   public AutoEatGApple() {
      super("Auto Eat GApple", "Автоматически кушает золотые яблоки", Category.Player);
      this.addSettings(new Setting[]{this.health});
   }

   @EventTarget
   public void onUpdate(EventUpdate event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null && (mc.currentScreen == null || mc.currentScreen instanceof InventoryScreen)) {
         if (GrimGlide.isGlideFlyActive()) {
            this.stopEating();
         } else {
            var10000 = mc;
            if (Minecraft.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE) {
               var10000 = mc;
               if (Minecraft.player.getHealth() <= (Float)this.health.get()) {
                  this.isEating = true;
                  if (!mc.gameSettings.keyBindUseItem.isKeyDown()) {
                     Minecraft var10001 = mc;
                     mc.playerController.processRightClick(Minecraft.player, mc.world, Hand.OFF_HAND);
                     mc.gameSettings.keyBindUseItem.setPressed(true);
                  }

                  return;
               }
            }

            if (this.isEating) {
               this.stopEating();
            }

         }
      } else {
         this.stopEating();
      }
   }

   private void stopEating() {
      if (this.isEating) {
         Minecraft var10000 = mc;
         if (Minecraft.player.isHandActive()) {
            Minecraft var10001 = mc;
            mc.playerController.onStoppedUsingItem(Minecraft.player);
         }

         mc.gameSettings.keyBindUseItem.setPressed(false);
         this.isEating = false;
      }

   }

   public void onDisable() {
      this.stopEating();
      super.onDisable();
   }
}
