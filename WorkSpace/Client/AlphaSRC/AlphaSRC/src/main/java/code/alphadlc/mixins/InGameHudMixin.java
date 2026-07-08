/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1921
 *  net.minecraft.class_266
 *  net.minecraft.class_310
 *  net.minecraft.class_329
 *  net.minecraft.class_332
 *  net.minecraft.class_9779
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.features.impl.render.CrossHair;
import code.alphadlc.features.impl.render.Hud;
import code.alphadlc.features.impl.render.NoRender;
import code.alphadlc.utils.display.interfaces.QuickImports;
import net.minecraft.class_1297;
import net.minecraft.class_1921;
import net.minecraft.class_266;
import net.minecraft.class_310;
import net.minecraft.class_329;
import net.minecraft.class_332;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_329.class})
public abstract class InGameHudMixin
implements QuickImports {
    @Final
    @Shadow
    private class_310 field_2035;

    @Unique
    private Hud getHud() {
        return Hud.getInstance();
    }

    @Shadow
    protected abstract void method_1760(class_332 var1);

    @Shadow
    protected abstract void method_1741(class_332 var1);

    @Inject(method={"renderCrosshair"}, at={@At(value="FIELD", target="Lnet/minecraft/client/gui/hud/InGameHud;CROSSHAIR_TEXTURE:Lnet/minecraft/util/Identifier;")}, cancellable=true)
    public void renderCrosshairHook(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
        CrossHair crossHair = CrossHair.getInstance();
        if (crossHair.isState()) {
            crossHair.onRenderCrossHair();
            ci.cancel();
        }
    }

    @Inject(at={@At(value="HEAD")}, method={"renderStatusEffectOverlay"}, cancellable=true)
    public void renderStatusEffectOverlayHook(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
        Hud hud = this.getHud();
        if (hud != null && hud.isState()) {
            if (Hud.interfaceSettings.isSelected("Potions")) {
                ci.cancel();
            }
        }
    }

    @Inject(method={"renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/scoreboard/ScoreboardObjective;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderScoreboardSidebarHook(class_332 context, class_266 objective, CallbackInfo ci) {
        NoRender noRender = NoRender.getInstance();
        if (noRender != null && noRender.shouldHideScoreboard()) {
            ci.cancel();
            return;
        }
        Hud hud = this.getHud();
        if (hud != null && hud.isState()) {
            if (Hud.interfaceSettings.isSelected("Score Board")) {
                ci.cancel();
            }
        }
    }

    @Inject(method={"renderVignetteOverlay"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderVignette(class_332 context, class_1297 entity, CallbackInfo ci) {
        NoRender noRender = NoRender.getInstance();
        if (noRender != null && noRender.shouldHideVignette()) {
            ci.cancel();
        }
    }

    @Inject(method={"renderTitleAndSubtitle"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderTitleAndSubtitle(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
        NoRender noRender = NoRender.getInstance();
        if (noRender != null && noRender.shouldHideTitles()) {
            ci.cancel();
        }
    }

    @Inject(method={"renderNauseaOverlay"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderNauseaOverlay(class_332 context, float nauseaStrength, CallbackInfo ci) {
        NoRender noRender = NoRender.getInstance();
        if (noRender != null && noRender.shouldHideBadEffects()) {
            ci.cancel();
        }
    }

    @Inject(method={"renderPortalOverlay"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderPortalOverlay(class_332 context, float nauseaStrength, CallbackInfo ci) {
        NoRender noRender = NoRender.getInstance();
        if (noRender != null && noRender.shouldHideBadEffects()) {
            ci.cancel();
        }
    }

    @Inject(method={"renderOverlayMessage"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderOverlayMessage(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
        block2: {
            block3: {
                Hud hud = this.getHud();
                if (hud == null || !hud.isState()) break block2;
                if (Hud.interfaceSettings.isSelected("HotBar")) break block3;
                if (!Hud.interfaceSettings.isSelected("CustomHotbar")) break block2;
            }
            ci.cancel();
        }
    }

    @Inject(method={"renderExperienceLevel"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderExperienceLevel(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
        block2: {
            block3: {
                Hud hud = this.getHud();
                if (hud == null || !hud.isState()) break block2;
                if (Hud.interfaceSettings.isSelected("HotBar")) break block3;
                if (!Hud.interfaceSettings.isSelected("CustomHotbar")) break block2;
            }
            ci.cancel();
        }
    }

    @Inject(method={"renderMainHud"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderMainHud(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
        block4: {
            block5: {
                Hud hud = this.getHud();
                if (hud == null || !hud.isState()) break block4;
                if (Hud.interfaceSettings.isSelected("HotBar")) break block5;
                if (!Hud.interfaceSettings.isSelected("CustomHotbar")) break block4;
            }
            context.method_52706(class_1921::method_62277, class_329.field_45314, 0, 0, 1, 1);
            if (this.field_2035.field_1761 != null && this.field_2035.field_1761.method_2908()) {
                this.method_1760(context);
            }
            this.method_1741(context);
            ci.cancel();
        }
    }
}
