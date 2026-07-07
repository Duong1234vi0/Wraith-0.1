package unique.core.utility.render.shaders.satin.impl;

import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import java.util.Set;
import java.util.function.Consumer;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_2960;
import net.minecraft.class_5912;
import unique.core.utility.render.WindowResizeCallback;
import unique.core.utility.render.shaders.satin.api.managed.ManagedCoreShader;
import unique.core.utility.render.shaders.satin.api.managed.ManagedShaderEffect;
import unique.core.utility.render.shaders.satin.api.managed.ShaderEffectManager;

public final class ReloadableShaderEffectManager implements ShaderEffectManager {
   public static final ReloadableShaderEffectManager INSTANCE = new ReloadableShaderEffectManager();
   private final Set<ResettableManagedShaderBase<?>> managedShaders = new ReferenceOpenHashSet();

   public ReloadableShaderEffectManager() {
      WindowResizeCallback.EVENT.register((WindowResizeCallback)(client, window) -> this.onResolutionChanged(window.method_4489(), window.method_4506()));
   }

   @Override
   public ManagedShaderEffect manage(class_2960 location) {
      return this.manage(location, s -> {});
   }

   @Override
   public ManagedShaderEffect manage(class_2960 location, Consumer<ManagedShaderEffect> initCallback) {
      ResettableManagedShaderEffect ret = new ResettableManagedShaderEffect(location, initCallback);
      this.managedShaders.add(ret);
      return ret;
   }

   @Override
   public ManagedCoreShader manageCoreShader(class_2960 location) {
      return this.manageCoreShader(location, class_290.field_1580);
   }

   @Override
   public ManagedCoreShader manageCoreShader(class_2960 location, class_293 vertexFormat) {
      return this.manageCoreShader(location, vertexFormat, s -> {});
   }

   @Override
   public ManagedCoreShader manageCoreShader(class_2960 location, class_293 vertexFormat, Consumer<ManagedCoreShader> initCallback) {
      ResettableManagedCoreShader ret = new ResettableManagedCoreShader(location, vertexFormat, initCallback);
      this.managedShaders.add(ret);
      return ret;
   }

   public void reload(class_5912 shaderResources) {
      for (ResettableManagedShaderBase<?> ss : this.managedShaders) {
         ss.initializeOrLog(shaderResources);
      }
   }

   public void onResolutionChanged(int newWidth, int newHeight) {
      this.runShaderSetup(newWidth, newHeight);
   }

   private void runShaderSetup(int newWidth, int newHeight) {
      if (!this.managedShaders.isEmpty()) {
         for (ResettableManagedShaderBase<?> ss : this.managedShaders) {
            if (ss.isInitialized()) {
               ss.setup(newWidth, newHeight);
            }
         }
      }
   }
}
