/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1269$class_9860
 *  net.minecraft.class_1269$class_9861
 *  net.minecraft.class_156
 *  net.minecraft.class_1657
 *  net.minecraft.class_239
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_310
 *  net.minecraft.class_3966
 *  net.minecraft.class_437
 *  net.minecraft.class_542
 *  net.minecraft.class_634
 *  net.minecraft.class_636
 *  net.minecraft.class_746
 *  net.minecraft.class_757
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package code.alphadlc.mixins;

import code.alphadlc.AlphaDLC;
import code.alphadlc.display.screens.ias.IASScreen;
import code.alphadlc.events.container.SetScreenEvent;
import code.alphadlc.events.player.GameUpdateEvent;
import code.alphadlc.events.player.HotBarUpdateEvent;
import code.alphadlc.features.impl.combat.NoInteract;
import code.alphadlc.utils.client.FakePlayerManager;
import code.alphadlc.utils.client.logs.Logger;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import code.alphadlc.utils.client.managers.file.exception.FileProcessingException;
import code.alphadlc.utils.client.window.WindowStyle;
import code.alphadlc.utils.client.window.WindowTitleAnimation;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.render.font.Fonts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_156;
import net.minecraft.class_1657;
import net.minecraft.class_239;
import net.minecraft.class_310;
import net.minecraft.class_3966;
import net.minecraft.class_437;
import net.minecraft.class_542;
import net.minecraft.class_634;
import net.minecraft.class_636;
import net.minecraft.class_746;
import net.minecraft.class_757;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_310.class})
public abstract class MinecraftClientMixin
implements QuickImports {
    @Shadow
    @Nullable
    public class_636 field_1761;
    @Shadow
    @Nullable
    public class_746 field_1724;
    @Shadow
    @Final
    public class_757 field_1773;
    @Shadow
    @Nullable
    public class_437 field_1755;
    @Shadow
    @Nullable
    public class_239 field_1765;
    private WindowTitleAnimation titleUtil;
    @Unique
    private long gameUpdateLastHookTime;
    @Unique
    private int gameUpdateAccumulatedCalls;
    private int titleUpdateCounter = 0;
    @Unique
    private boolean alphadlc$glintFixApplied;
    @Unique
    private boolean alphadlc$accountRestored;

    @Shadow
    @Nullable
    public abstract class_634 method_1562();

    @Inject(at={@At(value="TAIL")}, method={"<init>"})
    private void onInit(class_542 args, CallbackInfo ci) {
        if (System.getProperty("java.version").equals("sk3dguard-production-vm")) {
            System.setProperty("sun.font.layout.ffm", "false");
        }
        Fonts.init();
        try {
            this.titleUtil = WindowTitleAnimation.getInstance();
            class_310 client = class_310.method_1551();
            if (client != null && client.method_22683() != null) {
                client.method_22683().method_24286(this.titleUtil.getCurrentTitle());
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Inject(at={@At(value="HEAD")}, method={"stop"})
    private void stop(CallbackInfo ci) {
        block8: {
            Logger.info("Stopping for MinecraftClient");
            try {
                AlphaDLC alphaDlc = AlphaDLC.getInstance();
                if (alphaDlc == null || !alphaDlc.isInitialized()) break block8;
                try {
                    alphaDlc.getFileController().saveFiles();
                }
                catch (FileProcessingException e) {
                    Logger.error("Error occurred while saving files: " + e.getMessage() + " " + String.valueOf(e.getCause()));
                }
                finally {
                    if (alphaDlc.getFileController() != null) {
                        alphaDlc.getFileController().stopAutoSave();
                    }
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    @Inject(method={"doItemUse"}, at={@At(value="INVOKE", target="Lnet/minecraft/util/Hand;values()[Lnet/minecraft/util/Hand;")}, cancellable=true)
    public void doItemUseHook(CallbackInfo ci) {
        NoInteract noInteract = NoInteract.getInstance();
        if (noInteract != null && noInteract.isState() && this.field_1724 != null && this.field_1761 != null) {
            for (class_1268 hand : class_1268.values()) {
                class_1269.class_9860 success;
                class_1269 result;
                if (this.field_1724.method_5998(hand).method_7960() || !(result = this.field_1761.method_2919((class_1657)this.field_1724, hand)).method_23665()) continue;
                if (result instanceof class_1269.class_9860 && (success = (class_1269.class_9860)result).comp_2909().equals((Object)class_1269.class_9861.field_52427)) {
                    this.field_1773.field_4012.method_3215(hand);
                    this.field_1724.method_6104(hand);
                }
                ci.cancel();
            }
        }
    }

    @Inject(method={"doAttack"}, at={@At(value="HEAD")}, cancellable=true)
    private void doAttackHook(CallbackInfoReturnable<Boolean> cir) {
        if (this.field_1724 == null || this.field_1765 == null || this.field_1765.method_17783() != class_239.class_240.field_1331) {
            return;
        }
        class_3966 entityHitResult = (class_3966)this.field_1765;
        if (FakePlayerManager.handleAttack(entityHitResult.method_17782())) {
            this.field_1724.method_6104(class_1268.field_5808);
            cir.setReturnValue((Object)true);
        }
    }

    @Inject(method={"setScreen"}, at={@At(value="HEAD")}, cancellable=true)
    public void setScreenHook(class_437 screen, CallbackInfo ci) {
        try {
            class_437 eventScreen;
            SetScreenEvent event = SafeEvents.dispatch(() -> new SetScreenEvent(screen));
            AlphaDLC alphaDlc = AlphaDLC.getInstance();
            if (alphaDlc != null && alphaDlc.getDraggableRepository() != null && event != null) {
                alphaDlc.getDraggableRepository().draggable().forEach(drag -> drag.setScreen(event));
            }
            class_437 class_4372 = eventScreen = event != null ? event.getScreen() : screen;
            if (screen != eventScreen) {
                mc.method_1507(eventScreen);
                ci.cancel();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Inject(method={"onResolutionChanged"}, at={@At(value="TAIL")})
    private void applyDarkMode(CallbackInfo ci) {
        Fonts.clearCache();
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("linux")) {
                return;
            }
            class_310 client = class_310.method_1551();
            if (client != null && client.method_22683() != null) {
                WindowStyle.setDarkMode(client.method_22683().method_4490());
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Inject(method={"tick"}, at={@At(value="HEAD")})
    private void onTick(CallbackInfo ci) {
        this.alphadlc$restoreGlintStrength();
        this.alphadlc$restoreActiveAccount();
        try {
            if (this.titleUtil == null) {
                this.titleUtil = WindowTitleAnimation.getInstance();
            }
            this.titleUtil.updateTitle();
            if (++this.titleUpdateCounter >= 10) {
                this.titleUpdateCounter = 0;
                class_310 client = class_310.method_1551();
                if (client != null && client.method_22683() != null) {
                    client.method_22683().method_24286(this.titleUtil.getCurrentTitle());
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Unique
    private void alphadlc$restoreGlintStrength() {
        if (this.alphadlc$glintFixApplied) {
            return;
        }
        try {
            class_310 client = class_310.method_1551();
            if (client == null || client.field_1690 == null) {
                return;
            }
            this.alphadlc$glintFixApplied = true;
            boolean changed = false;
            if ((Double)client.field_1690.method_48581().method_41753() <= 0.0) {
                client.field_1690.method_48581().method_41748((Object)0.75);
                changed = true;
            }
            if ((Double)client.field_1690.method_48580().method_41753() <= 0.0) {
                client.field_1690.method_48580().method_41748((Object)0.5);
                changed = true;
            }
            if (changed) {
                client.field_1690.method_1640();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Unique
    private void alphadlc$restoreActiveAccount() {
        if (this.alphadlc$accountRestored) {
            return;
        }
        try {
            AlphaDLC alphaDlc = AlphaDLC.getInstance();
            if (alphaDlc == null || !alphaDlc.isInitialized() || alphaDlc.getAccountRepository() == null) {
                return;
            }
            this.alphadlc$accountRestored = true;
            IASScreen.restoreActiveAccount();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Inject(method={"updateWindowTitle"}, at={@At(value="HEAD")}, cancellable=true)
    private void onUpdateWindowTitle(CallbackInfo ci) {
        try {
            class_310 client;
            if (this.titleUtil == null) {
                this.titleUtil = WindowTitleAnimation.getInstance();
            }
            if ((client = class_310.method_1551()) != null && client.method_22683() != null) {
                client.method_22683().method_24286(this.titleUtil.getCurrentTitle());
            }
            ci.cancel();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Inject(method={"handleInputEvents"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;getInventory()Lnet/minecraft/entity/player/PlayerInventory;")}, cancellable=true)
    public void handleInputEventsHook(CallbackInfo ci) {
        HotBarUpdateEvent event = SafeEvents.dispatch(() -> new HotBarUpdateEvent());
        if (event != null && event.isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method={"render"}, at={@At(value="HEAD")})
    private void onRenderGameUpdate(boolean tick, CallbackInfo ci) {
        long now = class_156.method_648();
        long delta = now - this.gameUpdateLastHookTime;
        this.gameUpdateAccumulatedCalls += (int)(delta / 4166666L);
        this.gameUpdateLastHookTime += (long)this.gameUpdateAccumulatedCalls * 4166666L;
        this.gameUpdateAccumulatedCalls = Math.min(this.gameUpdateAccumulatedCalls, 240);
        while (this.gameUpdateAccumulatedCalls > 0) {
            SafeEvents.dispatch(GameUpdateEvent::new);
            --this.gameUpdateAccumulatedCalls;
        }
    }
}
