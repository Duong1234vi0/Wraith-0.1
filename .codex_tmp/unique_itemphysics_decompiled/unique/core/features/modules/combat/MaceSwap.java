package unique.core.features.modules.combat;

import java.util.Optional;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1802;
import net.minecraft.class_1887;
import net.minecraft.class_1890;
import net.minecraft.class_1893;
import net.minecraft.class_2378;
import net.minecraft.class_243;
import net.minecraft.class_2824;
import net.minecraft.class_2868;
import net.minecraft.class_5321;
import net.minecraft.class_6880;
import net.minecraft.class_7924;
import net.minecraft.class_2824.class_5908;
import net.minecraft.class_6880.class_6883;
import unique.core.events.impl.EventAttack;
import unique.core.events.impl.EventPostSync;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.utility.player.InventoryUtility;
import unique.core.utility.player.SearchInvResult;

public class MaceSwap extends Module {
   private final Setting<MaceSwap.Mode> mode = new Setting<>("Mode", MaceSwap.Mode.Breach);
   private final Setting<Boolean> maceStun = new Setting<>("MaceStun", true);
   private final Setting<Boolean> silent = new Setting<>("Silent", true);
   private int originalSlot = -1;
   private boolean swapped;

   public MaceSwap() {
      super("MaceSwap", Module.Category.COMBAT);
   }

   @Override
   public void onEnable() {
      this.reset();
   }

   @Override
   public void onDisable() {
      if (this.swapped) {
         this.revert();
      }

      this.reset();
   }

   @EventHandler
   public void onAttack(EventAttack e) {
      this.trySwap();
   }

   @EventHandler
   public void onPacketSend(PacketEvent.Send e) {
      if (e.getPacket() instanceof class_2824 packet) {
         packet.method_34209(new class_5908() {
            public void method_34218() {
               MaceSwap.this.trySwap();
            }

            public void method_34219(class_1268 hand) {
            }

            public void method_34220(class_1268 hand, class_243 pos) {
            }
         });
      }
   }

   @EventHandler
   public void onPostSync(EventPostSync e) {
      if (this.swapped && (!this.maceStun.getValue() || mc.field_1724.field_6235 > 0)) {
         this.revert();
      }
   }

   private void trySwap() {
      if (mc.field_1724 != null && !this.swapped) {
         if (mc.field_1724.method_6047().method_7909() != class_1802.field_49814) {
            SearchInvResult r = this.findMace();
            if (r.found()) {
               this.originalSlot = mc.field_1724.method_31548().field_7545;
               if (this.silent.getValue()) {
                  this.sendPacket(new class_2868(r.slot()));
               } else {
                  mc.field_1724.method_31548().field_7545 = r.slot();
               }

               this.swapped = true;
            }
         }
      }
   }

   private SearchInvResult findMace() {
      if (this.mode.getValue() == MaceSwap.Mode.Normal) {
         return InventoryUtility.findInHotBar(s -> s.method_7909() == class_1802.field_49814);
      } else {
         SearchInvResult prio = InventoryUtility.findInHotBar(s -> {
            if (s.method_7909() != class_1802.field_49814) {
               return false;
            } else {
               class_2378<class_1887> registry = mc.field_1687.method_30349().method_30530(class_7924.field_41265);

               class_5321<class_1887> key = switch ((MaceSwap.Mode)this.mode.getValue()) {
                  case Breach -> class_1893.field_50158;
                  case WindBurst -> class_1893.field_50159;
                  case Density -> class_1893.field_50157;
                  default -> null;
               };
               if (key == null) {
                  return false;
               } else {
                  Optional<class_6883<class_1887>> enchant = registry.method_40264(key);
                  return enchant.isPresent() && class_1890.method_8225((class_6880)enchant.get(), s) > 0;
               }
            }
         });
         if (this.mode.getValue() == MaceSwap.Mode.Density) {
            return prio;
         } else {
            return prio.found() ? prio : InventoryUtility.findInHotBar(s -> s.method_7909() == class_1802.field_49814);
         }
      }
   }

   private void revert() {
      if (this.originalSlot != -1) {
         if (this.silent.getValue()) {
            this.sendPacket(new class_2868(this.originalSlot));
         } else {
            mc.field_1724.method_31548().field_7545 = this.originalSlot;
         }

         this.reset();
      }
   }

   private void reset() {
      this.originalSlot = -1;
      this.swapped = false;
   }

   public static enum Mode {
      Normal,
      Breach,
      WindBurst,
      Density;
   }
}
