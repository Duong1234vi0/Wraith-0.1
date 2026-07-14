package sky.core.modules.impl.miscellaneous;

import com.adl.nativeprotect.Native;
import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SChatPacket;
import net.minecraft.util.text.TextFormatting;
import sky.core.SkyCore;
import sky.core.events.EventPacket;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.MultiBooleanSetting;
import sky.core.utils.math.TimeUtil;

public class AutoDuel extends Module {
   private static final Pattern pattern = Pattern.compile("^\\w{3,16}$");
   private final MultiBooleanSetting mode = new MultiBooleanSetting("Кит", new BooleanSetting[]{new BooleanSetting("Щит", true), new BooleanSetting("Шипы 3", true), new BooleanSetting("Лук", true), new BooleanSetting("Тотемы", true), new BooleanSetting("Исцеление", true), new BooleanSetting("Шары", true), new BooleanSetting("Классик", true), new BooleanSetting("Читерский рай", true), new BooleanSetting("Незерка", true)});
   private final BooleanSetting noFriends = new BooleanSetting("Пропускать друзей", true);
   private double lastPosX;
   private double lastPosY;
   private double lastPosZ;
   private final List sent = Lists.newArrayList();
   private final TimeUtil counter = new TimeUtil();
   private final TimeUtil counter2 = new TimeUtil();
   private final TimeUtil counterChoice = new TimeUtil();
   private final TimeUtil counterTo = new TimeUtil();
   private final HashMap duelTimestamps = new HashMap();

   public AutoDuel() {
      super("Auto Duel", "Автоматически вызывает игрока на дуэль", Category.Miscellaneous);
      this.addSettings(new Setting[]{this.mode, this.noFriends});
   }

   @EventTarget
   @Native
   public void update(EventUpdate e) {
      List players = this.getOnlinePlayers();
      Minecraft var10001 = mc;
      double var10000 = Math.pow(this.lastPosX - Minecraft.player.getPosX(), 2.0);
      Minecraft var10002 = mc;
      var10000 += Math.pow(this.lastPosY - Minecraft.player.getPosY(), 2.0);
      var10002 = mc;
      double distance = Math.sqrt(var10000 + Math.pow(this.lastPosZ - Minecraft.player.getPosZ(), 2.0));
      if (distance > 500.0) {
         this.toggle();
      }

      var10001 = mc;
      this.lastPosX = Minecraft.player.getPosX();
      var10001 = mc;
      this.lastPosY = Minecraft.player.getPosY();
      var10001 = mc;
      this.lastPosZ = Minecraft.player.getPosZ();
      if (this.counter2.hasTimeElapsed(80L * (long)players.size())) {
         this.sent.clear();
         this.counter2.reset();
      }

      Iterator var5 = players.iterator();

      while(true) {
         String player;
         long lastDuelTime;
         long currentTime;
         Minecraft var15;
         do {
            do {
               do {
                  do {
                     do {
                        if (!var5.hasNext()) {
                           var15 = mc;
                           Container container = Minecraft.player.openContainer;
                           if (container instanceof ChestContainer) {
                              ChestContainer chest = (ChestContainer)container;
                              Minecraft var10005;
                              if (mc.currentScreen != null && mc.currentScreen.getTitle().getString().contains("Выбор набора (1/1)")) {
                                 List slotsID = new ArrayList();
                                 int index = 0;

                                 int slotID;
                                 for(slotID = 0; slotID < ((List)this.mode.get()).size(); ++slotID) {
                                    if (!(Boolean)this.mode.getIndex(slotID).get()) {
                                       ++index;
                                    } else {
                                       slotsID.add(index);
                                       ++index;
                                    }
                                 }

                                 Collections.shuffle(slotsID);
                                 if (!slotsID.isEmpty() && this.counterChoice.hasTimeElapsed(80L)) {
                                    slotID = (Integer)slotsID.get(0);
                                    var10005 = mc;
                                    mc.playerController.windowClick(chest.windowId, slotID, 0, ClickType.QUICK_MOVE, Minecraft.player);
                                    this.counterChoice.reset();
                                 }
                              } else if (mc.currentScreen != null && mc.currentScreen.getTitle().getString().contains("Настройка поединка") && this.counterTo.hasTimeElapsed(80L)) {
                                 var10005 = mc;
                                 mc.playerController.windowClick(chest.windowId, 0, 0, ClickType.QUICK_MOVE, Minecraft.player);
                                 this.counterTo.reset();
                              }
                           }

                           return;
                        }

                        player = (String)var5.next();
                     } while(this.sent.contains(player));
                  } while(player.equals(mc.session.getProfile().getName()));
               } while(!this.counter.hasTimeElapsed(600L));
            } while((Boolean)this.noFriends.get() && SkyCore.getInstance().getFriendManager().isFriend(player));

            if (!this.duelTimestamps.containsKey(player)) {
               break;
            }

            lastDuelTime = (Long)this.duelTimestamps.get(player);
            currentTime = System.currentTimeMillis();
         } while(currentTime - lastDuelTime < 31000L);

         var15 = mc;
         Minecraft.player.sendChatMessage("/duel " + player);
         this.sent.add(player);
         this.duelTimestamps.put(player, System.currentTimeMillis());
         this.counter.reset();
      }
   }

   @EventTarget
   @Native
   public void send(EventPacket event) {
      if (event.isReceive()) {
         IPacket var3 = event.getPacket();
         if (var3 instanceof SChatPacket) {
            SChatPacket chat = (SChatPacket)var3;
            String text = TextFormatting.getTextWithoutFormattingCodes(chat.getChatComponent().getString()).toLowerCase();
            if (text.contains("начало") && text.contains("через") && text.contains("секунд!") || text.equals("дуэли » во время поединка запрещено использовать команды")) {
               this.toggle();
            }
         }

      }
   }

   private List getOnlinePlayers() {
      Minecraft var10000 = mc;
      return (List)Minecraft.player.connection.getPlayerInfoMap().stream().map(NetworkPlayerInfo::getGameProfile).map(GameProfile::getName).filter((profileName) -> {
         return pattern.matcher(profileName).matches();
      }).collect(Collectors.toList());
   }
}
