package sky.core.modules.impl.miscellaneous;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import sky.core.SkyCore;
import sky.core.events.EventInput;
import sky.core.events.EventPacket;
import sky.core.events.EventUpdate;
import sky.core.handlers.impl.Rotation;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.modules.impl.combat.AttackAura;
import sky.core.utils.component.RotationAccess;
import sky.core.utils.component.impl.RotationComponent;
import sky.core.utils.player.InventoryUtil;
import sky.core.utils.player.MoveUtil;

public class TargetPearl extends Module {
   private static final int ROTATION_PRIORITY = 50;
   private static final float ROTATION_SPEED = 360.0F;
   private static final float ROTATION_EPSILON = 4.0F;
   private final BooleanSetting onlyTarget = new BooleanSetting("Только за таргетом", false);
   private final SliderSetting distance = new SliderSetting("Мин дистанция", 10.0F, 8.0F, 20.0F, 1.0F);
   private EnderPearlEntity targetPearl = null;
   private Vector3d cachedLanding = null;
   private long lastPearlScan = 0L;
   private long lastThrowTime = 0L;
   private long rotationHoldUntil = 0L;
   private boolean isThrowing = false;
   private Vector2f server = null;
   private boolean awaitingThrow = false;
   private float pendingYaw;
   private float pendingPitch;
   private int rotationWaitTicks = 0;
   public Entity rememberedTarget = null;
   private long rememberedTargetTime = 0L;
   private final Map pearlOwnerById = new HashMap();
   private final Map recentThrowers = new HashMap();
   private static final long TARGET_MEMORY_DURATION = 10000L;
   private static final int PEARL_SCAN_INTERVAL = 35;
   private static final int THROW_COOLDOWN = 2000;
   private static final int MAX_SIM_TICKS = 160;
   private static final double MAX_DISTANCE_FROM_TARGET = 1.5;
   private static final double MAX_ACCEPTABLE_ERROR = 2.0;

   public TargetPearl() {
      super("TargetPearl", "Кидает перл точно за перлом врага (через всё)", Category.Combat);
      this.addSettings(new Setting[]{this.onlyTarget, this.distance});
   }

   public static boolean isRotationActive() {
      TargetPearl module = (TargetPearl)SkyCore.getInstance().getModuleManager().getModule(TargetPearl.class);
      return module != null && module.isManagingRotation();
   }

   public boolean isManagingRotation() {
      if (!this.isEnabled()) {
         return false;
      } else {
         return this.awaitingThrow || this.isThrowing || this.rotationHoldUntil > 0L && System.currentTimeMillis() < this.rotationHoldUntil;
      }
   }

   private boolean check() {
      return this.isEnabled() && this.isThrowing && this.server != null;
   }

   @EventTarget
   public void onEvent(EventInput event) {
      if (event != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null && mc.world != null) {
            var10000 = mc;
            if (!Minecraft.player.isElytraFlying()) {
               if (this.rotationHoldUntil > 0L && System.currentTimeMillis() >= this.rotationHoldUntil) {
                  this.isThrowing = false;
                  this.server = null;
                  this.rotationHoldUntil = 0L;
               }

               if (this.check()) {
                  MoveUtil.fixMovement(event, this.server.x);
               }

               return;
            }
         }
      }

   }

   @EventTarget
   public void onPacket(EventPacket event) {
      if (this.isEnabled() && event != null && event.isReceive() && mc.world != null) {
         Object packet = event.getPacket();
         Minecraft var10000 = mc;
         int var9;
         if (Minecraft.player != null) {
            var10000 = mc;
            var9 = Minecraft.player.ticksExisted;
         } else {
            var9 = 0;
         }

         int tick = var9;
         if (packet instanceof SAnimateHandPacket) {
            SAnimateHandPacket animate = (SAnimateHandPacket)packet;
            if (animate.getAnimationType() == 0) {
               this.recentThrowers.put(animate.getEntityID(), tick + 12);
            }

         } else {
            if (packet instanceof SSpawnObjectPacket) {
               SSpawnObjectPacket spawn = (SSpawnObjectPacket)packet;
               if (spawn.getType() == EntityType.ENDER_PEARL) {
                  int ownerId = spawn.getData();
                  if (ownerId != 0) {
                     this.pearlOwnerById.put(spawn.getEntityID(), ownerId);
                     return;
                  }

                  Iterator var6 = this.recentThrowers.entrySet().iterator();

                  while(var6.hasNext()) {
                     Map.Entry entry = (Map.Entry)var6.next();
                     if ((Integer)entry.getValue() >= tick) {
                        this.pearlOwnerById.put(spawn.getEntityID(), (Integer)entry.getKey());
                        break;
                     }
                  }

                  Entity nearest = this.findNearestThrower(spawn.getX(), spawn.getY(), spawn.getZ(), 4.5);
                  if (nearest != null) {
                     this.pearlOwnerById.put(spawn.getEntityID(), nearest.getEntityId());
                  }
               }
            }

         }
      }
   }

   @EventTarget
   public void onEvent(EventUpdate event) {
      if (event != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null && mc.world != null) {
            var10000 = mc;
            if (!Minecraft.player.isElytraFlying()) {
               if (this.awaitingThrow) {
                  this.processPendingThrow();
                  return;
               }

               this.tryThrow();
               return;
            }
         }
      }

   }

   private void processPendingThrow() {
      if (this.canThrow() && this.cachedLanding != null) {
         if (!this.applyThrowRotation(this.pendingYaw, this.pendingPitch)) {
            ++this.rotationWaitTicks;
            if (this.rotationWaitTicks < 10) {
               return;
            }
         }

         this.rotationWaitTicks = 0;
         Minecraft var10000 = mc;
         if (!Minecraft.player.getCooldownTracker().hasCooldown(Items.ENDER_PEARL) && InventoryUtil.getItemSlot(Items.ENDER_PEARL) != -1) {
            InventoryUtil.findItemAndThrow(Items.ENDER_PEARL);
            this.lastThrowTime = System.currentTimeMillis();
         }

         this.awaitingThrow = false;
         this.rotationHoldUntil = System.currentTimeMillis() + 50L;
      } else {
         this.cancelPendingThrow();
      }
   }

   private void cancelPendingThrow() {
      this.awaitingThrow = false;
      this.isThrowing = false;
      this.server = null;
   }

   private void tryThrow() {
      if (System.currentTimeMillis() - this.lastThrowTime >= 2000L) {
         if (this.canThrow()) {
            this.updateTargetPearl();
            if (this.cachedLanding != null) {
               float[] rot = this.calculateYawPitch(this.cachedLanding);
               if (rot == null) {
                  rot = this.findAnyWorkingRotation(this.cachedLanding);
                  if (rot == null) {
                     return;
                  }
               }

               Vector3d predicted = this.simulateTrajectory(rot[0], rot[1]);
               if (predicted != null) {
                  double distToTarget = this.cachedLanding.distanceTo(predicted);
                  Minecraft var10000 = mc;
                  Vector3d eye = Minecraft.player.getEyePosition(1.0F);
                  double distToCached = eye.distanceTo(this.cachedLanding);
                  double heightDiff = this.cachedLanding.y - eye.y;
                  boolean isHighTarget = heightDiff > 5.0;
                  double maxAllowedError = distToCached > 60.0 ? 3.0 : 2.25;
                  if (isHighTarget) {
                     maxAllowedError = 4.5;
                  }

                  if (this.hasObstacleBetween(eye, this.cachedLanding)) {
                     maxAllowedError = isHighTarget ? 6.0 : 3.75;
                  }

                  if (distToTarget > maxAllowedError) {
                     rot = this.findAnyWorkingRotation(this.cachedLanding);
                     if (rot == null) {
                        return;
                     }
                  }
               }

               this.pendingYaw = rot[0];
               this.pendingPitch = rot[1];
               this.isThrowing = true;
               this.server = new Vector2f(this.pendingYaw, this.pendingPitch);
               this.awaitingThrow = true;
               this.applyThrowRotation(this.pendingYaw, this.pendingPitch);
            }
         }
      }
   }

   private boolean canThrow() {
      Minecraft var10000 = mc;
      return !Minecraft.player.getCooldownTracker().hasCooldown(Items.ENDER_PEARL);
   }

   private void updateTargetPearl() {
      this.cleanupPearlTracking();
      long now = System.currentTimeMillis();
      if (now - this.lastPearlScan < 35L) {
         if (this.targetPearl != null && this.targetPearl.isAlive() && this.cachedLanding != null) {
            return;
         }

         if (now - this.lastPearlScan < 17L) {
            return;
         }
      }

      this.lastPearlScan = now;
      this.cachedLanding = null;
      this.targetPearl = this.findBestPearl();
      if (this.targetPearl != null && this.targetPearl.isAlive()) {
         this.cachedLanding = this.predictLanding(this.targetPearl);
         if (this.cachedLanding != null && !this.isValidLanding(this.cachedLanding)) {
            this.cachedLanding = null;
         }
      }

   }

   private boolean isValidLanding(Vector3d landing) {
      BlockPos pos = new BlockPos(landing);

      for(int i = 1; i <= 4; ++i) {
         if (!mc.world.getBlockState(pos.down(i)).getCollisionShape(mc.world, pos.down(i)).isEmpty()) {
            return true;
         }
      }

      return landing.y <= 1.0;
   }

   private void cleanupPearlTracking() {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         var10000 = mc;
         int tick = Minecraft.player.ticksExisted;
         this.recentThrowers.entrySet().removeIf((entry) -> {
            return (Integer)entry.getValue() < tick;
         });
         this.pearlOwnerById.entrySet().removeIf((entry) -> {
            return mc.world.getEntityByID((Integer)entry.getKey()) == null;
         });
      }
   }

   private LivingEntity resolveAuraTarget() {
      long currentTime = System.currentTimeMillis();
      LivingEntity currentAuraTarget = AttackAura.getTarget();
      if (currentAuraTarget == null) {
         currentAuraTarget = RotationAccess.target;
      }

      if (currentAuraTarget != null && currentAuraTarget.isAlive() && !currentAuraTarget.removed) {
         this.rememberedTarget = currentAuraTarget;
         this.rememberedTargetTime = currentTime;
         return currentAuraTarget;
      } else {
         if (this.rememberedTarget != null) {
            if (!this.rememberedTarget.isAlive() || this.rememberedTarget.removed) {
               this.rememberedTarget = null;
               this.rememberedTargetTime = 0L;
               return null;
            }

            if (currentTime - this.rememberedTargetTime < 10000L) {
               return (LivingEntity)this.rememberedTarget;
            }

            this.rememberedTarget = null;
            this.rememberedTargetTime = 0L;
         }

         return null;
      }
   }

   private Entity getPearlShooter(EnderPearlEntity pearl) {
      this.assignPearlOwner(pearl);
      Entity shooter = pearl.getShooter();
      if (shooter != null && shooter.isAlive()) {
         return shooter;
      } else {
         shooter = pearl.func_234616_v_();
         if (shooter != null) {
            return shooter;
         } else {
            Integer mappedOwner = (Integer)this.pearlOwnerById.get(pearl.getEntityId());
            if (mappedOwner != null && mc.world != null) {
               return mc.world.getEntityByID(mappedOwner);
            } else {
               int ownerId = pearl.getOwnerEntityId();
               return ownerId != 0 && mc.world != null ? mc.world.getEntityByID(ownerId) : null;
            }
         }
      }
   }

   private void assignPearlOwner(EnderPearlEntity pearl) {
      if (pearl != null && mc.world != null) {
         Entity knownOwner = this.getPearlShooterWithoutAssign(pearl);
         if (knownOwner != null) {
            if (pearl.getShooter() == null) {
               pearl.setShooter(knownOwner);
            }

         } else if (pearl.ticksExisted <= 8) {
            Entity nearest = this.findNearestThrower(pearl.getPosX(), pearl.getPosY(), pearl.getPosZ(), 4.0);
            if (nearest != null) {
               pearl.setShooter(nearest);
               this.pearlOwnerById.put(pearl.getEntityId(), nearest.getEntityId());
            }

         }
      }
   }

   private Entity getPearlShooterWithoutAssign(EnderPearlEntity pearl) {
      Entity shooter = pearl.getShooter();
      if (shooter != null && shooter.isAlive()) {
         return shooter;
      } else {
         shooter = pearl.func_234616_v_();
         if (shooter != null) {
            return shooter;
         } else {
            Integer mappedOwner = (Integer)this.pearlOwnerById.get(pearl.getEntityId());
            if (mappedOwner != null) {
               return mc.world.getEntityByID(mappedOwner);
            } else {
               int ownerId = pearl.getOwnerEntityId();
               return ownerId != 0 ? mc.world.getEntityByID(ownerId) : null;
            }
         }
      }
   }

   private Entity findNearestThrower(double x, double y, double z, double maxDistance) {
      PlayerEntity nearest = null;
      double nearestDist = maxDistance * maxDistance;
      Iterator var12 = mc.world.getPlayers().iterator();

      while(var12.hasNext()) {
         PlayerEntity player = (PlayerEntity)var12.next();
         Minecraft var10001 = mc;
         if (player != Minecraft.player) {
            double eyeDist = player.getEyePosition(1.0F).squareDistanceTo(x, y, z);
            double bodyDist = player.getPositionVec().squareDistanceTo(x, y, z);
            double dist = Math.min(eyeDist, bodyDist);
            if (dist < nearestDist) {
               nearestDist = dist;
               nearest = player;
            }
         }
      }

      return nearest;
   }

   private boolean isPearlFromEntity(EnderPearlEntity pearl, Entity entity) {
      if (pearl != null && entity != null) {
         Entity shooter = this.getPearlShooter(pearl);
         if (shooter != null) {
            return this.isSameEntity(shooter, entity);
         } else {
            int ownerId = pearl.getOwnerEntityId();
            if (ownerId != 0 && ownerId == entity.getEntityId()) {
               return true;
            } else {
               UUID ownerUuid = pearl.getOwnerUuid();
               return ownerUuid != null && ownerUuid.equals(entity.getUniqueID());
            }
         }
      } else {
         return false;
      }
   }

   private boolean isSameEntity(Entity a, Entity b) {
      if (a != null && b != null) {
         return a == b || a.getEntityId() == b.getEntityId() || a.getUniqueID().equals(b.getUniqueID());
      } else {
         return false;
      }
   }

   private EnderPearlEntity findBestPearl() {
      Minecraft var10001 = mc;
      Minecraft var10002 = mc;
      List entities = mc.world.getEntitiesWithinAABBExcludingEntity(Minecraft.player, Minecraft.player.getBoundingBox().grow(130.0));
      EnderPearlEntity best = null;
      double bestDist = Double.MAX_VALUE;
      double minDist = (double)(Float)this.distance.get();
      LivingEntity targetToUse = (Boolean)this.onlyTarget.get() ? this.resolveAuraTarget() : null;
      if ((Boolean)this.onlyTarget.get() && targetToUse == null) {
         return null;
      } else {
         Iterator var8 = entities.iterator();

         while(true) {
            EnderPearlEntity pearl;
            Entity shooter;
            do {
               do {
                  do {
                     Entity e;
                     do {
                        if (!var8.hasNext()) {
                           return best;
                        }

                        e = (Entity)var8.next();
                     } while(!(e instanceof EnderPearlEntity));

                     pearl = (EnderPearlEntity)e;
                  } while(!pearl.isAlive());

                  this.assignPearlOwner(pearl);
                  shooter = this.getPearlShooter(pearl);
                  if (shooter == null) {
                     break;
                  }

                  var10002 = mc;
               } while(this.isSameEntity(shooter, Minecraft.player));
            } while((Boolean)this.onlyTarget.get() && !this.isPearlFromEntity(pearl, targetToUse));

            Vector3d landing = this.predictLanding(pearl);
            if (landing != null) {
               Minecraft var10000 = mc;
               double dist = Minecraft.player.getPositionVec().distanceTo(landing);
               if (dist >= minDist && dist <= 120.0 && dist < bestDist) {
                  best = pearl;
                  bestDist = dist;
               }
            }
         }
      }
   }

   private Vector3d predictLanding(EnderPearlEntity pearl) {
      Vector3d pos = pearl.getPositionVec();
      Vector3d vel = pearl.getMotion();

      for(int i = 0; i < 160; ++i) {
         Vector3d next = pos.add(vel);
         vel = vel.scale(0.99).subtract(0.0, 0.03, 0.0);
         if (next.y <= 0.0) {
            return this.snapToBlockCenter(next);
         }

         BlockPos bp = new BlockPos(next);
         if (!mc.world.getBlockState(bp).getCollisionShape(mc.world, bp).isEmpty()) {
            return this.snapToBlockCenter(next);
         }

         pos = next;
      }

      return null;
   }

   private Vector3d snapToBlockCenter(Vector3d vec) {
      return new Vector3d((double)MathHelper.floor(vec.x) + 0.5, (double)MathHelper.floor(vec.y), (double)MathHelper.floor(vec.z) + 0.5);
   }

   private boolean hasObstacleBetween(Vector3d start, Vector3d end) {
      Vector3d direction = end.subtract(start);
      double distanceToTarget = direction.length();
      Vector3d normalized = direction.normalize();
      int steps = (int)(distanceToTarget / 0.5) + 1;

      for(int i = 1; i < steps; ++i) {
         Vector3d checkPos = start.add(normalized.scale((double)i * 0.5));
         BlockPos bp = new BlockPos(checkPos);
         if (!mc.world.getBlockState(bp).getCollisionShape(mc.world, bp).isEmpty()) {
            return true;
         }
      }

      return false;
   }

   private float[] calculateYawPitch(Vector3d target) {
      Minecraft var10000 = mc;
      Vector3d eye = Minecraft.player.getEyePosition(1.0F);
      double dx = target.x - eye.x;
      double dz = target.z - eye.z;
      float yaw = (float)Math.toDegrees(Math.atan2(dz, dx)) - 90.0F;
      double dist = eye.distanceTo(target);
      double heightDiff = target.y - eye.y;
      boolean isHighTarget = heightDiff > 5.0;
      float maxPitch = isHighTarget ? 89.0F : 85.0F;
      float minPitch = dist > 60.0 ? -50.0F : -30.0F;
      if (isHighTarget) {
         minPitch = -80.0F;
      }

      float step = dist > 60.0 ? 0.5F : 0.42F;
      if (isHighTarget) {
         step = 0.3F;
      }

      float bestPitch = 0.0F;
      int bestTicks = Integer.MAX_VALUE;
      double bestError = Double.MAX_VALUE;
      double maxError = isHighTarget ? 4.0 : 2.0;

      for(float pitch = maxPitch; pitch >= minPitch; pitch -= step) {
         SimulationResult res = this.simulateWithTicks(yaw, pitch, target);
         if (res != null && res.error <= maxError && (res.ticks < bestTicks || res.ticks == bestTicks && res.error < bestError)) {
            bestTicks = res.ticks;
            bestPitch = pitch;
            bestError = res.error;
         }
      }

      if (bestTicks != Integer.MAX_VALUE) {
         return new float[]{yaw, MathHelper.clamp(bestPitch, -90.0F, 90.0F)};
      } else {
         return null;
      }
   }

   private float[] findAnyWorkingRotation(Vector3d target) {
      Minecraft var10000 = mc;
      Vector3d eye = Minecraft.player.getEyePosition(1.0F);
      double dx = target.x - eye.x;
      double dz = target.z - eye.z;
      float baseYaw = (float)Math.toDegrees(Math.atan2(dz, dx)) - 90.0F;
      double dist = eye.distanceTo(target);
      double heightDiff = target.y - eye.y;
      boolean isHighTarget = heightDiff > 5.0;
      float maxPitch = isHighTarget ? 89.0F : 85.0F;
      float minPitch = dist > 60.0 ? -70.0F : -50.0F;
      if (isHighTarget) {
         minPitch = -85.0F;
      }

      float step = dist > 60.0 ? 1.2F : 1.5F;
      if (isHighTarget) {
         step = 1.0F;
      }

      float yawRange = dist > 60.0 ? 30.0F : 20.0F;
      if (isHighTarget) {
         yawRange = 40.0F;
      }

      float yawStep = dist > 60.0 ? 3.0F : 2.5F;
      if (isHighTarget) {
         yawStep = 2.0F;
      }

      double baseMaxError = dist > 60.0 ? 3.0 : 2.25;
      if (isHighTarget) {
         baseMaxError = 4.5;
      }

      float pitch;
      for(pitch = maxPitch; pitch >= minPitch; pitch -= step) {
         Vector3d landing = this.simulateTrajectory(baseYaw, pitch);
         if (landing != null && target.distanceTo(landing) <= baseMaxError) {
            return new float[]{baseYaw, MathHelper.clamp(pitch, -90.0F, 90.0F)};
         }
      }

      float yaw;
      Vector3d landing;
      float yawOffset;
      for(pitch = -yawRange; pitch <= yawRange; pitch += yawStep) {
         if (pitch != 0.0F) {
            yawOffset = baseYaw + pitch;

            for(yaw = maxPitch; yaw >= minPitch; yaw -= step) {
               landing = this.simulateTrajectory(yawOffset, yaw);
               if (landing != null && target.distanceTo(landing) <= baseMaxError) {
                  return new float[]{yawOffset, MathHelper.clamp(yaw, -90.0F, 90.0F)};
               }
            }
         }
      }

      if (isHighTarget) {
         for(pitch = 89.0F; pitch >= 75.0F; --pitch) {
            for(yawOffset = -yawRange; yawOffset <= yawRange; yawOffset += yawStep) {
               yaw = baseYaw + yawOffset;
               landing = this.simulateTrajectory(yaw, pitch);
               if (landing != null && target.distanceTo(landing) <= baseMaxError * 1.5) {
                  return new float[]{yaw, MathHelper.clamp(pitch, -90.0F, 90.0F)};
               }
            }
         }
      }

      for(pitch = maxPitch; pitch >= 70.0F; pitch -= 2.0F) {
         for(yawOffset = -yawRange; yawOffset <= yawRange; yawOffset += yawStep * 2.0F) {
            yaw = baseYaw + yawOffset;
            landing = this.simulateTrajectory(yaw, pitch);
            double maxError = isHighTarget ? 6.0 : 3.75;
            if (landing != null && target.distanceTo(landing) <= maxError) {
               return new float[]{yaw, MathHelper.clamp(pitch, -90.0F, 90.0F)};
            }
         }
      }

      return null;
   }

   private SimulationResult simulateWithTicks(float yaw, float pitch, Vector3d target) {
      Vector3d pos = this.getThrowPos(yaw, pitch);
      Vector3d motion = this.getThrowMotion(yaw, pitch);

      for(int tick = 0; tick < 160; ++tick) {
         pos = pos.add(motion);
         motion = motion.scale(0.99).subtract(0.0, 0.03, 0.0);
         if (pos.y <= 0.0) {
            return new SimulationResult(this.snapToBlockCenter(pos).distanceTo(target), tick + 1);
         }

         BlockPos bp = new BlockPos(pos);
         if (!mc.world.getBlockState(bp).getCollisionShape(mc.world, bp).isEmpty()) {
            return new SimulationResult(this.snapToBlockCenter(pos).distanceTo(target), tick + 1);
         }
      }

      return null;
   }

   private Vector3d simulateTrajectory(float yaw, float pitch) {
      Vector3d pos = this.getThrowPos(yaw, pitch);
      Vector3d motion = this.getThrowMotion(yaw, pitch);

      for(int i = 0; i < 160; ++i) {
         pos = pos.add(motion);
         motion = motion.scale(0.99).subtract(0.0, 0.03, 0.0);
         if (pos.y <= 0.0) {
            return this.snapToBlockCenter(pos);
         }

         BlockPos bp = new BlockPos(pos);
         if (!mc.world.getBlockState(bp).getCollisionShape(mc.world, bp).isEmpty()) {
            return this.snapToBlockCenter(pos);
         }
      }

      return null;
   }

   private Vector3d getThrowPos(float yaw, float pitch) {
      float yr = (float)Math.toRadians((double)yaw);
      Minecraft var10000 = mc;
      double x = Minecraft.player.getPosX() - (double)MathHelper.cos(yr) * 0.16;
      var10000 = mc;
      double var10 = Minecraft.player.getPosY();
      Minecraft var10001 = mc;
      double y = var10 + (double)Minecraft.player.getEyeHeight() - 0.1;
      var10000 = mc;
      double z = Minecraft.player.getPosZ() - (double)MathHelper.sin(yr) * 0.16;
      return new Vector3d(x, y, z);
   }

   private Vector3d getThrowMotion(float yaw, float pitch) {
      double v = 1.5;
      float yr = (float)Math.toRadians((double)yaw);
      float pr = (float)Math.toRadians((double)pitch);
      double vx = (double)(-MathHelper.sin(yr) * MathHelper.cos(pr)) * v;
      double vy = (double)(-MathHelper.sin(pr)) * v;
      double vz = (double)(MathHelper.cos(yr) * MathHelper.cos(pr)) * v;
      Minecraft var10001 = mc;
      vy += Minecraft.player.getMotion().y;
      return new Vector3d(vx, vy, vz);
   }

   private boolean applyThrowRotation(float yaw, float pitch) {
      float clampedPitch = MathHelper.clamp(pitch, -90.0F, 90.0F);
      Rotation target = new Rotation(yaw, clampedPitch);
      RotationComponent.update(target, 360.0F, 360.0F, 360.0F, 360.0F, 8, 50, false);
      Minecraft var10002 = mc;
      return (new Rotation(Minecraft.player)).getDelta(target) <= 4.0F;
   }

   public void onDisable() {
      this.awaitingThrow = false;
      this.isThrowing = false;
      this.targetPearl = null;
      this.cachedLanding = null;
      this.server = null;
      this.rotationHoldUntil = 0L;
      this.rememberedTarget = null;
      this.rememberedTargetTime = 0L;
      this.rotationWaitTicks = 0;
      this.pearlOwnerById.clear();
      this.recentThrowers.clear();
      RotationComponent.getInstance().stopRotation();
      super.onDisable();
   }

   private static class SimulationResult {
      final double error;
      final int ticks;

      SimulationResult(double error, int ticks) {
         this.error = error;
         this.ticks = ticks;
      }
   }
}
