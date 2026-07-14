package ua.babka.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.babka.module.impl.render.Chams;
import ua.babka.util.Instance;

@Mixin(value={HeadFeatureRenderer.class})
public class HeadFeatureRendererMixin {
    @Inject(method={"render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/state/LivingEntityRenderState;FF)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void babka$hideHead(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i2, LivingEntityRenderState livingEntityRenderState, float f2, float g2, CallbackInfo ci) {
        if (!(livingEntityRenderState instanceof PlayerEntityRenderState)) {
            return;
        }
        PlayerEntityRenderState playerState = (PlayerEntityRenderState)livingEntityRenderState;
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.world == null) {
            return;
        }
        Entity entity = mc.world.getEntityById(playerState.id);
        if (!(entity instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity player = (PlayerEntity)entity;
        Chams chams = Instance.get(Chams.class);
        if (chams != null && chams.shouldHideItemsAndCape(player)) {
            ci.cancel();
        }
    }
}

