package ua.babka.mixin;

import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Fog;
import net.minecraft.client.render.FogShape;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ua.babka.Babka;
import ua.babka.module.impl.render.Ambience;

@Mixin(value={BackgroundRenderer.class})
public class FogMixin {
    @Inject(method={"applyFog"}, at={@At(value="RETURN")}, cancellable=true)
    private static void onApplyFog(Camera camera, BackgroundRenderer.FogType fogType, Vector4f color, float viewDistance, boolean thickenFog, float tickDelta, CallbackInfoReturnable<Fog> cir) {
        try {
            if (Babka.getInstance() == null) {
                return;
            }
            if (Babka.getInstance().getModuleStorage() == null) {
                return;
            }
            Ambience ambience = Babka.getInstance().getModuleStorage().get(Ambience.class);
            if (ambience == null || !ambience.isEnabled() || !ambience.isFogEnabled()) {
                return;
            }
            if (ambience.isFogClear()) {
                cir.setReturnValue((Object)new Fog(-1.0f, 100000.0f, FogShape.SPHERE, 0.0f, 0.0f, 0.0f, 0.0f));
                return;
            }
            float start = ambience.getFogStart();
            float end = ambience.getFogDistance();
            int fogColorRaw = ambience.fogColor.getValue();
            float r2 = (float)(fogColorRaw >> 16 & 0xFF) / 255.0f;
            float g2 = (float)(fogColorRaw >> 8 & 0xFF) / 255.0f;
            float b2 = (float)(fogColorRaw & 0xFF) / 255.0f;
            cir.setReturnValue((Object)new Fog(start, end, FogShape.SPHERE, r2, g2, b2, 1.0f));
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}

