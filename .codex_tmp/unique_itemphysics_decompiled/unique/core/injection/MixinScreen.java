package unique.core.injection;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.logging.LogUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_2583;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_437;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import unique.core.core.Managers;
import unique.core.core.manager.client.CommandManager;
import unique.core.core.manager.client.ModuleManager;
import unique.core.core.manager.client.ProxyManager;
import unique.core.events.impl.ClientClickEvent;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.ClientSettings;
import unique.core.gui.misc.DialogScreen;
import unique.core.utility.math.MathUtility;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.TextureStorage;

@Mixin({class_437.class})
public abstract class MixinScreen {
   @Shadow
   public abstract void method_25423(class_310 var1, int var2, int var3);

   @Inject(
      method = {"handleTextClick"},
      at = {@At(
         value = "INVOKE",
         target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;)V",
         ordinal = 1,
         remap = false
      )},
      cancellable = true
   )
   private void onRunCommand(class_2583 style, CallbackInfoReturnable<Boolean> cir) {
      if (Objects.requireNonNull(style.method_10970()) instanceof ClientClickEvent clientClickEvent
         && clientClickEvent.method_10844().startsWith(Managers.COMMAND.getPrefix())) {
         try {
            CommandManager manager = Managers.COMMAND;
            manager.getDispatcher().execute(style.method_10970().method_10844().substring(Managers.COMMAND.getPrefix().length()), manager.getSource());
            cir.setReturnValue(true);
         } catch (CommandSyntaxException var5) {
         }
      }
   }

   @Inject(
      method = {"filesDragged"},
      at = {@At("HEAD")}
   )
   public void filesDragged(List<Path> paths, CallbackInfo ci) {
      String configPath = paths.get(0).toString();
      File cfgFile = new File(configPath);
      String fileName = cfgFile.getName();
      if (fileName.contains(".th")) {
         DialogScreen dialogScreen = new DialogScreen(
            TextureStorage.questionPic,
            ClientSettings.isRu() ? "Обнаружен конфиг!" : "Config Detected!",
            ClientSettings.isRu() ? "Ты действительно хочешь загрузить " + fileName + "?" : "Are You Sure You Want To Load " + fileName + "?",
            ClientSettings.isRu() ? "Да" : "Yes",
            ClientSettings.isRu() ? "Нет" : "No",
            () -> {
               Managers.MODULE.onUnload("none");
               Managers.CONFIG.load(cfgFile);
               Managers.MODULE.onLoad("none");
               Module.mc.method_1507(null);
            },
            () -> Module.mc.method_1507(null)
         );
         Module.mc.method_1507(dialogScreen);
      } else if (fileName.contains(".txt")) {
         DialogScreen dialogScreen2 = new DialogScreen(
            TextureStorage.questionPic,
            ClientSettings.isRu() ? "Обнаружен текстовый файл!" : "Text File Detected!",
            ClientSettings.isRu() ? "Импортировать файл " + fileName + " как" : "Import file " + fileName + " as",
            ClientSettings.isRu() ? "Прокси" : "Proxies",
            ClientSettings.isRu() ? "Забить" : "Cancel",
            () -> {
               try (BufferedReader reader = new BufferedReader(new FileReader(cfgFile))) {
                  while (reader.ready()) {
                     String[] line = reader.readLine().split(":");
                     String ip = line[0];
                     String port = line[1];
                     String login = line[2];
                     String password = line[3];
                     int p = 80;

                     try {
                        p = Integer.parseInt(port);
                     } catch (Exception var10) {
                        LogUtils.getLogger().warn(var10.getMessage());
                     }

                     Managers.PROXY.addProxy(new ProxyManager.ThProxy("Proxy" + (int)MathUtility.random(0.0F, 10000.0F), ip, p, login, password));
                  }
               } catch (Exception var12) {
               }

               Module.mc.method_1507(null);
            },
            () -> Module.mc.method_1507(null)
         );
         Module.mc.method_1507(dialogScreen2);
      }
   }

   @Inject(
      method = {"render"},
      at = {@At("HEAD")}
   )
   private void onRender(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      if (Module.mc.field_1687 == null) {
         int w = Module.mc.method_22683().method_4486();
         int h = Module.mc.method_22683().method_4502();
         context.method_25290(TextureStorage.backmenu, 0, 0, 0.0F, 0.0F, w, h, w, h);
      }
   }

   @Inject(
      method = {"renderPanoramaBackground"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void renderPanoramaBackgroundHook(class_332 context, float delta, CallbackInfo ci) {
      if (ClientSettings.customPanorama.getValue() && Module.mc.field_1687 == null) {
         ci.cancel();
         Render2DEngine.drawMainMenuShader(context.method_51448(), 0.0F, 0.0F, Module.mc.method_22683().method_4486(), Module.mc.method_22683().method_4502());
      }
   }

   @Inject(
      method = {"renderInGameBackground"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderInGameBackground(CallbackInfo info) {
      if (Module.mc.field_1687 == null) {
         info.cancel();
      } else if (ModuleManager.noRender.isEnabled() && ModuleManager.noRender.disableGuiBackGround.getValue()) {
         info.cancel();
      }
   }

   @Inject(
      method = {"renderBackground"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderCustomMenuBackground(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      if (Module.mc.field_1687 == null) {
         int w = Module.mc.method_22683().method_4486();
         int h = Module.mc.method_22683().method_4502();
         context.method_25290(TextureStorage.backmenu, 0, 0, 0.0F, 0.0F, w, h, w, h);
         ci.cancel();
      }
   }
}
