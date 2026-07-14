package ua.babka.mixin;

import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ua.babka.Babka;
import ua.babka.module.impl.render.Ambience;

@Mixin(value={ClientWorld.Properties.class})
public class WorldMixin {
    @Inject(method={"getTimeOfDay"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetTimeOfDay(CallbackInfoReturnable<Long> cir) {
        try {
            if (Babka.getInstance() == null) {
                return;
            }
            if (Babka.getInstance().getModuleStorage() == null) {
                return;
            }
            Ambience ambience = Babka.getInstance().getModuleStorage().get(Ambience.class);
            if (ambience != null && ambience.isEnabled()) {
                cir.setReturnValue((Object)ambience.getCustomTime());
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}

