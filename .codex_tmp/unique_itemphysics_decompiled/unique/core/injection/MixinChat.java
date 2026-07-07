package unique.core.injection;

import java.util.concurrent.atomic.AtomicReference;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_7594;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import unique.core.core.Managers;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.modules.misc.NameProtect;

@Mixin({class_7594.class})
public abstract class MixinChat {
   @Inject(
      method = {"onGameMessage"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onGameMessage(class_2561 message, boolean overlay, CallbackInfo ci) {
      if (ModuleManager.nameProtect != null && ModuleManager.nameProtect.isEnabled() && !overlay) {
         class_310 mc = class_310.method_1551();
         if (mc.field_1724 != null) {
            String originalContent = message.getString();
            AtomicReference<String> newContent = new AtomicReference<>(originalContent);
            if (newContent.get().contains("SD_AkiraExploit")) {
               newContent.set(newContent.get().replace("SD_AkiraExploit", "Admin -> Secret"));
            }

            String myName = mc.method_1548().method_1676();
            if (!myName.equalsIgnoreCase("SD_AkiraExploit") && newContent.get().contains(myName)) {
               newContent.set(newContent.get().replace(myName, "User -> Universe"));
            }

            if (NameProtect.hideFriends.getValue()) {
               for (String friend : Managers.FRIEND.getFriends()) {
                  if (newContent.get().contains(friend)) {
                     newContent.set(newContent.get().replace(friend, "User -> Universe"));
                  }
               }
            }

            if (!newContent.get().equals(originalContent)) {
               mc.field_1705.method_1743().method_1812(class_2561.method_43470(newContent.get()));
               ci.cancel();
            }
         }
      }
   }
}
