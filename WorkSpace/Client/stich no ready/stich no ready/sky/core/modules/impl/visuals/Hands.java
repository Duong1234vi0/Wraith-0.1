package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import sky.core.events.EventHandsRender;
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
import sky.core.utils.render.shader.KawaseBlur;
import sky.core.utils.render.shader.ShaderUtil;

public class Hands extends Module {
   private final ModeSetting mode = new ModeSetting("Режим", "Заливка", new String[]{"Зеркало", "Заливка"});
   private final ModeSetting mirrorColorMode = new ModeSetting("Цвет зеркала", "Свой", () -> {
      return this.mode.is("Зеркало");
   }, new String[]{"Интерфейс", "Свой"});
   private final ColorSetting mirrorColor = new ColorSetting("Цвет", false, ColorUtil.hex("#8A98FFFF"), () -> {
      return this.mode.is("Зеркало") && this.mirrorColorMode.is("Свой");
   });
   private final SliderSetting mixFactor = new SliderSetting("Смешивание", 0.0F, 0.0F, 0.5F, 0.01F, () -> {
      return this.mode.is("Зеркало");
   });
   private final BooleanSetting fillRainbow = new BooleanSetting("Радужная", false, () -> {
      return this.mode.is("Заливка");
   });
   private final SliderSetting rainbowSpeed = new SliderSetting("Скорость радуги", 0.4F, 0.0F, 2.0F, 0.05F, () -> {
      return this.mode.is("Заливка") && (Boolean)this.fillRainbow.get();
   });
   private final SliderSetting rainbowScale = new SliderSetting("Масштаб радуги", 1.0F, 0.2F, 3.0F, 0.1F, () -> {
      return this.mode.is("Заливка") && (Boolean)this.fillRainbow.get();
   });
   private final ModeSetting fillColorMode = new ModeSetting("Цвет заливки", "Свой", () -> {
      return this.mode.is("Заливка") && !(Boolean)this.fillRainbow.get();
   }, new String[]{"Интерфейс", "Свой"});
   private final ColorSetting fillColor = new ColorSetting("Цвет заливки (свой)", false, ColorUtil.hex("#FF4444FF"), () -> {
      return this.mode.is("Заливка") && !(Boolean)this.fillRainbow.get() && this.fillColorMode.is("Свой");
   });
   private final SliderSetting fillAlpha = new SliderSetting("Прозрачность заливки", 0.8F, 0.0F, 1.0F, 0.05F, () -> {
      return this.mode.is("Заливка");
   });
   private final BooleanSetting keepShading = new BooleanSetting("Сохранить тени", true, () -> {
      return this.mode.is("Заливка");
   });
   private final SliderSetting shadingStrength = new SliderSetting("Сила теней", 0.3F, 0.0F, 1.0F, 0.05F, () -> {
      return this.mode.is("Заливка") && (Boolean)this.keepShading.get();
   });
   private final BooleanSetting outlineEnabled = new BooleanSetting("Обводка", false);
   private final SliderSetting outlineWidth = new SliderSetting("Толщина обводки", 1.0F, 0.5F, 3.0F, 0.5F, () -> {
      return (Boolean)this.outlineEnabled.get();
   });
   private final ColorSetting outlineColor = new ColorSetting("Цвет обводки", false, ColorUtil.hex("#8A98FFFF"), () -> {
      return (Boolean)this.outlineEnabled.get() && this.effectColorMode.is("Свой");
   });
   private final BooleanSetting glowEnabled = new BooleanSetting("Глов", true);
   private final SliderSetting glowRadius = new SliderSetting("Размытие", 4.0F, 1.0F, 6.0F, 1.0F, () -> {
      return (Boolean)this.glowEnabled.get();
   });
   private final BooleanSetting outerGlow = new BooleanSetting("Внешний глов", true, () -> {
      return (Boolean)this.glowEnabled.get();
   });
   private final SliderSetting glowExposure = new SliderSetting("Яркость", 2.0F, 0.5F, 5.0F, 0.1F, () -> {
      return (Boolean)this.glowEnabled.get() && (Boolean)this.outerGlow.get();
   });
   private final BooleanSetting autoColor = new BooleanSetting("Авто цвет", false, () -> {
      return (Boolean)this.glowEnabled.get() || (Boolean)this.outlineEnabled.get();
   });
   private final SliderSetting saturation = new SliderSetting("Насыщенность", 1.4F, 0.5F, 3.0F, 0.1F, () -> {
      return ((Boolean)this.glowEnabled.get() || (Boolean)this.outlineEnabled.get()) && (Boolean)this.autoColor.get();
   });
   private final ModeSetting effectColorMode = new ModeSetting("Цвет эффекта", "Свой", () -> {
      return ((Boolean)this.glowEnabled.get() || (Boolean)this.outlineEnabled.get()) && !(Boolean)this.autoColor.get();
   }, new String[]{"Интерфейс", "Свой"});
   private final ColorSetting glowColor1 = new ColorSetting("Цвет глова 1", false, ColorUtil.hex("#8A98FFFF"), () -> {
      return (Boolean)this.glowEnabled.get() && !(Boolean)this.autoColor.get() && this.effectColorMode.is("Свой");
   });
   private final ColorSetting glowColor2 = new ColorSetting("Цвет глова 2", false, ColorUtil.hex("#FF6BACFF"), () -> {
      return (Boolean)this.glowEnabled.get() && !(Boolean)this.autoColor.get() && this.effectColorMode.is("Свой");
   });
   private final BooleanSetting trailEnabled = new BooleanSetting("Шлейф", true, () -> {
      return (Boolean)this.glowEnabled.get() && (Boolean)this.outerGlow.get();
   });
   private final SliderSetting trailFade = new SliderSetting("Скорость затухания", 0.009F, 0.002F, 0.2F, 0.002F, () -> {
      return (Boolean)this.glowEnabled.get() && (Boolean)this.outerGlow.get() && (Boolean)this.trailEnabled.get();
   });
   private final SliderSetting trailRise = new SliderSetting("Подъём", 0.14F, 0.0F, 1.5F, 0.05F, () -> {
      return (Boolean)this.glowEnabled.get() && (Boolean)this.outerGlow.get() && (Boolean)this.trailEnabled.get();
   });
   private final SliderSetting trailSway = new SliderSetting("Качание", 0.025F, 0.0F, 0.2F, 0.005F, () -> {
      return (Boolean)this.glowEnabled.get() && (Boolean)this.outerGlow.get() && (Boolean)this.trailEnabled.get();
   });
   private final SliderSetting trailTurb = new SliderSetting("Турбулентность", 0.0F, 0.0F, 0.6F, 0.01F, () -> {
      return (Boolean)this.glowEnabled.get() && (Boolean)this.outerGlow.get() && (Boolean)this.trailEnabled.get();
   });
   private final SliderSetting trailFlicker = new SliderSetting("Мерцание", 0.0F, 0.0F, 0.2F, 0.01F, () -> {
      return (Boolean)this.glowEnabled.get() && (Boolean)this.outerGlow.get() && (Boolean)this.trailEnabled.get();
   });
   private final BooleanSetting trailBurst = new BooleanSetting("Сдув при ударе", true, () -> {
      return (Boolean)this.glowEnabled.get() && (Boolean)this.outerGlow.get() && (Boolean)this.trailEnabled.get();
   });
   private final SliderSetting trailBurstPower = new SliderSetting("Сила сдува", 2.5F, 1.0F, 10.0F, 0.5F, () -> {
      return (Boolean)this.glowEnabled.get() && (Boolean)this.outerGlow.get() && (Boolean)this.trailEnabled.get() && (Boolean)this.trailBurst.get();
   });
   private final BooleanSetting trailModel = new BooleanSetting("Шлейф модели", true, () -> {
      return (Boolean)this.glowEnabled.get() && (Boolean)this.outerGlow.get() && (Boolean)this.trailEnabled.get();
   });
   private final SliderSetting trailModelAlpha = new SliderSetting("Прозрачность модели", 0.4F, 0.1F, 1.0F, 0.05F, () -> {
      return (Boolean)this.glowEnabled.get() && (Boolean)this.outerGlow.get() && (Boolean)this.trailEnabled.get() && (Boolean)this.trailModel.get();
   });
   private Framebuffer handsBuffer;
   private Framebuffer trailRead;
   private Framebuffer trailWrite;
   private final List bloomBuffers = new ArrayList();
   private int downProg = -1;
   private int upProg = -1;
   private int glowProg = -1;
   private int fillProg = -1;
   private int outlineProg = -1;
   private int trailFadeProg = -1;
   private int trailColorProg = -1;
   private long lastTrailTime = 0L;
   private float smoothDt = 0.016666668F;
   private float smoothTrailRise;
   private float smoothTrailSway;
   private float smoothBurst;
   private long lastSwingMs = -10000L;
   private boolean wasSwinging = false;
   private final float[] autoCol = new float[]{1.0F, 1.0F, 1.0F, 1.0F};
   private int autoTick = 0;
   private int lastBloomTex = -1;
   private final Map uniformCache = new HashMap();
   private static final boolean MAC;

   public Hands() {
      super("Hands", "Накладывает эффект на ваши руки", Category.Visuals);
      this.addSettings(new Setting[]{this.mode, this.mirrorColorMode, this.mirrorColor, this.mixFactor, this.fillRainbow, this.rainbowSpeed, this.rainbowScale, this.fillColorMode, this.fillColor, this.fillAlpha, this.keepShading, this.shadingStrength, this.glowEnabled, this.glowRadius, this.outerGlow, this.glowExposure, this.autoColor, this.saturation, this.effectColorMode, this.glowColor1, this.glowColor2, this.trailEnabled, this.trailFade, this.trailRise, this.trailSway, this.trailTurb, this.trailFlicker, this.trailBurst, this.trailBurstPower, this.trailModel, this.trailModelAlpha, this.outlineEnabled, this.outlineWidth, this.outlineColor});
   }

   private int uloc(int p, String n) {
      long key = (long)p << 32 | (long)n.hashCode() & 4294967295L;
      Integer c = (Integer)this.uniformCache.get(key);
      if (c != null) {
         return c;
      } else {
         int l = GL20.glGetUniformLocation(p, n);
         this.uniformCache.put(key, l);
         return l;
      }
   }

   private void u1i(int p, String n, int v) {
      int l = this.uloc(p, n);
      if (l >= 0) {
         GL20.glUniform1i(l, v);
      }

   }

   private void u1f(int p, String n, float v) {
      int l = this.uloc(p, n);
      if (l >= 0) {
         GL20.glUniform1f(l, v);
      }

   }

   private void u2f(int p, String n, float a, float b) {
      int l = this.uloc(p, n);
      if (l >= 0) {
         GL20.glUniform2f(l, a, b);
      }

   }

   private void u3f(int p, String n, float a, float b, float c) {
      int l = this.uloc(p, n);
      if (l >= 0) {
         GL20.glUniform3f(l, a, b, c);
      }

   }

   private void bindTex(int unit, int tex) {
      GlStateManager.activeTexture(unit);
      GlStateManager.bindTexture(tex);
   }

   private int themeColor() {
      return ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL);
   }

   private Framebuffer ensureBuffer(Framebuffer buf, boolean depth) {
      return this.ensureBuffer(buf, depth, 1);
   }

   private Framebuffer ensureBuffer(Framebuffer buf, boolean depth, int divisor) {
      int fw = mc.getMainWindow().getFramebufferWidth();
      int fh = mc.getMainWindow().getFramebufferHeight();
      int w = Math.max(2, fw / divisor);
      int h = Math.max(2, fh / divisor);
      if (buf == null) {
         buf = new Framebuffer(w, h, depth, MAC);
         buf.setFramebufferFilter(9729);
         buf.setFramebufferColor(0.0F, 0.0F, 0.0F, 0.0F);
         buf.framebufferClear(MAC);
      } else if (buf.framebufferWidth != w || buf.framebufferHeight != h) {
         buf.resize(w, h, MAC);
         buf.setFramebufferFilter(9729);
         buf.framebufferClear(MAC);
      }

      return buf;
   }

   @EventTarget
   private void onHandsPre(EventHandsRender.Pre e) {
      if (this.fillProg == -1) {
         this.initShaders();
      }

      if ((Boolean)this.glowEnabled.get() || (Boolean)this.outlineEnabled.get()) {
         this.handsBuffer = this.ensureBuffer(this.handsBuffer, true);
         this.handsBuffer.framebufferClear(MAC);
         this.handsBuffer.bindFramebuffer(true);
      }

      int mc_clr;
      float[] c;
      if (this.mode.is("Зеркало")) {
         if (KawaseBlur.blur.BLURRED == null) {
            return;
         }

         ShaderUtil.hands.attach();
         GL13.glActiveTexture(33989);
         GL11.glBindTexture(3553, KawaseBlur.blur.BLURRED.framebufferTexture);
         GL13.glActiveTexture(33984);
         ShaderUtil.hands.setUniform("originalTexture", 0);
         ShaderUtil.hands.setUniform("blurredTexture", 5);
         mc_clr = this.mirrorColorMode.is("Интерфейс") ? this.themeColor() : (Integer)this.mirrorColor.get();
         c = ColorUtil.getColor(mc_clr);
         ShaderUtil.hands.setUniformf("multiplier", c[0], c[1], c[2], c[3]);
         ShaderUtil.hands.setUniformf("resolution", (float)KawaseBlur.blur.BLURRED.framebufferWidth, (float)KawaseBlur.blur.BLURRED.framebufferHeight);
         ShaderUtil.hands.setUniformf("mixFactor", (Float)this.mixFactor.get());
      } else if (this.mode.is("Заливка")) {
         GL20.glUseProgram(this.fillProg);
         mc_clr = this.fillColorMode.is("Интерфейс") ? this.themeColor() : (Integer)this.fillColor.get();
         c = ColorUtil.getColor(mc_clr);
         this.u3f(this.fillProg, "fillColor", c[0], c[1], c[2]);
         this.u1f(this.fillProg, "fillAlpha", (Float)this.fillAlpha.get());
         this.u1i(this.fillProg, "keepShading", (Boolean)this.keepShading.get() ? 1 : 0);
         this.u1f(this.fillProg, "shadingStrength", (Float)this.shadingStrength.get());
         this.u1i(this.fillProg, "originalTexture", 0);
         this.u1i(this.fillProg, "rainbow", (Boolean)this.fillRainbow.get() ? 1 : 0);
         this.u1f(this.fillProg, "rainbowTime", (float)(System.currentTimeMillis() % 100000L) / 1000.0F);
         this.u1f(this.fillProg, "rainbowSpeed", (Float)this.rainbowSpeed.get());
         this.u1f(this.fillProg, "rainbowScale", (Float)this.rainbowScale.get());
         this.u1f(this.fillProg, "screenH", (float)mc.getMainWindow().getFramebufferHeight());
      }

   }

   @EventTarget
   private void onHandsPost(EventHandsRender.Post e) {
      if (this.mode.is("Зеркало")) {
         ShaderUtil.hands.detach();
      } else {
         GL20.glUseProgram(0);
      }

      if (((Boolean)this.glowEnabled.get() || (Boolean)this.outlineEnabled.get()) && this.handsBuffer != null) {
         mc.getFramebuffer().bindFramebuffer(true);
         this.renderEffects();
      }

   }

   private void renderEffects() {
      this.setup2D();
      this.lastBloomTex = -1;
      boolean doBloom = (Boolean)this.glowEnabled.get() && (Boolean)this.outerGlow.get();
      if (doBloom) {
         this.doBloomChain();
      } else if ((Boolean)this.outlineEnabled.get() && (Boolean)this.autoColor.get()) {
         this.doSmallBlurForAuto();
      }

      RenderSystem.enableBlend();
      RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.handsBuffer.bindFramebufferTexture();
      this.drawQuads();
      if ((Boolean)this.outlineEnabled.get()) {
         this.renderOutline();
      }

      this.restore3D();
   }

   private void doBloomChain() {
      int iter = (int)(Float)this.glowRadius.get();
      int cur = this.handsBuffer.framebufferTexture;
      this.setupBloomBuffers(iter);
      GL20.glUseProgram(this.downProg);
      this.u1i(this.downProg, "inTexture", 0);

      int g1;
      Framebuffer b;
      for(g1 = 0; g1 < iter; ++g1) {
         b = (Framebuffer)this.bloomBuffers.get(g1);
         b.framebufferClear(MAC);
         b.bindFramebuffer(true);
         this.u2f(this.downProg, "uSize", (float)b.framebufferWidth, (float)b.framebufferHeight);
         this.u2f(this.downProg, "uOffset", 1.0F + (float)g1, 1.0F + (float)g1);
         this.u2f(this.downProg, "uHalfPixel", 0.5F / (float)b.framebufferWidth, 0.5F / (float)b.framebufferHeight);
         this.bindTex(33984, cur);
         this.drawQuads();
         cur = b.framebufferTexture;
      }

      GL20.glUseProgram(this.upProg);
      this.u1i(this.upProg, "inTexture", 0);

      for(g1 = iter - 1; g1 >= 1; --g1) {
         b = (Framebuffer)this.bloomBuffers.get(g1 - 1);
         b.bindFramebuffer(true);
         this.u2f(this.upProg, "uSize", (float)b.framebufferWidth, (float)b.framebufferHeight);
         this.u2f(this.upProg, "uOffset", 1.0F + (float)g1, 1.0F + (float)g1);
         this.u2f(this.upProg, "uHalfPixel", 0.5F / (float)b.framebufferWidth, 0.5F / (float)b.framebufferHeight);
         this.u3f(this.upProg, "color", 1.0F, 1.0F, 1.0F);
         this.bindTex(33984, cur);
         this.drawQuads();
         cur = b.framebufferTexture;
      }

      GL20.glUseProgram(0);
      this.lastBloomTex = cur;
      if ((Boolean)this.autoColor.get()) {
         this.updateAutoColor();
      }

      g1 = this.effectColorMode.is("Интерфейс") ? this.themeColor() : (Integer)this.glowColor1.get();
      int g2 = this.effectColorMode.is("Интерфейс") ? this.themeColor() : (Integer)this.glowColor2.get();
      float[] c1 = ColorUtil.getColor(g1);
      float[] c2 = ColorUtil.getColor(g2);
      if ((Boolean)this.trailEnabled.get()) {
         this.renderTrail(cur, c1, c2);
      } else {
         mc.getFramebuffer().bindFramebuffer(true);
         RenderSystem.enableBlend();
         RenderSystem.blendFunc(770, 1);
         GL20.glUseProgram(this.glowProg);
         this.u1i(this.glowProg, "bloomTexture", 0);
         this.u1i(this.glowProg, "maskTexture", 1);
         this.u3f(this.glowProg, "glowColor1", c1[0], c1[1], c1[2]);
         this.u3f(this.glowProg, "glowColor2", c2[0], c2[1], c2[2]);
         this.u1f(this.glowProg, "exposure", (Float)this.glowExposure.get());
         this.u1i(this.glowProg, "autoColor", (Boolean)this.autoColor.get() ? 1 : 0);
         this.u1f(this.glowProg, "saturation", (Float)this.saturation.get());
         this.bindTex(33985, this.handsBuffer.framebufferTexture);
         this.bindTex(33984, cur);
         this.drawQuads();
         GL20.glUseProgram(0);
         this.bindTex(33985, 0);
         this.bindTex(33984, 0);
         RenderSystem.defaultBlendFunc();
      }

   }

   private void doSmallBlurForAuto() {
      int iter = 3;
      this.setupBloomBuffers(iter);
      int cur = this.handsBuffer.framebufferTexture;
      GL20.glUseProgram(this.downProg);
      this.u1i(this.downProg, "inTexture", 0);

      int i;
      Framebuffer b;
      for(i = 0; i < iter; ++i) {
         b = (Framebuffer)this.bloomBuffers.get(i);
         b.framebufferClear(MAC);
         b.bindFramebuffer(true);
         this.u2f(this.downProg, "uSize", (float)b.framebufferWidth, (float)b.framebufferHeight);
         this.u2f(this.downProg, "uOffset", 1.0F + (float)i, 1.0F + (float)i);
         this.u2f(this.downProg, "uHalfPixel", 0.5F / (float)b.framebufferWidth, 0.5F / (float)b.framebufferHeight);
         this.bindTex(33984, cur);
         this.drawQuads();
         cur = b.framebufferTexture;
      }

      GL20.glUseProgram(this.upProg);
      this.u1i(this.upProg, "inTexture", 0);

      for(i = iter - 1; i >= 1; --i) {
         b = (Framebuffer)this.bloomBuffers.get(i - 1);
         b.bindFramebuffer(true);
         this.u2f(this.upProg, "uSize", (float)b.framebufferWidth, (float)b.framebufferHeight);
         this.u2f(this.upProg, "uOffset", 1.0F + (float)i, 1.0F + (float)i);
         this.u2f(this.upProg, "uHalfPixel", 0.5F / (float)b.framebufferWidth, 0.5F / (float)b.framebufferHeight);
         this.u3f(this.upProg, "color", 1.0F, 1.0F, 1.0F);
         this.bindTex(33984, cur);
         this.drawQuads();
         cur = b.framebufferTexture;
      }

      GL20.glUseProgram(0);
      this.lastBloomTex = cur;
      this.updateAutoColor();
      mc.getFramebuffer().bindFramebuffer(true);
   }

   private void renderTrail(int bloomTex, float[] c1, float[] c2) {
      this.trailRead = this.ensureBuffer(this.trailRead, false, 2);
      this.trailWrite = this.ensureBuffer(this.trailWrite, false, 2);
      long now = System.currentTimeMillis();
      float rawDt = this.lastTrailTime > 0L ? (float)(now - this.lastTrailTime) / 1000.0F : 0.016666668F;
      this.lastTrailTime = now;
      if (rawDt <= 0.0F || rawDt > 0.05F) {
         rawDt = this.smoothDt;
      }

      float dt;
      float t;
      boolean var19;
      label36: {
         rawDt = Math.max(0.0069444445F, Math.min(0.016666668F, rawDt));
         this.smoothDt += (rawDt - this.smoothDt) * 0.08F;
         dt = this.smoothDt;
         t = (float)(now % 100000L) / 1000.0F;
         float swayFreq = 3.6F;
         float upDelta = (Float)this.trailRise.get() * dt;
         float amp = (Float)this.trailSway.get();
         float swayDelta = (float)(Math.sin((double)(t * swayFreq)) - Math.sin((double)((t - dt) * swayFreq))) * amp;
         float trailSmooth = 1.0F - (float)Math.exp((double)(-dt * 8.0F));
         this.smoothTrailRise += (upDelta - this.smoothTrailRise) * trailSmooth;
         this.smoothTrailSway += (swayDelta - this.smoothTrailSway) * trailSmooth;
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            var10000 = mc;
            if (Minecraft.player.isSwingInProgress) {
               var19 = true;
               break label36;
            }
         }

         var19 = false;
      }

      boolean swinging = var19;
      if ((Boolean)this.trailBurst.get() && swinging && !this.wasSwinging) {
         this.lastSwingMs = now;
      }

      this.wasSwinging = swinging;
      float burstAge = (float)(now - this.lastSwingMs) / 1000.0F;
      float burst = Math.max(0.0F, 1.0F - burstAge / 0.45F);
      this.smoothBurst += (burst - this.smoothBurst) * (1.0F - (float)Math.exp((double)(-dt * 6.0F)));
      float fadeAdd = this.smoothBurst * (Float)this.trailBurstPower.get() * 0.012F;
      this.trailWrite.bindFramebuffer(true);
      GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
      GL11.glClear(16384);
      RenderSystem.disableBlend();
      GL20.glUseProgram(this.trailFadeProg);
      this.u1i(this.trailFadeProg, "inTex", 0);
      this.u2f(this.trailFadeProg, "offset", this.smoothTrailSway, this.smoothTrailRise);
      this.u1f(this.trailFadeProg, "fade", (Float)this.trailFade.get() + fadeAdd);
      this.u1f(this.trailFadeProg, "t", t);
      this.u1f(this.trailFadeProg, "dt", dt);
      this.u1f(this.trailFadeProg, "turb", (Float)this.trailTurb.get());
      this.u1f(this.trailFadeProg, "flickAmp", (Float)this.trailFlicker.get());
      this.u2f(this.trailFadeProg, "texSize", (float)this.trailRead.framebufferWidth, (float)this.trailRead.framebufferHeight);
      this.bindTex(33984, this.trailRead.framebufferTexture);
      this.drawQuads();
      GL20.glUseProgram(0);
      this.bindTex(33984, 0);
      RenderSystem.enableBlend();
      RenderSystem.blendFunc(1, 771);
      GL20.glUseProgram(this.trailColorProg);
      this.u1i(this.trailColorProg, "bloomTexture", 0);
      this.u3f(this.trailColorProg, "glowColor1", c1[0], c1[1], c1[2]);
      this.u3f(this.trailColorProg, "glowColor2", c2[0], c2[1], c2[2]);
      this.u1f(this.trailColorProg, "exposure", (Float)this.glowExposure.get());
      this.u1i(this.trailColorProg, "autoColor", (Boolean)this.autoColor.get() ? 1 : 0);
      this.u1f(this.trailColorProg, "saturation", (Float)this.saturation.get());
      this.bindTex(33984, bloomTex);
      this.drawQuads();
      GL20.glUseProgram(0);
      this.bindTex(33984, 0);
      if ((Boolean)this.trailModel.get()) {
         RenderSystem.enableBlend();
         RenderSystem.blendFunc(770, 771);
         RenderSystem.color4f(1.0F, 1.0F, 1.0F, (Float)this.trailModelAlpha.get());
         this.handsBuffer.bindFramebufferTexture();
         this.drawQuads();
         RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      }

      mc.getFramebuffer().bindFramebuffer(true);
      RenderSystem.enableBlend();
      RenderSystem.blendFunc(1, 771);
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.trailWrite.bindFramebufferTexture();
      this.drawQuads();
      RenderSystem.defaultBlendFunc();
      Framebuffer tmp = this.trailRead;
      this.trailRead = this.trailWrite;
      this.trailWrite = tmp;
   }

   private void renderOutline() {
      boolean rainbow = (Boolean)this.fillRainbow.get() && this.mode.is("Заливка");
      boolean auto = (Boolean)this.autoColor.get() && this.lastBloomTex != -1;
      int colorMode = rainbow ? 1 : (auto ? 2 : 0);
      int oc = this.effectColorMode.is("Интерфейс") ? this.themeColor() : (Integer)this.outlineColor.get();
      Color col = new Color(oc, true);
      float a = (float)col.getAlpha() / 255.0F;
      GL20.glUseProgram(this.outlineProg);
      this.u1i(this.outlineProg, "inTex", 0);
      this.u1i(this.outlineProg, "bloomTex", 1);
      this.u1i(this.outlineProg, "colorMode", colorMode);
      this.u1f(this.outlineProg, "width", (Float)this.outlineWidth.get());
      this.u2f(this.outlineProg, "texelSize", 1.0F / (float)mc.getMainWindow().getFramebufferWidth(), 1.0F / (float)mc.getMainWindow().getFramebufferHeight());
      this.u1f(this.outlineProg, "alpha", a);
      this.u1f(this.outlineProg, "rainbowTime", (float)(System.currentTimeMillis() % 100000L) / 1000.0F);
      this.u1f(this.outlineProg, "rainbowSpeed", (Float)this.rainbowSpeed.get());
      this.u1f(this.outlineProg, "rainbowScale", (Float)this.rainbowScale.get());
      this.u1f(this.outlineProg, "screenH", (float)mc.getMainWindow().getFramebufferHeight());
      this.u1f(this.outlineProg, "saturation", (Float)this.saturation.get());
      this.u3f(this.outlineProg, "solidColor", (float)col.getRed() / 255.0F, (float)col.getGreen() / 255.0F, (float)col.getBlue() / 255.0F);
      if (auto) {
         this.bindTex(33985, this.lastBloomTex);
      }

      this.bindTex(33984, this.handsBuffer.framebufferTexture);
      this.drawQuads();
      if (auto) {
         this.bindTex(33985, 0);
      }

      this.bindTex(33984, 0);
      GL20.glUseProgram(0);
   }

   private void updateAutoColor() {
      if (!this.bloomBuffers.isEmpty()) {
         if (this.autoTick++ % 6 == 0) {
            Framebuffer small = (Framebuffer)this.bloomBuffers.get(this.bloomBuffers.size() - 1);
            int w = small.framebufferWidth;
            int h = small.framebufferHeight;
            int prev = GL11.glGetInteger(36006);
            small.bindFramebuffer(false);
            ByteBuffer buf = BufferUtils.createByteBuffer(w * h * 4);
            GL11.glReadPixels(0, 0, w, h, 6408, 5121, buf);
            GL30.glBindFramebuffer(36160, prev);
            float sumR = 0.0F;
            float sumG = 0.0F;
            float sumB = 0.0F;
            float sumA = 0.0F;

            for(int i = 0; i < w * h; ++i) {
               int o = i * 4;
               sumR += (float)(buf.get(o) & 255) / 255.0F;
               sumG += (float)(buf.get(o + 1) & 255) / 255.0F;
               sumB += (float)(buf.get(o + 2) & 255) / 255.0F;
               sumA += (float)(buf.get(o + 3) & 255) / 255.0F;
            }

            if (!(sumA < 0.001F)) {
               float r = sumR / sumA;
               float g = sumG / sumA;
               float b = sumB / sumA;
               float m = Math.max(r, Math.max(g, b));
               if (m > 0.001F) {
                  r /= m;
                  g /= m;
                  b /= m;
               }

               float sat = (Float)this.saturation.get();
               float l = 0.299F * r + 0.587F * g + 0.114F * b;
               this.autoCol[0] = clamp01(l + (r - l) * sat);
               this.autoCol[1] = clamp01(l + (g - l) * sat);
               this.autoCol[2] = clamp01(l + (b - l) * sat);
               this.autoCol[3] = 1.0F;
            }
         }
      }
   }

   private static float clamp01(float v) {
      return v < 0.0F ? 0.0F : Math.min(v, 1.0F);
   }

   private void setupBloomBuffers(int n) {
      int fw = mc.getMainWindow().getFramebufferWidth();
      int fh = mc.getMainWindow().getFramebufferHeight();
      int i;
      if (this.bloomBuffers.size() < n) {
         this.bloomBuffers.forEach(Framebuffer::deleteFramebuffer);
         this.bloomBuffers.clear();

         for(i = 0; i < n; ++i) {
            Framebuffer f = new Framebuffer(Math.max(2, fw >> i + 1), Math.max(2, fh >> i + 1), false, MAC);
            f.setFramebufferFilter(9729);
            f.setFramebufferColor(0.0F, 0.0F, 0.0F, 0.0F);
            this.bloomBuffers.add(f);
         }
      }

      for(i = 0; i < n; ++i) {
         int w = Math.max(2, fw >> i + 1);
         int h = Math.max(2, fh >> i + 1);
         Framebuffer b = (Framebuffer)this.bloomBuffers.get(i);
         if (b.framebufferWidth != w || b.framebufferHeight != h) {
            b.resize(w, h, MAC);
            b.setFramebufferFilter(9729);
         }
      }

   }

   private void drawQuads() {
      BufferBuilder b = Tessellator.getInstance().getBuffer();
      float w = (float)mc.getMainWindow().getScaledWidth();
      float h = (float)mc.getMainWindow().getScaledHeight();
      b.begin(7, DefaultVertexFormats.POSITION_TEX);
      b.pos(0.0, (double)h, 0.0).tex(0.0F, 0.0F).endVertex();
      b.pos((double)w, (double)h, 0.0).tex(1.0F, 0.0F).endVertex();
      b.pos((double)w, 0.0, 0.0).tex(1.0F, 1.0F).endVertex();
      b.pos(0.0, 0.0, 0.0).tex(0.0F, 1.0F).endVertex();
      Tessellator.getInstance().draw();
   }

   private void setup2D() {
      RenderSystem.matrixMode(5889);
      RenderSystem.pushMatrix();
      RenderSystem.loadIdentity();
      RenderSystem.ortho(0.0, (double)mc.getMainWindow().getScaledWidth(), (double)mc.getMainWindow().getScaledHeight(), 0.0, 1000.0, 3000.0);
      RenderSystem.matrixMode(5888);
      RenderSystem.pushMatrix();
      RenderSystem.loadIdentity();
      RenderSystem.translatef(0.0F, 0.0F, -2000.0F);
      RenderSystem.disableDepthTest();
      RenderSystem.disableAlphaTest();
   }

   private void restore3D() {
      RenderSystem.enableDepthTest();
      RenderSystem.enableAlphaTest();
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.bindTexture(0);
      RenderSystem.matrixMode(5889);
      RenderSystem.popMatrix();
      RenderSystem.matrixMode(5888);
      RenderSystem.popMatrix();
   }

   private void initShaders() {
      String V = "#version 120\nvoid main(){gl_TexCoord[0]=gl_MultiTexCoord0;gl_Position=gl_ModelViewProjectionMatrix*gl_Vertex;}";
      this.downProg = this.createProgram(V, "#version 120\nuniform sampler2D inTexture;uniform vec2 uOffset,uHalfPixel,uSize;void main(){vec2 u=gl_TexCoord[0].xy,h=uHalfPixel*uOffset;gl_FragColor=(texture2D(inTexture,u)*4.0+texture2D(inTexture,u-h)+texture2D(inTexture,u+h)+texture2D(inTexture,u+vec2(h.x,-h.y))+texture2D(inTexture,u-vec2(h.x,-h.y)))/8.0;}");
      this.upProg = this.createProgram(V, "#version 120\nuniform sampler2D inTexture;uniform vec2 uOffset,uHalfPixel,uSize;uniform vec3 color;void main(){vec2 u=gl_TexCoord[0].xy,h=uHalfPixel*uOffset;vec4 s=texture2D(inTexture,u+vec2(-h.x*2.,0.))+texture2D(inTexture,u+vec2(-h.x,h.y))*2.+texture2D(inTexture,u+vec2(0.,h.y*2.))+texture2D(inTexture,u+vec2(h.x,h.y))*2.+texture2D(inTexture,u+vec2(h.x*2.,0.))+texture2D(inTexture,u+vec2(h.x,-h.y))*2.+texture2D(inTexture,u+vec2(0.,-h.y*2.))+texture2D(inTexture,u+vec2(-h.x,-h.y))*2.;vec4 r=s/12.;gl_FragColor=vec4(r.rgb*color,r.a);}");
      this.glowProg = this.createProgram(V, "#version 120\nuniform sampler2D bloomTexture, maskTexture;\nuniform vec3 glowColor1, glowColor2;\nuniform float exposure;\nuniform int autoColor;\nuniform float saturation;\nvoid main(){\n  vec2 u = gl_TexCoord[0].xy;\n  vec4 b = texture2D(bloomTexture, u);\n  float i = b.a * (1.0 - texture2D(maskTexture, u).a) * exposure;\n  vec3 col;\n  if (autoColor == 1) {\n    vec3 c = b.rgb / max(b.a, 0.001);\n    float m = max(c.r, max(c.g, c.b));\n    if (m > 0.001) c /= m;\n    float l = dot(c, vec3(0.299, 0.587, 0.114));\n    col = clamp(mix(vec3(l), c, saturation), 0.0, 1.0);\n  } else {\n    col = mix(glowColor1, glowColor2, u.y);\n  }\n  gl_FragColor = vec4(col, i);\n}");
      this.outlineProg = this.createProgram(V, "#version 120\nuniform sampler2D inTex;\nuniform sampler2D bloomTex;\nuniform vec2 texelSize;\nuniform float width, alpha, rainbowTime, rainbowSpeed, rainbowScale, screenH, saturation;\nuniform int colorMode;\nuniform vec3 solidColor;\nvec3 hue2rgb(float h){\n  vec3 p = abs(fract(vec3(h) + vec3(0.0, 2.0/3.0, 1.0/3.0)) * 6.0 - 3.0);\n  return clamp(p - 1.0, 0.0, 1.0);\n}\nvoid main(){\n  vec2 uv = gl_TexCoord[0].xy;\n  vec4 c = texture2D(inTex, uv);\n  if (c.a > 0.01) discard;\n  float maxA = 0.0;\n  for (int x = -2; x <= 2; x++) {\n    for (int y = -2; y <= 2; y++) {\n      if (x == 0 && y == 0) continue;\n      vec2 d = vec2(float(x), float(y)) * texelSize * width;\n      maxA = max(maxA, texture2D(inTex, uv + d).a);\n    }\n  }\n  if (maxA < 0.01) discard;\n  vec3 col;\n  if (colorMode == 1) {\n    float yN = gl_FragCoord.y / max(screenH, 1.0);\n    float h = fract(yN * rainbowScale + rainbowTime * rainbowSpeed);\n    col = hue2rgb(h);\n  } else if (colorMode == 2) {\n    vec4 b = texture2D(bloomTex, uv);\n    vec3 cb = b.rgb / max(b.a, 0.001);\n    float m = max(cb.r, max(cb.g, cb.b));\n    if (m > 0.001) cb /= m;\n    float l = dot(cb, vec3(0.299, 0.587, 0.114));\n    col = clamp(mix(vec3(l), cb, saturation), 0.0, 1.0);\n  } else {\n    col = solidColor;\n  }\n  gl_FragColor = vec4(col, maxA * alpha);\n}");
      this.fillProg = this.createProgram(V, "#version 120\nuniform sampler2D originalTexture;\nuniform vec3 fillColor;\nuniform float fillAlpha, shadingStrength;\nuniform int keepShading;\nuniform int rainbow;\nuniform float rainbowTime, rainbowSpeed, rainbowScale, screenH;\nvec3 hue2rgb(float h){\n  vec3 p = abs(fract(vec3(h) + vec3(0.0, 2.0/3.0, 1.0/3.0)) * 6.0 - 3.0);\n  return clamp(p - 1.0, 0.0, 1.0);\n}\nvoid main(){\n  vec4 s = texture2D(originalTexture, gl_TexCoord[0].xy);\n  if (s.a < 0.01) discard;\n  vec3 f;\n  if (rainbow == 1) {\n    float yN = gl_FragCoord.y / max(screenH, 1.0);\n    float h = fract(yN * rainbowScale + rainbowTime * rainbowSpeed);\n    f = hue2rgb(h);\n  } else {\n    f = fillColor;\n  }\n  if (keepShading == 1) f *= mix(1.0, dot(s.rgb, vec3(0.299, 0.587, 0.114)), shadingStrength);\n  gl_FragColor = vec4(mix(s.rgb, f, fillAlpha), s.a);\n}");
      this.trailFadeProg = this.createProgram(V, "#version 120\nuniform sampler2D inTex;\nuniform vec2 offset;\nuniform vec2 texSize;\nuniform float fade;\nuniform float t;\nuniform float dt;\nuniform float turb;\nuniform float flickAmp;\nfloat wob(float y, float tt){\n  return sin(y*9.0 + tt*4.3)*0.6 + sin(y*17.0 - tt*2.1)*0.3 + sin(y*4.0 + tt*1.3)*0.4 + sin(y*28.0 + tt*5.7)*0.15;\n}\nvec4 blurSample(vec2 p){\n  vec2 px = 1.0 / max(texSize, vec2(1.0));\n  vec4 c = texture2D(inTex, p) * 0.36;\n  c += texture2D(inTex, p + vec2( px.x, 0.0)) * 0.16;\n  c += texture2D(inTex, p + vec2(-px.x, 0.0)) * 0.16;\n  c += texture2D(inTex, p + vec2(0.0,  px.y)) * 0.16;\n  c += texture2D(inTex, p + vec2(0.0, -px.y)) * 0.16;\n  return c;\n}\nvoid main(){\n  vec2 uv = gl_TexCoord[0].xy;\n  float dxTurb = (wob(uv.y, t) - wob(uv.y, t - dt)) * turb;\n  float yFactor = 0.7 + uv.y * 0.8;\n  vec2 disp = vec2(offset.x + dxTurb, offset.y * yFactor);\n  vec4 c = blurSample(uv - disp);\n  float flick = 1.0 - flickAmp + flickAmp * (0.5 + 0.5*sin(t*14.0) + 0.25*sin(t*9.3));\n  float oldA = c.a;\n  float newA = max(0.0, oldA * (0.985 * flick) - fade);\n  float scale = oldA > 0.001 ? newA / oldA : 0.0;\n  gl_FragColor = vec4(c.rgb * scale, newA);\n}");
      this.trailColorProg = this.createProgram(V, "#version 120\nuniform sampler2D bloomTexture;\nuniform vec3 glowColor1;\nuniform vec3 glowColor2;\nuniform float exposure;\nuniform int autoColor;\nuniform float saturation;\nvoid main(){\n  vec2 u = gl_TexCoord[0].xy;\n  vec4 b = texture2D(bloomTexture, u);\n  float a = clamp(b.a * exposure, 0.0, 1.0);\n  vec3 col;\n  if (autoColor == 1) {\n    vec3 c = b.rgb / max(b.a, 0.001);\n    float m = max(c.r, max(c.g, c.b));\n    if (m > 0.001) c /= m;\n    float l = dot(c, vec3(0.299, 0.587, 0.114));\n    col = clamp(mix(vec3(l), c, saturation), 0.0, 1.0);\n  } else {\n    col = mix(glowColor1, glowColor2, u.y);\n  }\n  gl_FragColor = vec4(col * a, a);\n}");
   }

   private int createProgram(String vs, String fs) {
      int v = GL20.glCreateShader(35633);
      int f = GL20.glCreateShader(35632);
      int p = GL20.glCreateProgram();
      GL20.glShaderSource(v, vs);
      GL20.glCompileShader(v);
      if (GL20.glGetShaderi(v, 35713) == 0) {
         System.err.println("[Hands] VS error: " + GL20.glGetShaderInfoLog(v, 1024));
      }

      GL20.glShaderSource(f, fs);
      GL20.glCompileShader(f);
      if (GL20.glGetShaderi(f, 35713) == 0) {
         System.err.println("[Hands] FS error: " + GL20.glGetShaderInfoLog(f, 1024));
      }

      GL20.glAttachShader(p, v);
      GL20.glAttachShader(p, f);
      GL20.glLinkProgram(p);
      return p;
   }

   public void onDisable() {
      super.onDisable();
      if (this.handsBuffer != null) {
         this.handsBuffer.deleteFramebuffer();
         this.handsBuffer = null;
      }

      if (this.trailRead != null) {
         this.trailRead.deleteFramebuffer();
         this.trailRead = null;
      }

      if (this.trailWrite != null) {
         this.trailWrite.deleteFramebuffer();
         this.trailWrite = null;
      }

      this.bloomBuffers.forEach(Framebuffer::deleteFramebuffer);
      this.bloomBuffers.clear();
      this.lastTrailTime = 0L;
      this.smoothDt = 0.016666668F;
      this.smoothTrailRise = 0.0F;
      this.smoothTrailSway = 0.0F;
      this.smoothBurst = 0.0F;
      if (this.downProg != -1) {
         GL20.glDeleteProgram(this.downProg);
         GL20.glDeleteProgram(this.upProg);
         GL20.glDeleteProgram(this.glowProg);
         GL20.glDeleteProgram(this.fillProg);
         GL20.glDeleteProgram(this.trailFadeProg);
         GL20.glDeleteProgram(this.trailColorProg);
         if (this.outlineProg != -1) {
            GL20.glDeleteProgram(this.outlineProg);
         }

         this.downProg = this.upProg = this.glowProg = this.fillProg = this.outlineProg = this.trailFadeProg = this.trailColorProg = -1;
         this.uniformCache.clear();
      }

   }

   static {
      MAC = Minecraft.IS_RUNNING_ON_MAC;
   }
}
