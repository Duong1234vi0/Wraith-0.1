package unique.core.features.modules.movement;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1294;
import net.minecraft.class_1713;
import net.minecraft.class_238;
import net.minecraft.class_2708;
import net.minecraft.class_2743;
import net.minecraft.class_2848;
import net.minecraft.class_2338.class_2339;
import net.minecraft.class_2350.class_2351;
import net.minecraft.class_2848.class_2849;
import unique.core.core.Core;
import unique.core.core.manager.client.ModuleManager;
import unique.core.events.impl.EventMove;
import unique.core.events.impl.EventSprint;
import unique.core.events.impl.EventSync;
import unique.core.events.impl.PacketEvent;
import unique.core.events.impl.PlayerUpdateEvent;
import unique.core.features.modules.Module;
import unique.core.injection.accesors.ISPacketEntityVelocity;
import unique.core.setting.Setting;
import unique.core.utility.player.InventoryUtility;
import unique.core.utility.player.MovementUtility;

public class Strafe extends Module {
   private final Setting<Strafe.Boost> boost = new Setting<>("Boost", Strafe.Boost.None);
   private final Setting<Float> setSpeed = new Setting<>("speed", 1.3F, 0.0F, 2.0F, v -> this.boost.getValue() == Strafe.Boost.Elytra);
   private final Setting<Float> velReduction = new Setting<>("Reduction", 6.0F, 0.1F, 10.0F, v -> this.boost.getValue() == Strafe.Boost.Damage);
   private final Setting<Float> maxVelocitySpeed = new Setting<>("MaxVelocity", 0.8F, 0.1F, 2.0F, v -> this.boost.getValue() == Strafe.Boost.Damage);
   private final Setting<Boolean> sunrise = new Setting<>("Sunrise", true, v -> this.boost.getValue() == Strafe.Boost.Elytra);
   public static double oldSpeed;
   public static double contextFriction;
   public static double fovval;
   public static boolean needSwap;
   public static boolean needSprintState;
   public static boolean disabled;
   public static int noSlowTicks;
   static long disableTime;

   public Strafe() {
      super("Strafe", Module.Category.MOVEMENT);
   }

   public double calculateSpeed(EventMove move) {
      float speedAttributes = this.getAIMoveSpeed();
      float frictionFactor = mc.field_1687
            .method_8320(
               new class_2339()
                  .method_10102(
                     mc.field_1724.method_23317(), this.getBoundingBox().method_1001(class_2351.field_11052) - move.getY(), mc.field_1724.method_23321()
                  )
            )
            .method_26204()
            .method_9499()
         * 0.91F;
      float n6 = mc.field_1724.method_6059(class_1294.field_5913) && mc.field_1724.method_6115()
         ? 0.88F
         : (float)(oldSpeed > 0.32 && mc.field_1724.method_6115() ? 0.88 : 0.91F);
      if (mc.field_1724.method_24828()) {
         n6 = frictionFactor;
      }

      float n7 = (float)(0.1631F / Math.pow(n6, 3.0));
      float n8;
      if (mc.field_1724.method_24828()) {
         n8 = speedAttributes * n7;
         if (move.getY() > 0.0) {
            n8 += this.boost.getValue() == Strafe.Boost.Elytra
                  && InventoryUtility.getElytra() != -1
                  && disabled
                  && System.currentTimeMillis() - disableTime < 300L
               ? 0.65F
               : 0.2F;
         }

         disabled = false;
      } else {
         n8 = 0.0255F;
      }

      boolean noslow = false;
      double max2 = oldSpeed + n8;
      double max = 0.0;
      if (mc.field_1724.method_6115() && move.getY() <= 0.0 && !this.sunrise.getValue()) {
         double n10 = oldSpeed + n8 * 0.25;
         double motionY2 = move.getY();
         if (motionY2 != 0.0 && Math.abs(motionY2) < 0.08) {
            n10 += 0.055;
         }

         if (max2 > (max = Math.max(0.043, n10))) {
            noslow = true;
            noSlowTicks++;
         } else {
            noSlowTicks = Math.max(noSlowTicks - 1, 0);
         }
      } else {
         noSlowTicks = 0;
      }

      if (noSlowTicks > 3) {
         max2 = max - 0.019;
      } else {
         max2 = Math.max(noslow ? 0.0 : 0.25, max2) - (mc.field_1724.field_6012 % 2 == 0 ? 0.001 : 0.002);
      }

      contextFriction = n6;
      if (!mc.field_1724.method_24828()) {
         needSprintState = !mc.field_1724.field_3919;
         needSwap = true;
      } else {
         needSprintState = false;
      }

      return max2;
   }

   public float getAIMoveSpeed() {
      boolean prevSprinting = mc.field_1724.method_5624();
      mc.field_1724.method_5728(false);
      float speed = mc.field_1724.method_6029() * 1.3F;
      mc.field_1724.method_5728(prevSprinting);
      return speed;
   }

   public static void disabler(int elytra) {
      if (elytra != -1) {
         if (System.currentTimeMillis() - disableTime > 190L) {
            if (elytra != -2) {
               mc.field_1761.method_2906(0, elytra, 1, class_1713.field_7790, mc.field_1724);
               mc.field_1761.method_2906(0, 6, 1, class_1713.field_7790, mc.field_1724);
            }

            mc.field_1724.field_3944.method_52787(new class_2848(mc.field_1724, class_2849.field_12982));
            mc.field_1724.field_3944.method_52787(new class_2848(mc.field_1724, class_2849.field_12982));
            if (elytra != -2) {
               mc.field_1761.method_2906(0, 6, 1, class_1713.field_7790, mc.field_1724);
               mc.field_1761.method_2906(0, elytra, 1, class_1713.field_7790, mc.field_1724);
            }

            disableTime = System.currentTimeMillis();
         }

         disabled = true;
      }
   }

   @Override
   public void onEnable() {
      oldSpeed = 0.0;
      fovval = (Double)mc.field_1690.method_42454().method_41753();
      mc.field_1690.method_42454().method_41748(0.0);
   }

   @Override
   public void onDisable() {
      mc.field_1690.method_42454().method_41748(fovval);
   }

   public boolean canStrafe() {
      if (mc.field_1724.method_5715()) {
         return false;
      } else if (mc.field_1724.method_5771()) {
         return false;
      } else if (ModuleManager.scaffold.isEnabled()) {
         return false;
      } else if (ModuleManager.speed.isEnabled()) {
         return false;
      } else {
         return mc.field_1724.method_5869() ? false : !mc.field_1724.method_31549().field_7479;
      }
   }

   public class_238 getBoundingBox() {
      return new class_238(
         mc.field_1724.method_23317() - 0.1,
         mc.field_1724.method_23318(),
         mc.field_1724.method_23321() - 0.1,
         mc.field_1724.method_23317() + 0.1,
         mc.field_1724.method_23318() + 1.0,
         mc.field_1724.method_23321() + 0.1
      );
   }

   @EventHandler
   public void onMove(EventMove event) {
      int elytraSlot = InventoryUtility.getElytra();
      if (this.boost.getValue() == Strafe.Boost.Elytra
         && elytraSlot != -1
         && MovementUtility.isMoving()
         && !mc.field_1724.method_24828()
         && mc.field_1687.method_20812(mc.field_1724, mc.field_1724.method_5829().method_989(0.0, event.getY(), 0.0)).iterator().hasNext()
         && disabled) {
         oldSpeed = this.setSpeed.getValue().floatValue();
      }

      if (this.canStrafe()) {
         if (MovementUtility.isMoving()) {
            double[] motions = MovementUtility.forward(this.calculateSpeed(event));
            event.setX(motions[0]);
            event.setZ(motions[1]);
         } else {
            oldSpeed = 0.0;
            event.setX(0.0);
            event.setZ(0.0);
         }

         event.cancel();
      } else {
         oldSpeed = 0.0;
      }
   }

   @EventHandler
   public void onSync(EventSync e) {
      oldSpeed = Math.hypot(mc.field_1724.method_23317() - mc.field_1724.field_6014, mc.field_1724.method_23321() - mc.field_1724.field_5969) * contextFriction;
   }

   @EventHandler
   public void onPacketReceive(PacketEvent.Receive e) {
      if (e.getPacket() instanceof class_2708) {
         oldSpeed = 0.0;
      }

      class_2743 velocity;
      if (e.getPacket() instanceof class_2743
         && (velocity = e.getPacket()).method_11818() == mc.field_1724.method_5628()
         && this.boost.getValue() == Strafe.Boost.Damage) {
         if (mc.field_1724.method_24828()) {
            return;
         }

         double vX = velocity.method_11815();
         double vZ = velocity.method_11819();
         if (vX < 0.0) {
            vX *= -1.0;
         }

         if (vZ < 0.0) {
            vZ *= -1.0;
         }

         oldSpeed = (vX + vZ) / (this.velReduction.getValue() * 1000.0F);
         oldSpeed = Math.min(oldSpeed, (double)this.maxVelocitySpeed.getValue().floatValue());
         ((ISPacketEntityVelocity)velocity).setMotionX(0);
         ((ISPacketEntityVelocity)velocity).setMotionY(0);
         ((ISPacketEntityVelocity)velocity).setMotionZ(0);
      }
   }

   @EventHandler
   public void actionEvent(EventSprint eventAction) {
      if (this.canStrafe() && Core.serverSprint != needSprintState) {
         eventAction.setSprintState(!Core.serverSprint);
      }

      if (needSwap) {
         eventAction.setSprintState(!mc.field_1724.field_3919);
         needSwap = false;
      }
   }

   @EventHandler
   public void onUpdate(PlayerUpdateEvent event) {
      if (this.boost.getValue() == Strafe.Boost.Elytra
         && InventoryUtility.getElytra() != -1
         && !mc.field_1724.method_24828()
         && mc.field_1724.field_6017 > 0.0F
         && !disabled
         && (
            !mc.field_1687.method_20812(mc.field_1724, mc.field_1724.method_5829().method_989(0.0, -1.1F, 0.0)).iterator().hasNext()
               || !this.sunrise.getValue()
         )) {
         disabler(InventoryUtility.getElytra());
      }
   }

   private static enum Boost {
      None,
      Elytra,
      Damage;
   }
}
