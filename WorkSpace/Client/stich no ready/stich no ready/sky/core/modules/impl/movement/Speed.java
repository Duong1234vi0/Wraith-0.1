package sky.core.modules.impl.movement;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.client.CEntityActionPacket;
import net.minecraft.network.play.client.CEntityActionPacket.Action;
import net.minecraft.network.play.server.SPlayerPositionLookPacket;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.vector.Vector3d;
import sky.core.events.EventInput;
import sky.core.events.EventOnMovePost;
import sky.core.events.EventPacket;
import sky.core.events.EventPostUpdate;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.utils.player.MoveUtil;

public class Speed extends Module {
   public final ModeSetting mode = new ModeSetting("Режим", "RW", new String[]{"RW", "Collision", "Strafe", "StrafeStrict", "Motion", "Matrix", "GrimCollision", "MetaHvH", "HolyWorld", "Grim Timer"});
   private final BooleanSetting waterSpeed = new BooleanSetting("Скорость в воде", false, () -> {
      return this.isStrafeMode();
   });
   private final BooleanSetting timerEnabled = new BooleanSetting("Таймер", false, () -> {
      return this.isStrafeMode();
   });
   private final BooleanSetting timerBypass = new BooleanSetting("Обход таймера", true, () -> {
      return this.isStrafeMode() && (Boolean)this.timerEnabled.get();
   });
   private final SliderSetting bypassThreshold = new SliderSetting("Порог обхода", 25.0F, 15.0F, 30.0F, 1.0F, () -> {
      return this.isStrafeMode() && (Boolean)this.timerEnabled.get() && (Boolean)this.timerBypass.get();
   });
   private final SliderSetting timerMultiplier = new SliderSetting("Множитель таймера", 1.08F, 1.0F, 1.2F, 0.01F, () -> {
      return this.isStrafeMode() && (Boolean)this.timerEnabled.get();
   });
   private final SliderSetting motionSpeed = new SliderSetting("Скорость", 0.15F, 0.1F, 0.5F, 0.01F, () -> {
      return this.mode.is("Motion");
   });
   private final BooleanSetting autoJump = new BooleanSetting("Авто прыжок", true, () -> {
      return this.mode.is("Motion");
   });
   private final ModeSetting metaMode = new ModeSetting("Meta режим", "Default", () -> {
      return this.mode.is("MetaHvH");
   }, new String[]{"Default", "Custom"});
   private final SliderSetting metaSpeed = new SliderSetting("Meta скорость", 0.2F, 0.2F, 1.05F, 0.01F, () -> {
      return this.mode.is("MetaHvH") && this.metaMode.is("Custom");
   });
   private final BooleanSetting metaDamageBoost = new BooleanSetting("Meta буст с дамагом", false, () -> {
      return this.mode.is("MetaHvH");
   });
   private final SliderSetting metaBoostValue = new SliderSetting("Meta значение буста", 0.7F, 0.1F, 5.0F, 0.1F, () -> {
      return this.mode.is("MetaHvH") && (Boolean)this.metaDamageBoost.get();
   });
   private final SliderSetting metaBoostDuration = new SliderSetting("Meta длительность буста", 700.0F, 100.0F, 2000.0F, 100.0F, () -> {
      return this.mode.is("MetaHvH") && (Boolean)this.metaDamageBoost.get();
   });
   private final SliderSetting collisionSpeed = new SliderSetting("Скорость буста", 8.0F, 0.1F, 8.0F, 0.1F, () -> {
      return this.mode.is("GrimCollision");
   });
   private final SliderSetting collisionRadius = new SliderSetting("Радиус", 1.0F, 0.5F, 1.5F, 0.1F, () -> {
      return this.mode.is("GrimCollision");
   });
   private int ticks;
   private int groundTicks;
   private int strafeTicks = 1;
   private int bypassCounter = 0;
   private double lastSpeed = 0.0;
   private float lastForward = 0.0F;
   private int holyWorldTicks = 0;
   private int grimTimerCounter = 0;
   private int grimTimerTicks = 0;
   private final Map previousPositions = new HashMap();
   private long lastDamageTime = 0L;

   public Speed() {
      super("Speed", "Позволяет буститься", Category.Movement);
      this.addSettings(new Setting[]{this.mode, this.waterSpeed, this.timerEnabled, this.timerBypass, this.bypassThreshold, this.timerMultiplier, this.motionSpeed, this.autoJump, this.metaMode, this.metaSpeed, this.metaDamageBoost, this.metaBoostValue, this.metaBoostDuration, this.collisionSpeed, this.collisionRadius});
   }

   private boolean isStrafeMode() {
      return this.mode.is("Strafe") || this.mode.is("StrafeStrict");
   }

   public void onEnable() {
      this.resetState();
      this.strafeTicks = 1;
      this.bypassCounter = 0;
      this.holyWorldTicks = 0;
      this.grimTimerCounter = 0;
      this.grimTimerTicks = 0;
      super.onEnable();
   }

   public void onDisable() {
      this.resetState();
      this.setTimer(1.0F);
      super.onDisable();
   }

   @EventTarget
   public void onEvent(EventUpdate event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         if (this.mode.is("Collision")) {
            this.handleCollision();
         } else if (this.isStrafeMode()) {
            this.handleStrafe();
         } else if (this.mode.is("Motion")) {
            this.handleMotion();
         } else if (this.mode.is("Matrix")) {
            this.handleMatrix();
         } else if (this.mode.is("GrimCollision")) {
            this.handleGrimCollision();
         } else if (this.mode.is("MetaHvH")) {
            this.handleMetaHvH();
         } else if (this.mode.is("HolyWorld")) {
            this.handleHolyWorld();
         } else if (this.mode.is("Grim Timer")) {
            this.handleGrimTimer();
         } else {
            if (this.mode.is("RW")) {
            }

         }
      }
   }

   private void handleCollision() {
      if (MoveUtil.isMoving()) {
         double scanRadius = 1.25;
         Minecraft var10001 = mc;
         Minecraft var10002 = mc;
         List nearbyEntities = mc.world.getEntitiesWithinAABBExcludingEntity(Minecraft.player, Minecraft.player.getBoundingBox().grow(scanRadius));
         Iterator var4 = nearbyEntities.iterator();

         while(var4.hasNext()) {
            Entity entity = (Entity)var4.next();
            var10001 = mc;
            if (entity != Minecraft.player && entity instanceof PlayerEntity) {
               Minecraft var10000 = mc;
               double distanceX = Math.abs(Minecraft.player.getPosX() - entity.getPosX());
               var10000 = mc;
               double distanceZ = Math.abs(Minecraft.player.getPosZ() - entity.getPosZ());
               if (!(distanceX > 2.1) && !(distanceZ > 1.3)) {
                  double entitySpeed = this.getEntitySpeed(entity);
                  double boostAmount = entitySpeed < 5.0 ? 0.02 : 0.032;
                  var10001 = mc;
                  var10002 = mc;
                  List collisionEntities = mc.world.getEntitiesWithinAABBExcludingEntity(Minecraft.player, Minecraft.player.getBoundingBox().grow(entitySpeed < 5.0 ? 0.1 : 1.25));
                  int collisions = 0;
                  Iterator var16 = collisionEntities.iterator();

                  while(var16.hasNext()) {
                     Entity collisionEntity = (Entity)var16.next();
                     var10001 = mc;
                     if (collisionEntity != Minecraft.player && !(collisionEntity instanceof ArmorStandEntity)) {
                        ++collisions;
                     }
                  }

                  if (collisions > 0) {
                     double[] motion = this.forward(boostAmount);
                     var10000 = mc;
                     Minecraft.player.addVelocity(motion[0], 0.0, motion[1]);
                  }
                  break;
               }
            }
         }

      }
   }

   private void handleStrafe() {
      Minecraft var10000;
      boolean var19;
      label127: {
         var10000 = mc;
         if (!Minecraft.player.isInWater()) {
            var10000 = mc;
            if (!Minecraft.player.isInLava()) {
               var19 = false;
               break label127;
            }
         }

         var19 = true;
      }

      boolean inWater = var19;
      var10000 = mc;
      boolean inCobweb = Minecraft.player.isInWeb();
      var10000 = mc;
      if (!Minecraft.player.isOnLadder()) {
         var10000 = mc;
         if (!Minecraft.player.isElytraFlying()) {
            var10000 = mc;
            if (!Minecraft.player.isOnGround() && (!inWater || (Boolean)this.waterSpeed.get()) && !inCobweb) {
               var10000 = mc;
               if (!Minecraft.player.abilities.isFlying) {
                  var10000 = mc;
                  if (!(Minecraft.player.fallDistance >= 5.0F)) {
                     if (!MoveUtil.isMoving()) {
                        this.strafeTicks = 1;
                        this.setTimer(1.0F);
                        return;
                     }

                     float forward;
                     double base;
                     double speed;
                     label105: {
                        var10000 = mc;
                        double var21 = Minecraft.player.getPosX();
                        Minecraft var10001 = mc;
                        double dx = var21 - Minecraft.player.prevPosX;
                        var10000 = mc;
                        var21 = Minecraft.player.getPosZ();
                        var10001 = mc;
                        double dz = var21 - Minecraft.player.prevPosZ;
                        this.lastSpeed = Math.sqrt(dx * dx + dz * dz);
                        var10000 = mc;
                        forward = Minecraft.player.movementInput.moveForward;
                        base = this.getBaseSpeed(0.2873);
                        speed = base * (forward <= 0.0F && this.lastForward > 0.0F ? 0.66 : 1.0);
                        this.lastForward = forward;
                        if (this.strafeTicks == 1) {
                           var10000 = mc;
                           if (Minecraft.player.isOnGround()) {
                              var10000 = mc;
                              ClientPlayerEntity var22 = Minecraft.player;
                              var10001 = mc;
                              double var20 = Minecraft.player.getMotion().x;
                              double var10002 = this.getJumpHeight(0.42);
                              Minecraft var10003 = mc;
                              var22.setMotion(var20, var10002, Minecraft.player.getMotion().z);
                              speed *= 2.149;
                              this.strafeTicks = 2;
                              break label105;
                           }
                        }

                        if (this.strafeTicks == 2) {
                           speed = this.lastSpeed - 0.66 * (this.lastSpeed - base);
                           this.strafeTicks = 3;
                        } else {
                           var10000 = mc;
                           if (Minecraft.player.isOnGround()) {
                              this.strafeTicks = 1;
                           }

                           speed = this.lastSpeed > 0.0 ? this.lastSpeed - this.lastSpeed / 159.0 : base;
                        }
                     }

                     speed = Math.max(speed, base);
                     boolean strict = this.mode.is("StrafeStrict");
                     double ncpCap = this.getBaseSpeed(!strict && !(forward < 1.0F) ? 0.576 : 0.465);
                     double bypassCap = this.getBaseSpeed(!strict && !(forward < 1.0F) ? 0.57 : 0.44);
                     speed = Math.min(speed, (float)this.bypassCounter > (Float)this.bypassThreshold.get() ? ncpCap : bypassCap);
                     if (++this.bypassCounter > 50) {
                        this.bypassCounter = 0;
                     }

                     MoveUtil.setMotion(speed);
                     this.lastForward = forward;
                     if ((Boolean)this.timerEnabled.get()) {
                        label87: {
                           var10000 = mc;
                           if (!Minecraft.player.isOnLadder() && !inWater) {
                              var10000 = mc;
                              if (Minecraft.player.fallDistance < 5.0F) {
                                 var19 = true;
                                 break label87;
                              }
                           }

                           var19 = false;
                        }

                        boolean canBoost = var19;
                        boolean applyTimer = canBoost && ((float)this.bypassCounter > (Float)this.bypassThreshold.get() || !(Boolean)this.timerBypass.get());
                        this.setTimer(applyTimer ? (Float)this.timerMultiplier.get() : 1.0F);
                     }

                     return;
                  }
               }
            }
         }
      }

      this.strafeTicks = 1;
      this.setTimer(1.0F);
   }

   private double getBaseSpeed(double speed) {
      Minecraft var10000 = mc;
      int amp;
      if (Minecraft.player.isPotionActive(Effects.SPEED)) {
         var10000 = mc;
         amp = Minecraft.player.getActivePotionEffect(Effects.SPEED).getAmplifier();
         speed *= 1.0 + 0.2 * (double)(amp + 1);
      }

      var10000 = mc;
      if (Minecraft.player.isPotionActive(Effects.SLOWNESS)) {
         var10000 = mc;
         amp = Minecraft.player.getActivePotionEffect(Effects.SLOWNESS).getAmplifier();
         speed /= 1.0 + 0.2 * (double)(amp + 1);
      }

      return speed;
   }

   private double getJumpHeight(double jump) {
      Minecraft var10000 = mc;
      if (Minecraft.player.isPotionActive(Effects.JUMP_BOOST)) {
         Minecraft var10001 = mc;
         jump += (double)(Minecraft.player.getActivePotionEffect(Effects.JUMP_BOOST).getAmplifier() + 1) * 0.1;
      }

      return jump;
   }

   private void handleMotion() {
      Minecraft var10000 = mc;
      if (!Minecraft.player.isElytraFlying()) {
         if (MoveUtil.isMoving()) {
            var10000 = mc;
            if (Minecraft.player.isOnGround() && (Boolean)this.autoJump.get()) {
               var10000 = mc;
               Minecraft.player.jump();
            }

            MoveUtil.setMotion((double)(Float)this.motionSpeed.get());
         }
      }
   }

   private void handleMatrix() {
      Minecraft var10000 = mc;
      if (!Minecraft.player.isElytraFlying()) {
         var10000 = mc;
         if (Minecraft.player.isOnGround() && MoveUtil.isMoving()) {
            var10000 = mc;
            Minecraft.player.jump();
         }

         var10000 = mc;
         if (Minecraft.player.getMotion().y == -0.4448259643949201) {
            var10000 = mc;
            ClientPlayerEntity var2 = Minecraft.player;
            Minecraft var10001 = mc;
            double var1 = Minecraft.player.getMotion().x * 2.4;
            Minecraft var10002 = mc;
            Minecraft var10003 = mc;
            var2.setVelocity(var1, Minecraft.player.getMotion().y, Minecraft.player.getMotion().z * 2.4);
         }

      }
   }

   private void handleGrimCollision() {
      float radius = (Float)this.collisionRadius.get();
      Minecraft var10002 = mc;
      List armorStands = mc.world.getEntitiesWithinAABB(ArmorStandEntity.class, Minecraft.player.getBoundingBox().grow((double)radius));
      var10002 = mc;
      List living = mc.world.getEntitiesWithinAABB(LivingEntity.class, Minecraft.player.getBoundingBox().grow((double)radius));
      int armorStandCount = armorStands.size();
      int livingCount = living.size();
      boolean canBoost = armorStandCount > 1 || livingCount > 1;
      if (canBoost) {
         Minecraft var10000 = mc;
         if (!Minecraft.player.isOnGround()) {
            float boost = armorStandCount > 1 ? (Float)this.collisionSpeed.get() / (float)armorStandCount : (Float)this.collisionSpeed.get() * 0.16F;
            MoveUtil.setMotion((double)boost);
         }
      }

   }

   private void handleMetaHvH() {
      Minecraft var10000 = mc;
      if (!Minecraft.player.isElytraFlying()) {
         float speedToApply = 0.0F;
         if (this.metaMode.is("Default")) {
            var10000 = mc;
            int var5;
            if (Minecraft.player.isPotionActive(Effects.SPEED)) {
               var10000 = mc;
               var5 = Minecraft.player.getActivePotionEffect(Effects.SPEED).getAmplifier();
            } else {
               var5 = 0;
            }

            int amp = var5;
            if (amp == 2) {
               speedToApply = 0.49665F;
            } else if (amp == 1) {
               speedToApply = 0.36F;
            } else {
               speedToApply = 0.24480002F;
            }
         } else {
            speedToApply = (Float)this.metaSpeed.get();
         }

         var10000 = mc;
         if (Minecraft.player.isPotionActive(Effects.SLOWNESS)) {
            speedToApply *= 0.835F;
         }

         var10000 = mc;
         if (!Minecraft.player.isOnGround()) {
            speedToApply *= 1.435F;
         }

         if ((Boolean)this.metaDamageBoost.get()) {
            long now = System.currentTimeMillis();
            if ((float)(now - this.lastDamageTime) < (Float)this.metaBoostDuration.get()) {
               speedToApply += (Float)this.metaBoostValue.get() / 10.0F;
            }
         }

         MoveUtil.setMotion((double)speedToApply);
      }
   }

   private void handleHolyWorld() {
      if (MoveUtil.isMoving()) {
         Minecraft var10000 = mc;
         if (!Minecraft.player.isElytraFlying()) {
            var10000 = mc;
            if (!Minecraft.player.isOnGround()) {
               var10000 = mc;
               if (!Minecraft.player.isInWater()) {
                  var10000 = mc;
                  if (!Minecraft.player.isInLava()) {
                     ++this.holyWorldTicks;
                     var10000 = mc;
                     Minecraft.player.setSprinting(false);
                     if (this.holyWorldTicks % 25 < 5) {
                        double radius = 0.55;
                        double radiusSq = radius * radius;
                        var10000 = mc;
                        Vector3d self = Minecraft.player.getPositionVec();
                        int nearby = 0;
                        double proximity = 0.0;
                        Vector3d bestPos = null;
                        double bestSq = Double.MAX_VALUE;
                        Iterator var12 = mc.world.getPlayers().iterator();

                        while(var12.hasNext()) {
                           PlayerEntity player = (PlayerEntity)var12.next();
                           Minecraft var10001 = mc;
                           if (player != Minecraft.player && player.isAlive()) {
                              Vector3d trackPos = player.getPositionVec();
                              double dx = trackPos.x - self.x;
                              double dz = trackPos.z - self.z;
                              double dSq = dx * dx + dz * dz;
                              if (!(dSq > radiusSq)) {
                                 ++nearby;
                                 proximity += 1.0 - Math.min(dSq / radiusSq, 1.0);
                                 if (dSq < bestSq) {
                                    bestSq = dSq;
                                    bestPos = trackPos;
                                 }
                              }
                           }
                        }

                        if (nearby > 0 && bestPos != null) {
                           double density = Math.min(1.0, (double)nearby / 4.0);
                           double prox = Math.min(1.0, proximity / (double)nearby);
                           double yaw = Math.atan2(bestPos.z - self.z, bestPos.x - self.x) - 1.5707963267948966;
                           double baseBoost = 0.055;
                           double dynamicBoost = baseBoost * (0.55 + 0.45 * density) * (0.7 + 0.3 * prox);
                           var10000 = mc;
                           Vector3d motion = Minecraft.player.getMotion();
                           double addX = -Math.sin(yaw) * dynamicBoost;
                           double addZ = Math.cos(yaw) * dynamicBoost;
                           double newX = motion.x + addX;
                           double newZ = motion.z + addZ;
                           double maxHorizontal = 0.82 + 0.24 * density + 0.1 * prox;
                           double horizontal = Math.sqrt(newX * newX + newZ * newZ);
                           if (horizontal > maxHorizontal) {
                              double scale = maxHorizontal / horizontal;
                              newX *= scale;
                              newZ *= scale;
                           }

                           var10000 = mc;
                           Minecraft.player.setVelocity(newX, motion.y, newZ);
                        }
                     }

                     return;
                  }
               }
            }
         }
      }

      this.holyWorldTicks = 0;
   }

   private void handleGrimTimer() {
      Minecraft var10000 = mc;
      if (Minecraft.player.isOnGround()) {
         ++this.grimTimerCounter;
         if (this.grimTimerCounter > 4) {
            this.grimTimerCounter = 0;
         }

      } else {
         this.grimTimerCounter = 0;
         if (MoveUtil.isMoving()) {
            this.setTimer(1.08F);
            ++this.grimTimerTicks;
            if (this.grimTimerTicks > 8) {
               this.setTimer(1.0F);
               this.grimTimerTicks = 0;
            }
         } else {
            this.setTimer(1.0F);
            this.grimTimerTicks = 0;
         }

      }
   }

   @EventTarget
   public void onEvent(EventOnMovePost event) {
      if (this.mode.is("RW") && this.canUseRW()) {
         this.setTimer(1.9F);
         if (this.ticks > 3) {
            double boost = 0.03;
            Minecraft var10000;
            Minecraft var10001;
            if (this.ticks % 2 == 0) {
               var10000 = mc;
               var10001 = mc;
               Minecraft.player.setMotion(Minecraft.player.getMotion().add(0.0, 0.03, 0.0));
               var10000 = mc;
               boost = Minecraft.player.isOnGround() ? 0.085 : 0.03;
            }

            double yaw = Math.toRadians(MoveUtil.getDirection(false));
            double x = -Math.sin(yaw);
            double z = Math.cos(yaw);
            var10000 = mc;
            var10001 = mc;
            Minecraft.player.setMotion(Minecraft.player.getMotion().add(x * boost, 0.0, z * boost));
         }

         ++this.ticks;
      } else {
         this.resetState();
      }
   }

   @EventTarget
   public void onEvent(EventInput event) {
      if (this.mode.is("RW")) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null && MoveUtil.isMoving()) {
            var10000 = mc;
            if (Minecraft.player.collidedVertically) {
               ++this.groundTicks;
            } else {
               this.groundTicks = 0;
            }

            if (this.groundTicks >= 1) {
               var10000 = mc;
               Minecraft.player.jump();
            }

            return;
         }
      }

      this.groundTicks = 0;
   }

   @EventTarget
   public void onEvent(EventPostUpdate event) {
      if (this.mode.is("RW")) {
         if (!this.canUseRW()) {
            this.resetState();
         } else {
            if (this.ticks % 2 == 0) {
               this.setTimer(0.3F);
               Minecraft var10000 = mc;
               Minecraft var10003 = mc;
               Minecraft.player.connection.sendPacket(new CEntityActionPacket(Minecraft.player, Action.START_FALL_FLYING));
            }

         }
      }
   }

   @EventTarget
   public void onEvent(EventPacket event) {
      if (this.mode.is("RW")) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null && event.isReceive()) {
            IPacket packet = event.getPacket();
            if (packet instanceof SPlayerPositionLookPacket) {
               if (this.ticks % 2 == 1) {
                  ++this.ticks;
               }

               this.setTimer(1.0F);
            }

            return;
         }
      }

   }

   public double getEntitySpeed(Entity entity) {
      Vector3d currentPos = entity.getPositionVec();
      Vector3d previousPos = (Vector3d)this.previousPositions.getOrDefault(entity, currentPos);
      double dx = currentPos.x - previousPos.x;
      double dz = currentPos.z - previousPos.z;
      double speed = Math.sqrt(dx * dx + dz * dz) * 20.0;
      this.previousPositions.put(entity, currentPos);
      return speed;
   }

   private double[] forward(double speed) {
      Minecraft var10000 = mc;
      float forward = Minecraft.player.movementInput.moveForward;
      var10000 = mc;
      float strafe = Minecraft.player.movementInput.moveStrafe;
      var10000 = mc;
      float yaw = Minecraft.player.rotationYaw;
      if (forward != 0.0F) {
         if (strafe > 0.0F) {
            yaw += forward > 0.0F ? -45.0F : 45.0F;
         } else if (strafe < 0.0F) {
            yaw += forward > 0.0F ? 45.0F : -45.0F;
         }

         strafe = 0.0F;
         if (forward > 0.0F) {
            forward = 1.0F;
         } else if (forward < 0.0F) {
            forward = -1.0F;
         }
      }

      double sin = Math.sin(Math.toRadians((double)(yaw + 90.0F)));
      double cos = Math.cos(Math.toRadians((double)(yaw + 90.0F)));
      return new double[]{(double)forward * speed * cos + (double)strafe * speed * sin, (double)forward * speed * sin - (double)strafe * speed * cos};
   }

   private boolean canUseRW() {
      Minecraft var10000 = mc;
      boolean var1;
      if (Minecraft.player != null && mc.world != null) {
         var10000 = mc;
         if (Minecraft.player.connection != null && MoveUtil.isMoving()) {
            var10000 = mc;
            if (!Minecraft.player.isPassenger()) {
               var10000 = mc;
               if (!Minecraft.player.abilities.isFlying) {
                  var1 = true;
                  return var1;
               }
            }
         }
      }

      var1 = false;
      return var1;
   }

   private void resetState() {
      this.ticks = 0;
      this.groundTicks = 0;
      this.setTimer(1.0F);
   }

   private void setTimer(float multiplier) {
      if (mc.timer != null) {
         mc.timer.setSpeed(multiplier);
      }

   }
}
