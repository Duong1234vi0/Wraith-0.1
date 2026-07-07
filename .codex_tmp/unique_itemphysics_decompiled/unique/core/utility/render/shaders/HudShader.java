package unique.core.utility.render.shaders;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.HudEditor;
import unique.core.utility.render.shaders.satin.api.managed.ManagedCoreShader;
import unique.core.utility.render.shaders.satin.api.managed.ShaderEffectManager;
import unique.core.utility.render.shaders.satin.api.managed.uniform.Uniform1f;
import unique.core.utility.render.shaders.satin.api.managed.uniform.Uniform2f;
import unique.core.utility.render.shaders.satin.api.managed.uniform.Uniform4f;

public class HudShader {
   private Uniform2f uSize;
   private Uniform2f uLocation;
   private Uniform1f radius;
   private Uniform1f blend;
   private Uniform1f alpha;
   private Uniform1f outline;
   private Uniform1f glow;
   private Uniform4f color1;
   private Uniform4f color2;
   private Uniform4f color3;
   private Uniform4f color4;
   public static final ManagedCoreShader HUD_SHADER = ShaderEffectManager.getInstance()
      .manageCoreShader(class_2960.method_60655("unique", "hudshader"), class_290.field_1592);

   public HudShader() {
      this.setup();
   }

   public void setParameters(float x, float y, float width, float height, float r, float externalAlpha, float internalAlpha) {
      float i = (float)Module.mc.method_22683().method_4495();
      this.radius.set(r * i);
      this.uLocation.set(x * i, -y * i + Module.mc.method_22683().method_4502() * i - height * i);
      this.uSize.set(width * i, height * i);
      Color c1 = HudEditor.getColor(270);
      Color c2 = HudEditor.getColor(0);
      Color c3 = HudEditor.getColor(180);
      Color c4 = HudEditor.getColor(90);
      this.color1.set(c1.getRed() / 255.0F, c1.getGreen() / 255.0F, c1.getBlue() / 255.0F, externalAlpha);
      this.color2.set(c2.getRed() / 255.0F, c2.getGreen() / 255.0F, c2.getBlue() / 255.0F, externalAlpha);
      this.color3.set(c3.getRed() / 255.0F, c3.getGreen() / 255.0F, c3.getBlue() / 255.0F, externalAlpha);
      this.color4.set(c4.getRed() / 255.0F, c4.getGreen() / 255.0F, c4.getBlue() / 255.0F, externalAlpha);
      this.blend.set(HudEditor.blend.getValue());
      this.outline.set(HudEditor.outline.getValue());
      this.glow.set(HudEditor.glow1.getValue());
      this.alpha.set(internalAlpha);
   }

   public void use() {
      RenderSystem.setShader(HUD_SHADER::getProgram);
   }

   public void setup() {
      this.uSize = HUD_SHADER.findUniform2f("uSize");
      this.uLocation = HUD_SHADER.findUniform2f("uLocation");
      this.radius = HUD_SHADER.findUniform1f("radius");
      this.blend = HUD_SHADER.findUniform1f("blend");
      this.alpha = HUD_SHADER.findUniform1f("alpha");
      this.color1 = HUD_SHADER.findUniform4f("color1");
      this.color2 = HUD_SHADER.findUniform4f("color2");
      this.color3 = HUD_SHADER.findUniform4f("color3");
      this.color4 = HUD_SHADER.findUniform4f("color4");
      this.outline = HUD_SHADER.findUniform1f("outline");
      this.glow = HUD_SHADER.findUniform1f("glow");
   }
}
