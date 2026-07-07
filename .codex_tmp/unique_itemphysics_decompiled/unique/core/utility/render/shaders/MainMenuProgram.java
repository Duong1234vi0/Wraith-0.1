package unique.core.utility.render.shaders;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import unique.core.features.modules.Module;
import unique.core.utility.render.animation.AnimationUtility;
import unique.core.utility.render.shaders.satin.api.managed.ManagedCoreShader;
import unique.core.utility.render.shaders.satin.api.managed.ShaderEffectManager;
import unique.core.utility.render.shaders.satin.api.managed.uniform.Uniform1f;
import unique.core.utility.render.shaders.satin.api.managed.uniform.Uniform2f;
import unique.core.utility.render.shaders.satin.api.managed.uniform.Uniform4f;

public class MainMenuProgram {
   private Uniform1f Time;
   private Uniform2f uSize;
   private Uniform4f color;
   public static float time_ = 10000.0F;
   public static final ManagedCoreShader MAIN_MENU = ShaderEffectManager.getInstance()
      .manageCoreShader(class_2960.method_60655("unique", "mainmenu"), class_290.field_1592);

   public MainMenuProgram() {
      this.setup();
   }

   public void setParameters(float x, float y, float width, float height) {
      float i = (float)Module.mc.method_22683().method_4495();
      this.uSize.set(width * i, height * i);
      time_ = time_ + (float)(0.55 * AnimationUtility.deltaTime());
      this.Time.set(time_);
   }

   public void use() {
      RenderSystem.setShader(MAIN_MENU::getProgram);
   }

   protected void setup() {
      this.uSize = MAIN_MENU.findUniform2f("uSize");
      this.Time = MAIN_MENU.findUniform1f("Time");
      this.color = MAIN_MENU.findUniform4f("color");
   }
}
