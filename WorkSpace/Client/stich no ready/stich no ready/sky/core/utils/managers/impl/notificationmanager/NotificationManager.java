package sky.core.utils.managers.impl.notificationmanager;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import sky.core.SkyCore;
import sky.core.events.EventRender2D;
import sky.core.modules.impl.visuals.Interface;
import sky.core.utils.Wrapper;
import sky.core.utils.managers.impl.notificationmanager.impl.FontNotification;
import sky.core.utils.managers.impl.notificationmanager.impl.ImageNotification;
import sky.core.utils.managers.impl.notificationmanager.impl.StackNotification;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class NotificationManager implements Wrapper {
   private static final List notifications = new LinkedList();

   private static void addNotification(AbstractNotification notification) {
      notification.getYAnimation().setValue((float)mw.getScaledHeight() / 2.0F + 21.0F);
      notification.getYAnimation().update(13.5F);
      notifications.add(0, notification);
      if (notifications.size() > 10) {
         for(int i = 10; i < notifications.size(); ++i) {
            ((AbstractNotification)notifications.get(i)).expireNow();
         }
      }

   }

   public static boolean hasActiveNotifications() {
      return !notifications.isEmpty();
   }

   public static void addNotification(ResourceLocation image, ITextComponent message) {
      addNotification(new ImageNotification(image, message));
   }

   public static void addNotification(ResourceLocation image, ITextComponent message, int color) {
      addNotification(new ImageNotification(image, message, color));
   }

   public static void addNotification(ItemStack stack, ITextComponent message) {
      addNotification(new StackNotification(stack, message));
   }

   public static void addNotification(ItemStack stack, ITextComponent message, boolean autoSwapStyle) {
      addNotification(new StackNotification(stack, message, autoSwapStyle));
   }

   public static void addNotification(String categoryIcon, String message, int color) {
      addNotification(new FontNotification(categoryIcon, message, color));
   }

   @EventTarget
   public void render(EventRender2D.Pre event) {
      boolean ordinaryMode = Interface.isNewHud();
      float startY = (float)mw.getScaledHeight() / 2.0F + (ordinaryMode ? 15.0F : 21.0F);
      notifications.removeIf((n) -> {
         return n.isExpired() && n.getAlphaAnimation().isDone();
      });

      for(Iterator var4 = notifications.iterator(); var4.hasNext(); startY += ordinaryMode ? 21.0F : 13.5F) {
         AbstractNotification notification = (AbstractNotification)var4.next();
         float width;
         FontNotification fontNotification;
         String displayMessage;
         if (ordinaryMode) {
            if (!(notification instanceof StackNotification) && !(notification instanceof ImageNotification)) {
               if (notification instanceof FontNotification) {
                  fontNotification = (FontNotification)notification;
                  displayMessage = fontNotification.getMessage().getString();
                  String prefix = "";
                  String moduleName = displayMessage;
                  String status = "";
                  if (displayMessage.startsWith("Module ")) {
                     prefix = "Module ";
                     moduleName = displayMessage.substring(7);
                  }

                  int enabledIdx = moduleName.indexOf(" enabled");
                  int disabledIdx = moduleName.indexOf(" disabled");
                  if (enabledIdx != -1) {
                     status = moduleName.substring(enabledIdx);
                     moduleName = moduleName.substring(0, enabledIdx);
                  } else if (disabledIdx != -1) {
                     status = moduleName.substring(disabledIdx);
                     moduleName = moduleName.substring(0, disabledIdx);
                  }

                  float textWidth = Fonts.sfregular[12].getWidth(prefix) + Fonts.sfregular[12].getWidth(moduleName) + Fonts.sfregular[12].getWidth(status);
                  width = 19.0F + textWidth + 8.0F;
               } else {
                  width = Fonts.sfregular[12].getWidth(notification.getMessage().getString()) + 24.0F;
               }
            } else {
               label182: {
                  float textWidth = Fonts.sfregular[12].getWidth(notification.getMessage().getString());
                  if (notification instanceof StackNotification) {
                     StackNotification stackNotification = (StackNotification)notification;
                     if (stackNotification.isAutoSwapStyle()) {
                        width = 20.0F + textWidth + 6.0F;
                        break label182;
                     }
                  }

                  width = 22.0F + textWidth + 8.0F;
               }
            }
         } else if (!(notification instanceof ImageNotification) && !(notification instanceof StackNotification)) {
            fontNotification = (FontNotification)notification;
            displayMessage = fontNotification.getCategoryIcon();
            float iconWidth = displayMessage != null && !displayMessage.isEmpty() ? Fonts.skycore[12].getWidth(displayMessage) : 7.0F;
            width = iconWidth + 4.0F + Fonts.sfregular[12].getWidth(notification.getMessage().getString()) + 16.0F;
         } else {
            width = Fonts.sf_medium[12].getWidth(notification.getMessage().getString()) + 24.0F;
         }

         float x = ((float)mw.getScaledWidth() - width) / 2.0F;
         if (SkyCore.getInstance().getModuleManager().getModule(Interface.class).isEnabled() && Interface.elements.is("Уведомления")) {
            RenderUtil.beginInterfaceRounding();
            RenderUtil.beginHudLiquidGlass();

            try {
               notification.render(x, startY, event.getStack());
            } finally {
               RenderUtil.endHudLiquidGlass();
               RenderUtil.endInterfaceRounding();
            }
         }
      }

   }
}
