package ua.babka.mixin;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={LivingEntityRenderer.class})
public interface LivingEntityRendererAccessor {
    @Invoker(value="setupTransforms")
    public void babka$setupTransforms(LivingEntityRenderState var1, MatrixStack var2, float var3, float var4);

    @Invoker(value="scale")
    public void babka$scale(LivingEntityRenderState var1, MatrixStack var2);
}

