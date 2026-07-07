package unique.core.injection;

import net.minecraft.class_1297;
import net.minecraft.class_266;
import net.minecraft.class_329;
import net.minecraft.class_332;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import unique.core.core.Managers;
import unique.core.core.manager.IManager;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.hud.impl.Hotbar;
import unique.core.features.modules.Module;
import unique.core.gui.windows.WindowsScreen;

@Mixin({class_329.class})
public abstract class MixinInGameHud {
   @Inject(
      at = {@At("HEAD")},
      method = {"render"}
   )
   public void renderHook(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
      if (!Module.fullNullCheck()) {
         Managers.MODULE.onRender2D(context);
         Managers.NOTIFICATION.onRender2D(context);
      }
   }

   @Inject(
      at = {@At("HEAD")},
      method = {"renderStatusBars"},
      cancellable = true
   )
   private void renderStatusBarsHook(class_332 context, CallbackInfo ci) {
      if (IManager.mc != null && IManager.mc.field_1755 instanceof WindowsScreen) {
         ci.cancel();
      }
   }

   @Inject(
      at = {@At("HEAD")},
      method = {"renderHotbar"},
      cancellable = true
   )
   public void renderHotbarCustom(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
      if (IManager.mc != null && IManager.mc.field_1755 instanceof WindowsScreen) {
         ci.cancel();
      }

      if (ModuleManager.hotbar.isEnabled()) {
         ci.cancel();
         Hotbar.renderHotBarItems(tickCounter.method_60637(true), context);
      }
   }

   @Inject(
      at = {@At("HEAD")},
      method = {"renderHeldItemTooltip"},
      cancellable = true
   )
   public void renderHeldItemTooltipHook(class_332 context, CallbackInfo ci) {
      if (ModuleManager.noRender.isEnabled() && ModuleManager.noRender.hotbarItemName.getValue()) {
         ci.cancel();
      }
   }

   @Inject(
      at = {@At("HEAD")},
      method = {"renderStatusEffectOverlay"},
      cancellable = true
   )
   public void renderStatusEffectOverlayHook(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
      if (ModuleManager.potionHud.isEnabled() || ModuleManager.legacyHud.isEnabled() && ModuleManager.legacyHud.potions.getValue()) {
         ci.cancel();
      }
   }

   @Inject(
      method = {"renderExperienceBar"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void renderXpBarCustom(class_332 context, int x, CallbackInfo ci) {
      if (IManager.mc != null && IManager.mc.field_1755 instanceof WindowsScreen) {
         ci.cancel();
      }

      if (ModuleManager.hotbar.isEnabled()) {
         ci.cancel();
         Hotbar.renderXpBar(x, context.method_51448());
      }
   }

   @Inject(
      method = {"renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/scoreboard/ScoreboardObjective;)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderScoreboardSidebarHook(class_332 context, class_266 objective, CallbackInfo ci) {
      if (ModuleManager.noRender.noScoreBoard.getValue() && ModuleManager.noRender.isEnabled()) {
         ci.cancel();
      }
   }

   @Inject(
      method = {"renderVignetteOverlay"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderVignetteOverlayHook(class_332 context, class_1297 entity, CallbackInfo ci) {
      if (ModuleManager.noRender.vignette.getValue()) {
         ci.cancel();
      }
   }

   @Inject(
      method = {"renderPortalOverlay"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderPortalOverlayHook(class_332 context, float nauseaStrength, CallbackInfo ci) {
      if (ModuleManager.noRender.portal.getValue()) {
         ci.cancel();
      }
   }

   @Inject(
      method = {"renderCrosshair"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void renderCrosshair(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
      if (ModuleManager.crosshair.isEnabled()) {
         ci.cancel();
      }
   }
}
