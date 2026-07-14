package sky.core.handlers.richidog;

import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import sky.core.events.EventUpdate;
import sky.core.modules.impl.combat.AttackAura;
import sky.core.utils.animations.advanced.InfinityAnimation;
import sky.core.utils.timer.Timer;

public class RichiBrain {
   static final Minecraft mc = Minecraft.getInstance();
   private Vector3d pos;
   private Vector3d motion;
   private float direction;
   private float yaw;
   private float body;
   private int speed;
   private final InfinityAnimation x;
   private final InfinityAnimation y;
   private final InfinityAnimation z;
   private final InfinityAnimation bodyAnim;
   private final InfinityAnimation yawAnim;
   private final InfinityAnimation pitchAnim;
   private boolean lay;
   private final Timer staying;
   public float prevLimbSwingAmount;
   public float limbSwingAmount;
   public float limbSwing;
   private PlayerEntity entity;

   public RichiBrain() {
      this.motion = Vector3d.ZERO;
      this.direction = random(0.0F, 360.0F);
      this.speed = 50;
      this.x = new InfinityAnimation();
      this.y = new InfinityAnimation();
      this.z = new InfinityAnimation();
      this.bodyAnim = new InfinityAnimation();
      this.yawAnim = new InfinityAnimation();
      this.pitchAnim = new InfinityAnimation();
      this.staying = new Timer();
   }

   public boolean isLay() {
      return this.lay;
   }

   public void reset() {
      this.pos = null;
      this.motion = Vector3d.ZERO;
      this.direction = random(0.0F, 360.0F);
      this.yaw = 0.0F;
      this.body = 0.0F;
      this.lay = false;
      this.prevLimbSwingAmount = 0.0F;
      this.limbSwingAmount = 0.0F;
      this.limbSwing = 0.0F;
   }

   public void onUpdate(EventUpdate event) {
      if (this.entity != null && mc.world != null) {
         Vector3d playerPos = this.entity.getPositionVec();
         if (this.pos == null || this.pos.distanceTo(playerPos) > 10.0) {
            this.pos = playerPos;
            this.x.animate((float)this.pos.x, 1);
            this.y.animate((float)this.pos.y, 1);
            this.z.animate((float)this.pos.z, 1);
         }

         this.motion = this.motion.add(0.0, -0.20000000298023224, 0.0);
         Vector3d newPos = this.pos.add(this.motion);
         double xMot;
         if (isBlockSolid(newPos.x, newPos.y, newPos.z)) {
            int blockY = (int)newPos.y;
            xMot = (double)(blockY + 1) + 0.1;
            newPos = new Vector3d(newPos.x, xMot, newPos.z);
            this.motion = new Vector3d(this.motion.x, 0.0, this.motion.z);
         }

         this.motion = new Vector3d(this.motion.x, 0.0, this.motion.z);
         LivingEntity target = AttackAura.target;
         if (target != null && this.entity instanceof ClientPlayerEntity) {
            if (isBlockSolid(newPos.x, newPos.y - 0.10000000149011612, newPos.z)) {
               this.motion = new Vector3d(this.motion.x, 0.6200000047683716, this.motion.z);
            }

            AxisAlignedBB box = new AxisAlignedBB(this.getPos().subtract(0.4, 0.0, 0.4), this.getPos().add(0.4, 0.4, 0.4));
            AxisAlignedBB targetbox = target.getBoundingBox().grow(-0.10000000149011612, 0.0, -0.10000000149011612);
            this.motion = this.motion.add(target.getPositionVec().subtract(newPos).normalize());
            if (box.maxX > targetbox.minX && box.maxY > targetbox.minY && box.maxZ > targetbox.minZ && box.minX < targetbox.maxX && box.minY < targetbox.maxY && box.minZ < targetbox.maxZ) {
               this.motion = this.motion.mul(-1.0, 1.0, -1.0);
            }
         } else if (newPos.distanceTo(playerPos) > 2.0) {
            this.motion = this.motion.add(playerPos.subtract(newPos).normalize());
         }

         this.handleRotation();
         this.pos = newPos;
         if (this.pos.distanceTo(playerPos) < 0.10000000149011612) {
            this.direction = random(0.0F, 360.0F);
            xMot = -Math.sin(Math.toRadians((double)this.direction)) * 0.1;
            double zMot = Math.cos(Math.toRadians((double)this.direction)) * 0.1;
            this.motion = this.motion.add(xMot, 0.0, zMot);
         }

         this.motion = this.motion.scale(0.5);
         this.speed = 150;
         this.x.animate((float)this.pos.x, this.speed);
         this.y.animate((float)this.pos.y, this.speed);
         this.z.animate((float)this.pos.z, this.speed);
         this.limbTick();
         if (Math.abs(this.pos.x - (double)this.x.get()) > 0.10000000149011612 || Math.abs(this.pos.z - (double)this.z.get()) > 0.10000000149011612) {
            this.staying.reset();
         }

         this.lay = this.staying.getElapsedTime() >= 1000L;
      }
   }

   private static boolean isBlockSolid(double x, double y, double z) {
      if (mc.world == null) {
         return false;
      } else {
         BlockPos bp = new BlockPos(x, y, z);
         return !mc.world.getBlockState(bp).getCollisionShape(mc.world, bp).isEmpty();
      }
   }

   private void handleRotation() {
      if (this.motion.x != 0.0 || this.motion.z != 0.0) {
         double angle = Math.atan2(this.motion.z, this.motion.x);
         this.yaw = (float)Math.toDegrees(angle) - 90.0F;
         this.yaw %= 360.0F;
         if (this.yaw < 0.0F) {
            this.yaw += 360.0F;
         }
      }

      Vector3d eye = this.entity.getPositionVec().add(0.0, (double)this.entity.getEyeHeight(), 0.0);
      Vector2f rotation = rotationToward(this.getPos(), eye);
      LivingEntity target = AttackAura.target;
      if (target != null && this.entity instanceof ClientPlayerEntity) {
         rotation = rotationToward(this.getPos(), target.getPositionVec());
      }

      float gradus = this.lay ? 200.0F : 150.0F;
      float gradus1 = this.lay ? 100.0F : 50.0F;
      if (rotation.x - this.yaw < -gradus || rotation.x - this.yaw > gradus) {
         this.yaw = rotation.x;
      }

      float shortestYawPath = ((this.yaw - this.body) % 360.0F + 540.0F) % 360.0F - 180.0F;
      if (!this.lay) {
         this.bodyAnim.animate(this.body + shortestYawPath, 150);
      }

      this.yawAnim.animate(MathHelper.clamp(rotation.x - this.yaw, -gradus1, gradus1), 150);
      this.pitchAnim.animate(rotation.y, 150);
      this.body += shortestYawPath;
   }

   private static Vector2f rotationToward(Vector3d from, Vector3d to) {
      double dx = to.x - from.x;
      double dy = to.y - from.y;
      double dz = to.z - from.z;
      double h = (double)MathHelper.sqrt(dx * dx + dz * dz);
      float yaw = (float)(Math.atan2(dz, dx) * 57.29577951308232) - 90.0F;
      float pitch = (float)(-(Math.atan2(dy, h) * 57.29577951308232));
      return new Vector2f(yaw, pitch);
   }

   public void limbTick() {
      this.prevLimbSwingAmount = this.limbSwingAmount;
      double d0 = (double)this.x.get() - this.pos.x;
      double d1 = 0.0;
      double d2 = (double)this.z.get() - this.pos.z;
      float f = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2) * 4.0F;
      if (f > 1.0F) {
         f = 1.0F;
      }

      this.limbSwingAmount += (f - this.limbSwingAmount) * 0.4F;
      this.limbSwing += this.limbSwingAmount;
   }

   public float getBody() {
      return this.bodyAnim.get();
   }

   public float getYaw() {
      return this.yawAnim.get();
   }

   public float getPitch() {
      return this.pitchAnim.get();
   }

   public Vector3d getPos() {
      return new Vector3d((double)this.x.get(), (double)this.y.get(), (double)this.z.get());
   }

   private static float random(float min, float max) {
      return (float)(ThreadLocalRandom.current().nextDouble() * (double)(max - min) + (double)min);
   }

   public void setEntity(PlayerEntity entity) {
      this.entity = entity;
   }
}
