package unique.core.utility.render.shaders.satin.impl;

import java.util.function.IntSupplier;
import net.minecraft.class_1044;
import net.minecraft.class_276;
import net.minecraft.class_280;
import unique.core.utility.render.shaders.satin.api.managed.uniform.SamplerUniformV2;

public final class ManagedSamplerUniformV2 extends ManagedSamplerUniformBase implements SamplerUniformV2 {
   public ManagedSamplerUniformV2(String name) {
      super(name);
   }

   @Override
   public void set(class_1044 texture) {
      this.set(texture::method_4624);
   }

   @Override
   public void set(class_276 textureFbo) {
      this.set(textureFbo::method_30277);
   }

   @Override
   public void set(int textureName) {
      this.set((IntSupplier)(() -> textureName));
   }

   @Override
   protected void set(Object value) {
      this.set((IntSupplier)value);
   }

   @Override
   public void set(IntSupplier value) {
      SamplerAccess[] targets = this.targets;
      if (targets.length > 0 && this.cachedValue != value) {
         for (SamplerAccess target : targets) {
            ((class_280)target).method_1269(this.name, value);
         }

         this.cachedValue = value;
      }
   }
}
