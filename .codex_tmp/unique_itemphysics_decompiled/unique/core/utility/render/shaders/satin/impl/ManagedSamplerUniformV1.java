package unique.core.utility.render.shaders.satin.impl;

import net.minecraft.class_1044;
import net.minecraft.class_276;
import net.minecraft.class_5944;

public final class ManagedSamplerUniformV1 extends ManagedSamplerUniformBase {
   public ManagedSamplerUniformV1(String name) {
      super(name);
   }

   @Override
   public void set(class_1044 texture) {
      this.set((Object)texture);
   }

   @Override
   public void set(class_276 textureFbo) {
      this.set((Object)textureFbo);
   }

   @Override
   public void set(int textureName) {
      this.set(Integer.valueOf(textureName));
   }

   @Override
   protected void set(Object value) {
      SamplerAccess[] targets = this.targets;
      if (targets.length > 0 && this.cachedValue != value) {
         for (SamplerAccess target : targets) {
            ((class_5944)target).method_34583(this.name, value);
         }

         this.cachedValue = value;
      }
   }
}
