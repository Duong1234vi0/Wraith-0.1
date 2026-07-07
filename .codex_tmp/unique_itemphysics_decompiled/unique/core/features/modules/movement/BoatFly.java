package unique.core.features.modules.movement;

import java.util.ArrayList;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1690;
import net.minecraft.class_1713;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2661;
import net.minecraft.class_2684;
import net.minecraft.class_2692;
import net.minecraft.class_2708;
import net.minecraft.class_2740;
import net.minecraft.class_2824;
import net.minecraft.class_2833;
import net.minecraft.class_2851;
import net.minecraft.class_2828.class_2831;
import org.jetbrains.annotations.NotNull;
import unique.core.UniqueCore;
import unique.core.events.impl.EventPlayerTravel;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.setting.impl.SettingGroup;
import unique.core.utility.player.MovementUtility;

public class BoatFly extends Module {
   private final Setting<BoatFly.Mode> mode = new Setting<>("Mode", BoatFly.Mode.Packet);
   private final Setting<Boolean> phase = new Setting<>("Phase", false);
   private final Setting<Boolean> gravity = new Setting<>("Gravity", false);
   private final Setting<Boolean> automount = new Setting<>("AutoMount", true);
   public final Setting<Boolean> allowShift = new Setting<>("AllowShift", true);
   private final Setting<Float> speed = new Setting<>("Speed", 2.0F, 0.0F, 25.0F);
   private final Setting<Float> yspeed = new Setting<>("YSpeed", 1.0F, 0.0F, 10.0F);
   public final Setting<SettingGroup> advanced = new Setting<>("Advanced", new SettingGroup(false, 0));
   private final Setting<Float> glidespeed = new Setting<>("GlideSpeed", 0.0F, 0.0F, 10.0F).addToGroup(this.advanced);
   private final Setting<Boolean> slotClick = new Setting<>("ClickSlot", false).addToGroup(this.advanced);
   private final Setting<Boolean> limit = new Setting<>("Limit", true).addToGroup(this.advanced);
   private final Setting<Boolean> ongroundpacket = new Setting<>("OnGroundPacket", false).addToGroup(this.advanced);
   private final Setting<Boolean> spoofpackets = new Setting<>("SpoofPackets", false).addToGroup(this.advanced);
   private final Setting<Float> jitter = new Setting<>("Jitter", 0.1F, 0.0F, 10.0F, v -> this.spoofpackets.getValue()).addToGroup(this.advanced);
   private final Setting<Boolean> cancelrotations = new Setting<>("CancelRotations", true).addToGroup(this.advanced);
   private final Setting<Boolean> cancel = new Setting<>("Cancel", true).addToGroup(this.advanced);
   private final Setting<Boolean> pause = new Setting<>("Pause", false).addToGroup(this.advanced);
   private final Setting<Integer> enableticks = new Setting<>("EnableTicks", 10, 1, 100, v -> this.pause.getValue()).addToGroup(this.advanced);
   private final Setting<Integer> waitTicks = new Setting<>("WaitTicks", 10, 1, 100, v -> this.pause.getValue()).addToGroup(this.advanced);
   private final Setting<Boolean> stopunloaded = new Setting<>("StopUnloaded", true).addToGroup(this.advanced);
   private final Setting<Float> timer = new Setting<>("Timer", 1.0F, 0.1F, 5.0F).addToGroup(this.advanced);
   public final Setting<Boolean> hideBoat = new Setting<>("HideBoat", true).addToGroup(this.advanced);
   private final ArrayList<class_2833> vehiclePackets = new ArrayList<>();
   private int ticksEnabled = 0;
   private int enableDelay = 0;
   private boolean waitedCooldown = false;
   private boolean returnGravity = false;
   private boolean jitterSwitch = false;

   public BoatFly() {
      super("BoatFly", Module.Category.MOVEMENT);
   }

   @Override
   public void onEnable() {
      if (fullNullCheck()) {
         this.disable();
      } else {
         if (this.automount.getValue()) {
            this.mountToBoat();
         }
      }
   }

   @Override
   public void onDisable() {
      UniqueCore.TICK_TIMER = 1.0F;
      this.vehiclePackets.clear();
      this.waitedCooldown = false;
      if (mc.field_1724 != null) {
         if (this.phase.getValue() && this.mode.getValue() == BoatFly.Mode.Motion) {
            if (mc.field_1724.method_49694() != null) {
               mc.field_1724.method_49694().field_5960 = false;
            }

            mc.field_1724.field_5960 = false;
         }

         if (mc.field_1724.method_49694() != null) {
            mc.field_1724.method_49694().method_5875(false);
         }

         mc.field_1724.method_5875(false);
      }
   }

   private float randomizeYOffset() {
      this.jitterSwitch = !this.jitterSwitch;
      return this.jitterSwitch ? this.jitter.getValue() : -this.jitter.getValue();
   }

   private void sendMovePacket(class_2833 pac) {
      this.vehiclePackets.add(pac);
      this.sendPacket(pac);
   }

   private void teleportToGround(class_1297 boat) {
      class_2338 blockPos = class_2338.method_49638(boat.method_19538());

      for (int i = 0; i < 255; i++) {
         if (!mc.field_1687.method_8320(blockPos).method_45474() || mc.field_1687.method_8320(blockPos).method_26204() == class_2246.field_10382) {
            boat.method_5814(boat.method_23317(), blockPos.method_10264() + 1, boat.method_23321());
            this.sendMovePacket(new class_2833(boat));
            boat.method_5814(boat.method_23317(), boat.method_23318(), boat.method_23321());
            break;
         }

         blockPos = blockPos.method_10074();
      }
   }

   private void mountToBoat() {
      for (class_1297 entity : mc.field_1687.method_18112()) {
         if (entity instanceof class_1690 && !(mc.field_1724.method_5858(entity) > 25.0)) {
            this.sendPacket(class_2824.method_34207(entity, false, class_1268.field_5808));
            break;
         }
      }
   }

   @EventHandler
   public void onPlayerTravel(@NotNull EventPlayerTravel ev) {
      if (ev.isPre()) {
         if (!fullNullCheck()) {
            if (mc.field_1724.method_49694() == null) {
               if (this.automount.getValue()) {
                  this.mountToBoat();
               }
            } else {
               if (this.phase.getValue() && this.mode.getValue() == BoatFly.Mode.Motion) {
                  mc.field_1724.method_49694().field_5960 = true;
                  mc.field_1724.method_49694().method_5875(true);
                  mc.field_1724.field_5960 = true;
               }

               if (!this.returnGravity) {
                  mc.field_1724.method_49694().method_5875(!this.gravity.getValue());
                  mc.field_1724.method_5875(!this.gravity.getValue());
               }

               if (this.pause.getValue()) {
                  if (this.ticksEnabled > this.enableticks.getValue() && !this.waitedCooldown) {
                     this.ticksEnabled = 0;
                     this.waitedCooldown = true;
                     this.enableDelay = this.waitTicks.getValue();
                  }

                  if (this.enableDelay > 0 && this.waitedCooldown) {
                     this.enableDelay--;
                     return;
                  }

                  if (this.enableDelay <= 0) {
                     this.waitedCooldown = false;
                  }
               }

               class_1297 entity = mc.field_1724.method_49694();
               if ((
                     !mc.field_1687.method_8393((int)entity.method_19538().method_10216() >> 4, (int)entity.method_19538().method_10215() >> 4)
                        || entity.method_19538().method_10214() < -60.0
                  )
                  && this.stopunloaded.getValue()) {
                  this.returnGravity = true;
               } else {
                  if (this.timer.getValue() != 1.0F) {
                     UniqueCore.TICK_TIMER = this.timer.getValue();
                  }

                  entity.method_36456(mc.field_1724.method_36454());
                  double[] boatMotion = MovementUtility.forward(this.speed.getValue().floatValue());
                  double predictedX = entity.method_23317() + boatMotion[0];
                  double predictedZ = entity.method_23321() + boatMotion[1];
                  double predictedY = entity.method_23318();
                  if ((!mc.field_1687.method_8393((int)predictedX >> 4, (int)predictedZ >> 4) || entity.method_19538().method_10214() < -60.0)
                     && this.stopunloaded.getValue()) {
                     this.returnGravity = true;
                  } else {
                     this.returnGravity = false;
                     entity.method_18800(entity.method_18798().method_10216(), -this.glidespeed.getValue() / 100.0F, entity.method_18798().method_10215());
                     if (this.mode.getValue() == BoatFly.Mode.Motion) {
                        entity.method_18800(boatMotion[0], entity.method_18798().method_10214(), boatMotion[1]);
                     }

                     if (mc.field_1690.field_1903.method_1434()) {
                        if (this.mode.getValue() == BoatFly.Mode.Motion) {
                           entity.method_18800(
                              entity.method_18798().method_10216(),
                              entity.method_18798().method_10214() + this.yspeed.getValue().floatValue(),
                              entity.method_18798().method_10215()
                           );
                        } else {
                           predictedY += this.yspeed.getValue().floatValue();
                        }
                     } else if (mc.field_1690.field_1832.method_1434()) {
                        if (this.mode.getValue() == BoatFly.Mode.Motion) {
                           entity.method_18800(
                              entity.method_18798().method_10216(),
                              entity.method_18798().method_10214() - this.yspeed.getValue().floatValue(),
                              entity.method_18798().method_10215()
                           );
                        } else {
                           predictedY -= this.yspeed.getValue().floatValue();
                        }
                     }

                     if (!MovementUtility.isMoving()) {
                        entity.method_18800(0.0, entity.method_18798().method_10214(), 0.0);
                     }

                     if (this.ongroundpacket.getValue()) {
                        this.teleportToGround(entity);
                     }

                     if (this.mode.getValue() == BoatFly.Mode.Packet) {
                        entity.method_5814(predictedX, predictedY, predictedZ);
                        this.sendMovePacket(new class_2833(entity));
                     }

                     if (this.slotClick.getValue()) {
                        mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, 0, 0, class_1713.field_7796, mc.field_1724);
                     }

                     if (this.spoofpackets.getValue()) {
                        class_243 vec3d = entity.method_19538().method_1031(0.0, this.randomizeYOffset(), 0.0);
                        class_1690 entityBoat = new class_1690(mc.field_1687, vec3d.field_1352, vec3d.field_1351, vec3d.field_1350);
                        entityBoat.method_36456(entity.method_36454());
                        entityBoat.method_36457(entity.method_36455());
                        this.sendMovePacket(new class_2833(entityBoat));
                     }

                     ev.cancel();
                     this.ticksEnabled++;
                  }
               }
            }
         }
      }
   }

   @EventHandler
   public void onPacketReceive(PacketEvent.Receive event) {
      if (!fullNullCheck()) {
         if (event.getPacket() instanceof class_2661) {
            this.disable();
         }

         if (mc.field_1724.method_3144() && !this.returnGravity && !this.waitedCooldown) {
            if (this.cancel.getValue()) {
               if (event.getPacket() instanceof class_2692) {
                  event.cancel();
               }

               if (event.getPacket() instanceof class_2708) {
                  event.cancel();
               }

               if (event.getPacket() instanceof class_2684) {
                  event.cancel();
               }

               if (event.getPacket() instanceof class_2740) {
                  event.cancel();
               }
            }
         }
      }
   }

   @EventHandler
   public void onPacketSend(PacketEvent.Send event) {
      if (!fullNullCheck()) {
         if ((event.getPacket() instanceof class_2831 && this.cancelrotations.getValue() || event.getPacket() instanceof class_2851)
            && mc.field_1724.method_3144()) {
            event.cancel();
         }

         if (this.returnGravity && event.getPacket() instanceof class_2833) {
            event.cancel();
         }

         if (event.getPacket() instanceof class_2851 && this.allowShift.getValue()) {
            event.cancel();
         }

         if (mc.field_1724.method_49694() != null && !this.returnGravity && !this.waitedCooldown) {
            class_243 boatPos = mc.field_1724.method_49694().method_19538();
            if (mc.field_1687.method_8393((int)boatPos.method_10216() >> 4, (int)boatPos.method_10215() >> 4) && !(boatPos.method_10214() < -60.0)
               || !this.stopunloaded.getValue()) {
               if (event.getPacket() instanceof class_2833 pac && this.limit.getValue() && this.mode.getValue() == BoatFly.Mode.Packet) {
                  if (this.vehiclePackets.contains(pac)) {
                     this.vehiclePackets.remove(pac);
                  } else {
                     event.cancel();
                  }
               }
            }
         }
      }
   }

   public static enum Mode {
      Packet,
      Motion;
   }
}
