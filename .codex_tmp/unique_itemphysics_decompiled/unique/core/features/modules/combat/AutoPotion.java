package unique.core.features.modules.combat;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1799;
import net.minecraft.class_1828;
import net.minecraft.class_1844;
import net.minecraft.class_2868;
import net.minecraft.class_2886;
import net.minecraft.class_6880;
import net.minecraft.class_9334;
import unique.core.events.impl.EventAfterRotate;
import unique.core.events.impl.EventPostSync;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.setting.impl.BooleanSettingGroup;

public final class AutoPotion extends Module {
   private final Setting<AutoPotion.StrengthMode> strengthMode = new Setting<>("Mode", AutoPotion.StrengthMode.Normal);
   private final Setting<Boolean> strength = new Setting<>("Strength", true);
   private final Setting<Boolean> speed = new Setting<>("Speed", true);
   private final Setting<Boolean> fire = new Setting<>("FireResistance", true);
   private final Setting<BooleanSettingGroup> heal = new Setting<>("InstantHealing", new BooleanSettingGroup(true));
   private final Setting<Integer> healthH = new Setting<>("Health", 8, 0, 20).addToGroup(this.heal);
   private final Setting<Boolean> onDaGround = new Setting<>("OnlyOnGround", true);
   private final Setting<Integer> throwTick = new Setting<>("ThrowTick", 2, 1, 3);
   private final Setting<Boolean> inventory = new Setting<>("Inventory", false);
   private boolean spoofed;
   private int tickCounter;

   public AutoPotion() {
      super("AutoPotion", Module.Category.COMBAT);
   }

   public static int getPotionSlot(AutoPotion.Potions potion, boolean inventory) {
      int max = inventory ? 36 : 9;

      for (int i = 0; i < max; i++) {
         if (isStackPotion(mc.field_1724.method_31548().method_5438(i), potion)) {
            return i;
         }
      }

      return -1;
   }

   public static boolean isPotionAvailable(AutoPotion.Potions potion, boolean inventory) {
      return getPotionSlot(potion, inventory) != -1;
   }

   public static boolean isStackPotion(class_1799 stack, AutoPotion.Potions potion) {
      if (stack == null) {
         return false;
      } else {
         if (stack.method_7909() instanceof class_1828) {
            class_1844 contents = (class_1844)stack.method_57825(class_9334.field_49651, class_1844.field_49274);
            class_6880<class_1291> id = null;
            switch (potion) {
               case STRENGTH:
                  id = class_1294.field_5910;
                  break;
               case SPEED:
                  id = class_1294.field_5904;
                  break;
               case FIRERES:
                  id = class_1294.field_5918;
                  break;
               case HEAL:
                  id = class_1294.field_5915;
            }

            for (class_1293 effect : contents.method_57397()) {
               if (effect.method_5579() == id) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   @EventHandler
   public void onPostRotationSet(EventAfterRotate e) {
      if (Aura.target == null || !(mc.field_1724.method_7261(1.0F) > 0.5F)) {
         if (mc.field_1724.field_6012 > 80 && this.shouldThrow()) {
            mc.field_1724.method_36457(90.0F);
            this.spoofed = true;
         }
      }
   }

   private boolean shouldThrow() {
      boolean inv = this.inventory.getValue();
      return !mc.field_1724.method_6059(class_1294.field_5904) && isPotionAvailable(AutoPotion.Potions.SPEED, inv) && this.speed.getValue()
         || !mc.field_1724.method_6059(class_1294.field_5910) && isPotionAvailable(AutoPotion.Potions.STRENGTH, inv) && this.strength.getValue()
         || !mc.field_1724.method_6059(class_1294.field_5918) && isPotionAvailable(AutoPotion.Potions.FIRERES, inv) && this.fire.getValue()
         || mc.field_1724.method_6032() + mc.field_1724.method_6067() < this.healthH.getValue().intValue()
            && isPotionAvailable(AutoPotion.Potions.HEAL, inv)
            && this.heal.getValue().isEnabled();
   }

   @EventHandler
   public void onPostSync(EventPostSync e) {
      if (Aura.target == null || !(mc.field_1724.method_7261(1.0F) > 0.5F)) {
         if (!this.onDaGround.getValue() || mc.field_1724.method_24828()) {
            if (this.spoofed && this.shouldThrow()) {
               if (this.strengthMode.getValue() == AutoPotion.StrengthMode.Normal) {
                  this.tickCounter++;
                  if (this.tickCounter < this.throwTick.getValue()) {
                     return;
                  }
               }

               boolean inv = this.inventory.getValue();
               if (!mc.field_1724.method_6059(class_1294.field_5904) && this.speed.getValue() && isPotionAvailable(AutoPotion.Potions.SPEED, inv)) {
                  this.throwPotion(AutoPotion.Potions.SPEED);
               } else if (!mc.field_1724.method_6059(class_1294.field_5910) && this.strength.getValue() && isPotionAvailable(AutoPotion.Potions.STRENGTH, inv)) {
                  this.throwPotion(AutoPotion.Potions.STRENGTH);
               } else if (!mc.field_1724.method_6059(class_1294.field_5918) && this.fire.getValue() && isPotionAvailable(AutoPotion.Potions.FIRERES, inv)) {
                  this.throwPotion(AutoPotion.Potions.FIRERES);
               } else if (mc.field_1724.method_6032() + mc.field_1724.method_6067() < this.healthH.getValue().intValue()
                  && this.heal.getValue().isEnabled()
                  && isPotionAvailable(AutoPotion.Potions.HEAL, inv)) {
                  this.throwPotion(AutoPotion.Potions.HEAL);
               }

               this.sendPacket(new class_2868(mc.field_1724.method_31548().field_7545));
               this.tickCounter = 0;
               this.spoofed = false;
            }
         }
      }
   }

   public void throwPotion(AutoPotion.Potions potion) {
      int slot = getPotionSlot(potion, this.inventory.getValue());
      if (slot != -1) {
         if (slot > 8) {
            mc.field_1724.method_31548().field_7545 = slot;
         } else {
            this.sendPacket(new class_2868(slot));
         }

         this.sendSequencedPacket(id -> new class_2886(class_1268.field_5808, id, mc.field_1724.method_36454(), mc.field_1724.method_36455()));
      }
   }

   public static enum Potions {
      STRENGTH,
      SPEED,
      FIRERES,
      HEAL;
   }

   public static enum StrengthMode {
      Normal,
      Fast;
   }
}
