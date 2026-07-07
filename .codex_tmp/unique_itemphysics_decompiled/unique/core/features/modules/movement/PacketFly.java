package unique.core.features.modules.movement;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_243;
import net.minecraft.class_2708;
import net.minecraft.class_2793;
import net.minecraft.class_2828;
import net.minecraft.class_434;
import net.minecraft.class_2828.class_2829;
import org.jetbrains.annotations.NotNull;
import unique.core.UniqueCore;
import unique.core.events.impl.EventMove;
import unique.core.events.impl.EventSync;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.injection.accesors.IPlayerPositionLookS2CPacket;
import unique.core.setting.Setting;
import unique.core.setting.impl.BooleanSettingGroup;
import unique.core.utility.player.MovementUtility;

public class PacketFly extends Module {
   private final Setting<PacketFly.Mode> mode = new Setting<>("Mode", PacketFly.Mode.Fast);
   private final Setting<PacketFly.Type> type = new Setting<>("Type", PacketFly.Type.Preserve);
   private final Setting<PacketFly.Phase> phase = new Setting<>("Phase", PacketFly.Phase.Full);
   private final Setting<Boolean> limit = new Setting<>("Limit", true);
   private final Setting<BooleanSettingGroup> antiKick = new Setting<>("AntiKick", new BooleanSettingGroup(true));
   private final Setting<Integer> interval = new Setting<>("Interval", 4, 1, 50).addToGroup(this.antiKick);
   private final Setting<Integer> upInterval = new Setting<>("UpInterval", 20, 1, 50).addToGroup(this.antiKick);
   private final Setting<Float> anticKickOffset = new Setting<>("anticKickOffset", 0.04F, 0.008F, 1.0F).addToGroup(this.antiKick);
   private final Setting<Float> speed = new Setting<>("Speed", 1.0F, 0.0F, 10.0F);
   private final Setting<Float> upSpeed = new Setting<>("UpSpeed", 0.062F, 0.001F, 0.1F);
   private final Setting<Float> timer = new Setting<>("Timer", 1.0F, 0.1F, 5.0F);
   private final Setting<Integer> increaseTicks = new Setting<>("IncreaseTicks", 1, 1, 20);
   private final Setting<Float> factor = new Setting<>("Factor", 1.0F, 1.0F, 10.0F);
   private final Setting<Float> offset = new Setting<>(
      "Offset", 1337.0F, 1.0F, 1337.0F, v -> this.type.is(PacketFly.Type.Up) || this.type.is(PacketFly.Type.Down)
   );
   private final ConcurrentHashMap<Integer, PacketFly.Teleport> teleports = new ConcurrentHashMap<>();
   private final ArrayList<class_2828> movePackets = new ArrayList<>();
   private int ticks;
   private int factorTicks;
   private int teleportId = -1;
   private boolean flip = false;

   public PacketFly() {
      super("PacketFly", Module.Category.MOVEMENT);
   }

   @Override
   public void onEnable() {
      this.teleportId = -1;
      if (fullNullCheck() && mc.field_1724 != null) {
         this.ticks = 0;
         this.teleportId = 0;
         this.movePackets.clear();
         this.teleports.clear();
      }

      this.factorTicks = 0;
   }

   @Override
   public void onDisable() {
      UniqueCore.TICK_TIMER = 1.0F;
   }

   public boolean getTickCounter(int n) {
      this.ticks++;
      if (this.ticks >= n) {
         this.ticks = 0;
         return true;
      } else {
         return false;
      }
   }

   private int getWorldBorder() {
      if (mc.method_1542()) {
         return 1;
      } else {
         int n = ThreadLocalRandom.current().nextInt(29000000);
         return ThreadLocalRandom.current().nextBoolean() ? n : -n;
      }
   }

   public class_243 getVectorByMode(@NotNull class_243 vec3d, class_243 vec3d2) {
      class_243 vec3d3 = vec3d.method_1019(vec3d2);
      switch ((PacketFly.Type)this.type.getValue()) {
         case Preserve:
            vec3d3 = vec3d3.method_1031(this.getWorldBorder(), 0.0, this.getWorldBorder());
            break;
         case Up:
            vec3d3 = vec3d3.method_1031(0.0, this.offset.getValue().floatValue(), 0.0);
            break;
         case Down:
            vec3d3 = vec3d3.method_1031(0.0, -this.offset.getValue(), 0.0);
            break;
         case Bounds:
            vec3d3 = new class_243(vec3d3.field_1352, mc.field_1724.method_23318() <= 10.0 ? 255.0 : 1.0, vec3d3.field_1350);
      }

      return vec3d3;
   }

   public void sendPackets(class_243 vec3d, boolean confirm) {
      class_243 motion = mc.field_1724.method_19538().method_1019(vec3d);
      class_243 rubberBand = this.getVectorByMode(vec3d, motion);
      class_2828 motionPacket = new class_2829(motion.field_1352, motion.field_1351, motion.field_1350, mc.field_1724.method_24828());
      this.movePackets.add(motionPacket);
      this.sendPacket(motionPacket);
      class_2828 rubberBandPacket = new class_2829(rubberBand.field_1352, rubberBand.field_1351, rubberBand.field_1350, mc.field_1724.method_24828());
      this.movePackets.add(rubberBandPacket);
      this.sendPacket(rubberBandPacket);
      if (confirm) {
         this.sendPacket(new class_2793(++this.teleportId));
         this.teleports.put(this.teleportId, new PacketFly.Teleport(motion.field_1352, motion.field_1351, motion.field_1350, System.currentTimeMillis()));
      }
   }

   @EventHandler
   public void onPacketReceive(PacketEvent.Receive event) {
      if (!fullNullCheck()) {
         if (mc.field_1724 != null && event.getPacket() instanceof class_2708 pac) {
            PacketFly.Teleport teleport = this.teleports.remove(pac.method_11737());
            if (mc.field_1724.method_5805()
               && mc.field_1687.method_8393((int)mc.field_1724.method_23317() >> 4, (int)mc.field_1724.method_23321() >> 4)
               && !(mc.field_1755 instanceof class_434)
               && this.mode.getValue() != PacketFly.Mode.Rubber
               && teleport != null
               && teleport.x == pac.method_11734()
               && teleport.y == pac.method_11735()
               && teleport.z == pac.method_11738()) {
               event.cancel();
               return;
            }

            ((IPlayerPositionLookS2CPacket)pac).setYaw(mc.field_1724.method_36454());
            ((IPlayerPositionLookS2CPacket)pac).setPitch(mc.field_1724.method_36455());
            this.teleportId = pac.method_11737();
         }
      }
   }

   @EventHandler
   public void onPacketSend(@NotNull PacketEvent.Send event) {
      if (event.getPacket() instanceof class_2828) {
         if (this.movePackets.contains((class_2828)event.getPacket())) {
            this.movePackets.remove((class_2828)event.getPacket());
            return;
         }

         event.cancel();
      }
   }

   @Override
   public void onUpdate() {
      this.teleports.entrySet().removeIf(entry -> System.currentTimeMillis() - entry.getValue().time > 30000L);
   }

   @EventHandler
   public void onMove(@NotNull EventMove event) {
      if (!event.isCancelled()) {
         if (this.mode.getValue() != PacketFly.Mode.Rubber && this.teleportId == 0) {
            return;
         }

         event.cancel();
         event.setX(mc.field_1724.method_18798().field_1352);
         event.setY(mc.field_1724.method_18798().field_1351);
         event.setZ(mc.field_1724.method_18798().field_1350);
         if (this.phase.getValue() != PacketFly.Phase.Off
            && (
               this.phase.getValue() == PacketFly.Phase.Semi
                  || mc.field_1687.method_20812(mc.field_1724, mc.field_1724.method_5829().method_1009(-0.0625, -0.0625, -0.0625)).iterator().hasNext()
            )) {
            mc.field_1724.field_5960 = true;
         }
      }
   }

   @EventHandler
   public void onSync(EventSync eventPlayerUpdateWalking) {
      if (this.timer.getValue().floatValue() != 1.0) {
         UniqueCore.TICK_TIMER = this.timer.getValue();
      }

      mc.field_1724.method_18800(0.0, 0.0, 0.0);
      if (this.mode.getValue() != PacketFly.Mode.Rubber && this.teleportId == 0) {
         if (this.getTickCounter(4)) {
            this.sendPackets(class_243.field_1353, false);
         }
      } else {
         boolean insideBlock = mc.field_1687
            .method_20812(mc.field_1724, mc.field_1724.method_5829().method_1009(-0.0625, -0.0625, -0.0625))
            .iterator()
            .hasNext();
         double upMotion = 0.0;
         if (mc.field_1690.field_1903.method_1434() && (insideBlock || !MovementUtility.isMoving())) {
            if (this.antiKick.getValue().isEnabled() && !insideBlock) {
               upMotion = this.getTickCounter(this.mode.is(PacketFly.Mode.Rubber) ? this.upInterval.getValue() / 2 : this.upInterval.getValue())
                  ? -this.upSpeed.getValue() / 2.0F
                  : this.upSpeed.getValue().floatValue();
            } else {
               upMotion = this.upSpeed.getValue().floatValue();
            }
         } else if (mc.field_1690.field_1832.method_1434()) {
            upMotion = -this.upSpeed.getValue();
         } else if (this.antiKick.getValue().isEnabled() && !insideBlock) {
            upMotion = this.getTickCounter(this.interval.getValue()) ? -this.anticKickOffset.getValue() : 0.0;
         }

         if (this.phase.is(PacketFly.Phase.Full) && insideBlock && MovementUtility.isMoving() && upMotion != 0.0) {
            upMotion = mc.field_1690.field_1903.method_1434() ? upMotion / 2.5 : upMotion / 1.5;
         }

         double[] motion = MovementUtility.forward(
            this.phase.is(PacketFly.Phase.Full) && insideBlock ? 0.034444444444444444 : this.speed.getValue().floatValue() * 0.26
         );
         int factorInt = 1;
         if (this.mode.getValue() == PacketFly.Mode.Factor && mc.field_1724.field_6012 % this.increaseTicks.getValue() == 0) {
            factorInt = (int)Math.floor(this.factor.getValue().floatValue());
            this.factorTicks++;
            if (this.factorTicks > (int)(20.0 / ((this.factor.getValue() - factorInt) * 20.0))) {
               factorInt++;
               this.factorTicks = 0;
            }
         }

         for (int i = 1; i <= factorInt; i++) {
            if (this.mode.getValue() == PacketFly.Mode.Limit) {
               if (mc.field_1724.field_6012 % 2 == 0) {
                  if (this.flip && upMotion >= 0.0) {
                     this.flip = false;
                     upMotion = -this.upSpeed.getValue() / 2.0F;
                  }

                  mc.field_1724.method_18800(motion[0] * i, upMotion * i, motion[1] * i);
                  this.sendPackets(mc.field_1724.method_18798(), !this.limit.getValue());
               } else if (upMotion < 0.0) {
                  this.flip = true;
               }
            } else {
               mc.field_1724.method_18800(motion[0] * i, upMotion * i, motion[1] * i);
               this.sendPackets(mc.field_1724.method_18798(), !this.mode.is(PacketFly.Mode.Rubber));
            }
         }
      }
   }

   public static enum Mode {
      Fast,
      Factor,
      Rubber,
      Limit;
   }

   public static enum Phase {
      Full,
      Off,
      Semi;
   }

   public record Teleport(double x, double y, double z, long time) {
   }

   public static enum Type {
      Preserve,
      Up,
      Down,
      Bounds;
   }
}
