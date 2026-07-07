package unique.core.injection;

import net.minecraft.class_1309;
import net.minecraft.class_1671;
import net.minecraft.class_243;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import unique.core.core.Managers;
import unique.core.core.manager.IManager;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.modules.combat.Aura;

@Mixin({class_1671.class})
public class MixinFireWorkEntity {
   @Shadow
   private class_1309 field_7616;

   @Redirect(
      method = {"tick"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/entity/LivingEntity;getRotationVector()Lnet/minecraft/util/math/Vec3d;"
      )
   )
   private class_243 tickHook(class_1309 instance) {
      return ModuleManager.aura.isEnabled()
            && ModuleManager.aura.rotationMode.not(Aura.Mode.None)
            && Aura.target != null
            && this.field_7616 == IManager.mc.field_1724
            && ModuleManager.aura.elytraTarget.getValue()
         ? Managers.PLAYER.getRotationVector(ModuleManager.aura.rotationPitch, ModuleManager.aura.rotationYaw)
         : this.field_7616.method_5720();
   }
}
