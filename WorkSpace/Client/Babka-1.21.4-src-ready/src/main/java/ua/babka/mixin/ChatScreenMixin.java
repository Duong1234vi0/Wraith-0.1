package ua.babka.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ua.babka.Babka;
import ua.babka.module.impl.render.Interface;
import ua.babka.ui.drag.DragManager;

@Mixin(value={ChatScreen.class})
public class ChatScreenMixin
extends Screen {
    protected ChatScreenMixin(Text title) {
        super(title);
    }

    @Inject(method={"mouseClicked"}, at={@At(value="HEAD")}, cancellable=true)
    private void onMouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        Interface iface = Babka.getInstance().getModuleStorage().get(Interface.class);
        if (iface != null && iface.isEnabled() && iface.handleHudContextClick(mouseX, mouseY, button)) {
            cir.setReturnValue((Object)true);
        }
    }

    @Inject(method={"mouseClicked"}, at={@At(value="TAIL")})
    private void onMouseClickedTail(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        DragManager.onClickAll(button);
    }

    @Inject(method={"render"}, at={@At(value="RETURN")})
    public void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        DragManager.onDrawAll();
        Interface iface = Babka.getInstance().getModuleStorage().get(Interface.class);
        if (iface != null && iface.isEnabled()) {
            MinecraftClient mc;
            iface.renderHudContextMenu(context, mouseX, mouseY);
            if (!iface.isContextMenuActive() && (mc = MinecraftClient.getInstance()).getWindow() != null) {
                String hint = "нажми на меня чтоб настроить";
                float sw = mc.getWindow().getScaledWidth();
                float hintW = mc.textRenderer.getWidth(hint);
                float hintX = (sw - hintW) / 2.0f;
                context.drawText(mc.textRenderer, hint, (int)hintX, 8, 0x55FFFFFF, false);
            }
        }
    }

    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        DragManager.onReleaseAll(button);
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Inject(method={"removed"}, at={@At(value="HEAD")})
    private void removed(CallbackInfo ci) {
        DragManager.onReleaseAll(0);
    }
}

