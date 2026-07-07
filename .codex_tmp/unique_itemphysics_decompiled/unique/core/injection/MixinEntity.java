package unique.core.injection;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import unique.core.UniqueCore;
import unique.core.core.manager.client.ModuleManager;
import unique.core.events.impl.EventFixVelocity;
import unique.core.features.modules.Module;
import unique.core.features.modules.combat.HitBox;
import unique.core.features.modules.render.Shaders;
import unique.core.features.modules.render.Trails;
import unique.core.utility.interfaces.IEntity;

@Mixin({class_1297.class})
public abstract class MixinEntity implements IEntity {
   @Shadow
   private class_238 field_6005;
   @Unique
   public List<Trails.Trail> trails = new ArrayList<>();

   @Shadow
   protected abstract class_2338 method_23314();

   @Override
   public List<Trails.Trail> getTrails() {
      return this.trails;
   }

   @Override
   public class_2338 thunderHack_Recode$getVelocityBP() {
      return this.method_23314();
   }

   @ModifyArgs(
      method = {"pushAwayFrom"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/entity/Entity;addVelocity(DDD)V"
      )
   )
   public void pushAwayFromHook(Args args) {
      if (this == Module.mc.field_1724 && ModuleManager.noPush.isEnabled() && ModuleManager.noPush.players.getValue()) {
         args.set(0, 0.0);
         args.set(1, 0.0);
         args.set(2, 0.0);
      }
   }

   @Inject(
      method = {"updateVelocity"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void updateVelocityHook(float speed, class_243 movementInput, CallbackInfo ci) {
      if (!Module.fullNullCheck()) {
         if (this == Module.mc.field_1724) {
            ci.cancel();
            EventFixVelocity event = new EventFixVelocity(
               movementInput, speed, Module.mc.field_1724.method_36454(), movementInputToVelocityC(movementInput, speed, Module.mc.field_1724.method_36454())
            );
            UniqueCore.EVENT_BUS.post(event);
            Module.mc.field_1724.method_18799(Module.mc.field_1724.method_18798().method_1019(event.getVelocity()));
         }
      }
   }

   @Unique
   private static class_243 movementInputToVelocityC(class_243 movementInput, float speed, float yaw) {
      double d = movementInput.method_1027();
      if (d < 1.0E-7) {
         return class_243.field_1353;
      } else {
         class_243 vec3d = (d > 1.0 ? movementInput.method_1029() : movementInput).method_1021(speed);
         float f = class_3532.method_15374(yaw * (float) (Math.PI / 180.0));
         float g = class_3532.method_15362(yaw * (float) (Math.PI / 180.0));
         return new class_243(vec3d.field_1352 * g - vec3d.field_1350 * f, vec3d.field_1351, vec3d.field_1350 * g + vec3d.field_1352 * f);
      }
   }

   @Inject(
      method = {"getBoundingBox"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public final void getBoundingBox(CallbackInfoReturnable<class_238> cir) {
      if (ModuleManager.hitBox.isEnabled()
         && Module.mc != null
         && Module.mc.field_1724 != null
         && ((class_1297)this).method_5628() != Module.mc.field_1724.method_5628()
         && (ModuleManager.aura.isDisabled() || HitBox.affectToAura.getValue())) {
         cir.setReturnValue(
            new class_238(
               this.field_6005.field_1323 - HitBox.XZExpand.getValue() / 2.0F,
               this.field_6005.field_1322 - HitBox.YExpand.getValue() / 2.0F,
               this.field_6005.field_1321 - HitBox.XZExpand.getValue() / 2.0F,
               this.field_6005.field_1320 + HitBox.XZExpand.getValue() / 2.0F,
               this.field_6005.field_1325 + HitBox.YExpand.getValue() / 2.0F,
               this.field_6005.field_1324 + HitBox.XZExpand.getValue() / 2.0F
            )
         );
      }
   }

   @Inject(
      method = {"isGlowing"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void isGlowingHook(CallbackInfoReturnable<Boolean> cir) {
      Shaders shaders = ModuleManager.shaders;
      if (shaders.isEnabled()) {
         cir.setReturnValue(shaders.shouldRender((class_1297)this));
      }
   }

   @Inject(
      method = {"isOnFire"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void isOnFireHook(CallbackInfoReturnable<Boolean> cir) {
      if (ModuleManager.noRender.isEnabled() && ModuleManager.noRender.fireEntity.getValue()) {
         cir.setReturnValue(false);
      }
   }

   @Inject(
      method = {"isInvisibleTo"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void isInvisibleToHook(class_1657 player, CallbackInfoReturnable<Boolean> cir) {
      if (ModuleManager.serverHelper.isEnabled() && ModuleManager.serverHelper.trueSight.getValue()) {
         cir.setReturnValue(false);
      }
   }

   @Inject(
      method = {"isInLava"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void isInLavaHook(CallbackInfoReturnable<Boolean> cir) {
   }

   @Inject(
      method = {"isTouchingWater"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void isTouchingWaterHook(CallbackInfoReturnable<Boolean> cir) {
   }

   @Inject(
      method = {"setSwimming"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void setSwimmingHook(boolean swimming, CallbackInfo ci) {
   }
}
