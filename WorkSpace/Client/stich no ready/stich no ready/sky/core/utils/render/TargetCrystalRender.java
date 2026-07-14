package sky.core.utils.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import sky.core.events.EventWorld3D;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;

public final class TargetCrystalRender {
   private static final Minecraft mc = Minecraft.getInstance();
   private static final ResourceLocation GLOW_TEXTURE = new ResourceLocation("SkyCore/icons/world_render/bloom.png");
   private static final Tessellator TESSELLATOR = Tessellator.getInstance();
   private static final float[] CRYSTAL_FACE_MUL = new float[]{1.0F, 0.8F, 0.6F, 0.9F, 0.7F, 0.5F, 0.4F, 0.6F};
   private static List cachedCrystalPoints = new ArrayList();
   private static int cachedCrystalTargetId = Integer.MIN_VALUE;
   private static float cachedCrystalWidth = -1.0F;
   private static float cachedCrystalHeight = -1.0F;
   private static long cachedCrystalPointsAt = 0L;
   private static final long CRYSTAL_POINTS_TTL_MS = 120L;

   private TargetCrystalRender() {
   }

   public static void render(EventWorld3D e, LivingEntity target, float anim, float scatter, float sizeSetting) {
      if (target != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null && mc.world != null) {
            float alpha = MathHelper.clamp(anim, 0.0F, 1.0F);
            if (alpha <= 0.01F) {
               return;
            }

            float partialTicks = e.getPartialTicks();
            Vector3d cameraPosition = mc.getRenderManager().info.getProjectedView();
            double targetX = target.lastTickPosX + (target.getPosX() - target.lastTickPosX) * (double)partialTicks;
            double targetY = target.lastTickPosY + (target.getPosY() - target.lastTickPosY) * (double)partialTicks;
            double targetZ = target.lastTickPosZ + (target.getPosZ() - target.lastTickPosZ) * (double)partialTicks;
            double x = targetX - cameraPosition.x;
            double y = targetY - cameraPosition.y;
            double z = targetZ - cameraPosition.z;
            float width = target.getWidth() * 1.5F;
            float height = target.getHeight();
            int baseColor = setAlpha(ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL), (int)(255.0F * alpha));
            float rotate = (float)(System.currentTimeMillis() % 360000L) / 7.25F;
            float timeSec = (float)System.currentTimeMillis() * 0.001F;
            float meshScale = 0.1F * MathHelper.clamp(sizeSetting, 0.5F, 3.0F);
            float radiusAnimFactor = 1.25F - 0.5F * alpha + MathHelper.clamp(scatter, 0.0F, 1.0F) * 0.35F;
            float rotationRad = (float)Math.toRadians((double)rotate);
            float sinRot = (float)Math.sin((double)rotationRad);
            float cosRot = (float)Math.cos((double)rotationRad);
            List points = getCrystalPoints(target, width, height);
            MatrixStack ms = e.getMatrixStack();
            double cx = x;
            double cy = y + (double)height * 0.5;
            double cz = z;
            RenderSystem.pushMatrix();
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
            RenderSystem.disableCull();
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.disableAlphaTest();
            RenderSystem.shadeModel(7425);
            BufferBuilder buffer = TESSELLATOR.getBuffer();
            RenderSystem.disableTexture();
            buffer.begin(4, DefaultVertexFormats.POSITION_COLOR);

            int i;
            Vector3f p;
            float rotX;
            float rotZ;
            float rotY;
            float finalX;
            float finalZ;
            float bob;
            float finalY;
            for(i = 0; i < points.size(); ++i) {
               p = (Vector3f)points.get(i);
               rotX = p.getX() * cosRot - p.getZ() * sinRot;
               rotZ = p.getX() * sinRot + p.getZ() * cosRot;
               rotY = p.getY();
               finalX = rotX * radiusAnimFactor;
               finalZ = rotZ * radiusAnimFactor;
               bob = (float)(0.05000000074505806 * Math.sin((double)(timeSec * 2.0F + (float)i * 1337.0F)));
               finalY = rotY + bob;
               ms.push();
               ms.translate(x + (double)finalX, y + (double)finalY, z + (double)finalZ);
               Vector3f dir = new Vector3f((float)(cx - (x + (double)finalX)), (float)(cy - (y + (double)finalY)), (float)(cz - (z + (double)finalZ)));
               float len = (float)Math.sqrt((double)(dir.getX() * dir.getX() + dir.getY() * dir.getY() + dir.getZ() * dir.getZ()));
               if (len > 0.001F) {
                  dir.set(dir.getX() / len, dir.getY() / len, dir.getZ() / len);
                  ms.rotate(quatFromTo(Vector3f.YP, dir));
               }

               ms.scale(meshScale, meshScale, meshScale);
               putCrystalMesh(buffer, ms.getLast().getMatrix(), baseColor);
               ms.pop();
            }

            TESSELLATOR.draw();
            RenderSystem.enableTexture();
            mc.getTextureManager().bindTexture(GLOW_TEXTURE);
            buffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);

            for(i = 0; i < points.size(); ++i) {
               p = (Vector3f)points.get(i);
               rotX = p.getX() * cosRot - p.getZ() * sinRot;
               rotZ = p.getX() * sinRot + p.getZ() * cosRot;
               rotY = p.getY();
               finalX = rotX * radiusAnimFactor;
               finalZ = rotZ * radiusAnimFactor;
               bob = (float)(0.05000000074505806 * Math.sin((double)(timeSec * 2.0F + (float)i * 1337.0F)));
               finalY = rotY + bob;
               ms.push();
               ms.translate(x + (double)finalX, y + (double)finalY, z + (double)finalZ);
               ms.rotate(mc.getRenderManager().getCameraOrientation());
               Matrix4f mat = ms.getLast().getMatrix();
               int bigCol = setAlpha(baseColor, (int)(255.0F * alpha * 0.2F));
               int smallCol = setAlpha(baseColor, (int)(255.0F * alpha));
               float glowScale = MathHelper.clamp(sizeSetting, 0.5F, 3.0F);
               drawTextureQuad(buffer, mat, 0.46750003F * glowScale, bigCol);
               drawTextureQuad(buffer, mat, 0.15300001F * glowScale, smallCol);
               ms.pop();
            }

            TESSELLATOR.draw();
            RenderSystem.shadeModel(7424);
            RenderSystem.enableDepthTest();
            RenderSystem.enableCull();
            RenderSystem.enableAlphaTest();
            RenderSystem.depthMask(true);
            RenderSystem.defaultBlendFunc();
            RenderSystem.popMatrix();
            return;
         }
      }

   }

   private static void putCrystalMesh(BufferBuilder bb, Matrix4f mat, int color) {
      int r = color >> 16 & 255;
      int g = color >> 8 & 255;
      int b = color & 255;
      int a = color >> 24 & 255;
      float[] vertices = new float[]{0.0F, 0.5F, 0.0F, 0.0F, -0.5F, 0.0F, 0.3F, 0.0F, 0.3F, -0.3F, 0.0F, -0.3F, 0.3F, 0.0F, -0.3F, -0.3F, 0.0F, 0.3F};

      for(int face = 0; face < 8; ++face) {
         float mul = CRYSTAL_FACE_MUL[face % CRYSTAL_FACE_MUL.length];
         int faceR = (int)((float)r * mul);
         int faceG = (int)((float)g * mul);
         int faceB = (int)((float)b * mul);
         byte v1;
         int v2;
         int v3;
         if (face < 4) {
            v1 = 0;
            v2 = 2 + face % 4;
            v3 = 2 + (face + 1) % 4;
            bb.pos(mat, vertices[v1 * 3], vertices[v1 * 3 + 1], vertices[v1 * 3 + 2]).color(faceR, faceG, faceB, a).endVertex();
            bb.pos(mat, vertices[v2 * 3], vertices[v2 * 3 + 1], vertices[v2 * 3 + 2]).color(faceR, faceG, faceB, a).endVertex();
            bb.pos(mat, vertices[v3 * 3], vertices[v3 * 3 + 1], vertices[v3 * 3 + 2]).color(faceR, faceG, faceB, a).endVertex();
         } else {
            v1 = 1;
            v2 = 2 + (face - 4) % 4;
            v3 = 2 + (face - 3) % 4;
            bb.pos(mat, vertices[v1 * 3], vertices[v1 * 3 + 1], vertices[v1 * 3 + 2]).color(faceR, faceG, faceB, a).endVertex();
            bb.pos(mat, vertices[v3 * 3], vertices[v3 * 3 + 1], vertices[v3 * 3 + 2]).color(faceR, faceG, faceB, a).endVertex();
            bb.pos(mat, vertices[v2 * 3], vertices[v2 * 3 + 1], vertices[v2 * 3 + 2]).color(faceR, faceG, faceB, a).endVertex();
         }
      }

   }

   private static void drawTextureQuad(BufferBuilder bb, Matrix4f mat, float size, int color) {
      int r = color >> 16 & 255;
      int g = color >> 8 & 255;
      int b = color & 255;
      int a = color >> 24 & 255;
      float halfSize = size / 2.0F;
      bb.pos(mat, -halfSize, halfSize, 0.0F).tex(0.0F, 1.0F).color(r, g, b, a).endVertex();
      bb.pos(mat, halfSize, halfSize, 0.0F).tex(1.0F, 1.0F).color(r, g, b, a).endVertex();
      bb.pos(mat, halfSize, -halfSize, 0.0F).tex(1.0F, 0.0F).color(r, g, b, a).endVertex();
      bb.pos(mat, -halfSize, -halfSize, 0.0F).tex(0.0F, 0.0F).color(r, g, b, a).endVertex();
   }

   private static Quaternion quatFromTo(Vector3f from, Vector3f to) {
      Vector3f cross = new Vector3f(from.getY() * to.getZ() - from.getZ() * to.getY(), from.getZ() * to.getX() - from.getX() * to.getZ(), from.getX() * to.getY() - from.getY() * to.getX());
      float dot = from.getX() * to.getX() + from.getY() * to.getY() + from.getZ() * to.getZ();
      float len = (float)Math.sqrt((double)(cross.getX() * cross.getX() + cross.getY() * cross.getY() + cross.getZ() * cross.getZ()));
      if (len < 0.001F) {
         if (dot > 0.0F) {
            return Quaternion.ONE;
         } else {
            Vector3f axis = Math.abs(from.getX()) < 0.9F ? new Vector3f(1.0F, 0.0F, 0.0F) : new Vector3f(0.0F, 1.0F, 0.0F);
            return new Quaternion(axis, 180.0F, true);
         }
      } else {
         float angle = (float)Math.acos((double)MathHelper.clamp(dot, -1.0F, 1.0F));
         cross.set(cross.getX() / len, cross.getY() / len, cross.getZ() / len);
         return new Quaternion(cross, (float)Math.toDegrees((double)angle), true);
      }
   }

   private static int setAlpha(int color, int alpha) {
      alpha = MathHelper.clamp(alpha, 0, 255);
      return alpha << 24 | color & 16777215;
   }

   private static List getCrystalPoints(LivingEntity target, float width, float height) {
      long now = System.currentTimeMillis();
      int targetId = target.getEntityId();
      if (targetId == cachedCrystalTargetId && Math.abs(width - cachedCrystalWidth) < 0.001F && Math.abs(height - cachedCrystalHeight) < 0.001F && now - cachedCrystalPointsAt <= 120L && !cachedCrystalPoints.isEmpty()) {
         return cachedCrystalPoints;
      } else {
         List points = new ArrayList();
         Random rng = new Random((long)targetId * 133769420L);
         int count = (int)(width + height * 12.0F);
         int candidatesPerPoint = 15;

         for(int i = 0; i < count; ++i) {
            Vector3f bestCandidate = null;
            float bestDistSq = -1.0F;

            for(int attempt = 0; attempt < candidatesPerPoint; ++attempt) {
               float angle = rng.nextFloat() * 360.0F;
               float h = rng.nextFloat() * height;
               float rad = (float)Math.toRadians((double)angle);
               float px = (float)(Math.sin((double)rad) * (double)width);
               float pz = (float)(Math.cos((double)rad) * (double)width);
               float py = h;
               if (points.isEmpty()) {
                  bestCandidate = new Vector3f(px, h, pz);
                  break;
               }

               float nearestDistSq = Float.MAX_VALUE;
               Iterator var21 = points.iterator();

               while(var21.hasNext()) {
                  Vector3f existing = (Vector3f)var21.next();
                  float dSq = (existing.getX() - px) * (existing.getX() - px) + (existing.getY() - py) * (existing.getY() - py) + (existing.getZ() - pz) * (existing.getZ() - pz);
                  if (dSq < nearestDistSq) {
                     nearestDistSq = dSq;
                  }
               }

               if (nearestDistSq > bestDistSq) {
                  bestDistSq = nearestDistSq;
                  bestCandidate = new Vector3f(px, py, pz);
               }
            }

            if (bestCandidate != null) {
               points.add(bestCandidate);
            }
         }

         cachedCrystalPoints = points;
         cachedCrystalTargetId = targetId;
         cachedCrystalWidth = width;
         cachedCrystalHeight = height;
         cachedCrystalPointsAt = now;
         return cachedCrystalPoints;
      }
   }
}
