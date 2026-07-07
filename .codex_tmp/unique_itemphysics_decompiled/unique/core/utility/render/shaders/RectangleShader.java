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

public class RectangleShader {
   private Uniform2f uSize;
   private Uniform2f uLocation;
   private Uniform1f radius;
   private Uniform4f color1;
   private Uniform4f color2;
   private Uniform4f color3;
   private Uniform4f color4;
   public static final ManagedCoreShader RECTANGLE_SHADER = ShaderEffectManager.getInstance()
      .manageCoreShader(class_2960.method_60655("unique", "rectangle"), class_290.field_1592);

   public RectangleShader() {
      this.setup();
   }

   public void setParameters(float x, float y, float width, float height, float r, float alpha) {
      float i = (float)Module.mc.method_22683().method_4495();
      this.radius.set(r * i);
      this.uLocation.set(x * i, -y * i + Module.mc.method_22683().method_4502() * i - height * i);
      this.uSize.set(width * i, height * i);
      Color c1 = HudEditor.getColor(270);
      Color c2 = HudEditor.getColor(0);
      Color c3 = HudEditor.getColor(180);
      Color c4 = HudEditor.getColor(90);
      this.color1.set(c1.getRed() / 255.0F, c1.getGreen() / 255.0F, c1.getBlue() / 255.0F, alpha);
      this.color2.set(c2.getRed() / 255.0F, c2.getGreen() / 255.0F, c2.getBlue() / 255.0F, alpha);
      this.color3.set(c3.getRed() / 255.0F, c3.getGreen() / 255.0F, c3.getBlue() / 255.0F, alpha);
      this.color4.set(c4.getRed() / 255.0F, c4.getGreen() / 255.0F, c4.getBlue() / 255.0F, alpha);
   }

   public void setParameters(float x, float y, float width, float height, float r, float alpha, Color c1, Color c2, Color c3, Color c4) {
      int i = (Integer)Module.mc.field_1690.method_42474().method_41753();
      this.radius.set(r * i);
      this.uLocation.set(x * i, -y * i + Module.mc.method_22683().method_4502() * i - height * i);
      this.uSize.set(width * i, height * i);
      this.color1.set(c1.getRed() / 255.0F, c1.getGreen() / 255.0F, c1.getBlue() / 255.0F, alpha);
      this.color2.set(c2.getRed() / 255.0F, c2.getGreen() / 255.0F, c2.getBlue() / 255.0F, alpha);
      this.color3.set(c3.getRed() / 255.0F, c3.getGreen() / 255.0F, c3.getBlue() / 255.0F, alpha);
      this.color4.set(c4.getRed() / 255.0F, c4.getGreen() / 255.0F, c4.getBlue() / 255.0F, alpha);
   }

   public void use() {
      RenderSystem.setShader(RECTANGLE_SHADER::getProgram);
   }

   protected void setup() {
      this.uSize = RECTANGLE_SHADER.findUniform2f("uSize");
      this.uLocation = RECTANGLE_SHADER.findUniform2f("uLocation");
      this.radius = RECTANGLE_SHADER.findUniform1f("radius");
      this.color1 = RECTANGLE_SHADER.findUniform4f("color1");
      this.color2 = RECTANGLE_SHADER.findUniform4f("color2");
      this.color3 = RECTANGLE_SHADER.findUniform4f("color3");
      this.color4 = RECTANGLE_SHADER.findUniform4f("color4");
   }
}
