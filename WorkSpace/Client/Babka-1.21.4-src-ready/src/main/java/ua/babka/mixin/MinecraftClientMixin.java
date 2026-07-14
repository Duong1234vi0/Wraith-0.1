package ua.babka.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.util.Util;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ua.babka.baritone.BaritoneAntiStuck;
import ua.babka.event.EventGameUpdate;
import ua.babka.event.EventMinecraftInit;
import ua.babka.event.EventTick;
import ua.babka.event.EventTickEnd;
import ua.babka.module.impl.combat.AutoExplosion;
import ua.babka.util.Instance;

@Mixin(value={MinecraftClient.class})
public class MinecraftClientMixin {
    @Unique
    private long lastHookTime = Util.getMeasuringTimeNano();
    @Unique
    private int accumulatedCalls = 0;
    @Unique
    private static final String BASE = "Babka 1.21.4";
    @Unique
    private static final String CHARS = "abcdefghijklmnopqrstuvwxyz0123456789#@!%&*";
    @Unique
    private static final long FRAME = 50L;
    @Unique
    private static final int STATE_APPEAR = 0;
    @Unique
    private static final int STATE_HOLD = 1;
    @Unique
    private static final int STATE_ERASE = 2;
    @Unique
    private int animState = 0;
    @Unique
    private int animPos = 0;
    @Unique
    private long lastStepMs = 0L;
    @Unique
    private long holdUntilMs = 0L;
    @Unique
    private long lastNoiseMs = 0L;
    @Unique
    private long noiseSeed = 0L;
    @Unique
    private static final long APPEAR_STEP_MS = 35L;
    @Unique
    private static final long ERASE_STEP_MS = 25L;
    @Unique
    private static final long HOLD_MS = 1200L;

    @Unique
    private String buildTitle() {
        long now = System.currentTimeMillis();
        int len = BASE.length();
        if (now - this.lastNoiseMs >= 50L) {
            this.noiseSeed = now;
            this.lastNoiseMs = now;
        }
        switch (this.animState) {
            case 0: {
                if (this.animPos < len && now - this.lastStepMs >= 35L) {
                    ++this.animPos;
                    this.lastStepMs = now;
                }
                if (this.animPos < len) break;
                this.animState = 1;
                this.holdUntilMs = now + 1200L;
                break;
            }
            case 1: {
                if (now < this.holdUntilMs) break;
                this.animState = 2;
                this.animPos = len;
                this.lastStepMs = now;
                break;
            }
            case 2: {
                if (this.animPos > 0 && now - this.lastStepMs >= 25L) {
                    --this.animPos;
                    this.lastStepMs = now;
                }
                if (this.animPos > 0) break;
                this.animState = 0;
                this.animPos = 0;
                this.lastStepMs = now;
            }
        }
        StringBuilder sb = new StringBuilder(len);
        for (int i2 = 0; i2 < len; ++i2) {
            boolean showReal;
            char real = BASE.charAt(i2);
            if (real == ' ') {
                sb.append(' ');
                continue;
            }
            if (this.animState == 0 || this.animState == 1) {
                showReal = i2 < this.animPos;
            } else {
                boolean bl = showReal = i2 < this.animPos;
            }
            if (showReal) {
                sb.append(real);
                continue;
            }
            int idx = (int)Math.abs((this.noiseSeed * 31L + (long)i2 * 1337L) % (long)CHARS.length());
            sb.append(CHARS.charAt(idx));
        }
        return sb.toString();
    }

    @Inject(method={"getWindowTitle"}, at={@At(value="HEAD")}, cancellable=true)
    private void getWindowTitle(CallbackInfoReturnable<String> cir) {
        cir.setReturnValue((Object)this.buildTitle());
    }

    @Inject(method={"render"}, at={@At(value="HEAD")})
    private void render(boolean tick, CallbackInfo ci) {
        MinecraftClient mc = (MinecraftClient)this;
        GLFW.glfwSetWindowTitle((long)mc.getWindow().getHandle(), (CharSequence)this.buildTitle());
        long timeNano = Util.getMeasuringTimeNano();
        long deltaTime = timeNano - this.lastHookTime;
        this.accumulatedCalls += (int)(deltaTime / 4166666L);
        this.lastHookTime += (long)this.accumulatedCalls * 4166666L;
        this.accumulatedCalls = Math.min(this.accumulatedCalls, 240);
        while (this.accumulatedCalls > 0) {
            new EventGameUpdate().post();
            --this.accumulatedCalls;
        }
    }

    @Inject(method={"<init>"}, at={@At(value="NEW", target="(Lnet/minecraft/client/MinecraftClient;Ljava/io/File;)Lnet/minecraft/client/option/GameOptions;")})
    private void initOptions(RunArgs args, CallbackInfo ci) {
        new EventMinecraftInit().post();
    }

    @Inject(method={"tick"}, at={@At(value="HEAD")})
    private void tick(CallbackInfo ci) {
        new EventTick().post();
    }

    @Inject(method={"tick"}, at={@At(value="RETURN")})
    private void tickEnd(CallbackInfo ci) {
        new EventTickEnd().post();
        BaritoneAntiStuck.tick();
    }

    @Inject(method={"doItemUse"}, at={@At(value="HEAD")}, cancellable=true)
    private void fixAutoExplosion(CallbackInfo ci) {
        if (Instance.get(AutoExplosion.class).getTicksToDisableRightClicks() > 0) {
            ci.cancel();
        }
    }
}

