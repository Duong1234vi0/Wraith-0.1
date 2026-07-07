package unique.core.injection;

import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_3965;
import net.minecraft.class_636;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import unique.core.UniqueCore;
import unique.core.core.manager.client.ModuleManager;
import unique.core.events.impl.EventAttackBlock;
import unique.core.events.impl.EventBreakBlock;
import unique.core.events.impl.EventClickSlot;
import unique.core.features.modules.Module;
import unique.core.features.modules.player.SpeedMine;

@Mixin({class_636.class})
public class MixinClientPlayerInteractionManager {
   @Shadow
   private int field_3716;

   @Inject(
      method = {"interactBlock"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void interactBlock(class_746 player, class_1268 hand, class_3965 hitResult, CallbackInfoReturnable<class_1269> cir) {
      if (Module.mc.field_1724 != null
         && (
            Module.mc.field_1724.method_6079().method_7909() == class_1802.field_8575 && hand == class_1268.field_5810
               || Module.mc.field_1724.method_6047().method_7909() == class_1802.field_8575 && hand == class_1268.field_5808
         )) {
         cir.setReturnValue(class_1269.field_5811);
      }
   }

   @Redirect(
      method = {"updateBlockBreakingProgress"},
      at = @At(
         value = "FIELD",
         target = "Lnet/minecraft/client/network/ClientPlayerInteractionManager;blockBreakingCooldown:I",
         opcode = 180,
         ordinal = 0
      )
   )
   public int updateBlockBreakingProgressHook(class_636 clientPlayerInteractionManager) {
      return ModuleManager.speedMine.isEnabled() ? 0 : this.field_3716;
   }

   @Inject(
      method = {"updateBlockBreakingProgress"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void updateBlockBreakingProgress(class_2338 pos, class_2350 direction, CallbackInfoReturnable<Boolean> cir) {
      if (ModuleManager.speedMine.isEnabled() && ModuleManager.speedMine.mode.getValue() == SpeedMine.Mode.Packet) {
         cir.setReturnValue(false);
      }
   }

   @Inject(
      method = {"attackBlock"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void attackBlockHook(class_2338 pos, class_2350 direction, CallbackInfoReturnable<Boolean> cir) {
      if (!Module.fullNullCheck()) {
         EventAttackBlock event = new EventAttackBlock(pos, direction);
         UniqueCore.EVENT_BUS.post(event);
         if (event.isCancelled()) {
            cir.setReturnValue(false);
         }
      }
   }

   @Inject(
      method = {"breakBlock"},
      at = {@At("RETURN")},
      cancellable = true
   )
   public void breakBlockHook(class_2338 pos, CallbackInfoReturnable<Boolean> cir) {
      if (!Module.fullNullCheck()) {
         EventBreakBlock event = new EventBreakBlock(pos);
         UniqueCore.EVENT_BUS.post(event);
         if (event.isCancelled()) {
            cir.setReturnValue(false);
         }
      }
   }

   @Inject(
      method = {"clickSlot"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void clickSlotHook(int syncId, int slotId, int button, class_1713 actionType, class_1657 player, CallbackInfo ci) {
      if (!Module.fullNullCheck()) {
         EventClickSlot event = new EventClickSlot(actionType, slotId, button, syncId);
         UniqueCore.EVENT_BUS.post(event);
         if (event.isCancelled()) {
            ci.cancel();
         }
      }
   }
}
