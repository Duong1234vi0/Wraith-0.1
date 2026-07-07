package unique.core.injection;

import java.util.List;
import java.util.Map;
import net.minecraft.class_5944;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import unique.core.utility.render.shaders.satin.impl.SamplerAccess;

@Mixin({class_5944.class})
public abstract class MixinCoreShader implements SamplerAccess {
   @Shadow
   @Final
   private Map<String, Object> field_29487;

   @Override
   public boolean hasSampler(String name) {
      return this.field_29487.containsKey(name);
   }

   @Accessor("samplerNames")
   @Override
   public abstract List<String> getSamplerNames();

   @Accessor("loadedSamplerIds")
   @Override
   public abstract List<Integer> getSamplerShaderLocs();
}
