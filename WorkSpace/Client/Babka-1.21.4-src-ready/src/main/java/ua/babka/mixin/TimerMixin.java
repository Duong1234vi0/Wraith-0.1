package ua.babka.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import ua.babka.util.math.TimerUtil;

@Mixin(value={MinecraftClient.class})
public abstract class TimerMixin {
    @Unique
    private long babkaLastRealTime = 0L;
    @Unique
    private long babkaFakeTime = 0L;

    @ModifyArg(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/RenderTickCounter$Dynamic;beginRenderTick(JZ)I"), index=0)
    private long modifyRenderTime(long currentTime) {
        if (TimerUtil.speed == 1.0f) {
            this.babkaLastRealTime = currentTime;
            this.babkaFakeTime = currentTime;
            return currentTime;
        }
        if (this.babkaLastRealTime == 0L) {
            this.babkaLastRealTime = currentTime;
            this.babkaFakeTime = currentTime;
            return currentTime;
        }
        long realElapsed = currentTime - this.babkaLastRealTime;
        this.babkaLastRealTime = currentTime;
        this.babkaFakeTime += (long)((double)realElapsed * (double)TimerUtil.speed);
        return this.babkaFakeTime;
    }
}

