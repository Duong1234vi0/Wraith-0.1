package ua.babka.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ua.babka.event.EventEntityHitBox;

@Mixin(value={Entity.class})
public class EntityHitBoxMixin {
    @Inject(method={"getTargetingMargin"}, at={@At(value="RETURN")}, cancellable=true)
    private void onGetTargetingMargin(CallbackInfoReturnable<Float> cir) {
        Entity entity = (Entity)this;
        EventEntityHitBox event = new EventEntityHitBox(entity, 1.0f);
        event.post();
        if (event.getSize() != 1.0f) {
            cir.setReturnValue((Object)Float.valueOf(event.getSize()));
        }
    }
}

