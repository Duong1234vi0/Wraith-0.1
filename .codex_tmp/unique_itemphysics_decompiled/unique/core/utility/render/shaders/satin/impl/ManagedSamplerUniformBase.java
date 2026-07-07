package unique.core.utility.render.shaders.satin.impl;

import com.mojang.logging.LogUtils;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_280;
import net.minecraft.class_283;
import net.minecraft.class_5944;
import unique.core.utility.render.shaders.satin.api.managed.uniform.SamplerUniform;

public abstract class ManagedSamplerUniformBase extends ManagedUniformBase implements SamplerUniform {
   protected SamplerAccess[] targets = new SamplerAccess[0];
   protected int[] locations = new int[0];
   protected Object cachedValue;

   public ManagedSamplerUniformBase(String name) {
      super(name);
   }

   @Override
   public boolean findUniformTargets(List<class_283> shaders) {
      List<SamplerAccess> targets = new ArrayList<>(shaders.size());
      IntList rawTargets = new IntArrayList(shaders.size());

      for (class_283 shader : shaders) {
         class_280 program = shader.method_1295();
         SamplerAccess access = (SamplerAccess)program;
         if (access.hasSampler(this.name)) {
            targets.add(access);
            rawTargets.add(this.getSamplerLoc(access));
         }
      }

      this.targets = targets.toArray(new SamplerAccess[0]);
      this.locations = rawTargets.toArray(new int[0]);
      this.syncCurrentValues();
      return this.targets.length > 0;
   }

   private int getSamplerLoc(SamplerAccess access) {
      return access.getSamplerShaderLocs().get(access.getSamplerNames().indexOf(this.name));
   }

   @Override
   public boolean findUniformTarget(class_5944 shader) {
      LogUtils.getLogger().warn(shader.method_35787());
      return this.findUniformTarget1((SamplerAccess)shader);
   }

   private boolean findUniformTarget1(SamplerAccess access) {
      if (access.hasSampler(this.name)) {
         this.targets = new SamplerAccess[]{access};
         this.locations = new int[]{this.getSamplerLoc(access)};
         this.syncCurrentValues();
         return true;
      } else {
         return false;
      }
   }

   private void syncCurrentValues() {
      Object value = this.cachedValue;
      if (value != null) {
         this.cachedValue = null;
         this.set(value);
      }
   }

   protected abstract void set(Object var1);
}
