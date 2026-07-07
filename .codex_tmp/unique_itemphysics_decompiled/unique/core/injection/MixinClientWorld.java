package unique.core.injection;

import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3414;
import net.minecraft.class_3419;
import net.minecraft.class_638;
import net.minecraft.class_1297.class_5529;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import unique.core.UniqueCore;
import unique.core.core.manager.client.ModuleManager;
import unique.core.events.impl.EventEntityRemoved;
import unique.core.events.impl.EventEntitySpawn;
import unique.core.events.impl.EventEntitySpawnPost;
import unique.core.features.modules.Module;
import unique.core.features.modules.render.WorldTweaks;
import unique.core.setting.impl.ColorSetting;

@Mixin({class_638.class})
public class MixinClientWorld {
   @Inject(
      method = {"addEntity"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void addEntityHook(class_1297 entity, CallbackInfo ci) {
      if (!Module.fullNullCheck()) {
         EventEntitySpawn ees = new EventEntitySpawn(entity);
         UniqueCore.EVENT_BUS.post(ees);
         if (ees.isCancelled()) {
            ci.cancel();
         }
      }
   }

   @Inject(
      method = {"addEntity"},
      at = {@At("RETURN")},
      cancellable = true
   )
   public void addEntityHookPost(class_1297 entity, CallbackInfo ci) {
      if (!Module.fullNullCheck()) {
         EventEntitySpawnPost ees = new EventEntitySpawnPost(entity);
         UniqueCore.EVENT_BUS.post(ees);
         if (ees.isCancelled()) {
            ci.cancel();
         }
      }
   }

   @Inject(
      method = {"removeEntity"},
      at = {@At("HEAD")}
   )
   public void removeEntityHook(int entityId, class_5529 removalReason, CallbackInfo ci) {
      if (!Module.fullNullCheck()) {
         EventEntityRemoved eer = new EventEntityRemoved(Module.mc.field_1687.method_8469(entityId));
         UniqueCore.EVENT_BUS.post(eer);
      }
   }

   @Inject(
      method = {"getSkyColor"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void getSkyColorHook(class_243 cameraPos, float tickDelta, CallbackInfoReturnable<class_243> cir) {
      if (ModuleManager.worldTweaks.isEnabled() && WorldTweaks.fogModify.getValue().isEnabled()) {
         ColorSetting c = WorldTweaks.fogColor.getValue();
         cir.setReturnValue(new class_243(c.getGlRed(), c.getGlGreen(), c.getGlBlue()));
      }
   }

   @Inject(
      method = {"playSound(DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FFZJ)V"},
      at = {@At("HEAD")}
   )
   private void playSoundHoof(
      double x, double y, double z, class_3414 event, class_3419 category, float volume, float pitch, boolean useDistance, long seed, CallbackInfo ci
   ) {
      if (ModuleManager.soundESP.isEnabled()) {
         ModuleManager.soundESP.add(x, y, z, event.method_14833().method_42094());
      }
   }
}
