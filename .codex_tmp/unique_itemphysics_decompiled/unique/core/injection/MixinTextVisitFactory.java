package unique.core.injection;

import net.minecraft.class_5223;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import unique.core.core.Managers;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.modules.Module;
import unique.core.features.modules.misc.NameProtect;

@Mixin({class_5223.class})
public class MixinTextVisitFactory {
   @ModifyArg(
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/text/TextVisitFactory;visitFormatted(Ljava/lang/String;ILnet/minecraft/text/Style;Lnet/minecraft/text/Style;Lnet/minecraft/text/CharacterVisitor;)Z",
         ordinal = 0
      ),
      method = {"visitFormatted(Ljava/lang/String;ILnet/minecraft/text/Style;Lnet/minecraft/text/CharacterVisitor;)Z"},
      index = 0
   )
   private static String adjustText(String text) {
      return protect(text);
   }

   private static String protect(String string) {
      if (ModuleManager.nameProtect != null && ModuleManager.nameProtect.isEnabled() && Module.mc.field_1724 != null) {
         String result = string;
         if (string.contains("SD_AkiraExploit")) {
            result = string.replace("SD_AkiraExploit", "Admin -> Secret");
         }

         String myName = Module.mc.method_1548().method_1676();
         if (!myName.equalsIgnoreCase("SD_AkiraExploit") && result.contains(myName)) {
            result = result.replace(myName, "User -> Universe");
         }

         if (NameProtect.hideFriends.getValue()) {
            for (String friend : Managers.FRIEND.getFriends()) {
               if (result.contains(friend)) {
                  result = result.replace(friend, "User -> Universe");
               }
            }
         }

         return result;
      } else {
         return string;
      }
   }
}
