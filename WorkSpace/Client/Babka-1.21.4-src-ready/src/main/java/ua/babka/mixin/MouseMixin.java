package ua.babka.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.util.math.Smoother;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.babka.Babka;
import ua.babka.event.EventKeyInput;
import ua.babka.event.LookEvent;
import ua.babka.module.impl.combat.AttackAura;

@Mixin(value={Mouse.class})
public abstract class MouseMixin {
    @Shadow
    @Final
    private MinecraftClient client;
    @Shadow
    private double cursorDeltaX;
    @Shadow
    private double cursorDeltaY;
    @Final
    @Shadow
    private Smoother cursorXSmoother;
    @Final
    @Shadow
    private Smoother cursorYSmoother;

    @Inject(method={"updateMouse"}, at={@At(value="HEAD")}, cancellable=true)
    private void onUpdateMouse(double timeDelta, CallbackInfo ci) {
        double j2;
        double i2;
        if (this.client.player == null) {
            return;
        }
        double sensitivity = (Double)this.client.options.getMouseSensitivity().getValue() * 0.6 + 0.2;
        double scaled = sensitivity * sensitivity * sensitivity * 8.0;
        if (this.client.options.smoothCameraEnabled) {
            i2 = this.cursorXSmoother.smooth(this.cursorDeltaX * scaled, timeDelta * scaled);
            j2 = this.cursorYSmoother.smooth(this.cursorDeltaY * scaled, timeDelta * scaled);
        } else if (this.client.options.getPerspective().isFirstPerson() && this.client.player.isUsingSpyglass()) {
            this.cursorXSmoother.clear();
            this.cursorYSmoother.clear();
            i2 = this.cursorDeltaX * sensitivity * sensitivity * sensitivity;
            j2 = this.cursorDeltaY * sensitivity * sensitivity * sensitivity;
        } else {
            this.cursorXSmoother.clear();
            this.cursorYSmoother.clear();
            i2 = this.cursorDeltaX * scaled;
            j2 = this.cursorDeltaY * scaled;
        }
        int invert = (Boolean)this.client.options.getInvertYMouse().getValue() != false ? -1 : 1;
        LookEvent event = new LookEvent(i2, j2 * (double)invert);
        event.post();
        if (Math.abs(event.getYaw()) > 0.01 || Math.abs(event.getPitch()) > 0.01) {
            AttackAura.lastPhysicalMoveTime = System.currentTimeMillis();
        }
        if (!event.isCancelled()) {
            this.client.getTutorialManager().onUpdateMouse(event.getYaw(), event.getPitch());
            this.client.player.changeLookDirection(event.getYaw(), event.getPitch());
        }
        this.cursorDeltaX = 0.0;
        this.cursorDeltaY = 0.0;
        ci.cancel();
    }

    @Inject(method={"onMouseButton"}, at={@At(value="HEAD")})
    private void onMouseButton(long window, int button, int action, int mods, CallbackInfo ci) {
        if (MinecraftClient.getInstance().currentScreen != null || action == 2) {
            return;
        }
        Babka.getInstance().getEventBus().post((Object)new EventKeyInput(button, action));
    }
}

