package ua.babka.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Style;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ua.babka.event.ChatEvent;

@Mixin(value={Screen.class})
public class ScreenMixin {
    @Inject(at={@At(value="INVOKE", target="Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;)V", remap=false, ordinal=1)}, method={"handleTextClick"}, cancellable=true)
    public void handleCustomClickEvent(Style style, CallbackInfoReturnable<Boolean> cir) {
        ClickEvent clickEvent = style.getClickEvent();
        if (clickEvent == null) {
            return;
        }
        new ChatEvent(clickEvent.getValue()).post();
        cir.setReturnValue((Object)true);
        cir.cancel();
    }
}

