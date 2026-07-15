package net.ccbluex.liquidbounce.injection.mixins.minecraft.handmyitems;

import com.mojang.blaze3d.vertex.PoseStack;
import it.unimi.dsi.fastutil.ints.IntList;
import java.util.List;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.access.AlternateBlockRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BlockModelResolver.class)
public abstract class MixinHandMyItemsBlockModelResolver implements AlternateBlockRenderer {

    @Unique
    private static final BlockDisplayContext HMI_BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();
    @Unique
    private final BlockModelRenderState hmi$renderState = new BlockModelRenderState();

    @Override
    public void renderSingleBlockWithEmission(
        BlockState blockState,
        PoseStack poseStack,
        SubmitNodeCollector submitNodeCollector,
        int combinedLight,
        ClientLevel level,
        AbstractClientPlayer player
    ) {
        if (blockState.getRenderShape() == RenderShape.INVISIBLE) {
            return;
        }

        BlockModelResolver resolver = (BlockModelResolver) (Object) this;
        resolver.update(this.hmi$renderState, blockState, HMI_BLOCK_DISPLAY_CONTEXT);

        List<BlockTintSource> tintSources = Minecraft.getInstance().getBlockColors().getTintSources(blockState);
        if (!tintSources.isEmpty()) {
            IntList tintLayers = this.hmi$renderState.tintLayers();
            tintLayers.clear();
            for (BlockTintSource tintSource : tintSources) {
                tintLayers.add(tintSource.colorInWorld(blockState, level, player.blockPosition()));
            }
        }

        int emittedLight = LightCoordsUtil.lightCoordsWithEmission(combinedLight, blockState.getLightEmission());
        this.hmi$renderState.submit(poseStack, submitNodeCollector, emittedLight, OverlayTexture.NO_OVERLAY, 0);
    }
}
