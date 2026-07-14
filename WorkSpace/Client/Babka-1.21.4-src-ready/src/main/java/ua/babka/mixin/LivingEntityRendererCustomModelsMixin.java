package ua.babka.mixin;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import ua.babka.render.model.CustomPlayerModelRenderer;
import ua.babka.render.model.ICustomPlayerModelState;

@Mixin(value={LivingEntityRenderer.class})
public class LivingEntityRendererCustomModelsMixin {
    @Redirect(method={"render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/entity/model/EntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;III)V"))
    private void mre$replacePlayerModel(EntityModel model, MatrixStack matrices, VertexConsumer consumer, int light, int overlay, int color, LivingEntityRenderState state, MatrixStack sourceMatrices, VertexConsumerProvider vertexConsumers, int sourceLight) {
        if (state instanceof PlayerEntityRenderState) {
            ICustomPlayerModelState customState;
            PlayerEntityRenderState playerState = (PlayerEntityRenderState)state;
            if (state instanceof ICustomPlayerModelState && (customState = (ICustomPlayerModelState)state).mre$hasCustomModel() && model instanceof PlayerEntityModel) {
                PlayerEntityModel playerModel = (PlayerEntityModel)model;
                if (CustomPlayerModelRenderer.render(customState.mre$getCustomModel(), playerModel, playerState, matrices, vertexConsumers, light, overlay, color)) {
                    return;
                }
            }
        }
        model.render(matrices, consumer, light, overlay, color);
    }
}

