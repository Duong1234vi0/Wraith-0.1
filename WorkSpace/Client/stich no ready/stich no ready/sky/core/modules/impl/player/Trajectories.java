package sky.core.modules.impl.player;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.Iterator;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceContext.BlockMode;
import net.minecraft.util.math.RayTraceContext.FluidMode;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.vector.Vector3d;
import org.lwjgl.opengl.GL11;
import sky.core.events.EventRender3D;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;

public class Trajectories extends Module {
   private static final Minecraft mc = Minecraft.getInstance();
   private static final int MAX_SHOTS = 3;
   private final PredictionSlot[] slots = new PredictionSlot[3];

   public Trajectories() {
      super("Trajectories", Category.Visuals);

      for(int i = 0; i < this.slots.length; ++i) {
         this.slots[i] = new PredictionSlot();
      }

   }

   @EventTarget
   private void onUpdate(EventUpdate event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         var10000 = mc;
         PlayerEntity player = Minecraft.player;
         ItemStack stack = player.getHeldItemMainhand();
         if (stack == null || stack.isEmpty()) {
            stack = player.getHeldItemOffhand();
         }

         if (stack != null && !stack.isEmpty()) {
            ProjectileParams params = getParams(player, stack);
            if (params == null) {
               this.clearSlots();
            } else {
               Vector3d dir;
               if (stack.getItem() == Items.CROSSBOW && EnchantmentHelper.getEnchantmentLevel(Enchantments.MULTISHOT, stack) > 0) {
                  dir = player.getLookVec().normalize();
                  float baseYaw = (float)(MathHelper.atan2(dir.z, dir.x) * 57.29577951308232) - 90.0F;
                  float basePitch = (float)(-(MathHelper.atan2(dir.y, (double)MathHelper.sqrt(dir.x * dir.x + dir.z * dir.z)) * 57.29577951308232));
                  float[] yawOff = new float[]{-10.0F, 0.0F, 10.0F};

                  for(int i = 0; i < 3; ++i) {
                     Vector3d dir = getDirectionFromYawPitch(baseYaw + yawOff[i], basePitch);
                     PredictionResult pr = predict(player, params, dir);
                     this.updateSlot(this.slots[i], pr);
                  }
               } else {
                  dir = player.getLookVec().normalize();
                  PredictionResult pr = predict(player, params, dir);
                  this.updateSlot(this.slots[0], pr);

                  for(int i = 1; i < 3; ++i) {
                     this.slots[i].clear();
                  }
               }

            }
         } else {
            this.clearSlots();
         }
      } else {
         this.clearSlots();
      }
   }

   @EventTarget
   private void onRender(EventRender3D event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         float pt = event.getPartialTicks();
         Vector3d cam = mc.getRenderManager().info.getProjectedView();

         for(int i = 0; i < 3; ++i) {
            PredictionSlot s = this.slots[i];
            if (s.hasHit && s.prevHit != null && s.currHit != null && s.prevFace != null && s.currFace != null) {
               boolean entityHit = s.hitEntity != null && s.hitEntity.isAlive() && s.prevEntityHit != null && s.currEntityHit != null;
               Vector3d entityHitVec;
               if (entityHit) {
                  entityHitVec = lerpVec(s.prevEntityHit, s.currEntityHit, pt);
                  this.renderBillboardMarker(entityHitVec.subtract(cam), ColorUtil.rgb(255, 50, 50));
                  this.renderEntityBox(event, s.hitEntity, ColorUtil.rgb(255, 50, 50));
               } else {
                  entityHitVec = lerpVec(s.prevHit, s.currHit, pt);
                  this.renderMarker(event, entityHitVec.subtract(cam), s.prevFace, s.currFace, pt, ColorUtil.rgb(63, 233, 53));
               }
            }
         }

      }
   }

   private static ProjectileParams getParams(PlayerEntity player, ItemStack stack) {
      Item item = stack.getItem();
      if (item == Items.ENDER_PEARL) {
         return new ProjectileParams(1.5F, 0.03F, 0.99F);
      } else {
         float velocity;
         if (item == Items.BOW) {
            velocity = 1.0F;
            if (player.isHandActive() && player.getActiveItemStack() == stack) {
               int use = stack.getUseDuration() - player.getItemInUseCount();
               float f = (float)use / 20.0F;
               f = (f * f + f * 2.0F) / 3.0F;
               if (f > 1.0F) {
                  f = 1.0F;
               }

               velocity = f;
            }

            float velocity = 3.0F * velocity;
            return velocity <= 0.01F ? null : new ProjectileParams(velocity, 0.05F, 0.99F);
         } else if (item == Items.CROSSBOW) {
            if (!CrossbowItem.isCharged(stack)) {
               return null;
            } else {
               velocity = 3.15F;
               return new ProjectileParams(velocity, 0.05F, 0.99F);
            }
         } else if (item == Items.TRIDENT) {
            velocity = 2.5F;
            return new ProjectileParams(velocity, 0.05F, 0.99F);
         } else {
            return null;
         }
      }
   }

   private static BlockRayTraceResult predictHit(PlayerEntity player, ProjectileParams params) {
      Vector3d pos = player.getEyePosition(1.0F);
      Vector3d motion = player.getLookVec().normalize().scale((double)params.velocity);

      for(int i = 0; i < 200; ++i) {
         Vector3d prev = pos;
         pos = pos.add(motion);
         RayTraceContext ctx = new RayTraceContext(prev, pos, BlockMode.COLLIDER, FluidMode.NONE, player);
         BlockRayTraceResult blockHit = mc.world.rayTraceBlocks(ctx);
         if (blockHit.getType() == Type.BLOCK) {
            return blockHit;
         }

         motion = motion.scale((double)params.drag);
         motion = motion.subtract(0.0, (double)params.gravity, 0.0);
         if (pos.y < 0.0) {
            break;
         }
      }

      return null;
   }

   private void renderMarker(EventRender3D event, Vector3d hitPosView, Direction prevFace, Direction currFace, float partialTicks, int color) {
      double eps = 0.002;
      Vector3d n0 = Vector3d.copy(prevFace.getDirectionVec()).normalize();
      Vector3d n1 = Vector3d.copy(currFace.getDirectionVec()).normalize();
      Vector3d u0 = prevFace != Direction.UP && prevFace != Direction.DOWN ? n0.crossProduct(new Vector3d(0.0, 1.0, 0.0)).normalize() : new Vector3d(1.0, 0.0, 0.0);
      Vector3d u1 = currFace != Direction.UP && currFace != Direction.DOWN ? n1.crossProduct(new Vector3d(0.0, 1.0, 0.0)).normalize() : new Vector3d(1.0, 0.0, 0.0);
      Vector3d n = lerpVec(n0, n1, partialTicks).normalize();
      Vector3d u = lerpVec(u0, u1, partialTicks).normalize();
      Vector3d v = n.crossProduct(u).normalize();
      u = v.crossProduct(n).normalize();
      Vector3d center = hitPosView.add(n.scale(eps));
      float[] rgba = ColorUtil.rgba(color);
      float a = 0.95F;
      Tessellator tess = Tessellator.getInstance();
      BufferBuilder buffer = tess.getBuffer();
      RenderSystem.pushMatrix();
      GL11.glDisable(3553);
      GL11.glEnable(2929);
      GL11.glEnable(3042);
      GL11.glEnable(2848);
      GL11.glBlendFunc(770, 771);
      GL11.glDepthMask(false);
      GL11.glLineWidth(2.5F);
      buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
      int segments = 48;
      double r = 0.35;

      Vector3d p2;
      for(int i = 0; i <= segments; ++i) {
         double t = 6.283185307179586 * ((double)i / (double)segments);
         p2 = center.add(u.scale(Math.cos(t) * r)).add(v.scale(Math.sin(t) * r));
         buffer.pos(p2.x, p2.y, p2.z).color(rgba[0], rgba[1], rgba[2], a).endVertex();
      }

      tess.draw();
      buffer.begin(1, DefaultVertexFormats.POSITION_COLOR);
      Vector3d p1 = center.add(u.scale(-r));
      p2 = center.add(u.scale(r));
      buffer.pos(p1.x, p1.y, p1.z).color(rgba[0], rgba[1], rgba[2], a).endVertex();
      buffer.pos(p2.x, p2.y, p2.z).color(rgba[0], rgba[1], rgba[2], a).endVertex();
      Vector3d p3 = center.add(v.scale(-r));
      Vector3d p4 = center.add(v.scale(r));
      buffer.pos(p3.x, p3.y, p3.z).color(rgba[0], rgba[1], rgba[2], a).endVertex();
      buffer.pos(p4.x, p4.y, p4.z).color(rgba[0], rgba[1], rgba[2], a).endVertex();
      tess.draw();
      GL11.glDisable(2848);
      GL11.glDisable(3042);
      GL11.glDepthMask(true);
      GL11.glEnable(2929);
      GL11.glEnable(3553);
      RenderSystem.popMatrix();
   }

   private void renderBillboardMarker(Vector3d posView, int color) {
      Vector3d n = posView.scale(-1.0);
      if (!(n.lengthSquared() < 1.0E-6)) {
         n = n.normalize();
         Vector3d u = n.crossProduct(new Vector3d(0.0, 1.0, 0.0));
         if (u.lengthSquared() < 1.0E-6) {
            u = new Vector3d(1.0, 0.0, 0.0);
         }

         u = u.normalize();
         Vector3d v = n.crossProduct(u).normalize();
         float[] rgba = ColorUtil.rgba(color);
         float a = 0.95F;
         Tessellator tess = Tessellator.getInstance();
         BufferBuilder buffer = tess.getBuffer();
         RenderSystem.pushMatrix();
         GL11.glDisable(3553);
         GL11.glEnable(2929);
         GL11.glEnable(3042);
         GL11.glEnable(2848);
         GL11.glBlendFunc(770, 771);
         GL11.glDepthMask(false);
         GL11.glLineWidth(2.5F);
         buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
         int segments = 48;
         double r = 0.35;

         Vector3d p2;
         for(int i = 0; i <= segments; ++i) {
            double t = 6.283185307179586 * ((double)i / (double)segments);
            p2 = posView.add(u.scale(Math.cos(t) * r)).add(v.scale(Math.sin(t) * r));
            buffer.pos(p2.x, p2.y, p2.z).color(rgba[0], rgba[1], rgba[2], a).endVertex();
         }

         tess.draw();
         buffer.begin(1, DefaultVertexFormats.POSITION_COLOR);
         Vector3d p1 = posView.add(u.scale(-r));
         p2 = posView.add(u.scale(r));
         buffer.pos(p1.x, p1.y, p1.z).color(rgba[0], rgba[1], rgba[2], a).endVertex();
         buffer.pos(p2.x, p2.y, p2.z).color(rgba[0], rgba[1], rgba[2], a).endVertex();
         Vector3d p3 = posView.add(v.scale(-r));
         Vector3d p4 = posView.add(v.scale(r));
         buffer.pos(p3.x, p3.y, p3.z).color(rgba[0], rgba[1], rgba[2], a).endVertex();
         buffer.pos(p4.x, p4.y, p4.z).color(rgba[0], rgba[1], rgba[2], a).endVertex();
         tess.draw();
         GL11.glDisable(2848);
         GL11.glDisable(3042);
         GL11.glDepthMask(true);
         GL11.glEnable(2929);
         GL11.glEnable(3553);
         RenderSystem.popMatrix();
      }
   }

   private static Vector3d lerpVec(Vector3d a, Vector3d b, float t) {
      return new Vector3d(a.x + (b.x - a.x) * (double)t, a.y + (b.y - a.y) * (double)t, a.z + (b.z - a.z) * (double)t);
   }

   private static Vector3d getDirectionFromYawPitch(float yawDeg, float pitchDeg) {
      float yaw = yawDeg * 0.017453292F;
      float pitch = pitchDeg * 0.017453292F;
      float f = MathHelper.cos(-yaw - 3.1415927F);
      float f1 = MathHelper.sin(-yaw - 3.1415927F);
      float f2 = -MathHelper.cos(-pitch);
      float f3 = MathHelper.sin(-pitch);
      return new Vector3d((double)(f1 * f2), (double)f3, (double)(f * f2));
   }

   private void updateSlot(PredictionSlot slot, PredictionResult pr) {
      if (pr != null && pr.blockHit != null && pr.blockHit.getType() == Type.BLOCK) {
         Vector3d hitVec = pr.blockHit.getHitVec();
         Direction face = pr.blockHit.getFace();
         slot.hitEntity = pr.entityHit;
         if (pr.entityHitPos != null) {
            if (slot.currEntityHit == null) {
               slot.prevEntityHit = pr.entityHitPos;
               slot.currEntityHit = pr.entityHitPos;
            } else {
               slot.prevEntityHit = slot.currEntityHit;
               slot.currEntityHit = pr.entityHitPos;
            }
         } else {
            slot.prevEntityHit = null;
            slot.currEntityHit = null;
         }

         if (slot.hasHit && slot.currHit != null && slot.currFace != null) {
            slot.prevHit = slot.currHit;
            slot.prevFace = slot.currFace;
            slot.currHit = hitVec;
            slot.currFace = face;
         } else {
            slot.prevHit = hitVec;
            slot.currHit = hitVec;
            slot.prevFace = face;
            slot.currFace = face;
            slot.hasHit = true;
         }

      } else {
         slot.clear();
      }
   }

   private void clearSlots() {
      for(int i = 0; i < 3; ++i) {
         this.slots[i].clear();
      }

   }

   private static PredictionResult predict(PlayerEntity player, ProjectileParams params, Vector3d dirNorm) {
      Vector3d pos = player.getEyePosition(1.0F);
      Vector3d motion = dirNorm.normalize().scale((double)params.velocity);
      Entity firstEntityHit = null;
      Vector3d firstEntityHitPos = null;

      for(int i = 0; i < 200; ++i) {
         Vector3d prev = pos;
         pos = pos.add(motion);
         EntityHit entityHit = rayTraceEntities(prev, pos, player);
         if (entityHit != null && firstEntityHit == null) {
            firstEntityHit = entityHit.entity;
            firstEntityHitPos = entityHit.hitPos;
         }

         RayTraceContext ctx = new RayTraceContext(prev, pos, BlockMode.COLLIDER, FluidMode.NONE, player);
         BlockRayTraceResult blockHit = mc.world.rayTraceBlocks(ctx);
         if (blockHit.getType() == Type.BLOCK) {
            return new PredictionResult(blockHit, firstEntityHit, firstEntityHitPos);
         }

         motion = motion.scale((double)params.drag);
         motion = motion.subtract(0.0, (double)params.gravity, 0.0);
         if (pos.y < 0.0) {
            break;
         }
      }

      return null;
   }

   private static EntityHit rayTraceEntities(Vector3d from, Vector3d to, Entity owner) {
      AxisAlignedBB aabb = (new AxisAlignedBB(from, to)).grow(1.0);
      Entity closest = null;
      Vector3d closestHit = null;
      double closestDist = Double.MAX_VALUE;
      Iterator var8 = mc.world.getEntitiesInAABBexcluding(owner, aabb, (entity) -> {
         return entity != null && entity.isAlive() && entity.canBeCollidedWith();
      }).iterator();

      while(var8.hasNext()) {
         Entity e = (Entity)var8.next();
         AxisAlignedBB bb = e.getBoundingBox().grow(0.3);
         Optional hit = bb.rayTrace(from, to);
         if (hit.isPresent()) {
            double dist = from.squareDistanceTo((Vector3d)hit.get());
            if (dist < closestDist) {
               closestDist = dist;
               closest = e;
               closestHit = (Vector3d)hit.get();
            }
         }
      }

      return closest == null ? null : new EntityHit(closest, closestHit);
   }

   private void renderEntityBox(EventRender3D event, Entity entity, int color) {
      float partialTicks = event.getPartialTicks();
      Vector3d cam = mc.getRenderManager().info.getProjectedView();
      double interpX = entity.lastTickPosX + (entity.getPosX() - entity.lastTickPosX) * (double)partialTicks;
      double interpY = entity.lastTickPosY + (entity.getPosY() - entity.lastTickPosY) * (double)partialTicks;
      double interpZ = entity.lastTickPosZ + (entity.getPosZ() - entity.lastTickPosZ) * (double)partialTicks;
      AxisAlignedBB box = entity.getBoundingBox().offset(-cam.x + (interpX - entity.getPosX()), -cam.y + (interpY - entity.getPosY()), -cam.z + (interpZ - entity.getPosZ()));
      RenderUtil.box(box, color);
   }

   private static final class PredictionSlot {
      private Vector3d prevHit;
      private Vector3d currHit;
      private Direction prevFace;
      private Direction currFace;
      private boolean hasHit;
      private Entity hitEntity;
      private Vector3d prevEntityHit;
      private Vector3d currEntityHit;

      private void clear() {
         this.prevHit = null;
         this.currHit = null;
         this.prevFace = null;
         this.currFace = null;
         this.hasHit = false;
         this.hitEntity = null;
         this.prevEntityHit = null;
         this.currEntityHit = null;
      }
   }

   private static final class ProjectileParams {
      private final float velocity;
      private final float gravity;
      private final float drag;

      private ProjectileParams(float velocity, float gravity, float drag) {
         this.velocity = velocity;
         this.gravity = gravity;
         this.drag = drag;
      }
   }

   private static final class PredictionResult {
      private final BlockRayTraceResult blockHit;
      private final Entity entityHit;
      private final Vector3d entityHitPos;

      private PredictionResult(BlockRayTraceResult blockHit, Entity entityHit, Vector3d entityHitPos) {
         this.blockHit = blockHit;
         this.entityHit = entityHit;
         this.entityHitPos = entityHitPos;
      }
   }

   private static final class EntityHit {
      private final Entity entity;
      private final Vector3d hitPos;

      private EntityHit(Entity entity, Vector3d hitPos) {
         this.entity = entity;
         this.hitPos = hitPos;
      }
   }
}
