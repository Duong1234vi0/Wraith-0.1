package ua.babka.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.state.EntityRenderState;
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

@Mixin(value={HeldItemFeatureRenderer.class})
public class HeldItemFeatureRendererMixin {
    @Inject(method={"render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/state/EntityRenderState;FF)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void babka$hideHeldItems(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, EntityRenderState state, float limbAngle, float limbDistance, CallbackInfo ci) {
        PlayerEntity player;
        if (!(state instanceof PlayerEntityRenderState)) {
            return;
        }
        PlayerEntityRenderState playerState = (PlayerEntityRenderState)state;
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.world == null) {
            return;
        }
        Chams chams = Instance.get(Chams.class);
        if (chams == null || !chams.isEnabled()) {
            return;
        }
        Entity entity = mc.world.getEntityById(playerState.id);
        if (entity instanceof PlayerEntity && chams.shouldHideItemsAndCape(player = (PlayerEntity)entity)) {
            ci.cancel();
        }
    }
}

