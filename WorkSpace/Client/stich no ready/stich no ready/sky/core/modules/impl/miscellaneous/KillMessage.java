package sky.core.modules.impl.miscellaneous;

import com.adl.nativeprotect.Native;
import com.darkmagician6.eventapi.eventapinew.EventTarget;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import sky.core.events.EventUpdate;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.impl.combat.AttackAura;

public class KillMessage extends Module {
   private final BooleanSetting soft = new BooleanSetting("Упоминать чит", true);
   private static final String[] killMessages = new String[]{"але шлюха? да-да нексус айм пастер айм NUCLEAR DLC", "Удаляй нахуй свою пасточку на нуклеаре и покупай скайкор нах", "Please buy SkyCore, not chatgptclear.!", "Опять упал в колени и скушал мою сперму потому что ты не был с лучшим читом SkyCore", "Веревочка + мыло = суицыд, ну а если не хочешь гетай skycore", "Соси мне дальше уёбок с нуклеаром купи лучше скайкор дура", "Ты будешь сосать если выбрал пастаклир, а не SkyCore!", "Пора в пастель пока бабка не отпиздила тебя как субо", "Пиздуй оправдоваться якобы у тебя не нупокакал длц", "Придумал тебе кличку (сосуякин) как тебе? если заебок напиши выпиши чот дура", "Пойми сын дуры SkyCore непобедить.", "Выебан лучшим читом SkyCore!", "get good SkyCore!", "Хочешь чтобы тебя не ебали как сейчас я тебя? - Покупай SkyCore", "полетел на спавн хуесос", "Пойми что SkyCore убил тебя в 0", "У тебя есть сиська у меня пиписька и если мы будем рядом то нам будет заебисько!"};
   private final Minecraft mc = Minecraft.getInstance();
   private final Random random = new Random();
   private LivingEntity lastTrackedTarget = null;
   private float lastTargetHealth = -1.0F;
   private boolean killMessageSent = false;
   private int lastMessageIndex = -1;
   private long lastMessageTime = 0L;
   private String pendingKillMessage = null;
   private long pendingKillMessageTime = 0L;
   private long lastTotemMessageTime = 0L;

   public KillMessage() {
      super("KillMessage", Category.Miscellaneous);
      this.addSettings(new Setting[]{this.soft});
   }

   @Native
   private String replaceRandomLetter(String name) {
      Map letterMap = new HashMap();
      letterMap.put('p', 'р');
      letterMap.put('a', 'а');
      letterMap.put('e', 'е');
      letterMap.put('o', 'о');
      letterMap.put('k', 'к');
      letterMap.put('c', 'с');
      letterMap.put('x', 'х');
      letterMap.put('y', 'у');
      letterMap.put('P', 'Р');
      letterMap.put('A', 'А');
      letterMap.put('E', 'Е');
      letterMap.put('O', 'О');
      letterMap.put('K', 'К');
      letterMap.put('T', 'Т');
      letterMap.put('H', 'Н');
      letterMap.put('B', 'В');
      letterMap.put('M', 'М');
      letterMap.put('C', 'С');
      letterMap.put('X', 'Х');
      letterMap.put('Y', 'У');
      letterMap.put('r', 'г');
      letterMap.put('n', 'п');
      letterMap.put('N', 'И');
      letterMap.put('m', 'м');
      letterMap.put('b', 'ь');
      letterMap.put('w', 'ш');
      letterMap.put('W', 'Ш');
      letterMap.put('0', 'O');
      List replaceablePositions = new ArrayList();

      int randomPosition;
      for(randomPosition = 0; randomPosition < name.length(); ++randomPosition) {
         if (letterMap.containsKey(name.charAt(randomPosition))) {
            replaceablePositions.add(randomPosition);
         }
      }

      if (replaceablePositions.isEmpty()) {
         return name;
      } else {
         randomPosition = (Integer)replaceablePositions.get(this.random.nextInt(replaceablePositions.size()));
         char russianChar = (Character)letterMap.get(name.charAt(randomPosition));
         StringBuilder modified = new StringBuilder(name);
         modified.setCharAt(randomPosition, russianChar);
         return modified.toString();
      }
   }

   private String pickSpecialOrDefault(String[] specialMessages) {
      boolean useSpecial = this.random.nextBoolean();
      String[] messagesToUse = useSpecial ? specialMessages : killMessages;
      List availableIndices = new ArrayList();

      int messageIndex;
      for(messageIndex = 0; messageIndex < messagesToUse.length; ++messageIndex) {
         if ((Boolean)this.soft.get() || !messagesToUse[messageIndex].contains("SkyCore") || messagesToUse[messageIndex].contains("by SkyCore нахуй")) {
            availableIndices.add(messageIndex);
         }
      }

      if (availableIndices.isEmpty()) {
         return null;
      } else {
         messageIndex = (Integer)availableIndices.get(this.random.nextInt(availableIndices.size()));
         String msg = messagesToUse[messageIndex];
         if (!(Boolean)this.soft.get() && msg.endsWith(" by SkyCore нахуй")) {
            msg = msg.substring(0, msg.length() - " by SkyCore нахуй".length());
         }

         return msg;
      }
   }

   @Native
   private void sendKillMessage(LivingEntity deadTarget) {
      Minecraft var10000 = this.mc;
      if (Minecraft.player != null) {
         if (deadTarget instanceof PlayerEntity) {
            long currentTime = System.currentTimeMillis();
            String targetName = deadTarget.getName().getString();
            String displayName = deadTarget.getDisplayName().getString().toLowerCase();
            String modifiedName = this.replaceRandomLetter(targetName);
            List availableIndices = new ArrayList();

            int messageIndex;
            for(messageIndex = 0; messageIndex < killMessages.length; ++messageIndex) {
               if ((Boolean)this.soft.get() || !killMessages[messageIndex].contains("SkyCore") || killMessages[messageIndex].contains("by SkyCore нахуй")) {
                  availableIndices.add(messageIndex);
               }
            }

            if (!availableIndices.isEmpty()) {
               do {
                  messageIndex = (Integer)availableIndices.get(this.random.nextInt(availableIndices.size()));
               } while(messageIndex == this.lastMessageIndex && availableIndices.size() > 1);

               this.lastMessageIndex = messageIndex;
               String randomMessage = killMessages[messageIndex];
               if (!(Boolean)this.soft.get() && randomMessage.endsWith(" by SkyCore нахуй")) {
                  randomMessage = randomMessage.substring(0, randomMessage.length() - " by SkyCore нахуй".length());
               }

               if (randomMessage != null) {
                  String message = "!-" + modifiedName + randomMessage;
                  if (currentTime - this.lastMessageTime >= 3000L) {
                     var10000 = this.mc;
                     Minecraft.player.sendChatMessage(message);
                     this.lastMessageTime = currentTime;
                  } else {
                     this.pendingKillMessage = message;
                     this.pendingKillMessageTime = currentTime;
                  }

               }
            }
         }
      }
   }

   @Native
   private void sendTotemMessage(LivingEntity totemTarget) {
      Minecraft var10000 = this.mc;
      if (Minecraft.player != null) {
         if (totemTarget instanceof PlayerEntity) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - this.lastTotemMessageTime >= 15000L) {
               if (currentTime - this.lastMessageTime >= 3000L) {
                  String targetName = totemTarget.getName().getString();
                  this.replaceRandomLetter(targetName);
                  this.lastTotemMessageTime = currentTime;
                  this.lastMessageTime = currentTime;
               }
            }
         }
      }
   }

   @EventTarget
   public void onUpdate(EventUpdate event) {
      Minecraft var10000 = this.mc;
      if (Minecraft.player != null && this.mc.world != null) {
         long currentTime = System.currentTimeMillis();
         if (this.pendingKillMessage != null && currentTime - this.pendingKillMessageTime >= 3000L) {
            var10000 = this.mc;
            Minecraft.player.sendChatMessage(this.pendingKillMessage);
            this.lastMessageTime = currentTime;
            this.pendingKillMessage = null;
            this.pendingKillMessageTime = 0L;
         }

         LivingEntity currentTarget = AttackAura.getTarget();
         if (currentTarget != null && currentTarget instanceof PlayerEntity) {
            float currentHealth = currentTarget.getHealth();
            if (this.lastTrackedTarget != null && this.lastTrackedTarget == currentTarget) {
               if (this.lastTargetHealth > 0.0F && this.lastTargetHealth <= 3.0F && currentHealth > this.lastTargetHealth + 10.0F) {
                  this.sendTotemMessage(currentTarget);
               }

               if (!this.killMessageSent && this.lastTargetHealth > 0.0F && (currentHealth <= 0.0F || currentTarget.deathTime > 0 || !currentTarget.isAlive())) {
                  this.sendKillMessage(currentTarget);
                  this.killMessageSent = true;
               }
            }

            this.lastTrackedTarget = currentTarget;
            this.lastTargetHealth = currentHealth;
            if (currentTarget.isAlive() && currentHealth > 0.0F) {
               this.killMessageSent = false;
            }
         } else if (this.lastTrackedTarget != null && this.lastTrackedTarget instanceof PlayerEntity) {
            if (!this.killMessageSent && (this.lastTrackedTarget.deathTime > 0 || !this.lastTrackedTarget.isAlive() || this.lastTrackedTarget.getHealth() <= 0.0F)) {
               this.sendKillMessage(this.lastTrackedTarget);
               this.killMessageSent = true;
            }

            if (currentTarget == null) {
               this.lastTrackedTarget = null;
               this.lastTargetHealth = -1.0F;
               this.killMessageSent = false;
            }
         }

      }
   }

   public void onDisable() {
      super.onDisable();
      this.lastTrackedTarget = null;
      this.lastTargetHealth = -1.0F;
      this.killMessageSent = false;
      this.pendingKillMessage = null;
      this.lastMessageIndex = -1;
   }
}
