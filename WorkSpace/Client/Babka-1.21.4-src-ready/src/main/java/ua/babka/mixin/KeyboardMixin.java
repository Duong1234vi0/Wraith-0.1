package ua.babka.mixin;

import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.babka.Babka;
import ua.babka.event.EventKeyInput;

@Mixin(value={Keyboard.class})
public class KeyboardMixin {
    @Inject(method={"onKey"}, at={@At(value="HEAD")})
    private void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        if (MinecraftClient.getInstance().currentScreen != null || key == -1 || action == 2) {
            return;
        }
        Babka.getInstance().getEventBus().post((Object)new EventKeyInput(key, action));
    }
}

