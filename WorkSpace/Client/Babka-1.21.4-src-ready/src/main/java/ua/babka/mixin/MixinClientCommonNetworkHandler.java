package ua.babka.mixin;

import net.minecraft.client.network.ClientCommonNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ClientCommonNetworkHandler.class})
public class MixinClientCommonNetworkHandler {
    @Inject(method={"getBrand"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetBrand(CallbackInfoReturnable<String> cir) {
        cir.setReturnValue((Object)"vanilla");
    }
}

