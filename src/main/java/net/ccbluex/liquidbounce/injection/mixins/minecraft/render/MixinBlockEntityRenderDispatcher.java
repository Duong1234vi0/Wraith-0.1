package net.ccbluex.liquidbounce.injection.mixins.minecraft.render;

import com.llamalad7.mixinextras.sugar.Local;
import net.ccbluex.liquidbounce.common.OutlineFlag;
import net.ccbluex.liquidbounce.features.module.modules.render.ModuleStorageESP;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BlockEntityRenderDispatcher.class)
public abstract class MixinBlockEntityRenderDispatcher {

    @ModifyArg(
            method = "submit",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/blockentity/BlockEntityRenderer;submit(Lnet/minecraft/client/renderer/blockentity/state/BlockEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V")
    )
    private static <S extends BlockEntityRenderState> S render(
            S state,
            @Local(argsOnly = true) S localState
    ) {
        var client = Minecraft.getInstance();
        if (ModuleStorageESP.Glow.INSTANCE.getRunning() && client.level != null) {
            var type = ModuleStorageESP.categorize(client.level.getBlockEntity(state.blockPos));

            if (type != null && type.shouldRender(state.blockPos)) {
                var color = type.getColor();

                if (color.a() > 0) {
                    client.renderBuffers().outlineBufferSource().setColor(color.toARGB());
                    OutlineFlag.drawOutline = true;
                }
            }
        }

        return state;
    }

}
