package dev.client.mixins.hooks;

import dev.client.WildClient;
import dev.client.event.classes.ClickEvent;
import dev.client.modules.Module;
import dev.client.util.math.TimerUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({Mouse.class})
public class MouseMixin {
   private final TimerUtil timerUtil = new TimerUtil();

   @Inject(
      method = {"onMouseButton"},
      at = {@At("HEAD")}
   )
   public void onMouseButtonHook(long window, int button, int action, int mods, CallbackInfo ci) {
      if (window == MinecraftClient.getInstance().getWindow().getHandle()) {
         int bind = button + 1330;
         WildClient.INSTANCE.getEventManager().hookEvent(new ClickEvent(bind, action));
         if (action == 0 && MinecraftClient.getInstance().currentScreen instanceof ChatScreen) {
            WildClient.INSTANCE.getDraggableManager().dragEnd();
         }

         if (action != 1) {
            return;
         }

         if (MinecraftClient.getInstance().currentScreen != null) {
            return;
         }

         for(Module module : WildClient.INSTANCE.getModuleManager().getModules()) {
            if (module.getBind() == bind) {
               module.setEnabled(!module.isEnabled());
            }
         }
      }

   }
}
