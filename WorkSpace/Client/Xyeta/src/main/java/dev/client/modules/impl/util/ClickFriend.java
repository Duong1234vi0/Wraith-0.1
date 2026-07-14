package dev.client.modules.impl.util;

import dev.client.WildClient;
import dev.client.event.classes.ClickEvent;
import dev.client.event.interfaces.IClickaable;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleInfo;
import dev.client.modules.settings.Setting;
import dev.client.modules.settings.impl.KeySetting;
import dev.client.util.IUtil;
import dev.client.util.math.TimerUtil;
import dev.client.util.player.ChatUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

@Environment(EnvType.CLIENT)
public class ClickFriend extends Module implements IUtil, IClickaable {
   private final TimerUtil timerUtil = new TimerUtil();
   private final KeySetting bind = ((KeySetting)(new KeySetting()).name("Bind")).value(0);

   public ClickFriend() {
      super(new ModuleInfo("ClickFriend", Category.UTIL, "Добавляет наведенного игрока в клиентские друзья по нажатию кнопки"));
      this.addSetting(new Setting[]{this.bind});
   }

   public void onClick(ClickEvent event) {
      if (mc.currentScreen == null && event.getAction() == 1 && event.getKey() == this.bind.getValue()) {
         Entity entity = mc.targetedEntity;
         if (entity != null && entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)entity;
            if (this.timerUtil.isReached(250L)) {
               boolean deleted;
               if (WildClient.INSTANCE.getFriendManager().isFriend(player.getNameForScoreboard())) {
                  WildClient.INSTANCE.getFriendManager().removeFriend(player.getNameForScoreboard());
                  deleted = true;
               } else {
                  deleted = false;
                  WildClient.INSTANCE.getFriendManager().addFriend(player.getNameForScoreboard());
               }

               String text = deleted ? "Игрок " + player.getNameForScoreboard() + " успешно удален из друзей!" : "Игрок " + player.getNameForScoreboard() + " успешно добавлен в друзья!";
               ChatUtil.addMessage(text);
               this.timerUtil.reset();
            }
         }
      }

   }
}
