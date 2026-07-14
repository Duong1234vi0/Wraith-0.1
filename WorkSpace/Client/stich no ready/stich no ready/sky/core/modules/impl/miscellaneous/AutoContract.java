package sky.core.modules.impl.miscellaneous;

import com.adl.nativeprotect.Native;
import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SChatPacket;
import net.minecraft.network.play.server.SJoinGamePacket;
import net.minecraft.util.text.TextFormatting;
import sky.core.cmd.impl.AutoContractCommand;
import sky.core.events.EventKey;
import sky.core.events.EventPacket;
import sky.core.events.EventUpdate;
import sky.core.handlers.impl.ReallyWorldJoinHandler;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BindSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.utils.math.TimeUtil;
import sky.core.utils.misc.ChatUtil;
import sky.core.utils.misc.ServerUtil;

public class AutoContract extends Module {
   private final SliderSetting griefSelection = new SliderSetting("Гриф", 1.0F, 1.0F, 54.0F, 1.0F);
   private final BindSetting disable = new BindSetting("Клавиша отключения");
   private final TimeUtil delayedRejoinTimer = new TimeUtil();
   private boolean pendingRejoin = false;
   boolean join = false;

   public AutoContract() {
      super("AutoContract", "Автоматически ищет цель на ReallyWorld", Category.Miscellaneous);
      this.addSettings(new Setting[]{this.griefSelection, this.disable});
   }

   @Native
   public void onEnable() {
      if (AutoContractCommand.targetNickname != null) {
         ReallyWorldJoinHandler.setManualGrief(((Float)this.griefSelection.get()).intValue());
         ReallyWorldJoinHandler.startRejoin();
      }

      super.onEnable();
   }

   @EventTarget
   @Native
   public void receive(EventPacket event) {
      if (ServerUtil.isPvP()) {
         ChatUtil.addText("Вы находитесь в PVP-режиме!");
         this.toggle();
      }

      if (AutoContractCommand.targetNickname == null) {
         ChatUtil.addText("Укажите цель! .autocontract <name> | .act <name>");
         this.toggle();
      }

      if (event.getPacket() instanceof SJoinGamePacket) {
         this.join = true;
      }

      IPacket var3 = event.getPacket();
      String message;
      if (var3 instanceof SChatPacket packet) {
         message = TextFormatting.getTextWithoutFormattingCodes(packet.getChatComponent().getString());
         if (message.contains("Ваша цель") && message.contains(AutoContractCommand.targetNickname)) {
            ChatUtil.addText("Найден нужный игрок");
            this.toggle();
         }

         if (message.contains("Ваша цель") || message.contains("Не спамь!") || message.contains("Не получилось")) {
            this.join = false;
            this.pendingRejoin = true;
            this.delayedRejoinTimer.reset();
         }
      }

      var3 = event.getPacket();
      if (var3 instanceof SChatPacket packet) {
         message = TextFormatting.getTextWithoutFormattingCodes(packet.getChatComponent().getString());
         if (message.contains("К сожалению сервер переполнен") || message.contains("Подождите 20 секунд!") || message.contains("большой поток игроков")) {
            this.pendingRejoin = true;
            this.delayedRejoinTimer.reset();
         }
      }

   }

   @EventTarget
   @Native
   public void update(EventUpdate event) {
      if (this.pendingRejoin && this.delayedRejoinTimer.hasTimeElapsed(600L)) {
         ReallyWorldJoinHandler.setManualGrief(((Float)this.griefSelection.get()).intValue());
         ReallyWorldJoinHandler.startRejoin();
         this.pendingRejoin = false;
      }

      boolean check = true;
      Iterator var3 = mc.getConnection().getPlayerInfoMap().iterator();

      while(var3.hasNext()) {
         NetworkPlayerInfo info = (NetworkPlayerInfo)var3.next();
         if (info.getGameProfile().getName().equals(AutoContractCommand.targetNickname)) {
            check = false;
            break;
         }
      }

      if (this.join && !check) {
         Minecraft var10000 = mc;
         Minecraft.player.sendChatMessage("/contract get");
         this.join = false;
      }

   }

   @EventTarget
   public void onEvent(EventKey event) {
      if ((Integer)this.disable.get() == event.getKey() && event.isHold()) {
         this.toggle();
      }

   }
}
