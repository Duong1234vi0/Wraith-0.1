package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import sky.core.events.EventCriticalHit;
import sky.core.events.EventMotion;
import sky.core.events.EventPopTotem;
import sky.core.events.EventRender3D;
import sky.core.events.EventSwapWorld;
import sky.core.events.EventTotemParticle;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.Wrapper;
import sky.core.utils.math.MathUtil;
import sky.core.utils.math.TimeUtil;
import sky.core.utils.player.MoveUtil;
import sky.core.utils.render.RenderUtil;

public class Particles extends Module {
   private final ModeSetting type = new ModeSetting("Режим", "Звездочки", new String[]{"Сердечки", "Снежинки", "Доллары", "Звездочки", "Тыковки", "Бубенцы", "Сияние"});
   private final MultiBooleanSetting reason = new MultiBooleanSetting("Добавлять при", new BooleanSetting[]{new BooleanSetting("Бездействии", true), new BooleanSetting("Беге", false), new BooleanSetting("Крите", false), new BooleanSetting("Падении перла", false), new BooleanSetting("Падении трезубца", false), new BooleanSetting("Падении стрелы", false), new BooleanSetting("Сносе тотема", false)});
   private final SliderSetting count = new SliderSetting("Количество", 10.0F, 2.0F, 40.0F, 1.0F, () -> {
      return this.reason.is("Бездействии");
   });
   private final BooleanSetting glow = new BooleanSetting("Свечение", true);
   private final ArrayList particles = new ArrayList();
   private boolean isPlayerTotem = false;

   public Particles() {
      super("Particles", "Добавляет частицы при разных условиях", Category.Visuals);
      this.addSettings(new Setting[]{this.type, this.reason, this.count, this.glow});
   }

   @EventTarget
   public void onEvent(EventRender3D event) {
      this.particles.removeIf((particlex) -> {
         return particlex.time.hasTimeElapsed(particlex.lifeTime);
      });
      if (!this.particles.isEmpty()) {
         Quaternion rotation = new Quaternion(mc.getRenderManager().info.getRotation());
         rotation.multiply(Vector3f.ZP.rotationDegrees(180.0F));
         Vector3f right3f = new Vector3f(1.0F, 0.0F, 0.0F);
         Vector3f up3f = new Vector3f(0.0F, 1.0F, 0.0F);
         Vector3f forward3f = new Vector3f(0.0F, 0.0F, 1.0F);
         right3f.transform(rotation);
         up3f.transform(rotation);
         forward3f.transform(rotation);
         ResourceLocation texture = new ResourceLocation("SkyCore/icons/world_render/" + getTexturePath((String)this.type.get()));
         Iterator var7 = this.particles.iterator();

         while(var7.hasNext()) {
            Particle particle = (Particle)var7.next();
            particle.update();
            Vector3d toCenter = particle.position.subtract(mc.getRenderManager().info.getProjectedView());
            double halfSize = (double)(particle.size * 0.5F);
            AxisAlignedBB aabb = new AxisAlignedBB(particle.position.x - halfSize, particle.position.y - halfSize, particle.position.z - halfSize, particle.position.x + halfSize, particle.position.y + halfSize, particle.position.z + halfSize);
            if (mc.worldRenderer.getClippinghelper().isBoundingBoxInFrustum(aabb)) {
               Vector3d halfRight = (new Vector3d((double)right3f.getX(), (double)right3f.getY(), (double)right3f.getZ())).scale(halfSize);
               Vector3d halfUp = (new Vector3d((double)up3f.getX(), (double)up3f.getY(), (double)up3f.getZ())).scale(halfSize);
               Vector3d p0 = toCenter.subtract(halfRight).subtract(halfUp);
               Vector3d p1 = toCenter.add(halfRight).subtract(halfUp);
               Vector3d p2 = toCenter.add(halfRight).add(halfUp);
               Vector3d p3 = toCenter.subtract(halfRight).add(halfUp);
               int color = particle.color & 16777215 | (int)(particle.alpha * 255.0F) << 24;
               float p0x = (float)p0.x;
               float p0y = (float)p0.y;
               float p0z = (float)p0.z;
               float p1x = (float)p1.x;
               float p1y = (float)p1.y;
               float p1z = (float)p1.z;
               float p2x = (float)p2.x;
               float p2y = (float)p2.y;
               float p2z = (float)p2.z;
               float p3x = (float)p3.x;
               float p3y = (float)p3.y;
               float p3z = (float)p3.z;
               RenderUtil.drawImage3DQuad(texture, (Boolean)this.glow.get(), p0x, p0y, p0z, p1x, p1y, p1z, p2x, p2y, p2z, p3x, p3y, p3z, color);
               if ((Boolean)this.glow.get()) {
                  RenderUtil.drawImage3DQuad(texture, (Boolean)this.glow.get(), p0x, p0y, p0z, p1x, p1y, p1z, p2x, p2y, p2z, p3x, p3y, p3z, color);
               }
            }
         }

         RenderUtil.flushImage3DBatch();
      }
   }

   @EventTarget
   public void onEvent(EventMotion event) {
      if (this.reason.is("Беге") && MoveUtil.isMoving()) {
         Minecraft var10000 = mc;
         Minecraft var10001 = mc;
         double var27 = Minecraft.player.motion.x * Minecraft.player.motion.x;
         var10001 = mc;
         Minecraft var10002 = mc;
         double speed = Math.sqrt(var27 + Minecraft.player.motion.z * Minecraft.player.motion.z);
         Vector3d direction;
         if (speed < 0.01) {
            var10000 = mc;
            direction = Minecraft.player.getLookVec().scale(-1.0);
         } else {
            var10000 = mc;
            if (Minecraft.player.isElytraFlying()) {
               var10000 = mc;
               direction = Minecraft.player.motion.normalize().scale(-1.0);
            } else {
               var10002 = mc;
               double var28 = -Minecraft.player.motion.x / speed;
               Minecraft var10004 = mc;
               direction = new Vector3d(var28, 0.0, -Minecraft.player.motion.z / speed);
            }
         }

         var10000 = mc;
         double distanceBehind = (Minecraft.player.isElytraFlying() ? 1.2 : 0.5) + (speed > 0.1 ? speed * 1.5 : 0.0);
         double offsetX = (double)MathUtil.random(-0.35F, 0.35F);
         double offsetZ = (double)MathUtil.random(-0.35F, 0.35F);
         long life = (long)MathUtil.random(1500.0F, 2000.0F);
         var10000 = mc;
         double posX = Minecraft.player.getPosX() + direction.x * distanceBehind + offsetX;
         var10000 = mc;
         if (Minecraft.player.isElytraFlying()) {
            var10000 = mc;
            var27 = Minecraft.player.getPosY();
            var10001 = mc;
            var27 = var27 + (double)Minecraft.player.getHeight() / 2.0 + direction.y * distanceBehind + (double)MathUtil.random(-0.35F, 0.35F);
         } else {
            var10000 = mc;
            var27 = Minecraft.player.getPosY();
            var10002 = mc;
            var27 += (double)MathUtil.random(0.2F, Minecraft.player.getHeight() + 0.1F);
         }

         double posY = var27;
         var10000 = mc;
         double posZ = Minecraft.player.getPosZ() + direction.z * distanceBehind + offsetZ;
         double halfSize = 0.5;
         AxisAlignedBB aabb = new AxisAlignedBB(posX - halfSize, posY - halfSize, posZ - halfSize, posX + halfSize, posY + halfSize, posZ + halfSize);
         if (!mc.worldRenderer.getClippinghelper().isBoundingBoxInFrustum(aabb)) {
            return;
         }

         Vector3d velocity = direction.scale(0.075).add(new Vector3d((double)MathUtil.random(-0.01F, 0.01F), (double)MathUtil.random(-0.05F, 0.01F), (double)MathUtil.random(-0.01F, 0.01F))).scale(0.10000000149011612);
         this.addParticles(posX, posY, posZ, velocity, ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL), 0.3F, life, 3.0F, 4.999999873689376E-6);
      }

      Iterator var23 = mc.world.getAllEntities().iterator();

      while(var23.hasNext()) {
         Entity entity = (Entity)var23.next();
         if (this.reason.is("Падении перла") && entity instanceof EnderPearlEntity pearl) {
            if (!pearl.isOnGround()) {
               this.createParticles(pearl.getPositionVec());
            }
         }

         if (this.reason.is("Падении трезубца") && entity instanceof TridentEntity trident) {
            if (this.isProjectileFlying(trident)) {
               this.createParticles(trident.getPositionVec());
            }
         }

         if (this.reason.is("Падении стрелы") && entity instanceof ArrowEntity arrow) {
            if (this.isProjectileFlying(arrow)) {
               this.createParticles(arrow.getPositionVec());
            }
         }
      }

   }

   private boolean isProjectileFlying(Entity projectile) {
      if (!projectile.isOnGround() && !(projectile.getMotion().lengthSquared() <= 1.0E-4)) {
         Vector3d pos = projectile.getPositionVec();
         Vector3d motion = projectile.getMotion().normalize().scale(0.5);
         BlockPos currentPos = new BlockPos(pos);
         BlockPos frontPos = new BlockPos(pos.add(motion));
         return mc.world.getBlockState(currentPos).isAir() && mc.world.getBlockState(frontPos).isAir();
      } else {
         return false;
      }
   }

   private void createParticles(Vector3d position) {
      double halfSize = 0.5;
      AxisAlignedBB aabb = new AxisAlignedBB(position.x - halfSize, position.y - halfSize, position.z - halfSize, position.x + halfSize, position.y + halfSize, position.z + halfSize);
      if (mc.worldRenderer.getClippinghelper().isBoundingBoxInFrustum(aabb)) {
         for(int i = 0; i < 2; ++i) {
            double distance = 0.0;
            double angle = Math.toRadians((double)MathUtil.random(0.0F, 360.0F));
            double cosAngle = Math.cos(angle);
            double sinAngle = Math.sin(angle);
            double dx = cosAngle * distance;
            double dz = sinAngle * distance;
            double dy = (double)MathUtil.random(0.1F, 0.35F);
            Vector3d particlePos = new Vector3d(position.x + dx, position.y + dy, position.z + dz);
            long life = (long)MathUtil.random(2000.0F, 2500.0F);
            float speedMin = MathUtil.random(0.015F, 0.0375F);
            float speedMax = MathUtil.random(0.05F, 0.075F);
            double speedFinal = (double)MathUtil.random(speedMin, speedMax);
            double speedFinalY = speedFinal * 0.4;
            double angleVel = Math.toRadians((double)MathUtil.random(0.0F, 360.0F));
            double cosVel = Math.cos(angleVel);
            double sinVel = Math.sin(angleVel);
            double velX = cosVel * speedFinal;
            double velZ = sinVel * speedFinal;
            double velY = (double)MathUtil.random((float)(-speedFinalY), (float)speedFinalY);
            this.addParticles(particlePos.x, particlePos.y, particlePos.z, new Vector3d(velX, velY, velZ), ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL), 0.3F, life, 2.0F, 4.999999873689376E-5);
         }

      }
   }

   @EventTarget
   public void onEvent(EventUpdate event) {
      if (this.reason.is("Бездействии")) {
         Minecraft var10002 = mc;
         double var24 = Minecraft.player.getPosX();
         Minecraft var10003 = mc;
         double var25 = Minecraft.player.getPosY();
         Minecraft var10004 = mc;
         var25 += (double)Minecraft.player.getHeight() / 2.0;
         var10004 = mc;
         Vector3d base = new Vector3d(var24, var25, Minecraft.player.getPosZ());
         this.particles.ensureCapacity(this.particles.size() + ((Float)this.count.get()).intValue());

         for(int i = 0; i < ((Float)this.count.get()).intValue(); ++i) {
            double distance = (double)MathUtil.random(7.0F, 35.0F);
            double angle = Math.toRadians((double)MathUtil.random(0.0F, 360.0F));
            double height = (double)MathUtil.random(-7.0F, 25.0F);
            Vector3d offset = new Vector3d(Math.cos(angle) * distance, height, Math.sin(angle) * distance);
            Vector3d spawnPos = base.add(offset);
            double halfSize = 0.5;
            AxisAlignedBB aabb = new AxisAlignedBB(spawnPos.x - halfSize, spawnPos.y - halfSize, spawnPos.z - halfSize, spawnPos.x + halfSize, spawnPos.y + halfSize, spawnPos.z + halfSize);
            if (mc.worldRenderer.getClippinghelper().isBoundingBoxInFrustum(aabb)) {
               Vector3d originalPosition = base.add(offset);
               long life = (long)MathUtil.random(1500.0F, 2000.0F);
               double speed = Math.random() < 0.8 ? (double)MathUtil.random(0.015F, 0.03F) : 0.125;
               double phi = Math.toRadians((double)MathUtil.random(0.0F, 360.0F));
               float smooth = 3.0F;
               Vector3d velocity = new Vector3d(Math.cos(phi) * speed, (double)MathUtil.random((float)(-speed * 0.10000000149011612), (float)(speed * 0.10000000149011612)), Math.sin(phi) * speed);
               this.addParticles(originalPosition.x, originalPosition.y, originalPosition.z, velocity, ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL), 0.4F, life, smooth, 4.999999873689376E-6);
            }
         }

      }
   }

   @EventTarget
   public void onEvent(EventCriticalHit event) {
      if (this.reason.is("Крите") && event.getTarget() != null) {
         this.particles.ensureCapacity(this.particles.size() + 35);

         for(int i = 0; i < 35; ++i) {
            double targetX = event.getTarget().getPosX() + (double)MathUtil.random(-0.4F, 0.4F);
            double targetY = event.getTarget().getPosY() + (double)MathUtil.random(-0.5F, event.getTarget().getHeight() + 0.4F);
            double targetZ = event.getTarget().getPosZ() + (double)MathUtil.random(-0.4F, 0.4F);
            double halfSize = 0.5;
            AxisAlignedBB aabb = new AxisAlignedBB(targetX - halfSize, targetY - halfSize, targetZ - halfSize, targetX + halfSize, targetY + halfSize, targetZ + halfSize);
            if (mc.worldRenderer.getClippinghelper().isBoundingBoxInFrustum(aabb)) {
               float baseMx = MathUtil.random(-0.8F, 0.8F) * 2.0F;
               float baseMy = MathUtil.random(-0.25F, 1.4F);
               float baseMz = MathUtil.random(-0.8F, 0.8F) * 2.0F;
               float smooth = 0.5F;
               long life = (long)MathUtil.random(1000.0F, 1250.0F);
               Vector3d velocity = new Vector3d((double)(baseMx * 0.07F), (double)(baseMy * 0.07F), (double)(baseMz * 0.07F));
               this.addParticles(targetX, targetY, targetZ, velocity, ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL), 0.3F, life, smooth, 5.000000237487257E-4);
            }
         }

      }
   }

   @EventTarget
   public void onEvent(EventPopTotem event) {
      if (this.reason.is("Сносе тотема")) {
         Entity var10001 = event.getEntity();
         Minecraft var10002 = mc;
         this.isPlayerTotem = var10001 == Minecraft.player;
      }
   }

   @EventTarget
   public void onEvent(EventTotemParticle event) {
      if (this.reason.is("Сносе тотема") && !this.isPlayerTotem) {
         double x = event.getX();
         double y = event.getY();
         double z = event.getZ();
         double halfSize = 0.5;
         AxisAlignedBB aabb = new AxisAlignedBB(x - halfSize, y - halfSize, z - halfSize, x + halfSize, y + halfSize, z + halfSize);
         if (mc.worldRenderer.getClippinghelper().isBoundingBoxInFrustum(aabb)) {
            int color = Math.random() < 0.7 ? -16711936 : -256;
            long life = (long)MathUtil.random(1500.0F, 2000.0F);
            Vector3d adjustedVelocity = new Vector3d(event.getXSpeed() * 0.06, event.getYSpeed() * 0.06, event.getZSpeed() * 0.06);
            this.addParticles(x, y, z, adjustedVelocity, color, 0.3F, life, 0.6F, 2.0E-4);
            event.setCancelled(true);
         }
      }
   }

   @EventTarget
   public void onEvent(EventSwapWorld event) {
      this.particles.clear();
   }

   public void onDisable() {
      this.particles.clear();
      super.onDisable();
   }

   private static String getTexturePath(String displayName) {
      String var10000;
      switch (displayName) {
         case "Сердечки":
            var10000 = "heart.png";
            break;
         case "Доллары":
            var10000 = "dollar.png";
            break;
         case "Снежинки":
            var10000 = "snowflake.png";
            break;
         case "Тыковки":
            var10000 = "pumpkin.png";
            break;
         case "Бубенцы":
            var10000 = "firepart2.png";
            break;
         case "Сияние":
            var10000 = "sparkle.png";
            break;
         default:
            var10000 = "star.png";
      }

      return var10000;
   }

   private void addParticles(double x, double y, double z, Vector3d velocity, int color, float size, long lifeTime, float smooth, double gravity) {
      Vector3d safePos = Particles.Particle.checkCollision(x, y, z, size);
      if (safePos != null) {
         this.particles.add(new Particle(safePos.x, safePos.y, safePos.z, velocity, color, size, lifeTime, smooth, gravity));
      }

   }

   public static class Particle {
      Vector3d position;
      Vector3d velocity;
      int color;
      float size;
      float alpha = 1.0F;
      float smoothFactor;
      long lifeTime;
      TimeUtil time = new TimeUtil();
      private long lastUpdateNs;
      private final double gravity;

      public Particle(double x, double y, double z, Vector3d velocity, int color, float size, long lifeTime, float smooth, double gravity) {
         this.position = new Vector3d(x, y, z);
         this.velocity = velocity;
         this.color = color;
         this.size = size;
         this.lifeTime = lifeTime;
         this.time.reset();
         this.lastUpdateNs = System.nanoTime();
         this.smoothFactor = smooth;
         this.gravity = gravity;
      }

      public void update() {
         long now = System.nanoTime();
         double delta = (double)(now - this.lastUpdateNs) / 1.0E9;
         this.lastUpdateNs = now;
         float progress = Math.min(1.0F, (float)this.time.getTimePassed() / (float)this.lifeTime);
         double factor = Math.pow(1.0 - (double)progress, (double)this.smoothFactor);
         double scale = delta * 60.0;
         double vx = this.velocity.x;
         double vy = this.velocity.y;
         double vz = this.velocity.z;
         double px = this.position.x;
         double py = this.position.y;
         double pz = this.position.z;
         double newX = px + vx * factor * scale;
         if (checkCollision(newX, py, pz, this.size) == null) {
            vx *= -0.8;
            newX = px;
         }

         double newY = py + vy * factor * scale;
         if (checkCollision(newX, newY, pz, this.size) == null) {
            vy *= -1.5;
            newY = py;
         }

         double newZ = pz + vz * factor * scale;
         if (checkCollision(newX, newY, newZ, this.size) == null) {
            vz *= -0.8;
            newZ = pz;
         }

         this.position = new Vector3d(newX, newY, newZ);
         this.velocity = new Vector3d(vx * 0.9999, vy * 0.9999 - this.gravity, vz * 0.9999);
         this.alpha = 1.0F - progress;
      }

      private static Vector3d checkCollision(double x, double y, double z, float size) {
         double half = (double)size * 0.5;
         int minX = MathHelper.floor(x - half);
         int maxX = MathHelper.floor(x + half);
         int minY = MathHelper.floor(y - half);
         int maxY = MathHelper.floor(y + half);
         int minZ = MathHelper.floor(z - half);
         int maxZ = MathHelper.floor(z + half);
         BlockPos.Mutable pos = new BlockPos.Mutable();

         for(int bx = minX; bx <= maxX; ++bx) {
            for(int by = minY; by <= maxY; ++by) {
               for(int bz = minZ; bz <= maxZ; ++bz) {
                  BlockState state = Wrapper.mc.world.getBlockState(pos.setPos(bx, by, bz));
                  if (!state.isAir()) {
                     return null;
                  }
               }
            }
         }

         return new Vector3d(x, y, z);
      }

      public Vector3d getPosition() {
         return this.position;
      }

      public Vector3d getVelocity() {
         return this.velocity;
      }

      public int getColor() {
         return this.color;
      }

      public float getSize() {
         return this.size;
      }

      public float getAlpha() {
         return this.alpha;
      }

      public float getSmoothFactor() {
         return this.smoothFactor;
      }

      public long getLifeTime() {
         return this.lifeTime;
      }

      public TimeUtil getTime() {
         return this.time;
      }

      public long getLastUpdateNs() {
         return this.lastUpdateNs;
      }

      public double getGravity() {
         return this.gravity;
      }
   }
}
