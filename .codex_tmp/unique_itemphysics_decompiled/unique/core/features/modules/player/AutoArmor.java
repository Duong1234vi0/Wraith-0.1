package unique.core.features.modules.player;

import java.util.Arrays;
import java.util.List;
import net.minecraft.class_1268;
import net.minecraft.class_1304;
import net.minecraft.class_1738;
import net.minecraft.class_1770;
import net.minecraft.class_1799;
import net.minecraft.class_1890;
import net.minecraft.class_1893;
import net.minecraft.class_2815;
import net.minecraft.class_2848;
import net.minecraft.class_2886;
import net.minecraft.class_408;
import net.minecraft.class_6880;
import net.minecraft.class_9304;
import net.minecraft.class_2848.class_2849;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.modules.Module;
import unique.core.gui.clickui.ClickGUI;
import unique.core.gui.hud.HudEditorGui;
import unique.core.setting.Setting;
import unique.core.utility.player.InventoryUtility;
import unique.core.utility.player.MovementUtility;

public class AutoArmor extends Module {
   private final Setting<AutoArmor.EnchantPriority> head = new Setting<>("Head", AutoArmor.EnchantPriority.Protection);
   private final Setting<AutoArmor.EnchantPriority> body = new Setting<>("Body", AutoArmor.EnchantPriority.Protection);
   private final Setting<AutoArmor.EnchantPriority> tights = new Setting<>("Tights", AutoArmor.EnchantPriority.Protection);
   private final Setting<AutoArmor.EnchantPriority> feet = new Setting<>("Feet", AutoArmor.EnchantPriority.Protection);
   private final Setting<AutoArmor.ElytraPriority> elytraPriority = new Setting<>("ElytraPriority", AutoArmor.ElytraPriority.Ignore);
   private final Setting<Integer> delay = new Setting<>("Delay", 5, 0, 10);
   private final Setting<Boolean> oldVersion = new Setting<>("OldVersion", false);
   private final Setting<Boolean> pauseInventory = new Setting<>("PauseInventory", false);
   private final Setting<Boolean> noMove = new Setting<>("NoMove", false);
   private final Setting<Boolean> ignoreCurse = new Setting<>("IgnoreCurse", true);
   private final Setting<Boolean> strict = new Setting<>("Strict", false);
   private int tickDelay = 0;
   List<AutoArmor.ArmorData> armorList = Arrays.asList(
      new AutoArmor.ArmorData(class_1304.field_6166, 36, -1, -1, -1),
      new AutoArmor.ArmorData(class_1304.field_6172, 37, -1, -1, -1),
      new AutoArmor.ArmorData(class_1304.field_6174, 38, -1, -1, -1),
      new AutoArmor.ArmorData(class_1304.field_6169, 39, -1, -1, -1)
   );

   public AutoArmor() {
      super("AutoArmor", Module.Category.PLAYER);
   }

   @Override
   public void onUpdate() {
      if (mc.field_1755 == null
         || !this.pauseInventory.getValue()
         || mc.field_1755 instanceof class_408
         || mc.field_1755 instanceof ClickGUI
         || mc.field_1755 instanceof HudEditorGui) {
         if (this.tickDelay-- <= 0) {
            this.armorList.forEach(AutoArmor.ArmorData::reset);

            for (int i = 0; i < 36; i++) {
               class_1799 stack = mc.field_1724.method_31548().method_5438(i);
               int prot = this.getProtection(stack);
               if (prot > 0) {
                  for (AutoArmor.ArmorData e : this.armorList) {
                     if (e.getEquipmentSlot() == (stack.method_7909() instanceof class_1738 ai ? ai.method_7685() : class_1304.field_6174)
                        && prot > e.getPrevProt()
                        && prot > e.getNewProtection()) {
                        e.setNewSlot(i);
                        e.setNewProtection(prot);
                     }
                  }
               }
            }

            for (AutoArmor.ArmorData armorPiece : this.armorList) {
               int slot = armorPiece.getNewSlot();
               if (slot != -1) {
                  if ((armorPiece.getPrevProt() == -1 || !this.oldVersion.getValue()) && slot < 9) {
                     InventoryUtility.saveAndSwitchTo(slot);
                     this.sendSequencedPacket(id -> new class_2886(class_1268.field_5808, id, mc.field_1724.method_36454(), mc.field_1724.method_36455()));
                     InventoryUtility.returnSlot();
                  } else {
                     if (MovementUtility.isMoving() && this.noMove.getValue()) {
                        return;
                     }

                     int newArmorSlot = slot < 9 ? 36 + slot : slot;
                     if (this.strict.getValue()) {
                        this.sendPacket(new class_2848(mc.field_1724, class_2849.field_12985));
                     }

                     clickSlot(newArmorSlot);
                     clickSlot(armorPiece.getArmorSlot() - 34 + (39 - armorPiece.getArmorSlot()) * 2);
                     if (armorPiece.getPrevProt() != -1) {
                        clickSlot(newArmorSlot);
                     }

                     this.sendPacket(new class_2815(mc.field_1724.field_7512.field_7763));
                  }

                  this.tickDelay = this.delay.getValue();
                  return;
               }
            }
         }
      }
   }

   private int getProtection(class_1799 is) {
      if (is.method_7909() instanceof class_1738 || is.method_7909() instanceof class_1770) {
         int prot = 0;
         class_1304 slot = is.method_7909() instanceof class_1738 ai ? ai.method_7685() : class_1304.field_48824;
         if (is.method_7909() instanceof class_1770) {
            if (!class_1770.method_7804(is)) {
               return 0;
            }

            boolean ePlus = this.elytraPriority.is(AutoArmor.ElytraPriority.ElytraPlus) && ModuleManager.elytraPlus.isEnabled();
            boolean ignore = this.elytraPriority.is(AutoArmor.ElytraPriority.Ignore)
               && mc.field_1724.method_31548().method_5438(38).method_7909() instanceof class_1770;
            if (ePlus || ignore || this.elytraPriority.is(AutoArmor.ElytraPriority.Always)) {
               prot = 999;
            }
         }

         int blastMultiplier = 1;
         int protectionMultiplier = 1;
         switch (slot) {
            case field_6169:
               if (this.head.is(AutoArmor.EnchantPriority.Protection)) {
                  protectionMultiplier *= 2;
               } else {
                  blastMultiplier *= 2;
               }
               break;
            case field_48824:
               if (this.body.is(AutoArmor.EnchantPriority.Protection)) {
                  protectionMultiplier *= 2;
               } else {
                  blastMultiplier *= 2;
               }
               break;
            case field_6172:
               if (this.tights.is(AutoArmor.EnchantPriority.Protection)) {
                  protectionMultiplier *= 2;
               } else {
                  blastMultiplier *= 2;
               }
               break;
            case field_6166:
               if (this.feet.is(AutoArmor.EnchantPriority.Protection)) {
                  protectionMultiplier *= 2;
               } else {
                  blastMultiplier *= 2;
               }
         }

         if (is.method_7942()) {
            class_9304 enchants = class_1890.method_57532(is);
            if (enchants.method_57534()
               .contains(mc.field_1687.method_30349().method_30530(class_1893.field_9111.method_58273()).method_40264(class_1893.field_9111).get())) {
               prot += enchants.method_57536(
                     (class_6880)mc.field_1687.method_30349().method_30530(class_1893.field_9111.method_58273()).method_40264(class_1893.field_9111).get()
                  )
                  * protectionMultiplier;
            }

            if (enchants.method_57534()
               .contains(mc.field_1687.method_30349().method_30530(class_1893.field_9107.method_58273()).method_40264(class_1893.field_9107).get())) {
               prot += enchants.method_57536(
                     (class_6880)mc.field_1687.method_30349().method_30530(class_1893.field_9107.method_58273()).method_40264(class_1893.field_9107).get()
                  )
                  * blastMultiplier;
            }

            if (enchants.method_57534()
                  .contains(mc.field_1687.method_30349().method_30530(class_1893.field_9107.method_58273()).method_40264(class_1893.field_9113).get())
               && this.ignoreCurse.getValue()) {
               prot = -999;
            }
         }

         return (is.method_7909() instanceof class_1738 armorItem ? (armorItem.method_7687() + (int)Math.ceil(armorItem.method_26353())) * 10 : 0) + prot;
      } else {
         return !is.method_7960() ? 0 : -1;
      }
   }

   public class ArmorData {
      private class_1304 equipmentSlot;
      private int armorSlot;
      private int prevProtection;
      private int newSlot;
      private int newProtection;

      public ArmorData(class_1304 equipmentSlot, int armorSlot, int prevProtection, int newSlot, int newProtection) {
         this.equipmentSlot = equipmentSlot;
         this.armorSlot = armorSlot;
         this.prevProtection = prevProtection;
         this.newSlot = newSlot;
         this.newProtection = newProtection;
      }

      public int getArmorSlot() {
         return this.armorSlot;
      }

      public int getPrevProt() {
         return this.prevProtection;
      }

      public void setPrevProt(int prevProtection) {
         this.prevProtection = prevProtection;
      }

      public int getNewSlot() {
         return this.newSlot;
      }

      public void setNewSlot(int newSlot) {
         this.newSlot = newSlot;
      }

      public int getNewProtection() {
         return this.newProtection;
      }

      public void setNewProtection(int newProtection) {
         this.newProtection = newProtection;
      }

      public class_1304 getEquipmentSlot() {
         return this.equipmentSlot;
      }

      public void reset() {
         this.setPrevProt(AutoArmor.this.getProtection(Module.mc.field_1724.method_31548().method_5438(this.getArmorSlot())));
         this.setNewSlot(-1);
         this.setNewProtection(-1);
      }
   }

   private static enum ElytraPriority {
      None,
      Always,
      ElytraPlus,
      Ignore;
   }

   private static enum EnchantPriority {
      Blast,
      Protection;
   }
}
