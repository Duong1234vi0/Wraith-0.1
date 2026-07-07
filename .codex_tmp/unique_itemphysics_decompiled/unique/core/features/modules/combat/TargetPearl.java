package unique.core.features.modules.combat;

import java.util.Comparator;
import java.util.HashMap;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1667;
import net.minecraft.class_1684;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2868;
import net.minecraft.class_2879;
import net.minecraft.class_2886;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.jetbrains.annotations.NotNull;
import unique.core.core.manager.client.ModuleManager;
import unique.core.events.impl.EventEntitySpawn;
import unique.core.events.impl.EventPostSync;
import unique.core.events.impl.EventSync;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.ClientSettings;
import unique.core.setting.Setting;
import unique.core.setting.impl.BooleanSettingGroup;
import unique.core.utility.Timer;
import unique.core.utility.math.MathUtility;
import unique.core.utility.player.MovementUtility;

public class TargetPearl extends Module {
   private final Setting<BooleanSettingGroup> stopMotion = new Setting<>("StopMotion", new BooleanSettingGroup(false));
   private final Setting<Boolean> legitStop = new Setting<>("LegitStop", false).addToGroup(this.stopMotion);
   private final Setting<Boolean> pauseAura = new Setting<>("PauseAura", false);
   private final Setting<Boolean> onlyOnGround = new Setting<>("OnlyOnGround", false);
   private final Setting<Boolean> noMove = new Setting<>("NoMove", false);
   private final Setting<Boolean> onlyTarget = new Setting<>("OnlyTarget", false);
   private final Setting<Boolean> auraOnly = new Setting<>("AuraOnly", false);
   private final Setting<Boolean> disableMessenger = new Setting<>("DisableMessenger", false);
   private Runnable postSyncAction;
   private final Timer delayTimer = new Timer();
   private class_2338 targetBlock;
   private int lastPearlId;
   private int lastOurPearlId;
   private HashMap<class_1657, Long> targets = new HashMap<>();
   private HashMap<Integer, class_1657> pearlOwners = new HashMap<>();
   private HashMap<class_1657, class_243> lastPlayerPositions = new HashMap<>();
   private HashMap<class_1657, class_243> playerVelocities = new HashMap<>();

   public TargetPearl() {
      super("TargetPearl", Module.Category.MISC);
   }

   @EventHandler
   public void onEntitySpawn(EventEntitySpawn e) {
      if (e.getEntity() instanceof class_1684) {
         mc.field_1687.method_18456().stream().min(Comparator.comparingDouble(p -> p.method_5707(e.getEntity().method_19538()))).ifPresent(player -> {
            if (player.equals(mc.field_1724)) {
               this.lastOurPearlId = e.getEntity().method_5628();
            } else {
               this.pearlOwners.put(e.getEntity().method_5628(), player);
            }
         });
      }
   }

   @EventHandler(
      priority = -100
   )
   public void onSync(EventSync event) {
      this.updatePlayerVelocities();
      if (this.onlyTarget.getValue()) {
         if (Aura.target != null && ModuleManager.aura.isEnabled() && Aura.target instanceof class_1657 pl && !this.targets.containsKey(pl)) {
            this.targets.put(pl, System.currentTimeMillis());
         }

         if (AutoCrystal.target != null && ModuleManager.autoCrystal.isEnabled()) {
            class_1657 var10 = AutoCrystal.target;
            if (var10 instanceof class_1657 && !this.targets.containsKey(var10)) {
               this.targets.put(var10, System.currentTimeMillis());
            }
         }

         new HashMap<>(this.targets).forEach((k, v) -> {
            if (System.currentTimeMillis() - v > 10000L) {
               this.targets.remove(k);
            }
         });
      }

      if (!(mc.field_1724.method_6032() < 5.0F)) {
         if (this.delayTimer.passedMs(1000L)) {
            for (class_1297 ent : mc.field_1687.method_18112()) {
               if (ent instanceof class_1684 && ent.method_5628() != this.lastPearlId && ent.method_5628() != this.lastOurPearlId) {
                  class_1657 pearlOwner = this.pearlOwners.get(ent.method_5628());
                  if (pearlOwner != null) {
                     boolean shouldTarget = false;
                     if (this.auraOnly.getValue()) {
                        shouldTarget = Aura.target != null && pearlOwner.equals(Aura.target);
                     } else {
                        shouldTarget = this.targets.containsKey(pearlOwner) || !this.onlyTarget.getValue();
                     }

                     if (shouldTarget && !pearlOwner.equals(mc.field_1724)) {
                        class_2338 predictedLanding = this.calcTrajectory(ent);
                        if (predictedLanding != null) {
                           class_2338 finalTarget = this.predictPearlInterception(ent, predictedLanding, pearlOwner);
                           if (finalTarget != null) {
                              this.targetBlock = finalTarget;
                              this.lastPearlId = ent.method_5628();
                           }
                        }
                     }
                  }
               }
            }

            if (this.targetBlock != null) {
               if (!(mc.field_1724.method_5707(this.targetBlock.method_46558()) < 49.0)) {
                  float rotationPitch = (float)(-Math.toDegrees(this.calcTrajectory(this.targetBlock)));
                  float rotationYaw = (float)Math.toDegrees(
                        Math.atan2(
                           this.targetBlock.method_10260() + 0.5F - mc.field_1724.method_23321(),
                           this.targetBlock.method_10263() + 0.5F - mc.field_1724.method_23317()
                        )
                     )
                     - 90.0F;
                  class_2338 tracedBP = this.checkTrajectory(rotationYaw, rotationPitch);
                  if (tracedBP != null && !(this.targetBlock.method_19770(tracedBP.method_46558()) > 36.0)) {
                     if (this.pauseAura.getValue() && ModuleManager.aura.isEnabled()) {
                        ModuleManager.aura.pause();
                     }

                     if (!this.onlyOnGround.getValue() || mc.field_1724.method_24828()) {
                        if (!this.noMove.getValue() || !MovementUtility.isMoving()) {
                           if (this.stopMotion.getValue().isEnabled()) {
                              if (!this.legitStop.getValue()) {
                                 mc.field_1724.method_18800(0.0, 0.0, 0.0);
                              }

                              mc.field_1690.field_1894.method_23481(false);
                              mc.field_1690.field_1881.method_23481(false);
                              mc.field_1690.field_1913.method_23481(false);
                              mc.field_1690.field_1849.method_23481(false);
                              mc.field_1724.field_3913.field_3905 = 0.0F;
                              mc.field_1724.field_3913.field_3907 = 0.0F;
                           } else {
                              if (!this.disableMessenger.getValue()) {
                                 this.sendMessage(
                                    ClientSettings.isRu()
                                       ? "Догоняем перл! Позиция X:"
                                          + tracedBP.method_10263()
                                          + " Y:"
                                          + tracedBP.method_10264()
                                          + " Z:"
                                          + tracedBP.method_10260()
                                          + " Углы Y:"
                                          + rotationYaw
                                          + " P:"
                                          + rotationPitch
                                       : "Chasing Pearl On X:"
                                          + tracedBP.method_10263()
                                          + " Y:"
                                          + tracedBP.method_10264()
                                          + " Z:"
                                          + tracedBP.method_10260()
                                          + " Angle Y:"
                                          + rotationYaw
                                          + " P:"
                                          + rotationPitch
                                 );
                              }

                              mc.field_1724.method_36456(rotationYaw);
                              mc.field_1724.method_36457(MathUtility.clamp(rotationPitch, -89.0F, 89.0F));
                              float yaw = mc.field_1724.method_36454();
                              float pitch = mc.field_1724.method_36455();
                              this.postSyncAction = () -> {
                                 int epSlot = this.findEPSlot();
                                 int originalSlot = mc.field_1724.method_31548().field_7545;
                                 if (epSlot != -1) {
                                    mc.field_1724.method_31548().field_7545 = epSlot;
                                    this.sendPacket(new class_2868(epSlot));
                                    this.sendSequencedPacket(id -> new class_2886(class_1268.field_5808, id, yaw, pitch));
                                    this.sendPacket(new class_2879(class_1268.field_5808));
                                    mc.field_1724.method_31548().field_7545 = originalSlot;
                                    this.sendPacket(new class_2868(originalSlot));
                                 }
                              };
                              this.targetBlock = null;
                              this.delayTimer.reset();
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @EventHandler
   public void onPostSync(EventPostSync event) {
      if (this.postSyncAction != null) {
         this.postSyncAction.run();
         this.postSyncAction = null;
      }
   }

   private void updatePlayerVelocities() {
      for (class_1657 player : mc.field_1687.method_18456()) {
         if (!player.equals(mc.field_1724)) {
            class_243 currentPos = player.method_19538();
            class_243 lastPos = this.lastPlayerPositions.get(player);
            if (lastPos != null) {
               class_243 velocity = currentPos.method_1020(lastPos);
               this.playerVelocities.put(player, velocity);
            }

            this.lastPlayerPositions.put(player, currentPos);
         }
      }
   }

   private class_2338 predictPearlInterception(class_1297 pearl, class_2338 pearlLanding, class_1657 owner) {
      if (pearlLanding == null) {
         return null;
      } else {
         class_243 ownerVelocity = this.playerVelocities.getOrDefault(owner, class_243.field_1353);
         double pearlTravelTime = this.estimatePearlTravelTime(pearl, pearlLanding);
         class_243 predictedOwnerPos = owner.method_19538().method_1019(ownerVelocity.method_1021(pearlTravelTime));
         class_243 pearlLandingVec = pearlLanding.method_46558();
         class_243 directionToPearl = pearlLandingVec.method_1020(predictedOwnerPos).method_1029();
         double optimalDistance = 3.5;
         class_243 interceptionPoint = pearlLandingVec.method_1019(directionToPearl.method_1021(optimalDistance));
         class_2338 interceptionBlock = class_2338.method_49638(interceptionPoint);
         class_2338 groundedBlock = this.findGroundBelow(interceptionBlock);
         return groundedBlock != null ? groundedBlock : pearlLanding;
      }
   }

   private double estimatePearlTravelTime(class_1297 pearl, class_2338 landing) {
      double distance = pearl.method_19538().method_1022(landing.method_46558());
      double averageSpeed = 1.5;
      return distance / averageSpeed;
   }

   private class_2338 findGroundBelow(class_2338 startPos) {
      for (int i = 0; i < 10; i++) {
         class_2338 checkPos = startPos.method_10087(i);
         if (!mc.field_1687.method_8320(checkPos).method_26215() && mc.field_1687.method_8320(checkPos.method_10084()).method_26215()) {
            return checkPos.method_10084();
         }
      }

      return null;
   }

   private int findEPSlot() {
      int epSlot = -1;
      if (mc.field_1724.method_6047().method_7909() == class_1802.field_8634) {
         epSlot = mc.field_1724.method_31548().field_7545;
      }

      if (epSlot == -1) {
         for (int l = 0; l < 9; l++) {
            if (mc.field_1724.method_31548().method_5438(l).method_7909() == class_1802.field_8634) {
               epSlot = l;
               break;
            }
         }
      }

      return epSlot;
   }

   private float calcTrajectory(@NotNull class_2338 bp) {
      double a = Math.hypot(bp.method_10263() + 0.5F - mc.field_1724.method_23317(), bp.method_10260() + 0.5F - mc.field_1724.method_23321());
      double y = 6.125 * (bp.method_10264() + 1.0F - (mc.field_1724.method_23318() + mc.field_1724.method_18381(mc.field_1724.method_18376())));
      y = 0.05F * (0.05F * (a * a) + y);
      y = Math.sqrt(9.378906F - y);
      double d = 3.0625 - y;
      y = Math.atan2(d * d + y, 0.05F * a);
      d = Math.atan2(d, 0.05F * a);
      return (float)Math.min(y, d);
   }

   private class_2338 calcTrajectory(class_1297 e) {
      return this.traceTrajectory(
         e.method_23317(), e.method_23318(), e.method_23321(), e.method_18798().field_1352, e.method_18798().field_1351, e.method_18798().field_1350
      );
   }

   private class_2338 checkTrajectory(float yaw, float pitch) {
      if (Float.isNaN(pitch)) {
         return null;
      } else {
         float yawRad = yaw / 180.0F * (float) Math.PI;
         float pitchRad = pitch / 180.0F * (float) Math.PI;
         double x = mc.field_1724.method_23317() - class_3532.method_15362(yawRad) * 0.16F;
         double y = mc.field_1724.method_23318() + mc.field_1724.method_18381(mc.field_1724.method_18376()) - 0.1000000014901161;
         double z = mc.field_1724.method_23321() - class_3532.method_15374(yawRad) * 0.16F;
         double motionX = -class_3532.method_15374(yawRad) * class_3532.method_15362(pitchRad) * 0.4F;
         double motionY = -class_3532.method_15374(pitchRad) * 0.4F;
         double motionZ = class_3532.method_15362(yawRad) * class_3532.method_15362(pitchRad) * 0.4F;
         float distance = class_3532.method_15355((float)(motionX * motionX + motionY * motionY + motionZ * motionZ));
         motionX /= distance;
         motionY /= distance;
         motionZ /= distance;
         motionX *= 1.5;
         motionY *= 1.5;
         motionZ *= 1.5;
         if (!mc.field_1724.method_24828()) {
            motionY += mc.field_1724.method_18798().method_10214();
         }

         return this.traceTrajectory(x, y, z, motionX, motionY, motionZ);
      }
   }

   private class_2338 traceTrajectory(double x, double y, double z, double mx, double my, double mz) {
      for (int i = 0; i < 300; i++) {
         class_243 lastPos = new class_243(x, y, z);
         x += mx;
         y += my;
         z += mz;
         mx *= 0.99;
         my *= 0.99;
         mz *= 0.99;
         my -= 0.03F;
         class_243 pos = new class_243(x, y, z);
         class_3965 bhr = mc.field_1687.method_17742(new class_3959(lastPos, pos, class_3960.field_17559, class_242.field_1348, mc.field_1724));
         if (bhr != null && bhr.method_17783() == class_240.field_1332) {
            return bhr.method_17777();
         }

         for (class_1297 ent : mc.field_1687.method_18112()) {
            if (!(ent instanceof class_1667)
               && ent != mc.field_1724
               && !(ent instanceof class_1684)
               && ent.method_5829().method_994(new class_238(x - 0.3, y - 0.3, z - 0.3, x + 0.3, y + 0.3, z + 0.2))) {
               return null;
            }
         }

         if (y <= -65.0) {
            break;
         }
      }

      return null;
   }
}
