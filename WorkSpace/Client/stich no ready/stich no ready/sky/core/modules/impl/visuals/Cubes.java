package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.border.WorldBorder;
import org.lwjgl.opengl.GL11;
import sky.core.events.EventRender3D;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;

public class Cubes extends Module {
   private static final ResourceLocation GLOW_TEXTURE = new ResourceLocation("SkyCore/icons/world_render/glow.png");
   private static final float SPAWN_RADIUS = 12.0F;
   private static final float PARTICLE_SPEED = 0.25F;
   private static final float PARTICLE_SIZE = 0.18F;
   private final List cubes = new ArrayList();
   private final Random random = new Random();
   public final SliderSetting count = new SliderSetting("Количество", 30.0F, 5.0F, 100.0F, 1.0F);
   private boolean lastAttackPressed;

   public Cubes() {
      super("Cubes", "", Category.Visuals);
      this.addSettings(new Setting[]{this.count});
   }

   public void onEnable() {
      super.onEnable();
      this.cubes.clear();
   }

   public void onDisable() {
      super.onDisable();
      this.cubes.clear();
   }

   private Vector3d cameraLookDir() {
      Vector3f look = mc.gameRenderer.getActiveRenderInfo().getViewVector();
      return new Vector3d((double)look.getX(), (double)look.getY(), (double)look.getZ());
   }

   @EventTarget
   public void onRender3D(EventRender3D event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         boolean atk = mc.gameSettings.keyBindAttack.isKeyDown();
         if (atk && !this.lastAttackPressed) {
            this.applyHitImpulse();
         }

         this.lastAttackPressed = atk;
         this.updateCubes();
         this.renderCubes(event);
      }
   }

   private void applyHitImpulse() {
      if (!this.cubes.isEmpty()) {
         Vector3d origin = mc.gameRenderer.getActiveRenderInfo().getProjectedView();
         Vector3d dir = this.cameraLookDir().normalize();
         double bestT = Double.MAX_VALUE;
         float[] best = null;
         Iterator var6 = this.cubes.iterator();

         while(var6.hasNext()) {
            float[] p = (float[])var6.next();
            Vector3d op = (new Vector3d((double)p[0], (double)p[1], (double)p[2])).subtract(origin);
            double t = op.dotProduct(dir);
            if (t >= 0.0 && t <= 128.0) {
               double dist = (new Vector3d((double)p[0], (double)p[1], (double)p[2])).distanceTo(origin.add(dir.scale(t)));
               if (dist <= 1.15 && t < bestT) {
                  bestT = t;
                  best = p;
               }
            }
         }

         if (best != null) {
            best[3] += (float)(dir.x * 0.08);
            best[4] += (float)(dir.y * 0.08 + 0.02);
            best[5] += (float)(dir.z * 0.08);
         }

      }
   }

   private boolean isBehindCamera(double px, double py, double pz) {
      Vector3d cam = mc.gameRenderer.getActiveRenderInfo().getProjectedView();
      Vector3d d = this.cameraLookDir();
      return (px - cam.x) * d.x + (py - cam.y) * d.y + (pz - cam.z) * d.z < -1.0;
   }

   private void updateCubes() {
      int target = ((Float)this.count.get()).intValue();

      while(this.cubes.size() < target) {
         this.cubes.add(this.spawnCube());
      }

      while(this.cubes.size() > target) {
         this.cubes.remove(this.cubes.size() - 1);
      }

      Iterator it = this.cubes.iterator();

      while(it.hasNext()) {
         float[] p = (float[])it.next();
         float spd = 0.25F;
         p[0] += p[3] * spd;
         p[1] += p[4] * spd;
         p[2] += p[5] * spd;

         for(int i = 6; i <= 8; ++i) {
            p[i] += p[i + 3] * spd;
         }

         p[3] *= 0.995F;
         p[4] *= 0.995F;
         p[5] *= 0.995F;
         int var10002 = p[12]--;
         if (p[12] <= 0.0F) {
            it.remove();
         }
      }

   }

   private void renderCubes(EventRender3D e) {
      ActiveRenderInfo info = mc.gameRenderer.getActiveRenderInfo();
      Vector3d cam = info.getProjectedView();
      MatrixStack ms = new MatrixStack();
      float s = 0.18F;
      int c = ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL);
      float cr = (float)(c >> 16 & 255) / 255.0F;
      float cg = (float)(c >> 8 & 255) / 255.0F;
      float cb = (float)(c & 255) / 255.0F;
      List visible = new ArrayList();
      Iterator var11 = this.cubes.iterator();

      while(var11.hasNext()) {
         float[] p = (float[])var11.next();
         if (!this.isBehindCamera((double)p[0], (double)p[1], (double)p[2])) {
            visible.add(p);
         }
      }

      if (!visible.isEmpty()) {
         RenderSystem.pushMatrix();
         RenderSystem.enableBlend();
         RenderSystem.disableCull();
         RenderSystem.disableDepthTest();
         RenderSystem.depthMask(false);
         RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ZERO, DestFactor.ONE);
         mc.getTextureManager().bindTexture(GLOW_TEXTURE);
         Tessellator tess = Tessellator.getInstance();
         BufferBuilder bb = tess.getBuffer();
         bb.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
         Iterator var13 = visible.iterator();

         float[] p;
         float eb;
         while(var13.hasNext()) {
            p = (float[])var13.next();
            eb = this.getAlpha(p);
            Vector3d pos = new Vector3d((double)p[0], (double)p[1], (double)p[2]);
            float[][] var17 = new float[][]{{17.0F, 0.06F}, {10.0F, 0.14F}, {6.0F, 0.25F}};
            int var18 = var17.length;

            for(int var19 = 0; var19 < var18; ++var19) {
               float[] gl = var17[var19];
               this.drawGlow(bb, ms, info, pos, cam, s * gl[0], cr, cg, cb, gl[1] * eb);
            }
         }

         tess.draw();
         RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
         RenderSystem.disableTexture();
         bb.begin(7, DefaultVertexFormats.POSITION_COLOR);
         var13 = visible.iterator();

         while(var13.hasNext()) {
            p = (float[])var13.next();
            eb = this.getAlpha(p) * 0.4F;
            ms.push();
            ms.translate((double)p[0] - cam.x, (double)p[1] - cam.y, (double)p[2] - cam.z);
            ms.rotate(Vector3f.XP.rotationDegrees(p[6]));
            ms.rotate(Vector3f.YP.rotationDegrees(p[7]));
            ms.rotate(Vector3f.ZP.rotationDegrees(p[8]));
            this.drawCubeFaces(bb, ms.getLast().getMatrix(), s, cr, cg, cb, eb);
            ms.pop();
         }

         tess.draw();
         RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
         GL11.glEnable(2848);
         bb.begin(1, DefaultVertexFormats.POSITION_COLOR);
         float er = Math.min(1.0F, cr * 1.5F);
         float eg = Math.min(1.0F, cg * 1.5F);
         eb = Math.min(1.0F, cb * 1.5F);
         Iterator var25 = visible.iterator();

         while(var25.hasNext()) {
            float[] p = (float[])var25.next();
            float a = this.getAlpha(p);
            ms.push();
            ms.translate((double)p[0] - cam.x, (double)p[1] - cam.y, (double)p[2] - cam.z);
            ms.rotate(Vector3f.XP.rotationDegrees(p[6]));
            ms.rotate(Vector3f.YP.rotationDegrees(p[7]));
            ms.rotate(Vector3f.ZP.rotationDegrees(p[8]));
            this.drawDashedEdges(bb, ms.getLast().getMatrix(), s, er, eg, eb, a);
            ms.pop();
         }

         tess.draw();
         RenderSystem.depthMask(true);
         RenderSystem.enableCull();
         RenderSystem.enableDepthTest();
         RenderSystem.defaultBlendFunc();
         RenderSystem.disableBlend();
         RenderSystem.enableTexture();
         RenderSystem.popMatrix();
      }
   }

   private float getAlpha(float[] p) {
      return MathHelper.clamp(p[12] / p[13], 0.0F, 1.0F) * Math.min(1.0F, (p[13] - p[12]) / 20.0F);
   }

   private void drawGlow(BufferBuilder bb, MatrixStack ms, ActiveRenderInfo info, Vector3d pos, Vector3d cam, float scale, float r, float g, float b, float a) {
      if (!(a <= 0.001F)) {
         ms.push();
         ms.translate(pos.x - cam.x, pos.y - cam.y, pos.z - cam.z);
         ms.rotate(Vector3f.YP.rotationDegrees(-info.getYaw()));
         ms.rotate(Vector3f.XP.rotationDegrees(info.getPitch()));
         ms.scale(scale, scale, scale);
         Matrix4f m = ms.getLast().getMatrix();
         bb.pos(m, -0.5F, 0.5F, 0.0F).tex(0.0F, 1.0F).color(r, g, b, a).endVertex();
         bb.pos(m, 0.5F, 0.5F, 0.0F).tex(1.0F, 1.0F).color(r, g, b, a).endVertex();
         bb.pos(m, 0.5F, -0.5F, 0.0F).tex(1.0F, 0.0F).color(r, g, b, a).endVertex();
         bb.pos(m, -0.5F, -0.5F, 0.0F).tex(0.0F, 0.0F).color(r, g, b, a).endVertex();
         ms.pop();
      }
   }

   private void drawCubeFaces(BufferBuilder bb, Matrix4f m, float s, float r, float g, float b, float a) {
      float[][] faces = new float[][]{{-s, -s, s, s, -s, s, s, s, s, -s, s, s}, {s, -s, -s, -s, -s, -s, -s, s, -s, s, s, -s}, {-s, s, s, s, s, s, s, s, -s, -s, s, -s}, {-s, -s, -s, s, -s, -s, s, -s, s, -s, -s, s}, {s, -s, s, s, -s, -s, s, s, -s, s, s, s}, {-s, -s, -s, -s, -s, s, -s, s, s, -s, s, -s}};
      float[][] var9 = faces;
      int var10 = faces.length;

      for(int var11 = 0; var11 < var10; ++var11) {
         float[] f = var9[var11];

         for(int i = 0; i < 12; i += 3) {
            bb.pos(m, f[i], f[i + 1], f[i + 2]).color(r, g, b, a).endVertex();
         }
      }

   }

   private void drawDashedEdges(BufferBuilder bb, Matrix4f m, float s, float r, float g, float b, float a) {
      float[][] edges = new float[][]{{-s, -s, -s, s, -s, -s}, {s, -s, -s, s, -s, s}, {s, -s, s, -s, -s, s}, {-s, -s, s, -s, -s, -s}, {-s, s, -s, s, s, -s}, {s, s, -s, s, s, s}, {s, s, s, -s, s, s}, {-s, s, s, -s, s, -s}, {-s, -s, -s, -s, s, -s}, {s, -s, -s, s, s, -s}, {s, -s, s, s, s, s}, {-s, -s, s, -s, s, s}};
      float d = s * 0.3F;
      float gp = s * 0.25F;
      float[][] var11 = edges;
      int var12 = edges.length;

      for(int var13 = 0; var13 < var12; ++var13) {
         float[] e = var11[var13];
         this.dLine(bb, m, e[0], e[1], e[2], e[3], e[4], e[5], d, gp, r, g, b, a);
      }

   }

   private void dLine(BufferBuilder bb, Matrix4f m, float x1, float y1, float z1, float x2, float y2, float z2, float dl, float gl, float r, float g, float b, float a) {
      float dx = x2 - x1;
      float dy = y2 - y1;
      float dz = z2 - z1;
      float len = MathHelper.sqrt(dx * dx + dy * dy + dz * dz);
      if (!(len < 0.001F)) {
         float nx = dx / len;
         float ny = dy / len;
         float nz = dz / len;
         float p = 0.0F;

         for(boolean dr = true; p < len; dr = !dr) {
            float e = Math.min(p + (dr ? dl : gl), len);
            if (dr) {
               bb.pos(m, x1 + nx * p, y1 + ny * p, z1 + nz * p).color(r, g, b, a).endVertex();
               bb.pos(m, x1 + nx * e, y1 + ny * e, z1 + nz * e).color(r, g, b, a).endVertex();
            }

            p = e;
         }

      }
   }

   private float[] spawnCube() {
      int life = 420 + this.random.nextInt(420);
      WorldBorder border = mc.world.getWorldBorder();
      double minX = border.minX() + 16.0;
      double maxX = border.maxX() - 16.0;
      double minZ = border.minZ() + 16.0;
      double maxZ = border.maxZ() - 16.0;
      double x = MathHelper.lerp(this.random.nextDouble(), minX, maxX);
      double z = MathHelper.lerp(this.random.nextDouble(), minZ, maxZ);
      double y = (double)mc.world.getHeight() * 0.25 + this.random.nextDouble() * (double)mc.world.getHeight() * 0.7;
      float yaw = this.random.nextFloat() * 360.0F;
      float v = 0.01F + this.random.nextFloat() * 0.02F;
      float vx = -MathHelper.sin((float)Math.toRadians((double)yaw)) * v;
      float vz = MathHelper.cos((float)Math.toRadians((double)yaw)) * v;
      float vy = (this.random.nextFloat() - 0.5F) * 0.01F;
      return new float[]{(float)x, (float)y, (float)z, vx, vy, vz, this.random.nextFloat() * 360.0F, this.random.nextFloat() * 360.0F, this.random.nextFloat() * 360.0F, (this.random.nextFloat() - 0.5F) * 1.5F, (this.random.nextFloat() - 0.5F) * 1.5F, (this.random.nextFloat() - 0.5F) * 1.5F, (float)life, (float)life, (float)(this.random.nextDouble() * Math.PI * 2.0), this.random.nextFloat() * 10.0F};
   }
}
