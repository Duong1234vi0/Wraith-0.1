package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import sky.core.events.EventAttack;
import sky.core.events.EventRender3D;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.ColorSetting;
import sky.core.modules.api.constructors.impl.ModeSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;

public class KillEffect extends Module {
   private static final ResourceLocation GLOW_TEXTURE = new ResourceLocation("SkyCore/icons/world_render/glow.png");
   private static final String MODE_PARTICLES = "Частицы";
   private static final String MODE_LIGHTNING = "Молния";
   private static final String MODE_GLOW = "Сияние";
   private static final String COLOR_RAINBOW = "Радуга";
   private static final String COLOR_CLIENT = "Клиент";
   private static final String COLOR_CUSTOM = "Свой";
   private static final long TRACK_TARGET_MS = 2500L;
   private static final long SAME_ENTITY_COOLDOWN_MS = 900L;
   private static final int MAX_CAPTURED_VERTICES = 24000;
   private static final int FULL_BRIGHT = 15728880;
   private static final float GLOW_DURATION = 2.8F;
   private static final float GLOW_Y_OFFSET = -0.42F;
   private static final float GLOW_DISC_RADIUS = 2.15F;
   private static final float GLOW_SLICE_STEP = 0.035F;
   private static final float GLOW_APPEAR_END = 0.3F;
   private static final float GLOW_BEAM_DISAPPEAR_START = 0.34F;
   private static final float GLOW_BEAM_DISAPPEAR_END = 0.54F;
   private static final float GLOW_DISC_FADE_START = 0.4F;
   private static int nextClientEffectEntityId = -100000;
   private final Random random = new Random();
   private final ModeSetting effectMode = new ModeSetting("Режим", "Частицы", new String[]{"Частицы", "Молния", "Сияние"});
   private final SliderSetting particles = new SliderSetting("Частицы", 280.0F, 80.0F, 650.0F, 10.0F, () -> {
      return this.effectMode.is("Частицы");
   });
   private final SliderSetting particleSize = new SliderSetting("Размер частиц", 0.115F, 0.045F, 0.22F, 0.005F, () -> {
      return this.effectMode.is("Частицы");
   });
   private final SliderSetting deathHold = new SliderSetting("Задержка", 200.0F, 0.0F, 500.0F, 10.0F, () -> {
      return this.effectMode.is("Частицы");
   });
   private final SliderSetting evaporation = new SliderSetting("Испарение", 1050.0F, 250.0F, 2400.0F, 25.0F, () -> {
      return this.effectMode.is("Частицы");
   });
   private final SliderSetting riseHeight = new SliderSetting("Подъем", 1.75F, 0.35F, 4.0F, 0.05F, () -> {
      return this.effectMode.is("Частицы");
   });
   private final SliderSetting chaos = new SliderSetting("Хаос", 0.75F, 0.0F, 1.75F, 0.05F, () -> {
      return this.effectMode.is("Частицы");
   });
   private final BooleanSetting throughWalls = new BooleanSetting("Через стены", false, () -> {
      return this.effectMode.is("Частицы");
   });
   private final ModeSetting colorMode = new ModeSetting("Цвет", "Клиент", () -> {
      return this.effectMode.is("Частицы");
   }, new String[]{"Радуга", "Клиент", "Свой"});
   private final BooleanSetting secondColor = new BooleanSetting("Второй цвет", false, () -> {
      return this.effectMode.is("Частицы") && this.colorMode.is("Свой");
   });
   private final ColorSetting color = new ColorSetting("Цвет 1", true, (new Color(255, 255, 255, 255)).getRGB(), () -> {
      return this.effectMode.is("Частицы") && this.colorMode.is("Свой");
   });
   private final ColorSetting color2 = new ColorSetting("Цвет 2", true, (new Color(70, 70, 70, 170)).getRGB(), () -> {
      return this.effectMode.is("Частицы") && this.colorMode.is("Свой") && (Boolean)this.secondColor.get();
   });
   private final SliderSetting glowWidth = new SliderSetting("Ширина глоу", 0.13F, 0.05F, 0.35F, 0.01F, () -> {
      return this.effectMode.is("Сияние");
   });
   private final SliderSetting glowHeight = new SliderSetting("Высота глоу", 4.0F, 1.5F, 8.0F, 0.25F, () -> {
      return this.effectMode.is("Сияние");
   });
   private final CopyOnWriteArrayList activeParticles = new CopyOnWriteArrayList();
   private final List recentKills = new ArrayList();
   private final List glowEffects = new ArrayList();
   private int trackedEntityId = Integer.MIN_VALUE;
   private long trackedAtMs;
   private Vector3d lastTrackedPos;
   private float lastTrackedWidth;
   private float lastTrackedHeight;
   private float lastTrackedYaw;
   private long lastStaticEffectMs;

   public KillEffect() {
      super("KillEffect", "Визуальный эффект при убийстве цели", Category.Visuals);
      this.addSettings(new Setting[]{this.effectMode, this.particles, this.particleSize, this.deathHold, this.evaporation, this.riseHeight, this.chaos, this.throughWalls, this.colorMode, this.secondColor, this.color, this.color2, this.glowWidth, this.glowHeight});
   }

   @EventTarget
   private void onAttack(EventAttack event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         Entity entity = event.getTarget();
         if (entity instanceof LivingEntity) {
            Minecraft var10001 = mc;
            if (entity != Minecraft.player) {
               this.trackedEntityId = entity.getEntityId();
               this.trackedAtMs = System.currentTimeMillis();
               this.updateLastTracked(entity);
               return;
            }
         }

      }
   }

   @EventTarget
   private void onUpdate(EventUpdate event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         if (this.trackedEntityId == Integer.MIN_VALUE) {
            this.cleanupRecentKills(System.currentTimeMillis());
         } else {
            long nowMs = System.currentTimeMillis();
            if (nowMs - this.trackedAtMs > 2500L) {
               this.resetTrackedEntity();
            } else {
               Entity tracked = mc.world.getEntityByID(this.trackedEntityId);
               if (tracked == null) {
                  this.spawnFromLastKnownPosition(nowMs);
                  this.resetTrackedEntity();
               } else {
                  if (tracked instanceof LivingEntity) {
                     Minecraft var10001 = mc;
                     if (tracked != Minecraft.player) {
                        LivingEntity living = (LivingEntity)tracked;
                        if (this.isDeadOrRemoved(living)) {
                           if (!this.isRecentlySpawned(tracked.getEntityId(), nowMs)) {
                              this.recentKills.add(new RecentKill(tracked.getEntityId(), nowMs));
                              this.spawnFromEntity(living, nowMs);
                           }

                           this.resetTrackedEntity();
                           return;
                        }

                        this.updateLastTracked(tracked);
                        return;
                     }
                  }

                  this.resetTrackedEntity();
               }
            }
         }
      } else {
         this.clearState();
      }
   }

   @EventTarget
   private void onRender3D(EventRender3D event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         this.renderParticles();
         this.renderGlowEffects();
      }
   }

   private void updateLastTracked(Entity entity) {
      this.lastTrackedPos = entity.getPositionVec();
      this.lastTrackedWidth = entity.getWidth();
      this.lastTrackedHeight = entity.getHeight();
      this.lastTrackedYaw = entity.rotationYaw;
   }

   private void spawnFromLastKnownPosition(long nowMs) {
      if (this.lastTrackedPos != null && !this.isRecentlySpawned(this.trackedEntityId, nowMs)) {
         this.recentKills.add(new RecentKill(this.trackedEntityId, nowMs));
         if (this.effectMode.is("Частицы")) {
            boolean playerLike = this.lastTrackedWidth <= 0.7F && this.lastTrackedHeight > 1.4F;
            ParticleModel model = this.makeFallbackModel(this.lastTrackedPos, this.lastTrackedWidth, this.lastTrackedHeight, this.lastTrackedYaw, playerLike);
            this.spawnParticles(model, nowMs);
         } else {
            this.spawnStaticEffect(this.lastTrackedPos);
         }

      }
   }

   private void spawnFromEntity(LivingEntity entity, long nowMs) {
      if (this.effectMode.is("Частицы")) {
         ParticleModel model = this.captureModel(entity, mc.getRenderPartialTicks());
         if (model.isEmpty()) {
            model = this.makeFallbackModel(entity.getPositionVec(), entity.getWidth(), entity.getHeight(), entity.rotationYaw, entity instanceof PlayerEntity);
         }

         this.spawnParticles(model, nowMs);
      } else {
         this.spawnStaticEffect(entity.getPositionVec());
      }
   }

   private ParticleModel captureModel(LivingEntity entity, float partialTicks) {
      List capturedVertices = KillEffect.EntityModelCapture.capture(entity, partialTicks, 24000);
      return KillEffect.ParticleModel.fromVertices(capturedVertices);
   }

   private void spawnParticles(ParticleModel model, long nowMs) {
      if (!model.isEmpty()) {
         int count = Math.max(1, Math.round((Float)this.particles.get()));
         float chaosValue = (Float)this.chaos.get();

         for(int i = 0; i < count; ++i) {
            Vector3d worldPoint = model.randomPoint(this.random);
            Vector3d away = worldPoint.subtract(model.center);
            if (away.length() < 1.0E-5) {
               away = new Vector3d(this.random.nextDouble() - 0.5, this.random.nextDouble() * 0.4, this.random.nextDouble() - 0.5);
            }

            Vector3d drift = away.normalize().scale((0.32 + this.random.nextDouble() * 0.58) * (double)chaosValue).add((this.random.nextDouble() - 0.5) * 0.34 * (double)chaosValue, this.random.nextDouble() * 0.38 * (double)chaosValue, (this.random.nextDouble() - 0.5) * 0.34 * (double)chaosValue);
            this.activeParticles.add(new KillParticle(worldPoint, drift, nowMs, this.random.nextInt(1440) * (this.random.nextBoolean() ? 1 : -1), this.random.nextFloat() * 360.0F, (this.random.nextFloat() - 0.5F) * 210.0F, this.random.nextFloat() * 3.1415927F * 2.0F, 0.72F + this.random.nextFloat() * 0.65F));
         }

      }
   }

   private void renderParticles() {
      if (!this.activeParticles.isEmpty()) {
         long nowMs = System.currentTimeMillis();
         long holdMs = Math.max(0L, (long)Math.round((Float)this.deathHold.get()));
         long fadeMs = Math.max(1L, (long)Math.round((Float)this.evaporation.get()));
         long maxLifeMs = holdMs + fadeMs;
         this.activeParticles.removeIf((particlex) -> {
            return particlex.isDead(nowMs, maxLifeMs);
         });
         if (!this.activeParticles.isEmpty()) {
            ActiveRenderInfo camera = mc.getRenderManager().info;
            if (camera != null) {
               Vector3d cameraPos = camera.getProjectedView();
               RenderSystem.enableBlend();
               RenderSystem.blendFunc(770, 1);
               RenderSystem.disableCull();
               RenderSystem.depthMask(false);
               RenderSystem.disableAlphaTest();
               if ((Boolean)this.throughWalls.get()) {
                  RenderSystem.disableDepthTest();
               } else {
                  RenderSystem.enableDepthTest();
               }

               RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
               mc.getTextureManager().bindTexture(GLOW_TEXTURE);
               Tessellator tessellator = Tessellator.getInstance();
               BufferBuilder buffer = tessellator.getBuffer();
               buffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
               Iterator var13 = this.activeParticles.iterator();

               while(var13.hasNext()) {
                  KillParticle particle = (KillParticle)var13.next();
                  float alpha = particle.getAlpha(nowMs, holdMs, fadeMs);
                  if (!(alpha <= 0.003921569F)) {
                     Vector3d pos = particle.getPosition(nowMs, holdMs, fadeMs, (Float)this.chaos.get(), (Float)this.riseHeight.get());
                     float size = particle.getSize(nowMs, holdMs, fadeMs, (Float)this.particleSize.get());
                     int argb = ColorUtil.setAlpha(this.getParticleColor(particle.getColorSeed()), Math.round(alpha * 255.0F));
                     this.drawBillboard(buffer, pos.x - cameraPos.x, pos.y - cameraPos.y, pos.z - cameraPos.z, size * 0.5F, particle.getRotation(nowMs), camera, argb);
                  }
               }

               tessellator.draw();
               RenderSystem.enableAlphaTest();
               RenderSystem.depthMask(true);
               RenderSystem.enableDepthTest();
               RenderSystem.enableCull();
               RenderSystem.defaultBlendFunc();
               RenderSystem.disableBlend();
               RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            }
         }
      }
   }

   private int getParticleColor(int seed) {
      if (this.colorMode.is("Радуга")) {
         float hue = (float)Math.floorMod(System.currentTimeMillis() / 12L + (long)seed, 360L) / 360.0F;
         return Color.HSBtoRGB(hue, 1.0F, 1.0F) | -16777216;
      } else {
         int firstColor;
         int secondColorValue;
         if (this.colorMode.is("Клиент")) {
            firstColor = ColorUtil.setAlpha(ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL), 255);
            secondColorValue = firstColor;
         } else {
            firstColor = (Integer)this.color.get();
            secondColorValue = (Boolean)this.secondColor.get() ? (Integer)this.color2.get() : (Integer)this.color.get();
         }

         int angle = (int)Math.floorMod(System.currentTimeMillis() / 8L + (long)seed, 360L);
         float progress = angle > 180 ? (float)(360 - angle) / 180.0F : (float)angle / 180.0F;
         return ColorUtil.interpolate(firstColor, secondColorValue, progress);
      }
   }

   private void drawBillboard(BufferBuilder buffer, double x, double y, double z, float halfSize, float angleDeg, ActiveRenderInfo camera, int argb) {
      MatrixStack matrices = new MatrixStack();
      matrices.push();
      matrices.translate(x, y, z);
      matrices.rotate(camera.getRotation().copy());
      if (angleDeg != 0.0F) {
         matrices.rotate(Vector3f.ZP.rotationDegrees(angleDeg));
      }

      Matrix4f matrix = matrices.getLast().getMatrix();
      int a = argb >>> 24 & 255;
      int r = argb >>> 16 & 255;
      int g = argb >>> 8 & 255;
      int b = argb & 255;
      buffer.pos(matrix, -halfSize, -halfSize, 0.0F).tex(1.0F, 1.0F).color(r, g, b, a).endVertex();
      buffer.pos(matrix, -halfSize, halfSize, 0.0F).tex(1.0F, 0.0F).color(r, g, b, a).endVertex();
      buffer.pos(matrix, halfSize, halfSize, 0.0F).tex(0.0F, 0.0F).color(r, g, b, a).endVertex();
      buffer.pos(matrix, halfSize, -halfSize, 0.0F).tex(0.0F, 1.0F).color(r, g, b, a).endVertex();
      matrices.pop();
   }

   private ParticleModel makeFallbackModel(Vector3d origin, float width, float height, float yaw, boolean playerLike) {
      if (width > 0.0F && height > 0.0F) {
         ArrayList vertices = new ArrayList();
         if (playerLike) {
            this.addBox(vertices, origin, yaw, -0.25, 1.22, -0.25, 0.25, 1.72, 0.25);
            this.addBox(vertices, origin, yaw, -0.25, 0.72, -0.125, 0.25, 1.22, 0.125);
            this.addBox(vertices, origin, yaw, -0.43, 0.72, -0.105, -0.25, 1.22, 0.105);
            this.addBox(vertices, origin, yaw, 0.25, 0.72, -0.105, 0.43, 1.22, 0.105);
            this.addBox(vertices, origin, yaw, -0.24, 0.0, -0.105, -0.02, 0.72, 0.105);
            this.addBox(vertices, origin, yaw, 0.02, 0.0, -0.105, 0.24, 0.72, 0.105);
         } else {
            double half = Math.max(0.05, (double)width * 0.5);
            double entityHeight = Math.max(0.1, (double)height);
            this.addBox(vertices, origin, yaw, -half, 0.0, -half, half, entityHeight, half);
         }

         return KillEffect.ParticleModel.fromVertices(vertices);
      } else {
         return KillEffect.ParticleModel.empty();
      }
   }

   private void addBox(List vertices, Vector3d origin, float yaw, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
      Vector3d nnn = this.transform(origin, yaw, minX, minY, minZ);
      Vector3d pnn = this.transform(origin, yaw, maxX, minY, minZ);
      Vector3d ppn = this.transform(origin, yaw, maxX, maxY, minZ);
      Vector3d npn = this.transform(origin, yaw, minX, maxY, minZ);
      Vector3d nnp = this.transform(origin, yaw, minX, minY, maxZ);
      Vector3d pnp = this.transform(origin, yaw, maxX, minY, maxZ);
      Vector3d ppp = this.transform(origin, yaw, maxX, maxY, maxZ);
      Vector3d npp = this.transform(origin, yaw, minX, maxY, maxZ);
      this.addQuad(vertices, nnn, pnn, ppn, npn);
      this.addQuad(vertices, pnp, nnp, npp, ppp);
      this.addQuad(vertices, nnp, nnn, npn, npp);
      this.addQuad(vertices, pnn, pnp, ppp, ppn);
      this.addQuad(vertices, npn, ppn, ppp, npp);
      this.addQuad(vertices, nnp, pnp, pnn, nnn);
   }

   private void addQuad(List vertices, Vector3d a, Vector3d b, Vector3d c, Vector3d d) {
      vertices.add(a);
      vertices.add(b);
      vertices.add(c);
      vertices.add(d);
   }

   private Vector3d transform(Vector3d origin, float yaw, double x, double y, double z) {
      double radians = Math.toRadians((double)(-yaw));
      double sin = Math.sin(radians);
      double cos = Math.cos(radians);
      double rotatedX = x * cos - z * sin;
      double rotatedZ = x * sin + z * cos;
      return origin.add(rotatedX, y, rotatedZ);
   }

   private void spawnStaticEffect(Vector3d pos) {
      if (pos != null && Double.isFinite(pos.x) && Double.isFinite(pos.y) && Double.isFinite(pos.z)) {
         long now = System.currentTimeMillis();
         if (now - this.lastStaticEffectMs >= 250L) {
            this.lastStaticEffectMs = now;
            if (this.effectMode.is("Молния")) {
               this.spawnLightning(pos.x, pos.y, pos.z);
            } else if (this.effectMode.is("Сияние")) {
               this.glowEffects.add(new GlowEffect(pos, now));
            }

         }
      }
   }

   private void renderGlowEffects() {
      if (!this.glowEffects.isEmpty()) {
         ActiveRenderInfo camera = mc.getRenderManager().info;
         if (camera != null) {
            long currentTime = System.currentTimeMillis();
            Vector3d cameraPos = camera.getProjectedView();
            int themeColor = ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL);
            float themeAlpha = ThemeEditor.getAlpha(ThemeSettings.MODULE_VISUAL);
            RenderSystem.enableBlend();
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.disableCull();
            RenderSystem.disableAlphaTest();
            RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ZERO, DestFactor.ONE);
            mc.getTextureManager().bindTexture(GLOW_TEXTURE);
            Iterator effectIterator = this.glowEffects.iterator();

            while(effectIterator.hasNext()) {
               GlowEffect effect = (GlowEffect)effectIterator.next();
               float progress = (float)(currentTime - effect.startTime) / 2800.0F;
               if (progress >= 1.0F) {
                  effectIterator.remove();
               } else {
                  this.renderGlowPillar(camera, cameraPos, effect.position, progress, themeColor, themeAlpha);
               }
            }

            RenderSystem.enableCull();
            RenderSystem.enableAlphaTest();
            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableBlend();
         }
      }
   }

   private void renderGlowPillar(ActiveRenderInfo camera, Vector3d cameraPos, Vector3d basePos, float progress, int themeColor, float themeAlpha) {
      Vector3d effectPos = basePos.add(0.0, -0.41999998688697815, 0.0);
      float appear = this.getAppearProgress(progress);
      float beamDisappear = this.getBeamDisappear(progress);
      float beamFactor = appear * (1.0F - beamDisappear);
      float discFade = this.getDiscGlowFade(progress);
      this.drawFlatGlowDisc(cameraPos, effectPos, 2.15F, themeColor, themeAlpha, appear * discFade);
      if (!(beamFactor <= 0.005F)) {
         float glowH = (Float)this.glowHeight.get();
         float beamHeight = glowH * beamFactor;
         float maxHeight = glowH * appear;
         float baseRadius = (Float)this.glowWidth.get();
         float softEdge = 0.01925F;
         float tipZone = 0.14F;

         for(float y = 0.0F; y <= maxHeight; y += 0.035F) {
            float over = y - beamHeight;
            if (!(over > softEdge)) {
               float edgeFade = over > 0.0F ? (float)Math.pow((double)(1.0F - MathHelper.clamp(over / softEdge, 0.0F, 1.0F)), 2.200000047683716) : 1.0F;
               float heightT = beamHeight > 0.01F ? y / beamHeight : 0.0F;
               float tipFade = 1.0F;
               float tipRadius = 1.0F;
               float sliceAlpha;
               if (heightT > 1.0F - tipZone) {
                  sliceAlpha = (heightT - (1.0F - tipZone)) / tipZone;
                  tipFade = (float)Math.pow((double)(1.0F - sliceAlpha), 2.5999999046325684);
                  tipRadius = 0.28F + 0.72F * tipFade;
               }

               sliceAlpha = appear * (1.0F - beamDisappear) * edgeFade * tipFade;
               if (!(sliceAlpha <= 0.005F)) {
                  float radius = baseRadius * tipRadius;
                  Vector3d pos = effectPos.add(0.0, (double)y, 0.0);
                  this.drawGlowBillboard(camera, cameraPos, pos, radius * 1.95F, themeColor, themeAlpha, sliceAlpha * 0.2F);
                  this.drawGlowBillboard(camera, cameraPos, pos, radius * 1.05F, themeColor, themeAlpha, sliceAlpha * 0.44F);
                  this.drawGlowBillboard(camera, cameraPos, pos, radius * 0.5F, themeColor, themeAlpha, sliceAlpha * 0.8F);
               }
            }
         }

      }
   }

   private float easeSmooth(float t) {
      t = MathHelper.clamp(t, 0.0F, 1.0F);
      float inv = 1.0F - t;
      return 1.0F - inv * inv * inv;
   }

   private float smootherstep(float t) {
      t = MathHelper.clamp(t, 0.0F, 1.0F);
      return t * t * t * (t * (t * 6.0F - 15.0F) + 10.0F);
   }

   private float getDiscGlowFade(float progress) {
      if (progress < 0.4F) {
         return 1.0F;
      } else {
         float gone = MathHelper.clamp((progress - 0.4F) / 0.6F, 0.0F, 1.0F);
         float remain = 1.0F - gone;
         return remain * remain * remain * remain * remain;
      }
   }

   private float getAppearProgress(float progress) {
      return progress >= 0.3F ? 1.0F : this.easeSmooth(progress / 0.3F);
   }

   private float getBeamDisappear(float progress) {
      if (progress <= 0.34F) {
         return 0.0F;
      } else if (progress >= 0.54F) {
         return 1.0F;
      } else {
         float t = (progress - 0.34F) / 0.20000002F;
         return this.smootherstep(t);
      }
   }

   private void drawFlatGlowDisc(Vector3d cameraPos, Vector3d basePos, float radius, int themeColor, float themeAlpha, float alpha) {
      if (!(alpha <= 0.003F)) {
         MatrixStack stack = new MatrixStack();
         stack.translate(basePos.x - cameraPos.x, basePos.y - cameraPos.y + 0.03, basePos.z - cameraPos.z);
         stack.rotate(Vector3f.XP.rotationDegrees(90.0F));
         float baseAlpha = themeAlpha / 255.0F * alpha;
         int color = ColorUtil.applyOpacity(themeColor, baseAlpha);
         RenderSystem.pushMatrix();
         RenderSystem.multMatrix(stack.getLast().getMatrix());
         float size = radius * 2.0F;
         float outerMul = 0.28F * alpha;
         float midMul = 0.55F * alpha;
         RenderUtil.drawImage3D(GLOW_TEXTURE, -radius * 1.35F, -radius * 1.35F, 0.0F, size * 1.35F, size * 1.35F, ColorUtil.applyOpacity(themeColor, baseAlpha * outerMul), true);
         RenderUtil.drawImage3D(GLOW_TEXTURE, -radius, -radius, 0.0F, size, size, ColorUtil.applyOpacity(themeColor, baseAlpha * midMul), true);
         RenderUtil.drawImage3D(GLOW_TEXTURE, -radius * 0.55F, -radius * 0.55F, 0.0F, size * 0.55F, size * 0.55F, color, true);
         RenderSystem.popMatrix();
      }
   }

   private void drawGlowBillboard(ActiveRenderInfo camera, Vector3d cameraPos, Vector3d worldPos, float size, int themeColor, float themeAlpha, float alpha) {
      if (!(alpha <= 0.01F)) {
         MatrixStack stack = new MatrixStack();
         stack.translate(worldPos.x - cameraPos.x, worldPos.y - cameraPos.y, worldPos.z - cameraPos.z);
         stack.rotate(camera.getRotation().copy());
         int color = ColorUtil.applyOpacity(themeColor, themeAlpha / 255.0F * alpha);
         RenderSystem.pushMatrix();
         RenderSystem.multMatrix(stack.getLast().getMatrix());
         RenderUtil.drawImage3D(GLOW_TEXTURE, -size * 0.5F, -size * 0.5F, 0.0F, size, size, color, true);
         RenderSystem.popMatrix();
      }
   }

   private void spawnLightning(double x, double y, double z) {
      if (mc.world != null) {
         ClientWorld spawnWorld = mc.world instanceof ClientWorld ? mc.world : null;
         mc.execute(() -> {
            if (mc.world != null && (spawnWorld == null || mc.world == spawnWorld)) {
               LightningBoltEntity bolt = (LightningBoltEntity)EntityType.LIGHTNING_BOLT.create(mc.world);
               if (bolt != null) {
                  bolt.setEffectOnly(false);
                  bolt.setPosition(x, y, z);

                  try {
                     if (mc.world instanceof ClientWorld) {
                        int id = this.allocateUniqueClientEntityId(mc.world);
                        mc.world.addEntity(id, bolt);
                     } else {
                        mc.world.addEntity(bolt);
                     }
                  } catch (Throwable var10) {
                  }

               }
            }
         });
         mc.getSoundHandler().play(SimpleSound.master(SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, 0.8F + mc.world.rand.nextFloat() * 0.2F, 0.5F));
         mc.getSoundHandler().play(SimpleSound.master(SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, 0.8F + mc.world.rand.nextFloat() * 0.2F, 0.5F));
      }
   }

   private int allocateUniqueClientEntityId(ClientWorld world) {
      int attempts = 0;
      int candidate = nextClientEffectEntityId;

      while(attempts++ < 100000) {
         if (world.getEntityByID(candidate) == null) {
            nextClientEffectEntityId = candidate - 1;
            if (nextClientEffectEntityId >= -1) {
               nextClientEffectEntityId = -100000;
            }

            return candidate;
         }

         --candidate;
         if (candidate >= -1) {
            candidate = -100000;
         }
      }

      return -100000 - (int)(System.nanoTime() & 32767L);
   }

   private boolean isDeadOrRemoved(LivingEntity entity) {
      return entity == null || !entity.isAlive() || entity.removed || entity.getHealth() <= 0.0F || entity.deathTime > 0;
   }

   private boolean isRecentlySpawned(int entityId, long nowMs) {
      Iterator iterator = this.recentKills.iterator();

      while(iterator.hasNext()) {
         RecentKill stamp = (RecentKill)iterator.next();
         if (nowMs - stamp.timeMs > 900L) {
            iterator.remove();
         } else if (stamp.entityId == entityId) {
            return true;
         }
      }

      return false;
   }

   private void cleanupRecentKills(long nowMs) {
      this.recentKills.removeIf((stamp) -> {
         return nowMs - stamp.timeMs > 900L;
      });
   }

   private float smoothStep(float value) {
      float t = MathHelper.clamp(value, 0.0F, 1.0F);
      return t * t * (3.0F - 2.0F * t);
   }

   private float easeOutCubic(float value) {
      float inv = 1.0F - MathHelper.clamp(value, 0.0F, 1.0F);
      return 1.0F - inv * inv * inv;
   }

   private void resetTrackedEntity() {
      this.trackedEntityId = Integer.MIN_VALUE;
      this.trackedAtMs = 0L;
      this.lastTrackedPos = null;
      this.lastTrackedWidth = 0.0F;
      this.lastTrackedHeight = 0.0F;
      this.lastTrackedYaw = 0.0F;
   }

   private void clearState() {
      this.activeParticles.clear();
      this.recentKills.clear();
      this.glowEffects.clear();
      this.resetTrackedEntity();
   }

   public void onDisable() {
      this.clearState();
      super.onDisable();
   }

   private static double triangleArea(Vector3d a, Vector3d b, Vector3d c) {
      Vector3d ab = b.subtract(a);
      Vector3d ac = c.subtract(a);
      double crossX = ab.y * ac.z - ab.z * ac.y;
      double crossY = ab.z * ac.x - ab.x * ac.z;
      double crossZ = ab.x * ac.y - ab.y * ac.x;
      return Math.sqrt(crossX * crossX + crossY * crossY + crossZ * crossZ) * 0.5;
   }

   private static Vector3d randomPointInTriangle(Vector3d a, Vector3d b, Vector3d c, Random random) {
      double u = random.nextDouble();
      double v = random.nextDouble();
      if (u + v > 1.0) {
         u = 1.0 - u;
         v = 1.0 - v;
      }

      return a.add(b.subtract(a).scale(u)).add(c.subtract(a).scale(v));
   }

   private static boolean isSaneCoordinate(double value) {
      return Double.isFinite(value) && Math.abs(value) < 3.0E7;
   }

   private static double lerp(double partialTicks, double previous, double current) {
      return previous + (current - previous) * partialTicks;
   }

   private static float lerpAngle(float partialTicks, float previous, float current) {
      return previous + partialTicks * MathHelper.wrapDegrees(current - previous);
   }

   private static final class RecentKill {
      private final int entityId;
      private final long timeMs;

      private RecentKill(int entityId, long timeMs) {
         this.entityId = entityId;
         this.timeMs = timeMs;
      }
   }

   private static final class ParticleModel {
      private final List vertices;
      private final List surfaces;
      private final double totalArea;
      private final Vector3d center;

      private ParticleModel(List vertices, List surfaces, double totalArea, Vector3d center) {
         this.vertices = vertices;
         this.surfaces = surfaces;
         this.totalArea = totalArea;
         this.center = center;
      }

      private static ParticleModel empty() {
         return new ParticleModel(new ArrayList(), new ArrayList(), 0.0, Vector3d.ZERO);
      }

      private static ParticleModel fromVertices(List capturedVertices) {
         if (capturedVertices != null && capturedVertices.size() >= 8) {
            ArrayList vertices = new ArrayList(capturedVertices.size());
            Iterator var2 = capturedVertices.iterator();

            Vector3d max;
            while(var2.hasNext()) {
               max = (Vector3d)var2.next();
               if (max != null && KillEffect.isSaneCoordinate(max.x) && KillEffect.isSaneCoordinate(max.y) && KillEffect.isSaneCoordinate(max.z)) {
                  vertices.add(max);
               }
            }

            if (vertices.size() < 8) {
               return empty();
            } else {
               Vector3d min = (Vector3d)vertices.get(0);
               max = (Vector3d)vertices.get(0);

               Vector3d vertex;
               for(Iterator var4 = vertices.iterator(); var4.hasNext(); max = new Vector3d(Math.max(max.x, vertex.x), Math.max(max.y, vertex.y), Math.max(max.z, vertex.z))) {
                  vertex = (Vector3d)var4.next();
                  min = new Vector3d(Math.min(min.x, vertex.x), Math.min(min.y, vertex.y), Math.min(min.z, vertex.z));
               }

               Vector3d center = new Vector3d((min.x + max.x) * 0.5, (min.y + max.y) * 0.5, (min.z + max.z) * 0.5);
               ArrayList surfaces = new ArrayList();
               double totalArea = 0.0;

               for(int i = 0; i + 3 < vertices.size(); i += 4) {
                  Surface surface = new Surface((Vector3d)vertices.get(i), (Vector3d)vertices.get(i + 1), (Vector3d)vertices.get(i + 2), (Vector3d)vertices.get(i + 3));
                  if (!(surface.area <= 1.0E-7)) {
                     surfaces.add(surface);
                     totalArea += surface.area;
                  }
               }

               return new ParticleModel(vertices, surfaces, totalArea, center);
            }
         } else {
            return empty();
         }
      }

      private boolean isEmpty() {
         return this.vertices.isEmpty();
      }

      private Vector3d randomPoint(Random random) {
         if (!this.surfaces.isEmpty() && this.totalArea > 1.0E-7) {
            double cursor = random.nextDouble() * this.totalArea;
            Iterator var4 = this.surfaces.iterator();

            Surface surface;
            do {
               if (!var4.hasNext()) {
                  return ((Surface)this.surfaces.get(this.surfaces.size() - 1)).randomPoint(random);
               }

               surface = (Surface)var4.next();
               cursor -= surface.area;
            } while(!(cursor <= 0.0));

            return surface.randomPoint(random);
         } else {
            Vector3d vertex = (Vector3d)this.vertices.get(random.nextInt(this.vertices.size()));
            return vertex.add((random.nextDouble() - 0.5) * 0.025, (random.nextDouble() - 0.5) * 0.025, (random.nextDouble() - 0.5) * 0.025);
         }
      }
   }

   private static final class EntityModelCapture {
      private static List capture(LivingEntity entity, float partialTicks, int maxVertices) {
         ArrayList empty = new ArrayList();
         if (entity != null && maxVertices > 0) {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft != null && minecraft.getRenderManager() != null) {
               try {
                  EntityRenderer renderer = minecraft.getRenderManager().getRenderer(entity);
                  if (renderer == null) {
                     return empty;
                  } else {
                     CapturingRenderTypeBuffer buffer = new CapturingRenderTypeBuffer(maxVertices);
                     MatrixStack matrices = new MatrixStack();
                     double x = KillEffect.lerp((double)partialTicks, entity.lastTickPosX, entity.getPosX());
                     double y = KillEffect.lerp((double)partialTicks, entity.lastTickPosY, entity.getPosY());
                     double z = KillEffect.lerp((double)partialTicks, entity.lastTickPosZ, entity.getPosZ());
                     float yaw = KillEffect.lerpAngle(partialTicks, entity.prevRotationYaw, entity.rotationYaw);
                     boolean oldRenderName = renderer.isRenderName();

                     try {
                        renderer.setRenderName(false);
                        matrices.push();
                        matrices.translate(x, y, z);
                        renderer.render(entity, yaw, partialTicks, matrices, buffer, 15728880);
                        matrices.pop();
                     } finally {
                        renderer.setRenderName(oldRenderName);
                     }

                     return buffer.getVertices();
                  }
               } catch (Throwable var20) {
                  return empty;
               }
            } else {
               return empty;
            }
         } else {
            return empty;
         }
      }
   }

   private final class KillParticle {
      private final Vector3d anchor;
      private final Vector3d drift;
      private final long bornMs;
      private final int colorSeed;
      private final float startAngle;
      private final float spin;
      private final float phase;
      private final float sizeMultiplier;

      private KillParticle(Vector3d anchor, Vector3d drift, long bornMs, int colorSeed, float startAngle, float spin, float phase, float sizeMultiplier) {
         this.anchor = anchor;
         this.drift = drift;
         this.bornMs = bornMs;
         this.colorSeed = colorSeed;
         this.startAngle = startAngle;
         this.spin = spin;
         this.phase = phase;
         this.sizeMultiplier = sizeMultiplier;
      }

      private boolean isDead(long nowMs, long maxLifeMs) {
         return nowMs - this.bornMs >= maxLifeMs;
      }

      private Vector3d getPosition(long nowMs, long holdMs, long fadeMs, float chaosValue, float riseValue) {
         float evaporationProgress = this.getEvaporation(nowMs, holdMs, fadeMs);
         if (evaporationProgress <= 0.0F) {
            return this.anchor;
         } else {
            float eased = KillEffect.this.easeOutCubic(evaporationProgress);
            double wave = Math.sin((double)(nowMs - this.bornMs) * 0.012 + (double)this.phase) * 0.075 * (double)chaosValue * (double)evaporationProgress;
            double sideWave = Math.cos((double)(nowMs - this.bornMs) * 0.009 + (double)this.phase * 1.37) * 0.055 * (double)chaosValue * (double)evaporationProgress;
            double rise = (double)(riseValue * eased) * (0.75 + (double)this.sizeMultiplier * 0.35);
            return this.anchor.add(this.drift.scale((double)eased)).add(wave, rise, sideWave);
         }
      }

      private float getAlpha(long nowMs, long holdMs, long fadeMs) {
         long ageMs = nowMs - this.bornMs;
         float fadeIn = MathHelper.clamp((float)ageMs / 80.0F, 0.0F, 1.0F);
         float evaporationProgress = this.getEvaporation(nowMs, holdMs, fadeMs);
         return evaporationProgress <= 0.0F ? fadeIn : fadeIn * (1.0F - KillEffect.this.smoothStep(evaporationProgress));
      }

      private float getSize(long nowMs, long holdMs, long fadeMs, float baseSize) {
         float evaporationProgress = this.getEvaporation(nowMs, holdMs, fadeMs);
         float pulse = 1.0F + MathHelper.sin((float)(nowMs - this.bornMs) * 0.018F + this.phase) * 0.08F;
         float dissolveScale = 1.0F - KillEffect.this.smoothStep(evaporationProgress) * 0.45F;
         return baseSize * this.sizeMultiplier * pulse * dissolveScale;
      }

      private float getRotation(long nowMs) {
         return this.startAngle + (float)(nowMs - this.bornMs) / 1000.0F * this.spin;
      }

      private float getEvaporation(long nowMs, long holdMs, long fadeMs) {
         return MathHelper.clamp((float)(nowMs - this.bornMs - holdMs) / (float)fadeMs, 0.0F, 1.0F);
      }

      private int getColorSeed() {
         return this.colorSeed;
      }
   }

   private static final class GlowEffect {
      private final Vector3d position;
      private final long startTime;

      private GlowEffect(Vector3d position, long startTime) {
         this.position = position;
         this.startTime = startTime;
      }
   }

   private static final class CapturingVertexBuilder implements IVertexBuilder {
      private final List vertices = new ArrayList();
      private final int maxVertices;
      private double x;
      private double y;
      private double z;

      private CapturingVertexBuilder(int maxVertices) {
         this.maxVertices = maxVertices;
      }

      public IVertexBuilder pos(double x, double y, double z) {
         this.x = x;
         this.y = y;
         this.z = z;
         return this;
      }

      public IVertexBuilder pos(Matrix4f matrix, float x, float y, float z) {
         return this.pos((double)matrix.getTransformX(x, y, z, 1.0F), (double)matrix.getTransformY(x, y, z, 1.0F), (double)matrix.getTransformZ(x, y, z, 1.0F));
      }

      public IVertexBuilder color(int red, int green, int blue, int alpha) {
         return this;
      }

      public IVertexBuilder tex(float u, float v) {
         return this;
      }

      public IVertexBuilder overlay(int u, int v) {
         return this;
      }

      public IVertexBuilder lightmap(int u, int v) {
         return this;
      }

      public IVertexBuilder normal(float x, float y, float z) {
         return this;
      }

      public IVertexBuilder normal(Matrix3f matrix, float x, float y, float z) {
         return this.normal(x, y, z);
      }

      public void endVertex() {
         this.addVertex(this.x, this.y, this.z);
      }

      private void addVertex(double x, double y, double z) {
         if (this.vertices.size() < this.maxVertices && KillEffect.isSaneCoordinate(x) && KillEffect.isSaneCoordinate(y) && KillEffect.isSaneCoordinate(z)) {
            this.vertices.add(new Vector3d(x, y, z));
         }
      }

      private List getVertices() {
         return this.vertices;
      }
   }

   private static final class CapturingRenderTypeBuffer implements IRenderTypeBuffer {
      private final CapturingVertexBuilder vertexBuilder;

      private CapturingRenderTypeBuffer(int maxVertices) {
         this.vertexBuilder = new CapturingVertexBuilder(maxVertices);
      }

      public IVertexBuilder getBuffer(RenderType renderType) {
         return this.vertexBuilder;
      }

      private List getVertices() {
         return this.vertexBuilder.getVertices();
      }
   }

   private static final class Surface {
      private final Vector3d a;
      private final Vector3d b;
      private final Vector3d c;
      private final Vector3d d;
      private final double firstTriangleArea;
      private final double area;

      private Surface(Vector3d a, Vector3d b, Vector3d c, Vector3d d) {
         this.a = a;
         this.b = b;
         this.c = c;
         this.d = d;
         this.firstTriangleArea = KillEffect.triangleArea(a, b, c);
         this.area = this.firstTriangleArea + KillEffect.triangleArea(a, c, d);
      }

      private Vector3d randomPoint(Random random) {
         return random.nextDouble() * this.area <= this.firstTriangleArea ? KillEffect.randomPointInTriangle(this.a, this.b, this.c, random) : KillEffect.randomPointInTriangle(this.a, this.c, this.d, random);
      }
   }
}
