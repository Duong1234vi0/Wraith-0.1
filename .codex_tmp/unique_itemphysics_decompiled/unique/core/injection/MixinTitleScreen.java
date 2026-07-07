package unique.core.injection;

import java.net.URI;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_124;
import net.minecraft.class_156;
import net.minecraft.class_2561;
import net.minecraft.class_3675;
import net.minecraft.class_410;
import net.minecraft.class_437;
import net.minecraft.class_442;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import unique.core.UniqueCore;
import unique.core.core.manager.client.ModuleManager;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.ClientSettings;
import unique.core.gui.mainmenu.MainMenuScreen;
import unique.core.gui.misc.DialogScreen;
import unique.core.utility.render.TextureStorage;

@Mixin({class_442.class})
public class MixinTitleScreen extends class_437 {
   protected MixinTitleScreen(class_2561 title) {
      super(title);
   }

   @Inject(
      method = {"init"},
      at = {@At("RETURN")}
   )
   public void postInitHook(CallbackInfo ci) {
      if (ClientSettings.customMainMenu.getValue() && !MainMenuScreen.getInstance().confirm && ModuleManager.clickGui.getBind().getKey() != -1) {
         Module.mc.method_1507(MainMenuScreen.getInstance());
      }

      if (ModuleManager.clickGui.getBind().getKey() == -1) {
         DialogScreen dialogScreen2 = new DialogScreen(
            TextureStorage.cutie,
            ClientSettings.isRu() ? "Спасибо что скачали UniqueCore CosmicDLC!" : "Thank You For Downloading UniqueCore CosmicDLC!",
            ClientSettings.isRu() ? "Меню с функциями клиента открывается на клавишу - P" : "Menu With Client Modules Is Opened With The Key - P",
            ClientSettings.isRu() ? "Зайти в майн" : "Join On minecraft",
            ClientSettings.isRu() ? "Закрыть майн" : "Close Minecraft",
            () -> {
               ModuleManager.clickGui.setBind(class_3675.method_15981("key.keyboard.p").method_1444(), false, false);
               Module.mc.method_1507(MainMenuScreen.getInstance());
            },
            () -> {
               ModuleManager.clickGui.setBind(class_3675.method_15981("key.keyboard.p").method_1444(), false, false);
               Module.mc.method_1490();
            }
         );
         DialogScreen dialogScreen1 = new DialogScreen(TextureStorage.questionPic, "Hello!", "What's Your Language?", "Русский", "English", () -> {
            ClientSettings.language.setValue(ClientSettings.Language.RU);
            Module.mc.method_1507(dialogScreen2);
         }, () -> {
            ClientSettings.language.setValue(ClientSettings.Language.ENG);
            Module.mc.method_1507(dialogScreen2);
         });
         Module.mc.method_1507(dialogScreen1);
      }

      if (UniqueCore.isOutdated && !FabricLoader.getInstance().isDevelopmentEnvironment()) {
         Module.mc
            .method_1507(
               new class_410(
                  confirm -> {
                     if (confirm) {
                        class_156.method_668().method_673(URI.create("https://discord.gg/AEyetataPB"));
                     } else {
                        Module.mc.method_1490();
                     }
                  },
                  class_2561.method_30163(class_124.field_1061 + "You Are Using An Outdated Version Of UniqueCore CosmicDLC"),
                  class_2561.method_30163("Please Update To The Latest Release"),
                  class_2561.method_30163("Download"),
                  class_2561.method_30163("Quit Game")
               )
            );
      }
   }
}
