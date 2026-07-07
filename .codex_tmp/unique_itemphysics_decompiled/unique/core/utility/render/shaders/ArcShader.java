package unique.core.utility.render.shaders;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import unique.core.features.modules.Module;
import unique.core.utility.render.shaders.satin.api.managed.ManagedCoreShader;
import unique.core.utility.render.shaders.satin.api.managed.ShaderEffectManager;
import unique.core.utility.render.shaders.satin.api.managed.uniform.Uniform1f;
import unique.core.utility.render.shaders.satin.api.managed.uniform.Uniform2f;
import unique.core.utility.render.shaders.satin.api.managed.uniform.Uniform4f;

public class ArcShader {
   private Uniform2f uLocation;
   private Uniform2f uSize;
   private Uniform1f radius;
   private Uniform1f thickness;
   private Uniform1f time;
   private Uniform4f color1;
   private Uniform4f color2;
   private Uniform1f start;
   private Uniform1f end;
   public static final ManagedCoreShader ARC = ShaderEffectManager.getInstance()
      .manageCoreShader(class_2960.method_60655("unique", "arc"), class_290.field_1592);

   public ArcShader() {
      this.setup();
   }

   public void setParameters(float x, float y, float width, float height, float r, float thickness, float start, float end, Color c1, Color c2) {
      if (Module.mc.field_1724 != null) {
         float i = (float)Module.mc.method_22683().method_4495();
         this.radius.set(r * i);
         this.uLocation.set(x * i, -y * i + Module.mc.method_22683().method_4502() * i - height * i);
         this.uSize.set(width * i, height * i);
         this.color1.set(c1.getRed() / 255.0F, c1.getGreen() / 255.0F, c1.getBlue() / 255.0F, 1.0F);
         this.color2.set(c2.getRed() / 255.0F, c2.getGreen() / 255.0F, c2.getBlue() / 255.0F, 1.0F);
         this.time.set(Module.mc.field_1724.field_6012 * 4.0F);
         this.thickness.set(thickness);
         this.start.set(start);
         this.end.set(end);
      }
   }

   public void use() {
      RenderSystem.setShader(ARC::getProgram);
   }

   protected void setup() {
      this.uSize = ARC.findUniform2f("uSize");
      this.uLocation = ARC.findUniform2f("uLocation");
      this.radius = ARC.findUniform1f("radius");
      this.thickness = ARC.findUniform1f("thickness");
      this.start = ARC.findUniform1f("start");
      this.end = ARC.findUniform1f("end");
      this.time = ARC.findUniform1f("time");
      this.color1 = ARC.findUniform4f("color1");
      this.color2 = ARC.findUniform4f("color2");
   }
}
