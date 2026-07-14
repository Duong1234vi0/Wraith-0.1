package sky.core.modules.impl.miscellaneous;

import com.adl.nativeprotect.Native;
import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SChatPacket;
import net.minecraft.util.text.TextFormatting;
import sky.core.events.EventPacket;
import sky.core.modules.Category;
import sky.core.modules.Module;

public class AutoMessage extends Module {
   private static final String[] INSULT_MESSAGES = new String[]{"Гетай StichDLC,лучший free чит", "Удаляй нахуй свою пасточку на коклеаре и покупай стич нах", "Please buy Stich, not chatgpt Client.!", "Опять упал в колени и скушал мою сперму потому что ты не был с лучшим читом Stich", "Веревочка + мыло = суицыд, ну а если не хочешь гетай Stich", "Соси мне дальше уёбок с коклеаром купи лучше скайкор дура", "Ты будешь сосать если выбрал пастаклир, а не Stich!", "Пора в пастель пока бабка не отпиздила тебя как субо", "Пиздуй оправдоваться якобы у тебя не нупокакал длц", "Придумал тебе кличку (сосуякин) как тебе? если заебок напиши выпиши чот дура", "Пойми сын дуры Stich непобедить.", "Выебан лучшим читом Stich!", "get good Stich!", "Хочешь чтобы тебя не ебали как сейчас я тебя? - Покупай Stich", "полетел на спавн хуесос", "Пойми что Stich убил тебя в 0", "У тебя есть сиська у меня пиписька и если мы будем рядом то нам будет заебисько!", "Пиздуй лизать пизду как даун с подворотни у своей матери,сквиртовой шлюхи,ссыт тебе в ебало"};
   private final Random random = new Random();

   public AutoMessage() {
      super("AutoMessage", "Автоматические оскорбления после убийства", Category.Miscellaneous);
   }

   @EventTarget
   public void onPacket(EventPacket event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null && event.isReceive()) {
         IPacket var3 = event.getPacket();
         if (var3 instanceof SChatPacket) {
            SChatPacket chatPacket = (SChatPacket)var3;
            String var7 = chatPacket.getChatComponent().getString();
            String message = TextFormatting.getTextWithoutFormattingCodes(var7);
            if (message != null) {
               if (message.contains("Вы убили")) {
                  String victimName = this.extractPlayerNameFromKillMessage(message);
                  if (victimName != null && !victimName.isEmpty()) {
                     String insult = INSULT_MESSAGES[this.random.nextInt(INSULT_MESSAGES.length)];
                     var10000 = mc;
                     Minecraft.player.sendChatMessage("! -ezz " + victimName + " " + insult);
                  }
               }

            }
         }
      }
   }

   @Native
   private String extractPlayerNameFromKillMessage(String message) {
      int killIndex = message.toLowerCase().indexOf("вы убили");
      if (killIndex == -1) {
         return null;
      } else {
         String afterKill = message.substring(killIndex + "Вы убили".length()).trim();
         if (afterKill.isEmpty()) {
            return null;
         } else {
            afterKill = afterKill.replaceAll("^[!.,:;\\s]+", "");
            String[] parts = afterKill.split("\\s+");
            if (parts.length == 0) {
               return null;
            } else {
               String name = parts[0].trim().replaceAll("[!.,:;]+$", "");
               return name.isEmpty() ? null : name;
            }
         }
      }
   }
}
