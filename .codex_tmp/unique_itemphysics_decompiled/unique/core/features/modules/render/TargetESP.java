package unique.core.features.modules.render;

import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_757;
import net.minecraft.class_7833;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.HudEditor;
import unique.core.features.modules.combat.Aura;
import unique.core.setting.Setting;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.Render3DEngine;
import unique.core.utility.render.animation.CaptureMark;

public class TargetESP extends Module {
   private final Setting<TargetESP.Mode> mode = new Setting<>("Mode", TargetESP.Mode.None);
   public static final class_2960 glow = class_2960.method_60655("unique", "textures/particles/glow.png");
   private static final int MAX_PARTICLES = 3;
   private static final float BASE_MUL = 0.04F;
   private static final float ALPHA_STEP = 0.005F;
   private final List<TargetESP.GhostParticle> particles = new ArrayList<>();
   private final TargetESP.LocalInfinityAnimation moving = new TargetESP.LocalInfinityAnimation();
   private final TargetESP.LocalSimpleAnimation targetEspAnim = new TargetESP.LocalSimpleAnimation(300.0F);

   public TargetESP() {
      super("TargetESP", Module.Category.RENDER);
   }

   @Override
   public void onRender3D(class_4587 stack) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         Aura aura = ModuleManager.aura;
         if (aura != null && aura.isEnabled() && Aura.target instanceof class_1309 target && target.method_5805()) {
            TargetESP.Mode currentMode = this.mode.getValue();
            if (currentMode == TargetESP.Mode.GhostV2) {
               this.renderGhostV2(target);
            } else {
               if (currentMode == TargetESP.Mode.Circle) {
                  Render3DEngine.drawOldTargetEsp(stack, target);
               }

               if (currentMode == TargetESP.Mode.NurikZapen) {
                  CaptureMark.render(target);
               }

               if (currentMode == TargetESP.Mode.ZenixC) {
                  Render3DEngine.renderZenixC(20, 1.0F, target);
               }

               if (currentMode == TargetESP.Mode.Rockstar) {
                  Render3DEngine.drawChain(stack, target, 0.75F, 20);
               }
            }
         }
      }
   }

   private void renderGhostV2(class_1309 target) {
      if (this.particles.size() < 3) {
         this.particles.add(new TargetESP.GhostParticle(target.method_19538(), 0.3F));
      }

      float fpsFactor = 500.0F / Math.max(mc.method_47599(), 5);
      RenderSystem.enableBlend();
      RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
      RenderSystem.setShaderTexture(0, glow);
      RenderSystem.setShader(class_757::method_34543);
      RenderSystem.disableCull();
      RenderSystem.disableDepthTest();
      RenderSystem.depthMask(false);
      this.moving.animate(this.moving.get() + 15.0F, 50.0F);
      this.targetEspAnim.setForward(target.field_6235 > 7);
      class_243 cameraPos = mc.field_1773.method_19418().method_19326();
      List<TargetESP.GhostParticle> toRemove = new ArrayList<>();

      for (int i = 0; i < this.particles.size(); i++) {
         TargetESP.GhostParticle p = this.particles.get(i);
         float angleOffset = i * 360.0F / 3.0F;
         double radian = Math.toRadians(this.moving.get() + angleOffset);
         float radius = 0.3F - this.targetEspAnim.get() * 0.3F;
         float x = (float)Math.sin(radian) * radius;
         float z = (float)Math.cos(radian) * radius;
         double yHeight = 0.2 + target.method_17682() / 2.0 * Math.sin(Math.toRadians(this.moving.get() / (i + 1.0F)));
         class_243 targetPos = target.method_19538().method_1031(x, yHeight, z);
         p.motion = targetPos.method_1020(p.pos).method_1021(0.04F * fpsFactor);
         p.render(cameraPos, fpsFactor, i);
         if (p.alpha < 1.0F) {
            p.alpha = Math.max(0.0F, p.alpha - 0.005F * fpsFactor);
         }

         if (p.alpha <= 0.0F) {
            toRemove.add(p);
         }
      }

      RenderSystem.depthMask(true);
      RenderSystem.enableDepthTest();
      RenderSystem.disableBlend();
      this.particles.removeAll(toRemove);
   }

   private class GhostParticle {
      public class_243 pos;
      public class_243 motion = class_243.field_1353;
      private final List<Vector4f> tail = new CopyOnWriteArrayList<>();
      public float alpha = 1.0F;
      private final float size;

      public GhostParticle(class_243 pos, float size) {
         this.pos = pos;
         this.size = size;
      }

      public void render(class_243 cameraPos, float fpsFactor, int index) {
         this.pos = this.pos.method_1019(this.motion);
         float length = 30.0F;
         this.tail.add(new Vector4f((float)this.pos.field_1352, (float)this.pos.field_1351 + 0.7F, (float)this.pos.field_1350, length));

         for (Vector4f vec : this.tail) {
            if (vec.w() > 0.0F) {
               float lifePercent = vec.w() / length;
               float miniSize = this.size * lifePercent;
               Color hudColor = HudEditor.getColor((int)(index * 100 + vec.w()));
               int color = Render2DEngine.injectAlpha(hudColor, (int)(lifePercent * this.alpha * 255.0F)).getRGB();
               this.drawBillboardQuad(vec, miniSize, cameraPos, color);
               float upSpeed = 0.004F / Math.max(Module.mc.method_47599(), 5) * 300.0F;
               float decay = 0.3F / Math.max(Module.mc.method_47599(), 5) * 300.0F;
               vec.set(vec.x(), vec.y() + upSpeed, vec.z(), vec.w() - decay);
            }

            if (vec.w() <= 0.0F) {
               this.tail.remove(vec);
            }
         }
      }

      private void drawBillboardQuad(Vector4f p, float scale, class_243 cameraPos, int color) {
         class_4184 camera = Module.mc.field_1773.method_19418();
         class_4587 matrices = new class_4587();
         matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
         matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
         matrices.method_22904(p.x() - cameraPos.field_1352, p.y() - cameraPos.field_1351, p.z() - cameraPos.field_1350);
         matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
         matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
         Matrix4f matrix = matrices.method_23760().method_23761();
         class_289 tessellator = class_289.method_1348();
         class_287 buffer = tessellator.method_60827(class_5596.field_27382, class_290.field_1575);
         float hSize = scale / 2.0F;
         buffer.method_22918(matrix, -hSize, hSize, 0.0F).method_22913(0.0F, 1.0F).method_39415(color);
         buffer.method_22918(matrix, hSize, hSize, 0.0F).method_22913(1.0F, 1.0F).method_39415(color);
         buffer.method_22918(matrix, hSize, -hSize, 0.0F).method_22913(1.0F, 0.0F).method_39415(color);
         buffer.method_22918(matrix, -hSize, -hSize, 0.0F).method_22913(0.0F, 0.0F).method_39415(color);
         class_286.method_43433(buffer.method_60800());
      }
   }

   private static class LocalInfinityAnimation {
      private float output;
      private float endpoint;

      public void animate(float destination, float speed) {
         this.output = this.endpoint - (this.endpoint - destination) * 0.1F;
         this.endpoint = destination;
      }

      public float get() {
         return this.output;
      }
   }

   private static class LocalSimpleAnimation {
      private float value;
      private long lastMS = System.currentTimeMillis();
      private final float speed;
      private boolean forward;

      public LocalSimpleAnimation(float speed) {
         this.speed = speed;
      }

      public void setForward(boolean forward) {
         this.forward = forward;
      }

      public float get() {
         long now = System.currentTimeMillis();
         float step = (float)(now - this.lastMS) / this.speed;
         this.lastMS = now;
         this.value = this.forward ? Math.min(1.0F, this.value + step) : Math.max(0.0F, this.value - step);
         return this.value;
      }
   }

   public static enum Mode {
      None,
      Circle,
      NurikZapen,
      GhostV2,
      ZenixC,
      Rockstar;
   }
}
