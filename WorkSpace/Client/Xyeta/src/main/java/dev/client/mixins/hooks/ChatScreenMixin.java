package dev.client.mixins.hooks;

import dev.client.WildClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin({ChatScreen.class})
public class ChatScreenMixin {
   @Inject(
      method = {"render"},
      at = {@At("HEAD")}
   )
   void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      WildClient.INSTANCE.getDraggableManager().draggableHook(mouseX, mouseY, context);
   }

   @Inject(
      method = {"mouseClicked"},
      at = {@At("HEAD")}
   )
   void mouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
      WildClient.INSTANCE.getDraggableManager().mouseClickHook(button, mouseX, mouseY);
   }
}
