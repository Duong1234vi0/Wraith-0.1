package unique.core.injection;

import net.minecraft.class_1297;
import net.minecraft.class_1313;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_4174;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import unique.core.UniqueCore;
import unique.core.core.manager.client.ModuleManager;
import unique.core.events.impl.EventAttack;
import unique.core.events.impl.EventEatFood;
import unique.core.events.impl.EventPlayerJump;
import unique.core.events.impl.EventPlayerTravel;
import unique.core.features.modules.Module;
import unique.core.features.modules.movement.AutoSprint;
import unique.core.features.modules.movement.Speed;

@Mixin(
   value = {class_1657.class},
   priority = 800
)
public class MixinPlayerEntity {
   @Inject(
      method = {"getAttackCooldownProgressPerTick"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void getAttackCooldownProgressPerTickHook(CallbackInfoReturnable<Float> cir) {
      if (ModuleManager.aura.isEnabled()) {
         cir.setReturnValue(12.5F);
      }
   }

   @Inject(
      method = {"getDisplayName"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void getDisplayNameHook(CallbackInfoReturnable<class_2561> cir) {
   }

   @Inject(
      method = {"attack"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/entity/player/PlayerEntity;setSprinting(Z)V",
         shift = Shift.AFTER
      )}
   )
   public void attackAHook(CallbackInfo callbackInfo) {
      if (ModuleManager.autoSprint.isEnabled() && AutoSprint.sprint.getValue()) {
         float multiplier = 0.6F + 0.4F * AutoSprint.motion.getValue();
         Module.mc
            .field_1724
            .method_18800(
               Module.mc.field_1724.method_18798().field_1352 / 0.6 * multiplier,
               Module.mc.field_1724.method_18798().field_1351,
               Module.mc.field_1724.method_18798().field_1350 / 0.6 * multiplier
            );
         Module.mc.field_1724.method_5728(true);
      }
   }

   @Inject(
      method = {"getMovementSpeed"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void getMovementSpeedHook(CallbackInfoReturnable<Float> cir) {
      if (ModuleManager.speed.isEnabled() && ModuleManager.speed.mode.is(Speed.Mode.Vanilla)) {
         cir.setReturnValue(ModuleManager.speed.boostFactor.getValue());
      }
   }

   @Inject(
      method = {"attack"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void attackAHook2(class_1297 target, CallbackInfo ci) {
      EventAttack event = new EventAttack(target, false);
      UniqueCore.EVENT_BUS.post(event);
      if (event.isCancelled()) {
         ci.cancel();
      }
   }

   @Inject(
      method = {"travel"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onTravelhookPre(class_243 movementInput, CallbackInfo ci) {
      if (Module.mc.field_1724 != null) {
         EventPlayerTravel event = new EventPlayerTravel(movementInput, true);
         UniqueCore.EVENT_BUS.post(event);
         if (event.isCancelled()) {
            Module.mc.field_1724.method_5784(class_1313.field_6308, Module.mc.field_1724.method_18798());
            ci.cancel();
         }
      }
   }

   @Inject(
      method = {"travel"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void onTravelhookPost(class_243 movementInput, CallbackInfo ci) {
      if (Module.mc.field_1724 != null) {
         EventPlayerTravel event = new EventPlayerTravel(movementInput, false);
         UniqueCore.EVENT_BUS.post(event);
         if (event.isCancelled()) {
            Module.mc.field_1724.method_5784(class_1313.field_6308, Module.mc.field_1724.method_18798());
            ci.cancel();
         }
      }
   }

   @Inject(
      method = {"jump"},
      at = {@At("HEAD")}
   )
   private void onJumpPre(CallbackInfo ci) {
      UniqueCore.EVENT_BUS.post(new EventPlayerJump(true));
   }

   @Inject(
      method = {"jump"},
      at = {@At("RETURN")}
   )
   private void onJumpPost(CallbackInfo ci) {
      UniqueCore.EVENT_BUS.post(new EventPlayerJump(false));
   }

   @Inject(
      method = {"eatFood"},
      at = {@At("RETURN")}
   )
   public void eatFoodHook(class_1937 world, class_1799 stack, class_4174 foodComponent, CallbackInfoReturnable<class_1799> cir) {
      UniqueCore.EVENT_BUS.post(new EventEatFood((class_1799)cir.getReturnValue()));
   }

   @Inject(
      method = {"shouldDismount"},
      at = {@At("HEAD")},
      cancellable = true
   )
   protected void shouldDismountHook(CallbackInfoReturnable<Boolean> cir) {
      if (ModuleManager.boatFly.isEnabled() && ModuleManager.boatFly.allowShift.getValue()) {
         cir.setReturnValue(false);
      }
   }

   @Inject(
      method = {"getBlockInteractionRange"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void getBlockInteractionRangeHook(CallbackInfoReturnable<Double> cir) {
      if (ModuleManager.reach.isEnabled()) {
         if (ModuleManager.reach.Creative.getValue() && Module.mc.field_1724.method_7337()) {
            cir.setReturnValue((double)ModuleManager.reach.creativeBlocksRange.getValue().floatValue());
         } else {
            cir.setReturnValue((double)ModuleManager.reach.blocksRange.getValue().floatValue());
         }
      }
   }

   @Inject(
      method = {"getEntityInteractionRange"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void getEntityInteractionRangeHook(CallbackInfoReturnable<Double> cir) {
      if (ModuleManager.reach.isEnabled()) {
         if (ModuleManager.reach.Creative.getValue() && Module.mc.field_1724.method_7337()) {
            cir.setReturnValue((double)ModuleManager.reach.creativeEntityRange.getValue().floatValue());
         } else {
            cir.setReturnValue((double)ModuleManager.reach.entityRange.getValue().floatValue());
         }
      }
   }
}
