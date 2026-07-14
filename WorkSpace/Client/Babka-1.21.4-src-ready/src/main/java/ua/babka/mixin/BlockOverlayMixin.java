package ua.babka.mixin;

import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.babka.module.impl.render.BlockOverlay;
import ua.babka.util.Instance;

@Mixin(value={WorldRenderer.class})
public class BlockOverlayMixin {
    @Inject(method={"drawBlockOutline"}, at={@At(value="HEAD")}, cancellable=true)
    private void onDrawBlockOutline(CallbackInfo ci) {
        BlockOverlay bo = Instance.get(BlockOverlay.class);
        if (bo != null && bo.isEnabled()) {
            ci.cancel();
        }
    }
}

