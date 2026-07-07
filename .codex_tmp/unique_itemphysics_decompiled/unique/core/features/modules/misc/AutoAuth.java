package unique.core.features.modules.misc;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_124;
import net.minecraft.class_7439;
import org.apache.commons.lang3.RandomStringUtils;
import org.jetbrains.annotations.NotNull;
import unique.core.core.Managers;
import unique.core.events.impl.PacketEvent;
import unique.core.features.modules.Module;
import unique.core.features.modules.client.ClientSettings;
import unique.core.gui.notification.Notification;
import unique.core.setting.Setting;

public final class AutoAuth extends Module {
   private final Setting<AutoAuth.Mode> mode = new Setting<>("Mode", AutoAuth.Mode.Custom);
   private final Setting<String> cpass = new Setting<>("Password", "12345678", v -> this.mode.getValue() == AutoAuth.Mode.Custom);
   private final Setting<Boolean> show = new Setting<>("ShowPassword", true);

   public AutoAuth() {
      super("AutoAuth", Module.Category.MISC);
   }

   @Override
   public void onEnable() {
      String warningMsg = ClientSettings.isRu()
         ? class_124.field_1061
            + "Внимание! "
            + class_124.field_1070
            + "Пароль сохраняется в конфиге, перед передачей конфига "
            + class_124.field_1061
            + " ВЫКЛЮЧИ МОДУЛЬ!"
         : class_124.field_1061
            + "Attention! "
            + class_124.field_1070
            + "The Passwords Are Stored In The Config, So Before Sharing Your Configs "
            + class_124.field_1061
            + " TOGGLE OFF THE MODULE!";
      this.sendMessage(warningMsg);
   }

   @Override
   public void onDisable() {
      this.sendMessage("Resetting Password...");
      this.cpass.setValue("");
   }

   @EventHandler
   public void onPacketReceive(@NotNull PacketEvent.Receive event) {
      if (event.getPacket() instanceof class_7439 pac && mc.method_1562() != null) {
         String password = "";
         switch ((AutoAuth.Mode)this.mode.getValue()) {
            case Custom:
               password = this.cpass.getValue();
               if (password.isEmpty()) {
                  this.sendMessage(
                     class_124.field_1061 + (ClientSettings.isRu() ? "Ошибка регистрации: Пароль пуст!" : "Registration Error: Password Is Empty!")
                  );
                  return;
               }
               break;
            case Random:
               password = RandomStringUtils.randomAlphabetic(5) + RandomStringUtils.randomPrint(5);
               break;
            case Qwerty:
               password = "Akira!";
         }

         String m = pac.comp_763().getString().toLowerCase();
         if (m.contains("/reg") || m.contains("/register") || m.contains("зарегистрируйтесь")) {
            mc.method_1562().method_45730("reg " + password + " " + password);
            if (this.show.getValue()) {
               this.sendMessage((ClientSettings.isRu() ? "Твой пароль: " : "Your Password: ") + class_124.field_1061 + password);
            }

            Managers.NOTIFICATION
               .publicity("AutoAuth", ClientSettings.isRu() ? "Выполнена регистрация!" : "Registration Completed!", 4, Notification.Type.SUCCESS);
         } else if (m.contains("авторизуйтесь") || m.contains("/l")) {
            mc.method_1562().method_45730("login " + password);
            Managers.NOTIFICATION.publicity("AutoAuth", ClientSettings.isRu() ? "Выполнен вход!" : "Logged In!", 4, Notification.Type.SUCCESS);
         }
      }
   }

   private static enum Mode {
      Custom,
      Random,
      Qwerty;
   }
}
