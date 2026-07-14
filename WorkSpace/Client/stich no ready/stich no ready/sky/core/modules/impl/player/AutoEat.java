package sky.core.modules.impl.player;

import com.adl.nativeprotect.Native;
import com.darkmagician6.eventapi.eventapinew.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import sky.core.SkyCore;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.modules.impl.combat.AttackAura;
import sky.core.modules.impl.movement.GrimGlide;

public class AutoEat extends Module {
   private final BooleanSetting noUseWithAura = new BooleanSetting("Не использовать с AttackAura", false);
   private final SliderSetting hungerThreshold = new SliderSetting("Кол-во голода", 18.0F, 1.0F, 20.0F, 0.5F);
   private boolean isEating = false;

   public AutoEat() {
      super("AutoEat", "Автоматически ест еду из рук для восстановления голода", Category.Player);
      this.addSettings(new Setting[]{this.noUseWithAura, this.hungerThreshold});
   }

   @EventTarget
   @Native
   public void onUpdate(EventUpdate event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null && (mc.currentScreen == null || mc.currentScreen instanceof InventoryScreen)) {
         if (GrimGlide.isGlideFlyActive()) {
            this.stopEating();
         } else {
            if ((Boolean)this.noUseWithAura.get()) {
               Module attackAura = SkyCore.getInstance().getModuleManager().getModule(AttackAura.class);
               if (attackAura != null && attackAura.isEnabled()) {
                  this.stopEating();
                  return;
               }
            }

            var10000 = mc;
            ItemStack offHandStack = Minecraft.player.getHeldItemOffhand();
            var10000 = mc;
            ItemStack mainHandStack = Minecraft.player.getHeldItemMainhand();
            boolean offHandFood = this.isEdibleFood(offHandStack);
            boolean mainHandFood = this.isEdibleFood(mainHandStack);
            if (offHandFood || mainHandFood) {
               var10000 = mc;
               if (Minecraft.player.getFoodStats().getFoodLevel() <= ((Float)this.hungerThreshold.get()).intValue()) {
                  Hand handToUse = offHandFood ? Hand.OFF_HAND : Hand.MAIN_HAND;
                  this.isEating = true;
                  if (!mc.gameSettings.keyBindUseItem.isKeyDown()) {
                     Minecraft var10001 = mc;
                     mc.playerController.processRightClick(Minecraft.player, mc.world, handToUse);
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

   private boolean isEdibleFood(ItemStack stack) {
      return !stack.isEmpty() && stack.getItem().isFood();
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
