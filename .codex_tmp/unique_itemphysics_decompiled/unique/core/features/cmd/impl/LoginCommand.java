package unique.core.features.cmd.impl;

import com.mojang.authlib.minecraft.UserApiService;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.Optional;
import net.minecraft.class_2172;
import net.minecraft.class_320;
import net.minecraft.class_4844;
import net.minecraft.class_5520;
import net.minecraft.class_7569;
import net.minecraft.class_7574;
import net.minecraft.class_7853;
import net.minecraft.class_320.class_321;
import org.jetbrains.annotations.NotNull;
import unique.core.features.cmd.Command;
import unique.core.features.modules.client.ClientSettings;
import unique.core.injection.accesors.IMinecraftClient;

public class LoginCommand extends Command {
   public LoginCommand() {
      super("login");
   }

   @Override
   public void executeBuild(@NotNull LiteralArgumentBuilder<class_2172> builder) {
      builder.then(arg("name", StringArgumentType.word()).executes(context -> {
         this.login((String)context.getArgument("name", String.class));
         sendMessage((ClientSettings.isRu() ? "Аккаунт изменен на: " : "Switched account to: ") + mc.method_1548().method_1676());
         return 1;
      }));
      builder.executes(context -> {
         sendMessage(ClientSettings.isRu() ? "Использование: .login <nickname>" : "Usage: .login <nickname>");
         return 1;
      });
   }

   public void login(String name) {
      try {
         this.setSession(new class_320(name, class_4844.method_43344(name), "", Optional.empty(), Optional.empty(), class_321.field_1988));
      } catch (Exception var3) {
         sendMessage((ClientSettings.isRu() ? "Неверное имя! " : "Incorrect username! ") + var3);
      }
   }

   public void setSession(class_320 session) {
      IMinecraftClient mca = (IMinecraftClient)mc;
      mca.setSessionT(session);
      mc.method_53462().getProperties().clear();
      UserApiService apiService = UserApiService.OFFLINE;
      mca.setUserApiService(apiService);
      mca.setSocialInteractionsManagerT(new class_5520(mc, apiService));
      mca.setProfileKeys(class_7853.method_46532(apiService, session, mc.field_1697.toPath()));
      mca.setAbuseReportContextT(class_7574.method_44599(class_7569.method_44586(), apiService));
   }
}
