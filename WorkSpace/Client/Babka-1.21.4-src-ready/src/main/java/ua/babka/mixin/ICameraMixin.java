package ua.babka.mixin;

import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={Camera.class})
public interface ICameraMixin {
    @Invoker(value="moveBy")
    public void callMoveBy(float var1, float var2, float var3);
}

