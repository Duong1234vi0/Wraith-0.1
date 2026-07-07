package unique.core.features.modules.movement;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1802;
import net.minecraft.class_9334;
import unique.core.events.impl.EventKeyboardInput;
import unique.core.events.impl.EventTick;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.setting.impl.SettingGroup;

public class NoSlow extends Module {
   public final Setting<NoSlow.Mode> mode = new Setting<>("Mode", NoSlow.Mode.Grim);
   private final Setting<Boolean> mainHand = new Setting<>("MainHand", true);
   private final Setting<SettingGroup> selection = new Setting<>("Selection", new SettingGroup(false, 0));
   private final Setting<Boolean> food = new Setting<>("Food", true).addToGroup(this.selection);
   private final Setting<Boolean> projectiles = new Setting<>("Projectiles", false).addToGroup(this.selection);
   private final Setting<Boolean> shield = new Setting<>("Shield", true).addToGroup(this.selection);
   public final Setting<Boolean> soulSand = new Setting<>("SoulSand", false).addToGroup(this.selection);
   public final Setting<Boolean> honey = new Setting<>("Honey", false).addToGroup(this.selection);
   public final Setting<Boolean> slime = new Setting<>("Slime", false).addToGroup(this.selection);
   public final Setting<Boolean> ice = new Setting<>("Ice", true).addToGroup(this.selection);
   public final Setting<Boolean> sweetBerryBush = new Setting<>("SweetBerryBush", false).addToGroup(this.selection);
   public final Setting<Boolean> sneak = new Setting<>("Sneak", false).addToGroup(this.selection);
   public final Setting<Boolean> crawl = new Setting<>("Crawl", false).addToGroup(this.selection);
   private int grimLatestTicks = 0;
   private int grimLatestLastNoSlowTick = -1;

   public NoSlow() {
      super("NoSlow", Module.Category.MOVEMENT);
   }

   @Override
   public void onUpdate() {
      if (mc.field_1724 != null) {
         if (this.mode.getValue() == NoSlow.Mode.Grim && !mc.field_1724.method_6128()) {
            if (mc.field_1724.method_6115()) {
               this.grimLatestTicks++;
            } else {
               this.grimLatestTicks = 0;
            }
         }

         if (this.mode.getValue() == NoSlow.Mode.Matrix) {
            if (mc.field_1724.method_24828() && !mc.field_1690.field_1903.method_1434()) {
               mc.field_1724
                  .method_18800(
                     mc.field_1724.method_18798().field_1352 * 0.3, mc.field_1724.method_18798().field_1351, mc.field_1724.method_18798().field_1350 * 0.3
                  );
            } else if (mc.field_1724.field_6017 > 0.18F) {
               mc.field_1724
                  .method_18800(
                     mc.field_1724.method_18798().field_1352 * 0.95, mc.field_1724.method_18798().field_1351, mc.field_1724.method_18798().field_1350 * 0.95
                  );
            }
         }
      }
   }

   @EventHandler
   public void onKeyboardInput(EventKeyboardInput e) {
      if (this.mode.getValue() == NoSlow.Mode.MatrixNew && mc.field_1724 != null && mc.field_1724.method_6115() && !mc.field_1724.method_6128()) {
         mc.field_1724.field_3913.field_3905 *= 5.0F;
         mc.field_1724.field_3913.field_3907 *= 5.0F;
         float mult;
         if (mc.field_1724.method_24828()) {
            if (mc.field_1724.field_3913.field_3905 != 0.0F && mc.field_1724.field_3913.field_3907 != 0.0F) {
               mult = 0.35F;
            } else {
               mult = 0.5F;
            }
         } else if (mc.field_1724.field_3913.field_3905 != 0.0F && mc.field_1724.field_3913.field_3907 != 0.0F) {
            mult = 0.47F;
         } else {
            mult = 0.67F;
         }

         mc.field_1724.field_3913.field_3905 *= mult;
         mc.field_1724.field_3913.field_3907 *= mult;
      }
   }

   @EventHandler
   public void onTick(EventTick e) {
      if (mc.field_1724 != null) {
         ;
      }
   }

   public boolean canNoSlow() {
      if (this.mode.getValue() == NoSlow.Mode.MatrixNew) {
         return false;
      } else if (mc.field_1724.method_6030().method_7960()) {
         return false;
      } else if (!this.food.getValue() && mc.field_1724.method_6030().method_57353().method_57832(class_9334.field_50075)) {
         return false;
      } else if (!this.shield.getValue() && mc.field_1724.method_6030().method_7909() == class_1802.field_8255) {
         return false;
      } else if (this.projectiles.getValue()
         || mc.field_1724.method_6030().method_7909() != class_1802.field_8399
            && mc.field_1724.method_6030().method_7909() != class_1802.field_8102
            && mc.field_1724.method_6030().method_7909() != class_1802.field_8547) {
         if (!this.mainHand.getValue() && mc.field_1724.method_6058() == class_1268.field_5808) {
            return false;
         } else if (this.mode.getValue() != NoSlow.Mode.Grim) {
            return true;
         } else if (mc.field_1724.method_6128()) {
            return false;
         } else {
            int tick = mc.field_1724.field_6012;
            if (tick == this.grimLatestLastNoSlowTick) {
               return true;
            } else if (this.grimLatestTicks >= 2) {
               this.grimLatestTicks = 0;
               this.grimLatestLastNoSlowTick = tick;
               return true;
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   public static enum Mode {
      Grim,
      Matrix,
      MatrixNew;
   }
}
