/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 *  net.minecraft.class_5481
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package code.alphadlc.mixins;

import code.alphadlc.utils.display.render.GuiRenderState;
import net.minecraft.class_2561;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_5481;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_332.class})
public class DrawContextMixin {
    @Inject(method={"drawText(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;IIIZ)I"}, at={@At(value="HEAD")})
    private void alphadlc$beforeVanillaTextString(class_327 textRenderer, String text, int x, int y, int color, boolean shadow, CallbackInfoReturnable<Integer> cir) {
        GuiRenderState.restoreFontAtlasesNearest();
    }

    @Inject(method={"drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIIZ)I"}, at={@At(value="HEAD")})
    private void alphadlc$beforeVanillaText(class_327 textRenderer, class_2561 text, int x, int y, int color, boolean shadow, CallbackInfoReturnable<Integer> cir) {
        GuiRenderState.restoreFontAtlasesNearest();
    }

    @Inject(method={"drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/OrderedText;IIIZ)I"}, at={@At(value="HEAD")})
    private void alphadlc$beforeVanillaOrderedText(class_327 textRenderer, class_5481 text, int x, int y, int color, boolean shadow, CallbackInfoReturnable<Integer> cir) {
        GuiRenderState.restoreFontAtlasesNearest();
    }

    @Inject(method={"drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)I"}, at={@At(value="HEAD")})
    private void alphadlc$beforeVanillaShadowString(class_327 textRenderer, String text, int x, int y, int color, CallbackInfoReturnable<Integer> cir) {
        GuiRenderState.restoreFontAtlasesNearest();
    }

    @Inject(method={"drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)I"}, at={@At(value="HEAD")})
    private void alphadlc$beforeVanillaShadowText(class_327 textRenderer, class_2561 text, int x, int y, int color, CallbackInfoReturnable<Integer> cir) {
        GuiRenderState.restoreFontAtlasesNearest();
    }

    @Inject(method={"drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/OrderedText;III)I"}, at={@At(value="HEAD")})
    private void alphadlc$beforeVanillaShadowOrderedText(class_327 textRenderer, class_5481 text, int x, int y, int color, CallbackInfoReturnable<Integer> cir) {
        GuiRenderState.restoreFontAtlasesNearest();
    }

    @Inject(method={"drawTextWithBackground"}, at={@At(value="HEAD")})
    private void alphadlc$beforeVanillaTextBackground(class_327 textRenderer, class_2561 text, int x, int y, int width, int color, CallbackInfoReturnable<Integer> cir) {
        GuiRenderState.restoreFontAtlasesNearest();
    }
}
