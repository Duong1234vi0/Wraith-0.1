package net.ccbluex.liquidbounce.injection.mixins.minecraft.handmyitems;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.HandMyItemsRuntime;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.LuaTestHMI;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class MixinHandMyItemsGameRenderer {

    @Inject(method = "render", at = @At("HEAD"))
    private void hmi$updateDeltaTime(DeltaTracker deltaTracker, boolean advanceGameTime, CallbackInfo ci) {
        float currentTime = (float) GLFW.glfwGetTime();
        float elapsed = currentTime - LuaTestHMI.prevTime;
        LuaTestHMI.prevTime = currentTime;

        if (!HandMyItemsRuntime.isActive() || Minecraft.getInstance().isPaused()) {
            LuaTestHMI.deltaTime = 0.0F;
            return;
        }
        LuaTestHMI.deltaTime = (float) Math.min(0.05D, elapsed);
    }
}
