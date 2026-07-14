package sky.core.modules.impl.movement;

import com.adl.nativeprotect.Native;
import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SChatPacket;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import sky.core.events.EventPacket;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;

public class AntiAfk extends Module {
   private String savedAnarchyId = null;
   private int reconnectState = 0;
   private int timer = 0;

   public AntiAfk() {
      super("AntiAfk", "Reconnect AutoBuy", Category.Movement);
   }

   @Native
   public void onEnable() {
      super.onEnable();
      this.reconnectState = 0;
      this.timer = 0;
      this.parseScoreboard();
   }

   @EventTarget
   @Native
   public void onUpdate(EventUpdate e) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         if (this.reconnectState == 0) {
            this.parseScoreboard();
         } else {
            this.handleReconnectSequence();
         }

      }
   }

   @EventTarget
   @Native
   public void onPacket(EventPacket e) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         IPacket packet = e.getPacket();
         if (packet instanceof SChatPacket) {
            SChatPacket chatPacket = (SChatPacket)packet;
            String text = chatPacket.getChatComponent().getString();
            String cleanText = text.replaceAll("§.", "").trim();
            if ((cleanText.contains("недоступна в режиме AFK") || cleanText.contains("AFK")) && this.reconnectState == 0 && this.savedAnarchyId != null) {
               this.reconnectState = 1;
               this.timer = 0;
            }
         }

      }
   }

   @Native
   private void handleReconnectSequence() {
      ++this.timer;
      Minecraft var10000;
      switch (this.reconnectState) {
         case 1:
            var10000 = mc;
            Minecraft.player.sendChatMessage("/hub");
            this.timer = 0;
            this.reconnectState = 2;
            break;
         case 2:
            if (this.timer > 60) {
               this.timer = 0;
               this.reconnectState = 3;
            }
            break;
         case 3:
            var10000 = mc;
            Minecraft.player.sendChatMessage("/an" + this.savedAnarchyId);
            this.timer = 0;
            this.reconnectState = 4;
            break;
         case 4:
            if (this.timer > 100) {
               this.timer = 0;
               this.reconnectState = 5;
            }
            break;
         case 5:
            var10000 = mc;
            Minecraft.player.sendChatMessage("/ah");
            this.timer = 0;
            this.reconnectState = 0;
      }

   }

   @Native
   private void parseScoreboard() {
      try {
         if (mc.world.getScoreboard() != null) {
            Scoreboard sb = mc.world.getScoreboard();
            ScoreObjective obj = sb.getObjectiveInDisplaySlot(1);
            if (obj != null) {
               String title = obj.getDisplayName().getString();
               String cleanTitle = title.replaceAll("§.", "").trim();
               Pattern pattern = Pattern.compile("Анархия-(\\d+)");
               Matcher matcher = pattern.matcher(cleanTitle);
               if (matcher.find()) {
                  String id = matcher.group(1);
                  if (!id.equals(this.savedAnarchyId)) {
                     this.savedAnarchyId = id;
                  }
               }
            }
         }
      } catch (Exception var8) {
      }

   }
}
