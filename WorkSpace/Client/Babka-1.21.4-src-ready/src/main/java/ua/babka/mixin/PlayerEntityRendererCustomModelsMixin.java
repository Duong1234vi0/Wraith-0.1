package ua.babka.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.babka.module.impl.render.CustomModels;
import ua.babka.render.model.ICustomPlayerModelState;
import ua.babka.util.Instance;

@Mixin(value={PlayerEntityRenderer.class})
public class PlayerEntityRendererCustomModelsMixin {
    @Inject(method={"updateRenderState(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/client/render/entity/state/PlayerEntityRenderState;F)V"}, at={@At(value="TAIL")})
    private void mre$updateCustomModelState(AbstractClientPlayerEntity player, PlayerEntityRenderState state, float tickProgress, CallbackInfo ci) {
        CustomModels customModels = Instance.get(CustomModels.class);
        boolean enabled = customModels != null && customModels.shouldApplyTo((PlayerEntity)player);
        ((ICustomPlayerModelState)state).mre$setCustomModel(enabled, enabled ? customModels.getModelName() : "", player.getId());
    }
}

