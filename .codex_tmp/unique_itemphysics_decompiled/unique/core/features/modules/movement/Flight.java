package unique.core.features.modules.movement;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_124;
import net.minecraft.class_2846;
import net.minecraft.class_495;
import net.minecraft.class_2846.class_2847;
import unique.core.events.impl.EventSync;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.ClientSettings;
import unique.core.setting.Setting;
import unique.core.utility.player.MovementUtility;

public class Flight extends Module {
   private final Setting<Flight.Mode> mode = new Setting<>("Mode", Flight.Mode.Vanilla);
   private final Setting<Float> hSpeed = new Setting<>(
      "Horizontal", 1.0F, 0.0F, 10.0F, v -> this.mode.is(Flight.Mode.Vanilla) || this.mode.is(Flight.Mode.Shulker)
   );
   private final Setting<Float> vSpeed = new Setting<>("Vertical", 0.78F, 0.0F, 5.0F, v -> this.mode.is(Flight.Mode.Vanilla));
   private final Setting<Float> boostValue = new Setting<>("Boost", 1.0F, 0.1F, 1.0F, v -> this.mode.is(Flight.Mode.StormBreak));
   private final Setting<Integer> height = new Setting<>("Height", 5, 0, 50, v -> this.mode.is(Flight.Mode.Shulker));
   private final Setting<Boolean> antiKick = new Setting<>("AntiKick", false, v -> this.mode.is(Flight.Mode.Vanilla));

   public Flight() {
      super("Flight", Module.Category.MOVEMENT);
   }

   @EventHandler
   public void onEventSync(EventSync event) {
      switch ((Flight.Mode)this.mode.getValue()) {
         case Vanilla:
            if (MovementUtility.isMoving()) {
               double[] dir = MovementUtility.forward(this.hSpeed.getValue().floatValue());
               mc.field_1724.method_18800(dir[0], 0.0, dir[1]);
            } else {
               mc.field_1724.method_18800(0.0, 0.0, 0.0);
            }

            if (mc.field_1690.field_1903.method_1434()) {
               mc.field_1724.method_18799(mc.field_1724.method_18798().method_1031(0.0, this.vSpeed.getValue().floatValue(), 0.0));
            }

            if (mc.field_1690.field_1832.method_1434()) {
               mc.field_1724.method_18799(mc.field_1724.method_18798().method_1031(0.0, -this.vSpeed.getValue(), 0.0));
            }
            break;
         case AirJump:
            if (MovementUtility.isMoving()
               && mc.field_1687
                  .method_20812(mc.field_1724, mc.field_1724.method_5829().method_1009(0.5, 0.0, 0.5).method_989(0.0, -1.0, 0.0))
                  .iterator()
                  .hasNext()) {
               mc.field_1724.method_24830(true);
               mc.field_1724.method_6043();
            }
            break;
         case StormBreak:
            if (mc.field_1724.field_6012 % 60 == 0) {
               this.sendMessage(
                  class_124.field_1061 + (ClientSettings.isRu() ? "В этом режиме нужно ломать blocks!" : "In This Mode You Need To Break Blocks!")
               );
            }
            break;
         case Shulker:
            if (MovementUtility.isMoving()) {
               if (mc.field_1755 instanceof class_495) {
                  double[] dir = MovementUtility.forward(this.hSpeed.getValue().floatValue());
                  mc.field_1724.method_18800(dir[0], this.height.getValue().intValue(), dir[1]);
               }
            } else {
               mc.field_1724.method_18800(0.0, 0.0, 0.0);
            }
      }

      if (this.antiKick.getValue() && this.mode.is(Flight.Mode.Vanilla)) {
         mc.field_1724.method_18799(mc.field_1724.method_18798().method_1031(0.0, -0.08, 0.0));
      }
   }

   @EventHandler
   public void onPacketSend(PacketEvent.Send e) {
      if (this.mode.is(Flight.Mode.StormBreak)
         && e.getPacket() instanceof class_2846 pac
         && (
            pac.method_12363() == class_2847.field_12973
               || pac.method_12363() == class_2847.field_12968 && mc.field_1687.method_8320(pac.method_12362()).method_45474()
         )) {
         double[] dir = MovementUtility.forward(2.0F * this.boostValue.getValue());
         mc.field_1724.method_18800(dir[0], 3.0F * this.boostValue.getValue(), dir[1]);
      }
   }

   @Override
   public void onDisable() {
      mc.field_1724.method_31549().field_7479 = false;
      mc.field_1724.method_31549().method_7248(0.05F);
   }

   private static enum Mode {
      Vanilla,
      AirJump,
      StormBreak,
      Shulker;
   }
}
