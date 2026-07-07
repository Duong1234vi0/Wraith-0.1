package unique.core.injection;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_1297;
import net.minecraft.class_4184;
import net.minecraft.class_758;
import net.minecraft.class_758.class_4596;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.modules.render.WorldTweaks;

@Mixin({class_758.class})
public class MixinBackgroundRenderer {
   @Inject(
      method = {"applyFog"},
      at = {@At("TAIL")}
   )
   private static void onApplyFog(class_4184 camera, class_4596 fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo info) {
      if (ModuleManager.noRender.isEnabled() && ModuleManager.noRender.fog.getValue() && fogType == class_4596.field_20946) {
         RenderSystem.setShaderFogStart(viewDistance * 4.0F);
         RenderSystem.setShaderFogEnd(viewDistance * 4.25F);
      }

      if (ModuleManager.worldTweaks.isEnabled() && WorldTweaks.fogModify.getValue().isEnabled()) {
         RenderSystem.setShaderFogStart(WorldTweaks.fogStart.getValue().intValue());
         RenderSystem.setShaderFogEnd(WorldTweaks.fogEnd.getValue().intValue());
         RenderSystem.setShaderFogColor(
            WorldTweaks.fogColor.getValue().getGlRed(), WorldTweaks.fogColor.getValue().getGlGreen(), WorldTweaks.fogColor.getValue().getGlBlue()
         );
      }
   }

   @Inject(
      method = {"getFogModifier(Lnet/minecraft/entity/Entity;F)Lnet/minecraft/client/render/BackgroundRenderer$StatusEffectFogModifier;"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void onGetFogModifier(class_1297 entity, float tickDelta, CallbackInfoReturnable<Object> info) {
      if (ModuleManager.noRender.isEnabled() && ModuleManager.noRender.blindness.getValue()) {
         info.setReturnValue(null);
      }
   }
}
