package ua.babka.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.babka.render.model.ICustomPlayerModelState;

@Mixin(value={ArmorFeatureRenderer.class})
public class ArmorFeatureRendererCustomModelsMixin {
    @Inject(method={"render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/state/BipedEntityRenderState;FF)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void mre$hideArmorForCustomModels(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, BipedEntityRenderState state, float limbAngle, float limbDistance, CallbackInfo ci) {
        ICustomPlayerModelState customState;
        if (state instanceof ICustomPlayerModelState && (customState = (ICustomPlayerModelState)state).mre$hasCustomModel()) {
            ci.cancel();
        }
    }
}

