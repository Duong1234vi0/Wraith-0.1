package sky.core.handlers.impl;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import sky.core.SkyCore;
import sky.core.events.EventRender3D;
import sky.core.events.EventWorld3D;
import sky.core.modules.impl.combat.AimBot;
import sky.core.modules.impl.combat.AttackAura;
import sky.core.modules.impl.visuals.TargetESP;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.Wrapper;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.animation.Easings;
import sky.core.utils.math.AnimationTest;
import sky.core.utils.math.Easing;
import sky.core.utils.math.MathUtil;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.GhostRenderer3D;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.TargetCrystalRender;

public class TargetESPHandler {
   private static final AnimationUtil szAnim;
   private static final AnimationUtil alpAnim;
   private static Vector3d lstSqPos;
   private static float lstSqHH;
   private static boolean lstHT;
   private static long sqRotMs;
   private static double sqRotPh;
   private static Entity lstSqEnt;
   private static final AnimationUtil htScAnim;
   private static final AnimationUtil htClAnim;
   private static LivingEntity target;
   private LivingEntity crystalsLastTarget = null;
   private final AnimationTest crystalsAnim;
   private static final AnimationUtil nigerAnim;
   private static float nigerPlavnost;
   private static LivingEntity nigerTarget;
   private static final ResourceLocation BLOOM_TEX;
   private static final ResourceLocation BLOOM_TEX4;
   private static final int MAX_FOLLOW_GHOSTS = 3;
   private static final float FOLLOW_ALPHA_STEP = 0.005F;
   private static final float FOLLOW_MOTION_MUL = 0.05F;
   private static final float FOLLOW_BASE_SIZE = 0.3F;
   private final CopyOnWriteArrayList glowPoints;
   private LivingEntity ghostTarget;
   private static final int PIG_FULL_BRIGHT = 15728880;
   private final AnimationTest ghostAnim;
   private final List followGhosts;
   private LivingEntity followGhostTarget;
   private float followSpin;
   private float jelloMoving;
   private long lastDamageTime;
   private int lastTargetHurtTime;
   private float damageFlashIntensity;
   private static final long DAMAGE_FLASH_DURATION = 350L;
   private static final AnimationUtil timeAnimation;
   private long startTime;
   private float animationSpeed;

   public TargetESPHandler() {
      this.crystalsAnim = new AnimationTest(Easing.LINEAR, 300L);
      this.glowPoints = new CopyOnWriteArrayList();
      this.ghostAnim = new AnimationTest(Easing.EASE_OUT_CIRC, 400L);
      this.followGhosts = new ArrayList();
      this.startTime = System.currentTimeMillis();
      this.animationSpeed = 0.0F;
   }

   private TargetESP getTargetESPModule() {
      return SkyCore.getInstance().getModuleManager().getTargetESP();
   }

   private boolean isTargetESPMode(String mode) {
      TargetESP targetESP = this.getTargetESPModule();
      return targetESP != null && targetESP.isEnabled() && targetESP.getMode().is(mode);
   }

   private boolean isCrystalMode() {
      TargetESP targetESP = this.getTargetESPModule();
      if (targetESP != null && targetESP.isEnabled()) {
         return targetESP.getMode().is("Кристаллы") || targetESP.getMode().is("Кристалики") || targetESP.getMode().is("Кристалы");
      } else {
         return false;
      }
   }

   private AttackAura getAura() {
      return SkyCore.getInstance().getModuleManager().getAttackAura();
   }

   private AimBot getAimBot() {
      return (AimBot)SkyCore.getInstance().getModuleManager().getModule(AimBot.class);
   }

   private List getEspTargets() {
      List list = new ArrayList();
      AimBot aimBot = this.getAimBot();
      if (aimBot != null && aimBot.isEnabled() && aimBot.isAiming()) {
         LivingEntity aimTarget = aimBot.getTarget();
         if (aimTarget != null && aimTarget.isAlive()) {
            list.add(aimTarget);
         }
      }

      AttackAura aura = this.getAura();
      if (aura != null && aura.isEnabled()) {
         LivingEntity auraTarget = AttackAura.getTarget();
         if (auraTarget != null && auraTarget.isAlive() && !list.contains(auraTarget)) {
            list.add(auraTarget);
         }
      }

      return list;
   }

   private boolean hasEspTargets() {
      return !this.getEspTargets().isEmpty();
   }

   @EventTarget
   public void onRender3D(EventRender3D e) {
      this.renderSquare(e);
      this.renderCircle(e);
   }

   @EventTarget
   public void onWorld3D(EventWorld3D e) {
      this.sada(e);
      this.renderJello(e);
      this.handleGhost(e);
      this.renderetoROKSTAR(e);
      this.renderGhostRider(e);
      this.spawnGhostPoints(e);
      this.renderPigs(e);
   }

   @EventTarget
   private void renderSquare(EventRender3D e) {
      boolean rombMode = this.isTargetESPMode("Ромб");
      boolean romb2Mode = this.isTargetESPMode("Ромб-2");
      if (rombMode || romb2Mode) {
         List targets = this.getEspTargets();
         if (!targets.isEmpty()) {
            alpAnim.update(1.0F);
            szAnim.update(1.0F);
            Iterator var10 = targets.iterator();

            while(var10.hasNext()) {
               LivingEntity t = (LivingEntity)var10.next();
               this.renderSquareAt(e, t, rombMode, romb2Mode, true);
            }

            lstHT = true;
            lstSqEnt = (Entity)targets.get(targets.size() - 1);
         } else {
            ActiveRenderInfo cm = Wrapper.mc.getRenderManager().info;
            Vector3d cp = cm.getProjectedView();
            alpAnim.update(0.0F);
            szAnim.update(2.0F);
            if (alpAnim.getValue() <= 0.01F) {
               lstHT = false;
               lstSqEnt = null;
            } else {
               Entity f = lstSqEnt;
               Vector3d bp;
               float hh;
               if (f != null && f.isAlive()) {
                  bp = MathUtil.interpolate(f, e.getPartialTicks());
                  hh = f.getHeight() / 2.0F;
                  lstSqPos = bp;
                  lstSqHH = hh;
               } else {
                  if (lstSqPos == null || !lstHT) {
                     return;
                  }

                  bp = lstSqPos;
                  hh = lstSqHH;
               }

               this.renderSquareAt(e, (LivingEntity)f, rombMode, romb2Mode, false, bp, hh, cp, cm);
            }
         }
      }
   }

   private void renderSquareAt(EventRender3D e, LivingEntity t, boolean rombMode, boolean romb2Mode, boolean active) {
      ActiveRenderInfo cm = Wrapper.mc.getRenderManager().info;
      Vector3d cp = cm.getProjectedView();
      Vector3d bp = MathUtil.interpolate(t, e.getPartialTicks());
      float hh = t.getHeight() / 2.0F;
      this.renderSquareAt(e, t, rombMode, romb2Mode, active, bp, hh, cp, cm);
   }

   private void renderSquareAt(EventRender3D e, LivingEntity t, boolean rombMode, boolean romb2Mode, boolean active, Vector3d bp, float hh, Vector3d cp, ActiveRenderInfo cm) {
      MatrixStack m = new MatrixStack();
      m.translate(bp.x - cp.x, bp.y + (double)hh - cp.y, bp.z - cp.z);
      m.rotate(cm.getRotation().copy());
      float hf = t != null && t.maxHurtTime > 0 ? MathHelper.clamp((float)t.hurtTime / (float)t.maxHurtTime, 0.0F, 1.0F) : 0.0F;
      htScAnim.update(MathHelper.lerp(hf, 1.0F, 0.5F));
      htClAnim.update(hf);
      float sz = szAnim.getValue() * htScAnim.getValue();
      long n = System.currentTimeMillis();
      double dt = sqRotMs == 0L ? 0.0 : (double)(n - sqRotMs) / 1000.0;
      sqRotMs = n;
      double spin = romb2Mode ? -2.15 : 1.667;
      sqRotPh += spin * (active ? 1.0 : 1.5) * dt;
      RenderSystem.pushMatrix();
      RenderSystem.multMatrix(m.getLast().getMatrix());
      float rotMul = romb2Mode ? 260.0F : 180.0F;
      float sizeMul = romb2Mode ? 1.18F : 1.0F;
      RenderSystem.rotatef((float)(Math.sin(sqRotPh) * (double)rotMul), 0.0F, 0.0F, 1.0F);
      RenderSystem.disableDepthTest();
      RenderSystem.disableAlphaTest();
      RenderSystem.disableCull();
      int bc = ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL);
      float mx = htClAnim.getValue();
      int cl = ColorUtil.getColor(MathHelper.clamp((int)((float)ColorUtil.red(bc) * (1.0F - mx) + 255.0F * mx), 0, 255), MathHelper.clamp((int)((float)ColorUtil.green(bc) * (1.0F - mx)), 0, 255), MathHelper.clamp((int)((float)ColorUtil.blue(bc) * (1.0F - mx)), 0, 255), (int)(ThemeEditor.getAlpha(ThemeSettings.MODULE_VISUAL) * MathHelper.clamp(alpAnim.getValue(), 0.0F, 1.0F)));
      float finalSize = sz * sizeMul;
      RenderUtil.drawImage3D(new ResourceLocation("SkyCore/icons/world_render/target.png"), -finalSize / 2.0F, -finalSize / 2.0F, 0.0F, finalSize, finalSize, cl, true);
      RenderSystem.enableCull();
      RenderSystem.enableAlphaTest();
      RenderSystem.enableDepthTest();
      RenderSystem.popMatrix();
   }

   @EventTarget
   private void renderCircle(EventRender3D e) {
      if (this.isTargetESPMode("Кольцо")) {
         Iterator var2 = this.getEspTargets().iterator();

         while(var2.hasNext()) {
            LivingEntity t = (LivingEntity)var2.next();
            this.renderCircleAt(e, t);
         }

      }
   }

   private void renderCircleAt(EventRender3D e, LivingEntity t) {
      float hf = t.maxHurtTime > 0 ? MathHelper.clamp((float)t.hurtTime / (float)t.maxHurtTime, 0.0F, 1.0F) : 0.0F;
      float r = t.getWidth() * 0.8F;
      Vector3d v = MathUtil.interpolate(t, e.getPartialTicks());
      double d = 3000.0;
      double el = (double)System.currentTimeMillis() % d;
      boolean s = el > d / 2.0;
      double p = el / (d / 2.0);
      p = s ? p - 1.0 : 1.0 - p;
      p = p < 0.5 ? 2.0 * p * p : 1.0 - Math.pow(-2.0 * p + 2.0, 2.0) / 2.0;
      double ea = (double)(t.getHeight() / 2.0F) * (p > 0.5 ? 1.0 - p : p) * (double)(s ? -1 : 1);
      BufferBuilder b = Tessellator.getInstance().getBuffer();
      MatrixStack st = new MatrixStack();
      Vector3d o = Wrapper.mc.getRenderManager().info.getProjectedView();
      st.push();
      RenderSystem.disableTexture();
      RenderSystem.enableBlend();
      RenderSystem.disableAlphaTest();
      RenderSystem.shadeModel(7425);
      RenderSystem.disableCull();
      GlStateManager.depthMask(false);
      RenderSystem.lineWidth(2.0F);
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.disableDepthTest();
      RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
      b.begin(8, DefaultVertexFormats.POSITION_COLOR);

      int i;
      int bcc;
      for(i = 0; i <= 360; ++i) {
         bcc = ColorUtil.gradientModule(i * 5);
         int cll = ColorUtil.getColor(MathHelper.clamp((int)((float)ColorUtil.red(bcc) * (1.0F - hf) + 60.0F * hf), 0, 255), MathHelper.clamp((int)((float)ColorUtil.green(bcc) * (1.0F - hf)), 0, 255), MathHelper.clamp((int)((float)ColorUtil.blue(bcc) * (1.0F - hf)), 0, 255), 255);
         double c = Math.cos(Math.toRadians((double)i));
         double si = Math.sin(Math.toRadians((double)i));
         b.pos(st.getLast().getMatrix(), (float)(v.x + c * (double)r - o.x), (float)(v.y + (double)t.getHeight() * p - o.y), (float)(v.z + si * (double)r - o.z)).color(cll).endVertex();
         b.pos(st.getLast().getMatrix(), (float)(v.x + c * (double)r - o.x), (float)(v.y + (double)t.getHeight() * p + ea - o.y), (float)(v.z + si * (double)r - o.z)).color(ColorUtil.applyOpacity(cll, 0)).endVertex();
      }

      Tessellator.getInstance().draw();
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      b.begin(2, DefaultVertexFormats.POSITION_COLOR);

      for(i = 0; i <= 360; ++i) {
         bcc = ColorUtil.gradientModule(i * 5);
         double c = Math.cos(Math.toRadians((double)i));
         double si = Math.sin(Math.toRadians((double)i));
         b.pos(st.getLast().getMatrix(), (float)(v.x + c * (double)r - o.x), (float)(v.y + (double)t.getHeight() * p - o.y), (float)(v.z + si * (double)r - o.z)).color(ColorUtil.getColor(MathHelper.clamp((int)((float)ColorUtil.red(bcc) * (1.0F - hf) + 60.0F * hf), 0, 255), MathHelper.clamp((int)((float)ColorUtil.green(bcc) * (1.0F - hf)), 0, 255), MathHelper.clamp((int)((float)ColorUtil.blue(bcc) * (1.0F - hf)), 0, 255), 255)).endVertex();
      }

      Tessellator.getInstance().draw();
      RenderSystem.enableDepthTest();
      RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
      RenderSystem.enableCull();
      RenderSystem.disableBlend();
      RenderSystem.enableTexture();
      RenderSystem.enableAlphaTest();
      RenderSystem.shadeModel(7424);
      GlStateManager.depthMask(true);
      st.pop();
   }

   @EventTarget
   private void sada(EventWorld3D eventRender3D) {
      if (this.isCrystalMode()) {
         TargetESP targetESP = this.getTargetESPModule();
         List targets = this.getEspTargets();
         if (!targets.isEmpty()) {
            target = (LivingEntity)targets.get(0);
            this.crystalsLastTarget = (LivingEntity)targets.get(0);
         }

         this.crystalsAnim.run(targets.isEmpty() ? 0.0 : 1.0);
         if (this.crystalsLastTarget != null && !(this.crystalsAnim.getValue() <= 0.001)) {
            float anim = (float)this.crystalsAnim.getValue();
            float scatter = 1.0F - anim;
            float size = targetESP != null ? (Float)targetESP.getCrystalSize().get() : 2.0F;
            Iterator var7 = targets.iterator();

            while(var7.hasNext()) {
               LivingEntity crystalTarget = (LivingEntity)var7.next();
               TargetCrystalRender.render(eventRender3D, crystalTarget, anim, scatter, size);
            }

            if (anim <= 0.01F && targets.isEmpty()) {
               this.crystalsLastTarget = null;
            }

         } else {
            if (targets.isEmpty()) {
               this.crystalsLastTarget = null;
            }

         }
      }
   }

   @EventTarget
   private void renderJello(EventWorld3D event) {
      if (this.isTargetESPMode("Кружочек")) {
         List targets = this.getEspTargets();
         if (!targets.isEmpty()) {
            target = (LivingEntity)targets.get(0);

            LivingEntity current;
            for(Iterator var3 = targets.iterator(); var3.hasNext(); this.lastTargetHurtTime = Math.max(this.lastTargetHurtTime, current.hurtTime)) {
               current = (LivingEntity)var3.next();
               if (current.hurtTime > this.lastTargetHurtTime) {
                  this.lastDamageTime = System.currentTimeMillis();
               }
            }
         } else {
            this.lastTargetHurtTime = 0;
         }

         timeAnimation.update(targets.isEmpty() ? 0.0F : 1.0F);
         float alpha = MathHelper.clamp(timeAnimation.getValue(), 0.0F, 1.0F);
         if (!(alpha <= 0.0F)) {
            TargetESP targetESP = this.getTargetESPModule();
            if (targetESP != null) {
               List toRender = new ArrayList(targets);
               if (toRender.isEmpty() && target != null && target.isAlive()) {
                  toRender.add(target);
               }

               if (!toRender.isEmpty()) {
                  this.jelloMoving += 2.5F;
                  Iterator var6 = toRender.iterator();

                  while(var6.hasNext()) {
                     LivingEntity entityToRender = (LivingEntity)var6.next();
                     this.renderJelloAt(event, entityToRender, targetESP, alpha);
                  }

               }
            }
         }
      }
   }

   private void renderJelloAt(EventWorld3D event, LivingEntity entityToRender, TargetESP targetESP, float alpha) {
      Vector3d cameraPos = Wrapper.mc.getRenderManager().info.getProjectedView();
      Vector3d targetPos = MathUtil.interpolate(entityToRender, event.partialTicks);
      Vector3d renderPos = targetPos.subtract(cameraPos);
      float entityWidth = entityToRender.getWidth() * 1.65F;
      float entityHeight = entityToRender.getHeight() - 0.15F;
      int color = this.applyDamageFlash(ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL), (Boolean)targetESP.getDamageRed().get());
      int r = ColorUtil.red(color);
      int g = ColorUtil.green(color);
      int b = ColorUtil.blue(color);
      MatrixStack matrices = event.getMatrixStack();
      RenderSystem.enableBlend();
      RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
      RenderSystem.disableCull();
      if ((Boolean)targetESP.getThroughWalls().get()) {
         RenderSystem.disableDepthTest();
      } else {
         RenderSystem.enableDepthTest();
         RenderSystem.depthMask(false);
      }

      matrices.push();
      matrices.translate(renderPos.x, renderPos.y, renderPos.z);
      Wrapper.mc.getTextureManager().bindTexture(BLOOM_TEX);
      BufferBuilder buffer = Tessellator.getInstance().getBuffer();
      buffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);

      for(int i = 0; i < 360; i += 2) {
         float scale = Math.max(0.5F, 0.7F - 0.2F * alpha);
         double rad = Math.toRadians((double)((float)i + this.jelloMoving));
         float xOffset = (float)(Math.cos(rad) * (double)entityWidth * (double)scale);
         float zOffset = (float)(Math.sin(rad) * (double)entityWidth * (double)scale);
         float sizeBase = 0.2F;

         for(int jLayer = 0; jLayer < 15; ++jLayer) {
            float yOffsetLayer = entityHeight / 1.7F + entityHeight / 2.0F * (float)Math.cos(Math.toRadians((double)(this.jelloMoving / 1.5F + (float)jLayer * 2.0F)));
            matrices.push();
            matrices.translate((double)xOffset, (double)yOffsetLayer, (double)zOffset);
            matrices.rotate(Wrapper.mc.getRenderManager().getCameraOrientation());
            Matrix4f mat = matrices.getLast().getMatrix();
            int finalAlpha = (int)(255.0F * alpha * ((float)jLayer / 15.0F) * 0.05F);
            buffer.pos(mat, -sizeBase / 2.0F, -sizeBase / 2.0F, 0.0F).tex(0.0F, 0.0F).color(r, g, b, finalAlpha).endVertex();
            buffer.pos(mat, sizeBase / 2.0F, -sizeBase / 2.0F, 0.0F).tex(1.0F, 0.0F).color(r, g, b, finalAlpha).endVertex();
            buffer.pos(mat, sizeBase / 2.0F, sizeBase / 2.0F, 0.0F).tex(1.0F, 1.0F).color(r, g, b, finalAlpha).endVertex();
            buffer.pos(mat, -sizeBase / 2.0F, sizeBase / 2.0F, 0.0F).tex(0.0F, 1.0F).color(r, g, b, finalAlpha).endVertex();
            matrices.pop();
         }
      }

      Tessellator.getInstance().draw();
      matrices.pop();
      if (!(Boolean)targetESP.getThroughWalls().get()) {
         RenderSystem.depthMask(true);
      }

      RenderSystem.enableDepthTest();
      RenderSystem.enableCull();
      RenderSystem.defaultBlendFunc();
      RenderSystem.disableBlend();
   }

   private int applyDamageFlash(int color, boolean enabled) {
      if (!enabled) {
         return color;
      } else {
         float targetIntensity = 0.0F;
         long timeSinceDamage = System.currentTimeMillis() - this.lastDamageTime;
         if (timeSinceDamage < 350L) {
            float progress = (float)timeSinceDamage / 350.0F;
            targetIntensity = 1.0F - (1.0F - MathHelper.clamp(progress, 0.0F, 1.0F)) * (1.0F - MathHelper.clamp(progress, 0.0F, 1.0F));
         }

         this.damageFlashIntensity = MathHelper.lerp(0.1F, this.damageFlashIntensity, targetIntensity);
         if (this.damageFlashIntensity < 0.05F) {
            return color;
         } else {
            int rr = ColorUtil.red(color);
            int gg = ColorUtil.green(color);
            int bb = ColorUtil.blue(color);
            int aa = ColorUtil.alpha(color);
            int finalR = (int)MathHelper.lerp(this.damageFlashIntensity, (float)rr, 255.0F);
            int finalG = (int)MathHelper.lerp(this.damageFlashIntensity, (float)gg, 50.0F);
            int finalB = (int)MathHelper.lerp(this.damageFlashIntensity, (float)bb, 50.0F);
            return ColorUtil.getColor(finalR, finalG, finalB, aa);
         }
      }
   }

   @EventTarget
   private void handleGhost(EventWorld3D eventRender) {
      boolean enabled = this.isTargetESPMode("Призраки");
      if (enabled) {
         List targets = this.getEspTargets();
         if (!targets.isEmpty()) {
            target = (LivingEntity)targets.get(0);
         }

         if (targets.isEmpty() && target == null) {
            timeAnimation.update(1.0F);
            this.startTime = System.currentTimeMillis();
         } else {
            this.animationSpeed += (float)(9L * (System.currentTimeMillis() - this.startTime)) / 1000.0F;
            this.startTime = System.currentTimeMillis();
            timeAnimation.update(this.hasEspTargets() ? 1.0F : 0.0F);
            List toRender = new ArrayList(targets);
            if (toRender.isEmpty() && target != null && target.isAlive()) {
               toRender.add(target);
            }

            Iterator var5 = toRender.iterator();

            while(var5.hasNext()) {
               LivingEntity ghostEntity = (LivingEntity)var5.next();
               this.renderGhostAt(eventRender, ghostEntity);
            }

         }
      }
   }

   private void renderGhostAt(EventWorld3D eventRender, LivingEntity ghostEntity) {
      float hf = ghostEntity.maxHurtTime > 0 ? MathHelper.clamp((float)ghostEntity.hurtTime / (float)ghostEntity.maxHurtTime, 0.0F, 1.0F) : 0.0F;
      htClAnim.update(hf);
      MatrixStack e = eventRender.getMatrixStack();
      Vector3d vector3d = MathUtil.interpolate(ghostEntity, eventRender.partialTicks);
      double x = vector3d.x - Wrapper.mc.getRenderManager().info.getProjectedView().x;
      double y = vector3d.y - Wrapper.mc.getRenderManager().info.getProjectedView().y;
      double z = vector3d.z - Wrapper.mc.getRenderManager().info.getProjectedView().z;
      int rings = 3;
      int n3 = 12;
      int n4 = 3 * rings;
      float size = 50.0F;

      for(int i = 0; i < n4; i += rings) {
         for(int j = 0; j < n3; ++j) {
            int moduleVisual = ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL);
            float mx = htClAnim.getValue();
            int cl = ColorUtil.getColor(MathHelper.clamp((int)((float)ColorUtil.red(moduleVisual) * (1.0F - mx) + 255.0F * mx), 0, 255), MathHelper.clamp((int)((float)ColorUtil.green(moduleVisual) * (1.0F - mx)), 0, 255), MathHelper.clamp((int)((float)ColorUtil.blue(moduleVisual) * (1.0F - mx)), 0, 255), (int)(ThemeEditor.getAlpha(ThemeSettings.MODULE_VISUAL) * timeAnimation.getValue()));
            float f2 = this.animationSpeed + (float)j * 0.1F;
            float f3 = 0.8F;
            float f4 = 0.5F;
            int n5 = (int)Math.pow((double)i, 2.0);
            e.push();
            e.translate(x + (double)(f3 * MathHelper.sin(f2 + (float)n5)), y + (double)f4 + (double)(0.3F * MathHelper.sin(this.animationSpeed + (float)j * 0.2F)) + (double)(0.2F * (float)i), z + (double)(f3 * MathHelper.cos(f2 - (float)n5)));
            e.scale(timeAnimation.getValue() * (0.005F + (float)j / 1800.0F));
            e.rotate(Wrapper.mc.getRenderManager().getCameraOrientation());
            RenderSystem.disableDepthTest();
            RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
            RenderSystem.disableAlphaTest();
            RenderSystem.shadeModel(7425);
            RenderUtil.drawTexture(e, BLOOM_TEX4, -size / 2.0F, -size / 2.0F, size, size, cl);
            RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
            RenderSystem.enableDepthTest();
            RenderSystem.enableAlphaTest();
            RenderSystem.shadeModel(7424);
            e.pop();
         }
      }

   }

   private void renderetoROKSTAR(EventWorld3D eventRender) {
      boolean enabled = this.isTargetESPMode("Души");
      if (enabled) {
         List targets = this.getEspTargets();
         if (!targets.isEmpty()) {
            nigerTarget = (LivingEntity)targets.get(0);
         }

         nigerAnim.update(targets.isEmpty() ? 0.0F : 1.0F);
         nigerPlavnost = (float)(System.currentTimeMillis() % 100000L) / 1.5F;
         if (nigerAnim.getValue() <= 0.01F) {
            if (targets.isEmpty()) {
               nigerTarget = null;
            }

         } else {
            List toRender = new ArrayList(targets);
            if (toRender.isEmpty() && nigerTarget != null && nigerTarget.isAlive()) {
               toRender.add(nigerTarget);
            }

            Iterator var5 = toRender.iterator();

            while(var5.hasNext()) {
               LivingEntity soulTarget = (LivingEntity)var5.next();
               this.renderSoulsAt(eventRender, soulTarget);
            }

            if (targets.isEmpty() && nigerAnim.getValue() <= 0.01F) {
               nigerTarget = null;
            }

         }
      }
   }

   private void renderSoulsAt(EventWorld3D eventRender, LivingEntity nigerTarget) {
      MatrixStack ms = eventRender.getMatrixStack();
      Vector3d pos = MathUtil.interpolate(nigerTarget, eventRender.partialTicks);
      double x = pos.x - Wrapper.mc.getRenderManager().info.getProjectedView().x;
      double y = pos.y - Wrapper.mc.getRenderManager().info.getProjectedView().y;
      double z = pos.z - Wrapper.mc.getRenderManager().info.getProjectedView().z;
      float width = nigerTarget.getWidth() * 1.5F;
      int color = ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL);
      float alpha = (float)((double)(ThemeEditor.getAlpha(ThemeSettings.MODULE_VISUAL) * nigerAnim.getValue()) / 255.0);
      ms.push();
      RenderSystem.enableBlend();
      RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
      RenderSystem.disableDepthTest();
      RenderSystem.disableAlphaTest();
      RenderSystem.depthMask(false);
      RenderSystem.disableCull();
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder builder = tessellator.getBuffer();
      Wrapper.mc.getTextureManager().bindTexture(BLOOM_TEX);
      RenderSystem.shadeModel(7425);
      builder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      int r = ColorUtil.red(color);
      int g = ColorUtil.green(color);
      int b = ColorUtil.blue(color);
      int step = 2;
      int wormTick = 0;
      int wormCD = 0;

      for(int i = 0; i < 360; i += step) {
         float size = 0.13F + 0.005F * (float)wormTick;
         float bigSize = 0.7F + 0.005F * (float)wormTick;
         if (wormCD > 0) {
            wormCD -= step;
         } else if ((wormTick += step) > 50) {
            wormCD = 100;
            wormTick = 0;
         } else {
            float val = Math.max(0.5F, 1.2F - 0.5F * nigerAnim.getValue());
            float angleRad = (float)Math.toRadians((double)((float)i + nigerPlavnost));
            float sin = (float)(Math.sin((double)angleRad) * (double)width * (double)val);
            float cos = (float)(Math.cos((double)angleRad) * (double)width * (double)val);
            float waveY = (float)Math.sin(Math.toRadians((double)((float)i / 2.0F + nigerPlavnost / 5.0F)));
            ms.push();
            ms.translate(x + (double)sin, y + (double)(nigerTarget.getHeight() / 1.5F) + (double)(nigerTarget.getHeight() / 3.0F * waveY), z + (double)cos);
            ms.rotate(Wrapper.mc.getRenderManager().getCameraOrientation());
            this.putBloomQuad(builder, ms, -bigSize / 2.0F, -bigSize / 2.0F, bigSize, r, g, b, alpha * 0.05F);
            this.putBloomQuad(builder, ms, -size / 2.0F, -size / 2.0F, size, r, g, b, alpha);
            ms.pop();
         }
      }

      tessellator.draw();
      RenderSystem.depthMask(true);
      RenderSystem.enableDepthTest();
      RenderSystem.enableAlphaTest();
      RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
      RenderSystem.disableBlend();
      RenderSystem.enableCull();
      ms.pop();
   }

   private void spawnGhostPoints(EventWorld3D e) {
      if (!this.isTargetESPMode("Преследующие призраки")) {
         this.glowPoints.clear();
      } else {
         List targets = this.getEspTargets();
         if (!targets.isEmpty()) {
            TargetESP targetESP = this.getTargetESPModule();
            if (targetESP != null) {
               int count = Math.max(1, Math.round((Float)targetESP.getGhostCount().get()));
               int maxTime = Math.max(50, Math.round((Float)targetESP.getGhostLifeTime().get()));
               int color = ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL);
               Iterator var7 = targets.iterator();

               while(var7.hasNext()) {
                  LivingEntity current = (LivingEntity)var7.next();
                  if (current.isAlive()) {
                     this.addGlowPoints(current, count, e.partialTicks, maxTime, color, (Float)targetESP.getStrengthXZ().get(), (Float)targetESP.getStrengthY().get());
                  }
               }

               this.removeOldGlowPoints();
               this.drawGlowPoints(e);
            }
         }
      }
   }

   private void addGlowPoints(LivingEntity entity, int count, float partialTicks, int maxTime, int colorBase, float strengthXZ, float strengthY) {
      float x = (float)(entity.lastTickPosX + (entity.getPosX() - entity.lastTickPosX) * (double)partialTicks);
      float y = (float)(entity.lastTickPosY + (entity.getPosY() - entity.lastTickPosY) * (double)partialTicks);
      float z = (float)(entity.lastTickPosZ + (entity.getPosZ() - entity.lastTickPosZ) * (double)partialTicks);
      float xzRange = entity.getWidth();
      float yRange = entity.getHeight();
      int delayXZ = Math.max(1, (int)strengthXZ);
      int delayY = Math.max(1, (int)strengthY);
      long time = System.currentTimeMillis();

      for(int corner = 0; corner < count; ++corner) {
         float cornerProgress = (float)corner / (float)count;
         float xzRotate = (float)((time + (long)((int)((float)delayXZ * cornerProgress))) % (long)delayXZ) / (float)delayXZ * 360.0F;
         float yProgress = (float)((time + (long)((int)((float)delayY * cornerProgress))) % (long)delayY) / (float)delayY;
         yProgress = (yProgress > 0.5F ? 1.0F - yProgress : yProgress) * 2.0F;
         yProgress = (float)Math.pow((double)yProgress, 2.0);
         double yawRad = Math.toRadians((double)MathHelper.wrapDegrees(cornerProgress * 360.0F + xzRotate));
         float xPos = x - (float)Math.sin(yawRad) * xzRange;
         float yPos = y + yRange * yProgress;
         float zPos = z + (float)Math.cos(yawRad) * xzRange;
         this.glowPoints.add(new GlowPoint(xPos, yPos, zPos, maxTime, colorBase));
      }

   }

   private void removeOldGlowPoints() {
      this.glowPoints.removeIf(GlowPoint::shouldRemove);
   }

   private void drawGlowPoints(EventWorld3D event) {
      if (!this.glowPoints.isEmpty()) {
         RenderSystem.disableDepthTest();
         RenderSystem.depthMask(false);
         RenderSystem.disableCull();
         RenderSystem.enableBlend();
         RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
         MatrixStack matrixStack = event.getMatrixStack();
         Iterator var3 = this.glowPoints.iterator();

         while(var3.hasNext()) {
            GlowPoint point = (GlowPoint)var3.next();
            point.update();
            float timeProgress = point.getTimeProgress();
            float scale = 0.5F * (1.0F - timeProgress);
            int color = point.getColor(timeProgress);
            matrixStack.push();
            Vector3d view = Wrapper.mc.getRenderManager().info.getProjectedView();
            matrixStack.translate((double)point.x - view.x, (double)point.y - view.y, (double)point.z - view.z);
            matrixStack.rotate(Wrapper.mc.getRenderManager().getCameraOrientation());
            matrixStack.scale(scale, scale, scale);
            RenderUtil.drawTexture(matrixStack, BLOOM_TEX4, -0.5F, -0.5F, 1.0F, 1.0F, color);
            matrixStack.pop();
         }

         RenderSystem.enableDepthTest();
         RenderSystem.depthMask(true);
         RenderSystem.enableCull();
         RenderSystem.disableBlend();
      }
   }

   private void renderGhostRider(EventWorld3D eventRender) {
      if (this.isTargetESPMode("Души 2")) {
         List targets = this.getEspTargets();
         if (!targets.isEmpty()) {
            this.ghostTarget = (LivingEntity)targets.get(0);
         }

         this.ghostAnim.run(targets.isEmpty() ? 0.0 : 1.0);
         if (this.ghostAnim.getValue() < 0.01) {
            if (targets.isEmpty()) {
               this.ghostTarget = null;
            }

         } else {
            List toRender = new ArrayList(targets);
            if (toRender.isEmpty() && this.ghostTarget != null && this.ghostTarget.isAlive()) {
               toRender.add(this.ghostTarget);
            }

            Iterator var4 = toRender.iterator();

            while(var4.hasNext()) {
               LivingEntity riderTarget = (LivingEntity)var4.next();
               this.renderGhostRiderAt(eventRender, riderTarget);
            }

         }
      }
   }

   private void renderGhostRiderAt(EventWorld3D eventRender, LivingEntity ghostTarget) {
      Vector3d vec = MathUtil.interpolate(ghostTarget, eventRender.partialTicks);
      double baseX = vec.x - Wrapper.mc.getRenderManager().info.getProjectedView().x;
      double baseY = vec.y - Wrapper.mc.getRenderManager().info.getProjectedView().y + (double)ghostTarget.getHeight() / 2.0;
      double baseZ = vec.z - Wrapper.mc.getRenderManager().info.getProjectedView().z;
      float alphaPC = (float)this.ghostAnim.getValue();
      int baseColor = ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL);
      long time = System.currentTimeMillis();
      MatrixStack ms = eventRender.getMatrixStack();
      ms.push();
      RenderSystem.enableBlend();
      RenderSystem.depthMask(false);
      RenderSystem.disableDepthTest();
      RenderSystem.disableCull();
      RenderSystem.disableAlphaTest();
      RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
      RenderSystem.shadeModel(7425);
      Wrapper.mc.getTextureManager().bindTexture(BLOOM_TEX4);
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder buffer = tessellator.getBuffer();
      ActiveRenderInfo camera = Wrapper.mc.getRenderManager().info;
      buffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);

      for(int i = 0; i < 20; ++i) {
         float trailFactor = 1.0F - (float)i / 20.0F * 0.7F;
         double angle = 0.15000000596046448 * ((double)time - (double)i * 10.0) / 25.0;
         this.drawOrbitalParticle(ms, buffer, camera, baseX, baseY, baseZ, angle, 1.0, 1.0, -1.0, (float)(0.285 * (double)trailFactor), alphaPC, baseColor);
         this.drawOrbitalParticle(ms, buffer, camera, baseX, baseY, baseZ, angle, -1.0, 1.0, -1.0, (float)(0.285 * (double)trailFactor), alphaPC, baseColor);
         this.drawOrbitalParticle(ms, buffer, camera, baseX, baseY, baseZ, angle, -1.0, -1.0, 1.0, (float)(0.285 * (double)trailFactor), alphaPC, baseColor);
      }

      tessellator.draw();
      RenderSystem.shadeModel(7424);
      RenderSystem.enableDepthTest();
      RenderSystem.enableCull();
      RenderSystem.enableAlphaTest();
      RenderSystem.depthMask(true);
      RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
      RenderSystem.disableBlend();
      ms.pop();
   }

   private void drawOrbitalParticle(MatrixStack ms, BufferBuilder buffer, ActiveRenderInfo camera, double bx, double by, double bz, double angle, double sMult, double cMult, double cZMult, float size, float alphaPC, int baseColor) {
      double s = Math.sin(angle) * 0.7;
      double c = Math.cos(angle) * 0.7;
      double finalX;
      double finalY;
      double finalZ;
      if (sMult == 1.0 && cMult == 1.0 && cZMult == -1.0) {
         finalX = s;
         finalY = c;
         finalZ = -c;
      } else if (sMult == -1.0 && cMult == 1.0 && cZMult == -1.0) {
         finalX = -s;
         finalY = s;
         finalZ = -c;
      } else {
         finalX = -s;
         finalY = -s;
         finalZ = c;
      }

      ms.push();
      ms.translate(bx + finalX, by + finalY, bz + finalZ);
      ms.rotate(camera.getRotation());
      Matrix4f mat = ms.getLast().getMatrix();
      int r = ColorUtil.red(baseColor);
      int g = ColorUtil.green(baseColor);
      int b = ColorUtil.blue(baseColor);
      float glowSize = size * 2.5F;
      buffer.pos(mat, -glowSize / 2.0F, glowSize / 2.0F, 0.0F).tex(0.0F, 1.0F).color(r, g, b, (int)(255.0F * alphaPC * 0.3F)).endVertex();
      buffer.pos(mat, glowSize / 2.0F, glowSize / 2.0F, 0.0F).tex(1.0F, 1.0F).color(r, g, b, (int)(255.0F * alphaPC * 0.3F)).endVertex();
      buffer.pos(mat, glowSize / 2.0F, -glowSize / 2.0F, 0.0F).tex(1.0F, 0.0F).color(r, g, b, (int)(255.0F * alphaPC * 0.3F)).endVertex();
      buffer.pos(mat, -glowSize / 2.0F, -glowSize / 2.0F, 0.0F).tex(0.0F, 0.0F).color(r, g, b, (int)(255.0F * alphaPC * 0.3F)).endVertex();
      int coreAlpha = (int)(255.0F * alphaPC);
      buffer.pos(mat, -size / 2.0F, size / 2.0F, 0.0F).tex(0.0F, 1.0F).color(r, g, b, coreAlpha).endVertex();
      buffer.pos(mat, size / 2.0F, size / 2.0F, 0.0F).tex(1.0F, 1.0F).color(r, g, b, coreAlpha).endVertex();
      buffer.pos(mat, size / 2.0F, -size / 2.0F, 0.0F).tex(1.0F, 0.0F).color(r, g, b, coreAlpha).endVertex();
      buffer.pos(mat, -size / 2.0F, -size / 2.0F, 0.0F).tex(0.0F, 0.0F).color(r, g, b, coreAlpha).endVertex();
      ms.pop();
   }

   private void putBloomQuad(BufferBuilder builder, MatrixStack ms, float x, float y, float size, int r, int g, int b, float a) {
      Matrix4f mat = ms.getLast().getMatrix();
      builder.pos(mat, x, y + size, 0.0F).tex(0.0F, 1.0F).color((float)r / 255.0F, (float)g / 255.0F, (float)b / 255.0F, a).endVertex();
      builder.pos(mat, x + size, y + size, 0.0F).tex(1.0F, 1.0F).color((float)r / 255.0F, (float)g / 255.0F, (float)b / 255.0F, a).endVertex();
      builder.pos(mat, x + size, y, 0.0F).tex(1.0F, 0.0F).color((float)r / 255.0F, (float)g / 255.0F, (float)b / 255.0F, a).endVertex();
      builder.pos(mat, x, y, 0.0F).tex(0.0F, 0.0F).color((float)r / 255.0F, (float)g / 255.0F, (float)b / 255.0F, a).endVertex();
   }

   private void renderFollowGhosts(EventWorld3D event) {
      if (!this.isTargetESPMode("Преследующие призраки")) {
         this.followGhosts.clear();
         this.followGhostTarget = null;
         this.followSpin = 0.0F;
      } else {
         List espTargets = this.getEspTargets();
         this.followGhostTarget = espTargets.isEmpty() ? null : (LivingEntity)espTargets.get(0);
         if (this.followGhostTarget != null && this.followGhostTarget.isAlive()) {
            if (this.followGhosts.size() < 3) {
               int color = ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL);

               for(int i = this.followGhosts.size(); i < 3; ++i) {
                  this.followGhosts.add(new GhostRenderer3D(this.followGhostTarget.getPositionVec(), Vector3d.ZERO, 0.3F, color));
               }
            }

            float fpsFactor = 500.0F / (float)Math.max(Minecraft.getDebugFPS(), 5);
            this.followSpin += 29.0F;
            RenderSystem.disableDepthTest();
            RenderSystem.disableCull();
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
            List toRemove = new ArrayList();
            int color = ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL);

            for(int i = 0; i < this.followGhosts.size(); ++i) {
               GhostRenderer3D ghost = (GhostRenderer3D)this.followGhosts.get(i);
               ghost.setColor(color);
               this.updateFollowGhostPosition(ghost, i, fpsFactor);
               if (ghost.getAlpha() < 1.0F) {
                  ghost.setAlpha(Math.max(0.0F, ghost.getAlpha() - 0.005F * fpsFactor));
               }

               if (ghost.getAlpha() <= 0.0F) {
                  toRemove.add(ghost);
                  this.followGhosts.add(new GhostRenderer3D(this.followGhostTarget.getPositionVec(), Vector3d.ZERO, 0.3F, color));
               } else {
                  ghost.render(event.matrixStack, BLOOM_TEX);
               }
            }

            this.followGhosts.removeAll(toRemove);
            RenderSystem.enableDepthTest();
            RenderSystem.enableCull();
            RenderSystem.disableBlend();
         } else {
            this.followGhosts.clear();
         }
      }
   }

   private void updateFollowGhostPosition(GhostRenderer3D ghost, int index, float fpsFactor) {
      if (this.followGhostTarget != null) {
         Vector3d base = this.followGhostTarget.getPositionVec();
         float angleOffset = (float)index * 120.0F;
         double radian = Math.toRadians((double)(this.followSpin + angleOffset));
         float hurtFactor = this.followGhostTarget.hurtTime > 7 ? 1.0F : 0.0F;
         float radius = 0.3F - hurtFactor * 0.3F;
         double x = Math.sin(radian) * (double)radius;
         double z = Math.cos(radian) * (double)radius;
         double y = 0.2 + (double)this.followGhostTarget.getHeight() / 2.0 * Math.sin(Math.toRadians((double)(this.followSpin / ((float)index + 1.0F))));
         Vector3d targetPos = base.add(x, y, z);
         Vector3d motion = targetPos.subtract(ghost.getPosition()).scale((double)(0.05F * fpsFactor));
         ghost.setMotion(motion);
      }
   }

   private PigEntity createPigForRender(LivingEntity target, Minecraft mc) {
      PigEntity pig = new PigEntity(EntityType.PIG, mc.world);
      pig.setPosition(target.getPosX(), target.getPosY(), target.getPosZ());
      pig.prevPosX = pig.getPosX();
      pig.prevPosY = pig.getPosY();
      pig.prevPosZ = pig.getPosZ();
      pig.lastTickPosX = pig.getPosX();
      pig.lastTickPosY = pig.getPosY();
      pig.lastTickPosZ = pig.getPosZ();
      pig.rotationYaw = 0.0F;
      pig.prevRotationYaw = 0.0F;
      pig.rotationPitch = 0.0F;
      pig.prevRotationPitch = 0.0F;
      pig.renderYawOffset = 0.0F;
      pig.prevRenderYawOffset = 0.0F;
      pig.rotationYawHead = 0.0F;
      pig.prevRotationYawHead = 0.0F;
      pig.limbSwing = 0.0F;
      pig.limbSwingAmount = 0.0F;
      pig.prevLimbSwingAmount = 0.0F;
      pig.hurtTime = 0;
      pig.deathTime = 0;
      pig.ticksExisted = Minecraft.player != null ? Minecraft.player.ticksExisted : 0;
      return pig;
   }

   private void renderPigs(EventWorld3D e) {
      if (this.isTargetESPMode("Свинки")) {
         List targets = this.getEspTargets();
         if (!targets.isEmpty() && Wrapper.mc.world != null) {
            TargetESP targetESP = this.getTargetESPModule();
            if (targetESP != null) {
               Iterator var4 = targets.iterator();

               while(var4.hasNext()) {
                  LivingEntity target = (LivingEntity)var4.next();
                  if (target != null && target.isAlive()) {
                     this.renderPigsAt(e, target, targetESP);
                  }
               }

            }
         }
      }
   }

   private void renderPigsAt(EventWorld3D e, LivingEntity target, TargetESP targetESP) {
      Minecraft mc = Minecraft.getInstance();
      float pt = e.getPartialTicks();
      Vector3d cameraPosition = mc.getRenderManager().info.getProjectedView();
      double targetX = target.lastTickPosX + (target.getPosX() - target.lastTickPosX) * (double)pt;
      double targetY = target.lastTickPosY + (target.getPosY() - target.lastTickPosY) * (double)pt;
      double targetZ = target.lastTickPosZ + (target.getPosZ() - target.lastTickPosZ) * (double)pt;
      double x = targetX - cameraPosition.x;
      double y = targetY - cameraPosition.y;
      double z = targetZ - cameraPosition.z;
      PigEntity pigForRender = this.createPigForRender(target, mc);
      IRenderTypeBuffer.Impl buffer = mc.getRenderTypeBuffers().getBufferSource();
      MatrixStack ms = e.getMatrixStack();
      EntityRendererManager rm = mc.getRenderManager();
      float progress = 1.0F;
      float radius = 0.7F;
      float spinStep = 2.5E-4F * (Float)targetESP.getPigSpeed().get();
      float halfHeight = target.getHeight() / 2.0F;
      float timeRing = (float)(-(System.currentTimeMillis() % 1000000L)) * spinStep;
      double[] px = new double[8];
      double[] py = new double[8];
      double[] pz = new double[8];
      float aoe = timeRing * 360.0F;

      double centerZ;
      for(int i = 0; i < 8; ++i) {
         float ta = aoe + (float)i / 8.0F * 360.0F;
         centerZ = Math.toRadians((double)ta);
         float yOff = i % 2 == 0 ? 0.1F : -0.1F;
         double oX = Math.cos(centerZ) * (double)radius;
         double oZ = Math.sin(centerZ) * (double)radius;
         px[i] = x + oX;
         py[i] = y + (double)halfHeight + (double)yOff - 0.20000000298023224;
         pz[i] = z + oZ;
      }

      double centerX = x;
      centerZ = z;
      double topY = y + (double)target.getHeight() * 0.95;
      float timeSpin = (float)(System.currentTimeMillis() % 1000000L) * (Float)targetESP.getPigSpeed().get() * 0.001F;
      float yawSpin = timeSpin * 180.0F;
      float pitchWobble = (float)(Math.sin((double)timeSpin * 1.5) * 120.0);
      float rollWobble = (float)(Math.cos((double)timeSpin * 1.2) * 90.0);
      RenderSystem.enableDepthTest();
      RenderSystem.disableCull();

      try {
         for(int i = 0; i < 9; ++i) {
            double mx;
            double my;
            double mz;
            double nx;
            double nz;
            if (i < 8) {
               mx = px[i];
               my = py[i];
               mz = pz[i];
               int next = (i + 1) % 8;
               nx = px[next];
               double var10000 = py[next];
               nz = pz[next];
            } else {
               mx = centerX;
               my = topY;
               mz = centerZ;
               nx = px[0];
               double ny = py[0];
               nz = pz[0];
            }

            ms.push();
            ms.translate(mx, my, mz);
            if (i == 8) {
               ms.rotate(Vector3f.YP.rotationDegrees(yawSpin));
               ms.rotate(Vector3f.XP.rotationDegrees(pitchWobble));
               ms.rotate(Vector3f.ZP.rotationDegrees(rollWobble));
            } else {
               double lX = nx - mx;
               double lZ = nz - mz;
               float yaw = (float)Math.toDegrees(Math.atan2(-lZ, lX)) - 95.0F;
               ms.rotate(Vector3f.YP.rotationDegrees(yaw));
            }

            float scale = i == 8 ? 0.4F * progress : 0.3F * progress;
            ms.scale(scale, scale, scale);

            try {
               rm.renderEntityStatic(pigForRender, 0.0, 0.0, 0.0, 0.0F, pt, ms, buffer, 15728880);
            } catch (Exception var63) {
            }

            ms.pop();
         }
      } finally {
         buffer.finish();
         RenderSystem.enableCull();
      }

   }

   static {
      szAnim = new AnimationUtil(1.0F, 6.0F, Easings.LINEAR);
      alpAnim = new AnimationUtil(0.0F, 6.0F, Easings.LINEAR);
      htScAnim = new AnimationUtil(1.0F, 6.0F, Easings.LINEAR);
      htClAnim = new AnimationUtil(0.0F, 6.0F, Easings.LINEAR);
      nigerAnim = new AnimationUtil(0.0F, 6.0F, Easings.CUBIC_IN_OUT);
      nigerPlavnost = 0.0F;
      BLOOM_TEX = new ResourceLocation("SkyCore/icons/world_render/bloom.png");
      BLOOM_TEX4 = new ResourceLocation("SkyCore/icons/world_render/firepart2.png");
      timeAnimation = new AnimationUtil(1.0F, 6.0F, Easings.LINEAR);
   }

   private static class GlowPoint {
      private final float x;
      private final float y;
      private final float z;
      private final long creationTime;
      private final int maxTime;
      private final int baseColor;
      private float currentTimeProgress;

      private GlowPoint(float x, float y, float z, int maxTime, int color) {
         this.x = x;
         this.y = y;
         this.z = z;
         this.creationTime = System.currentTimeMillis();
         this.maxTime = maxTime;
         this.baseColor = color;
         this.currentTimeProgress = 0.0F;
      }

      private void update() {
         this.currentTimeProgress = Math.min((float)(System.currentTimeMillis() - this.creationTime) / (float)this.maxTime, 1.0F);
      }

      private float getTimeProgress() {
         return this.currentTimeProgress;
      }

      private int getColor(float timeProgress) {
         float alphaMultiplier = (timeProgress > 0.5F ? 1.0F - timeProgress : timeProgress) * 2.0F;
         alphaMultiplier = Math.max(0.0F, Math.min(1.0F, alphaMultiplier));
         int alpha = (int)(alphaMultiplier * 255.0F);
         int rgb = this.baseColor & 16777215;
         return alpha << 24 | rgb;
      }

      private boolean shouldRemove() {
         this.update();
         return this.currentTimeProgress >= 1.0F;
      }
   }
}
