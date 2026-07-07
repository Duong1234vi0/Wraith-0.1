package unique.core.core.manager.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_276;
import net.minecraft.class_279;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import org.jetbrains.annotations.NotNull;
import unique.core.core.manager.IManager;
import unique.core.features.modules.render.Shaders;
import unique.core.utility.interfaces.IShaderEffect;
import unique.core.utility.render.Render3DEngine;
import unique.core.utility.render.shaders.satin.api.managed.ManagedShaderEffect;
import unique.core.utility.render.shaders.satin.api.managed.ShaderEffectManager;

public class ShaderManager implements IManager {
   private static final List<ShaderManager.RenderTask> tasks = new ArrayList<>();
   private ShaderManager.ThunderHackFramebuffer shaderBuffer;
   public float time = 0.0F;
   public static ManagedShaderEffect DEFAULT_OUTLINE;
   public static ManagedShaderEffect SMOKE_OUTLINE;
   public static ManagedShaderEffect GRADIENT_OUTLINE;
   public static ManagedShaderEffect SNOW_OUTLINE;
   public static ManagedShaderEffect FADE_OUTLINE;
   public static ManagedShaderEffect DEFAULT;
   public static ManagedShaderEffect SMOKE;
   public static ManagedShaderEffect GRADIENT;
   public static ManagedShaderEffect SNOW;
   public static ManagedShaderEffect FADE;

   public void renderShader(Runnable runnable, ShaderManager.Shader mode) {
      tasks.add(new ShaderManager.RenderTask(runnable, mode));
   }

   public void renderShaders() {
      if (DEFAULT == null) {
         this.shaderBuffer = new ShaderManager.ThunderHackFramebuffer(mc.method_1522().field_1482, mc.method_1522().field_1481);
         this.reloadShaders();
      }

      if (this.shaderBuffer != null) {
         tasks.forEach(t -> this.applyShader(t.task(), t.shader()));
         tasks.clear();
      }
   }

   public void applyShader(Runnable runnable, ShaderManager.Shader mode) {
      class_276 MCBuffer = class_310.method_1551().method_1522();
      RenderSystem.assertOnRenderThreadOrInit();
      if (this.shaderBuffer.field_1482 != MCBuffer.field_1482 || this.shaderBuffer.field_1481 != MCBuffer.field_1481) {
         this.shaderBuffer.method_1234(MCBuffer.field_1482, MCBuffer.field_1481, false);
      }

      GlStateManager._glBindFramebuffer(36009, this.shaderBuffer.field_1476);
      this.shaderBuffer.method_1235(true);
      runnable.run();
      this.shaderBuffer.method_1240();
      GlStateManager._glBindFramebuffer(36009, MCBuffer.field_1476);
      MCBuffer.method_1235(false);
      ManagedShaderEffect shader = this.getShader(mode);
      class_276 mainBuffer = class_310.method_1551().method_1522();
      class_279 effect = shader.getShaderEffect();
      if (effect != null) {
         ((IShaderEffect)effect).addFakeTargetHook("bufIn", this.shaderBuffer);
      }

      class_276 outBuffer = shader.getShaderEffect().method_1264("bufOut");
      this.setupShader(mode, shader);
      this.shaderBuffer.method_1230(false);
      mainBuffer.method_1235(false);
      RenderSystem.enableBlend();
      RenderSystem.blendFuncSeparate(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA, class_4535.ZERO, class_4534.ONE);
      RenderSystem.backupProjectionMatrix();
      outBuffer.method_22594(outBuffer.field_1482, outBuffer.field_1481, false);
      RenderSystem.restoreProjectionMatrix();
      RenderSystem.defaultBlendFunc();
      RenderSystem.disableBlend();
   }

   public ManagedShaderEffect getShader(@NotNull ShaderManager.Shader mode) {
      return switch (mode) {
         case Smoke -> SMOKE;
         case Gradient -> GRADIENT;
         case Snow -> SNOW;
         case Fade -> FADE;
         default -> DEFAULT;
      };
   }

   public ManagedShaderEffect getShaderOutline(@NotNull ShaderManager.Shader mode) {
      return switch (mode) {
         case Smoke -> SMOKE_OUTLINE;
         case Gradient -> GRADIENT_OUTLINE;
         case Snow -> SNOW_OUTLINE;
         case Fade -> FADE_OUTLINE;
         default -> DEFAULT_OUTLINE;
      };
   }

   public void setupShader(ShaderManager.Shader shader, ManagedShaderEffect effect) {
      Shaders shaders = ModuleManager.shaders;
      if (shader == ShaderManager.Shader.Gradient) {
         effect.setUniformValue("alpha0", shaders.glow.getValue() ? -1.0F : shaders.outlineColor.getValue().getAlpha() / 255.0F);
         effect.setUniformValue("alpha1", shaders.fillAlpha.getValue().intValue() / 255.0F);
         effect.setUniformValue("alpha2", shaders.alpha2.getValue().intValue() / 255.0F);
         effect.setUniformValue("lineWidth", shaders.lineWidth.getValue());
         effect.setUniformValue("oct", shaders.octaves.getValue());
         effect.setUniformValue("quality", shaders.quality.getValue());
         effect.setUniformValue("factor", shaders.factor.getValue());
         effect.setUniformValue("moreGradient", shaders.gradient.getValue());
         effect.setUniformValue("resolution", mc.method_22683().method_4486(), mc.method_22683().method_4502());
         effect.setUniformValue("time", this.time);
         effect.render(Render3DEngine.getTickDelta());
         this.time += 0.008F;
      } else if (shader == ShaderManager.Shader.Smoke) {
         effect.setUniformValue("alpha0", shaders.glow.getValue() ? -1.0F : shaders.outlineColor.getValue().getAlpha() / 255.0F);
         effect.setUniformValue("alpha1", shaders.fillAlpha.getValue().intValue() / 255.0F);
         effect.setUniformValue("lineWidth", shaders.lineWidth.getValue());
         effect.setUniformValue("quality", shaders.quality.getValue());
         effect.setUniformValue(
            "first",
            shaders.outlineColor.getValue().getGlRed(),
            shaders.outlineColor.getValue().getGlGreen(),
            shaders.outlineColor.getValue().getGlBlue(),
            shaders.outlineColor.getValue().getGlAlpha()
         );
         effect.setUniformValue(
            "second", shaders.outlineColor1.getValue().getGlRed(), shaders.outlineColor1.getValue().getGlGreen(), shaders.outlineColor1.getValue().getGlBlue()
         );
         effect.setUniformValue(
            "third", shaders.outlineColor2.getValue().getGlRed(), shaders.outlineColor2.getValue().getGlGreen(), shaders.outlineColor2.getValue().getGlBlue()
         );
         effect.setUniformValue(
            "ffirst",
            shaders.fillColor1.getValue().getGlRed(),
            shaders.fillColor1.getValue().getGlGreen(),
            shaders.fillColor1.getValue().getGlBlue(),
            shaders.fillColor1.getValue().getGlAlpha()
         );
         effect.setUniformValue(
            "fsecond", shaders.fillColor2.getValue().getGlRed(), shaders.fillColor2.getValue().getGlGreen(), shaders.fillColor2.getValue().getGlBlue()
         );
         effect.setUniformValue(
            "fthird", shaders.fillColor3.getValue().getGlRed(), shaders.fillColor3.getValue().getGlGreen(), shaders.fillColor3.getValue().getGlBlue()
         );
         effect.setUniformValue("oct", shaders.octaves.getValue());
         effect.setUniformValue("resolution", mc.method_22683().method_4486(), mc.method_22683().method_4502());
         effect.setUniformValue("time", this.time);
         effect.render(Render3DEngine.getTickDelta());
         this.time += 0.008F;
      } else if (shader == ShaderManager.Shader.Default) {
         effect.setUniformValue("alpha0", shaders.glow.getValue() ? -1.0F : shaders.outlineColor.getValue().getAlpha() / 255.0F);
         effect.setUniformValue("lineWidth", shaders.lineWidth.getValue());
         effect.setUniformValue("quality", shaders.quality.getValue());
         effect.setUniformValue(
            "color",
            shaders.fillColor1.getValue().getGlRed(),
            shaders.fillColor1.getValue().getGlGreen(),
            shaders.fillColor1.getValue().getGlBlue(),
            shaders.fillColor1.getValue().getGlAlpha()
         );
         effect.setUniformValue(
            "outlinecolor",
            shaders.outlineColor.getValue().getGlRed(),
            shaders.outlineColor.getValue().getGlGreen(),
            shaders.outlineColor.getValue().getGlBlue(),
            shaders.outlineColor.getValue().getGlAlpha()
         );
         effect.render(Render3DEngine.getTickDelta());
      } else if (shader == ShaderManager.Shader.Snow) {
         effect.setUniformValue(
            "color",
            shaders.fillColor1.getValue().getGlRed(),
            shaders.fillColor1.getValue().getGlGreen(),
            shaders.fillColor1.getValue().getGlBlue(),
            shaders.fillColor1.getValue().getGlAlpha()
         );
         effect.setUniformValue("quality", shaders.quality.getValue());
         effect.setUniformValue("resolution", mc.method_22683().method_4486(), mc.method_22683().method_4502());
         effect.setUniformValue("time", this.time);
         effect.render(Render3DEngine.getTickDelta());
         this.time += 0.008F;
      } else if (shader == ShaderManager.Shader.Fade) {
         effect.setUniformValue("alpha0", shaders.glow.getValue() ? -1.0F : shaders.outlineColor.getValue().getAlpha() / 255.0F);
         effect.setUniformValue("fillAlpha", shaders.fillAlpha.getValue().intValue() / 255.0F);
         effect.setUniformValue("lineWidth", shaders.lineWidth.getValue());
         effect.setUniformValue("quality", shaders.quality.getValue());
         effect.setUniformValue(
            "outlinecolor",
            shaders.outlineColor.getValue().getGlRed(),
            shaders.outlineColor.getValue().getGlGreen(),
            shaders.outlineColor.getValue().getGlBlue(),
            shaders.outlineColor.getValue().getGlAlpha()
         );
         effect.setUniformValue(
            "primaryColor",
            shaders.fillColor1.getValue().getGlRed(),
            shaders.fillColor1.getValue().getGlGreen(),
            shaders.fillColor1.getValue().getGlBlue(),
            shaders.fillColor1.getValue().getAlpha()
         );
         effect.setUniformValue(
            "secondaryColor",
            shaders.fillColor2.getValue().getGlRed(),
            shaders.fillColor2.getValue().getGlGreen(),
            shaders.fillColor2.getValue().getGlBlue(),
            shaders.fillColor1.getValue().getAlpha()
         );
         effect.setUniformValue("time", (float)(System.currentTimeMillis() % 100000L) / 1000.0F);
         effect.render(Render3DEngine.getTickDelta());
      }
   }

   public void reloadShaders() {
      DEFAULT = ShaderEffectManager.getInstance().manage(class_2960.method_60655("unique", "shaders/post/outline.json"));
      SMOKE = ShaderEffectManager.getInstance().manage(class_2960.method_60655("unique", "shaders/post/smoke.json"));
      GRADIENT = ShaderEffectManager.getInstance().manage(class_2960.method_60655("unique", "shaders/post/gradient.json"));
      SNOW = ShaderEffectManager.getInstance().manage(class_2960.method_60655("unique", "shaders/post/snow.json"));
      FADE = ShaderEffectManager.getInstance().manage(class_2960.method_60655("unique", "shaders/post/fade.json"));
      FADE_OUTLINE = ShaderEffectManager.getInstance().manage(class_2960.method_60655("unique", "shaders/post/fade.json"), managedShaderEffect -> {
         class_279 effect = managedShaderEffect.getShaderEffect();
         if (effect != null) {
            ((IShaderEffect)effect).addFakeTargetHook("bufIn", mc.field_1769.method_22990());
            ((IShaderEffect)effect).addFakeTargetHook("bufOut", mc.field_1769.method_22990());
         }
      });
      DEFAULT_OUTLINE = ShaderEffectManager.getInstance().manage(class_2960.method_60655("unique", "shaders/post/outline.json"), managedShaderEffect -> {
         class_279 effect = managedShaderEffect.getShaderEffect();
         if (effect != null) {
            ((IShaderEffect)effect).addFakeTargetHook("bufIn", mc.field_1769.method_22990());
            ((IShaderEffect)effect).addFakeTargetHook("bufOut", mc.field_1769.method_22990());
         }
      });
      SMOKE_OUTLINE = ShaderEffectManager.getInstance().manage(class_2960.method_60655("unique", "shaders/post/smoke.json"), managedShaderEffect -> {
         class_279 effect = managedShaderEffect.getShaderEffect();
         if (effect != null) {
            ((IShaderEffect)effect).addFakeTargetHook("bufIn", mc.field_1769.method_22990());
            ((IShaderEffect)effect).addFakeTargetHook("bufOut", mc.field_1769.method_22990());
         }
      });
      GRADIENT_OUTLINE = ShaderEffectManager.getInstance().manage(class_2960.method_60655("unique", "shaders/post/gradient.json"), managedShaderEffect -> {
         class_279 effect = managedShaderEffect.getShaderEffect();
         if (effect != null) {
            ((IShaderEffect)effect).addFakeTargetHook("bufIn", mc.field_1769.method_22990());
            ((IShaderEffect)effect).addFakeTargetHook("bufOut", mc.field_1769.method_22990());
         }
      });
      SNOW_OUTLINE = ShaderEffectManager.getInstance().manage(class_2960.method_60655("unique", "shaders/post/snow.json"), managedShaderEffect -> {
         class_279 effect = managedShaderEffect.getShaderEffect();
         if (effect != null) {
            ((IShaderEffect)effect).addFakeTargetHook("bufIn", mc.field_1769.method_22990());
            ((IShaderEffect)effect).addFakeTargetHook("bufOut", mc.field_1769.method_22990());
         }
      });
   }

   public boolean fullNullCheck() {
      if (GRADIENT != null && SMOKE != null && DEFAULT != null) {
         return false;
      } else {
         this.shaderBuffer = new ShaderManager.ThunderHackFramebuffer(mc.method_1522().field_1482, mc.method_1522().field_1481);
         this.reloadShaders();
         return true;
      }
   }

   public record RenderTask(Runnable task, ShaderManager.Shader shader) {
   }

   public static enum Shader {
      Default,
      Smoke,
      Gradient,
      Snow,
      Fade;
   }

   public static class ThunderHackFramebuffer extends class_276 {
      public ThunderHackFramebuffer(int width, int height) {
         super(false);
         RenderSystem.assertOnRenderThreadOrInit();
         this.method_1234(width, height, true);
         this.method_1236(0.0F, 0.0F, 0.0F, 0.0F);
      }
   }
}
