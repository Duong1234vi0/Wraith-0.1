package ua.babka.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ua.babka.event.EventWorldRender;
import ua.babka.module.impl.render.ViewBobbing;
import ua.babka.module.impl.render.Zoom;
import ua.babka.render.DrawUtil;
import ua.babka.util.Instance;

@Mixin(value={GameRenderer.class})
public class GameRendererMixin {
    @Inject(method={"getFov"}, at={@At(value="RETURN")}, cancellable=true)
    private void onGetFov(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Float> cir) {
        if (Zoom.zooming || (double)Zoom.currentFovModifier != 1.0) {
            float original = ((Float)cir.getReturnValue()).floatValue();
            cir.setReturnValue((Object)Float.valueOf(original * Zoom.currentFovModifier));
        }
    }

    @Inject(method={"renderWorld"}, at={@At(value="FIELD", target="Lnet/minecraft/client/render/GameRenderer;renderHand:Z", opcode=180, ordinal=0)})
    public void hookWorldRender(RenderTickCounter tickCounter, CallbackInfo ci, @Local(ordinal=2) Matrix4f matrix4f) {
        MatrixStack matrixStack = new MatrixStack();
        matrixStack.multiplyPositionMatrix(matrix4f);
        EventWorldRender event = new EventWorldRender(matrixStack, tickCounter.getTickDelta(false));
        event.post();
        DrawUtil.onRender3D(event.getMatrixStack());
    }

    @Inject(method={"bobView"}, at={@At(value="HEAD")}, cancellable=true)
    private void onBobView(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        ViewBobbing viewBobbing = Instance.get(ViewBobbing.class);
        if (!viewBobbing.isEnabled()) {
            return;
        }
        if (viewBobbing.shouldCancel()) {
            ci.cancel();
        } else if (viewBobbing.shouldReplace()) {
            ci.cancel();
            viewBobbing.applyReducedBobbing(matrices, tickDelta);
        }
    }
}

