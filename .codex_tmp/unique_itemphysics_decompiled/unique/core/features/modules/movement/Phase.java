package unique.core.features.modules.movement;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2879;
import net.minecraft.class_2886;
import unique.core.core.manager.client.ModuleManager;
import unique.core.events.impl.EventCollision;
import unique.core.events.impl.EventPostSync;
import unique.core.events.impl.EventSync;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.utility.player.InteractionUtility;
import unique.core.utility.player.InventoryUtility;
import unique.core.utility.player.MovementUtility;

public class Phase extends Module {
   private final Setting<Phase.Mode> mode = new Setting<>("Mode", Phase.Mode.Vanilla);
   private final Setting<Boolean> onlyOnGround = new Setting<>("OnlyOnGround", false, v -> this.mode.is(Phase.Mode.Pearl));
   private final Setting<Boolean> autoDisable = new Setting<>("AutoDisable", true, v -> this.mode.is(Phase.Mode.Pearl));
   private final Setting<Integer> afterPearl = new Setting<>("PearlTimeout", 10, 0, 60, v -> this.mode.is(Phase.Mode.Pearl));
   private final Setting<Float> pitch = new Setting<>("Pitch", 85.0F, 0.0F, 90.0F, v -> this.mode.is(Phase.Mode.Pearl));
   private final Setting<Integer> delay = new Setting<>("Delay", 2, 0, 20, v -> this.mode.is(Phase.Mode.Pearl));
   public int afterPearlTime;
   private int throwDelay;
   private boolean thrown;

   public Phase() {
      super("Phase", Module.Category.MOVEMENT);
   }

   @EventHandler
   public void onCollide(EventCollision e) {
      if (!fullNullCheck()) {
         class_2338 playerPos = class_2338.method_49638(mc.field_1724.method_19538());
         if ((this.mode.is(Phase.Mode.Vanilla) || this.afterPearlTime > 0)
            && (!e.getPos().equals(playerPos.method_10074()) || mc.field_1690.field_1832.method_1434())) {
            e.setState(class_2246.field_10124.method_9564());
         }
      }
   }

   @Override
   public void onEnable() {
      this.afterPearlTime = 0;
      this.throwDelay = 0;
      this.thrown = false;
   }

   @EventHandler
   public void onSync(EventSync e) {
      if (!fullNullCheck()) {
         if (this.afterPearlTime > 0) {
            this.afterPearlTime--;
         }

         if (this.throwDelay > 0) {
            this.throwDelay--;
         }

         if (this.mode.is(Phase.Mode.Pearl)
            && (mc.field_1724.method_24828() || !this.onlyOnGround.getValue())
            && mc.field_1724.field_5976
            && !this.playerInsideBlock()
            && this.throwDelay <= 0
            && mc.field_1724.field_6012 > 60) {
            if (mc.field_1690.field_1832.method_1434()) {
               return;
            }

            double[] dir = MovementUtility.forward(0.5);
            class_2338 block = class_2338.method_49637(
               mc.field_1724.method_23317() + dir[0], mc.field_1724.method_23318(), mc.field_1724.method_23321() + dir[1]
            );
            float[] angle = InteractionUtility.calculateAngle(block.method_46558());
            int epSlot = this.findEPSlot();
            if (epSlot != -1 && !this.thrown) {
               ModuleManager.autoCrystal.pause();
               ModuleManager.aura.pause();
               mc.field_1724.method_36456(angle[0]);
               mc.field_1724.method_36457(this.pitch.getValue());
               this.thrown = true;
            }
         }
      }
   }

   @EventHandler
   public void onPostSync(EventPostSync e) {
      if (this.mode.is(Phase.Mode.Pearl)
         && (mc.field_1724.method_24828() || !this.onlyOnGround.getValue())
         && mc.field_1724.field_5976
         && !this.playerInsideBlock()
         && this.throwDelay <= 0
         && mc.field_1724.field_6012 > 60) {
         if (mc.field_1690.field_1832.method_1434()) {
            return;
         }

         int epSlot = this.findEPSlot();
         int prevItem = mc.field_1724.method_31548().field_7545;
         if (epSlot != -1 && this.thrown) {
            InventoryUtility.switchTo(epSlot);
            this.sendSequencedPacket(id -> new class_2886(class_1268.field_5808, id, mc.field_1724.method_36454(), mc.field_1724.method_36455()));
            this.sendPacket(new class_2879(class_1268.field_5808));
            InventoryUtility.switchTo(prevItem);
            this.throwDelay = this.delay.getValue();
            this.afterPearlTime = this.afterPearl.getValue();
            this.thrown = false;
            if (this.autoDisable.getValue()) {
               this.disable();
            }
         }
      }
   }

   private int findEPSlot() {
      if (mc.field_1724.method_6047().method_7909() == class_1802.field_8634) {
         return mc.field_1724.method_31548().field_7545;
      } else {
         for (int l = 0; l < 9; l++) {
            if (mc.field_1724.method_31548().method_5438(l).method_7909() == class_1802.field_8634) {
               return l;
            }
         }

         return -1;
      }
   }

   public boolean playerInsideBlock() {
      return !mc.field_1687.method_22347(class_2338.method_49638(mc.field_1724.method_19538()));
   }

   private static enum Mode {
      Vanilla,
      Pearl;
   }
}
