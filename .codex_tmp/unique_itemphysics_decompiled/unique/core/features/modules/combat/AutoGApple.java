package unique.core.features.modules.combat;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1802;
import unique.core.core.manager.client.ModuleManager;
import unique.core.events.impl.PostPlayerUpdateEvent;
import unique.core.features.modules.Module;
import unique.core.injection.accesors.IMinecraftClient;
import unique.core.setting.Setting;
import unique.core.utility.Timer;
import unique.core.utility.player.InventoryUtility;

public final class AutoGApple extends Module {
   public final Setting<Integer> Delay = new Setting<>("UseDelay", 0, 0, 2000);
   private final Setting<Float> health = new Setting<>("Health", 15.0F, 1.0F, 36.0F);
   private boolean isActive;
   private final Timer useDelay = new Timer();

   public AutoGApple() {
      super("AutoGApple", Module.Category.COMBAT);
   }

   @EventHandler
   public void onUpdate(PostPlayerUpdateEvent e) {
      if (!fullNullCheck()) {
         if (this.GapInOffHand()) {
            if (mc.field_1724.method_6032() <= this.health.getValue() && this.useDelay.passedMs(this.Delay.getValue().intValue())) {
               this.isActive = true;
               if (mc.field_1755 != null && !mc.field_1724.method_6115()) {
                  ((IMinecraftClient)mc).idoItemUse();
               } else {
                  mc.field_1690.field_1904.method_23481(true);
               }
            } else if (this.isActive) {
               this.isActive = false;
               mc.field_1690.field_1904.method_23481(false);
            }
         } else if (this.isActive) {
            this.isActive = false;
            mc.field_1690.field_1904.method_23481(false);
         }
      }
   }

   private boolean GapInOffHand() {
      return ModuleManager.autoTotem.isEnabled() && InventoryUtility.findItemInHotBar(class_1802.field_8463, class_1802.field_8367).found()
         ? true
         : !mc.field_1724.method_6079().method_7960()
            && (mc.field_1724.method_6079().method_7909() == class_1802.field_8463 || mc.field_1724.method_6079().method_7909() == class_1802.field_8367);
   }
}
