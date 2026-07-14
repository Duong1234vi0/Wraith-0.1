package ua.babka.mixin;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.babka.module.impl.render.NoRenderSphere;
import ua.babka.util.Instance;

@Mixin(value={BlockEntityRenderDispatcher.class})
public class NoPlaceHeadSphereMixin {
    @Inject(method={"render(Lnet/minecraft/block/entity/BlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private <T extends BlockEntity> void onRenderBlockEntity(T blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, CallbackInfo ci) {
        NoRenderSphere noPlace;
        if (blockEntity instanceof SkullBlockEntity && (noPlace = Instance.get(NoRenderSphere.class)) != null && noPlace.isEnabled() && noPlace.hideHeads.getValue()) {
            ci.cancel();
        }
    }
}

