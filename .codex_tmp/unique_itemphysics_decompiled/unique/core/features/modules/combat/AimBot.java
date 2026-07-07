package unique.core.features.modules.combat;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1531;
import net.minecraft.class_1657;
import net.minecraft.class_1753;
import net.minecraft.class_243;
import net.minecraft.class_2886;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import org.jetbrains.annotations.NotNull;
import unique.core.core.Managers;
import unique.core.core.manager.client.ModuleManager;
import unique.core.events.impl.EventSync;
import unique.core.events.impl.PlayerUpdateEvent;
import unique.core.features.modules.Module;
import unique.core.setting.Setting;
import unique.core.utility.Timer;
import unique.core.utility.math.MathUtility;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.Render3DEngine;

public final class AimBot extends Module {
   private final Setting<AimBot.Mode> mode = new Setting<>("Mode", AimBot.Mode.BowAim);
   private final Setting<AimBot.Rotation> rotation = new Setting<>("Rotation", AimBot.Rotation.Silent, v -> this.mode.getValue() != AimBot.Mode.AimAssist);
   private final Setting<Float> aimRange = new Setting<>("Range", 20.0F, 1.0F, 30.0F, v -> this.mode.getValue() != AimBot.Mode.AimAssist);
   private final Setting<Integer> aimStrength = new Setting<>("AimStrength", 30, 1, 100, v -> this.mode.getValue() == AimBot.Mode.AimAssist);
   private final Setting<Integer> aimSmooth = new Setting<>("AimSmooth", 45, 1, 180, v -> this.mode.getValue() == AimBot.Mode.AimAssist);
   private final Setting<Integer> aimtime = new Setting<>("AimTime", 2, 1, 10, v -> this.mode.getValue() == AimBot.Mode.AimAssist);
   private final Setting<Boolean> ignoreWalls = new Setting<>("IgnoreWalls", true, v -> this.mode.getValue() != AimBot.Mode.BowAim);
   private final Setting<Boolean> ignoreTeam = new Setting<>("IgnoreTeam", true);
   private final Setting<Integer> reactionTime = new Setting<>("ReactionTime", 80, 1, 500, v -> this.mode.getValue() == AimBot.Mode.AimAssist);
   private final Setting<Boolean> ignoreInvisible = new Setting<>("IgnoreInvis", false);
   private final Setting<Float> rotYawRandom = new Setting<>("YawRandom", 0.0F, 0.0F, 3.0F, v -> this.mode.getValue() == AimBot.Mode.CSAim);
   private final Setting<Float> rotPitchRandom = new Setting<>("PitchRandom", 0.0F, 0.0F, 3.0F, v -> this.mode.getValue() == AimBot.Mode.CSAim);
   private final Setting<Float> predict = new Setting<>("AimPredict", 0.5F, 0.5F, 8.0F);
   private final Setting<Integer> delay = new Setting<>("ShootDelay", 5, 0, 10, v -> this.mode.getValue() == AimBot.Mode.CSAim);
   private final Setting<Integer> fov = new Setting<>("FOV", 65, 10, 360);
   private final Setting<Integer> predictTicks = new Setting<>("PredictTicks", 2, 0, 20, v -> this.mode.getValue() == AimBot.Mode.BowAim);
   private final Setting<AimBot.Bone> part = new Setting<>("Bone", AimBot.Bone.Head, v -> this.mode.getValue() == AimBot.Mode.CSAim);
   private class_1297 target;
   private class_1297 assistTarget;
   private float rotationYaw;
   private float rotationPitch;
   private float assistYaw;
   private float assistPitch;
   private long assistLastTime;
   private Timer visibleTime = new Timer();

   public AimBot() {
      super("AimBot", Module.Category.COMBAT);
   }

   @EventHandler
   public void onPlayerUpdate(PlayerUpdateEvent event) {
      if (this.mode.getValue() == AimBot.Mode.BowAim) {
         if (mc.field_1724.method_6030().method_7909() instanceof class_1753) {
            class_1657 t = Managers.COMBAT.getTargetByFOV(this.fov.getValue().intValue());
            if (t != null) {
               float charge = (mc.field_1724.method_6030().method_7935(mc.field_1724) - mc.field_1724.method_6048()) / 20.0F;
               charge = class_3532.method_15363(charge, 0.1F, 1.0F);
               float velocity = charge * 3.0F;
               double distance = mc.field_1724.method_5739(t);
               double travelTime = distance / velocity;
               class_243 targetPos = t.method_19538()
                  .method_1031(
                     (t.method_23317() - t.field_6014) * travelTime * this.predict.getValue().floatValue(),
                     (t.method_23318() - t.field_6036) * travelTime * this.predict.getValue().floatValue(),
                     (t.method_23321() - t.field_5969) * travelTime * this.predict.getValue().floatValue()
                  )
                  .method_1031(0.0, t.method_17682() * 0.5, 0.0);
               double dx = targetPos.field_1352 - mc.field_1724.method_23317();
               double dz = targetPos.field_1350 - mc.field_1724.method_23321();
               double dy = targetPos.field_1351 - (mc.field_1724.method_23318() + mc.field_1724.method_18381(mc.field_1724.method_18376()));
               double horizontalDist = Math.sqrt(dx * dx + dz * dz);
               this.rotationYaw = (float)Math.toDegrees(Math.atan2(dz, dx)) - 90.0F;
               float g = 0.05F;
               float v2 = velocity * velocity;
               float v4 = v2 * v2;
               double root = v4 - g * (g * horizontalDist * horizontalDist + 2.0 * dy * v2);
               if (root >= 0.0) {
                  this.rotationPitch = (float)(-Math.toDegrees(Math.atan((v2 + Math.sqrt(root)) / (g * horizontalDist))));
               }

               ModuleManager.rotations.fixRotation = this.rotationYaw;
            }
         }
      } else if (this.mode.getValue() == AimBot.Mode.CSAim) {
         this.calcThread();
         ModuleManager.rotations.fixRotation = this.rotationYaw;
      } else {
         if (this.mode.getValue() == AimBot.Mode.AimAssist) {
            this.assistTarget = this.findBestAssistTarget();
            if (this.assistTarget == null) {
               this.assistYaw = mc.field_1724.method_36454();
               this.assistPitch = mc.field_1724.method_36455();
               return;
            }

            if (!this.ignoreWalls.getValue() && !mc.field_1724.method_6057(this.assistTarget)) {
               this.visibleTime.reset();
               return;
            }

            if (!this.visibleTime.passedMs(this.reactionTime.getValue().intValue())) {
               return;
            }

            class_243 pos = this.assistTarget.method_33571();
            float targetYaw = (float)Math.toDegrees(Math.atan2(pos.field_1350 - mc.field_1724.method_23321(), pos.field_1352 - mc.field_1724.method_23317()))
               - 90.0F;
            float targetPitch = (float)(
               -Math.toDegrees(
                  Math.atan2(
                     pos.field_1351 - (mc.field_1724.method_23318() + mc.field_1724.method_18381(mc.field_1724.method_18376())),
                     Math.sqrt(Math.pow(pos.field_1352 - mc.field_1724.method_23317(), 2.0) + Math.pow(pos.field_1350 - mc.field_1724.method_23321(), 2.0))
                  )
               )
            );
            float speed = this.aimStrength.getValue().intValue() * 0.005F;
            this.assistYaw = mc.field_1724.method_36454() + class_3532.method_15393(targetYaw - mc.field_1724.method_36454()) * speed;
            this.assistPitch = mc.field_1724.method_36455() + class_3532.method_15393(targetPitch - mc.field_1724.method_36455()) * speed;
            double sensitivity = (Double)mc.field_1690.method_42495().method_41753() * 0.6 + 0.2;
            double multiplier = sensitivity * sensitivity * sensitivity * 8.0;
            this.assistYaw = (float)(this.assistYaw - this.assistYaw % (multiplier * 0.15));
            this.assistPitch = (float)(this.assistPitch - this.assistPitch % (multiplier * 0.15));
            mc.field_1724.method_36456(this.assistYaw);
            mc.field_1724.method_36457(class_3532.method_15363(this.assistPitch, -90.0F, 90.0F));
         }
      }
   }

   @EventHandler
   public void onSync(EventSync event) {
      if (this.mode.getValue() == AimBot.Mode.CSAim) {
         if (this.target != null
            && (mc.field_1724.method_6057(this.target) || this.ignoreWalls.getValue())
            && mc.field_1724.field_6012 % this.delay.getValue() == 0) {
            event.addPostAction(
               () -> this.sendSequencedPacket(id -> new class_2886(class_1268.field_5808, id, mc.field_1724.method_36454(), mc.field_1724.method_36455()))
            );
         }
      }
   }

   @Override
   public void onEnable() {
      this.target = null;
      this.assistTarget = null;
      this.assistLastTime = 0L;
      this.rotationYaw = mc.field_1724.method_36454();
      this.rotationPitch = mc.field_1724.method_36455();
      this.assistYaw = mc.field_1724.method_36454();
      this.assistPitch = mc.field_1724.method_36455();
   }

   @Override
   public void onRender3D(class_4587 stack) {
      if (this.mode.getValue() != AimBot.Mode.AimAssist) {
         if (this.rotation.getValue() == AimBot.Rotation.Client && !Float.isNaN(this.rotationYaw)) {
            mc.field_1724.method_36456((float)Render2DEngine.interpolate(mc.field_1724.field_5982, this.rotationYaw, Render3DEngine.getTickDelta()));
            mc.field_1724.method_36457((float)Render2DEngine.interpolate(mc.field_1724.field_6004, this.rotationPitch, Render3DEngine.getTickDelta()));
         }
      }
   }

   private void calcThread() {
      if (this.target == null) {
         this.findTarget();
      } else if (this.skipEntity(this.target)) {
         this.target = null;
      } else {
         class_243 pos = this.getResolvedPos(this.target).method_1031(0.0, this.part.getValue().getH(), 0.0);
         float dy = class_3532.method_15393(
            (float)Math.toDegrees(Math.atan2(pos.field_1350 - mc.field_1724.method_23321(), pos.field_1352 - mc.field_1724.method_23317()))
               - 90.0F
               - this.rotationYaw
         );
         float dp = (float)(
               -Math.toDegrees(
                  Math.atan2(
                     pos.field_1351 - (mc.field_1724.method_23318() + mc.field_1724.method_18381(mc.field_1724.method_18376())),
                     Math.sqrt(Math.pow(pos.field_1352 - mc.field_1724.method_23317(), 2.0) + Math.pow(pos.field_1350 - mc.field_1724.method_23321(), 2.0))
                  )
               )
            )
            - this.rotationPitch;
         this.rotationYaw = this.rotationYaw
            + (class_3532.method_15363(dy, -40.0F, 40.0F) + MathUtility.random(-this.rotYawRandom.getValue(), this.rotYawRandom.getValue()));
         this.rotationPitch = class_3532.method_15363(this.rotationPitch + class_3532.method_15363(dp, -15.0F, 15.0F), -90.0F, 90.0F)
            + MathUtility.random(-this.rotPitchRandom.getValue(), this.rotPitchRandom.getValue());
      }
   }

   private void findTarget() {
      List<class_1297> list = new CopyOnWriteArrayList<>();

      for (class_1297 e : mc.field_1687.method_18112()) {
         if (!this.skipEntity(e)) {
            list.add(e);
         }
      }

      float best = this.fov.getValue().intValue();
      class_1297 bestEnt = null;

      for (class_1297 ex : list) {
         float yaw = Math.abs(this.getYawToEntityNew(ex));
         if (yaw < best) {
            best = yaw;
            bestEnt = ex;
         }
      }

      this.target = bestEnt;
   }

   private class_1297 findBestAssistTarget() {
      class_1297 best = null;
      double score = Double.MAX_VALUE;

      for (class_1297 e : mc.field_1687.method_18112()) {
         if (!this.skipEntity(e)) {
            double d = mc.field_1724.method_5739(e);
            float yaw = Math.abs(this.getYawToEntityNew(e));
            double s = d + yaw * 2.0;
            if (s < score) {
               score = s;
               best = e;
            }
         }
      }

      return best;
   }

   private boolean skipEntity(class_1297 e) {
      if (e instanceof class_1309 ent) {
         if (!ent.method_5805()) {
            return true;
         } else if (e instanceof class_1531) {
            return true;
         } else if (e instanceof class_1657 pl) {
            if (e == mc.field_1724) {
               return true;
            } else if (e.method_5767() && this.ignoreInvisible.getValue()) {
               return true;
            } else if (Managers.FRIEND.isFriend(pl)) {
               return true;
            } else if (pl.method_22861() == mc.field_1724.method_22861() && this.ignoreTeam.getValue()) {
               return true;
            } else {
               return Math.abs(this.getYawToEntityNew(e)) > this.fov.getValue().intValue()
                  ? true
                  : mc.field_1724.method_5707(this.getResolvedPos(e)) > this.aimRange.getPow2Value();
            }
         } else {
            return true;
         }
      } else {
         return true;
      }
   }

   private float getYawToEntityNew(@NotNull class_1297 e) {
      return this.getYawBetween(mc.field_1724.method_36454(), mc.field_1724.method_23317(), mc.field_1724.method_23321(), e.method_23317(), e.method_23321());
   }

   private float getYawBetween(float yaw, double sx, double sz, double dx, double dz) {
      double x = dx - sx;
      double z = dz - sz;
      float yaw1 = (float)(StrictMath.atan2(z, x) * 180.0 / Math.PI) - 90.0F;
      return class_3532.method_15393(yaw1 - yaw);
   }

   private class_243 getResolvedPos(@NotNull class_1297 e) {
      return new class_243(
         e.method_23317() + (e.method_23317() - e.field_6014) * this.predict.getValue().floatValue(),
         e.method_23318(),
         e.method_23321() + (e.method_23321() - e.field_5969) * this.predict.getValue().floatValue()
      );
   }

   private static enum Bone {
      Head(1.7F),
      Neck(1.5F),
      Torso(1.0F),
      Tights(0.8F),
      Feet(0.25F);

      private final float h;

      private Bone(float h) {
         this.h = h;
      }

      public float getH() {
         return this.h;
      }
   }

   private static enum Mode {
      CSAim,
      AimAssist,
      BowAim;
   }

   private static enum Rotation {
      Client,
      Silent;
   }
}
