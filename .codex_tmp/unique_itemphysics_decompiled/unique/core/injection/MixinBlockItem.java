package unique.core.injection;

import net.minecraft.class_1747;
import net.minecraft.class_1750;
import net.minecraft.class_2680;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import unique.core.UniqueCore;
import unique.core.events.impl.EventPlaceBlock;
import unique.core.features.modules.Module;

@Mixin({class_1747.class})
public class MixinBlockItem {
   @Inject(
      method = {"place(Lnet/minecraft/item/ItemPlacementContext;Lnet/minecraft/block/BlockState;)Z"},
      at = {@At("RETURN")}
   )
   private void onPlace(@NotNull class_1750 context, class_2680 state, CallbackInfoReturnable<Boolean> info) {
      if (!Module.fullNullCheck()) {
         if (context.method_8045().field_9236) {
            UniqueCore.EVENT_BUS.post(new EventPlaceBlock(context.method_8037(), state.method_26204()));
         }
      }
   }
}
