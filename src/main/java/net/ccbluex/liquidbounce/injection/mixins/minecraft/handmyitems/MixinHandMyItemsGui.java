package net.ccbluex.liquidbounce.injection.mixins.minecraft.handmyitems;

import java.util.List;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.HandMyItemsRuntime;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global.GlobalsStorage;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class MixinHandMyItemsGui {

    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void hmi$renderDebugText(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (!HandMyItemsRuntime.isActive()) {
            GlobalsStorage.debugTextRenderer.clear();
            return;
        }

        List<String> lines = GlobalsStorage.debugTextRenderer.get();
        int y = 10;
        for (String line : lines) {
            graphics.centeredText(Minecraft.getInstance().font, line, 10, y, -1);
            y += 10;
        }
        GlobalsStorage.debugTextRenderer.clear();
    }
}
