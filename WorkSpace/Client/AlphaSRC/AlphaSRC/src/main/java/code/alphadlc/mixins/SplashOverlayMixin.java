/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_156
 *  net.minecraft.class_1921
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_425
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.utils.display.gif.GifTextureRenderer;
import code.alphadlc.utils.display.render.StartupBackgroundRenderer;
import java.util.Random;
import java.util.function.Function;
import net.minecraft.class_156;
import net.minecraft.class_1921;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_425;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_425.class})
public abstract class SplashOverlayMixin {
    private static final class_2960[] SPLASH_GIFS = new class_2960[]{class_2960.method_60655((String)"minecraft", (String)"gifs/blatnoy.gif"), class_2960.method_60655((String)"minecraft", (String)"gifs/clean.gif"), class_2960.method_60655((String)"minecraft", (String)"gifs/duck.gif"), class_2960.method_60655((String)"minecraft", (String)"gifs/nozik.gif"), class_2960.method_60655((String)"minecraft", (String)"gifs/poplach.gif"), class_2960.method_60655((String)"minecraft", (String)"gifs/wait.gif"), class_2960.method_60655((String)"minecraft", (String)"gifs/sleep.gif")};
    private static final GifTextureRenderer SPLASH_GIF_RENDERER = new GifTextureRenderer(SPLASH_GIFS[new Random().nextInt(SPLASH_GIFS.length)]);
    @Shadow
    @Final
    private class_310 field_18217;
    @Shadow
    @Final
    private boolean field_18219;
    @Shadow
    private long field_17771;
    @Shadow
    private long field_18220;

    @Inject(method={"render"}, at={@At(value="HEAD")})
    private void onSplashRenderHead(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        float opacity = this.alphadlc$getOverlayOpacity();
        if (opacity <= 0.01f) {
            return;
        }
        SPLASH_GIF_RENDERER.preload();
        int screenW = context.method_51421();
        int screenH = context.method_51443();
        StartupBackgroundRenderer.drawBackground(context, 0.0f, 0.0f, screenW, screenH, opacity);
        float gifSize = (float)Math.min(screenW, screenH) * 0.32f;
        float centerX = (float)screenW / 2.0f;
        float centerY = (float)screenH / 2.0f;
        SPLASH_GIF_RENDERER.render(context, centerX - gifSize / 2.0f, centerY - gifSize / 2.0f, gifSize, gifSize, opacity);
    }

    @Unique
    private float alphadlc$getOverlayOpacity() {
        float fadeIn;
        long now = class_156.method_658();
        float fadeOut = this.field_17771 > -1L ? (float)(now - this.field_17771) / 1000.0f : -1.0f;
        float f = fadeIn = this.field_18220 > -1L ? (float)(now - this.field_18220) / 500.0f : -1.0f;
        if (fadeOut >= 1.0f) {
            return 1.0f - class_3532.method_15363((float)(fadeOut - 1.0f), (float)0.0f, (float)1.0f);
        }
        if (this.field_18219) {
            return class_3532.method_15363((float)fadeIn, (float)0.0f, (float)1.0f);
        }
        return 1.0f;
    }

    @Redirect(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/DrawContext;drawTexture(Ljava/util/function/Function;Lnet/minecraft/util/Identifier;IIFFIIIIIII)V"), require=0)
    private void skipMojangLogoDraw(class_332 context, Function<class_2960, class_1921> renderLayers, class_2960 texture, int x, int y, float u, float v, int width, int height, int regionWidth, int regionHeight, int textureWidth, int textureHeight, int color) {
        if (class_425.field_2483.equals((Object)texture)) {
            return;
        }
        context.method_25293(renderLayers, texture, x, y, u, v, width, height, regionWidth, regionHeight, textureWidth, textureHeight, color);
    }

    @Redirect(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/screen/SplashOverlay;renderProgressBar(Lnet/minecraft/client/gui/DrawContext;IIIIF)V"))
    private void skipVanillaProgressBar(class_425 instance, class_332 context, int width, int height, int centerX, int bottomY, float progress) {
    }

    @Redirect(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/DrawContext;fill(Lnet/minecraft/client/render/RenderLayer;IIIII)V"))
    private void replaceStartupBrandOverlay(class_332 context, class_1921 layer, int x1, int y1, int x2, int y2, int color) {
    }

    static {
        SPLASH_GIF_RENDERER.preloadEarly();
    }
}
