package unique.core.core.manager.client;

import com.google.common.collect.Lists;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_124;
import net.minecraft.class_332;
import org.apache.commons.lang3.SystemUtils;
import unique.core.UniqueCore;
import unique.core.core.manager.IManager;
import unique.core.features.cmd.Command;
import unique.core.features.modules.client.Notifications;
import unique.core.gui.notification.Notification;

public class NotificationManager implements IManager {
   private final List<Notification> notifications = new ArrayList<>();
   private TrayIcon trayIcon;

   public void publicity(String title, String content, int second, Notification.Type type) {
      if (ModuleManager.notifications.mode.getValue() == Notifications.Mode.Text) {
         Command.sendMessage(class_124.field_1080 + "[" + class_124.field_1064 + title + class_124.field_1080 + "] " + type.getColor() + content);
      }

      if (!mc.method_1569()) {
         this.nativeNotification(content, title);
      }

      this.notifications.add(new Notification(title, content, type, second * 1000));
   }

   public void onRender2D(class_332 context) {
      if (ModuleManager.notifications.isEnabled()) {
         float startY = isDefault() ? mc.method_22683().method_4502() - 36.0F : mc.method_22683().method_4502() / 2.0F + 25.0F;
         if (this.notifications.size() > 8) {
            this.notifications.removeFirst();
         }

         this.notifications.removeIf(Notification::shouldDelete);

         for (Notification n : Lists.newArrayList(this.notifications)) {
            startY = (float)(startY - n.getHeight() - 3.0);
            n.renderShaders(context.method_51448(), startY + (isDefault() ? 0 : this.notifications.size() * 16));
            n.render(context.method_51448(), startY + (isDefault() ? 0 : this.notifications.size() * 16));
         }
      }
   }

   public void onUpdate() {
      if (ModuleManager.notifications.isEnabled()) {
         this.notifications.forEach(Notification::onUpdate);
      }
   }

   public static boolean isDefault() {
      return ModuleManager.notifications.mode.getValue() == Notifications.Mode.Default;
   }

   private void nativeNotification(String message, String title) {
      if (SystemUtils.IS_OS_WINDOWS) {
         this.windows(message, title);
      } else if (SystemUtils.IS_OS_LINUX) {
         this.linux(message);
      } else if (SystemUtils.IS_OS_MAC) {
         this.mac(message);
      } else {
         UniqueCore.LOGGER.error("Unsupported OS: {}", SystemUtils.OS_NAME);
      }
   }

   private void windows(String message, String title) {
      if (SystemTray.isSupported()) {
         try {
            if (this.trayIcon == null) {
               SystemTray tray = SystemTray.getSystemTray();
               Image image = Toolkit.getDefaultToolkit().createImage("resources/icon.png");
               this.trayIcon = new TrayIcon(image, "UniqueCore");
               this.trayIcon.setImageAutoSize(true);
               this.trayIcon.setToolTip("UniqueCore");
               tray.add(this.trayIcon);
            }

            this.trayIcon.displayMessage(title, message, MessageType.INFO);
         } catch (Exception var5) {
            UniqueCore.LOGGER.error(var5.getMessage());
         }
      } else {
         UniqueCore.LOGGER.error("SystemTray is not supported");
      }
   }

   private void mac(String message) {
      ProcessBuilder processBuilder = new ProcessBuilder();
      processBuilder.command("osascript", "-e", "display notification \"" + message + "\" with title \"UniqueCore\"");

      try {
         processBuilder.start();
      } catch (IOException var4) {
         UniqueCore.LOGGER.error(var4.getMessage());
      }
   }

   private void linux(String message) {
      ProcessBuilder processBuilder = new ProcessBuilder();
      processBuilder.command("notify-send", "-a", "UniqueCore", message);

      try {
         processBuilder.start();
      } catch (IOException var4) {
         UniqueCore.LOGGER.error(var4.getMessage());
      }
   }
}
