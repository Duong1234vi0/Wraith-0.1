package unique.core.features.modules.combat;

import io.netty.buffer.Unpooled;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1511;
import net.minecraft.class_2540;
import net.minecraft.class_2824;
import net.minecraft.class_2828.class_2829;
import net.minecraft.class_2828.class_2830;
import org.jetbrains.annotations.NotNull;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.injection.accesors.IClientPlayerEntity;
import unique.core.setting.Setting;

public final class Criticals extends Module {
   public final Setting<Criticals.Mode> mode = new Setting<>("Mode", Criticals.Mode.UpdatedNCP);
   public static boolean cancelCrit;
   private boolean waitingForJumpCrit = false;

   public Criticals() {
      super("Criticals", Module.Category.COMBAT);
   }

   @EventHandler
   public void onPacketSend(@NotNull PacketEvent.Send event) {
      if (event.getPacket() instanceof class_2824 packet) {
         if (getInteractType(packet) == Criticals.InteractType.ATTACK) {
            class_1297 entity = getEntity(packet);
            if (entity instanceof class_1309 && !(entity instanceof class_1511) && !cancelCrit) {
               if (this.mode.getValue() == Criticals.Mode.CriticalsOnly) {
                  if (!mc.field_1724.method_24828()
                     && mc.field_1724.field_6017 > 0.0F
                     && !mc.field_1724.method_5771()
                     && !mc.field_1724.method_5869()
                     && !mc.field_1724.method_31549().field_7479) {
                     this.doHVHCrit();
                  }
               } else if (this.mode.getValue() != Criticals.Mode.Jump) {
                  this.doCrit();
               } else if (mc.field_1724.method_24828()) {
                  mc.field_1724.method_6043();
                  this.waitingForJumpCrit = true;
                  event.cancel();
               } else if (this.waitingForJumpCrit && mc.field_1724.field_6017 <= 0.0F) {
                  event.cancel();
               } else {
                  if (mc.field_1724.field_6017 > 0.0F) {
                     this.waitingForJumpCrit = false;
                  }
               }
            }
         }
      }
   }

   public boolean doCrit() {
      if (!this.isDisabled() && mc.field_1724 != null && mc.field_1687 != null) {
         switch ((Criticals.Mode)this.mode.getValue()) {
            case UpdatedNCP:
               this.doUpdatedNcpCrit();
               break;
            case Jump:
               return true;
            case Grim:
               this.doGrimCrit();
               break;
            case HVH:
               this.doHVHCrit();
               break;
            case CriticalsOnly:
               return this.isInCritState();
         }

         return true;
      } else {
         return false;
      }
   }

   private void doUpdatedNcpCrit() {
      if (mc.field_1724.method_24828() && !mc.field_1724.method_5771() && !mc.field_1724.method_5869() && !mc.field_1724.method_31549().field_7479) {
         this.sendPacket(new class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 2.71875E-7, mc.field_1724.method_23321(), false));
         this.sendPacket(new class_2829(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321(), false));
      }
   }

   private void doGrimCrit() {
      if (!mc.field_1724.method_24828() && !mc.field_1724.method_5771() && !mc.field_1724.method_5869() && !mc.field_1724.method_31549().field_7479) {
         this.sendPacket(
            new class_2830(
               mc.field_1724.method_23317(),
               mc.field_1724.method_23318() - 1.0E-6,
               mc.field_1724.method_23321(),
               ((IClientPlayerEntity)mc.field_1724).getLastYaw(),
               ((IClientPlayerEntity)mc.field_1724).getLastPitch(),
               false
            )
         );
      }
   }

   private void doHVHCrit() {
      if (!mc.field_1724.method_5771() && !mc.field_1724.method_5869() && !mc.field_1724.method_31549().field_7479) {
         double x = mc.field_1724.method_23317();
         double y = mc.field_1724.method_23318();
         double z = mc.field_1724.method_23321();
         this.sendPacket(new class_2829(x, y + 0.0625, z, false));
         this.sendPacket(new class_2829(x, y, z, false));
      }
   }

   private boolean isInCritState() {
      return !mc.field_1724.method_24828()
         && mc.field_1724.field_6017 > 0.0F
         && !mc.field_1724.method_5771()
         && !mc.field_1724.method_5869()
         && !mc.field_1724.method_31549().field_7479;
   }

   public static class_1297 getEntity(@NotNull class_2824 packet) {
      class_2540 buf = new class_2540(Unpooled.buffer());
      packet.method_55976(buf);
      return mc.field_1687.method_8469(buf.method_10816());
   }

   public static Criticals.InteractType getInteractType(@NotNull class_2824 packet) {
      class_2540 buf = new class_2540(Unpooled.buffer());
      packet.method_55976(buf);
      buf.method_10816();
      return (Criticals.InteractType)buf.method_10818(Criticals.InteractType.class);
   }

   public static enum InteractType {
      INTERACT,
      ATTACK,
      INTERACT_AT;
   }

   public static enum Mode {
      UpdatedNCP,
      Jump,
      Grim,
      HVH,
      CriticalsOnly;
   }
}
